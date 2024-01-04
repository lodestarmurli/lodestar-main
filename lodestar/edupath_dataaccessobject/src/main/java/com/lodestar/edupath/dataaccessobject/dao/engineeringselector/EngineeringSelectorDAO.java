package com.lodestar.edupath.dataaccessobject.dao.engineeringselector;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EBFavouriteSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EngineeringSelectorDTO;
 
 

public class EngineeringSelectorDAO  {

	private static final Logger	OUT	= LoggerFactory.getLogger(EngineeringSelectorDAO.class);
	
	
	public int insertORUpdateStudentCGTResult(SqlSession session, EngineeringSelectorDTO dto) throws Exception
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_OR_UPDATE_ENINEERING_SELECTOR_RESULT, dto);
		return id;
	}
	
	public static List<Integer> getStudentIdList(EngineeringSelectorDTO dto) throws Exception
	{
		List<Integer> studentIdList= MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENT_CGT_COMPLETED_FOR_ENINEERING_SELECTOR_RESULT, dto );
		return studentIdList;
	}
	
	public  int  InsertEBFavouriteSubject(SqlSession session, EBFavouriteSelectorDTO ebdto)
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_EB_FAVOURITE_SUBJECT, ebdto);
		return id;
	}
	
	public   EBFavouriteSelectorDTO getEBFavouriteSubject(int studentId) throws Exception
	{
		EBFavouriteSelectorDTO dto = (EBFavouriteSelectorDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_EB_FAVOURITE_SUBJECTRESULT, studentId );
		return dto;
	}
	
	
}
