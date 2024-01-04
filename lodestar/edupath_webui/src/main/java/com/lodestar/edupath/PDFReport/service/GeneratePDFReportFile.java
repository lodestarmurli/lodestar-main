package com.lodestar.edupath.PDFReport.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.gagawa.java.elements.Var;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.IntegratedCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCollegeShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTutorialCentreShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.collegeparameter.CollegeParametersDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.CombinationDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.SubjectDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.IndustryDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.studentparameter.StudentCollegeParametersDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.finalsummary.bean.ReportSummaryBean;
import com.lodestar.edupath.school.service.ManageSchoolService;
import com.lodestar.edupath.studentinfo.StudentInfoService;
import com.lodestar.edupath.util.ApplicationProperties;


public class GeneratePDFReportFile {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(GeneratePDFReportFile.class);
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	private String inputFilePathTUMReport = properties.getProperty("com.edupath.report.template.tum.location");
	private String inputFilePathTUMReport2ndpart = properties
			.getProperty("com.edupath.report.template.tum.2ndpart.location");
	private String inputFilePathFinalReport = properties
			.getProperty("com.edupath.report.template.DirectoryPath")+"template.pdf";
	private ByteArrayOutputStream baos = null;
	private StudentDetailsDTO studentdetailsDTO;
	private SchoolDTO schoolDTO;

	public static int PageNo=0;
	public static boolean LastPageNo=false;

	
	
	public ByteArrayOutputStream GenerateFile(StudentDetailsDTO studentDetailsDTO, StudentCGTResult studentCGTResult,
			Map<String, String> occupIndusMap) {
		OUT.info("GeneratePDFReportFile : inside GenerateFile method");
		OUT.info("InputFile Name : " + inputFilePathTUMReport);
		String Score = "-";
		String Occupation = "";
		String PersonalityCode = "";
		String Name = "";
		String Class = "";
		String School = "";
		String Father = "";
		String School_sub = "";
		String Mother = "";
		String Considarion = "";
		String Considarion_sub = "";
		String With = "";
		String Strenth = "";
		String Hobbies = "";
		String Acadamic = "";
		String Subject = "";
		String Aspiration = "";
		String Things = "";
		String location = "";
		String BrTag = "";
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		JFreeChart chart = null;
		
		try {
			if (studentCGTResult != null) {
				if (studentCGTResult.getAppScore() != null && !studentCGTResult.getAppScore().isEmpty())
					Score = studentCGTResult.getAppScore();
				if (studentCGTResult.getPersonalityCode() != null)
					PersonalityCode = studentCGTResult.getPersonalityCode();
				
				if (!Score.equals(null) && !Score.trim().equals("") && !Score.trim().equals("-")) {

					String[] Catagory = Score.split(",");
					
					for (int k = 0; k < Catagory.length; k++) {
						String[] CatagoryScore = Catagory[k].split(":");

						if (CatagoryScore[1].trim().equals("L")) {
							dataSet.setValue(30, "Abilities Score", CatagoryScore[0].trim());
						} else if (CatagoryScore[1].trim().equals("M")) {
							dataSet.setValue(60, "Abilities Score", CatagoryScore[0].trim());
						} else if (CatagoryScore[1].trim().equals("H")) {
							dataSet.setValue(100, "Abilities Score", CatagoryScore[0].trim());
						}
					}
					chart = ChartFactory.createBarChart("* Scores are indicative", "", "", dataSet,
							PlotOrientation.VERTICAL, false, true, false);
					chart.getCategoryPlot().getRangeAxis().setUpperBound(100);
					BrTag = "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>";

				}
				
			}
			
			if (studentDetailsDTO != null) {
				
				if (studentDetailsDTO.getName() != null)
					Name = studentDetailsDTO.getName();		
				if (studentDetailsDTO.getClassName() != null)
					Class = studentDetailsDTO.getClassName();
				if (studentDetailsDTO.getSchoolName() != null)
					School = studentDetailsDTO.getSchoolName();
				if(studentDetailsDTO.getStudentQuesAnsMap()!=null)
				{
					if (studentDetailsDTO.getStudentQuesAnsMap().get(1l) != null)
						Father = studentDetailsDTO.getStudentQuesAnsMap().get(1l);
					if (studentDetailsDTO.getStudentQuesAnsMap().get(3l) != null)
						School_sub = studentDetailsDTO.getStudentQuesAnsMap().get(3l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(2l) != null)
						Mother = studentDetailsDTO.getStudentQuesAnsMap().get(2l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(5l) != null)
						Considarion = studentDetailsDTO.getStudentQuesAnsMap().get(5l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(6l) != null)
						Considarion_sub = studentDetailsDTO.getStudentQuesAnsMap().get(6l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(4l) != null)
						With = studentDetailsDTO.getStudentQuesAnsMap().get(4l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(8l) != null)
						Strenth = studentDetailsDTO.getStudentQuesAnsMap().get(8l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(9l) != null)
						Hobbies = studentDetailsDTO.getStudentQuesAnsMap().get(9l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(7l) != null)
						Acadamic = studentDetailsDTO.getStudentQuesAnsMap().get(7l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(10l) != null)
						Subject = studentDetailsDTO.getStudentQuesAnsMap().get(10l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(11l) != null)
						Aspiration = studentDetailsDTO.getStudentQuesAnsMap().get(11l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(12l) != null)
						Things = studentDetailsDTO.getStudentQuesAnsMap().get(12l);
					
					if (studentDetailsDTO.getStudentQuesAnsMap().get(13l) != null)
						location = studentDetailsDTO.getStudentQuesAnsMap().get(13l);
				}
				
			}
			
			for (Map.Entry<String, String> occup : occupIndusMap.entrySet()) {
				if (Occupation != "") {
					Occupation = Occupation + ", " + occup.getKey() + " (" + occup.getValue() + ")";
				} else {
					Occupation = occup.getKey() + " (" + occup.getValue() + ")";
				}
			}
			
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathTUMReport);
			Rectangle pagesize = pdfReader.getPageSize(1);
			Document document = new Document(pagesize, 0, 0, 0, 0);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.open();
			PdfContentByte cb = writer.getDirectContentUnder();
			for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {

				PdfImportedPage page = writer.getImportedPage(pdfReader, i);
				document.newPage();
				cb.addTemplate(page, 0, 0);
				if (i == 6) {
					Paragraph p = new Paragraph("\n");
					p.setIndentationLeft(5);
					p.setIndentationRight(5);
					p.setLeading(5, 11);
					document.add(p);

					StringBuilder sb = new StringBuilder();
					sb.append("<table width=\"100%\" height=\"100\" border=\"0\" style=\"background-color:#fafafa;\">"
							+ "<tr>" + "<td><br/>" + "<p style=\"padding-bottom: 15px;\">"
							+ "<span style=\"color:#acacac;font-size:18f;\">" + "SELECTED STUDENT : " + "</span>"
							+ "<span style=\"color:#333;font-size:16f;\"><b>" + Name + "" + "</b></span>" + "<br/>"
							+ "</p></td>" + "</tr>" + "</table>" + "<table width=\"100%\" height=\"396\" border=\"0\">"
							+ "<tr valign=\"top\">" + "<td width=\"100%\" height=\"392\">"
							+ "<table width=\"100%\" border=\"0\">" + "<tr>" + "<td width=\"311\">"
							+ "<span style=\"color:#0871bb;font-size:18f;\">Personal &amp; Academic info" + "</span>"
							+ "</td>" + "<td width=\"222\"><br/><br/><br/><br/><br/></td>" + "</tr>"
							+ "<tr valign=\"top\">" + "<td><p style=\"font-size:15f;\">" + "<b>Name</b>  : "
							+ "<span style=\"font-size:14f;\"><b>" + Name + " ( Class " + Class + " )" + "</b></span>"
							+ "</p>" + "<p style=\"font-size:15f;line-height:50px;\"><b>Father</b>:"
							+ "<span style=\"font-size:14f;\"> " + Father + "</span>" + "</p>" + "</td>" + "<td>"
							+ "<p style=\"font-size:15f;line-height:30px;\"><b>School</b> : "
							+ "<span style=\"font-size:14f;\">" + School + " (" + School_sub + ")" + "</span>" + "</p>"
							+ "</td>" + "</tr>" + "<tr>" + "<td style=\"vertical-align: top;text-align: left;\">"
							+ "<br/><span style=\"font-size:15f;\"><b>Mother</b> :</span>"
							+ "<span style=\"font-size:14f;line-height:30px;\"> " + Mother + "</span>" + "</td>"
							+ "<td>" + "<br/><p style=\"font-size:15f;line-height:30px;\"><b></b>" // Class
																									// 11
																									// Consideration:
							+ "<span style=\"font-size:14f;\">" + ""// Considarion
							+ ""// " ("+Considarion_sub+")"
							+ ""// " With "
							+ ""// With
							+ ""// " Syllabus"
							+ "</span>" + "</p>" + "</td>" + "</tr>" + "<tr>" + "<td><br/><br/>" + "</td>"
							+ "<td><br/><br/>" + "</td>" + "</tr>" + "<tr>" + "<td colspan=\"2\">"
							+ "<br/><span style=\"color:#0871bb;font-size:18f;\">Hobbies &amp; Interest" + "</span>"
							+ "</td>" + "</tr>" + "<tr>" + "<td colspan=\"2\">"
							+ "<br/><p style=\"font-size:15f;padding-bottom: 15px;\"><b>Self Evaluation : </b></p>"
							+ "</td>" + "</tr>" + "<tr>" + "<td colspan=\"2\">"
							+ "<p style=\"font-size:14f;line-height:30px;\">" + Strenth + "</p>" + "</td>" + "</tr>"
							+ "<tr>" + "<td colspan=\"2\">" + "<br/>" + "</td>" + "</tr>" + "<tr>"
							+ "<td colspan=\"2\">"
							+ "<br/><p style=\"font-size:15f;padding-bottom: 15px;\"><b>Hobbies : </b></p>" + "</td>"
							+ "</tr>" + "<tr>" + "<td colspan=\"2\">" + "<p style=\"font-size:14f;line-height:30px;\">"
							+ Hobbies + "</p>" + "</td>" + "</tr>" + "<tr>" + "<td colspan=\"2\"><br/></td>" + "</tr>"
							+ "<tr>" + "<td colspan=\"2\">"
							+ "<br/><p style=\"font-size:15f;padding-bottom: 15px;\"><b>Most Interested Subjects</b> : </p>" + "</td>"
							+ "</tr>" + "<tr>" + "<td colspan=\"2\">"
							+ "<span style=\"font-size:14f;line-height:30px;\">" + Subject + "</span><br/>" + "</td>"
							+ "</tr>" + " <tr>" + "<td colspan=\"2\">"
							+ "<br/><p style=\"font-size:15f;padding-bottom: 15px;\"><b>Work Aspiration : </b></p>"
							+ "</td>" + "</tr>" + "<tr>" + "<td colspan=\"2\">" + "<span style=\"font-size:14f;line-height:30px;\">"
							+ Aspiration + "</span>" + "</td>" + "</tr>" + "</table>" + "</td>"
							+ "</tr>" + "</table>");
							
							
							

					// sb.append("<b>&nbsp;<font color=\"#32cd32\">My centered
					// Para</font></b>");
					// sb.append("</font>");
					// sb.append("<font color=\"#32cd32\">&nbsp;</font>");
					// sb.append("</p>\n</div>");
					PdfPTable table = new PdfPTable(1);

					PdfPCell cell = new PdfPCell();
					cell.setBorder(Rectangle.NO_BORDER);
					table.setWidthPercentage(90);
					cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
					ElementList list = XMLWorkerHelper.parseToElementList(sb.toString(), null);
					for (Element element : list) {
						cell.addElement(element);
					}

					table.addCell(cell);
					document.add(table);

					

				}
				
				if(i==7)
				{
					Paragraph p = new Paragraph("\n");
					p.setIndentationLeft(5);
					p.setIndentationRight(5);
					p.setLeading(5, 9.7f);
					document.add(p);
					StringBuilder sb = new StringBuilder();
					sb.append("<table width=\"100%\"><tr valign=\"top\">"
					        + "<td width=\"100%\">"
							+ "<table width=\"100%\" height=\"333\" border=\"0\">" + "<tr>" + "<td colspan=\"2\">"
							+ "<br/><span style=\"color:#0871bb;font-size:18f;\">Assessment Result</span>" + "</td>"
							+ "</tr>" + "<tr>" + "<td colspan=\"2\">"
							+ "<br/><p style=\"font-size:15f;padding-bottom: 10px;\"><b>Abilities Score : </b>"
							+ "<span style=\"font-size:14f;line-height:30px;\">" + Score + "</span></p>" + "</td>"
							+ "</tr>" + "<tr>" + "<td colspan=\"2\">"
							+ "<p><span style=\"font-size:14f;line-height:30px;\">" + BrTag + "</span></p>" + "</td>"
							+ "</tr>" + "<tr>" + "<td colspan=\"2\">"
							+ "<br/><p style=\"font-size:15f;padding-bottom: 15px;\"><b>RIASEC Interest Code : </b>"
							+ "<span style=\"font-size:14f;line-height:30px;\">" + PersonalityCode + "</span></p>"
							+ "</td>" + "</tr>" 
//							 
							+ "</table>" + "</td>" + "</tr>" + "</table>");
					
					
					PdfPTable table = new PdfPTable(1);

					PdfPCell cell = new PdfPCell();
					cell.setBorder(Rectangle.NO_BORDER);
					table.setWidthPercentage(90);
					cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
					ElementList list = XMLWorkerHelper.parseToElementList(sb.toString(), null);
					for (Element element : list) {
						cell.addElement(element);
					}

					table.addCell(cell);
					document.add(table);
					if (chart != null) {
						PdfTemplate pie = cb.createTemplate(600, 210);
						Graphics2D g2d1 = new PdfGraphics2D(pie, 600, 210);
						Rectangle2D r2d1 = new Rectangle2D.Double(0, 0, 600, 210);
						chart.draw(g2d1, r2d1);
						g2d1.dispose();

						Image img = Image.getInstance(pie);

						img.setAbsolutePosition(220f, 335f);
						document.add(img);
					}
					
					
				}
			}

			PdfReader pdfReader1 = new PdfReader(inputFilePathTUMReport2ndpart);
			Rectangle pagesize1 = pdfReader1.getPageSize(1);
			document.setPageSize(pagesize1);
			for (int i = 1; i <= pdfReader1.getNumberOfPages(); i++) {
				PdfImportedPage page = writer.getImportedPage(pdfReader1, i);
				document.newPage();
				cb.addTemplate(page, 0, 0);

			}

			document.close();
			pdfReader1.close();
			pdfReader.close();
			writer.close();

		} catch (Exception ex) {
			OUT.error(ApplicationConstants.EXCEPTION, ex);
			baos = null;
		}

