package com.lodestar.edupath.Psychometrictests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.APISService;
import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerStudentDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.tum.questionnaire.service.InterestService;
import com.lodestar.edupath.tum.service.StudentTUMService;
import com.opensymphony.xwork2.ActionContext;


public class PsychometricTests extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(PsychometricTests.class);
	private boolean isInterestCompleted;
	private int pageAction=1;
	private String				answerList;
	private int					userid;
	private String              studentAnswerList;
	private String				puin;
	private String				suin;
	private String 				logo;
	private int 				isregister;
	
	public int getIsregister() {
		return isregister;
	}

	public void setIsregister(int isregister) {
		this.isregister = isregister;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPuin() {
		return puin;
	}

	public void setPuin(String puin) {
		this.puin = puin;
	}

	public String getSuin() {
		return suin;
	}

	public void setSuin(String suin) {
		this.suin = suin;
	}

	public String getStudentAnswerList() {
		return studentAnswerList;
	}

	public void setStudentAnswerList(String studentAnswerList) {
		this.studentAnswerList = studentAnswerList;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAnswerList() {
		return answerList;
	}

	public void setAnswerList(String answerList) {
		this.answerList = answerList;
	}

	public String execute()
	{
		OUT.debug("Inside PsychometricTests class : execute method");
		try
		{
			PartnerStudentDetailDTO partnerstudent=new PartnerStudentDetailDTO();
			PartnerStudentDetailDTO partnerstudentDetails=new PartnerStudentDetailDTO();
			PartnerDetailsDTO partnerdetails=new PartnerDetailsDTO();
			if(puin!=null && suin!=null && !puin.equals("") && !suin.equals(""))
			{
			
				partnerstudent.setPartnerUIN(puin.toUpperCase());
				partnerstudent.setStudenUIN(suin);
				
				APISService apiservice=new APISService();
				
				partnerstudentDetails= apiservice.GetPartnetstudentDetails(partnerstudent);
				
				partnerdetails= apiservice.GetPartnerDetails(puin.toUpperCase());
				if(partnerdetails!=null && partnerdetails.getLogpath()!=null &&  !partnerdetails.getLogpath().trim().equals(""))
				{
					logo=partnerdetails.getLogpath();
				}
				else
				{
					logo="lodestar_logo.gif";
				}
				
				
				if(partnerstudentDetails!=null && partnerstudentDetails.getLDID()!=null && !partnerstudentDetails.getLDID().equals(""))
				{
					userid=Integer.parseInt(partnerstudentDetails.getLDID().trim().substring(2));
				
					InterestService		service=new InterestService();
					
					isInterestCompleted = service.isIntrestCompleted(userid);
					
					JSONArray jsonArray = null;
					if (!isInterestCompleted)
					{
						jsonArray = service.getStudentCGTBySection(userid);
					}
					
					isregister=partnerstudentDetails.getIsRegister();
					
					
					 pageAction = 1;
					 
					 if(jsonArray!=null)
					 {
					 
						 	studentAnswerList=jsonArray.toString();
					 }
					 
				}
				else
				{
					//Check Partner Present if present create new student
					
					if(partnerdetails!=null)
					{
						
						
						
						try
						{
							
							
							String authurl = getAuthenticatedUrl(suin);
							
							System.out.println("Authurl = " + authurl);
							
							
							String url = authurl;
							URL obj = new URL(url);
							HttpURLConnection con = (HttpURLConnection) obj.openConnection();
							con.setRequestMethod("GET");
							con.setRequestProperty("Content-Type:", "application/json");
							OUT.error("Response Code From Steps API====>"+con.getResponseCode());
							if(con.getResponseCode()==200)
							{
							
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
								System.out.println("=====>"+sb.toString().trim());
								JSONArray  jsonDataArray = new JSONArray(sb.toString().trim());
								JSONObject jsonDataObject=jsonDataArray.getJSONObject(0);
								if(jsonDataObject!=null)
								{
									StudentDetailsDTO studentDTO=new StudentDetailsDTO();
									String newsuin=null;
									String CityStringData=null;
									boolean FatherEmailid=true;
									boolean Fathercontactno=true;
									boolean Fathername=true;
									
									
									
									if(jsonDataObject.has("first_name"))
									{
									
										if(!jsonDataObject.getString("first_name").isEmpty() && !jsonDataObject.getString("first_name").equals(""))
										{
											studentDTO.setName(jsonDataObject.getString("first_name").trim());
										}
									}
									
									
									
									if(jsonDataObject.has("name"))
									{
									
										if(!jsonDataObject.getString("name").isEmpty() && !jsonDataObject.getString("name").equals(""))
										{
											newsuin=jsonDataObject.getString("name").trim();
										}
									}
									
									
									if(jsonDataObject.has("gender"))
									{
									
										if(!jsonDataObject.getString("gender").isEmpty() && !jsonDataObject.getString("gender").equals(""))
										{
											
											if(jsonDataObject.getString("gender").trim()=="female")
											{
												studentDTO.setGender("F");
											}
											else
											{
												studentDTO.setGender("M");
											}
											
											
										}
									}
									
									
									if(jsonDataObject.has("city"))
									{
									
										if(!jsonDataObject.getString("city").isEmpty() && !jsonDataObject.getString("city").equals(""))
										{
											CityStringData=jsonDataObject.getString("city");
										}
									}
									
									
									studentDTO.setClassId(Integer.parseInt("1"));
									studentDTO.setSchoolId(1);
									
									
									if(jsonDataObject.has("first_name"))
									{
									
										if(!jsonDataObject.getString("first_name").isEmpty() && !jsonDataObject.getString("first_name").equals(""))
										{
									
											studentDTO.setFatherName(jsonDataObject.getString("first_name").trim()+" Parent");
											
											studentDTO.setFathername(jsonDataObject.getString("first_name").trim()+" Parent");
											
											Fathername=false;
											
										}
									}
											
									if(jsonDataObject.has("mail"))
									{
									
										if(!jsonDataObject.getString("mail").isEmpty() && !jsonDataObject.getString("mail").equals(""))
										{
											studentDTO.setFatherEmailId(jsonDataObject.getString("mail"));
											studentDTO.setFatheremailId(jsonDataObject.getString("mail"));
											
											
											FatherEmailid=false;
										}
									}
									
									
									if(jsonDataObject.has("mobile"))
									{
									
										if(!jsonDataObject.getString("mobile").isEmpty() && !jsonDataObject.getString("mobile").equals(""))
										{
											studentDTO.setFathercontactno(jsonDataObject.getString("mobile"));
											
											studentDTO.setContactNumber(jsonDataObject.getString("mobile"));
											
											Fathercontactno=false;
										
										}
									}
									
									
									studentDTO.setStudentType(StudentTypeEnum.TRIAL);
									
									EActionStatus RequiredFieldValidation=ValidationField(studentDTO);
									if(RequiredFieldValidation==EActionStatus.SUCCESS && newsuin!=null && !newsuin.equals(""))
									{
										
										EActionStatus DuplicationCheck=EActionStatus.SUCCESS;//ValidateDuplication(studentDTO);
										
										if(DuplicationCheck==EActionStatus.SUCCESS)
										{
											//APISService apiservice=new APISService();
											
											EActionStatus status= apiservice.insertStudent(studentDTO, partnerdetails,CityStringData,newsuin);
											
											if (status == EActionStatus.SUCCESS)
											{
												//Status="SUCCESS";
												//Message="Student Creation Successfuly";
												PartnerStudentDetailDTO partnerstudentDetails1=new PartnerStudentDetailDTO();
												partnerstudentDetails1= apiservice.GetPartnetstudentDetails(partnerstudent);
												
												if(partnerstudentDetails1!=null && partnerstudentDetails1.getLDID()!=null && !partnerstudentDetails1.getLDID().equals(""))
												{
													userid=Integer.parseInt(partnerstudentDetails1.getLDID().trim().substring(2));
												
													InterestService		service=new InterestService();
													
													isInterestCompleted = service.isIntrestCompleted(userid);
													
													JSONArray jsonArray = null;
													if (!isInterestCompleted)
													{
														jsonArray = service.getStudentCGTBySection(userid);
													}
													
													 pageAction = 1;
													 
													 if(jsonArray!=null)
													 {
													 
														 	studentAnswerList=jsonArray.toString();
													 }
													 
												}
												else
												{
													OUT.error("Student Creation Failed");
													return "error";
												}
											}
											else
											{
												//Status="ERROR";
												//Message="Student Creation Failed";
												OUT.error("Student Creation Failed");
												return "error";
											}
										}
										else
										{
											//Status="ERROR";
											//Message="Duplicate Student.";
											//Studentuin="";
											OUT.error("Duplicate Student");
											return "error";
										}
									}
									else
									{
										//Status="ERROR";
										//Message="Required Field Validation Failed Level 1";
										//Studentuin="";
										OUT.error("Required Field Validation Failed Level 1");
										return "error";
									}
								}
								
								
							}
							else
							{
								OUT.error("Error In Steps API");
								return "error";
							}
							
						}
						catch (Exception e)
						{
							OUT.error("Authentication error for Steps API Exception", e);
							return "error";
						}
						
					}
					else
					{
						OUT.debug("Partner not found Error");
						return "error";
					}
					
					
					
				}
			}
			else
			{
				OUT.debug("Second Error");
				return "error";
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return "error";
		}
		return SUCCESS;
	}
	public String insert()
	{
		OUT.debug("Inside PsychometricTests class : insert method");
		try
		{
			InterestService		service=new InterestService();
			if(answerList!=null)
			{
				
				boolean result = service.insertAnswer(answerList, userid);
				StudentTUMService tumservice = new StudentTUMService();
				tumservice.updateTUMSession(userid);
				isInterestCompleted=true;
			}
			else
			{
				isInterestCompleted=true;
			}
			PartnerDetailsDTO partnerdetails=new PartnerDetailsDTO();
			APISService apiservice=new APISService();
			if(puin!=null && suin!=null && !puin.equals("") && !suin.equals(""))
			{
				PartnerStudentDetailDTO partnerstudentDetails=new PartnerStudentDetailDTO();
				PartnerStudentDetailDTO partnerstudent=new PartnerStudentDetailDTO();
				partnerstudent.setPartnerUIN(puin.toUpperCase());
				partnerstudent.setStudenUIN(suin);
				
				partnerstudentDetails= apiservice.GetPartnetstudentDetails(partnerstudent);
				
				if(partnerstudentDetails!=null && partnerstudentDetails.getLDID()!=null && !partnerstudentDetails.getLDID().equals(""))
				{
					isregister=partnerstudentDetails.getIsRegister();
				}
				
				
				
				
				
				partnerdetails= apiservice.GetPartnerDetails(puin.toUpperCase());
				if(partnerdetails!=null && partnerdetails.getLogpath()!=null &&  !partnerdetails.getLogpath().trim().equals(""))
				{
					logo=partnerdetails.getLogpath();
				}
				else
				{
					logo="lodestar_logo.gif";
				}
			}
			else
			{
				logo="lodestar_logo.gif";
			}
			
			
			
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return "success";
	}
	public String register()
	{
		OUT.debug("Inside PsychometricTests class : register method");
		try
		{
			
			
			isInterestCompleted=true;
			
			PartnerDetailsDTO partnerdetails=new PartnerDetailsDTO();
			APISService apiservice=new APISService();
			if(puin!=null && suin!=null && !puin.equals("") && !suin.equals(""))
			{
				PartnerStudentDetailDTO partnerstudentDetails=new PartnerStudentDetailDTO();
				PartnerStudentDetailDTO partnerstudent=new PartnerStudentDetailDTO();
				partnerstudent.setPartnerUIN(puin.toUpperCase());
				partnerstudent.setStudenUIN(suin);
				
				partnerstudentDetails= apiservice.GetPartnetstudentDetails(partnerstudent);
				
				if(partnerstudentDetails!=null && partnerstudentDetails.getLDID()!=null && !partnerstudentDetails.getLDID().equals(""))
				{
					
					apiservice.registerstudentforcall(partnerstudentDetails.getId());
					
					
					isregister=1;
				}
				
				
				
				
				
				partnerdetails= apiservice.GetPartnerDetails(puin.toUpperCase());
				if(partnerdetails!=null && partnerdetails.getLogpath()!=null &&  !partnerdetails.getLogpath().trim().equals(""))
				{
					logo=partnerdetails.getLogpath();
				}
				else
				{
					logo="lodestar_logo.gif";
				}
			}
			else
			{
				logo="lodestar_logo.gif";
			}
			
			
			
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return "success";
	}

	
	
	
	
	
	public static String getAuthenticatedUrl(String Suin)
	{
		String finalUrl = "";
		String uri="http://admin.learn.allindiasteps.com/api/getuserinfo?username="+Suin;
		String requestType="GET";
		String CLASSNAME = "AuthUtils";
		String strAPPKEY = "abdjsbdwddasjbdste";
		String strAPPSECRET = "ldfvnkjdbckjbdste";
		String strTIMEOUT = "3000";
		String strENCODINGALGORITHM = "HmacSHA1";
		String strURLENCODING = "UTF-8";
		String strPORTALDOMAIN = "http://admin.learn.allindiasteps.com";
		try
		{
			String queryString = "";
			String domainName = "";

			if (strPORTALDOMAIN != null && uri.contains(strPORTALDOMAIN))
			{
				domainName = strPORTALDOMAIN;
				uri = uri.replaceFirst(domainName, "");
			}
			
			if (uri.contains("?"))
			{
				// extract querystring and & to add access token
				queryString = uri.substring(uri.indexOf("?") + 1) + "&";
				uri = uri.substring(0, uri.indexOf("?"));
			}

			queryString += "accesskey="+ URLEncoder.encode(strAPPKEY, strURLENCODING);
			queryString += "&timestamp=" + String.valueOf(System.currentTimeMillis() / 1000 + Integer.parseInt(strTIMEOUT));

			String stringtoSign = requestType + uri + "?" + queryString;
			// Get an hmac_sha1 key from the raw key bytes
			SecretKeySpec hmac_sha1_key = new SecretKeySpec(strAPPSECRET.getBytes(Charset.defaultCharset()), strURLENCODING);

			// Create Mac and initializing with key
			Mac mac = Mac.getInstance(strENCODINGALGORITHM);
			mac.init(hmac_sha1_key);
			// encrypted bytes array
			byte[] raw_encrypted_byte_array = mac.doFinal(stringtoSign.getBytes(strURLENCODING));
			StringBuilder sb = new StringBuilder();
			for (byte b : raw_encrypted_byte_array)
				sb.append(String.format("%02x", b & 0xff));

			if (domainName != null && domainName.length() > 0)
			{
				finalUrl = domainName + uri.split(Pattern.quote("?"))[0] + "?" + queryString + "&authtoken="+ sb.toString();
			}
			else
			{
				finalUrl = uri.split(Pattern.quote("?"))[0] + "?" + queryString + "&authtoken=" + sb.toString();
			}
		}
		catch (UnsupportedEncodingException e)
		{
			System.out.println(CLASSNAME + " Get Authendicated URL failed " + e.getMessage());
		}
		catch (NoSuchAlgorithmException e)
		{
			System.out.println(CLASSNAME + " Get Authendicated URL failed " + e.getMessage());
		}
		catch (InvalidKeyException e)
		{
			System.out.println(CLASSNAME + " Get Authendicated URL failed " + e.getMessage());
		}
		
		return finalUrl;
	}
	
	private EActionStatus ValidationField(StudentDetailsDTO studentDTO)
	{
		boolean FatherEmailid=false;
		boolean Fathercontactno=false;
		boolean Fathername=false;
		
		boolean MotherEmailid=false;
		boolean Mothercontactno=false;
		boolean Mothername=false;
		
		boolean IsFatherDetailsAvailable=false;
		
		boolean IsMotherDetailsAvailable=false;
		
		if(studentDTO==null || studentDTO.getName()==null || studentDTO.getName().equals(""))
		{
			OUT.error("Name Validation Failed");
			
			return EActionStatus.FAILURE;
		}
		
		if(studentDTO==null || studentDTO.getGender()==null || studentDTO.getGender().equals("") || (!studentDTO.getGender().equals("M") && !studentDTO.getGender().equals("F")))
		{
			
			OUT.error("Gender Validation Failed");
			return EActionStatus.FAILURE;
		}
		
		if(studentDTO==null || studentDTO.getClassId()==0)
		{
			OUT.error("Class Validation Failed");
			
			return EActionStatus.FAILURE;
		}
		
		
		
		if(studentDTO==null || studentDTO.getFatherName()==null || studentDTO.getFatherName().equals(""))
		{
			OUT.error("Father Name Validation Failed");
			
			Fathername=true;
		}
		
		if(studentDTO!=null && studentDTO.getFatherEmailId()!=null && !studentDTO.getFatherEmailId().trim().equals(""))
		{
			if(!isEmailIdValid(studentDTO.getFatherEmailId().trim()))
			{
				OUT.error("Father EmailID Validation Failed");
				return EActionStatus.FAILURE;
			}
			
		}
		else
		{
			OUT.error("Father EmailID Validation Failed");
			FatherEmailid=true;
		}
		
		
		
		if(studentDTO==null || studentDTO.getFathercontactno()==null || studentDTO.getFathercontactno().equals(""))
		{
			OUT.error("Father Contact No Validation Failed");
			
			Fathercontactno=true;
		}
		
		if(Fathername || FatherEmailid || Fathercontactno)
		{
			OUT.error("Father Details Validation Failed");
			
			IsFatherDetailsAvailable=true;
		}
		
		
		
		
		if(studentDTO==null || studentDTO.getMotherName()==null || studentDTO.getMotherName().equals(""))
		{
			OUT.error("Mother Name Validation Failed");
			
			Mothername=true;
		}
		
		if(studentDTO!=null && studentDTO.getMotheremailId()!=null && !studentDTO.getMotheremailId().trim().equals(""))
		{
			if(!isEmailIdValid(studentDTO.getMotheremailId().trim()))
			{
				OUT.error("Mother EmailID Validation Failed");
				return EActionStatus.FAILURE;
			}
			
		
		}
		else
		{
			OUT.error("Mother EmailID Validation Failed");
			MotherEmailid=true;
		}
		
		
		if(studentDTO==null || studentDTO.getMothercontactno()==null || studentDTO.getMothercontactno().equals(""))
		{
			OUT.error("Mother Contact No Validation Failed");
			Mothercontactno=true;
		}
		
		if(Mothername || MotherEmailid || Mothercontactno)
		{
			OUT.error("Mother Details Validation Failed");
			IsMotherDetailsAvailable=true;
		}
		
		
		
		if(IsFatherDetailsAvailable && IsMotherDetailsAvailable)
		{
			OUT.error("Parent Details Validation Failed");
			return EActionStatus.FAILURE;
		}
		
		
		if(studentDTO==null || studentDTO.getStudentType()==null || (studentDTO.getStudentType()!=StudentTypeEnum.FULL && studentDTO.getStudentType()!=StudentTypeEnum.TRIAL))
		{
			OUT.error("Student Type Validation Failed");
			return EActionStatus.FAILURE;
		}
		
		
		
		return EActionStatus.SUCCESS;
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
	
	private boolean isEmailIdValid(String email)
	{
		
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";
		Pattern pattern = Pattern.compile(regex);
		if (null == email || email.isEmpty())
		{
			
			return false;
		}
		if (!email.isEmpty() && !pattern.matcher(email).matches())
		{
			
			return false;
		}
		return true;
	}
	
	
	
	
	
	public String insertAjax()
	{
		OUT.debug("Inside PsychometricTests class : insertAjax method");
		try
		{
			InterestService		service=new InterestService();
			
			
			if(answerList!=null)
			{
			
				service = new InterestService();
				boolean result = service.insertAnswer(answerList, userid);
				StudentTUMService tumservice = new StudentTUMService();
				tumservice.updateTUMSession(userid);
			}
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}
	public boolean isInterestCompleted() {
		return isInterestCompleted;
	}


	public void setInterestCompleted(boolean isInterestCompleted) {
		this.isInterestCompleted = isInterestCompleted;
	}


	public int getPageAction() {
		return pageAction;
	}


	public void setPageAction(int pageAction) {
		this.pageAction = pageAction;
	}
	
	
	
	
}
