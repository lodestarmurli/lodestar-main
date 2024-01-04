package com.lodestar.edupath.programTest.CareerFitment.service;

import java.io.ByteArrayOutputStream;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEdupathDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEntExamDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.RiasecActivityDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;

public class CareerFitmentPDFReportService {
	
	private static final Logger	OUT	= LoggerFactory.getLogger(CareerFitmentPDFReportService.class);
	public static int PageNo = 0;
	public static boolean LastPageNo = false;
	private ByteArrayOutputStream baos = null;
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	private String inputFilePathFinalReport = properties.getProperty("com.edupath.OccCluster.template.DirectoryPath");
	private int index = 0;
	// Font imports
	private Font OpensansBold= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font Helvetica= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaBold= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaBoldOccName= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaBoldFitmentInterest= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaBoldFitmentAptitude= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaBoldDegreeName= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
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
	
	
	public ByteArrayOutputStream GeneratePDFReport(StudentDetailsDTO studentDTO, List<OccupationDTO> occlistwithfitment, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score, List<DHEntExamDTO> entExamlist,String webURL ) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("CareerFitmentPDFReportService : inside GeneratePDFReport method");
		OUT.debug("CareerFitmentPDFReportService : inside GeneratePDFReport studentDTO:{},occlistwithfitment:{}", studentDTO,occlistwithfitment);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template_ER.pdf");
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
					OpensansBold.setColor(61, 61, 61);
					OpensansBold.setSize(16);
					Paragraph Pname = new Paragraph(studentDTO.getName(), OpensansBold);

					ColumnText columnTable = new ColumnText(writer.getDirectContent());
					columnTable.setSimpleColumn(280, 280, 780, 780);
					columnTable.addElement(Pname);
					columnTable.go();
					
				}
				
				if (i == 3) 
				{
					Paragraph appscoreName = null;
					Paragraph OccName = null;
					String fitmentpath = "";
					Image fitment_img = null;
					Paragraph fullform = null;
					Paragraph description = null;
					Paragraph character = null;
					Paragraph raisecCode = null;

					float size = 0f;
					String nameocc = "Testing name for occupation with long text and long w o r d s size resize";

//==========================================================================start RIASEC====================================================================				
					PdfPTable tableRIASEC = new PdfPTable(1);
					tableRIASEC.setTotalWidth(new float[] { 100 });
					tableRIASEC.setLockedWidth(true);
					PdfPCell cellRIASEC = null;
					String raisec = raiseccodeDTO.getRaisec().replaceAll(",", " ");
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(38, 118, 187);
					HelveticaBold.setSize(18f);
					raisecCode = new Paragraph(raisec, HelveticaBold);
					cellRIASEC = new PdfPCell(new Phrase(raisecCode));
					cellRIASEC.setFixedHeight(24.4f);
					cellRIASEC.setBorder(Rectangle.NO_BORDER);
					cellRIASEC.setColspan(1);
					cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellRIASEC.setPaddingLeft(5);
					tableRIASEC.addCell(cellRIASEC);
					tableRIASEC.writeSelectedRows(0, -1, 320, 730, cb);

//=============================================================================end RIASEC=================================================================

//=============================================================================start RIASEC desc=================================================================

					PdfPTable tabledesc = new PdfPTable(2);
					tabledesc.setTotalWidth(new float[] { 115, 370 });
					tabledesc.setLockedWidth(true);
					PdfPCell cellDESC = null;

					HelveticaBold.setColor(65, 64, 66);
					HelveticaBold.setSize(16f);
					fullform = new Paragraph(raiseccodeDTO.getFullFrom().trim(), HelveticaBold);
					cellDESC = new PdfPCell(new Phrase(fullform));
					cellDESC.setFixedHeight(88f);
					cellDESC.setBorder(Rectangle.NO_BORDER);
					cellDESC.setColspan(1);
					tabledesc.addCell(cellDESC);

					Helvetica.setColor(65, 64, 66);
					Helvetica.setSize(11f);
					description = new Paragraph(raiseccodeDTO.getDescription(), Helvetica);
					cellDESC = new PdfPCell(new Phrase(description));
					cellDESC.setFixedHeight(88f);
					cellDESC.setBorder(Rectangle.NO_BORDER);
					cellDESC.setLeading(3, 0.9f);
					cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellDESC.setPaddingLeft(12);
					cellDESC.setPaddingTop(2);
					tabledesc.addCell(cellDESC);

					tabledesc.writeSelectedRows(0, -1, 50, 688, cb);

//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar = new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
					PdfPCell cellChar = null;
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(38, 118, 187);
					HelveticaBold.setSize(11f);
					raisecCode = new Paragraph(raiseccodeDTO.getCharacters(), HelveticaBold);
					cellChar = new PdfPCell(new Phrase(raisecCode));
					cellChar.setFixedHeight(24.4f);
					cellChar.setBorder(Rectangle.NO_BORDER);
					cellChar.setColspan(1);
					cellChar.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					tablechar.addCell(cellChar);
					tablechar.writeSelectedRows(0, -1, 128, 553, cb);

//=============================================================================end RIASEC character=================================================================

					PdfPTable table = new PdfPTable(2);
					table.setTotalWidth(new float[] { 85, 140 });
					table.setLockedWidth(true);
					PdfPCell cell = null;

//--------------------------------------------------------------------AppScore 1 start---------------------------------------------------------------------					
					index = 1;
					for (Map.Entry<String, String> score : app_score.entrySet()) {
						String _score = score.getValue().trim();

						if (_score.equalsIgnoreCase("H")) {
							HelveticaBold.setColor(98, 191, 0);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.High");
						}

						else if (_score.equalsIgnoreCase("M")) {
							HelveticaBold.setColor(255, 128, 0);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.Medium");
						} else if (_score.equalsIgnoreCase("L")) {
							HelveticaBold.setColor(255, 179, 26);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.Low");
						}
						HelveticaBold.setSize(13);
						appscoreName = new Paragraph(score.getKey().trim(), HelveticaBold);
						cell = new PdfPCell(new Phrase(appscoreName));
						cell.setFixedHeight(52.6f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(17);
						table.addCell(cell);

						fitment_img = Image.getInstance(fitmentpath);
						fitment_img.setAlignment(Element.ALIGN_MIDDLE);
						cell = new PdfPCell(fitment_img);
						cell.setFixedHeight(52.6f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setColspan(1);
						cell.setPadding(20);
						table.addCell(cell);
//			        
						table.writeSelectedRows(index - 1, -1, 55, (383 - size), cb);
						index++;
						size += 52.6f;
						OUT.debug("Size:{}", size);

					}
//--------------------------------------------------------------------AppScore 1 end---------------------------------------------------------------------					

				}
				
				
				if(i == 4)
				{
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitment_interest="";
					Image fitment_img=null;
					float size=0f;
					String fitment_apptitude="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 65,220,123 ,92});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
				 {
			    	OUT.debug("bharath CareerFitmentPDFReportService occdto:{}",occdto);
						
						
	
						HelveticaBoldOccName.setSize(10);
						HelveticaBoldOccName.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index),HelveticaBoldOccName);
				    	cell = new PdfPCell(new Phrase(slno));
				    	cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPaddingLeft(20);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
						
				       
						OccName = new Paragraph(occdto.getName(),HelveticaBoldOccName);
				        cell = new PdfPCell(new Phrase(OccName));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        if (occdto.getNewfitment() == 4) {
				        	HelveticaBoldFitmentInterest.setColor(98, 191, 0);
							 fitment_interest="Excellent";
							 
						} 
						else if (occdto.getNewfitment() == 3)
						{
							HelveticaBoldFitmentInterest.setColor(255, 128, 0);
								fitment_interest ="High";
							
						}
						else if (occdto.getNewfitment() == 2){
							HelveticaBoldFitmentInterest.setColor(255, 179, 26);
								fitment_interest="Medium";
							
						}
						else if (occdto.getNewfitment() == 1){
							HelveticaBoldFitmentInterest.setColor(176, 178, 179);
								fitment_interest="Low";
						}
				        HelveticaBoldFitmentInterest.setSize(10);
				        cell = new PdfPCell(new Paragraph(fitment_interest,HelveticaBoldFitmentInterest));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setColspan(1);
				        cell.setPaddingLeft(20);
				        cell.setPadding(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        if(occdto.getFitment()==4)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(98, 191, 0);
				        	fitment_apptitude="Excellent";
				        }
				        else if(occdto.getFitment()==3)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(255, 128, 0);
				        	fitment_apptitude="High";
				        }
				        else if(occdto.getFitment()==2)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(255, 179, 26);
				        	fitment_apptitude="Medium";
				        }
				        else if(occdto.getFitment()==1)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(176, 178, 179);
				        	fitment_apptitude="Low";
				        }
				        
				        HelveticaBoldFitmentAptitude.setSize(10);
				        cell = new PdfPCell(new Phrase(new Paragraph(fitment_apptitude,HelveticaBoldFitmentAptitude)));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        table.writeSelectedRows(index-1, -1, 50, (663-size), cb);
				        index++;  
				        size+=31.552f;
				         
				    }
					        
				}
				
				if(i == 5)
				{
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitment_interest="";
					Image fitment_img=null;
					float size=0f;
					String fitment_apptitude="";
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 65,180,250 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
				 {
			    	OUT.debug("bharath CareerFitmentPDFReportService occdto.getRequiredAbilityStr(),occdto:{}",getRequiredAbilityStr(occdto.getRequiredAbility()),occdto);
						
						
	
						HelveticaBoldOccName.setSize(10);
						HelveticaBoldOccName.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index),HelveticaBoldOccName);
				    	cell = new PdfPCell(new Phrase(slno));
				    	cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPaddingLeft(20);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
						
				       
						OccName = new Paragraph(occdto.getName(),HelveticaBoldOccName);
				        cell = new PdfPCell(new Phrase(OccName));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				         
				        
				        HelveticaBold.setSize(10);
				        cell = new PdfPCell(new Phrase(new Paragraph(getRequiredAbilityStr(occdto.getRequiredAbility()),HelveticaBold)));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        table.writeSelectedRows(index-1, -1, 50, (240-size), cb);
				        index++;  
				        size+=31.552f;
				         
				    }
					        
				}
				if(i == 6)
				{
					Paragraph link = new Paragraph();
			        HelveticaBold.setSize(11);
			        HelveticaBold.setColor(65,64,66);
			        Anchor anchor = new Anchor("Lodestar Aptitude",HelveticaBold);
		            anchor.setReference( webURL);
		            link.add(anchor);
		            
		            PdfPTable tableLink = new PdfPTable(1);
		            tableLink.setTotalWidth(new float[] { 350});
		            tableLink.setLockedWidth(true);
			        PdfPCell celltableLink=null;
			        celltableLink = new PdfPCell(new Paragraph(link));
			        celltableLink.setFixedHeight(278.715f);
			        celltableLink.setBorder(Rectangle.NO_BORDER);
			        celltableLink.setLeading(3, 0.9f);
			        tableLink.addCell(celltableLink);
			        tableLink.writeSelectedRows(-1, -1, 440, 784, cb);
			        
			        Paragraph link2 = new Paragraph();
			        PdfPTable tableLink2 = new PdfPTable(1);
		            tableLink2.setTotalWidth(new float[] { 350});
		            tableLink2.setLockedWidth(true);
			        PdfPCell celltableLink2=null;
			        Anchor anchor2 = new Anchor("Improvement Guide",HelveticaBold);
		            anchor2.setReference( webURL);
		            link2.add(anchor2);
			        celltableLink2 = new PdfPCell(new Paragraph(link2));
			        celltableLink2.setFixedHeight(278.715f);
			        celltableLink2.setBorder(Rectangle.NO_BORDER);
			        celltableLink2.setLeading(3, 0.9f);
			        tableLink2.addCell(celltableLink2);
			        tableLink2.writeSelectedRows(-1, -1, 48, 771.5f, cb);
			        
				}
				if(i==8)
				{
					float size=0f;
					Paragraph slno=null;
//--------------------------------------------------------------------edupath list end start---------------------------------------------------------------------						         
							        
				    PdfPTable _edupathtable = new PdfPTable(4);
				    _edupathtable.setTotalWidth(new float[] {47, 125,  165, 163});
				    _edupathtable.setLockedWidth(true);
			        PdfPCell _edupathcell=null;
			        index=1;
				    for(OccupationDTO occdto:occlistwithfitment)
				    {
				    	HelveticaBold.setSize(10);
				    	HelveticaBold.setColor(98, 191, 0);
						slno = new Paragraph(Integer.toString(index),HelveticaBold);
						_edupathcell = new PdfPCell(new Phrase(slno));
						_edupathcell.setFixedHeight(55.54f);
						_edupathcell.setBorder(Rectangle.NO_BORDER);
						_edupathcell.setColspan(1);
						_edupathcell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						_edupathcell.setPaddingLeft(20);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
	
				        
				        HelveticaBold.setSize(11);
				        HelveticaBold.setColor(98, 191, 0);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getName(),HelveticaBold)));
				        _edupathcell.setFixedHeight(54.4f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
						
				        
	
				        Helvetica.setSize(10);
				        Helvetica.setColor(65, 64, 66);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getEntExam(),Helvetica)));
				        _edupathcell.setFixedHeight(54.4f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
				    	
				        Helvetica.setSize(10);
				        Helvetica.setColor(65, 64, 66);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getDegree(),Helvetica)));
				        _edupathcell.setFixedHeight(54.4f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
						    	
				        _edupathtable.writeSelectedRows(index-1, -1, 50, (685-size), cb);
				        index++;  
				        size+=54.4f;
				        OUT.debug("_edupathcell Size:{}",size);
				    }
										    