		return baos;

	}

	
	public ByteArrayOutputStream GenerateStudentFinalReport(ReportSummaryBean bean, StudentCGTResult studentCGTResult,
			Map<String, String> occupIndusMap) {
		PageNo=0;
		LastPageNo=false;
		OUT.info("GeneratePDFReportFile : inside GenerateStudentFinalReport method");
		String Name = "";
		String PrefaceParagraph = "";
		int TotalIndustriesexplored=0;
		int TotalOccupationsdiscovered=0;
		int TotalCoursesexamined=0;
		String IndustrychoiceMyPlane_DB="";
		String IndustrychoiceAlternatePlan_DB="";
		String OCCUPATIONCHOICEMyPlane_DB="";
		String OCCUPATIONCHOICEAlternatePlan_DB="";
		
		String MYPLAN_EDUPATH_streamName_DB=null;
		String MYPLAN_EDUPATH_combinationName_DB=null;
		String MYPLAN_EDUPATH_degreeStream_DB=null;
		String MYPLAN_EDUPATH_pgStream_DB=null;
		String MYPLAN_EDUPATH_occupationName_DB=null;
		String MYPLAN_EDUPATH_industryName_DB=null;
		
		String ALTERNATEPLAN_EDUPATH_streamName_DB=null;
		String ALTERNATEPLAN_EDUPATH_combinationName_DB=null;
		String ALTERNATEPLAN_EDUPATH_degreeStream_DB=null;
		String ALTERNATEPLAN_EDUPATH_pgStream_DB=null;
		String ALTERNATEPLAN_EDUPATH_occupationName_DB=null;
		String ALTERNATEPLAN_EDUPATH_industryName_DB=null;
		
		String SUBJECT_CHOICE_TEXT_DB=null;
		String SUBJECT_CHOICE_COMBINATION_TEXT_DB=null;
		String SUBJECT_CHOICE_FINAL_TEXT_DB=null;
		int MyPlanBlockCount=0;
		int AlternatePlanBlockCount=0;
		
		String EXAMS_TO_PREPARE_MY_PLAN_DB=null;
		String EXAMS_TO_PREPARE_ALTERNATE_PLAN_DB=null;
		int OccupationID_MYPLAN=0;
		int OccupationID_ALTERNATEPLAN=0;
		
		String TUTORIAL_SELECTION_DB=null;
		String PARAMETERS_SELECTED_DB=null;
		
		String COLLEGE_SCHOOL_FOR_COMPARISON_DB=null;
		
		Paragraph pMyplan_TEXT=new Paragraph();
        pMyplan_TEXT.setAlignment(Element.ALIGN_CENTER);
        
        Paragraph pAlternateplan_TEXT=new Paragraph();
        pAlternateplan_TEXT.setAlignment(Element.ALIGN_CENTER);
        
        
        
        Paragraph TUTORIAL_SELECTION_Myplan_TEXT=new Paragraph();
        TUTORIAL_SELECTION_Myplan_TEXT.setAlignment(Element.ALIGN_CENTER);
        
		
        Paragraph COLLEGE_SCHOOL_FOR_COMPARISON_TEXT=new Paragraph();
        COLLEGE_SCHOOL_FOR_COMPARISON_TEXT.setAlignment(Element.ALIGN_CENTER);
        
        
        String Score = "Abilities Score: -";
		
		String ObtainedInterestCode = "";
        
        
        
//        Paragraph PARAMETERS_SELECTED_TEXT=new Paragraph();
//        PARAMETERS_SELECTED_TEXT.setAlignment(Element.ALIGN_CENTER);
        
        
        
		Font OpensansRegularFont= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
		Font OpensansLightFont= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		Font ArialBlod_12= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"arial/arial-bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
		Font ArialBlod_12_Underline= FontFactory.getFont("D:/pdf report/arial/arial-bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED,Font.UNDERLINE);
		
		
		Font ArialBlod_10= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"arial/arial-bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        Font ArialRegular= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"arial/Arial-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		
        Font OpensansSemiboldFont= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Semibold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansSemiboldFont.setSize(7.5f);
		OpensansSemiboldFont.setColor(188,190,192);
		
		
		Font OpensansSemiboldFont1= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Semibold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansSemiboldFont1.setSize(7.5f);
		OpensansSemiboldFont1.setColor(147,149,152);
        
        
		Font OpensansSemiboldFont_16= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Semibold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansSemiboldFont_16.setSize(15.5f);
		OpensansSemiboldFont_16.setColor(102,45,145);
        
        
		
		Font OpensansSemiboldFont_12= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Semibold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansSemiboldFont_12.setSize(11.5f);
		OpensansSemiboldFont_12.setColor(102,45,145);
		
		
		
		
		
		Font OpensansSemiboldFont_10= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Semibold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansSemiboldFont_10.setSize(9.5f);
		OpensansSemiboldFont_10.setColor(99,100,102);
		
		
		
		Font OpensansLightFont_10= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansLightFont_10.setSize(9.5f);
		OpensansLightFont_10.setColor(99,100,102);
		
		
		Font OpensansSemiboldFont_24= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Semibold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansSemiboldFont_24.setSize(22.5f);
		OpensansSemiboldFont_24.setColor(102,45,145);
		
		
		Font OpensansSemiboldFont_10_white= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Semibold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansSemiboldFont_10_white.setSize(9.5f);
		OpensansSemiboldFont_10_white.setColor(255,255,255);
		
		
		
		Font OpensansLightFont_10_White= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansLightFont_10_White.setSize(9.5f);
		OpensansLightFont_10_White.setColor(16,37,63);
		
		Font OpensansSemiboldFont_14= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Semibold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansSemiboldFont_14.setSize(13.5f);
		OpensansSemiboldFont_14.setColor(102,45,145);
		
		
		Font OpensansLightFont_8= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansLightFont_8.setSize(7.5f);
		OpensansLightFont_8.setColor(255,255,255);
		
		
		Font OpensansboldFont_8= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansboldFont_8.setSize(7.5f);
		OpensansboldFont_8.setColor(255,255,255);
		
		
		Font OpensansRegulatdFont_10_Dark= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansRegulatdFont_10_Dark.setSize(10);
		OpensansRegulatdFont_10_Dark.setColor(99,100,102);
		
		
		Font OpensansLightFont_10_Dark= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansLightFont_10_Dark.setSize(9.5f);
		OpensansLightFont_10_Dark.setColor(99,100,102);
		
		
		
		Font OpensansBold_12_White= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansBold_12_White.setSize(12);
		OpensansBold_12_White.setColor(255,255,255);
		
		
		Font OpensansLightFont_10_White_1= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansLightFont_10_White_1.setSize(9.5f);
		OpensansLightFont_10_White_1.setColor(255,255,255);
		
		
		Font OpensansLightFont_10_blue= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/open-sans/OpenSans-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
		OpensansLightFont_10_blue.setSize(9.5f);
		OpensansLightFont_10_blue.setColor(102,45,145);
		
		
		
		
		
		java.awt.Font OpensansBoldFont_14=new java.awt.Font(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Bold.ttf",Font.BOLD , 14);
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		if (studentCGTResult != null) {
			if (studentCGTResult.getAppScore() != null && !studentCGTResult.getAppScore().isEmpty())
				Score = "Abilities Score: "+studentCGTResult.getAppScore();
			if (studentCGTResult.getPersonalityCode() != null)
				ObtainedInterestCode = studentCGTResult.getPersonalityCode();
			if (!studentCGTResult.getAppScore().equals(null) && !studentCGTResult.getAppScore().trim().equals("")) { 

				String[] Catagory = studentCGTResult.getAppScore().split(",");
				
				for (int k = 0; k < Catagory.length; k++) {
					String[] CatagoryScore = Catagory[k].split(":");

					if (CatagoryScore[1].trim().equals("L")) {
						dataSet.setValue(30, "Abilities Score", CatagoryScore[0].trim());
					} else if (CatagoryScore[1].trim().equals("M")) {
						dataSet.setValue(60, "Abilities Score", CatagoryScore[0].trim());
					} else if (CatagoryScore[1].trim().equals("H")) {
						dataSet.setValue(100, "Abilities Score", CatagoryScore[0].trim());
					}
				}
				
			}
			
			
			
			
			
		}
		
		
		Image LeftArrow=null;
		try {
			LeftArrow = Image.getInstance(properties.getProperty("com.edupath.report.template.DirectoryPath")+"leftarrow.png");
			LeftArrow.scaleAbsolute(15, 10);
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        
        ArialBlod_12.setSize(12);
        ArialBlod_12.setColor(255,255,255);
        
        ArialBlod_12_Underline.setSize(12);
        
        ArialBlod_12_Underline.setColor(255,255,255);
        
        ArialBlod_10.setSize(10.08f);
        ArialBlod_10.setColor(255,255,255);
        
        ArialRegular.setSize(10.08f);
		ArialRegular.setColor(255,255,255);
		
		List IndustriesExplored = new List(List.ORDERED);
		List OccupationsDiscovered_1 = new List(List.ORDERED);
		List OccupationsDiscovered_2 = new List(List.ORDERED);
		OccupationsDiscovered_2.setFirst(33);
		List CoursesExamined = new List(List.ORDERED);
		
		try {
			if (bean != null && bean.getIsStudent())
			{
				if (bean.getSection1() != null) {
					if (bean.getSection1().getName() != null)
					Name = bean.getSection1().getName();
					//start bharath on 20-05-2019
					int studentId = bean.getStudentId();
					studentdetailsDTO = new StudentInfoService().getStudentDetailsById(studentId);
					schoolDTO = new SchoolDAO().getSchoolById(studentdetailsDTO.getSchoolId());
					//end bharath on 20-05-2019
				}

				if (bean.getSection3OccNInd() != null) {
					if (bean.getSection3OccNInd().getOccupations() != null) {
						PrefaceParagraph = "\nThank you for successfully completing the Lodestar Career Guidance Program. The Lodestar Career Guidance Program has taken you through a unique career exploration process to help you make a career decision. During the course of this program, you explored a world of different industries and jobs, and learnt how your interest in an industry or occupation plays a role in the career decisions you make. You shortlisted";
						int i = 1;
						for (OccupationDTO occp : bean.getSection3OccNInd().getOccupations()) {

							if (i == 1) {
								PrefaceParagraph = PrefaceParagraph + " " + occp.getName();
								OCCUPATIONCHOICEMyPlane_DB=occp.getName();
								OccupationID_MYPLAN=occp.getId();
							} else if (i == 2) {
								PrefaceParagraph = PrefaceParagraph + " as your primary career choice and "
										+ occp.getName();
								OCCUPATIONCHOICEAlternatePlan_DB=occp.getName();
								OccupationID_ALTERNATEPLAN=occp.getId();
							}
							i++;
						}

						PrefaceParagraph = PrefaceParagraph
								+ " as your backup career choice. Through this program you were also able to arrive at the right education pathway and course selection linked to your primary and back up career choice.";
					}

					
					if(bean.getSection3OccNInd().getIndustries()!=null)
					{
						int i = 1;
						for (IndustryDTO industrie : bean.getSection3OccNInd().getIndustries()) {
							if (i == 1) {
								IndustrychoiceMyPlane_DB=industrie.getName();
							} else if(i==2)
							{
								IndustrychoiceAlternatePlan_DB=industrie.getName();
							}
							i++;
						}
					}
					
					
					
					
					
					
					
				}
				
				if(bean.getSection3Edupath()!=null)
				{
					int i=1;
					for (EduPathShortListDTO edupath:bean.getSection3Edupath())
					{
						if(i==1)
						{
							
							if(edupath.getStreamName()!=null)
							{
								MYPLAN_EDUPATH_streamName_DB= edupath.getStreamName();
								MyPlanBlockCount++;
							}
							if(edupath.getCombinationName()!=null)
							{
								MYPLAN_EDUPATH_combinationName_DB=edupath.getCombinationName();
								MyPlanBlockCount++;
							}
							if(edupath.getDegreeStream()!=null)
							{
								MYPLAN_EDUPATH_degreeStream_DB=edupath.getDegreeStream();
								
								if(edupath.getDegreeSubject()!=null)
								{
									MYPLAN_EDUPATH_degreeStream_DB=MYPLAN_EDUPATH_degreeStream_DB+" - "+ edupath.getDegreeSubject();
								}
								
								MyPlanBlockCount++;
							}
							if(edupath.getPgStream()!=null)
							{
								MYPLAN_EDUPATH_pgStream_DB=edupath.getPgStream();
								
								if(edupath.getPgSubject()!=null)
								{
									MYPLAN_EDUPATH_pgStream_DB=MYPLAN_EDUPATH_pgStream_DB+" - "+edupath.getPgSubject();
								}
								MyPlanBlockCount++;
							}
							if(edupath.getOccupationName() !=null)
							{
								MYPLAN_EDUPATH_occupationName_DB=edupath.getOccupationName();
								MyPlanBlockCount++;
							}
							if(edupath.getIndustryName()!=null)
							{
								MYPLAN_EDUPATH_industryName_DB=edupath.getIndustryName();
								MyPlanBlockCount++;
							}
						}
						else if(i==2)
						{
							
							if(edupath.getStreamName()!=null)
							{
								ALTERNATEPLAN_EDUPATH_streamName_DB= edupath.getStreamName();
								AlternatePlanBlockCount++;
							}
							if(edupath.getCombinationName()!=null)
							{
								ALTERNATEPLAN_EDUPATH_combinationName_DB=edupath.getCombinationName();
								AlternatePlanBlockCount++;
							}
							if(edupath.getDegreeStream()!=null)
							{
								ALTERNATEPLAN_EDUPATH_degreeStream_DB=edupath.getDegreeStream();
								
								if(edupath.getDegreeSubject()!=null)
								{
									ALTERNATEPLAN_EDUPATH_degreeStream_DB=ALTERNATEPLAN_EDUPATH_degreeStream_DB+" - "+ edupath.getDegreeSubject();
								}
								
								AlternatePlanBlockCount++;
							}
							if(edupath.getPgStream()!=null)
							{
								ALTERNATEPLAN_EDUPATH_pgStream_DB=edupath.getPgStream();
								
								if(edupath.getPgSubject()!=null)
								{
									ALTERNATEPLAN_EDUPATH_pgStream_DB=ALTERNATEPLAN_EDUPATH_pgStream_DB+" - "+edupath.getPgSubject();
								}
								AlternatePlanBlockCount++;
							}
							if(edupath.getOccupationName() !=null)
							{
								ALTERNATEPLAN_EDUPATH_occupationName_DB=edupath.getOccupationName();
								AlternatePlanBlockCount++;
							}
							if(edupath.getIndustryName()!=null)
							{
								ALTERNATEPLAN_EDUPATH_industryName_DB=edupath.getIndustryName();
								AlternatePlanBlockCount++;
							}
						}
						i++;
					}
					
					
				}
				
				
				if (bean.getSection2OccNInd() != null) {
					
					if(bean.getSection2OccNInd().getIndustries()!=null)
					{
						ArrayList<String> TempListOfIndustry = new ArrayList<String>();
						ArrayList<Integer> ListOfNoOfCharactor = new ArrayList<Integer>();
						ArrayList<String> FinalListOfIndustry = new ArrayList<String>(ListOfNoOfCharactor.size());
						
						
					for (IndustryDTO Industry : bean.getSection2OccNInd().getIndustries()) {
	
						
						ListOfNoOfCharactor.add(Industry.getName().length());
						TempListOfIndustry.add(Industry.getName());
						
						}
					Collections.sort(ListOfNoOfCharactor,Collections.reverseOrder());
					for(int k=0;k<ListOfNoOfCharactor.size();k++)
			         {
			        	 int count=ListOfNoOfCharactor.get(k);
			        	 for(int l=0;l<TempListOfIndustry.size();l++) 
			        	 {
			        	 
			        		 int count1=TempListOfIndustry.get(l).length();
			        		 if(count1==count)
			        		 {
			        			 FinalListOfIndustry.add(TempListOfIndustry.get(l));
			        			 TempListOfIndustry.remove(l);
			        			 break;
			        		 }
			        		 
			        	 }
			        	 
			        	 
			        	 
			         }
					for(int h=0;h<FinalListOfIndustry.size();h++)
			         {
						OpensansLightFont.setColor(99,100,102);
						OpensansLightFont.setSize(10);
						
						ListItem IndustriesExploredItem = new ListItem(FinalListOfIndustry.get(h),OpensansLightFont);
						IndustriesExploredItem.setLeading(16);
						IndustriesExplored.add(IndustriesExploredItem);
						
						
			         }
					

					
//						for (IndustryDTO Industry : bean.getSection2OccNInd().getIndustries()) {
//							
//							OpensansLightFont.setColor(99,100,102);
//							OpensansLightFont.setSize(10);
//							ListItem IndustriesExploredItem = new ListItem(Industry.getName(),OpensansLightFont);
//							IndustriesExploredItem.setLeading(16);
//							IndustriesExplored.add(IndustriesExploredItem);
//						}
						
						
						
						
						TotalIndustriesexplored=bean.getSection2OccNInd().getIndustries().size();
							
						}
						
						
						
						
					
					
					
					if(bean.getSection2OccNInd().getOccupations()!=null)
					{
						TotalOccupationsdiscovered=bean.getSection2OccNInd().getOccupations().size();
						int CountTotalOccupation=0;
						for (OccupationDTO occupation : bean.getSection2OccNInd().getOccupations()) {
							CountTotalOccupation++;
							if(CountTotalOccupation<33)
							{
								OpensansLightFont.setColor(99,100,102);
								OpensansLightFont.setSize(10);
								ListItem OccupationsDiscoveredItem = new ListItem(occupation.getName(),OpensansLightFont);
								OccupationsDiscoveredItem.setLeading(16);
								OccupationsDiscovered_1.add(OccupationsDiscoveredItem);
							}
							else
							{
								OpensansLightFont.setColor(99,100,102);
								OpensansLightFont.setSize(10);
								ListItem OccupationsDiscoveredItem = new ListItem(occupation.getName(),OpensansLightFont);
								OccupationsDiscoveredItem.setLeading(16);
								OccupationsDiscovered_2.add(OccupationsDiscoveredItem);
							}
						}
						
						
						
					}
					
					
					
					
				}

				if(bean.getSection2CourseRead()!=null)
				{
					TotalCoursesexamined=bean.getSection2CourseRead().size();
					
					for (SubjectDTO subject : bean.getSection2CourseRead()) {
						
						OpensansLightFont.setColor(99,100,102);
						OpensansLightFont.setSize(10);
						ListItem CoursesExaminedItem = new ListItem(subject.getName(),OpensansLightFont);
						CoursesExaminedItem.setLeading(16);
						CoursesExamined.add(CoursesExaminedItem);
					}
				}
				
				
				
				if(bean.getSection3StreamNElective()!=null && bean.getSection3StreamNElective().getCombinations()!=null)
				{
					SUBJECT_CHOICE_TEXT_DB=bean.getSection3StreamNElective().getStreamName();
					
					for(CombinationDTO comb: bean.getSection3StreamNElective().getCombinations())
					{
						if(SUBJECT_CHOICE_COMBINATION_TEXT_DB==null)
						{
							SUBJECT_CHOICE_COMBINATION_TEXT_DB=comb.getName();
						}
						else
						{
							SUBJECT_CHOICE_COMBINATION_TEXT_DB=SUBJECT_CHOICE_COMBINATION_TEXT_DB+", "+comb.getName();
						}
					}
					
					SUBJECT_CHOICE_FINAL_TEXT_DB=SUBJECT_CHOICE_TEXT_DB+" With "+SUBJECT_CHOICE_COMBINATION_TEXT_DB;
				}
				
				if(bean.getSection3Exams()!=null){
					
					
					
					pMyplan_TEXT.setFont(ArialRegular);
					pAlternateplan_TEXT.setFont(ArialRegular);
					float ALTERNATEper=0;
					float MyPlanper=0;
					for(EntranceExamsDTO exams:bean.getSection3Exams())
					{
						if(OccupationID_MYPLAN==exams.getOccupationId())
						{
							if(EXAMS_TO_PREPARE_MY_PLAN_DB==null)
							{
								EXAMS_TO_PREPARE_MY_PLAN_DB=exams.getExamName();
								
								pMyplan_TEXT.add(exams.getExamName());
								
								 float legstring=exams.getExamName().length();
								 MyPlanper=legstring*2.5f;
							}
							else
							{
								EXAMS_TO_PREPARE_MY_PLAN_DB=EXAMS_TO_PREPARE_MY_PLAN_DB+" | "+exams.getExamName();
								
								Paragraph pMyplan_heading11=new Paragraph();
								pMyplan_heading11.setLeading(1, 0.5f);
						         LineSeparator line3 = new LineSeparator();
						         line3.setLineColor(BaseColor.WHITE);
						         line3.setLineWidth(0.5f);
						         line3.setPercentage(MyPlanper);
						         
						         float legstring=exams.getExamName().length();
								 MyPlanper=legstring*2.5f;
						         
						         pMyplan_heading11.add(line3);
						         pMyplan_TEXT.add(pMyplan_heading11);
						         
						         pMyplan_TEXT.add(exams.getExamName());
								
							}
						}
						else if(OccupationID_ALTERNATEPLAN==exams.getOccupationId())
						{
							if(EXAMS_TO_PREPARE_ALTERNATE_PLAN_DB==null)
							{
								EXAMS_TO_PREPARE_ALTERNATE_PLAN_DB=exams.getExamName();
								
								pAlternateplan_TEXT.add(exams.getExamName());
								
								float legstring=exams.getExamName().length();
								ALTERNATEper=legstring*2.5f;
								
							}
							else
							{
								EXAMS_TO_PREPARE_ALTERNATE_PLAN_DB=EXAMS_TO_PREPARE_ALTERNATE_PLAN_DB+" | "+exams.getExamName();
								
								
								Paragraph pMyplan_heading11=new Paragraph();
								pMyplan_heading11.setLeading(1, 0.5f);
						         LineSeparator line3 = new LineSeparator();
						         line3.setLineColor(BaseColor.WHITE);
						         line3.setLineWidth(0.5f);
						         
						         line3.setPercentage(ALTERNATEper);
						        float legstring=exams.getExamName().length();
						          //System.out.println("======Exam name==============>"+exams.getExamName());
						        // System.out.println("====================>"+legstring);
						         
						         ALTERNATEper=legstring*2.5f;
						         
						         //line3.setPercentage(50);
						         
						         
						         pMyplan_heading11.add(line3);
						         pAlternateplan_TEXT.add(pMyplan_heading11);
						         pAlternateplan_TEXT.add(exams.getExamName());
								
							}
							
						}
						
						
					}
				}
				
				if(bean.getSection3Tutorials()!=null)
				{
					
					TUTORIAL_SELECTION_Myplan_TEXT.setFont(ArialRegular);

					float TUTORIALper=0;
					
					for(StudentTutorialCentreShortListDTO tuto:bean.getSection3Tutorials())
					{
						if(TUTORIAL_SELECTION_DB==null)
						{
							TUTORIAL_SELECTION_DB=tuto.getTutorialName() +" ("+tuto.getCityName()+" - "+tuto.getLocality()+")";
							
							
							TUTORIAL_SELECTION_Myplan_TEXT.add(tuto.getTutorialName() +" ("+tuto.getCityName()+" - "+tuto.getLocality()+")");
							
							float legstring=(tuto.getTutorialName() +" ("+tuto.getCityName()+" - "+tuto.getLocality()+")").length();
							TUTORIALper=legstring*1.15f;
							
						}
						else
						{
							TUTORIAL_SELECTION_DB=TUTORIAL_SELECTION_DB+", "+tuto.getTutorialName()+" ("+tuto.getCityName()+" - "+tuto.getLocality()+")";
							
							Paragraph pMyplan_heading11=new Paragraph();
							pMyplan_heading11.setLeading(1, 0.5f);
					         LineSeparator line3 = new LineSeparator();
					         line3.setLineColor(BaseColor.WHITE);
					         line3.setLineWidth(0.5f);
					         line3.setPercentage(TUTORIALper);
					         
					         
					        // System.out.println("======TUTORIAL name==============>"+tuto.getTutorialName() +" ("+tuto.getCityName()+" - "+tuto.getLocality()+")");
						    // System.out.println("====================>"+(tuto.getTutorialName() +" ("+tuto.getCityName()+" - "+tuto.getLocality()+")").length());
					         
					         float legstring=(tuto.getTutorialName() +" ("+tuto.getCityName()+" - "+tuto.getLocality()+")").length();
								TUTORIALper=legstring*1.15f;
					         
						//System.out.println("====================>"+TUTORIALper);
					         
					         pMyplan_heading11.add(line3);
					         TUTORIAL_SELECTION_Myplan_TEXT.add(pMyplan_heading11);
							
							TUTORIAL_SELECTION_Myplan_TEXT.add(tuto.getTutorialName() +" ("+tuto.getCityName()+" - "+tuto.getLocality()+")");
							
						}
					}
				}
				
				
				
				if(bean.getSelectedParam()!=null)
				{
						StudentCollegeParametersDTO tempparm=bean.getSelectedParam();
							
//						PARAMETERS_SELECTED_TEXT.setFont(ArialRegular);
						for(CollegeParametersDTO param:tempparm.getCollegeParams())
						{
							if(PARAMETERS_SELECTED_DB==null)
							{
								PARAMETERS_SELECTED_DB=param.getParameter();
//								PARAMETERS_SELECTED_TEXT.add(param.getParameter());
							}
							else
							{
								PARAMETERS_SELECTED_DB=PARAMETERS_SELECTED_DB+" | "+param.getParameter();
								
								
//								Paragraph pMyplan_heading11=new Paragraph();
//								pMyplan_heading11.setLeading(1, 0.5f);
//						         LineSeparator line3 = new LineSeparator();
//						         line3.setLineColor(BaseColor.WHITE);
//						         line3.setLineWidth(0.5f);
//						         line3.setPercentage(80);
//						         
//						         pMyplan_heading11.add(line3);
//						         PARAMETERS_SELECTED_TEXT.add(pMyplan_heading11);
//								
//						         PARAMETERS_SELECTED_TEXT.add(param.getParameter());
								
								
								
								
							}
					
							
						}
						
	
				}
					
				
				if(bean.getSection3Colleges()!=null)
				{
					COLLEGE_SCHOOL_FOR_COMPARISON_TEXT.setFont(ArialRegular);
					
					float COLLEGEper=0;
					
					for(StudentCollegeShortListDTO col:bean.getSection3Colleges())
					{
						if(COLLEGE_SCHOOL_FOR_COMPARISON_DB==null)
						{
							COLLEGE_SCHOOL_FOR_COMPARISON_DB=col.getCollegeName();
							COLLEGE_SCHOOL_FOR_COMPARISON_TEXT.add(col.getCollegeName());
							 float legstring=col.getCollegeName().length();
					         COLLEGEper=legstring*1.15f;
						}
						else
						{
							COLLEGE_SCHOOL_FOR_COMPARISON_DB=COLLEGE_SCHOOL_FOR_COMPARISON_DB+" | "+col.getCollegeName();
							
							
							Paragraph pMyplan_heading11=new Paragraph();
							pMyplan_heading11.setLeading(1, 0.5f);
					         LineSeparator line3 = new LineSeparator();
					         line3.setLineColor(BaseColor.WHITE);
					         line3.setLineWidth(0.5f);
					         line3.setPercentage(COLLEGEper);
					         
					         float legstring=col.getCollegeName().length();
					         COLLEGEper=legstring*1.15f;
					         
					         
					         
					         
					         
					         pMyplan_heading11.add(line3);
					         COLLEGE_SCHOOL_FOR_COMPARISON_TEXT.add(pMyplan_heading11);
							
					         COLLEGE_SCHOOL_FOR_COMPARISON_TEXT.add(col.getCollegeName());
							
						}
					}
				}
					

			}
			
//			if(PARAMETERS_SELECTED_DB==null)
//			{
//				PARAMETERS_SELECTED_DB="--";
//			}

			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport);
			Rectangle pagesize = pdfReader.getPageSize(1);
			Document document = new Document(pagesize, 0, 0, 0, 0);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			
			Rectangle rectangle = new Rectangle(30, 30, 550, 800);
			writer.setBoxSize("rectangle", rectangle);
		    HeaderAndFooterPdfPageEventHelper headerAndFooter =  new HeaderAndFooterPdfPageEventHelper();
		    writer.setPageEvent(headerAndFooter);
			
			
			
			document.open();
			PdfContentByte cb = writer.getDirectContentUnder();
			
			boolean checksuplimentaryadded=false;
			boolean checksallcommentadded=false;
			
			if((bean!=null && bean.getSupplementaryinformation()!=null && bean.getSupplementaryinformation().trim()!="") || (bean!=null && bean.getComments().trim()!=null && bean.getComments().trim()!="" && bean.getComments().trim().length()>500))
			{
				checksuplimentaryadded=true;
			}
			
			if((bean!=null && bean.getComments()!=null && bean.getComments().trim()!="" && bean.getComments().trim().length()<=500) || (bean!=null && bean.getChallenges()!=null && bean.getChallenges().trim()!="") || (bean!=null && bean.getApproachtopathway1()!=null && bean.getApproachtopathway1().trim()!="") || (bean!=null && bean.getApproachtopathway2()!=null && bean.getApproachtopathway2().trim()!="") || (bean!=null && bean.getFinalnotes()!=null && bean.getFinalnotes().trim()!=""))
			{
				checksallcommentadded=true;
			}
			
			
			
			
			for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
				PageNo=i;
				PdfImportedPage page=null;
				
				if(i==12)
				{
					if(checksallcommentadded)
					{
						page = writer.getImportedPage(pdfReader, i);
					}
				}
				else if(i==17)
				{
					if(checksuplimentaryadded)
					{
					 page = writer.getImportedPage(pdfReader, i);
					}
				}
				else
				{
				
					 page = writer.getImportedPage(pdfReader, i);
				}
				
				if(i==14 || i==15 || i==16)
				{
						document.setMargins(0,0,0,90F);
						
				}
				
				if(i==12)
				{
					if(checksallcommentadded)
					{
						document.newPage();
						cb.addTemplate(page, 0, 0);
					}
					
				}
				else if(i==17)
				{
					if(checksuplimentaryadded)
					{
						document.newPage();
						cb.addTemplate(page, 0, 0);
					}
				}
				else
				{
					document.newPage();
					cb.addTemplate(page, 0, 0);
				}
				
				if (i == 1) {
					OpensansRegularFont.setColor(35,31,32);
					OpensansRegularFont.setSize(14);
					Paragraph Pname = new Paragraph(Name,OpensansRegularFont);
					Pname.setIndentationLeft(130);
					Pname.setIndentationRight(40);
					Pname.setLeading(5, 17.9f);
					document.add(Pname);
					//School_logo   
					//start bharath on 21-05-2019
					if(schoolDTO.getDisplaylogo()==1 && schoolDTO.getLogopath() != null && !schoolDTO.getLogopath().equals(""))
					{
						String path = ApplicationProperties.getInstance().getProperty("com.edupath.school.logo.destination")+schoolDTO.getLogopath();
							Image School_logo = Image.getInstance(path);
							School_logo.setAbsolutePosition(35, 750); 
							document.add(School_logo); 
					}
					//end bharath on 21-05-2019
					
				}
				if (i == 4) {
					OpensansLightFont.setColor(99,100,102);
					OpensansLightFont.setSize(10);
					Paragraph pPrefaceName = new Paragraph(Name + ",", OpensansLightFont);

					pPrefaceName.setAlignment(Element.ALIGN_JUSTIFIED);
					pPrefaceName.setIndentationLeft(64);
					pPrefaceName.setIndentationRight(10);
					pPrefaceName.setLeading(5, 11);

					document.add(pPrefaceName);

					Paragraph pPrefacePargraph = new Paragraph(PrefaceParagraph, OpensansLightFont);

					pPrefacePargraph.setAlignment(Element.ALIGN_JUSTIFIED);
					pPrefacePargraph.setIndentationLeft(64);
					pPrefacePargraph.setIndentationRight(60);
					pPrefacePargraph.setLeading(5, 0.9f);

					document.add(pPrefacePargraph);

				}
				
				if(i==6)
				{
					//ASSESSMENT REPORT
					
					
					
			    	
			    	
			    	 Paragraph AbilitiesScoreParagraph=new Paragraph(Score,OpensansRegulatdFont_10_Dark);
			    	 AbilitiesScoreParagraph.setAlignment(Element.ALIGN_CENTER);
			    	 
			    	 
			    	 Paragraph ObtainedInterestCodeeParagraph=new Paragraph(ObtainedInterestCode,OpensansRegulatdFont_10_Dark);
			    	 ObtainedInterestCodeeParagraph.setAlignment(Element.ALIGN_CENTER);
			    	 
			    	 
			    	 
			    	 
			    	 ColumnText columnTable = new ColumnText(writer.getDirectContent());
			         columnTable.setSimpleColumn(55, 36, 540, 662);
			         columnTable.addElement(AbilitiesScoreParagraph);
			         columnTable.go();
			         
			         JFreeChart chart = null;
			     	 
			    	chart = ChartFactory.createBarChart("* Scores are indicative", "", "", dataSet,
			    			PlotOrientation.VERTICAL, false, true, false);
			    	chart.setBackgroundPaint(Color.white);
			    	chart.setBorderVisible(false);
			    	 
			    	
			    	 TextTitle my_Chart_title=new TextTitle("*Scores are indicative");
			    	 my_Chart_title.setFont(OpensansBoldFont_14);
			    	 Color color1 = new Color(99,100,102);
			    	 my_Chart_title.setPaint(color1);
			    	 chart.setTitle(my_Chart_title);
			    	
			    	
			    	CategoryPlot cplot = (CategoryPlot)chart.getPlot();
			    	
			    	cplot.getRangeAxis().setUpperBound(100);
			    	BarRenderer renderer = (BarRenderer) cplot.getRenderer();

			    	cplot.setBackgroundPaint(Color.white);
			    	
			    	// set the color (r,g,b) or (r,g,b,a)
			    	Color color = new Color(1,124,116);
			    	renderer.setSeriesPaint(0, color);
			    	
			  
			    	
			    	
			    	cplot.setOutlineStroke(null);
			    	cplot.setRangeGridlinesVisible(false);
			    	
			    	//chart.getCategoryPlot().getRangeAxis().setUpperBound(100);
			    	
			    	if (chart != null) {
						PdfTemplate pie = cb.createTemplate(525, 196);
						Graphics2D g2d1 = new PdfGraphics2D(pie, 525, 196);
						Rectangle2D r2d1 = new Rectangle2D.Double(0, 0, 525, 196);
						chart.draw(g2d1, r2d1);
						g2d1.dispose();

						Image img = Image.getInstance(pie);

						img.setAbsolutePosition(35f, 435f);
						document.add(img);
					}
			    	
			    	
			    	 columnTable = new ColumnText(writer.getDirectContent());
			         columnTable.setSimpleColumn(55, 36, 860, 308);
			         columnTable.addElement(ObtainedInterestCodeeParagraph);
			         columnTable.go();
			         
			         int RECOMMEDED_OCCUPATIONS_Count=occupIndusMap.size();
			         
			           PdfPTable RECOMMEDED_OCCUPATIONS_Table;
			           
			           
			           if(RECOMMEDED_OCCUPATIONS_Count>6)
			           {
			        	   RECOMMEDED_OCCUPATIONS_Table= new PdfPTable(3);
			           }
			           else if(RECOMMEDED_OCCUPATIONS_Count>3)
			           {
			        	   RECOMMEDED_OCCUPATIONS_Table= new PdfPTable(2);
			           }
			           else
			           {
			        	   RECOMMEDED_OCCUPATIONS_Table= new PdfPTable(1);
			           }
			           
			          
			           RECOMMEDED_OCCUPATIONS_Table.setHorizontalAlignment(Element.ALIGN_CENTER);
			           RECOMMEDED_OCCUPATIONS_Table.setWidthPercentage(100);
			           RECOMMEDED_OCCUPATIONS_Table.setSplitRows(false);
			    	  
			         
			           
			           
			           
			    	   PdfPCell RECOMMEDED_OCCUPATIONS_cell_1 = new PdfPCell();
			    	   RECOMMEDED_OCCUPATIONS_cell_1.setBorder(Rectangle.NO_BORDER);
				    	
				        
				         
			    	   RECOMMEDED_OCCUPATIONS_cell_1.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	   RECOMMEDED_OCCUPATIONS_cell_1.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	   RECOMMEDED_OCCUPATIONS_cell_1.setPaddingTop(0);
			    	   RECOMMEDED_OCCUPATIONS_cell_1.setPaddingBottom(0);//35.8f
			    	   RECOMMEDED_OCCUPATIONS_cell_1.setPaddingLeft(0);
			    	   RECOMMEDED_OCCUPATIONS_cell_1.setPaddingRight(8);
			         
			    	   PdfPCell RECOMMEDED_OCCUPATIONS_cell_2 = new PdfPCell();
			    	   RECOMMEDED_OCCUPATIONS_cell_2.setBorder(Rectangle.NO_BORDER);
				    	
				        
				         
			    	   RECOMMEDED_OCCUPATIONS_cell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	   RECOMMEDED_OCCUPATIONS_cell_2.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	   RECOMMEDED_OCCUPATIONS_cell_2.setPaddingTop(0);
			    	   RECOMMEDED_OCCUPATIONS_cell_2.setPaddingBottom(0);//35.8f
			    	   RECOMMEDED_OCCUPATIONS_cell_2.setPaddingLeft(0);
			    	   RECOMMEDED_OCCUPATIONS_cell_2.setPaddingRight(8);
			    	   
			    	   PdfPCell RECOMMEDED_OCCUPATIONS_cell_3 = new PdfPCell();
			    	   RECOMMEDED_OCCUPATIONS_cell_3.setBorder(Rectangle.NO_BORDER);
				    	
				        
				         
			    	   RECOMMEDED_OCCUPATIONS_cell_3.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	   RECOMMEDED_OCCUPATIONS_cell_3.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	   RECOMMEDED_OCCUPATIONS_cell_3.setPaddingTop(0);
			    	   RECOMMEDED_OCCUPATIONS_cell_3.setPaddingBottom(0);//35.8f
			    	   RECOMMEDED_OCCUPATIONS_cell_3.setPaddingLeft(0);
			    	   RECOMMEDED_OCCUPATIONS_cell_3.setPaddingRight(8);
			    	   
			    	   
			    	   PdfPTable RECOMMEDED_OCCUPATIONS_Table_1=new PdfPTable(1);
			    	   RECOMMEDED_OCCUPATIONS_Table_1.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	   RECOMMEDED_OCCUPATIONS_Table_1.setWidthPercentage(100);
			    	   RECOMMEDED_OCCUPATIONS_Table_1.setSplitRows(false);
			    	   
			    	   PdfPTable RECOMMEDED_OCCUPATIONS_Table_2=new PdfPTable(1);
			    	   RECOMMEDED_OCCUPATIONS_Table_2.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	   RECOMMEDED_OCCUPATIONS_Table_2.setWidthPercentage(100);
			    	   RECOMMEDED_OCCUPATIONS_Table_2.setSplitRows(false);
			    	   
			    	   PdfPTable RECOMMEDED_OCCUPATIONS_Table_3=new PdfPTable(1);
			    	   RECOMMEDED_OCCUPATIONS_Table_3.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	   RECOMMEDED_OCCUPATIONS_Table_3.setWidthPercentage(100);
			    	   RECOMMEDED_OCCUPATIONS_Table_3.setSplitRows(false);
			    	   
			    	   int j=1;
			    	   for (Map.Entry<String, String> occup : occupIndusMap.entrySet())
			    	   //for(int j=1;j<=RECOMMEDED_OCCUPATIONS_Count;j++)
			    	   {
			    		   
			    		   Paragraph Occupation = new  Paragraph(j+". "+occup.getKey(),OpensansLightFont_10_Dark);
				    	   Occupation.setAlignment(Element.ALIGN_LEFT);
				    	   Occupation.setLeading(1,0.9f);
			    		   
			    		   if(RECOMMEDED_OCCUPATIONS_Count<=3)
			    		   {
			    			   
			    			  
			    			   
					    	    PdfPCell RECOMMEDED_OCCUPATIONS_cell = new PdfPCell();
						    	RECOMMEDED_OCCUPATIONS_cell.addElement(Occupation);
						    	RECOMMEDED_OCCUPATIONS_cell.setBorder(Rectangle.NO_BORDER);
						    	
						        
						         
						    	RECOMMEDED_OCCUPATIONS_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						    	RECOMMEDED_OCCUPATIONS_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
						    	RECOMMEDED_OCCUPATIONS_cell.setPaddingTop(0);
						    	RECOMMEDED_OCCUPATIONS_cell.setPaddingBottom(7);//35.8f
						    	RECOMMEDED_OCCUPATIONS_cell.setPaddingLeft(0);
						    	RECOMMEDED_OCCUPATIONS_cell.setPaddingRight(0);
						    	RECOMMEDED_OCCUPATIONS_Table_1.addCell(RECOMMEDED_OCCUPATIONS_cell);
					    	   
					    	   
			    			   
			    		   }
			    		   else if(RECOMMEDED_OCCUPATIONS_Count>3 && RECOMMEDED_OCCUPATIONS_Count<=6)
			    		   {
			    			   
			    			   
			    			  
					    	   if(j<=3)
					    	   {
					    		   
					    		    PdfPCell RECOMMEDED_OCCUPATIONS_cell = new PdfPCell();
							    	RECOMMEDED_OCCUPATIONS_cell.addElement(Occupation);
							    	RECOMMEDED_OCCUPATIONS_cell.setBorder(Rectangle.NO_BORDER);
							    	
							        
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							    	RECOMMEDED_OCCUPATIONS_cell.setVerticalAlignment(Element.ALIGN_LEFT);
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingTop(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingBottom(7);//35.8f
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingLeft(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingRight(0);
							    	RECOMMEDED_OCCUPATIONS_Table_1.addCell(RECOMMEDED_OCCUPATIONS_cell);
					    		   
					    		   
					    	   }
					    	   else
					    	   {
					    		   
					    		   
					    		    PdfPCell RECOMMEDED_OCCUPATIONS_cell = new PdfPCell();
							    	RECOMMEDED_OCCUPATIONS_cell.addElement(Occupation);
							    	RECOMMEDED_OCCUPATIONS_cell.setBorder(Rectangle.NO_BORDER);
							    	
							        
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							    	RECOMMEDED_OCCUPATIONS_cell.setVerticalAlignment(Element.ALIGN_LEFT);
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingTop(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingBottom(7);//35.8f
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingLeft(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingRight(0);
							    	RECOMMEDED_OCCUPATIONS_Table_2.addCell(RECOMMEDED_OCCUPATIONS_cell);
					    		   
					    		   
					    		   
					    		   
					    	   }
					    	   
					    	   
					    	   
					    	   
					    	   
					    	   
			    			   
			    			   
			    		   }
			    		   else
			    		   {
			    			  
			    			    
					    	   if(j<=3)
					    	   {
					    		   
					    		    PdfPCell RECOMMEDED_OCCUPATIONS_cell = new PdfPCell();
							    	RECOMMEDED_OCCUPATIONS_cell.addElement(Occupation);
							    	RECOMMEDED_OCCUPATIONS_cell.setBorder(Rectangle.NO_BORDER);
							    	
							        
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							    	RECOMMEDED_OCCUPATIONS_cell.setVerticalAlignment(Element.ALIGN_LEFT);
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingTop(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingBottom(7);//35.8f
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingLeft(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingRight(0);
							    	RECOMMEDED_OCCUPATIONS_Table_1.addCell(RECOMMEDED_OCCUPATIONS_cell);
					    		   
					    		   
					    	   }
					    	   else if(j>3 && j<=6)
					    	   {
					    		   
					    		   
					    		    PdfPCell RECOMMEDED_OCCUPATIONS_cell = new PdfPCell();
							    	RECOMMEDED_OCCUPATIONS_cell.addElement(Occupation);
							    	RECOMMEDED_OCCUPATIONS_cell.setBorder(Rectangle.NO_BORDER);
							    	
							        
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							    	RECOMMEDED_OCCUPATIONS_cell.setVerticalAlignment(Element.ALIGN_LEFT);
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingTop(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingBottom(7);//35.8f
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingLeft(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingRight(0);
							    	RECOMMEDED_OCCUPATIONS_Table_2.addCell(RECOMMEDED_OCCUPATIONS_cell);
					    		   
					    		   
					    		   
					    		   
					    	   }
					    	   else
					    	   {
					    		    PdfPCell RECOMMEDED_OCCUPATIONS_cell = new PdfPCell();
							    	RECOMMEDED_OCCUPATIONS_cell.addElement(Occupation);
							    	RECOMMEDED_OCCUPATIONS_cell.setBorder(Rectangle.NO_BORDER);
							    	
							        
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							    	RECOMMEDED_OCCUPATIONS_cell.setVerticalAlignment(Element.ALIGN_LEFT);
							         
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingTop(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingBottom(7);//35.8f
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingLeft(0);
							    	RECOMMEDED_OCCUPATIONS_cell.setPaddingRight(0);
							    	RECOMMEDED_OCCUPATIONS_Table_3.addCell(RECOMMEDED_OCCUPATIONS_cell);
					    	   }
			    		   }
			    		   
			    		   
			    		   j++;
			    		   
			    		   
			    	   }
			    	   
			    	   
			    	   
			    	   if(RECOMMEDED_OCCUPATIONS_Count>6)
			    	   {
			    		   
			    		   RECOMMEDED_OCCUPATIONS_cell_1.addElement(RECOMMEDED_OCCUPATIONS_Table_1);
			    		   RECOMMEDED_OCCUPATIONS_cell_2.addElement(RECOMMEDED_OCCUPATIONS_Table_2);
			    		   RECOMMEDED_OCCUPATIONS_cell_3.addElement(RECOMMEDED_OCCUPATIONS_Table_3);
			    		   
			    		   RECOMMEDED_OCCUPATIONS_Table.addCell(RECOMMEDED_OCCUPATIONS_cell_1);
			    		   RECOMMEDED_OCCUPATIONS_Table.addCell(RECOMMEDED_OCCUPATIONS_cell_2);
			    		   RECOMMEDED_OCCUPATIONS_Table.addCell(RECOMMEDED_OCCUPATIONS_cell_3);
			    		   
			    		   
			    		   
			    	   }
			    	   else if(RECOMMEDED_OCCUPATIONS_Count>3)
			    	   {
			    		   
			    		   RECOMMEDED_OCCUPATIONS_cell_1.addElement(RECOMMEDED_OCCUPATIONS_Table_1);
			    		   RECOMMEDED_OCCUPATIONS_cell_2.addElement(RECOMMEDED_OCCUPATIONS_Table_2);
			    		   
			    		   
			    		   RECOMMEDED_OCCUPATIONS_Table.addCell(RECOMMEDED_OCCUPATIONS_cell_1);
			    		   RECOMMEDED_OCCUPATIONS_Table.addCell(RECOMMEDED_OCCUPATIONS_cell_2);
			    		   
			    	   }
			    	   else
			    	   {
			    		   RECOMMEDED_OCCUPATIONS_cell_1.addElement(RECOMMEDED_OCCUPATIONS_Table_1);
			    		   RECOMMEDED_OCCUPATIONS_Table.addCell(RECOMMEDED_OCCUPATIONS_cell_1);
			    	   }
			    	   
			    	  
			    	   
			    	  
			    	   
			    	
			    	   
			    	   
			    	   
			    	  
			    	   
			    	   
//				    	 columnTable = new ColumnText(writer.getDirectContent());
//				         columnTable.setSimpleColumn(540, 36,65, 160);
//				         columnTable.addElement(RECOMMEDED_OCCUPATIONS_Table);
//				         columnTable.go();
			         
			         
					
					
					
				}
				
				
				
				
				
				
				
				
				
				
				if (i == 7) {
					
					
					OpensansRegularFont.setColor(99,100,102);
					OpensansRegularFont.setSize(10);
					
					
					
					Chunk TextTotalIndustriesexplored = new Chunk(Integer.toString(TotalIndustriesexplored),OpensansRegularFont);
					Chunk TextTotalOccupationsdiscovered = new Chunk(Integer.toString(TotalOccupationsdiscovered),OpensansRegularFont);
					Chunk TextTotalCoursesexamined = new Chunk(Integer.toString(TotalCoursesexamined),OpensansRegularFont);
					
					ColumnText columnTotalIndustriesexplored = new ColumnText(writer.getDirectContent()); 
					columnTotalIndustriesexplored.setSimpleColumn(230, 36, 559, 726);
			   	
				   	columnTotalIndustriesexplored.addText(TextTotalIndustriesexplored);
				   	columnTotalIndustriesexplored.go();
				   	 
				   	 ColumnText columnTotalOccupationsdiscovered = new ColumnText(writer.getDirectContent()); 
				   	columnTotalOccupationsdiscovered.setSimpleColumn(230, 36, 559, 704);
				   	 
				   	columnTotalOccupationsdiscovered.addText(TextTotalOccupationsdiscovered);
				   	columnTotalOccupationsdiscovered.go();
				   	 
				   	 ColumnText columnTotalCoursesexamined = new ColumnText(writer.getDirectContent()); 
				   	columnTotalCoursesexamined.setSimpleColumn(230, 36, 559, 681);
				   	
				   	columnTotalCoursesexamined.addText(TextTotalCoursesexamined);
				   	columnTotalCoursesexamined.go();
					
					
					
					
					ColumnText columnIndustriesExplored = new ColumnText(writer.getDirectContent());
					columnIndustriesExplored.setSimpleColumn(62, 36, 559, 597);
					columnIndustriesExplored.addElement(IndustriesExplored);
					columnIndustriesExplored.go();
				}
				if(i==8)
				{
					Image CoursesExaminedimage = Image.getInstance(properties.getProperty("com.edupath.report.template.DirectoryPath")+"CoursesExamined.png");
					CoursesExaminedimage.scaleToFit(490, 30);
					ColumnText columnOccupationsDiscovered_left = new ColumnText(writer.getDirectContent());
					columnOccupationsDiscovered_left.setSimpleColumn(62, 36, 310, 708);
					columnOccupationsDiscovered_left.addElement(OccupationsDiscovered_1);
					columnOccupationsDiscovered_left.go();
					
					if(OccupationsDiscovered_2.size()>0)
					{
						ColumnText columnOccupationsDiscovered_right = new ColumnText(writer.getDirectContent()); 
						columnOccupationsDiscovered_right.setSimpleColumn(320, 36, 559, 735.5f);
						
						columnOccupationsDiscovered_right.addElement(OccupationsDiscovered_2);
						columnOccupationsDiscovered_right.go();
						
						Chunk AddingSpaace = new Chunk("\n",OpensansRegularFont);
						columnOccupationsDiscovered_right.addElement(AddingSpaace);
						columnOccupationsDiscovered_right.go();
						
						columnOccupationsDiscovered_right.addElement(CoursesExaminedimage);
						columnOccupationsDiscovered_right.go();
						
						columnOccupationsDiscovered_right.addElement(CoursesExamined);
						columnOccupationsDiscovered_right.go();
						
					}
					else
					{
						
						 
				        ColumnText columnCoursesExaminedImage = new ColumnText(writer.getDirectContent()); 
				        columnCoursesExaminedImage.setSimpleColumn(318, 36, 559, 735.5f);
						
				        columnCoursesExaminedImage.addElement(CoursesExaminedimage);
				        columnCoursesExaminedImage.go();
						
						ColumnText columnOccupationsDiscovered_right = new ColumnText(writer.getDirectContent()); 
						columnOccupationsDiscovered_right.setSimpleColumn(320, 36, 559, 705);
						
						columnOccupationsDiscovered_right.addElement(CoursesExamined);
						columnOccupationsDiscovered_right.go();
					}
					
				}
				if(i==10)
				{
					
					 
					
					 //----------------------START INDUSTRY CHOICE-------------------------
					 
					 PdfPTable INDUSTRYCHOICETable = new PdfPTable(2);
					 INDUSTRYCHOICETable.setSpacingAfter(4);
					 INDUSTRYCHOICETable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					 INDUSTRYCHOICETable.setWidthPercentage(96);
					 PdfPCell INDUSTRYCHOICETableCell;
					
					 
					
					
					
					
					     //----------------------START INDUSTRY CHOICE  HEADING-------------------------
					 
					 Chunk INDUSTRYCHOICE = new Chunk("INDUSTRY CHOICE",ArialBlod_12);
					 INDUSTRYCHOICETableCell = new PdfPCell(new Phrase(INDUSTRYCHOICE));
					 INDUSTRYCHOICETableCell.setBorderColorBottom(BaseColor.WHITE);
					 INDUSTRYCHOICETableCell.setBorderWidthBottom(2);
					 INDUSTRYCHOICETableCell.setBorder(Rectangle.BOTTOM);
					 INDUSTRYCHOICETableCell.setColspan(2);
			         BaseColor CellBackgroundColorINDUSTRYCHOICE = new BaseColor(241,90,34);
			         INDUSTRYCHOICETableCell.setBackgroundColor(CellBackgroundColorINDUSTRYCHOICE);
			         
			         INDUSTRYCHOICETableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         INDUSTRYCHOICETableCell.setVerticalAlignment(Element.ALIGN_CENTER);
			         
			         INDUSTRYCHOICETableCell.setPaddingTop(5);
			         INDUSTRYCHOICETableCell.setPaddingBottom(12);
			         INDUSTRYCHOICETableCell.setPaddingLeft(5);
			         INDUSTRYCHOICETableCell.setPaddingRight(5);
			         
			         INDUSTRYCHOICETable.addCell(INDUSTRYCHOICETableCell);
			         
			         
			         //----------------------END INDUSTRY CHOICE  HEADING-------------------------
			         
			         
			         //----------------------START INDUSTRY CHOICE  MY PLAN-------------------------
			           
			       
			         
			         Paragraph INDUSTRYCHOICEMYPLAN=new Paragraph("My plan",ArialBlod_10);
			         Paragraph INDUSTRYCHOICEMYPLAN_TEXT=new Paragraph(IndustrychoiceMyPlane_DB,ArialRegular);
			         
			        
			         
			         
			         INDUSTRYCHOICEMYPLAN.setAlignment(Element.ALIGN_CENTER);
			         INDUSTRYCHOICEMYPLAN_TEXT.setAlignment(Element.ALIGN_CENTER);
			         INDUSTRYCHOICETableCell = new PdfPCell();
			         INDUSTRYCHOICETableCell.setBorder(Rectangle.NO_BORDER);
			         INDUSTRYCHOICETableCell.addElement(INDUSTRYCHOICEMYPLAN);
			         INDUSTRYCHOICETableCell.addElement(INDUSTRYCHOICEMYPLAN_TEXT);
			         BaseColor CellBackgroundColorINDUSTRYCHOICEMYPLAN = new BaseColor(0,113,181);
			         INDUSTRYCHOICETableCell.setBackgroundColor(CellBackgroundColorINDUSTRYCHOICEMYPLAN);
			         
			         INDUSTRYCHOICETableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         INDUSTRYCHOICETableCell.setVerticalAlignment(Element.ALIGN_CENTER);
			        
			         INDUSTRYCHOICETableCell.setPaddingTop(5);
			         INDUSTRYCHOICETableCell.setPaddingBottom(12);
			         INDUSTRYCHOICETableCell.setPaddingLeft(2);
			         INDUSTRYCHOICETableCell.setPaddingRight(2);
			         
			         
			         
			         INDUSTRYCHOICETable.addCell(INDUSTRYCHOICETableCell);
			         
			         
			       
			         //----------------------END INDUSTRY CHOICE  MY PLAN-------------------------
			         
			       //----------------------START INDUSTRY CHOICE  Alternate plan-------------------------
			         
			         Paragraph INDUSTRYCHOICEAlternateplan=new Paragraph("Alternate plan",ArialBlod_10);
			         Paragraph INDUSTRYCHOICEAlternateplan_TEXT=new Paragraph(IndustrychoiceAlternatePlan_DB,ArialRegular);
			         INDUSTRYCHOICEAlternateplan.setAlignment(Element.ALIGN_CENTER);
			         INDUSTRYCHOICEAlternateplan_TEXT.setAlignment(Element.ALIGN_CENTER);
			        
			         INDUSTRYCHOICETableCell = new PdfPCell();
			         INDUSTRYCHOICETableCell.setBorder(Rectangle.NO_BORDER);
			         INDUSTRYCHOICETableCell.addElement(INDUSTRYCHOICEAlternateplan);
			         INDUSTRYCHOICETableCell.addElement(INDUSTRYCHOICEAlternateplan_TEXT);
			         BaseColor CellBackgroundColorINDUSTRYCHOICEAlternateplan = new BaseColor(0,166,81);
			         INDUSTRYCHOICETableCell.setBackgroundColor(CellBackgroundColorINDUSTRYCHOICEAlternateplan);
			         
			         INDUSTRYCHOICETableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         INDUSTRYCHOICETableCell.setVerticalAlignment(Element.ALIGN_CENTER);
			         
			         INDUSTRYCHOICETableCell.setPaddingTop(5);
			         INDUSTRYCHOICETableCell.setPaddingBottom(12);
			         INDUSTRYCHOICETableCell.setPaddingLeft(2);
			         INDUSTRYCHOICETableCell.setPaddingRight(2);
			         
			         
			         
			         INDUSTRYCHOICETable.addCell(INDUSTRYCHOICETableCell);
			         
			         //----------------------END INDUSTRY CHOICE  Alternate plan-------------------------
			         
			         ColumnText columnTable = new ColumnText(writer.getDirectContent());
			         columnTable.setSimpleColumn(55, 36, 559, 730);
			         columnTable.addElement(INDUSTRYCHOICETable);
			         columnTable.go();
			         
			        
			       //----------------------END INDUSTRY CHOICE-------------------------
			      
			         
			       //----------------------START OCCUPATION CHOICE-------------------------  
			         
			        
			         
			         PdfPTable OCCUPATIONCHOICETable = new PdfPTable(2);
			         OCCUPATIONCHOICETable.setSpacingAfter(4);
			         OCCUPATIONCHOICETable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			         OCCUPATIONCHOICETable.setWidthPercentage(96);
			    	 PdfPCell OCCUPATIONCHOICETablecell;
			         
			         
			    	 //----------------------START OCCUPATION CHOICE HEADING-------------------------  
			    	 
			    	 
			    	 Chunk OCCUPATIONCHOICEHEADING = new Chunk("OCCUPATION CHOICE",ArialBlod_12);
			    	 OCCUPATIONCHOICETablecell = new PdfPCell(new Phrase(OCCUPATIONCHOICEHEADING));
			    	 OCCUPATIONCHOICETablecell.setBorderColorBottom(BaseColor.WHITE);
			    	 OCCUPATIONCHOICETablecell.setBorderWidthBottom(3);
			    	 OCCUPATIONCHOICETablecell.setBorder(Rectangle.BOTTOM);
			    	 OCCUPATIONCHOICETablecell.setColspan(2);
			         BaseColor OCCUPATIONCHOICETablecellHEADINGcolor = new BaseColor(241,90,41);
			         OCCUPATIONCHOICETablecell.setBackgroundColor(OCCUPATIONCHOICETablecellHEADINGcolor);
			         
			         OCCUPATIONCHOICETablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         OCCUPATIONCHOICETablecell.setVerticalAlignment(Element.ALIGN_CENTER);
			         
			         OCCUPATIONCHOICETablecell.setPaddingTop(5);
			         OCCUPATIONCHOICETablecell.setPaddingBottom(12);
			         OCCUPATIONCHOICETablecell.setPaddingLeft(5);
			         OCCUPATIONCHOICETablecell.setPaddingRight(5);
			         
			         OCCUPATIONCHOICETable.addCell(OCCUPATIONCHOICETablecell);
			         
			         
			         //----------------------END OCCUPATION CHOICE HEADING------------------------- 
			         
			         
			         //----------------------START OCCUPATION CHOICE MY PLAN------------------------- 
			         
			        
			         
			         Paragraph OCCUPATIONCHOICEMYPLAN=new Paragraph("My Career Decision",ArialBlod_10);
			         Paragraph OCCUPATIONCHOICEMYPLAN_TEXT=new Paragraph(OCCUPATIONCHOICEMyPlane_DB,ArialRegular);
			         
			        
			         
			         
			         OCCUPATIONCHOICEMYPLAN.setAlignment(Element.ALIGN_CENTER);
			         OCCUPATIONCHOICEMYPLAN_TEXT.setAlignment(Element.ALIGN_CENTER);
			         OCCUPATIONCHOICETablecell = new PdfPCell();
			         OCCUPATIONCHOICETablecell.setBorderColorRight(BaseColor.WHITE);
			         OCCUPATIONCHOICETablecell.setBorderWidthRight(1.3f);
			         OCCUPATIONCHOICETablecell.setBorder(Rectangle.RIGHT);
			         OCCUPATIONCHOICETablecell.addElement(OCCUPATIONCHOICEMYPLAN);
			         OCCUPATIONCHOICETablecell.addElement(OCCUPATIONCHOICEMYPLAN_TEXT);
			         BaseColor OCCUPATIONCHOICETablecellMYPLANcolor = new BaseColor(0,113,181);
			         OCCUPATIONCHOICETablecell.setBackgroundColor(OCCUPATIONCHOICETablecellMYPLANcolor);
			         
			         OCCUPATIONCHOICETablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         OCCUPATIONCHOICETablecell.setVerticalAlignment(Element.ALIGN_CENTER);
			        
			         OCCUPATIONCHOICETablecell.setPaddingTop(5);
			         OCCUPATIONCHOICETablecell.setPaddingBottom(12);
			         OCCUPATIONCHOICETablecell.setPaddingLeft(2);
			         OCCUPATIONCHOICETablecell.setPaddingRight(2);
			         
			         
			         
			         OCCUPATIONCHOICETable.addCell(OCCUPATIONCHOICETablecell);
			         
			         
			         //----------------------END OCCUPATION CHOICE MY PLAN------------------------- 
			         
			       //----------------------START OCCUPATION CHOICE Alternate plan------------------------- 
			         
			         Paragraph OCCUPATIONCHOICEAlternateplan=new Paragraph("Alternate plan",ArialBlod_10);
			         Paragraph OCCUPATIONCHOICEAlternateplan_TEXT=new Paragraph(OCCUPATIONCHOICEAlternatePlan_DB,ArialRegular);
			         
			        
			         
			         
			         OCCUPATIONCHOICEAlternateplan.setAlignment(Element.ALIGN_CENTER);
			         OCCUPATIONCHOICEAlternateplan_TEXT.setAlignment(Element.ALIGN_CENTER);
			         OCCUPATIONCHOICETablecell = new PdfPCell();
			         OCCUPATIONCHOICETablecell.setBorderColorLeft(BaseColor.WHITE);
			         OCCUPATIONCHOICETablecell.setBorderWidthLeft(1.3f);
			         OCCUPATIONCHOICETablecell.setBorder(Rectangle.LEFT);
			         OCCUPATIONCHOICETablecell.addElement(OCCUPATIONCHOICEAlternateplan);
			         OCCUPATIONCHOICETablecell.addElement(OCCUPATIONCHOICEAlternateplan_TEXT);
			         BaseColor OCCUPATIONCHOICETablecellAlternateplancolor = new BaseColor(0,166,81);
			         OCCUPATIONCHOICETablecell.setBackgroundColor(OCCUPATIONCHOICETablecellAlternateplancolor);
			         
			         OCCUPATIONCHOICETablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         OCCUPATIONCHOICETablecell.setVerticalAlignment(Element.ALIGN_CENTER);
			        
			         OCCUPATIONCHOICETablecell.setPaddingTop(5);
			         OCCUPATIONCHOICETablecell.setPaddingBottom(12);
			         OCCUPATIONCHOICETablecell.setPaddingLeft(2);
			         OCCUPATIONCHOICETablecell.setPaddingRight(2);
			         
			         
			         
			         OCCUPATIONCHOICETable.addCell(OCCUPATIONCHOICETablecell);
			         
			         //----------------------END OCCUPATION CHOICE Alternate plan------------------------- 
			       
			         
			         
//			         OCCUPATIONCHOICETable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//			         OCCUPATIONCHOICETable.setWidthPercentage(96);
//			         
//			         PdfPCell OCCUPATIONCHOICETableDOWNARROWCell;
//			         OCCUPATIONCHOICETableDOWNARROWCell = new PdfPCell();
//			         Image MYPLANDOWNARROW = Image.getInstance("D:/pdf report/downarrow.png");
//			         Image ALTERNATEPLANDOWNARROW = Image.getInstance("D:/pdf report/downarrow1.png");
//			         OCCUPATIONCHOICETableDOWNARROWCell.setBorder(Rectangle.NO_BORDER);
//			         MYPLANDOWNARROW.scaleToFit(10,11);
//			         ALTERNATEPLANDOWNARROW.scaleToFit(10,11);
//			         MYPLANDOWNARROW.setAlignment(Element.ALIGN_CENTER);
//			         ALTERNATEPLANDOWNARROW.setAlignment(Element.ALIGN_CENTER);
//			         OCCUPATIONCHOICETableDOWNARROWCell.addElement(MYPLANDOWNARROW);
//			         OCCUPATIONCHOICETableDOWNARROWCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			         OCCUPATIONCHOICETableDOWNARROWCell.setVerticalAlignment(Element.ALIGN_CENTER);
//			        
//			         OCCUPATIONCHOICETableDOWNARROWCell.setPadding(0.5f);
//			         
//			         
//			         OCCUPATIONCHOICETable.addCell(OCCUPATIONCHOICETableDOWNARROWCell);
//			         
//			         OCCUPATIONCHOICETableDOWNARROWCell = new PdfPCell();
//			         OCCUPATIONCHOICETableDOWNARROWCell.setBorder(Rectangle.NO_BORDER);
//			         OCCUPATIONCHOICETableDOWNARROWCell.addElement(ALTERNATEPLANDOWNARROW);
//			         OCCUPATIONCHOICETableDOWNARROWCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			         OCCUPATIONCHOICETableDOWNARROWCell.setVerticalAlignment(Element.ALIGN_CENTER);
//			        
//			         OCCUPATIONCHOICETableDOWNARROWCell.setPadding(0.5f);
//			         
//			         
//			         
//			         
//			         OCCUPATIONCHOICETable.addCell(OCCUPATIONCHOICETableDOWNARROWCell);
//			         
			         columnTable.addElement(OCCUPATIONCHOICETable);
			         columnTable.go();
			         
			         //----------------------END OCCUPATION CHOICE-------------------------     
			         
			         
			         //----------------------START EDUCATION PATHWAY-------------------------     
			         
			         PdfPTable EDUCATIONPATHWAYTABLE = new PdfPTable(2);
			         EDUCATIONPATHWAYTABLE.setSpacingAfter(4);
			         EDUCATIONPATHWAYTABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			         EDUCATIONPATHWAYTABLE.setWidthPercentage(96);
			    	 PdfPCell EDUCATIONPATHWAYTABLEcell;
			    	 
			    	 
			    	 
			    	 //----------------------START EDUCATION PATHWAY HEADING-------------------------   
			    	 
			    	 
			    	 Chunk EDUCATIONPATHWAYHEADING = new Chunk("EDUCATION PATHWAY",ArialBlod_12);
			    	 
			    	 EDUCATIONPATHWAYTABLEcell = new PdfPCell(new Phrase(EDUCATIONPATHWAYHEADING));
			    	 EDUCATIONPATHWAYTABLEcell.setBorderColorBottom(BaseColor.WHITE);
			    	 EDUCATIONPATHWAYTABLEcell.setBorderWidthBottom(3);
			    	 EDUCATIONPATHWAYTABLEcell.setBorder(Rectangle.BOTTOM);
			    	 EDUCATIONPATHWAYTABLEcell.setColspan(2);
			    	 EDUCATIONPATHWAYTABLEcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    	 EDUCATIONPATHWAYTABLEcell.setVerticalAlignment(Element.ALIGN_CENTER);
			         BaseColor EDUCATIONPATHWAYTABLEcellHEADcolor = new BaseColor(241,90,34);
			         EDUCATIONPATHWAYTABLEcell.setBackgroundColor(EDUCATIONPATHWAYTABLEcellHEADcolor);
			         EDUCATIONPATHWAYTABLEcell.setPaddingTop(5);
			         EDUCATIONPATHWAYTABLEcell.setPaddingBottom(12);
			         EDUCATIONPATHWAYTABLEcell.setPaddingLeft(5);
			         EDUCATIONPATHWAYTABLEcell.setPaddingRight(5);
			         
			         EDUCATIONPATHWAYTABLE.addCell(EDUCATIONPATHWAYTABLEcell);
			         
			       //----------------------END EDUCATION PATHWAY HEADING-------------------------   
			         
			         
			         //----------------------START EDUCATION PATHWAY MY PLAN-------------------------   
			         
			        
//			         Paragraph EDUCATIONPATHWAYMYPLAN=new Paragraph("My plan",ArialBlod_10);
//			         
//			         EDUCATIONPATHWAYMYPLAN.setAlignment(Element.ALIGN_CENTER);
//			         
//			         EDUCATIONPATHWAYTABLEcell = new PdfPCell(new Phrase(EDUCATIONPATHWAYMYPLAN));
//			    	
//			    	 
//			         EDUCATIONPATHWAYTABLEcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			         EDUCATIONPATHWAYTABLEcell.setVerticalAlignment(Element.ALIGN_CENTER);
			         BaseColor EDUCATIONPATHWAYTABLEcellcolor = new BaseColor(0,113,181);
//			         EDUCATIONPATHWAYTABLEcell.setBackgroundColor(EDUCATIONPATHWAYTABLEcellcolor);
//			         EDUCATIONPATHWAYTABLEcell.setPaddingTop(2);
//			         EDUCATIONPATHWAYTABLEcell.setPaddingBottom(10);
//			         EDUCATIONPATHWAYTABLEcell.setPaddingLeft(2);
//			         EDUCATIONPATHWAYTABLEcell.setPaddingRight(2);
//			         
//			         EDUCATIONPATHWAYTABLEcell.setBorderColorRight(BaseColor.WHITE);
//			    	 EDUCATIONPATHWAYTABLEcell.setBorderWidthRight(1.3f);
//			         EDUCATIONPATHWAYTABLEcell.setBorder(Rectangle.RIGHT);
//			         
//			         EDUCATIONPATHWAYTABLE.addCell(EDUCATIONPATHWAYTABLEcell);
			         
			         //----------------------END EDUCATION PATHWAY MY PLAN-------------------------
			         
			       //----------------------START EDUCATION PATHWAY Alternate plan-------------------------
			         
			         
//			         Paragraph EDUCATIONPATHWAYAlternateplan=new Paragraph("Alternate plan",ArialBlod_10);
//			         EDUCATIONPATHWAYAlternateplan.setAlignment(Element.ALIGN_CENTER);
//			         EDUCATIONPATHWAYTABLEcell = new PdfPCell(new Phrase(EDUCATIONPATHWAYAlternateplan));
//			    	 EDUCATIONPATHWAYTABLEcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			         EDUCATIONPATHWAYTABLEcell.setVerticalAlignment(Element.ALIGN_CENTER);
			         BaseColor EDUCATIONPATHWAYTABLEcellcolorAlter = new BaseColor(0,166,81);
//			         EDUCATIONPATHWAYTABLEcell.setBackgroundColor(EDUCATIONPATHWAYTABLEcellcolorAlter);
//			         EDUCATIONPATHWAYTABLEcell.setPaddingTop(2);
//			         EDUCATIONPATHWAYTABLEcell.setPaddingBottom(10);
//			         EDUCATIONPATHWAYTABLEcell.setPaddingLeft(2);
//			         EDUCATIONPATHWAYTABLEcell.setPaddingRight(2);
//			         
//			         EDUCATIONPATHWAYTABLEcell.setBorderColorLeft(BaseColor.WHITE);
//			    	 EDUCATIONPATHWAYTABLEcell.setBorderWidthLeft(1.3f);
//			         EDUCATIONPATHWAYTABLEcell.setBorder(Rectangle.LEFT);
//			         EDUCATIONPATHWAYTABLE.addCell(EDUCATIONPATHWAYTABLEcell);
			         
			         //----------------------END EDUCATION PATHWAY Alternate plan-------------------------
			         
			         //----------------------START EDUCATION PATHWAY BLOCK-------------------------
			         
			       //---------------------------My plan-------------------------
			         
			         
			         Image EDUCATIONPATHWAYTABLE_BLOCKCELL_BACKGROUD_OVER = Image.getInstance(properties.getProperty("com.edupath.report.template.DirectoryPath")+"downarrowwhite.png");
			         
			         EDUCATIONPATHWAYTABLE_BLOCKCELL_BACKGROUD_OVER.scaleToFit(10,12);
			         EDUCATIONPATHWAYTABLE_BLOCKCELL_BACKGROUD_OVER.setAlignment(Element.ALIGN_CENTER);
			         
			         PdfPTable EDUCATIONPATHWAYTABLE_BLOCK_My_Plan = new PdfPTable(1);
			         EDUCATIONPATHWAYTABLE_BLOCK_My_Plan.setWidthPercentage(87);
			         
			         PdfPTable EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan = new PdfPTable(1);
			            
			         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan.setWidthPercentage(90);
			         
			         for(int j=1;j<=6;j++)
			         {	
			        	 PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1 = new PdfPCell();
				            
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.setBorderColor(BaseColor.WHITE);
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.setBorderWidth(0.5f);
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.setBorder(Rectangle.BOX);
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.setHorizontalAlignment(Element.ALIGN_CENTER);
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.setVerticalAlignment(Element.ALIGN_CENTER);
					        
				           
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.setPaddingTop(0);
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.setPaddingBottom(0);
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.setPaddingLeft(0);
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.setPaddingRight(0);
				            
				           
			        	 	PdfPTable EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1 = new PdfPTable(1);
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.setWidthPercentage(100);
			        	 	
			        	 	
			        	 	
				            
				            if(MYPLAN_EDUPATH_streamName_DB!=null)
				            {
					            Paragraph StreamName=new Paragraph(MYPLAN_EDUPATH_streamName_DB,ArialRegular);
					            
					            StreamName.setLeading(1, 0.9f);
						         
					            StreamName.setAlignment(Element.ALIGN_CENTER);
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(8);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(0);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(0);  
	   				            LineDash dashed = new DashedLine();
	   				         
	   				            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new CustomBorder(null, null, null, dashed));
	   				        
				        	 Paragraph Stream=new Paragraph("Stream",ArialBlod_12);
	   				        
				        	 Stream.setLeading(1, 0.9f);
					         
				        	 Stream.setAlignment(Element.ALIGN_CENTER);
				        	 
				        	 
				        	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(Stream);
					            
					            
				        	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);	
				        	 	
				        	 	
				        	    EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(8);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(2);  
					            
					            
					            
					            
					            
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(StreamName);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);
					            
//					            if(MyPlanBlockCount==1)
//					            {
//					            	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {					            
//					            	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            
					            MyPlanBlockCount--;
					            MYPLAN_EDUPATH_streamName_DB=null;
				            }
				            else if(MYPLAN_EDUPATH_combinationName_DB!=null)
				            {
				            	Paragraph StreamName=new Paragraph(MYPLAN_EDUPATH_combinationName_DB,ArialRegular);
					            
					            StreamName.setLeading(1, 0.9f);
						         
					            StreamName.setAlignment(Element.ALIGN_CENTER);
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(8);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(0);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(0);  
	   				            LineDash dashed = new DashedLine();
	   				         
	   				            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new CustomBorder(null, null, null, dashed));
	   				        
				        	 Paragraph Stream=new Paragraph("Elective",ArialBlod_12);
	   				        
				        	 Stream.setLeading(1, 0.9f);
					         
				        	 Stream.setAlignment(Element.ALIGN_CENTER);
				        	 
				        	 
				        	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(Stream);
					            
					            
				        	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);	
				        	 	
				        	 	
				        	    EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(8);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(2);  
					            
					            
					            
					            
					            
					            
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(StreamName);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);
//					            if(MyPlanBlockCount==1)
//					            {
//					            	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {					            
//					            	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            MyPlanBlockCount--;
					            MYPLAN_EDUPATH_combinationName_DB=null;
				            }
				            else if(MYPLAN_EDUPATH_degreeStream_DB!=null)
				            {
				            	Paragraph StreamName=new Paragraph(MYPLAN_EDUPATH_degreeStream_DB,ArialRegular);
					            
					            StreamName.setLeading(1, 0.9f);
						         
					            StreamName.setAlignment(Element.ALIGN_CENTER);
					            
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(8);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(0);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(0);  
	   				            LineDash dashed = new DashedLine();
	   				         
	   				            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new CustomBorder(null, null, null, dashed));
	   				        
				        	 Paragraph Stream=new Paragraph("Graduation",ArialBlod_12);
	   				        
				        	 Stream.setLeading(1, 0.9f);
					         
				        	 Stream.setAlignment(Element.ALIGN_CENTER);
				        	 
				        	 
				        	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(Stream);
					            
					            
				        	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);	
				        	 	
				        	 	
				        	    EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(8);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(2);  
					            
					            
					            
					            
					            
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(StreamName);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);
//					            if(MyPlanBlockCount==1)
//					            {
//					            	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {					            
//					            	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            MyPlanBlockCount--;
					            MYPLAN_EDUPATH_degreeStream_DB=null;
				            }
				            else if(MYPLAN_EDUPATH_pgStream_DB!=null)
				            {
				            	Paragraph StreamName=new Paragraph(MYPLAN_EDUPATH_pgStream_DB,ArialRegular);
					            
					            StreamName.setLeading(1, 0.9f);
						         
					            StreamName.setAlignment(Element.ALIGN_CENTER);
					            
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(8);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(0);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(0);  
	   				            LineDash dashed = new DashedLine();
	   				         
	   				            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new CustomBorder(null, null, null, dashed));
	   				        
				        	 Paragraph Stream=new Paragraph("Post Graduation",ArialBlod_12);
	   				        
				        	 Stream.setLeading(1, 0.9f);
					         
				        	 Stream.setAlignment(Element.ALIGN_CENTER);
				        	 
				        	 
				        	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(Stream);
					            
					            
				        	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);	
				        	 	
				        	 	
				        	    EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(8);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(2);  
					            
					            
					            
					            
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(StreamName);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);
//					            if(MyPlanBlockCount==1)
//					            {
//					            	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//							           
//					            }
//					            else
//					            {
//					            	
//					                 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            MyPlanBlockCount--;
					            MYPLAN_EDUPATH_pgStream_DB=null;
				            }
				            else if(MYPLAN_EDUPATH_occupationName_DB!=null)
				            {
				            	Paragraph StreamName=new Paragraph(MYPLAN_EDUPATH_occupationName_DB,ArialRegular);
					            
					            StreamName.setLeading(1, 0.9f);
						         
					            StreamName.setAlignment(Element.ALIGN_CENTER);
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(10);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(18);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(2);  
					            
					            
					            
					            
					            
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(StreamName);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);
//					            if(MyPlanBlockCount==1)
//					            {
//					            	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {					            
//					            	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            MyPlanBlockCount--;
					            MYPLAN_EDUPATH_occupationName_DB=null;
				            }
				            else if(MYPLAN_EDUPATH_industryName_DB!=null)
				            {
				            	Paragraph StreamName=new Paragraph(MYPLAN_EDUPATH_industryName_DB,ArialRegular);
					            
					            StreamName.setLeading(1, 0.9f);
						         
					            StreamName.setAlignment(Element.ALIGN_CENTER);
					            
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL = new PdfPCell();
				        	 	
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setBorder(Rectangle.NO_BORDER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingTop(10);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingBottom(18);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingLeft(2);
				        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setPaddingRight(2);  
					            
					            
					            
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.addElement(StreamName);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL);
//					            if(MyPlanBlockCount==1)
//					            {
//					            	 EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {					            
//					            	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            MyPlanBlockCount--;
					            MYPLAN_EDUPATH_industryName_DB=null;
				            }
				           
				            
				            
				            	
				            
				            
				            
				            
				            
				            
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1.addElement(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_1);
				            
				            
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_1);
				            
				           
				         //-----------Triabgle Image Cell
				            
				            
				            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image = new PdfPCell();
				            
				            
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image.setBorder(Rectangle.NO_BORDER);
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image.setHorizontalAlignment(Element.ALIGN_CENTER);
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image.setVerticalAlignment(Element.ALIGN_CENTER);
					        
				           
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image.setPaddingTop(0);
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image.setPaddingBottom(10);
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image.setPaddingLeft(0);
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image.setPaddingRight(0);
				            
				            if(MyPlanBlockCount!=0)
				            {
				            
						       EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image.addElement(EDUCATIONPATHWAYTABLE_BLOCKCELL_BACKGROUD_OVER);
				            }
					            
				            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan.addCell(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_CELL_Trianle_Image);	
				            
				            
				            
				            
				            
				          //----------------------------Alternate plan-------------------------
				         
				         
				            
				         
				        
				            
				            
				            
				            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1 = new PdfPCell();
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.setBorderColor(BaseColor.WHITE);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.setBorderWidth(0.5f);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.setBorder(Rectangle.BOX);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.setHorizontalAlignment(Element.ALIGN_CENTER);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.setVerticalAlignment(Element.ALIGN_CENTER);
					        
				          
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.setPaddingTop(0);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.setPaddingBottom(0);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.setPaddingLeft(0);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.setPaddingRight(0);
				            
				            PdfPTable EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2 = new PdfPTable(1);
			        	 	EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.setWidthPercentage(100);
				            
				            
				            
				            
				    		
				    		if(ALTERNATEPLAN_EDUPATH_streamName_DB!=null)
				    		{
				    			Paragraph StreamNameAlternate=new Paragraph(ALTERNATEPLAN_EDUPATH_streamName_DB,ArialRegular);
					            StreamNameAlternate.setLeading(1, 0.9f);
					            
					            
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(2);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(8);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(0);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(0);  
	   				            LineDash dashed = new DashedLine();
	   				         
	   				            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new CustomBorder(null, null, null, dashed));
	   				        
	   				            Paragraph Stream=new Paragraph("Stream",ArialBlod_12);
	   				        
	   				            Stream.setLeading(1, 0.9f);
					         
	   				            Stream.setAlignment(Element.ALIGN_CENTER);
				        	 
				        	 
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(Stream);
					            
					            
	   				      EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);	
				        	 	
				        	 	
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(8);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(2);  
					            
					            
					            
					            
					            
					            
					            
						         
					            StreamNameAlternate.setAlignment(Element.ALIGN_CENTER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(StreamNameAlternate);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);
