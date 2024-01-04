package com.lodestar.edupath.programTest.engineeringBranchSelector;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.engineeringselector.EngineeringSelectorDAO;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EBFavouriteSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class EBFavouriteSubjectAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(EBFavouriteSubjectAction.class);
	
	private boolean saved=false;
	private int studentId;
	private int physics;
	private int mathematics;
	private int chemistry;
	private int biology;
	private SqlSession session = null;

	

	EBFavouriteSelectorDTO eBFSdto = new EBFavouriteSelectorDTO();
	EngineeringSelectorDAO dao = new EngineeringSelectorDAO();
	private StudentDetailsDTO	student				= new StudentDetailsDTO();
	StudentDetailsDAO studentDAO = new StudentDetailsDAO();
	
	
	public String execute() throws Exception {
		
		OUT.debug("Inside EBFavouriteSubjectAction");
		
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject)
		{
			return "SessionExpired";
		}
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setId(getUserSessionObject().getId());
		student=studentDAO.getStudentDetailsByUserId(userDetailDTO);
		eBFSdto=dao.getEBFavouriteSubject(student.getId());
		
		if(eBFSdto!=null)
		{
			saved=true;
			physics=eBFSdto.getPhysics();
		    mathematics=eBFSdto.getMathematics();
			chemistry=eBFSdto.getChemistry();
			biology=eBFSdto.getBiology();
		}
		else
		{
			saved=false;
		}
		OUT.debug("bharath EBFavouriteSubjectAction saved:{}, EBFavouriteSelectorDTO:{}",saved,eBFSdto);
		
		
		
		return SUCCESS;
	}
	
	
	public String save()
	{
		OUT.debug("Inside EBFavouriteSubjectAction save method studentId:{},eBFSdto:{}, physics:{}, mathematics:{}, chemistry:{}, biology:{} ",studentId,eBFSdto,physics,mathematics,chemistry,biology);
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
			eBFSdto.setStudentId(student.getId());
			eBFSdto.setPhysics(physics);
			eBFSdto.setChemistry(chemistry);
			eBFSdto.setMathematics(mathematics);
			eBFSdto.setBiology(biology);
			session = MyBatisManager.getInstance().getSession();
			int result = dao.InsertEBFavouriteSubject(session, eBFSdto);
			session.commit();
			if(result>0)
			{
				saved=true;
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

		return "EBSELECTORWELCOMEPAGE";
		
		
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


	public int getPhysics() {
		return physics;
	}


	public void setPhysics(int physics) {
		this.physics = physics;
	}


	public int getMathematics() {
		return mathematics;
	}


	public void setMathematics(int mathematics) {
		this.mathematics = mathematics;
	}


	public int getChemistry() {
		return chemistry;
	}


	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}


	public int getBiology() {
		return biology;
	}


	public void setBiology(int biology) {
		this.biology = biology;
	}


	public EBFavouriteSelectorDTO geteBFSdto() {
		return eBFSdto;
	}


	public void seteBFSdto(EBFavouriteSelectorDTO eBFSdto) {
		this.eBFSdto = eBFSdto;
	}
	
	
	
	
	

}
