package com.lodestar.edupath.programTest.CareerDegreeDiscovery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.CareerDegreeDiscovery.CareerDegreeDiscoveryDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.engineeringselector.EngineeringSelectorDAO;
import com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery.CDDStreamDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EBFavouriteSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StreamTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class CCDStreamSelectAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(CCDStreamSelectAction.class);
	
	private boolean saved=false;
	private int studentId;
	private int classId;
	private int stream;
	private SqlSession session = null;
	private Map<Integer,String> streamList = new HashMap<Integer,String>();

	

	CDDStreamDTO dto = new CDDStreamDTO();
	CareerDegreeDiscoveryDAO dao = new CareerDegreeDiscoveryDAO();
	private StudentDetailsDTO	student				= new StudentDetailsDTO();
	StudentDetailsDAO studentDAO = new StudentDetailsDAO();
	
	
	public String execute() throws Exception {
		
		OUT.debug("Inside CCDStreamSelectAction");
		
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject)
		{
			return "SessionExpired";
		}
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setId(getUserSessionObject().getId());
		student=studentDAO.getStudentDetailsByUserId(userDetailDTO);
		dto=dao.getCDDStream(student.getId());
		
		if(dto!=null)
		{
			saved=true;
			classId=dto.getClassId();
			stream=dto.getStream();
		}
		else
		{
			for (StreamTypeEnum stenum : StreamTypeEnum.values()) {
				if(!stenum.getDisplayName().isEmpty())
				{
					streamList.put(stenum.getWeight(),stenum.getDisplayName());
				}
			}
			
			saved=false;
		}
		OUT.debug("bharath CCDStreamSelectAction saved:{}, CDDStreamDTO:{}",saved,dto);
		
		
		
		return SUCCESS;
	}
	
	
	public String save()
	{
		OUT.debug("Inside CCDStreamSelectAction save method studentId:{},dto:{}, classId:{}, stream:{}",studentId,dto,classId,stream);
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject)
		{
			return "SessionExpired";
		}
		
		try
		{
			UserDetailDTO userDetailDTO = new UserDetailDTO();
			userDetailDTO.setId(getUserSessionObject().getId());
			student=studentDAO.getStudentDetailsByUserId(userDetailDTO);
			dto.setStudentId(student.getId());
			dto.setClassId(classId);
			dto.setStream(stream); 
			session = MyBatisManager.getInstance().getSession();
			int result = dao.insertCDDStream(session, dto);
			StudentDetailsDTO	studentdto				= new StudentDetailsDTO();
			StudentDetailsDAO studentDAO = new StudentDetailsDAO();
			studentdto.setId(student.getId());
			studentdto.setClassId(classId);
			studentDAO.updateStudentClass(session,studentdto);
			
			
			session.commit();
			if(result>0)
			{
				return "Welcome";
			}
		
			
			
		}catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return "Welcome";
		
		
	}


	public boolean isSaved() {
		return saved;
	}


	public void setSaved(boolean saved) {
		this.saved = saved;
	}


	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public int getClassId() {
		return classId;
	}


	public void setClassId(int classId) {
		this.classId = classId;
	}


	public int getStream() {
		return stream;
	}


	public void setStream(int stream) {
		this.stream = stream;
	}


	public CDDStreamDTO getDto() {
		return dto;
	}


	public void setDto(CDDStreamDTO dto) {
		this.dto = dto;
	}


	public StudentDetailsDTO getStudent() {
		return student;
	}


	public void setStudent(StudentDetailsDTO student) {
		this.student = student;
	}


	public Map<Integer, String> getStreamList() {
		return streamList;
	}


	public void setStreamList(Map<Integer, String> streamList) {
		this.streamList = streamList;
	}

 
	

}