//					            if(AlternatePlanBlockCount==1)
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            AlternatePlanBlockCount--;
					            ALTERNATEPLAN_EDUPATH_streamName_DB=null;
				    		}
				    		else if(ALTERNATEPLAN_EDUPATH_combinationName_DB!=null)
				    		{
				    			Paragraph StreamNameAlternate=new Paragraph(ALTERNATEPLAN_EDUPATH_combinationName_DB,ArialRegular);
				    			StreamNameAlternate.setLeading(1, 0.9f);
					            
					            
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(2);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(8);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(0);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(0);  
	   				            LineDash dashed = new DashedLine();
	   				         
	   				            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new CustomBorder(null, null, null, dashed));
	   				        
	   				            Paragraph Stream=new Paragraph("Elective",ArialBlod_12);
	   				        
	   				            Stream.setLeading(1, 0.9f);
					         
	   				            Stream.setAlignment(Element.ALIGN_CENTER);
				        	 
				        	 
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(Stream);
					            
					            
	   				      EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);	
				        	 	
				        	 	
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(8);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(2);  
						         
					            StreamNameAlternate.setAlignment(Element.ALIGN_CENTER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(StreamNameAlternate);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);
//					            if(AlternatePlanBlockCount==1)
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            AlternatePlanBlockCount--;
					            ALTERNATEPLAN_EDUPATH_combinationName_DB=null;
				    		}
				    		else if(ALTERNATEPLAN_EDUPATH_degreeStream_DB!=null)
				    		{
				    			Paragraph StreamNameAlternate=new Paragraph(ALTERNATEPLAN_EDUPATH_degreeStream_DB,ArialRegular);
				    			StreamNameAlternate.setLeading(1, 0.9f);
					            
					            
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(2);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(8);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(0);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(0);  
	   				            LineDash dashed = new DashedLine();
	   				         
	   				            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new CustomBorder(null, null, null, dashed));
	   				        
	   				            Paragraph Stream=new Paragraph("Graduation",ArialBlod_12);
	   				        
	   				            Stream.setLeading(1, 0.9f);
					         
	   				            Stream.setAlignment(Element.ALIGN_CENTER);
				        	 
				        	 
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(Stream);
					            
					            
	   				      EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);	
				        	 	
				        	 	
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(8);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(2);  
					            StreamNameAlternate.setAlignment(Element.ALIGN_CENTER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(StreamNameAlternate);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);
