package com.lodestar.edupath.dataaccessobject.dao.StudentDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.student.TumCgtResultDTO;


public class TumCgtResultDAO 
{
	private static final Logger	OUT	= LoggerFactory.getLogger(TumCgtResultDAO.class);

	public int updateAptitude(TumCgtResultDTO tumcgtResult) throws Exception
	{
		OUT.info("Inside TumCgtResultDAO updateAptitude");
		int id = MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_TUMCGT_APTITUDE, tumcgtResult);
		return id;
	}
	
	public int insertAptitude(TumCgtResultDTO students) throws Exception
	{
		OUT.info("Inside TumCgtResultDAO insertTumCgtResult students:{}",students);
		int value = MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_TUMCGT_APTITUDE, students);
		OUT.debug("Inside TumCgtResultDAO insertTumCgtResult value:{}",value);
		return value;
	}
	public int updateInterestTUMCGT(TumCgtResultDTO tumcgtResult) throws Exception
	{
		OUT.info("Inside TumCgtResultDAO updateInterestTUMCGT");
		int id = MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_TUMCGT_INTEREST, tumcgtResult);
		OUT.debug("Inside TumCgtResultDAO updateInterestTUMCGT value:{}",id);
		return id;
	}
	
	public int insertInterestTUMCGT(TumCgtResultDTO tumcgtResult) throws Exception
	{
		OUT.info("Inside TumCgtResultDAO insertInterestTUMCGT");
		int id = MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_TUMCGT_INTEREST, tumcgtResult);
		OUT.debug("Inside TumCgtResultDAO insertInterestTUMCGT value:{}",id);
		return id;
	}
	//start by bharath 5/7/2019
	public int updateTumCgtResult(TumCgtResultDTO students) throws Exception
	{
		OUT.info("Inside TumCgtResultDAO updateStudentCGT students:{}",students);
		int value = MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_STUDENT_TUM_CGT, students);
		OUT.debug("Inside TumCgtResultDAO updateStudentCGT value:{}",value);
		return value;
	}
			
			
	public int insertTumCgtResult(TumCgtResultDTO students) throws Exception
	{
		OUT.info("Inside TumCgtResultDAO insertTumCgtResult students:{}",students);
		int value = MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_STUDENT_TUM_CGT, students);
		OUT.debug("Inside TumCgtResultDAO insertTumCgtResult value:{}",value);
		return value;
	}
			
			
			
		//end by bharath 5/7/2019
	
	public TumCgtResultDTO getTumCgtResultById(TumCgtResultDTO students) throws Exception
	{
		OUT.info("Inside TumCgtResultDAO getTumCgtResultById students:{}",students);
		TumCgtResultDTO value = (TumCgtResultDTO)  MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENTTUMCGT_BY_ID, students.getStudentId());
		OUT.debug("Inside TumCgtResultDAO insertTumCgtResult value:{}",value);
		return value;
	}
	
}
