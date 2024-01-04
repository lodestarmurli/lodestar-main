package com.lodestar.edupath.facilitator.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.EducationLevelDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.LanguagesDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.facilitator.service.FacilitatorCalendarService;
import com.lodestar.edupath.facilitator.service.FacilitatorDetailsService;
import com.lodestar.edupath.student.bean.StudentIndividualVO;
import com.lodestar.edupath.util.datatable.DataTableUtils;
import com.opensymphony.xwork2.ModelDriven;

public class FacilitatorCalendarAction extends BaseAction implements ModelDriven<FacilitatorDetailsDTO>
{

	private static final long			serialVersionUID		= 1L;
	private static final Logger			OUT						= LoggerFactory.getLogger(FacilitatorCalendarAction.class);

	private FacilitatorDetailsDTO		facilitatorDetailsDTO	= new FacilitatorDetailsDTO();
	private List<FacilitatorDetailsDTO>	facilitatorDetailsList	= new ArrayList<FacilitatorDetailsDTO>();

	private Map<Integer, String>		cityMap					= new LinkedHashMap<Integer, String>();
	private Map<Integer, String>		highestQualificationMap	= new LinkedHashMap<Integer, String>();
	private Map<Character, String>		typeList				= new HashMap<Character, String>();
	private String						readOnly				= "";
	private List<String>				languageList;
	private List<Integer>		   faceToFaceCityIds;
	private List<Integer>					onCallCityIds;
	private int  						facilitatorId;
	 

	
	

