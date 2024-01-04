package com.lodestar.edupath.APIS.dailyHunt;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.lodestar.edupath.PDFReport.service.GeneratePDFReportFile;


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
//import com.itextpdf.text.List;
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
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
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

//import com.lodestar.edupath.PDFReport.service.HeaderAndFooterPdfPageEventHelper;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.finalsummary.bean.ReportSummaryBean;
import com.lodestar.edupath.util.ApplicationProperties;

public class DownloadPDFReportService {
	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(DownloadPDFReportService.class);
	public static int PageNo=0;
	public static boolean LastPageNo=false;
	private ByteArrayOutputStream baos = null;
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	private String inputFilePathFinalReport = properties.getProperty("com.DailyHunt.pdf.template.DirectoryPath");
	private String inputFilePathFinalReport12plus = properties.getProperty("com.DailyHunt.pdf.template.12plus.DirectoryPath");
	private int index = 0;
	//Font imports
	private Font OpensansBold= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font Helvetica= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaBold= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaBoldOblique= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-BoldOblique.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaCompressed= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"helvetica-compressed.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaLight= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"helvetica-light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaOblique= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-Oblique.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansBoldItalic= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-BoldItalic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansCondensedBold= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSansCondensed-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansCondensedLight= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSansCondensed-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansCondensedLightItalic= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSansCondensed-LightItalic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansExtraBold= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-ExtraBold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansExtraBoldItalic= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-ExtraBoldItalic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansItalic= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-Italic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansLight= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-Light.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansLightItalic= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-LightItalic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansRegular= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansSemibold= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-Semibold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font OpenSansSemiboldItalic= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-SemiboldItalic", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font Rupee_Foradian= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Rupee_Foradian.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	
	
	
	public ByteArrayOutputStream GenerateDHStage1Report(DHStudentDetailsDTO studentDTO,List<OccupationDTO> occlistwithfitment, RaisecCodeDTO raiseccodeDTO) {
		PageNo=0;
		LastPageNo=false;
		OUT.info("DownloadPDFReportService : inside GenerateStudentFinalReport method");
		OUT.debug("DownloadPDFReportService : inside GenerateStudentFinalReport studentDTO:{}",studentDTO);
		OUT.debug("DownloadPDFReportService : inside GenerateStudentFinalReport occlistwithfitment:{}",occlistwithfitment);
		
		
		
		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport+"Template.pdf");
			Rectangle pagesize = pdfReader.getPageSize(1);
			Document document = new Document(pagesize, 0, 0, 0, 0);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			OUT.info("try block2");
			Rectangle rectangle = new Rectangle(20, 30, 550, 800);
			document.open();
			PdfContentByte cb = writer.getDirectContentUnder();

			for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
				PageNo = i;
			
				
				PdfImportedPage page = writer.getImportedPage(pdfReader, i);
				document.newPage();
				cb.addTemplate(page, 0, 0);
				
