package com.lodestar.edupath.APIS;


import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.dao.APIS.APIAuthentication;
import com.lodestar.edupath.datatransferobject.dto.APIS.AuthCodeValidationDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;



public class StudentCreationAPI extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentCreationAPI.class);
	
	private String									Data;
	private String									UIN;
	
	private String   								Status="ERROR";
	
	private String   								Message;
	
	public static String							Studentuin="";
	
	
	private String getStudentUIN() {
		return Studentuin;
	}

	public void setStudentUIN(String studentUIN) {
		Studentuin = studentUIN;
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

	

	private String getUIN() {
		return UIN;
	}

	public void setUIN(String uIN) {
		UIN = uIN;
	}

	private String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	public String execute()
	{
		OUT.debug("Inside StudentCreationAPI class: execute Method");
		
		try
		{
			
			OUT.debug("Data Receive=>"+Data);
			OUT.debug("UIN Receive==>"+UIN);
			
			 
			
			
			
			if(Data!=null && !getData().equals("") && UIN!=null && !getUIN().equals(""))
			{
					String JsonData=Data;
					
					PartnerDetailsDTO parnerdetails=new PartnerDetailsDTO();
					
					//parnerdetails.setPartneName("MRT");
					//parnerdetails.setUIN("2007-MRT-1");
					
					parnerdetails=new APISService().GetPartnerDetails(UIN.toUpperCase());
					
					if(parnerdetails!=null && parnerdetails.getEncryptionCode()!=null && !parnerdetails.getEncryptionCode().trim().equals(""))
					{
					
						String DecryptJsonData=Decrypt(JsonData,parnerdetails.getEncryptionCode());
						
						OUT.debug("After Decrytion JsonData=====>"+DecryptJsonData);
						
						if(DecryptJsonData!=null && !DecryptJsonData.trim().equals(""))
						{
									JSONObject jsonDataObject = new JSONObject(DecryptJsonData.trim());
									
									if(jsonDataObject.has("APIAuth") && jsonDataObject.has("StudentDetails"))
									{
										JSONObject APIAuth = jsonDataObject.getJSONObject("APIAuth");
									
									
									
									
											AuthCodeValidationDTO authcodevalidate=new AuthCodeValidationDTO();
											
											if(APIAuth.has("EmailID"))
											{
											
												if(!APIAuth.getString("EmailID").isEmpty() && !APIAuth.getString("EmailID").equals(""))
												{
													authcodevalidate.setEmailID(APIAuth.getString("EmailID"));
												}
											
											}
											
											if(APIAuth.has("Password"))
											{
											
												if(!APIAuth.getString("Password").isEmpty() && !APIAuth.getString("Password").equals(""))
												{
													authcodevalidate.setPassword(APIAuth.getString("Password"));
												}
											}
											
											
											if(APIAuth.has("AuthCode"))
											{
												if(!APIAuth.getString("AuthCode").isEmpty() && !APIAuth.getString("AuthCode").equals(""))
												{
													authcodevalidate.setAuthCode(APIAuth.getString("AuthCode"));
												}
											}
											
											
											if(authcodevalidate!=null && authcodevalidate.getAuthCode()!=null && authcodevalidate.getEmailID()!=null && authcodevalidate.getPassword()!=null)
											{
												APIAuthentication apiauthenticated=new APIAuthentication();
												
												boolean apivalide=apiauthenticated.APIAuthentication(authcodevalidate);
												
												if(apivalide)
												{
													try
													{
														
														
														JSONObject StudentDetails = jsonDataObject.getJSONObject("StudentDetails");
														
														StudentDetailsDTO studentDTO=new StudentDetailsDTO();
														
														boolean FatherEmailid=true;
														boolean Fathercontactno=true;
														boolean Fathername=true;
														
														String CityStringData=null;
														
														String suin=null;
														if(StudentDetails.has("StudentName"))
														{
														
															if(!StudentDetails.getString("StudentName").isEmpty() && !StudentDetails.getString("StudentName").equals(""))
															{
																studentDTO.setName(StudentDetails.getString("StudentName").trim());
															}
														}
														
														
														if(StudentDetails.has("SUIN"))
														{
														
															if(!StudentDetails.getString("SUIN").isEmpty() && !StudentDetails.getString("SUIN").equals(""))
															{
																suin=StudentDetails.getString("SUIN").trim();
															}
														}
														
														
														
														
														if(StudentDetails.has("Gender"))
														{
														
															if(!StudentDetails.getString("Gender").isEmpty() && !StudentDetails.getString("Gender").equals(""))
															{
																studentDTO.setGender(StudentDetails.getString("Gender").trim());
															}
														}
														
														if(StudentDetails.has("City"))
														{
														
															if(!StudentDetails.getString("City").isEmpty() && !StudentDetails.getString("City").equals(""))
															{
																studentDTO.setCityId(Integer.parseInt(StudentDetails.getString("City")));
															}
														}
														
														if(StudentDetails.has("CityString"))
														{
														
															if(!StudentDetails.getString("CityString").isEmpty() && !StudentDetails.getString("CityString").equals(""))
															{
																CityStringData=StudentDetails.getString("CityString");
															}
														}
														
														
														
														
														
														if(StudentDetails.has("School"))
														{
														
															if(!StudentDetails.getString("School").isEmpty() && !StudentDetails.getString("School").equals(""))
															{
																studentDTO.setSchoolId(Integer.parseInt(StudentDetails.getString("School")));
															}
															else
															{
																studentDTO.setSchoolId(1);
															}
														}
														
														
														if(StudentDetails.has("Class"))
														{
														
															if(!StudentDetails.getString("Class").isEmpty() && !StudentDetails.getString("Class").equals(""))
															{
																studentDTO.setClassId(Integer.parseInt(StudentDetails.getString("Class")));
															}
														}
														
														
														if(StudentDetails.has("Section"))
														{
														
															if(!StudentDetails.getString("Section").isEmpty() && !StudentDetails.getString("Section").equals(""))
															{
																studentDTO.setSection(StudentDetails.getString("Section"));
															}
														}
														
														
														if(StudentDetails.has("StudentEmailID"))
														{
														
															if(!StudentDetails.getString("StudentEmailID").isEmpty() && !StudentDetails.getString("StudentEmailID").equals(""))
															{
																studentDTO.setStudentemailId(StudentDetails.getString("StudentEmailID"));
															}
														}
														
														
														if(StudentDetails.has("StudentMobileNumber"))
														{
														
															if(!StudentDetails.getString("StudentMobileNumber").isEmpty() && !StudentDetails.getString("StudentMobileNumber").equals(""))
															{
																studentDTO.setStudentcontactNumber(StudentDetails.getString("StudentMobileNumber"));
															}
														}
														
														
														if(StudentDetails.has("FatherName"))
														{
														
															if(!StudentDetails.getString("FatherName").isEmpty() && !StudentDetails.getString("FatherName").equals(""))
															{
																studentDTO.setFatherName(StudentDetails.getString("FatherName"));
																studentDTO.setFathername(StudentDetails.getString("FatherName"));
																
																Fathername=false;
																
															}
														}
														
														
														if(StudentDetails.has("FatherEmailID"))
														{
														
															if(!StudentDetails.getString("FatherEmailID").isEmpty() && !StudentDetails.getString("FatherEmailID").equals(""))
															{
																studentDTO.setFatherEmailId(StudentDetails.getString("FatherEmailID").trim());
																
																studentDTO.setFatheremailId(StudentDetails.getString("FatherEmailID").trim());
																
																FatherEmailid=false;
																
																
															}
														}
														
														
														if(StudentDetails.has("FatherMobileNumber"))
														{
														
															if(!StudentDetails.getString("FatherMobileNumber").isEmpty() && !StudentDetails.getString("FatherMobileNumber").equals(""))
															{
																studentDTO.setFathercontactno(StudentDetails.getString("FatherMobileNumber"));
																
																studentDTO.setContactNumber(StudentDetails.getString("FatherMobileNumber"));
																
																Fathercontactno=false;
															
															}
														}
														
														if(StudentDetails.has("MotherName"))
														{
														
															if(!StudentDetails.getString("MotherName").isEmpty() && !StudentDetails.getString("MotherName").equals(""))
															{
																studentDTO.setMotherName(StudentDetails.getString("MotherName"));
																
																if(Fathername)
																{
																	studentDTO.setFathername(StudentDetails.getString("MotherName"));
																}
																
															}
														}
														
														
														if(StudentDetails.has("MotherEmailID"))
														{
														
															if(!StudentDetails.getString("MotherEmailID").isEmpty() && !StudentDetails.getString("MotherEmailID").equals(""))
															{
																studentDTO.setMotheremailId(StudentDetails.getString("MotherEmailID"));
																if(FatherEmailid)
																{
																	studentDTO.setFatheremailId(StudentDetails.getString("MotherEmailID"));
																}
																
																
															}
														}
														
														
														if(StudentDetails.has("MotherMobileNumber"))
														{
														
															if(!StudentDetails.getString("MotherMobileNumber").isEmpty() && !StudentDetails.getString("MotherMobileNumber").equals(""))
															{
																studentDTO.setMothercontactno(StudentDetails.getString("MotherMobileNumber"));
																if(Fathercontactno)
																{
																	studentDTO.setContactNumber(StudentDetails.getString("MotherMobileNumber"));
																}
															}
														}
														
														
														if(StudentDetails.has("ComputerFacility"))
														{
														
															if(!StudentDetails.getString("ComputerFacility").isEmpty() && !StudentDetails.getString("ComputerFacility").equals(""))
															{
																if(StudentDetails.getString("ComputerFacility").equals("1"))
																{
																	studentDTO.setComputerFacility(true);
																}
																else
																{
																	studentDTO.setComputerFacility(false);
																}
																
															}
														}
														
														
														if(StudentDetails.has("SelectedFacilitator"))
														{
														
															if(!StudentDetails.getString("SelectedFacilitator").isEmpty() && !StudentDetails.getString("SelectedFacilitator").equals(""))
															{
																studentDTO.setFacilitatorId(Integer.parseInt(StudentDetails.getString("SelectedFacilitator")));
															}
															
														}
														
														
														if(StudentDetails.has("StudentType"))
														{
														
															if(!StudentDetails.getString("StudentType").isEmpty() && !StudentDetails.getString("StudentType").equals(""))
															{
																
																if(StudentDetails.getString("StudentType").equalsIgnoreCase("FULL"))
																{
																	studentDTO.setStudentType(StudentTypeEnum.FULL);
																}
																else if(StudentDetails.getString("StudentType").equalsIgnoreCase("TRIAL"))
																{
																	studentDTO.setStudentType(StudentTypeEnum.TRIAL);
																}
																
															}
														}
														
														
														if(StudentDetails.has("Session1Date") && StudentDetails.has("Session1Time"))
														{
															if(!StudentDetails.getString("Session1Date").isEmpty() && !StudentDetails.getString("Session1Date").equals("") && !StudentDetails.getString("Session1Time").isEmpty() && !StudentDetails.getString("Session1Time").equals(""))
															{
																studentDTO.setSession1DateStr(StudentDetails.getString("Session1Date")+" "+StudentDetails.getString("Session1Time"));
															}
														}
														
														
														if(StudentDetails.has("Session2Date") && StudentDetails.has("Session2Time"))
														{
															if(!StudentDetails.getString("Session2Date").isEmpty() && !StudentDetails.getString("Session2Date").equals("") && !StudentDetails.getString("Session2Time").isEmpty() && !StudentDetails.getString("Session2Time").equals(""))
															{
																studentDTO.setSession2DateStr(StudentDetails.getString("Session2Date")+" "+StudentDetails.getString("Session2Time"));
															}
														}
														
														
														
														
														if(StudentDetails.has("Session3Date") && StudentDetails.has("Session3Time"))
														{
															if(!StudentDetails.getString("Session3Date").isEmpty() && !StudentDetails.getString("Session3Date").equals("") && !StudentDetails.getString("Session3Time").isEmpty() && !StudentDetails.getString("Session3Time").equals(""))
															{
																studentDTO.setSession3DateStr(StudentDetails.getString("Session3Date")+" "+StudentDetails.getString("Session3Time"));
															}
														}
														
														
														
														
														
														if(StudentDetails.has("Venue"))
														{
															if(!StudentDetails.getString("Venue").isEmpty() && !StudentDetails.getString("Venue").equals(""))
															{
																studentDTO.setVenue(StudentDetails.getString("Venue"));
															}
														}
														
														
														
														if(StudentDetails.has("AgreeAmount"))
														{
															if(!StudentDetails.getString("AgreeAmount").isEmpty() && !StudentDetails.getString("AgreeAmount").equals(""))
															{
																studentDTO.setAgreedAmount(Double.parseDouble(StudentDetails.getString("AgreeAmount")));
															}
														}
														
														
														
														
														if(StudentDetails.has("PaidAmount"))
														{
															if(!StudentDetails.getString("PaidAmount").isEmpty() && !StudentDetails.getString("PaidAmount").equals(""))
															{
																studentDTO.setPaidAmount(Double.parseDouble(StudentDetails.getString("PaidAmount")));
															}
														}
														
														
														
														
														
														EActionStatus RequiredFieldValidation=ValidationField(studentDTO);
														
														if(RequiredFieldValidation==EActionStatus.SUCCESS && suin!=null && !suin.equals(""))
														{
															
															EActionStatus DuplicationCheck=ValidateDuplication(studentDTO);
															
															if(DuplicationCheck==EActionStatus.SUCCESS)
															{
																APISService apiservice=new APISService();
																
																EActionStatus status= apiservice.insertStudent(studentDTO, parnerdetails,CityStringData,suin);
																
																if (status == EActionStatus.SUCCESS)
																{
																	Status="SUCCESS";
																	Message="Student Creation Successfuly";
																}
																else
																{
																	Status="ERROR";
																	Message="Student Creation Failed";
																	Studentuin="";
																}
															}
															else
															{
																Status="ERROR";
																Message="Duplicate Student.";
																Studentuin="";
															}
														}
														else
														{
															Status="ERROR";
															Message="Required Field Validation Failed Level 1";
															Studentuin="";
														}
														
													}
													catch (Exception e)
													{
														OUT.error(ApplicationConstants.EXCEPTION, e);
														Status="ERROR";
														Message="Internal Exception Level 3";
														Studentuin="";
													}
													
													
													
													
													
												}
												else
												{
													
													Status="ERROR";
													Message="API Authentication Failed  Level 3";
													Studentuin="";
												}
											}
											else
											{
												Status="ERROR";
												Message="API Authentication Failed Level 2";
												Studentuin="";
											}
											
											
											
									
									
									}
									else
									{
										Status="ERROR";
										Message="API Authentication Failed Level 1";
										Studentuin="";
									}
					
					
					
					
						}
						else
						{
							Status="ERROR";
							Message="Internal Exception Level 2";
							Studentuin="";
						}
					
					
					
					
					
					
					
				}
				else
				{
					Status="ERROR";
					Message="Missing parameter Level 2";
					Studentuin="";
				}
			
			}
			else
			{
				Status="ERROR";
				Message="Missing parameter Level 1";
				Studentuin="";
			}
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			Status="ERROR";
			Message="Internal Exception Level 1";
			Studentuin="";
		}
		
		
		
		return SUCCESS;
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
	
	private String Decrypt(String jsondata, String encryptionKey)
	{
		
		try
		{
			String key =encryptionKey;// "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] keyBytes= new byte[16];
			byte[] b= key.getBytes("UTF-8");
			int len= b.length;
			if (len > keyBytes.length) len = keyBytes.length;
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
			cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
	
	
			byte [] results = cipher.doFinal(Base64.decodeBase64(jsondata));
			return new String(results,"UTF-8");
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			
		}
		
		return null;
	}
	
	
	
}
