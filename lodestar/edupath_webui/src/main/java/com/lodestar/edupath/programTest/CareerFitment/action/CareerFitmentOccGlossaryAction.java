package com.lodestar.edupath.programTest.CareerFitment.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AlertsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.induoccchoice.service.OccupationGlossaryService;
import com.lodestar.edupath.programTest.CareerFitment.service.CareerFitmentOccGlossaryService;
import com.lodestar.edupath.tum.service.StudentTUMService;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class CareerFitmentOccGlossaryAction  extends BaseAction {


	private static final long								serialVersionUID	= 1L;
	private static final Logger								OUT					= LoggerFactory.getLogger(CareerFitmentOccGlossaryAction.class);
	private int												pageNumber;
	private Map<String, Map<String, List<OccupationDTO>>>	occupGlossMap		= new HashMap<String, Map<String, List<OccupationDTO>>>();
	private Map<String, List<AlertsDTO>>					alerts				= new HashMap<>();
	private int												occupCount;
	private int												studentId;
	private String[] 										searchOccName;
	StudentDetailsDTO										student				= new StudentDetailsDTO();
	StudentDetailsDAO 										studentDAO = new StudentDetailsDAO();
	private String isSearched;
	
	 
	@SuppressWarnings("unchecked")
	public String execute()
	{
		OUT.info("CareerFitmentOccGlossaryAction : inside execute method");
		try
		{
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			studentId = student.getId();
			OUT.debug("Get Occupation golossar details for student:{}", student);
			//START Sasedeve  by Mrutyunjaya on date 04-04-2017
			Map<String, Object> occupationGlossaryDetails = new CareerFitmentOccGlossaryService().getOccupationGlossaryDetails(pageNumber,studentId);
			OUT.debug("bharath CareerFitmentOccGlossaryAction occupationGlossaryDetails:{}",occupationGlossaryDetails);
			//END Sasedeve  by Mrutyunjaya on date 04-05-2017
			occupGlossMap = (Map<String, Map<String, List<OccupationDTO>>>) occupationGlossaryDetails.get("occupation");
			OUT.debug("bharath CareerFitmentOccGlossaryAction occupGlossMap:{}",occupGlossMap);
//			alerts = (Map<String, List<AlertsDTO>>) occupationGlossaryDetails.get("alerts");
			OUT.debug("bharath CareerFitmentOccGlossaryAction alerts:{}",alerts);
			occupCount = new CareerFitmentOccGlossaryService().getOccupCount();
			isSearched="false";
			OUT.debug("bharath CareerFitmentOccGlossaryAction isSearched:{}",isSearched);
			setResopns();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String searchOcc()
	{
		try
		{
			searchOccName =   request.getParameter("occName").split(" ");
			OUT.debug("bharath CareerFitmentOccGlossaryAction searchOccName:{}",searchOccName);
			List<String> occList = new ArrayList<String>();
			for(String name :searchOccName )
			{
				occList.add(name);
			}
			Map<String, Object> occupationGlossaryDetails=new CareerFitmentOccGlossaryService().getSearchCFOccupationGlossaryDetails(occList);
			occupGlossMap = (Map<String, Map<String, List<OccupationDTO>>>) occupationGlossaryDetails.get("occupation");
			OUT.debug("bharath CareerFitmentOccGlossaryAction occupGlossMap:{}",occupGlossMap);
//			alerts = (Map<String, List<AlertsDTO>>) occupationGlossaryDetails.get("alerts");
			OUT.debug("bharath CareerFitmentOccGlossaryAction alerts:{}",alerts);
			isSearched="true";
			OUT.debug("bharath CareerFitmentOccGlossaryAction isSearched:{}",isSearched);
			setResopns();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
		
	}
	
	
	void setResopns()
	{
		JSONArray occupation = getOccupation();
		request.setAttribute("OCCUPATION", occupation);

	}
	public JSONArray getOccupation()
	{
		JSONArray jsonArr = null;
		try
		{
			OccupationDAO occupationDAO = new OccupationDAO();
			List<OccupationDTO> list = occupationDAO.getOccupationDetails();
			if (null != list && !list.isEmpty())
			{
				List<String> occupation = new ArrayList<String>();
				for (OccupationDTO occupationDTO : list)
				{
					occupation.add(CommonUtil.replaceXMLEntities(occupationDTO.getName()));
				}
				jsonArr = new JSONArray(occupation);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception: {}", e);
		}
		return jsonArr;
	}
	
	public Map<String, List<AlertsDTO>> getAlerts()
	{
		return alerts;
	}

	public int getPageNumber()
	{
		return pageNumber;
	}

	public void setPageNumber(int pageNumber)
	{
		this.pageNumber = pageNumber;
	}

	public Map<String, Map<String, List<OccupationDTO>>> getOccupGlossMap()
	{
		return occupGlossMap;
	}

	public int getOccupCount()
	{
		return occupCount;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public String getIsSearched() {
		return isSearched;
	}

	public void setIsSearched(String isSearched) {
		this.isSearched = isSearched;
	}

	 
}
