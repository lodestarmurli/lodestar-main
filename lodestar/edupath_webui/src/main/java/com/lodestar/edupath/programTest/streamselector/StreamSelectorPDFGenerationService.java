package com.lodestar.edupath.programTest.streamselector;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.*;
import com.lodestar.edupath.APIS.dailyHunt.AptitudeResultDownload.DownloadAptitudeReportPDFService;
import com.lodestar.edupath.PDFReport.service.GeneratePDFReportFile;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEdupathDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEntExamDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorResultVO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.STREAM;
import com.lodestar.edupath.util.ApplicationProperties;

public class StreamSelectorPDFGenerationService {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(StreamSelectorPDFGenerationService.class);
	public static int PageNo = 0;
	public static boolean LastPageNo = false;
	private ByteArrayOutputStream baos = null;
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	private String inputFilePathFinalReport = properties.getProperty("com.streamselector.pdf.template.DirectoryPath");
	private int index = 0;
	// Font imports
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
	
	
	
	public ByteArrayOutputStream GeneratePDFReportSize5(StudentDetailsDTO studentDTO,
			List<StreamSelectorResultVO> streamSelectorResultVO, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize5 method");
		OUT.debug("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize5 studentDTO:{}", studentDTO);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template5.pdf");
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
					columnTable.setSimpleColumn(340, 340, 827, 827);
					columnTable.addElement(Pname);
					columnTable.go();
					
					String classVal =""; 
					switch(studentDTO.getClassId())
					{
					case  1 : classVal="9"; break;
					case  2 : classVal="10"; break;
					case  3 : classVal="11"; break;
					case  4 : classVal="12"; break;
					case  5 : classVal="12 Plus"; break;
					}
					 
					Paragraph Pclass = new Paragraph(classVal, OpensansBold);

					ColumnText columnTableCLass = new ColumnText(writer.getDirectContent());
					columnTableCLass.setSimpleColumn(340, 340, 793, 793);
					columnTableCLass.addElement(Pclass);
					columnTableCLass.go();

				}
				
				if (i == 4) 
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
					HelveticaBold.setColor(246, 146, 30);
					HelveticaBold.setSize(18f);
					raisecCode = new Paragraph(raisec, HelveticaBold);
					cellRIASEC = new PdfPCell(new Phrase(raisecCode));
					cellRIASEC.setFixedHeight(24.4f);
					cellRIASEC.setBorder(Rectangle.NO_BORDER);
					cellRIASEC.setColspan(1);
					cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellRIASEC.setPaddingLeft(5);
					tableRIASEC.addCell(cellRIASEC);
					tableRIASEC.writeSelectedRows(0, -1, 350, 730, cb);

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
					cellDESC.setColspan(1);
					cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellDESC.setPaddingLeft(12);
					cellDESC.setPaddingTop(2);
					tabledesc.addCell(cellDESC);

					tabledesc.writeSelectedRows(0, -1, 50, 685, cb);

//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar = new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
					PdfPCell cellChar = null;
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(239, 170, 3);
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

				if(i==5)
				{
					Paragraph mainStream=null;
					Paragraph subStream=null;
					
					String StreamIconpath="";
					Image Icon_img=null;
					float size=0f;
					String fitmentstr="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 179,50,160,150});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
			        Phrase subStreamphrase ;
					
// --------------------------------------------------------------------Stream Recommendation start---------------------------------------------------------------------
					index = 1;
				    for(StreamSelectorResultVO _SSRVO : streamSelectorResultVO) 
					 {
				    		StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.icon.DirectoryPath")+_SSRVO.getStream().trim()+".png";
				    		
							if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.HIGH.getScore())  {
								 fitmentstr="High Fitment";
							} 
							else if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.MEDIUM.getScore()){
								HelveticaBold.setColor(255, 128, 0);
								fitmentstr="Moderate Fitment";
							}

							
							subStreamphrase = new Phrase();
							if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHBIO.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Bio", HelveticaBold);
						        subStreamphrase = new Phrase();
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHOUTMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("without ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHLANGUAGES.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Languages", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHPSYCHOLOGY.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Psychology", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.GENERALARTS.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("General Arts", HelveticaBold);
						        subStreamphrase.add(c1);
						        
							}
								
							
							
