package com.lodestar.edupath.dataaccessobject.dao.integratedcourse;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.IntegratedCourseDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class IntegratedCourseDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(IntegratedCourseDAO.class);

	public List<IntegratedCourseDTO> getIntegratedCourseDetailsByOccupatioId(int occupationId) throws Exception
	{
		List<IntegratedCourseDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_INTEGRATED_COURSE_BY_OCC_ID, occupationId);
		OUT.debug("Related Integrated course founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<IntegratedCourseDTO> getAllIntegratedCourseDetails() throws Exception
	{
		List<IntegratedCourseDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_INTEGRATED_COURSE, null);
		OUT.debug("Related Integrated course founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<IntegratedCourseDTO> getShortListIntegratedCourseByStudentId(Integer studentId) throws Exception
	{
		List<IntegratedCourseDTO> list = null;
		list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_SHORTLIST_INTEGRATED_COURSE_BY_STUDENTID, studentId.intValue());
		OUT.debug("ShortList integrated course by studentId : {}", null != list ? list.size() : 0);
		return list;
	}

	public List<IntegratedCourseDTO> getIntegratedCourses(IntegratedCourseDTO courseDTO)
	{
		List<IntegratedCourseDTO> list = null;
		MyBatisManager instance = MyBatisManager.getInstance();
		try
		{
			if (courseDTO.getOccupationId() != 0)
			{
				int occupationId = courseDTO.getOccupationId();
				list = instance.getResultList(MyBatisMappingConstants.GET_INTEGRATED_COURSE_BY_OCC_ID, occupationId);
			}
			else if (courseDTO.getId() != 0)
			{
				int id = courseDTO.getId();
				list = instance.getResultList(MyBatisMappingConstants.GET_INTEGRATED_COURSE_BY_ID, id);
			}
			else if (null != courseDTO.getInstitute() && "" != courseDTO.getInstitute())
			{
				list = instance.getResultAsList(MyBatisMappingConstants.GET_INTEGRATED_COURSE_BY_INSTITUTE, courseDTO);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		OUT.debug("Related Integrated Courses founded :{}", (list != null ? list.size() : 0));
		return list;
	}
	
	public int getIntegratedCourseCountByOccId(List<Integer> occIdsList) throws Exception
	{
		int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_INTEGRATED_COURSE_COUNT_BY_OCC_ID, occIdsList);
		OUT.debug("Founded IntegratedCourse count: {}", count);
		return count;
	}
	
	public IntegratedCourseDTO getIntegratedCourseById(int id) throws Exception
	{
		OUT.debug("IntegratedCourse id: {}", id);
		IntegratedCourseDTO dto = (IntegratedCourseDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_INTEGRATED_COURSE_BY_ID, id);
		return dto;
	}
}
