package com.lodestar.edupath.bulkupload.service;

import java.io.File;
import java.net.URI;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.bulkupload.BulkUploadMessage;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.bulkupload.ModuleEnum;
import com.lodestar.edupath.bulkupload.UploadStatusEnum;
import com.lodestar.edupath.bulkupload.util.CDDStudentExcelUploadDBUtil;
import com.lodestar.edupath.bulkupload.util.FacilitatorExcelUploadDBUtil;
import com.lodestar.edupath.bulkupload.util.InterestTestExcelUploadDBUtil;
import com.lodestar.edupath.bulkupload.util.PaymenttExcelUploadDBUtil;
import com.lodestar.edupath.bulkupload.util.StudentExcelUploadDBUtil;
import com.lodestar.edupath.bulkupload.util.StudentInterestTestExcelUploadDBUtil;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.BulkUploadStatusDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;
import netvalence.commons.excel.utils.NetValenceExcelSheetVO;

public class EduPathBulkUploadService
{
	private static final Logger				OUT			= LoggerFactory.getLogger(EduPathBulkUploadService.class);

	private static final SimpleDateFormat	DATE_FORMAT	= new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");

	/**
	 * @param excelRowsList
	 * @param xmlTemplatePath
	 * @param excelFileName
	 * @param moduleName
	 * @return
	 * @throws Exception
	 */

