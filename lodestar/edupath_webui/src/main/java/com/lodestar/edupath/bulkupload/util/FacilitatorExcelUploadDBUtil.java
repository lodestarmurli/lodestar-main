package com.lodestar.edupath.bulkupload.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.EducationLevelDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.LanguagesDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.facilitator.service.FacilitatorDetailsService;
import com.lodestar.edupath.util.ApplicationProperties;

public class FacilitatorExcelUploadDBUtil
{
	private static final Logger		OUT						= LoggerFactory.getLogger(FacilitatorExcelUploadDBUtil.class);
	public static final int			excelNumberOfRows		= 100;
	private String					type;
	private String					highestQualification;
	private String					city;
	private Map<String, Integer>	cityMap					= null;
	private Map<String, Integer>	highestQualificationMap	= null;

	public List<NetValenceExcelRowObject> prepareTemplate()
	{
		FacilitatorDetailsDAO facilitatorDetailsDAO = new FacilitatorDetailsDAO();
		List<NetValenceExcelRowObject> rowObjectList = new ArrayList<NetValenceExcelRowObject>();
		NetValenceExcelRowObject excelRowObject = null;
		NetValenceExcelCellVO cellVO = null;
		try
		{
			setColumnHeadersLabel();

			List<String> typeList = new ArrayList<String>();
			typeList.add("Full Time");
			typeList.add("Part Time");

			List<String> cityList = new ArrayList<String>();
			setCityList(cityList, facilitatorDetailsDAO);

			List<String> highestQualificationList = new ArrayList<String>();
			setHighestQualificationList(highestQualificationList, facilitatorDetailsDAO);

			for (int i = 0; i < excelNumberOfRows; i++)
			{
				excelRowObject = new NetValenceExcelRowObject();

				// type list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(typeList);
				excelRowObject.addRowItem(type, cellVO);

				// city list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValue("");
				cellVO.setCustomListColumn(true);
				excelRowObject.addRowItem(city, cellVO);

				// highest qualification list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(highestQualificationList);
				excelRowObject.addRowItem(highestQualification, cellVO);

				rowObjectList.add(excelRowObject);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return rowObjectList;
	}

	private void setHighestQualificationList(List<String> highestQualificationList1, FacilitatorDetailsDAO facilitatorDetailsDAO)
	{
		try
		{
			List<EducationLevelDTO> highestQualificationList = facilitatorDetailsDAO.getEducationLevelNameWithId();
			for (EducationLevelDTO educationLevelDTO : highestQualificationList)
			{
				highestQualificationList1.add(educationLevelDTO.getName());
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}

	public void setCityList(List<String> cityList1, FacilitatorDetailsDAO facilitatorDetailsDAO)
	{
		try
		{
			List<CityDTO> cityList = facilitatorDetailsDAO.getCityNameWithId();
			for (CityDTO cityDTO : cityList)
			{
				cityList1.add(cityDTO.getName());
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}

	private void setColumnHeadersLabel()
	{
		ApplicationProperties properties = ApplicationProperties.getInstance();
		type = properties.getProperty("com.edupath.facilitator.details.type");
		city = properties.getProperty("com.edupath.facilitator.details.city");
		highestQualification = properties.getProperty("com.edupath.facilitator.details.qualification");
	}

	public EActionStatus persistExcelObjects(XLSEngine xlsEngine, List<NetValenceExcelRowObject> excelRowObjList, String loggedInUserLoginId)
	{
		ApplicationProperties properties = ApplicationProperties.getInstance();
		FacilitatorDetailsDAO facilitatorDetailsDAO = new FacilitatorDetailsDAO();
		int totalRecords = 0;
		int successfull = 0;
		int failed = 0;
		try
		{
			List<CityDTO> cityList = facilitatorDetailsDAO.getCityNameWithId();
			cityMap = new HashMap<String, Integer>();
			for (CityDTO cityDTO : cityList)
			{
				cityMap.put(cityDTO.getName(), cityDTO.getId());
			}

			List<EducationLevelDTO> highestQualificationList = facilitatorDetailsDAO.getEducationLevelNameWithId();
			highestQualificationMap = new HashMap<String, Integer>();
			for (EducationLevelDTO educationLevelDTO : highestQualificationList)
			{
				highestQualificationMap.put(educationLevelDTO.getName(), educationLevelDTO.getId());
			}

			NetValenceExcelRowObject excelRowObject;
			Map<String, NetValenceExcelCellVO> rowDataMap;
			for (int i = 0; i < excelRowObjList.size(); i++)
			{
				excelRowObject = excelRowObjList.get(i);
				rowDataMap = excelRowObject.getRowData();

				totalRecords++;
				if (addDetails(excelRowObject, xlsEngine, rowDataMap, properties, loggedInUserLoginId))
				{
					OUT.debug("facilitator details added successfully");
					successfull++;
				}
				else
				{
					OUT.debug("facilitator details insertion failed");
					failed++;
				}
			}

			OUT.debug("Total Records:" + totalRecords + ", Successful:" + successfull + ", Failed:" + failed);
			if (totalRecords == successfull)
			{
				return EActionStatus.SUCCESS;
			}
			else
			{
				return EActionStatus.FAILURE;
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return EActionStatus.FAILURE;
	}

	private boolean addDetails(NetValenceExcelRowObject excelRowObject, XLSEngine xlsEngine, Map<String, NetValenceExcelCellVO> rowDataMap,
			ApplicationProperties properties, String loggedInUserLoginId)
	{
		boolean isInserted = false;
		NetValenceExcelCellVO statusCellVO = rowDataMap.get(properties.getProperty("com.edupath.bulkupload.summary.label.status"));
		FacilitatorDetailsDTO facilitatorDetailsDTO = prepareFacilitator(rowDataMap, properties);
		FacilitatorDetailsService service = new FacilitatorDetailsService();
		isInserted = service.insertFacilitatorDetails(facilitatorDetailsDTO);
		if (isInserted)
		{
			xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "SUCCESS");
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.USER_DEFINITION, "Facilitator :" + facilitatorDetailsDTO.getEmailId()
					+ " created by bulk upload and cretaed On : " + service.getAuditDateStr(), loggedInUserLoginId);
			return isInserted;
		}
		xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "FAILURE");
		return isInserted;
	}

	private FacilitatorDetailsDTO prepareFacilitator(Map<String, NetValenceExcelCellVO> rowDataMap, ApplicationProperties properties)
	{
		FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDTO();
		facilitatorDetailsDTO.setName(rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.name")).getValue().trim());
		facilitatorDetailsDTO.setPanNumber(rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.pannumber")).getValue().trim());
		facilitatorDetailsDTO.setEmailId(rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.email")).getValue().trim());
		facilitatorDetailsDTO.setAltEmailId(rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.altemail")).getValue().trim());
		facilitatorDetailsDTO.setPhoneNumber(rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.phonenumber")).getValue().trim());
		facilitatorDetailsDTO.setAltPhoneNumber(rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.altphonenumber")).getValue().trim());
		facilitatorDetailsDTO.setStreetAddr(rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.street")).getValue().trim());
		facilitatorDetailsDTO.setAreaAddr(rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.area")).getValue().trim());
		facilitatorDetailsDTO.setIsActive(true);

		String dobStr = rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.dob")).getValue().trim();
		if (dobStr != null && !dobStr.equals(""))
		{
			try
			{
				facilitatorDetailsDTO.setDob(TimeUtil.getDate(dobStr, TimeUtil.EXCEL_DATE_FORMAT));
			}
			catch (ParseException e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}

		String reviewer = rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.reviewer")).getValue().trim();
		if (reviewer != null && !reviewer.equals(""))
		{
			if (reviewer.equalsIgnoreCase("yes"))
			{
				facilitatorDetailsDTO.setIsReviewer(true);
			}
			else if (reviewer.equalsIgnoreCase("no"))
			{
				facilitatorDetailsDTO.setIsReviewer(false);
			}
		}
		else
		{
			facilitatorDetailsDTO.setIsReviewer(false);
		}

		String type = rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.type")).getValue().trim();
		if (type != null && !type.equals(""))
		{
			if (type.equalsIgnoreCase("Full Time"))
			{
				facilitatorDetailsDTO.setType("F");
			}
			else
			{
				facilitatorDetailsDTO.setType("P");
			}
		}
		else
		{
			facilitatorDetailsDTO.setType("F");
		}

		String city = rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.city")).getValue().trim();
		if (city != null && !city.equals(""))
		{
			int cityId = getCityId(city);
			facilitatorDetailsDTO.setCityId(cityId);
		}

		String qualification = rowDataMap.get(properties.getProperty("com.edupath.facilitator.details.qualification")).getValue().trim();
		if (qualification != null && !qualification.equals(""))
		{
			int qualificationId = getQualificationId(qualification);
			facilitatorDetailsDTO.setHighestQualificationId(qualificationId);
		}

		String languages = rowDataMap.get(properties.getProperty("com.edupath.bulkupload.details.languages")).getValue().trim();
		String[] languagesArray = languages.split(",");
		List<LanguagesDTO> langList = new FacilitatorDetailsService().getLanguages();
		List<String> list = new ArrayList<>();

		for (int i = 0; i < languagesArray.length; i++)
		{
			String value = languagesArray[i].trim();
			for (LanguagesDTO langDTO : langList)
			{
				if (value.equalsIgnoreCase(langDTO.getName().trim()))
				{
					list.add(value);
				}
			}
		}
		facilitatorDetailsDTO.setAdditionalLanguages(StringUtils.join(list, ","));
		return facilitatorDetailsDTO;
	}

	private int getQualificationId(String qualification)
	{
		return highestQualificationMap.get(qualification);
	}

	private int getCityId(String cityName)
	{
		return cityMap.get(cityName);
	}

}
