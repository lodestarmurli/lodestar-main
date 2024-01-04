package com.lodestar.edupath.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant.MessageNotificationType;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.notification.NotificationTemplateGenerator;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.MessageNotificationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.scheduler.SchedulerTask;

public class SIATTestReminderEmail implements SchedulerTask{
	private static final Logger	OUT			= LoggerFactory.getLogger(SIATTestReminderEmail.class);

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
		OUT.info("Started SIATTestReminderEmail Service");
		
		try
		{
			GlobalSttingDAO globalDAO = new GlobalSttingDAO();
			GlobalSettingDTO globalDTO = new GlobalSettingDTO();
			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
			globalDTO = globalDAO.getPropertesValueByName(globalDTO);
			String webURL = globalDTO.getPropertyValue();
			
			
			List<LeadParentDTO> resultAsList1 = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.Lead_Parent_For_Reminder_email,null);
			
			OUT.info("Number of SIATTestReminderEmail to be sent {}", resultAsList1.size());
		
			StudentDetailsDTO studentDetailsDTO;
			
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyyy-MM-dd");
			
			for(LeadParentDTO leadparent: resultAsList1)
			{
				studentDetailsDTO=null;
				boolean emailsent=false;
				
				LeadParentDTO leadparentForUpdate=new LeadParentDTO();
				
				leadparentForUpdate.setId(leadparent.getId());

				if(leadparent.getStudentRegister()==1)
				{
					
					 studentDetailsDTO=(StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STUDENT_DETAIL_BY_ID,leadparent.getStudentID());
					 Date date1 = myFormat.parse(leadparent.getParentRegisterTime());
					 Date currentdate = new Date();
					 String currentdatestr=myFormat.format(currentdate);
					 Date date2 =myFormat.parse(currentdatestr);
					 long diff = date2.getTime() - date1.getTime();
					 float daysBetween = (diff / (1000*60*60*24));
					
					 if(daysBetween>=10 && leadparent.getIsTendaymailsent()==0)
					 {
						 sendSTIATTestReminder(leadparent,studentDetailsDTO, 10, webURL);
						 emailsent=true;
						 
						 leadparentForUpdate.setIsFivedaymailsent(leadparent.getIsFivedaymailsent());
						 leadparentForUpdate.setIsTendaymailsent(1);
					 }
					 else if(daysBetween>=5 && leadparent.getIsTendaymailsent()==0 && leadparent.getIsFivedaymailsent()==0)
					 {
						 sendSTIATTestReminder(leadparent,studentDetailsDTO, 10, webURL);
						 emailsent=true;
						 
						 leadparentForUpdate.setIsFivedaymailsent(1);
						 leadparentForUpdate.setIsTendaymailsent(leadparent.getIsTendaymailsent());
						 
					 }
					
				}
				else
				{
					
					 Date date1 = myFormat.parse(leadparent.getParentRegisterTime());
					 Date currentdate = new Date();
					 String currentdatestr=myFormat.format(currentdate);
					 Date date2 =myFormat.parse(currentdatestr);
					 long diff = date2.getTime() - date1.getTime();
					 float daysBetween = (diff / (1000*60*60*24));
					
					 if(daysBetween>=10 && leadparent.getIsTendaymailsent()==0)
					 {
						 sendSTIATTestReminder(leadparent,studentDetailsDTO, 10, webURL);
						 emailsent=true;
						 
						 leadparentForUpdate.setIsFivedaymailsent(leadparent.getIsFivedaymailsent());
						 leadparentForUpdate.setIsTendaymailsent(1);
						 
					 }
					 else if(daysBetween>=5 && leadparent.getIsTendaymailsent()==0 && leadparent.getIsFivedaymailsent()==0)
					 {
						 sendSTIATTestReminder(leadparent,studentDetailsDTO, 10, webURL);
						 emailsent=true;
						 
						 leadparentForUpdate.setIsFivedaymailsent(1);
						 leadparentForUpdate.setIsTendaymailsent(leadparent.getIsTendaymailsent());
					 }
					 
					 
					 
				}
				if(emailsent)
				{
					SqlSession session=null;
					try
					{
					   int id = 0;
					   session = MyBatisManager.getInstance().getSession();
					   id=session.update(MyBatisMappingConstants.Lead_Parent_For_Reminder_Update_Mail_Sent, leadparentForUpdate);
					   OUT.debug("SIATTestReminderEmail sent update  id:{}", id);
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
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		OUT.info("Completed SIATTestReminderEmail Service");
		
	}
    
	
	private void sendSTIATTestReminder(LeadParentDTO leadparent,StudentDetailsDTO studentDetailsDTO, int Numberofdays, String webURL)
	{
		try
		{
			String messageTypeParent = null;
			String messageTypeStudent = null;
			
			if(Numberofdays==5)
			{
				messageTypeParent= NotificationConstant.MessageTemplateNameAndType.SIATTest_Five_Day_Reminder_Parent.name();
				messageTypeStudent= NotificationConstant.MessageTemplateNameAndType.SIATTest_Five_Day_Reminder_Student.name();
			}
			else
			{
				messageTypeParent= NotificationConstant.MessageTemplateNameAndType.SIATTest_Ten_Day_Reminder_Parent.name();
				messageTypeStudent= NotificationConstant.MessageTemplateNameAndType.SIATTest_Ten_Day_Reminder_Student.name();
			}

			
			//For Parent
			MessageNotificationDTO notificationDTOParent = new MessageNotificationDTO();
			notificationDTOParent.setNotificationType(MessageNotificationType.EMAIL.name());
			notificationDTOParent.setMessageType(messageTypeParent);
			notificationDTOParent.setNew(true);

			notificationDTOParent.setRoleTypeId(2);

			notificationDTOParent.setUserName("Parent");
			notificationDTOParent.setRecipientMailIds(new String[]
			{
					leadparent.getParentEmailID()
			});
			
			
			//For Student
			
			MessageNotificationDTO notificationDTOStudent = new MessageNotificationDTO();
			notificationDTOStudent.setNotificationType(MessageNotificationType.EMAIL.name());
			notificationDTOStudent.setMessageType(messageTypeStudent);
			notificationDTOStudent.setNew(true);

			notificationDTOStudent.setRoleTypeId(2);

			notificationDTOStudent.setUserName("Student");
			
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("UserName", "Reminder");
			parameterMap.put("webURL", webURL);
			
			
			
			
			if(leadparent.getStudentRegister()==1)
			{
			
				if(studentDetailsDTO!=null && studentDetailsDTO.getStudentemailId()!=null && !studentDetailsDTO.getStudentemailId().trim().equals(""))
				{
					notificationDTOStudent.setRecipientMailIds(new String[]
					{
						studentDetailsDTO.getStudentemailId()
					});
					
					boolean addNotification = NotificationTemplateGenerator.addNotification(notificationDTOStudent, parameterMap);
				}
				else if(leadparent.getStudentEmailID()!=null && !leadparent.getStudentEmailID().trim().equals(""))
				{
					notificationDTOStudent.setRecipientMailIds(new String[]
					{
						leadparent.getStudentEmailID()
					});
							
					boolean addNotification = NotificationTemplateGenerator.addNotification(notificationDTOStudent, parameterMap);
				}
			}
			else
			{
				if(leadparent.getStudentEmailID()!=null && !leadparent.getStudentEmailID().trim().equals(""))
				{
					notificationDTOStudent.setRecipientMailIds(new String[]
					{
						leadparent.getStudentEmailID()
					});
					
					boolean addNotification = NotificationTemplateGenerator.addNotification(notificationDTOStudent, parameterMap);
				}
			}
			
			
			
			
			boolean addNotificationparent = NotificationTemplateGenerator.addNotification(notificationDTOParent, parameterMap);

			
			OUT.debug("SIAT Test Remainder Mail sent");
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	
	

}
