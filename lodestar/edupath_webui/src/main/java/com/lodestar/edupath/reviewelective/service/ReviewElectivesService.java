package com.lodestar.edupath.reviewelective.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.CombinationDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathElectiveShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.SubjectDAO;
import com.lodestar.edupath.datatransferobject.dto.elective.CombinationDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathElectiveShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.SubjectDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ReviewElectivesService
{
	private static Logger	OUT	= LoggerFactory.getLogger(ReviewElectivesService.class);

	public Map<String, Object> getElectiveMap(Integer studentId)
	{
		Map<String, Object> finalMap = new HashMap<String, Object>();

		List<EduPathShortListDTO> finalList = new ArrayList<EduPathShortListDTO>();
		List<SubjectDTO> subjectList = new ArrayList<SubjectDTO>();
		List<CombinationDTO> combinationDTOs = new ArrayList<CombinationDTO>();
		try
		{
			finalList = new EduPathShortListDAO().getEduPathShortListByStudentId(studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finalMap.put(ElectiveMapKey.EDUPATHSHORTLIST.name(), finalList);

		if (null != finalList && !finalList.isEmpty())
		{
			EduPathShortListDTO eduPathShortListDTO = finalList.get(0);
			finalMap.put(ElectiveMapKey.STREAM.name(), eduPathShortListDTO);
		}
		try
		{
			combinationDTOs = new CombinationDAO().getCombinationListByStudentId(studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finalMap.put(ElectiveMapKey.COMBINATION.name(), combinationDTOs);
		try
		{
			subjectList = new SubjectDAO().getSubjectListByStudentId(studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finalMap.put(ElectiveMapKey.SUBJECT.name(), subjectList);
		return finalMap;
	}

	public Integer getStudentIdByUserId(int studentUserId)
	{
		int studentId = 0;
		if (studentUserId == 0)
		{
			return studentId;
		}
		try
		{

			StudentDetailsDTO studentDetailsDTO = new StudentDetailsDAO().getStudentDetailsByUserId(studentUserId);
			if (null != studentDetailsDTO)
			{
				studentId = studentDetailsDTO.getId();
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentId;
	}

	public EActionStatus updateShortlistElectiveById(String shortListIds, Integer stdId)
	{
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			List<EduPathElectiveShortListDTO> listDTOs = new ArrayList<EduPathElectiveShortListDTO>();
			EduPathElectiveShortListDTO dto;
			String[] idArray = shortListIds.split(",");
			for (int i = 0; i < idArray.length; i++)
			{
				String shortListId = idArray[i];
				dto = new EduPathElectiveShortListDTO();
				dto.setCombinationId(Integer.parseInt(shortListId));
				dto.setOrderNo(i + 1);
				dto.setStudentId(stdId);
				listDTOs.add(dto);
			}

			for (EduPathElectiveShortListDTO shortListDTO : listDTOs)
			{
				new EduPathElectiveShortListDAO().updateShortListById(session, shortListDTO);
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
		return EActionStatus.SUCCESS;
	}
}

enum ElectiveMapKey
{
	EDUPATHSHORTLIST, SUBJECT, COMBINATION, STREAM
}