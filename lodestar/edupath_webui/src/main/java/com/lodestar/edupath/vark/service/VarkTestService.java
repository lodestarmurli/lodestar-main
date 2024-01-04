package com.lodestar.edupath.vark.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.vark.VarkStudentAnswerDAO;
import com.lodestar.edupath.dataaccessobject.dao.vark.VarkStudentDAO;
import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTForEvalDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentAnswerDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentResultDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;


public class VarkTestService {
	
	private static Logger				OUT					= LoggerFactory.getLogger(VarkTestService.class);
	SqlSession session = null;
	
	
	public UserDetailDTO getUserDetailTO(String loginId) throws Exception
	{
		UserDetailDTO userDetailsTO = new UserDetailDTO();
		userDetailsTO.setLoginId(loginId);
		IModel resultAsObject = MyBatisManager.getInstance().getResultAsObject(UserDetailDTO.GET_USER_BY_LOGINID, userDetailsTO);
		UserDetailDTO userTO = (UserDetailDTO) resultAsObject;
		return userTO;
	}
	
	public boolean insertAnswer(String answerList,int userId) throws Exception 
	{
		boolean result = true;
		try
		{
			StudentDetailsDAO sdao = new StudentDetailsDAO();
			StudentDetailsDTO student = sdao.getStudentDetailsByUserId(userId);
			JSONObject jsonobj =new JSONObject(answerList);
			session = MyBatisManager.getInstance().getSession();
			if(jsonobj.has("answer") && jsonobj.has("questionNo"))
			{
				VarkStudentAnswerDTO dto = new VarkStudentAnswerDTO();
				VarkStudentAnswerDAO dao = new VarkStudentAnswerDAO();
				dto.setStudentId(student.getId());
				dto.setAnswer((String)jsonobj.get("answer"));
				dto.setQuestionNo((int)jsonobj.get("questionNo"));
				int result1= dao.insertVarkStudentAnswer(session, dto);
				session.commit();
			}
			else
			{
				result=false;
			}
			
			
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			result = false;
			OUT.error("Exception", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return result;
	}

	
	public boolean deleteAnswer(String answerList,int userId) throws Exception 
	{
		OUT.debug("bharath VarkTestService deleteAnswer answerList:{}, userid:{}",answerList,userId);
		boolean result = true;
		try
		{
			StudentDetailsDAO sdao = new StudentDetailsDAO();
			StudentDetailsDTO student = sdao.getStudentDetailsByUserId(userId);
			JSONObject jsonobj =new JSONObject(answerList);
			session = MyBatisManager.getInstance().getSession();
			if(jsonobj.has("answer") && jsonobj.has("questionNo"))
			{
				VarkStudentAnswerDTO dto = new VarkStudentAnswerDTO();
				VarkStudentAnswerDAO dao = new VarkStudentAnswerDAO();
				dto.setStudentId(student.getId());
				dto.setAnswer((String)jsonobj.get("answer"));
				dto.setQuestionNo((int)jsonobj.get("questionNo"));
				int result1= dao.deleteVarkStudentAnswer(session, dto);
				session.commit();
			}
			else
			{
				result=false;
			}
			
			
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			result = false;
			OUT.error("Exception", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return result;
	}
	
	
	public JSONArray getStudentVarkAnswer(int studentId)
	{
		JSONArray finalJsonArray = null;
		try
		{
			List<VarkStudentAnswerDTO> list = new VarkStudentAnswerDAO().getVarkStudentByStudentID(studentId);
			OUT.debug("bharath VarkTestService getStudentVarkAnswer list:{}",list);
			
			if (null != list && !list.isEmpty())
			{
				JSONObject json = null;
				finalJsonArray = new JSONArray();
				for (VarkStudentAnswerDTO dto : list)
				{
					json = new JSONObject();
					json.put("questionNo", dto.getQuestionNo());
					json.put("answer", dto.getAnswer());
					finalJsonArray.put(json);
				}

			}
			
	
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return finalJsonArray;
	}
	
	
	public int submitStudentVarkResult(int userId)
	{
		int result=0;
		List questionCount;
		try
		{
			StudentDetailsDAO sdao = new StudentDetailsDAO();
			StudentDetailsDTO student = sdao.getStudentDetailsByUserId(userId);
			int visual=0,aural=0,readWrite=0,kinesthetic=0;
			List<VarkStudentAnswerDTO> list = new VarkStudentAnswerDAO().getVarkStudentByStudentID(student.getId());
			OUT.debug("bharath VarkTestService SubmitStudentVarkResult list:{}",list);
			VarkStudentAnswerDAO questions = new VarkStudentAnswerDAO();
			questionCount = questions.getNumberOfQuestionsAnswered(student.getId());
			for(VarkStudentAnswerDTO dto : list )
			{
				if(dto.getAnswer().trim().equalsIgnoreCase("V"))
				{
					visual++;
				}
				else if(dto.getAnswer().trim().equalsIgnoreCase("A"))
				{
					aural++;
				}
				else if(dto.getAnswer().trim().equalsIgnoreCase("R"))
				{
					readWrite++;
				}
				else if(dto.getAnswer().trim().equalsIgnoreCase("K"))
				{
					kinesthetic++;
				}
			}
			OUT.debug("bharath VarkTestService SubmitStudentVarkResult V={},A={},R={},K={}",visual,aural,readWrite,kinesthetic);
			VarkStudentResultDTO resultdto = new VarkStudentResultDTO();
			VarkStudentAnswerDAO dao = new VarkStudentAnswerDAO();
			resultdto.setStudentId(student.getId());
			resultdto.setVisual(visual);
			resultdto.setAural(aural);
			resultdto.setReadWrite(readWrite);
			resultdto.setKinesthetic(kinesthetic);
			session = MyBatisManager.getInstance().getSession();
			result=dao.insertVarkStudentResult(session, resultdto);

			VarkStudentDTO _VSDTO = new VarkStudentDTO();
			VarkStudentDAO _VSDAO = new VarkStudentDAO();
			_VSDTO.setStudentId(student.getId());
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			_VSDTO.setStudentTestTime(formatter.parse(formatter.format(date)));
			int id =_VSDAO.updateVarkStudent(session, _VSDTO);
			session.commit();
	
		}
		 catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return result;
	}
	
}
