	package com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTForEvalDTO;

public class DHStudentOccupationDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(DHStudentOccupationDAO.class);

//	public List<StudentCGTForEvalDTO> getStudentCGTByStudentId(Map<String, Object> parameterObject) throws Exception
//	{
//		List<StudentCGTForEvalDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENT_CGT_BY_STUDENTID, parameterObject);
//		return list;
//	}
//
	public int insertORUpdateStudentOcc(SqlSession session, DHStudentOccupationDTO studentOcc) throws Exception
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_STUDENT_OCC, studentOcc);
		return studentOcc.getId();
	}
//
//	public int updateStudentCGT(SqlSession session, DHStudentCGTDTO studentCGT) throws Exception
//	{
//		int id = MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_STUDENT_CGT, studentCGT);
//		return id;
//	}
//
//	public int insertORUpdateStudentCGTResult(SqlSession session, StudentCGTResult cgtResult) throws Exception
//	{
//		int id = session.insert(MyBatisMappingConstants.INSERT_OR_UPDATE_STUDENT_CGT_RESULT, cgtResult);
//		return id;
//	}
//
	public DHStudentOccupationDTO getDHStudentOccupationbyToken(String token) throws Exception
	{
		DHStudentOccupationDTO result = (DHStudentOccupationDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DH_STUDENT_OCC, token);
		return result;
	}
	
	public DHStudentOccupationDTO getPersonalityCodeOccupationByStudentId(int studentId) throws Exception
	{
		DHStudentOccupationDTO result = (DHStudentOccupationDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DH_STUDENT_PERSONALITYCODE_OCCUPATION, studentId);
		return result;
	}
	
//
//	public int deleteStudentCGTResultsByStudentId(SqlSession session, Integer studentId)
//	{
//		OUT.debug("Deleting student cgt results by studentId : {}", studentId);
//		return session.delete(MyBatisMappingConstants.DELETE_STUDENT_CGT_RESULT_BY_STUDENT_ID, studentId);
//	}
//
//	/**
//	 * @param studentId
//	 * @param questionIds
//	 * @return
//	 * @throws Exception
//	 */
//	public Integer getAnsweredCountByQuestionIds(int studentId, List<String> questionIds) throws Exception
//	{
//		OUT.debug("Getting count of answered questions by questionIds: {}, studentId :{}", questionIds, studentId);
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("studentId", studentId);
//		paramMap.put("questionIds", questionIds);
//		Integer uniqueCount = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_ANSWERED_COUNT_BY_QUESTIONIDS, paramMap);
//		OUT.debug("Number of answered questions found: {}, for questionIds: {}, studentId :{}", uniqueCount, questionIds, studentId);
//		return uniqueCount;
//	}
//
	public static DHStudentCGTDTO insertForBulkStudentCGT(SqlSession session, DHStudentCGTDTO studentCGT) throws Exception
	{
		OUT.debug("Bulk Insert StudentCGT with studentId : {} and qusetionId : {}", studentCGT.getStudentId(), studentCGT.getQuestionId());
		session.insert(MyBatisMappingConstants.INSERT_DH_STUDENT_CGT, studentCGT);
		return studentCGT;
	}
//
//	public int updateForBulkStudentCGT(SqlSession session, DHStudentCGTDTO studentCGT) throws Exception
//	{
//		OUT.debug("Bulk Update StudentCGT with studentId : {} and qusetionId : {}", studentCGT.getStudentId(), studentCGT.getQuestionId());
//		session.update(MyBatisMappingConstants.UPDATE_STUDENT_CGT, studentCGT);
//		return studentCGT.getId();
//	}
//
//	public List<String> getStudentCGTForBulk() throws Exception
//	{
//		List<String> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.STUDENTCGT_GET_ALL_FOR_BULK, null);
//		OUT.debug("Founded Student CGT Size : {} for bulkupload", null != list ? list.size() : 0);
//		return null != list ? list : new ArrayList<String>();
//	}
}