//					            if(AlternatePlanBlockCount==1)
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            AlternatePlanBlockCount--;
					            ALTERNATEPLAN_EDUPATH_degreeStream_DB=null;
				    		}
				    		else if(ALTERNATEPLAN_EDUPATH_pgStream_DB!=null)
				    		{
				    			Paragraph StreamNameAlternate=new Paragraph(ALTERNATEPLAN_EDUPATH_pgStream_DB,ArialRegular);
				    			StreamNameAlternate.setLeading(1, 0.9f);
					            
					            
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(2);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(8);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(0);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(0);  
	   				            LineDash dashed = new DashedLine();
	   				         
	   				            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new CustomBorder(null, null, null, dashed));
	   				        
	   				            Paragraph Stream=new Paragraph("Post Graduation",ArialBlod_12);
	   				        
	   				            Stream.setLeading(1, 0.9f);
					         
	   				            Stream.setAlignment(Element.ALIGN_CENTER);
				        	 
				        	 
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(Stream);
					            
					            
	   				      EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);	
				        	 	
				        	 	
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(8);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(2);  
					            StreamNameAlternate.setAlignment(Element.ALIGN_CENTER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(StreamNameAlternate);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);
//					            if(AlternatePlanBlockCount==1)
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            AlternatePlanBlockCount--;
					            ALTERNATEPLAN_EDUPATH_pgStream_DB=null;
				    		}
				    		else if(ALTERNATEPLAN_EDUPATH_occupationName_DB!=null)
				    		{
				    			Paragraph StreamNameAlternate=new Paragraph(ALTERNATEPLAN_EDUPATH_occupationName_DB,ArialRegular);
				    			StreamNameAlternate.setLeading(1, 0.9f);
					            
					            
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
					            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(10);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(18);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(2);  
					            StreamNameAlternate.setAlignment(Element.ALIGN_CENTER);
					            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(StreamNameAlternate);
					            EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);
