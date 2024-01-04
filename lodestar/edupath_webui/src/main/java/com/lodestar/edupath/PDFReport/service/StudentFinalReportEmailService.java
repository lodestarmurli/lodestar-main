package com.lodestar.edupath.PDFReport.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.finalsummary.FinalSummaryAction;
import com.lodestar.edupath.finalsummary.bean.ReportSummaryBean;
import com.lodestar.edupath.finalsummary.service.FinalSummaryService;
import com.lodestar.edupath.studentinfo.StudentInfoService;
import com.lodestar.edupath.util.ApplicationProperties;
import com.lodestar.edupath.util.PasswordGeneratorService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class StudentFinalReportEmailService  extends BaseAction implements Action,ModelDriven<ReportSummaryBean>{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(FinalSummaryAction.class);
	private ReportSummaryBean	bean				= new ReportSummaryBean();
	private List<StudentTUMDTO> studentTUMList = new ArrayList<StudentTUMDTO>();
	private StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
	private StudentCGTResult studentCGTResult = new StudentCGTResult();
	private Map<String, String> occupIndusMap = new HashMap<String, String>();
	private int studentId;
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	private GeneratePDFReportFile generatePDFReportFileobj = new GeneratePDFReportFile();
	private static final SimpleDateFormat	DATE_FORMAT		= new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
	
	public String execute()
	{
		
		StudentDetailsDTO studentDetailsByUserId;
			try {
				
				
					studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(getUserSessionObject().getId());
				
				return createPDFReport(studentDetailsByUserId);
				
			} catch (Exception e) {
				
				OUT.error(ApplicationConstants.EXCEPTION, e);
				return "error";
			}
		
	}
	
	public String CreatePDFFromReviewer(int userId) {
		StudentDetailsDTO studentDetailsByUserId;
		try {
			if(new FinalSummaryService().checkFinalFeedBackAvailable(userId))
			{
			
			   studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		       return createPDFReport(studentDetailsByUserId);
			}
			else
			{
				return "error";
			}
		
	} catch (Exception e) {
		
		OUT.error(ApplicationConstants.EXCEPTION, e);
		return "error";
	}
	}
	
	private String createPDFReport(StudentDetailsDTO studentDetailsByUserId) {
		if(studentDetailsByUserId!=null)
		{
			int userId= studentDetailsByUserId.getUserId();
		
		
		
		OUT.debug("Show summary report for student user Id: {}", userId);
		try
		{
			
			
			setStudentId(studentDetailsByUserId.getId());
			
			
			
			bean = new FinalSummaryService().getSummaryReportForStudent(userId);
			
			
			
			studentDetailsDTO = new StudentInfoService().getStudentDetailsById(studentId);
			
			List<StudentTUMDTO> tumList = null;
			Map<Long, String> studentQuesAnsMap = null;
			if (null != studentDetailsDTO) {

				tumList = studentDetailsDTO.getStudentTUMList();
				studentQuesAnsMap = studentDetailsDTO.getStudentQuesAnsMap();
			}
			if (tumList != null) {
				for (StudentTUMDTO studentTUMDTO : tumList) {
					studentQuesAnsMap.put((long) studentTUMDTO.getQuestionSlNo(), studentTUMDTO.getAnswer());
				}
			}

			studentCGTResult = new StudentInfoService().getStudentCGTResultByStudentId(studentId);
			if (studentCGTResult != null) {
				String occupationIds = studentCGTResult.getOccupationIds();
				if (occupationIds != null) {
					String[] occupaIdArray = occupationIds.split(",");
					for (String occupid : occupaIdArray) {
						OccupationDTO occupationDTO = new StudentInfoService()
								.getOccupationDetailsById(Integer.parseInt(occupid.trim()));
						if (occupationDTO != null) {
							occupIndusMap.put(occupationDTO.getName(), occupationDTO.getIndustryName());
						}
					}
				}
			}
			
			
			ByteArrayOutputStream baos= generatePDFReportFileobj.GenerateStudentFinalReport(bean,studentCGTResult,occupIndusMap);
			if (baos != null) {
			String	fileName = properties.getProperty("com.edupath.report.FinalReport.name");
				
				String newFolderP = null;
				String newFolderS = null;
				GlobalSettingDTO globalDTO = new GlobalSettingDTO();
				globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.EMAIL_FOLDER_PATH.getProperty());
				globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
				if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
				{
					String currentDir = globalDTO.getPropertyValue();
					
					if((studentDetailsByUserId.getFatherEmailId()!=null && !studentDetailsByUserId.getFatherEmailId().trim().isEmpty()) || (studentDetailsByUserId.getMotheremailId()!=null && !studentDetailsByUserId.getMotheremailId().trim().isEmpty()))
					{
						newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDetailsByUserId.getId()+"_P";
						File dirsP = new File(currentDir + newFolderP);
						if (!dirsP.exists())
						{
							dirsP.mkdirs();
						}
					}
					else
					{
						newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDetailsByUserId.getId()+"_P";
						File dirsP = new File(currentDir + newFolderP);
						if (!dirsP.exists())
						{
							dirsP.mkdirs();
						}
					}
					
					if(studentDetailsByUserId.getStudentemailId()!=null && !studentDetailsByUserId.getStudentemailId().trim().isEmpty())
					{
						newFolderS = DATE_FORMAT.format(new Date())+"_"+studentDetailsByUserId.getId()+"_S";
						File dirsS = new File(currentDir + newFolderS);
						if (!dirsS.exists())
						{
							dirsS.mkdirs();
						}
					}
					
					
					if(newFolderP!=null)
					{
						String filePath = currentDir + newFolderP + File.separator + fileName;
						FileOutputStream fos = new FileOutputStream (new File(filePath));
						baos.writeTo(fos);
						fos.close();
						baos.close();
					
					}
					
					if(newFolderS!=null)
					{
						
						String filePath = currentDir + newFolderS + File.separator + fileName;
						FileOutputStream fos = new FileOutputStream (new File(filePath));
						baos.writeTo(fos);
						fos.close();
						baos.close();
					
					
					}
					
					sendEmailFinalReport(2,studentDetailsByUserId,newFolderP,newFolderS);
				}
				
				
				
				
			} else {
				return "error";
			}
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return "error";
		}
		return "success";
		}
		else
		{
			return "error";
		}
	}
	
	
	
	
	private void sendEmailFinalReport(int roleTypeId,StudentDetailsDTO studentDetailsDTO,String filePathP,String filePathS) throws Exception
	{
		StringBuilder headerMessage = new StringBuilder();
		headerMessage.append("Your Report Details are as below");

			
		String StudentEmailID=null;
		String ParentEmailID=null;
		
		int checkFatherEmailid=0,CheckMotheremailid=0;
		
		
		
		if(studentDetailsDTO.getFatherEmailId()!=null && !studentDetailsDTO.getFatherEmailId().trim().isEmpty())
		{
			
			ParentEmailID=studentDetailsDTO.getFatherEmailId();
			checkFatherEmailid=1;
		}
		
		if(studentDetailsDTO.getMotheremailId()!=null && !studentDetailsDTO.getMotheremailId().trim().isEmpty())
		{
			CheckMotheremailid=1;
			if(ParentEmailID!=null)
			{
				ParentEmailID=ParentEmailID+","+studentDetailsDTO.getMotheremailId();
			}
			else
			{
				ParentEmailID=studentDetailsDTO.getMotheremailId();
			}
			
		}
		
		if(checkFatherEmailid==0 && CheckMotheremailid==0)
		{
			ParentEmailID=studentDetailsDTO.getFatheremailId();
		}
		
		if(studentDetailsDTO.getStudentemailId()!=null && !studentDetailsDTO.getStudentemailId().trim().isEmpty())
		{
			StudentEmailID=studentDetailsDTO.getStudentemailId();
		}
		
		if(StudentEmailID!=null)
		{
			PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), StudentEmailID, roleTypeId,
					NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_FINAL_REPORT.name(), filePathS, studentDetailsDTO.getFathername());
		}
		
		if(ParentEmailID!=null)
		{
			PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), ParentEmailID, roleTypeId,
					NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_FINAL_REPORT_PARENT.name(), filePathP, studentDetailsDTO.getFathername());
		}
		
		
	
		
	}


	
	
	@Override
	public ReportSummaryBean getModel()
	{
		return bean;
	}

	public ReportSummaryBean getBean()
	{
		return bean;
	}

	public void setBean(ReportSummaryBean bean)
	{
		this.bean = bean;
	}



	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<StudentTUMDTO> getStudentTUMList() {
		return studentTUMList;
	}

	public void setStudentTUMList(List<StudentTUMDTO> studentTUMList) {
		this.studentTUMList = studentTUMList;
	}

	public StudentDetailsDTO getStudentDetailsDTO() {
		return studentDetailsDTO;
	}

	public void setStudentDetailsDTO(StudentDetailsDTO studentDetailsDTO) {
		this.studentDetailsDTO = studentDetailsDTO;
	}

	public StudentCGTResult getStudentCGTResult() {
		return studentCGTResult;
	}

	public void setStudentCGTResult(StudentCGTResult studentCGTResult) {
		this.studentCGTResult = studentCGTResult;
	}

	public Map<String, String> getOccupIndusMap() {
		return occupIndusMap;
	}

	public void setOccupIndusMap(Map<String, String> occupIndusMap) {
		this.occupIndusMap = occupIndusMap;
	}


}
