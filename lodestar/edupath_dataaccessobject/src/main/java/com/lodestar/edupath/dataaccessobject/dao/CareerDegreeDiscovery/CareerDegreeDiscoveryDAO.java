package com.lodestar.edupath.dataaccessobject.dao.CareerDegreeDiscovery;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery.CDDStreamDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery.CareerDegreeDiscoveryResultDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EBFavouriteSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EngineeringSelectorDTO;
 
 

public class CareerDegreeDiscoveryDAO  {

	private static final Logger	OUT	= LoggerFactory.getLogger(CareerDegreeDiscoveryDAO.class);
	
	
	public int insertORUpdateStudentCGTResult(SqlSession session, CDDStreamDTO dto) throws Exception
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_OR_UPDATE_ENINEERING_SELECTOR_RESULT, dto);
		return id;
	}
	
	public static List<Integer> getStudentIdList(CDDStreamDTO dto) throws Exception
	{
		List<Integer> studentIdList= MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENT_CGT_COMPLETED_FOR_ENINEERING_SELECTOR_RESULT, dto );
		return studentIdList;
	}
	
	public  int  insertCDDStream(SqlSession session, CDDStreamDTO dto)
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_CDD_STREAM, dto);
		return id;
	}
	
	public   CDDStreamDTO getCDDStream(int studentId) throws Exception
	{
		CDDStreamDTO dto = (CDDStreamDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CDD_STREAM, studentId );
		return dto;
	}
	
	public CareerDegreeDiscoveryResultDTO getCareerDegreeDiscoveryResult(int studentId) throws Exception
	{
		CareerDegreeDiscoveryResultDTO dto= (CareerDegreeDiscoveryResultDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CDD_RESULT, studentId );
		return dto;
	}
	public int insertORUpdateCDDResult(SqlSession session, CareerDegreeDiscoveryResultDTO dto) throws Exception
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_OR_UPDATE_CDD_RESULT, dto);
		return id;
	}
	
	
}
