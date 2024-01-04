package com.lodestar.edupath.dataaccessobject.dao.careerFitment;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.WishListCareerFitmentDTO;

public class WishListCareerFitementDAO {
	private static final Logger	OUT	= LoggerFactory.getLogger(WishListCareerFitementDAO.class);
	
	public List<ClusterOccupationDTO> getWishListCareerFitment(WishListCareerFitmentDTO dto) throws Exception
	{
		List<ClusterOccupationDTO> resultWishList = new ArrayList<ClusterOccupationDTO>();
		try {
			resultWishList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_CF_WISH_LIST_BY_STUDENT_ID, dto);
		} catch (Exception e) 
		{
			OUT.error("Exception", e);
		}
		
		return resultWishList;
	}
	
	public List<ClusterOccupationDTO> getAllWishListCareerFitment(WishListCareerFitmentDTO dto) throws Exception
	{
		List<ClusterOccupationDTO> resultWishList = new ArrayList<ClusterOccupationDTO>();
		try {
			resultWishList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_CF_WISH_LIST_BY_STUDENT_ID, dto);
		} catch (Exception e) 
		{
			OUT.error("Exception", e);
		}
		
		return resultWishList;
	}
	public void insertWishListCareerFitment(  WishListCareerFitmentDTO dto) throws Exception
	{
		OUT.debug("bharath WishListCareerFitementDAO insertWishListCareerFitment dto:{}", dto);
		MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_CF_WISH_LIST_STUDENT_ID, dto);	
	}
	
	public void deleteWishListCFById( int id) throws Exception
	{
		 MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.DELETE_CF_WISH_LIST_BY_ID, id);
	}
	
	public void deleteWishListCFByOcc(WishListCareerFitmentDTO dto) throws Exception
	{
		 MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_CF_WISH_LIST_BY_OCCID, dto);
	}
	
	public void deleteWishListCFByClusters( WishListCareerFitmentDTO dto) throws Exception
	{
		 MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_CF_WISH_LIST_BY_CLUSTERID, dto);
	}
	
	public void deleteOptionAWishListCFByStudentId( int studentId) throws Exception
	{
		 MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.DELETE_CF_WISH_LIST_OPTION_A_BY_STUDENT_ID	, studentId);
	}
	
	public void deleteOptionBWishListCFByStudentId( int studentId) throws Exception
	{
		 MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.DELETE_CF_WISH_LIST_OPTION_B_BY_STUDENT_ID, studentId);
	}
	
//	public int deleteWishListCFByClusterId(SqlSession session, WishListCareerFitmentDTO dto)
//	{
//		return session.delete(MyBatisMappingConstants.DELETE_CF_WISH_LIST_CLUSTER_ID_BY_STUDENT_ID, dto);
//	}
//	
//	public int deleteWishListCFForOptionA(SqlSession session, WishListCareerFitmentDTO dto)
//	{
//		return session.delete(MyBatisMappingConstants., dto);
//	}
//	
//	public int deleteWishListCFForOptionB(SqlSession session, WishListCareerFitmentDTO dto)
//	{
//		return session.delete(MyBatisMappingConstants., dto);
//	}

}
