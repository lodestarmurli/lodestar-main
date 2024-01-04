package com.lodestar.edupath.FreeSIATTest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.APISService;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.student.service.StudentDetailService;
import com.lodestar.edupath.tum.questionnaire.service.InterestService;
import com.lodestar.edupath.tum.service.StudentTUMService;
import com.lodestar.edupath.util.EncryptionDecryptionData;

public class TYEProgTest extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(TYEProgTest.class);
	private boolean isInterestCompleted;
	private int pageAction=1;
	private String				answerList;
	private int					userid;
	private String              studentAnswerList;
	private String				token;
	private List<ClassDTO>			classList			= new ArrayList<ClassDTO>();
	private String 				Stnumber;
	private String 				Stemail;
	
	
	public String getStnumber() {
		return Stnumber;
	}
	public void setStnumber(String stnumber) {
		Stnumber = stnumber;
	}
	public String getStemail() {
		return Stemail;
	}
	public void setStemail(String stemail) {
		Stemail = stemail;
	}
	public List<ClassDTO> getClassList() {
		return classList;
	}
	public void setClassList(List<ClassDTO> classList) {
		this.classList = classList;
	}
	public boolean isInterestCompleted() {
		return isInterestCompleted;
	}
	public void setInterestCompleted(boolean isInterestCompleted) {
		this.isInterestCompleted = isInterestCompleted;
	}
	public int getPageAction() {
		return pageAction;
	}
	public void setPageAction(int pageAction) {
		this.pageAction = pageAction;
	}
	public String getAnswerList() {
		return answerList;
	}
	public void setAnswerList(String answerList) {
		this.answerList = answerList;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getStudentAnswerList() {
		return studentAnswerList;
	}
	public void setStudentAnswerList(String studentAnswerList) {
		this.studentAnswerList = studentAnswerList;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String Start()
	{
		OUT.debug("Inside TYEProgTest class : Start method");
		try
		{
			String id=null;
			if(token!=null && !token.equals(""))
			{
			
				id=new EncryptionDecryptionData().Decrypt(token);
				OUT.debug("After Decrypted ID IS==>"+id);
				if(id!=null && !id.trim().equals(""))
				{
					
					APISService apiservice=new APISService();
					LeadParentDTO leadparent=new LeadParentDTO();
					leadparent= apiservice.getLeadDetails(Integer.parseInt(id.trim()));
					if(leadparent!=null)
					{
						if(leadparent.getId()!=0 && leadparent.getStudentRegister()==1 && leadparent.getStudentID()!=0)
						{
							
							userid=Integer.parseInt(leadparent.getLDID().trim().substring(2));
							
							InterestService		service=new InterestService();
							
							isInterestCompleted = service.isIntrestCompleted(userid);
							
							JSONArray jsonArray = null;
							if (!isInterestCompleted)
							{
								jsonArray = service.getStudentCGTBySection(userid);
							}
							
							pageAction = 1;
							 
							 if(jsonArray!=null)
							 {
							 
								 	studentAnswerList=jsonArray.toString();
							 }
						}
						else if(leadparent.getId()!=0 && leadparent.getStudentRegister()==0)
						{
							OUT.debug("Student Need To Register");
							StudentDetailService	service=new StudentDetailService();
							classList = service.getClassList();
							
							if(leadparent.getStudentContactNo()!=null && !leadparent.getStudentContactNo().trim().equals(""))
							{
								Stnumber=leadparent.getStudentContactNo().trim();
							}
							if(leadparent.getStudentEmailID()!=null && !leadparent.getStudentEmailID().trim().equals(""))
							{
								Stemail=leadparent.getStudentEmailID().trim();
							}
						
							
							
							
							
							return "studentregister";
						}
						else
						{
							OUT.debug("Second Error");
							return "error";
						}
					}
					else
					{
						OUT.debug("Second Error");
						return "error";
					}
				
				}
				else
				{
					OUT.debug("Second Error");
					return "error";
				}
				
				
			}
			else
			{
				OUT.debug("Second Error");
				return "error";
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return "error";
		}
		return SUCCESS;
	}
	
	public String insert()
	{
		OUT.debug("Inside TYEProgTest class : insert method");
		try
		{
			InterestService		service=new InterestService();
			if(answerList!=null)
			{
				
					boolean result = service.insertAnswer(answerList, userid);
					StudentTUMService tumservice = new StudentTUMService();
					tumservice.updateTUMSession(userid);
					isInterestCompleted=true;
					
					OUT.debug("Inside TYEProgTest class : insert method  userid"+userid);
					
					OUT.debug("Inside TYEProgTest class : insert method  token"+token);
				if(token!=null && !token.trim().equals(""))
				{
					String id=null;
					id=new EncryptionDecryptionData().Decrypt(token);
					if(id!=null && !id.trim().equals(""))
					{
						OUT.debug("Inside TYEProgTest class : insert method  ID==="+id);
						APISService apiservice=new APISService();
						LeadParentDTO leadparent=new LeadParentDTO();
						leadparent= apiservice.getLeadDetails(Integer.parseInt(id.trim()));
						if(leadparent!=null)
						{
							if(leadparent.getStudentTestTaken()==0 && leadparent.getId()!=0)
							{
								OUT.debug("Inside TYEProgTest class : insert method  Update Test Taken");
								
								apiservice.UpdateStudentTestTaken(leadparent.getId());
								
								
								service.sendMailForTrialStudentTYEProgTest(userid,token,2,leadparent);
								
								
							}
						}
					}
				}
				
			}
			else
			{
				isInterestCompleted=true;
			}
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return "success";
	}
	
	public String insertAjax()
	{
		OUT.debug("Inside TYEProgTest class : insertAjax method");
		try
		{
			InterestService		service=new InterestService();
			
			
			if(answerList!=null)
			{
			
				service = new InterestService();
				boolean result = service.insertAnswer(answerList, userid);
				StudentTUMService tumservice = new StudentTUMService();
				tumservice.updateTUMSession(userid);
			}
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}
	
	public String execute()
	{
		StudentDetailService	service=new StudentDetailService();
		classList = service.getClassList();
		return "studentregister";
	}
	
}
