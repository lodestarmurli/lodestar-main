package com.lodestar.edupath.dataaccessobject.dao.careerFitment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.CareerFitmentOccNotFoundDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.JustForLodestar;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.StudentCareerFitmentDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AlertsDTO;


public class CareerFitmentDAO {

	private static final Logger	OUT	= LoggerFactory.getLogger(CareerFitmentDAO.class);
	
	public int insertORUpdateStudentCareerFitment(SqlSession session, StudentCareerFitmentDTO dto) throws Exception
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_OR_UPDATE_STUDENT_CAREER_FITMENT, dto);
		return id;
	}
	
	public StudentCareerFitmentDTO getStudentCareerFitment(int studentId) throws Exception
	{
		StudentCareerFitmentDTO result = (StudentCareerFitmentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STUDENT_CAREER_FITMENT, studentId);
		OUT.debug("bharath CareerFitmentDAO getStudentCareerFitment result:{}",result);
		return result;
	}
	
	public List<ClusterDTO> getClusterByIds(List<String> clusterIds) throws Exception
	{
		List<ClusterDTO> cluster = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_CLUSTER_BY_IDS, clusterIds);
		return cluster;
	}
	
	public int insertORUpdateJustForLodestar(SqlSession session, JustForLodestar dto) throws Exception
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_OR_UPDATE_JUST_FOR_LODESTAR, dto);
		return id;
	}
	
	public List<ClusterDTO> getClusterList() throws Exception
	{
		List<ClusterDTO> list= MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_CLUSTER_DETAILS, null);
		return list;
	}
	
	public int insertCFOccNotFound(SqlSession session, CareerFitmentOccNotFoundDTO dto) throws Exception
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_CF_OCC_NOT_FOUND_LOCK, dto);
		return id;
	}
	
	public CareerFitmentOccNotFoundDTO getCFOccNotFound(int studentId) throws Exception
	{
		CareerFitmentOccNotFoundDTO result = (CareerFitmentOccNotFoundDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CF_OCC_NOT_FOUND_LOCK, studentId);
		OUT.debug("bharath CareerFitmentDAO getStudentCareerFitment result:{}",result);
		return result;
	}
	
}
