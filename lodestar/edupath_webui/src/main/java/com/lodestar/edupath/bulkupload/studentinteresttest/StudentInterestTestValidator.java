package com.lodestar.edupath.bulkupload.studentinteresttest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.bulkupload.util.StudentExcelUploadDBUtil;
import com.lodestar.edupath.bulkupload.util.StudentInterestTestExcelUploadDBUtil;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.enumtype.GenderTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.VenueTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.student.service.StudentDetailService;
import com.lodestar.edupath.util.ApplicationProperties;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

public class StudentInterestTestValidator {
	
	private static Logger		OUT				= LoggerFactory.getLogger(StudentInterestTestValidator.class);
	private static final long	daysDiffCount	= 24 * 60 * 60 * 1000;

	public boolean validate(XLSEngine xlsEngine, List<NetValenceExcelRowObject> excelRowObjList, List<CityDTO> cityListDTO, List<SchoolDTO> schoolListDTO,
			List<ClassDTO> classListDTO, List<FacilitatorDetailsDTO> facilitatorListDTO)
	{
		StudentInterestTestExcelUploadDBUtil util = new StudentInterestTestExcelUploadDBUtil();
		List<String> cityList = new ArrayList<String>();
		util.setCityList(cityList, cityListDTO);

		List<String> schoolList = new ArrayList<String>();
		util.setSchoolList(schoolList, schoolListDTO);

		List<String> classList = new ArrayList<String>();
		util.setClassList(classList, classListDTO);

		List<String> facilitatorList = new ArrayList<String>();
		util.setFacilitatorList(facilitatorList, facilitatorListDTO);

		List<String> timeList = new ArrayList<String>();
		util.setTimeList(timeList);

		List<String> studentTypeList = new ArrayList<String>();
		util.setStudentTypeList(studentTypeList);

		Map<Integer, Map<String, List<String>>> sessionFacilitatorMap = new HashMap<Integer, Map<String, List<String>>>();
		util.getSessionMapWithFailitator(sessionFacilitatorMap);

		Map<String, String> classNameWithGapKeyMap = new HashMap<String, String>();
		util.setClassMapWithGapKey(classNameWithGapKeyMap);

		Map<String, String> globalSettingMap = new HashMap<String, String>();
		new StudentDetailService().getGlobalSettingMap(globalSettingMap);
		int minDays = 0;

		boolean validationSuccess = true;
		boolean isStatusSuccess;
		boolean sessionDateTime = true;
		boolean isGapCheck = true;
		ApplicationProperties propInstance = ApplicationProperties.getInstance();
		int rowNumber = 0;
		String status = propInstance.getProperty("com.edupath.bulkupload.column.status");
		if (null != excelRowObjList && !excelRowObjList.isEmpty())
		{

			StudentTypeEnum typeEnum = null;
			for (NetValenceExcelRowObject excelRowObject : excelRowObjList)
			{
				typeEnum = null;
				Map<String, NetValenceExcelCellVO> rowDataMap = excelRowObject.getRowData();
				rowNumber = rowDataMap.get(status).getRowIndex();
				OUT.debug("RowNumber : {}", rowNumber);
				isStatusSuccess = true;
				if (!isNameValid(rowDataMap, xlsEngine, propInstance))
				{
					OUT.warn("Validation failed in name at Row: " + rowNumber);
					validationSuccess = false;
					isStatusSuccess = false;
				}

				if (!isGenderValid(rowDataMap, xlsEngine, propInstance))
				{
					OUT.warn("Validation failed in gender at Row: " + rowNumber);
					validationSuccess = false;
					isStatusSuccess = false;
				}

				

				if (!isCityValid(rowDataMap, xlsEngine, propInstance, cityList))
				{
					OUT.warn("Validation failed in city at Row: " + rowNumber);
					validationSuccess = false;
					isStatusSuccess = false;
				}

				if (!isSchoolValid(rowDataMap, xlsEngine, propInstance, schoolList))
				{
					OUT.warn("Validation failed in school at Row: " + rowNumber);
					validationSuccess = false;
					isStatusSuccess = false;
				}

				if (!isClassValid(rowDataMap, xlsEngine, propInstance, classList))
				{
					OUT.warn("Validation failed in class at Row: " + rowNumber);
					validationSuccess = false;
					isStatusSuccess = false;
				}

			

				if (!isComputerFacilityValid(rowDataMap, xlsEngine, propInstance))
				{
					OUT.warn("Validation failed in email at Row: " + rowNumber);
					validationSuccess = false;
					isStatusSuccess = false;
				}
				
				///VYANKATESH STARTED NEW ONE 
				
				String classNameValidation = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.class")).getValue().trim();
				StudentTypeEnum classEnum = null;
				
				for (StudentTypeEnum typeE : StudentTypeEnum.values())
				{
					if (typeE.getText().equalsIgnoreCase(classNameValidation))
					{
						classEnum = typeE;
						break;
					}
				}
				String fatheremailvalid = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.fatheremail")).getValue().trim();
				String motheremailvalid = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.motheremail")).getValue().trim();
				
				
				if( classNameValidation.equals("9") || classNameValidation.equals("10") )
				{
						if((null == fatheremailvalid || fatheremailvalid.isEmpty()) && (null == motheremailvalid || motheremailvalid.isEmpty()))
						{
							if (!isEmailIdValid(rowDataMap, xlsEngine, propInstance))
							{
								OUT.warn("Validation failed in email at Row: " + rowNumber);
								validationSuccess = false;
								isStatusSuccess = false;
							}
							
						}
					
				if(null != fatheremailvalid && !fatheremailvalid.isEmpty())
						{
							
							if (!isContactNumberValid(rowDataMap, xlsEngine, propInstance))
							{
								OUT.warn("Validation failed in contact at Row: " + rowNumber);
								validationSuccess = false;
								isStatusSuccess = false;
							}
	
							if (!isEmailIdValid(rowDataMap, xlsEngine, propInstance))
							{
								OUT.warn("Validation failed in email at Row: " + rowNumber);
								validationSuccess = false;
								isStatusSuccess = false;
							}
							if (!isFatherNameValid(rowDataMap, xlsEngine, propInstance))
							{
								OUT.warn("Validation failed in city at Row: " + rowNumber);
								validationSuccess = false;
								isStatusSuccess = false;
							}
							
						}
					if(null != motheremailvalid && !motheremailvalid.isEmpty())
						{
							if (!isMotherNameValid(rowDataMap, xlsEngine, propInstance))
							{
								OUT.warn("Validation failed in email at Row: " + rowNumber);
								validationSuccess = false;
								isStatusSuccess = false;
							}
							if (!isMotherEmailIdValid(rowDataMap, xlsEngine, propInstance))
							{
								OUT.warn("Validation failed in email at Row: " + rowNumber);
								validationSuccess = false;
								isStatusSuccess = false;
							}
							if (!isMotherContactNumberValid(rowDataMap, xlsEngine, propInstance))
							{
								OUT.warn("Validation failed in email at Row: " + rowNumber);
								validationSuccess = false;
								isStatusSuccess = false;
							}
							
						}
					
				}
				else
				{
					
					if((null == fatheremailvalid || fatheremailvalid.isEmpty()) && (null == motheremailvalid || motheremailvalid.isEmpty()))
					{
						if (!isEmailIdValid(rowDataMap, xlsEngine, propInstance))
						{
							OUT.warn("Validation failed in email at Row: " + rowNumber);
							validationSuccess = false;
							isStatusSuccess = false;
						}
						
					}
				
				if(null != fatheremailvalid && !fatheremailvalid.isEmpty())
					{
						
						if (!isContactNumberValid(rowDataMap, xlsEngine, propInstance))
						{
							OUT.warn("Validation failed in contact at Row: " + rowNumber);
							validationSuccess = false;
							isStatusSuccess = false;
						}

						if (!isEmailIdValid(rowDataMap, xlsEngine, propInstance))
						{
							OUT.warn("Validation failed in email at Row: " + rowNumber);
							validationSuccess = false;
							isStatusSuccess = false;
						}
						if (!isFatherNameValid(rowDataMap, xlsEngine, propInstance))
						{
							OUT.warn("Validation failed in city at Row: " + rowNumber);
							validationSuccess = false;
							isStatusSuccess = false;
						}
						
					}
				if(null != motheremailvalid && !motheremailvalid.isEmpty())
					{
						if (!isMotherNameValid(rowDataMap, xlsEngine, propInstance))
						{
							OUT.warn("Validation failed in email at Row: " + rowNumber);
							validationSuccess = false;
							isStatusSuccess = false;
						}
						if (!isMotherEmailIdValid(rowDataMap, xlsEngine, propInstance))
						{
							OUT.warn("Validation failed in email at Row: " + rowNumber);
							validationSuccess = false;
							isStatusSuccess = false;
						}
						if (!isMotherContactNumberValid(rowDataMap, xlsEngine, propInstance))
						{
							OUT.warn("Validation failed in email at Row: " + rowNumber);
							validationSuccess = false;
							isStatusSuccess = false;
						}
						
					}
					
					
					
					if (!isStudentContactNumberValid(rowDataMap, xlsEngine, propInstance))
					{
						OUT.warn("Validation failed in email at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
					}
					if (!isStudentEmailIdValid(rowDataMap, xlsEngine, propInstance))
					{
						OUT.warn("Validation failed in email at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
					}
					
				}
				
				
				
				
				

				typeEnum = isStudentTypeValid(rowDataMap, xlsEngine, propInstance, studentTypeList);

				if (typeEnum == null)
				{
					OUT.warn("Validation failed in student type at Row: " + rowNumber);
					validationSuccess = false;
					isStatusSuccess = false;
				}
				 

				if (null != typeEnum && StudentTypeEnum.FULL == typeEnum)
				{
					
					//start by vynkatesh validation
					
					if (!isAgreedAmonut(rowDataMap, xlsEngine, propInstance))
					{
						OUT.warn("Validation failed in email at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
					}
					
					
					if (!isPaidAmonut(rowDataMap, xlsEngine, propInstance))
					{
						OUT.warn("Validation failed in email at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
					}
					
					
					
					//End by vynkatesh validation
					
					
					if (!isVenueValid(rowDataMap, xlsEngine, propInstance))
					{
						OUT.warn("Validation failed in venue at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
					}

					if (!isFacilitatorValid(rowDataMap, xlsEngine, propInstance, facilitatorList))
					{
						OUT.warn("Validation failed in facilitator at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
					}

					if (!isSessionDateValid(rowDataMap, xlsEngine, propInstance, propInstance.getProperty("com.edupath.bulkupload.column.session1date")))
					{
						OUT.warn("Validation failed in session 1 date at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
						sessionDateTime = false;
						isGapCheck = false;
					}

					if (!isSessionTimeValid(rowDataMap, xlsEngine, propInstance, timeList, propInstance.getProperty("com.edupath.bulkupload.column.session1time")))
					{
						OUT.warn("Validation failed in session 1 time at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
						sessionDateTime = false;
						isGapCheck = false;
					}

					if (sessionDateTime
							&& !isSessionDateTimeValid(rowDataMap, xlsEngine, propInstance, timeList, propInstance, sessionFacilitatorMap, facilitatorListDTO,
									SessionTypeEnum.SESSION1.getSessionName()))
					{
						OUT.warn("Validation failed in session 1 date time at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
						isGapCheck = false;
					}
					sessionDateTime = true;
					if (!isSessionDateValid(rowDataMap, xlsEngine, propInstance, propInstance.getProperty("com.edupath.bulkupload.column.session2date")))
					{
						OUT.warn("Validation failed in session 2 date at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
						sessionDateTime = false;
						isGapCheck = false;
					}

					if (!isSessionTimeValid(rowDataMap, xlsEngine, propInstance, timeList, propInstance.getProperty("com.edupath.bulkupload.column.session2time")))
					{
						OUT.warn("Validation failed in session 2 time at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
						sessionDateTime = false;
						isGapCheck = false;
					}

					if (sessionDateTime
							&& !isSessionDateTimeValid(rowDataMap, xlsEngine, propInstance, timeList, propInstance, sessionFacilitatorMap, facilitatorListDTO,
									SessionTypeEnum.SESSION2.getSessionName()))
					{
						OUT.warn("Validation failed in session 2 date time at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
						isGapCheck = false;
					}
					sessionDateTime = true;

					if (!isSessionDateValid(rowDataMap, xlsEngine, propInstance, propInstance.getProperty("com.edupath.bulkupload.column.session3date")))
					{
						OUT.warn("Validation failed in session 3 date at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
						sessionDateTime = false;
						isGapCheck = false;
					}

					if (!isSessionTimeValid(rowDataMap, xlsEngine, propInstance, timeList, propInstance.getProperty("com.edupath.bulkupload.column.session3time")))
					{
						OUT.warn("Validation failed in session 3 time at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
						sessionDateTime = false;
						isGapCheck = false;
					}

					if (sessionDateTime
							&& !isSessionDateTimeValid(rowDataMap, xlsEngine, propInstance, timeList, propInstance, sessionFacilitatorMap, facilitatorListDTO,
									SessionTypeEnum.SESSION3.getSessionName()))
					{
						OUT.warn("Validation failed in session 3 date time at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
						isGapCheck = false;
					}
					sessionDateTime = true;
					if (isGapCheck && !isDateTimeValidGap(rowDataMap, xlsEngine, propInstance, minDays))
					{
						OUT.warn("Validation failed in session date time at Row: " + rowNumber);
						validationSuccess = false;
						isStatusSuccess = false;
					}
					isGapCheck = true;
				}

				String className = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.class")).getValue().trim();
				// Get Session Gap time From Global Setting and Class Gap Key
				if (classNameWithGapKeyMap.containsKey(className))
				{
					if (globalSettingMap.containsKey(classNameWithGapKeyMap.get(className)))
					{
						minDays = Integer.valueOf(globalSettingMap.get(classNameWithGapKeyMap.get(className)));
					}
					else
					{
						OUT.warn("Unable to get the session gap for the class: {}, key:{}", className, classNameWithGapKeyMap.get(className));
						validationSuccess = false;
					}
				}
				else
				{
					OUT.warn("Unable to get the class key for the class: {}", className);
					validationSuccess = false;
				}

				if (!isStatusSuccess)
				{
					setExternalValue(rowDataMap, xlsEngine, status, "INVALID_OPERATION");
				}
			}
		}
		else
		{
			validationSuccess = false;
		}
		return validationSuccess;
	}

	private boolean isAgreedAmonut(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine,
			ApplicationProperties propInstance) {
		String agreedAmonut = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.agreedAmount")).getValue().trim();
		boolean isEmptyValid = false;
		if (agreedAmonut == null || agreedAmonut.isEmpty())
		{
			isEmptyValid = true;
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.agreedAmount"), "REQUIRED_PAID_AMOUNT");
		}
		else
		{
			try
			{
				Double.parseDouble(agreedAmonut);
			}
			catch (Exception e)
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.agreedAmount"), "INVALID_PAID_FORMAT");
				
				isEmptyValid = true;
			}
			
			
		}
		return !isEmptyValid;
	}

	private boolean isPaidAmonut(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine,
			ApplicationProperties propInstance) {
		String paidamonut = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.paidAmount")).getValue().trim();
		String agreedAmonut = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.agreedAmount")).getValue().trim();
		boolean isEmptyValid = false;
		if (paidamonut == null || paidamonut.isEmpty())
		{
			isEmptyValid = true;
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.paidAmount"), "REQUIRED_PAID_AMOUNT");
		}
		else
		{
			try
			{
				double PaidDouble = Double.parseDouble(paidamonut);
				double AmountDouble = Double.parseDouble(agreedAmonut);
				int value = Double.compare(PaidDouble, AmountDouble);
						
				if(value > 0)
				{
					setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.paidAmount"), "AMOUNT_VALIDATION");
					
					isEmptyValid = true;
					
				}
				
				
			}
			catch (Exception e)
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.paidAmount"), "AMOUNT_VALIDATION");
				
				isEmptyValid = true;
			}
			
			
		}
		return !isEmptyValid;
	}

	private boolean isDateTimeValidGap(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance, int minDays)
	{

		String session1Date = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session1date")).getValue();
		String session1time = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session1time")).getValue();

		String session2Date = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session2date")).getValue();
		String session2time = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session2time")).getValue();

		String session3Date = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session3date")).getValue();
		String session3time = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.session3time")).getValue();
		long diff = 0;
		double days = 0;
		boolean isValid = true;
		try
		{
			Calendar session1DateTime = Calendar.getInstance();
			Calendar session2DateTime = Calendar.getInstance();
			Calendar session3DateTime = Calendar.getInstance();
			try
			{
				Date date1 = new Date(TimeUtil.getDate(session1Date, TimeUtil.EXCEL_DATE_FORMAT).getTime());
				Date time1 = new Date(TimeUtil.getDate(session1time, TimeUtil.EXCEL_DATE_FORMAT).getTime());

				Date date2 = new Date(TimeUtil.getDate(session2Date, TimeUtil.EXCEL_DATE_FORMAT).getTime());
				Date time2 = new Date(TimeUtil.getDate(session2time, TimeUtil.EXCEL_DATE_FORMAT).getTime());

				Date date3 = new Date(TimeUtil.getDate(session3Date, TimeUtil.EXCEL_DATE_FORMAT).getTime());
				Date time3 = new Date(TimeUtil.getDate(session3time, TimeUtil.EXCEL_DATE_FORMAT).getTime());

				session1DateTime.setTime(date1);

				session2DateTime.setTime(date2);

				session3DateTime.setTime(date3);

				if (minDays == 0)
				{
					session1DateTime.add(Calendar.HOUR, time1.getHours());
					session1DateTime.add(Calendar.MINUTE, time1.getMinutes());

					session2DateTime.add(Calendar.HOUR, time2.getHours());
					session2DateTime.add(Calendar.MINUTE, time2.getMinutes());

					session3DateTime.add(Calendar.HOUR, time3.getHours());
					session3DateTime.add(Calendar.MINUTE, time3.getMinutes());
				}

			}
			catch (ParseException e)
			{
				// setErrorComment(rowDataMap, xlsEngine, property,
				// "INVALID_DATE_FORMAT");

				OUT.warn("Date entered is in wrong format");
				return false;
			}

			diff = session2DateTime.getTimeInMillis() - session1DateTime.getTimeInMillis();
			days = (double) diff / daysDiffCount;

			if (days < minDays || days <= 0)
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.session2date"), "DATE_AND_TIME_DIFFER_" + minDays);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.session2time"), "DATE_AND_TIME_DIFFER_" + minDays);
				isValid = false;
			}

			diff = session3DateTime.getTimeInMillis() - session2DateTime.getTimeInMillis();
			days = (double) diff / daysDiffCount;

			if (days < minDays || days <= 0)
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.session3date"), "DATE_AND_TIME_DIFFER_" + minDays);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.session3time"), "DATE_AND_TIME_DIFFER_" + minDays);
				isValid = false;
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			isValid = false;

		}
		return isValid;
	}

	private boolean isSessionDateTimeValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance,
			List<String> timeList, ApplicationProperties propInstance2, Map<Integer, Map<String, List<String>>> sessionFacilitatorMap,
			List<FacilitatorDetailsDTO> facilitatorListDTO, String sessionName)
	{
		String propertySessionDate = "";
		String propertySessionTime = "";
		String sessionDate = "";
		String sessionTime = "";
		// String sessionDateTime = "";
		String sessionDateTimeStr = "";
		String facilitatorName = "";
		int facilitatorId = 0;
		if (sessionName.equals(SessionTypeEnum.SESSION1.getSessionName()))
		{
			propertySessionDate = propInstance.getProperty("com.edupath.bulkupload.column.session1date");
			propertySessionTime = propInstance.getProperty("com.edupath.bulkupload.column.session1time");
		}
		else if (sessionName.equals(SessionTypeEnum.SESSION2.getSessionName()))
		{
			propertySessionDate = propInstance.getProperty("com.edupath.bulkupload.column.session2date");
			propertySessionTime = propInstance.getProperty("com.edupath.bulkupload.column.session2time");
		}
		else if (sessionName.equals(SessionTypeEnum.SESSION3.getSessionName()))
		{
			propertySessionDate = propInstance.getProperty("com.edupath.bulkupload.column.session3date");
			propertySessionTime = propInstance.getProperty("com.edupath.bulkupload.column.session3time");
		}
		sessionDate = rowDataMap.get(propertySessionDate).getValue();
		sessionTime = rowDataMap.get(propertySessionTime).getValue();
		Calendar sessionDateTime = Calendar.getInstance();
		try
		{
			Date date = new Date(TimeUtil.getDate(sessionDate, TimeUtil.EXCEL_DATE_FORMAT).getTime());
			Date time = new Date(TimeUtil.getDate(sessionTime, TimeUtil.EXCEL_DATE_FORMAT).getTime());

			sessionDateTime.setTime(date);
			sessionDateTime.add(Calendar.HOUR, time.getHours());
			sessionDateTime.add(Calendar.MINUTE, time.getMinutes());
			sessionDateTimeStr = TimeUtil.getDateFromMillis(sessionDateTime.getTimeInMillis(), TimeUtil.FILTER_DATE_TIME_FORMAT);
		}
		catch (ParseException e)
		{
			// setErrorComment(rowDataMap, xlsEngine, propertySessionDate,
			// "INVALID_DATE_FORMAT");
			OUT.warn("Date entered is in wrong format");
			return false;
		}

		facilitatorName = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.facilitator")).getValue().trim();
		if (null == facilitatorName || facilitatorName.isEmpty() || facilitatorName.equals(""))
		{
			return true;
		}
		if (null != facilitatorListDTO && !facilitatorListDTO.isEmpty())
		{
			for (FacilitatorDetailsDTO facilitatorDetailsDTO : facilitatorListDTO)
			{
				if (facilitatorDetailsDTO.getName().equals(facilitatorName))
				{
					facilitatorId = facilitatorDetailsDTO.getId();
					break;
				}
			}
			if (facilitatorId == 0)
			{
				return true;
			}
			Map<String, List<String>> sessionMap = null;
			List<String> sessionList = null;
			if (null != sessionFacilitatorMap && sessionFacilitatorMap.containsKey(facilitatorId))
			{
				sessionMap = sessionFacilitatorMap.get(facilitatorId);
			}
			else
			{
				sessionMap = new HashMap<String, List<String>>();
				sessionFacilitatorMap.put(facilitatorId, sessionMap);
			}
			if (null != sessionMap && sessionMap.containsKey(sessionName))
			{
				sessionList = sessionMap.get(sessionName);

				Calendar iterateSessionStartDateTime = Calendar.getInstance();
				Calendar iterateSessionStopTime = Calendar.getInstance();
				Date currentDate = new Date(System.currentTimeMillis());
				Calendar currentCal = Calendar.getInstance();
				currentCal.setTimeInMillis(currentDate.getTime());

				Date date = sessionDateTime.getTime();
				iterateSessionStartDateTime.setTime(date);
				iterateSessionStopTime.setTime(date);
				iterateSessionStopTime.add(Calendar.HOUR, ApplicationConstants.SESSION_HOURS);
				if (date != null && date.before(currentDate))
				{
					OUT.debug("Current date: " + currentDate);
					setErrorComment(rowDataMap, xlsEngine, propertySessionDate, "PAST_DATE_AND_TIME");
					setErrorComment(rowDataMap, xlsEngine, propertySessionTime, "PAST_DATE_AND_TIME");
					return false;
				}
				if (null != sessionList && !sessionList.isEmpty())
				{
					Calendar startTime = Calendar.getInstance();
					Calendar stopTime = Calendar.getInstance();
					for (String session : sessionList)
					{
						try
						{
							startTime.setTimeInMillis(TimeUtil.getDate(session, TimeUtil.FILTER_DATE_TIME_FORMAT).getTime());
							stopTime.setTimeInMillis(TimeUtil.getDate(session, TimeUtil.FILTER_DATE_TIME_FORMAT).getTime());
							stopTime.add(Calendar.HOUR, ApplicationConstants.SESSION_HOURS);
							stopTime.add(Calendar.MINUTE, -1);
							if (iterateSessionStartDateTime.getTime().equals(startTime.getTime())
									|| iterateSessionStartDateTime.getTime().after(startTime.getTime())
									&& iterateSessionStartDateTime.getTime().before(stopTime.getTime()))
							{
								setErrorComment(rowDataMap, xlsEngine, propertySessionDate, "OVERLAP_SESSION_DATE_AND_TIME");
								setErrorComment(rowDataMap, xlsEngine, propertySessionTime, "OVERLAP_SESSION_DATE_AND_TIME");
								return false;
							}
							else if (iterateSessionStopTime.getTime().after(startTime.getTime()) && iterateSessionStopTime.getTime().before(stopTime.getTime()))
							{
								setErrorComment(rowDataMap, xlsEngine, propertySessionDate, "OVERLAP_SESSION_DATE_AND_TIME");
								setErrorComment(rowDataMap, xlsEngine, propertySessionTime, "OVERLAP_SESSION_DATE_AND_TIME");
								return false;
							}
						}
						catch (ParseException e)
						{
							OUT.info("Date from database is in wrong format");
						}
					}
				}
				sessionList.add(sessionDateTimeStr);
			}
			else
			{
				sessionList = new ArrayList<String>();
				sessionMap.put(sessionName, sessionList);
				sessionList.add(sessionDateTimeStr);
			}
		}
		return true;
	}

	private boolean isSessionDateValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance,
			String sessionDateProperty)
	{
		String sessionDate = rowDataMap.get(sessionDateProperty).getValue().trim();
		if (null == sessionDate || sessionDate.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, sessionDateProperty, "REQUIRED_SESSIONDATE");
			return false;
		}
		if (!isValidDate(rowDataMap, xlsEngine, sessionDateProperty, sessionDate))
		{
			return false;
		}

		return true;
	}

	private boolean isSessionTimeValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance,
			List<String> timeList, String sessionTime)
	{
		String time = rowDataMap.get(sessionTime).getValue();
		if (null == time || time.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, sessionTime, "REQUIRED_SESSIONTIME");
			return false;
		}

		String strTime = null;
		try
		{
			Date date = new Date(TimeUtil.getDate(time, TimeUtil.EXCEL_DATE_FORMAT).getTime());
			if (date.getMinutes() == 0)
			{
				strTime = date.getHours() + ":" + date.getMinutes() + "0";
			}
			else
			{
				strTime = date.getHours() + ":" + date.getMinutes();
			}
		}
		catch (ParseException e)
		{
		}

		if (timeList.contains(strTime))
		{
			return true;
		}
		setErrorComment(rowDataMap, xlsEngine, sessionTime, "INVALID_SESSIONTIME");
		return false;
	}

	private boolean isValidDate(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, String property, String sessionDate)
	{
		try
		{
			new Date(TimeUtil.getDate(sessionDate, TimeUtil.EXCEL_DATE_FORMAT).getTime());
		}
		catch (ParseException e)
		{
			setErrorComment(rowDataMap, xlsEngine, property, "INVALID_DATE_FORMAT");
			OUT.info("Date entered is in wrong format");
			return false;
		}

		return true;
	}

	private boolean isFacilitatorValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance,
			List<String> facilitatorList)
	{
		String facilitator = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.facilitator")).getValue().trim();
		if (null == facilitator || facilitator.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.facilitator"), "REQUIRED_FACILITATOR");
			return false;
		}
		if (null != facilitator && facilitator.contains(facilitator))
		{
			return true;
		}
		setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.facilitator"), "INVALID_FACILITATOR");
		return false;
	}

	private boolean isComputerFacilityValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String computerFacility = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.computerfacility")).getValue().trim();
		if (computerFacility == null || computerFacility.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.computerfacility"), "REQUIRED_COMPUTER_FACILITY");
			return false;
		}
		if (computerFacility.equalsIgnoreCase("Yes") || computerFacility.equalsIgnoreCase("No"))
		{
			return true;
		}
		setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.computerfacility"), "INVALID_COMPUTER_FACILITY");
		return false;
	}

	private boolean isEmailIdValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String email = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.fatheremail")).getValue().trim();
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";
		Pattern pattern = Pattern.compile(regex);
		if (null == email || email.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.fatheremail"), "REQUIRED_EMAIL");
			return false;
		}
		if (!email.isEmpty() && !pattern.matcher(email).matches())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.fatheremail"), "INVALID_EMAIL");
			return false;
		}
		return true;
	}

	private boolean isContactNumberValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		try
		{
			// Double contactDoubleFormat = new Double(
			// rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.contactno")).getValue().trim());
			// long contactLongFormat = contactDoubleFormat.longValue();
			// String contactNo = String.valueOf(contactLongFormat);
			String contactNo = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.contactno")).getValue().trim();
			String regex = "(^\\d{10}$)";
			Pattern pattern = Pattern.compile(regex);
			if (null == contactNo || contactNo.isEmpty())
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.contactno"), "REQUIRED_CONTACT_NUMBER");
				return false;
			}
			if (!pattern.matcher(contactNo).matches())
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.contactno"), "INVALID_CONTACT_NUMBER");
				return false;
			}
		}
		catch (Exception e)
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.contactno"), "INVALID_CONTACT_NUMBER");
			return false;
		}
		return true;
	}

	private boolean isClassValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance, List<String> classList)
	{
		String className = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.class")).getValue().trim();
		if (null == className || className.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.class"), "REQUIRED_CLASS");
			return false;
		}
		if (null != classList && classList.contains(className))
		{
			return true;
		}
		setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.class"), "INVALID_CLASS");
		return false;
	}

	private boolean isSchoolValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance, List<String> schoolList)
	{
		String school = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.school")).getValue().trim();
		if (null == school || school.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.school"), "REQUIRED_SCHOOL");
			return false;
		}
		if (null != schoolList && schoolList.contains(school))
		{
			return true;
		}
		setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.school"), "INVALID_SCHOOL");
		return false;
	}

	private boolean isCityValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance, List<String> cityList)
	{
		String city = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.city")).getValue().trim();
		if (null == city || city.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.city"), "REQUIRED_CITY");
			return false;
		}
		if (!cityList.contains(city))
		{
			
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.city"), "INVALID_CITY");
			return false;
		}
		
		return true;
	}

	/**
	 * @param rowDataMap
	 * @param xlsEngine
	 * @param propInstance
	 * @return
	 */
	private boolean isFatherNameValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String fatherName = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.fathername")).getValue().trim();
		if (fatherName == null || fatherName.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.fathername"), "REQUIRED_FATHER_NAME");
			return false;
		}
		return true;
	}

	/**
	 * @param rowDataMap
	 * @param xlsEngine
	 * @param propInstance
	 * @return
	 */
	private boolean isGenderValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String gender = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.gender")).getValue().trim();
		if (gender == null || gender.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.gender"), "REQUIRED_GENDER");
			return false;
		}
		GenderTypeEnum[] values = GenderTypeEnum.values();
		for (GenderTypeEnum genderEnum : values)
		{
			if (gender.equalsIgnoreCase(genderEnum.getGender()))
				return true;
		}
		setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.gender"), "INVALID_GENDER");
		return false;
	}

	private void setExternalValue(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, String status, String errorTitle)
	{
		NetValenceExcelCellVO excelCellVO = rowDataMap.get(status);
		if (errorTitle.contains("INVALID_OPERATION"))
		{
			xlsEngine.setCellValueExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "FAILURE");
		}

	}

	private boolean isNameValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String name = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.name")).getValue().trim();
		boolean isEmptyValid = false;
		if (name == null || name.isEmpty())
		{
			isEmptyValid = true;
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.name"), "REQUIRED_ST_NAME");
		}
		return !isEmptyValid;
	}

	private StudentTypeEnum isStudentTypeValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance,
			List<String> studentTypeList)
	{
		StudentTypeEnum typeEnum = null;
		String type = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.student.type")).getValue().trim();
		if (null == type || type.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.student.type"), "REQUIRED_STUDENT_TYPE");
			return typeEnum;
		}
		if (null != studentTypeList && studentTypeList.contains(type))
		{
			for (StudentTypeEnum typeE : StudentTypeEnum.values())
			{
				if (typeE.getText().equalsIgnoreCase(type))
				{
					typeEnum = typeE;
					break;
				}
			}
			return typeEnum;
		}
		setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.student.type"), "INVALID_STUDENT_TYPE");
		return typeEnum;
	}

	private void setErrorComment(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, String property, String errorTitle)
	{
		NetValenceExcelCellVO excelCellVO = rowDataMap.get(property);
		if (errorTitle.contains("REQUIRED_"))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "This is required information");
		}
		else if ("INVALID_GENDER".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Gender");
		}

		else if ("INVALID_CITY".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid City");
		}

		else if ("INVALID_SCHOOL".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid School");
		}

		else if ("INVALID_CLASS".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Class");
		}

		else if ("INVALID_COMPUTER_FACILITY".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Computer facility");
		}

		else if ("INVALID_EMAIL".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Email");
		}

		else if ("INVALID_FACILITATOR".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Facilitator");
		}
		else if ("REQUIRED_PAID_AMOUNT".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "This is required information");
		}
		else if ("INVALID_PAID_FORMAT".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Amount");
		}
		else if ("AMOUNT_VALIDATION".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Paid Amount Less than Agreed Amount");
		}

		else if ("INVALID_SESSIONDATE".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Session Date");
		}

		else if ("PAST_DATE_AND_TIME".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Past date time are not allowed");
		}
		else if ("INVALID_SESSIONTIME".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Session Time");
		}
		else if ("OVERLAP_SESSION_DATE_AND_TIME".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Session date-time is overlapping for same facilitator");
		}
		else if ("INVALID_DATE_FORMAT".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Date format is invalid");
		}
		else if (errorTitle.startsWith("DATE_AND_TIME_DIFFER_"))
		{
			StringBuilder stringBuilder = new StringBuilder();
			if (ApplicationProperties.getInstance().getProperty("com.edupath.bulkupload.column.session2date").equals(property))
			{
				stringBuilder.append("Session 1, 2 date differ must be minimum of ");
			}
			else if (ApplicationProperties.getInstance().getProperty("com.edupath.bulkupload.column.session3date").equals(property))
			{
				stringBuilder.append("Session 2, 3 date differ must be minimum of ");
			}

			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(),
					stringBuilder.toString() + errorTitle.replace("DATE_AND_TIME_DIFFER_", "") + " days");
		}
		else if ("TIME_DIFFER".equals(errorTitle))
		{
			StringBuilder stringBuilder = new StringBuilder();
			if (ApplicationProperties.getInstance().getProperty("com.edupath.bulkupload.column.session2date").equals(property))
			{
				stringBuilder.append("Session 2 date & time must be greater than Session 1");
			}
			else if (ApplicationProperties.getInstance().getProperty("com.edupath.bulkupload.column.session3date").equals(property))
			{
				stringBuilder.append("Session 3 date & time must be greater than Session 2");
			}
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), stringBuilder.toString());
		}
		else if ("INVALID_CONTACT_NUMBER".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid contact number");
		}
		else if ("REQUIRED_VENUE".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid venue");
		}
		else if ("REQUIRED_VENUE(OTHER)".equals(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid other venue");
		}
		else if ("INVALID_STUDENT_TYPE".equalsIgnoreCase(errorTitle))
		{
			xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid student type");
		}
	}

	private boolean isVenueValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String venue = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue")).getValue().trim();
		if (venue == null || venue.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.venue"), "REQUIRED_VENUE");
			return false;
		}
		VenueTypeEnum[] values = VenueTypeEnum.values();
		for (VenueTypeEnum venueEnum : values)
		{
			if (venue.equalsIgnoreCase(VenueTypeEnum.OTHER.getValue()))
			{
				String venueOther = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.venue.other")).getValue().trim();
				if (venueOther == null || venueOther.isEmpty())
				{
					setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.venue.other"), "REQUIRED_VENUE(OTHER)");
					return false;
				}
				return true;
			}
			else if (venue.equalsIgnoreCase("AT SCHOOL"))
			{
				return true;
			}
			else if (venue.equalsIgnoreCase(venueEnum.getValue()))
			{
				return true;
			}
		}

		setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.venue"), "INVALID_VENUE");
		return false;
	}
	
	
	private boolean isMotherNameValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String name = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.mothername")).getValue().trim();
		boolean isEmptyValid = false;
		if (name == null || name.isEmpty())
		{
			isEmptyValid = true;
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.mothername"), "REQUIRED_ST_NAME");
		}
		return !isEmptyValid;
	}
	
	private boolean isMotherEmailIdValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String email = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.motheremail")).getValue().trim();
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";
		Pattern pattern = Pattern.compile(regex);
		if (null == email || email.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.motheremail"), "REQUIRED_EMAIL");
			return false;
		}
		if (!email.isEmpty() && !pattern.matcher(email).matches())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.motheremail"), "INVALID_EMAIL");
			return false;
		}
		return true;
	}
	
	private boolean isStudentEmailIdValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String email = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.studentemail")).getValue().trim();
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";
		Pattern pattern = Pattern.compile(regex);
		if (null == email || email.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.studentemail"), "REQUIRED_EMAIL");
			return false;
		}
		if (!email.isEmpty() && !pattern.matcher(email).matches())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.studentemail"), "INVALID_EMAIL");
			return false;
		}
		return true;
	}
	
	private boolean isMotherContactNumberValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		try
		{
			String contactNo = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.mothercontact")).getValue().trim();
			String regex = "(^\\d{10}$)";
			Pattern pattern = Pattern.compile(regex);
			if (null == contactNo || contactNo.isEmpty())
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.mothercontact"), "REQUIRED_CONTACT_NUMBER");
				return false;
			}
			if (!pattern.matcher(contactNo).matches())
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.mothercontact"), "INVALID_CONTACT_NUMBER");
				return false;
			}
		}
		catch (Exception e)
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.mothercontact"), "INVALID_CONTACT_NUMBER");
			return false;
		}
		return true;
	}
	private boolean isStudentContactNumberValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		try
		{
			
			String contactNo = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.studentcontact")).getValue().trim();
			String regex = "(^\\d{10}$)";
			Pattern pattern = Pattern.compile(regex);
			if (null == contactNo || contactNo.isEmpty())
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.studentcontact"), "REQUIRED_CONTACT_NUMBER");
				return false;
			}
			if (!pattern.matcher(contactNo).matches())
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.studentcontact"), "INVALID_CONTACT_NUMBER");
				return false;
			}
		}
		catch (Exception e)
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.column.studentcontact"), "INVALID_CONTACT_NUMBER");
			return false;
		}
		return true;
	}
	
	
	public static void main(String[] args)
	{
		// Calendar iterateSessionDateTime = Calendar.getInstance();
		// try
		// {
		// iterateSessionDateTime.setTime(TimeUtil.getDate("22/07/2015 11:00",
		// TimeUtil.FILTER_DATE_TIME_FORMAT));
		// }
		// catch (ParseException e)
		// {
		// OUT.info("Date entered is in wrong format");
		// }
		// Calendar startTime = Calendar.getInstance();
		// Calendar stopTime = Calendar.getInstance();
		// try
		// {
		// startTime.setTimeInMillis(TimeUtil.getDate("22/07/2015 11:00",
		// TimeUtil.FILTER_DATE_TIME_FORMAT).getTime());
		// stopTime.setTimeInMillis(TimeUtil.getDate("22/07/2015 11:00",
		// TimeUtil.FILTER_DATE_TIME_FORMAT).getTime());
		// stopTime.add(Calendar.HOUR, ApplicationConstants.SESSION_HOURS);
		// stopTime.add(Calendar.MINUTE, -1);
		// }
		// catch (ParseException e)
		// {
		// OUT.info("Date from database is in wrong format");
		// }
		// System.out.println(iterateSessionDateTime.getTime());
		// System.out.println(startTime.getTime());
		// System.out.println(stopTime.getTime());
		// System.out.println(iterateSessionDateTime.equals(startTime) ||
		// iterateSessionDateTime.getTime().before(stopTime.getTime())
		// && iterateSessionDateTime.getTime().after(startTime.getTime()));

		double val = (double) 165600000 / daysDiffCount;
		System.out.println(val);
		System.out.println(Math.ceil((double) 165600000 / daysDiffCount));
	}

}
