package com.lodestar.edupath.dataaccessobject.dao.occupation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;

public class OccupationDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(OccupationDAO.class);

	public List<OccupationDTO> getOccupationDetails() throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_OCCUPATION_DETAIL, null);
		OUT.debug("Occupation founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public OccupationDTO getOccupationDetails(int id) throws Exception
	{
		return (OccupationDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_OCCUPATION_DETAIL_BY_ID, id);
	}

	public OccupationDTO getOccupationBasicDetails(int id) throws Exception
	{
		return (OccupationDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_OCCUPATION_BASIC_DETAIL_BY_ID, id);
	}

	public List<OccupationDTO> getRelatedOccupationDetails(int occupationId) throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_RELATED_OCCPATION_BY_OCC_ID, occupationId);
		OUT.debug("Related Occupation founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public Integer doUpdateImage(OccupationDTO occupationDTO) throws Exception
	{
		Integer count = -1;
		count = MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_OCCUPATION_IMAGE, occupationDTO);
		return count;
	}

	public List<OccupationDTO> getOccupationNameAndId() throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_OCCUPATION_NAME_AND_ID, null);
		OUT.debug("Occupation founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<OccupationDTO> getOccupationWithExamsByIndustryid(int industryId) throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_WITH_EXAMS_BY_INDUSTRY_ID, industryId);
		OUT.debug("Related Occupation founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<OccupationDTO> getOccupationWithCourseByIndustryid(int industryId) throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_WITH_COURSE_BY_INDUSTRY_ID, industryId);
		OUT.debug("Related Occupation founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<OccupationDTO> getOccupationName() throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_NAME, null);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<Object> getOccDetailsByStudentId(int studentId) throws Exception
	{
		OUT.debug("Getting occupation details by studentId : {}", studentId);
		List<Object> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCC_DETAILS_BY_STUDENTID, studentId);
		OUT.debug("Number of occ details found : {}", resultList !=null ? resultList.size() : 0);
		return resultList;
	}
//	started by bharath on 27-03-2020
	
	public List<OccupationDTO> getOccupationListForDH() throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_LIST_FOR_DH, null);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	public List<OccupationDTO> getOccupationListBasedStream(DHStudentDetailsDTO studentDTO) throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_LIST_BASED_ON_STREAM, studentDTO);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	
	public List<OccupationDTO> getOccupationListBasedStream(ChanakyaStudentDetailsDTO studentDTO) throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_LIST_BASED_ON_STREAM_CHNK, studentDTO);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	
	public List<OccupationDTO> getOccupationListBasedStreamForClient(ClientStudentDetailsDTO studentDTO) throws Exception
	{
		OUT.debug("bharath OccupationDAO OccupationDAO studentDTO:{}",studentDTO);
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_LIST_BASED_ON_STREAM_FOR_CLIENT, studentDTO);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	public List<OccupationDTO> getOccupationForTwelvePlusSCIENCE_MATH(String raisecCode) throws Exception
	{
		List<OccupationDTO> list =  MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_FOR_TWELVE_PLUS_SCIENCE_MATH, raisecCode);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	public List<OccupationDTO> getOccupationForTwelvePlusSCIENCE_WO_MATH(String raisecCode) throws Exception
	{
		List<OccupationDTO> list =  MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_FOR_TWELVE_PLUS_SCIENCE_WO_MATH, raisecCode);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	public List<OccupationDTO> getOccupationForTwelvePlusCOMMERCE(String raisecCode) throws Exception
	{
		List<OccupationDTO> list =  MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_FOR_TWELVE_PLUS_COMMERCE, raisecCode);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	public List<OccupationDTO> getOccupationForTwelvePlusARTS(String raisecCode) throws Exception
	{
		List<OccupationDTO> list =  MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_FOR_TWELVE_PLUS_ARTS, raisecCode);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	
	
//end by bharath on 08-04-2020
	
	public static List<OccupationDTO> getOccupationListForEngineeringS2WithException() throws Exception
	{
		List<OccupationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCC_LIST_ENGINEER_S2_WITH_EXCEPTION, null);
		OUT.debug("Occupation name founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	
}
