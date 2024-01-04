package com.lodestar.edupath.TipsAndSuggetions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.TipsAndSuggestionService.TipsAndSuggestionService;
import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions.TipsAndSuggestionsSessioncompleteFaciDTO;


public class TipsuggetionsAction extends BaseAction {
   
	
	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(TipsuggetionsAction.class);
	
	public String execute()
	{
		OUT.debug("Inside TipsAndSuggetions class execute method");
		return "success";
	}
	
	
	public String SubmitSession1TipsAndSuggestion()
	{
		
		OUT.debug("Inside TipsAndSuggetions class SubmitSession1TipsAndSuggestion method");
		
		try
		{
		StudentSessionObject studentSessionObject = getStudentSessionObject();
		
		UserSessionObject userSessionObject = getUserSessionObject();
		FacilitatorDetailsDTO facidto=new FacilitatorDetailsDAO().getFacilitatorDetailsByUserIdTypeInt(userSessionObject.getId());
		TipsAndSuggestionService tipsservice=new TipsAndSuggestionService();
		
		boolean isstudentexistfortipsandsuggestion=tipsservice.ChecktipsSessionFaciExits(studentSessionObject.getId());
		TipsAndSuggestionsSessioncompleteFaciDTO  insertipsdata=new TipsAndSuggestionsSessioncompleteFaciDTO();
		
		insertipsdata.setFacilitatorid(facidto.getId());
		insertipsdata.setIssession1completefaci(1);
		insertipsdata.setStudenid(studentSessionObject.getId());
		if(isstudentexistfortipsandsuggestion)
		{
			//update new record
			OUT.debug("Update Tips And Suggestions");
			
			
			tipsservice.Updatetipsdata(insertipsdata);
			
			
			
		}
		else
		{
			
			//insert new record
			OUT.debug("Inert Tips And Suggestions");
			
			
			tipsservice.insertstipsdata(insertipsdata);
			
			
		}
		
		
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		
		return "success";
	}
	
}
