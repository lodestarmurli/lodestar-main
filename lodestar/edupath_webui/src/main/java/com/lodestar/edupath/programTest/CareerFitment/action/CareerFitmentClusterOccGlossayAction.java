package com.lodestar.edupath.programTest.CareerFitment.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AlertsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.induoccchoice.service.OccupationGlossaryService;
import com.lodestar.edupath.programTest.CareerFitment.service.CareerFitmentClusterOccGlossayService;

public class CareerFitmentClusterOccGlossayAction  extends BaseAction{
	
	 
	private static final long serialVersionUID = 1L;
	private static final Logger	OUT	= LoggerFactory.getLogger(CareerFitmentClusterOccGlossayAction.class);
	private int												occupCount;
	private int												studentId;
	private int												pageNumber;
	private Map<String, Map<String, List<OccupationDTO>>>	occupGlossMap		= new HashMap<String, Map<String, List<OccupationDTO>>>();
	private List<ClusterDTO>								clusterGloss 		= new ArrayList<ClusterDTO>();
	private Map<String, List<AlertsDTO>>					alerts				= new HashMap<>();
	StudentDetailsDTO	student				= new StudentDetailsDTO();
	StudentDetailsDAO studentDAO = new StudentDetailsDAO();
	

	@SuppressWarnings("unchecked")
	public String execute()
	{
		OUT.info("CareerFitmentClusterOccGlossayAction : inside execute method");
		try
		{
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			studentId = student.getId();
			OUT.debug("Get CareerFitmentOptionAOccAction golossar details for studentId:{}", studentId);
			clusterGloss= new CareerFitmentClusterOccGlossayService().getClusterList();
			OUT.debug("Get CareerFitmentClusterOccGlossayAction clusterGloss:{}", clusterGloss);
			
			Map<String, Object> occupationGlossaryDetails = new CareerFitmentClusterOccGlossayService().getOccupationGlossaryDetails(pageNumber,studentId);
			OUT.debug("Get CareerFitmentClusterOccGlossayAction occupationGlossaryDetails:{}", occupationGlossaryDetails);
			
			occupGlossMap = (Map<String, Map<String, List<OccupationDTO>>>) occupationGlossaryDetails.get("occupation");
			OUT.debug("Get CareerFitmentClusterOccGlossayAction occupGlossMap:{}", occupGlossMap);
			
			alerts = (Map<String, List<AlertsDTO>>) occupationGlossaryDetails.get("alerts");
			OUT.debug("bharath CareerFitmentClusterOccGlossayAction alerts:{}",alerts);

			occupCount = new CareerFitmentClusterOccGlossayService().getOccupCount();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
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

	public List<ClusterDTO> getClusterGloss() {
		return clusterGloss;
	}

	public void setClusterGloss(List<ClusterDTO> clusterGloss) {
		this.clusterGloss = clusterGloss;
	}

	public void setOccupCount(int occupCount) {
		this.occupCount = occupCount;
	}

	public void setOccupGlossMap(Map<String, Map<String, List<OccupationDTO>>> occupGlossMap) {
		this.occupGlossMap = occupGlossMap;
	}

	public void setAlerts(Map<String, List<AlertsDTO>> alerts) {
		this.alerts = alerts;
	}
	

	
}