//--------------------------------------------------------------------edupath list end---------------------------------------------------------------------						         

										    

					
					Paragraph examName=null;
					Paragraph description=null;
					String fitmentpath="";
					ColumnText columnTable=null;
					
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 55, 115,330 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			        
					//--------------------------------------------------------------------entexam list start---------------------------------------------------------------------						         
			         index=1;
					for (DHEntExamDTO examdto : entExamlist) 
					{
						
						HelveticaBold.setColor(98, 191, 0);
						HelveticaBold.setSize(10);
						slno = new Paragraph(Integer.toString(index), HelveticaBold);
						cell = new PdfPCell(new Phrase(slno));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(25);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						HelveticaBold.setSize(11);
						examName = new Paragraph(examdto.getExam(), HelveticaBold);
						cell = new PdfPCell(new Phrase(examName));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(25);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						Helvetica.setSize(11);
						Helvetica.setColor(65, 64, 66);
						description = new Paragraph(examdto.getDescription(), Helvetica);
						cell = new PdfPCell(new Phrase(description));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(10);
						cell.setLeading(3, 0.9f);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);
						table.writeSelectedRows(index - 1, -1, 50, (610 - size), cb);
						index++;
						size+=84.635f;
					}
			         
			       //--------------------------------------------------------------------entexam list 1 end---------------------------------------------------------------------
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

	public ByteArrayOutputStream GeneratePDFReportActivities(StudentDetailsDTO studentDTO, List<OccupationDTO> occlistwithfitment, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score, List<DHEntExamDTO> entExamlist,String webURL,List<RiasecActivityDTO> riasecActivityDTOList ) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("CareerFitmentPDFReportService : inside GeneratePDFReportActivities method");
		OUT.debug("CareerFitmentPDFReportService : inside GeneratePDFReportActivities studentDTO:{},occlistwithfitment:{}", studentDTO,occlistwithfitment);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template_ER_Activities.pdf");
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
					OpensansBold.setColor(61, 61, 61);
					OpensansBold.setSize(16);
					Paragraph Pname = new Paragraph(studentDTO.getName(), OpensansBold);

					ColumnText columnTable = new ColumnText(writer.getDirectContent());
					columnTable.setSimpleColumn(280, 280, 780, 780);
					columnTable.addElement(Pname);
					columnTable.go();
					
				}
				
				if (i == 3) 
				{
					Paragraph appscoreName = null;
					Paragraph OccName = null;
					String fitmentpath = "";
					Image fitment_img = null;
					Paragraph fullform = null;
					Paragraph description = null;
					Paragraph character = null;
					Paragraph raisecCode = null;

					float size = 0f;
					String nameocc = "Testing name for occupation with long text and long w o r d s size resize";

//==========================================================================start RIASEC====================================================================				
					PdfPTable tableRIASEC = new PdfPTable(1);
					tableRIASEC.setTotalWidth(new float[] { 100 });
					tableRIASEC.setLockedWidth(true);
					PdfPCell cellRIASEC = null;
					String raisec = raiseccodeDTO.getRaisec().replaceAll(",", " ");
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(38, 118, 187);
					HelveticaBold.setSize(18f);
					raisecCode = new Paragraph(raisec, HelveticaBold);
					cellRIASEC = new PdfPCell(new Phrase(raisecCode));
					cellRIASEC.setFixedHeight(24.4f);
					cellRIASEC.setBorder(Rectangle.NO_BORDER);
					cellRIASEC.setColspan(1);
					cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellRIASEC.setPaddingLeft(5);
					tableRIASEC.addCell(cellRIASEC);
					tableRIASEC.writeSelectedRows(0, -1, 320, 730, cb);

//=============================================================================end RIASEC=================================================================

//=============================================================================start RIASEC desc=================================================================

					PdfPTable tabledesc = new PdfPTable(2);
					tabledesc.setTotalWidth(new float[] { 115, 370 });
					tabledesc.setLockedWidth(true);
					PdfPCell cellDESC = null;

					HelveticaBold.setColor(65, 64, 66);
					HelveticaBold.setSize(16f);
					fullform = new Paragraph(raiseccodeDTO.getFullFrom().trim(), HelveticaBold);
					cellDESC = new PdfPCell(new Phrase(fullform));
					cellDESC.setFixedHeight(88f);
					cellDESC.setBorder(Rectangle.NO_BORDER);
					cellDESC.setColspan(1);
					tabledesc.addCell(cellDESC);

					Helvetica.setColor(65, 64, 66);
					Helvetica.setSize(11f);
					description = new Paragraph(raiseccodeDTO.getDescription(), Helvetica);
					cellDESC = new PdfPCell(new Phrase(description));
					cellDESC.setFixedHeight(88f);
					cellDESC.setBorder(Rectangle.NO_BORDER);
					cellDESC.setLeading(3, 0.9f);
					cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellDESC.setPaddingLeft(12);
					cellDESC.setPaddingTop(2);
					tabledesc.addCell(cellDESC);

					tabledesc.writeSelectedRows(0, -1, 50, 688, cb);

//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar = new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
					PdfPCell cellChar = null;
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(38, 118, 187);
					HelveticaBold.setSize(11f);
					raisecCode = new Paragraph(raiseccodeDTO.getCharacters(), HelveticaBold);
					cellChar = new PdfPCell(new Phrase(raisecCode));
					cellChar.setFixedHeight(24.4f);
					cellChar.setBorder(Rectangle.NO_BORDER);
					cellChar.setColspan(1);
					cellChar.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					tablechar.addCell(cellChar);
					tablechar.writeSelectedRows(0, -1, 128, 553, cb);

//=============================================================================end RIASEC character=================================================================

					PdfPTable table = new PdfPTable(2);
					table.setTotalWidth(new float[] { 85, 140 });
					table.setLockedWidth(true);
					PdfPCell cell = null;

//--------------------------------------------------------------------AppScore 1 start---------------------------------------------------------------------					
					index = 1;
					for (Map.Entry<String, String> score : app_score.entrySet()) {
						String _score = score.getValue().trim();

						if (_score.equalsIgnoreCase("H")) {
							HelveticaBold.setColor(98, 191, 0);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.High");
						}

						else if (_score.equalsIgnoreCase("M")) {
							HelveticaBold.setColor(255, 128, 0);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.Medium");
						} else if (_score.equalsIgnoreCase("L")) {
							HelveticaBold.setColor(255, 179, 26);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.Low");
						}
						HelveticaBold.setSize(13);
						appscoreName = new Paragraph(score.getKey().trim(), HelveticaBold);
						cell = new PdfPCell(new Phrase(appscoreName));
						cell.setFixedHeight(52.6f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(17);
						table.addCell(cell);

						fitment_img = Image.getInstance(fitmentpath);
						fitment_img.setAlignment(Element.ALIGN_MIDDLE);
						cell = new PdfPCell(fitment_img);
						cell.setFixedHeight(52.6f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setColspan(1);
						cell.setPadding(20);
						table.addCell(cell);
//			        
						table.writeSelectedRows(index - 1, -1, 55, (383 - size), cb);
						index++;
						size += 52.6f;
						OUT.debug("Size:{}", size);

					}
//--------------------------------------------------------------------AppScore 1 end---------------------------------------------------------------------					

				}
				
				
				if(i == 4)
				{
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitment_interest="";
					Image fitment_img=null;
					float size=0f;
					String fitment_apptitude="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 65,220,123 ,92});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
				 {
			    	OUT.debug("bharath CareerFitmentPDFReportService occdto:{}",occdto);
						
						
	
						HelveticaBoldOccName.setSize(10);
						HelveticaBoldOccName.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index),HelveticaBoldOccName);
				    	cell = new PdfPCell(new Phrase(slno));
				    	cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPaddingLeft(20);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
						
				       
						OccName = new Paragraph(occdto.getName(),HelveticaBoldOccName);
				        cell = new PdfPCell(new Phrase(OccName));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        if (occdto.getNewfitment() == 4) {
				        	HelveticaBoldFitmentInterest.setColor(98, 191, 0);
							 fitment_interest="Excellent";
							 
						} 
						else if (occdto.getNewfitment() == 3)
						{
							HelveticaBoldFitmentInterest.setColor(255, 128, 0);
								fitment_interest ="High";
							
						}
						else if (occdto.getNewfitment() == 2){
							HelveticaBoldFitmentInterest.setColor(255, 179, 26);
								fitment_interest="Medium";
							
						}
						else if (occdto.getNewfitment() == 1){
							HelveticaBoldFitmentInterest.setColor(176, 178, 179);
								fitment_interest="Low";
						}
				        HelveticaBoldFitmentInterest.setSize(10);
				        cell = new PdfPCell(new Paragraph(fitment_interest,HelveticaBoldFitmentInterest));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setColspan(1);
				        cell.setPaddingLeft(20);
				        cell.setPadding(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        if(occdto.getFitment()==4)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(98, 191, 0);
				        	fitment_apptitude="Excellent";
				        }
				        else if(occdto.getFitment()==3)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(255, 128, 0);
				        	fitment_apptitude="High";
				        }
				        else if(occdto.getFitment()==2)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(255, 179, 26);
				        	fitment_apptitude="Medium";
				        }
				        else if(occdto.getFitment()==1)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(176, 178, 179);
				        	fitment_apptitude="Low";
				        }
				        
				        HelveticaBoldFitmentAptitude.setSize(10);
				        cell = new PdfPCell(new Phrase(new Paragraph(fitment_apptitude,HelveticaBoldFitmentAptitude)));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        table.writeSelectedRows(index-1, -1, 50, (663-size), cb);
				        index++;  
				        size+=31.552f;
				         
				    }
					        
				}
				
				if(i == 5)
				{
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitment_interest="";
					Image fitment_img=null;
					float size=0f;
					String fitment_apptitude="";
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 65,180,250 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
				 {
			    	OUT.debug("bharath CareerFitmentPDFReportService occdto.getRequiredAbilityStr(),occdto:{}",getRequiredAbilityStr(occdto.getRequiredAbility()),occdto);
						
						
	
						HelveticaBoldOccName.setSize(10);
						HelveticaBoldOccName.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index),HelveticaBoldOccName);
				    	cell = new PdfPCell(new Phrase(slno));
				    	cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPaddingLeft(20);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
						
				       
						OccName = new Paragraph(occdto.getName(),HelveticaBoldOccName);
				        cell = new PdfPCell(new Phrase(OccName));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				         
				        
				        HelveticaBold.setSize(10);
				        cell = new PdfPCell(new Phrase(new Paragraph(getRequiredAbilityStr(occdto.getRequiredAbility()),HelveticaBold)));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        table.writeSelectedRows(index-1, -1, 50, (240-size), cb);
				        index++;  
				        size+=31.552f;
				         
				    }
					        
				}
				if(i == 6)
				{
					Paragraph link = new Paragraph();
			        HelveticaBold.setSize(11);
			        HelveticaBold.setColor(65,64,66);
			        Anchor anchor = new Anchor("Lodestar Aptitude",HelveticaBold);
		            anchor.setReference( webURL);
		            link.add(anchor);
		            
		            PdfPTable tableLink = new PdfPTable(1);
		            tableLink.setTotalWidth(new float[] { 350});
		            tableLink.setLockedWidth(true);
			        PdfPCell celltableLink=null;
			        celltableLink = new PdfPCell(new Paragraph(link));
			        celltableLink.setFixedHeight(278.715f);
			        celltableLink.setBorder(Rectangle.NO_BORDER);
			        celltableLink.setLeading(3, 0.9f);
			        tableLink.addCell(celltableLink);
			        tableLink.writeSelectedRows(-1, -1, 440, 785, cb);
			        
			        Paragraph link2 = new Paragraph();
			        PdfPTable tableLink2 = new PdfPTable(1);
		            tableLink2.setTotalWidth(new float[] { 350});
		            tableLink2.setLockedWidth(true);
			        PdfPCell celltableLink2=null;
			        Anchor anchor2 = new Anchor("Improvement Guide",HelveticaBold);
		            anchor2.setReference( webURL);
		            link2.add(anchor2);
			        celltableLink2 = new PdfPCell(new Paragraph(link2));
			        celltableLink2.setFixedHeight(278.715f);
			        celltableLink2.setBorder(Rectangle.NO_BORDER);
			        celltableLink2.setLeading(3, 0.9f);
			        tableLink2.addCell(celltableLink2);
			        tableLink2.writeSelectedRows(-1, -1, 48, 772f, cb);
			        
				}
				if(i==8)
				{
					float size=0f;
					Paragraph slno=null;
//--------------------------------------------------------------------edupath list end start---------------------------------------------------------------------						         
							        
				    PdfPTable _edupathtable = new PdfPTable(4);
				    _edupathtable.setTotalWidth(new float[] {47, 125,  165, 163});
				    _edupathtable.setLockedWidth(true);
			        PdfPCell _edupathcell=null;
			        index=1;
				    for(OccupationDTO occdto:occlistwithfitment)
				    {
				    	HelveticaBold.setSize(10);
				    	HelveticaBold.setColor(98, 191, 0);
						slno = new Paragraph(Integer.toString(index),HelveticaBold);
						_edupathcell = new PdfPCell(new Phrase(slno));
						_edupathcell.setFixedHeight(55.54f);
						_edupathcell.setBorder(Rectangle.NO_BORDER);
						_edupathcell.setColspan(1);
						_edupathcell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						_edupathcell.setPaddingLeft(20);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
	
				        
				        HelveticaBold.setSize(11);
				        HelveticaBold.setColor(98, 191, 0);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getName(),HelveticaBold)));
				        _edupathcell.setFixedHeight(54.4f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
						
				        
	
				        Helvetica.setSize(10);
				        Helvetica.setColor(65, 64, 66);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getEntExam(),Helvetica)));
				        _edupathcell.setFixedHeight(54.4f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
				    	
				        Helvetica.setSize(10);
				        Helvetica.setColor(65, 64, 66);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getDegree(),Helvetica)));
				        _edupathcell.setFixedHeight(54.4f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
						    	
				        _edupathtable.writeSelectedRows(index-1, -1, 50, (685-size), cb);
				        index++;  
				        size+=54.4f;
				        OUT.debug("_edupathcell Size:{}",size);
				    }
										    
