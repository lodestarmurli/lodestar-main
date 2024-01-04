package com.lodestar.edupath.APIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.StudentCreation;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.payment.ManagePaymentDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userrole.UserRoleDAO;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.MessageQueueDTO;
import com.lodestar.edupath.datatransferobject.dto.PaymentDTO;
import com.lodestar.edupath.datatransferobject.dto.TrialStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerStudentDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserRoleDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.UserTypeEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.util.EncryptionDecryptionData;
import com.lodestar.edupath.util.PasswordGeneratorService;



public class APISService {
	public static Logger OUT = LoggerFactory.getLogger(APISService.class);
	private String auditDateStr;
	
	public EActionStatus insertStudent(StudentDetailsDTO studentDTO, PartnerDetailsDTO parnerdetails,String CityStringData,String suin)
	{
		OUT.debug("Inside APISService class: insertStudent Method");
		StudentDetailsDTO studentDetailsDTO = null;
		StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		
		Double dueAmount = null;
		
		try
		{
			
			session = MyBatisManager.getInstance().getSession();

			int lastUserDetailId = new UserDetailsDAO().lastUserDetailId();
			studentLoginId = new StringBuilder();
			studentLoginId.append("LD").append(++lastUserDetailId);
			if (null != studentLoginId.toString() && !studentLoginId.toString().isEmpty()) {
				UserRoleDTO roleDTO = new UserRoleDTO();
				roleDTO.setName(UserTypeEnum.USER.getDisplayName());

				UserRoleDAO roleDAO = new UserRoleDAO();
				roleDTO = roleDAO.getUserRoleByName(roleDTO);

				UserDetailDTO userDetailDTO = new UserDetailDTO();
				String password = PasswordGeneratorService.getRandomAlphanumeric();
				userDetailDTO.setLoginId(studentLoginId.toString());
				userDetailDTO.setPassword(AESCipher.encrypt(password.getBytes()));
				userDetailDTO.setRoleId(roleDTO.getId());
				userDetailDTO.setUserType(UserTypeEnum.USER.getDisplayName());
				userDetailDTO.setIsActive("Y");

				// Insert User Details
				
				int userId = new UserDetailsDAO().insertUserDetail(session, userDetailDTO);

				if (userId < 0) {
					return EActionStatus.FAILURE;
				}

				// Insert Student Detail

				TrialStudentExtraDetailDTO.setMothercontactno(studentDTO.getMothercontactno());
				TrialStudentExtraDetailDTO.setMotheremailId(studentDTO.getMotheremailId());
				TrialStudentExtraDetailDTO.setMotherName(studentDTO.getMotherName());
				TrialStudentExtraDetailDTO.setStudentcontactNumber(studentDTO.getStudentcontactNumber());
				TrialStudentExtraDetailDTO.setStudentemailId(studentDTO.getStudentemailId());
				TrialStudentExtraDetailDTO.setFatherEmailId(studentDTO.getFatheremailId());
				TrialStudentExtraDetailDTO.setFathercontactno(studentDTO.getContactNumber());
				TrialStudentExtraDetailDTO.setFatherName(studentDTO.getFathername());

				studentDTO.setUserId(userId);
				if(parnerdetails!=null && parnerdetails.getPartneName()!=null && !parnerdetails.getPartneName().equals(""))
				{
					studentDTO.setSource(studentDTO.getStudentType().name()+"_Partner_"+parnerdetails.getPartneName());
				}
				else
				{
					studentDTO.setSource(studentDTO.getStudentType().name()+"_Partner_"+"No_Partner");
				}

				
				

				if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
					studentDTO.setFatheremailId(studentDTO.getMotheremailId());
					studentDTO.setFathername(studentDTO.getMotherName());
				}
				if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
					studentDTO.setContactNumber(studentDTO.getMothercontactno());
				}

				studentDetailsDTO = studentDetailsDAO.insertStudent(session, studentDTO);
				// studentDetailsDAO.inserttrialStudent(session, studentDTO);

				SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();

				// sasedeve added by vyankatesh
				PaymentDTO paymentDTO = new PaymentDTO();
				// sasedeve added by vyankatesh
				
				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty())
				{
					seDetailsDTO.setVenue(studentDTO.getVenue());
				}
				else
				{
					seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
				}
				
