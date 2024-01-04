package com.lodestar.edupath.student.action.trial;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.GenderTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.student.bean.StudentBean;
import com.lodestar.edupath.student.service.StudentDetailService;
import com.opensymphony.xwork2.ModelDriven;

public class TrialStudentAction extends BaseAction implements ModelDriven<StudentBean>
{

	private static final long		serialVersionUID	= 1L;

	private final static Logger		OUT					= LoggerFactory.getLogger(TrialStudentAction.class);

	private List<CityDTO>			cityList			= new ArrayList<CityDTO>();
	private List<ClassDTO>			classList			= new ArrayList<ClassDTO>();
	private List<SchoolDTO>			schoolList			= new ArrayList<SchoolDTO>();
	private List<GenderTypeEnum>	genderList			= new ArrayList<GenderTypeEnum>();

	private StudentBean				bean				= new StudentBean();
	private StudentDetailService	service;
	private String					webUrl;

	public String execute()
	{
		try
		{
			service = new StudentDetailService();
			authKey = service.getTrialAuthKey();
			recaptchaSiteKey = service.getRecaptchaKey();
			if (null == authKey || authKey.isEmpty())
			{
				OUT.error("DB Auth Key not exit..");
				addActionError("Invalid Request.");
				return "INVALID_REQUEST";
			}
			fieldRequired();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			addActionError("Inavlid Request.");
			return "INVALID_REQUEST";
		}
		return SUCCESS;
	}

	public String doAdd()
	{
		OUT.debug("Trial Student Auth Key : {}", authKey);
		if (null == authKey || authKey.isEmpty())
		{
			OUT.error("Auth Key not exit in request..");
			addActionError("Invalid Request.");
			return "INVALID_REQUEST";
		}
		boolean isSuccss = false;
		try
		{
			service = new StudentDetailService();
			String dbAuthKey = service.getTrialAuthKey();
			if (null == dbAuthKey || dbAuthKey.isEmpty())
			{
				OUT.error("DB Auth Key not exit");
				addActionError("Invalid Request.");
				return "INVALID_REQUEST";
			}
			if (!dbAuthKey.equals(authKey))
			{
				OUT.error("Auth Key and DB Auth key not match in request..");
				addActionError("Invalid Request.");
				return "INVALID_REQUEST";
			}
			StudentDetailsDTO studentDTO = new StudentDetailsDTO();
			BeanUtils.copyProperties(studentDTO, bean);
			studentDTO.setStudentType(StudentTypeEnum.TRIAL);
			studentDTO.setComputerFacility(false);
			studentDTO.setTestTaken(StudentTypeEnum.StudentTestTakenEnum.NO.name());
			EActionStatus status = service.insertStudent(studentDTO);
			if (status == EActionStatus.FAILURE)
			{
				addActionError("can't add due to Exception");
				return ERROR;
			}
			isSuccss = true;
			addActionMessage(getText("com.edupath.action.add.msg.successful", new String[]
			{
				bean.getName()
			}));
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			addActionError("can't add due to Exception");
			return ERROR;
		}
		finally
		{
			fieldRequired();
			if (isSuccss)
			{
				webUrl = service.getAppWebUrl();
				StringBuilder auditMessage = new StringBuilder();
				auditMessage.append("Trial Student ").append(bean.getName())
						.append(" details has been created successfully and created on : " + service.getAuditDateStr());
				insertAudit(auditMessage.toString());
			}
		}
		return "ADDSUCCESS";
	}

	private void insertAudit(String auditMessage)
	{
		String ip = request.getRemoteAddr();
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1"))
		{
			InetAddress inetAddress;
			try
			{
				inetAddress = InetAddress.getLocalHost();
				String ipAddress = inetAddress.getHostAddress();
				ip = ipAddress;
			}
			catch (Exception e)
			{
				// do nothing
			}
		}
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.MANAGE_TRIAL_STUDENT, auditMessage, ip);
	}

	private void fieldRequired()
	{
		cityList = service.getCityList();
		classList = service.getClassList();
		schoolList = service.getSchoolList();
		genderList.addAll(Arrays.asList(GenderTypeEnum.values()));
	}

	@Override
	public StudentBean getModel()
	{
		return bean;
	}

	public StudentBean getBean()
	{
		return bean;
	}

	public void setBean(StudentBean bean)
	{
		this.bean = bean;
	}

	public List<CityDTO> getCityList()
	{
		return cityList;
	}

	public List<ClassDTO> getClassList()
	{
		return classList;
	}

	public List<GenderTypeEnum> getGenderList()
	{
		return genderList;
	}

	public List<SchoolDTO> getSchoolList()
	{
		return schoolList;
	}

	public String getWebUrl()
	{
		return webUrl;
	}
}
