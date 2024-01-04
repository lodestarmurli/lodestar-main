package com.lodestar.edupath.APIS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.util.EncryptionDecryptionData;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class LeadStudentCreation extends BaseAction{

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(LeadStudentCreation.class);
	
	private String   								Status="ERROR";
	private String				srname;
	private String				sremailid;
	private int					srclassid;
	private String				srtoken;
	private String				srgender;
	private String  			srpno;
	
	private String  			prpno;
	private String  			premail;
	
	private int id;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrpno() {
		return prpno;
	}
	public void setPrpno(String prpno) {
		this.prpno = prpno;
	}
	public String getPremail() {
		return premail;
	}
	public void setPremail(String premail) {
		this.premail = premail;
	}
	public String getSrpno() {
		return srpno;
	}
	public void setSrpno(String srpno) {
		this.srpno = srpno;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	private String getSrname() {
		return srname;
	}
	public void setSrname(String srname) {
		this.srname = srname;
	}
	private String getSremailid() {
		return sremailid;
	}
	public void setSremailid(String sremailid) {
		this.sremailid = sremailid;
	}
	private int getSrclassid() {
		return srclassid;
	}
	public void setSrclassid(int srclassid) {
		this.srclassid = srclassid;
	}
	public String getSrtoken() {
		return srtoken;
	}
	public void setSrtoken(String srtoken) {
		this.srtoken = srtoken;
	}
	private String getSrgender() {
		return srgender;
	}
	public void setSrgender(String srgender) {
		this.srgender = srgender;
	}
	public String execute()
	{
		OUT.debug("Inside LeadStudentCreation class : execute method");
		OUT.debug("srtoken===>"+srtoken);
		OUT.debug("srname===>"+srname);
		OUT.debug("sremailid===>"+sremailid);
		OUT.debug("srclassid===>"+srclassid);
		OUT.debug("srgender===>"+srgender);
		OUT.debug("srpno===>"+srpno);
		try
		{
			if(srtoken!=null && !srtoken.trim().equals(""))
			{
				
				String id=null;
				id=new EncryptionDecryptionData().Decrypt(srtoken);
				if(id!=null && !id.trim().equals(""))
				{
					APISService apiservice=new APISService();
					LeadParentDTO leadparent=new LeadParentDTO();
					leadparent= apiservice.getLeadDetails(Integer.parseInt(id.trim()));
					if(leadparent!=null)
					{
						if(leadparent.getId()!=0 && leadparent.getParentEmailID()!=null && !leadparent.getParentEmailID().trim().equals(""))
						{
							
							if(srname!=null && !srname.trim().equals("") && sremailid!=null && !sremailid.trim().equals("") && srclassid!=0 && srgender!=null && !srgender.trim().equals(""))
							{
								StudentDetailsDTO studentDTO=new StudentDetailsDTO();
								
								studentDTO.setName(srname.trim());
								
								studentDTO.setGender(srgender.trim());
								studentDTO.setClassId(srclassid);
								studentDTO.setSchoolId(1);
								
								studentDTO.setStudentemailId(sremailid);
								
								if(leadparent.getParentName()!=null && !leadparent.getParentName().trim().equals(""))
								{
									studentDTO.setFatherName(leadparent.getParentName().trim());
									
									studentDTO.setFathername(leadparent.getParentName().trim());
									
									
								}
								else
								{
									studentDTO.setFatherName(srname.trim()+" Parent");
									
									studentDTO.setFathername(srname.trim()+" Parent");
									
									
								}
								
								if(srpno!=null && !srpno.trim().equals(""))
								{
									studentDTO.setStudentcontactNumber(srpno.trim());
								}
								
									studentDTO.setFatherEmailId(leadparent.getParentEmailID().trim());
									studentDTO.setFatheremailId(leadparent.getParentEmailID().trim());
									
									
									if(leadparent.getParentContactNo()!=null && !leadparent.getParentContactNo().trim().equals(""))
									{
									
								
										studentDTO.setFathercontactno(leadparent.getParentContactNo());
										
										studentDTO.setContactNumber(leadparent.getParentContactNo());
									}
									else
									{
										if(srpno!=null && !srpno.trim().equals(""))
										{
											studentDTO.setFathercontactno(srpno.trim());
											
											studentDTO.setContactNumber(srpno.trim());
										}
										else
										{
											studentDTO.setFathercontactno("1234567898");
											
											studentDTO.setContactNumber("1234567898");
										}
									}
								
									studentDTO.setStudentType(StudentTypeEnum.TRIAL);
									
									
									EActionStatus DuplicationCheck=ValidateDuplication(studentDTO);
									
									if(DuplicationCheck==EActionStatus.SUCCESS)
									{
										EActionStatus status= apiservice.LeadinsertStudent(studentDTO, leadparent);
										if (status == EActionStatus.SUCCESS)
										{
											OUT.error("Student Creation successfully");
											Status="success";
										}
										else
										{
											OUT.error("Student Creation Failed");
											Status="error";
										}
										
										
										
									}
									else
									{
										OUT.error("Duplicate Entry");
										
										Status="duplicate";
									}

							}
							else
							{
								OUT.error("Error 5");
								Status="error";
							}
							
							
						}
						else
						{
							OUT.error("Error 4");
							Status="error";
						}
					}
					else
					{
						OUT.error("Error 3");
						Status="error";
					}
				}
				else
				{
					OUT.error("Error 2");
					Status="error";
				}
			}
			else
			{
				OUT.error("Error 1");
				Status="error";
			}
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			Status="error";
		}
		
		
		return SUCCESS;
	}
	private EActionStatus ValidateDuplication(StudentDetailsDTO studentDTO)
	{
		
		
		boolean Duplicatevalidate=new APISService().Duplicatev(studentDTO);
		
		if(Duplicatevalidate)
		{
			return EActionStatus.FAILURE;
		}
		
		
		return EActionStatus.SUCCESS;
	}
	
	
	
	
	public String NewStudentRegistration()
	{
		OUT.debug("Inside LeadStudentCreation class : execute NewStudentRegistration");
		
		try
		{
					APISService apiservice=new APISService();
					LeadParentDTO leadparent=new LeadParentDTO();
					
					
							
				if(prpno!=null && !prpno.trim().equals("") && premail!=null && !premail.trim().equals("")   &&    srname!=null && !srname.trim().equals("") && sremailid!=null && !sremailid.trim().equals("") && srclassid!=0 && srgender!=null && !srgender.trim().equals(""))
				{
					StudentDetailsDTO studentDTO=new StudentDetailsDTO();
					
					studentDTO.setName(srname.trim());
					
					studentDTO.setGender(srgender.trim());
					studentDTO.setClassId(srclassid);
					studentDTO.setSchoolId(1);
					
					studentDTO.setStudentemailId(sremailid.trim());
					
					leadparent.setStudentEmailID(sremailid.trim());
					
					
					
					
					studentDTO.setFatherName(srname.trim()+" Parent");
						
					studentDTO.setFathername(srname.trim()+" Parent");
						
					
					
					
					
					studentDTO.setStudentcontactNumber(srpno.trim());
					
					leadparent.setStudentContactNo(srpno.trim());
					
					
					
					
					
					studentDTO.setFatherEmailId(premail.trim());
					studentDTO.setFatheremailId(premail.trim());
					
					leadparent.setParentEmailID(premail.trim());
					
					
					
						
					studentDTO.setFathercontactno(prpno.trim());
							
					studentDTO.setContactNumber(prpno.trim());
					
					leadparent.setParentContactNo(prpno.trim());
					
					
					
						
					studentDTO.setStudentType(StudentTypeEnum.TRIAL);
						
						
					LeadParentDTO leadid= apiservice.insertStudentNew(studentDTO, leadparent);
					if (leadid!=null && leadid.getId() > 0)
					{
						
						
						PasswordGeneratorService.sendleadStudentmessageToLodestar("Admin",leadparent.getParentEmailID(),leadparent.getParentContactNo(),studentDTO.getName(),leadparent.getStudentEmailID(),leadparent.getStudentContactNo(),leadparent.getLDID(),2);
						
						
						
						OUT.error("Student Creation successfully");
						Status="success";
						id=leadid.getId();
					}
					else
					{
						OUT.error("Student Creation Failed");
						Status="error";
					}
							
						
				}
				else
				{
					OUT.error("Error 5");
					Status="error";
				}
							
							
						
					
				
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			Status="error";
		}
		
		
		return SUCCESS;
	}
	
	
	public String NewChildRegistration()
	{
		OUT.debug("Inside LeadStudentCreation class : execute NewChildRegistration");
		
		try
		{
					APISService apiservice=new APISService();
					LeadParentDTO leadparent=new LeadParentDTO();
					
					
							
				if(prpno!=null && !prpno.trim().equals("") && premail!=null && !premail.trim().equals("")   &&    srname!=null && !srname.trim().equals("") && sremailid!=null && !sremailid.trim().equals("") && srclassid!=0 && srgender!=null && !srgender.trim().equals(""))
				{
					StudentDetailsDTO studentDTO=new StudentDetailsDTO();
					
					studentDTO.setName(srname.trim());
					
					studentDTO.setGender(srgender.trim());
					studentDTO.setClassId(srclassid);
					studentDTO.setSchoolId(1);
					
					studentDTO.setStudentemailId(sremailid.trim());
					
					leadparent.setStudentEmailID(sremailid.trim());
					
					
					
					
					studentDTO.setFatherName(srname.trim()+" Parent");
						
					studentDTO.setFathername(srname.trim()+" Parent");
						
					
					
					
					
					studentDTO.setStudentcontactNumber(srpno.trim());
					
					leadparent.setStudentContactNo(srpno.trim());
					
					
					
					
					
					studentDTO.setFatherEmailId(premail.trim());
					studentDTO.setFatheremailId(premail.trim());
					
					leadparent.setParentEmailID(premail.trim());
					
					
					
						
					studentDTO.setFathercontactno(prpno.trim());
							
					studentDTO.setContactNumber(prpno.trim());
					
					leadparent.setParentContactNo(prpno.trim());
					
					
					
						
					studentDTO.setStudentType(StudentTypeEnum.TRIAL);
						
						
					LeadParentDTO leadid= apiservice.insertChildNew(studentDTO, leadparent);
					if (leadid!=null && leadid.getId() > 0)
					{
						
						
						PasswordGeneratorService.sendleadChildmessageToLodestar("Admin",leadparent.getParentEmailID(),leadparent.getParentContactNo(),studentDTO.getName(),leadparent.getStudentEmailID(),leadparent.getStudentContactNo(),leadparent.getLDID(),2);
						
						
						
						OUT.error("Student Creation successfully");
						Status="success";
						srtoken=leadid.getToken();
					}
					else
					{
						OUT.error("Student Creation Failed");
						Status="error";
					}
							
						
				}
				else
				{
					OUT.error("Error 5");
					Status="error";
				}
							
							
						
					
				
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			Status="error";
		}
		
		
		return SUCCESS;
	}
	
	
	public String NewTYEProgTestRegistration()
	{
		OUT.debug("Inside LeadStudentCreation class : execute NewTYEProgTestRegistration");
		
		try
		{
					APISService apiservice=new APISService();
					LeadParentDTO leadparent=new LeadParentDTO();
					
					
							
				if(prpno!=null && !prpno.trim().equals("") && premail!=null && !premail.trim().equals("")   &&    srname!=null && !srname.trim().equals("") && sremailid!=null && !sremailid.trim().equals("") && srclassid!=0 && srgender!=null && !srgender.trim().equals(""))
				{
					StudentDetailsDTO studentDTO=new StudentDetailsDTO();
					
					studentDTO.setName(srname.trim());
					
					studentDTO.setGender(srgender.trim());
					studentDTO.setClassId(srclassid);
					studentDTO.setSchoolId(1);
					
					studentDTO.setStudentemailId(sremailid.trim());
					
					leadparent.setStudentEmailID(sremailid.trim());
					
					
					
					
					studentDTO.setFatherName(srname.trim()+" Parent");
						
					studentDTO.setFathername(srname.trim()+" Parent");
						
					
					
					
					
					studentDTO.setStudentcontactNumber(srpno.trim());
					
					leadparent.setStudentContactNo(srpno.trim());
					
					
					
					
					
					studentDTO.setFatherEmailId(premail.trim());
					studentDTO.setFatheremailId(premail.trim());
					
					leadparent.setParentEmailID(premail.trim());
					
					
					
						
					studentDTO.setFathercontactno(prpno.trim());
							
					studentDTO.setContactNumber(prpno.trim());
					
					leadparent.setParentContactNo(prpno.trim());
					
					
					
						
					studentDTO.setStudentType(StudentTypeEnum.TRIAL);
						
						
					LeadParentDTO leadid= apiservice.insertChildNew(studentDTO, leadparent);
					if (leadid!=null && leadid.getId() > 0)
					{
						
						
						PasswordGeneratorService.sendTYEProgTestmessageToLodestar("Admin",leadparent.getParentEmailID(),leadparent.getParentContactNo(),studentDTO.getName(),leadparent.getStudentEmailID(),leadparent.getStudentContactNo(),leadparent.getLDID(),2);
						
						
						
						OUT.error("Student Creation successfully");
						Status="success";
						srtoken=leadid.getToken();
					}
					else
					{
						OUT.error("Student Creation Failed");
						Status="error";
					}
							
						
				}
				else
				{
					OUT.error("Error 5");
					Status="error";
				}
							
							
						
					
				
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			Status="error";
		}
		
		
		return SUCCESS;
	}
	
	
	
	
	
	

}
