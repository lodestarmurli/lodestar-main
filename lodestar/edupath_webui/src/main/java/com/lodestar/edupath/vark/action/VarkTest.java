package com.lodestar.edupath.vark.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.vark.VarkStudentAnswerDAO;
import com.lodestar.edupath.dataaccessobject.dao.vark.VarkStudentDAO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentAnswerDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.util.EncryptionDecryptionData;
import com.lodestar.edupath.vark.service.VarkTestService;

public class VarkTest  extends BaseAction{

	private static final long			serialVersionUID	= 6090415257988873149L;
	private static Logger				OUT					= LoggerFactory.getLogger(VarkTest.class);
	private String				answerList;
	VarkTestService service;
	UserSessionObject sessionObj = new UserSessionObject();
	UserDetailDTO userDetailDTo = null;
	private String						token;
	private int					userid;
	
	
	
	@Override
	public String execute() throws Exception
	{
		OUT.debug("Inside VarkTest (execute method)");
		service = new VarkTestService();
		StudentDetailsDTO studentDTO = new StudentDetailsDTO();
		try
		{
			VarkStudentDTO _VSDTO = new VarkStudentDTO();
			VarkStudentDAO _VSDAO = new VarkStudentDAO();
			_VSDTO=_VSDAO.getVarkStudentByToken(token);
			OUT.debug("bharath vark WelcomeScreen _VSDTO:{}",_VSDTO);
			if(_VSDTO.getTestTaken()==1)
			{
				return "THANKYOU";
			}
			String jsonStr=new EncryptionDecryptionData().Decrypt(token);
			JSONObject jsonDataObject = new JSONObject(jsonStr.trim());
			OUT.debug("bharath vark WelcomeScreen jsonDataObject:{}",jsonDataObject);
			if(jsonDataObject.has("LdId"))
			{
				userDetailDTo = service.getUserDetailTO(jsonDataObject.getString("LdId"));
				if(userDetailDTo != null)
				{
					OUT.debug("bharath vark WelcomeScreen userDetailDTo:{}",userDetailDTo);
					studentDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userDetailDTo);
					OUT.debug("bharath vark WelcomeScreen studentDTO:{}",studentDTO);
					userid=userDetailDTo.getId();
					sessionObj.setId(userDetailDTo.getId());
					sessionObj.setFullName(CommonUtil.replaceXMLEntities(studentDTO.getName()));
					sessionObj.setLoginId(userDetailDTo.getLoginId());
					sessionObj.setRoleId(userDetailDTo.getRoleId());
					sessionObj.setRoleWeight(userDetailDTo.getRoleWeight());
					sessionObj.setRoleTypeId(userDetailDTo.getRoleTypeId());
					sessionObj.setTrial(true);
					OUT.debug("bharath vark WelcomeScreen sessionObj:{}",sessionObj);
					request.setAttribute(ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty(), sessionObj);
					service = new VarkTestService();
					JSONArray jsonArray =service.getStudentVarkAnswer(studentDTO.getId());
					request.setAttribute("studentAnswerList", jsonArray);
					
					
				}else
				{
	//				return ERROR;
				}
			}
			 
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		return SUCCESS;
	}
	
	
	public String insertAjax()
	{
		OUT.debug("Inside VarkTest (insertAjax method)");
		try
		{
			service = new VarkTestService();
			
			OUT.debug("bharath VarkTest insertAjax answerList:{}, userid:{}",answerList,userid);
			boolean result = service.insertAnswer(answerList,userid);
			OUT.debug("bharath VarkTest insertAjax result:{}",result);
		
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}
	
	public String deleteAjax()
	{
		OUT.debug("Inside VarkTest (deleteAjax method)");
		service = new VarkTestService();
		try
		{
			OUT.debug("bharath VarkTest deleteAjax answerList:{}, userid:{}",answerList,userid);
			boolean result = service.deleteAnswer(answerList,userid);
			OUT.debug("bharath VarkTest deleteAjax result:{}",result);
		
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}
	
	public String submit()
	{
		OUT.debug("Inside VarkTest (submit method)");
		service = new VarkTestService();
		try
		{

			OUT.debug("bharath VarkTest submit  userid:{}",userid);
			int result = service.submitStudentVarkResult(userid);
			OUT.debug("bharath VarkTest submit result:{}",result);
			
		
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return "THANKYOU";
	}
	
	

	public String getAnswerList()
	{
		return answerList;
	}

	public void setAnswerList(String answerList)
	{
		this.answerList = answerList;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	
}
