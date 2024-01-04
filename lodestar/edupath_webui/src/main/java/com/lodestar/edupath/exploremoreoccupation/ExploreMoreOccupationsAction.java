package com.lodestar.edupath.exploremoreoccupation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AreaDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.studentcart.service.StudentCartDetailService;
import com.lodestar.edupath.util.ApplicationProperties;

public class ExploreMoreOccupationsAction extends BaseAction
{

	private static final long						serialVersionUID	= -7958393836499906892L;
	private static Logger							OUT					= LoggerFactory.getLogger(ExploreMoreOccupationsAction.class);
	private Map<String, Map<String, List<AreaDTO>>>	categoryMap			= new HashMap<String, Map<String, List<AreaDTO>>>();
	private String									selectedAnswer;
	private Map<String, List<OccupationDTO>>		manualSearchMap		= new HashMap<String, List<OccupationDTO>>();
	private Integer									studentId;
	private ExploreMoreOccupationService			service;

	//START Sasedeve  by Mrutyunjaya on date 15-05-2017
	private static HashMap<Integer, String>	fitmentcolors			= new HashMap<Integer, String>();

	static
	{
		fitmentcolors.put(ApplicationConstants.FitMent.HIG.getScore(), ApplicationConstants.FitMent.HIG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.A_AVG.getScore(), ApplicationConstants.FitMent.A_AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.AVG.getScore(), ApplicationConstants.FitMent.AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.LOW.getScore(), ApplicationConstants.FitMent.LOW.getColor());
	}
	
	//END Sasedeve  by Mrutyunjaya on date 15-05-2017
	
	
	
	public ExploreMoreOccupationsAction()
	{
		service = new ExploreMoreOccupationService();
	}

	@Override
	public String execute() throws Exception
	{
		OUT.debug("In ExploreMoreOccupations Action ");
		
		service.getCategoryMap(categoryMap);
		return SUCCESS;
	}

	private void requiredField()
	{
		UserSessionObject sessionObject = getUserSessionObject();
		studentId = new StudentCartDetailService().getStudentIdByUserId(sessionObject.getId());
		OUT.debug("bharath inside ExploreMoreOccupationsAction requiredField studentId:{}",studentId);

	}

	public String getSearchResult()
	{
		OUT.debug("inside ExploreMoreOccupationsAction getSearchResult");
		requiredField();
		if (null == selectedAnswer || selectedAnswer.isEmpty())
		{
			addActionError(ApplicationProperties.getInstance().getProperty("com.edupath.action.internal.error"));
			return ERROR;
		}
		try
		{
			//START SASEDEVE Edited by Mrutyunjaya on date 15-05-2017
			OUT.debug("bharath inside ExploreMoreOccupationsAction getSearchResult selectedAnswer:{}",selectedAnswer);
			manualSearchMap = service.getOccupationSearch(selectedAnswer,studentId);
			OUT.debug("bharath inside ExploreMoreOccupationsAction getSearchResult manualSearchMap:{}",manualSearchMap);
			//END SASEDEVE Edited by Mrutyunjaya on date 15-05-2017
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			addActionError(ApplicationProperties.getInstance().getProperty("com.edupath.action.internal.error"));
			return ERROR;
		}
		request.setAttribute("actiontype", "searchResult");
		return SUCCESS;
	}
	
	//cloning above method for calling as from api.  
	public Map<String, List<OccupationDTO>> getSearchResultForAPi(String selectedOcc)
	{
		OUT.debug("inside ExploreMoreOccupationsAction getSearchResult selectedOcc:{}",selectedOcc);
//		requiredField();
		manualSearchMap=null;
		studentId=0;
		if (null == selectedOcc || selectedOcc.isEmpty())
		{
//			addActionError(ApplicationProperties.getInstance().getProperty("com.edupath.action.internal.error"));
//			return ERROR;
		}
		try
		{
			OUT.debug("bharath inside ExploreMoreOccupationsAction getSearchResult selectedOcc:{}",selectedOcc);
			manualSearchMap = service.getOccupationSearch(selectedOcc,studentId);
			OUT.debug("bharath inside ExploreMoreOccupationsAction getSearchResult manualSearchMap:{}",manualSearchMap);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
//			addActionError(ApplicationProperties.getInstance().getProperty("com.edupath.action.internal.error"));
//			return ERROR;
		}
//		request.setAttribute("actiontype", "searchResult");
		return manualSearchMap;
	}

	public String getSelectedAnswer()
	{
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer)
	{
		this.selectedAnswer = selectedAnswer;
	}

	public Map<String, Map<String, List<AreaDTO>>> getCategoryMap()
	{
		return categoryMap;
	}

	public void setCategoryMap(Map<String, Map<String, List<AreaDTO>>> categoryMap)
	{
		this.categoryMap = categoryMap;
	}

	public Map<String, List<OccupationDTO>> getManualSearchMap()
	{
		return manualSearchMap;
	}

	public void setManualSearchMap(Map<String, List<OccupationDTO>> manualSearchMap)
	{
		this.manualSearchMap = manualSearchMap;
	}

	public Integer getStudentId()
	{
		return studentId;
	}

	public void setStudentId(Integer studentId)
	{
		this.studentId = studentId;
	}
	
	//START Sasedeve  by Mrutyunjaya on date 15-05-2017
	public HashMap<Integer, String> getFitmentcolors()
	{
		return fitmentcolors;
	}
//END Sasedeve  by Mrutyunjaya on date 15-05-2017
	
	
}
