package com.lodestar.edupath.APIS.chanakya;


import com.lodestar.edupath.base.BaseAction;
//import org.apache.http.Header;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClientBuilder;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTResultDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.DHStudentTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.GenderTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StreamTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.HttpHeaders;
import org.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentAptitudeTestAction extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentAptitudeTestAction.class);
	
	
	private String   								Status="ERROR";
	
	private String   								Message="";
	
	private int 									result=0;
	private StudentAptitudeTestService service; 
	
	
	public StudentAptitudeTestAction()
	{
		service =new StudentAptitudeTestService();
	}
	

	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute  the StudentAptitudeTestAction Summary Action Of======================= {}");
		
		DHStudentDetailsDTO studentDTO = new DHStudentDetailsDTO();
		
		try
		{

			String hearderResult = Util.validatePostHeader();
			if (hearderResult.equalsIgnoreCase("success")) 
			{
				String Data = Util.getData();
				OUT.debug("Data Receive=>" + Data);
				OUT.debug("Data!=null Receive=>:{}" , Data!=null);
				OUT.debug("!Data.equals() Receive=>" + !Data.equals(""));
				OUT.debug("!Data.equals() Receive=>" + !Data.equals("error"));
				if(Data!=null && !Data.equals("") && !Data.equals("error"))
				{
					String JsonData=Data;
					JSONObject jsonDataObject = new JSONObject(Data.trim());
					OUT.debug("jsonDataObject Receive=>"+jsonDataObject);
					if(jsonDataObject.has("testresult") && jsonDataObject.has("studentid") && jsonDataObject.has("studentType") )
					{
						if(jsonDataObject.getString("studentid").trim() != null && !jsonDataObject.getString("studentid").trim().equalsIgnoreCase(" "))
						{	
							String DHID = jsonDataObject.getString("studentid");
							studentDTO = service.getStudentAptitudeByDHID(DHID);
							if(studentDTO != null )
							{			
								if(studentDTO.getAptitudeComplete() == null || studentDTO.getAptitudeComplete().equals("") )		
								{

									OUT.debug(" studentDTO:{} =>",studentDTO);
									if(jsonDataObject.getString("studentType").trim() != null && !jsonDataObject.getString("studentType").trim().equalsIgnoreCase(" "))
									{
										if(jsonDataObject.getString("studentType").trim().equals(DHStudentTypeEnum.TYPE1.getText()))
										{
											studentDTO.setStudentType(DHStudentTypeEnum.TYPE1);
										}
										else if(jsonDataObject.getString("studentType").trim().equals(DHStudentTypeEnum.TYPE2.getText()))
										{
											studentDTO.setStudentType(DHStudentTypeEnum.TYPE2);
										}
										else 
										{
											Status="202";
											Message="studentType mismatch";
											return SUCCESS;	
										}
									}
									else 
									{
										Status="202";
										Message="studentType missing";
										return SUCCESS;	
									}
									
									
									JSONObject jsonDataTestObject = jsonDataObject.getJSONObject("testresult");
									if(jsonDataTestObject!=null )
									{
										
										OUT.debug("jsonDataTestObject Receive=>"+jsonDataTestObject);
										List<DHStudentCGTDTO> studentcgtList = new ArrayList<DHStudentCGTDTO>();
										
										
										for(Iterator iterator = jsonDataTestObject.keySet().iterator(); iterator.hasNext();) 
										{
											DHStudentCGTDTO studentcgtDTO = new DHStudentCGTDTO();
											String qID = (String) iterator.next();
											String ans=  (String)jsonDataTestObject.getString(qID);

											studentcgtDTO.setStudentId(studentDTO.getId());
											studentcgtDTO.setQuestionId(Integer.parseInt(qID)+60);
											studentcgtDTO.setAnswer(ans);
											studentcgtList.add(studentcgtDTO);
										}
										
										OUT.debug("studentcgtList Receive=>"+studentcgtList);
										result = service.updateStudentType(studentDTO);
										result= service.uploadAptitudeTest(studentcgtList,studentDTO.getId());
										
									}
									else 
									{
										Status="202";
										Message="Test results missing";
										return SUCCESS;	
									}
									if (result != 0) 
									{
										Status = "200";
										Message = "Successful upload";
									}
									else {
										Status="202";
										Message="Unsuccessful upload";
										return SUCCESS;
									}
								}
								else
								{
									Status="202";
									Message="Test results for student id " + studentDTO.getDHID() +" exists";
									return SUCCESS;		
								}

							}
							else
							{
								Status="404";
								Message="DialyHunt student Id not found, can not upload test results";
								return SUCCESS;	
							}

						}
						else
						{
							Status="202";
							Message="student Id or testresults or studentType is null";
							return SUCCESS;
						}		
					}
					else
					{
						Status="202";
					Message="student Id or testresults or studentType is missing";
					return SUCCESS;
					}
				
				}else{
					Status="204";
					Message="Parameter null";
					return SUCCESS;
				}

			}else if(hearderResult.equalsIgnoreCase("errorMethod"))
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
