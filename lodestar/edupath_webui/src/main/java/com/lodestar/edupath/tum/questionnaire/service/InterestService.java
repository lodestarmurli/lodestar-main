package com.lodestar.edupath.tum.questionnaire.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.CGT.CGTQuestioneriesDAO;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.TumCgtResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendation;
import com.lodestar.edupath.dataaccessobject.dao.trialInterestcodemapping.TrialInterestCodeMappingDAO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.TrialInterestCodeMappingDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.CGTQuestioneriesDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTForEvalDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.TumCgtResultDTO;
import com.lodestar.edupath.datatransferobject.enumtype.PDFContentSearchEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.util.PasswordGeneratorService;
import com.lodestar.edupath.util.pdfeditor.PDFManager;

public class InterestService
{
	private static final Logger				OUT				= LoggerFactory.getLogger(InterestService.class);
	private static final SimpleDateFormat	DATE_FORMAT		= new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
	private static final String				FILE_TYPE		= "pdf";
	private static final int				PDF_FROM_PAGE	= 1;
	private static final int				PDF_TO_PAGE		= 1;
	private static String					FILE_NAME		= "Trial_Student";

	public boolean insertAnswer(String answerList, int studentId)
	{
		OUT.debug("Inside InterestService");
		SqlSession session = null;
		boolean result = true;
		try
		{
			Map<Object, Integer> CGTMap = getCGTQuestioneries();
			if (null != CGTMap && !CGTMap.isEmpty())
			{
				ObjectMapper mapper = new ObjectMapper();
				@SuppressWarnings("deprecation")
				List<CGTQuestioneriesDTO> list = mapper.readValue(answerList, TypeFactory.collectionType(List.class, CGTQuestioneriesDTO.class));

				if (null != list && !list.isEmpty())
				{
					StudentDetailsDAO dao = new StudentDetailsDAO();
					StudentDetailsDTO student = dao.getStudentDetailsByUserId(studentId);
					session = MyBatisManager.getInstance().getSession();
					StudentCGTDTO studentCGT = null;
					StudentCGTDAO studentCGTDAO = new StudentCGTDAO();
					for (CGTQuestioneriesDTO cgtQuestioneriesDTO : list)
					{
						studentCGT = new StudentCGTDTO();
						studentCGT.setAnswer(cgtQuestioneriesDTO.getCorrectAnswer());
						studentCGT.setStudentId(student.getId());
						if (CGTMap.containsKey(cgtQuestioneriesDTO.getSlNo()))
						{
							studentCGT.setQuestionId(CGTMap.get(cgtQuestioneriesDTO.getSlNo()));
						}
						int id = studentCGTDAO.updateStudentCGT(session, studentCGT);
						if (id == 0)
						{
							studentCGTDAO.insertStudentCGT(session, studentCGT);
						}
					}
					session.commit();
				}

			}

		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			result = false;
			OUT.error("Exception", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return result;
	}

	public JSONArray getStudentCGTBySection(int studentId)
	{
		JSONArray finalJsonArray = null;
		try
		{
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO student = dao.getStudentDetailsByUserId(studentId);
			Map<String, Object> parameterObject = new HashMap<String, Object>();
			parameterObject.put("studentId", student.getId());
			parameterObject.put("section", SystemRecommendation.INTREST_TEST_SECTION);
			StudentCGTDAO studentDAO = new StudentCGTDAO();
			List<StudentCGTForEvalDTO> list = studentDAO.getStudentCGTByStudentId(parameterObject);
			if (null != list && !list.isEmpty())
			{
				finalJsonArray = new JSONArray(list);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return finalJsonArray;
	}

	public List<String> createQuestionList()
	{
		List<String> list = new LinkedList<>();
		try
		{
			list.add("I like to understand how different machines function?");
			list.add("I enjoy reading about scientific inventions and discoveries?");
			list.add("I enjoy drawing and/or painting?");
			list.add("While working in a group on school projects, I prefer working along with others than monitor their work?");
			list.add("I am comfortable speaking in front of large audiences?");
			list.add("I like keeping a track of my monthly expenses?");

			list.add("I can manage to repair a gadget, even if it is in a bad shape?");
			list.add("I like visiting monuments and historical sites?");
			list.add("I enjoy participating in musical/ theatrical performances at school?");
			list.add("I like to participate in activities that require interacting in a group than work by myself?");
			list.add("I enjoy learning about and discussing political issues?");
			list.add("When working on a problem, I like to follow the rules to solve it?");

			list.add("I would prefer to play games like cricket or football, rather than solve puzzles?");
			list.add("I find it iteresting to learn about environmental facts?");
			list.add("Given a chance, I would enjoy clicking and/or collecting photographs?");
			list.add("Spending time around children fascinates me?");
			list.add("During group projects, I like to decide and assign tasks for people?");
			list.add("I like to follow a time-table for completing my daily tasks?");

			list.add("I like to gain knowledge about growing different plants and trees?");
			list.add("I enjoy tasks that require researching on new topics?");
			list.add("I would like to take up projects that require creative designing?");
			list.add("I would enjoy working for a social cause than conduct science experiments?");
			list.add("It is important for me to express my opinion, in any group discussion?");
			list.add("I pay attention to the minutest details, while performing any assignment?");

			list.add("I enjoy reading sports magazines?");
			list.add("Conducting laboratory experiments is fascinating for me?");
			list.add("I would be comfortable performing  in front of an audience?");
			list.add("I like being involved in organizing the functions and events in school?");
			list.add("I enjoy taking up new hobbies and interests?");
			list.add("My desk and shelves are always neatly organized?");

			list.add("I generally enjoy playing outdoors than draw or paint?");
			list.add("I would enjoy solving brain-teaser puzzles, rather than playing outdoor sports?");
			list.add("I would prefer to sketch or paint than play outdoors?");
			list.add("I like to work with others to help them solve their problems?");
			list.add("I like being in charge of group projects?");
			list.add("My daily activities generally follow a fixed schedule?");

			list.add("I enjoy creating working models for science projects?");
			list.add("I enjoy reading about the mysteries of space and solar system?");
			list.add("Sometimes, I express myself best in a short poem or write-up?");
			list.add("I can easily start conversations with new people?");
			list.add("I would enjoy debating on a political issue than sketch or paint?");
			list.add("I like to keep all my things in an orderly fashion?");

			list.add("I enjoy fixing household appliances like phones, clocks etc?");
			list.add("I like working with number games and brain-teasers such as suduko?");
			list.add("I enjoy reading about art rather than about sports?");
			list.add("I like to volunteer for events for social causes?");
			list.add("I enjoy the thrill of adventure sports?");
			list.add("I can maintain my things in a systematic manner?");

			list.add("I enjoy taking care of animals?");
			list.add("I enjoy working with and analysing maps and charts?");
			list.add("Theatre and dramatics is something I really enjoy?");
			list.add("I enjoy working on projects that require working in a group than working alone?");
			list.add("I would be interested to learn how a business work?");
			list.add("To ensure accuracy, I check my work many times before submitting?");

			list.add("I enjoy taking up adventerous activities/sports?");
			list.add("I like to understand and explore different scientific ideas and theories?");
			list.add("I will choose to play a  musical instrument than working with puzzles?");
			list.add("I like participating in group debates and discussions?");
			list.add("I can express my opinions strongly during classroom/ group discussions?");
			list.add("I use diagrams and flow-charts while explaining any concept or theory?");

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return list;
	}

	private Map<Object, Integer> getCGTQuestioneries()
	{
		Map<Object, Integer> cgtMap = null;
		try
		{
			CGTQuestioneriesDAO dao = new CGTQuestioneriesDAO();
			CGTQuestioneriesDTO dto = new CGTQuestioneriesDTO();
			dto.setSection(SystemRecommendation.INTREST_TEST_SECTION);
			List<CGTQuestioneriesDTO> list = dao.getCGTQuestioneriesDetailsBySection(dto);

			if (null != list && !list.isEmpty())
			{
				cgtMap = new HashMap<Object, Integer>();
				for (CGTQuestioneriesDTO cgtDTO : list)
				{
					cgtMap.put(cgtDTO.getSlNo(), cgtDTO.getId());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return cgtMap;
	}

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean isIntrestCompleted(int userId) throws Exception
	{
		Integer answeredCount = null;
		StudentDetailsDTO studentDetailsDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		if (studentDetailsDTO != null)
		{
			int studentId = studentDetailsDTO.getId();

			CGTQuestioneriesDTO cgtQuestioneriesDTO = new CGTQuestioneriesDTO();
			cgtQuestioneriesDTO.setSection(SystemRecommendation.INTREST_TEST_SECTION);
			List<CGTQuestioneriesDTO> questioneriesDTOs = new CGTQuestioneriesDAO().getCGTQuestioneriesDetailsBySection(cgtQuestioneriesDTO);
			List<String> questionIds = new ArrayList<String>();
			if (questioneriesDTOs != null)
			{
				for (CGTQuestioneriesDTO questioneriesDTO : questioneriesDTOs)
				{
					questionIds.add(questioneriesDTO.getSlNo());
				}
				answeredCount = new StudentCGTDAO().getAnsweredCountByQuestionIds(studentId, questionIds);
			}
			if (answeredCount != null && answeredCount.intValue() == questionIds.size())
			{
				return true;
			}
		}
		return false;
	}

	public File getTrialReportContent(int userId) throws Exception
	{
		String saveFilePath = null;
		StudentDetailsDTO student = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		Map<PDFContentSearchEnum, String> replaceMap = new HashMap<PDFContentSearchEnum, String>();
		File file;
		try
		{
			file = createPDFFileByRAISECCode(student, replaceMap);
			if (file != null)
			{
				saveFilePath = createFilePath(FILE_NAME, FILE_TYPE, getSystemFilePath());
				PDFManager pdfManager = new PDFManager(file, saveFilePath);
				pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);
				if (null != file)
				{
					file.delete();
				}
				file = new File(saveFilePath);
				return file;
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		saveFilePath = createFilePath("TempFile", FILE_TYPE, getSystemFilePath());
		file = new File(saveFilePath);
		return file;
	}

	public void sendMailForTrialStudent(int userId, int roleTypeId, StudentDetailsDTO studDetailsDTO) throws Exception
	{
		StudentDetailsDTO studentDTO = null;
		if (studDetailsDTO != null)
		{
			studentDTO = studDetailsDTO;
		}
		else
		{
			studentDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		}
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.EMAIL_FOLDER_PATH.getProperty());
		globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
		//===============Start Sasedeve edited by Mrutyunjaya on date 1-04-2017=============================
		String newFolderP = null;
		String newFolderS = null;
		if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
		{
			String currentDir = globalDTO.getPropertyValue();
			if((studentDTO.getFatherEmailId()!=null && !studentDTO.getFatherEmailId().trim().isEmpty()) || (studentDTO.getMotheremailId()!=null && !studentDTO.getMotheremailId().trim().isEmpty()))
			{
				newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_P";
				File dirsP = new File(currentDir + newFolderP);
				if (!dirsP.exists())
				{
					dirsP.mkdirs();
				}
			}
			else
			{
				newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_P";
				File dirsP = new File(currentDir + newFolderP);
				if (!dirsP.exists())
				{
					dirsP.mkdirs();
				}
			}
			
			//System.out.println("==============================>"+studentDTO.getStudentemailId());
			
			if(studentDTO.getStudentemailId()!=null && !studentDTO.getStudentemailId().trim().isEmpty())
			{
				newFolderS = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_S";
				File dirsS = new File(currentDir + newFolderS);
				if (!dirsS.exists())
				{
					dirsS.mkdirs();
				}
			}
			
			
			
			
			Map<PDFContentSearchEnum, String> replaceMap = new HashMap<PDFContentSearchEnum, String>();
			try
			{
				File file = createPDFFileByRAISECCode(studentDTO, replaceMap);
				if (file != null)
				{
				//	System.out.println("=========currentDir================>"+currentDir);
					//System.out.println("==========================>"+newFolderP);
					if(newFolderP!=null)
					{
						String filePath = newFolderP + File.separator + FILE_NAME + "_" + DATE_FORMAT.format(new Date());
						
						//System.out.println("==========================>"+filePath);
						String saveFile = createReportFilePath(filePath, FILE_TYPE, currentDir);
						OUT.debug("SaveFilePath : {}", saveFile);
						PDFManager pdfManager = new PDFManager(file, saveFile);
						pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);
//						if (null != file)
//						{
//							file.delete();
//						}
					}
					if(newFolderS!=null)
					{
						String filePath = newFolderS + File.separator + FILE_NAME + "_" + DATE_FORMAT.format(new Date());
						String saveFile = createReportFilePath(filePath, FILE_TYPE, currentDir);
						OUT.debug("SaveFilePath : {}", saveFile);
						PDFManager pdfManager = new PDFManager(file, saveFile);
						pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);
//						if (null != file)
//						{
//							file.delete();
//						}
					}
					
					if (null != file)
					{
						file.delete();
					}

					//===============END Sasedeve edited by Mrutyunjaya on date 1-04-2017=============================	
					sendEmail(roleTypeId, studentDTO, newFolderP,newFolderS);
				}
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		else
		{
			OUT.debug("EMAIL_FOLDER_PATH IS Required");
		}
	}

	
	
	public void sendMailForTrialStudentBulkUpload(int userId, int roleTypeId, StudentDetailsDTO studDetailsDTO) throws Exception
	{
		StudentDetailsDTO studentDTO = null;
		if (studDetailsDTO != null)
		{
			studentDTO = studDetailsDTO;
		}
		else
		{
			studentDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		}
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.EMAIL_FOLDER_PATH.getProperty());
		globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
		//===============Start Sasedeve edited by Mrutyunjaya on date 1-04-2017=============================
		String newFolderP = null;
		String newFolderS = null;
		
		String StudentEmailID = null;
		String ParentEmailID = null;
		boolean checkstudentissame=true;
		
		
		if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
		{
			String currentDir = globalDTO.getPropertyValue();
			if((studentDTO.getFatherEmailId()!=null && !studentDTO.getFatherEmailId().trim().isEmpty()) || (studentDTO.getMotheremailId()!=null && !studentDTO.getMotheremailId().trim().isEmpty()))
			{
				newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_P";
				File dirsP = new File(currentDir + newFolderP);
				if (!dirsP.exists())
				{
					dirsP.mkdirs();
				}
				
				if(studentDTO.getFatherEmailId()!=null && !studentDTO.getFatherEmailId().trim().isEmpty())
				{
					ParentEmailID=studentDTO.getFatherEmailId();
				}
				if(studentDTO.getMotheremailId()!=null && !studentDTO.getMotheremailId().trim().isEmpty())
				{
					if(ParentEmailID!=null)
					{
						ParentEmailID=ParentEmailID+","+studentDTO.getMotheremailId();
					}
					else
					{
						ParentEmailID=studentDTO.getMotheremailId();
					}
					
				}
				
			}
			else
			{
				newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_P";
				File dirsP = new File(currentDir + newFolderP);
				if (!dirsP.exists())
				{
					dirsP.mkdirs();
				}
				
				ParentEmailID=studentDTO.getFatheremailId();
			}
			if(studentDTO.getStudentemailId()!=null && !studentDTO.getStudentemailId().trim().isEmpty())
			{
				StudentEmailID=studentDTO.getStudentemailId();
			}
			if(ParentEmailID!=null)
			{
				ParentEmailID=getUniqueEmailIds(ParentEmailID.split(","));
				if(StudentEmailID!=null)
				{
					String tempemaild[]=ParentEmailID.split(",");
					
					for(String s : tempemaild )
					{
						if(s.trim().equalsIgnoreCase(StudentEmailID.trim()))
						{
							checkstudentissame=false;
							break;
						}
					}
				}
			}
			
			
			if(StudentEmailID!=null && checkstudentissame)
			{
				newFolderS = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_S";
				File dirsS = new File(currentDir + newFolderS);
				if (!dirsS.exists())
				{
					dirsS.mkdirs();
				}
			}
			
			
			
			
			Map<PDFContentSearchEnum, String> replaceMap = new HashMap<PDFContentSearchEnum, String>();
			try
			{
				File file = createPDFFileByRAISECCode(studentDTO, replaceMap);
				if (file != null)
				{
				//	System.out.println("=========currentDir================>"+currentDir);
					//System.out.println("==========================>"+newFolderP);
					if(newFolderP!=null)
					{
						String filePath = newFolderP + File.separator + FILE_NAME + "_" + DATE_FORMAT.format(new Date());
						
						//System.out.println("==========================>"+filePath);
						String saveFile = createReportFilePath(filePath, FILE_TYPE, currentDir);
						OUT.debug("SaveFilePath : {}", saveFile);
						PDFManager pdfManager = new PDFManager(file, saveFile);
						pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);
//						if (null != file)
//						{
//							file.delete();
//						}
					}
					if(newFolderS!=null)
					{
						String filePath = newFolderS + File.separator + FILE_NAME + "_" + DATE_FORMAT.format(new Date());
						String saveFile = createReportFilePath(filePath, FILE_TYPE, currentDir);
						OUT.debug("SaveFilePath : {}", saveFile);
						PDFManager pdfManager = new PDFManager(file, saveFile);
						pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);
//						if (null != file)
//						{
//							file.delete();
//						}
					}
					
					if (null != file)
					{
						file.delete();
					}

					//===============END Sasedeve edited by Mrutyunjaya on date 1-04-2017=============================	
					sendEmailBulKUpload(roleTypeId, studentDTO, newFolderP,newFolderS);
				}
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		else
		{
			OUT.debug("EMAIL_FOLDER_PATH IS Required");
		}
	}

	private String getUniqueEmailIds(String[] arrayValue)
	{
		if (arrayValue != null)
		{
			Set<String> emailIds = new HashSet<String>();
			StringBuilder commaSeparatedValue = new StringBuilder();
			for (String emailId : arrayValue)
			{
				if (!emailIds.contains(emailId.trim()))
				{
					emailIds.add(emailId.trim());

					if (!commaSeparatedValue.toString().trim().isEmpty())
					{
						commaSeparatedValue.append(",");
					}
					commaSeparatedValue.append(emailId.trim());
				}
			}

			return commaSeparatedValue.toString();
		}
		return null;
	}

	
	
	private File createPDFFileByRAISECCode(StudentDetailsDTO studentDTO, Map<PDFContentSearchEnum, String> replaceMap) throws Exception
	{
		File file = null;
		String raisecCode = new SystemRecommendation().getTrailStudentRAISEC_Code(studentDTO.getId());
		if (null != raisecCode && !raisecCode.isEmpty())
		{
			raisecCode = raisecCode.replaceAll(",", "");
			int oldRaisecCode = CommonUtil.getSumOfChar(raisecCode.toLowerCase());
			OUT.debug("Trial Student : {} CGT raisecCode : {} and SUM : {}", "LD" + studentDTO.getUserId(), raisecCode, oldRaisecCode);
			TrialInterestCodeMappingDAO codeMappingDAO = new TrialInterestCodeMappingDAO();
			
			//===============Start Sasedeve edited by Mrutyunjaya on date 1-04-2017=============================
			
			//System.out.println("====================>"+studentDTO.getClassName());
			//System.out.println("====================>"+studentDTO.getClassId());
			List<String> list;
			if(studentDTO.getClassId()==1 || studentDTO.getClassId()==2)
			{
				 list = codeMappingDAO.getAllTrialInterestMapping(1); //For Class 9, 10====1 for isclass==1
			}
			else
			{
				list = codeMappingDAO.getAllTrialInterestMapping(0); //For Class 11, 12====0 for isclass==0
			}
			//===============END Sasedeve edited by Mrutyunjaya on date 1-04-2017=============================	
			
			if (list.isEmpty())
			{
				throw new Exception("TrialInterestMapping NO RAISEC Code PDF Uploaded");
			}
			String exitRAISECCode = CommonUtil.getExitRAISECCode(oldRaisecCode, list, true);
			if (null != exitRAISECCode && !exitRAISECCode.isEmpty())
			{
				//===============Start Sasedeve edited by Mrutyunjaya on date 1-04-2017=============================
				TrialInterestCodeMappingDTO codeMappingDTO;
				if(studentDTO.getClassId()==1 || studentDTO.getClassId()==2)
				{
					codeMappingDTO = codeMappingDAO.getTrialMappingByRAISECCode(exitRAISECCode,1);//1 for isclass==1
				}
				else
				{
					codeMappingDTO = codeMappingDAO.getTrialMappingByRAISECCode(exitRAISECCode,0);//0 for isclass==0
				}
				//===============END Sasedeve edited by Mrutyunjaya on date 1-04-2017=============================	
				
				if (null != codeMappingDTO)
				{
					StudentDetailsDTO studentDetailsDTO = new StudentDetailsDAO().getStudentForTrialReport(studentDTO.getId());
					if (null != studentDetailsDTO.getSchoolCode() && ApplicationConstants.OTHER_SCHHOL_CODE.equalsIgnoreCase(studentDetailsDTO.getSchoolCode())
							&& ApplicationConstants.OTHER_SCHHOL.equalsIgnoreCase(studentDetailsDTO.getSchoolName()))
					{
						studentDetailsDTO.setSchoolName(studentDetailsDTO.getOtherSchool());
					}
					file = generateFile(codeMappingDTO.getFileName(), FILE_TYPE, codeMappingDTO.getContent());

					// Set Replace content in PDF File
					replaceMap.put(PDFContentSearchEnum.STUDENT_NAME, studentDetailsDTO.getName());
					replaceMap.put(PDFContentSearchEnum.CLASS_AND_SECTION, studentDetailsDTO.getClassName()
							+ (null != studentDetailsDTO.getSection() && !studentDetailsDTO.getSection().isEmpty() ? " (" + studentDetailsDTO.getSection() + ")"
									: ""));
					replaceMap.put(PDFContentSearchEnum.SCHOOL, studentDetailsDTO.getSchoolName());
				}
			}
			else
			{
				OUT.debug("Trial Student : {} doesn't exit RAISECCode IN DB", "LD" + studentDTO.getUserId());
			}
		}
		else
		{
			OUT.debug("Trial Student : {} doesn't take CGT (Intrest Exam)", "LD" + studentDTO.getUserId());
		}
		return file;
	}

	private void sendEmail(int roleTypeId, StudentDetailsDTO studentDetailsDTO, String filePathP,String filePathS) throws Exception
	{
		StringBuilder headerMessage = new StringBuilder();
		headerMessage.append("Your Report Details are as below");

		//=================Start Sasedeve edited by Mrutyunjaya on 01-04-2017===========
		
//		PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), studentDetailsDTO.getFatheremailId(), roleTypeId,
//				NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_REPORT.name(), filePath, studentDetailsDTO.getFathername());
		
		
		
		String StudentEmailID=null;
		String ParentEmailID=null;
		
		int checkFatherEmailid=0,CheckMotheremailid=0;
		
		
		
		if(studentDetailsDTO.getFatherEmailId()!=null && !studentDetailsDTO.getFatherEmailId().trim().isEmpty())
		{
			
			ParentEmailID=studentDetailsDTO.getFatherEmailId();
			checkFatherEmailid=1;
		}
		
		if(studentDetailsDTO.getMotheremailId()!=null && !studentDetailsDTO.getMotheremailId().trim().isEmpty())
		{
			CheckMotheremailid=1;
			if(ParentEmailID!=null)
			{
				ParentEmailID=ParentEmailID+","+studentDetailsDTO.getMotheremailId();
			}
			else
			{
				ParentEmailID=studentDetailsDTO.getMotheremailId();
			}
			
		}
		
		if(checkFatherEmailid==0 && CheckMotheremailid==0)
		{
			ParentEmailID=studentDetailsDTO.getFatheremailId();
		}
		
		if(studentDetailsDTO.getStudentemailId()!=null && !studentDetailsDTO.getStudentemailId().trim().isEmpty())
		{
			StudentEmailID=studentDetailsDTO.getStudentemailId();
		}
		
		if(StudentEmailID!=null)
		{
			PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), StudentEmailID, roleTypeId,
					NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_REPORT_NEW_TEMP.name(), filePathS, studentDetailsDTO.getFathername());
		}
		
		if(ParentEmailID!=null)
		{
			PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), ParentEmailID, roleTypeId,
					NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_REPORT_PARENT.name(), filePathP, studentDetailsDTO.getFathername());
		}
		
		
		//=================END Sasedeve edited by Mrutyunjaya on 01-04-2017===========
		
	}


	private void sendEmailBulKUpload(int roleTypeId, StudentDetailsDTO studentDetailsDTO, String filePathP,String filePathS) throws Exception
	{
		StringBuilder headerMessage = new StringBuilder();
		headerMessage.append("Your Report Details are as below");

		//=================Start Sasedeve edited by Mrutyunjaya on 01-04-2017===========
		
//		PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), studentDetailsDTO.getFatheremailId(), roleTypeId,
//				NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_REPORT.name(), filePath, studentDetailsDTO.getFathername());
		
		
		
		String StudentEmailID=null;
		String ParentEmailID=null;
		
		int checkFatherEmailid=0,CheckMotheremailid=0;
		
		
		
		if(studentDetailsDTO.getFatherEmailId()!=null && !studentDetailsDTO.getFatherEmailId().trim().isEmpty())
		{
			
			ParentEmailID=studentDetailsDTO.getFatherEmailId();
			checkFatherEmailid=1;
		}
		
		if(studentDetailsDTO.getMotheremailId()!=null && !studentDetailsDTO.getMotheremailId().trim().isEmpty())
		{
			CheckMotheremailid=1;
			if(ParentEmailID!=null)
			{
				ParentEmailID=ParentEmailID+","+studentDetailsDTO.getMotheremailId();
			}
			else
			{
				ParentEmailID=studentDetailsDTO.getMotheremailId();
			}
			
		}
		
		if(checkFatherEmailid==0 && CheckMotheremailid==0)
		{
			ParentEmailID=studentDetailsDTO.getFatheremailId();
		}
		
		if(studentDetailsDTO.getStudentemailId()!=null && !studentDetailsDTO.getStudentemailId().trim().isEmpty())
		{
			StudentEmailID=studentDetailsDTO.getStudentemailId();
		}
		
		boolean checkstudentissame=true;
		if(ParentEmailID!=null)
		{
			ParentEmailID=getUniqueEmailIds(ParentEmailID.split(","));
			if(StudentEmailID!=null)
			{
				String tempemaild[]=ParentEmailID.split(",");
				
				for(String s : tempemaild )
				{
					if(s.trim().equalsIgnoreCase(StudentEmailID.trim()))
					{
						checkstudentissame=false;
						break;
					}
				}
			}
		}
		
		
		
		
		
		
		
		if(StudentEmailID!=null && checkstudentissame)
		{
			PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), StudentEmailID, roleTypeId,
					NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_REPORT_NEW_TEMP_BULK.name(), filePathS, studentDetailsDTO.getFathername());
		}
		
		if(ParentEmailID!=null)
		{
			PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), ParentEmailID, roleTypeId,
					NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_REPORT_PARENT_BULK.name(), filePathP, studentDetailsDTO.getFathername());
		}
		
		
		//=================END Sasedeve edited by Mrutyunjaya on 01-04-2017===========
		
	}


	
	
	
	
	private File generateFile(String fileName, String fileType, byte[] content) throws Exception
	{
		String currentDir = getSystemFilePath();
		File dirs = new File(currentDir + File.separator);
		if (!dirs.exists())
		{
			dirs.mkdirs();
		}
		File file = writeFileContent(createFilePath(fileName, fileType, currentDir), content);
		return file;
	}

	private File writeFileContent(String filePath, byte[] content) throws IOException
	{
		File file = new File(filePath);
		if (!file.exists())
		{
			file.createNewFile();
		}
		if (null != content)
		{
			FileOutputStream out = null;
			try
			{
				out = new FileOutputStream(file);
				out.write(content);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (null != out)
				{
					out.close();
				}
			}
		}
		return file;
	}

	private String createFilePath(String fileName, String fileType, String currentDir)
	{
		return currentDir + File.separator + fileName + "_" + DATE_FORMAT.format(new Date()) + "." + fileType;
	}

	private String createReportFilePath(String fileName, String fileType, String currentDir)
	{
		//Start Sasedeve Edited By Mrutyunjaya on Date 25-05-2017
		
		return currentDir + fileName + "." + fileType;
		
		//return currentDir + File.separator + fileName + "." + fileType;
		
		//End Sasedeve Edited By Mrutyunjaya on Date 25-05-2017
	}

	private String getSystemFilePath()
	{
		String currentDir = System.getProperty("java.io.tmpdir");
		if (currentDir.indexOf("bin") > 0)
		{
			currentDir = currentDir.substring(0, currentDir.indexOf("bin"));
		}
		currentDir += "upload_template";
		return currentDir;
	}
	
	
	//Start Sasedeve edited by Mrutyunjay on date 12-10-2017
	
	public void sendMailForTrialStudentSIATTestLeadparent(int userId,String token, int roleTypeId) throws Exception
	{
		StudentDetailsDTO studentDTO = null;
		studentDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.EMAIL_FOLDER_PATH.getProperty());
		globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);

		String newFolderP = null;
		String newFolderS = null;
		if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
		{
			String currentDir = globalDTO.getPropertyValue();
			if((studentDTO.getFatherEmailId()!=null && !studentDTO.getFatherEmailId().trim().isEmpty()) || (studentDTO.getMotheremailId()!=null && !studentDTO.getMotheremailId().trim().isEmpty()))
			{
				newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_P";
				File dirsP = new File(currentDir + newFolderP);
				if (!dirsP.exists())
				{
					dirsP.mkdirs();
				}
			}
			else
			{
				newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_P";
				File dirsP = new File(currentDir + newFolderP);
				if (!dirsP.exists())
				{
					dirsP.mkdirs();
				}
			}
			

			
			if(studentDTO.getStudentemailId()!=null && !studentDTO.getStudentemailId().trim().isEmpty())
			{
				newFolderS = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_S";
				File dirsS = new File(currentDir + newFolderS);
				if (!dirsS.exists())
				{
					dirsS.mkdirs();
				}
			}
			
			
			
			
			Map<PDFContentSearchEnum, String> replaceMap = new HashMap<PDFContentSearchEnum, String>();
			try
			{
				//File file1=createPDFFileByRAISECCode(studentDTO, replaceMap);
				File file = createPDFFileByRAISECCode(studentDTO, replaceMap);
				if (file != null)
				{

					if(newFolderP!=null)
					{
						String filePath = newFolderP + File.separator + FILE_NAME + "_" + DATE_FORMAT.format(new Date());
						

						String saveFile = createReportFilePath(filePath, FILE_TYPE, currentDir);
						OUT.debug("SaveFilePath : {}", saveFile);
						PDFManager pdfManager = new PDFManager(file, saveFile);
						pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);

					}
					if(newFolderS!=null)
					{
						String filePath = newFolderS + File.separator + FILE_NAME + "_" + DATE_FORMAT.format(new Date());
						String saveFile = createReportFilePath(filePath, FILE_TYPE, currentDir);
						OUT.debug("SaveFilePath : {}", saveFile);
						PDFManager pdfManager = new PDFManager(file, saveFile);
						pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);

					}
					
					if (null != file)
					{
						file.delete();
					}

				
					sendEmailSIATTestLeadParent(roleTypeId, studentDTO, newFolderP,newFolderS,token);
				}
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		else
		{
			OUT.debug("EMAIL_FOLDER_PATH IS Required");
		}
	}
	
	public void sendMailForTrialStudentTYEProgTest(int userId,String token, int roleTypeId,LeadParentDTO leadparent) throws Exception
	{
		StudentDetailsDTO studentDTO = null;
		studentDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.EMAIL_FOLDER_PATH.getProperty());
		globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);

		String newFolderTYEProgTest = null;
		if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
		{
			String currentDir = globalDTO.getPropertyValue();
			newFolderTYEProgTest = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_TYEProgTest";
			File dirsTYEProgTest = new File(currentDir + newFolderTYEProgTest);
			if (!dirsTYEProgTest.exists())
			{
				dirsTYEProgTest.mkdirs();
			}
			
			Map<PDFContentSearchEnum, String> replaceMap = new HashMap<PDFContentSearchEnum, String>();
			try
			{
				//File file1=createPDFFileByRAISECCode(studentDTO, replaceMap);
				File file = createPDFFileByRAISECCode(studentDTO, replaceMap);
				if (file != null)
				{

					if(newFolderTYEProgTest!=null)
					{
						String filePath = newFolderTYEProgTest + File.separator + FILE_NAME + "_" + DATE_FORMAT.format(new Date());
						

						String saveFile = createReportFilePath(filePath, FILE_TYPE, currentDir);
						OUT.debug("SaveFilePath : {}", saveFile);
						PDFManager pdfManager = new PDFManager(file, saveFile);
						pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);

					}
					if (null != file)
					{
						file.delete();
					}

				
					PasswordGeneratorService.sendNewNotificationForTYEProgTest(roleTypeId,NotificationConstant.MessageTemplateNameAndType.TYE_Prog_Test.name(), studentDTO, newFolderTYEProgTest,token,leadparent);
				}
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		else
		{
			OUT.debug("EMAIL_FOLDER_PATH IS Required");
		}
	}
	
	
	
	
	
	
	
	
	
	public void sendMailForTrialStudentSIATTestLeadStudent(int userId,int id, int roleTypeId) throws Exception
	{
		StudentDetailsDTO studentDTO = null;
		studentDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.EMAIL_FOLDER_PATH.getProperty());
		globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);

		String newFolderP = null;
		String newFolderS = null;
		if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
		{
			String currentDir = globalDTO.getPropertyValue();
			if((studentDTO.getFatherEmailId()!=null && !studentDTO.getFatherEmailId().trim().isEmpty()) || (studentDTO.getMotheremailId()!=null && !studentDTO.getMotheremailId().trim().isEmpty()))
			{
				newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_P";
				File dirsP = new File(currentDir + newFolderP);
				if (!dirsP.exists())
				{
					dirsP.mkdirs();
				}
			}
			else
			{
				newFolderP = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_P";
				File dirsP = new File(currentDir + newFolderP);
				if (!dirsP.exists())
				{
					dirsP.mkdirs();
				}
			}
			

			
			if(studentDTO.getStudentemailId()!=null && !studentDTO.getStudentemailId().trim().isEmpty())
			{
				newFolderS = DATE_FORMAT.format(new Date())+"_"+studentDTO.getId()+"_S";
				File dirsS = new File(currentDir + newFolderS);
				if (!dirsS.exists())
				{
					dirsS.mkdirs();
				}
			}
			
			
			
			
			Map<PDFContentSearchEnum, String> replaceMap = new HashMap<PDFContentSearchEnum, String>();
			try
			{
				//File file1=createPDFFileByRAISECCode(studentDTO, replaceMap);
				File file = createPDFFileByRAISECCode(studentDTO, replaceMap);
				if (file != null)
				{

					if(newFolderP!=null)
					{
						String filePath = newFolderP + File.separator + FILE_NAME + "_" + DATE_FORMAT.format(new Date());
						

						String saveFile = createReportFilePath(filePath, FILE_TYPE, currentDir);
						OUT.debug("SaveFilePath : {}", saveFile);
						PDFManager pdfManager = new PDFManager(file, saveFile);
						pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);

					}
					if(newFolderS!=null)
					{
						String filePath = newFolderS + File.separator + FILE_NAME + "_" + DATE_FORMAT.format(new Date());
						String saveFile = createReportFilePath(filePath, FILE_TYPE, currentDir);
						OUT.debug("SaveFilePath : {}", saveFile);
						PDFManager pdfManager = new PDFManager(file, saveFile);
						pdfManager.editAndSavePDF(PDF_FROM_PAGE, PDF_TO_PAGE, replaceMap);

					}
					
					if (null != file)
					{
						file.delete();
					}

				
					sendEmailSIATTestLeadStudent(roleTypeId, studentDTO, newFolderP,newFolderS,id);
				}
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		else
		{
			OUT.debug("EMAIL_FOLDER_PATH IS Required");
		}
	}
	
	
	private void sendEmailSIATTestLeadStudent(int roleTypeId, StudentDetailsDTO studentDetailsDTO, String filePathP,String filePathS,int id) throws Exception
	{
		StringBuilder headerMessage = new StringBuilder();
		headerMessage.append("Your Report Details are as below");

		//=================Start Sasedeve edited by Mrutyunjaya on 01-04-2017===========
		
//		PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), studentDetailsDTO.getFatheremailId(), roleTypeId,
//				NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_REPORT.name(), filePath, studentDetailsDTO.getFathername());
		
		
		
		String StudentEmailID=null;
		String ParentEmailID=null;
		
		int checkFatherEmailid=0,CheckMotheremailid=0;
		
		
		
		if(studentDetailsDTO.getFatherEmailId()!=null && !studentDetailsDTO.getFatherEmailId().trim().isEmpty())
		{
			
			ParentEmailID=studentDetailsDTO.getFatherEmailId();
			checkFatherEmailid=1;
		}
		
		if(studentDetailsDTO.getMotheremailId()!=null && !studentDetailsDTO.getMotheremailId().trim().isEmpty())
		{
			CheckMotheremailid=1;
			if(ParentEmailID!=null)
			{
				ParentEmailID=ParentEmailID+","+studentDetailsDTO.getMotheremailId();
			}
			else
			{
				ParentEmailID=studentDetailsDTO.getMotheremailId();
			}
			
		}
		
		if(checkFatherEmailid==0 && CheckMotheremailid==0)
		{
			ParentEmailID=studentDetailsDTO.getFatheremailId();
		}
		
		if(studentDetailsDTO.getStudentemailId()!=null && !studentDetailsDTO.getStudentemailId().trim().isEmpty())
		{
			StudentEmailID=studentDetailsDTO.getStudentemailId();
		}
		
		if(StudentEmailID!=null)
		{
			PasswordGeneratorService.sendNewNotificationForLeadParentTrialReport(null, studentDetailsDTO.getName(), StudentEmailID, roleTypeId,
					NotificationConstant.MessageTemplateNameAndType.SIAT_TEST_REPORT_FOR_STUDENT.name(), filePathS, studentDetailsDTO.getFathername(),null);
		}
		
//		if(ParentEmailID!=null)
//		{
//			PasswordGeneratorService.sendNewNotificationForLeadParentTrialReport(null, studentDetailsDTO.getName(), ParentEmailID, roleTypeId,
//					NotificationConstant.MessageTemplateNameAndType.SIAT_TEST_REPORT_FOR_PARENT.name(), filePathP, studentDetailsDTO.getFathername(),null);
//		}
		
		
		//=================END Sasedeve edited by Mrutyunjaya on 01-04-2017===========
		
	}

	

	
	//END Sasedeve edited by Mrutyunjay on date 12-10-2017
	
	private void sendEmailSIATTestLeadParent(int roleTypeId, StudentDetailsDTO studentDetailsDTO, String filePathP,String filePathS,String token) throws Exception
	{
		StringBuilder headerMessage = new StringBuilder();
		headerMessage.append("Your Report Details are as below");

		//=================Start Sasedeve edited by Mrutyunjaya on 01-04-2017===========
		
//		PasswordGeneratorService.sendNewNotificationForTrialReport(null, studentDetailsDTO.getName(), studentDetailsDTO.getFatheremailId(), roleTypeId,
//				NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_REPORT.name(), filePath, studentDetailsDTO.getFathername());
		
		
		
		String StudentEmailID=null;
		String ParentEmailID=null;
		
		int checkFatherEmailid=0,CheckMotheremailid=0;
		
		
		
		if(studentDetailsDTO.getFatherEmailId()!=null && !studentDetailsDTO.getFatherEmailId().trim().isEmpty())
		{
			
			ParentEmailID=studentDetailsDTO.getFatherEmailId();
			checkFatherEmailid=1;
		}
		
		if(studentDetailsDTO.getMotheremailId()!=null && !studentDetailsDTO.getMotheremailId().trim().isEmpty())
		{
			CheckMotheremailid=1;
			if(ParentEmailID!=null)
			{
				ParentEmailID=ParentEmailID+","+studentDetailsDTO.getMotheremailId();
			}
			else
			{
				ParentEmailID=studentDetailsDTO.getMotheremailId();
			}
			
		}
		
		if(checkFatherEmailid==0 && CheckMotheremailid==0)
		{
			ParentEmailID=studentDetailsDTO.getFatheremailId();
		}
		
		if(studentDetailsDTO.getStudentemailId()!=null && !studentDetailsDTO.getStudentemailId().trim().isEmpty())
		{
			StudentEmailID=studentDetailsDTO.getStudentemailId();
		}
		
		if(StudentEmailID!=null)
		{
			PasswordGeneratorService.sendNewNotificationForLeadParentTrialReport(null, studentDetailsDTO.getName(), StudentEmailID, roleTypeId,
					NotificationConstant.MessageTemplateNameAndType.SIAT_TEST_REPORT_FOR_STUDENT.name(), filePathS, studentDetailsDTO.getFathername(),token);
		}
		
		if(ParentEmailID!=null)
		{
			PasswordGeneratorService.sendNewNotificationForLeadParentTrialReport(null, studentDetailsDTO.getName(), ParentEmailID, roleTypeId,
					NotificationConstant.MessageTemplateNameAndType.SIAT_TEST_REPORT_FOR_PARENT.name(), filePathP, studentDetailsDTO.getFathername(),token);
		}
		
		
		//=================END Sasedeve edited by Mrutyunjaya on 01-04-2017===========
		
	}
	
	//start by bharath on 6/7/2019
	public void savetoReferenceTable(int loginId)
	{
		try
		{
			OUT.debug("Inside InterestService (savetoReferenceTable method) loginId:{}",loginId);
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO students = dao.getStudentDetailsByUserId(loginId);
			TumCgtResultDTO tumcgtResult= new TumCgtResultDTO();
			tumcgtResult.setStudentId(students.getId());
			tumcgtResult.setInterestResult(1); 
			TumCgtResultDAO tdoa = new TumCgtResultDAO();
			int id = tdoa.updateInterestTUMCGT(tumcgtResult);
			if(id==0) 
			{
				int value = tdoa.insertInterestTUMCGT(tumcgtResult);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception : {}", e);
		}
	}
	//END by bharath on 6/7/2019

	
}
