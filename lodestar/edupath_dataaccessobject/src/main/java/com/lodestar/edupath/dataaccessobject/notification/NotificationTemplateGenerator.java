/*
 * @(#) NotificationTemplateGenerator.java  
 * 
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 1.0 
 * @Date Mar 12, 2013
 * @Author Smrutiranjan Behera
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.dataaccessobject.notification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant.MessageNotificationType;
import com.lodestar.edupath.datatransferobject.dto.MessageNotificationDTO;
import com.lodestar.edupath.datatransferobject.dto.MessageQueueDTO;
import com.lodestar.edupath.datatransferobject.dto.MessageTemplateDTO;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class NotificationTemplateGenerator
{
	private static final Logger							OUT			= LoggerFactory.getLogger(NotificationTemplateGenerator.class.getName());

	private final static String							TABLE_STYLE	= "width: 100%; font-size: 11px; font-family: Verdana; ";

	private final static String							DIV_STYLE	= "width: 100%; margin: 10px 0 10px 0;";

	private static final NotificationTemplateGenerator	INSTANCE	= new NotificationTemplateGenerator();

	private NotificationTemplateGenerator()
	{
		// Do Nothing
	}

	public static NotificationTemplateGenerator getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Method to send notification based on the template.
	 * 
	 * @param notificationDTO
	 * @return
	 */
	public static boolean addNotification(MessageNotificationDTO notificationDTO)
	{
		return addNotification(notificationDTO, null);
	}

	/**
	 * Method to send notification based on the template.
	 * 
	 * @param notificationDTO
	 * @return
	 */
	public static boolean addNotification(MessageNotificationDTO notificationDTO, Map<String, Object> parameterMap)
	{
		boolean returnStatus = false;
		synchronized (notificationDTO)
		{
			// for (TenantMasterDTO tenantDetailDTO : tenantList)
			{
				try
				{
					if (OUT.isDebugEnabled())
					{
						OUT.debug("Notification parameters: " + notificationDTO);
					}
					MessageTemplateDTO msgTemplate = new MessageTemplateDTO();
					msgTemplate.setNotificationType(notificationDTO.getNotificationType());
					msgTemplate.setMessageType(notificationDTO.getMessageType());
					msgTemplate.setRoleTypeId(notificationDTO.getRoleTypeId());

					msgTemplate = (MessageTemplateDTO) MyBatisManager.getInstance().getResultAsObject(
							MyBatisMappingConstants.GET_TEMPLATE_BY_NOTIFICATION_N_MESSAGE_TYPE, msgTemplate);
					if (msgTemplate == null)
					{
						OUT.warn("Message Template cannot be null");
						returnStatus = false;
						return returnStatus;
					}

					Map<String, Object> rootMap = setNotificationParamTOMap(notificationDTO);
					if (parameterMap != null && !parameterMap.isEmpty())
					{
						for (Entry<String, Object> parameterEntry : parameterMap.entrySet())
						{
							rootMap.put(parameterEntry.getKey(), parameterEntry.getValue());
						}
					}
					String generatedTemplate = generateTemplate(msgTemplate.getTemplateString(), msgTemplate.getTemplateParams(), rootMap, false);
					if (generatedTemplate == null || generatedTemplate.trim().isEmpty())
					{
						returnStatus = false;
						return returnStatus;
					}

					// This parameter added for the subject line without replace XML exntities as replaceXML value is not required for subject.
					rootMap.put(NotificationConstant.USER_NAME, notificationDTO.getUserName());
					String generatedSubjectTemplate = generateTemplate(msgTemplate.getMessageSubject(), NotificationConstant.USER_NAME, rootMap, true);

					MessageQueueDTO msgQueue = new MessageQueueDTO();
					int insertId = 0;
					if (notificationDTO.isNew())
					{
						msgQueue.setMessageSubject(generatedSubjectTemplate);
						msgQueue.setMessageData(generatedTemplate);
						msgQueue.setStatus(NotificationConstant.MessageNotificationStatus.INPROGRESS.name());
						msgQueue.setQueuedTime(new Timestamp(System.currentTimeMillis()));
						msgQueue.setNotifiedTime(new Timestamp(System.currentTimeMillis()));
						msgQueue.setToAddress(getUniqueEmailIdsCommaSeparatedValue(notificationDTO.getRecipientMailIds()));
						msgQueue.setNotifierType(notificationDTO.getNotificationType());
						msgQueue.setRetryCount(0);
						msgQueue.setFilePath(notificationDTO.getFilePath());
						
						//Start SASEDEVE edited by Mrutyunjaya on Date 10-08-2017
						
//						String EmailID=getUniqueEmailIdsCommaSeparatedValue(notificationDTO.getRecipientMailIds());
//						
//						if(EmailID!=null && !EmailID.trim().equals(""))
//						{
//								String[] EMAILIDS = EmailID.split(",");
//								
//								String Emails=null;
//								
//								for(String email:EMAILIDS)
//								{
//								
//									boolean isvalidate=ValidateEmailWithmailgun(email.trim());
//									
//									if(isvalidate)
//									{
//										if(Emails!=null)
//										{
//											Emails=Emails+","+email;
//										}
//										else
//										{
//											Emails=email;
//										}
//									
//									}
//									else
//									{
//										MessageQueueDTO msgQueueinvalid = new MessageQueueDTO();
//										msgQueueinvalid.setMessageSubject(generatedSubjectTemplate);
//										msgQueueinvalid.setMessageData(generatedTemplate);
//										msgQueueinvalid.setStatus(NotificationConstant.MessageNotificationStatus.FAILED.name());
//										msgQueueinvalid.setQueuedTime(new Timestamp(System.currentTimeMillis()));
//										msgQueueinvalid.setNotifiedTime(new Timestamp(System.currentTimeMillis()));
//										msgQueueinvalid.setToAddress(email);
//										msgQueueinvalid.setNotifierType(notificationDTO.getNotificationType());
//										msgQueueinvalid.setFilePath(notificationDTO.getFilePath());
//										MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_MESSAGE_QUEUE_FAILED, msgQueueinvalid);
//										
//									}
//								}
//								if(Emails!=null)
//								{
//									msgQueue.setToAddress(Emails);
									if (MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_MESSAGE_QUEUE, msgQueue) == 1)
									{
										insertId = msgQueue.getId();
									}
//								}
//								
//						}
						
						//END SASEDEVE edited by Mrutyunjaya on Date 10-08-2017
						
					}
					else
					{
						insertId = notificationDTO.getMessageQueueId();
					}
					notificationDTO.setNew(false);
					returnStatus = true;
				}
				catch (Exception e)
				{
					OUT.error("Exception", e);
				}
			}
		}
		return returnStatus;
	}

	//Start SASEDEVE Edited By Mrutyunjaya on Date 08-08-2017

	private static boolean ValidateEmailWithmailgun(String EmailID)
	{
		boolean IsValid=false;
		
		try
		{
			if(EmailID!=null && !EmailID.trim().equals(""))
			{
			
					String url = "https://api.mailgun.net/v3/address/validate?api_key=pubkey-cc30cbd33bcb4738c52144b7b1310f3c&mailbox_verification=true&address="+EmailID;
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("GET");
					con.setRequestProperty("Content-Type:", "application/json");
					InputStreamReader in = null;
					StringBuilder sb = new StringBuilder();
					in = new InputStreamReader(con.getInputStream(),Charset.defaultCharset());
					BufferedReader bufferedReader = new BufferedReader(in);
					if (bufferedReader != null) {
						int cp;
						while ((cp = bufferedReader.read()) != -1) {
							sb.append((char) cp);
						}
						bufferedReader.close();
					}
					in.close();
					
					JSONObject jsonDataObject = new JSONObject(sb.toString());
					
					if(jsonDataObject!=null && jsonDataObject.has("is_valid") && jsonDataObject.has("mailbox_verification"))
					{
						if(jsonDataObject.getBoolean("is_valid") && jsonDataObject.getBoolean("mailbox_verification"))
						{
							IsValid=true;
						}
					}
					OUT.debug("email validate=====>"+sb.toString());
			}
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			IsValid=false;
		}
		
		return IsValid;
	}
	
	//END SASEDEVE Edited By Mrutyunjaya on Date 08-08-2017
	
	/**
	 * @param notificationDTO
	 * @return
	 */
	private static String setMessageDataAsJSONString(MessageNotificationDTO notificationDTO)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			jsonObj.put("userName", notificationDTO.getUserName());
			jsonObj.put("userId", notificationDTO.getUserId());
			jsonObj.put("userPassword", notificationDTO.getUserPassword());
			jsonObj.put("recipientMailIds", notificationDTO.getRecipientMailIds());
			jsonObj.put("mobileNumbers", notificationDTO.getMobileNumbers());
			jsonObj.put("webURL", notificationDTO.getWebURL());
			jsonObj.put("messageType", notificationDTO.getMessageType());
			jsonObj.put("notificationType", notificationDTO.getNotificationType());
			jsonObj.put("notificationBody", notificationDTO.getNotificationBody());

			return jsonObj.toString();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		return null;
	}

	/**
	 * Method to generate template using freemaker.
	 * 
	 * @param templateString
	 * @param templateParam
	 * @param rootMap
	 * @param isSubject
	 * @return
	 */
	public static String generateTemplate(String templateString, String templateParam, Map<String, Object> rootMap, boolean isSubject)
	{
		try
		{
			List<String> templateParamList = addAllParamTOList(templateParam);
			if (templateParamList == null || templateParamList.size() == 0)
			{
				if (OUT.isDebugEnabled())
				{
					OUT.debug("Template parameters cannot be null or empty");
				}
				return null;
			}
			if (isValidTemplateParam(templateParamList, rootMap))
			{
				StringTemplateLoader stringLoader = new StringTemplateLoader();
				String notificationTemplate = NotificationConstant.NOTIFICATION_TEMPLATE;
				StringBuilder freemarkerTemplate = null;
				if(null != templateString && !templateString.isEmpty())
				{
					templateString = templateString.replaceAll("''", "'");
				}
				if (isSubject)
				{
					freemarkerTemplate = new StringBuilder(templateString);
				}
				else
				{
					freemarkerTemplate = getTemplateString(templateString);
				}
				if (freemarkerTemplate == null)
				{
					return null;
				}
				stringLoader.putTemplate(notificationTemplate, freemarkerTemplate.toString());
				Configuration cfg = new Configuration();
				cfg.setTemplateLoader(stringLoader);
				Template template = cfg.getTemplate(notificationTemplate);

				Writer out = new StringWriter();
				template.process(rootMap, out);
				return out.toString();
			}
		}
		catch (IOException e)
		{
			OUT.error("Exception", e);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}

	/**
	 * @param notificationDTO
	 * @return
	 */
	private static Map<String, Object> setNotificationParamTOMap(MessageNotificationDTO notificationDTO)
	{
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put(NotificationConstant.USER_ID, CommonUtil.replaceXMLEntities(notificationDTO.getUserId()));
		rootMap.put(NotificationConstant.SYSTEM_GEN_PASSWORD, CommonUtil.replaceXMLEntities(notificationDTO.getUserPassword()));
		rootMap.put(NotificationConstant.USER_NAME, CommonUtil.replaceXMLEntities(notificationDTO.getUserName()));
		rootMap.put(NotificationConstant.WEB_URL, notificationDTO.getWebURL());
		rootMap.put(NotificationConstant.PARENT_NAME, notificationDTO.getParentName());
		rootMap.put(NotificationConstant.RECIPIENT_MAIL_IDS, getUniqueEmailIdsCommaSeparatedValue(notificationDTO.getRecipientMailIds()));
		rootMap.put("bodyData", notificationDTO.getBodyData());
		return rootMap;
	}

	/**
	 * Method to validate template parameters.
	 * 
	 * @param templateParamList
	 * @param rootMap
	 * @return
	 */
	private static boolean isValidTemplateParam(List<String> templateParamList, Map<String, Object> rootMap)
	{
		if (rootMap == null || rootMap.isEmpty())
			return false;
		for (String param : templateParamList)
		{
			if (rootMap.get(param) == null)
			{
				OUT.error(param + " is required information");
				return false;
			}
		}
		return true;
	}

	/**
	 * @param templateParam
	 * @return
	 */
	private static List<String> addAllParamTOList(String templateParam)
	{
		if (templateParam == null || templateParam.trim().isEmpty())
			return null;
		List<String> paramList = new ArrayList<String>();
		String[] templateParamArray = templateParam.split(",");
		for (int param = 0; param < templateParamArray.length; param++)
		{
			paramList.add(templateParamArray[param].trim());
		}
		return paramList;
	}

	/**
	 * @param templateString
	 * @return
	 */
	public static StringBuilder getTemplateString(String templateString)
	{
		try
		{
			Table table = new Table();
			table.setCellpadding("0");
			table.setCellspacing("0");
			table.setStyle(TABLE_STYLE);

			Tr trTemplate = new Tr();
			Td tdTemplate = new Td();
			tdTemplate.setStyle("text-align: left;");
			Div divTemplate = new Div();
			divTemplate.setStyle(DIV_STYLE);
			divTemplate.appendText(templateString);
			tdTemplate.appendChild(divTemplate);

			/*
			 * Div div = new Div();
			 * div.setStyle(DIV_STYLE);
			 * div.appendText("This is an auto generated email by Lodestar. Please do not reply to this message.");
			 * tdTemplate.appendChild(div);
			 */

			trTemplate.appendChild(tdTemplate);
			table.appendChild(trTemplate);

			String htmlStr = table.write();
			return new StringBuilder(htmlStr);
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		return null;
	}

	private static String getUniqueEmailIdsCommaSeparatedValue(String[] arrayValue)
	{
		if (arrayValue != null)
		{
			Set<String> emailIds = new HashSet<String>();
			StringBuilder commaSeparatedValue = new StringBuilder();
			for (String emailId : arrayValue)
			{
				if (!emailIds.contains(emailId.trim()))
				{
					emailIds.add(emailId.trim());

					if (!commaSeparatedValue.toString().trim().isEmpty())
					{
						commaSeparatedValue.append(",");
					}
					commaSeparatedValue.append(emailId.trim());
				}
			}

			return commaSeparatedValue.toString();
		}
		return null;
	}

	public static void main(String[] args)
	{
		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.NEW_USER.name());
		notificationDTO.setRoleTypeId(1);
		notificationDTO.setNew(true);
		notificationDTO.setUserName("aaaa");
		notificationDTO.setRecipientMailIds(new String[]
		{
			"kiran.rs@jamochatech.com"
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("UserName", "aaa");
		parameterMap.put("webURL", "sdasdfsafd");
		parameterMap.put("userPassword", "randomGeneratedPassword");
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);

	}
}
