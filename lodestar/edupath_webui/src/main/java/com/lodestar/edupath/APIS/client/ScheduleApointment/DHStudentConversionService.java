package com.lodestar.edupath.APIS.client.ScheduleApointment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentExtraDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class DHStudentConversionService {
	private static final long		serialVersionUID	= 4107476161853263600L;
	private static Logger			OUT					= LoggerFactory.getLogger(DHStudentConversionService.class);


	public StudentDetailsDTO getStudentDTO(Integer studentId) {
		StudentDetailsDTO studentDetailsDTO = null;
		DHStudentExtraDetailDTO dHSEDlDTO =null;
		DHStudentExtraDetailsDAO dhStudentExtraDetailDAO = new DHStudentExtraDetailsDAO();
		try {
			dHSEDlDTO = dhStudentExtraDetailDAO.getCompleteStudentDetailsByStudentId(studentId);
			
			
			if(dHSEDlDTO != null)
			{ 
				studentDetailsDTO = new StudentDetailsDTO();
				studentDetailsDTO.setName(dHSEDlDTO.getName());
				studentDetailsDTO.setStudentContact(dHSEDlDTO.getStudentcontactNumber());
				studentDetailsDTO.setStudentemailId(dHSEDlDTO.getStudentemailId());
				studentDetailsDTO.setStudentcontactNumber(dHSEDlDTO.getStudentcontactNumber());
				studentDetailsDTO.setFatherName(dHSEDlDTO.getFatherName());
				studentDetailsDTO.setFathercontactno(dHSEDlDTO.getFathercontactno());
				studentDetailsDTO.setFatherEmailId(dHSEDlDTO.getFatherEmailId());
				studentDetailsDTO.setMotherName(dHSEDlDTO.getMotherName());
				studentDetailsDTO.setMotherContact(dHSEDlDTO.getMothercontactno());
				studentDetailsDTO.setMothercontactno(dHSEDlDTO.getMothercontactno());
				studentDetailsDTO.setMotheremailId(dHSEDlDTO.getMotheremailId());
				studentDetailsDTO.setVenue("ONLINE");
				studentDetailsDTO.setSection(dHSEDlDTO.getSection());
				studentDetailsDTO.setSession1Date(dHSEDlDTO.getSession1Date());
				studentDetailsDTO.setSession2Date(dHSEDlDTO.getSession2Date());
				studentDetailsDTO.setSession3Date(dHSEDlDTO.getSession2Date());
				studentDetailsDTO.setFacilitatorId(0);
				studentDetailsDTO.setComputerFacility(false);
				studentDetailsDTO.setDueAmount(0.00);
				studentDetailsDTO.setGender(dHSEDlDTO.getGender());				
				studentDetailsDTO.setCityId(dHSEDlDTO.getCityId());
				studentDetailsDTO.setCityName(dHSEDlDTO.getCityName());
				studentDetailsDTO.setStudentType(StudentTypeEnum.FULL);
				studentDetailsDTO.setClassId(dHSEDlDTO.getClassId());				
				if(dHSEDlDTO.getSchoolId()==0)
				{
					studentDetailsDTO.setSchoolId(126);
					studentDetailsDTO.setOtherSchool(dHSEDlDTO.getSchoolName());
				}
				else {
					studentDetailsDTO.setSchoolId(dHSEDlDTO.getSchoolId());
					studentDetailsDTO.setSchoolName(dHSEDlDTO.getSchoolName());
				}
				 
				SessionScheduleDetailsDTO sedDTO =new SessionScheduleDetailsDTO();
				sedDTO.setSession1Date(dHSEDlDTO.getSession1Date());
				sedDTO.setSession2Date(dHSEDlDTO.getSession2Date());
				sedDTO.setSession3Date(dHSEDlDTO.getSession2Date());
				studentDetailsDTO.setSeDetailsDTO(sedDTO);
				
			}
			
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentDetailsDTO;
	}

	
	
	
	
}
