package com.lodestar.edupath.CDDBulkDownload.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHEdupathDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.RaisecCodeDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendationV2;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEdupathDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEntExamDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCode;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.programTest.CareerDegreeDiscovery.CDDPDFReportService;
import com.lodestar.edupath.programTest.CareerDegreeDiscovery.CareerDegreeDiscoveryRecommendation;
import com.lodestar.edupath.programTest.engineeringBranchSelector.EBS_Set2Service;

public class CDDBulkPDFReportDownload extends BaseAction {

	private String ldidFrom;
	private String ldidTo;

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(CDDBulkPDFReportDownload.class);
	StudentDetailsDTO studentId;
	private String fileName;
	private InputStream fileInputStream;
	private ByteArrayOutputStream baos;
	private SqlSession session = null;
	private Map<String, Object> systemRecommendation = new HashMap<String, Object>();
	List<OccupationDTO> cgtOcc = new ArrayList<OccupationDTO>();
	CDDPDFReportService service = new CDDPDFReportService();
	StudentDetailsDTO student = null;
	StudentDetailsDAO studentDAO = new StudentDetailsDAO();
	CDDRecommendationBulk _CDDRService = new CDDRecommendationBulk();
	List<byte[]> reportList = new ArrayList<>();
	String currentLDID;
	List<String> reportLDIDs = new ArrayList<>();