//--------------------------------------------------------------------edupath list end---------------------------------------------------------------------						         

										    

					
					Paragraph examName=null;
					Paragraph description=null;
					String fitmentpath="";
					ColumnText columnTable=null;
					
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 55, 115,330 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			        
					//--------------------------------------------------------------------entexam list start---------------------------------------------------------------------						         
			         index=1;
					for (DHEntExamDTO examdto : entExamlist) 
					{
						
						HelveticaBold.setColor(98, 191, 0);
						HelveticaBold.setSize(10);
						slno = new Paragraph(Integer.toString(index), HelveticaBold);
						cell = new PdfPCell(new Phrase(slno));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(25);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						HelveticaBold.setSize(11);
						examName = new Paragraph(examdto.getExam(), HelveticaBold);
						cell = new PdfPCell(new Phrase(examName));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(25);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						Helvetica.setSize(11);
						Helvetica.setColor(65, 64, 66);
						description = new Paragraph(examdto.getDescription(), Helvetica);
						cell = new PdfPCell(new Phrase(description));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(10);
						cell.setLeading(3, 0.9f);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);
						table.writeSelectedRows(index - 1, -1, 50, (610 - size), cb);
						index++;
						size+=84.635f;
					}
			         
			       //--------------------------------------------------------------------entexam list 1 end---------------------------------------------------------------------
				}
				
				if(i==9)
				{
					float size=0f;
					Paragraph slno=null;
					Paragraph activity=null;
					
					PdfPTable table = new PdfPTable(2);
			        table.setTotalWidth(new float[] { 65, 435 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					 index=1;
					for (RiasecActivityDTO rDTo : riasecActivityDTOList) 
					{
						
						HelveticaBold.setSize(10);
						HelveticaBold.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index), HelveticaBold);
						cell = new PdfPCell(new Phrase(slno));
						cell.setFixedHeight(55.272f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setPaddingTop(10);
						cell.setPaddingLeft(20);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						Helvetica.setSize(10);
						Helvetica.setColor(65,64,66);
						activity = new Paragraph(rDTo.getActivity(), Helvetica);
						cell = new PdfPCell(new Phrase(activity));
						cell.setFixedHeight(55.272f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(10);
						cell.setLeading(3, 0.9f);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						 
						table.writeSelectedRows(index - 1, -1, 50, (635 - size), cb);
						index++;
						size+=55.272f;
					}
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

	
	
	public ByteArrayOutputStream GeneratePDFReportAlternative(StudentDetailsDTO studentDTO, List<OccupationDTO> occlistwithfitment, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score, List<DHEntExamDTO> entExamlist,String webURL,List<OccupationDTO> top3Occ ) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("CareerFitmentPDFReportService : inside GeneratePDFReportGeneratePDFReportAlternative method");
		OUT.debug("CareerFitmentPDFReportService : inside GeneratePDFReportGeneratePDFReportAlternative studentDTO:{},occlistwithfitment:{}", studentDTO,occlistwithfitment);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template_Alternate.pdf");
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
					OpensansBold.setColor(61, 61, 61);
					OpensansBold.setSize(16);
					Paragraph Pname = new Paragraph(studentDTO.getName(), OpensansBold);

					ColumnText columnTable = new ColumnText(writer.getDirectContent());
					columnTable.setSimpleColumn(280, 280, 780, 780);
					columnTable.addElement(Pname);
					columnTable.go();
					
				}
				
				if (i == 3) 
				{
					Paragraph appscoreName = null;
					Paragraph OccName = null;
					String fitmentpath = "";
					Image fitment_img = null;
					Paragraph fullform = null;
					Paragraph description = null;
					Paragraph character = null;
					Paragraph raisecCode = null;

					float size = 0f;
					String nameocc = "Testing name for occupation with long text and long w o r d s size resize";

//==========================================================================start RIASEC====================================================================				
					PdfPTable tableRIASEC = new PdfPTable(1);
					tableRIASEC.setTotalWidth(new float[] { 100 });
					tableRIASEC.setLockedWidth(true);
					PdfPCell cellRIASEC = null;
					String raisec = raiseccodeDTO.getRaisec().replaceAll(",", " ");
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(38, 118, 187);
					HelveticaBold.setSize(18f);
					raisecCode = new Paragraph(raisec, HelveticaBold);
					cellRIASEC = new PdfPCell(new Phrase(raisecCode));
					cellRIASEC.setFixedHeight(24.4f);
					cellRIASEC.setBorder(Rectangle.NO_BORDER);
					cellRIASEC.setColspan(1);
					cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellRIASEC.setPaddingLeft(5);
					tableRIASEC.addCell(cellRIASEC);
					tableRIASEC.writeSelectedRows(0, -1, 320, 730, cb);

//=============================================================================end RIASEC=================================================================

//=============================================================================start RIASEC desc=================================================================

					PdfPTable tabledesc = new PdfPTable(2);
					tabledesc.setTotalWidth(new float[] { 115, 370 });
					tabledesc.setLockedWidth(true);
					PdfPCell cellDESC = null;

					HelveticaBold.setColor(65, 64, 66);
					HelveticaBold.setSize(16f);
					fullform = new Paragraph(raiseccodeDTO.getFullFrom().trim(), HelveticaBold);
					cellDESC = new PdfPCell(new Phrase(fullform));
					cellDESC.setFixedHeight(88f);
					cellDESC.setBorder(Rectangle.NO_BORDER);
					cellDESC.setColspan(1);
					tabledesc.addCell(cellDESC);

					Helvetica.setColor(65, 64, 66);
					Helvetica.setSize(11f);
					description = new Paragraph(raiseccodeDTO.getDescription(), Helvetica);
					cellDESC = new PdfPCell(new Phrase(description));
					cellDESC.setFixedHeight(88f);
					cellDESC.setBorder(Rectangle.NO_BORDER);
					cellDESC.setLeading(3, 0.9f);
					cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellDESC.setPaddingLeft(12);
					cellDESC.setPaddingTop(2);
					tabledesc.addCell(cellDESC);

					tabledesc.writeSelectedRows(0, -1, 50, 688, cb);

//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar = new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
					PdfPCell cellChar = null;
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(38, 118, 187);
					HelveticaBold.setSize(11f);
					raisecCode = new Paragraph(raiseccodeDTO.getCharacters(), HelveticaBold);
					cellChar = new PdfPCell(new Phrase(raisecCode));
					cellChar.setFixedHeight(24.4f);
					cellChar.setBorder(Rectangle.NO_BORDER);
					cellChar.setColspan(1);
					cellChar.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					tablechar.addCell(cellChar);
					tablechar.writeSelectedRows(0, -1, 128, 553, cb);

//=============================================================================end RIASEC character=================================================================

					PdfPTable table = new PdfPTable(2);
					table.setTotalWidth(new float[] { 85, 140 });
					table.setLockedWidth(true);
					PdfPCell cell = null;

//--------------------------------------------------------------------AppScore 1 start---------------------------------------------------------------------					
					index = 1;
					for (Map.Entry<String, String> score : app_score.entrySet()) {
						String _score = score.getValue().trim();

						if (_score.equalsIgnoreCase("H")) {
							HelveticaBold.setColor(98, 191, 0);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.High");
						}

						else if (_score.equalsIgnoreCase("M")) {
							HelveticaBold.setColor(255, 128, 0);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.Medium");
						} else if (_score.equalsIgnoreCase("L")) {
							HelveticaBold.setColor(255, 179, 26);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.Low");
						}
						HelveticaBold.setSize(13);
						appscoreName = new Paragraph(score.getKey().trim(), HelveticaBold);
						cell = new PdfPCell(new Phrase(appscoreName));
						cell.setFixedHeight(52.6f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(17);
						table.addCell(cell);

						fitment_img = Image.getInstance(fitmentpath);
						fitment_img.setAlignment(Element.ALIGN_MIDDLE);
						cell = new PdfPCell(fitment_img);
						cell.setFixedHeight(52.6f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setColspan(1);
						cell.setPadding(20);
						table.addCell(cell);
//			        
						table.writeSelectedRows(index - 1, -1, 55, (383 - size), cb);
						index++;
						size += 52.6f;
						OUT.debug("Size:{}", size);

					}
//--------------------------------------------------------------------AppScore 1 end---------------------------------------------------------------------					

				}
				
				
				if(i == 4)
				{
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitment_interest="";
					Image fitment_img=null;
					float size=0f;
					String fitment_apptitude="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 65,220,123 ,92});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
				 {
			    	OUT.debug("bharath CareerFitmentPDFReportService occdto:{}",occdto);
						
						
	
						HelveticaBoldOccName.setSize(10);
						HelveticaBoldOccName.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index),HelveticaBoldOccName);
				    	cell = new PdfPCell(new Phrase(slno));
				    	cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPaddingLeft(20);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
						
				       
						OccName = new Paragraph(occdto.getName(),HelveticaBoldOccName);
				        cell = new PdfPCell(new Phrase(OccName));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        if (occdto.getNewfitment() == 4) {
				        	HelveticaBoldFitmentInterest.setColor(98, 191, 0);
							 fitment_interest="Excellent";
							 
						} 
						else if (occdto.getNewfitment() == 3)
						{
							HelveticaBoldFitmentInterest.setColor(255, 128, 0);
								fitment_interest ="High";
							
						}
						else if (occdto.getNewfitment() == 2){
							HelveticaBoldFitmentInterest.setColor(255, 179, 26);
								fitment_interest="Medium";
							
						}
						else if (occdto.getNewfitment() == 1){
							HelveticaBoldFitmentInterest.setColor(176, 178, 179);
								fitment_interest="Low";
						}
				        HelveticaBoldFitmentInterest.setSize(10);
				        cell = new PdfPCell(new Paragraph(fitment_interest,HelveticaBoldFitmentInterest));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setColspan(1);
				        cell.setPaddingLeft(20);
				        cell.setPadding(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        if(occdto.getFitment()==4)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(98, 191, 0);
				        	fitment_apptitude="Excellent";
				        }
				        else if(occdto.getFitment()==3)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(255, 128, 0);
				        	fitment_apptitude="High";
				        }
				        else if(occdto.getFitment()==2)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(255, 179, 26);
				        	fitment_apptitude="Medium";
				        }
				        else if(occdto.getFitment()==1)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(176, 178, 179);
				        	fitment_apptitude="Low";
				        }
				        
				        HelveticaBoldFitmentAptitude.setSize(10);
				        cell = new PdfPCell(new Phrase(new Paragraph(fitment_apptitude,HelveticaBoldFitmentAptitude)));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        table.writeSelectedRows(index-1, -1, 50, (663-size), cb);
				        index++;  
				        size+=31.552f;
				         
				    }
					        
				}
				
				if(i == 5)
				{
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitment_interest="";
					Image fitment_img=null;
					float size=0f;
					String fitment_apptitude="";
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 65,180,250 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
				 {
			    	OUT.debug("bharath CareerFitmentPDFReportService occdto.getRequiredAbilityStr(),occdto:{}",getRequiredAbilityStr(occdto.getRequiredAbility()),occdto);
						
						
	
						HelveticaBoldOccName.setSize(10);
						HelveticaBoldOccName.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index),HelveticaBoldOccName);
				    	cell = new PdfPCell(new Phrase(slno));
				    	cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPaddingLeft(20);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
						
				       
						OccName = new Paragraph(occdto.getName(),HelveticaBoldOccName);
				        cell = new PdfPCell(new Phrase(OccName));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				         
				        
				        HelveticaBold.setSize(10);
				        cell = new PdfPCell(new Phrase(new Paragraph(getRequiredAbilityStr(occdto.getRequiredAbility()),HelveticaBold)));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        table.writeSelectedRows(index-1, -1, 50, (240-size), cb);
				        index++;  
				        size+=31.552f;
				         
				    }
					        
				}
				if(i == 6)
				{
					Paragraph link = new Paragraph();
			        HelveticaBold.setSize(11);
			        HelveticaBold.setColor(65,64,66);
			        Anchor anchor = new Anchor("Lodestar Aptitude",HelveticaBold);
		            anchor.setReference( webURL);
		            link.add(anchor);
		            
		            PdfPTable tableLink = new PdfPTable(1);
		            tableLink.setTotalWidth(new float[] { 350});
		            tableLink.setLockedWidth(true);
			        PdfPCell celltableLink=null;
			        celltableLink = new PdfPCell(new Paragraph(link));
			        celltableLink.setFixedHeight(278.715f);
			        celltableLink.setBorder(Rectangle.NO_BORDER);
			        celltableLink.setLeading(3, 0.9f);
			        tableLink.addCell(celltableLink);
			        tableLink.writeSelectedRows(-1, -1, 440, 784, cb);
			        
			        Paragraph link2 = new Paragraph();
			        PdfPTable tableLink2 = new PdfPTable(1);
		            tableLink2.setTotalWidth(new float[] { 350});
		            tableLink2.setLockedWidth(true);
			        PdfPCell celltableLink2=null;
			        Anchor anchor2 = new Anchor("Improvement Guide",HelveticaBold);
		            anchor2.setReference( webURL);
		            link2.add(anchor2);
			        celltableLink2 = new PdfPCell(new Paragraph(link2));
			        celltableLink2.setFixedHeight(278.715f);
			        celltableLink2.setBorder(Rectangle.NO_BORDER);
			        celltableLink2.setLeading(3, 0.9f);
			        tableLink2.addCell(celltableLink2);
			        tableLink2.writeSelectedRows(-1, -1, 48, 771.5f, cb);
			        
				}
				if(i==8)
				{
					float size=0f;
					Paragraph slno=null;
//--------------------------------------------------------------------edupath list end start---------------------------------------------------------------------						         
							        
				    PdfPTable _edupathtable = new PdfPTable(4);
				    _edupathtable.setTotalWidth(new float[] {47, 125,  165, 163});
				    _edupathtable.setLockedWidth(true);
			        PdfPCell _edupathcell=null;
			        index=1;
				    for(OccupationDTO occdto:top3Occ)
				    {
				    	HelveticaBold.setSize(10);
				    	HelveticaBold.setColor(98, 191, 0);
						slno = new Paragraph(Integer.toString(index),HelveticaBold);
						_edupathcell = new PdfPCell(new Phrase(slno));
						_edupathcell.setFixedHeight(45.54f);
						_edupathcell.setBorder(Rectangle.NO_BORDER);
						_edupathcell.setColspan(1);
						_edupathcell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						_edupathcell.setPaddingLeft(20);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
	
				        
				        HelveticaBold.setSize(11);
				        HelveticaBold.setColor(98, 191, 0);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getName(),HelveticaBold)));
				        _edupathcell.setFixedHeight(45.54f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
						
				        
	
				        Helvetica.setSize(10);
				        Helvetica.setColor(65, 64, 66);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getEntExam(),Helvetica)));
				        _edupathcell.setFixedHeight(45.54f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
				    	
				        Helvetica.setSize(10);
				        Helvetica.setColor(65, 64, 66);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getDegree(),Helvetica)));
				        _edupathcell.setFixedHeight(45.54f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
						    	
				        _edupathtable.writeSelectedRows(index-1, -1, 50, (640-size), cb);
				        index++;  
				        size+=45.54f;
				        OUT.debug("_edupathcell Size:{}",size);
				    }
										    