	public File generateXLSFile(List<NetValenceExcelRowObject> excelRowsList, String xmlTemplatePath, String excelFileName, String moduleName) throws Exception
	{
		String currentDir = System.getProperty("java.io.tmpdir");
		if (currentDir.indexOf("bin") > 0)
		{
			currentDir = currentDir.substring(0, currentDir.indexOf("bin"));
		}
		currentDir += "upload_template";
		File dirs = new File(currentDir + File.separator);
		if (!dirs.exists())
			dirs.mkdirs();

		File file = new File(currentDir + File.separator + excelFileName + "_" + DATE_FORMAT.format(new Date()) + ".xls");
		if (!file.exists())
		{
			file.createNewFile();
		}

		// Generating xls template
		// OUT.debug("excelRowsList: {},file: {}", excelRowsList, file);
		OUT.debug("Class: {},Load: {}", this.getClass(), this.getClass().getClassLoader());
		URI uri = this.getClass().getClassLoader().getResource(xmlTemplatePath).toURI();
		try
		{
			XLSEngine xlsEngine = new XLSEngine(uri);
			// xlsEngine.writeExcelFile(file, excelRowsList);
			
			
			//Start Sasedeve Edited by vyankatesh on date 3-2-2017
			
			//Original code
			//if (ModuleEnum.INTEREST_TEST.getModuleName().equalsIgnoreCase(moduleName))
			//{
			//	xlsEngine.writeExcelFile(file, excelRowsList);
			//}
			//Original code
			// Start change code
			if (ModuleEnum.INTEREST_TEST.getModuleName().equalsIgnoreCase(moduleName) || ModuleEnum.PAYMENT.getModuleName().equalsIgnoreCase(moduleName))
			{
				xlsEngine.writeExcelFile(file, excelRowsList);
			}
			//End Sasedeve Edited by vyankatesh on date 3-2-2017
			else
			{
				processXLSFile(excelRowsList, file, moduleName, xlsEngine);
			}
			OUT.info("Excel created successfully");
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return file;
	}

	public List<NetValenceExcelRowObject> prepareTemplate(List<? extends Object> summaryList, String moduleName)
	{
		if (moduleName.equalsIgnoreCase(ModuleEnum.FACILITATORS.getModuleName()))
		{
			return new FacilitatorExcelUploadDBUtil().prepareTemplate();
		}
		else if (moduleName.equalsIgnoreCase(ModuleEnum.CDDSTUDENT.getModuleName())) 
		{
		return new CDDStudentExcelUploadDBUtil().prepareTemplate();
		}
		else if (moduleName.equalsIgnoreCase(ModuleEnum.STUDENT.getModuleName()))
		{
			return new StudentExcelUploadDBUtil().prepareTemplate();
		}
		else if (ModuleEnum.INTEREST_TEST.getModuleName().equalsIgnoreCase(moduleName))
		{
			return new InterestTestExcelUploadDBUtil().prepareTemplate();
		}
		else if(moduleName.equalsIgnoreCase(ModuleEnum.PAYMENT.getModuleName()))
		{
			return new PaymenttExcelUploadDBUtil().prepareTemplate();
		}
		else if(moduleName.equalsIgnoreCase(ModuleEnum.STUDENT_INTEREST_TEST.getModuleName()))
		{
			return new StudentInterestTestExcelUploadDBUtil().prepareTemplate();
		}
		else
		{
			OUT.warn("Invalid Modulename Identified while Bulk Upload");
			return null;
		}
	}

	private void processXLSFile(List<NetValenceExcelRowObject> excelRowsList, File file, String moduleName, XLSEngine xlsEngine)
	{
		try
		{
			List<NetValenceExcelSheetVO> excelSheetVOList = new ArrayList<NetValenceExcelSheetVO>();
			NetValenceExcelSheetVO targetSheetVO = new NetValenceExcelSheetVO();
			targetSheetVO.setSheetName("Sheet1");
			targetSheetVO.setHidden(false);
			excelSheetVOList.add(targetSheetVO);
			String targetSheetName = targetSheetVO.getSheetName();
			if (moduleName.equalsIgnoreCase(ModuleEnum.CDDSTUDENT.getModuleName()))
			{
				// Hidden sheet data populate process
				xlsEngine.createWorkBookAndSheet(excelSheetVOList);

			}
			else if (moduleName.equalsIgnoreCase(ModuleEnum.STUDENT.getModuleName()))
			{
				StudentExcelUploadDBUtil studentExcelUploadDBUtil = new StudentExcelUploadDBUtil();

				List<String> cityList = new ArrayList<String>();
				studentExcelUploadDBUtil.setCityList(cityList, null);

				NetValenceExcelSheetVO citySheet = new NetValenceExcelSheetVO();
				citySheet.setSheetName("cityList");
				citySheet.setHidden(true);
				excelSheetVOList.add(citySheet);

				List<String> schoolList = new ArrayList<String>();
				studentExcelUploadDBUtil.setSchoolList(schoolList, null);

				NetValenceExcelSheetVO schoolSheet = new NetValenceExcelSheetVO();
				schoolSheet.setSheetName("schoolList");
				schoolSheet.setHidden(true);
				excelSheetVOList.add(schoolSheet);

				List<String> facilitatorList = new ArrayList<String>();
				studentExcelUploadDBUtil.setFacilitatorList(facilitatorList, null);

				NetValenceExcelSheetVO facilitatorSheet = new NetValenceExcelSheetVO();
				facilitatorSheet.setSheetName("facilitatorList");
				facilitatorSheet.setHidden(true);
				excelSheetVOList.add(facilitatorSheet);

				String referSheetName = citySheet.getSheetName();
				StringBuilder referFormula = new StringBuilder("");
				referFormula.append(referSheetName).append("!$A$1:$A$").append(cityList.size());

				// Hidden sheet data populate process
				xlsEngine.createWorkBookAndSheet(excelSheetVOList);
				xlsEngine.populateHiddenSheet(referSheetName, cityList);
				xlsEngine.attachReferFormulaToWorkBook(referSheetName, referFormula.toString());
				//xlsEngine.addDataValidation(targetSheetName, referSheetName, StudentExcelUploadDBUtil.excelNumberOfRows, 4, 2);
				xlsEngine.addDataValidation(targetSheetName, referSheetName, StudentExcelUploadDBUtil.excelNumberOfRows, 3, 2);

				StringBuilder schoolSB = new StringBuilder("");
				schoolSB.append(schoolSheet.getSheetName()).append("!$A$1:$A$").append(schoolList.size());
				// Hidden sheet data populate process
				xlsEngine.populateHiddenSheet(schoolSheet.getSheetName(), schoolList);
				xlsEngine.attachReferFormulaToWorkBook(schoolSheet.getSheetName(), schoolSB.toString());
				//xlsEngine.addDataValidation(targetSheetName, schoolSheet.getSheetName(), StudentExcelUploadDBUtil.excelNumberOfRows, 5, 2);
				xlsEngine.addDataValidation(targetSheetName, schoolSheet.getSheetName(), StudentExcelUploadDBUtil.excelNumberOfRows, 4, 2);
				

				StringBuilder facilitatorSB = new StringBuilder("");
				facilitatorSB.append(facilitatorSheet.getSheetName()).append("!$A$1:$A$").append(facilitatorList.size());
				// Hidden sheet data populate process
				xlsEngine.populateHiddenSheet(facilitatorSheet.getSheetName(), facilitatorList);
				xlsEngine.attachReferFormulaToWorkBook(facilitatorSheet.getSheetName(), facilitatorSB.toString());
				//xlsEngine.addDataValidation(targetSheetName, facilitatorSheet.getSheetName(), StudentExcelUploadDBUtil.excelNumberOfRows, 13, 2);
				xlsEngine.addDataValidation(targetSheetName, facilitatorSheet.getSheetName(), StudentExcelUploadDBUtil.excelNumberOfRows, 18, 2);

			}
			else if (moduleName.equalsIgnoreCase(ModuleEnum.FACILITATORS.getModuleName()))
			{
				FacilitatorExcelUploadDBUtil facilitatorExcelUploadDBUtil = new FacilitatorExcelUploadDBUtil();
				FacilitatorDetailsDAO facilitatorDetailsDAO = new FacilitatorDetailsDAO();
				List<String> cityList = new ArrayList<String>();
				facilitatorExcelUploadDBUtil.setCityList(cityList, facilitatorDetailsDAO);

				NetValenceExcelSheetVO referSheetVO = new NetValenceExcelSheetVO();
				referSheetVO.setSheetName("cityList");
				referSheetVO.setHidden(true);
				excelSheetVOList.add(referSheetVO);

				String referSheetName = referSheetVO.getSheetName();
				StringBuilder referFormula = new StringBuilder("");
				referFormula.append(referSheetName).append("!$A$1:$A$").append(cityList.size());
				// Hidden sheet data populate process
				xlsEngine.createWorkBookAndSheet(excelSheetVOList);
				xlsEngine.populateHiddenSheet(referSheetName, cityList);
				xlsEngine.attachReferFormulaToWorkBook(referSheetName, referFormula.toString());
				xlsEngine.addDataValidation(targetSheetName, referSheetName, StudentExcelUploadDBUtil.excelNumberOfRows, 12, 2);
				xlsEngine.populateData(file, targetSheetName, excelRowsList, false);
			}
			else if (moduleName.equalsIgnoreCase(ModuleEnum.STUDENT_INTEREST_TEST.getModuleName()))
			{
				StudentInterestTestExcelUploadDBUtil studentExcelUploadDBUtil = new StudentInterestTestExcelUploadDBUtil();

				List<String> cityList = new ArrayList<String>();
				studentExcelUploadDBUtil.setCityList(cityList, null);

				NetValenceExcelSheetVO citySheet = new NetValenceExcelSheetVO();
				citySheet.setSheetName("cityList");
				citySheet.setHidden(true);
				excelSheetVOList.add(citySheet);

				List<String> schoolList = new ArrayList<String>();
				studentExcelUploadDBUtil.setSchoolList(schoolList, null);

				NetValenceExcelSheetVO schoolSheet = new NetValenceExcelSheetVO();
				schoolSheet.setSheetName("schoolList");
				schoolSheet.setHidden(true);
				excelSheetVOList.add(schoolSheet);

				List<String> facilitatorList = new ArrayList<String>();
				studentExcelUploadDBUtil.setFacilitatorList(facilitatorList, null);

				NetValenceExcelSheetVO facilitatorSheet = new NetValenceExcelSheetVO();
				facilitatorSheet.setSheetName("facilitatorList");
				facilitatorSheet.setHidden(true);
				excelSheetVOList.add(facilitatorSheet);

				String referSheetName = citySheet.getSheetName();
				StringBuilder referFormula = new StringBuilder("");
				referFormula.append(referSheetName).append("!$A$1:$A$").append(cityList.size());

				// Hidden sheet data populate process
				xlsEngine.createWorkBookAndSheet(excelSheetVOList);
				xlsEngine.populateHiddenSheet(referSheetName, cityList);
				xlsEngine.attachReferFormulaToWorkBook(referSheetName, referFormula.toString());
				xlsEngine.addDataValidation(targetSheetName, referSheetName, StudentExcelUploadDBUtil.excelNumberOfRows, 4, 2);

				StringBuilder schoolSB = new StringBuilder("");
				schoolSB.append(schoolSheet.getSheetName()).append("!$A$1:$A$").append(schoolList.size());
				// Hidden sheet data populate process
				xlsEngine.populateHiddenSheet(schoolSheet.getSheetName(), schoolList);
				xlsEngine.attachReferFormulaToWorkBook(schoolSheet.getSheetName(), schoolSB.toString());
				xlsEngine.addDataValidation(targetSheetName, schoolSheet.getSheetName(), StudentExcelUploadDBUtil.excelNumberOfRows, 5, 2);

				StringBuilder facilitatorSB = new StringBuilder("");
				facilitatorSB.append(facilitatorSheet.getSheetName()).append("!$A$1:$A$").append(facilitatorList.size());
				// Hidden sheet data populate process
				xlsEngine.populateHiddenSheet(facilitatorSheet.getSheetName(), facilitatorList);
				xlsEngine.attachReferFormulaToWorkBook(facilitatorSheet.getSheetName(), facilitatorSB.toString());
				xlsEngine.addDataValidation(targetSheetName, facilitatorSheet.getSheetName(), StudentExcelUploadDBUtil.excelNumberOfRows, 19, 2);

			}
			
			
			// Final sheet data populate process
			xlsEngine.populateData(file, targetSheetName, excelRowsList, false);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	/*
	*//**
	 * @param fileName
	 * @return
	 */

	public File showProcessedFile(String fileName)
	{
		String path = System.getProperty("java.io.tmpdir") + File.separator;
		File dirs = new File(path);
		if (!dirs.exists())
			dirs.mkdirs();

		File file = new File(path + fileName.replace(".xls", "") + "_processed" + ".xls");
		return file;
	}

	/**
	 * @param moduleName
	 * @param fileName
	 * @param loggedInUserName
	 * @param success
	 * @param inprogress
	 * @return
	 */

	public EActionStatus updateUploadStatus(String moduleName, String fileName, String loggedInUserName, UploadStatusEnum statusValue, BulkUploadMessage message)
	{
		OUT.debug("Getting record from bulkuploadstatus table for modulename: {}", moduleName);
		BulkUploadStatusDTO excelUploadVO = getExcelUploadDetails(moduleName);
		EActionStatus status = null;
		if (statusValue == UploadStatusEnum.INPROGRESS)
		{
			excelUploadVO = new BulkUploadStatusDTO();
			excelUploadVO.setModule(moduleName);
			excelUploadVO.setFileName(fileName);
			excelUploadVO.setUploadedAt(new Timestamp(System.currentTimeMillis()));
			excelUploadVO.setUploadedBy(loggedInUserName);
			excelUploadVO.setStatus(statusValue.getValue());
			excelUploadVO.setMessage(message.getDisplayValue());
			status = insertUploadDetails(excelUploadVO);
		}
		else
		{
			excelUploadVO.setCompletedAt(new Timestamp(System.currentTimeMillis()));
			excelUploadVO.setStatus(statusValue.getValue());
			excelUploadVO.setMessage(message.getDisplayValue());
			status = modifyUploadDetails(excelUploadVO);
		}
		OUT.debug("excelUploadVO values : excel fileName: {}, excel UploadedAt: {}, excel Status: {}, EActionStatus Status: {}", excelUploadVO.getFileName(),
				excelUploadVO.getUploadedAt(), excelUploadVO.getStatus(), status);
		return status;
	}

	/*
	 * public void updateInProgressRecords()
	 * {
	 * try
	 * {
	 * BulkUploadStatusDTO params = new BulkUploadStatusDTO();
	 * params.setMessage(BulkUploadMessage.FAILURE_SERVER.getDisplayValue());
	 * params.setStatus(UploadStatusEnum.FAILED.getValue());
	 * params.setCompletedAt(new Timestamp(System.currentTimeMillis()));
	 * DBManager.getInstance().update(BulkUploadStatusDTO.UPDATE_IN_PROGRESS_RECORDS, params);
	 * }
	 * catch (Exception e)
	 * {
	 * OUT.error("Exception", e);
	 * }
	 * }
	 */

	/**
	 * @return
	 */
	public BulkUploadStatusDTO getExcelUploadDetails(String module)
	{
		try
		{
			BulkUploadStatusDTO uploadParams = new BulkUploadStatusDTO();
			uploadParams.setModule(module);
			List<BulkUploadStatusDTO> uploadList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_DETAILS, uploadParams, 0, 1);
			if (uploadList != null && uploadList.size() > 0)
			{
				return uploadList.get(0);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}

	/**
	 * @param excelUploadVO
	 * @return
	 */

	public EActionStatus insertUploadDetails(BulkUploadStatusDTO excelUploadVO)
	{
		try
		{
			MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_UPLOAD_DETAILS, excelUploadVO);
			return EActionStatus.SUCCESS;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return EActionStatus.FAILURE;
	}

	/**
	 * @param excelUploadVO
	 * @return
	 */

	public EActionStatus modifyUploadDetails(BulkUploadStatusDTO excelUploadVO)
	{
		try
		{
			MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_STATUS_TIME, excelUploadVO);
			return EActionStatus.SUCCESS;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return EActionStatus.FAILURE;
	}

	// public List<String> getCompanyNameAndId()
	// {
	// List<String> contractorDetailList = null;
	//
	// try
	// {
	// contractorDetailList = DBManager.getInstance().getResultAsList(ContractorDetailDTO.GET_COMPANY_NAME_AND_ID, null);
	// }
	// catch (Exception e)
	// {
	// OUT.error("Exception", e);
	// }
	// return contractorDetailList;
	// }

	// public File generateXLSFile(List<NetValenceExcelRowObject> excelRowsList, String xmlTemplatePath, String excelFileName, String moduleName)
	// {
	// File file = null;
	// try
	// {
	// String path = System.getProperty("java.io.tmpdir") + File.separator;
	// File dirs = new File(path);
	// if (!dirs.exists())
	// dirs.mkdirs();
	//
	// file = new File(path + excelFileName + "_" + DATE_FORMAT.format(new Date()) + ".xls");
	//
	// // Generating xls template
	// URI uri = this.getClass().getClassLoader().getResource(xmlTemplatePath).toURI();
	// XLSEngine xlsEngine = new XLSEngine(uri);
	// processXLSFile(excelRowsList, file, moduleName, xlsEngine);
	// OUT.info("Excel created successfully");
	// }
	// catch (Exception e)
	// {
	// OUT.error("Exception", e);
	// }
	// return file;
	// }

	// private void processXLSFile(List<NetValenceExcelRowObject> excelRowsList, File file, String moduleName, XLSEngine xlsEngine)
	// {
	// if (moduleName.equalsIgnoreCase(ModuleNameEnum.CONTRACTOR_COMPLIANCE.getDisplayName()))
	// {
	// try
	// {
	// List<String> companyNameList = getCompanyNameAndId();
	// List<NetValenceExcelSheetVO> excelSheetVOList = new ArrayList<NetValenceExcelSheetVO>();
	// NetValenceExcelSheetVO targetSheetVO = new NetValenceExcelSheetVO();
	// targetSheetVO.setSheetName("Sheet1");
	// targetSheetVO.setHidden(false);
	// excelSheetVOList.add(targetSheetVO);
	//
	// NetValenceExcelSheetVO referSheetVO = new NetValenceExcelSheetVO();
	// referSheetVO.setSheetName("CompanyList");
	// referSheetVO.setHidden(true);
	// excelSheetVOList.add(referSheetVO);
	//
	// String targetSheetName = targetSheetVO.getSheetName();
	// String referSheetName = referSheetVO.getSheetName();
	// StringBuilder referFormula = new StringBuilder("");
	// referFormula.append(referSheetName).append("!$A$1:$A$").append(companyNameList.size());
	// // Hidden sheet data populate process
	// xlsEngine.createWorkBookAndSheet(excelSheetVOList);
	// xlsEngine.populateHiddenSheet(referSheetName, companyNameList);
	// xlsEngine.attachReferFormulaToWorkBook(referSheetName, referFormula.toString());
	// xlsEngine.addDataValidation(targetSheetName, referSheetName, ContractorComplianceExcelUploadDBUtil.excelNumberOfRows, 2, 2);
	// // Final sheet data populate process
	// xlsEngine.populateData(file, targetSheetName, excelRowsList, false);
	// }
	// catch (Exception e)
	// {
	// OUT.error("Exception", e);
	// }
	// }
	// }
}
