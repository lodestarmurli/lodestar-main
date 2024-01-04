package com.lodestar.edupath.APIS.chanakya.ScheduleApointment;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentExtraDetailDTO;  

public class ScheduleAppointmentAction extends BaseAction {
	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger( ScheduleAppointmentAction.class);
	ScheduleAppointmentAPIService service = new ScheduleAppointmentAPIService();
	
	List<ChanakyaStudentExtraDetailDTO> dhstudentExtraDetailDTO = new ArrayList<ChanakyaStudentExtraDetailDTO>();
	
	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute  the ScheduleAppointmentAction Summary Action Of======================= {}", ScheduleAppointmentAction.class);
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject)
		{
			return "SessionExpired";
		}
		dhstudentExtraDetailDTO = service.getAllChanakyaStudentExtraDetails();
		
		
		return SUCCESS;
	}
	

	public List<ChanakyaStudentExtraDetailDTO> getChanakyastudentExtraDetailDTO() {
		return dhstudentExtraDetailDTO;
	}

	public void setDhstudentExtraDetailDTO(List<ChanakyaStudentExtraDetailDTO> dhstudentExtraDetailDTO) {
		this.dhstudentExtraDetailDTO = dhstudentExtraDetailDTO;
	}
	
	

}
