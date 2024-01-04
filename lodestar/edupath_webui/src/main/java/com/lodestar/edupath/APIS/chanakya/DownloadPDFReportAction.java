package com.lodestar.edupath.APIS.chanakya;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.APIS.chanakya.ChanakyaStudentOccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentOccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.EncryptionDecryptionData;

public class DownloadPDFReportAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(DownloadPDFReportAction.class);

	private String Status = "ERROR";

	private String Message = "";
	private String token;
	private String fileName;

	private ChanakyaStudentDetailsDTO studentDTO = new ChanakyaStudentDetailsDTO();
	private DownloadPDFReportService downloadPDFReportService = new DownloadPDFReportService();
	ChanakyaStudentOccupationDAO studentOccDAO = new ChanakyaStudentOccupationDAO();

	int stage;
	private InputStream fileInputStream;
	private byte[] inFileBytes = null;

	public String execute() {
		OUT.info("Inside DownloadPDFReportAction execute");
		if (token != null || token != "") {
			String jsonStr = new EncryptionDecryptionData().Decrypt(token);
			String hearderResult = "";
			JSONObject jsonDataObject = new JSONObject(jsonStr.trim());
			if (jsonDataObject.has("validateHeader")
					&& jsonDataObject.getString("validateHeader").equalsIgnoreCase("1")) {
				hearderResult = Util.validateGetHeader();
			} else {
				hearderResult = "success";
			}

			OUT.debug("Inside DownloadPDFReportAction execute hearderResult:{}", hearderResult);
			if (hearderResult.equalsIgnoreCase("success")) {

				try {

					if (jsonDataObject.has("StudentId") && jsonDataObject.has("Stage") && jsonDataObject.has("date")) {
						studentDTO.setId(Integer.parseInt(jsonDataObject.getString("StudentId")));
						stage = Integer.parseInt(jsonDataObject.getString("Stage"));
						Date jsondate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
								.parse(jsonDataObject.getString("date"));
						Date d2 = new Date();
						long diff = d2.getTime() - jsondate.getTime();

						long diffSeconds = diff / 1000 % 60;
						long diffMinutes = diff / (60 * 1000) % 60;
						long diffHours = diff / (60 * 60 * 1000) % 24;
						long diffDays = diff / (24 * 60 * 60 * 1000);

						if (diffMinutes > 5 && diffSeconds > 0) {
							Status = "ERROR";
							Message = "Token expired";
							return "data";
						}

					} else {
						Status = "ERROR";
						Message = "Invalid Token";
						return "data";
					}

					if (stage == 1) {
						ChanakyaStudentOccupationDTO studentoccDTO = studentOccDAO.getChanakyaStudentOccupationbyToken(token);
						inFileBytes = downloadPDFReportService.GenerateChankyaStudentReport(studentoccDTO);
						if (inFileBytes != null) {
							fileInputStream = new ByteArrayInputStream(inFileBytes);
						} else {
							Status = "ERROR";
							Message = "Internal Exception Level 1";
							return "error";

						}
					} else {
						Status = "ERROR";
						Message = "Invalid Token";
						return "data";
					}

				} catch (Exception e) {
					OUT.error(ApplicationConstants.EXCEPTION, e);
					Status = "ERROR";
					Message = "Internal Exception Level 1";
				}

			} else if (hearderResult.equalsIgnoreCase("errorMethod")) {
				Status = "204";
				Message = "Invalid Method";
				return "data";
			} else {
				Status = "204";
				Message = "Header invalid ";
				return "data";
			}

		} else {
			Status = "204";
			Message = "Token null ";
			return SUCCESS;
		}

		return "success";
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

}