	public String execute() {
		OUT.info("Inside CareerDegreeDiscoveryPDFReport execute");
		try {
			OUT.debug("bharath CareerDegreeDiscoveryPDFReport getUserSessionObject():{}", getUserSessionObject());
			if (getUserSessionObject() == null) {
				return "SessionExpired";
			}

			int fromId = Integer.parseInt(ldidFrom.substring(2));
			int toId = Integer.parseInt(ldidTo.substring(2));

			for (int currentId = fromId; currentId <= toId; currentId++) {

				currentLDID = "LD" + currentId;
				System.out.println("Processing LDID: " + currentLDID);

				student = (StudentDetailsDTO) MyBatisManager.getInstance()
						.getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_BY_USERID_FOR_CDD_BULK, currentId);

				if (student == null) {
					System.out.println("Student is null for LDID: " + currentLDID + ".Skipping.");
					continue;
				}

				int studentIdValue = student.getId();
				// systemRecommendation = new
				// SystemRecommendationV2().getSystemRecommendation(student.getId(), false);
				systemRecommendation = new EBS_Set2Service().getSystemRecommendation(studentIdValue, false);
				StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance()
						.getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT, studentIdValue);
				
				RaisecCodeDTO raiseccodeDTO = new RaisecCodeDTO();
				RaisecCodeDAO raiseccodeDAO = new RaisecCodeDAO();
				raiseccodeDTO = raiseccodeDAO.getRaisecCode(cgtResult.getPersonalityCode());
				String raiseccode = raiseccodeDTO.getRaisec();
				String[] raiseccodeSplit = raiseccode.split(",");
				String raisecfullform = "";
				for (String c : raiseccodeSplit) {
					for (RaisecCode RCenum : RaisecCode.values()) {
						if (c.trim().equalsIgnoreCase(RCenum.name())) {
							raisecfullform += RCenum.Value() + " ";
						}
					}
				}
				raiseccodeDTO.setFullFrom(raisecfullform);
				
				
				
				OUT.debug("bharath CareerDegreeDiscoveryPDFReport cgtResult:{}", cgtResult);
				cgtOcc = _CDDRService.getCareerDegreeDiscoveryRecommendation(student);
				
				if (cgtOcc.isEmpty()) {
					System.out.println("Occupations is null due to low aptitude for LDID: " + currentLDID + ".Skipping.");
					continue;
				}
				
				OUT.debug("bharath CareerDegreeDiscoveryPDFReport cgtOcc:{}", cgtOcc);

				List<Integer> occupationIds = new ArrayList<Integer>();
				for (OccupationDTO occ : cgtOcc) {
					occupationIds.add(occ.getId());
				}
				List<OccupationDTO> occlistwithfitment = cgtOcc;
				List<DHEdupathDTO> edupathlist = DHEdupathDAO.getDHEdupathbyOccId(occupationIds);
				for (OccupationDTO occ : occlistwithfitment) {
					for (DHEdupathDTO edu : edupathlist) {
						if (occ.getId() == edu.getOccupationId()) {
							// edu.setOccName(occ.getName());
							// edu.setDhPriority(occ.getDhPriority());
							occ.setDheduDTO(edu);
						}
					}
				}

				OUT.debug("updated fitement edupathlist:{}", edupathlist);
				Collections.sort(occlistwithfitment, new OccupationPrioritySorter());
				OUT.debug("updated fitement occlistwithfitment:{}", occlistwithfitment);
				List<String> examList = new ArrayList<String>();
				for (DHEdupathDTO dhedupath : edupathlist) {
					String[] examNamelist = dhedupath.getEntExam().split(",");
					for (String _examName : examNamelist) {
						examList.add(_examName.trim());
					}

				}
				List<DHEntExamDTO> entExamlist = DHEdupathDAO.getexamdescription(examList);
				Map<String, String> app_score = new HashMap<String, String>();
				String _appscorestr = (String) systemRecommendation
						.get(ApplicationConstants.SystemRecommendation.APP_SCORE.name());
				String[] keyVals = _appscorestr.split(",");
				for (String keyVal : keyVals) {
					String[] parts = keyVal.split(":", 2);
					app_score.put(parts[0], parts[1]);
				}
	

				if (edupathlist.isEmpty()) {
					return "reporterror";
				}

				if (student.getClassId() == 1 || student.getClassId() == 2) {
					System.out.println("Processing LDID for Report: " + currentLDID);
					baos = service.GeneratePDFReportClasss910(student, occlistwithfitment, raiseccodeDTO, app_score,
							entExamlist, edupathlist);
				} else {
					System.out.println("Processing LDID for Report: " + currentLDID);
					baos = service.GeneratePDFReportClasssRest(student, occlistwithfitment, raiseccodeDTO, app_score,
							entExamlist, edupathlist);
				}

				if (baos != null) {
					reportList.add(baos.toByteArray());
					reportLDIDs.add(currentLDID);
				}
			}

		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;

		}
		if (!reportList.isEmpty()) {
			// Now, reportList contains individual PDF reports.
			if (reportList.size() == 1) {
				// If there is only one report, download it individually
				fileName = "Lodestar_Career_Degree_Discovery_Report.pdf";
				fileInputStream = new ByteArrayInputStream(reportList.get(0));
				return "success";
			} else {
				// If there are multiple reports, create a ZIP archive
				try {
					fileName = "Lodestar_Career_Degree_Discovery_Reports.zip";
					ByteArrayOutputStream zipBaos = new ByteArrayOutputStream();
					ZipOutputStream zipOutputStream = new ZipOutputStream(zipBaos);

					for (int i = 0; i < reportList.size(); i++) {
						ByteArrayInputStream reportInputStream = new ByteArrayInputStream(reportList.get(i));
						zipOutputStream.putNextEntry(new ZipEntry("Report_" + reportLDIDs.get(i) + ".pdf"));
						byte[] buffer = new byte[8192];
						int len;
						while ((len = reportInputStream.read(buffer)) > 0) {
							zipOutputStream.write(buffer, 0, len);
						}
						zipOutputStream.closeEntry();
						reportInputStream.close();
					}

					zipOutputStream.close();
					fileInputStream = new ByteArrayInputStream(zipBaos.toByteArray());
					System.out.println(reportLDIDs);

				} catch (Exception e) {
					OUT.error(ApplicationConstants.EXCEPTION, e);
					return ERROR;
				}

				return "success";
			}
		} else {
			return "reporterror";
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getLdidFrom() {
		return ldidFrom;
	}

	public void setLdidFrom(String ldidFrom) {
		this.ldidFrom = ldidFrom;
	}

	// Getter and setter methods for ldidTo
	public String getLdidTo() {
		return ldidTo;
	}

	public void setLdidTo(String ldidTo) {
		this.ldidTo = ldidTo;
	}

}
