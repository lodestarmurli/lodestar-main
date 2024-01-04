package com.lodestar.edupath.dataaccessobject.dao.vark;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentAnswerDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentResultDTO;

public class VarkStudentAnswerDAO {

	private static final Logger	OUT	= LoggerFactory.getLogger(VarkStudentAnswerDAO.class);
	
	public int insertVarkStudentAnswer(SqlSession session, VarkStudentAnswerDTO dto) throws Exception
	{
		int id =session.insert(MyBatisMappingConstants.INSERT_VARK_STUDNET_ANSWER, dto);
		return id;
	}
	
	public List<Object> getNumberOfQuestionsAnswered(int studentId) throws Exception {
	    List<Object> result = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_NUMBER_OF_QUESTIONS_ANSWERED, studentId);
	    int x = studentId;
	    return result;
	}

	
	
	public List<VarkStudentAnswerDTO> getVarkStudentByStudentID(int studentId) throws Exception
	{
		List<VarkStudentAnswerDTO> result =  MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_VARK_STUDENT_ANSWER_BY_STUDENTID, studentId);
		OUT.debug("bharath VarkStudentDAO getVarkStudentByStudentID result:{}",result);
		return result;
	}
	
	public int deleteVarkStudentAnswer(SqlSession session, VarkStudentAnswerDTO dto) throws Exception
	{
		return session.delete(MyBatisMappingConstants.DELETE_VARK_STUDENT__ANSWER, dto);
	}
	
	
	public int insertVarkStudentResult(SqlSession session, VarkStudentResultDTO dto) throws Exception
	{
		int id =session.insert(MyBatisMappingConstants.INSERT_VARK_STUDNET_RESULT, dto);
		return id;
	}
	
	public List<VarkStudentResultDTO> getVarkStudentResultBySchoolID(VarkStudentResultDTO dto) throws Exception
	{
		List<VarkStudentResultDTO> result =  MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_VARK_STUDENT_RESULT_BY_SCHOOLID, dto);
		OUT.debug("bharath VarkStudentDAO getVarkStudentByStudentID result:{}",result);
		return result;
	}
}
