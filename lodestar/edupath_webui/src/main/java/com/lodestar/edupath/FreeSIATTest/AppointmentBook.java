package com.lodestar.edupath.FreeSIATTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.APISService;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.util.EncryptionDecryptionData;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class AppointmentBook {
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(AppointmentBook.class);
	private String datestr;
	private String timestr;
	private String token;
	private String dtp_input1;
	
	public String getDtp_input1() {
		return dtp_input1;
	}
	public void setDtp_input1(String dtp_input1) {
		this.dtp_input1 = dtp_input1;
	}
	public String getDatestr() {
		return datestr;
	}
	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}
	public String getTimestr() {
		return timestr;
	}
	public void setTimestr(String timestr) {
		this.timestr = timestr;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String execute()
	{
		
		OUT.debug("Inside AppointmentBook class : execute method");
		
		try
		{
			String id=null;
			if(token!=null && !token.trim().equals("") && datestr!=null && !datestr.trim().equals("") && timestr!=null && !timestr.trim().equals(""))
			{
				id=new EncryptionDecryptionData().Decrypt(token.trim());
				OUT.debug("After Decrypted ID IS==>"+id);
				if(id!=null && !id.trim().equals(""))
				{
					APISService apiservice=new APISService();
					LeadParentDTO leadparent=new LeadParentDTO();
					leadparent= apiservice.getLeadDetails(Integer.parseInt(id.trim()));
					if(leadparent!=null)
					{
						if(leadparent.getId()!=0)
						{
							
							String completedatetime=datestr.trim()+" "+timestr.trim();
							
							leadparent.setAppointmentDateTime(completedatetime);
							
							apiservice.AppointmentBooking(leadparent);
							
							PasswordGeneratorService.AppointmentBookToLodestar(completedatetime,leadparent.getParentEmailID(),leadparent.getLDID(),2);
						}
						else
						{

							OUT.debug("Invalid LeadParent ID");
							return "error";
						}
					}
					else
					{
						OUT.debug("Invalid LeadParent");
						return "error";
					}
				}
				else
				{
					OUT.debug("Invalid ID");
					return "error";
				}
			}
			else
			{
				OUT.debug("Parameter missing");
				return "error";
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return "error";
		}
		
		
		
		return "success";
	}


	public String AppointmentBookLink()
	{
		
		OUT.debug("Inside AppointmentBook class : AppointmentBookLink method");
		
		try
		{
			String id=null;
			if(token!=null && !token.trim().equals("") && dtp_input1!=null && !dtp_input1.trim().equals(""))
			{
				id=new EncryptionDecryptionData().Decrypt(token.trim());
				OUT.debug("After Decrypted ID IS==>"+id);
				if(id!=null && !id.trim().equals(""))
				{
					APISService apiservice=new APISService();
					LeadParentDTO leadparent=new LeadParentDTO();
					leadparent= apiservice.getLeadDetails(Integer.parseInt(id.trim()));
					if(leadparent!=null)
					{
						if(leadparent.getId()!=0)
						{
							
							String completedatetime=dtp_input1.trim();
							
							leadparent.setAppointmentDateTime(completedatetime);
							
							apiservice.AppointmentBooking(leadparent);
							StudentDetailsDTO studentDTO = null;
							studentDTO = new StudentDetailsDAO().getStudentObjectById(leadparent.getStudentID());
							
							Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(completedatetime.trim());
							
							String Gender=null;
							
							if(studentDTO!=null && studentDTO.getGender()!=null && studentDTO.getGender().trim().equals("F"))
							{
								Gender="she";
							}
							else if(studentDTO!=null && studentDTO.getGender()!=null && studentDTO.getGender().trim().equals("M"))
							{
								Gender="he";
							}
							else
							{
								Gender="he/she";
							}
							
							PasswordGeneratorService.AppointmentBookToLodestarHDFC(completedatetime,leadparent.getParentEmailID(),leadparent.getLDID(),studentDTO.getName(),studentDTO.getStudentemailId(),studentDTO.getStudentcontactNumber(),leadparent.getParentContactNo(),2);
						
							PasswordGeneratorService.SendMailTOParentBookingSlots(new SimpleDateFormat("yyyy-MM-dd").format(date),new SimpleDateFormat("H:mm").format(date),leadparent.getParentEmailID(),studentDTO.getName(),Gender,2);
							
							PasswordGeneratorService.SendMailTOStudentBookingSlots( new SimpleDateFormat("yyyy-MM-dd").format(date),new SimpleDateFormat("H:mm").format(date),studentDTO.getStudentemailId(),studentDTO.getName(),2);
							
						
						}
						else
						{

							OUT.debug("Invalid LeadParent ID");
							return "error";
						}
					}
					else
					{
						OUT.debug("Invalid LeadParent");
						return "error";
					}
				}
				else
				{
					OUT.debug("Invalid ID");
					return "error";
				}
			}
			else
			{
				OUT.debug("Parameter missing");
				return "error";
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return "error";
		}
		
		
		
		return "success";
	}




}