	public String execute()          
	{
		OUT.info("Inside FacilitatorCalendarAction : Getting all Facilitator Details list");
		try
		{
			facilitatorDetailsList = new FacilitatorCalendarService().getAllFacilitatorDetails();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;

	}

	

	
	public void getFacilitatorSchedule()
	{
		try
		{
			OUT.info("Inside FacilitatorCalendarAction getFacilitatorSchedule");
			facilitatorId = Integer.parseInt(request.getParameter("facilitatorId"));
			List<CalendarVO> finalList = new FacilitatorCalendarService().getFacilitatorSchedule(facilitatorId);
			JSONArray dataArr = convertCollectionToJSONArray(finalList);
			writeToJSONResponse(dataArr);
	//		List<StudentIndividualVO> finalList = service.getIndividualStudentSummary(userId);
	//		JSONArray dataArr = convertCollectionToJSONArray(finalList);
	//		JSONObject jsonObj = DataTableUtils.createDataTableJSON(dataTableVO.getDraw(), dataTableVO.getRecordsTotalCount(),dataTableVO.getRecordsFilteredCount(), dataArr);
	//		writeToJSONResponse(dataArr);
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		
		
	
	}
	
	
	
	public List<String> getLanguageList()
	{
		languageList = new ArrayList<String>();
		List<LanguagesDTO> languages = new FacilitatorDetailsService().getLanguages();
		for (LanguagesDTO languagesDTO : languages)
		{
			languageList.add(languagesDTO.getName());
		}
		return languageList;
	}

	public String getData()
	{
		int id = facilitatorDetailsDTO.getId();
		faceToFaceCityIds = new ArrayList<Integer>();
		onCallCityIds  = new ArrayList<Integer>();
		
		
		List<FacilitatorDetailsDTO> facilatormapping = new FacilitatorDetailsService().getfacilatormapping(id);
		List<FacilitatorDetailsDTO> faceToFaceCityId = new FacilitatorDetailsService().getfaceToFaceCityId(id);
		List<FacilitatorDetailsDTO> CallCityId = new FacilitatorDetailsService().getonCallCityId(id);
		for (FacilitatorDetailsDTO facilitatorDetailsDTO : faceToFaceCityId) {
			
			faceToFaceCityIds.add(facilitatorDetailsDTO.getCityId());
		}
		
		
		for (FacilitatorDetailsDTO facilitatorDetailsDTO : CallCityId) {
			onCallCityIds.add(facilitatorDetailsDTO.getCityId());
		}
		
		if(facilatormapping.isEmpty())
		{
			faceToFaceCityIds.add(326);
		}
		
		if (id != 0)
		{
			OUT.info("Getting Facilitator Details for id : {}", id);
			try
			{
				facilitatorDetailsDTO = new FacilitatorDetailsService().getFacilitatorDetailsById(id);
				facilitatorDetailsDTO.setFaceToFaceCityId(faceToFaceCityIds);
				facilitatorDetailsDTO.setOnCallCityId(onCallCityIds);
				
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		setCityMap();
		setHighestQualificationMap();
		setType();
		return "insert";
	}

	public String showData()
	{
		readOnly = "readonly";
		int id = facilitatorDetailsDTO.getId();
		if (id != 0)
		{
			OUT.info("Getting Facilitator Details for id : {}", id);
			try
			{
				facilitatorDetailsDTO = new FacilitatorDetailsService().getFacilitatorDetailsById(id);
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		setCityMap();
		setHighestQualificationMap();
		setType();
		return "insert";
	}

	private void setType()
	{
		typeList.put('F', "Full Time");
		typeList.put('P', "Part Time");

	}

	public String getDefaultTypeValue()
	{
		return "F";
	}

	private void setHighestQualificationMap()
	{
		List<EducationLevelDTO> educationLevelList = new FacilitatorDetailsService().getEducationLevelNameWithId();
		for (EducationLevelDTO educationLevelDTO : educationLevelList)
		{
			highestQualificationMap.put(educationLevelDTO.getId(), educationLevelDTO.getName());
		}
	}

	private void setCityMap()
	{
		List<CityDTO> cityList = new FacilitatorDetailsService().getCityNameWithId();
		for (CityDTO cityDTO : cityList)
		{
			cityMap.put(cityDTO.getId(), cityDTO.getName());
		}
	}

	public String insert()
	{
		OUT.debug("Inside FacilitatorDetailsAction (insert method)");

		try
		{
			if (facilitatorDetailsDTO.getDobStr() != null && !facilitatorDetailsDTO.getDobStr().trim().equals(""))
			{
				facilitatorDetailsDTO.setDob(TimeUtil.getDate(facilitatorDetailsDTO.getDobStr(), TimeUtil.FILTER_DATE_FORMAT));
			}

			FacilitatorDetailsService service = new FacilitatorDetailsService();
			boolean isInserted = service.insertFacilitatorDetails(facilitatorDetailsDTO);

			if (!isInserted)
			{
				addActionError(getText("com.edupath.action.add.msg.error", new String[]
				{
						"Facilitator",
						"email id already exit"
				}));

				setCityMap();
				setHighestQualificationMap();
				setType();
				return "insert";
			}
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.USER_DEFINITION,
					"Facilitator :" + facilitatorDetailsDTO.getEmailId() + " created on : " + service.getAuditDateStr(), getUserSessionObject().getLoginId());
			addActionMessage(getText("com.edupath.action.add.msg.successful", new String[]
			{
				"Facilitator"
			}));

		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		execute();
		return SUCCESS;
	}

	public String update()
	{
		OUT.debug("Inside FacilitatorDetailsAction (update method)");
		try
		{
			if (facilitatorDetailsDTO.getDobStr() != null && !facilitatorDetailsDTO.getDobStr().trim().equals(""))
			{
				facilitatorDetailsDTO.setDob(TimeUtil.getDate(facilitatorDetailsDTO.getDobStr(), TimeUtil.FILTER_DATE_FORMAT));
			}
			FacilitatorDetailsService service = new FacilitatorDetailsService();
			boolean isUpdated = service.updateFacilitatorDetailsById(facilitatorDetailsDTO);

			if (!isUpdated)
			{
				addActionError(getText("com.edupath.action.modify.error", new String[]
				{
						"Facilitator",
						"email id already exit"
				}));
				setCityMap();
				setHighestQualificationMap();
				setType();
				return "insert";
			}
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.USER_DEFINITION,
					"Facilitator :" + facilitatorDetailsDTO.getEmailId() + " updated on : " + service.getAuditDateStr(), getUserSessionObject().getLoginId());
			addActionMessage(getText("com.edupath.action.modify.successful", new String[]
			{
				"Facilitator"
			}));
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		execute();
		return SUCCESS;
	}

	public String delete()
	{
		OUT.debug("Inside FacilitatorDetailsAction (delete method)");
		try
		{
			FacilitatorDetailsService service = new FacilitatorDetailsService();
			boolean isDeleted = service.deleteFacilitatorDetailsById(facilitatorDetailsDTO);
			if (isDeleted)
			{
				AuditTrailLogger.addAuditInfo(ModuleNameEnum.USER_DEFINITION,
						"Facilitator :" + facilitatorDetailsDTO.getEmailId() + " deleted on : " + service.getAuditDateStr(), getUserSessionObject().getLoginId());
				addActionMessage(getText("com.edupath.action.delete.msg.successful", new String[]
				{
					getText("com.edupath.facilitator.action.title")
				}));
			}
			else
			{
				addActionError(getText("com.edupath.action.delete.dependency.msg.failed", new String[]
				{
					getText("com.edupath.facilitator.action.title")

				}));
			}
		}
		catch (Exception e)
		{
			addActionError(getText("com.edupath.action.delete.msg.failed", new String[]
			{
				getText("com.edupath.facilitator.action.title")

			}));
			OUT.error(e.getMessage());
		}
		execute();
		return SUCCESS;
	}

	@Override
	public FacilitatorDetailsDTO getModel()
	{

		return facilitatorDetailsDTO;
	}

	public FacilitatorDetailsDTO getFacilitatorDetailsDTO()
	{
		return facilitatorDetailsDTO;
	}

	public void setFacilitatorDetailsDTO(FacilitatorDetailsDTO facilitatorDetailsDTO)
	{
		this.facilitatorDetailsDTO = facilitatorDetailsDTO;
	}

	public List<FacilitatorDetailsDTO> getFacilitatorDetailsList()
	{
		return facilitatorDetailsList;
	}

	public void setFacilitatorDetailsList(List<FacilitatorDetailsDTO> facilitatorDetailsList)
	{
		this.facilitatorDetailsList = facilitatorDetailsList;
	}

	public Map<Integer, String> getCityMap()
	{
		return cityMap;
	}

	public void setCityMap(Map<Integer, String> cityMap)
	{
		this.cityMap = cityMap;
	}

	public Map<Integer, String> getHighestQualificationMap()
	{
		return highestQualificationMap;
	}

	public void setHighestQualificationMap(Map<Integer, String> highestQualificationMap)
	{
		this.highestQualificationMap = highestQualificationMap;
	}

	public Map<Character, String> getTypeList()
	{
		return typeList;
	}

	public void setTypeList(Map<Character, String> typeList)
	{
		this.typeList = typeList;
	}

	public String getReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(String readOnly)
	{
		this.readOnly = readOnly;
	}

	public List<Integer> getFaceToFaceCityIds() {
		return faceToFaceCityIds;
	}

	public void setFaceToFaceCityIds(List<Integer> faceToFaceCityIds) {
		this.faceToFaceCityIds = faceToFaceCityIds;
	}

	public List<Integer> getOnCallCityIds() {
		return onCallCityIds;
	}

	public void setOnCallCityIds(List<Integer> onCallCityIds) {
		this.onCallCityIds = onCallCityIds;
	}

	public int getFacilitatorId() {
		return facilitatorId;
	}

	public void setFacilitatorId(int facilitatorId) {
		this.facilitatorId = facilitatorId;
	}
	

}
