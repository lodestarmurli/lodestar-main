package com.lodestar.edupath.dataaccessobject.dao.vark;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentDTO;

public class VarkStudentDAO {

	private static final Logger	OUT	= LoggerFactory.getLogger(VarkStudentDAO.class);
	
	public int insertVarkStudent(SqlSession session, VarkStudentDTO dto) throws Exception
	{
		int id =session.insert(MyBatisMappingConstants.INSERT_VARK_STUDNET, dto);
		return id;
	}
	
	public VarkStudentDTO getVarkStudentByStudentID(int studentId) throws Exception
	{
		VarkStudentDTO result = (VarkStudentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_VARK_STUDENT_BY_STUDENTID, studentId);
		OUT.debug("bharath VarkStudentDAO getVarkStudentByStudentID result:{}",result);
		return result;
	}
	
	public VarkStudentDTO getVarkStudentByToken(String token) throws Exception
	{
		VarkStudentDTO result = (VarkStudentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_VARK_STUDENT_BY_TOKEN, token);
		OUT.debug("bharath VarkStudentDAO getVarkStudentByToken result:{}",result);
		return result;
	}
	
	
	public int updateVarkStudent(SqlSession session, VarkStudentDTO dto) throws Exception
	{
		int id = MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_VARK_STUDENT_BY_STUDENTID, dto);
		return id;
	}
	
}