//					            if(AlternatePlanBlockCount==1)
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            AlternatePlanBlockCount--;
					            ALTERNATEPLAN_EDUPATH_occupationName_DB=null;
				    		}
				    		else if(ALTERNATEPLAN_EDUPATH_industryName_DB!=null)
				    		{
				    			Paragraph StreamNameAlternate=new Paragraph(ALTERNATEPLAN_EDUPATH_industryName_DB,ArialRegular);
				    			StreamNameAlternate.setLeading(1, 0.9f);
					            
					            
					            
					            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL = new PdfPCell();
					            
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setBorder(Rectangle.NO_BORDER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setHorizontalAlignment(Element.ALIGN_CENTER);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setVerticalAlignment(Element.ALIGN_CENTER);  
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingTop(10);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingBottom(18);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingLeft(2);
	   				         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setPaddingRight(2);  
					         StreamNameAlternate.setAlignment(Element.ALIGN_CENTER);
					         EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.addElement(StreamNameAlternate);
					         EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL);
//					            if(AlternatePlanBlockCount==1)
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(1.5f));
//					            }
//					            else
//					            {
//					            	EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL.setCellEvent(new RoundedBorder(0));
//					            }
					            AlternatePlanBlockCount--;
					            ALTERNATEPLAN_EDUPATH_industryName_DB=null;
				    		}
				            
				    		EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1.addElement(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan_2);
					            
					            
				    		EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan_CELL_1);
				            
				           
				            
				           
				            
				            PdfPCell EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image = new PdfPCell();
				            
				            
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image.setBorder(Rectangle.NO_BORDER);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image.setHorizontalAlignment(Element.ALIGN_CENTER);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image.setVerticalAlignment(Element.ALIGN_CENTER);
					        
				           
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image.setPaddingTop(0);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image.setPaddingBottom(10);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image.setPaddingLeft(0);
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image.setPaddingRight(0);
				            
				            if(AlternatePlanBlockCount!=0)
				            {
				            
						       EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image.addElement(EDUCATIONPATHWAYTABLE_BLOCKCELL_BACKGROUD_OVER);
				            }
					            
				            EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan.addCell(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_CELL_Trianle_Image);
			        	 
			            
			         }
			            
			            //--------START MY PLAN BLOCL CELL---------------------------
			         	EDUCATIONPATHWAYTABLEcell = new PdfPCell();
				    	
			         	EDUCATIONPATHWAYTABLEcell.setBackgroundColor(EDUCATIONPATHWAYTABLEcellcolor);
			         	EDUCATIONPATHWAYTABLEcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         	EDUCATIONPATHWAYTABLEcell.setVerticalAlignment(Element.ALIGN_CENTER);
			        
			        
			         	EDUCATIONPATHWAYTABLEcell.setPaddingTop(20);
			         	EDUCATIONPATHWAYTABLEcell.setPaddingBottom(30);
			         	EDUCATIONPATHWAYTABLEcell.setPaddingLeft(0);
			         	EDUCATIONPATHWAYTABLEcell.setPaddingRight(0);
			         
			         
			         
			         	EDUCATIONPATHWAYTABLEcell.setBorderColorRight(BaseColor.WHITE);
			         	EDUCATIONPATHWAYTABLEcell.setBorderWidthRight(1.3f);
			         	EDUCATIONPATHWAYTABLEcell.setBorder(Rectangle.RIGHT);
			         
			         
			            
			           
			            EDUCATIONPATHWAYTABLEcell.addElement(EDUCATIONPATHWAYTABLE_BLOCK_My_Plan);
			         
			            EDUCATIONPATHWAYTABLE.addCell(EDUCATIONPATHWAYTABLEcell);   
			            //--------END MY PLAN BLOCL CELL---------------------------
			            //--------START ALTERNATE PLAN BLOCL CELL---------------------------
			            EDUCATIONPATHWAYTABLEcell = new PdfPCell();
				    	
				    	 
			            EDUCATIONPATHWAYTABLEcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            EDUCATIONPATHWAYTABLEcell.setVerticalAlignment(Element.ALIGN_CENTER);
			        
			            EDUCATIONPATHWAYTABLEcell.setBackgroundColor(EDUCATIONPATHWAYTABLEcellcolorAlter);
			            EDUCATIONPATHWAYTABLEcell.setPaddingTop(20);
			            EDUCATIONPATHWAYTABLEcell.setPaddingBottom(30);
			            EDUCATIONPATHWAYTABLEcell.setPaddingLeft(0);
			            EDUCATIONPATHWAYTABLEcell.setPaddingRight(0);
			         
			         
			            EDUCATIONPATHWAYTABLEcell.setBorderColorLeft(BaseColor.WHITE);
			         	EDUCATIONPATHWAYTABLEcell.setBorderWidthLeft(1.3f);
			            EDUCATIONPATHWAYTABLEcell.setBorder(Rectangle.LEFT);
			            
			            EDUCATIONPATHWAYTABLEcell.addElement(EDUCATIONPATHWAYTABLE_BLOCK_Aletrnate_Plan);
				         
				         
			            EDUCATIONPATHWAYTABLE.addCell(EDUCATIONPATHWAYTABLEcell);
			            
			            
			            
			            //--------END ALTERNATE PLAN BLOCL CELL---------------------------
			         
			            
			            //------------Start Blank Cell-------------
			            
			            //-----Start For My Plan-------------
//			            EDUCATIONPATHWAYTABLEcell = new PdfPCell();
//				    	
//			            EDUCATIONPATHWAYTABLEcell.setBackgroundColor(EDUCATIONPATHWAYTABLEcellcolor);
//			            EDUCATIONPATHWAYTABLEcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			            EDUCATIONPATHWAYTABLEcell.setVerticalAlignment(Element.ALIGN_CENTER);
//				        
//				        
//			            EDUCATIONPATHWAYTABLEcell.setPaddingTop(0);
//			            EDUCATIONPATHWAYTABLEcell.setPaddingBottom(0);
//			            EDUCATIONPATHWAYTABLEcell.setPaddingLeft(0);
//			            EDUCATIONPATHWAYTABLEcell.setPaddingRight(0);
//				         
//				         
//				         
//				       
//			            EDUCATIONPATHWAYTABLEcell.setBorderColorRight(BaseColor.WHITE);
//			         	EDUCATIONPATHWAYTABLEcell.setBorderWidthRight(1.3f);
//			            EDUCATIONPATHWAYTABLEcell.setBorder(Rectangle.RIGHT);
//				         EDUCATIONPATHWAYTABLEcell.addElement(new  Paragraph(" "));
//				         
//				         
//				         
//				         
//				         EDUCATIONPATHWAYTABLE.addCell(EDUCATIONPATHWAYTABLEcell);
//				         
//				         //---------ENd For My Plan------------
//				         
//				         //----------Start For Alternate Plan------------
//				         EDUCATIONPATHWAYTABLEcell = new PdfPCell();
//					    	
//				         EDUCATIONPATHWAYTABLEcell.setBackgroundColor(EDUCATIONPATHWAYTABLEcellcolorAlter);
//				         EDUCATIONPATHWAYTABLEcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				         EDUCATIONPATHWAYTABLEcell.setVerticalAlignment(Element.ALIGN_CENTER);
//				        
//				        
//				         EDUCATIONPATHWAYTABLEcell.setPaddingTop(0);
//				         EDUCATIONPATHWAYTABLEcell.setPaddingBottom(0);
//				         EDUCATIONPATHWAYTABLEcell.setPaddingLeft(0);
//				         EDUCATIONPATHWAYTABLEcell.setPaddingRight(0);
//				         
//				         
//				         
//				       
//				         EDUCATIONPATHWAYTABLEcell.setBorderColorLeft(BaseColor.WHITE);
//				         EDUCATIONPATHWAYTABLEcell.setBorderWidthLeft(1.3f);
//				         EDUCATIONPATHWAYTABLEcell.setBorder(Rectangle.LEFT);
//				         EDUCATIONPATHWAYTABLEcell.addElement(new  Paragraph(" "));
//				         
//				         
//				         
//				         
//				         EDUCATIONPATHWAYTABLE.addCell(EDUCATIONPATHWAYTABLEcell);
			            
				         //-----------End For Alternate Plan-----
				         
			            //-----------End Blank Cell=----------------
			            
			       //----------------------END EDUCATION PATHWAY BLOCK-------------------------
				         
				       
			        
			         columnTable.addElement(EDUCATIONPATHWAYTABLE);
			         columnTable.go();
			         
			       //----------------------END EDUCATION PATHWAY-------------------------      
			         
			      
			       //------------------------START SUBJECT CHOICE-----------------------------
			         
			        
			         
			         PdfPTable SUBJECTCHOICETABLE = new PdfPTable(1);
			         SUBJECTCHOICETABLE.setSpacingAfter(6);
			         SUBJECTCHOICETABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			         SUBJECTCHOICETABLE.setWidthPercentage(96);
			         PdfPCell SUBJECTCHOICETABLECell;
			         
			         //------------------START SUBJECT CHOICE HEADING---------------------------
			         
			         Chunk SUBJECTCHOICEHEADING = new Chunk("SUBJECT CHOICE",ArialBlod_12);
			         
			         SUBJECTCHOICETABLECell = new PdfPCell(new Phrase(SUBJECTCHOICEHEADING));
			         SUBJECTCHOICETABLECell.setBorder(Rectangle.NO_BORDER);
			         
			         BaseColor CellBackgroundColorSUBJECTCHOICE = new BaseColor(241,90,34);
			         SUBJECTCHOICETABLECell.setBackgroundColor(CellBackgroundColorSUBJECTCHOICE);
			         
			         SUBJECTCHOICETABLECell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         SUBJECTCHOICETABLECell.setVerticalAlignment(Element.ALIGN_CENTER);
			         
			         SUBJECTCHOICETABLECell.setPaddingTop(5);
			         SUBJECTCHOICETABLECell.setPaddingBottom(12);
			         SUBJECTCHOICETABLECell.setPaddingLeft(5);
			         SUBJECTCHOICETABLECell.setPaddingRight(5);
			         
			         SUBJECTCHOICETABLE.addCell(SUBJECTCHOICETABLECell);
			        
			         //------------------END SUBJECT CHOICE HEADING---------------------------
			         
			         
			         //------------------START SUBJECT CHOICE FROM DB----------------------------
			         
			         Paragraph SUBJECTCHOICE_TEXT=new Paragraph(SUBJECT_CHOICE_FINAL_TEXT_DB,ArialRegular);
			         
			         
			         SUBJECTCHOICETABLECell = new PdfPCell(new Phrase(SUBJECTCHOICE_TEXT));
			         SUBJECTCHOICETABLECell.setBorder(Rectangle.NO_BORDER);
			         
			         BaseColor CellBackgroundColorSUBJECTCHOICE_TEXT = new BaseColor(244,125,92);
			         SUBJECTCHOICETABLECell.setBackgroundColor(CellBackgroundColorSUBJECTCHOICE_TEXT);
			         
			         SUBJECTCHOICETABLECell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         SUBJECTCHOICETABLECell.setVerticalAlignment(Element.ALIGN_CENTER);
			         
			         SUBJECTCHOICETABLECell.setPaddingTop(6);
			         SUBJECTCHOICETABLECell.setPaddingBottom(20);
			         SUBJECTCHOICETABLECell.setPaddingLeft(5);
			         SUBJECTCHOICETABLECell.setPaddingRight(5);
			         
			         SUBJECTCHOICETABLE.addCell(SUBJECTCHOICETABLECell);
			         
			         //------------------END SUBJECT CHOICE FROM DB---------------------------- 
			         
			         columnTable.addElement(SUBJECTCHOICETABLE);
			         columnTable.go();
			         
			         //------------------------END SUBJECT CHOICE-----------------------------  
			         
			         
			         
			         //----------------------START ADDING FALSE IMAGES--------------------------
			        
