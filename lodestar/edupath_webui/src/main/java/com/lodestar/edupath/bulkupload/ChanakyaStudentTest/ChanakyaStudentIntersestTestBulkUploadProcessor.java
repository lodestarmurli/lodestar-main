package com.lodestar.edupath.bulkupload.ChanakyaStudentTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.bulkupload.BulkUploadMessage;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.bulkupload.UploadStatusEnum;
import com.lodestar.edupath.bulkupload.service.EduPathBulkUploadService;
import com.lodestar.edupath.bulkupload.util.ChanakyaStudentInterestTestExcelUploadDBUtil;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.ClassDAO;
import com.lodestar.edupath.dataaccessobject.dao.city.CityDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.PasswordGeneratorService;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelException;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;
import netvalence.commons.excel.utils.NetValenceExcelValidationException;

public class ChanakyaStudentIntersestTestBulkUploadProcessor {

	private static final Logger OUT = LoggerFactory.getLogger(ChanakyaStudentIntersestTestBulkUploadProcessor.class);

	private static final ChanakyaStudentIntersestTestBulkUploadProcessor instance = new ChanakyaStudentIntersestTestBulkUploadProcessor();

	private XLSEngine xlsEngine = null;

	private ExecutorService threadExecutor = null;

	private boolean inProgress;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");

