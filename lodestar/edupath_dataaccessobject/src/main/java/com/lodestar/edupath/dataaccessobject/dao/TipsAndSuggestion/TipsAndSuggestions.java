package com.lodestar.edupath.dataaccessobject.dao.TipsAndSuggestion;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions.GSInputDTO;
import com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions.TipsAndSuggestionsSessioncompleteFaciDTO;
import com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions.ViewGSIputsDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;



public class TipsAndSuggestions {
	
	private static final Logger	OUT	= LoggerFactory.getLogger(TipsAndSuggestions.class);
	

	
	public boolean Gettipssuggestionsession1completefaci(int studentid)
	{
		boolean tipssuggestioncompletefaci=false;
		OUT.debug("Inside dao TipsAndSuggestions class: Gettipssuggestionsession1completefaci Method");
		
		try
		{
			TipsAndSuggestionsSessioncompleteFaciDTO tipsandsuggestions=(TipsAndSuggestionsSessioncompleteFaciDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GetSessionTipsAndSuggestionFaciComplete, studentid);
			
			if(tipsandsuggestions!=null && tipsandsuggestions.getIssession1completefaci()==1)
			{
				tipssuggestioncompletefaci=true;
			}
			else
			{
				tipssuggestioncompletefaci=false;
			}
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		
		
		return tipssuggestioncompletefaci;
	}
	
	public boolean Gettipssuggestionsession2completefaci(int studentid)
	{
		boolean tipssuggestioncompletefaci=false;
		OUT.debug("Inside dao TipsAndSuggestions class: Gettipssuggestionsession2completefaci Method");
		
		try
		{
			TipsAndSuggestionsSessioncompleteFaciDTO tipsandsuggestions=(TipsAndSuggestionsSessioncompleteFaciDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GetSessionTipsAndSuggestionFaciComplete, studentid);
			
			if(tipsandsuggestions!=null && tipsandsuggestions.getIssession2completefaci()==1)
			{
				tipssuggestioncompletefaci=true;
			}
			else
			{
				tipssuggestioncompletefaci=false;
			}
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		
		
		return tipssuggestioncompletefaci;
	}
	
	public boolean tipsandsuggestionexitsforstudent(int studentid)
	{
		boolean isexits=false;
		OUT.debug("Inside dao TipsAndSuggestions class: tipsandsuggestionexitsforstudent Method");
		
		try
		{
			TipsAndSuggestionsSessioncompleteFaciDTO tipsandsuggestions=(TipsAndSuggestionsSessioncompleteFaciDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GetSessionTipsAndSuggestionFaciComplete, studentid);
			
			if(tipsandsuggestions!=null)
			{
				isexits=true;
			}
			else
			{
				isexits=false;
			}
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		
		
		return isexits;
	}
	
	public void InsertTipsDetails(SqlSession session, TipsAndSuggestionsSessioncompleteFaciDTO  insertipsdata)
	{
		OUT.debug("Inside dao TipsAndSuggestions class: InsertTipsDetails Method");
		int id = 0;
		try
		{
			
			id = session.insert(MyBatisMappingConstants.Insert_Tips_Data_Session1, insertipsdata);
			OUT.debug("Tips insert id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
	}
	public void UpdateTipsDetails(SqlSession session, TipsAndSuggestionsSessioncompleteFaciDTO  insertipsdata)
	{
		OUT.debug("Inside dao TipsAndSuggestions class: UpdateTipsDetails Method");
		int id = 0;
		try
		{
			
			id = session.update(MyBatisMappingConstants.Update_Tips_Data_Session1, insertipsdata);
			OUT.debug("Tips Update id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
	}

	public void insertgsinputdata(SqlSession session,GSInputDTO gsinputdata)
	{
		OUT.debug("Inside dao TipsAndSuggestions class: insertgsinputdata Method");
		
		int id = 0;
		try
		{
			
			id = session.insert(MyBatisMappingConstants.GS_Input_Answer_Data, gsinputdata);
			OUT.debug("GSInput answer insert id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		
	}
	
	public void InsertGSInputDetails(SqlSession session, TipsAndSuggestionsSessioncompleteFaciDTO  insertipsdata)
	{
		OUT.debug("Inside dao TipsAndSuggestions class: InsertGSInputDetails Method");
		int id = 0;
		try
		{
			
			id = session.insert(MyBatisMappingConstants.Insert_Tips_Data_Session2, insertipsdata);
			OUT.debug("Tips insert id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
	}
	public void UpdateGSinputDetails(SqlSession session, TipsAndSuggestionsSessioncompleteFaciDTO  insertipsdata)
	{
		OUT.debug("Inside dao TipsAndSuggestions class: UpdateGSinputDetails Method");
		int id = 0;
		try
		{
			
			id = session.update(MyBatisMappingConstants.Update_Tips_Data_Session2, insertipsdata);
			OUT.debug("Tips Update id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
	}

	
	public List<ViewGSIputsDTO> getGSinputsDetails(ViewGSIputsDTO gsinputsdto) throws Exception
	{
		List<ViewGSIputsDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.Get_GS_Iput_Data, gsinputsdto);
		OUT.debug("Number of GS Iputs details found  is {}", resultList != null ? resultList.size() : 0);
		return resultList;
	}
	
	public UserDetailDTO GetUserDetailsById(int studentId) throws Exception
	{
		
		UserDetailDTO userDetailDTo=new UserDetailDTO();
		
		userDetailDTo=(UserDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.Student_UserDetails_By_Student_id, studentId);
		
		return userDetailDTo;
	}
	
	
}