				seDetailsDTO.setStudentId(studentDTO.getId());
				seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());

				// Vyankatesh edit add Trialstudent extra Field
				TrialStudentExtraDetailDTO.setStudentId(studentDetailsDTO.getId());

				boolean isTrial = false;
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					setSessionSchedule(studentDetailsDTO, seDetailsDTO);

				} else {
					isTrial = true;
					seDetailsDTO.setFacilitatorId(0);

				}
				// Insert Student Session Schedule
				int studentId = new SessionScheduleDetailsDAO().insertSessionSchedule(session, seDetailsDTO);
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);

				// sasedeve added by vyankatesh
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					
					
					if(studentDTO.getAgreedAmount()!=null && studentDTO.getPaidAmount()!=null)
					{
					
						dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());
	
						paymentDTO.setLoginId(studentLoginId.toString());
						paymentDTO.setStudentId(studentId);
						paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
						paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
						paymentDTO.setDueAmount(String.valueOf(dueAmount));
						new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
					}
				}
				// sasedeve added by vyankatesh

				boolean isNew = true;
				if(parnerdetails!=null)
				{
					PartnerStudentDetailDTO partnerstudentdetails=new PartnerStudentDetailDTO();
					StudentCreation studentcreation=new StudentCreation();
					
					
					String StudentUIN=null;
					String StudentN=null;
					Date ndate=new Date();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
					
					
					
					if(studentDTO.getName().length()>4)
					{
						StudentN=StringUtils.substring(studentDTO.getName(),0,4);
					}
					else
					{
						StudentN=StringUtils.substring(studentDTO.getName(),0,1);
					}
					
					StudentUIN=simpleDateFormat.format(ndate)+"-"+StudentN.trim().toUpperCase();
					
					
					
					partnerstudentdetails.setPartnerUIN(parnerdetails.getUIN());
					//partnerstudentdetails.setStudenUIN(StudentUIN);
					partnerstudentdetails.setStudentid(studentId);
					partnerstudentdetails.setLDID(studentLoginId.toString());
					partnerstudentdetails.setCityString(CityStringData);
					
					
					int LASTID=studentcreation.InsertPartnerStudentDetails(session,partnerstudentdetails);
					
					//StudentUIN=StudentUIN+"-"+LASTID;
					partnerstudentdetails.setStudenUIN(suin);
					partnerstudentdetails.setId(LASTID);
					
					
					studentcreation.updatetPartnerStudentUIN(session,partnerstudentdetails);
					//StudentCreationAPI.Studentuin=StudentUIN;
				}

				session.commit();
				if (userDetailDTO.getCreatedOn() != null) {
					auditDateStr = TimeUtil.getDateFromMillis(userDetailDTO.getCreatedOn().getTime(),
							TimeUtil.QUERY_DATE_FORMAT);
				}
				//EActionStatus.
				return EActionStatus.SUCCESS;
			}
			
		}
		catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return EActionStatus.FAILURE;
	}
	private void setSessionSchedule(StudentDetailsDTO studentDTO, SessionScheduleDetailsDTO seDetailsDTO)
			throws ParseException {
		 Date date = new Date();
		 SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		if(studentDTO.getSession1DateStr()!=null && !studentDTO.getSession1DateStr().equals(""))
		{
			seDetailsDTO
			.setSession1Date(TimeUtil.getDate(studentDTO.getSession1DateStr(), TimeUtil.QUERY_DATE_FORMAT));
		}
		else
		{
			seDetailsDTO
			.setSession1Date(TimeUtil.getDate(ft.format(date), TimeUtil.QUERY_DATE_FORMAT));
		}
		if(studentDTO.getSession2DateStr()!=null && !studentDTO.getSession2DateStr().equals(""))
		{
			seDetailsDTO
			.setSession2Date(TimeUtil.getDate(ft.format(date), TimeUtil.QUERY_DATE_FORMAT));
		}
		else
		{
			seDetailsDTO
			.setSession2Date(TimeUtil.getDate(ft.format(date), TimeUtil.QUERY_DATE_FORMAT));
		}
		if(studentDTO.getSession3DateStr()!=null && !studentDTO.getSession3DateStr().equals(""))
		{
			seDetailsDTO
			.setSession3Date(TimeUtil.getDate(studentDTO.getSession3DateStr(), TimeUtil.QUERY_DATE_FORMAT));
		}
		else
		{
			seDetailsDTO
			.setSession3Date(TimeUtil.getDate(ft.format(date), TimeUtil.QUERY_DATE_FORMAT));
		}
		
		
		
	}

	public PartnerStudentDetailDTO GetPartnetstudentDetails(PartnerStudentDetailDTO partnerstudent)
	{
		PartnerStudentDetailDTO partnerstudentdetails=new PartnerStudentDetailDTO();
		OUT.debug("APISService class : GetPartnetstudentDetails method");
		
		try
		{
			
			partnerstudentdetails=new StudentCreation().GetPartnetStudentDetails(partnerstudent);
			
			
		}
		catch (Exception e) 
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		return partnerstudentdetails;
	}
	
	
	public PartnerDetailsDTO GetPartnerDetails(String puin)
	{
		PartnerDetailsDTO partnerdetails=new PartnerDetailsDTO();
		
		
	 OUT.debug("APISService class : GetPartnerDetails method");
	 
	// System.out.println("=================>"+puin);
	 
		
		try
		{
			
			partnerdetails=new StudentCreation().Getpartnerdetails(puin);
			
			
		}
		catch (Exception e) 
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		
		return partnerdetails;
	}
	
	
	public boolean Duplicatev(StudentDetailsDTO studentDTO)
	{
		boolean dv=false;
		
		try
		{
			
			dv=new StudentCreation().DuplicateValidate(studentDTO);
			
			
		}
		catch (Exception e) 
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		
		}
		
		return dv;
	}


	public List<MessageQueueDTO> GetFailedEmails()
	{
		OUT.info("APISService : GetFailedEmails");
		
		try
		{
			List<MessageQueueDTO>	failedemails	= new ArrayList<MessageQueueDTO>();
			
			failedemails=new StudentCreation().getallfailedemails();
			
			
			return failedemails;
			
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public MessageQueueDTO GetFailedEmailsbyid(int id)
	{
		OUT.info("APISService : GetFailedEmailsbyid");
		
		try
		{
			MessageQueueDTO	failedemails	= new MessageQueueDTO();
			
			failedemails=new StudentCreation().getallfailedemailsbyid(id);
			
			
			return failedemails;
			
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public void updateFailedEmailsbyid(MessageQueueDTO msgque)
	{
		OUT.info("APISService : updateFailedEmailsbyid");
		
		try
		{
			StudentCreation updatefailedemail=new StudentCreation();
			
			updatefailedemail.updateallfailedemailsbyid(msgque);
			
			
			
			
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
	}
	
	public void registerstudentforcall(int id)
	{
		OUT.info("APISService : registerstudentforcall");
		
		try
		{
			StudentCreation updatefailedemail=new StudentCreation();
			
			updatefailedemail.registerforcallbyid(id);
			
			
			
			
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}
    
	public LeadParentDTO getLeadDetails(int id)
	{
		OUT.info("APISService : getLeadDetails");
		
		LeadParentDTO leadparent=new LeadParentDTO();
		
		try
		{
			leadparent=new StudentCreation().GetLeadParentDetails(id);
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		return leadparent;
		
	}
	
	
	public LeadParentDTO getLeadStudent(int id)
	{
		OUT.info("APISService : getLeadDetails");
		
		LeadParentDTO leadparent=new LeadParentDTO();
		
		try
		{
			leadparent=new StudentCreation().GetLeadStudentDetails(id);
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		return leadparent;
		
	}
	
	
	
	public int insertLeadParent(LeadParentDTO leadparent)
	{
		OUT.info("APISService : insertLeadParent");
		
		int id=0;
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			StudentCreation InserLeadParentobj=new StudentCreation();
			id=InserLeadParentobj.insertleadparent(session,leadparent);
			session.commit();
		}
		catch (Throwable e)
		{
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		
		return id;
		
	}
	
	
	public void UpdateLeadToken(LeadParentDTO UpdateLeadToken)
	{
		OUT.info("APISService : UpdateLeadToken");
		
		try
		{
			StudentCreation LeadToken=new StudentCreation();
			
			LeadToken.updatedToken(UpdateLeadToken);
			
			
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}

	public EActionStatus LeadinsertStudent(StudentDetailsDTO studentDTO,LeadParentDTO leadparent)
	{
		OUT.debug("Inside APISService class: LeadinsertStudent Method");
		StudentDetailsDTO studentDetailsDTO = null;
		StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		
		Double dueAmount = null;
		
		try
		{
			
			session = MyBatisManager.getInstance().getSession();

			int lastUserDetailId = new UserDetailsDAO().lastUserDetailId();
			studentLoginId = new StringBuilder();
			studentLoginId.append("LD").append(++lastUserDetailId);
			if (null != studentLoginId.toString() && !studentLoginId.toString().isEmpty()) {
				UserRoleDTO roleDTO = new UserRoleDTO();
				roleDTO.setName(UserTypeEnum.USER.getDisplayName());

				UserRoleDAO roleDAO = new UserRoleDAO();
				roleDTO = roleDAO.getUserRoleByName(roleDTO);

				UserDetailDTO userDetailDTO = new UserDetailDTO();
				String password = PasswordGeneratorService.getRandomAlphanumeric();
				userDetailDTO.setLoginId(studentLoginId.toString());
				userDetailDTO.setPassword(AESCipher.encrypt(password.getBytes()));
				userDetailDTO.setRoleId(roleDTO.getId());
				userDetailDTO.setUserType(UserTypeEnum.USER.getDisplayName());
				userDetailDTO.setIsActive("Y");

				// Insert User Details
				
				int userId = new UserDetailsDAO().insertUserDetail(session, userDetailDTO);

				if (userId < 0) {
					return EActionStatus.FAILURE;
				}

				// Insert Student Detail

				TrialStudentExtraDetailDTO.setMothercontactno(studentDTO.getMothercontactno());
				TrialStudentExtraDetailDTO.setMotheremailId(studentDTO.getMotheremailId());
				TrialStudentExtraDetailDTO.setMotherName(studentDTO.getMotherName());
				TrialStudentExtraDetailDTO.setStudentcontactNumber(studentDTO.getStudentcontactNumber());
				TrialStudentExtraDetailDTO.setStudentemailId(studentDTO.getStudentemailId());
				TrialStudentExtraDetailDTO.setFatherEmailId(studentDTO.getFatheremailId());
				TrialStudentExtraDetailDTO.setFathercontactno(studentDTO.getContactNumber());
				TrialStudentExtraDetailDTO.setFatherName(studentDTO.getFathername());

				studentDTO.setUserId(userId);
				
				studentDTO.setSource(studentDTO.getStudentType().name()+"_LeadPartner");
				

				
				

				if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
					studentDTO.setFatheremailId(studentDTO.getMotheremailId());
					studentDTO.setFathername(studentDTO.getMotherName());
				}
				if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
					studentDTO.setContactNumber(studentDTO.getMothercontactno());
				}

				studentDetailsDTO = studentDetailsDAO.insertStudent(session, studentDTO);
				// studentDetailsDAO.inserttrialStudent(session, studentDTO);

				SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();

				// sasedeve added by vyankatesh
				PaymentDTO paymentDTO = new PaymentDTO();
				// sasedeve added by vyankatesh
				
				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty())
				{
					seDetailsDTO.setVenue(studentDTO.getVenue());
				}
				else
				{
					seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
				}
				
				seDetailsDTO.setStudentId(studentDTO.getId());
				seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());

				// Vyankatesh edit add Trialstudent extra Field
				TrialStudentExtraDetailDTO.setStudentId(studentDetailsDTO.getId());

				boolean isTrial = false;
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					setSessionSchedule(studentDetailsDTO, seDetailsDTO);

				} else {
					isTrial = true;
					seDetailsDTO.setFacilitatorId(0);

				}
				// Insert Student Session Schedule
				int studentId = new SessionScheduleDetailsDAO().insertSessionSchedule(session, seDetailsDTO);
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);

				// sasedeve added by vyankatesh
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					
					
					if(studentDTO.getAgreedAmount()!=null && studentDTO.getPaidAmount()!=null)
					{
					
						dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());
	
						paymentDTO.setLoginId(studentLoginId.toString());
						paymentDTO.setStudentId(studentId);
						paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
						paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
						paymentDTO.setDueAmount(String.valueOf(dueAmount));
						new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
					}
				}
				// sasedeve added by vyankatesh

				boolean isNew = true;
				if(leadparent!=null)
				{
					leadparent.setLDID(studentLoginId.toString());
					leadparent.setStudentID(studentId);
					
					StudentCreation studentcreation=new StudentCreation();
					
					studentcreation.updateLeadParentStudentDetails(leadparent);
					
				}

				session.commit();
				if (userDetailDTO.getCreatedOn() != null) {
					auditDateStr = TimeUtil.getDateFromMillis(userDetailDTO.getCreatedOn().getTime(),
							TimeUtil.QUERY_DATE_FORMAT);
				}
				//EActionStatus.
				return EActionStatus.SUCCESS;
			}
			
		}
		catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return EActionStatus.FAILURE;
	}
	
	
	
	public LeadParentDTO insertStudentNew(StudentDetailsDTO studentDTO,LeadParentDTO leadparent)
	{
		OUT.debug("Inside APISService class: insertStudentNew Method");
		StudentDetailsDTO studentDetailsDTO = null;
		StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		
		Double dueAmount = null;
		
		try
		{
			
			session = MyBatisManager.getInstance().getSession();

			int lastUserDetailId = new UserDetailsDAO().lastUserDetailId();
			studentLoginId = new StringBuilder();
			studentLoginId.append("LD").append(++lastUserDetailId);
			if (null != studentLoginId.toString() && !studentLoginId.toString().isEmpty()) {
				UserRoleDTO roleDTO = new UserRoleDTO();
				roleDTO.setName(UserTypeEnum.USER.getDisplayName());

				UserRoleDAO roleDAO = new UserRoleDAO();
				roleDTO = roleDAO.getUserRoleByName(roleDTO);

				UserDetailDTO userDetailDTO = new UserDetailDTO();
				String password = PasswordGeneratorService.getRandomAlphanumeric();
				userDetailDTO.setLoginId(studentLoginId.toString());
				userDetailDTO.setPassword(AESCipher.encrypt(password.getBytes()));
				userDetailDTO.setRoleId(roleDTO.getId());
				userDetailDTO.setUserType(UserTypeEnum.USER.getDisplayName());
				userDetailDTO.setIsActive("Y");

				// Insert User Details
				
				int userId = new UserDetailsDAO().insertUserDetail(session, userDetailDTO);

				if (userId < 0) {
					return leadparent;
				}

				// Insert Student Detail

				TrialStudentExtraDetailDTO.setMothercontactno(studentDTO.getMothercontactno());
				TrialStudentExtraDetailDTO.setMotheremailId(studentDTO.getMotheremailId());
				TrialStudentExtraDetailDTO.setMotherName(studentDTO.getMotherName());
				TrialStudentExtraDetailDTO.setStudentcontactNumber(studentDTO.getStudentcontactNumber());
				TrialStudentExtraDetailDTO.setStudentemailId(studentDTO.getStudentemailId());
				TrialStudentExtraDetailDTO.setFatherEmailId(studentDTO.getFatheremailId());
				TrialStudentExtraDetailDTO.setFathercontactno(studentDTO.getContactNumber());
				TrialStudentExtraDetailDTO.setFatherName(studentDTO.getFathername());

				studentDTO.setUserId(userId);
				
				studentDTO.setSource(studentDTO.getStudentType().name()+"_LeadPartner");
				

				
				

				if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
					studentDTO.setFatheremailId(studentDTO.getMotheremailId());
					studentDTO.setFathername(studentDTO.getMotherName());
				}
				if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
					studentDTO.setContactNumber(studentDTO.getMothercontactno());
				}

				studentDetailsDTO = studentDetailsDAO.insertStudent(session, studentDTO);
				// studentDetailsDAO.inserttrialStudent(session, studentDTO);

				SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();

				// sasedeve added by vyankatesh
				PaymentDTO paymentDTO = new PaymentDTO();
				// sasedeve added by vyankatesh
				
				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty())
				{
					seDetailsDTO.setVenue(studentDTO.getVenue());
				}
				else
				{
					seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
				}
				
				seDetailsDTO.setStudentId(studentDTO.getId());
				seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());

				// Vyankatesh edit add Trialstudent extra Field
				TrialStudentExtraDetailDTO.setStudentId(studentDetailsDTO.getId());

				boolean isTrial = false;
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					setSessionSchedule(studentDetailsDTO, seDetailsDTO);

				} else {
					isTrial = true;
					seDetailsDTO.setFacilitatorId(0);

				}
				// Insert Student Session Schedule
				int studentId = new SessionScheduleDetailsDAO().insertSessionSchedule(session, seDetailsDTO);
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);

				// sasedeve added by vyankatesh
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					
					
					if(studentDTO.getAgreedAmount()!=null && studentDTO.getPaidAmount()!=null)
					{
					
						dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());
	
						paymentDTO.setLoginId(studentLoginId.toString());
						paymentDTO.setStudentId(studentId);
						paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
						paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
						paymentDTO.setDueAmount(String.valueOf(dueAmount));
						new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
					}
				}
				// sasedeve added by vyankatesh

				boolean isNew = true;
				
				int leadid=0;
				if(leadparent!=null)
				{
					leadparent.setLDID(studentLoginId.toString());
					leadparent.setStudentID(studentId);
					
					StudentCreation studentcreation=new StudentCreation();
					
					leadid=studentcreation.InserNewLeadStudentDetails(leadparent);
					leadparent.setId(leadid);
				}

				session.commit();
				if (userDetailDTO.getCreatedOn() != null) {
					auditDateStr = TimeUtil.getDateFromMillis(userDetailDTO.getCreatedOn().getTime(),
							TimeUtil.QUERY_DATE_FORMAT);
				}
				//EActionStatus.
				return leadparent;
			}
			
		}
		catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return leadparent;
	}
	
	public LeadParentDTO insertChildNew(StudentDetailsDTO studentDTO,LeadParentDTO leadparent)
	{
		OUT.debug("Inside APISService class: insertStudentNew Method");
		StudentDetailsDTO studentDetailsDTO = null;
		StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		
		Double dueAmount = null;
		
		try
		{
			
			session = MyBatisManager.getInstance().getSession();

			int lastUserDetailId = new UserDetailsDAO().lastUserDetailId();
			studentLoginId = new StringBuilder();
			studentLoginId.append("LD").append(++lastUserDetailId);
			if (null != studentLoginId.toString() && !studentLoginId.toString().isEmpty()) {
				UserRoleDTO roleDTO = new UserRoleDTO();
				roleDTO.setName(UserTypeEnum.USER.getDisplayName());

				UserRoleDAO roleDAO = new UserRoleDAO();
				roleDTO = roleDAO.getUserRoleByName(roleDTO);

				UserDetailDTO userDetailDTO = new UserDetailDTO();
				String password = PasswordGeneratorService.getRandomAlphanumeric();
				userDetailDTO.setLoginId(studentLoginId.toString());
				userDetailDTO.setPassword(AESCipher.encrypt(password.getBytes()));
				userDetailDTO.setRoleId(roleDTO.getId());
				userDetailDTO.setUserType(UserTypeEnum.USER.getDisplayName());
				userDetailDTO.setIsActive("Y");

				// Insert User Details
				
				int userId = new UserDetailsDAO().insertUserDetail(session, userDetailDTO);

				if (userId < 0) {
					return leadparent;
				}

				// Insert Student Detail

				TrialStudentExtraDetailDTO.setMothercontactno(studentDTO.getMothercontactno());
				TrialStudentExtraDetailDTO.setMotheremailId(studentDTO.getMotheremailId());
				TrialStudentExtraDetailDTO.setMotherName(studentDTO.getMotherName());
				TrialStudentExtraDetailDTO.setStudentcontactNumber(studentDTO.getStudentcontactNumber());
				TrialStudentExtraDetailDTO.setStudentemailId(studentDTO.getStudentemailId());
				TrialStudentExtraDetailDTO.setFatherEmailId(studentDTO.getFatheremailId());
				TrialStudentExtraDetailDTO.setFathercontactno(studentDTO.getContactNumber());
				TrialStudentExtraDetailDTO.setFatherName(studentDTO.getFathername());

				studentDTO.setUserId(userId);
				
				studentDTO.setSource(studentDTO.getStudentType().name()+"_LeadPartner");
				

				
				

				if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
					studentDTO.setFatheremailId(studentDTO.getMotheremailId());
					studentDTO.setFathername(studentDTO.getMotherName());
				}
				if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
					studentDTO.setContactNumber(studentDTO.getMothercontactno());
				}

				studentDetailsDTO = studentDetailsDAO.insertStudent(session, studentDTO);
				// studentDetailsDAO.inserttrialStudent(session, studentDTO);

				SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();

				// sasedeve added by vyankatesh
				PaymentDTO paymentDTO = new PaymentDTO();
				// sasedeve added by vyankatesh
				
				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty())
				{
					seDetailsDTO.setVenue(studentDTO.getVenue());
				}
				else
				{
					seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
				}
				
				seDetailsDTO.setStudentId(studentDTO.getId());
				seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());

				// Vyankatesh edit add Trialstudent extra Field
				TrialStudentExtraDetailDTO.setStudentId(studentDetailsDTO.getId());

				boolean isTrial = false;
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					setSessionSchedule(studentDetailsDTO, seDetailsDTO);

				} else {
					isTrial = true;
					seDetailsDTO.setFacilitatorId(0);

				}
				// Insert Student Session Schedule
				int studentId = new SessionScheduleDetailsDAO().insertSessionSchedule(session, seDetailsDTO);
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);

				// sasedeve added by vyankatesh
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					
					
					if(studentDTO.getAgreedAmount()!=null && studentDTO.getPaidAmount()!=null)
					{
					
						dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());
	
						paymentDTO.setLoginId(studentLoginId.toString());
						paymentDTO.setStudentId(studentId);
						paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
						paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
						paymentDTO.setDueAmount(String.valueOf(dueAmount));
						new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
					}
				}
				// sasedeve added by vyankatesh

				boolean isNew = true;
				
				int leadid=0;
				if(leadparent!=null)
				{
					leadparent.setLDID(studentLoginId.toString());
					leadparent.setStudentID(studentId);
					
					StudentCreation studentcreation=new StudentCreation();
					
					leadid=studentcreation.InserNewLeadChildDetails(leadparent);
					String Token=new EncryptionDecryptionData().Encrypt(String.valueOf(leadid));
					
					LeadParentDTO UpdateLeadToken=new LeadParentDTO();
					UpdateLeadToken.setId(leadid);
					UpdateLeadToken.setToken(Token);
					APISService apiservice=new APISService();
					apiservice.UpdateLeadToken(UpdateLeadToken);
					
					leadparent.setId(leadid);
					leadparent.setToken(Token);
				}

				session.commit();
				if (userDetailDTO.getCreatedOn() != null) {
					auditDateStr = TimeUtil.getDateFromMillis(userDetailDTO.getCreatedOn().getTime(),
							TimeUtil.QUERY_DATE_FORMAT);
				}
				//EActionStatus.
				return leadparent;
			}
			
		}
		catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return leadparent;
	}
	
	
	
	
	public void UpdateLesdStudent(int leadparentid)
	{
		OUT.info("APISService : UpdateLesdStudent");
		
		try
		{
			StudentCreation testToken=new StudentCreation();
			
			testToken.LeadStudenttestupdatedTaken(leadparentid);
			
			
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}
	
	
	
	
	
	
	
	public void UpdateStudentTestTaken(int leadparentid)
	{
		OUT.info("APISService : UpdateStudentTestToken");
		
		try
		{
			StudentCreation testToken=new StudentCreation();
			
			testToken.StudenttestupdatedTaken(leadparentid);
			
			
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}
	
	
	public void AppointmentBooking(LeadParentDTO leadparentappointbooking)
	{
		OUT.info("APISService : AppointmentBooking");
		
		try
		{
			StudentCreation appointment=new StudentCreation();
			
			appointment.Appointmentbooking(leadparentappointbooking);
			
			
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}
	
	
	public LeadParentDTO insertSchoolChildNew(StudentDetailsDTO studentDTO, LeadParentDTO leadparent) {

		OUT.debug("Inside APISService class: insertStudentNew Method");
		StudentDetailsDTO studentDetailsDTO = null;
		StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		
		Double dueAmount = null;
		
		try
		{
			
			session = MyBatisManager.getInstance().getSession();

			int lastUserDetailId = new UserDetailsDAO().lastUserDetailId();
			studentLoginId = new StringBuilder();
			studentLoginId.append("LD").append(++lastUserDetailId);
			if (null != studentLoginId.toString() && !studentLoginId.toString().isEmpty()) {
				UserRoleDTO roleDTO = new UserRoleDTO();
				roleDTO.setName(UserTypeEnum.USER.getDisplayName());

				UserRoleDAO roleDAO = new UserRoleDAO();
				roleDTO = roleDAO.getUserRoleByName(roleDTO);

				UserDetailDTO userDetailDTO = new UserDetailDTO();
				String password = PasswordGeneratorService.getRandomAlphanumeric();
				userDetailDTO.setLoginId(studentLoginId.toString());
				userDetailDTO.setPassword(AESCipher.encrypt(password.getBytes()));
				userDetailDTO.setRoleId(roleDTO.getId());
				userDetailDTO.setUserType(UserTypeEnum.USER.getDisplayName());
				userDetailDTO.setIsActive("Y");

				// Insert User Details
				
				int userId = new UserDetailsDAO().insertUserDetail(session, userDetailDTO);

				if (userId < 0) {
					return leadparent;
				}

				// Insert Student Detail

				TrialStudentExtraDetailDTO.setMothercontactno(studentDTO.getMothercontactno());
				TrialStudentExtraDetailDTO.setMotheremailId(studentDTO.getMotheremailId());
				TrialStudentExtraDetailDTO.setMotherName(studentDTO.getMotherName());
				TrialStudentExtraDetailDTO.setStudentcontactNumber(studentDTO.getStudentcontactNumber());
				TrialStudentExtraDetailDTO.setStudentemailId(studentDTO.getStudentemailId());
				TrialStudentExtraDetailDTO.setFatherEmailId(studentDTO.getFatheremailId());
				TrialStudentExtraDetailDTO.setFathercontactno(studentDTO.getContactNumber());
				TrialStudentExtraDetailDTO.setFatherName(studentDTO.getFathername());

				studentDTO.setUserId(userId);
				
				studentDTO.setSource(studentDTO.getStudentType().name()+"_LeadPartnerSchool");
				

				
				

				if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
					studentDTO.setFatheremailId(studentDTO.getMotheremailId());
					studentDTO.setFathername(studentDTO.getMotherName());
				}
				if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
					studentDTO.setContactNumber(studentDTO.getMothercontactno());
				}

				studentDetailsDTO = studentDetailsDAO.insertStudent(session, studentDTO);
				// studentDetailsDAO.inserttrialStudent(session, studentDTO);

				SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();

				// sasedeve added by vyankatesh
				PaymentDTO paymentDTO = new PaymentDTO();
				// sasedeve added by vyankatesh
				
				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty())
				{
					seDetailsDTO.setVenue(studentDTO.getVenue());
				}
				else
				{
					seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
				}
				
				seDetailsDTO.setStudentId(studentDTO.getId());
				seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());

				// Vyankatesh edit add Trialstudent extra Field
				TrialStudentExtraDetailDTO.setStudentId(studentDetailsDTO.getId());

				boolean isTrial = false;
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					setSessionSchedule(studentDetailsDTO, seDetailsDTO);

				} else {
					isTrial = true;
					seDetailsDTO.setFacilitatorId(0);

				}
				// Insert Student Session Schedule
				int studentId = new SessionScheduleDetailsDAO().insertSessionSchedule(session, seDetailsDTO);
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);

				// sasedeve added by vyankatesh
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					
					
					if(studentDTO.getAgreedAmount()!=null && studentDTO.getPaidAmount()!=null)
					{
					
						dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());
	
						paymentDTO.setLoginId(studentLoginId.toString());
						paymentDTO.setStudentId(studentId);
						paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
						paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
						paymentDTO.setDueAmount(String.valueOf(dueAmount));
						new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
					}
				}
				// sasedeve added by vyankatesh

				boolean isNew = true;
				
				int leadid=0;
				if(leadparent!=null)
				{
					leadparent.setLDID(studentLoginId.toString());
					leadparent.setStudentID(studentId);
					
					StudentCreation studentcreation=new StudentCreation();
					
					leadid=studentcreation.InserNewLeadChildDetails(leadparent);
					String Token=new EncryptionDecryptionData().Encrypt(String.valueOf(leadid));
					
					LeadParentDTO UpdateLeadToken=new LeadParentDTO();
					UpdateLeadToken.setId(leadid);
					UpdateLeadToken.setToken(Token);
					APISService apiservice=new APISService();
					apiservice.UpdateLeadToken(UpdateLeadToken);
					
					leadparent.setId(leadid);
					leadparent.setToken(Token);
				}

				session.commit();
				if (userDetailDTO.getCreatedOn() != null) {
					auditDateStr = TimeUtil.getDateFromMillis(userDetailDTO.getCreatedOn().getTime(),
							TimeUtil.QUERY_DATE_FORMAT);
				}
				//EActionStatus.
				return leadparent;
			}
			
		}
		catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return leadparent;
	
	}
	
	
	public LeadParentDTO insertSchoolStudentNew(StudentDetailsDTO studentDTO,LeadParentDTO leadparent)
	{
		OUT.debug("Inside APISService class: insertStudentNew Method");
		StudentDetailsDTO studentDetailsDTO = null;
		StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		
		Double dueAmount = null;
		
		try
		{
			
			session = MyBatisManager.getInstance().getSession();

			int lastUserDetailId = new UserDetailsDAO().lastUserDetailId();
			studentLoginId = new StringBuilder();
			studentLoginId.append("LD").append(++lastUserDetailId);
			if (null != studentLoginId.toString() && !studentLoginId.toString().isEmpty()) {
				UserRoleDTO roleDTO = new UserRoleDTO();
				roleDTO.setName(UserTypeEnum.USER.getDisplayName());

				UserRoleDAO roleDAO = new UserRoleDAO();
				roleDTO = roleDAO.getUserRoleByName(roleDTO);

				UserDetailDTO userDetailDTO = new UserDetailDTO();
				String password = PasswordGeneratorService.getRandomAlphanumeric();
				userDetailDTO.setLoginId(studentLoginId.toString());
				userDetailDTO.setPassword(AESCipher.encrypt(password.getBytes()));
				userDetailDTO.setRoleId(roleDTO.getId());
				userDetailDTO.setUserType(UserTypeEnum.USER.getDisplayName());
				userDetailDTO.setIsActive("Y");

				// Insert User Details
				
				int userId = new UserDetailsDAO().insertUserDetail(session, userDetailDTO);

				if (userId < 0) {
					return leadparent;
				}

				// Insert Student Detail

				TrialStudentExtraDetailDTO.setMothercontactno(studentDTO.getMothercontactno());
				TrialStudentExtraDetailDTO.setMotheremailId(studentDTO.getMotheremailId());
				TrialStudentExtraDetailDTO.setMotherName(studentDTO.getMotherName());
				TrialStudentExtraDetailDTO.setStudentcontactNumber(studentDTO.getStudentcontactNumber());
				TrialStudentExtraDetailDTO.setStudentemailId(studentDTO.getStudentemailId());
				TrialStudentExtraDetailDTO.setFatherEmailId(studentDTO.getFatheremailId());
				TrialStudentExtraDetailDTO.setFathercontactno(studentDTO.getContactNumber());
				TrialStudentExtraDetailDTO.setFatherName(studentDTO.getFathername());

				studentDTO.setUserId(userId);
				
				studentDTO.setSource(studentDTO.getStudentType().name()+"_LeadPartnerSchool");
				

				
				

				if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
					studentDTO.setFatheremailId(studentDTO.getMotheremailId());
					studentDTO.setFathername(studentDTO.getMotherName());
				}
				if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
					studentDTO.setContactNumber(studentDTO.getMothercontactno());
				}

				studentDetailsDTO = studentDetailsDAO.insertStudent(session, studentDTO);
				// studentDetailsDAO.inserttrialStudent(session, studentDTO);

				SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();

				// sasedeve added by vyankatesh
				PaymentDTO paymentDTO = new PaymentDTO();
				// sasedeve added by vyankatesh
				
				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty())
				{
					seDetailsDTO.setVenue(studentDTO.getVenue());
				}
				else
				{
					seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
				}
				
				seDetailsDTO.setStudentId(studentDTO.getId());
				seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());

				// Vyankatesh edit add Trialstudent extra Field
				TrialStudentExtraDetailDTO.setStudentId(studentDetailsDTO.getId());

				boolean isTrial = false;
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					setSessionSchedule(studentDetailsDTO, seDetailsDTO);

				} else {
					isTrial = true;
					seDetailsDTO.setFacilitatorId(0);

				}
				// Insert Student Session Schedule
				int studentId = new SessionScheduleDetailsDAO().insertSessionSchedule(session, seDetailsDTO);
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);

				// sasedeve added by vyankatesh
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					
					
					if(studentDTO.getAgreedAmount()!=null && studentDTO.getPaidAmount()!=null)
					{
					
						dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());
	
						paymentDTO.setLoginId(studentLoginId.toString());
						paymentDTO.setStudentId(studentId);
						paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
						paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
						paymentDTO.setDueAmount(String.valueOf(dueAmount));
						new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
					}
				}
				// sasedeve added by vyankatesh

				boolean isNew = true;
				
				int leadid=0;
				if(leadparent!=null)
				{
					leadparent.setLDID(studentLoginId.toString());
					leadparent.setStudentID(studentId);
					
					StudentCreation studentcreation=new StudentCreation();
					
					leadid=studentcreation.InserNewLeadStudentDetails(leadparent);
					leadparent.setId(leadid);
				}

				session.commit();
				if (userDetailDTO.getCreatedOn() != null) {
					auditDateStr = TimeUtil.getDateFromMillis(userDetailDTO.getCreatedOn().getTime(),
							TimeUtil.QUERY_DATE_FORMAT);
				}
				//EActionStatus.
				return leadparent;
			}
			
		}
		catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return leadparent;
	}

}
