package com.lodestar.edupath.bulkupload.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.ClassDAO;
import com.lodestar.edupath.dataaccessobject.dao.city.CityDAO;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.GenderTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.VenueTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.student.service.Studentinteresttestbuluploadservice;
import com.lodestar.edupath.util.ApplicationProperties;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

public class StudentInterestTestExcelUploadDBUtil {
	
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentInterestTestExcelUploadDBUtil.class);
	public static final int		excelNumberOfRows	= 1;
	private String				gender;
	private String				city;
	private String				school;
	private String				className;
	private String				facilitator;
	private String				session1Time;
	private String				session2Time;
	private String				session3Time;
	private String				venue;
	private String				studentType;
	private String				studentTest;
	private String				section;
	
	
	

	public List<NetValenceExcelRowObject> prepareTemplate() {

		List<NetValenceExcelRowObject> rowObjectList = new ArrayList<NetValenceExcelRowObject>();
		NetValenceExcelRowObject excelRowObject = null;
		NetValenceExcelCellVO cellVO = null;
		try
		{
			setColumnHeadersLabel();

			List<String> genderList = new ArrayList<String>();
			setGenderList(genderList);

			List<String> cityList = new ArrayList<String>();
			setCityList(cityList, null);

			List<String> schoolList = new ArrayList<String>();
			setSchoolList(schoolList, null);

			List<String> classList = new ArrayList<String>();
			setClassList(classList, null);

			List<String> facilitatorList = new ArrayList<String>();
			setFacilitatorList(facilitatorList, null);

			List<String> timeList = new ArrayList<String>();
			setTimeList(timeList);

			//Start Sasedeve Edited By Mrutyunjaya on Date 13-10-2017
			
			timeList.remove("9:00");
			timeList.remove("9:15");
			timeList.remove("9:30");
			timeList.remove("9:45");
			
			
			List<String> valueList = new ArrayList<String>();
			setVenueList(valueList);

			valueList.remove("school");
			
			//END Sasedeve Edited By Mrutyunjaya on Date 13-10-2017
			
			List<String> studentTypeList = new ArrayList<String>();
			setStudentTypeList(studentTypeList);

			List<String> studenttestTakenList = new ArrayList<String>();
			setStudentTestTakenList(studenttestTakenList);

			for (int i = 0; i < excelNumberOfRows; i++)
			{
				excelRowObject = new NetValenceExcelRowObject();

				// gender list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(genderList);
				excelRowObject.addRowItem(gender, cellVO);

				// city list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValue("");
				cellVO.setCustomListColumn(true);
				excelRowObject.addRowItem(city, cellVO);

				// school list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValue("");
				cellVO.setCustomListColumn(true);
				excelRowObject.addRowItem(school, cellVO);

				// classList
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(classList);
				excelRowObject.addRowItem(className, cellVO);

				// section
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValue("");
				excelRowObject.addRowItem(section, cellVO);

				// facilitator list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValue("");
				cellVO.setCustomListColumn(true);
				excelRowObject.addRowItem(facilitator, cellVO);

				// Student Type list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(studentTypeList);
				excelRowObject.addRowItem(studentType, cellVO);

				// Student Test Taken list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(studenttestTakenList);
				excelRowObject.addRowItem(studentTest, cellVO);

				// session1Time list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(timeList);
				excelRowObject.addRowItem(session1Time, cellVO);

				// session2Time list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(timeList);
				excelRowObject.addRowItem(session2Time, cellVO);

				// session3Time list
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(timeList);
				excelRowObject.addRowItem(session3Time, cellVO);

				// set Venue List
				cellVO = new NetValenceExcelCellVO();
				cellVO.setValueList(valueList);
				excelRowObject.addRowItem(venue, cellVO);
				rowObjectList.add(excelRowObject);

			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return rowObjectList;
	}
	public void setTimeList(List<String> timeList)
	{
		int startTime = 9;
		for (; startTime <= 20; startTime++)
		{
			if (startTime < 10)
			{
				timeList.add("0" + startTime + ":00");
				timeList.add("0" + startTime + ":15");
				timeList.add("0" + startTime + ":30");
				timeList.add("0" + startTime + ":45");
				//Start Sasedeve edited by Mrutyunjaya on Date 13-10-2017
				
				timeList.add(startTime + ":00");
				timeList.add(startTime + ":15");
				timeList.add(startTime + ":30");
				timeList.add(startTime + ":45");
				
				//END Sasedeve edited by Mrutyunjaya on Date 13-10-2017
			}
			else
			{
				timeList.add(startTime + ":00");
				if (startTime != 20 && startTime != 19)
				{
					timeList.add(startTime + ":15");
					timeList.add(startTime + ":30");
					timeList.add(startTime + ":45");
				}
			}
		}

	}

	public void setFacilitatorList(List<String> facilitatorList, List<FacilitatorDetailsDTO> facilitatorListDTO)
	{
		try
		{
			List<FacilitatorDetailsDTO> faList = new ArrayList<FacilitatorDetailsDTO>();
			if (null == facilitatorListDTO)
			{

				faList = new FacilitatorDetailsDAO().getActiveFacilitatorList();
			}
			else
			{
				faList.addAll(facilitatorListDTO);
			}

			if (null != faList && !faList.isEmpty())
			{
				for (FacilitatorDetailsDTO facilitatorDetailsDTO : faList)
				{
					facilitatorList.add(facilitatorDetailsDTO.getName());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

	}

	public void setClassList(List<String> classList, List<ClassDTO> classListDTO)
	{
		try
		{
			List<ClassDTO> sclList = new ArrayList<ClassDTO>();
			if (null == classListDTO)
			{

				sclList = new ClassDAO().getAllClassList();
			}
			else
			{
				sclList.addAll(classListDTO);
			}
			if (null != sclList && !sclList.isEmpty())
			{
				for (ClassDTO classDTO : sclList)
				{
					classList.add(classDTO.getName());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

	}

	public void setSchoolList(List<String> schoolList, List<SchoolDTO> schoolListDTO)
	{
		try
		{
			List<SchoolDTO> sclList = new ArrayList<SchoolDTO>();
			if (null == schoolListDTO)
			{

				sclList = new SchoolDAO().getAllSchoolList();
			}
			else
			{
				sclList.addAll(schoolListDTO);
			}

			if (null != sclList && !sclList.isEmpty())
			{
				for (SchoolDTO schoolDTO : sclList)
				{
					schoolList.add(schoolDTO.getName());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

	}

	private void setGenderList(List<String> genderList)
	{
		GenderTypeEnum[] genderTypeEnums = GenderTypeEnum.values();

		for (GenderTypeEnum genderTypeEnum : genderTypeEnums)
		{
			genderList.add(genderTypeEnum.getGender());
		}
	}

	public void setCityList(List<String> cityList, List<CityDTO> cityListDTO)
	{
		try
		{
			List<CityDTO> ciList = new ArrayList<CityDTO>();
			if (null == cityListDTO)
			{

				ciList = new ArrayList<CityDTO>();
			}
			else
			{
				ciList.addAll(cityListDTO);
			}

			ciList = new CityDAO().getAllCityList();
			if (null != ciList && !ciList.isEmpty())
			{
				for (CityDTO cityDTO : ciList)
				{
					cityList.add(cityDTO.getName());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

	}

	private void setVenueList(List<String> venueList)
	{

		for (VenueTypeEnum venue : VenueTypeEnum.values())
		{
			if (venue.getValue().equalsIgnoreCase(VenueTypeEnum.ATSCHOOL.getValue()))
			{
				venueList.add("AT SCHOOL");
			}
			else
			{
				venueList.add(venue.getValue());
			}
		}
	}

	public void getSessionMapWithFailitator(Map<Integer, Map<String, List<String>>> sessionFacilitatorMap)
	{
		Map<String, List<String>> sessionMap = null;
		List<String> session1List = null;
		List<String> session2List = null;
		List<String> session3List = null;
		try
		{
			List<FacilitatorDetailsDTO> faList = new FacilitatorDetailsDAO().getActiveFacilitatorList();
			List<SessionScheduleDetailsDTO> sessionScList = new SessionScheduleDetailsDAO().getAllsesionList();
			if (null != faList && !faList.isEmpty())
			{
				for (FacilitatorDetailsDTO facilitatorDetailsDTO : faList)
				{
					sessionMap = new HashMap<String, List<String>>();
					sessionFacilitatorMap.put(facilitatorDetailsDTO.getId(), sessionMap);
				}
			}
			if (null != sessionScList && !sessionScList.isEmpty())
			{

				for (SessionScheduleDetailsDTO sessionScheduleDetailsDTO : sessionScList)
				{
					if (sessionScheduleDetailsDTO.getSession1Date() == null)
					{
						continue;
					}
					if (null != sessionFacilitatorMap && sessionFacilitatorMap.containsKey(sessionScheduleDetailsDTO.getFacilitatorId()))
					{
						sessionMap = sessionFacilitatorMap.get(sessionScheduleDetailsDTO.getFacilitatorId());
					}
					else
					{
						sessionMap = new HashMap<String, List<String>>();
						sessionFacilitatorMap.put(sessionScheduleDetailsDTO.getFacilitatorId(), sessionMap);
					}
					if (null != sessionMap && sessionMap.containsKey(SessionTypeEnum.SESSION1.getSessionName()))
					{
						session1List = sessionMap.get(SessionTypeEnum.SESSION1.getSessionName());
					}
					else
					{
						session1List = new ArrayList<String>();
						sessionMap.put(SessionTypeEnum.SESSION1.getSessionName(), session1List);
					}
					session1List.add(TimeUtil.getDateFromMillis(sessionScheduleDetailsDTO.getSession1Date().getTime(), TimeUtil.FILTER_DATE_TIME_FORMAT));

					if (sessionMap.containsKey(SessionTypeEnum.SESSION2.getSessionName()))
					{
						session2List = sessionMap.get(SessionTypeEnum.SESSION2.getSessionName());
					}
					else
					{
						session2List = new ArrayList<String>();
						sessionMap.put(SessionTypeEnum.SESSION2.getSessionName(), session2List);
					}
					session2List.add(TimeUtil.getDateFromMillis(sessionScheduleDetailsDTO.getSession2Date().getTime(), TimeUtil.FILTER_DATE_TIME_FORMAT));

					if (sessionMap.containsKey(SessionTypeEnum.SESSION3.getSessionName()))
					{
						session3List = sessionMap.get(SessionTypeEnum.SESSION3.getSessionName());
					}
					else
					{
						session3List = new ArrayList<String>();
						sessionMap.put(SessionTypeEnum.SESSION3.getSessionName(), session3List);
					}
					if(sessionScheduleDetailsDTO.getSession3Date() !=null) {
						session3List.add(TimeUtil.getDateFromMillis(sessionScheduleDetailsDTO.getSession3Date().getTime(), TimeUtil.FILTER_DATE_TIME_FORMAT));
					}
				}
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
		gender = properties.getProperty("com.edupath.bulkupload.column.gender");
		city = properties.getProperty("com.edupath.bulkupload.column.city");
		school = properties.getProperty("com.edupath.bulkupload.column.school");
		className = properties.getProperty("com.edupath.bulkupload.column.class");
		facilitator = properties.getProperty("com.edupath.bulkupload.column.facilitator");
		session1Time = properties.getProperty("com.edupath.bulkupload.column.session1time");
		session2Time = properties.getProperty("com.edupath.bulkupload.column.session2time");
		session3Time = properties.getProperty("com.edupath.bulkupload.column.session3time");
		venue = properties.getProperty("com.edupath.bulkupload.column.venue");
		studentType = properties.getProperty("com.edupath.bulkupload.column.student.type");
		studentTest = properties.getProperty("com.edupath.bulkupload.column.student.test");
		section = properties.getProperty("com.edupath.bulkupload.column.student.section");

	}
	
	public void setStudentTypeList(List<String> studentTypeList)
	{
		for (StudentTypeEnum typeEnum : StudentTypeEnum.values())
		{
			studentTypeList.add(typeEnum.getText());
		}
	}

	public void setStudentTestTakenList(List<String> studenttestTakenList)
	{
		for (StudentTypeEnum.StudentTestTakenEnum typeEnum : StudentTypeEnum.StudentTestTakenEnum.values())
		{
			studenttestTakenList.add(typeEnum.getText());
		}
	}
	public EActionStatus persistExcelObjects(XLSEngine xlsEngine, List<NetValenceExcelRowObject> excelRowObjList, List<CityDTO> cityList,
			List<SchoolDTO> schoolList, List<ClassDTO> calssList, List<FacilitatorDetailsDTO> facilitatorList, String loggedInUserName)
	{
		ApplicationProperties propInstance = ApplicationProperties.getInstance();

		// StudentExcelValidator excelValidator = new StudentExcelValidator();

		int totalRecords = 0;
		int successfull = 0;
		int failed = 0;
		int invalid = 0;
		for (NetValenceExcelRowObject excelRowObject : excelRowObjList)
		{
			Map<String, NetValenceExcelCellVO> rowDataMap = excelRowObject.getRowData();
			totalRecords++;
			String newinterestLoginID =addDetails(rowDataMap, xlsEngine, propInstance, cityList, schoolList, calssList, facilitatorList, loggedInUserName);
			if (newinterestLoginID != null)
			{
				OUT.debug("Student added successfully");
				
				try {
					
					 
					EActionStatus persistStatus= new InterestStudentTestUplodeDBUtil().persistExcelObjects(xlsEngine,rowDataMap, newinterestLoginID,loggedInUserName);
					
					if(persistStatus==EActionStatus.SUCCESS)
					{
						successfull++;
					}
					else
					{
						OUT.debug("Student insert Operation failed");
						failed++;
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					OUT.debug("Student insert Operation failed");
					failed++;
				}
			}
			else
			{
				OUT.debug("Student insert Operation failed");
				failed++;
			}
		}
		OUT.debug("Total Records:" + totalRecords + ", Successful:" + successfull + ", Failed:" + failed + ", Invalid:" + invalid);
		if (totalRecords == successfull)
		{
			return EActionStatus.SUCCESS;
		}
		else if (totalRecords == invalid || totalRecords == failed)
		{
			return EActionStatus.FAILURE;
		}
		else
		{
			return EActionStatus.SUCCESS;
		}
	}

	private String addDetails(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance, List<CityDTO> cityList,
			List<SchoolDTO> schoolList, List<ClassDTO> calssList, List<FacilitatorDetailsDTO> facilitatorList, String loggedInUserName)
	{
		NetValenceExcelCellVO statusCellVO = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.status"));
		
		NetValenceExcelCellVO LDID = rowDataMap.get("LODESTARID");
		
		try
		{
			StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
			prepareStudentDTO(rowDataMap, studentDetailsDTO, propInstance, cityList, schoolList, calssList, facilitatorList);
			Studentinteresttestbuluploadservice service = new Studentinteresttestbuluploadservice();
			String interestLoginID = service.insertStudentDetailForInterstBulkUplode(studentDetailsDTO);
			if (interestLoginID != null )
			{
				StringBuilder auditMessage = new StringBuilder();
				auditMessage.append("Student ").append(studentDetailsDTO.getName())
						.append(" details has been created successfully and created on : " + service.getAuditDateStr());
				insertAudit(auditMessage.toString(), loggedInUserName);
				xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "SUCCESS");		
				xlsEngine.setCellValueExternally(LDID.getRowIndex(), LDID.getColumnIndex(), interestLoginID);
				
				return interestLoginID;
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "FAILURE");
		return null;
	}

	private void insertAudit(String auditMessage, String loggedInUserName)
	{
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.BULK_UPLOAD_STUDENT, auditMessage, loggedInUserName);

	}

	private void prepareStudentDTO(Map<String, NetValenceExcelCellVO> rowDataMap, StudentDetailsDTO studentDetailsDTO, ApplicationProperties propInstance,
			List<CityDTO> cityList, List<SchoolDTO> schoolList, List<ClassDTO> calssList, List<FacilitatorDetailsDTO> facilitatorList)
	{
		synchronized (this)
		{
			try
			{
				OUT.debug("rowData: {}", rowDataMap);
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.name")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.name")).getValue().trim().isEmpty())
				{
					studentDetailsDTO.setName(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.name")).getValue().trim());
				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.gender")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.gender")).getValue().trim().isEmpty())
				{
					String gender = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.gender")).getValue().trim();
					if (gender.equals(GenderTypeEnum.M.getGender()))
					{
						studentDetailsDTO.setGender("M");
					}
					else if (gender.equals(GenderTypeEnum.F.getGender()))
					{
						studentDetailsDTO.setGender("F");
					}

				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.fathername")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.fathername")).getValue().trim().isEmpty())
				{
					studentDetailsDTO.setFathername(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.fathername")).getValue().trim());
				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.city")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.city")).getValue().trim().isEmpty())
				{
					for (CityDTO cityDTO : cityList)
					{
						if (cityDTO.getName().equals(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.city")).getValue().trim()))
						{
							studentDetailsDTO.setCityId(cityDTO.getId());
							break;
						}
					}
				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.school")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.school")).getValue().trim().isEmpty())
				{
					for (SchoolDTO schoolDTO : schoolList)
					{
						if (schoolDTO.getName().equals(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.school")).getValue().trim()))
						{
							studentDetailsDTO.setSchoolId(schoolDTO.getId());
							break;
						}
					}
				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.class")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.class")).getValue().trim().isEmpty())
				{
					for (ClassDTO classDTO : calssList)
					{
						if (classDTO.getName().equals(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.class")).getValue().trim()))
						{
							studentDetailsDTO.setClassId(classDTO.getId());
							break;
						}
					}
				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.contactno")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.contactno")).getValue().trim().isEmpty())
				{
					studentDetailsDTO.setContactNumber(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.contactno")).getValue().trim());
				}

				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.fatheremail")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.fatheremail")).getValue().trim().isEmpty())
				{
					studentDetailsDTO.setFatheremailId(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.fatheremail")).getValue().trim());
				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.computerfacility")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.computerfacility")).getValue().trim().isEmpty())
				{
					String computerfacility = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.computerfacility")).getValue().trim();
					if (computerfacility.equalsIgnoreCase("Yes"))
					{
						studentDetailsDTO.setComputerFacility(true);
					}
					else if (computerfacility.equalsIgnoreCase("No"))
					{
						studentDetailsDTO.setComputerFacility(false);
					}

				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.facilitator")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.facilitator")).getValue().trim().isEmpty())
				{
					for (FacilitatorDetailsDTO facilitatorDetailsDTO : facilitatorList)
					{
						if (facilitatorDetailsDTO.getName().equals(
								rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.facilitator")).getValue().trim()))
						{
							studentDetailsDTO.setFacilitatorId(facilitatorDetailsDTO.getId());
							break;
						}
					}
				}
				Calendar sessionDateTime;

				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session1date")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session1date")).getValue().trim().isEmpty()
						&& rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session1time")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session1time")).getValue().trim().isEmpty())
				{
					String session1DateTimeStr = null;
					try
					{
						sessionDateTime = Calendar.getInstance();
						Date date = new Date(TimeUtil.getDate(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session1date")).getValue(),
								TimeUtil.EXCEL_DATE_FORMAT).getTime());
						Date time = new Date(TimeUtil.getDate(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session1time")).getValue(),
								TimeUtil.EXCEL_DATE_FORMAT).getTime());

						sessionDateTime.setTime(date);
						sessionDateTime.add(Calendar.HOUR, time.getHours());
						sessionDateTime.add(Calendar.MINUTE, time.getMinutes());
						session1DateTimeStr = TimeUtil.getDateFromMillis(sessionDateTime.getTimeInMillis(), TimeUtil.FILTER_DATE_TIME_FORMAT);
					}
					catch (Exception e)
					{
						OUT.error(ApplicationConstants.EXCEPTION, e);
					}
					studentDetailsDTO.setSession1DateStr(session1DateTimeStr);
				}

				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session2date")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session2date")).getValue().trim().isEmpty()
						&& rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session2time")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session2time")).getValue().trim().isEmpty())
				{
					String session2DateTimeStr = null;
					try
					{
						sessionDateTime = Calendar.getInstance();
						Date date = new Date(TimeUtil.getDate(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session2date")).getValue(),
								TimeUtil.EXCEL_DATE_FORMAT).getTime());
						Date time = new Date(TimeUtil.getDate(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session2time")).getValue(),
								TimeUtil.EXCEL_DATE_FORMAT).getTime());

						sessionDateTime.setTime(date);
						sessionDateTime.add(Calendar.HOUR, time.getHours());
						sessionDateTime.add(Calendar.MINUTE, time.getMinutes());
						session2DateTimeStr = TimeUtil.getDateFromMillis(sessionDateTime.getTimeInMillis(), TimeUtil.FILTER_DATE_TIME_FORMAT);
					}
					catch (Exception e)
					{
						OUT.error(ApplicationConstants.EXCEPTION, e);
					}
					studentDetailsDTO.setSession2DateStr(session2DateTimeStr);
				}

				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session3date")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session3date")).getValue().trim().isEmpty()
						&& rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session3time")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session3time")).getValue().trim().isEmpty())
				{
					String session3DateTimeStr = null;
					try
					{
						sessionDateTime = Calendar.getInstance();
						Date date = new Date(TimeUtil.getDate(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session3date")).getValue(),
								TimeUtil.EXCEL_DATE_FORMAT).getTime());
						Date time = new Date(TimeUtil.getDate(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session3time")).getValue(),
								TimeUtil.EXCEL_DATE_FORMAT).getTime());

						sessionDateTime.setTime(date);
						sessionDateTime.add(Calendar.HOUR, time.getHours());
						sessionDateTime.add(Calendar.MINUTE, time.getMinutes());
						session3DateTimeStr = TimeUtil.getDateFromMillis(sessionDateTime.getTimeInMillis(), TimeUtil.FILTER_DATE_TIME_FORMAT);
					}
					catch (Exception e)
					{
						OUT.error(ApplicationConstants.EXCEPTION, e);
					}
					studentDetailsDTO.setSession3DateStr(session3DateTimeStr);
				}

				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue")).getValue().trim().isEmpty())
				{
					String venue = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue")).getValue().trim();
					if (venue.equalsIgnoreCase(VenueTypeEnum.OTHER.getValue()))
					{
						String otherVenue = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue.other")).getValue().trim();
						studentDetailsDTO.setVenue(otherVenue);
					}
					else if (venue.equalsIgnoreCase("AT SCHOOL"))
					{
						studentDetailsDTO.setVenue(VenueTypeEnum.ATSCHOOL.toString());
					}
					else if (venue.equalsIgnoreCase(VenueTypeEnum.ATOFFICE.getValue()))
					{
						studentDetailsDTO.setVenue(VenueTypeEnum.ATOFFICE.getValue());
					}

				}

				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue")).getValue().trim().isEmpty())
				{
					String venue = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue")).getValue().trim();
					if (venue.equalsIgnoreCase(VenueTypeEnum.OTHER.getValue()))
					{
						String otherVenue = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue.other")).getValue().trim();
						studentDetailsDTO.setVenue(otherVenue);
					}
					else if (venue.equalsIgnoreCase("AT SCHOOL"))
					{
						studentDetailsDTO.setVenue(VenueTypeEnum.ATSCHOOL.toString());
					}
					else if (venue.equalsIgnoreCase(VenueTypeEnum.ATOFFICE.getValue()))
					{
						studentDetailsDTO.setVenue(VenueTypeEnum.ATOFFICE.getValue());
					}

				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.student.type")).getValue() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.student.type")).getValue().trim().isEmpty())
				{
					String type = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.student.type")).getValue().trim();

					if (type.equals(StudentTypeEnum.FULL.getText()))
					{
						studentDetailsDTO.setStudentType(StudentTypeEnum.FULL);
					}
					else if (type.equals(StudentTypeEnum.TRIAL.getText()))
					{
						studentDetailsDTO.setStudentType(StudentTypeEnum.TRIAL);
					}

				}

				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.student.section")).getValue() != null)
				{
					studentDetailsDTO.setSection(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.student.section")).getValue());

				}

				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.student.test")).getValue() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.student.test")).getValue().trim().isEmpty())
				{
					studentDetailsDTO.setTestTaken(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.student.test")).getValue());

					if (StudentTypeEnum.StudentTestTakenEnum.YES.name().equalsIgnoreCase(studentDetailsDTO.getTestTaken()))
					{
						studentDetailsDTO.setTestTaken(StudentTypeEnum.StudentTestTakenEnum.YES.name());
					}
					else
					{
						studentDetailsDTO.setTestTaken(StudentTypeEnum.StudentTestTakenEnum.NO.name());
					}
				}
				else
				{
					studentDetailsDTO.setTestTaken(StudentTypeEnum.StudentTestTakenEnum.NO.name());
				}
				
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.agreedAmount")).getValue() != null &&
						!rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.agreedAmount")).getValue().trim().isEmpty())
				{
					studentDetailsDTO.setAgreedAmount(Double.parseDouble(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.agreedAmount")).getValue()));

				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.paidAmount")).getValue() != null
						&&
						!rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.paidAmount")).getValue().trim().isEmpty())
				{
					studentDetailsDTO.setPaidAmount(Double.parseDouble(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.paidAmount")).getValue()));

				
				}
				
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.studentemail")).getValue().trim() != null
					&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.studentemail")).getValue().trim().isEmpty())
				{
					studentDetailsDTO.setStudentemailId(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.studentemail")).getValue());
				}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.studentcontact")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.studentcontact")).getValue().trim().isEmpty())
					{
						studentDetailsDTO.setStudentcontactNumber(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.studentcontact")).getValue());
						//studentDetailsDTO.setStudentemailId(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.studentemail")).getValue());
					}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.mothername")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.mothername")).getValue().trim().isEmpty())
					{
					
						studentDetailsDTO.setMotherName(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.mothername")).getValue());
					}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.mothercontact")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.mothercontact")).getValue().trim().isEmpty())
					{
					
						studentDetailsDTO.setMothercontactno(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.mothercontact")).getValue());
					}
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.motheremail")).getValue().trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.motheremail")).getValue().trim().isEmpty())
					{
					
						studentDetailsDTO.setMotheremailId(rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.motheremail")).getValue());
					}
				

			}
			catch (Exception e)
			{
				OUT.error("Exception", e);
			}
		}

	}

	public int getSessionTimeGap()
	{
		return new Studentinteresttestbuluploadservice().sessionValidGap();
	}

	public void setClassMapWithGapKey(Map<String, String> classNameWithGapKeyMap)
	{
		try
		{
			List<ClassDTO> clsList = new ClassDAO().getAllClassList();
			if (null != clsList && !clsList.isEmpty())
			{
				for (ClassDTO cDTO : clsList)
				{
					if (null != cDTO.getGap())
					{
						classNameWithGapKeyMap.put(cDTO.getName(), cDTO.getGap());
					}
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

	}

}
