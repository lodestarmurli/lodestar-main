package com.lodestar.edupath.PDFReport.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.studentinfo.StudentInfoService;
import com.lodestar.edupath.util.ApplicationProperties;
import com.opensymphony.xwork2.Action;

public class StudentTUMReportService implements Action {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(StudentTUMReportService.class);
	private int studentId;
	private List<StudentTUMDTO> studentTUMList = new ArrayList<StudentTUMDTO>();
	private StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
	private StudentCGTResult studentCGTResult = new StudentCGTResult();
	private Map<String, String> occupIndusMap = new HashMap<String, String>();
	private GeneratePDFReportFile generatePDFReportFileobj = new GeneratePDFReportFile();
	private String fileName;
	private InputStream fileInputStream;
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	public String execute() {
		try {
			OUT.debug("student id: {}", studentId);
			OUT.info("StudentTUMReportService : inside getStudentTUMReport method");
			// studentTUMList = new
			// StudentInfoService().getStudentTUMByStudentId(studentId);
			studentDetailsDTO = new StudentInfoService().getStudentDetailsById(studentId);
			List<StudentTUMDTO> tumList = null;
			Map<Long, String> studentQuesAnsMap = null;
			if (null != studentDetailsDTO) {

				tumList = studentDetailsDTO.getStudentTUMList();
				studentQuesAnsMap = studentDetailsDTO.getStudentQuesAnsMap();
			}
			if (tumList != null) {
				for (StudentTUMDTO studentTUMDTO : tumList) {
					studentQuesAnsMap.put((long) studentTUMDTO.getQuestionSlNo(), studentTUMDTO.getAnswer());
				}
			}

			studentCGTResult = new StudentInfoService().getStudentCGTResultByStudentId(studentId);
			if (studentCGTResult != null) {
				String occupationIds = studentCGTResult.getOccupationIds();
				if (occupationIds != null) {
					String[] occupaIdArray = occupationIds.split(",");
					for (String occupid : occupaIdArray) {
						OccupationDTO occupationDTO = new StudentInfoService()
								.getOccupationDetailsById(Integer.parseInt(occupid.trim()));
						if (occupationDTO != null) {
							occupIndusMap.put(occupationDTO.getName(), occupationDTO.getIndustryName());
						}
					}
				}
			}

			ByteArrayOutputStream baos = generatePDFReportFileobj.GenerateFile(studentDetailsDTO,studentCGTResult,occupIndusMap);

			if (baos != null) {
				fileName = properties.getProperty("com.edupath.report.tum.name");
				fileInputStream = new ByteArrayInputStream(baos.toByteArray());
			} else {
				return "error";
			}

		} catch (Exception e) {
			OUT.info(ApplicationConstants.EXCEPTION, e);
			return "error";
		}
		return "success";
	}

	public String getFileName() {
		return fileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<StudentTUMDTO> getStudentTUMList() {
		return studentTUMList;
	}

	public void setStudentTUMList(List<StudentTUMDTO> studentTUMList) {
		this.studentTUMList = studentTUMList;
	}

	public StudentDetailsDTO getStudentDetailsDTO() {
		return studentDetailsDTO;
	}

	public void setStudentDetailsDTO(StudentDetailsDTO studentDetailsDTO) {
		this.studentDetailsDTO = studentDetailsDTO;
	}

	public StudentCGTResult getStudentCGTResult() {
		return studentCGTResult;
	}

	public void setStudentCGTResult(StudentCGTResult studentCGTResult) {
		this.studentCGTResult = studentCGTResult;
	}

	public Map<String, String> getOccupIndusMap() {
		return occupIndusMap;
	}

	public void setOccupIndusMap(Map<String, String> occupIndusMap) {
		this.occupIndusMap = occupIndusMap;
	}

}
