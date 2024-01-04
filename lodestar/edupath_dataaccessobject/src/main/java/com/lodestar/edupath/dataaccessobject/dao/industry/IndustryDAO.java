package com.lodestar.edupath.dataaccessobject.dao.industry;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.occupation.IndustryDTO;

public class IndustryDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(IndustryDAO.class);

	public List<IndustryDTO> getListOfIndustryForOcc(int occupationId) throws Exception
	{
		OUT.debug("Getting industries mapped to occupation:{}", occupationId);
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_INDUSTRIES_BYOCCUPATION, occupationId);
	}

	public List<IndustryDTO> getListOfIndustryForOccFromWishOrPrimary(int occupationId) throws Exception
	{
		OUT.debug("Getting primary industry or from wishlist of occupation:{}", occupationId);
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_INDUSTRY_FROM_WISH_PRIMARY_BYOCCUPATION, occupationId);
	}

	public IndustryDTO getIndustryById(int industryId) throws Exception
	{
		OUT.debug("Gettingg Industry Detail for Industry: {}",industryId);
		return (IndustryDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_INDUSTRIES_BYID, industryId);
	}
	
	public List<Object> getIndustryDetailsByStudentId(int studentId) throws Exception
	{
		OUT.debug("Getting industry details by studentId : {}", studentId);
		List<Object> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_INDUSTRY_DETAILS_BY_STUDENTID, studentId);
		OUT.debug("Number of industry details found : {}", resultList !=null ? resultList.size() : 0);
		return resultList;
	}
	
}
