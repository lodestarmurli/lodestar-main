package com.lodestar.edupath.dataaccessobject.dao.entranceexams;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class EntranceExamsDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(EntranceExamsDAO.class);

	public List<EntranceExamsDTO> getEntranceExamsDetailsByOccupatioId(int occupationId) throws Exception
	{

		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ENTRANCE_EXAMS_BY_OCC_ID, occupationId);
	}

	public List<EntranceExamsDTO> getAllEntranceExam() throws Exception
	{
		List<EntranceExamsDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_ENTRANCE_EXAMS, null);
		OUT.debug("Related Entrance exams founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<EntranceExamsDTO> getEntranceExams(EntranceExamsDTO examsDTO)
	{
		List<EntranceExamsDTO> list = null;
		MyBatisManager instance = MyBatisManager.getInstance();
		try
		{
			if (examsDTO.getOccupationId() != 0)
			{
				int occupationId = examsDTO.getOccupationId();
				list = instance.getResultList(MyBatisMappingConstants.GET_ENTRANCE_EXAMS_BY_OCC_ID, occupationId);
			}
			else if (examsDTO.getId() != 0)
			{
				int id = examsDTO.getId();
				list = instance.getResultList(MyBatisMappingConstants.GET_ENTRANCE_EXAMS_BY_ID, id);
			}
			else if (null != examsDTO.getExamWhen() && "" != examsDTO.getExamWhen())
			{
				list = instance.getResultAsList(MyBatisMappingConstants.GET_ENTRANCE_EXAMS_BY_WHEN, examsDTO);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		OUT.debug("Related Entrance exams founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<EntranceExamsDTO> getShortListExamByStudentId(Integer studentId) throws Exception
	{
		List<EntranceExamsDTO> list = null;
		list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_SHORTLIST_ENTRANCE_EXAM_BY_STUDENTID, studentId.intValue());
		OUT.debug("ShortList entrance exam by studentId : {}", null != list ? list.size() : 0);
		return list;
	}

	public int getEntranceExamsCountByOccId(List<Integer> occIdsList) throws Exception
	{
		int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_ENTRANCE_EXAMSCOUNT_OCCID, occIdsList);
		OUT.debug("Entrance Exam count: {}", count);
		return count;
	}

	public EntranceExamsDTO getEntranceExamsById(int id) throws Exception
	{
		OUT.debug("Entrance Exams id : {} ", id);
		EntranceExamsDTO dto = (EntranceExamsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_ENTRANCE_EXAMS_BY_ID, id);
		return dto;
	}

	//Added Vyankatesh 4-11-2017
	public List<EntranceExamsDTO> getEntranceExamsDetailsByOccupatioIdNational(int occupationId) throws Exception
	{
		// TODO Auto-generated method stub
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ENTRANCE_EXAMS_BY_OCC_ID_National, occupationId);
	}

	public List<EntranceExamsDTO> getEntranceExamsDetailsByOccupatioIdstate(int occupationId, int cityId) throws Exception
	{ // TODO Auto-generated method stub{
		
		EntranceExamsDTO   EntranceExamsDTO =new EntranceExamsDTO();

		EntranceExamsDTO.setOccupationId(occupationId);
		EntranceExamsDTO.setCityId(cityId);
		
		OUT.debug("GET_ENTRANCE_EXAMS_BY_OCC_ID_State:}");
		List<EntranceExamsDTO> list = null;
		list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ENTRANCE_EXAMS_BY_OCC_ID_State, EntranceExamsDTO);
	OUT.debug("GET_ENTRANCE_EXAMS_BY_OCC_ID_State: {}", null != list ? list.size() : 0);
	
	return list;
	}
	//vyankatesh added 13-4-2017
	public List<StudentCityLockDTO> getAllStudentIdFromLock() {
		try
		{
			OUT.debug("EntranceDAO: Getting all StudentId  Details");
			List<StudentCityLockDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_StudentIDLOCK_CITY, null);
			OUT.debug("EntranceDAO: Number of StudentId Details found: {}", resultList != null ? resultList.size() : 0);
			return resultList;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public int updateLockDate(SqlSession session, StudentCityLockDTO studentCityLock) {
		int id = 0;
		try
		{
			//MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_PAYMENT_STUDENT_BY_ID, paymentDTO);
			MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_LOCK_VALUE, studentCityLock);
			OUT.debug("Update EntranceDAO id:{} ");
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return id;
	}

	public  int insertLockDate(SqlSession session, StudentCityLockDTO studentCityLock) {
		int id = 0;
		try
		{
			OUT.debug("EntranceDAO: Insert all Mapping Details");
			MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_LOCK_VALUE, studentCityLock);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return id;
		
	}

	public StudentCityLockDTO getCityIdByStudentId(int studentId) throws Exception {
		// TODO Auto-generated method stub
		StudentCityLockDTO StudentCityLockDTO =(StudentCityLockDTO) MyBatisManager.getInstance().getResultAsObjectById(
				MyBatisMappingConstants.GET_CITYID_BY_STUDENTID, studentId);
		return StudentCityLockDTO;
	}

}
