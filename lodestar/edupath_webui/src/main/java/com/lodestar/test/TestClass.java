package com.lodestar.test;

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


public class TestClass {

	
	private static final Logger	OUT			= LoggerFactory.getLogger(TestClass.class);

	public static void main(String args[])
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
				OUT.debug("bharath SIATTestReminderEmail to be sent to {}", resultAsList1);
			
				StudentDetailsDTO studentDetailsDTO;
				
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyyy-MM-dd");
				
				for(LeadParentDTO leadparent: resultAsList1)
				{
					OUT.info("Bharath main inside for loop ", leadparent);
					studentDetailsDTO=null;
					boolean emailsent=false;
					
					LeadParentDTO leadparentForUpdate=new LeadParentDTO();
					
					leadparentForUpdate.setId(leadparent.getId());

					if(leadparent.getStudentRegister()==1)
					{
						 OUT.debug("bharath SIATTestReminderEmail inside if loop leadparent.getStudentRegister()==1:{}", leadparent.getStudentRegister());
						 studentDetailsDTO=(StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STUDENT_DETAIL_BY_ID,leadparent.getStudentID());
						 Date date1 = myFormat.parse(leadparent.getParentRegisterTime());
						 Date currentdate = new Date();
						 String currentdatestr=myFormat.format(currentdate);
						 Date date2 =myFormat.parse(currentdatestr);
						 long diff = date2.getTime() - date1.getTime();
						 int daysBetween = (int) (diff / (1000*60*60*24));
						boolean resultif = daysBetween>=10 && leadparent.getIsTendaymailsent()==0;
						OUT.debug("bharath SIATTestReminderEmail condition for if loop: daysBetween>=10 && leadparent.getIsTendaymailsent()==0 resultif:{}",resultif);
						resultif =daysBetween>=5 && leadparent.getIsTendaymailsent()==0 && leadparent.getIsFivedaymailsent()==0;
						OUT.debug("bharath SIATTestReminderEmail condition for if loop: daysBetween>=5 && leadparent.getIsTendaymailsent()==0 && leadparent.getIsFivedaymailsent()==0 resultif:{}",resultif);
						 
						if(daysBetween>=10 && leadparent.getIsTendaymailsent()==0)
						 {
							 OUT.debug("bharath SIATTestReminderEmail inside if loop daysBetween>=10:{} && leadparent.getIsTendaymailsent():{}, leadparent.getIsFivedaymailsent():{}", daysBetween,leadparent.getIsTendaymailsent(),leadparent.getIsFivedaymailsent());
							 sendSTIATTestReminder(leadparent,studentDetailsDTO, 10, webURL);
							 emailsent=true;
							 
							 leadparentForUpdate.setIsFivedaymailsent(leadparent.getIsFivedaymailsent());
							 leadparentForUpdate.setIsTendaymailsent(1);
						 }
						 else if(daysBetween>=5 && leadparent.getIsTendaymailsent()==0 && leadparent.getIsFivedaymailsent()==0)
						 {
							 OUT.debug("bharath SIATTestReminderEmail inside else if loop daysBetween>=10:{} && leadparent.getIsTendaymailsent():{}, leadparent.getIsFivedaymailsent():{}", daysBetween,leadparent.getIsTendaymailsent(),leadparent.getIsFivedaymailsent());
							 sendSTIATTestReminder(leadparent,studentDetailsDTO, 10, webURL);
							 emailsent=true;
							 
							 leadparentForUpdate.setIsFivedaymailsent(1);
							 leadparentForUpdate.setIsTendaymailsent(leadparent.getIsTendaymailsent());
							 
						 }
						 OUT.debug("bharath SIATTestReminderEmail Done  if loop daysBetween:{}, leadparent.getIsTendaymailsent():{}, leadparent.getIsFivedaymailsent():{}, leadparentForUpdate:{}, studentDetailsDTO:{}", daysBetween,leadparent.getIsTendaymailsent(),leadparent.getIsFivedaymailsent()==0,leadparentForUpdate,studentDetailsDTO);
						
					}
					else
					{
						OUT.debug("bharath SIATTestReminderEmail inside if else loop leadparent.getStudentRegister()==0:{}", leadparent.getStudentRegister());
						 Date date1 = myFormat.parse(leadparent.getParentRegisterTime());
						 Date currentdate = new Date();
						 String currentdatestr=myFormat.format(currentdate);
						 Date date2 =myFormat.parse(currentdatestr);
						 long diff = date2.getTime() - date1.getTime();
						 int daysBetween = (int) (diff / (1000*60*60*24));
						 boolean resultif = daysBetween>=10 && leadparent.getIsTendaymailsent()==0;
						OUT.debug("bharath SIATTestReminderEmail condition for if else  loop: daysBetween>=10 && leadparent.getIsTendaymailsent()==0 resultif:{}",resultif);
						resultif =daysBetween>=5 && leadparent.getIsTendaymailsent()==0 && leadparent.getIsFivedaymailsent()==0;
						OUT.debug("bharath SIATTestReminderEmail condition for if esle loop: daysBetween>=5 && leadparent.getIsTendaymailsent()==0 && leadparent.getIsFivedaymailsent()==0 resultif:{}",resultif);
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
						 
						 OUT.debug("bharath SIATTestReminderEmail Done if else loop daysBetween:{}, leadparent.getIsTendaymailsent():{}, leadparent.getIsFivedaymailsent():{} , leadparentForUpdate:{}, studentDetailsDTO:{}", daysBetween,leadparent.getIsTendaymailsent(),leadparent.getIsFivedaymailsent()==0,leadparentForUpdate,studentDetailsDTO); 
						 
					}
					if(emailsent)
					{
						 OUT.debug("bharath SIATTestReminderEmail Done inside if email sent true:"); 
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
	private static void sendSTIATTestReminder(LeadParentDTO leadparent,StudentDetailsDTO studentDetailsDTO, int Numberofdays, String webURL)
	{
		OUT.info("bharath inside  sendSTIATTestReminder leadparent:{}, studentDetailsDTO:{}, Numberofdays:{}, webURL",leadparent,studentDetailsDTO,Numberofdays, webURL);
		try
		{
			String messageTypeParent = null;
			String messageTypeStudent = null;
			
			if(Numberofdays==5)
			{
				messageTypeParent= NotificationConstant.MessageTemplateNameAndType.SIATTest_Five_Day_Reminder_Parent.name();
				messageTypeStudent= NotificationConstant.MessageTemplateNameAndType.SIATTest_Five_Day_Reminder_Student.name();
				OUT.debug("bharath sendSTIATTestReminder if(Numberofdays==5) messageTypeParent:{}, messageTypeStudent:{}",messageTypeParent,messageTypeStudent);
			}
			else
			{
				messageTypeParent= NotificationConstant.MessageTemplateNameAndType.SIATTest_Ten_Day_Reminder_Parent.name();
				messageTypeStudent= NotificationConstant.MessageTemplateNameAndType.SIATTest_Ten_Day_Reminder_Student.name();
				OUT.debug("bharath sendSTIATTestReminder else if(Numberofdays==5) messageTypeParent:{}, messageTypeStudent:{}",messageTypeParent,messageTypeStudent);
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
			
			OUT.debug("bharath sendSTIATTestReminder notificationDTOParent:{}",notificationDTOParent);
			
			//For Student
			
			MessageNotificationDTO notificationDTOStudent = new MessageNotificationDTO();
			notificationDTOStudent.setNotificationType(MessageNotificationType.EMAIL.name());
			notificationDTOStudent.setMessageType(messageTypeStudent);
			notificationDTOStudent.setNew(true);

			notificationDTOStudent.setRoleTypeId(2);

			notificationDTOStudent.setUserName("Student");
			
			OUT.debug("bharath sendSTIATTestReminder notificationDTOStudent:{}",notificationDTOStudent);
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("UserName", "Reminder");
			parameterMap.put("webURL", webURL);
			
			OUT.debug("bharath sendSTIATTestReminder parameterMap:{}",parameterMap);
			
			
			
			if(leadparent.getStudentRegister()==1)
			{
				OUT.debug("bharath sendSTIATTestReminder if loop  leadparent.getStudentRegister():{}",leadparent.getStudentRegister());
				if(studentDetailsDTO!=null && studentDetailsDTO.getStudentemailId()!=null && !studentDetailsDTO.getStudentemailId().trim().equals(""))
				{
					notificationDTOStudent.setRecipientMailIds(new String[]
					{
						studentDetailsDTO.getStudentemailId()
					});
					
					boolean addNotification = NotificationTemplateGenerator.addNotification(notificationDTOStudent, parameterMap);
					OUT.debug("bharath sendSTIATTestReminder if loop  addNotification:{}",addNotification);
				}
				else if(leadparent.getStudentEmailID()!=null && !leadparent.getStudentEmailID().trim().equals(""))
				{
					notificationDTOStudent.setRecipientMailIds(new String[]
					{
						leadparent.getStudentEmailID()
					});
							
					boolean addNotification = NotificationTemplateGenerator.addNotification(notificationDTOStudent, parameterMap);
					OUT.debug("bharath sendSTIATTestReminder if loop  addNotification:{}",addNotification);
				}
			}
			else
			{
				OUT.debug("bharath sendSTIATTestReminder else loop  leadparent.getStudentRegister():{}",leadparent.getStudentRegister());
				if(leadparent.getStudentEmailID()!=null && !leadparent.getStudentEmailID().trim().equals(""))
				{
					notificationDTOStudent.setRecipientMailIds(new String[]
					{
						leadparent.getStudentEmailID()
					});
					
					boolean addNotification = NotificationTemplateGenerator.addNotification(notificationDTOStudent, parameterMap);
					OUT.debug("bharath sendSTIATTestReminder if loop  addNotification:{}",addNotification);
				}
			}
			
			
			
			
			boolean addNotificationparent = NotificationTemplateGenerator.addNotification(notificationDTOParent, parameterMap);
			OUT.debug("bharath sendSTIATTestReminder if loop  addNotificationparent:{}",addNotificationparent);

			
			OUT.debug("SIAT Test Remainder Mail sent");
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	
	

}
