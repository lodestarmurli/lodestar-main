package com.lodestar.edupath.path.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.dataaccessobject.dao.ShortListExams.ShortListExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.integratedcourse.IntegratedCourseDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.shortlistcourse.ShortListCourseDAO;
import com.lodestar.edupath.datatransferobject.dto.ShortListCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.ShortListExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.finalsummary.service.FinalSummaryService;

public class EduPathSessionScheduleService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(EduPathSessionScheduleService.class);

	public boolean checkAndUpdateSessionSchedule(int studentId)
	{
		OUT.debug("Inside EduPathSessionScheduleService");
		try
		{
			OUT.debug("Student Id:" + studentId);

			List<EduPathShortListDTO> list = new EduPathShortListDAO().getEduPathShortListByActiveStudentId(studentId);
			if (null != list && !list.isEmpty())
			{
				List<Integer> occIdsList = new ArrayList<Integer>();

				for (EduPathShortListDTO dto : list)
				{
					if (null != dto.getOccupationId() && dto.getOccupationId() > 0)
					{
						occIdsList.add(dto.getOccupationId());
					}
				}
				if (!occIdsList.isEmpty())
				{

					int courseCount = new IntegratedCourseDAO().getIntegratedCourseCountByOccId(occIdsList);
					int examCount = new EntranceExamsDAO().getEntranceExamsCountByOccId(occIdsList);
					if (examCount > 0)
					{
						ShortListExamsDTO shortListExamDTO = new ShortListExamsDTO();
						shortListExamDTO.setStudentId(studentId);
						shortListExamDTO.setOccIdsList(occIdsList);

						int shortListExamCount = new ShortListExamsDAO().getShortListExamCountByOccId(shortListExamDTO);
						if (shortListExamCount < 1)
						{
							return false;
						}
					}

					if (courseCount > 0)
					{
						ShortListCourseDTO shorListCourseDTO = new ShortListCourseDTO();
						shorListCourseDTO.setStudentId(studentId);
						shorListCourseDTO.setOccIdsList(occIdsList);

						int shortListCourseCount = new ShortListCourseDAO().getShortListCount(shorListCourseDTO);
						if (shortListCourseCount < 1)
						{
							return false;
						}
					}
				}
				updateSessionSchedule(studentId);
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return true;
	}

	@SuppressWarnings("null")
	private void updateSessionSchedule(int studentId)
	{
		OUT.debug("Update SessionSchedule student Id:" + studentId);
		try
		{
			new SessionScheduleDetailsDAO().updateSessionTwoByStudentId(studentId);
			StudentDetailsDTO st = StudentDetailsDAO.getStudentDetailsByStudentId(studentId);
			if(st.getClassId()>=3)
			{
				StudentSessionObject studentSessionObject = null;
				studentSessionObject.setId(st.getId());
				FinalSummaryService.doFinalizeSession3(studentSessionObject);
			
			}
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	public static void main(String[] args)
	{
		new EduPathSessionScheduleService().checkAndUpdateSessionSchedule(8);
	}

}