//			         float YCoOridinate=  columnTable.getYLine();
//			         float ActualImageHeight= YCoOridinate-60;
//			         if(YCoOridinate>=150)
//			         {
//			        	
//			        	 Image FalseImage = Image.getInstance("D:/pdf report/falseimage.png");
//			        	 FalseImage.setAlignment(Element.ALIGN_CENTER);
//			        	 FalseImage.scaleToFit(400, ActualImageHeight);
//			        	 
//			        	 PdfPTable FALSEIMAGETABLE = new PdfPTable(1);
//			        	 FALSEIMAGETABLE.setSpacingAfter(6);
//			        	 FALSEIMAGETABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//			        	 FALSEIMAGETABLE.setWidthPercentage(96);
//				         
//				         
//				         
//			        	 PdfPCell FALSEIMAGETABLE_CELL = new PdfPCell();
//			        	 FALSEIMAGETABLE_CELL.setBorder(Rectangle.NO_BORDER);
//				         
//				         
//				         
//			        	 FALSEIMAGETABLE_CELL.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//			        	 FALSEIMAGETABLE_CELL.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				         
//			        	 FALSEIMAGETABLE_CELL.setPaddingTop(20);
//			        	 FALSEIMAGETABLE_CELL.setPaddingBottom(0);
//			        	 FALSEIMAGETABLE_CELL.setPaddingLeft(0);
//			        	 FALSEIMAGETABLE_CELL.setPaddingRight(0);
//				        
//			        	 FALSEIMAGETABLE_CELL.addElement(FalseImage);
//				         
//			        	 FALSEIMAGETABLE.addCell(FALSEIMAGETABLE_CELL);
//				         columnTable.addElement(FALSEIMAGETABLE);
//				         columnTable.go();
//			        	 
//			        	 
//			        	 
//			        	 
//			         }
			        	
			         //----------------------END ADDING FALSE IMAGES--------------------------
			         
			         
				}
				
				if(i==11)
				{
					//-----------------------START EXAMS TO PREPARE---------------------------------
					
					//-----------------------START EXAMS TO PREPARE HEADING------------------------
					
			        
			        PdfPTable EXAMS_TO_PREPARE_TABLE = new PdfPTable(2);
			        EXAMS_TO_PREPARE_TABLE.setSpacingAfter(4);
			        EXAMS_TO_PREPARE_TABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        EXAMS_TO_PREPARE_TABLE.setWidthPercentage(96);
			        PdfPCell EXAMS_TO_PREPARE_TABLE_cell;
			        
			        
			         Chunk EXAMS_TO_PREPARE_HEADING = new Chunk("EXAMS TO PREPARE",ArialBlod_12);
			         EXAMS_TO_PREPARE_TABLE_cell = new PdfPCell(new Phrase(EXAMS_TO_PREPARE_HEADING));
			         EXAMS_TO_PREPARE_TABLE_cell.setBorderColorBottom(BaseColor.WHITE);
			         EXAMS_TO_PREPARE_TABLE_cell.setBorderWidthBottom(3);
			         EXAMS_TO_PREPARE_TABLE_cell.setBorder(Rectangle.BOTTOM);
			         EXAMS_TO_PREPARE_TABLE_cell.setColspan(2);
			         BaseColor EXAMS_TO_PREPARE_TABLE_cell_Color = new BaseColor(241,90,41);
			         EXAMS_TO_PREPARE_TABLE_cell.setBackgroundColor(EXAMS_TO_PREPARE_TABLE_cell_Color);
			         
			         EXAMS_TO_PREPARE_TABLE_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			         EXAMS_TO_PREPARE_TABLE_cell.setVerticalAlignment(Element.ALIGN_CENTER);
			         
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingTop(5);
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingBottom(12);
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingLeft(5);
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingRight(5);
			         
			         EXAMS_TO_PREPARE_TABLE.addCell(EXAMS_TO_PREPARE_TABLE_cell);
			        
			        
			        
					//-----------------------END EXAMS TO PREPARE HEADING------------------------
					
			       
			         
			         Paragraph pMyplan_heading=new Paragraph(OCCUPATIONCHOICEMyPlane_DB,ArialBlod_12);
			         pMyplan_heading.setAlignment(Element.ALIGN_CENTER);
			        
			         Paragraph pMyplan_heading1=new Paragraph();
			         pMyplan_heading1.setLeading(1, 0.5f);
			         LineSeparator line = new LineSeparator();
			         line.setLineColor(BaseColor.WHITE);
			         line.setLineWidth(0.5f);
			         
			         float leng=OCCUPATIONCHOICEMyPlane_DB.length();
			         
			         float hper=leng*2.5f;
			         
			         line.setPercentage(hper);
			         
			         pMyplan_heading1.add(line);
			         
//			         Paragraph pMyplan_TEXT=new Paragraph(EXAMS_TO_PREPARE_MY_PLAN_DB,ArialRegular);
//			         pMyplan_TEXT.setAlignment(Element.ALIGN_CENTER);
			         
			         
			         
			         
			         
			         
			         
			         Paragraph pAlternateplan_heading=new Paragraph(OCCUPATIONCHOICEAlternatePlan_DB,ArialBlod_12);
			         pAlternateplan_heading.setAlignment(Element.ALIGN_CENTER);
			         
			         
			         
			         Paragraph pAlternateplan_heading1=new Paragraph();
			         pAlternateplan_heading1.setLeading(1, 0.5f);
			         LineSeparator line1 = new LineSeparator();
			         line1.setLineColor(BaseColor.WHITE);
			         line1.setLineWidth(0.5f);
			         
			         float leng1=OCCUPATIONCHOICEAlternatePlan_DB.length();
			         
			         float hper1=leng1*2.5f;
			         
			         line1.setPercentage(hper1);
			         
			         pAlternateplan_heading1.add(line1);
			         
			         
			         
			         
			         
			         
			         
//			         Paragraph pAlternateplan_TEXT=new Paragraph(EXAMS_TO_PREPARE_ALTERNATE_PLAN_DB,ArialRegular);
//			         pAlternateplan_TEXT.setAlignment(Element.ALIGN_CENTER);
			        
			         
			         
			         //---------------------START MY PLAN FROM DB--------------------------------
			         
			         EXAMS_TO_PREPARE_TABLE_cell = new PdfPCell();
			         EXAMS_TO_PREPARE_TABLE_cell.setBorderColorRight(BaseColor.WHITE);
			         EXAMS_TO_PREPARE_TABLE_cell.setBorderWidthRight(1.3f);
			        
			         EXAMS_TO_PREPARE_TABLE_cell.setBorder(Rectangle.RIGHT);
			         BaseColor EXAMS_TO_PREPARE_TABLE_cell_Color_my_plan = new BaseColor(0,113,181);
			         EXAMS_TO_PREPARE_TABLE_cell.setBackgroundColor(EXAMS_TO_PREPARE_TABLE_cell_Color_my_plan);
			         
			         EXAMS_TO_PREPARE_TABLE_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			         EXAMS_TO_PREPARE_TABLE_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			         
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingTop(3);
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingBottom(18);
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingLeft(2);
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingRight(2);
			         EXAMS_TO_PREPARE_TABLE_cell.addElement(pMyplan_heading);
			         if(EXAMS_TO_PREPARE_MY_PLAN_DB!=null)
			         {
			        	 EXAMS_TO_PREPARE_TABLE_cell.addElement(pMyplan_heading1);
			        	 EXAMS_TO_PREPARE_TABLE_cell.addElement(pMyplan_TEXT);
			         }
			         EXAMS_TO_PREPARE_TABLE.addCell(EXAMS_TO_PREPARE_TABLE_cell);
			         
			         //---------------------END MY PLAN FROM DB--------------------------------
			         
			       //---------------------START ALTERNATE PLAN FROM DB--------------------------------
			         EXAMS_TO_PREPARE_TABLE_cell = new PdfPCell();
			         EXAMS_TO_PREPARE_TABLE_cell.setBorderColorLeft(BaseColor.WHITE);
			         EXAMS_TO_PREPARE_TABLE_cell.setBorderWidthLeft(1.3f);
			         EXAMS_TO_PREPARE_TABLE_cell.setBorder(Rectangle.LEFT);
			         BaseColor EXAMS_TO_PREPARE_TABLE_cell_Color_alternate_plan = new BaseColor(0,166,81);
			         EXAMS_TO_PREPARE_TABLE_cell.setBackgroundColor(EXAMS_TO_PREPARE_TABLE_cell_Color_alternate_plan);
			         
			         EXAMS_TO_PREPARE_TABLE_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			         EXAMS_TO_PREPARE_TABLE_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			         
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingTop(3);
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingBottom(18);
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingLeft(2);
			         EXAMS_TO_PREPARE_TABLE_cell.setPaddingRight(2);
			         EXAMS_TO_PREPARE_TABLE_cell.addElement(pAlternateplan_heading);
			         
			         if(EXAMS_TO_PREPARE_ALTERNATE_PLAN_DB!=null)
			         {
			         
			        	 EXAMS_TO_PREPARE_TABLE_cell.addElement(pAlternateplan_heading1);
			        	 EXAMS_TO_PREPARE_TABLE_cell.addElement(pAlternateplan_TEXT);
			         }
			         
			         EXAMS_TO_PREPARE_TABLE.addCell(EXAMS_TO_PREPARE_TABLE_cell);
			         
			         //---------------------END ALTERNATE PLAN FROM DB-------------------------------- 
			         
			         ColumnText columnTable = new ColumnText(writer.getDirectContent());
			         columnTable.setSimpleColumn(55, 36, 559, 730);
			         columnTable.addElement(EXAMS_TO_PREPARE_TABLE);
			         columnTable.go();
			         
					//-----------------------END EXAMS TO PREPARE---------------------------------
			         
			         
			         //----------------------START TUTORIAL SELECTION-----------------------------
//			         Paragraph TUTORIAL_SELECTION_Myplan_TEXT=new Paragraph(TUTORIAL_SELECTION_DB,ArialRegular);
//			         TUTORIAL_SELECTION_Myplan_TEXT.setAlignment(Element.ALIGN_CENTER);
			         
			         
			         //----------------------START TUTORIAL SELECTION HEADING-----------------------------
			         	PdfPTable TUTORIAL_SELECTION_TABLE = new PdfPTable(1);
			            TUTORIAL_SELECTION_TABLE.setSpacingAfter(4);
			            TUTORIAL_SELECTION_TABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			            TUTORIAL_SELECTION_TABLE.setWidthPercentage(96);
				        PdfPCell TUTORIAL_SELECTION_TABLE_cell;
			         
				        
				         Chunk TUTORIAL_SELECTION_HEADING = new Chunk("TUTORIAL SELECTION",ArialBlod_12);
				         TUTORIAL_SELECTION_TABLE_cell = new PdfPCell(new Phrase(TUTORIAL_SELECTION_HEADING));
				         
				         TUTORIAL_SELECTION_TABLE_cell.setBorder(Rectangle.NO_BORDER);
				         
				         BaseColor TUTORIAL_SELECTION_TABLE_cell_Color = new BaseColor(241,90,41);
				         TUTORIAL_SELECTION_TABLE_cell.setBackgroundColor(TUTORIAL_SELECTION_TABLE_cell_Color);
				         
				         TUTORIAL_SELECTION_TABLE_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				         TUTORIAL_SELECTION_TABLE_cell.setVerticalAlignment(Element.ALIGN_CENTER);
				         
				         TUTORIAL_SELECTION_TABLE_cell.setPaddingTop(5);
				         TUTORIAL_SELECTION_TABLE_cell.setPaddingBottom(12);
				         TUTORIAL_SELECTION_TABLE_cell.setPaddingLeft(5);
				         TUTORIAL_SELECTION_TABLE_cell.setPaddingRight(5);
				         
				         TUTORIAL_SELECTION_TABLE.addCell(TUTORIAL_SELECTION_TABLE_cell);
			         
			         //----------------------END TUTORIAL SELECTION HEADING-----------------------------
			         	
				        //-------------------START TUTORIAL SELECTION FROM DB-------------------------- 
				         TUTORIAL_SELECTION_TABLE_cell = new PdfPCell();
				         TUTORIAL_SELECTION_TABLE_cell.setBorder(Rectangle.NO_BORDER);
				         BaseColor TUTORIAL_SELECTION_TABLE_cell_Color_my_plan = new BaseColor(244,125,92);
				         TUTORIAL_SELECTION_TABLE_cell.setBackgroundColor(TUTORIAL_SELECTION_TABLE_cell_Color_my_plan);
				         
				         TUTORIAL_SELECTION_TABLE_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				         TUTORIAL_SELECTION_TABLE_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
				         TUTORIAL_SELECTION_TABLE_cell.setPaddingTop(3);
				         TUTORIAL_SELECTION_TABLE_cell.setPaddingBottom(15);
				         TUTORIAL_SELECTION_TABLE_cell.setPaddingLeft(2);
				         TUTORIAL_SELECTION_TABLE_cell.setPaddingRight(2);
				         if(TUTORIAL_SELECTION_DB!=null)
				         {
				         
				        	 TUTORIAL_SELECTION_TABLE_cell.addElement(TUTORIAL_SELECTION_Myplan_TEXT);
				         }
				         else
				         {
				        	 
				        	 Paragraph TUTORIAL_SELECTION_Myplan_TEXT1=new Paragraph("None Selected",ArialRegular);
					         TUTORIAL_SELECTION_Myplan_TEXT1.setAlignment(Element.ALIGN_CENTER);
				        	 
				        	 TUTORIAL_SELECTION_TABLE_cell.addElement(TUTORIAL_SELECTION_Myplan_TEXT1);
				         }
				         
				         TUTORIAL_SELECTION_TABLE.addCell(TUTORIAL_SELECTION_TABLE_cell);
				         
				         //-------------------END TUTORIAL SELECTION FROM DB-------------------------- 
				         
				         columnTable.addElement(TUTORIAL_SELECTION_TABLE);
				         columnTable.go();
			         
			         //----------------------END TUTORIAL SELECTION----------------------------- 
			         
			         //----------------------START PARAMETERS SELECTED FOR SEARCHING A PU COLLEGE/ SCHOOL-----------------------
				         Paragraph PARAMETERS_SELECTED_TEXT=new Paragraph(PARAMETERS_SELECTED_DB,ArialRegular);
				         PARAMETERS_SELECTED_TEXT.setAlignment(Element.ALIGN_CENTER);
				         
				         
				         	PdfPTable PARAMETERS_TABLE = new PdfPTable(1);
				            PARAMETERS_TABLE.setSpacingAfter(4);
				            PARAMETERS_TABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				            PARAMETERS_TABLE.setWidthPercentage(96);
					        PdfPCell PARAMETERS_TABLE_cell;
					        
					        
					     //----------------------START HEADING------------------------ 
					        
					        Chunk PARAMETERS_HEADING = new Chunk("PARAMETERS SELECTED FOR SEARCHING A PU COLLEGE/ SCHOOL",ArialBlod_12);
					         PARAMETERS_TABLE_cell = new PdfPCell(new Phrase(PARAMETERS_HEADING));
					         PARAMETERS_TABLE_cell.setBorder(Rectangle.NO_BORDER);
					        
					         BaseColor PARAMETERS_TABLE_cell_Color = new BaseColor(241,90,41);
					         PARAMETERS_TABLE_cell.setBackgroundColor(PARAMETERS_TABLE_cell_Color);
					         
					         PARAMETERS_TABLE_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					         PARAMETERS_TABLE_cell.setVerticalAlignment(Element.ALIGN_CENTER);
					         
					         PARAMETERS_TABLE_cell.setPaddingTop(5);
					         PARAMETERS_TABLE_cell.setPaddingBottom(12);
					         PARAMETERS_TABLE_cell.setPaddingLeft(5);
					         PARAMETERS_TABLE_cell.setPaddingRight(5);
					         
					         PARAMETERS_TABLE.addCell(PARAMETERS_TABLE_cell);
					      //----------------------END HEADING------------------------ 
					        
					        
					      //----------------------START DATA FROM DB------------------
					        
					         PARAMETERS_TABLE_cell.setBorder(Rectangle.NO_BORDER);
					         BaseColor PARAMETERS_TABLE_cell_Color_my_plan = new BaseColor(244,125,92);
					         PARAMETERS_TABLE_cell.setBackgroundColor(PARAMETERS_TABLE_cell_Color_my_plan);
					         
					         PARAMETERS_TABLE_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					         PARAMETERS_TABLE_cell.setVerticalAlignment(Element.ALIGN_CENTER);
					         
					         PARAMETERS_TABLE_cell.setPaddingTop(3);
					         PARAMETERS_TABLE_cell.setPaddingBottom(15);
					         PARAMETERS_TABLE_cell.setPaddingLeft(2);
					         PARAMETERS_TABLE_cell.setPaddingRight(2);
					         
					         if(PARAMETERS_SELECTED_DB!=null)
					         {
					        	 PARAMETERS_TABLE_cell.addElement(PARAMETERS_SELECTED_TEXT);
					         }
					         else
					         {
					        	 Paragraph PARAMETERS_SELECTED_TEXT1=new Paragraph("None Selected",ArialRegular);
						         PARAMETERS_SELECTED_TEXT1.setAlignment(Element.ALIGN_CENTER);
						         PARAMETERS_TABLE_cell.addElement(PARAMETERS_SELECTED_TEXT1);
					         }
					         
					        
					         
					         
					         PARAMETERS_TABLE.addCell(PARAMETERS_TABLE_cell);
					      //----------------------END DATA FROM DB--------------------
				         
					         columnTable.addElement(PARAMETERS_TABLE);
					         columnTable.go();
				         
				   //----------------------END PARAMETERS SELECTED FOR SEARCHING A PU COLLEGE/ SCHOOL-----------------------
					         
					         
					         
					//---------------------START COLLEGE/ SCHOOL FOR COMPARISON----------------------------------------- 
					         
//					         Paragraph COLLEGE_SCHOOL_FOR_COMPARISON_TEXT=new Paragraph(COLLEGE_SCHOOL_FOR_COMPARISON_DB,ArialRegular);
//					         COLLEGE_SCHOOL_FOR_COMPARISON_TEXT.setAlignment(Element.ALIGN_CENTER);
					         
					         	PdfPTable COLLEGE_SCHOOL_FOR_COMPARISON_TABLE = new PdfPTable(1);
					         	COLLEGE_SCHOOL_FOR_COMPARISON_TABLE.setSpacingAfter(4);
					         	COLLEGE_SCHOOL_FOR_COMPARISON_TABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					         	COLLEGE_SCHOOL_FOR_COMPARISON_TABLE.setWidthPercentage(96);
						        PdfPCell COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell;
					         
					         
					         //------------START HEADING----------------------
					         
						        Chunk COLLEGE_SCHOOL_FOR_COMPARISON_HEADING = new Chunk("COLLEGE/ SCHOOL FOR COMPARISON",ArialBlod_12);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell = new PdfPCell(new Phrase(COLLEGE_SCHOOL_FOR_COMPARISON_HEADING));
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setBorder(Rectangle.NO_BORDER);
						        
						         BaseColor COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell_Color = new BaseColor(241,90,41);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setBackgroundColor(COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell_Color);
						         
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setVerticalAlignment(Element.ALIGN_CENTER);
						         
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setPaddingTop(5);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setPaddingBottom(12);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setPaddingLeft(5);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setPaddingRight(5);
						         
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE.addCell(COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell);
						        
					         
					         //------------END HEADING-----------------------
					         
					         
					         //------------START DB CONTENT------------------
					         
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell = new PdfPCell();
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setBorder(Rectangle.NO_BORDER);
						         BaseColor COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell_Color_my_plan = new BaseColor(244,125,92);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setBackgroundColor(COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell_Color_my_plan);
						         
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setVerticalAlignment(Element.ALIGN_CENTER);
						         
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setPaddingTop(3);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setPaddingBottom(15);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setPaddingLeft(2);
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.setPaddingRight(2);
						         
						         
						         if(COLLEGE_SCHOOL_FOR_COMPARISON_DB!=null)
						         {
						        	 COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.addElement(COLLEGE_SCHOOL_FOR_COMPARISON_TEXT);
						         }
						         else
						         {
						        	 Paragraph COLLEGE_SCHOOL_FOR_COMPARISON_TEXT1=new Paragraph("None Selected",ArialRegular);
							         COLLEGE_SCHOOL_FOR_COMPARISON_TEXT1.setAlignment(Element.ALIGN_CENTER);
							         
							         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell.addElement(COLLEGE_SCHOOL_FOR_COMPARISON_TEXT1);
							         
						         }
						         
						         COLLEGE_SCHOOL_FOR_COMPARISON_TABLE.addCell(COLLEGE_SCHOOL_FOR_COMPARISON_TABLE_cell);
					         
					         
					       //------------END DB CONTENT------------------
					         
						         columnTable.addElement(COLLEGE_SCHOOL_FOR_COMPARISON_TABLE);
						         columnTable.go();
					         
				 //---------------------END COLLEGE/ SCHOOL FOR COMPARISON----------------------------------------- 
					         
						       //----------------------START ADDING FALSE IMAGES--------------------------
							        
//						         float YCoOridinate=  columnTable.getYLine();
//						         float ActualImageHeight= YCoOridinate-60;
//						         if(YCoOridinate>=150)
//						         {
//						        	
//						        	 Image FalseImage = Image.getInstance("D:/pdf report/falseimage.png");
//						        	 FalseImage.setAlignment(Element.ALIGN_CENTER);
//						        	 FalseImage.scaleToFit(500, ActualImageHeight);
//						        	 
//						        	 PdfPTable FALSEIMAGETABLE = new PdfPTable(1);
//						        	 FALSEIMAGETABLE.setSpacingAfter(6);
//						        	 FALSEIMAGETABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//						        	 FALSEIMAGETABLE.setWidthPercentage(96);
//							         
//							         
//							         
//						        	 PdfPCell FALSEIMAGETABLE_CELL = new PdfPCell();
//						        	 FALSEIMAGETABLE_CELL.setBorder(Rectangle.NO_BORDER);
//							         
//							         
//							         
//						        	 FALSEIMAGETABLE_CELL.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//						        	 FALSEIMAGETABLE_CELL.setVerticalAlignment(Element.ALIGN_MIDDLE);
//							         
//						        	 FALSEIMAGETABLE_CELL.setPaddingTop(20);
//						        	 FALSEIMAGETABLE_CELL.setPaddingBottom(0);
//						        	 FALSEIMAGETABLE_CELL.setPaddingLeft(0);
//						        	 FALSEIMAGETABLE_CELL.setPaddingRight(0);
//							        
//						        	 FALSEIMAGETABLE_CELL.addElement(FalseImage);
//							         
//						        	 FALSEIMAGETABLE.addCell(FALSEIMAGETABLE_CELL);
//							         columnTable.addElement(FALSEIMAGETABLE);
//							         columnTable.go();
//						        	 
//						        	 
//						        	 
//						        	 
//						         }
						        	
						         //----------------------END ADDING FALSE IMAGES--------------------------
				}
				
				
				
				
				//----------------------------------Start GUIDANCE SPECIALIST’S INSIGHTS------------------------------------------
				
				
				
				
				if(i==12 && checksallcommentadded)
				{
					
					
					
					// Start Observations
		        	
		        	
		        	Paragraph Observations = new Paragraph("Observations about "+Name,OpensansBold_12_White);
			         
		        	Paragraph Observations_TEXT;
		        	
		        	if(bean!=null && bean.getComments()!=null && bean.getComments().trim()!="" && bean.getComments().trim().length()<=500)
		        	{
		        		Observations_TEXT = new Paragraph(bean.getComments(),OpensansLightFont_10_White_1);
			        	
		        	}
		        	else
		        	{
		        		Observations_TEXT = new Paragraph("No Comments",OpensansLightFont_10_White_1);
		        	}
		        	
		        	Observations_TEXT.setAlignment(Element.ALIGN_JUSTIFIED);
		        	Observations_TEXT.setLeading(1, 1.2f);
		        	
		        	
		        	 ColumnText columnTable = new ColumnText(writer.getDirectContent());
			         columnTable.setSimpleColumn(100, 36, 559, 724);
			         columnTable.addElement(Observations);
			         columnTable.go();
			         
			         
			         columnTable = new ColumnText(writer.getDirectContent());
			         columnTable.setSimpleColumn(100, 36, 528, 690);
			         columnTable.addElement(Observations_TEXT);
			         columnTable.go();
			         
			         
			         
			         
			      // Start Challenges 
			         
			         
			            Paragraph Challenges = new Paragraph("Challenges that "+Name+" is facing",OpensansBold_12_White);
			         
			        	Paragraph Challenges_TEXT; 
			        	
			        	if(bean!=null && bean.getChallenges()!=null && bean.getChallenges().trim()!="")
			        	{
			        		Challenges_TEXT = new Paragraph(bean.getChallenges(),OpensansLightFont_10_White_1);
			        	}
			        	else
			        	{
			        		Challenges_TEXT = new Paragraph("No Comments",OpensansLightFont_10_White_1);
				        	
			        	}
			        	
			        	
			        	Challenges_TEXT.setAlignment(Element.ALIGN_JUSTIFIED);
			        	Challenges_TEXT.setLeading(1, 1.2f);
			        	
			        	
			        	
			        	 columnTable = new ColumnText(writer.getDirectContent());
				         columnTable.setSimpleColumn(100, 36, 559, 590);
				         columnTable.addElement(Challenges);
				         columnTable.go();
				         
				         
				         columnTable = new ColumnText(writer.getDirectContent());
				         columnTable.setSimpleColumn(100, 36, 528, 556);
				         columnTable.addElement(Challenges_TEXT);
				         columnTable.go();
				         
				         
				         
				         
				         
				         
					      // Start Approach to pathway 1
					         
					         
					            Paragraph Approachtopathway1 = new Paragraph("Approach to pathway 1",OpensansBold_12_White);
					         
					        	Paragraph Approachtopathway1_TEXT;
					        	
					        	if(bean!=null && bean.getApproachtopathway1()!=null && bean.getApproachtopathway1().trim()!="")
					        	{
					        		Approachtopathway1_TEXT= new Paragraph(bean.getApproachtopathway1(),OpensansLightFont_10_White_1);
					        	}
					        	else
					        	{
					        		Approachtopathway1_TEXT= new Paragraph("No Comments",OpensansLightFont_10_White_1);
					        	}
					        	
					        	
					        	
					        	
					        	Approachtopathway1_TEXT.setAlignment(Element.ALIGN_JUSTIFIED);
					        	Approachtopathway1_TEXT.setLeading(1, 1.2f);
					        	
					        	
					        	
					        	 columnTable = new ColumnText(writer.getDirectContent());
						         columnTable.setSimpleColumn(100, 36, 559, 459);
						         columnTable.addElement(Approachtopathway1);
						         columnTable.go();
						         
						         
						         columnTable = new ColumnText(writer.getDirectContent());
						         columnTable.setSimpleColumn(100, 36, 528, 425);
						         columnTable.addElement(Approachtopathway1_TEXT);
						         columnTable.go();
						         
						         
						         
						         
						         
						         
						         

							      // Start Approach to pathway 2
							         
							        
							            Paragraph Approachtopathway2 = new Paragraph("Approach to pathway 2",OpensansBold_12_White);
							         
							        	Paragraph Approachtopathway2_TEXT;
							        	
							        	if(bean!=null && bean.getApproachtopathway2()!=null && bean.getApproachtopathway2().trim()!="")
							        	{
							        		Approachtopathway2_TEXT= new Paragraph(bean.getApproachtopathway2(),OpensansLightFont_10_White_1);
							        	}
							        	else
							        	{
							        		Approachtopathway2_TEXT= new Paragraph("No Comments",OpensansLightFont_10_White_1);
							        	}
							        	
							        	
							        	
							        	
							        	
							        	
							        	
							        	Approachtopathway2_TEXT.setAlignment(Element.ALIGN_JUSTIFIED);
							        	Approachtopathway2_TEXT.setLeading(1, 1.2f);
							        	
							        	
							        	
							        	 columnTable = new ColumnText(writer.getDirectContent());
								         columnTable.setSimpleColumn(100, 36, 559, 323);
								         columnTable.addElement(Approachtopathway2);
								         columnTable.go();
								         
								         
								         columnTable = new ColumnText(writer.getDirectContent());
								         columnTable.setSimpleColumn(100, 36, 528, 289);
								         columnTable.addElement(Approachtopathway2_TEXT);
								         columnTable.go();
								         
								         
								         
								         
								         
								         // Start Final notes
								         
									        
								            Paragraph Finalnotes = new Paragraph("Final notes",OpensansBold_12_White);
								         
								        	Paragraph Finalnotes_TEXT;
								        	
								        	
								        	if(bean!=null && bean.getFinalnotes()!=null && bean.getFinalnotes().trim()!="")
								        	{
								        		Finalnotes_TEXT = new Paragraph(bean.getFinalnotes(),OpensansLightFont_10_White_1);
								        	}
								        	else
								        	{
								        		Finalnotes_TEXT = new Paragraph("No Comments",OpensansLightFont_10_White_1);
								        	}
								        	
								        	
								        	
								        	
								        	
								        	
								        	
								        	Finalnotes_TEXT.setAlignment(Element.ALIGN_JUSTIFIED);
								        	Finalnotes_TEXT.setLeading(1, 1.2f);
								        	
								        	
								        	
								        	 columnTable = new ColumnText(writer.getDirectContent());
									         columnTable.setSimpleColumn(100, 36, 559, 187);
									         columnTable.addElement(Finalnotes);
									         columnTable.go();
									         
									         
									         columnTable = new ColumnText(writer.getDirectContent());
									         columnTable.setSimpleColumn(100, 36, 528, 153);
									         columnTable.addElement(Finalnotes_TEXT);
									         columnTable.go();
								         
					
					
					
					
					
					
				}
				
				
				
				
				
				//----------------------------------End GUIDANCE SPECIALIST’S INSIGHTS------------------------------------------
				
				
				if(i==14)
				{
					
					PdfPTable ENTRANCE_EXAMS_TO_PREPARE_TABLE = new PdfPTable(2);
			    	ENTRANCE_EXAMS_TO_PREPARE_TABLE.setSpacingAfter(6);
			    	ENTRANCE_EXAMS_TO_PREPARE_TABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	ENTRANCE_EXAMS_TO_PREPARE_TABLE.setWidthPercentage(100);
			    	
			    	ENTRANCE_EXAMS_TO_PREPARE_TABLE.setSplitLate(false);
			    	
			    	
			    	PdfPCell ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell;
			    	Chunk EXAMS_TO_PREPARE_HEADER = new Chunk("LODESTAR CAREER GUIDANCE PROGRAM | ",OpensansSemiboldFont);
			    	
			    	Chunk EXAMS_TO_PREPARE_HEADER1 = new Chunk("SUMMARY REPORT",OpensansSemiboldFont1);
			    	
			    	ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell = new PdfPCell(new Phrase(EXAMS_TO_PREPARE_HEADER));
			        ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setBorder(Rectangle.NO_BORDER);
			      
			        
			         
			         ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			         ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setVerticalAlignment(Element.ALIGN_LEFT);
			         
			         ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingTop(10.5f);
			         ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingBottom(64);//35.8f
			         ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingLeft(61);
			         ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingRight(5);
			         ENTRANCE_EXAMS_TO_PREPARE_TABLE.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell);
			         
			         
			         
			         
			         
			          ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell = new PdfPCell(new Phrase(EXAMS_TO_PREPARE_HEADER1));
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setBorder(Rectangle.NO_BORDER);
				       
				        
				         
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingTop(10.5f);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingBottom(64);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingLeft(-81.5f);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingRight(5);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell);
			         
			    	  ENTRANCE_EXAMS_TO_PREPARE_TABLE.setHeaderRows(1);
			    	  
			    	  
			    	  Chunk SECTION_3_EDUCATION_PATHWAY = new Chunk("SECTION III: EDUCATION PATHWAY",OpensansSemiboldFont_24);
			    	  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell = new PdfPCell();
			    	  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.addElement(SECTION_3_EDUCATION_PATHWAY);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setBorder(Rectangle.NO_BORDER);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setColspan(2);
				        
				         
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingTop(-30.2f);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingBottom(45);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingLeft(61);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingRight(5);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell);
			    	  
			    	  
			    	  
				    
				      PdfPTable ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED = new PdfPTable(2);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.setSpacingAfter(6);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.setWidthPercentage(90);
				      
				      ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.setSplitRows(false);
				      
				      
				      
					  PdfPCell ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell;
				         
					  Chunk EXAMS_TO_PREPARE_HEADING = new Chunk("ENTRANCE EXAMS TO PREPARE",OpensansSemiboldFont_16);
				      
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell(new Phrase(EXAMS_TO_PREPARE_HEADING));
					    
					  BaseColor ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color = new BaseColor(102,45,145);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(2);
					    
					    
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setColspan(2);
				        
				         
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(0);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(7);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(0);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
				         
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
					  
					  
					  boolean CheckExams=true;
					 
					      
					  BaseColor Important_TABLE_CELL_COLOR_1 = new BaseColor(232,30,37);
					  BaseColor Important_TABLE_CELL_COLOR_2 = new BaseColor(102,45,145);
					  if(bean != null && bean.getIsStudent() && bean.getSection3Exams()!=null)
					  {
						  BaseColor ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB = new BaseColor(204,204,204);
						  for(EntranceExamsDTO ExamDetails:bean.getSection3Exams())
						  {
						  
							  CheckExams=false;	 
							  //=========================EXAM Name====================
							  Paragraph EXAMS_TO_PREPARE_EXAM_NAME;  
							  if(ExamDetails.getExamName()!=null)
							  {
								  EXAMS_TO_PREPARE_EXAM_NAME = new Paragraph(ExamDetails.getExamName(),OpensansSemiboldFont_12);
								  EXAMS_TO_PREPARE_EXAM_NAME.setIndentationRight(120);
							  }
							  else
							  {
								  EXAMS_TO_PREPARE_EXAM_NAME = new Paragraph("-",OpensansSemiboldFont_12);
								  EXAMS_TO_PREPARE_EXAM_NAME.setIndentationRight(120);
							  }
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(EXAMS_TO_PREPARE_EXAM_NAME);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(1.5f);
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
//								
//							  
							  
							  
							  
							  
							  Paragraph EXAMS_TO_PREPARE_EXAM_NAME_2nd;
							  
							  if(ExamDetails.getAboutExam()!=null)
							  {
								  EXAMS_TO_PREPARE_EXAM_NAME_2nd = new Paragraph(ExamDetails.getAboutExam(),OpensansSemiboldFont_12);
								  EXAMS_TO_PREPARE_EXAM_NAME_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  EXAMS_TO_PREPARE_EXAM_NAME_2nd = new Paragraph("-",OpensansSemiboldFont_12);
								  EXAMS_TO_PREPARE_EXAM_NAME_2nd.setLeading(5, 1.2f);
							  }
//							  
//							  Paragraph Important_TEXT=new Paragraph("Important",OpensansboldFont_8);
//							  Important_TEXT.setAlignment(Element.ALIGN_CENTER); 
//							  
//							  
//							    PdfPTable Important_TABLE = new PdfPTable(5);
//							    Important_TABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//							    Important_TABLE.setWidthPercentage(100);
//						    	
//							    
//							    PdfPCell Important_TABLE_cell = new PdfPCell();
//						    	Important_TABLE_cell.addElement(LeftArrow);
//						    	Important_TABLE_cell.setBorder(Rectangle.NO_BORDER);
//							    
//							    Important_TABLE_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//						    	Important_TABLE_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//						    	Important_TABLE_cell.setPaddingTop(5);
//						    	Important_TABLE_cell.setPaddingBottom(0);
//						    	Important_TABLE_cell.setPaddingLeft(63);
//						    	Important_TABLE_cell.setPaddingRight(0);
//							         
//						    	Important_TABLE.addCell(Important_TABLE_cell);
//							    
//							    
//							    Important_TABLE_cell = new PdfPCell();
//							    
//						    	Important_TABLE_cell.addElement(Important_TEXT);
//						    	Important_TABLE_cell.setBorder(Rectangle.NO_BORDER);
//							  
//						    	
//						    	Important_TABLE_cell.setBackgroundColor(Important_TABLE_CELL_COLOR_1);
//						    	Important_TABLE_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//						    	Important_TABLE_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//						    	Important_TABLE_cell.setPaddingTop(-5);
//						    	Important_TABLE_cell.setPaddingBottom(0);
//						    	Important_TABLE_cell.setPaddingLeft(0);
//						    	Important_TABLE_cell.setPaddingRight(0);
//							         
//						    	Important_TABLE.addCell(Important_TABLE_cell);
//							  
//							  
//						    	Important_TABLE_cell = new PdfPCell();
//						    	Important_TABLE_cell.addElement(EXAMS_TO_PREPARE_EXAM_NAME_2nd);
//						    	Important_TABLE_cell.setBorder(Rectangle.NO_BORDER);
//						    	Important_TABLE_cell.setColspan(3);
//						    	Important_TABLE_cell.setBackgroundColor(Important_TABLE_CELL_COLOR_2);
//						    	Important_TABLE_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//						    	Important_TABLE_cell.setVerticalAlignment(Element.ALIGN_LEFT);
//						    	Important_TABLE_cell.setPaddingTop(0);
//						    	Important_TABLE_cell.setPaddingBottom(8);
//						    	Important_TABLE_cell.setPaddingLeft(3);
//						    	Important_TABLE_cell.setPaddingRight(3);
//							         
//						    	Important_TABLE.addCell(Important_TABLE_cell);
//							  
//							  
							  
							  
							  
							  
							  
							  
							  
							  
							  
							  
							  
							  
							  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(EXAMS_TO_PREPARE_EXAM_NAME_2nd);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(1.5f);  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
							  
							  
							  
							  
							  
								  //=========================Details of the test====================
								  
							  Chunk Details_of_the_test=new Chunk("Details of the test",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Details_of_the_test);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							 // ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setw  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Details_of_the_test_2nd; 
							  if(ExamDetails.getExamName()!=null)
							  {
								  Details_of_the_test_2nd = new Paragraph(ExamDetails.getExamName(),OpensansLightFont_10);
								  Details_of_the_test_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Details_of_the_test_2nd = new Paragraph("-",OpensansLightFont_10);
								  Details_of_the_test_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Details_of_the_test_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
							  
								  
								  //=========================Scores accepted in====================
								  
							  Chunk Scores_accepted_in=new Chunk("Scores accepted in",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Scores_accepted_in);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							 // ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setw  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Scores_accepted_in_2nd; 
							  if(ExamDetails.getInsititutesAcceptScore()!=null)
							  {
								  Scores_accepted_in_2nd = new Paragraph(ExamDetails.getInsititutesAcceptScore(),OpensansLightFont_10);
								  Scores_accepted_in_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Scores_accepted_in_2nd = new Paragraph("-",OpensansLightFont_10);
								  Scores_accepted_in_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Scores_accepted_in_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								  
								  //=========================Eligibility====================
								  
							  Chunk Eligibility=new Chunk("Eligibility",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Eligibility);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							 // ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setw  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Eligibility_2nd; 
							  if(ExamDetails.getEligibility()!=null)
							  {
								  Eligibility_2nd = new Paragraph(ExamDetails.getEligibility(),OpensansLightFont_10);
								  Eligibility_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Eligibility_2nd = new Paragraph("-",OpensansLightFont_10);
								  Eligibility_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Eligibility_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								  
								  //=========================Examination Month====================
								  
							  Chunk Examination_Month=new Chunk("Examination Month",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Examination_Month);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							 // ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setw  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Examination_Month_2nd; 
							  if(ExamDetails.getMonthOfExam()!=null)
							  {
								  Examination_Month_2nd = new Paragraph(ExamDetails.getMonthOfExam(),OpensansLightFont_10);
								  Examination_Month_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Examination_Month_2nd = new Paragraph("-",OpensansLightFont_10);
								  Examination_Month_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Examination_Month_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								  
								  //=========================Subjects and marks details====================
								  
							  
							  Chunk Subjects_and_marks_details=new Chunk("Subjects and marks details",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Subjects_and_marks_details);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Subjects_and_marks_details_2nd; 
							  if(ExamDetails.getSubNMarks()!=null)
							  {
								  Subjects_and_marks_details_2nd = new Paragraph(ExamDetails.getSubNMarks(),OpensansLightFont_10);
								  Subjects_and_marks_details_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Subjects_and_marks_details_2nd = new Paragraph("-",OpensansLightFont_10);
								  Subjects_and_marks_details_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Subjects_and_marks_details_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
							  
							  
								//=========================No. of seats====================
							  
							  
							  Chunk No_of_seats=new Chunk("No. of seats",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(No_of_seats);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph No_of_seats_2nd; 
							  if(ExamDetails.getNoOfSeats()!=null)
							  {
								  No_of_seats_2nd = new Paragraph(Float.toString(ExamDetails.getNoOfSeats()),OpensansLightFont_10);
								  No_of_seats_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  No_of_seats_2nd = new Paragraph("-",OpensansLightFont_10);
								  No_of_seats_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(No_of_seats_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
							  
							  
							  
							  
							  
						  }
					  }
					  
					
					  
					  
					  
					  if(bean != null && bean.getIsStudent() && bean.getSection3Courses()!=null)
					  {
						  BaseColor ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB = new BaseColor(204,204,204);
						  if(bean.getSection3Courses().size()>0)
						  {
						//======================Special courses=========================
						  Chunk SPECIAL_COURSES = new Chunk("SPECIAL COURSES",OpensansSemiboldFont_16);
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
						    
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(SPECIAL_COURSES);
						  
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setColspan(2);
					         
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(25);
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(8);
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
					         
						  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
						  }
						  //========================Special Course from DB==================================
						  
						  
						  
						  for(IntegratedCourseDTO CourseDetails:bean.getSection3Courses())
						  {
							  CheckExams=false;
								 
							  //=========================Course Name====================
							  Paragraph EXAMS_TO_PREPARE_EXAM_NAME;  
							  if(CourseDetails.getProgramName()!=null)
							  {
								  EXAMS_TO_PREPARE_EXAM_NAME = new Paragraph(CourseDetails.getProgramName(),OpensansSemiboldFont_12);
								  EXAMS_TO_PREPARE_EXAM_NAME.setIndentationRight(120);
							  }
							  else
							  {
								  EXAMS_TO_PREPARE_EXAM_NAME = new Paragraph("-",OpensansSemiboldFont_12);
								  EXAMS_TO_PREPARE_EXAM_NAME.setIndentationRight(120);
							  }
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(EXAMS_TO_PREPARE_EXAM_NAME);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(1.5f);
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph EXAMS_TO_PREPARE_EXAM_NAME_2nd;
							  
//							  if(CourseDetails.getDescription()!=null)
//							  {
//								  EXAMS_TO_PREPARE_EXAM_NAME_2nd = new Paragraph(CourseDetails.getDescription(),OpensansSemiboldFont_12);
//								  EXAMS_TO_PREPARE_EXAM_NAME_2nd.setLeading(5, 1.2f);
//							  }
//							  else
//							  {
								  EXAMS_TO_PREPARE_EXAM_NAME_2nd = new Paragraph("--",OpensansSemiboldFont_12);
								  EXAMS_TO_PREPARE_EXAM_NAME_2nd.setLeading(5, 1.2f);
//							  }
							  
								  
								  
//								  Paragraph Important_TEXT=new Paragraph("Important",OpensansboldFont_8);
//								  Important_TEXT.setAlignment(Element.ALIGN_CENTER); 
//								  
//								  
//								    PdfPTable Important_TABLE = new PdfPTable(5);
//								    Important_TABLE.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//								    Important_TABLE.setWidthPercentage(100);
//							    	
//								    
//								    PdfPCell Important_TABLE_cell = new PdfPCell();
//							    	Important_TABLE_cell.addElement(LeftArrow);
//							    	Important_TABLE_cell.setBorder(Rectangle.NO_BORDER);
//								    
//								    Important_TABLE_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//							    	Important_TABLE_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//							    	Important_TABLE_cell.setPaddingTop(5);
//							    	Important_TABLE_cell.setPaddingBottom(0);
//							    	Important_TABLE_cell.setPaddingLeft(63);
//							    	Important_TABLE_cell.setPaddingRight(0);
//								         
//							    	Important_TABLE.addCell(Important_TABLE_cell);
//								    
//								    
//								    Important_TABLE_cell = new PdfPCell();
//								    
//							    	Important_TABLE_cell.addElement(Important_TEXT);
//							    	Important_TABLE_cell.setBorder(Rectangle.NO_BORDER);
//								  
//							    	
//							    	Important_TABLE_cell.setBackgroundColor(Important_TABLE_CELL_COLOR_1);
//							    	Important_TABLE_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//							    	Important_TABLE_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//							    	Important_TABLE_cell.setPaddingTop(-5);
//							    	Important_TABLE_cell.setPaddingBottom(0);
//							    	Important_TABLE_cell.setPaddingLeft(0);
//							    	Important_TABLE_cell.setPaddingRight(0);
//								         
//							    	Important_TABLE.addCell(Important_TABLE_cell);
//								  
//								  
//							    	Important_TABLE_cell = new PdfPCell();
//							    	Important_TABLE_cell.addElement(EXAMS_TO_PREPARE_EXAM_NAME_2nd);
//							    	Important_TABLE_cell.setBorder(Rectangle.NO_BORDER);
//							    	Important_TABLE_cell.setColspan(3);
//							    	Important_TABLE_cell.setBackgroundColor(Important_TABLE_CELL_COLOR_2);
//							    	Important_TABLE_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//							    	Important_TABLE_cell.setVerticalAlignment(Element.ALIGN_LEFT);
//							    	Important_TABLE_cell.setPaddingTop(0);
//							    	Important_TABLE_cell.setPaddingBottom(8);
//							    	Important_TABLE_cell.setPaddingLeft(3);
//							    	Important_TABLE_cell.setPaddingRight(3);
//								         
//							    	Important_TABLE.addCell(Important_TABLE_cell);
//								  
//								  
								  
								  
								  
								  
								  
								  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(EXAMS_TO_PREPARE_EXAM_NAME_2nd);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
//							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(1.5f);  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
							  
							  
							  
							  
							  
								  //=========================Location====================
								  
							  Chunk Details_of_the_test=new Chunk("Location",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Details_of_the_test);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							 // ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setw  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Details_of_the_test_2nd; 
							  if(CourseDetails.getLocation()!=null)
							  {
								  Details_of_the_test_2nd = new Paragraph(CourseDetails.getLocation(),OpensansLightFont_10);
								  Details_of_the_test_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Details_of_the_test_2nd = new Paragraph("-",OpensansLightFont_10);
								  Details_of_the_test_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Details_of_the_test_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
							  
								  
								  //=========================Institute====================
								  
							  Chunk Scores_accepted_in=new Chunk("Institute",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Scores_accepted_in);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							 // ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setw  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Scores_accepted_in_2nd; 
							  if(CourseDetails.getInstitute()!=null)
							  {
								  Scores_accepted_in_2nd = new Paragraph(CourseDetails.getInstitute(),OpensansLightFont_10);
								  Scores_accepted_in_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Scores_accepted_in_2nd = new Paragraph("-",OpensansLightFont_10);
								  Scores_accepted_in_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Scores_accepted_in_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								  
								  //=========================Description====================
								  
							  Chunk Eligibility=new Chunk("Description",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Eligibility);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							 // ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setw  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Eligibility_2nd; 
							  if(CourseDetails.getDescription()!=null)
							  {
								  Eligibility_2nd = new Paragraph(CourseDetails.getDescription(),OpensansLightFont_10);
								  Eligibility_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Eligibility_2nd = new Paragraph("-",OpensansLightFont_10);
								  Eligibility_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Eligibility_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								  
								  //=========================Duration====================
								  
							  Chunk Examination_Month=new Chunk("Duration",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Examination_Month);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							 // ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setw  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Examination_Month_2nd; 
							  if(CourseDetails.getCourseDuration()!=null)
							  {
								  Examination_Month_2nd = new Paragraph(CourseDetails.getCourseDuration(),OpensansLightFont_10);
								  Examination_Month_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Examination_Month_2nd = new Paragraph("-",OpensansLightFont_10);
								  Examination_Month_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Examination_Month_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								  
								  //=========================Eligibility====================
								  
							  
							  Chunk Subjects_and_marks_details=new Chunk("Eligibility",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Subjects_and_marks_details);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Subjects_and_marks_details_2nd; 
							  if(CourseDetails.getEligibility()!=null)
							  {
								  Subjects_and_marks_details_2nd = new Paragraph(CourseDetails.getEligibility(),OpensansLightFont_10);
								  Subjects_and_marks_details_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Subjects_and_marks_details_2nd = new Paragraph("-",OpensansLightFont_10);
								  Subjects_and_marks_details_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Subjects_and_marks_details_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
							  
							  
								//=========================Selection Process====================
							  
							  
							  Chunk No_of_seats=new Chunk("Selection Process",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(No_of_seats);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph No_of_seats_2nd; 
							  if(CourseDetails.getSelectionProcess()!=null)
							  {
								  No_of_seats_2nd = new Paragraph(CourseDetails.getSelectionProcess(),OpensansLightFont_10);
								  No_of_seats_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  No_of_seats_2nd = new Paragraph("-",OpensansLightFont_10);
								  No_of_seats_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(No_of_seats_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
							  
							  
							  
							  
							  //=========================Fees====================
							  
							  
							  Chunk Fees=new Chunk("Fees",OpensansSemiboldFont_10);
							  
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Fees);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							  
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(3);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
								
							  
							  
							  
							  
							  
							  Paragraph Fees_2nd; 
							  if(CourseDetails.getFeeStructure()!=null)
							  {
								  Fees_2nd = new Paragraph(CourseDetails.getFeeStructure(),OpensansLightFont_10);
								  Fees_2nd.setLeading(5, 1.2f);
							  }
							  else
							  {
								  Fees_2nd = new Paragraph("-",OpensansLightFont_10);
								  Fees_2nd.setLeading(5, 1.2f);
							  }
							  
								  
						      
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
							    
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Fees_2nd);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderColor(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell_Color_DB);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorderWidth(0.5f);
							    
							    
							  
						        
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(5);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(10);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(-80);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
							  
							  
						  }
					  }
					  
					  
					  
					  if(CheckExams)
					  {
						  	Paragraph Last_Message1 = new Paragraph("You have not selected any exams.",OpensansSemiboldFont_14);
					    	 Last_Message1.setAlignment(Element.ALIGN_CENTER);
					    	 ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell = new PdfPCell();
					    	 ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.addElement(Last_Message1);
					    	 ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
							   
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_CENTER);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingTop(20);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingBottom(0);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingLeft(0);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setPaddingRight(0);
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell.setColspan(2);
						         
							  ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED_cell);
					  }
					  
					  
			    	  
			    	  
			    	  //===========================Final Table========================
					  
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell = new PdfPCell();
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setBorder(Rectangle.NO_BORDER);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setColspan(2);
					        
					         
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setVerticalAlignment(Element.ALIGN_CENTER);
					         
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingTop(0);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingBottom(0);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingLeft(64);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.setPaddingRight(0);
					     
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell.addElement(ENTRANCE_EXAMS_TO_PREPARE_TABLE_NESTED);
					  ENTRANCE_EXAMS_TO_PREPARE_TABLE.addCell(ENTRANCE_EXAMS_TO_PREPARE_TABLE_cell);
					  
					  
					  
					  
					  document.add(ENTRANCE_EXAMS_TO_PREPARE_TABLE);
					
				}
				
				
				
				
				
				if(i==15)
				{
					
					boolean checkdatapresent=true;
					
					if(bean != null && bean.getIsStudent() && bean.getSection3Colleges()!=null)
					{
						
						for(int j=0;j<bean.getSection3Colleges().size();j++)
						{
							checkdatapresent=false;
							StudentCollegeShortListDTO ColleagDetailsDB_1=bean.getSection3Colleges().get(j);
							j++;
							StudentCollegeShortListDTO ColleagDetailsDB_2=null;
							if(j<bean.getSection3Colleges().size())
							{
								 ColleagDetailsDB_2=bean.getSection3Colleges().get(j);
							}
							
					   PdfPTable SCHOOL_COLLEGE_DETAILS_TABLE_NESTED= new PdfPTable(3);
			    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setSpacingAfter(6);
			    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setHorizontalAlignment(Element.ALIGN_CENTER);
			    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setWidthPercentage(80);
			    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setSplitRows(false);
			    	   PdfPCell SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell;
			    	   
			    	 
			    	   
			    	   
			    	   
			    	   
			    	    Chunk SCHOOL_COLLEGE_DETAILS_HEADER = new Chunk("LODESTAR CAREER GUIDANCE PROGRAM | ",OpensansSemiboldFont);
				    	
				    	Chunk SCHOOL_COLLEGE_DETAILS_HEADER1 = new Chunk("SUMMARY REPORT",OpensansSemiboldFont1);
				    	
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell(new Phrase(SCHOOL_COLLEGE_DETAILS_HEADER));
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	
				        
				         
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(10.5f);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(50);//35.8f
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(0);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(5);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
				         
				         
				         
				         
				         
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell(new Phrase(SCHOOL_COLLEGE_DETAILS_HEADER1));
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setColspan(2); 
					        
					         
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(10.5f);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(65);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(-4);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(5);
				    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
				         
				         
				         
				         
				    	
			        	
			        	
				    	
				    	
				    	 Chunk SCHOOL_COLLEGE_DETAILS = new Chunk("SCHOOL / COLLEGE DETAILS",OpensansSemiboldFont_24);
					    	
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(SCHOOL_COLLEGE_DETAILS);
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setColspan(3);
					        
					         
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(-29.2f);
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(20);
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(0);
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(5);
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
				    	 
				    	 
				    	 
				    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setHeaderRows(3);
			    	   
			    	   
			    	   
			    	   
			    	   
			    	   
			    	 
			    	 BaseColor TABLE_HEADER_COLOR = new BaseColor(102,45,145);
			    	 BaseColor ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB = new BaseColor(204,204,204);
			    	 
			    	 Chunk Column_1_TEXT;
			    	 Paragraph COlumn_2_TEXT;
			    	 Paragraph COlumn_3_TEXT;
			    	 
			    	 
			    	 
//			    		   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED = new PdfPTable(3);
//				    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setSpacingAfter(6);
//				    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//				    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setWidthPercentage(90);
//				    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setSplitRows(false);
			    	 //=====table header 1======================
			    	 
			    	 
			    	 
			    	 Chunk College = new Chunk("College",OpensansSemiboldFont_10_white);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(College);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBackgroundColor(TABLE_HEADER_COLOR);
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 //=====table header 2======================
			    	 Paragraph College_Name_1;
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getCollegeName()!=null)
			    	 {
			    		 College_Name_1 = new Paragraph(ColleagDetailsDB_1.getCollegeName(),OpensansSemiboldFont_10_white);
				    	 College_Name_1.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 College_Name_1 = new Paragraph("",OpensansSemiboldFont_10_white);
				    	 College_Name_1.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(College_Name_1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBackgroundColor(TABLE_HEADER_COLOR);
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	 //=====table header 3======================
			    	 
			    	 
			    	 
			    	 Paragraph College_Name_2;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getCollegeName()!=null)
			    	 {
			    		 College_Name_2= new Paragraph(ColleagDetailsDB_2.getCollegeName(),OpensansSemiboldFont_10_white);
				    	 College_Name_2.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 College_Name_2= new Paragraph("",OpensansSemiboldFont_10_white);
				    	 College_Name_2.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(College_Name_2);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBackgroundColor(TABLE_HEADER_COLOR);
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	
			    	 
			    	 
			    	 
			    	 
			    	 //=======================Location========================================
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 Chunk Location_TEXT = new Chunk("Location",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Location_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 Paragraph Location_TEXT_COlumn_2;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getLocation()!=null)
			    	 {
			    		 Location_TEXT_COlumn_2 = new Paragraph(ColleagDetailsDB_1.getLocation(),OpensansLightFont_10_White);
				    	 Location_TEXT_COlumn_2.setAlignment(Element.ALIGN_CENTER);
			    		 
			    	 }
			    	 else
			    	 {
			    		 Location_TEXT_COlumn_2 = new Paragraph("--",OpensansLightFont_10_White);
				    	 Location_TEXT_COlumn_2.setAlignment(Element.ALIGN_CENTER);
			    		 
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Location_TEXT_COlumn_2);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 Paragraph Location_TEXT_COlumn_3;
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getLocation()!=null)
			    	 {
			    		 Location_TEXT_COlumn_3 = new Paragraph(ColleagDetailsDB_2.getLocation(),OpensansLightFont_10_White);
				    	 Location_TEXT_COlumn_3.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Location_TEXT_COlumn_3 = new Paragraph("--",OpensansLightFont_10_White);
				    	 Location_TEXT_COlumn_3.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Location_TEXT_COlumn_3);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	//=======================Address========================================
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Address",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 Paragraph Address_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getCollegeAddress()!=null)
			    	 {
			    		 Address_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getCollegeAddress(),OpensansLightFont_10_White);
			    		 Address_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Address_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Address_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Address_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 Paragraph Address_COlumn_3_TEXT;
			    	 
			    	 
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getCollegeAddress()!=null)
			    	 {
			    		 Address_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getCollegeAddress(),OpensansLightFont_10_White);
			    		 Address_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Address_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Address_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Address_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	//=======================Website========================================
			    	 
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Website",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 Paragraph Website_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getWebsite()!=null)
			    	 {
			    		 Website_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getWebsite(),OpensansLightFont_10_White);
			    		 Website_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Website_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Website_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Website_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 Paragraph Website_COlumn_3_TEXT;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getWebsite()!=null)
			    	 {
			    		 Website_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getWebsite(),OpensansLightFont_10_White);
			    		 Website_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Website_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Website_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Website_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	//=======================Age of Institute========================================
			    	 
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Age of Institute",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 Paragraph AgeofInstitute_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getAgeofInstitute()!=null)
			    	 {
			    		 AgeofInstitute_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getAgeofInstitute(),OpensansLightFont_10_White);
			    		 AgeofInstitute_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 AgeofInstitute_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 AgeofInstitute_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(AgeofInstitute_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 Paragraph AgeofInstitute_COlumn_3_TEXT;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getAgeofInstitute()!=null)
			    	 {
			    		 AgeofInstitute_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getAgeofInstitute(),OpensansLightFont_10_White);
			    		 AgeofInstitute_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 AgeofInstitute_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 AgeofInstitute_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(AgeofInstitute_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	//=======================College type========================================
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("College type",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 Paragraph Collegetype_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getCollegetype()!=null)
			    	 {
			    		 Collegetype_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getCollegetype(),OpensansLightFont_10_White);
			    		 Collegetype_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Collegetype_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Collegetype_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Collegetype_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 Paragraph Collegetype_COlumn_3_TEXT;
			    	 
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getCollegetype()!=null)
			    	 {
			    		 Collegetype_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getCollegetype(),OpensansLightFont_10_White);
			    		 Collegetype_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Collegetype_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Collegetype_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Collegetype_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	//=======================Application Cost========================================
			    	 
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Application Cost",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 Paragraph ApplicationCost_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getApplicationCost()!=null)
			    	 {
			    		 ApplicationCost_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getApplicationCost(),OpensansLightFont_10_White);
			    		 ApplicationCost_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 ApplicationCost_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 ApplicationCost_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(ApplicationCost_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 Paragraph ApplicationCost_COlumn_3_TEXT;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getApplicationCost()!=null)
			    	 {
			    		 ApplicationCost_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getApplicationCost(),OpensansLightFont_10_White);
			    		 ApplicationCost_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 ApplicationCost_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 ApplicationCost_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(ApplicationCost_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	//=======================Application last date========================================
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Application last date",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 Paragraph Applicatiolastdate_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getApplicationlastdate()!=null)
			    	 {
			    		 Applicatiolastdate_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getApplicationlastdate(),OpensansLightFont_10_White);
			    		 Applicatiolastdate_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Applicatiolastdate_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Applicatiolastdate_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Applicatiolastdate_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 
			    	 Paragraph Applicatiolastdate_COlumn_3_TEXT;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getApplicationlastdate()!=null)
			    	 {
			    		 Applicatiolastdate_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getApplicationlastdate(),OpensansLightFont_10_White);
			    		 Applicatiolastdate_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Applicatiolastdate_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Applicatiolastdate_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Applicatiolastdate_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	 
			    	//=======================Admission application form submitted adress========================================
			    	 
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Admission application form submitted adress",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 Paragraph Admissionapplicationformsubmittedadress_COlumn_2_TEXT;
			    	 
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getAdmissionapplicationform()!=null)
			    	 {
			    		 Admissionapplicationformsubmittedadress_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getAdmissionapplicationform(),OpensansLightFont_10_White);
			    		 Admissionapplicationformsubmittedadress_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Admissionapplicationformsubmittedadress_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Admissionapplicationformsubmittedadress_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Admissionapplicationformsubmittedadress_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 Paragraph Admissionapplicationformsubmittedadress_COlumn_3_TEXT;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getAdmissionapplicationform()!=null)
			    	 {
			    		 Admissionapplicationformsubmittedadress_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getAdmissionapplicationform(),OpensansLightFont_10_White);
			    		 Admissionapplicationformsubmittedadress_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Admissionapplicationformsubmittedadress_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Admissionapplicationformsubmittedadress_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Admissionapplicationformsubmittedadress_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	//=======================Stream Available========================================
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Stream Available",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 Paragraph StreamAvailable_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getStreamAvailable()!=null)
			    	 {
			    		 StreamAvailable_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getStreamAvailable(),OpensansLightFont_10_White);
			    		 StreamAvailable_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 StreamAvailable_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 StreamAvailable_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(StreamAvailable_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 Paragraph StreamAvailable_COlumn_3_TEXT;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getStreamAvailable()!=null)
			    	 {
			    		 StreamAvailable_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getStreamAvailable(),OpensansLightFont_10_White);
			    		 StreamAvailable_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 StreamAvailable_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 StreamAvailable_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(StreamAvailable_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	//=======================Course available========================================
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Course available",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 
			    	 Paragraph Courseavailable_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getCourseavailable()!=null)
			    	 {
			    		 Courseavailable_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getCourseavailable(),OpensansLightFont_10_White);
			    		 Courseavailable_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Courseavailable_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Courseavailable_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Courseavailable_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 Paragraph Courseavailable_COlumn_3_TEXT;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getCourseavailable()!=null)
			    	 {
			    		 Courseavailable_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getCourseavailable(),OpensansLightFont_10_White);
			    		 Courseavailable_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Courseavailable_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Courseavailable_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Courseavailable_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	//=======================Stream Selected========================================
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Stream Selected",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 
			    	 Paragraph SelectedCourse_COlumn_2_TEXT;
			    	 
			    	 if(SUBJECT_CHOICE_TEXT_DB!=null && SUBJECT_CHOICE_TEXT_DB.trim()!="")
			    	 {
			    		 SelectedCourse_COlumn_2_TEXT = new Paragraph(SUBJECT_CHOICE_TEXT_DB,OpensansLightFont_10_White);
			    		 SelectedCourse_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 SelectedCourse_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 SelectedCourse_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(SelectedCourse_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	
			    	 Paragraph SelectedCourse_COlumn_3_TEXT;
			    	 if(SUBJECT_CHOICE_TEXT_DB!=null && SUBJECT_CHOICE_TEXT_DB.trim()!="")
			    	 {

			    		 SelectedCourse_COlumn_3_TEXT = new Paragraph(SUBJECT_CHOICE_TEXT_DB,OpensansLightFont_10_White);
			    		 SelectedCourse_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {

			    		 SelectedCourse_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 SelectedCourse_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(SelectedCourse_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	//=======================Selected Course========================================
			    	 
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Selected Course",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 
			    	 
			    	 Paragraph StreamSelected_COlumn_2_TEXT;
			    	 
			    	 if(SUBJECT_CHOICE_COMBINATION_TEXT_DB!=null && SUBJECT_CHOICE_COMBINATION_TEXT_DB.trim()!="")
			    	 {
			    		 StreamSelected_COlumn_2_TEXT = new Paragraph(SUBJECT_CHOICE_COMBINATION_TEXT_DB,OpensansLightFont_10_White);
			    		 StreamSelected_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 StreamSelected_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 StreamSelected_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(StreamSelected_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	
			    	 
			    	 Paragraph StreamSelected_COlumn_3_TEXT;
			    	 
			    	 
			    	 if(SUBJECT_CHOICE_COMBINATION_TEXT_DB!=null && SUBJECT_CHOICE_COMBINATION_TEXT_DB.trim()!="")
			    	 {
			    		 StreamSelected_COlumn_3_TEXT = new Paragraph(SUBJECT_CHOICE_COMBINATION_TEXT_DB,OpensansLightFont_10_White);
			    		 StreamSelected_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 StreamSelected_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 StreamSelected_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(StreamSelected_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	//=======================Cateogory========================================
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Cateogory",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 COlumn_2_TEXT = new Paragraph("Gen",OpensansLightFont_10_White);
			    	 COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 
			    	 COlumn_3_TEXT = new Paragraph("Gen",OpensansLightFont_10_White);
			    	 COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	
			    	 
			    	 
			    	//=======================Seats========================================
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Seats",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 Paragraph Seats_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getSeats()!=null)
			    	 {
			    		 Seats_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getSeats(),OpensansLightFont_10_White);
			    		 Seats_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Seats_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Seats_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Seats_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 Paragraph Seats_COlumn_3_TEXT;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getSeats()!=null)
			    	 {
			    		 Seats_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getSeats(),OpensansLightFont_10_White);
			    		 Seats_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Seats_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Seats_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Seats_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 
			    	//=======================Cut off(%)========================================
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Cut off(%)",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getCut()!=null)
			    	 {
			    		 COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getCut(),OpensansLightFont_10_White);
				    	 COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
				    	 COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getCut()!=null)
			    	 {
			    		 COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getCut(),OpensansLightFont_10_White);
				    	 COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
				    	 COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	//=======================Pass(%)========================================
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Pass(%)",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getPass()!=null)
			    	 {
			    		 COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getPass(),OpensansLightFont_10_White);
				    	 COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
				    	 COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getPass()!=null)
			    	 {
			    		 COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getPass(),OpensansLightFont_10_White);
				    	 COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
				    	 COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	//=======================Fee========================================
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Fee",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 Paragraph Fee_COlumn_2_TEXT;
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getFee()!=null)
			    	 {
			    		 Fee_COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getFee(),OpensansLightFont_10_White);
			    		 Fee_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Fee_COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Fee_COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Fee_COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 Paragraph Fee_COlumn_3_TEXT;
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getFee()!=null)
			    	 {
			    		 Fee_COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getFee(),OpensansLightFont_10_White);
			    		 Fee_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 Fee_COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
			    		 Fee_COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Fee_COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	//=======================Teaching Staff========================================
			    	 
			    	 
			    	 //======================Column 1========================================
			    	 
			    	 
			    	 Column_1_TEXT = new Chunk("Teaching Staff",OpensansLightFont_10_White);
				    	
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Column_1_TEXT);

			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //=====================Column 2=============================================
			    	 
			    	 
			    	 if(ColleagDetailsDB_1!=null && ColleagDetailsDB_1.getTeachingStaff()!=null)
			    	 {
			    		 COlumn_2_TEXT = new Paragraph(ColleagDetailsDB_1.getTeachingStaff(),OpensansLightFont_10_White);
				    	 COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 COlumn_2_TEXT = new Paragraph("--",OpensansLightFont_10_White);
				    	 COlumn_2_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(COlumn_2_TEXT);

			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
			    	 
			    	 
			    	 
			    	 //==========================Column 3==========================================
			    	 
			    	 
			    	 
			    	 if(ColleagDetailsDB_2!=null && ColleagDetailsDB_2.getTeachingStaff()!=null)
			    	 {
			    		 COlumn_3_TEXT = new Paragraph(ColleagDetailsDB_2.getTeachingStaff(),OpensansLightFont_10_White);
				    	 COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 else
			    	 {
			    		 COlumn_3_TEXT = new Paragraph("--",OpensansLightFont_10_White);
				    	 COlumn_3_TEXT.setAlignment(Element.ALIGN_CENTER);
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(COlumn_3_TEXT);
			    	 
			    	 
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderColor(ESCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell_Color_DB);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorderWidth(0.25f);
			    	 
				        
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				         
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(8);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(4);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(1);
			    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
					
					
					
					
					
					
			    	 document.add(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED);
			    	 document.newPage();
					
						}
					
					}
					if(checkdatapresent)
					{
						
						//System.out.println("================here============================");
						
						
						  PdfPTable SCHOOL_COLLEGE_DETAILS_TABLE_NESTED= new PdfPTable(3);
				    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setSpacingAfter(6);
				    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setHorizontalAlignment(Element.ALIGN_CENTER);
				    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setWidthPercentage(80);
				    	   SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.setSplitRows(false);
				    	   PdfPCell SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell;
				    	   
				    	 
				    	   
				    	   
				    	   
				    	   
				    	    Chunk SCHOOL_COLLEGE_DETAILS_HEADER = new Chunk("LODESTAR CAREER GUIDANCE PROGRAM | ",OpensansSemiboldFont);
					    	
					    	Chunk SCHOOL_COLLEGE_DETAILS_HEADER1 = new Chunk("SUMMARY REPORT",OpensansSemiboldFont1);
					    	
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell(new Phrase(SCHOOL_COLLEGE_DETAILS_HEADER));
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
					    	
					        
					         
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(10.5f);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(50);//35.8f
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(0);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(5);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
					         
					         
					         
					         
					         
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell(new Phrase(SCHOOL_COLLEGE_DETAILS_HEADER1));
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setColspan(2); 
						        
						         
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(10.5f);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(65);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(-4);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(5);
					    	SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
					         
					         
					         
					         
					    	
				        	
				        	
					    	
					    	
					    	 Chunk SCHOOL_COLLEGE_DETAILS = new Chunk("SCHOOL / COLLEGE DETAILS",OpensansSemiboldFont_24);
						    	
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(SCHOOL_COLLEGE_DETAILS);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setColspan(3);
						        
						         
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(-29.2f);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(20);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(0);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(5);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
					    	 
					    	 
					    	 
					    	 Paragraph Last_Message1 = new Paragraph("You have not selected any colleges.",OpensansSemiboldFont_14);
					    	 Last_Message1.setAlignment(Element.ALIGN_CENTER);
						    	
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell = new PdfPCell();
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.addElement(Last_Message1);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setColspan(3);
						        
						         
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingTop(10);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingBottom(20);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingLeft(0);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell.setPaddingRight(0);
					    	 SCHOOL_COLLEGE_DETAILS_TABLE_NESTED.addCell(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED_cell);
					    	 
					    	 
					    	 
					    	 
					    	 
					    	 document.add(SCHOOL_COLLEGE_DETAILS_TABLE_NESTED);
					}
					
					
					
					
					
					
					
					
					
				}
				
				
				
				
				if(i==16)
				{
					
					boolean CheckTutorial=true;
					
					if(bean!=null && bean.getSection3Tutorials()!=null)
					{
					   PdfPTable TUTORIAL_CENTRES_TABLE_NESTED= new PdfPTable(5);
		        	   TUTORIAL_CENTRES_TABLE_NESTED.setSpacingAfter(6);
		        	   TUTORIAL_CENTRES_TABLE_NESTED.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	   TUTORIAL_CENTRES_TABLE_NESTED.setWidthPercentage(85);
		        	   TUTORIAL_CENTRES_TABLE_NESTED.setSplitRows(false);
			    	   PdfPCell TUTORIAL_CENTRES_TABLE_NESTED_cell;
			    	   
			    	 
			    	   
			    	   
			    	   
			    	   
			    	    Chunk TUTORIAL_CENTRES_HEADER = new Chunk("LODESTAR CAREER GUIDANCE PROGRAM | ",OpensansSemiboldFont);
				    	
				    	Chunk TUTORIAL_CENTRES_HEADER1 = new Chunk("SUMMARY REPORT",OpensansSemiboldFont1);
				    	
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell(new Phrase(TUTORIAL_CENTRES_HEADER));
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setColspan(2);
				        
				         
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
				         
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(10.5f);
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(50);//35.8f
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(0);
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(5);
				    	TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				         
				         
				         
				         
				         
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell(new Phrase(TUTORIAL_CENTRES_HEADER1));
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setColspan(3); 
					        
					         
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(10.5f);
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(65);
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(-48);
				    	TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(5);
				    	TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				         
				         
				         
				         
				    	 BaseColor TABLE_HEADER_COLOR = new BaseColor(102,45,145);
				    	 BaseColor TUTORIAL_CENTRES_TABLE_NESTED_cell_Color_DB = new BaseColor(204,204,204);
			        	
			        	
				    	
				    	
				    	 Chunk TUTORIAL_CENTRES = new Chunk("TUTORIAL CENTRES",OpensansSemiboldFont_24);
					    	
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(TUTORIAL_CENTRES);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setColspan(5);
					        
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(-29.2f);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(20);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(0);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(5);
				    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				    	 
				    	 
				    	 
				    	 
				    	 
				    	 Chunk Institute = new Chunk("Institute",OpensansSemiboldFont_10_white);
					    	
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Institute);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBackgroundColor(TABLE_HEADER_COLOR);
					        
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				    	 
				    	 
				    	 
				    	 
				    	 
				    	 Chunk Center = new Chunk("Center",OpensansSemiboldFont_10_white);
					    	
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Center);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBackgroundColor(TABLE_HEADER_COLOR);
					        
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				    	 
				    	 
				    	 
				    	 
				    	 Chunk Address = new Chunk("Address",OpensansSemiboldFont_10_white);
					    	
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Address);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBackgroundColor(TABLE_HEADER_COLOR);
					        
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				    	 
				    	 
				    	 
				    	 Chunk Contact_number = new Chunk("Contact number",OpensansSemiboldFont_10_white);
					    	
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Contact_number);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBackgroundColor(TABLE_HEADER_COLOR);
					        
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				    	 
				    	 
				    	 Chunk Time_slots = new Chunk("Time slots",OpensansSemiboldFont_10_white);
					    	
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Time_slots);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBackgroundColor(TABLE_HEADER_COLOR);
					        
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
					         
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
				    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				    	 
				    	 
				    	 
				    	 TUTORIAL_CENTRES_TABLE_NESTED.setHeaderRows(3);
			    	 
				    	 Paragraph Institute_TEXT;
				    	 Paragraph Center_TEXT;
				    	 Paragraph Address_TEXT;
				    	 Paragraph Contact_number_TEXT;
				    	 Paragraph Time_slots_TEXT;
				    	 
				    	 for(StudentTutorialCentreShortListDTO TutorialDetails:bean.getSection3Tutorials())
				    	 {
				    		 CheckTutorial=false;
				    		 
				    		 if(TutorialDetails.getTutorialName()!=null)
				    		 {
				    			 Institute_TEXT = new Paragraph(TutorialDetails.getTutorialName(),OpensansLightFont_10_White);
				    		 }
				    		 else
				    		 {
				    			 Institute_TEXT = new Paragraph("-",OpensansLightFont_10_White);
				    			 Institute_TEXT.setAlignment(Element.ALIGN_CENTER);
				    		 }
				    		 
						    	
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Institute_TEXT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderColor(TUTORIAL_CENTRES_TABLE_NESTED_cell_Color_DB);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderWidth(0.25f);
						        
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
					    	 
					    	 
					    	 
					    	 if(TutorialDetails.getLocality()!=null)
					    	 {
					    		 Center_TEXT = new Paragraph(TutorialDetails.getLocality(),OpensansLightFont_10_White);
					    	 }
					    	 else
					    	 {
					    		 Center_TEXT = new Paragraph("-",OpensansLightFont_10_White);
					    		 Center_TEXT.setAlignment(Element.ALIGN_CENTER);
					    	 }
					    	 
					    	 
						    	
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Center_TEXT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderColor(TUTORIAL_CENTRES_TABLE_NESTED_cell_Color_DB);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderWidth(0.25f);
						        
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
					    	 
					    	 
					    	 if(TutorialDetails.getTutorialCenterAddress()!=null)
					    	 {
					    		 Address_TEXT = new Paragraph(TutorialDetails.getTutorialCenterAddress(),OpensansLightFont_10_White);
					    	 }
					    	 else
					    	 {
					    		 Address_TEXT = new Paragraph("-",OpensansLightFont_10_White);
					    		 Address_TEXT.setAlignment(Element.ALIGN_CENTER);
					    	 }
					    	 
					    	 
						    	
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Address_TEXT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderColor(TUTORIAL_CENTRES_TABLE_NESTED_cell_Color_DB);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderWidth(0.25f);
						        
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
					    	 
					    	 
					    	 if(TutorialDetails.getContactno()!=null)
					    	 {
					    		 Contact_number_TEXT = new Paragraph(TutorialDetails.getContactno().toString(),OpensansLightFont_10_White);
						    	 Contact_number_TEXT.setAlignment(Element.ALIGN_CENTER);
					    	 }
					    	 else
					    	 {
					    	 
						    	 Contact_number_TEXT = new Paragraph("-",OpensansLightFont_10_White);
						    	 Contact_number_TEXT.setAlignment(Element.ALIGN_CENTER);
					    	 }
					    	 
					    	 
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Contact_number_TEXT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderColor(TUTORIAL_CENTRES_TABLE_NESTED_cell_Color_DB);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderWidth(0.25f);
						        
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
					    	 
					    	 if(TutorialDetails.getTimeslots()!=null)
					    	 {
					    		 Time_slots_TEXT = new Paragraph(TutorialDetails.getTimeslots(),OpensansLightFont_10_White);
						    	 Time_slots_TEXT.setAlignment(Element.ALIGN_CENTER);
					    	 }
					    	 else
					    	 {
					    		 Time_slots_TEXT = new Paragraph("-",OpensansLightFont_10_White);
						    	 Time_slots_TEXT.setAlignment(Element.ALIGN_CENTER);
					    	 }
					    	 
					    	 
					    	
					    	 
					    	 
					    	 
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Time_slots_TEXT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderColor(TUTORIAL_CENTRES_TABLE_NESTED_cell_Color_DB);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorderWidth(0.25f);
						        
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_LEFT);
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(8);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(4);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
					    	 
					    	 
					    	 
					    	 
					    	 
				    	 }
				    	 
				    	 if(CheckTutorial)
				    	 {
				    		 Paragraph Last_Message1 = new Paragraph("You have  not selected any tutorial centres.",OpensansSemiboldFont_14);
					    	 Last_Message1.setAlignment(Element.ALIGN_CENTER);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Last_Message1);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setColspan(5);
						        
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_CENTER);
						         
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(10);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(0);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(0);
					    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(0);
					    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				    	 }
				    	 
//				    	 Paragraph Last_Message = new Paragraph("Lodestar team wishes you all the very best!",OpensansSemiboldFont_14);
//				    	 Last_Message.setAlignment(Element.ALIGN_CENTER);
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell = new PdfPCell();
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.addElement(Last_Message);
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setBorder(Rectangle.NO_BORDER);
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setColspan(5);
//					        
//					         
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setVerticalAlignment(Element.ALIGN_CENTER);
//					         
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingTop(60);
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingBottom(0);
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingLeft(0);
//				    	 TUTORIAL_CENTRES_TABLE_NESTED_cell.setPaddingRight(0);
//				    	 TUTORIAL_CENTRES_TABLE_NESTED.addCell(TUTORIAL_CENTRES_TABLE_NESTED_cell);
				    	 

			    	
			    	 
			    	 
				    	 
				    	 

			    	
			    	 
			    	 
				    	 document.add(TUTORIAL_CENTRES_TABLE_NESTED);
				    	 
					}
				    	 
				}
				
				
				if(i==17 && checksuplimentaryadded)
				{
					 Paragraph supplementaryinformation;
					  
					  if(bean!=null && bean.getSupplementaryinformation()!=null && bean.getSupplementaryinformation().trim()!="")
					  {
						  supplementaryinformation = new Paragraph(bean.getSupplementaryinformation(),OpensansLightFont_10_blue);
						  supplementaryinformation.setLeading(5, 1.2f);
						  supplementaryinformation.setAlignment(Element.ALIGN_JUSTIFIED);
						  
						  
						     ColumnText columnTable = new ColumnText(writer.getDirectContent());
					         columnTable.setSimpleColumn(100, 36, 500, 724);
					         columnTable.addElement(supplementaryinformation);
					         columnTable.go();
						  
						  
						  
						  
					  }
					  else
					  {
						  if(bean!=null && bean.getComments().trim()!=null && bean.getComments().trim()!="" && bean.getComments().trim().length()>500)
						  {
							  supplementaryinformation = new Paragraph(bean.getComments().trim(),OpensansLightFont_10_blue);
							  supplementaryinformation.setLeading(5, 1.2f);
							  supplementaryinformation.setAlignment(Element.ALIGN_JUSTIFIED);
							  
							  
							     ColumnText columnTable = new ColumnText(writer.getDirectContent());
						         columnTable.setSimpleColumn(100, 30, 500, 724);
						         columnTable.addElement(supplementaryinformation);
						         columnTable.go();
							  
							  
							  
							  
						  }
						  else
						  {
							  supplementaryinformation = new Paragraph("No Comments.",OpensansLightFont_10_blue);
							  supplementaryinformation.setLeading(5, 1.2f);
							  supplementaryinformation.setAlignment(Element.ALIGN_JUSTIFIED);
							  
							  
							     ColumnText columnTable = new ColumnText(writer.getDirectContent());
						         columnTable.setSimpleColumn(100, 30, 500, 724);
						         columnTable.addElement(supplementaryinformation);
						         columnTable.go();
						  }
					  }
					  
				}
				
				
				
				if(i==18)
		        {
					LastPageNo=true;
		        }
				

			}
			document.close();
			pdfReader.close();
			writer.close();
		} catch (Exception ex) {
			OUT.info(ApplicationConstants.EXCEPTION, ex);
			baos = null;
		}

		return baos;

	}
	
	
	
	
	
}

