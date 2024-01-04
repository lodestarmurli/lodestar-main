package com.lodestar.edupath.forgotpassword.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.forgotpassword.ForgotPasswordDAO;
import com.lodestar.edupath.dataaccessobject.dao.subadmin.SubAdminDAO;
import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.ForgotPasswordRequestDTO;
import com.lodestar.edupath.datatransferobject.dto.SubAdminDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class ForgotPasswordService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ForgotPasswordService.class);

	public boolean validateEmailId(String emailId)
	{
		boolean isResult = false;
		OUT.debug("ForgotPasswordService : checking email id exist");
		try
		{
			ForgotPasswordRequestDTO passwordRequestDTO = new ForgotPasswordRequestDTO();
			FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDTO();
			facilitatorDetailsDTO.setEmailId(emailId);
			facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorDetailsByEmailId(facilitatorDetailsDTO);
			if (facilitatorDetailsDTO != null)
			{
				isResult = setValueToPasswordRequestDTO(passwordRequestDTO, facilitatorDetailsDTO.getUserId(), facilitatorDetailsDTO.getName(), facilitatorDetailsDTO.getEmailId());
				if (isResult)
				{
					AuditTrailLogger.addAuditInfo(ModuleNameEnum.FORGOT_PASSWORD, "reset password requested", facilitatorDetailsDTO.getName());
				}
			}

			SubAdminDTO subAdminDTO = new SubAdminDTO();
			subAdminDTO.setEmailId(emailId);
			subAdminDTO = new SubAdminDAO().getSubAdminByEmailId(subAdminDTO);
			if (subAdminDTO != null)
			{
				isResult = setValueToPasswordRequestDTO(passwordRequestDTO, subAdminDTO.getUserId(), subAdminDTO.getName(), subAdminDTO.getEmailId());
				if (isResult)
				{
					AuditTrailLogger.addAuditInfo(ModuleNameEnum.FORGOT_PASSWORD, "reset password requested", subAdminDTO.getName());
				}
			}

			StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
			studentDetailsDTO.setFatheremailId(emailId);
			List<StudentDetailsDTO> studentDetailsListByEmailId = new StudentDetailsDAO().getAllStudentDetailsListByEmailId(studentDetailsDTO);
			if (studentDetailsListByEmailId.size()!=0)
			{
				for (StudentDetailsDTO studentDetailsDTO2 : studentDetailsListByEmailId)
				{
					isResult = setValueToPasswordRequestDTO(passwordRequestDTO, studentDetailsDTO2.getUserId(), studentDetailsDTO2.getFathername(), studentDetailsDTO2.getFatheremailId());
					if (isResult)
					{
						AuditTrailLogger.addAuditInfo(ModuleNameEnum.FORGOT_PASSWORD, "reset password requested", studentDetailsDTO2.getName());
					}
				}
			}
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return isResult;
	}

	private boolean setValueToPasswordRequestDTO(ForgotPasswordRequestDTO passwordRequestDTO, int id, String userName, String emailId) throws Exception
	{
		passwordRequestDTO.setUserId(id);
		passwordRequestDTO.setCode(PasswordGeneratorService.getRandomNumeric());
		passwordRequestDTO.setStartTime(new Date());
		passwordRequestDTO.setEndTime(getEndDate());
		passwordRequestDTO.setUsed(false);

		int insertedId = new ForgotPasswordDAO().insertForgotPasswordDetails(passwordRequestDTO);
		if (insertedId > 0)
		{
			PasswordGeneratorService.sendNewNotification(null, userName, emailId, 0, NotificationConstant.MessageTemplateNameAndType.FORGOT_PASSWORD.name(), passwordRequestDTO.getCode());
			return true;
		}
		return false;
	}

	private Date getEndDate()
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, ApplicationConstants.ADD_HOURS);
		return c.getTime();
	}

	public ForgotPasswordRequestDTO verifyCode(String code)
	{
		ForgotPasswordRequestDTO passwordRequestDTO = new ForgotPasswordRequestDTO();
		passwordRequestDTO.setCode(code);
		try
		{
			passwordRequestDTO = new ForgotPasswordDAO().getDetailsByCode(passwordRequestDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return passwordRequestDTO;
	}

	public boolean updateUserDetailsById(int userId, String password)
	{
		SqlSession session = null;
		boolean isUpdated = false;
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setId(userId);
		try
		{
			userDetailDTO.setPassword(AESCipher.encrypt(password.getBytes()));
			session = MyBatisManager.getInstance().getSession();
			int id = new UserDetailsDAO().updatePasswordForUserDetailsByid(session, userDetailDTO);
			if (id>0)
			{
				ForgotPasswordRequestDTO passwordRequestDTO = new ForgotPasswordRequestDTO();
				passwordRequestDTO.setUserId(userId);
				isUpdated = new ForgotPasswordDAO().updateForgotPasswordById(session, passwordRequestDTO);
			}
			session.commit();
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error("Exception ", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return isUpdated;
	}
	
}
