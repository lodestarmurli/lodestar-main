package com.lodestar.edupath.APIS.dailyHunt.ScheduleApointment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.student.bean.TrailStudentVO;
import com.lodestar.edupath.util.datatable.DataTableUtils;  

public class ScheduleAppointmentAction extends BaseAction {
	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger( ScheduleAppointmentAction.class);
	ScheduleAppointmentAPIService service = new ScheduleAppointmentAPIService();
	
	List<DHStudentExtraDetailDTO> dhstudentExtraDetailDTO = new ArrayList<DHStudentExtraDetailDTO>();
	
	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute  the ScheduleAppointmentAction Summary Action Of======================= {}", ScheduleAppointmentAction.class);
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject)
		{
			return "SessionExpired";
		}
		dhstudentExtraDetailDTO = service.getAllDHStudentExtraDetails();
		
		
		return SUCCESS;
	}
	

	public List<DHStudentExtraDetailDTO> getDhstudentExtraDetailDTO() {
		return dhstudentExtraDetailDTO;
	}

	public void setDhstudentExtraDetailDTO(List<DHStudentExtraDetailDTO> dhstudentExtraDetailDTO) {
		this.dhstudentExtraDetailDTO = dhstudentExtraDetailDTO;
	}
	
	

}