							HelveticaBold.setSize(16);
							mainStream = new Paragraph(_SSRVO.getMainStream(),HelveticaBold);
					    	cell = new PdfPCell(new Phrase(mainStream));
					    	cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setPaddingLeft(40);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
							
					        
					        Icon_img = Image.getInstance(StreamIconpath);
					        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
					        cell = new PdfPCell(Icon_img);
					        cell.setFixedHeight(52.23f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setColspan(1);
//					        cell.setPaddingLeft(20);
//					        cell.setPadding(10);
					        table.addCell(cell);
					        
					        HelveticaBold.setSize(12);
							Helvetica.setSize(12);
							HelveticaBold.setColor(0, 0, 0);
				    		Helvetica.setColor(0, 0, 0);
					        subStream = new Paragraph(subStreamphrase);
					        cell = new PdfPCell(new Phrase(subStream));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
		
					        
					        Helvetica.setSize(12);
					        cell = new PdfPCell(new Phrase(fitmentstr,Helvetica));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
					        
					        table.writeSelectedRows(index-1, -1, 50, (702-size), cb);
					        index++;  
					        size+=58.56f;
					         
					    }

//--------------------------------------------------------------------Stream Recommendation end ---------------------------------------------------------------------						         
 				}

				if (i == 6) 
				{
					String StreamIconpath="";
					Image Icon_img=null;
					Paragraph description;
					Chunk commonName;
					Chunk desp ;
					Chunk coursesHeading ;
					Chunk coursesDesp;
					Chunk careerPotentialHeading;
					Chunk careerPotentialdesp;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 1 and 2 start ---------------------------------------------------------------------						         
			        
					PdfPTable tableindex0 = new PdfPTable(2);
					tableindex0.setTotalWidth(new float[] { 325,290});
					tableindex0.setLockedWidth(true);
			        PdfPCell celltableindex0=null;
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(167, 61, 46);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        commonName = new Chunk(streamSelectorResultVO.get(0).getCommonName()+"\n \n", HelveticaBold);
			        desp = new Chunk(streamSelectorResultVO.get(0).getDescription(), Helvetica);
			        HelveticaBold.setSize(11);
//			        HelveticaBold.setColor(167, 61, 46);
			        coursesHeading = new Chunk("\n\nCourses: ", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(0).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential: ", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(0).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setFirstLineIndent(5);
			        
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(0).getStream().trim()+".png";
					
						Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
//				        Icon_img.setTransparency(new int[] { 0xF0, 0xFF} );
//				        Icon_img.setScaleToFitHeight(false);
				        celltableindex0 = new PdfPCell(Icon_img);
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        celltableindex0.setColspan(1);
//				        celltableindex0.setPaddingLeft(10);
	//			        celltableindex0.setPadding(10);
				        tableindex0.addCell(celltableindex0);
	
				        
				        HelveticaBold.setSize(11);
				        celltableindex0 = new PdfPCell(new Paragraph(description));
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setColspan(1);
				        celltableindex0.setRowspan(2);
//				        celltableindex0.setPaddingLeft(10);
//				        celltableindex0.setPaddingTop(10);
	//			        celltableindex0.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex0.addCell(celltableindex0);
			        
			        tableindex0.writeSelectedRows(-1, -1, -50, 749, cb);
			        
			        
			        PdfPTable tableindex1 = new PdfPTable(2);
			        tableindex1.setTotalWidth(new float[] { 295	,300});
			        tableindex1.setLockedWidth(true);
			        PdfPCell celltableindex1=null;
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(0, 147, 69);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        commonName = new Chunk(streamSelectorResultVO.get(1).getCommonName()+"\n \n", HelveticaBold);
			        desp = new Chunk(streamSelectorResultVO.get(1).getDescription(), Helvetica);
			        HelveticaBold.setSize(11);
//			        HelveticaBold.setColor(0, 147, 69);
			        coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(1).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(1).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
			        StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(1).getStream().trim()+".png";
					
//				        HelveticaBold.setSize(12);
				        celltableindex1 = new PdfPCell(new Paragraph(description));
				        celltableindex1.setFixedHeight(270f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setColspan(1);
				        celltableindex1.setPaddingLeft(10);
//				        celltableindex1.setPaddingTop(10);
	//			        celltableindex1.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex1.addCell(celltableindex1);
				        
				        Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
				        celltableindex1 = new PdfPCell(Icon_img);
				        celltableindex1.setFixedHeight(270f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	//			        celltableindex1.setColspan(1);
	//			        celltableindex1.setPaddingLeft(20);
	//			        celltableindex1.setPadding(10);
				        tableindex1.addCell(celltableindex1);
				        
			        tableindex1.writeSelectedRows(-1, -1, 40, 408, cb);
 
//--------------------------------------------------------------------Stream description 1 and 2 end ---------------------------------------------------------------------						         

				}
				
				
				if (i == 7) 
				{
					String StreamIconpath="";
					Image Icon_img=null;
					Paragraph description;
					Chunk commonName;
					Chunk desp ;
					Chunk coursesHeading ;
					Chunk coursesDesp;
					Chunk careerPotentialHeading;
					Chunk careerPotentialdesp;
					Phrase DespPhrase;
//--------------------------------------------------------------------Stream description 3 and 4 start ---------------------------------------------------------------------						         
					PdfPTable tableindex2 = new PdfPTable(2);
					tableindex2.setTotalWidth(new float[] { 325,300});
					tableindex2.setLockedWidth(true);
			        PdfPCell celltableindex2=null;
			       
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(5, 119, 128);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
					commonName = new Chunk(streamSelectorResultVO.get(2).getCommonName()+"\n\n", HelveticaBold);
					desp = new Chunk(streamSelectorResultVO.get(1).getDescription(), Helvetica);
				    HelveticaBold.setSize(11);
					coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(2).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(2).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(2).getStream().trim()+".png";
					
						Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
				        celltableindex2 = new PdfPCell(Icon_img);
				        celltableindex2.setFixedHeight(270f);
				        celltableindex2.setBorder(Rectangle.NO_BORDER);
				        celltableindex2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	//			        celltableindex2.setColspan(1);
//				        celltableindex2.setPaddingLeft(10);
	//			        celltableindex2.setPadding(10);
				        tableindex2.addCell(celltableindex2);
	
				        
//				        HelveticaBold.setSize(12);
				        celltableindex2 = new PdfPCell(new Paragraph(description));
				        celltableindex2.setFixedHeight(270f);
				        celltableindex2.setBorder(Rectangle.NO_BORDER);
				        celltableindex2.setColspan(1);
				        celltableindex2.setPaddingLeft(15);
//				        celltableindex2.setPaddingTop(10);
	//			        celltableindex0.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex2.addCell(celltableindex2);
			        
			        tableindex2.writeSelectedRows(-1, -1, -45, 790, cb);
			        
			        
			        PdfPTable tableindex3 = new PdfPTable(2);
			        tableindex3.setTotalWidth(new float[] { 295,300});
			        tableindex3.setLockedWidth(true);
			        PdfPCell celltableindex3=null;

			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(0, 147, 69);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);			        
			        commonName = new Chunk(streamSelectorResultVO.get(3).getCommonName()+"\n\n", HelveticaBold);
					desp = new Chunk(streamSelectorResultVO.get(3).getDescription(), Helvetica);
				    HelveticaBold.setSize(11);
					coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(3).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(3).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(3).getStream().trim()+".png";
					
//				        HelveticaBold.setSize(12);
				        celltableindex3 = new PdfPCell(new Paragraph(description));
				        celltableindex3.setFixedHeight(270f);
				        celltableindex3.setBorder(Rectangle.NO_BORDER);
				        celltableindex3.setColspan(1);
				        celltableindex3.setPaddingLeft(10);
//				        celltableindex3.setPaddingTop(10);
	//			        celltableindex1.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex3.addCell(celltableindex3);
				        
				        Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
				        celltableindex3 = new PdfPCell(Icon_img);
				        celltableindex3.setFixedHeight(270f);
				        celltableindex3.setBorder(Rectangle.NO_BORDER);
				        celltableindex3.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	//			        celltableindex1.setColspan(1);
	//			        celltableindex1.setPaddingLeft(20);
	//			        celltableindex1.setPadding(10);
				        tableindex3.addCell(celltableindex3);
				        
				    tableindex3.writeSelectedRows(-1, -1, 40, 408, cb);
			        
		 
//--------------------------------------------------------------------Stream description3 and 4 end ---------------------------------------------------------------------						         

				}
				if (i == 8) 
				{
					String StreamIconpath="";
					Image Icon_img=null;
					Paragraph description;
					Chunk commonName;
					Chunk desp ;
					Chunk coursesHeading ;
					Chunk coursesDesp;
					Chunk careerPotentialHeading;
					Chunk careerPotentialdesp;
					Phrase DespPhrase;

//--------------------------------------------------------------------Stream description 5 start ---------------------------------------------------------------------						         
					PdfPTable tableindex4 = new PdfPTable(2);
					tableindex4.setTotalWidth(new float[] { 325,300});
					tableindex4.setLockedWidth(true);
			        PdfPCell celltableindex4=null;
			        
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(5, 119, 128);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);			        
			        commonName = new Chunk(streamSelectorResultVO.get(4).getCommonName()+"\n \n", HelveticaBold);
					desp = new Chunk(streamSelectorResultVO.get(4).getDescription(), Helvetica);
				    HelveticaBold.setSize(11);
					coursesHeading = new Chunk("\n\n Courses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(4).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(3).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(4).getStream().trim()+".png";
					
					Icon_img = Image.getInstance(StreamIconpath);
			        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
			        celltableindex4 = new PdfPCell(Icon_img);
			        celltableindex4.setFixedHeight(270f);
			        celltableindex4.setBorder(Rectangle.NO_BORDER);
			        celltableindex4.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//			        celltableindex4.setColspan(1);
//			        celltableindex4.setPaddingLeft(10);
//			        celltableindex4.setPadding(10);
			        tableindex4.addCell(celltableindex4);

			        
//			        HelveticaBold.setSize(12);
			        celltableindex4 = new PdfPCell(new Paragraph(description));
			        celltableindex4.setFixedHeight(270f);
			        celltableindex4.setBorder(Rectangle.NO_BORDER);
			        celltableindex4.setColspan(1);
			        celltableindex4.setPaddingLeft(15);
//			        celltableindex4.setPaddingTop(10);
//			        celltableindex4.setBackgroundColor(new BaseColor(249,221,0));
			        tableindex4.addCell(celltableindex4);
			        
			        tableindex4.writeSelectedRows(-1, -1, -45, 790, cb);
 
//--------------------------------------------------------------------Stream description 5 end ---------------------------------------------------------------------						         

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




	public ByteArrayOutputStream GeneratePDFReportSize4(StudentDetailsDTO studentDTO,
			List<StreamSelectorResultVO> streamSelectorResultVO, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize4 method");
		OUT.debug("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize4 studentDTO:{}", studentDTO);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template4.pdf");
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
					columnTable.setSimpleColumn(340, 340, 827, 827);
					columnTable.addElement(Pname);
					columnTable.go();
					
					String classVal =""; 
					switch(studentDTO.getClassId())
					{
					case  1 : classVal="9"; break;
					case  2 : classVal="10"; break;
					case  3 : classVal="11"; break;
					case  4 : classVal="12"; break;
					case  5 : classVal="12 Plus"; break;
					}
					 
					Paragraph Pclass = new Paragraph(classVal, OpensansBold);

					ColumnText columnTableCLass = new ColumnText(writer.getDirectContent());
					columnTableCLass.setSimpleColumn(340, 340, 793, 793);
					columnTableCLass.addElement(Pclass);
					columnTableCLass.go();

				}
				
				if (i == 4) 
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
					HelveticaBold.setColor(246, 146, 30);
					HelveticaBold.setSize(18f);
					raisecCode = new Paragraph(raisec, HelveticaBold);
					cellRIASEC = new PdfPCell(new Phrase(raisecCode));
					cellRIASEC.setFixedHeight(24.4f);
					cellRIASEC.setBorder(Rectangle.NO_BORDER);
					cellRIASEC.setColspan(1);
					cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellRIASEC.setPaddingLeft(5);
					tableRIASEC.addCell(cellRIASEC);
					tableRIASEC.writeSelectedRows(0, -1, 350, 730, cb);

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
					cellDESC.setColspan(1);
					cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellDESC.setPaddingLeft(12);
					cellDESC.setPaddingTop(2);
					tabledesc.addCell(cellDESC);

					tabledesc.writeSelectedRows(0, -1, 50, 685, cb);

//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar = new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
					PdfPCell cellChar = null;
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(239, 170, 3);
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

				if(i==5)
				{
					Paragraph mainStream=null;
					Paragraph subStream=null;
					
					String StreamIconpath="";
					Image Icon_img=null;
					float size=0f;
					String fitmentstr="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 179,50,160,150});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
			        Phrase subStreamphrase ;
					
// --------------------------------------------------------------------Stream Recommendation start---------------------------------------------------------------------
					index = 1;
				    for(StreamSelectorResultVO _SSRVO : streamSelectorResultVO) 
					 {
				    		StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.icon.DirectoryPath")+_SSRVO.getStream().trim()+".png";
				    		
							if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.HIGH.getScore())  {
								 fitmentstr="High Fitment";
							} 
							else if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.MEDIUM.getScore()){
								HelveticaBold.setColor(255, 128, 0);
								fitmentstr="Moderate Fitment";
							}

							
							subStreamphrase = new Phrase();
							if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHBIO.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Bio", HelveticaBold);
						        subStreamphrase = new Phrase();
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHOUTMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("without ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHLANGUAGES.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Languages", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHPSYCHOLOGY.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Psychology", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.GENERALARTS.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("General Arts", HelveticaBold);
						        subStreamphrase.add(c1);
						        
							}
								
							
							
							HelveticaBold.setSize(16);
							mainStream = new Paragraph(_SSRVO.getMainStream(),HelveticaBold);
					    	cell = new PdfPCell(new Phrase(mainStream));
					    	cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setPaddingLeft(40);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
							
					        
					        Icon_img = Image.getInstance(StreamIconpath);
					        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
					        cell = new PdfPCell(Icon_img);
					        cell.setFixedHeight(52.23f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setColspan(1);
//					        cell.setPaddingLeft(20);
//					        cell.setPadding(10);
					        table.addCell(cell);
					        
					        HelveticaBold.setSize(12);
							Helvetica.setSize(12);
							HelveticaBold.setColor(0, 0, 0);
				    		Helvetica.setColor(0, 0, 0);
					        subStream = new Paragraph(subStreamphrase);
					        cell = new PdfPCell(new Phrase(subStream));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
		
					        
					        Helvetica.setSize(12);
					        cell = new PdfPCell(new Phrase(fitmentstr,Helvetica));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
					        
					        table.writeSelectedRows(index-1, -1, 50, (681-size), cb);
					        index++;  
					        size+=58.56f;
					         
					    }

//--------------------------------------------------------------------Stream Recommendation end ---------------------------------------------------------------------						         
 				}

				if (i == 6) 
				{
					String StreamIconpath="";
					Image Icon_img=null;
					Paragraph description;
					Chunk commonName;
					Chunk desp ;
					Chunk coursesHeading ;
					Chunk coursesDesp;
					Chunk careerPotentialHeading;
					Chunk careerPotentialdesp;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 1 and 2 start ---------------------------------------------------------------------						         
			        
					PdfPTable tableindex0 = new PdfPTable(2);
					tableindex0.setTotalWidth(new float[] { 325,290});
					tableindex0.setLockedWidth(true);
			        PdfPCell celltableindex0=null;
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(167, 61, 46);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        commonName = new Chunk(streamSelectorResultVO.get(0).getCommonName()+"\n \n", HelveticaBold);
			        desp = new Chunk(streamSelectorResultVO.get(0).getDescription(), Helvetica);
			        HelveticaBold.setSize(11);
//			        HelveticaBold.setColor(167, 61, 46);
			        coursesHeading = new Chunk("\n\nCourses: ", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(0).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential: ", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(0).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setFirstLineIndent(5);
			        
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(0).getStream().trim()+".png";
					
						Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
//				        Icon_img.setTransparency(new int[] { 0xF0, 0xFF} );
//				        Icon_img.setScaleToFitHeight(false);
				        celltableindex0 = new PdfPCell(Icon_img);
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        celltableindex0.setColspan(1);
//				        celltableindex0.setPaddingLeft(10);
	//			        celltableindex0.setPadding(10);
				        tableindex0.addCell(celltableindex0);
	
				        
				        HelveticaBold.setSize(11);
				        celltableindex0 = new PdfPCell(new Paragraph(description));
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setColspan(1);
				        celltableindex0.setRowspan(2);
//				        celltableindex0.setPaddingLeft(10);
//				        celltableindex0.setPaddingTop(10);
	//			        celltableindex0.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex0.addCell(celltableindex0);
			        
			        tableindex0.writeSelectedRows(-1, -1, -50, 749, cb);
			        
			        
			        PdfPTable tableindex1 = new PdfPTable(2);
			        tableindex1.setTotalWidth(new float[] { 295	,300});
			        tableindex1.setLockedWidth(true);
			        PdfPCell celltableindex1=null;
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(0, 147, 69);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        commonName = new Chunk(streamSelectorResultVO.get(1).getCommonName()+"\n \n", HelveticaBold);
			        desp = new Chunk(streamSelectorResultVO.get(1).getDescription(), Helvetica);
			        HelveticaBold.setSize(11);
//			        HelveticaBold.setColor(0, 147, 69);
			        coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(1).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(1).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
			        StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(1).getStream().trim()+".png";
					
//				        HelveticaBold.setSize(12);
				        celltableindex1 = new PdfPCell(new Paragraph(description));
				        celltableindex1.setFixedHeight(270f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setColspan(1);
				        celltableindex1.setPaddingLeft(10);
//				        celltableindex1.setPaddingTop(10);
	//			        celltableindex1.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex1.addCell(celltableindex1);
				        
				        Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
				        celltableindex1 = new PdfPCell(Icon_img);
				        celltableindex1.setFixedHeight(270f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	//			        celltableindex1.setColspan(1);
	//			        celltableindex1.setPaddingLeft(20);
	//			        celltableindex1.setPadding(10);
				        tableindex1.addCell(celltableindex1);
				        
			        tableindex1.writeSelectedRows(-1, -1, 40, 408, cb);
 
//--------------------------------------------------------------------Stream description 1 and 2 end ---------------------------------------------------------------------						         

				}
				
				
				if (i == 7) 
				{
					String StreamIconpath="";
					Image Icon_img=null;
					Paragraph description;
					Chunk commonName;
					Chunk desp ;
					Chunk coursesHeading ;
					Chunk coursesDesp;
					Chunk careerPotentialHeading;
					Chunk careerPotentialdesp;
					Phrase DespPhrase;
//--------------------------------------------------------------------Stream description 3 and 4 start ---------------------------------------------------------------------						         
					PdfPTable tableindex2 = new PdfPTable(2);
					tableindex2.setTotalWidth(new float[] { 325,300});
					tableindex2.setLockedWidth(true);
			        PdfPCell celltableindex2=null;
			       
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(5, 119, 128);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
					commonName = new Chunk(streamSelectorResultVO.get(2).getCommonName()+"\n\n", HelveticaBold);
					desp = new Chunk(streamSelectorResultVO.get(1).getDescription(), Helvetica);
				    HelveticaBold.setSize(11);
					coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(2).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(2).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(2).getStream().trim()+".png";
					
						Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
				        celltableindex2 = new PdfPCell(Icon_img);
				        celltableindex2.setFixedHeight(270f);
				        celltableindex2.setBorder(Rectangle.NO_BORDER);
				        celltableindex2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	//			        celltableindex2.setColspan(1);
//				        celltableindex2.setPaddingLeft(10);
	//			        celltableindex2.setPadding(10);
				        tableindex2.addCell(celltableindex2);
	
				        
//				        HelveticaBold.setSize(12);
				        celltableindex2 = new PdfPCell(new Paragraph(description));
				        celltableindex2.setFixedHeight(270f);
				        celltableindex2.setBorder(Rectangle.NO_BORDER);
				        celltableindex2.setColspan(1);
				        celltableindex2.setPaddingLeft(15);
//				        celltableindex2.setPaddingTop(10);
	//			        celltableindex0.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex2.addCell(celltableindex2);
			        
			        tableindex2.writeSelectedRows(-1, -1, -45, 790, cb);
			        
			        
			        PdfPTable tableindex3 = new PdfPTable(2);
			        tableindex3.setTotalWidth(new float[] { 295,300});
			        tableindex3.setLockedWidth(true);
			        PdfPCell celltableindex3=null;

			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(0, 147, 69);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);			        
			        commonName = new Chunk(streamSelectorResultVO.get(3).getCommonName()+"\n\n", HelveticaBold);
					desp = new Chunk(streamSelectorResultVO.get(3).getDescription(), Helvetica);
				    HelveticaBold.setSize(11);
					coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(3).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(3).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(3).getStream().trim()+".png";
					
//				        HelveticaBold.setSize(12);
				        celltableindex3 = new PdfPCell(new Paragraph(description));
				        celltableindex3.setFixedHeight(270f);
				        celltableindex3.setBorder(Rectangle.NO_BORDER);
				        celltableindex3.setColspan(1);
				        celltableindex3.setPaddingLeft(10);
//				        celltableindex3.setPaddingTop(10);
	//			        celltableindex1.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex3.addCell(celltableindex3);
				        
				        Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
				        celltableindex3 = new PdfPCell(Icon_img);
				        celltableindex3.setFixedHeight(270f);
				        celltableindex3.setBorder(Rectangle.NO_BORDER);
				        celltableindex3.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	//			        celltableindex1.setColspan(1);
	//			        celltableindex1.setPaddingLeft(20);
	//			        celltableindex1.setPadding(10);
				        tableindex3.addCell(celltableindex3);
				        
				    tableindex3.writeSelectedRows(-1, -1, 40, 408, cb);
			        
		 
//--------------------------------------------------------------------Stream description3 and 4 end ---------------------------------------------------------------------						         

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


	
	public ByteArrayOutputStream GeneratePDFReportSize3(StudentDetailsDTO studentDTO,
			List<StreamSelectorResultVO> streamSelectorResultVO, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize3 method");
		OUT.debug("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize3 studentDTO:{}", studentDTO);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template3.pdf");
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
					columnTable.setSimpleColumn(340, 340, 827, 827);
					columnTable.addElement(Pname);
					columnTable.go();
					
					String classVal =""; 
					switch(studentDTO.getClassId())
					{
					case  1 : classVal="9"; break;
					case  2 : classVal="10"; break;
					case  3 : classVal="11"; break;
					case  4 : classVal="12"; break;
					case  5 : classVal="12 Plus"; break;
					}
					 
					Paragraph Pclass = new Paragraph(classVal, OpensansBold);

					ColumnText columnTableCLass = new ColumnText(writer.getDirectContent());
					columnTableCLass.setSimpleColumn(340, 340, 793, 793);
					columnTableCLass.addElement(Pclass);
					columnTableCLass.go();

				}
				
				if (i == 4) 
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
					HelveticaBold.setColor(246, 146, 30);
					HelveticaBold.setSize(18f);
					raisecCode = new Paragraph(raisec, HelveticaBold);
					cellRIASEC = new PdfPCell(new Phrase(raisecCode));
					cellRIASEC.setFixedHeight(24.4f);
					cellRIASEC.setBorder(Rectangle.NO_BORDER);
					cellRIASEC.setColspan(1);
					cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellRIASEC.setPaddingLeft(5);
					tableRIASEC.addCell(cellRIASEC);
					tableRIASEC.writeSelectedRows(0, -1, 350, 730, cb);

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
					cellDESC.setColspan(1);
					cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellDESC.setPaddingLeft(12);
					cellDESC.setPaddingTop(2);
					tabledesc.addCell(cellDESC);

					tabledesc.writeSelectedRows(0, -1, 50, 685, cb);

//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar = new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
					PdfPCell cellChar = null;
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(239, 170, 3);
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

				if(i==5)
				{
					Paragraph mainStream=null;
					Paragraph subStream=null;
					
					String StreamIconpath="";
					Image Icon_img=null;
					float size=0f;
					String fitmentstr="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 179,50,160,150});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
			        Phrase subStreamphrase ;
					
// --------------------------------------------------------------------Stream Recommendation start---------------------------------------------------------------------
					index = 1;
				    for(StreamSelectorResultVO _SSRVO : streamSelectorResultVO) 
					 {
				    		StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.icon.DirectoryPath")+_SSRVO.getStream().trim()+".png";
				    		
							if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.HIGH.getScore())  {
								 fitmentstr="High Fitment";
							} 
							else if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.MEDIUM.getScore()){
								HelveticaBold.setColor(255, 128, 0);
								fitmentstr="Moderate Fitment";
							}

							
							subStreamphrase = new Phrase();
							if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHBIO.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Bio", HelveticaBold);
						        subStreamphrase = new Phrase();
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHOUTMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("without ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHLANGUAGES.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Languages", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHPSYCHOLOGY.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Psychology", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.GENERALARTS.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("General Arts", HelveticaBold);
						        subStreamphrase.add(c1);
						        
							}
								
							
							
							HelveticaBold.setSize(16);
							mainStream = new Paragraph(_SSRVO.getMainStream(),HelveticaBold);
					    	cell = new PdfPCell(new Phrase(mainStream));
					    	cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setPaddingLeft(40);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
							
					        
					        Icon_img = Image.getInstance(StreamIconpath);
					        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
					        cell = new PdfPCell(Icon_img);
					        cell.setFixedHeight(52.23f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setColspan(1);
//					        cell.setPaddingLeft(20);
//					        cell.setPadding(10);
					        table.addCell(cell);
					        
					        HelveticaBold.setSize(12);
							Helvetica.setSize(12);
							HelveticaBold.setColor(0, 0, 0);
				    		Helvetica.setColor(0, 0, 0);
					        subStream = new Paragraph(subStreamphrase);
					        cell = new PdfPCell(new Phrase(subStream));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
		
					        
					        Helvetica.setSize(12);
					        cell = new PdfPCell(new Phrase(fitmentstr,Helvetica));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
					        
					        table.writeSelectedRows(index-1, -1, 50, (656-size), cb);
					        index++;  
					        size+=58.56f;
					         
					    }

//--------------------------------------------------------------------Stream Recommendation end ---------------------------------------------------------------------						         
 				}

				if (i == 6) 
				{
					String StreamIconpath="";
					Image Icon_img=null;
					Paragraph description;
					Chunk commonName;
					Chunk desp ;
					Chunk coursesHeading ;
					Chunk coursesDesp;
					Chunk careerPotentialHeading;
					Chunk careerPotentialdesp;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 1 and 2 start ---------------------------------------------------------------------						         
			        
					PdfPTable tableindex0 = new PdfPTable(2);
					tableindex0.setTotalWidth(new float[] { 325,290});
					tableindex0.setLockedWidth(true);
			        PdfPCell celltableindex0=null;
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(167, 61, 46);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        commonName = new Chunk(streamSelectorResultVO.get(0).getCommonName()+"\n \n", HelveticaBold);
			        desp = new Chunk(streamSelectorResultVO.get(0).getDescription(), Helvetica);
			        HelveticaBold.setSize(11);
//			        HelveticaBold.setColor(167, 61, 46);
			        coursesHeading = new Chunk("\n\nCourses: ", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(0).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential: ", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(0).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setFirstLineIndent(5);
			        
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(0).getStream().trim()+".png";
					
						Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
//				        Icon_img.setTransparency(new int[] { 0xF0, 0xFF} );
//				        Icon_img.setScaleToFitHeight(false);
				        celltableindex0 = new PdfPCell(Icon_img);
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        celltableindex0.setColspan(1);
//				        celltableindex0.setPaddingLeft(10);
	//			        celltableindex0.setPadding(10);
				        tableindex0.addCell(celltableindex0);
	
				        
				        HelveticaBold.setSize(11);
				        celltableindex0 = new PdfPCell(new Paragraph(description));
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setColspan(1);
				        celltableindex0.setRowspan(2);
//				        celltableindex0.setPaddingLeft(10);
//				        celltableindex0.setPaddingTop(10);
	//			        celltableindex0.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex0.addCell(celltableindex0);
			        
			        tableindex0.writeSelectedRows(-1, -1, -50, 749, cb);
			        
			        
			        PdfPTable tableindex1 = new PdfPTable(2);
			        tableindex1.setTotalWidth(new float[] { 295	,300});
			        tableindex1.setLockedWidth(true);
			        PdfPCell celltableindex1=null;
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(0, 147, 69);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        commonName = new Chunk(streamSelectorResultVO.get(1).getCommonName()+"\n \n", HelveticaBold);
			        desp = new Chunk(streamSelectorResultVO.get(1).getDescription(), Helvetica);
			        HelveticaBold.setSize(11);
//			        HelveticaBold.setColor(0, 147, 69);
			        coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(1).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(1).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
			        StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(1).getStream().trim()+".png";
					
//				        HelveticaBold.setSize(12);
				        celltableindex1 = new PdfPCell(new Paragraph(description));
				        celltableindex1.setFixedHeight(270f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setColspan(1);
				        celltableindex1.setPaddingLeft(10);
//				        celltableindex1.setPaddingTop(10);
	//			        celltableindex1.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex1.addCell(celltableindex1);
				        
				        Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
				        celltableindex1 = new PdfPCell(Icon_img);
				        celltableindex1.setFixedHeight(270f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	//			        celltableindex1.setColspan(1);
	//			        celltableindex1.setPaddingLeft(20);
	//			        celltableindex1.setPadding(10);
				        tableindex1.addCell(celltableindex1);
				        
			        tableindex1.writeSelectedRows(-1, -1, 40, 408, cb);
 
//--------------------------------------------------------------------Stream description 1 and 2 end ---------------------------------------------------------------------						         

				}
				
				
				if (i == 7) 
				{
					String StreamIconpath="";
					Image Icon_img=null;
					Paragraph description;
					Chunk commonName;
					Chunk desp ;
					Chunk coursesHeading ;
					Chunk coursesDesp;
					Chunk careerPotentialHeading;
					Chunk careerPotentialdesp;
					Phrase DespPhrase;
//--------------------------------------------------------------------Stream description 3 start ---------------------------------------------------------------------						         
					PdfPTable tableindex2 = new PdfPTable(2);
					tableindex2.setTotalWidth(new float[] { 325,300});
					tableindex2.setLockedWidth(true);
			        PdfPCell celltableindex2=null;
			       
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(5, 119, 128);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
					commonName = new Chunk(streamSelectorResultVO.get(2).getCommonName()+"\n\n", HelveticaBold);
					desp = new Chunk(streamSelectorResultVO.get(1).getDescription(), Helvetica);
				    HelveticaBold.setSize(11);
					coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(2).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(2).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(2).getStream().trim()+".png";
					
						Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
				        celltableindex2 = new PdfPCell(Icon_img);
				        celltableindex2.setFixedHeight(270f);
				        celltableindex2.setBorder(Rectangle.NO_BORDER);
				        celltableindex2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	//			        celltableindex2.setColspan(1);
//				        celltableindex2.setPaddingLeft(10);
	//			        celltableindex2.setPadding(10);
				        tableindex2.addCell(celltableindex2);
	
				        
//				        HelveticaBold.setSize(12);
				        celltableindex2 = new PdfPCell(new Paragraph(description));
				        celltableindex2.setFixedHeight(270f);
				        celltableindex2.setBorder(Rectangle.NO_BORDER);
				        celltableindex2.setColspan(1);
				        celltableindex2.setPaddingLeft(15);
//				        celltableindex2.setPaddingTop(10);
	//			        celltableindex0.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex2.addCell(celltableindex2);
			        
			        tableindex2.writeSelectedRows(-1, -1, -45, 790, cb);
			        
//			        
//			        PdfPTable tableindex3 = new PdfPTable(2);
//			        tableindex3.setTotalWidth(new float[] { 295,300});
//			        tableindex3.setLockedWidth(true);
//			        PdfPCell celltableindex3=null;
//
//			        HelveticaBold.setSize(16);
//			        HelveticaBold.setColor(0, 147, 69);
//			        Helvetica.setSize(10);
//			        Helvetica.setColor(0,0,0);			        
//			        commonName = new Chunk(streamSelectorResultVO.get(3).getCommonName()+"\n\n", HelveticaBold);
//					desp = new Chunk(streamSelectorResultVO.get(3).getDescription(), Helvetica);
//				    HelveticaBold.setSize(11);
//					coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
//			        coursesDesp = new Chunk(streamSelectorResultVO.get(3).getCourses()+"\n \n", Helvetica);
//			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
//			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(3).getCareerPotential()+" \n", Helvetica);
//			        DespPhrase = new Phrase();
//			        DespPhrase.add(commonName);
//			        DespPhrase.add(desp);
//			        DespPhrase.add(coursesHeading);
//			        DespPhrase.add(coursesDesp);
//			        DespPhrase.add(careerPotentialHeading);
//			        DespPhrase.add(careerPotentialdesp);
//			        description = new Paragraph(new Phrase(DespPhrase));
//					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(3).getStream().trim()+".png";
//					
////				        HelveticaBold.setSize(12);
//				        celltableindex3 = new PdfPCell(new Paragraph(description));
//				        celltableindex3.setFixedHeight(270f);
//				        celltableindex3.setBorder(Rectangle.NO_BORDER);
//				        celltableindex3.setColspan(1);
//				        celltableindex3.setPaddingLeft(10);
////				        celltableindex3.setPaddingTop(10);
//	//			        celltableindex1.setBackgroundColor(new BaseColor(249,221,0));
//				        tableindex3.addCell(celltableindex3);
//				        
//				        Icon_img = Image.getInstance(StreamIconpath);
//				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
//				        celltableindex3 = new PdfPCell(Icon_img);
//				        celltableindex3.setFixedHeight(270f);
//				        celltableindex3.setBorder(Rectangle.NO_BORDER);
//				        celltableindex3.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//	//			        celltableindex1.setColspan(1);
//	//			        celltableindex1.setPaddingLeft(20);
//	//			        celltableindex1.setPadding(10);
//				        tableindex3.addCell(celltableindex3);
//				        
//				    tableindex3.writeSelectedRows(-1, -1, 40, 408, cb);
			        
		 
//--------------------------------------------------------------------Stream description 3  end ---------------------------------------------------------------------						         

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


	public ByteArrayOutputStream GeneratePDFReportSize2(StudentDetailsDTO studentDTO,
			List<StreamSelectorResultVO> streamSelectorResultVO, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize2 method");
		OUT.debug("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize2 studentDTO:{}", studentDTO);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template2.pdf");
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
					columnTable.setSimpleColumn(340, 340, 827, 827);
					columnTable.addElement(Pname);
					columnTable.go();
					
					String classVal =""; 
					switch(studentDTO.getClassId())
					{
					case  1 : classVal="9"; break;
					case  2 : classVal="10"; break;
					case  3 : classVal="11"; break;
					case  4 : classVal="12"; break;
					case  5 : classVal="12 Plus"; break;
					}
					 
					Paragraph Pclass = new Paragraph(classVal, OpensansBold);

					ColumnText columnTableCLass = new ColumnText(writer.getDirectContent());
					columnTableCLass.setSimpleColumn(340, 340, 793, 793);
					columnTableCLass.addElement(Pclass);
					columnTableCLass.go();

				}
				
				if (i == 4) 
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
					HelveticaBold.setColor(246, 146, 30);
					HelveticaBold.setSize(18f);
					raisecCode = new Paragraph(raisec, HelveticaBold);
					cellRIASEC = new PdfPCell(new Phrase(raisecCode));
					cellRIASEC.setFixedHeight(24.4f);
					cellRIASEC.setBorder(Rectangle.NO_BORDER);
					cellRIASEC.setColspan(1);
					cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellRIASEC.setPaddingLeft(5);
					tableRIASEC.addCell(cellRIASEC);
					tableRIASEC.writeSelectedRows(0, -1, 350, 730, cb);

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
					cellDESC.setColspan(1);
					cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellDESC.setPaddingLeft(12);
					cellDESC.setPaddingTop(2);
					tabledesc.addCell(cellDESC);

					tabledesc.writeSelectedRows(0, -1, 50, 685, cb);

//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar = new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
					PdfPCell cellChar = null;
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(239, 170, 3);
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

				if(i==5)
				{
					Paragraph mainStream=null;
					Paragraph subStream=null;
					
					String StreamIconpath="";
					Image Icon_img=null;
					float size=0f;
					String fitmentstr="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 179,50,160,150});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
			        Phrase subStreamphrase ;
					
// --------------------------------------------------------------------Stream Recommendation start---------------------------------------------------------------------
					index = 1;
				    for(StreamSelectorResultVO _SSRVO : streamSelectorResultVO) 
					 {
				    		StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.icon.DirectoryPath")+_SSRVO.getStream().trim()+".png";
				    		
							if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.HIGH.getScore())  {
								 fitmentstr="High Fitment";
							} 
							else if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.MEDIUM.getScore()){
								HelveticaBold.setColor(255, 128, 0);
								fitmentstr="Moderate Fitment";
							}

							
							subStreamphrase = new Phrase();
							if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHBIO.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Bio", HelveticaBold);
						        subStreamphrase = new Phrase();
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHOUTMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("without ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHLANGUAGES.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Languages", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHPSYCHOLOGY.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Psychology", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.GENERALARTS.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("General Arts", HelveticaBold);
						        subStreamphrase.add(c1);
						        
							}
								
							
							
							HelveticaBold.setSize(16);
							mainStream = new Paragraph(_SSRVO.getMainStream(),HelveticaBold);
					    	cell = new PdfPCell(new Phrase(mainStream));
					    	cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setPaddingLeft(40);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
							
					        
					        Icon_img = Image.getInstance(StreamIconpath);
					        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
					        cell = new PdfPCell(Icon_img);
					        cell.setFixedHeight(52.23f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setColspan(1);
//					        cell.setPaddingLeft(20);
//					        cell.setPadding(10);
					        table.addCell(cell);
					        
					        HelveticaBold.setSize(12);
							Helvetica.setSize(12);
							HelveticaBold.setColor(0, 0, 0);
				    		Helvetica.setColor(0, 0, 0);
					        subStream = new Paragraph(subStreamphrase);
					        cell = new PdfPCell(new Phrase(subStream));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
		
					        
					        Helvetica.setSize(12);
					        cell = new PdfPCell(new Phrase(fitmentstr,Helvetica));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
					        
					        table.writeSelectedRows(index-1, -1, 50, (637-size), cb);
					        index++;  
					        size+=58.56f;
					         
					    }

//--------------------------------------------------------------------Stream Recommendation end ---------------------------------------------------------------------						         
 				}

				if (i == 6) 
				{
					String StreamIconpath="";
					Image Icon_img=null;
					Paragraph description;
					Chunk commonName;
					Chunk desp ;
					Chunk coursesHeading ;
					Chunk coursesDesp;
					Chunk careerPotentialHeading;
					Chunk careerPotentialdesp;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 1 and 2 start ---------------------------------------------------------------------						         
			        
					PdfPTable tableindex0 = new PdfPTable(2);
					tableindex0.setTotalWidth(new float[] { 325,290});
					tableindex0.setLockedWidth(true);
			        PdfPCell celltableindex0=null;
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(167, 61, 46);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        commonName = new Chunk(streamSelectorResultVO.get(0).getCommonName()+"\n \n", HelveticaBold);
			        desp = new Chunk(streamSelectorResultVO.get(0).getDescription(), Helvetica);
			        HelveticaBold.setSize(11);
//			        HelveticaBold.setColor(167, 61, 46);
			        coursesHeading = new Chunk("\n\nCourses: ", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(0).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential: ", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(0).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setFirstLineIndent(5);
			        
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(0).getStream().trim()+".png";
					
						Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
//				        Icon_img.setTransparency(new int[] { 0xF0, 0xFF} );
//				        Icon_img.setScaleToFitHeight(false);
				        celltableindex0 = new PdfPCell(Icon_img);
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        celltableindex0.setColspan(1);
//				        celltableindex0.setPaddingLeft(10);
	//			        celltableindex0.setPadding(10);
				        tableindex0.addCell(celltableindex0);
	
				        
				        HelveticaBold.setSize(11);
				        celltableindex0 = new PdfPCell(new Paragraph(description));
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setColspan(1);
				        celltableindex0.setRowspan(2);
//				        celltableindex0.setPaddingLeft(10);
//				        celltableindex0.setPaddingTop(10);
	//			        celltableindex0.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex0.addCell(celltableindex0);
			        
			        tableindex0.writeSelectedRows(-1, -1, -50, 749, cb);
			        
			        
			        PdfPTable tableindex1 = new PdfPTable(2);
			        tableindex1.setTotalWidth(new float[] { 295	,300});
			        tableindex1.setLockedWidth(true);
			        PdfPCell celltableindex1=null;
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(0, 147, 69);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        commonName = new Chunk(streamSelectorResultVO.get(1).getCommonName()+"\n \n", HelveticaBold);
			        desp = new Chunk(streamSelectorResultVO.get(1).getDescription(), Helvetica);
			        HelveticaBold.setSize(11);
//			        HelveticaBold.setColor(0, 147, 69);
			        coursesHeading = new Chunk("\n\nCourses:", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(1).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential:", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(1).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
			        StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(1).getStream().trim()+".png";
					
//				        HelveticaBold.setSize(12);
				        celltableindex1 = new PdfPCell(new Paragraph(description));
				        celltableindex1.setFixedHeight(270f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setColspan(1);
				        celltableindex1.setPaddingLeft(10);
//				        celltableindex1.setPaddingTop(10);
	//			        celltableindex1.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex1.addCell(celltableindex1);
				        
				        Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
				        celltableindex1 = new PdfPCell(Icon_img);
				        celltableindex1.setFixedHeight(270f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	//			        celltableindex1.setColspan(1);
	//			        celltableindex1.setPaddingLeft(20);
	//			        celltableindex1.setPadding(10);
				        tableindex1.addCell(celltableindex1);
				        
			        tableindex1.writeSelectedRows(-1, -1, 40, 408, cb);
 
//--------------------------------------------------------------------Stream description 1 and 2 end ---------------------------------------------------------------------						         

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

	

	public ByteArrayOutputStream GeneratePDFReportSize1(StudentDetailsDTO studentDTO,
			List<StreamSelectorResultVO> streamSelectorResultVO, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize1 method");
		OUT.debug("StreamSelectorPDFGenerationService : inside GeneratePDFReportSize1 studentDTO:{}", studentDTO);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template1.pdf");
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
					columnTable.setSimpleColumn(340, 340, 827, 827);
					columnTable.addElement(Pname);
					columnTable.go();
					
					String classVal =""; 
					switch(studentDTO.getClassId())
					{
					case  1 : classVal="9"; break;
					case  2 : classVal="10"; break;
					case  3 : classVal="11"; break;
					case  4 : classVal="12"; break;
					case  5 : classVal="12 Plus"; break;
					}
					 
					Paragraph Pclass = new Paragraph(classVal, OpensansBold);

					ColumnText columnTableCLass = new ColumnText(writer.getDirectContent());
					columnTableCLass.setSimpleColumn(340, 340, 793, 793);
					columnTableCLass.addElement(Pclass);
					columnTableCLass.go();

				}
				
				if (i == 4) 
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
					HelveticaBold.setColor(246, 146, 30);
					HelveticaBold.setSize(18f);
					raisecCode = new Paragraph(raisec, HelveticaBold);
					cellRIASEC = new PdfPCell(new Phrase(raisecCode));
					cellRIASEC.setFixedHeight(24.4f);
					cellRIASEC.setBorder(Rectangle.NO_BORDER);
					cellRIASEC.setColspan(1);
					cellRIASEC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellRIASEC.setPaddingLeft(5);
					tableRIASEC.addCell(cellRIASEC);
					tableRIASEC.writeSelectedRows(0, -1, 350, 730, cb);

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
					cellDESC.setColspan(1);
					cellDESC.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellDESC.setPaddingLeft(12);
					cellDESC.setPaddingTop(2);
					tabledesc.addCell(cellDESC);

					tabledesc.writeSelectedRows(0, -1, 50, 685, cb);

//=============================================================================end RIASEC desc=================================================================
//==========================================================================start RIASEC character====================================================================				
					PdfPTable tablechar = new PdfPTable(1);
					tablechar.setTotalWidth(new float[] { 400 });
					tablechar.setLockedWidth(true);
					PdfPCell cellChar = null;
					OUT.debug("raisec:{}", raisec);
					HelveticaBold.setColor(239, 170, 3);
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

				if(i==5)
				{
					Paragraph mainStream=null;
					Paragraph subStream=null;
					
					String StreamIconpath="";
					Image Icon_img=null;
					float size=0f;
					String fitmentstr="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 179,50,160,150});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
			        Phrase subStreamphrase ;
					
// --------------------------------------------------------------------Stream Recommendation start---------------------------------------------------------------------
					index = 1;
				    for(StreamSelectorResultVO _SSRVO : streamSelectorResultVO) 
					 {
				    		StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.icon.DirectoryPath")+_SSRVO.getStream().trim()+".png";
				    		
							if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.HIGH.getScore())  {
								 fitmentstr="High Fitment";
							} 
							else if (_SSRVO.getStreamFitment() == ApplicationConstants.PRIORITY.MEDIUM.getScore()){
								HelveticaBold.setColor(255, 128, 0);
								fitmentstr="Moderate Fitment";
							}

							
							subStreamphrase = new Phrase();
							if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHBIO.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Bio", HelveticaBold);
						        subStreamphrase = new Phrase();
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.SCIENCEWITHMATHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Science ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.COMMERCEWITHOUTMATH.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Commerce ", HelveticaBold);
						        Chunk c2 = new Chunk("without ", Helvetica);
						        Chunk c3 = new Chunk("Maths", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHDESIGN.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Design", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHLANGUAGES.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Languages", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.ARTSWITHPSYCHOLOGY.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("Arts ", HelveticaBold);
						        Chunk c2 = new Chunk("with ", Helvetica);
						        Chunk c3 = new Chunk("Psychology", HelveticaBold);
						        subStreamphrase.add(c1);
						        subStreamphrase.add(c2);
						        subStreamphrase.add(c3);
						       
							}
							else if(_SSRVO.getStream().equalsIgnoreCase(STREAM.GENERALARTS.getName()))
							{
								HelveticaBold.setSize(12);
								Helvetica.setSize(12);
								HelveticaBold.setColor(0, 0, 0);
					    		Helvetica.setColor(0, 0, 0);
								Chunk c1 = new Chunk("General Arts", HelveticaBold);
						        subStreamphrase.add(c1);
						        
							}
								
							
							
							HelveticaBold.setSize(16);
							mainStream = new Paragraph(_SSRVO.getMainStream(),HelveticaBold);
					    	cell = new PdfPCell(new Phrase(mainStream));
					    	cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setPaddingLeft(40);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
							
					        
					        Icon_img = Image.getInstance(StreamIconpath);
					        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
					        cell = new PdfPCell(Icon_img);
					        cell.setFixedHeight(52.23f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					        cell.setColspan(1);
//					        cell.setPaddingLeft(20);
//					        cell.setPadding(10);
					        table.addCell(cell);
					        
					        HelveticaBold.setSize(12);
							Helvetica.setSize(12);
							HelveticaBold.setColor(0, 0, 0);
				    		Helvetica.setColor(0, 0, 0);
					        subStream = new Paragraph(subStreamphrase);
					        cell = new PdfPCell(new Phrase(subStream));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
		
					        
					        Helvetica.setSize(12);
					        cell = new PdfPCell(new Phrase(fitmentstr,Helvetica));
					        cell.setFixedHeight(26.6f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(5);
					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
					        
					        table.writeSelectedRows(index-1, -1, 50, (637-size), cb);
					        index++;  
					        size+=58.56f;
					         
					    }

//--------------------------------------------------------------------Stream Recommendation end ---------------------------------------------------------------------						         
 				}

				if (i == 6) 
				{
					String StreamIconpath="";
					Image Icon_img=null;
					Paragraph description;
					Chunk commonName;
					Chunk desp ;
					Chunk coursesHeading ;
					Chunk coursesDesp;
					Chunk careerPotentialHeading;
					Chunk careerPotentialdesp;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 1  start ---------------------------------------------------------------------						         
			        
					PdfPTable tableindex0 = new PdfPTable(2);
					tableindex0.setTotalWidth(new float[] { 325,290});
					tableindex0.setLockedWidth(true);
			        PdfPCell celltableindex0=null;
			        HelveticaBold.setSize(16);
			        HelveticaBold.setColor(167, 61, 46);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        commonName = new Chunk(streamSelectorResultVO.get(0).getCommonName()+"\n \n", HelveticaBold);
			        desp = new Chunk(streamSelectorResultVO.get(0).getDescription(), Helvetica);
			        HelveticaBold.setSize(11);
//			        HelveticaBold.setColor(167, 61, 46);
			        coursesHeading = new Chunk("\n\nCourses: ", HelveticaBold);
			        coursesDesp = new Chunk(streamSelectorResultVO.get(0).getCourses()+"\n \n", Helvetica);
			        careerPotentialHeading = new Chunk("Career Potential: ", HelveticaBold);
			        careerPotentialdesp = new Chunk(streamSelectorResultVO.get(0).getCareerPotential()+" \n", Helvetica);
			        DespPhrase = new Phrase();
			        DespPhrase.add(commonName);
			        DespPhrase.add(desp);
			        DespPhrase.add(coursesHeading);
			        DespPhrase.add(coursesDesp);
			        DespPhrase.add(careerPotentialHeading);
			        DespPhrase.add(careerPotentialdesp);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setFirstLineIndent(5);
			        
					StreamIconpath=ApplicationProperties.getInstance().getProperty("com.streamselector.DescriptionImage.DirectoryPath")+streamSelectorResultVO.get(0).getStream().trim()+".png";
					
						Icon_img = Image.getInstance(StreamIconpath);
				        Icon_img.setAlignment(Element.ALIGN_MIDDLE);
//				        Icon_img.setTransparency(new int[] { 0xF0, 0xFF} );
//				        Icon_img.setScaleToFitHeight(false);
				        celltableindex0 = new PdfPCell(Icon_img);
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        celltableindex0.setColspan(1);
//				        celltableindex0.setPaddingLeft(10);
	//			        celltableindex0.setPadding(10);
				        tableindex0.addCell(celltableindex0);
	
				        
				        HelveticaBold.setSize(11);
				        celltableindex0 = new PdfPCell(new Paragraph(description));
				        celltableindex0.setFixedHeight(270f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setColspan(1);
				        celltableindex0.setRowspan(2);
//				        celltableindex0.setPaddingLeft(10);
//				        celltableindex0.setPaddingTop(10);
	//			        celltableindex0.setBackgroundColor(new BaseColor(249,221,0));
				        tableindex0.addCell(celltableindex0);
			        
			        tableindex0.writeSelectedRows(-1, -1, -50, 749, cb);
 
//--------------------------------------------------------------------Stream description 1 and 2 end ---------------------------------------------------------------------						         

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

		Rectangle pagesize = document.getPageSize();
		Image image = null;
		try {
			image = Image.getInstance(
					properties.getProperty("com.edupath.report.template.DirectoryPath") + "bottom-pic.png");

			image.scaleAbsolute(pagesize.getWidth(), 99);
			image.setAbsolutePosition(0, 0);

			PdfContentByte cb = pdfWriter.getDirectContentUnder();

			if ((GeneratePDFReportFile.PageNo == 14 || GeneratePDFReportFile.PageNo == 15
					|| GeneratePDFReportFile.PageNo == 16 || GeneratePDFReportFile.PageNo == 17
					|| GeneratePDFReportFile.PageNo == 18) && GeneratePDFReportFile.LastPageNo == false) {

				cb.addImage(image);
			}

			Font OpensansRegularFont = FontFactory
					.getFont(properties.getProperty("com.edupath.report.template.DirectoryPath")
							+ "open-sans/OpenSans-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
			OpensansRegularFont.setSize(9.5f);
			OpensansRegularFont.setColor(255, 255, 255);

			if (GeneratePDFReportFile.PageNo != 2 && GeneratePDFReportFile.LastPageNo == false) {

				Chunk Page_NO = new Chunk(String.format("%02d", pdfWriter.getPageNumber()), OpensansRegularFont);

				ColumnText columnTable = new ColumnText(pdfWriter.getDirectContent());
				columnTable.setSimpleColumn(500, 0, 54, 30);
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

	public CustomBorder(LineDash left, LineDash right, LineDash top, LineDash bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
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
