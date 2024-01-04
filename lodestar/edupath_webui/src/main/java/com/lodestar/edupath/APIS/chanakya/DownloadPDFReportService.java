package com.lodestar.edupath.APIS.chanakya;

import java.io.File;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentOccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;

public class DownloadPDFReportService {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(DownloadPDFReportService.class);
	private byte[] inFileBytes = null;
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	private String inputFilePathFinalReport = properties.getProperty("com.Chanakya.pdf.template.DirectoryPath");

	public byte[] GenerateChankyaStudentReport(ChanakyaStudentOccupationDTO studentoccDTO) {

		OUT.info("DownloadPDFReportService : inside GenerateChankyaStudentReport method");
		OUT.debug("DownloadPDFReportService : inside GenerateChankyaStudentReport studentDTO:{}", studentoccDTO);

		String fileName = "ChanakyaReport_" + studentoccDTO.getPersonalityCode() + ".pdf";

		try {
			OUT.info("try block");
			File initialFile = new File(inputFilePathFinalReport + fileName);
			inFileBytes = Files.readAllBytes(initialFile.toPath());
		} catch (Exception ex) {
			OUT.info(ApplicationConstants.EXCEPTION, ex);
			inFileBytes = null;
		}
		return inFileBytes;
	}
}
