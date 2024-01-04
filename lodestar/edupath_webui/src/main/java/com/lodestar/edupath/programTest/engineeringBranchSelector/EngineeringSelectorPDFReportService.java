package com.lodestar.edupath.programTest.engineeringBranchSelector;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Anchor;
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
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEdupathDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEntExamDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;

public class EngineeringSelectorPDFReportService
{
	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(EngineeringSelectorPDFReportService.class);
	public static int PageNo = 0;
	public static boolean LastPageNo = false;
	private ByteArrayOutputStream baos = null;
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	private String inputFilePathFinalReport = properties.getProperty("com.edupath.Engineering.Branch.template.DirectoryPath");
	private int index = 0;
	// Font imports
	private Font OpensansBold= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"OpenSans-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font Helvetica= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaBold= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	private Font HelveticaBoldOccName= FontFactory.getFont(properties.getProperty("com.DailyHunt.pdf.font.DirectoryPath")+"Helvetica-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
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
	
	
	
	public ByteArrayOutputStream GeneratePDFReport(StudentDetailsDTO studentDTO,
			List<OccupationDTO> cgtOcc, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("EngineeringSelectorPDFReportService : inside GeneratePDFReport method");
		OUT.debug("EngineeringSelectorPDFReportService : inside GeneratePDFReport studentDTO:{}", studentDTO);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template.pdf");
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
					HelveticaBold.setColor(255, 128, 0);
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
					HelveticaBold.setColor(255, 128, 0);
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
					Paragraph _EB=null;
					Paragraph _EBRB=null;
					float size=0f;
					PdfPTable table = new PdfPTable(2);
			        table.setTotalWidth(new float[] { 160,350});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
// --------------------------------------------------------------------Stream Recommendation start---------------------------------------------------------------------
					index = 1;
				    for(OccupationDTO _CGTOCC : cgtOcc) 
					 {
				    		 							
							
							HelveticaBold.setSize(12);
							HelveticaBold.setColor(255, 128, 0);
							_EB = new Paragraph(_CGTOCC.getEngineeringDegree(),HelveticaBold);
					    	cell = new PdfPCell(new Phrase(_EB));
					    	cell.setFixedHeight(57.321f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//					        cell.setPaddingLeft(40);
//					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
							
					        
				    		_EBRB = new Paragraph(_CGTOCC.getEngineeringRelatedCourses(),HelveticaBold);
					        cell = new PdfPCell(new Phrase(_EBRB));
					        cell.setFixedHeight(57.321f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(15);
//					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
		
					        
					        table.writeSelectedRows(index-1, -1, 70, (690-size), cb);
					        index++;  
					        size+=57.321f;
					         
					    }

//--------------------------------------------------------------------Stream Recommendation end ---------------------------------------------------------------------						         
 				}

				if (i == 6) 
				{
					String EBIconpath="";
					EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(0).getEngineeringDegree().trim()+".png";
					Image Icon_img1=null;
					Icon_img1 = Image.getInstance(EBIconpath);
					Icon_img1.setAbsolutePosition(-60, 495);
					Icon_img1.scaleAbsolute(230f ,231f);
					document.add(Icon_img1); 
					
					Paragraph description;
					Chunk degreeName;
					Chunk despHeading ;
					Chunk desp ;
					Chunk subjectHeading ;
					Chunk subject;
					Chunk skillsHeading;
					Chunk skills;
					Chunk careerScopeHeading;
					Chunk careerScope;
					Chunk EducationHeading;
					Chunk Education;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 1 and 2 start ---------------------------------------------------------------------						         
			        
					PdfPTable tableindex0 = new PdfPTable(1);
					tableindex0.setTotalWidth(new float[] { 350});
					tableindex0.setLockedWidth(true);
			        PdfPCell celltableindex0=null;
			        HelveticaBoldDegreeName.setSize(16);
			        HelveticaBoldDegreeName.setColor(255, 128, 0);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        degreeName = new Chunk(cgtOcc.get(0).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
			        HelveticaBold.setSize(10);
			        HelveticaBold.setColor(0,0,0);
			        despHeading= new Chunk("Description ", HelveticaBold);
			        desp = new Chunk(cgtOcc.get(0).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
			        
			        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
			        subject = new Chunk(cgtOcc.get(0).getEngineeringDegreeSubjects().trim()+"\n ", Helvetica);
			        
			        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
			        skills = new Chunk(cgtOcc.get(0).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
			        
			        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
			        careerScope = new Chunk(cgtOcc.get(0).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
			        
			        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
			        Education = new Chunk(cgtOcc.get(0).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
			        
			        
			        
			        DespPhrase = new Phrase();
			        DespPhrase.add(degreeName);
			        DespPhrase.add(despHeading);
			        DespPhrase.add(desp);
			        DespPhrase.add(subjectHeading);
			        DespPhrase.add(subject);
			        if(cgtOcc.get(0).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(0).getEngineeringDegreeSkills().trim().equals(""))
			        {
				        DespPhrase.add(skillsHeading);
				        DespPhrase.add(skills);
			        }
			        DespPhrase.add(careerScopeHeading);
			        DespPhrase.add(careerScope);
			        DespPhrase.add(EducationHeading);
			        DespPhrase.add(Education);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setAlignment(Element.ALIGN_LEFT);

	 
				        celltableindex0 = new PdfPCell(new Paragraph(description));
				        celltableindex0.setFixedHeight(278.715f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setLeading(3, 0.9f);
				        tableindex0.addCell(celltableindex0);
			        tableindex0.writeSelectedRows(-1, -1, 190, 730, cb);
			        
			        
			        PdfPTable tableindex1 = new PdfPTable(1);
			        tableindex1.setTotalWidth(new float[] { 350	});
			        tableindex1.setLockedWidth(true);
			        PdfPCell celltableindex1=null;
			        HelveticaBoldDegreeName.setSize(16);
			        HelveticaBoldDegreeName.setColor(255, 128, 0);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        degreeName = new Chunk(cgtOcc.get(1).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
			        HelveticaBold.setSize(10);
			        HelveticaBold.setColor(0,0,0);
			        despHeading= new Chunk("Description ", HelveticaBold);
			        desp = new Chunk(cgtOcc.get(1).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
			        
			        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
			        subject = new Chunk(cgtOcc.get(1).getEngineeringDegreeSubjects().trim()+"\n", Helvetica);
			        
			        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
			        skills = new Chunk(cgtOcc.get(1).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
			        
			        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
			        careerScope = new Chunk(cgtOcc.get(1).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
			        
			        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
			        Education = new Chunk(cgtOcc.get(1).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
			        
			        DespPhrase = new Phrase();
			        DespPhrase.add(degreeName);
			        DespPhrase.add(despHeading);
			        DespPhrase.add(desp);
			        DespPhrase.add(subjectHeading);
			        DespPhrase.add(subject);
			        if(cgtOcc.get(1).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(1).getEngineeringDegreeSkills().trim().equals(""))
			        {
				        DespPhrase.add(skillsHeading);
				        DespPhrase.add(skills);
			        }
			        DespPhrase.add(careerScopeHeading);
			        DespPhrase.add(careerScope);
			        DespPhrase.add(EducationHeading);
			        DespPhrase.add(Education);
			        description = new Paragraph(new Phrase(DespPhrase));
					
				        celltableindex1 = new PdfPCell(new Paragraph(description));
				        celltableindex1.setFixedHeight(278.715f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setLeading(3, 0.9f);
				        tableindex1.addCell(celltableindex1);
				        
				        
			        tableindex1.writeSelectedRows(-1, -1, 40, 408, cb);

			        EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(1).getEngineeringDegree().trim()+".png";
			        Image Icon_img2=null;
			        Icon_img2 = Image.getInstance(EBIconpath);
					Icon_img2 = Image.getInstance(EBIconpath);
					Icon_img2.setAbsolutePosition(425, 101);
					Icon_img2.scaleAbsolute(230f ,235f);
					document.add(Icon_img2); 
 
//--------------------------------------------------------------------Stream description 1 and 2 end ---------------------------------------------------------------------						         

				}
				
				
				if (i == 7) 
				{
					String EBIconpath="";
					EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(2).getEngineeringDegree().trim()+".png";
					Image Icon_img1=null;
					Icon_img1 = Image.getInstance(EBIconpath);
					Icon_img1.setAbsolutePosition(-60, 495);
					Icon_img1.scaleAbsolute(230f ,231f);
					document.add(Icon_img1); 
					
					Paragraph description;
					Chunk degreeName;
					Chunk despHeading ;
					Chunk desp ;
					Chunk subjectHeading ;
					Chunk subject;
					Chunk skillsHeading;
					Chunk skills;
					Chunk careerScopeHeading;
					Chunk careerScope;
					Chunk EducationHeading;
					Chunk Education;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 3 and 4 start ---------------------------------------------------------------------						         
			        
					PdfPTable tableindex0 = new PdfPTable(1);
					tableindex0.setTotalWidth(new float[] { 350});
					tableindex0.setLockedWidth(true);
			        PdfPCell celltableindex0=null;
			        HelveticaBoldDegreeName.setSize(16);
			        HelveticaBoldDegreeName.setColor(255, 128, 0);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        degreeName = new Chunk(cgtOcc.get(2).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
			        HelveticaBold.setSize(10);
			        HelveticaBold.setColor(0,0,0);
			        despHeading= new Chunk("Description ", HelveticaBold);
			        desp = new Chunk(cgtOcc.get(2).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
			        
			        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
			        subject = new Chunk(cgtOcc.get(2).getEngineeringDegreeSubjects().trim()+"\n ", Helvetica);
			        
			        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
			        skills = new Chunk(cgtOcc.get(2).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
			        
			        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
			        careerScope = new Chunk(cgtOcc.get(2).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
			        
			        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
			        Education = new Chunk(cgtOcc.get(2).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
			        
			        
			        
			        DespPhrase = new Phrase();
			        DespPhrase.add(degreeName);
			        DespPhrase.add(despHeading);
			        DespPhrase.add(desp);
			        DespPhrase.add(subjectHeading);
			        DespPhrase.add(subject);
			        if(cgtOcc.get(2).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(2).getEngineeringDegreeSkills().trim().equals(""))
			        {
				        DespPhrase.add(skillsHeading);
				        DespPhrase.add(skills);
			        }
			        DespPhrase.add(careerScopeHeading);
			        DespPhrase.add(careerScope);
			        DespPhrase.add(EducationHeading);
			        DespPhrase.add(Education);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setAlignment(Element.ALIGN_LEFT);
	 
				        
				        celltableindex0 = new PdfPCell(new Paragraph(description));
				        celltableindex0.setFixedHeight(278.715f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setLeading(3, 0.9f);
				        tableindex0.addCell(celltableindex0);
			        
			        tableindex0.writeSelectedRows(-1, -1, 190, 730, cb);
			        
			        
			        PdfPTable tableindex1 = new PdfPTable(1);
			        tableindex1.setTotalWidth(new float[] { 350	});
			        tableindex1.setLockedWidth(true);
			        PdfPCell celltableindex1=null;
			        HelveticaBoldDegreeName.setSize(16);
			        HelveticaBoldDegreeName.setColor(255, 128, 0);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        degreeName = new Chunk(cgtOcc.get(3).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
			        HelveticaBold.setSize(10);
			        HelveticaBold.setColor(0,0,0);
			        despHeading= new Chunk("Description ", HelveticaBold);
			        desp = new Chunk(cgtOcc.get(3).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
			        
			        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
			        subject = new Chunk(cgtOcc.get(3).getEngineeringDegreeSubjects().trim()+"\n", Helvetica);
			        
			        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
			        skills = new Chunk(cgtOcc.get(3).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
			        
			        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
			        careerScope = new Chunk(cgtOcc.get(3).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
			        
			        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
			        Education = new Chunk(cgtOcc.get(3).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
			        
			        
			        
			        DespPhrase = new Phrase();
			        DespPhrase.add(degreeName);
			        DespPhrase.add(despHeading);
			        DespPhrase.add(desp);
			        DespPhrase.add(subjectHeading);
			        DespPhrase.add(subject);
			        if(cgtOcc.get(3).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(3).getEngineeringDegreeSkills().trim().equals(""))
			        {
				        DespPhrase.add(skillsHeading);
				        DespPhrase.add(skills);
			        }
			        DespPhrase.add(careerScopeHeading);
			        DespPhrase.add(careerScope);
			        DespPhrase.add(EducationHeading);
			        DespPhrase.add(Education);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setAlignment(Element.ALIGN_LEFT);
			        
					
				        celltableindex1 = new PdfPCell(new Paragraph(description));
				        celltableindex1.setFixedHeight(278.715f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setLeading(3, 0.9f);
				        tableindex1.addCell(celltableindex1);
				        
				        
			        tableindex1.writeSelectedRows(-1, -1, 40, 408, cb);

			        EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(3).getEngineeringDegree().trim()+".png";
			        Image Icon_img2=null;
			        Icon_img2 = Image.getInstance(EBIconpath);
					Icon_img2 = Image.getInstance(EBIconpath);
					Icon_img2.setAbsolutePosition(425, 101);
					Icon_img2.scaleAbsolute(230f ,235f);
					document.add(Icon_img2); 
 
//--------------------------------------------------------------------Stream description 3 and 4 end ---------------------------------------------------------------------						         

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




	public ByteArrayOutputStream GeneratePDFReportSET1A(StudentDetailsDTO studentDTO,
			List<OccupationDTO> cgtOcc, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score, String webURL ) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("EngineeringSelectorPDFReportService : inside GeneratePDFReport method");
		OUT.debug("EngineeringSelectorPDFReportService : inside GeneratePDFReport studentDTO:{},webURL:{}", studentDTO,webURL);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template_Set1A.pdf");
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
					HelveticaBold.setColor(255, 128, 0);
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
					HelveticaBold.setColor(255, 128, 0);
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
					Paragraph _EB=null;
					Paragraph _EBRB=null;
					Paragraph _comment=null;
					float size=0f;
					float _bgsize=0f;
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 148,245,100});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
// --------------------------------------------------------------------Stream Recommendation start---------------------------------------------------------------------
					index = 1;
				    for(OccupationDTO _CGTOCC : cgtOcc) 
					 {
				    		 							
							
							HelveticaBold.setSize(10);
							HelveticaBold.setColor(230, 128, 0);
							_EB = new Paragraph(_CGTOCC.getEngineeringDegree(),HelveticaBold);
					    	cell = new PdfPCell(new Phrase(_EB));
					    	cell.setFixedHeight(47.321f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//					        cell.setPaddingLeft(40);
//					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
							
					        HelveticaBold.setSize(8);
							HelveticaBold.setColor(255, 128, 0);
				    		_EBRB = new Paragraph(_CGTOCC.getEngineeringRelatedCourses(),HelveticaBold);
					        cell = new PdfPCell(new Phrase(_EBRB));
					        cell.setFixedHeight(47.321f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(15);
//					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
					        
					        
					        Helvetica.setSize(10);
					        Helvetica.setColor(109, 110, 113);
					        if(_CGTOCC.getEngineeringPriority()>=12)
					        {
					        	_comment = new Paragraph("Aptitude Improvement",Helvetica);
					        }else
					        {
					        	_comment = new Paragraph("				",Helvetica);
					        }
					        cell = new PdfPCell(new Phrase(_comment));
					        cell.setFixedHeight(47.321f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(15);
//					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
		
					        
					        table.writeSelectedRows(index-1, -1, 70, (698-size), cb);
					         
					        
					        
					        String EBIconpath="";
					        if( index % 2 == 0)
					        {
					        	EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+"EBS_ORANGE_BAR.png";
					        }
					        else 
					        {
					        	EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+"EBS_BLUE_BAR.png";
					        }
					        Image Icon_img1=null;
							Icon_img1 = Image.getInstance(EBIconpath);
							Icon_img1.setAbsolutePosition(50,( 665-_bgsize));
							Icon_img1.scaleAbsolute(497.132f ,48.5f);
							document.add(Icon_img1);
							size+=47.321f;
							_bgsize+=49.321f;
							 index++;
							 
							 
							 
					         
					    }
				      
			        Paragraph link = new Paragraph();
			        HelveticaBold.setSize(10);
			        HelveticaBold.setColor(255,128,0);
			        Anchor anchor = new Anchor("Lodestar Aptitude Development Guide",HelveticaBold);
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
			        tableLink.writeSelectedRows(-1, -1, 152, 174, cb);

//--------------------------------------------------------------------Stream Recommendation end ---------------------------------------------------------------------						         
 				}

				if (i == 6) 
				{
					String EBIconpath="";
					EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(0).getEngineeringDegree().trim()+".png";
					Image Icon_img1=null;
					Icon_img1 = Image.getInstance(EBIconpath);
					Icon_img1.setAbsolutePosition(-60, 495);
					Icon_img1.scaleAbsolute(230f ,231f);
					document.add(Icon_img1); 
					
					Paragraph description;
					Chunk degreeName;
					Chunk despHeading ;
					Chunk desp ;
					Chunk subjectHeading ;
					Chunk subject;
					Chunk skillsHeading;
					Chunk skills;
					Chunk careerScopeHeading;
					Chunk careerScope;
					Chunk EducationHeading;
					Chunk Education;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 1 and 2 start ---------------------------------------------------------------------						         
			        
					PdfPTable tableindex0 = new PdfPTable(1);
					tableindex0.setTotalWidth(new float[] { 350});
					tableindex0.setLockedWidth(true);
			        PdfPCell celltableindex0=null;
			        HelveticaBoldDegreeName.setSize(16);
			        HelveticaBoldDegreeName.setColor(255, 128, 0);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        degreeName = new Chunk(cgtOcc.get(0).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
			        HelveticaBold.setSize(10);
			        HelveticaBold.setColor(0,0,0);
			        despHeading= new Chunk("Description ", HelveticaBold);
			        desp = new Chunk(cgtOcc.get(0).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
			        
			        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
			        subject = new Chunk(cgtOcc.get(0).getEngineeringDegreeSubjects().trim()+"\n ", Helvetica);
			        
			        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
			        skills = new Chunk(cgtOcc.get(0).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
			        
			        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
			        careerScope = new Chunk(cgtOcc.get(0).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
			        
			        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
			        Education = new Chunk(cgtOcc.get(0).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
			        
			        
			        
			        DespPhrase = new Phrase();
			        DespPhrase.add(degreeName);
			        DespPhrase.add(despHeading);
			        DespPhrase.add(desp);
			        DespPhrase.add(subjectHeading);
			        DespPhrase.add(subject);
			        if(cgtOcc.get(0).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(0).getEngineeringDegreeSkills().trim().equals(""))
			        {
				        DespPhrase.add(skillsHeading);
				        DespPhrase.add(skills);
			        }
			        DespPhrase.add(careerScopeHeading);
			        DespPhrase.add(careerScope);
			        DespPhrase.add(EducationHeading);
			        DespPhrase.add(Education);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setAlignment(Element.ALIGN_LEFT);

	 
				        celltableindex0 = new PdfPCell(new Paragraph(description));
				        celltableindex0.setFixedHeight(278.715f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setLeading(3, 0.9f);
				        tableindex0.addCell(celltableindex0);
			        tableindex0.writeSelectedRows(-1, -1, 190, 730, cb);
			        
			        
			        PdfPTable tableindex1 = new PdfPTable(1);
			        tableindex1.setTotalWidth(new float[] { 350	});
			        tableindex1.setLockedWidth(true);
			        PdfPCell celltableindex1=null;
			        HelveticaBoldDegreeName.setSize(16);
			        HelveticaBoldDegreeName.setColor(255, 128, 0);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        degreeName = new Chunk(cgtOcc.get(1).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
			        HelveticaBold.setSize(10);
			        HelveticaBold.setColor(0,0,0);
			        despHeading= new Chunk("Description ", HelveticaBold);
			        desp = new Chunk(cgtOcc.get(1).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
			        
			        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
			        subject = new Chunk(cgtOcc.get(1).getEngineeringDegreeSubjects().trim()+"\n", Helvetica);
			        
			        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
			        skills = new Chunk(cgtOcc.get(1).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
			        
			        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
			        careerScope = new Chunk(cgtOcc.get(1).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
			        
			        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
			        Education = new Chunk(cgtOcc.get(1).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
			        
			        DespPhrase = new Phrase();
			        DespPhrase.add(degreeName);
			        DespPhrase.add(despHeading);
			        DespPhrase.add(desp);
			        DespPhrase.add(subjectHeading);
			        DespPhrase.add(subject);
			        if(cgtOcc.get(1).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(1).getEngineeringDegreeSkills().trim().equals(""))
			        {
				        DespPhrase.add(skillsHeading);
				        DespPhrase.add(skills);
			        }
			        DespPhrase.add(careerScopeHeading);
			        DespPhrase.add(careerScope);
			        DespPhrase.add(EducationHeading);
			        DespPhrase.add(Education);
			        description = new Paragraph(new Phrase(DespPhrase));
					
				        celltableindex1 = new PdfPCell(new Paragraph(description));
				        celltableindex1.setFixedHeight(278.715f);
				        celltableindex1.setBorder(Rectangle.NO_BORDER);
				        celltableindex1.setLeading(3, 0.9f);
				        tableindex1.addCell(celltableindex1);
				        
				        
			        tableindex1.writeSelectedRows(-1, -1, 40, 408, cb);

			        EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(1).getEngineeringDegree().trim()+".png";
			        Image Icon_img2=null;
			        Icon_img2 = Image.getInstance(EBIconpath);
					Icon_img2 = Image.getInstance(EBIconpath);
					Icon_img2.setAbsolutePosition(425, 101);
					Icon_img2.scaleAbsolute(230f ,235f);
					document.add(Icon_img2); 
 
//--------------------------------------------------------------------Stream description 1 and 2 end ---------------------------------------------------------------------						         

				}
				
				
				if (i == 7) 
				{
					String EBIconpath="";
					EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(2).getEngineeringDegree().trim()+".png";
					Image Icon_img1=null;
					Icon_img1 = Image.getInstance(EBIconpath);
					Icon_img1.setAbsolutePosition(-60, 495);
					Icon_img1.scaleAbsolute(230f ,231f);
					document.add(Icon_img1); 
					
					Paragraph description;
					Chunk degreeName;
					Chunk despHeading ;
					Chunk desp ;
					Chunk subjectHeading ;
					Chunk subject;
					Chunk skillsHeading;
					Chunk skills;
					Chunk careerScopeHeading;
					Chunk careerScope;
					Chunk EducationHeading;
					Chunk Education;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 3 and 4 start ---------------------------------------------------------------------						         
					if(cgtOcc.size()>=3)
					{  
						PdfPTable tableindex0 = new PdfPTable(1);
						tableindex0.setTotalWidth(new float[] { 350});
						tableindex0.setLockedWidth(true);
				        PdfPCell celltableindex0=null;
				        HelveticaBoldDegreeName.setSize(16);
				        HelveticaBoldDegreeName.setColor(255, 128, 0);
				        Helvetica.setSize(10);
				        Helvetica.setColor(0,0,0);
				        degreeName = new Chunk(cgtOcc.get(2).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
				        HelveticaBold.setSize(10);
				        HelveticaBold.setColor(0,0,0);
				        despHeading= new Chunk("Description ", HelveticaBold);
				        desp = new Chunk(cgtOcc.get(2).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
				        
				        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
				        subject = new Chunk(cgtOcc.get(2).getEngineeringDegreeSubjects().trim()+"\n ", Helvetica);
				        
				        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
				        skills = new Chunk(cgtOcc.get(2).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
				        
				        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
				        careerScope = new Chunk(cgtOcc.get(2).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
				        
				        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
				        Education = new Chunk(cgtOcc.get(2).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
				        
				        
				        
				        DespPhrase = new Phrase();
				        DespPhrase.add(degreeName);
				        DespPhrase.add(despHeading);
				        DespPhrase.add(desp);
				        DespPhrase.add(subjectHeading);
				        DespPhrase.add(subject);
				        if(cgtOcc.get(2).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(2).getEngineeringDegreeSkills().trim().equals(""))
				        {
					        DespPhrase.add(skillsHeading);
					        DespPhrase.add(skills);
				        }
				        DespPhrase.add(careerScopeHeading);
				        DespPhrase.add(careerScope);
				        DespPhrase.add(EducationHeading);
				        DespPhrase.add(Education);
				        description = new Paragraph(new Phrase(DespPhrase));
				        description.setAlignment(Element.ALIGN_LEFT);
		 
					        
					        celltableindex0 = new PdfPCell(new Paragraph(description));
					        celltableindex0.setFixedHeight(278.715f);
					        celltableindex0.setBorder(Rectangle.NO_BORDER);
					        celltableindex0.setLeading(3, 0.9f);
					        tableindex0.addCell(celltableindex0);
				        
				        tableindex0.writeSelectedRows(-1, -1, 190, 730, cb);
					}
			        if(cgtOcc.size()==4)
			        {
				        PdfPTable tableindex1 = new PdfPTable(1);
				        tableindex1.setTotalWidth(new float[] { 350	});
				        tableindex1.setLockedWidth(true);
				        PdfPCell celltableindex1=null;
				        HelveticaBoldDegreeName.setSize(16);
				        HelveticaBoldDegreeName.setColor(255, 128, 0);
				        Helvetica.setSize(10);
				        Helvetica.setColor(0,0,0);
				        degreeName = new Chunk(cgtOcc.get(3).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
				        HelveticaBold.setSize(10);
				        HelveticaBold.setColor(0,0,0);
				        despHeading= new Chunk("Description ", HelveticaBold);
				        desp = new Chunk(cgtOcc.get(3).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
				        
				        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
				        subject = new Chunk(cgtOcc.get(3).getEngineeringDegreeSubjects().trim()+"\n", Helvetica);
				        
				        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
				        skills = new Chunk(cgtOcc.get(3).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
				        
				        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
				        careerScope = new Chunk(cgtOcc.get(3).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
				        
				        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
				        Education = new Chunk(cgtOcc.get(3).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
				        
				        
				        
				        DespPhrase = new Phrase();
				        DespPhrase.add(degreeName);
				        DespPhrase.add(despHeading);
				        DespPhrase.add(desp);
				        DespPhrase.add(subjectHeading);
				        DespPhrase.add(subject);
				        if(cgtOcc.get(3).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(3).getEngineeringDegreeSkills().trim().equals(""))
				        {
					        DespPhrase.add(skillsHeading);
					        DespPhrase.add(skills);
				        }
				        DespPhrase.add(careerScopeHeading);
				        DespPhrase.add(careerScope);
				        DespPhrase.add(EducationHeading);
				        DespPhrase.add(Education);
				        description = new Paragraph(new Phrase(DespPhrase));
				        description.setAlignment(Element.ALIGN_LEFT);
				        
						
					        celltableindex1 = new PdfPCell(new Paragraph(description));
					        celltableindex1.setFixedHeight(278.715f);
					        celltableindex1.setBorder(Rectangle.NO_BORDER);
					        celltableindex1.setLeading(3, 0.9f);
					        tableindex1.addCell(celltableindex1);
					        
					        
				        tableindex1.writeSelectedRows(-1, -1, 40, 408, cb);
	
				        EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(3).getEngineeringDegree().trim()+".png";
				        Image Icon_img2=null;
				        Icon_img2 = Image.getInstance(EBIconpath);
						Icon_img2 = Image.getInstance(EBIconpath);
						Icon_img2.setAbsolutePosition(425, 101);
						Icon_img2.scaleAbsolute(230f ,235f);
						document.add(Icon_img2); 
			        }
//--------------------------------------------------------------------Stream description 3 and 4 end ---------------------------------------------------------------------						         

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


	
	public ByteArrayOutputStream GeneratePDFReportSET1B(StudentDetailsDTO studentDTO,
			List<OccupationDTO> cgtOcc, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score, String webURL ) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("EngineeringSelectorPDFReportService : inside GeneratePDFReport method");
		OUT.debug("EngineeringSelectorPDFReportService : inside GeneratePDFReport studentDTO:{},webURL:{}", studentDTO,webURL);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template_Set1B.pdf");
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
					HelveticaBold.setColor(255, 128, 0);
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
					HelveticaBold.setColor(255, 128, 0);
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
					Paragraph _EB=null;
					Paragraph _EBRB=null;
					Paragraph _comment=null;
					float size=0f;
					float _bgsize=0f;
					PdfPTable table = new PdfPTable(3);
			        table.setTotalWidth(new float[] { 148,242,100});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
// --------------------------------------------------------------------Stream Recommendation start---------------------------------------------------------------------
					index = 1;
				    for(OccupationDTO _CGTOCC : cgtOcc) 
					 {
				    		 							
							
							HelveticaBold.setSize(10);
							HelveticaBold.setColor(230, 128, 0);
							_EB = new Paragraph(_CGTOCC.getEngineeringDegree(),HelveticaBold);
					    	cell = new PdfPCell(new Phrase(_EB));
					    	cell.setFixedHeight(47.321f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//					        cell.setPaddingLeft(40);
//					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
							
					        HelveticaBold.setSize(8);
							HelveticaBold.setColor(255, 128, 0);
				    		_EBRB = new Paragraph(_CGTOCC.getEngineeringRelatedCourses(),HelveticaBold);
					        cell = new PdfPCell(new Phrase(_EBRB));
					        cell.setFixedHeight(47.321f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(15);
//					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
					        
					        
					        Helvetica.setSize(10);
					        Helvetica.setColor(109, 110, 113);
					        if(_CGTOCC.getEngineeringPriority()>=12)
					        {
					        	_comment = new Paragraph("Aptitude Improvement",Helvetica);
					        }else
					        {
					        	_comment = new Paragraph("				",Helvetica);
					        }
					        cell = new PdfPCell(new Phrase(_comment));
					        cell.setFixedHeight(47.321f);
					        cell.setBorder(Rectangle.NO_BORDER);
					        cell.setColspan(1);
					        cell.setPaddingLeft(15);
//					        cell.setPaddingTop(18);
//					        cell.setBackgroundColor(new BaseColor(249,221,0));
					        table.addCell(cell);
		
					        
					        table.writeSelectedRows(index-1, -1, 70, (698-size), cb);
					         
					        
					        
					        String EBIconpath="";
					        if( index % 2 == 0)
					        {
					        	EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+"EBS_ORANGE_BAR.png";
					        }
					        else 
					        {
					        	EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+"EBS_BLUE_BAR.png";
					        }
					        Image Icon_img1=null;
							Icon_img1 = Image.getInstance(EBIconpath);
							Icon_img1.setAbsolutePosition(50,( 665-_bgsize));
							Icon_img1.scaleAbsolute(497.132f ,48.5f);
							document.add(Icon_img1);
							size+=47.321f;
							_bgsize+=49.321f;
							 index++;
							 
							 
							 
					         
					    }
				      
			        Paragraph link = new Paragraph();
			        HelveticaBold.setSize(10);
			        HelveticaBold.setColor(255,128,0);
			        Anchor anchor = new Anchor("Lodestar Aptitude Development Guide",HelveticaBold);
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
			        tableLink.writeSelectedRows(-1, -1, 152, 174, cb);

//--------------------------------------------------------------------Stream Recommendation end ---------------------------------------------------------------------						         
 				}

				if (i == 6) 
				{
					String EBIconpath="";
					EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(0).getEngineeringDegree().trim()+".png";
					Image Icon_img1=null;
					Icon_img1 = Image.getInstance(EBIconpath);
					Icon_img1.setAbsolutePosition(-60, 495);
					Icon_img1.scaleAbsolute(230f ,231f);
					document.add(Icon_img1); 
					
					Paragraph description;
					Chunk degreeName;
					Chunk despHeading ;
					Chunk desp ;
					Chunk subjectHeading ;
					Chunk subject;
					Chunk skillsHeading;
					Chunk skills;
					Chunk careerScopeHeading;
					Chunk careerScope;
					Chunk EducationHeading;
					Chunk Education;
					Phrase DespPhrase;
					
//--------------------------------------------------------------------Stream description 1 and 2 start ---------------------------------------------------------------------						         
			        
					PdfPTable tableindex0 = new PdfPTable(1);
					tableindex0.setTotalWidth(new float[] { 350});
					tableindex0.setLockedWidth(true);
			        PdfPCell celltableindex0=null;
			        HelveticaBoldDegreeName.setSize(16);
			        HelveticaBoldDegreeName.setColor(255, 128, 0);
			        Helvetica.setSize(10);
			        Helvetica.setColor(0,0,0);
			        degreeName = new Chunk(cgtOcc.get(0).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
			        HelveticaBold.setSize(10);
			        HelveticaBold.setColor(0,0,0);
			        despHeading= new Chunk("Description ", HelveticaBold);
			        desp = new Chunk(cgtOcc.get(0).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
			        
			        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
			        subject = new Chunk(cgtOcc.get(0).getEngineeringDegreeSubjects().trim()+"\n ", Helvetica);
			        
			        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
			        skills = new Chunk(cgtOcc.get(0).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
			        
			        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
			        careerScope = new Chunk(cgtOcc.get(0).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
			        
			        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
			        Education = new Chunk(cgtOcc.get(0).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
			        
			        
			        
			        DespPhrase = new Phrase();
			        DespPhrase.add(degreeName);
			        DespPhrase.add(despHeading);
			        DespPhrase.add(desp);
			        DespPhrase.add(subjectHeading);
			        DespPhrase.add(subject);
			        if(cgtOcc.get(0).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(0).getEngineeringDegreeSkills().trim().equals(""))
			        {
				        DespPhrase.add(skillsHeading);
				        DespPhrase.add(skills);
			        }
			        DespPhrase.add(careerScopeHeading);
			        DespPhrase.add(careerScope);
			        DespPhrase.add(EducationHeading);
			        DespPhrase.add(Education);
			        description = new Paragraph(new Phrase(DespPhrase));
			        description.setAlignment(Element.ALIGN_LEFT);

	 
				        celltableindex0 = new PdfPCell(new Paragraph(description));
				        celltableindex0.setFixedHeight(278.715f);
				        celltableindex0.setBorder(Rectangle.NO_BORDER);
				        celltableindex0.setLeading(3, 0.9f);
				        tableindex0.addCell(celltableindex0);
			        tableindex0.writeSelectedRows(-1, -1, 190, 730, cb);
			        
			        if(cgtOcc.size() == 2)
			        {
				        PdfPTable tableindex1 = new PdfPTable(1);
				        tableindex1.setTotalWidth(new float[] { 350	});
				        tableindex1.setLockedWidth(true);
				        PdfPCell celltableindex1=null;
				        HelveticaBoldDegreeName.setSize(16);
				        HelveticaBoldDegreeName.setColor(255, 128, 0);
				        Helvetica.setSize(10);
				        Helvetica.setColor(0,0,0);
				        degreeName = new Chunk(cgtOcc.get(1).getEngineeringDegree().trim()+"\n \n", HelveticaBoldDegreeName);
				        HelveticaBold.setSize(10);
				        HelveticaBold.setColor(0,0,0);
				        despHeading= new Chunk("Description ", HelveticaBold);
				        desp = new Chunk(cgtOcc.get(1).getEngineeringDegreeDescription().trim()+"\n ", Helvetica);
				        
				        subjectHeading = new Chunk("\nTypical subjects studied include ", HelveticaBold);
				        subject = new Chunk(cgtOcc.get(1).getEngineeringDegreeSubjects().trim()+"\n", Helvetica);
				        
				        skillsHeading = new Chunk("\nSpecial / Technical skills ", HelveticaBold);
				        skills = new Chunk(cgtOcc.get(1).getEngineeringDegreeSkills().trim()+" \n", Helvetica);
				        
				        careerScopeHeading = new Chunk("\nCareer Scope ", HelveticaBold);
				        careerScope = new Chunk(cgtOcc.get(1).getEngineeringDegreeCareer().trim()+" \n", Helvetica);
				        
				        EducationHeading = new Chunk("\nFurther Education ", HelveticaBold);
				        Education = new Chunk(cgtOcc.get(1).getEngineeringDegreeStudyArea().trim()+" \n", Helvetica);
				        
				        DespPhrase = new Phrase();
				        DespPhrase.add(degreeName);
				        DespPhrase.add(despHeading);
				        DespPhrase.add(desp);
				        DespPhrase.add(subjectHeading);
				        DespPhrase.add(subject);
				        if(cgtOcc.get(1).getEngineeringDegreeSkills().trim() != null || !cgtOcc.get(1).getEngineeringDegreeSkills().trim().equals(""))
				        {
					        DespPhrase.add(skillsHeading);
					        DespPhrase.add(skills);
				        }
				        DespPhrase.add(careerScopeHeading);
				        DespPhrase.add(careerScope);
				        DespPhrase.add(EducationHeading);
				        DespPhrase.add(Education);
				        description = new Paragraph(new Phrase(DespPhrase));
						
					        celltableindex1 = new PdfPCell(new Paragraph(description));
					        celltableindex1.setFixedHeight(278.715f);
					        celltableindex1.setBorder(Rectangle.NO_BORDER);
					        celltableindex1.setLeading(3, 0.9f);
					        tableindex1.addCell(celltableindex1);
					        
					        
				        tableindex1.writeSelectedRows(-1, -1, 40, 408, cb);
	
				        EBIconpath=ApplicationProperties.getInstance().getProperty("com.edupath.Engineering.Branch.degree.image.DirectoryPath")+cgtOcc.get(1).getEngineeringDegree().trim()+".png";
				        Image Icon_img2=null;
				        Icon_img2 = Image.getInstance(EBIconpath);
						Icon_img2 = Image.getInstance(EBIconpath);
						Icon_img2.setAbsolutePosition(425, 101);
						Icon_img2.scaleAbsolute(230f ,235f);
						document.add(Icon_img2); 
					}
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


	public ByteArrayOutputStream GeneratePDFReportSET2(StudentDetailsDTO studentDTO,
			List<OccupationDTO> occlistwithfitment, RaisecCodeDTO raiseccodeDTO, Map<String, String> app_score, List<DHEntExamDTO> entExamlist,List<DHEdupathDTO> edupathlist ) {
		PageNo = 0;
		LastPageNo = false;
		OUT.info("EngineeringSelectorPDFReportService : inside GeneratePDFReport method");
		OUT.debug("EngineeringSelectorPDFReportService : inside GeneratePDFReport studentDTO:{},occlistwithfitment:{}", studentDTO,occlistwithfitment);

		try {
			OUT.info("try block");
			baos = new ByteArrayOutputStream();
			PdfReader pdfReader = new PdfReader(inputFilePathFinalReport + "Template_Set2.pdf");
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
					HelveticaBold.setColor(255, 128, 0);
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
					HelveticaBold.setColor(255, 128, 0);
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
					Paragraph slno=null;
					Paragraph OccName=null;
					String fitmentpath="";
					Image fitment_img=null;
					float size=0f;
					String fitmentstr="";
					PdfPTable table = new PdfPTable(4);
			        table.setTotalWidth(new float[] { 65,200,145 ,300});
			        table.setLockedWidth(true);
			        PdfPCell cell=null;
					
			    //--------------------------------------------------------------------occupation 1 start---------------------------------------------------------------------					
			        index=1;
			    for(OccupationDTO occdto:occlistwithfitment)  
				 {
			    	OUT.debug("bharath EngineeringSelectorPDFReportService occdto:{}",occdto);
						if (occdto.getNewfitment() == 4) {
							HelveticaBold.setColor(98, 191, 0);
							 fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.HighlySuited");
							 fitmentstr="Excellent";
						} 
						else if (occdto.getNewfitment() == 3){
							HelveticaBold.setColor(255, 128, 0);
							if (index % 2 == 0) {
								fitmentpath = ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.AboveAverageFit");
							} else {
								fitmentpath = ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.AboveAverageFitGray");
							}
							fitmentstr="High";
						}
						else if (occdto.getNewfitment() == 2){
							HelveticaBold.setColor(255, 179, 26);
							if (index % 2 == 0) {
								fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.AverageFit");
							} else {
								fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.AverageFitGray");
							}
							fitmentstr="Medium";
						}
						else if (occdto.getNewfitment() == 1){
							HelveticaBold.setColor(176, 178, 179);
							if (index % 2 == 0) {
								fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.LowFit");
							} else {
								fitmentpath= ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.star.LowFitGray");
							}
							fitmentstr="Low";
						}
	
						HelveticaBoldOccName.setSize(10);
						HelveticaBoldOccName.setColor(65,64,66);
						slno = new Paragraph(Integer.toString(index),HelveticaBoldOccName);
				    	cell = new PdfPCell(new Phrase(slno));
				    	cell.setFixedHeight(10f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPaddingLeft(20);
				        cell.setPaddingTop(10);
				        table.addCell(cell);
						
				       
						OccName = new Paragraph(occdto.getName(),HelveticaBoldOccName);
				        cell = new PdfPCell(new Phrase(OccName));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        table.addCell(cell);
	
				        fitment_img = Image.getInstance(fitmentpath);
				        fitment_img.setAlignment(Element.ALIGN_MIDDLE);
				        cell = new PdfPCell(fitment_img);
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setColspan(1);
				        cell.setPaddingLeft(20);
				        cell.setPadding(10);
				        table.addCell(cell);
				        
				        
				        HelveticaBold.setSize(10);
				        cell = new PdfPCell(new Phrase(new Paragraph(fitmentstr,HelveticaBold)));
				        cell.setFixedHeight(31.5f);
				        cell.setBorder(Rectangle.NO_BORDER);
				        cell.setColspan(1);
				        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				        cell.setPadding(5);
				        cell.setPaddingTop(10);
				        table.addCell(cell);
				        
				        table.writeSelectedRows(index-1, -1, 50, (687-size), cb);
				        index++;  
				        size+=31.5f;
				         
				    }
	
//--------------------------------------------------------------------edupath list end start---------------------------------------------------------------------						         
		        
					    PdfPTable _edupathtable = new PdfPTable(4);
					    _edupathtable.setTotalWidth(new float[] {55, 193, 105, 100});
					    _edupathtable.setLockedWidth(true);
				        PdfPCell _edupathcell=null;
				        index=1;
					    for(OccupationDTO occdto:occlistwithfitment)
					    {
					    	HelveticaBold.setSize(10);
					    	HelveticaBold.setColor(98, 191, 0);
							slno = new Paragraph(Integer.toString(index),HelveticaBold);
							_edupathcell = new PdfPCell(new Phrase(slno));
							_edupathcell.setFixedHeight(54.4f);
							_edupathcell.setBorder(Rectangle.NO_BORDER);
							_edupathcell.setColspan(1);
							_edupathcell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
							_edupathcell.setPaddingLeft(20);
					        _edupathcell.setPaddingTop(10);
					        _edupathtable.addCell(_edupathcell);
	
					        
					        HelveticaBold.setSize(11);
					        HelveticaBold.setColor(98, 191, 0);
					        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getName(),HelveticaBold)));
					        _edupathcell.setFixedHeight(54.4f);
					        _edupathcell.setBorder(Rectangle.NO_BORDER);
					        _edupathcell.setColspan(1);
					        _edupathcell.setPadding(5);
					        _edupathcell.setPaddingTop(10);
					        _edupathtable.addCell(_edupathcell);
							
//					        Helvetica.setSize(10);
//					        Helvetica.setColor(65, 64, 66);
//					        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getStream(),Helvetica)));
//					        _edupathcell.setFixedHeight(54.4f);
//					        _edupathcell.setBorder(Rectangle.NO_BORDER);
//					        _edupathcell.setColspan(1);
//					        _edupathcell.setPadding(5);
//					        _edupathcell.setPaddingTop(10);
//					        _edupathtable.addCell(_edupathcell);
	
					        Helvetica.setSize(10);
					        Helvetica.setColor(65, 64, 66);
					        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getEntExam(),Helvetica)));
					        _edupathcell.setFixedHeight(54.4f);
					        _edupathcell.setBorder(Rectangle.NO_BORDER);
					        _edupathcell.setColspan(1);
					        _edupathcell.setPadding(5);
					        _edupathcell.setPaddingTop(10);
					        _edupathtable.addCell(_edupathcell);
					    	
					        Helvetica.setSize(10);
					        Helvetica.setColor(65, 64, 66);
					        _edupathcell = new PdfPCell(new Phrase(new Paragraph(occdto.getDheduDTO().getDegree(),Helvetica)));
					        _edupathcell.setFixedHeight(54.4f);
					        _edupathcell.setBorder(Rectangle.NO_BORDER);
					        _edupathcell.setColspan(1);
					        _edupathcell.setPadding(5);
					        _edupathcell.setPaddingTop(10);
					        _edupathtable.addCell(_edupathcell);
					    	
					        _edupathtable.writeSelectedRows(index-1, -1, 50, (542-size), cb);
					        index++;  
					        size+=54.4f;
					        OUT.debug("_edupathcell Size:{}",size);
					    }
					    
//--------------------------------------------------------------------edupath list end---------------------------------------------------------------------						         
				        
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
