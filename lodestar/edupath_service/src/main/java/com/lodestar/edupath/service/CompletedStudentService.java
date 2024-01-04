package com.lodestar.edupath.service;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.scheduler.SchedulerTask;

public class CompletedStudentService implements SchedulerTask {
	private static final Logger	OUT			= LoggerFactory.getLogger(CompletedStudentService.class);

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String taskName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGroupName(String taskGroupName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParams(String taskParams) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGroupName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		OUT.info("Started Complete Student Service");
		

		try
		{
			OUT.info("Inside CompletedStudentService: Running service to move students to completed students");
			StudentDetailsDAO.CompleteStudentservice();
			OUT.info("Completed Complete Student Service");
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		
		
	}
	

}
