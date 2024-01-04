package com.lodestar.edupath.TipsAndSuggestionService;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.TipsAndSuggestion.TipsAndSuggestions;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions.GSInputDTO;
import com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions.TipsAndSuggestionsSessioncompleteFaciDTO;
import com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions.ViewGSIputsDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;




public class TipsAndSuggestionService {
	private static final Logger	OUT	= LoggerFactory.getLogger(TipsAndSuggestionService.class.getName());
	private static final String	FEED_BACK		= "FEED_BACK";
	private static final String	QUESTION_ANS	= "QUESTION_ANS";
	private static final String	QUESTION_NO		= "QUESTION_NO";
	
	public boolean GetTipsSession1CompletedFaci(int studentid)
	{
		
		boolean iscompletefacitips=false;
		OUT.debug("Inside TipsAndSuggestionService class: GetTipsSession1CompletedFaci Method");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		UserDetailDTO userDetailDTo = null;
		
		try
		{
			Date currntdate=format.parse("2017-09-22 00:00:00");
			
			
			
			
			TipsAndSuggestions tipssuggestions=new TipsAndSuggestions();
			userDetailDTo=tipssuggestions.GetUserDetailsById(studentid);
			
			if(userDetailDTo!=null && userDetailDTo.getCreatedOn()!=null && userDetailDTo.getCreatedOn().after(currntdate))
			{
			
				iscompletefacitips=tipssuggestions.Gettipssuggestionsession1completefaci(studentid);
			}
			else
			{
				iscompletefacitips=true;
			}
		
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		return iscompletefacitips;
	}
	
	
	
	public boolean GetTipsSession2CompletedFaci(int studentid)
	{
		
		boolean iscompletefacitips=false;
		OUT.debug("Inside TipsAndSuggestionService class: GetTipsSession2CompletedFaci Method");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		UserDetailDTO userDetailDTo = null;
		
		try
		{
			
			Date currntdate=format.parse("2017-09-22 00:00:00");
			TipsAndSuggestions tipssuggestions=new TipsAndSuggestions();
			
			userDetailDTo=tipssuggestions.GetUserDetailsById(studentid);
			
			if(userDetailDTo!=null && userDetailDTo.getCreatedOn()!=null && userDetailDTo.getCreatedOn().after(currntdate))
			{
			   iscompletefacitips=tipssuggestions.Gettipssuggestionsession2completefaci(studentid);
			}
			else
			{
				iscompletefacitips=true;
			}
		
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		return iscompletefacitips;
	}

	public boolean ChecktipsSessionFaciExits(int studentid)
	{
		boolean studentexist=false;
		
		OUT.debug("Inside TipsAndSuggestionService class: ChecktipsSessionFaciExits Method");
		
		try
		{
			TipsAndSuggestions tipssuggestions=new TipsAndSuggestions();
			
			studentexist=tipssuggestions.tipsandsuggestionexitsforstudent(studentid);
		
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		
		
		
		return studentexist;
	}
	public void insertstipsdata(TipsAndSuggestionsSessioncompleteFaciDTO  insertipsdata)
	{
		
		
		OUT.debug("Inside TipsAndSuggestionService class: insertstipsdata Method");
		SqlSession session = null;
		try
		{
			TipsAndSuggestions tipssuggestions=new TipsAndSuggestions();
			session = MyBatisManager.getInstance().getSession();
			tipssuggestions.InsertTipsDetails(session,insertipsdata);
			session.commit();
		}
		catch (Exception e)
		{
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		
		
		
		
	}
	
	
	public void Updatetipsdata(TipsAndSuggestionsSessioncompleteFaciDTO  insertipsdata)
	{
		
		
		OUT.debug("Inside TipsAndSuggestionService class: Updatetipsdata Method");
		SqlSession session = null;
		try
		{
			TipsAndSuggestions tipssuggestions=new TipsAndSuggestions();
			session = MyBatisManager.getInstance().getSession();
			tipssuggestions.UpdateTipsDetails(session,insertipsdata);
			session.commit();
		}
		catch (Exception e)
		{
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		
		
		
		
	}
	
	public void insergsinputdata(String jsondata,int studentid,int faciuserid)
	{
		OUT.debug("Inside TipsAndSuggestionService class: insergsinputdata Method");
		SqlSession session = null;
		try
		{
			TipsAndSuggestions tipssuggestions=new TipsAndSuggestions();
			session = MyBatisManager.getInstance().getSession();
			JSONObject feedback = new JSONObject(jsondata);
			JSONArray feedbackAnswers = feedback.getJSONArray(FEED_BACK);
			JSONObject feedbackAns = null;
			GSInputDTO gsinputdata=null;
			TipsAndSuggestionsSessioncompleteFaciDTO  insertipsdata=new TipsAndSuggestionsSessioncompleteFaciDTO();
			
			FacilitatorDetailsDTO facidto=new FacilitatorDetailsDAO().getFacilitatorDetailsByUserIdTypeInt(faciuserid);
			
			for (int i = 0; i < feedbackAnswers.length(); i++)
			{
				gsinputdata=new GSInputDTO();
				feedbackAns = feedbackAnswers.getJSONObject(i);
				
				gsinputdata.setStudentid(studentid);
				gsinputdata.setQuestionno(feedbackAns.getInt(QUESTION_NO));
				gsinputdata.setAnswer(feedbackAns.getString(QUESTION_ANS));
				
				tipssuggestions.insertgsinputdata(session,gsinputdata);
				session.flushStatements();
				
			}
			boolean isstudentexistfortipsandsuggestion=tipssuggestions.tipsandsuggestionexitsforstudent(studentid);
			
			if(isstudentexistfortipsandsuggestion)
			{
				//update
				
				insertipsdata.setIssession2completefaci(1);
				insertipsdata.setStudenid(studentid);;
				tipssuggestions.UpdateGSinputDetails(session,insertipsdata);
			}
			else
			{
				//insert
				
				insertipsdata.setFacilitatorid(facidto.getId());
				insertipsdata.setStudenid(studentid);
				insertipsdata.setIssession2completefaci(1);
				tipssuggestions.InsertGSInputDetails(session,insertipsdata);
				
			}
			
			
			session.commit();
		}
		catch (Exception e)
		{
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public List<ViewGSIputsDTO> getGSIputsList(ViewGSIputsDTO gsinputsdto, int loggedInUserId) throws Exception
	{
		OUT.debug("Inside TipsAndSuggestionService class: getGSIputsList Method");
		TipsAndSuggestions tipssuggestions=new TipsAndSuggestions();
		List<ViewGSIputsDTO> gsinputsDetailsList = tipssuggestions.getGSinputsDetails(gsinputsdto);
		for (ViewGSIputsDTO gsinputdetails : gsinputsDetailsList)
		{
			try
			{
				StringBuilder answer = new StringBuilder();
				JSONArray jsonArray = new JSONArray(gsinputdetails.getAnswer());
				for (int i = 0; i < jsonArray.length(); i++)
				{
					if (i != 0)
					{
						answer.append(", ");
					}
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					for (String key : JSONObject.getNames(jsonObject))
					{
						answer.append(key + " = " + jsonObject.getString(key));
					}
				}
				gsinputdetails.setAnswer(answer.toString());
			}
			catch (Exception e)
			{

			}
		}
		return gsinputsDetailsList;
	}

	
	public File reportExcel(String fileName, String destination, ViewGSIputsDTO gsinputsdto) throws Exception
	{
		List<ViewGSIputsDTO> feedbackDetailsList = new TipsAndSuggestions().getGSinputsDetails(gsinputsdto);
		Map<Integer, List<String>> headerMap = prepareColumnLists(feedbackDetailsList);
		return new GSInputsExcelGeneratorEngine().reportExcel(feedbackDetailsList, headerMap, fileName, destination);
	}
	
	
	private Map<Integer, List<String>> prepareColumnLists(List<ViewGSIputsDTO> reportList)
	{
		int firstLevelHeader = 1;
		int seconLevelHeader = 2;
		Map<Integer, List<String>> headerMap = new HashMap<Integer, List<String>>();
		headerMap.put(firstLevelHeader, new ArrayList<String>());
		headerMap.put(seconLevelHeader, new ArrayList<String>());

		ApplicationProperties propInstance = ApplicationProperties.getInstance();
		
		//Start Sasedeve Edited By Mrutyunjaya on Date 08-09-2017
		
		headerMap.get(firstLevelHeader).add("Date");
		headerMap.get(seconLevelHeader).add("");
		
		//END Sasedeve Edited By Mrutyunjaya on Date 08-09-2017
		
		headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.student.name"));
		headerMap.get(seconLevelHeader).add("");

		headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.student.loginid"));
		headerMap.get(seconLevelHeader).add("");
		
		headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.facilitator.name"));
		headerMap.get(seconLevelHeader).add("");
		
		
		headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.class"));
		headerMap.get(seconLevelHeader).add("");

		
		if (reportList != null)
		{
			boolean isMultiAns = false;
			int questionNo = 1;
			int studentId = 0;
			for (ViewGSIputsDTO studentDetailsDTO : reportList)
			{
				if (studentId != 0 && studentId != studentDetailsDTO.getStudentid())
				{
					break;
				}
				isMultiAns = false;
				try
				{
					JSONArray ansArray = new JSONArray(studentDetailsDTO.getAnswer());
					for (int i = 0; i < ansArray.length(); i++)
					{
						headerMap.get(firstLevelHeader).add("Question");
						
						if(studentDetailsDTO.getQuestionno()==1)
						{
							headerMap.get(seconLevelHeader).add("Could you please mention the child's interests and hobbies");
						}
						if(studentDetailsDTO.getQuestionno()==2)
						{
							headerMap.get(seconLevelHeader).add("Have you discussed his/her academic performance");
						}
						if(studentDetailsDTO.getQuestionno()==3)
						{
							headerMap.get(seconLevelHeader).add("Can you describe the nature of the student briefly from whatever you have understood in your limited interaction");
						}
						if(studentDetailsDTO.getQuestionno()==4)
						{
							headerMap.get(seconLevelHeader).add("Have the interest and abilities test results been discussed");
						}
						if(studentDetailsDTO.getQuestionno()==41)
						{
							headerMap.get(seconLevelHeader).add("What was the response of the student / parent about the same");
						}
						if(studentDetailsDTO.getQuestionno()==5)
						{
							headerMap.get(seconLevelHeader).add("What according to you is the child's theme");
						}
						if(studentDetailsDTO.getQuestionno()==6)
						{
							headerMap.get(seconLevelHeader).add("Have the expectations been set with the student and the parent");
						}
						if(studentDetailsDTO.getQuestionno()==7)
						{
							headerMap.get(seconLevelHeader).add("Have you briefed about the IRs and ORs");
						}
						if(studentDetailsDTO.getQuestionno()==8)
						{
							headerMap.get(seconLevelHeader).add("What were the parameters considered to create the occupation wish list for the child");
						}
						if(studentDetailsDTO.getQuestionno()==9)
						{
							headerMap.get(seconLevelHeader).add("Did the parent / student has any suggestion on the WOJs");
						}
						if(studentDetailsDTO.getQuestionno()==10)
						{
							headerMap.get(seconLevelHeader).add("Which are the industries the student / parent showed interest in");
						}
						
						
						
					}
					isMultiAns = true;
					
				}
				catch (Exception e)
				{
					
					// Do nothing
					isMultiAns = false;
				}
				if (!isMultiAns)
				{
					headerMap.get(firstLevelHeader).add("Question");
					
					if(studentDetailsDTO.getQuestionno()==1)
					{
						headerMap.get(seconLevelHeader).add("Could you please mention the child's interests and hobbies");
					}
					if(studentDetailsDTO.getQuestionno()==2)
					{
						headerMap.get(seconLevelHeader).add("Have you discussed his/her academic performance");
					}
					if(studentDetailsDTO.getQuestionno()==3)
					{
						headerMap.get(seconLevelHeader).add("Can you describe the nature of the student briefly from whatever you have understood in your limited interaction");
					}
					if(studentDetailsDTO.getQuestionno()==4)
					{
						headerMap.get(seconLevelHeader).add("Have the interest and abilities test results been discussed");
					}
					if(studentDetailsDTO.getQuestionno()==41)
					{
						headerMap.get(seconLevelHeader).add("What was the response of the student / parent about the same");
					}
					if(studentDetailsDTO.getQuestionno()==5)
					{
						headerMap.get(seconLevelHeader).add("What according to you is the child's theme");
					}
					if(studentDetailsDTO.getQuestionno()==6)
					{
						headerMap.get(seconLevelHeader).add("Have the expectations been set with the student and the parent");
					}
					if(studentDetailsDTO.getQuestionno()==7)
					{
						headerMap.get(seconLevelHeader).add("Have you briefed about the IRs and ORs");
					}
					if(studentDetailsDTO.getQuestionno()==8)
					{
						headerMap.get(seconLevelHeader).add("What were the parameters considered to create the occupation wish list for the child");
					}
					if(studentDetailsDTO.getQuestionno()==9)
					{
						headerMap.get(seconLevelHeader).add("Did the parent / student has any suggestion on the WOJs");
					}
					if(studentDetailsDTO.getQuestionno()==10)
					{
						headerMap.get(seconLevelHeader).add("Which are the industries the student / parent showed interest in");
					}
					
					
				}
				questionNo++;
				studentId = studentDetailsDTO.getStudentid();
			}
		}
		return headerMap;
	}

	
	
}
