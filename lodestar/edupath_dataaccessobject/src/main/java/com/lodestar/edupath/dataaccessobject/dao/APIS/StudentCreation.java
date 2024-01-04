package com.lodestar.edupath.dataaccessobject.dao.APIS;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.MessageQueueDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.CheckingDuplicationDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerStudentDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.sessionfeedback.SessionFeedBackStatusDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;


public class StudentCreation {
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentCreation.class);
	
	
	
	
	public int InsertPartnerStudentDetails(SqlSession session, PartnerStudentDetailDTO partnerstudentdetails)
	{
		int id = 0;
		try
		{
			
			id = session.insert(MyBatisMappingConstants.ADD_PARTNER_STUDENT_DETAILS, partnerstudentdetails);
			OUT.debug("User Details insert id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return partnerstudentdetails.getId();
	}

	public void updatetPartnerStudentUIN(SqlSession session,PartnerStudentDetailDTO partnerstudentdetails)
	{
		int id = 0;
		try
		{
			
			id = session.update(MyBatisMappingConstants.Update_STUDENTUIN, partnerstudentdetails);
			OUT.debug("User Details update id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
	}
	
	
	public PartnerStudentDetailDTO GetPartnetStudentDetails(PartnerStudentDetailDTO partnerstudent) throws Exception
	{
		OUT.debug("StudentCreation class : GetPartnetStudentDetails method");
		PartnerStudentDetailDTO partnerstudentdetails=new PartnerStudentDetailDTO();
		
		partnerstudentdetails =(PartnerStudentDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GetPartnetStudentDetails, partnerstudent);
		
		
		return partnerstudentdetails;
		
	}
	
	
	public PartnerDetailsDTO Getpartnerdetails(String puin) throws Exception
	{
		OUT.debug("StudentCreation class : Getpartnerdetails method");
		PartnerDetailsDTO partnerdetails=new PartnerDetailsDTO();
		
		partnerdetails =(PartnerDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GetPartnetDetails, puin);
		
		
		return partnerdetails;
		
	}
	
	
	public boolean DuplicateValidate(StudentDetailsDTO studentDTO) throws Exception
	{
		boolean vd=false;
		
		CheckingDuplicationDTO chk=new CheckingDuplicationDTO();
		
		chk=(CheckingDuplicationDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.DuplicateValidate, studentDTO);
		
		if(chk!=null && chk.getCountstudent()>0)
		{
			vd=true;
		}
		
		return vd;
	}


	public List<MessageQueueDTO> getallfailedemails()
	{
		try
		{
			OUT.debug("StudentCreation: getallfailedemails");
			
			List<MessageQueueDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_MESSAGE_QUEUE_FAILED, null);
			OUT.debug("getallfailedemails: Number found: {}", resultList != null ? resultList.size() : 0);
			
			
			return resultList;
			
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	
	public MessageQueueDTO getallfailedemailsbyid(int id)
	{
		try
		{
			OUT.debug("StudentCreation: getallfailedemailsbyid");
			
			MessageQueueDTO resultList = (MessageQueueDTO)MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_MESSAGE_QUEUE_FAILED_BYID, id);
			
			
			return resultList;
			
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public void updateallfailedemailsbyid(MessageQueueDTO msgque)
	{
		SqlSession session=null;
		try
		{
			OUT.debug("StudentCreation: updateallfailedemailsbyid");
			int id = 0;
			
		   session = MyBatisManager.getInstance().getSession();
			
			id=session.update(MyBatisMappingConstants.UPDATE_MESSAGE_QUEUE_FAILED_BYID, msgque);
			OUT.debug("updateallfailedemailsbyid  id:{}", id);
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
	
	public void registerforcallbyid(int psid)
	{
		SqlSession session=null;
		try
		{
			OUT.debug("StudentCreation: registerforcallbyid");
			int id = 0;
			
		   session = MyBatisManager.getInstance().getSession();
			
			id=session.update(MyBatisMappingConstants.Register_For_Call, psid);
			OUT.debug("update registerforcallbyid  id:{}", id);
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
	
	
	
	public int insertleadparent(SqlSession session, LeadParentDTO leadparent)
	{
		int id = 0;
		try
		{
			
			id = session.insert(MyBatisMappingConstants.Insert_Lead_Parent, leadparent);
			OUT.debug("insert lead parent insert id :{}", leadparent.getId());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return leadparent.getId();
	}
	
	
	public void updatedToken(LeadParentDTO UpdateLeadToken)
	{
		SqlSession session=null;
		try
		{
			OUT.debug("StudentCreation: updatedToken");
			int id = 0;
			
		   session = MyBatisManager.getInstance().getSession();
			
			id=session.update(MyBatisMappingConstants.Update_Lead_Token, UpdateLeadToken);
			OUT.debug("updatedToken  id:{}", id);
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
	
	public LeadParentDTO GetLeadParentDetails(int id) throws Exception
	{
		OUT.debug("StudentCreation class : GetLeadParentDetails method");
		LeadParentDTO LeadParentDetails=new LeadParentDTO();
		
		LeadParentDetails =(LeadParentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.Get_Lead_Details, id);
		
		
		return LeadParentDetails;
		
	}
	
	
	public LeadParentDTO GetLeadStudentDetails(int id) throws Exception
	{
		OUT.debug("StudentCreation class : GetLeadParentDetails method");
		LeadParentDTO LeadParentDetails=new LeadParentDTO();
		
		LeadParentDetails =(LeadParentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.Get_Lead_Student, id);
		
		
		return LeadParentDetails;
		
	}
	
	
	public void updateLeadParentStudentDetails(LeadParentDTO leadparent)
	{
		SqlSession session=null;
		try
		{
			OUT.debug("StudentCreation: updateLeadParentStudentDetails");
			int id = 0;
			
		   session = MyBatisManager.getInstance().getSession();
			
			id=session.update(MyBatisMappingConstants.Update_Lead_Parent_Student, leadparent);
			OUT.debug("updatedToken  id:{}", id);
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
	
	
	public int InserNewLeadStudentDetails(LeadParentDTO leadparent)
	{
		SqlSession session=null;
		try
		{
			OUT.debug("StudentCreation: InserNewLeadStudentDetails");
			int id = 0;
			
		   session = MyBatisManager.getInstance().getSession();
			
			id=session.insert(MyBatisMappingConstants.Insert_New_Lead_Student, leadparent);
			OUT.debug("updatedToken  id:{}", id);
			session.commit();
			
			if (session != null) {
				session.close();
			}
			
			return leadparent.getId();
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			if (session != null) {
				session.rollback();
			}
		}
		
		return 0;
	}
	
	public int InserNewLeadChildDetails(LeadParentDTO leadparent)
	{
		SqlSession session=null;
		try
		{
			OUT.debug("StudentCreation: InserNewLeadStudentDetails");
			int id = 0;
			
		   session = MyBatisManager.getInstance().getSession();
			
			id=session.insert(MyBatisMappingConstants.Insert_New_Lead_Child, leadparent);
			OUT.debug("updatedToken  id:{}", id);
			session.commit();
			
			if (session != null) {
				session.close();
			}
			
			return leadparent.getId();
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			if (session != null) {
				session.rollback();
			}
		}
		
		return 0;
	}
	
	public void LeadStudenttestupdatedTaken(int leadparentid)
	{
		SqlSession session=null;
		try
		{
			OUT.debug("StudentCreation: LeadStudenttestupdatedTaken");
			int id = 0;
			
		   session = MyBatisManager.getInstance().getSession();
			
			id=session.update(MyBatisMappingConstants.LEad_Student_Test_Taken_SIAT, leadparentid);
			OUT.debug("StudenttestupdatedTaken  id:{}", id);
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
	
	
	
	
	
	public void StudenttestupdatedTaken(int leadparentid)
	{
		SqlSession session=null;
		try
		{
			OUT.debug("StudentCreation: StudenttestupdatedTaken");
			int id = 0;
			
		   session = MyBatisManager.getInstance().getSession();
			
			id=session.update(MyBatisMappingConstants.Student_Test_Taken_SIAT, leadparentid);
			OUT.debug("StudenttestupdatedTaken  id:{}", id);
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
	
	
	public void Appointmentbooking(LeadParentDTO leadparentappointbooking)
	{
		SqlSession session=null;
		try
		{
			OUT.debug("StudentCreation: Appointmentbooking");
			int id = 0;
			
		   session = MyBatisManager.getInstance().getSession();
			
			id=session.update(MyBatisMappingConstants.Lead_Parent_Appointment_Booking, leadparentappointbooking);
			OUT.debug("Appointmentbooking  id:{}", id);
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
