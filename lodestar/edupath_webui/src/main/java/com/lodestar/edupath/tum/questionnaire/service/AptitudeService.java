package com.lodestar.edupath.tum.questionnaire.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.CGT.CGTQuestioneriesDAO;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.TumCgtResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendation;
import com.lodestar.edupath.datatransferobject.dto.cgt.CGTQuestioneriesDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTForEvalDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.TumCgtResultDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTestEnum;

public class AptitudeService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(AptitudeService.class);

	public boolean insertAnswer(String answerList, int studentId, long remainigTime)
	{
		OUT.debug("Inside InterestService");
		SqlSession session = null;
		boolean result = true;
		try
		{
			Map<Object, Integer> CGTMap = getCGTQuestioneries();
			if (null != CGTMap && !CGTMap.isEmpty())
			{
				ObjectMapper mapper = new ObjectMapper();
				List<CGTQuestioneriesDTO> list = mapper.readValue(answerList, TypeFactory.collectionType(List.class, CGTQuestioneriesDTO.class));

				if (null != list && !list.isEmpty())
				{
					StudentDetailsDAO dao = new StudentDetailsDAO();
					StudentDetailsDTO student = dao.getStudentDetailsByUserId(studentId);
					session = MyBatisManager.getInstance().getSession();
					StudentCGTDTO studentCGT = null;
					StudentCGTDAO studentCGTDAO = new StudentCGTDAO();
					StudentCGTResult cgtResult = new StudentCGTResult();
					for (CGTQuestioneriesDTO cgtQuestioneriesDTO : list)
					{
						studentCGT = new StudentCGTDTO();
						studentCGT.setAnswer(cgtQuestioneriesDTO.getCorrectAnswer());
						studentCGT.setStudentId(student.getId());
						if (CGTMap.containsKey(cgtQuestioneriesDTO.getSlNo()))
						{
							studentCGT.setQuestionId(CGTMap.get(cgtQuestioneriesDTO.getSlNo()));
						}
						int id = studentCGTDAO.updateStudentCGT(session, studentCGT);
						if (id == 0)
						{
							studentCGTDAO.insertStudentCGT(session, studentCGT);
						}
					}
					cgtResult.setStudentId(student.getId());
					cgtResult.setRemainigTime(remainigTime);
					cgtResult.setAptitudeComplete(StudentTestEnum.COMPLETED.getValue());
					studentCGTDAO.insertORUpdateStudentCGTResult(session, cgtResult);
					session.commit();
				}
				else
				{
					return false;
				}

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

	public StudentCGTResult getStudentCGTResult(int studentId)
	{
		String cgtMassage = null;
		StudentCGTResult cgtResult = null;
		try
		{
			StudentCGTDAO studentCGTDAO = new StudentCGTDAO();
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO student = dao.getStudentDetailsByUserId(studentId);
			cgtResult = studentCGTDAO.getStudentCGTResultByStudentId(student.getId());
			/*
			 * if(cgtResult != null)
			 * {
			 * cgtMassage = cgtResult.getAptitudeComplete();
			 * }
			 */
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return cgtResult;
	}

	public void insertStudentCGTResult(int studentId, long remainigTime)
	{
		SqlSession session = null;
		try
		{
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO student = dao.getStudentDetailsByUserId(studentId);
			session = MyBatisManager.getInstance().getSession();
			StudentCGTResult cgtResult = new StudentCGTResult();
			cgtResult.setStudentId(student.getId());
			cgtResult.setRemainigTime(remainigTime);
			cgtResult.setAptitudeComplete(StudentTestEnum.COMPLETED.getValue());
			StudentCGTDAO studentCGTDAO = new StudentCGTDAO();
			studentCGTDAO.insertORUpdateStudentCGTResult(session, cgtResult);
			
			session.commit();
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error("Exception", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

	public JSONArray getStudentCGTBySection(int studentId)
	{
		JSONArray finalJsonArray = null;
		try
		{
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO student = dao.getStudentDetailsByUserId(studentId);
			Map<String, Object> parameterObject = new HashMap<String, Object>();
			parameterObject.put("studentId", student.getId());
			parameterObject.put("section", SystemRecommendation.APP_TEST_SECION);
			StudentCGTDAO studentDAO = new StudentCGTDAO();
			List<StudentCGTForEvalDTO> list = studentDAO.getStudentCGTByStudentId(parameterObject);
			if (null != list && !list.isEmpty())
			{
				JSONObject json = null;
				finalJsonArray = new JSONArray();
				for (StudentCGTForEvalDTO dto : list)
				{
					json = new JSONObject();
					json.put("slNo", dto.getSlNo());
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

	private Map<Object, Integer> getCGTQuestioneries()
	{
		Map<Object, Integer> cgtMap = null;
		try
		{
			CGTQuestioneriesDAO dao = new CGTQuestioneriesDAO();
			CGTQuestioneriesDTO dto = new CGTQuestioneriesDTO();
			dto.setSection(SystemRecommendation.APP_TEST_SECION);
			List<CGTQuestioneriesDTO> list = dao.getCGTQuestioneriesDetailsBySection(dto);

			if (null != list && !list.isEmpty())
			{
				cgtMap = new HashMap<Object, Integer>();
				for (CGTQuestioneriesDTO cgtDTO : list)
				{
					cgtMap.put(cgtDTO.getSlNo(), cgtDTO.getId());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return cgtMap;
	}
	
	
	//start by bharath on 6/7/2019
		public void savetoReferenceTable(int loginId)
		{
			try
			{
				OUT.debug("Inside AptitudeService (savetoReferenceTable method) loginId:{}",loginId);
				StudentDetailsDAO dao = new StudentDetailsDAO();
				StudentDetailsDTO student = dao.getStudentDetailsByUserId(loginId);
				TumCgtResultDTO tumcgtResult= new TumCgtResultDTO();
				tumcgtResult.setStudentId(student.getId());
				tumcgtResult.setAptitudeResult(1);
				TumCgtResultDAO tdoa = new TumCgtResultDAO();
				int id = tdoa.updateAptitude(tumcgtResult);
				if(id==0) 
				{
					int value= tdoa.insertAptitude(tumcgtResult);
				}
			}
			catch (Exception e)
			{
				OUT.error("Exception : {}", e);
			}
		}
		//END by bharath on 6/7/2019

}
