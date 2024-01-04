package com.lodestar.edupath.APIS.client;


import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.GenderTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StreamTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.HttpHeaders;
import org.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentWithInterest extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentWithInterest.class);
	
	
	private String   								Status="ERROR";
	
	private String   								Message="";
	
	private static final String		CGT_ANSWER_STR_YES	= "YES";
	private static final String		CGT_ANSWER_BOOLEAN	= "TRUE";
	private static final String		CGT_ANSWER_STR_NO	= "NO";
	private StudentWithInterestService service; 
	
	
	public StudentWithInterest()
	{
		service =new StudentWithInterestService();
	}
	

	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute  the StudentWithInterest Summary Action Of======================= {}");
		
		ClientStudentDetailsDTO studentDTO = new ClientStudentDetailsDTO();
		String authorizationHeader = HttpHeaders.AUTHORIZATION;

		try
		{
			

			String hearderResult_partnerName = Util.validatePostHeader();
			if (!hearderResult_partnerName.equalsIgnoreCase("error") && !hearderResult_partnerName.equalsIgnoreCase("errorMethod")) 
			{
				String Data = Util.getDataForStudentCreation();
				OUT.debug("Data Receive=>" + Data);
				if(Data!=null && !Data.equals("") && !Data.equals("error"))
				{
					String JsonData=Data;
					JSONObject jsonDataObject = new JSONObject(Data.trim());
					OUT.debug("jsonDataObject Receive=>"+jsonDataObject);
					if(jsonDataObject.has("testresult") && jsonDataObject.has("studentid") )
					{
						if(jsonDataObject.getString("studentid").trim() != null || !jsonDataObject.getString("studentid").trim().equalsIgnoreCase(" "))
						{	
								studentDTO.setClientID(jsonDataObject.getString("studentid"));
								studentDTO.setClientName(hearderResult_partnerName);
								
								if(jsonDataObject.has("name") )
								{
										if(jsonDataObject.getString("name").trim() != null && !jsonDataObject.getString("name").trim().equalsIgnoreCase(" ") && !jsonDataObject.getString("name").trim().equalsIgnoreCase(""))
										{
											studentDTO.setName(jsonDataObject.getString("name").trim());
										}
										else
										{
											Status="202";
											Message="student name missing";
											return SUCCESS;
										}
										
								}		
								else
								{
									Status="202";
									Message=" student name missing";
									return SUCCESS;
								}
								
								if(jsonDataObject.has("className")  )
								{
									if( jsonDataObject.getString("className").trim() != null && !jsonDataObject.getString("className").trim().equalsIgnoreCase(" ") && !jsonDataObject.getString("className").trim().equalsIgnoreCase(""))
									{
										
										if(jsonDataObject.getString("className").trim().equalsIgnoreCase("9") || jsonDataObject.getString("className").trim().equalsIgnoreCase("10") || jsonDataObject.getString("className").trim().equalsIgnoreCase("11") || jsonDataObject.getString("className").trim().equalsIgnoreCase("12")  || jsonDataObject.getString("className").trim().equalsIgnoreCase("12 Plus")) 
										{
											studentDTO.setClassStr(jsonDataObject.getString("className").trim());
										}
										else
										{
											Status="202";
											Message="Invlaid className";
											return SUCCESS;
										}
										
										if (jsonDataObject.getString("className").trim().equalsIgnoreCase("11") || jsonDataObject.getString("className").trim().equalsIgnoreCase("12")) {
											if (jsonDataObject.has("Stream") ) {
												if( jsonDataObject.getString("Stream").trim() != null && !jsonDataObject.getString("Stream").trim().equalsIgnoreCase(" ") && !jsonDataObject.getString("Stream").trim().equalsIgnoreCase("")){
													for (StreamTypeEnum stenum : StreamTypeEnum.values()) {
														if (jsonDataObject.getString("Stream").trim().equalsIgnoreCase(stenum.name())) {
															studentDTO.setStream(stenum.getWeight());
														}
													}
												}else
												{
													Status="202";
													Message=" student Stream missing";
													return SUCCESS;
												}
											}
										else {
												Status = "202";
												Message = "Stream missing for this student";
												return SUCCESS;
											}
										}
									}else
									{
										Status="202";
										Message=" student className missing";
										return SUCCESS;
									}	
									
								}
								else
								{
									Status="202";
									Message=" student className missing";
									return SUCCESS;
								}

								if(jsonDataObject.has("email") )
								{
									if(  jsonDataObject.getString("email").trim() != null && !jsonDataObject.getString("email").trim().equalsIgnoreCase(" ") && !jsonDataObject.getString("email").trim().equalsIgnoreCase("")){
										studentDTO.setFatheremailId(jsonDataObject.getString("email").trim());
									}else
									{
										Status="202";
										Message=" student email missing";
										return SUCCESS;
									}
								}
								else
								{
									Status="202";
									Message=" student email missing";
									return SUCCESS;
								}

								if(jsonDataObject.has("school"))
								{
									if( jsonDataObject.getString("school").trim() != null && !jsonDataObject.getString("school").trim().equalsIgnoreCase(" ") && !jsonDataObject.getString("school").trim().equalsIgnoreCase(""))
									{
										studentDTO.setSchoolName(jsonDataObject.getString("school").trim());
									}
								}
								else
								{
									Status="202";
									Message=" student school missing";
									return SUCCESS;
								}
								
								if(jsonDataObject.has("gender") )
								{
									if(jsonDataObject.getString("gender").trim() != null && !jsonDataObject.getString("gender").trim().equalsIgnoreCase(" ") && !jsonDataObject.getString("gender").trim().equalsIgnoreCase("")){
										if(jsonDataObject.getString("gender").trim().equalsIgnoreCase(GenderTypeEnum.M.name()) || jsonDataObject.getString("gender").trim().equalsIgnoreCase(GenderTypeEnum.M.getGender()) || jsonDataObject.getString("gender").trim().equalsIgnoreCase("Male"))
										{
											studentDTO.setGender(GenderTypeEnum.M.name());
										}
										else if(jsonDataObject.getString("gender").trim().equalsIgnoreCase(GenderTypeEnum.F.name()) || jsonDataObject.getString("gender").trim().equalsIgnoreCase(GenderTypeEnum.F.getGender()) || jsonDataObject.getString("gender").trim().equalsIgnoreCase("Female"))
										{
											studentDTO.setGender(GenderTypeEnum.F.name());
										}
										else{
											Status="202";
											Message=" student gender invalid";
											return SUCCESS;
										}
									}else
									{
										Status="202";
										Message=" student gender missing";
										return SUCCESS;
									}
								}else
								{
									Status="202";
									Message=" student gender missing";
									return SUCCESS;
								}
								
								
								
							
							OUT.debug(" studentDTO:{} =>",studentDTO);
							
							JSONObject jsonDataTestObject = jsonDataObject.getJSONObject("testresult");
							if(jsonDataTestObject!=null  && jsonDataTestObject.length()==60)
							{
								ClientStudentDetailsDTO resultDHStudentDTO = service.studentCreation(studentDTO);
								if(resultDHStudentDTO.getId()!=0)
								{
									OUT.debug("jsonDataTestObject Receive=>"+jsonDataTestObject.length());
									List<ClientStudentCGTDTO> studentcgtList = new ArrayList<ClientStudentCGTDTO>();
									
									
									for(Iterator iterator = jsonDataTestObject.keySet().iterator(); iterator.hasNext();) 
									{
										ClientStudentCGTDTO studentcgtDTO = new ClientStudentCGTDTO();
										String qID = (String) iterator.next();
										String ans=  (String)jsonDataTestObject.getString(qID);
//										OUT.debug("key Receive=>"+qID);
//										OUT.debug("jsonDataTestObject.get(key) Receive=>"+ans);
	
										studentcgtDTO.setStudentId(resultDHStudentDTO.getId());
										studentcgtDTO.setQuestionId(Integer.parseInt(qID));
										if (CGT_ANSWER_STR_YES.equalsIgnoreCase(ans) || CGT_ANSWER_BOOLEAN.equalsIgnoreCase(ans))
										{
											studentcgtDTO.setAnswer(CGT_ANSWER_STR_YES);
										}
										else
										{
											studentcgtDTO.setAnswer(CGT_ANSWER_STR_NO);
										}
										studentcgtList.add(studentcgtDTO);
									}
									
									OUT.debug("studentcgtList Received list:{}=>", studentcgtList);
									
									int result= service.uploadInterestTest(studentcgtList);
									if (result != 0) 
									{
										Status = "200";
										Message = "Successful upload";
									}
									
									
								}
								else 
								{
									Status="202";
									Message="Client student Id found, can not upload duplicate record";
									return SUCCESS;
									
								}
							}
							else 
							{
								Status="202";
								Message="Test results missing. All 60 questions must be answered";
								return SUCCESS;
								
							}
						}
						else
						{
							Status="202";
							Message="student Id or testresults is null";
							return SUCCESS;
						}		
					}
					else
					{
						Status="202";
					Message="student Id or testresults is missing";
					return SUCCESS;
					}
				
				}else{
					Status="204";
					Message="Parameter null";
					return SUCCESS;
				}

			}else if(hearderResult_partnerName.equalsIgnoreCase("errorMethod"))
			{
				Status="204";
				Message="Invalid Method";
				return SUCCESS;
			}
			else{
				Status="204";
			Message="Header invalid ";
			return SUCCESS;
			}
		
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			Status="ERROR";
			Message="Internal Exception Level 1";
		}
		
		
		
		
		return SUCCESS;
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
	
	

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

		
	
	
	
	
	
}
