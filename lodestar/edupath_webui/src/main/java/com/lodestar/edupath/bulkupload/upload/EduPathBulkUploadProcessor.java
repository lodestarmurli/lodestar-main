package com.lodestar.edupath.bulkupload.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelException;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;
import netvalence.commons.excel.utils.NetValenceExcelValidationException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.bulkupload.BulkUploadMessage;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.bulkupload.UploadStatusEnum;
import com.lodestar.edupath.bulkupload.service.EduPathBulkUploadService;
import com.lodestar.edupath.bulkupload.util.FacilitatorExcelUploadDBUtil;

public class EduPathBulkUploadProcessor
{
	private static final Logger						OUT				= LoggerFactory.getLogger(EduPathBulkUploadProcessor.class);

	private static final EduPathBulkUploadProcessor	instance		= new EduPathBulkUploadProcessor();

	private XLSEngine								xlsEngine		= null;

	private ExecutorService							threadExecutor	= null;

	private boolean									inProgress;

	public static EduPathBulkUploadProcessor getInstance()
	{
		return instance;
	}

	/** 
	 * @param excelFile
	 * @param fileName
	 * @param moduleName
	 * @param xmlFilePath
	 * @param loggedInUserFullName
	 * @param loggedinUserLoginId
	 * @return
	 */
	public BulkUploadMessage uploadExcelTemplate(File excelFile, String fileName, String moduleName, String xmlFilePath, String loggedInUserFullName,
			int loggedInUserId, String loggedinUserLoginId)
	{
		BulkUploadMessage status = BulkUploadMessage.FAILURE_IN_PROGRESS;
		inProgress = true;
		try
		{
			this.threadExecutor = Executors.newFixedThreadPool(1);
			File file = copyFileToTempFolder(excelFile, fileName);
			Callable<BulkUploadMessage> callable = new EduPathBulkUploadJob(file, fileName, xmlFilePath, loggedInUserFullName, moduleName, loggedInUserId,
					loggedinUserLoginId);
			threadExecutor.submit(callable);
			// status = future.get(); will wait for the thread to complete
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return status;
	}

	/**
	 * @param excelFile
	 * @param fileName
	 * @return
	 */
	private File copyFileToTempFolder(File excelFile, String fileName)
	{
		File file = null;
		try
		{
			String path = System.getProperty("java.io.tmpdir") + File.separator;
			File dirs = new File(path);
			if (!dirs.exists())
				dirs.mkdirs();

			file = new File(path + File.separator + fileName);
			FileOutputStream stream = null;
			try
			{
				stream = new FileOutputStream(file);
				byte[] byteArray = FileUtils.readFileToByteArray(excelFile);
				stream.write(byteArray);
			}
			catch (Exception e)
			{
				OUT.error("Exception", e);
			}
			finally
			{
				if (null != stream)
				{
					stream.close();
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return file;
	}

	private class EduPathBulkUploadJob implements Callable<BulkUploadMessage>
	{
		File	uploadedFile;

		String	fileName, xmlFilePath, loggedInUserName, moduleName, loggedInUserLoginId;

		int		loggedInUserId;

		public EduPathBulkUploadJob(File uploadFile, String fileName, String xmlFilePath, String loggedInUserName, String moduleName, int loggedInUserId,
				String loggedInUserLoginId)
		{
			this.uploadedFile = uploadFile;
			this.fileName = fileName;
			this.xmlFilePath = xmlFilePath;
			this.loggedInUserName = loggedInUserName;
			this.moduleName = moduleName;
			this.loggedInUserId = loggedInUserId;
			this.loggedInUserLoginId = loggedInUserLoginId;
		}

		@Override
		public BulkUploadMessage call()
		{
			String threadName = Thread.currentThread().getName();
			Thread.currentThread().setName(moduleName + " Excel Upload");
			OUT.debug("\n-----" + moduleName + " BULK UPLOAD PROCESSING STARTED for " + fileName + "-----");
			boolean isValidationSuccess = true;
			EduPathBulkUploadService service = new EduPathBulkUploadService();
			BulkUploadMessage returnMsg = BulkUploadMessage.FAILURE_IN_PROGRESS;
			try
			{
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.INPROGRESS, BulkUploadMessage.FAILURE_IN_PROGRESS);
				inProgress = true;

				URI uri = this.getClass().getClassLoader().getResource(xmlFilePath).toURI();
				xlsEngine = new XLSEngine(uri);
				List<NetValenceExcelRowObject> excelRowObjList = xlsEngine.readExcelFile(uploadedFile);
				OUT.debug("excelRowObjList: {}", excelRowObjList);

				isValidationSuccess = new FacilitatorExcelValidator().validate(xlsEngine, excelRowObjList);
				OUT.debug("Excel validation status:" + (isValidationSuccess ? "SUCCESS" : "FAILED"));
				if (!isValidationSuccess)
				{
					inProgress = false;
					service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.FAILED, BulkUploadMessage.FAILURE_VALIDATION);
					returnMsg = BulkUploadMessage.FAILURE_VALIDATION;
					return returnMsg;
				}
				EActionStatus persistStatus = new FacilitatorExcelUploadDBUtil().persistExcelObjects(xlsEngine, excelRowObjList, loggedInUserLoginId);
				UploadStatusEnum uploadStatus = UploadStatusEnum.COMPLETED;
				if (persistStatus == EActionStatus.SUCCESS)
				{
					returnMsg = BulkUploadMessage.SUCCESS;
					uploadStatus = UploadStatusEnum.COMPLETED;
				}
				else if (persistStatus == EActionStatus.FAILURE)
				{
					returnMsg = BulkUploadMessage.FAILURE_EXCEPTION;
					uploadStatus = UploadStatusEnum.FAILED;
				}
				else
				{
					returnMsg = BulkUploadMessage.FAILURE_GENERAL;
					uploadStatus = UploadStatusEnum.COMPLETED_WITH_ERROR;
				}
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, uploadStatus, returnMsg);
			}
			catch (NetValenceExcelValidationException e)
			{
				OUT.error("NetValenceExcelValidationException", e);
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.FAILED, BulkUploadMessage.FAILURE_VALIDATION);
				returnMsg = BulkUploadMessage.FAILURE_VALIDATION;
				return BulkUploadMessage.FAILURE_VALIDATION;
			}
			catch (NetValenceExcelException e)
			{
				OUT.error("NetValenceExcelException", e);
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.FAILED, BulkUploadMessage.FAILURE_COLUMN_MISMATCH);
				returnMsg = BulkUploadMessage.FAILURE_COLUMN_MISMATCH;
				return BulkUploadMessage.FAILURE_COLUMN_MISMATCH;
			}
			catch (Exception e)
			{
				OUT.error("Exception", e);
				isValidationSuccess = false;
				inProgress = false;
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.FAILED, BulkUploadMessage.FAILURE_GENERAL);
				returnMsg = BulkUploadMessage.FAILURE_EXCEPTION;
				return BulkUploadMessage.FAILURE_EXCEPTION;
			}
			finally
			{
				Thread.currentThread().setName(threadName);
				if (uploadedFile != null)
				{
					createProcessedFile(fileName);
				}
				inProgress = false;
			}
			return returnMsg;
		}
	}

	/**
	 * @param fileName
	 * @return
	 */
	private File createProcessedFile(String fileName)
	{
		File file = null;
		try
		{
			String path = System.getProperty("java.io.tmpdir") + File.separator;
			File dirs = new File(path);
			if (!dirs.exists())
			{
				dirs.mkdirs();
			}
			File processedFile = new File(path + fileName.replace(".xls", "") + "_processed" + ".xls");
			file = xlsEngine.getErrorWorkbook(processedFile);
		}
		catch (Throwable e)
		{
			OUT.error("Exception", e);
		}
		return file;
	}

	public boolean isInProgress()
	{
		return inProgress;
	}

	public void setInProgress(boolean inProgress)
	{
		this.inProgress = inProgress;
	}
}