class HeaderAndFooterPdfPageEventHelper extends PdfPageEventHelper {
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	private static final Logger OUT = LoggerFactory.getLogger(HeaderAndFooterPdfPageEventHelper.class);
	public void onEndPage(PdfWriter pdfWriter, Document document) {
		
		Rectangle pagesize =document.getPageSize();
	      Image image = null;
		try {
			image = Image.getInstance(properties.getProperty("com.edupath.report.template.DirectoryPath")+"bottom-pic.png");

		image.scaleAbsolute(pagesize.getWidth(),99);
	        image.setAbsolutePosition(0, 0);
	       
	        PdfContentByte cb = pdfWriter.getDirectContentUnder();

	    	  
	    	  if((GeneratePDFReportFile.PageNo==14 || GeneratePDFReportFile.PageNo==15 || GeneratePDFReportFile.PageNo==16 || GeneratePDFReportFile.PageNo==17 || GeneratePDFReportFile.PageNo==18) && GeneratePDFReportFile.LastPageNo==false )
	    	  {
	    	  
			cb.addImage(image);
	    	  }

	      Font OpensansRegularFont= FontFactory.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")+"open-sans/OpenSans-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED); 
    	  OpensansRegularFont.setSize(9.5f);
    	  OpensansRegularFont.setColor(255,255,255);
    	  
    	  
    	  
    	  if(GeneratePDFReportFile.PageNo!=2 && GeneratePDFReportFile.LastPageNo==false)
    	  {
    		  
    		  Chunk Page_NO = new Chunk(String.format("%02d",pdfWriter.getPageNumber()),OpensansRegularFont);
    	  
    		  ColumnText columnTable = new ColumnText(pdfWriter.getDirectContent());
	         columnTable.setSimpleColumn(500,0,54,30);
	         columnTable.addElement(Page_NO);
	         
				columnTable.go();
				
    	  }
			} catch (Exception e) {
				
				OUT.info(ApplicationConstants.EXCEPTION, e);
				//System.out.println("Error 1==============>"+e.getMessage());
			}
    	  
	}
	
	
}

