package com.lodestar.edupath.APIS;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.GenderTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.PROGRAMTEST;
import com.lodestar.edupath.programTest.streamselector.Util;


public class ProgramTestStudentCreationAPI extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(ProgramTestStudentCreationAPI.class);
	private String id;
	private String Status = "ERROR";
	private StudentDetailsDTO studentDTO = new StudentDetailsDTO();
	private String Message = "";
	ProgramTestStudentCreationAPIService service = new ProgramTestStudentCreationAPIService();

	public String execute() throws Exception {
		
		String hearderResult = Util.validatePostHeader();

		try
		{


			if (hearderResult.equalsIgnoreCase("success")) {

				String Data = Util.getDataForStudentCreation();
				OUT.debug("Data Receive=>" + Data);
				if(Data!=null && !Data.equals("") && !Data.equals("error"))
				{
					String JsonData = Data;
					JSONObject jsonDataObject = new JSONObject(Data.trim());
					OUT.debug("jsonDataObject Receive=>" + jsonDataObject);
					
//					StudentDetailsDTO studentDTO = new StudentDetailsDTO();
					if(jsonDataObject.has("studentname") )
					{
							if(jsonDataObject.getString("studentname").trim() != null || !jsonDataObject.getString("studentname").trim().equalsIgnoreCase(" "))
							{
								studentDTO.setName(jsonDataObject.getString("studentname").trim());
							}
							else
							{
								Status="202";
								Message=" student name missing";
								return SUCCESS;
							}
					}		
					else
					{
						Status="202";
						Message=" student name missing";
						return SUCCESS;
					}
					
					if(jsonDataObject.has("studentemail") )
					{
						if(  jsonDataObject.getString("studentemail").trim() != null || !jsonDataObject.getString("studentemail").trim().equalsIgnoreCase(" ")){
							studentDTO.setStudentemailId(jsonDataObject.getString("studentemail").trim());
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
					
					if(jsonDataObject.has("class") )
					{
						if(  jsonDataObject.getString("class").trim() != null || !jsonDataObject.getString("class").trim().equalsIgnoreCase(" ")){
							if(jsonDataObject.getString("class").trim().equalsIgnoreCase("9")) {
								studentDTO.setClassId(1);
							}
							else if(jsonDataObject.getString("class").trim().equalsIgnoreCase("10")) {
								studentDTO.setClassId(2);
							}
							else if(jsonDataObject.getString("class").trim().equalsIgnoreCase("11")) {
								studentDTO.setClassId(3);
							}
							else if(jsonDataObject.getString("class").trim().equalsIgnoreCase("12")) {
								studentDTO.setClassId(4);
							}
							else if(jsonDataObject.getString("class").trim().equalsIgnoreCase("12 Plus")) {
								studentDTO.setClassId(5);
							}else
							{
								Status="202";
								Message="class mismatch";
								return SUCCESS;
							}
							
						}else
						{
							Status="202";
							Message="class missing";
							return SUCCESS;
						}
					}
					else
					{
						Status="202";
						Message="class missing";
						return SUCCESS;
					}

					if(jsonDataObject.has("gender") )
					{
						if(  jsonDataObject.getString("gender").trim() != null || !jsonDataObject.getString("gender").trim().equalsIgnoreCase(" ")){
							if(jsonDataObject.getString("gender").trim().equalsIgnoreCase(GenderTypeEnum.M.name()) || jsonDataObject.getString("gender").trim().equalsIgnoreCase(GenderTypeEnum.M.getGender()) || jsonDataObject.getString("gender").trim().equalsIgnoreCase("Male"))
							{
								studentDTO.setGender(GenderTypeEnum.M.name());
							}
							else if(jsonDataObject.getString("gender").trim().equalsIgnoreCase(GenderTypeEnum.F.name()) || jsonDataObject.getString("gender").trim().equalsIgnoreCase(GenderTypeEnum.F.getGender()) || jsonDataObject.getString("gender").trim().equalsIgnoreCase("Female"))
							{
								studentDTO.setGender(GenderTypeEnum.F.name());
							}
						}else
						{
							Status="202";
							Message="gender missing";
							return SUCCESS;
						}
					}
					else
					{
						Status="202";
						Message="gender missing";
						return SUCCESS;
					}
					
					
					
					 
					if(jsonDataObject.has("parentName") )
					{
						if( jsonDataObject.getString("parentName").trim() != null || !jsonDataObject.getString("parentName").trim().equalsIgnoreCase(" "))
						{
							studentDTO.setFathername(jsonDataObject.getString("parentName").trim());
							studentDTO.setFatherName(jsonDataObject.getString("parentName").trim());
						}
						else
						{
							Status="202";
							Message="parentName missing";
							return SUCCESS;
						}
					}
					else
					{
						Status="202";
						Message="parentName missing";
						return SUCCESS;
					}

					if(jsonDataObject.has("parentEmail") )
					{
						if(  jsonDataObject.getString("parentEmail").trim() != null || !jsonDataObject.getString("parentEmail").trim().equalsIgnoreCase(" ")){
							studentDTO.setFatherEmailId(jsonDataObject.getString("parentEmail").trim());
							studentDTO.setFatheremailId(jsonDataObject.getString("parentEmail").trim());
						}else
						{
							Status="202";
							Message="ParentEmail missing";
							return SUCCESS;
						}
					}
					else
					{
						Status="202";
						Message="ParentEmail missing";
						return SUCCESS;
					}
					
					if(jsonDataObject.has("parentPhone") )
					{
						if(  jsonDataObject.getString("parentPhone").trim() != null || !jsonDataObject.getString("parentPhone").trim().equalsIgnoreCase(" "))
						{
							studentDTO.setFathercontactno(jsonDataObject.getString("parentPhone").trim());
							studentDTO.setContactNumber(jsonDataObject.getString("parentPhone").trim());
						}else
						{
							Status="202";
							Message="parentPhone missing";
							return SUCCESS;
						}
					}
					else
					{
						Status="202";
						Message="parentPhone missing";
						return SUCCESS;
					}

					studentDTO.setStudentType(StudentTypeEnum.TRIAL);
					studentDTO.setSource(null);
					studentDTO.setSchoolId(1);
					
					if(jsonDataObject.has("package") )
					{
						if(  jsonDataObject.getString("package").trim() != null || !jsonDataObject.getString("package").trim().equalsIgnoreCase(" "))
						{
							EActionStatus status;
							OUT.debug("bharath StreamSelectorAPI studentDTO:{}",studentDTO);
							for (PROGRAMTEST ptenum : PROGRAMTEST.values()) {
								if (jsonDataObject.getString("package").trim().equals(ptenum.getPackageName().trim())) {
									studentDTO.setSource(ptenum.getSource());
								}
							}
							
							if(studentDTO.getSource()!=null)
							{
								status = service.insertStudent(studentDTO);
							
								if (status == EActionStatus.FAILURE) 
								{
									Status="ERROR";
									Message="Internal Exception Level 2";
									return SUCCESS;
								}
								else {
									Status="200";
									Message="Successful upload";
									return SUCCESS;
								}
							}
							else {
								Status="202";
								Message="package mismatch";
								return SUCCESS;
							}
						}else
						{
							Status="202";
							Message="package missing";
							return SUCCESS;
						}
					}
					else
					{
						Status="202";
						Message="package missing";
						return SUCCESS;
					}
					
					
					
				}else{
					Status="204";
					Message="Parameter null";
					return SUCCESS;
				}

			} else if(hearderResult.equalsIgnoreCase("errorMethod"))
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

	public StudentDetailsDTO getStudentDTO() {
		return studentDTO;
	}

	public void setStudentDTO(StudentDetailsDTO studentDTO) {
		this.studentDTO = studentDTO;
	}

	
	
	
	
}
