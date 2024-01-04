package com.lodestar.edupath.service;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.scheduler.SchedulerTask;

public class ArchiveStudentService implements SchedulerTask{

	private static final Logger	OUT			= LoggerFactory.getLogger(ArchiveStudentService.class);
	private static final String	DATE_FORMAT	= "dd-MMM-yyyy";
	private static final String	TIME_FORMAT	= "hh:mm a";
	
	@Override
	public void setId(Integer id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String taskName)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setGroupName(String taskGroupName)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setParams(String taskParams)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getId()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGroupName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParams()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void execute()
	{
		OUT.info("Started Archive Student Service");
		
		try
		{
			String datedtr="2018-04-10";
			List<StudentDetailsDTO> ArchiveStudentList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.List_OF_Archive_Student_ID, datedtr);
			for(StudentDetailsDTO Student: ArchiveStudentList)
			{
				OUT.info("Student ID to be Achive===>"+Student.getId());
				SqlSession session=null;
				try
				{
				session = MyBatisManager.getInstance().getSession();
				int id = 0;
				UserDetailDTO userdetail = new UserDetailDTO();
				userdetail.setId(Student.getUserId());
				Date currentdate = new Date();
				userdetail.setUpdatedOn(currentdate);
				userdetail.setIsActive("N");
				id=session.update(MyBatisMappingConstants.UPDATE_USER_DETAILS_Achirve_BY_ID, userdetail);
				   OUT.debug("Archive Student Service sent update  id:{}", id);
					session.commit();
					
					if (session != null) {
						session.close();
					}
				}
				catch (Exception e)
				{
					OUT.error(ApplicationConstants.EXCEPTION, e);
					if (session != null) {
						session.rollback();
					}
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		OUT.info("Completed Archive Student Service");
		
		
	}
	
	
	
	
	
}
