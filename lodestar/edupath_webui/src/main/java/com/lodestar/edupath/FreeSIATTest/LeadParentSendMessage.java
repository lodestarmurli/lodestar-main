package com.lodestar.edupath.FreeSIATTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.APISService;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.EncryptionDecryptionData;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class LeadParentSendMessage extends BaseAction{
	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(LeadParentSendMessage.class);
	
	private String email;
	private String name;
	private String  contactno;
	
	
	private String pemailid;
	private String scontactno;
	private String semailid;
	private String pname;
	private String pcontactno;
	private String Paymentid;
	private int 	linksendsuccess=0;
	private String Source;
	private String LEADID;
	private String City;
	
	private String lid;
	
    private String campaigntype;
	
	

	public String getCampaigntype() {
		return campaigntype;
	}


	public void setCampaigntype(String campaigntype) {
		this.campaigntype = campaigntype;
	}
	

	public String getLid() {
		return lid;
	}


	public void setLid(String lid) {
		this.lid = lid;
	}


	public String getCity() {
		return City;
	}


	public void setCity(String city) {
		City = city;
	}


	public String getLEADID() {
		return LEADID;
	}


	public void setLEADID(String lEADID) {
		LEADID = lEADID;
	}


	public String getSource() {
		return Source;
	}


	public void setSource(String source) {
		Source = source;
	}


	public String getPaymentid() {
		return Paymentid;
	}


	public void setPaymentid(String paymentid) {
		Paymentid = paymentid;
	}


	public int getLinksendsuccess() {
		return linksendsuccess;
	}


	public void setLinksendsuccess(int linksendsuccess) {
		this.linksendsuccess = linksendsuccess;
	}


	public String getSemailid() {
		return semailid;
	}


	public void setSemailid(String semailid) {
		this.semailid = semailid;
	}


	public String getPemailid() {
		return pemailid;
	}


	public void setPemailid(String pemailid) {
		this.pemailid = pemailid;
	}


	public String getScontactno() {
		return scontactno;
	}


	public void setScontactno(String scontactno) {
		this.scontactno = scontactno;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public String getPcontactno() {
		return pcontactno;
	}


	public void setPcontactno(String pcontactno) {
		this.pcontactno = pcontactno;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContactno() {
		return contactno;
	}


	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


	public String execute()
	{
		OUT.debug("Inside LeadParentSendMessage class : execute method");
		
		
		try
		{
			if(email!=null && !email.trim().equals(""))
			{
				String TempLeadId=null;
				TempLeadId=CheckLeadPresentInZoho(email.trim());
				if(TempLeadId!=null && !TempLeadId.trim().equals(""))
				{
					LEADID=TempLeadId;
				}
				else
				{
					if(name!=null && !name.trim().equals("") && contactno!=null && !contactno.trim().equals("") && Source!=null && !Source.trim().equals("") && City!=null && !City.trim().equals(""))
					{
						LEADID=PushLeadDataInZoho(email,name,contactno,Source,City);
					}
					
				}
				
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			
		}
		
		
		
		return "success";
	}
	
	public String sendmessagesuccessfull()
	{
		OUT.debug("Inside LeadParentSendMessage class : sendmessagesuccessfull method");
		try
		{
			if(pemailid!=null && !pemailid.trim().equals(""))
			{
				if((scontactno!=null && !scontactno.trim().equals("")) || (semailid!=null && !semailid.trim().equals("")))
				{
					LeadParentDTO leadparent=new LeadParentDTO();
					if(pcontactno!=null && !pcontactno.trim().equals(""))
					{
						leadparent.setParentContactNo(pcontactno.trim());
					}
					
					if(pname!=null && !pname.trim().equals(""))
					{
						leadparent.setParentName(pname.trim());
					}
					
					if(scontactno!=null && !scontactno.trim().equals(""))
					{
						leadparent.setStudentContactNo(scontactno.trim());
					}
					
					if(semailid!=null && !semailid.trim().equals(""))
					{
						leadparent.setStudentEmailID(semailid.trim());
					}
					
					
					
					leadparent.setParentEmailID(pemailid);
					
					
					
					
					APISService apiservice=new APISService();
					
					int insertedid=apiservice.insertLeadParent(leadparent);
					
					if(insertedid!=0)
					{
						String Token=new EncryptionDecryptionData().Encrypt(String.valueOf(insertedid));
						OUT.info("Encrypted Token=====>"+Token);
						LeadParentDTO UpdateLeadToken=new LeadParentDTO();
						UpdateLeadToken.setId(insertedid);
						UpdateLeadToken.setToken(Token);
						
						apiservice.UpdateLeadToken(UpdateLeadToken);
						GlobalSettingDTO globalDTO = new GlobalSettingDTO();
						globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
						globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
						if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
						{
							String Link=globalDTO.getPropertyValue()+"/Test/SIATTest?token="+Token;
							int smssend=0;
							int emailsend=0;
							

							try
							{
								String apiKey = "apikey=" + "C1dgUv/47mU-ojWUSihLWzHxF1JdNrvqKTf1z2BA1C";
								String message = "&message=" + "Deat Student,\nYou have been registered by your parent to take a free Vocational Personality Test. This will help you make future education and career decisions. Take the test to discover your True Career Path! \n Click here "+Link+" to take the test.";
								message=message+"\n It takes only 20-25 mins to complete this. This is not a subject or aptitude test. \n Please complete the test within 15 days of registration. \n Regards,\n Team Lodestar";
								String sender = "&sender=" + "TXTLCL";
								String numbers = "&numbers=";
								
								if(scontactno!=null && !scontactno.trim().equals(""))
								{
								 numbers = numbers + scontactno.trim();
								 
								    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
									String data = apiKey + numbers + message + sender;
									conn.setDoOutput(true);
									conn.setRequestMethod("POST");
									conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
									conn.getOutputStream().write(data.getBytes("UTF-8"));
									final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
									final StringBuffer stringBuffer = new StringBuffer();
									String line;
									while ((line = rd.readLine()) != null) {
										stringBuffer.append(line);
									}
									rd.close();
									JSONObject jsonDataObject = new JSONObject(stringBuffer.toString());
								 
									OUT.info("response text local api==>"+stringBuffer.toString());
									if(jsonDataObject!=null && jsonDataObject.has("status"))
									{
										if(jsonDataObject.get("status").equals("success"))
										{
											smssend=1;
										}
									}
								 
								 
								}
								
								
								
							}
							catch (Exception e1)
							{
								OUT.error("Exception send sms", e1);
								
							}
							
							
							try
							{
								if(semailid!=null && !semailid.trim().equals(""))
								{
									PasswordGeneratorService.sendleadparentmessage("student",semailid.trim(),2,Link);
									
									emailsend=1;
								}
								
								
							}
							catch (Exception e2)
							{
								OUT.error("Exception send email", e2);
								
							}
							
							if(smssend==1 || emailsend==1)
							{
								linksendsuccess=1;
								PasswordGeneratorService.sendleadparentmessageToLodestar("Admin",scontactno,semailid,pemailid.trim(),pname,pcontactno,2);
							}
							else
							{
								linksendsuccess=2;
							}
							
							OUT.info("Generated Link=====>"+Link);
							
							
							if(lid!=null && !lid.trim().equals(""))
							{
								UpdateLeadDataInZoho(semailid,scontactno,lid);
							}
							
							
						}
						else
						{
							OUT.error("error 4");
							return "error";
						}
					}
					else
					{
						OUT.error("error 3");
						return "error";
					}
					
				}
				else
				{
					OUT.error("error 2");
					return "error";
				}
				
				
			}
			else
			{
				OUT.error("error 1");
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
	
	
	public String sendtestlinksuccessfull()
	{
		OUT.debug("Inside LeadParentSendMessage class : sendtestlinksuccessfull method");
		try
		{
			if(pemailid!=null && !pemailid.trim().equals("") && Paymentid!=null && !Paymentid.trim().equals(""))
			{
				if((scontactno!=null && !scontactno.trim().equals("")) || (semailid!=null && !semailid.trim().equals("")))
				{
					LeadParentDTO leadparent=new LeadParentDTO();
					if(pcontactno!=null && !pcontactno.trim().equals(""))
					{
						leadparent.setParentContactNo(pcontactno.trim());
					}
					
					if(pname!=null && !pname.trim().equals(""))
					{
						leadparent.setParentName(pname.trim());
					}
					
					if(scontactno!=null && !scontactno.trim().equals(""))
					{
						leadparent.setStudentContactNo(scontactno.trim());
					}
					
					if(semailid!=null && !semailid.trim().equals(""))
					{
						leadparent.setStudentEmailID(semailid.trim());
					}
					
					leadparent.setPaymentid(Paymentid);
					
					leadparent.setParentEmailID(pemailid);
					
					
					
					
					APISService apiservice=new APISService();
					
					int insertedid=apiservice.insertLeadParent(leadparent);
					
					if(insertedid!=0)
					{
						String Token=new EncryptionDecryptionData().Encrypt(String.valueOf(insertedid));
						OUT.info("Encrypted Token=====>"+Token);
						LeadParentDTO UpdateLeadToken=new LeadParentDTO();
						UpdateLeadToken.setId(insertedid);
						UpdateLeadToken.setToken(Token);
						
						apiservice.UpdateLeadToken(UpdateLeadToken);
						GlobalSettingDTO globalDTO = new GlobalSettingDTO();
						globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
						globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
						if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
						{
							String Link=globalDTO.getPropertyValue()+"/Test/SIATTestLink?token="+Token;
							int smssend=0;
							int emailsend=0;
							

							try
							{
								String apiKey = "apikey=" + "C1dgUv/47mU-ojWUSihLWzHxF1JdNrvqKTf1z2BA1C";
								String message = "&message=" + "Deat Student,\nYou have been registered by your parent to take a Vocational Personality Test. This will help you make future education and career decisions. Take the test to discover your True Career Path!\n Click here "+Link+" to take the test.";
								message=message+"\n It takes only 20-25 mins to complete this. This is not a subject test. \n Please complete the test to schedule an hour discussion with our career expert. \n Regards,\n Team Lodestar";
								String sender = "&sender=" + "TXTLCL";
								String numbers = "&numbers=";
								
								if(scontactno!=null && !scontactno.trim().equals(""))
								{
								 numbers = numbers + scontactno.trim();
								 
								    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
									String data = apiKey + numbers + message + sender;
									conn.setDoOutput(true);
									conn.setRequestMethod("POST");
									conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
									conn.getOutputStream().write(data.getBytes("UTF-8"));
									final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
									final StringBuffer stringBuffer = new StringBuffer();
									String line;
									while ((line = rd.readLine()) != null) {
										stringBuffer.append(line);
									}
									rd.close();
									JSONObject jsonDataObject = new JSONObject(stringBuffer.toString());
								 
									OUT.info("response text local api==>"+stringBuffer.toString());
									if(jsonDataObject!=null && jsonDataObject.has("status"))
									{
										if(jsonDataObject.get("status").equals("success"))
										{
											smssend=1;
										}
									}
								 
								 
								}
								
								
								
							}
							catch (Exception e1)
							{
								OUT.error("Exception send sms", e1);
								
							}
							
							
							try
							{
								if(semailid!=null && !semailid.trim().equals(""))
								{
									PasswordGeneratorService.sendleadparentmessageHDFC("student",semailid.trim(),2,Link);
									
									emailsend=1;
								}
								
								
							}
							catch (Exception e2)
							{
								OUT.error("Exception send email", e2);
								
							}
							
							if(smssend==1 || emailsend==1)
							{
								linksendsuccess=1;
								PasswordGeneratorService.sendleadparentmessageToLodestarHDFC("Admin",scontactno,semailid,pemailid.trim(),pname,pcontactno,2);
							}
							else
							{
								linksendsuccess=2;
							}
							
							OUT.info("Generated Link=====>"+Link);
						}
						else
						{
							OUT.error("error 4");
							return "error";
						}
					}
					else
					{
						OUT.error("error 3");
						return "error";
					}
					
				}
				else
				{
					OUT.error("error 2");
					return "error";
				}
				
				
			}
			else
			{
				OUT.error("error 1");
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
	
	
	private String CheckLeadPresentInZoho(String EmailID)
	{
		OUT.debug("Inside LeadParentSendMessage class : CheckLeadPresentInZoho method");
		
		String LeadID=null;
		
		try
		{
			GlobalSettingDTO ZohoCRMAPIURl = new GlobalSettingDTO();
			GlobalSettingDTO ZohoCRMAPIKey = new GlobalSettingDTO();
			
			
			ZohoCRMAPIURl.setPropertyName(ApplicationConstants.GlobalSettings.ZohoCRMAPIURl.getProperty());
			ZohoCRMAPIURl = new GlobalSttingDAO().getPropertesValueByName(ZohoCRMAPIURl);
			
			ZohoCRMAPIKey.setPropertyName(ApplicationConstants.GlobalSettings.ZohoCRMAPIKey.getProperty());
			ZohoCRMAPIKey = new GlobalSttingDAO().getPropertesValueByName(ZohoCRMAPIKey);
			
			
			String URL=ZohoCRMAPIURl.getPropertyValue();
			String KEY=ZohoCRMAPIKey.getPropertyValue();
			
			
			String JsonParameter="(Email:"+EmailID+")";
			
			 HttpURLConnection conn = (HttpURLConnection) new URL(URL+"?").openConnection();
			 String data = "authtoken="+KEY+"&scope=crmapi&criteria="+JsonParameter;
			 conn.setDoOutput(true);
			 conn.setRequestMethod("POST");
			 conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			 conn.getOutputStream().write(data.getBytes("UTF-8"));
			 final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			 final StringBuffer stringBuffer = new StringBuffer();
			 String line;
			 while ((line = rd.readLine()) != null) {
			 stringBuffer.append(line);
			 }
			 rd.close();
			 JSONObject jsonDataObject = new JSONObject(stringBuffer.toString());
			 
				OUT.info("response Zoho CRM Search api==>"+stringBuffer.toString());
				
				if(jsonDataObject!=null && jsonDataObject.has("response"))
				{
					JSONObject jsonResponseObject=jsonDataObject.getJSONObject("response");
					
					if(jsonResponseObject!=null && jsonResponseObject.has("result"))
					{
						JSONObject jsonLeadsObject=jsonResponseObject.getJSONObject("result");
						
						
						if(jsonLeadsObject!=null && jsonLeadsObject.has("Leads"))
						{
							JSONObject jsonrowObject=jsonLeadsObject.getJSONObject("Leads");
							
							
							if(jsonrowObject!=null && jsonrowObject.has("row"))
							{
								JSONObject jsonFLObject=jsonrowObject.getJSONObject("row");
								
								
								
								if(jsonFLObject!=null && jsonFLObject.has("FL"))
								{
									JSONArray  jsonDataArray =jsonFLObject.getJSONArray("FL");
									
									if(jsonDataArray!=null)
									{
										
										 for (int i = 0; i < jsonDataArray.length(); i++)
										 {
											 JSONObject item = jsonDataArray.getJSONObject(i);
											 if(item!=null && item.has("val") && item.get("val").equals("LEADID"))
											 {
												 LeadID=item.getString("content");
												 
												 break;
											 }
										 }
									}
									
									
								}
								
							}
						}
					}
					
					
				}
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			
		}
		OUT.debug("LeadID====>"+LeadID);
		
		
		return LeadID;
	}
	
	private String PushLeadDataInZoho(String EmailID,String LeadName,String leadPhone,String Leadsource,String LeadCity)
	{
		OUT.debug("Inside LeadParentSendMessage class : PushLeadDataInZoho method");
		
		String LeadID=null;
		
		try
		{
			GlobalSettingDTO ZohoCRMAPIURl = new GlobalSettingDTO();
			GlobalSettingDTO ZohoCRMAPIKey = new GlobalSettingDTO();
			
			
			ZohoCRMAPIURl.setPropertyName(ApplicationConstants.GlobalSettings.ZohoCRMInsertAPIURl.getProperty());
			ZohoCRMAPIURl = new GlobalSttingDAO().getPropertesValueByName(ZohoCRMAPIURl);
			
			ZohoCRMAPIKey.setPropertyName(ApplicationConstants.GlobalSettings.ZohoCRMAPIKey.getProperty());
			ZohoCRMAPIKey = new GlobalSttingDAO().getPropertesValueByName(ZohoCRMAPIKey);
			
			
			String URL=ZohoCRMAPIURl.getPropertyValue();
			String KEY=ZohoCRMAPIKey.getPropertyValue();
			
			
			String XMLParameter="<Leads>" + 
					"<row no=\"1\">" + 
					"<FL val=\"Email\">"+EmailID+"</FL>" + 
					"<FL val=\"Lead's Status\">To be Called</FL>" + 
					"<FL val=\"Lead Source\">"+Leadsource+"</FL>" + 
					"<FL val=\"Last Name\">"+LeadName+"</FL>" + 
					"<FL val=\"Father's Name\">"+LeadName+"</FL>" +
					"<FL val=\"Father's Email\">"+EmailID+"</FL>" +
					"<FL val=\"Father's Mobile No\">"+leadPhone+"</FL>" +
					"<FL val=\"City\">"+LeadCity+"</FL>" + 
					"</row>" + 
					"</Leads>";
			
			 HttpURLConnection conn = (HttpURLConnection) new URL(URL+"?").openConnection();
			 String data = "authtoken="+KEY+"&scope=crmapi&xmlData="+XMLParameter;
			 conn.setDoOutput(true);
			 conn.setRequestMethod("POST");
			 conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			 conn.getOutputStream().write(data.getBytes("UTF-8"));
			 final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			 final StringBuffer stringBuffer = new StringBuffer();
			 String line;
			 while ((line = rd.readLine()) != null) {
			 stringBuffer.append(line);
			 }
			 rd.close();
			 
			 
				OUT.info("response Zoho CRM Search api==>"+stringBuffer.toString());
				JSONObject jsonDataObject = new JSONObject(stringBuffer.toString());
				if(jsonDataObject!=null && jsonDataObject.has("response"))
				{
					JSONObject jsonResponseObject=jsonDataObject.getJSONObject("response");
					
					if(jsonResponseObject!=null && jsonResponseObject.has("result"))
					{
						JSONObject jsonrecorddetailsObject=jsonResponseObject.getJSONObject("result");
						
						
						if(jsonrecorddetailsObject!=null && jsonrecorddetailsObject.has("recorddetail"))
						{
							JSONObject jsonFLObject=jsonrecorddetailsObject.getJSONObject("recorddetail");
							
							
								if(jsonFLObject!=null && jsonFLObject.has("FL"))
								{
									JSONArray  jsonDataArray =jsonFLObject.getJSONArray("FL");
									
									if(jsonDataArray!=null)
									{
										
										 for (int i = 0; i < jsonDataArray.length(); i++)
										 {
											 JSONObject item = jsonDataArray.getJSONObject(i);
											 if(item!=null && item.has("val") && item.get("val").equals("Id"))
											 {
												 LeadID=item.getString("content");
												 
												 break;
											 }
										 }
									}
									
									
								}
							
							
						}
					}
					
					
				}
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			
		}
		OUT.debug("LeadID====>"+LeadID);
		
		
		return LeadID;
	}
	
	private void UpdateLeadDataInZoho(String EmailID,String leadPhone,String LeadID)
	{
		OUT.debug("Inside LeadParentSendMessage class : UpdateLeadDataInZoho method");
		
		
		
		try
		{
			GlobalSettingDTO ZohoCRMAPIURl = new GlobalSettingDTO();
			GlobalSettingDTO ZohoCRMAPIKey = new GlobalSettingDTO();
			
			
			ZohoCRMAPIURl.setPropertyName(ApplicationConstants.GlobalSettings.ZohoCRMUpdateAPIURl.getProperty());
			ZohoCRMAPIURl = new GlobalSttingDAO().getPropertesValueByName(ZohoCRMAPIURl);
			
			ZohoCRMAPIKey.setPropertyName(ApplicationConstants.GlobalSettings.ZohoCRMAPIKey.getProperty());
			ZohoCRMAPIKey = new GlobalSttingDAO().getPropertesValueByName(ZohoCRMAPIKey);
			
			
			String URL=ZohoCRMAPIURl.getPropertyValue();
			String KEY=ZohoCRMAPIKey.getPropertyValue();
			
			
			String XMLParameter="<Leads>" + 
					"<row no=\"1\">" + 
					"<FL val=\"Secondary Email\">"+EmailID+"</FL>" + 
					"<FL val=\"Student's Mobile No\">"+leadPhone+"</FL>" + 
					"</row>" + 
					"</Leads>";
			
			 HttpURLConnection conn = (HttpURLConnection) new URL(URL+"?").openConnection();
			 String data = "authtoken="+KEY+"&scope=crmapi&id="+LeadID+"&xmlData="+XMLParameter;
			 conn.setDoOutput(true);
			 conn.setRequestMethod("POST");
			 conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			 conn.getOutputStream().write(data.getBytes("UTF-8"));
			 final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			 final StringBuffer stringBuffer = new StringBuffer();
			 String line;
			 while ((line = rd.readLine()) != null) {
			 stringBuffer.append(line);
			 }
			 rd.close();
			 
			 
				OUT.info("response Zoho CRM Search api==>"+stringBuffer.toString());
				/*JSONObject jsonDataObject = new JSONObject(stringBuffer.toString());
				if(jsonDataObject!=null && jsonDataObject.has("response"))
				{
					JSONObject jsonResponseObject=jsonDataObject.getJSONObject("response");
					
					if(jsonResponseObject!=null && jsonResponseObject.has("result"))
					{
						JSONObject jsonrecorddetailsObject=jsonResponseObject.getJSONObject("result");
						
						
						if(jsonrecorddetailsObject!=null && jsonrecorddetailsObject.has("recorddetail"))
						{
							JSONObject jsonFLObject=jsonrecorddetailsObject.getJSONObject("recorddetail");
							
							
								if(jsonFLObject!=null && jsonFLObject.has("FL"))
								{
									JSONArray  jsonDataArray =jsonFLObject.getJSONArray("FL");
									
									if(jsonDataArray!=null)
									{
										
										 for (int i = 0; i < jsonDataArray.length(); i++)
										 {
											 JSONObject item = jsonDataArray.getJSONObject(i);
											 if(item!=null && item.has("val") && item.get("val").equals("Id"))
											 {
												 LeadID=item.getString("content");
												 
												 break;
											 }
										 }
									}
									
									
								}
							
							
						}
					}
					
					
				}*/
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			
		}
		
	}
	
	
	public String sendmessagesuccessfulltochild()
	{
		OUT.debug("Inside LeadParentSendMessage class : sendmessagesuccessfulltochild method");
		try
		{
			if(pemailid!=null && !pemailid.trim().equals(""))
			{
				if((scontactno!=null && !scontactno.trim().equals("")) || (semailid!=null && !semailid.trim().equals("")))
				{
					LeadParentDTO leadparent=new LeadParentDTO();
					if(pcontactno!=null && !pcontactno.trim().equals(""))
					{
						leadparent.setParentContactNo(pcontactno.trim());
					}
					
					if(pname!=null && !pname.trim().equals(""))
					{
						leadparent.setParentName(pname.trim());
					}
					
					if(scontactno!=null && !scontactno.trim().equals(""))
					{
						leadparent.setStudentContactNo(scontactno.trim());
					}
					
					if(semailid!=null && !semailid.trim().equals(""))
					{
						leadparent.setStudentEmailID(semailid.trim());
					}
					
					
					
					leadparent.setParentEmailID(pemailid);
					
					
					
					
					APISService apiservice=new APISService();
					
					int insertedid=apiservice.insertLeadParent(leadparent);
					
					if(insertedid!=0)
					{
						String Token=new EncryptionDecryptionData().Encrypt(String.valueOf(insertedid));
						OUT.info("Encrypted Token=====>"+Token);
						LeadParentDTO UpdateLeadToken=new LeadParentDTO();
						UpdateLeadToken.setId(insertedid);
						UpdateLeadToken.setToken(Token);
						
						apiservice.UpdateLeadToken(UpdateLeadToken);
						GlobalSettingDTO globalDTO = new GlobalSettingDTO();
						globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
						globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
						if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
						{
							String Link=globalDTO.getPropertyValue()+"/Test/SIATTest?token="+Token;
							int smssend=0;
							int emailsend=0;
							

							try
							{
								String apiKey = "apikey=" + "C1dgUv/47mU-ojWUSihLWzHxF1JdNrvqKTf1z2BA1C";
								String message = "&message=" + "Deat Student,\nYou have been registered by your parent to take a free Vocational Personality Test. This will help you make future education and career decisions. Take the test to discover your True Career Path! \n Click here "+Link+" to take the test.";
								message=message+"\n It takes only 20-25 mins to complete this. This is not a subject or aptitude test. \n Please complete the test within 15 days of registration. \n Regards,\n Team Lodestar";
								String sender = "&sender=" + "TXTLCL";
								String numbers = "&numbers=";
								
								if(scontactno!=null && !scontactno.trim().equals(""))
								{
								 numbers = numbers + scontactno.trim();
								 
								    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
									String data = apiKey + numbers + message + sender;
									conn.setDoOutput(true);
									conn.setRequestMethod("POST");
									conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
									conn.getOutputStream().write(data.getBytes("UTF-8"));
									final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
									final StringBuffer stringBuffer = new StringBuffer();
									String line;
									while ((line = rd.readLine()) != null) {
										stringBuffer.append(line);
									}
									rd.close();
									JSONObject jsonDataObject = new JSONObject(stringBuffer.toString());
								 
									OUT.info("response text local api==>"+stringBuffer.toString());
									if(jsonDataObject!=null && jsonDataObject.has("status"))
									{
										if(jsonDataObject.get("status").equals("success"))
										{
											smssend=1;
										}
									}
								 
								 
								}
								
								
								
							}
							catch (Exception e1)
							{
								OUT.error("Exception send sms", e1);
								
							}
							
							
							try
							{
								if(semailid!=null && !semailid.trim().equals(""))
								{
									PasswordGeneratorService.sendleadparentmessage("student",semailid.trim(),2,Link);
									
									emailsend=1;
								}
								
								
							}
							catch (Exception e2)
							{
								OUT.error("Exception send email", e2);
								
							}
							
							if(smssend==1 || emailsend==1)
							{
								linksendsuccess=1;
								String adminemails="archisman@lodestar.guru" +","+"varun@lodestar.guru";
								String Subject="SIAT Test Data";
								if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("1"))
								{
									Subject="SIAT Data_Facebook Campaign.";
								}
								else if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("2"))
								{
									Subject="SIAT Data_Google Adwords & Re-targeting.";
								}
								else if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("3"))
								{
									Subject="SIAT Data_Email & Others.";
								}
								else if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("4"))
								{
									Subject="SIAT Data_SMS Campaign.";
								}
							    else if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("5"))
								{
									Subject="SIAT Data_Email_Sreekanth.";
									adminemails="archisman@lodestar.guru" +","+"shreekanth@ymail.com"+","+"vineet.khare@astechnosoft.net";
								}
								
								if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("5"))
								{
									PasswordGeneratorService.sendleadparentmessageToLodestarCampaign1("Admin",scontactno,semailid,pemailid.trim(),pname,pcontactno,2,Subject,adminemails);
								}
								else
								{
									PasswordGeneratorService.sendleadparentmessageToLodestarCampaign("Admin",scontactno,semailid,pemailid.trim(),pname,pcontactno,2,Subject,adminemails);
								}
								
								
							}
							else
							{
								linksendsuccess=2;
							}
							
							OUT.info("Generated Link=====>"+Link);
							
							
//							if(lid!=null && !lid.trim().equals(""))
//							{
//								UpdateLeadDataInZoho(semailid,scontactno,lid);
//							}
							
							
						}
						else
						{
							OUT.error("error 4");
							return "error";
						}
					}
					else
					{
						OUT.error("error 3");
						return "error";
					}
					
				}
				else
				{
					OUT.error("error 2");
					return "error";
				}
				
				
			}
			else
			{
				OUT.error("error 1");
				return "error";
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return "error";
		}
		
		if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("1"))
		{
			return "fbsuccess";
		}
		else if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("2"))
		{
			return "gsuccess";
		}
		else if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("3"))
		{
			return "ebsuccess";
		}
		else if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("4"))
		{
			return "smssuccess";
		}
		else if(campaigntype!=null && !campaigntype.trim().equals("") && campaigntype.trim().equals("5"))
		{
			return "essuccess";
		}
		else
		{
			return "success";
		}
	}
	
	
	
	
}