//--------------------------------------------------------------------edupath list end---------------------------------------------------------------------						         

										    

					
					Paragraph examName=null;
					Paragraph description=null;
					String fitmentpath="";
					ColumnText columnTable=null;
					
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 55, 115,330 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			        
					//--------------------------------------------------------------------entexam list start---------------------------------------------------------------------						         
			         index=1;
					for (DHEntExamDTO examdto : entExamlist) 
					{
						
						HelveticaBold.setColor(98, 191, 0);
						HelveticaBold.setSize(10);
						slno = new Paragraph(Integer.toString(index), HelveticaBold);
						cell = new PdfPCell(new Phrase(slno));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(25);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						HelveticaBold.setSize(11);
						examName = new Paragraph(examdto.getExam(), HelveticaBold);
						cell = new PdfPCell(new Phrase(examName));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(25);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						Helvetica.setSize(11);
						Helvetica.setColor(65, 64, 66);
						description = new Paragraph(examdto.getDescription(), Helvetica);
						cell = new PdfPCell(new Phrase(description));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(10);
						cell.setLeading(3, 0.9f);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);
						table.writeSelectedRows(index - 1, -1, 50, (580 - size), cb);
						index++;
						size+=84.635f;
					}
			         
			       //--------------------------------------------------------------------entexam list 1 end---------------------------------------------------------------------
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

	public ByteArrayOutputStream GeneratePDFReportAlternativeActivities(StudentDetailsDTO studentDTO, List<OccupationDTO> occlistwithfitment, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score, List<DHEntExamDTO> entExamlist,String webURL,List<RiasecActivityDTO> riasecActivityDTOList,List<OccupationDTO> top3Occ ) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("CareerFitmentPDFReportService : inside GeneratePDFReportAlternativeActivities method");
		OUT.debug("CareerFitmentPDFReportService : inside GeneratePDFReportAlternativeActivities studentDTO:{},occlistwithfitment:{}", studentDTO,occlistwithfitment);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template_Alternate_Activities.pdf");
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
					OpensansBold.setColor(61, 61, 61);
					OpensansBold.setSize(16);
					Paragraph Pname = new Paragraph(studentDTO.getName(), OpensansBold);

					ColumnText columnTable = new ColumnText(writer.getDirectContent());
					columnTable.setSimpleColumn(280, 280, 780, 780);
					columnTable.addElement(Pname);
					columnTable.go();
					
				}
				
				if (i == 3) 
				{
					Paragraph appscoreName = null;
					Paragraph OccName = null;
					String fitmentpath = "";
					Image fitment_img = null;
					Paragraph fullform = null;
					Paragraph description = null;
					Paragraph character = null;
					Paragraph raisecCode = null;

					float size = 0f;
					String nameocc = "Testing name for occupation with long text and long w o r d s size resize";

//==========================================================================start RIASEC====================================================================				
					PdfPTable tableRIASEC = new PdfPTable(1);
					tableRIASEC.setTotalWidth(new float[] { 100 });
					tableRIASEC.setLockedWidth(true);
					PdfPCell cellRIASEC = null;
					String raisec = raiseccodeDTO.getRaisec().replaceAll(",", " ");
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(38, 118, 187);
					HelveticaBold.setSize(18f);
					raisecCode = new Paragraph(raisec, HelveticaBold);
					cellRIASEC = new PdfPCell(new Phrase(raisecCode));
					cellRIASEC.setFixedHeight(24.4f);
					cellRIASEC.setBorder(Rectangle.NO_BORDER);
					cellRIASEC.setColspan(1);
					cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellRIASEC.setPaddingLeft(5);
					tableRIASEC.addCell(cellRIASEC);
					tableRIASEC.writeSelectedRows(0, -1, 320, 730, cb);

//=============================================================================end RIASEC=================================================================

//=============================================================================start RIASEC desc=================================================================

					PdfPTable tabledesc = new PdfPTable(2);
					tabledesc.setTotalWidth(new float[] { 115, 370 });
					tabledesc.setLockedWidth(true);
					PdfPCell cellDESC = null;

					HelveticaBold.setColor(65, 64, 66);
					HelveticaBold.setSize(16f);
					fullform = new Paragraph(raiseccodeDTO.getFullFrom().trim(), HelveticaBold);
					cellDESC = new PdfPCell(new Phrase(fullform));
					cellDESC.setFixedHeight(88f);
					cellDESC.setBorder(Rectangle.NO_BORDER);
					cellDESC.setColspan(1);
					tabledesc.addCell(cellDESC);

					Helvetica.setColor(65, 64, 66);
					Helvetica.setSize(11f);
					description = new Paragraph(raiseccodeDTO.getDescription(), Helvetica);
					cellDESC = new PdfPCell(new Phrase(description));
					cellDESC.setFixedHeight(88f);
					cellDESC.setBorder(Rectangle.NO_BORDER);
					cellDESC.setLeading(3, 0.9f);
					cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellDESC.setPaddingLeft(12);
					cellDESC.setPaddingTop(2);
					tabledesc.addCell(cellDESC);

					tabledesc.writeSelectedRows(0, -1, 50, 688, cb);

//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar = new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
					PdfPCell cellChar = null;
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(38, 118, 187);
					HelveticaBold.setSize(11f);
					raisecCode = new Paragraph(raiseccodeDTO.getCharacters(), HelveticaBold);
					cellChar = new PdfPCell(new Phrase(raisecCode));
					cellChar.setFixedHeight(24.4f);
					cellChar.setBorder(Rectangle.NO_BORDER);
					cellChar.setColspan(1);
					cellChar.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					tablechar.addCell(cellChar);
					tablechar.writeSelectedRows(0, -1, 128, 553, cb);

//=============================================================================end RIASEC character=================================================================

					PdfPTable table = new PdfPTable(2);
					table.setTotalWidth(new float[] { 85, 140 });
					table.setLockedWidth(true);
					PdfPCell cell = null;

//--------------------------------------------------------------------AppScore 1 start---------------------------------------------------------------------					
					index = 1;
					for (Map.Entry<String, String> score : app_score.entrySet()) {
						String _score = score.getValue().trim();

						if (_score.equalsIgnoreCase("H")) {
							HelveticaBold.setColor(98, 191, 0);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.High");
						}

						else if (_score.equalsIgnoreCase("M")) {
							HelveticaBold.setColor(255, 128, 0);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.Medium");
						} else if (_score.equalsIgnoreCase("L")) {
							HelveticaBold.setColor(255, 179, 26);
							fitmentpath = ApplicationProperties.getInstance()
									.getProperty("com.DailyHunt.pdf.appscore.Low");
						}
						HelveticaBold.setSize(13);
						appscoreName = new Paragraph(score.getKey().trim(), HelveticaBold);
						cell = new PdfPCell(new Phrase(appscoreName));
						cell.setFixedHeight(52.6f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(17);
						table.addCell(cell);

						fitment_img = Image.getInstance(fitmentpath);
						fitment_img.setAlignment(Element.ALIGN_MIDDLE);
						cell = new PdfPCell(fitment_img);
						cell.setFixedHeight(52.6f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setColspan(1);
						cell.setPadding(20);
						table.addCell(cell);
//			        
						table.writeSelectedRows(index - 1, -1, 55, (383 - size), cb);
						index++;
						size += 52.6f;
						OUT.debug("Size:{}", size);

					}
//--------------------------------------------------------------------AppScore 1 end---------------------------------------------------------------------					

				}
				
				
				if(i == 4)
				{
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitment_interest="";
					Image fitment_img=null;
					float size=0f;
					String fitment_apptitude="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 65,220,123 ,92});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
				 {
			    	OUT.debug("bharath CareerFitmentPDFReportService occdto:{}",occdto);
						
						
	
						HelveticaBoldOccName.setSize(10);
						HelveticaBoldOccName.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index),HelveticaBoldOccName);
				    	cell = new PdfPCell(new Phrase(slno));
				    	cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPaddingLeft(20);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
						
				       
						OccName = new Paragraph(occdto.getName(),HelveticaBoldOccName);
				        cell = new PdfPCell(new Phrase(OccName));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        if (occdto.getNewfitment() == 4) {
				        	HelveticaBoldFitmentInterest.setColor(98, 191, 0);
							 fitment_interest="Excellent";
							 
						} 
						else if (occdto.getNewfitment() == 3)
						{
							HelveticaBoldFitmentInterest.setColor(255, 128, 0);
								fitment_interest ="High";
							
						}
						else if (occdto.getNewfitment() == 2){
							HelveticaBoldFitmentInterest.setColor(255, 179, 26);
								fitment_interest="Medium";
							
						}
						else if (occdto.getNewfitment() == 1){
							HelveticaBoldFitmentInterest.setColor(176, 178, 179);
								fitment_interest="Low";
						}
				        HelveticaBoldFitmentInterest.setSize(10);
				        cell = new PdfPCell(new Paragraph(fitment_interest,HelveticaBoldFitmentInterest));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setColspan(1);
				        cell.setPaddingLeft(20);
				        cell.setPadding(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        if(occdto.getFitment()==4)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(98, 191, 0);
				        	fitment_apptitude="Excellent";
				        }
				        else if(occdto.getFitment()==3)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(255, 128, 0);
				        	fitment_apptitude="High";
				        }
				        else if(occdto.getFitment()==2)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(255, 179, 26);
				        	fitment_apptitude="Medium";
				        }
				        else if(occdto.getFitment()==1)
				        {
				        	HelveticaBoldFitmentAptitude.setColor(176, 178, 179);
				        	fitment_apptitude="Low";
				        }
				        
				        HelveticaBoldFitmentAptitude.setSize(10);
				        cell = new PdfPCell(new Phrase(new Paragraph(fitment_apptitude,HelveticaBoldFitmentAptitude)));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        table.writeSelectedRows(index-1, -1, 50, (663-size), cb);
				        index++;  
				        size+=31.552f;
				         
				    }
					        
				}
				
				if(i == 5)
				{
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitment_interest="";
					Image fitment_img=null;
					float size=0f;
					String fitment_apptitude="";
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 65,180,250 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
				 {
			    	OUT.debug("bharath CareerFitmentPDFReportService occdto.getRequiredAbilityStr(),occdto:{}",getRequiredAbilityStr(occdto.getRequiredAbility()),occdto);
						
						
	
						HelveticaBoldOccName.setSize(10);
						HelveticaBoldOccName.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index),HelveticaBoldOccName);
				    	cell = new PdfPCell(new Phrase(slno));
				    	cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPaddingLeft(20);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
						
				       
						OccName = new Paragraph(occdto.getName(),HelveticaBoldOccName);
				        cell = new PdfPCell(new Phrase(OccName));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				         
				        
				        HelveticaBold.setSize(10);
				        cell = new PdfPCell(new Phrase(new Paragraph(getRequiredAbilityStr(occdto.getRequiredAbility()),HelveticaBold)));
				        cell.setFixedHeight(31.552f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        if (index % 2 != 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        table.addCell(cell);
				        
				        table.writeSelectedRows(index-1, -1, 50, (240-size), cb);
				        index++;  
				        size+=31.552f;
				         
				    }
					        
				}
				if(i == 6)
				{
					Paragraph link = new Paragraph();
			        HelveticaBold.setSize(11);
			        HelveticaBold.setColor(65,64,66);
			        Anchor anchor = new Anchor("Lodestar Aptitude",HelveticaBold);
		            anchor.setReference( webURL);
		            link.add(anchor);
		            
		            PdfPTable tableLink = new PdfPTable(1);
		            tableLink.setTotalWidth(new float[] { 350});
		            tableLink.setLockedWidth(true);
			        PdfPCell celltableLink=null;
			        celltableLink = new PdfPCell(new Paragraph(link));
			        celltableLink.setFixedHeight(278.715f);
			        celltableLink.setBorder(Rectangle.NO_BORDER);
			        celltableLink.setLeading(3, 0.9f);
			        tableLink.addCell(celltableLink);
			        tableLink.writeSelectedRows(-1, -1, 440, 785, cb);
			        
			        Paragraph link2 = new Paragraph();
			        PdfPTable tableLink2 = new PdfPTable(1);
		            tableLink2.setTotalWidth(new float[] { 350});
		            tableLink2.setLockedWidth(true);
			        PdfPCell celltableLink2=null;
			        Anchor anchor2 = new Anchor("Improvement Guide",HelveticaBold);
		            anchor2.setReference( webURL);
		            link2.add(anchor2);
			        celltableLink2 = new PdfPCell(new Paragraph(link2));
			        celltableLink2.setFixedHeight(278.715f);
			        celltableLink2.setBorder(Rectangle.NO_BORDER);
			        celltableLink2.setLeading(3, 0.9f);
			        tableLink2.addCell(celltableLink2);
			        tableLink2.writeSelectedRows(-1, -1, 48, 772f, cb);
			        
				}
				if(i==8)
				{
					float size=0f;
					Paragraph slno=null;
//--------------------------------------------------------------------edupath list end start---------------------------------------------------------------------						         
							        
				    PdfPTable _edupathtable = new PdfPTable(4);
				    _edupathtable.setTotalWidth(new float[] {47, 125,  165, 163});
				    _edupathtable.setLockedWidth(true);
			        PdfPCell _edupathcell=null;
			        index=1;
				    for(OccupationDTO occdto:top3Occ)
				    {
				    	HelveticaBold.setSize(10);
				    	HelveticaBold.setColor(98, 191, 0);
						slno = new Paragraph(Integer.toString(index),HelveticaBold);
						_edupathcell = new PdfPCell(new Phrase(slno));
						_edupathcell.setFixedHeight(45.54f);
						_edupathcell.setBorder(Rectangle.NO_BORDER);
						_edupathcell.setColspan(1);
						_edupathcell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						_edupathcell.setPaddingLeft(20);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
	
				        
				        HelveticaBold.setSize(11);
				        HelveticaBold.setColor(98, 191, 0);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getName(),HelveticaBold)));
				        _edupathcell.setFixedHeight(45.54f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
						
				        
	
				        Helvetica.setSize(10);
				        Helvetica.setColor(65, 64, 66);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getEntExam(),Helvetica)));
				        _edupathcell.setFixedHeight(45.54f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
				    	
				        Helvetica.setSize(10);
				        Helvetica.setColor(65, 64, 66);
				        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getDegree(),Helvetica)));
				        _edupathcell.setFixedHeight(45.54f);
				        _edupathcell.setBorder(Rectangle.NO_BORDER);
				        _edupathcell.setColspan(1);
				        _edupathcell.setPadding(5);
				        _edupathcell.setPaddingTop(20);
				        if (index % 2 != 0) {
				        	_edupathcell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
				        _edupathtable.addCell(_edupathcell);
						    	
				        _edupathtable.writeSelectedRows(index-1, -1, 50, (640-size), cb);
				        index++;  
				        size+=45.54f;
				        OUT.debug("_edupathcell Size:{}",size);
				    }
										    
