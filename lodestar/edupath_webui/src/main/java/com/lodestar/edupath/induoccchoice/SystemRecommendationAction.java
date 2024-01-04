package com.lodestar.edupath.induoccchoice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.induoccchoice.service.SystemRecommendationService;

public class SystemRecommendationAction extends BaseAction
{
	private static final long				serialVersionUID		= 1L;
	private static final Logger				OUT						= LoggerFactory.getLogger(SystemRecommendationAction.class);
	private int								studentId;
	private Map<String, Object>				systemRecommendation	= new HashMap<String, Object>();
	private static HashMap<Integer, String>	fitmentcolors			= new HashMap<Integer, String>();

	static
	{
		fitmentcolors.put(ApplicationConstants.FitMent.HIG.getScore(), ApplicationConstants.FitMent.HIG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.A_AVG.getScore(), ApplicationConstants.FitMent.A_AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.AVG.getScore(), ApplicationConstants.FitMent.AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.LOW.getScore(), ApplicationConstants.FitMent.LOW.getColor());
	}

	public String execute()
	{
		try
		{
			if (studentId == 0)
			{
				studentId = getStudentSessionObject().getId();
			}

			OUT.debug("Get System Recommendation for studentId:{}", studentId);
			SystemRecommendationService service = new SystemRecommendationService();
			systemRecommendation = service.getSystemRecommendation(studentId);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public Map<String, Object> getSystemRecommendation()
	{
		return systemRecommendation;
	}

	public HashMap<Integer, String> getFitmentcolors()
	{
		return fitmentcolors;
	}
}
