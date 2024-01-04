package com.lodestar.edupath.tum.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.TumCgtResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.tags.AreaDAO;
import com.lodestar.edupath.dataaccessobject.dao.tum.DoYouKnowDAO;
import com.lodestar.edupath.dataaccessobject.dao.tum.StudentTUMDAO;
import com.lodestar.edupath.datatransferobject.dto.DoYouKnowDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.TumCgtResultDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AreaDTO;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class StudentTUMService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentTUMService.class);
	private int 				questionCount		=0;

	public Boolean insertORUpdateStudentTUM(String studentJson, int loginId, int range)
	{
		OUT.debug("Inside StudentTUMService (insertORUpdateStudentTUM method)");
		SqlSession session = null;
		try
		{
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO student = dao.getStudentDetailsByUserId(loginId);
			session = MyBatisManager.getInstance().getSession();
			List<StudentTUMDTO> list = null;
			ObjectMapper mapper = new ObjectMapper();

			if (null != studentJson && !studentJson.isEmpty())
			{
				list = mapper.readValue(studentJson, TypeFactory.collectionType(List.class, StudentTUMDTO.class));
			}

			if (null != list && !list.isEmpty())
			{
				StudentTUMDAO studentDAO = new StudentTUMDAO();
				for (StudentTUMDTO studentTUM : list)
				{
					studentTUM.setStudentId(student.getId());
					int id = studentDAO.updateStudentTUM(session, studentTUM);
					studentTUM.setStudentId(student.getId());
					if (id == 0)
					{
						studentDAO.insertORUpdateStudentTUM(session, studentTUM);
					}
				}
			}
			session.commit();
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error("Exception: {}", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return true;
	}

	public JSONArray getStutentTUM(int loginId, int range)
	{
		OUT.debug("Inside StudentTUMService (getStutentTUM method)");

		JSONArray jsonArr = null;
		try
		{
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO students = dao.getStudentDetailsByUserId(loginId);
			StudentTUMDAO studentDAO = new StudentTUMDAO();
			StudentTUMDTO studentTUM = new StudentTUMDTO();
			studentTUM.setStudentId(students.getId());
			studentTUM.setRange(range);
			if (range == 10)
			{
				studentTUM.setQuestionSlNo(8);
			}
			else if (range == 13)
			{
				studentTUM.setQuestionSlNo(11);
			}
			else
			{
				studentTUM.setQuestionSlNo(1);
				studentTUM.setRange(7);
			}
			List<StudentTUMDTO> list = studentDAO.getSuduntTUMDetailsById(studentTUM);

			if (null != list && !list.isEmpty())
			{
				List<StudentTUMDTO> finalList = new ArrayList<StudentTUMDTO>();
				for (StudentTUMDTO student : list)
				{
					student.setAnswer(CommonUtil.replaceXMLEntities(student.getAnswer()));
					finalList.add(student);
				}
				jsonArr = new JSONArray(finalList);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception : {}", e);
		}
		return jsonArr;
	}

	public JSONArray getHobbiesOrInterest()
	{
		JSONArray jsonArr = null;
		try
		{
			AreaDAO area = new AreaDAO();
			List<AreaDTO> list = area.getAreaByInterests();
			if (null != list && !list.isEmpty())
			{
				List<String> hobbies = new ArrayList<String>();
				for (AreaDTO areaDTO : list)
				{
					hobbies.add(CommonUtil.replaceXMLEntities(areaDTO.getName()));
				}
				jsonArr = new JSONArray(hobbies);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception : {} ", e);
		}
		return jsonArr;
	}

	public JSONArray getOccupation()
	{
		JSONArray jsonArr = null;
		try
		{
			OccupationDAO occupationDAO = new OccupationDAO();
			List<OccupationDTO> list = occupationDAO.getOccupationDetails();
			if (null != list && !list.isEmpty())
			{
				List<String> occupation = new ArrayList<String>();
				for (OccupationDTO occupationDTO : list)
				{
					occupation.add(CommonUtil.replaceXMLEntities(occupationDTO.getName()));
				}
				jsonArr = new JSONArray(occupation);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception: {}", e);
		}
		return jsonArr;
	}

	public JSONArray getDoYouKnow()
	{
		JSONArray jsonArr = null;
		try
		{
			DoYouKnowDAO doyouKnowDAO = new DoYouKnowDAO();
			List<DoYouKnowDTO> list = doyouKnowDAO.getDoYouKnowDetails();
			if (null != list && !list.isEmpty())
			{
				List<String> doyouKnow = new ArrayList<String>();
				for (DoYouKnowDTO doyouKnowDTO : list)
				{
					doyouKnow.add(CommonUtil.replaceXMLEntities(doyouKnowDTO.getMessage()));
				}
				jsonArr = new JSONArray(doyouKnow);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception : {} ", e);
		}
		return jsonArr;
	}

	public void updateTUMSession(int studentId)
	{
		try
		{
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO students = dao.getStudentDetailsByUserId(studentId);
			SessionScheduleDetailsDAO sessionDAO = new SessionScheduleDetailsDAO();
			sessionDAO.updatePreANDSessionOne(students.getId());

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	public void deleteQusetion(String deleteJsonStr, int userId, String newjsonStr)
	{
		try
		{
			OUT.debug("Deleted TUM Questions oldJSON : {}, and newJSON : {} with userId : {}", deleteJsonStr, newjsonStr, userId);
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO students = dao.getStudentDetailsByUserId(userId);
			ObjectMapper mapper = new ObjectMapper();
			List<StudentTUMDTO> oldlist = mapper.readValue(deleteJsonStr, TypeFactory.collectionType(List.class, StudentTUMDTO.class));
			List<StudentTUMDTO> newlist = mapper.readValue(newjsonStr, TypeFactory.collectionType(List.class, StudentTUMDTO.class));
			if ((null != oldlist && !oldlist.isEmpty()) && (null != newlist && !newlist.isEmpty()))
			{
				Map<Integer, String> map = new HashMap<Integer, String>();
				for (StudentTUMDTO studentTUMDTO : newlist)
				{
					map.put(studentTUMDTO.getQuestionSlNo(), studentTUMDTO.getAnswer());
				}
				StudentTUMDAO studentDAO = new StudentTUMDAO();

				for (StudentTUMDTO studentTO : oldlist)
				{
					if (!map.containsKey(studentTO.getQuestionSlNo()))
					{
						studentTO.setStudentId(students.getId());
						studentDAO.deleteByQuestionSlNo(studentTO);
					}
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception : {}", e);
		}
	}
	
	
	public void savetoReferenceTable(int loginId)
	{
		try
		{
			OUT.debug("Inside StudentTUMService (savetoReferenceTable method) loginId:{}",loginId);
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO students = dao.getStudentDetailsByUserId(loginId);
			StudentTUMDAO studentDAO = new StudentTUMDAO();
			StudentTUMDTO studentTUM = new StudentTUMDTO();
			TumCgtResultDAO tDAO = new TumCgtResultDAO();
			TumCgtResultDTO tdto = new TumCgtResultDTO();
			studentTUM.setStudentId(students.getId());
			List<StudentTUMDTO> list = studentDAO.getSuduntTUMListById(studentTUM);
			OUT.debug("Inside StudentTUMService (savetoReferenceTable method)list.size:{}, list:{}",list.size(), list);
			//questionCount = list.size();
			if(list.size()==13)
			{
				tdto.setStudentId(studentTUM.getStudentId());
				tdto.setTUMResult(1);
				int id =tDAO.updateTumCgtResult(tdto);
				OUT.debug("Inside StudentTUMService (getQuestionCount method) updateTumCgtResult id :{}",id);
				if(id==0)
				{
					int value = tDAO.insertTumCgtResult(tdto);
					OUT.debug("Inside StudentTUMService (getQuestionCount method) insertTumCgtResult value :{}",value);
				}
			}
			else
			{
				tdto.setStudentId(studentTUM.getStudentId());
				TumCgtResultDTO tumcgtresult = tDAO.getTumCgtResultById(tdto);	
				if(tumcgtresult.getTUMResult()==1)
				{
					tdto.setTUMResult(0);
					tDAO.updateTumCgtResult(tdto);
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception : {}", e);
		}
	}
	
	
}