//--------------------------------------------------------------------edupath list end---------------------------------------------------------------------						         

										    

					
					Paragraph examName=null;
					Paragraph description=null;
					String fitmentpath="";
					ColumnText columnTable=null;
					
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 55, 115,330 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			        
					//--------------------------------------------------------------------entexam list start---------------------------------------------------------------------						         
			         index=1;
					for (DHEntExamDTO examdto : entExamlist) 
					{
						
						HelveticaBold.setColor(98, 191, 0);
						HelveticaBold.setSize(10);
						slno = new Paragraph(Integer.toString(index), HelveticaBold);
						cell = new PdfPCell(new Phrase(slno));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(25);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						HelveticaBold.setSize(11);
						examName = new Paragraph(examdto.getExam(), HelveticaBold);
						cell = new PdfPCell(new Phrase(examName));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(25);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						Helvetica.setSize(11);
						Helvetica.setColor(65, 64, 66);
						description = new Paragraph(examdto.getDescription(), Helvetica);
						cell = new PdfPCell(new Phrase(description));
						cell.setFixedHeight(84.635f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(10);
						cell.setLeading(3, 0.9f);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);
						table.writeSelectedRows(index - 1, -1, 50, (580 - size), cb);
						index++;
						size+=84.635f;
					}
			         
			       //--------------------------------------------------------------------entexam list 1 end---------------------------------------------------------------------
				}
				
				if(i==9)
				{
					float size=0f;
					Paragraph slno=null;
					Paragraph activity=null;
					
					PdfPTable table = new PdfPTable(2);
			        table.setTotalWidth(new float[] { 65, 435 });
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					 index=1;
					for (RiasecActivityDTO rDTo : riasecActivityDTOList) 
					{
						
						HelveticaBold.setSize(10);
						HelveticaBold.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index), HelveticaBold);
						cell = new PdfPCell(new Phrase(slno));
						cell.setFixedHeight(55.272f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
						cell.setPaddingTop(20);
						cell.setPaddingLeft(20);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						Helvetica.setSize(10);
						Helvetica.setColor(65,64,66);
						activity = new Paragraph(rDTo.getActivity(), Helvetica);
						cell = new PdfPCell(new Phrase(activity));
						cell.setFixedHeight(55.272f);
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						cell.setPadding(5);
						cell.setPaddingTop(10);
						cell.setLeading(3, 0.9f);
						if (index % 2 == 0) {
							cell.setBackgroundColor(new BaseColor(247, 247, 247));
						}
						table.addCell(cell);

						 
						table.writeSelectedRows(index - 1, -1, 50, (635 - size), cb);
						index++;
						size+=55.272f;
					}
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

	public String getRequiredAbilityStr(String requiredAbility)
	{
		OUT.debug("bharath getRequiredAbilityStr requiredAbilityStr:{}",requiredAbility);
		String requiredAbilityStr=null;
		if( null != requiredAbility &&  !requiredAbility.equalsIgnoreCase("")  )
		{
			
			String[] str=requiredAbility.split(",");
			
			for(String substr : str)
			{
				if (requiredAbilityStr != null )
				{
					requiredAbilityStr += ", ";
					
				}
				else
				{
					requiredAbilityStr = new String();
				}
				if(substr.trim().equalsIgnoreCase("RA"))
				{
					requiredAbilityStr+="Reasoning";
				}
				if(substr.trim().equalsIgnoreCase("MA"))
				{
					requiredAbilityStr+="Mechanical";
				}
				if(substr.trim().equalsIgnoreCase("NA"))
				{
					requiredAbilityStr+="Numerical";
				}
				if(substr.trim().equalsIgnoreCase("SA"))
				{
					requiredAbilityStr+="Spatial";
				}
				if(substr.trim().equalsIgnoreCase("VA"))
				{
					requiredAbilityStr+="Verbal";
				}			
			}
		}
		else
		{
			requiredAbilityStr = new String();
			requiredAbilityStr ="";
		}
		
		return requiredAbilityStr;
	}

}
