package com.lodestar.edupath.APIS.dailyHunt.ScheduleApointment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.dailyHunt.Util;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ScheduleAppointmentAPI extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(ScheduleAppointmentAPI.class);

	private String   								Status="ERROR";
	
	private String   								Message="";

	ScheduleAppointmentAPIService service = new ScheduleAppointmentAPIService();
	
	
	@SuppressWarnings("null")
	@Override
	public String execute() throws Exception
	{
		OUT.debug("Inside ScheduleAppointmentAPI");
		
		DHStudentDetailsDTO studentDTO = new DHStudentDetailsDTO();
		
		try
		{

			String hearderResult = Util.validatePostHeader();
			if (hearderResult.equalsIgnoreCase("success")) 
			{
				DHStudentExtraDetailDTO resultStudentExtraDetailDTO = new DHStudentExtraDetailDTO();
				DHStudentDAO  dhstudentDAO = new DHStudentDAO();
				String Data = Util.getData();
				OUT.debug("Data Receive=>" + Data);
				OUT.debug("Data!=null Receive=>:{}" , Data!=null);
				OUT.debug("!Data.equals(\"\") Receive=>" + !Data.equals(""));
				OUT.debug("!Data.equals(\"error\") Receive=>" + !Data.equals("error"));
				if(Data!=null && !Data.equals("") && !Data.equals("error"))
				{
					String JsonData=Data;
					JSONObject jsonDataObject = new JSONObject(Data.trim());
					OUT.debug("jsonDataObject Receive=>"+jsonDataObject);
					if(jsonDataObject.has("studentid") )
					{
						if(jsonDataObject.getString("studentid") !=null && !jsonDataObject.getString("studentid").equals(""))
						{
							studentDTO = service.getStudentByDHID(jsonDataObject.getString("studentid"));
							if(studentDTO != null )
							{
								DHStudentCGTDAO dhsDAO = new DHStudentCGTDAO();
								List<DHStudentCGTDTO> dhstudentcgtlist = dhsDAO.getDHStudentCGTAptitudeByStudentId(studentDTO.getId());
								if(!dhstudentcgtlist.isEmpty())
								{
									resultStudentExtraDetailDTO = service.getStudentAppointmentByStudentID(studentDTO.getId());
									if(resultStudentExtraDetailDTO == null)
									{
										DHStudentExtraDetailDTO studentExtraDetailDTO = new DHStudentExtraDetailDTO();
										int dumid = studentDTO.getId();
										studentExtraDetailDTO.setStudentId(dumid);
										
										if(jsonDataObject.has("studentEmail"))
										{	
											if(jsonDataObject.getString("studentEmail") != null && !jsonDataObject.getString("studentEmail").equals(""))
											{
												studentExtraDetailDTO.setStudentemailId(jsonDataObject.getString("studentEmail"));
											}
											else{
												Status="202";
												Message="Student Email id null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="Student Email id missing";
											return SUCCESS;	
										}
										
										if(jsonDataObject.has("fatherName"))
										{
											if(jsonDataObject.getString("fatherName") != null && !jsonDataObject.getString("fatherName").equals(""))
											{
												studentExtraDetailDTO.setFatherName(jsonDataObject.getString("fatherName"));
											}else{
												Status="202";
												Message="father Name null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="father Name missing";
											return SUCCESS;	
										}
										
										if(jsonDataObject.has("fatherEmail"))
										{
											if(jsonDataObject.getString("fatherEmail") != null && !jsonDataObject.getString("fatherEmail").equals(""))
											{
												studentExtraDetailDTO.setFatherEmailId(jsonDataObject.getString("fatherEmail"));
											}
											else{
												Status="202";
												Message="father Email null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="Father Email id missing";
											return SUCCESS;	
										}
										
										if(jsonDataObject.has("fatherPhone"))
										{
											if(jsonDataObject.getString("fatherPhone") != null && !jsonDataObject.getString("fatherPhone").equals(""))
											{
												studentExtraDetailDTO.setFathercontactno(jsonDataObject.getString("fatherPhone"));
											}
											else{
												Status="202";
												Message="Father Phone number null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="Father Phone number missing";
											return SUCCESS;	
										}
										
										if(jsonDataObject.has("motherName"))
										{
											if(jsonDataObject.getString("motherName") != null && !jsonDataObject.getString("motherName").equals(""))
											{
												studentExtraDetailDTO.setMotherName(jsonDataObject.getString("motherName"));
											}
											else{
												Status="202";
												Message="Mother name null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="Mother name missing";
											return SUCCESS;	
										}
										
										if(jsonDataObject.has("motherEmail"))
										{
											if(jsonDataObject.getString("motherEmail") != null && !jsonDataObject.getString("motherEmail").equals(""))
											{
												studentExtraDetailDTO.setMotheremailId(jsonDataObject.getString("motherEmail"));
											}
											else{
												Status="202";
												Message="Mother Email id null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="Mother Email id missing";
											return SUCCESS;	
										}
										
										if(jsonDataObject.has("motherPhone"))
										{
											if(jsonDataObject.getString("motherPhone") != null && !jsonDataObject.getString("motherPhone").equals(""))
											{
												studentExtraDetailDTO.setMothercontactno(jsonDataObject.getString("motherPhone"));
											}
											else{
												Status="202";
												Message="Mother Phone number null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="Mother Phone number missing";
											return SUCCESS;	
										}
										
										
										if(jsonDataObject.has("studentPhone"))
										{
											if(jsonDataObject.getString("studentPhone") != null && !jsonDataObject.getString("studentPhone").equals(""))
											{
												studentExtraDetailDTO.setStudentcontactNumber(jsonDataObject.getString("studentPhone"));
											}
											else{
												Status="202";
												Message="student Phone number null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="student Phone number missing";
											return SUCCESS;	
										}
										
										if(jsonDataObject.has("city"))
										{
											if(jsonDataObject.getString("city") != null && !jsonDataObject.getString("city").equals(""))
											{
												studentExtraDetailDTO.setCityName(jsonDataObject.getString("city"));
											}else{
												Status="202";
												Message="city null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="city missing";
											return SUCCESS;	
										}
										
										if(jsonDataObject.has("schoolName"))
										{
											if(jsonDataObject.getString("schoolName") != null && !jsonDataObject.getString("schoolName").equals(""))
											{
												studentExtraDetailDTO.setSchoolName(jsonDataObject.getString("schoolName"));
											}else{
												Status="202";
												Message="School Name null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="School Name missing";
											return SUCCESS;	
										}
										
										if(jsonDataObject.has("session1DateTime"))
										{
											if(jsonDataObject.getString("session1DateTime") != null && !jsonDataObject.getString("session1DateTime").equals(""))
											{
												
												Date date=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(jsonDataObject.getString("session1DateTime"));
												Date currentDate = new Date();
												long diff = date.getTime() - currentDate.getTime();
												if(diff>=1) 
												{
													studentExtraDetailDTO.setSession1Date(date);
												}
												else{
													Status="202";
													Message="session1DateTime less than today";
													return SUCCESS;
												}
												
											}else{
												Status="202";
												Message="session1DateTime null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="session1DateTime missing";
											return SUCCESS;	
										}
										if(jsonDataObject.has("session2DateTime"))
										{
											if(jsonDataObject.getString("session2DateTime") != null && !jsonDataObject.getString("session2DateTime").equals(""))
											{
												
												Date date=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(jsonDataObject.getString("session2DateTime"));
												Date currentDate = new Date();
												long diff = date.getTime() - currentDate.getTime();
												if(diff>=1) 
												{
													studentExtraDetailDTO.setSession2Date(date);
												}
												else{
													Status="202";
													Message="session2DateTime less than today";
													return SUCCESS;
												}
											 
												diff= date.getTime()-  studentExtraDetailDTO.getSession1Date().getTime();
												if(diff<1)
												{
													Status="202";
													Message="session2DateTime less than session1DateTime";
													return SUCCESS;
												}
												
											}else{
												Status="202";
												Message="session2DateTime null";
												return SUCCESS;
											}
										}
										else
										{
											Status="202";
											Message="session2DateTime missing";
											return SUCCESS;	
										}
										
										
										int result = service.insertDHStudentExtraDetails(studentExtraDetailDTO);
										if(result !=0)
										{
											Status="200";
											Message="Seccessful upload";
											return SUCCESS;			
										}
										else 
										{
											Status="202";
											Message="Unseccessful upload";
											return SUCCESS;
										}
										
									}
									else
									{
										Status="404";
										Message="DialyHunt student Appointment Found, can not upload dulpicate appointment";
										return SUCCESS;	
									}
								}
								else{
									Status="404";
									Message="Student has not completed aptitude test. Upload aptitude results.";
									return SUCCESS;	
								}		
							}
							else
							{
								Status="404";
								Message="DialyHunt student Id not found, can not upload Appointment.";
								return SUCCESS;	
							}
						}
						else
						{
							Status="202";
							Message="Student ID null";
							return SUCCESS;
						}
						
					
					}
					else 
					{
						Status="202";
						Message="Student id missing";
						return SUCCESS;			
					}
				}
				else
				{
					Status="202";
					Message="Parameter not fount";
					return SUCCESS;
				}
				
			}
			else if(hearderResult.equalsIgnoreCase("errorMethod"))
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
