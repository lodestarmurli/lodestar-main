package com.lodestar.edupath.studentinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class StudentInfoAction extends BaseAction
{

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentInfoAction.class);
	private int					studentId;
	private List<StudentTUMDTO>	studentTUMList		= new ArrayList<StudentTUMDTO>();
	private StudentDetailsDTO	studentDetailsDTO	= new StudentDetailsDTO();
	private StudentCGTResult	studentCGTResult	= new StudentCGTResult();
	private Map<String, String>	occupIndusMap		= new HashMap<String, String>();

	public String getStudentTUM()
	{
		try
		{
			OUT.debug("student id: {}", studentId);
			OUT.info("StudentInfoAction : inside getStudentTUM method");
			// studentTUMList = new StudentInfoService().getStudentTUMByStudentId(studentId);
			studentDetailsDTO = new StudentInfoService().getStudentDetailsById(studentId);
			List<StudentTUMDTO> tumList = null;
			Map<Long, String> studentQuesAnsMap = null;
			if (null != studentDetailsDTO)
			{

				tumList = studentDetailsDTO.getStudentTUMList();
				studentQuesAnsMap = studentDetailsDTO.getStudentQuesAnsMap();
			}
			if (tumList != null)
			{
				for (StudentTUMDTO studentTUMDTO : tumList)
				{
					studentQuesAnsMap.put((long) studentTUMDTO.getQuestionSlNo(), studentTUMDTO.getAnswer());
				}
			}

			studentCGTResult = new StudentInfoService().getStudentCGTResultByStudentId(studentId);
			if (studentCGTResult != null)
			{
				String occupationIds = studentCGTResult.getOccupationIds();
				if (occupationIds != null)
				{
					String[] occupaIdArray = occupationIds.split(",");
					for (String occupid : occupaIdArray)
					{
						OccupationDTO occupationDTO = new StudentInfoService().getOccupationDetailsById(Integer.parseInt(occupid.trim()));
						if (occupationDTO != null)
						{
							occupIndusMap.put(occupationDTO.getName(), occupationDTO.getIndustryName());
						}
					}
				}
			}

		}
		catch (Exception e)
		{
			OUT.info(ApplicationConstants.EXCEPTION, e);
		}
		return "tuminfo";
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public List<StudentTUMDTO> getStudentTUMList()
	{
		return studentTUMList;
	}

	public void setStudentTUMList(List<StudentTUMDTO> studentTUMList)
	{
		this.studentTUMList = studentTUMList;
	}

	public StudentDetailsDTO getStudentDetailsDTO()
	{
		return studentDetailsDTO;
	}

	public void setStudentDetailsDTO(StudentDetailsDTO studentDetailsDTO)
	{
		this.studentDetailsDTO = studentDetailsDTO;
	}

	public StudentCGTResult getStudentCGTResult()
	{
		return studentCGTResult;
	}

	public void setStudentCGTResult(StudentCGTResult studentCGTResult)
	{
		this.studentCGTResult = studentCGTResult;
	}

	public Map<String, String> getOccupIndusMap()
	{
		return occupIndusMap;
	}

	public void setOccupIndusMap(Map<String, String> occupIndusMap)
	{
		this.occupIndusMap = occupIndusMap;
	}

}
