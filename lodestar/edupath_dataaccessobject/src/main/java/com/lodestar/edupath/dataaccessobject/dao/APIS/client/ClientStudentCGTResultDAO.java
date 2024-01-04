	package com.lodestar.edupath.dataaccessobject.dao.APIS.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentCGTResultDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTForEvalDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ClientStudentCGTResultDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ClientStudentCGTResultDAO.class);

//	public List<StudentCGTForEvalDTO> getStudentCGTByStudentId(Map<String, Object> parameterObject) throws Exception
//	{
//		List<StudentCGTForEvalDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENT_CGT_BY_STUDENTID, parameterObject);
//		return list;
//	}
//
	public int insertStudentCGTResult(SqlSession session, ClientStudentCGTResultDTO studentCGT) throws Exception
	{
		int id = MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_CLIENT_STUDENT_CGT_RESULT, studentCGT);
		return id;
	}
	//start by bharath 21-05-2020
		public  ClientStudentDetailsDTO getStudentAptitudeByDHID(SqlSession session, ClientStudentDetailsDTO studentDTO)
		{
			ClientStudentDetailsDTO dto = null;
			try
			{
				dto = (ClientStudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CLIENT_STUDENT_APTITUDE_DETAILS_BY_DHID, studentDTO);
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
			return dto;
		}
		//end by bharath 21-05-2020
//
	public int updateStudentCGTToken(SqlSession session, ClientStudentCGTResultDTO studentCGTResultDTO) throws Exception
	{
		int id = MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_CLIENT_STUDENT_CGTRESULT_TOKEN, studentCGTResultDTO);
		return id;
	}

	public int insertORUpdateStudentCGTResult(SqlSession session, StudentCGTResult cgtResult) throws Exception
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_OR_UPDATE_STUDENT_CGT_RESULT, cgtResult);
		return id;
	}

	public static ClientStudentCGTResultDTO getDHStudentCGTbyToken(String token) throws Exception
	{
		ClientStudentCGTResultDTO result = (ClientStudentCGTResultDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CLIENT_STUDENT_CGTRESULT_TOKEN, token);
		return result;
	}
	
	public ClientStudentCGTResultDTO getDHStudentCGTResultByStudentId(SqlSession session, int id) throws Exception
	{
		ClientStudentCGTResultDTO result = (ClientStudentCGTResultDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CLIENTSTUDENT_CGT_RESULT_BY_STUDENTID, id);
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
	public static ClientStudentCGTDTO insertForBulkStudentCGT(SqlSession session, ClientStudentCGTDTO studentCGT) throws Exception
	{
		OUT.debug("Bulk Insert StudentCGT with studentId : {} and qusetionId : {}", studentCGT.getStudentId(), studentCGT.getQuestionId());
		session.insert(MyBatisMappingConstants.INSERT_CLIENT_STUDENT_CGT, studentCGT);
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
