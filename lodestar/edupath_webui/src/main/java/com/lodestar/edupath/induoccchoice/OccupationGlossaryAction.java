package com.lodestar.edupath.induoccchoice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AlertsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.induoccchoice.service.OccupationGlossaryService;

public class OccupationGlossaryAction extends BaseAction
{

	private static final long								serialVersionUID	= 1L;
	private static final Logger								OUT					= LoggerFactory.getLogger(OccupationGlossaryAction.class);
	private int												pageNumber;
	private Map<String, Map<String, List<OccupationDTO>>>	occupGlossMap		= new HashMap<String, Map<String, List<OccupationDTO>>>();
	private Map<String, List<AlertsDTO>>					alerts				= new HashMap<>();
	private int												occupCount;
	private int												studentId;
	//START Sasedeve  by Mrutyunjaya on date 04-05-2017
			private static HashMap<Integer, String>	fitmentcolors			= new HashMap<Integer, String>();

			static
			{
				fitmentcolors.put(ApplicationConstants.FitMent.HIG.getScore(), ApplicationConstants.FitMent.HIG.getColor());
				fitmentcolors.put(ApplicationConstants.FitMent.A_AVG.getScore(), ApplicationConstants.FitMent.A_AVG.getColor());
				fitmentcolors.put(ApplicationConstants.FitMent.AVG.getScore(), ApplicationConstants.FitMent.AVG.getColor());
				fitmentcolors.put(ApplicationConstants.FitMent.LOW.getScore(), ApplicationConstants.FitMent.LOW.getColor());
			}
			
			//END Sasedeve  by Mrutyunjaya on date 04-05-2017
	@SuppressWarnings("unchecked")
	public String execute()
	{
		OUT.info("OccupationGlossaryAction : inside execute method");
		try
		{
			studentId = getStudentSessionObject().getId();
			OUT.debug("Get Occupation golossar details for studentId:{}", studentId);
			//START Sasedeve  by Mrutyunjaya on date 04-04-2017
			Map<String, Object> occupationGlossaryDetails = new OccupationGlossaryService().getOccupationGlossaryDetails(pageNumber,studentId);
			
			//END Sasedeve  by Mrutyunjaya on date 04-05-2017
			occupGlossMap = (Map<String, Map<String, List<OccupationDTO>>>) occupationGlossaryDetails.get("occupation");
			alerts = (Map<String, List<AlertsDTO>>) occupationGlossaryDetails.get("alerts");

			occupCount = new OccupationGlossaryService().getOccupCount();
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
	
	//START Sasedeve  by Mrutyunjaya on date 04-05-2017
			public HashMap<Integer, String> getFitmentcolors()
			{
				return fitmentcolors;
			}
		//END Sasedeve  by Mrutyunjaya on date 04-05-2017

}