interface LineDash {
    public void applyLineDash(PdfContentByte canvas);
}


class DashedLine implements LineDash {
    public void applyLineDash(PdfContentByte canvas) {
        canvas.setLineDash(2, 2);
    }
}
class CustomBorder implements PdfPCellEvent {
    protected LineDash left;
    protected LineDash right;
    protected LineDash top;
    protected LineDash bottom;
    public CustomBorder(LineDash left, LineDash right,
            LineDash top, LineDash bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }
    public void cellLayout(PdfPCell cell, Rectangle position,
        PdfContentByte[] canvases) {
        PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
        if (top != null) {
            canvas.saveState();
            top.applyLineDash(canvas);
            canvas.moveTo(position.getRight(), position.getTop());
            canvas.lineTo(position.getLeft(), position.getTop());
            canvas.setColorStroke(BaseColor.WHITE);
            canvas.stroke();
            canvas.restoreState();
        }
        if (bottom != null) {
            canvas.saveState();
            bottom.applyLineDash(canvas);
            canvas.moveTo(position.getRight(), position.getBottom());
            canvas.lineTo(position.getLeft(), position.getBottom());
            canvas.setColorStroke(BaseColor.WHITE);
            canvas.stroke();
            canvas.restoreState();
        }
        if (right != null) {
            canvas.saveState();
            right.applyLineDash(canvas);
            canvas.moveTo(position.getRight(), position.getTop());
            canvas.lineTo(position.getRight(), position.getBottom());
            canvas.setColorStroke(BaseColor.WHITE);
            canvas.stroke();
            canvas.restoreState();
        }
        if (left != null) {
            canvas.saveState();
            left.applyLineDash(canvas);
            canvas.moveTo(position.getLeft(), position.getTop());
            canvas.lineTo(position.getLeft(), position.getBottom());
            canvas.setColorStroke(BaseColor.WHITE);
            canvas.stroke();
            canvas.restoreState();
        }
    }
}




