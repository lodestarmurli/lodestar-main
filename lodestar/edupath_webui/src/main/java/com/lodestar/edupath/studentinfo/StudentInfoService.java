package com.lodestar.edupath.studentinfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.tum.StudentTUMDAO;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class StudentInfoService
{
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentInfoService.class);

	public List<StudentTUMDTO> getStudentTUMByStudentId(int studentId)
	{
		List<StudentTUMDTO> tumList = null;
		try
		{
			StudentTUMDTO tumdto = new StudentTUMDTO();
			tumdto.setStudentId(studentId);
			tumList = new StudentTUMDAO().getStudentTUMDetailsByStudentId(tumdto);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return tumList;
	}

	public StudentDetailsDTO getStudentDetailsById(int studentId)
	{
		StudentDetailsDTO studentDTO = new StudentDetailsDTO();
		try
		{
			
			studentDTO = new StudentDetailsDAO().getStudentDetailsById(studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentDTO;
	}

	public StudentCGTResult getStudentCGTResultByStudentId(int id)
	{

		StudentCGTResult studentCGTResult = new StudentCGTResult();
		try
		{
			studentCGTResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT, id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentCGTResult;
	}

	public OccupationDTO getOccupationDetailsById(int id)
	{
		OccupationDTO occupationDTO = new OccupationDTO();
		try
		{
			occupationDTO = new OccupationDAO().getOccupationBasicDetails(id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return occupationDTO;
		
	}
}
