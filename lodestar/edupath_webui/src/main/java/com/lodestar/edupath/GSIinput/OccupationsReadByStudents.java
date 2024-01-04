package com.lodestar.edupath.GSIinput;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.APIS.OccupationsReaddao;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.finalsummary.bean.ReportSummaryBean;
import com.opensymphony.xwork2.ModelDriven;

public class OccupationsReadByStudents extends BaseAction implements ModelDriven<ReportReadbean>
{
	
	private static final long		serialVersionUID	= 1L;
	private static final Logger		OUT					= LoggerFactory.getLogger(OccupationsReadByStudents.class);
	private ReportReadbean reportreadbystudentbean=new ReportReadbean();
	
	
	
	public ReportReadbean getReportreadbystudentbean() {
		return reportreadbystudentbean;
	}



	public void setReportreadbystudentbean(ReportReadbean reportreadbystudentbean) {
		this.reportreadbystudentbean = reportreadbystudentbean;
	}



	public String execute()
	{
		OUT.debug("OccupationsReadByStudents : inside execute method");
		try
		{
			StudentSessionObject studentSessionObject = getStudentSessionObject();
			
			OUT.debug("Student ID==>"+studentSessionObject.getId());
			
			OccupationsReaddao occr=new OccupationsReaddao();
			
			List<OccupationDTO> OcupationsList=occr.GetStudentOccupationsRead(studentSessionObject.getId());
			
			reportreadbystudentbean.setOcupationsList(OcupationsList);
			int totalindustry=occr.GetTotalIndustry(studentSessionObject.getId());
			
			int totalOccupation=occr.GetTotalOccupation(studentSessionObject.getId());
			
			reportreadbystudentbean.setTotalNoOfIndustry(totalindustry);
			
			reportreadbystudentbean.setTotalNoOfOccupation(totalOccupation);

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION);
		}
		return SUCCESS;
	}



	@Override
	public ReportReadbean getModel() {
		return reportreadbystudentbean;
	}

}