	public static ChanakyaStudentIntersestTestBulkUploadProcessor getInstance() {
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
	public BulkUploadMessage uploadExcelTemplate(File excelFile, String fileName, String moduleName, String xmlFilePath,
			String loggedInUserFullName, int loggedInUserId, String loggedinUserLoginId) {
		BulkUploadMessage status = BulkUploadMessage.FAILURE_IN_PROGRESS;
		inProgress = true;
		try {
			this.threadExecutor = Executors.newFixedThreadPool(1);
			File file = copyFileToTempFolder(excelFile, fileName);
			OUT.debug(
					"bharath inside  StudentIntersestTestBulkUploadProcessor uploadExcelTemplate() fileName:{}, moduleName:{}, xmlFilePath{} ",
					fileName, moduleName, xmlFilePath);
			Callable<BulkUploadMessage> callable = new StudentInterstBulkUploadJob(file, fileName, xmlFilePath,
					loggedInUserFullName, moduleName, loggedInUserId, loggedinUserLoginId);
			threadExecutor.submit(callable);
			// status = future.get(); will wait for the thread to complete
		} catch (Exception e) {
			OUT.error("Exception", e);
		}
		return status;
	}

	/**
	 * @param excelFile
	 * @param fileName
	 * @return
	 */
	private File copyFileToTempFolder(File excelFile, String fileName) {
		File file = null;
		try {
			String path = System.getProperty("java.io.tmpdir") + File.separator;
			File dirs = new File(path);
			if (!dirs.exists())
				dirs.mkdirs();

			file = new File(path + File.separator + fileName);
			FileOutputStream stream = null;
			try {
				stream = new FileOutputStream(file);
				byte[] byteArray = FileUtils.readFileToByteArray(excelFile);
				stream.write(byteArray);
			} catch (Exception e) {
				OUT.error("Exception", e);
			} finally {
				if (null != stream) {
					stream.close();
				}
			}
		} catch (Exception e) {
			OUT.error("Exception", e);
		}
		return file;
	}

	private class StudentInterstBulkUploadJob implements Callable<BulkUploadMessage> {
		File uploadedFile;

		String fileName, xmlFilePath, loggedInUserName, moduleName, loggedInUserLoginId;

		int loggedInUserId;

		public StudentInterstBulkUploadJob(File uploadFile, String fileName, String xmlFilePath,
				String loggedInUserName, String moduleName, int loggedInUserId, String loggedInUserLoginId) {
			OUT.debug(
					"bharath inside  StudentIntersestTestBulkUploadProcessor StudentInterstBulkUploadJob() fileName:{}, moduleName:{}, xmlFilePath{} ",
					fileName, moduleName, xmlFilePath);
			this.uploadedFile = uploadFile;
			this.fileName = fileName;
			this.xmlFilePath = xmlFilePath;
			this.loggedInUserName = loggedInUserName;
			this.moduleName = moduleName;
			this.loggedInUserId = loggedInUserId;
			this.loggedInUserLoginId = loggedInUserLoginId;
		}

		@Override
		public BulkUploadMessage call() {
			String threadName = Thread.currentThread().getName();
			Thread.currentThread().setName(moduleName + " Excel Upload");
			OUT.debug("\n-----" + moduleName + " BULK UPLOAD PROCESSING STARTED for " + fileName + "-----");
			OUT.debug(
					"bharath inside  StudentIntersestTestBulkUploadProcessor call() fileName:{}, moduleName:{}, xmlFilePath{} ",
					fileName, moduleName, xmlFilePath);
			boolean isValidationSuccess = true;
			EduPathBulkUploadService service = new EduPathBulkUploadService();
			BulkUploadMessage returnMsg = BulkUploadMessage.FAILURE_IN_PROGRESS;
			try {
				OUT.info("Bulk upload processing started");
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.INPROGRESS,
						BulkUploadMessage.FAILURE_IN_PROGRESS);
				inProgress = true;

				URI uri = this.getClass().getClassLoader().getResource(xmlFilePath).toURI();
//				URI uri = this.getClass().getResource(xmlFilePath).toURI();
				xlsEngine = new XLSEngine(uri);
				List<NetValenceExcelRowObject> excelRowObjList = xlsEngine.readExcelFile(uploadedFile);
				OUT.info("excelRowObjList size: " + excelRowObjList.size());
				// System.out.println(" bharath
				// =================================================="+excelRowObjList);
				OUT.debug("excelRowObjList: {}", excelRowObjList);

				List<CityDTO> cityList = new CityDAO().getAllCityList();
				List<SchoolDTO> schoolList = new SchoolDAO().getAllSchoolList();
				List<ClassDTO> classList = new ClassDAO().getAllClassList();
				List<FacilitatorDetailsDTO> facilitatorList = new FacilitatorDetailsDAO().getActiveFacilitatorList();

				isValidationSuccess = true;//new KAGovStudentTestValidator().validate(xlsEngine, excelRowObjList);
				OUT.debug("Excel validation status:" + (isValidationSuccess ? "SUCCESS" : "FAILED"));
				if (!isValidationSuccess) {
					inProgress = false;
					service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.FAILED,
							BulkUploadMessage.FAILURE_VALIDATION);
					returnMsg = BulkUploadMessage.FAILURE_VALIDATION;
					return returnMsg;
				}
				EActionStatus persistStatus = new ChanakyaStudentInterestTestExcelUploadDBUtil().persistExcelObjects(
						xlsEngine, excelRowObjList, cityList, schoolList, classList, facilitatorList, loggedInUserName);
				UploadStatusEnum uploadStatus = UploadStatusEnum.COMPLETED;
				if (persistStatus == EActionStatus.SUCCESS) {
					returnMsg = BulkUploadMessage.SUCCESS;
					uploadStatus = UploadStatusEnum.COMPLETED;

				} else if (persistStatus == EActionStatus.FAILURE) {
					returnMsg = BulkUploadMessage.FAILURE_EXCEPTION;
					uploadStatus = UploadStatusEnum.FAILED;
				} else {
					returnMsg = BulkUploadMessage.FAILURE_GENERAL;
					uploadStatus = UploadStatusEnum.COMPLETED_WITH_ERROR;
				}
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, uploadStatus, returnMsg);
				OUT.info("File upload done!!");
			} catch (NetValenceExcelValidationException e) {
				OUT.error("NetValenceExcelValidationException", e);
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.FAILED,
						BulkUploadMessage.FAILURE_VALIDATION);
				returnMsg = BulkUploadMessage.FAILURE_VALIDATION;
				return BulkUploadMessage.FAILURE_VALIDATION;
			} catch (NetValenceExcelException e) {
				OUT.error("NetValenceExcelException", e);
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.FAILED,
						BulkUploadMessage.FAILURE_COLUMN_MISMATCH);
				returnMsg = BulkUploadMessage.FAILURE_COLUMN_MISMATCH;
				return BulkUploadMessage.FAILURE_COLUMN_MISMATCH;
			} catch (Exception e) {
				OUT.error("Exception", e);
				isValidationSuccess = false;
				inProgress = false;
				service.updateUploadStatus(moduleName, fileName, loggedInUserName, UploadStatusEnum.FAILED,
						BulkUploadMessage.FAILURE_GENERAL);
				returnMsg = BulkUploadMessage.FAILURE_EXCEPTION;
				return BulkUploadMessage.FAILURE_EXCEPTION;
			} finally {
				Thread.currentThread().setName(threadName);
				if (uploadedFile != null) {
					File file = createProcessedFile(fileName);

					if (returnMsg == BulkUploadMessage.SUCCESS) {
						GlobalSettingDTO globalDTO = new GlobalSettingDTO();
						globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.EMAIL_FOLDER_PATH.getProperty());
						try {
							globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
						} catch (Exception e) {
							// TODO Auto-generated catch block

						}
						if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty()) {
							String newFolder = null;
							String currentDir = globalDTO.getPropertyValue();
							newFolder = DATE_FORMAT.format(new Date()) + "_Admin";
							File dirs = new File(currentDir + newFolder);
							if (!dirs.exists()) {
								dirs.mkdirs();

							}
							if (newFolder != null) {
								File processedFile = new File(
										currentDir + newFolder + "/" + "Uploaded_Student_processed" + ".xls");
								try {
									file = xlsEngine.getErrorWorkbook(processedFile);

									try {

										PasswordGeneratorService.sendNewNotificationToAdmin(null, "Admin",
												"guidance@lodestar.guru", 2,
												NotificationConstant.MessageTemplateNameAndType.Admin_EMAIL_BULK_UPLOAD_RESULT
														.name(),
												newFolder, "Admin");

									} catch (Exception e) {
										// TODO Auto-generated catch block
										// e.printStackTrace();
									}

								} catch (IOException e) {
									// TODO Auto-generated catch block
									// e.printStackTrace();
								}
							}
						}
					}

				}
				inProgress = false;
				OUT.info("Notification Done!!");
			}

			return returnMsg;
		}
	}

	/**
	 * @param fileName
	 * @return
	 */
	private File createProcessedFile(String fileName) {
		File file = null;
		try {
			String path = System.getProperty("java.io.tmpdir") + File.separator;
			File dirs = new File(path);
			if (!dirs.exists()) {
				dirs.mkdirs();
			}
			File processedFile = new File(path + fileName.replace(".xls", "") + "_processed" + ".xls");
			file = xlsEngine.getErrorWorkbook(processedFile);
		} catch (Throwable e) {
			OUT.error("Exception", e);
		}
		return file;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

}