				if (i == 1) {
					OpensansBold.setColor(61,61,61);
					OpensansBold.setSize(16);
					Paragraph Pname = new Paragraph(studentDTO.getName(),OpensansBold);

			    	 ColumnText columnTable = new ColumnText(writer.getDirectContent());
			         columnTable.setSimpleColumn(280, 280, 775, 778);
			         columnTable.addElement(Pname);
			         columnTable.go();
			         
				}
				if(i==3)
				{
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitmentpath="";
					Image fitment_img=null;
					Paragraph fullform=null;
					Paragraph description=null;
					Paragraph character=null;
					Paragraph raisecCode=null;
					
					float size=0f;
					String nameocc="Testing name for occupation with long text and long w o r d s size resize";
					
					
					
//==========================================================================start RIASEC====================================================================				
					PdfPTable tableRIASEC= new PdfPTable(1);
					tableRIASEC.setTotalWidth(new float[] { 100 });
					tableRIASEC.setLockedWidth(true);
			        PdfPCell cellRIASEC =null;
			        String raisec = raiseccodeDTO.getRaisec().replaceAll(",", " ");
			        OUT.debug("raisec:{}",raisec);
			        HelveticaBold.setColor(239, 170, 3);
			        HelveticaBold.setSize(18f);
			        raisecCode=new Paragraph(raisec,HelveticaBold);
			        cellRIASEC = new PdfPCell(new Phrase(raisecCode));
			        cellRIASEC.setFixedHeight(24.4f);
			        cellRIASEC.setBorder(Rectangle.NO_BORDER);
			        cellRIASEC.setColspan(1);
			        cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        tableRIASEC.addCell(cellRIASEC);
			        tableRIASEC.writeSelectedRows(0, -1, 320, 730, cb);
			        
//=============================================================================end RIASEC=================================================================

//=============================================================================start RIASEC desc=================================================================

			        PdfPTable tabledesc= new PdfPTable(2);
			        tabledesc.setTotalWidth(new float[] { 115, 370 });
			        tabledesc.setLockedWidth(true);
			        PdfPCell cellDESC =null;

			        HelveticaBold.setColor(65,64,66);
			        HelveticaBold.setSize(16f);
			        fullform=new Paragraph(raiseccodeDTO.getFullFrom().trim(),HelveticaBold);
			        cellDESC = new PdfPCell(new Phrase(fullform));
			        cellDESC.setFixedHeight(88f);
			        cellDESC.setBorder(Rectangle.NO_BORDER);
			        cellDESC.setColspan(1);
			        tabledesc.addCell(cellDESC);
			        
			        Helvetica.setColor(65,64,66);
			        Helvetica.setSize(11f);
			        description=new Paragraph(raiseccodeDTO.getDescription(),Helvetica);
			        cellDESC = new PdfPCell(new Phrase(description));
			        cellDESC.setFixedHeight(88f);
			        cellDESC.setBorder(Rectangle.NO_BORDER);
			        cellDESC.setColspan(1);
			        cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        cellDESC.setPaddingLeft(12);
			        cellDESC.setPaddingTop(2);
			        tabledesc.addCell(cellDESC);
			        
			        tabledesc.writeSelectedRows(0, -1, 50, 685, cb);
			        
					
//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar= new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
			        PdfPCell cellChar =null;
			        OUT.debug("raisec:{}",raisec);
			        HelveticaBold.setColor(239, 170, 3);
			        HelveticaBold.setSize(11f);
			        raisecCode=new Paragraph(raiseccodeDTO.getCharacters(),HelveticaBold);
			        cellChar = new PdfPCell(new Phrase(raisecCode));
			        cellChar.setFixedHeight(24.4f);
			        cellChar.setBorder(Rectangle.NO_BORDER);
			        cellChar.setColspan(1);
			        cellChar.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        tablechar.addCell(cellChar);
			        tablechar.writeSelectedRows(0, -1, 128, 579, cb);
			        
//=============================================================================end RIASEC character=================================================================
				
					
					
					
					
					
					
					
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 65, 340,95 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
			    {

					if (occdto.getNewfitment() == 4) {
						HelveticaBold.setColor(98, 191, 0);
						 fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.HighlySuited");
					} 
					else if (occdto.getNewfitment() == 3){
						HelveticaBold.setColor(255, 128, 0);
						if (index % 2 == 0) {
							fitmentpath = ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.AboveAverageFitGray");
						} else {
							fitmentpath = ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.AboveAverageFit");
						}
					}
					else if (occdto.getNewfitment() == 2){
						HelveticaBold.setColor(255, 179, 26);
						if (index % 2 == 0) {
							fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.AverageFitGray");
						} else {
							fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.AverageFit");
						}
					}
					else if (occdto.getNewfitment() == 1){
						HelveticaBold.setColor(176, 178, 179);
						if (index % 2 == 0) {
							fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.LowFitGray");
						} else {
							fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.LowFit");
						}
					}

					HelveticaBold.setSize(10);
					slno = new Paragraph(Integer.toString(index),HelveticaBold);
			    	cell = new PdfPCell(new Phrase(slno));
			    	cell.setFixedHeight(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setColspan(1);
			        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        cell.setPaddingLeft(20);
			        cell.setPaddingTop(10);
			        if(index%2==0) {cell.setBackgroundColor(new BaseColor(247,247,247));}
			        table.addCell(cell);
					
			        HelveticaBold.setSize(10);
					OccName = new Paragraph(occdto.getName(),HelveticaBold);
			        cell = new PdfPCell(new Phrase(OccName));
			        cell.setFixedHeight(31.5f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setColspan(1);
			        cell.setPadding(5);
			        cell.setPaddingTop(10);
			        if(index%2==0) {cell.setBackgroundColor(new BaseColor(247,247,247));}
			        table.addCell(cell);

			        fitment_img = Image.getInstance(fitmentpath);
			        fitment_img.setAlignment(Element.ALIGN_MIDDLE);
			        cell = new PdfPCell(fitment_img);
			        cell.setFixedHeight(31.5f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			        cell.setColspan(1);
			        cell.setPadding(10);
			        if(index%2==0) {cell.setBackgroundColor(new BaseColor(247,247,247));}
			        table.addCell(cell);
			        
			        table.writeSelectedRows(index-1, -1, 50, (475-size), cb);
			        index++;  
			        size+=31.5f;
			        OUT.debug("Size:{}",size);
			         
			    }
//--------------------------------------------------------------------occupation 1 end---------------------------------------------------------------------					
				
			
				
				
				}
				
				if(i==4)
				{

					Paragraph slno=null;
					Paragraph OccName=null;
					Paragraph rollover=null;
					String fitmentpath="";
					ColumnText columnTable=null;
					float size=0f;
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 55, 120,325 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
			        String nameocc="Testing name for occupation with long text and long w o r d s size resize";
			        String rolll="Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize ";
					
			        
					//--------------------------------------------------------------------occupation rollover 1 start---------------------------------------------------------------------						         
			         index=1;
					for (OccupationDTO occdto : occlistwithfitment) 
					{
//			         occdto =occlistwithfitment.get(index-1);
						if (occdto.getNewfitment() == 4) {
							HelveticaBold.setColor(98, 191, 0);
						} else if (occdto.getNewfitment() == 3) {
							HelveticaBold.setColor(255, 128, 0);
						} else if (occdto.getNewfitment() == 2) {
							HelveticaBold.setColor(255, 179, 26);
						} else if (occdto.getNewfitment() == 1) {
							HelveticaBold.setColor(176, 178, 179);

						}

						HelveticaBold.setSize(10);
						slno = new Paragraph(Integer.toString(index), HelveticaBold);
						cell = new PdfPCell(new Phrase(slno));
						cell.setFixedHeight(31.5f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(20);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						HelveticaBold.setSize(10);
						OccName = new Paragraph(occdto.getName(), HelveticaBold);
						cell = new PdfPCell(new Phrase(OccName));
						cell.setFixedHeight(52.7f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(20);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						Helvetica.setSize(9);
						Helvetica.setColor(65, 64, 66);
						rollover = new Paragraph(occdto.getRollOverContent(), Helvetica);
						cell = new PdfPCell(new Phrase(rollover));
						cell.setFixedHeight(52.7f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(10);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);
						table.writeSelectedRows(index - 1, -1, 50, (720 - size), cb);
						index++;
						size+=52.7f;
					}
			         
			       //--------------------------------------------------------------------occupation rollover 1 end---------------------------------------------------------------------
				}

			}
			OUT.info("try block3");
			document.close();
			pdfReader.close();
			writer.close();
		} catch (Exception ex) {
			OUT.info(ApplicationConstants.EXCEPTION, ex);
			baos = null;
		}
		
		
		
	return baos;
	}
	
	

public ByteArrayOutputStream GenerateDHStage1ReportTwelve(DHStudentDetailsDTO studentDTO,List<OccupationDTO> occlistwithfitment,RaisecCodeDTO raiseccodeDTO) {
	PageNo=0;
	LastPageNo=false;
	OUT.info("DownloadPDFReportService : inside GenerateStudentFinalReport method");
	OUT.debug("DownloadPDFReportService : inside GenerateStudentFinalReport studentDTO:{}",studentDTO);
	OUT.debug("DownloadPDFReportService : inside GenerateStudentFinalReport occlistwithfitment:{}",occlistwithfitment);
	
	
	
	try {
		OUT.info("try block");
		baos = new ByteArrayOutputStream();
		PdfReader pdfReader = new PdfReader(inputFilePathFinalReport12plus+"Template.pdf");
		Rectangle pagesize = pdfReader.getPageSize(1);
		Document document = new Document(pagesize, 0, 0, 0, 0);
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		OUT.info("try block2");
		Rectangle rectangle = new Rectangle(20, 30, 550, 800);

		document.open();
		PdfContentByte cb = writer.getDirectContentUnder();

		for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
			PageNo = i;
		
			
			PdfImportedPage page = writer.getImportedPage(pdfReader, i);
			document.newPage();
			cb.addTemplate(page, 0, 0);
			
			if (i == 1) {
				OpensansBold.setColor(61,61,61);
				OpensansBold.setSize(16);
				Paragraph Pname = new Paragraph(studentDTO.getName(),OpensansBold);

		    	 ColumnText columnTable = new ColumnText(writer.getDirectContent());
		         columnTable.setSimpleColumn(280, 280, 775, 778);
		         columnTable.addElement(Pname);
		         columnTable.go();
		         
			}
			if(i==3)
			{
				
				Paragraph slno=null;
				Paragraph OccName=null;
				String fitmentpath="";
				Image fitment_img=null;
				Paragraph fullform=null;
				Paragraph description=null;
				Paragraph character=null;
				Paragraph raisecCode=null;
				
				float size=0f;
				String nameocc="Testing name for occupation with long text and long w o r d s size resize";
				
				
				
//==========================================================================start RIASEC====================================================================				
				PdfPTable tableRIASEC= new PdfPTable(1);
				tableRIASEC.setTotalWidth(new float[] { 100 });
				tableRIASEC.setLockedWidth(true);
		        PdfPCell cellRIASEC =null;
		        String raisec = raiseccodeDTO.getRaisec().replaceAll(",", " ");
		        OUT.debug("raisec:{}",raisec);
		        HelveticaBold.setColor(239, 170, 3);
		        HelveticaBold.setSize(18f);
		        raisecCode=new Paragraph(raisec,HelveticaBold);
		        cellRIASEC = new PdfPCell(new Phrase(raisecCode));
		        cellRIASEC.setFixedHeight(24.4f);
		        cellRIASEC.setBorder(Rectangle.NO_BORDER);
		        cellRIASEC.setColspan(1);
		        cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		        tableRIASEC.addCell(cellRIASEC);
		        tableRIASEC.writeSelectedRows(0, -1, 320, 730, cb);
		        
//=============================================================================end RIASEC=================================================================

//=============================================================================start RIASEC desc=================================================================

		        PdfPTable tabledesc= new PdfPTable(2);
		        tabledesc.setTotalWidth(new float[] { 115, 370 });
		        tabledesc.setLockedWidth(true);
		        PdfPCell cellDESC =null;

		        HelveticaBold.setColor(65,64,66);
		        HelveticaBold.setSize(16f);
		        fullform=new Paragraph(raiseccodeDTO.getFullFrom().trim(),HelveticaBold);
		        cellDESC = new PdfPCell(new Phrase(fullform));
		        cellDESC.setFixedHeight(88f);
		        cellDESC.setBorder(Rectangle.NO_BORDER);
		        cellDESC.setColspan(1);
		        tabledesc.addCell(cellDESC);
		        
		        Helvetica.setColor(65,64,66);
		        Helvetica.setSize(11f);
		        description=new Paragraph(raiseccodeDTO.getDescription(),Helvetica);
		        cellDESC = new PdfPCell(new Phrase(description));
		        cellDESC.setFixedHeight(88f);
		        cellDESC.setBorder(Rectangle.NO_BORDER);
		        cellDESC.setColspan(1);
		        cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		        cellDESC.setPaddingLeft(12);
		        cellDESC.setPaddingTop(2);
		        tabledesc.addCell(cellDESC);
		        
		        tabledesc.writeSelectedRows(0, -1, 50, 685, cb);
		        
				
//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
				PdfPTable tablechar= new PdfPTable(1);
				tablechar.setTotalWidth(new float[] { 400 });
				tablechar.setLockedWidth(true);
		        PdfPCell cellChar =null;
		        OUT.debug("raisec:{}",raisec);
		        HelveticaBold.setColor(239, 170, 3);
		        HelveticaBold.setSize(11f);
		        raisecCode=new Paragraph(raiseccodeDTO.getCharacters(),HelveticaBold);
		        cellChar = new PdfPCell(new Phrase(raisecCode));
		        cellChar.setFixedHeight(24.4f);
		        cellChar.setBorder(Rectangle.NO_BORDER);
		        cellChar.setColspan(1);
		        cellChar.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		        tablechar.addCell(cellChar);
		        tablechar.writeSelectedRows(0, -1, 128, 579, cb);
		        
//=============================================================================end RIASEC character=================================================================


				
				
				
				
				
 
		        int yval=680;
				
//--------------------------------------------------------------------occupation Science with Math start---------------------------------------------------------------------					
		        size=0f;
		        for (OccupationDTO occdto : occlistwithfitment) {
					if (occdto.getDhStream() == 1) {
						PdfPTable table = new PdfPTable(1);
				        table.setTotalWidth(new float[] { 340 });
				        table.setLockedWidth(true);
				        PdfPCell cell=null;
						HelveticaBold.setColor(80,175,205);
						HelveticaBold.setSize(12);
						OccName = new Paragraph(occdto.getName(), HelveticaBold);
						cell = new PdfPCell(new Phrase(OccName));
						cell.setFixedHeight(35f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
				        size+=20f;
				        table.writeSelectedRows(0 , -1, 200, (500-size), cb);
				         
					}
				}
		        OUT.debug("size::{}",size);
//				
		    			
//--------------------------------------------------------------------occupation Science with Math end---------------------------------------------------------------------					
//			
//////--------------------------------------------------------------------occupation Science without Math start---------------------------------------------------------------------					
		        size+=20f;
		        for (OccupationDTO occdto : occlistwithfitment) {
					if (occdto.getDhStream() == 6) {
						PdfPTable table = new PdfPTable(1);
				        table.setTotalWidth(new float[] { 340 });
				        table.setLockedWidth(true);
				        PdfPCell cell=null;
						HelveticaBold.setColor(239,170,3);
						HelveticaBold.setSize(12);
						OccName = new Paragraph(occdto.getName(), HelveticaBold);
						cell = new PdfPCell(new Phrase(OccName));
						cell.setFixedHeight(35f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);

						table.addCell(cell);
				        size+=20f;
				        table.writeSelectedRows(0 , -1, 200, (500-size), cb);
				         
					}
				}
		        OUT.debug("size::{}",size);		
////--------------------------------------------------------------------occupation Science without Math end---------------------------------------------------------------------					
//
////--------------------------------------------------------------------occupation Commerce start---------------------------------------------------------------------					
		        size+=20f;
		        for (OccupationDTO occdto : occlistwithfitment) {
					if (occdto.getDhStream() == 7) {
						PdfPTable table = new PdfPTable(1);
				        table.setTotalWidth(new float[] { 340 });
				        table.setLockedWidth(true);
				        PdfPCell cell=null;
						HelveticaBold.setColor(135,212,71);
						HelveticaBold.setSize(12);
						OccName = new Paragraph(occdto.getName(), HelveticaBold);
						cell = new PdfPCell(new Phrase(OccName));
						cell.setFixedHeight(35f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
				        size+=20f;
				        table.writeSelectedRows(0 , -1, 200, (500-size), cb);
				         
					}
				}
		        OUT.debug("size::{}",size);			
//			    			
////--------------------------------------------------------------------occupation Commerce end---------------------------------------------------------------------					
//						
////--------------------------------------------------------------------occupation Arts start---------------------------------------------------------------------					
				size+=20f;
		        for (OccupationDTO occdto : occlistwithfitment) {
					if (occdto.getDhStream() == 5) {
						PdfPTable table = new PdfPTable(1);
				        table.setTotalWidth(new float[] { 340 });
				        table.setLockedWidth(true);
				        PdfPCell cell=null;
						HelveticaBold.setColor(217,84,35);
						HelveticaBold.setSize(12);
						OccName = new Paragraph(occdto.getName(), HelveticaBold);
						cell = new PdfPCell(new Phrase(OccName));
						cell.setFixedHeight(35f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
				        size+=20f;
				        table.writeSelectedRows(0 , -1, 200, (500-size), cb);
				         
					}
				}
		        OUT.debug("size::{}",size);			
//			    			
////--------------------------------------------------------------------occupation Arts end---------------------------------------------------------------------					

			
			
			}
			
			if(i==4)
			{

				Paragraph slno=null;
				Paragraph OccName=null;
				Paragraph rollover=null;
				String fitmentpath="";
				ColumnText columnTable=null;
				float size=0f;
				PdfPTable table = new PdfPTable(3);
		        table.setTotalWidth(new float[] { 55, 120,325 });
		        table.setLockedWidth(true);
		        PdfPCell cell=null;
		        String nameocc="Testing name for occupation with long text and long w o r d s size resize";
		        String rolll="Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize Testing name for occupation with long text and long w o r d s size resize ";
				
		        
				//--------------------------------------------------------------------occupation rollover 1 start---------------------------------------------------------------------						         
		         index=1;
				for (OccupationDTO occdto : occlistwithfitment) 
				{
 
					if (occdto.getNewfitment() == 4) {
						HelveticaBold.setColor(98, 191, 0);
					} else if (occdto.getNewfitment() == 3) {
						HelveticaBold.setColor(255, 128, 0);
					} else if (occdto.getNewfitment() == 2) {
						HelveticaBold.setColor(255, 179, 26);
					} else if (occdto.getNewfitment() == 1) {
						HelveticaBold.setColor(176, 178, 179);

					}

					HelveticaBold.setSize(10);
					slno = new Paragraph(Integer.toString(index), HelveticaBold);
 
					cell = new PdfPCell(new Phrase(slno));
					cell.setFixedHeight(65f);
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setColspan(1);
					cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cell.setPadding(20);
					if (index % 2 == 0) {
						cell.setBackgroundColor(new BaseColor(247, 247, 247));
					}
					table.addCell(cell);

					HelveticaBold.setSize(10);
					OccName = new Paragraph(occdto.getName(), HelveticaBold);
					cell = new PdfPCell(new Phrase(OccName));
					cell.setFixedHeight(65f);
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setColspan(1);
					cell.setPadding(5);
					cell.setPaddingTop(20);
					if (index % 2 == 0) {
						cell.setBackgroundColor(new BaseColor(247, 247, 247));
					}
					table.addCell(cell);

					Helvetica.setSize(9);
					Helvetica.setColor(65, 64, 66);
					rollover = new Paragraph(occdto.getRollOverContent(), Helvetica);
					cell = new PdfPCell(new Phrase(rollover));
					cell.setFixedHeight(65f);
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setColspan(1);
					cell.setPadding(5);
					cell.setPaddingTop(15);
					if (index % 2 == 0) {
						cell.setBackgroundColor(new BaseColor(247, 247, 247));
					}
					table.addCell(cell);
					table.writeSelectedRows(index - 1, -1, 50, (720 - size), cb);
					index++;
					size+=65f;
				}
		         
		       //--------------------------------------------------------------------occupation rollover 1 end---------------------------------------------------------------------
			}

		}
		OUT.info("try block3");
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




