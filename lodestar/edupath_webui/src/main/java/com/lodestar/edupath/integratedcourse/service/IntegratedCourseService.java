package com.lodestar.edupath.integratedcourse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.integratedcourse.IntegratedCourseDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.shortlistcourse.ShortListCourseDAO;
import com.lodestar.edupath.datatransferobject.dto.IntegratedCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.ShortListCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class IntegratedCourseService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(IntegratedCourseService.class);

	public List<IntegratedCourseDTO> getCourseDetailsByOccId(int occId)
	{
		List<IntegratedCourseDTO> integratedCourseList = new ArrayList<IntegratedCourseDTO>();
		try
		{
			List<IntegratedCourseDTO> list = new IntegratedCourseDAO().getIntegratedCourseDetailsByOccupatioId(occId);
			for (IntegratedCourseDTO courseDTO : list)
			{
				courseDTO.setProgramName(CommonUtil.replaceXMLEntities(courseDTO.getProgramName()));
				courseDTO.setDescription(CommonUtil.replaceXMLEntities(courseDTO.getDescription()));
				courseDTO.setInstitute(CommonUtil.replaceXMLEntities(courseDTO.getInstitute()));
				courseDTO.setLocation(CommonUtil.replaceXMLEntities(courseDTO.getLocation()));
				courseDTO.setEligibility(CommonUtil.replaceXMLEntities(courseDTO.getEligibility()));
				courseDTO.setEntrance(CommonUtil.replaceXMLEntities(courseDTO.getEntrance()));
				courseDTO.setSelectionProcess(CommonUtil.replaceXMLEntities(courseDTO.getSelectionProcess()));
				courseDTO.setFeeStructure(CommonUtil.replaceXMLEntities(courseDTO.getFeeStructure()));
				courseDTO.setProgramType(CommonUtil.replaceXMLEntities(courseDTO.getProgramType()));
				courseDTO.setOccupationName(CommonUtil.replaceXMLEntities(courseDTO.getOccupationName()));

				integratedCourseList.add(courseDTO);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return integratedCourseList;
	}

	public void addAllIntegCourseDetails(List<ShortListCourseDTO> shortListCourseList, String studentLoginId, String userLoginId)
	{
		SqlSession session = null;
		boolean isTrue = false;
		try
		{
			List<IntegratedCourseDTO> integratedCourseList = new IntegratedCourseDAO().getAllIntegratedCourseDetails();
			Map<Integer, String> courseMap = new HashMap<Integer, String>();
			if (integratedCourseList != null && !integratedCourseList.isEmpty())
			{
				for (IntegratedCourseDTO integratedCourseDTO : integratedCourseList)
				{
					courseMap.put(integratedCourseDTO.getId(), integratedCourseDTO.getProgramName());
				}
			}
			session = MyBatisManager.getInstance().getSession();
			ShortListCourseDAO shortListCourseDAO = new ShortListCourseDAO();
			for (ShortListCourseDTO shortListCourseDTO : shortListCourseList)
			{
				isTrue = shortListCourseDAO.isIdExist(shortListCourseDTO);
				if (!isTrue)
				{
					shortListCourseDAO.insertShortListCourse(session, shortListCourseDTO);
					AuditTrailLogger.addAuditInfo(ModuleNameEnum.EDU_PATH,
							"student: " + studentLoginId + " , " + "course Name: " + courseMap.get(shortListCourseDTO.getIntegratedCourseId())
									+ " is saved into Short list course", userLoginId);
				}
			}
			session.commit();
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

	}

	public List<OccupationDTO> getOccWithCourseByIndusId(int industryId)
	{
		List<OccupationDTO> occList = null;
		try
		{
			occList = new OccupationDAO().getOccupationWithCourseByIndustryid(industryId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return occList;
	}

	public int getCourseCount(int studentId)
	{
		int courseCount = 0;
		try
		{
			courseCount = new ShortListCourseDAO().getCourseCount(studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION , e);
		}
		return courseCount;
	}
}
