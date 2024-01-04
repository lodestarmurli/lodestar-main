package com.lodestar.edupath.studentcart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.collegeshortlist.service.CollegeShortListService;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.integratedcourse.IntegratedCourseDAO;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.IntegratedCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCollegeShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTutorialCentreShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeActivitiesDTO;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeCombinationVO;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeInfraDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.student.WishListDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.ExamTutorialVO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCombinationVO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.globalsearch.service.GlobalSearchService;
import com.lodestar.edupath.studentcart.service.StudentCartDetailService;
import com.lodestar.edupath.tutorialshortlist.TutorialCentersShortListService;

public class StudentCartDetailAction extends BaseAction
{
	private static final long						serialVersionUID				= -7078416733161841829L;
	private static Logger							OUT								= LoggerFactory.getLogger(StudentCartDetailAction.class);
	private Map<String, List<WishListDTO>>			occupationWishMap				= new HashMap<String, List<WishListDTO>>();
	private List<ShortListDTO>						shortListDTO					= new ArrayList<ShortListDTO>();
	private List<EntranceExamsDTO>					entranceExamList				= new ArrayList<EntranceExamsDTO>();
	private List<IntegratedCourseDTO>				integratedCourseList			= new ArrayList<IntegratedCourseDTO>();
	private List<? extends Object>					newResultList					= new ArrayList<Object>();
	private List<EduPathShortListDTO>				edupathList						= new ArrayList<EduPathShortListDTO>();
	private String									status;
	private StudentCartDetailService				service;
	private Map<Integer, EntranceExamsDTO>			entranceExamMap					= new HashMap<Integer, EntranceExamsDTO>();
	private Map<Integer, IntegratedCourseDTO>		integratedCourseExamMap			= new HashMap<Integer, IntegratedCourseDTO>();
	private String									type;
	private int										examOrCourseId;
	private IntegratedCourseDTO						integCourseDetails				= new IntegratedCourseDTO();
	private EntranceExamsDTO						entrExamDetails					= new EntranceExamsDTO();
	private List<StudentTutorialCentreShortListDTO>	tutorialCenterShortListDetails	= new ArrayList<StudentTutorialCentreShortListDTO>();
	private List<StudentCollegeShortListDTO>		collegeShortListDetails			= new ArrayList<StudentCollegeShortListDTO>();
	private int										tutorialId;
	private int										collegeId;
	private List<CollegeCombinationVO>				collegeList						= new ArrayList<CollegeCombinationVO>();
	private List<CollegeActivitiesDTO>				collegeActivitiesDetails		= new ArrayList<CollegeActivitiesDTO>();
	private List<CollegeInfraDTO>					collegeInfraDetails				= new ArrayList<CollegeInfraDTO>();
	private Map<String, CollegeActivitiesDTO>		collegeActivityMap				= new HashMap<String, CollegeActivitiesDTO>();
	private Map<String, CollegeInfraDTO>			collegeInfraMap					= new HashMap<String, CollegeInfraDTO>();
	private List<TutorialCombinationVO>				tutorialList					= new ArrayList<TutorialCombinationVO>();
	private List<ExamTutorialVO>					examTutList						= new ArrayList<ExamTutorialVO>();
	private int										tutorialCityCentersId;

	private Map<String, List<Object>>				reportsMap						= new TreeMap<String, List<Object>>();

	public StudentCartDetailAction()
	{
		service = new StudentCartDetailService();
	}
	
	
	//START Sasedeve  by Mrutyunjaya on date 10-05-2017
	private static HashMap<Integer, String>	fitmentcolors			= new HashMap<Integer, String>();

	static
	{
		fitmentcolors.put(ApplicationConstants.FitMent.HIG.getScore(), ApplicationConstants.FitMent.HIG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.A_AVG.getScore(), ApplicationConstants.FitMent.A_AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.AVG.getScore(), ApplicationConstants.FitMent.AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.LOW.getScore(), ApplicationConstants.FitMent.LOW.getColor());
	}
	
	//END Sasedeve  by Mrutyunjaya on date 10-05-2017
	
	

	@Override
	public String execute() throws Exception
	{
		OUT.debug("Inside student cart details");
		String subActionType = request.getParameter("subActionType");
		if (null != subActionType && !subActionType.isEmpty())
		{
			Integer studentId = 0;
			if (subActionType.equalsIgnoreCase("facilitator"))
			{
				studentId = Integer.parseInt(null == request.getParameter("studentId") || request.getParameter("studentId").isEmpty() ? "0" : request
						.getParameter("studentId"));
			}
			else if (subActionType.equalsIgnoreCase("student"))
			{
				int studentUserId = Integer.parseInt(null == request.getParameter("studentUserId") || request.getParameter("studentUserId").isEmpty() ? "0"
						: request.getParameter("studentUserId"));
				studentId = service.getStudentIdByUserId(studentUserId);

			}
			service.getStudentWishOccupationMap(studentId, occupationWishMap);
			shortListDTO = service.getStudentShortList(studentId);
			entranceExamMap = service.getStudentShortListExam(studentId);
			setIntegratedCourseExamMap(service.getStudentShortListIntegratedCourse(studentId));
			setEdupathList(service.getEdupathShortList(studentId));
			tutorialCenterShortListDetails = new TutorialCentersShortListService().getShortListedTutorialCenters(studentId);
			collegeShortListDetails = new CollegeShortListService().getShortListedCollege(studentId);
			reportsMap = service.getReportsByStudentId(studentId);
			request.setAttribute("userType", subActionType);
			return SUCCESS;
		}
		else
		{
			OUT.warn("There is no roleType Define for user, check user role");
		}
		return ERROR;
	}

	public String removeFromCart() throws Exception
	{
		String cartRowValue = request.getParameter("cartRowValue");
		if (null != cartRowValue && !cartRowValue.isEmpty())
		{
			StudentSessionObject studentSessionObject = getStudentSessionObject();
			int studentId = studentSessionObject.getId();
			int id = Integer.parseInt(request.getParameter("id"));
			boolean isDeleteSuccess = false;
			StringBuilder auditMessage = new StringBuilder();
			try
			{
				auditMessage.append(cartRowValue);
				if (cartRowValue.equals("exam"))
				{
					service.removeExamShortList(id, studentId);
					auditMessage.append(" id : ").append(id).append(" deleted from ").append("ExamShortlist");
					isDeleteSuccess = true;
				}
				else if (cartRowValue.equals("integrated_cource"))
				{
					service.removeCourseShortList(id, studentId);
					auditMessage.append(" id : ").append(id).append(" deleted from ").append("CourseShortList");
					isDeleteSuccess = true;
				}
				else if (cartRowValue.equals("edupath"))
				{
					service.removeEdupathShortList(studentId);
					auditMessage.append(" deleted from ").append("EdupathShortList, EdupathElectiveShortList");
					isDeleteSuccess = true;
				}
				else if (cartRowValue.equals("tutorial"))
				{
					service.removeTutorialCenterShortList(id);
					auditMessage.append(" deleted from ").append("StudentTudtorialCentersShortList");
					isDeleteSuccess = true;
				}
				else if (cartRowValue.equals("college"))
				{
					service.removeCollegeShortList(id);
					auditMessage.append(" deleted from ").append("StudentCollegeShortList");
					isDeleteSuccess = true;
				}
				if (isDeleteSuccess)
				{
					auditMessage.append(" for Student: { ").append(studentSessionObject.getFullName()).append(" }");
					insertAudit(auditMessage.toString(), getUserSessionObject());
					status = SUCCESS;
				}
				else
				{
					status = ERROR;
				}

				service.getStudentWishOccupationMap(studentId, occupationWishMap);
				shortListDTO = service.getStudentShortList(studentId);
				entranceExamMap = service.getStudentShortListExam(studentId);
				setIntegratedCourseExamMap(service.getStudentShortListIntegratedCourse(studentId));
				setEdupathList(service.getEdupathShortList(studentId));
				tutorialCenterShortListDetails = new TutorialCentersShortListService().getShortListedTutorialCenters(studentId);
				collegeShortListDetails = new CollegeShortListService().getShortListedCollege(studentId);
				request.setAttribute("userType", "facilitator");

			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
				return ERROR;
			}

		}
		return SUCCESS;
	}

	public String getStudentCourseAndExam()
	{
		try
		{
			OUT.debug("Type : {}, examOrCourseId : {}", type, examOrCourseId);
			if (null != type && !type.isEmpty())
			{
				if (type.equalsIgnoreCase("E"))
				{
					type = "E";
					entrExamDetails = new EntranceExamsDAO().getEntranceExamsById(examOrCourseId);
				}
				else if (type.equalsIgnoreCase("C"))
				{
					type = "C";
					integCourseDetails = new IntegratedCourseDAO().getIntegratedCourseById(examOrCourseId);
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return "showExamAndCourseScreen";
	}

	public String getCollegeDetails()
	{
		OUT.debug("StudentCartDetailAction : inside getCollegeDetails method");
		try
		{
			GlobalSearchService globalSearchService = new GlobalSearchService();
			collegeList = globalSearchService.getCollegeDetailsById(collegeId);
			collegeActivitiesDetails = globalSearchService.getAllCollegeActivities();
			collegeInfraDetails = globalSearchService.getAllCollegeInfra();
			if (null != collegeList && !collegeList.isEmpty())
			{
				Map<String, Object> finalMap = globalSearchService.getCollegeActivityInfraMap(collegeList.get(0));
				collegeInfraMap = (Map<String, CollegeInfraDTO>) finalMap.get("collegeInfra");
				collegeActivityMap = (Map<String, CollegeActivitiesDTO>) finalMap.get("collegeActivities");
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showCollegeScreen";
	}

	public String getTutorialDetails()
	{
		OUT.debug("StudentCartDetailAction : inside getTutorialDetails method");
		try
		{
			TutorialsDTO tutorialsDTO = new TutorialsDTO();
			tutorialsDTO.setStudentId(getStudentSessionObject().getId());
			tutorialsDTO.setId(tutorialId);
			tutorialsDTO.setTutorialCityCentersId(tutorialCityCentersId);
			tutorialList = new GlobalSearchService().getTutorialsDetailsById(tutorialsDTO);
			examTutList = tutorialList.get(0).getExamTutorialList();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showTutorialScreen";
	}

	private void insertAudit(String auditMessage, UserSessionObject userSessionObject)
	{
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.STUDENT_CART, auditMessage, userSessionObject.getLoginId());
	}

	public Map<String, List<WishListDTO>> getOccupationWishMap()
	{
		return occupationWishMap;
	}

	public void setOccupationWishMap(Map<String, List<WishListDTO>> occupationWishMap)
	{
		this.occupationWishMap = occupationWishMap;
	}

	public List<ShortListDTO> getShortListDTO()
	{
		return shortListDTO;
	}

	public void setShortListDTO(List<ShortListDTO> shortListDTO)
	{
		this.shortListDTO = shortListDTO;
	}

	public List<EntranceExamsDTO> getEntranceExamList()
	{
		return entranceExamList;
	}

	public void setEntranceExamList(List<EntranceExamsDTO> entranceExamList)
	{
		this.entranceExamList = entranceExamList;
	}

	public List<IntegratedCourseDTO> getIntegratedCourseList()
	{
		return integratedCourseList;
	}

	public void setIntegratedCourseList(List<IntegratedCourseDTO> integratedCourseList)
	{
		this.integratedCourseList = integratedCourseList;
	}

	public List<? extends Object> getNewResultList()
	{
		return newResultList;
	}

	public void setNewResultList(List<? extends Object> newResultList)
	{
		this.newResultList = newResultList;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public List<EduPathShortListDTO> getEdupathList()
	{
		return edupathList;
	}

	public void setEdupathList(List<EduPathShortListDTO> edupathList)
	{
		this.edupathList = edupathList;
	}

	public Map<Integer, EntranceExamsDTO> getEntranceExamMap()
	{
		return entranceExamMap;
	}

	public void setEntranceExamMap(Map<Integer, EntranceExamsDTO> entranceExamMap)
	{
		this.entranceExamMap = entranceExamMap;
	}

	public Map<Integer, IntegratedCourseDTO> getIntegratedCourseExamMap()
	{
		return integratedCourseExamMap;
	}

	public void setIntegratedCourseExamMap(Map<Integer, IntegratedCourseDTO> integratedCourseExamMap)
	{
		this.integratedCourseExamMap = integratedCourseExamMap;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getExamOrCourseId()
	{
		return examOrCourseId;
	}

	public void setExamOrCourseId(int examOrCourseId)
	{
		this.examOrCourseId = examOrCourseId;
	}

	public IntegratedCourseDTO getIntegCourseDetails()
	{
		return integCourseDetails;
	}

	public void setIntegCourseDetails(IntegratedCourseDTO integCourseDetails)
	{
		this.integCourseDetails = integCourseDetails;
	}

	public EntranceExamsDTO getEntrExamDetails()
	{
		return entrExamDetails;
	}

	public void setEntrExamDetails(EntranceExamsDTO entrExamDetails)
	{
		this.entrExamDetails = entrExamDetails;
	}

	public List<StudentTutorialCentreShortListDTO> getTutorialCenterShortListDetails()
	{
		return tutorialCenterShortListDetails;
	}

	public void setTutorialCenterShortListDetails(List<StudentTutorialCentreShortListDTO> tutorialCenterShortListDetails)
	{
		this.tutorialCenterShortListDetails = tutorialCenterShortListDetails;
	}

	public List<StudentCollegeShortListDTO> getCollegeShortListDetails()
	{
		return collegeShortListDetails;
	}

	public void setCollegeShortListDetails(List<StudentCollegeShortListDTO> collegeShortListDetails)
	{
		this.collegeShortListDetails = collegeShortListDetails;
	}

	public int getTutorialId()
	{
		return tutorialId;
	}

	public void setTutorialId(int tutorialId)
	{
		this.tutorialId = tutorialId;
	}

	public int getCollegeId()
	{
		return collegeId;
	}

	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}

	public List<CollegeCombinationVO> getCollegeList()
	{
		return collegeList;
	}

	public void setCollegeList(List<CollegeCombinationVO> collegeList)
	{
		this.collegeList = collegeList;
	}

	public List<CollegeActivitiesDTO> getCollegeActivitiesDetails()
	{
		return collegeActivitiesDetails;
	}

	public void setCollegeActivitiesDetails(List<CollegeActivitiesDTO> collegeActivitiesDetails)
	{
		this.collegeActivitiesDetails = collegeActivitiesDetails;
	}

	public List<CollegeInfraDTO> getCollegeInfraDetails()
	{
		return collegeInfraDetails;
	}

	public void setCollegeInfraDetails(List<CollegeInfraDTO> collegeInfraDetails)
	{
		this.collegeInfraDetails = collegeInfraDetails;
	}

	public Map<String, CollegeActivitiesDTO> getCollegeActivityMap()
	{
		return collegeActivityMap;
	}

	public void setCollegeActivityMap(Map<String, CollegeActivitiesDTO> collegeActivityMap)
	{
		this.collegeActivityMap = collegeActivityMap;
	}

	public Map<String, CollegeInfraDTO> getCollegeInfraMap()
	{
		return collegeInfraMap;
	}

	public void setCollegeInfraMap(Map<String, CollegeInfraDTO> collegeInfraMap)
	{
		this.collegeInfraMap = collegeInfraMap;
	}

	public List<TutorialCombinationVO> getTutorialList()
	{
		return tutorialList;
	}

	public void setTutorialList(List<TutorialCombinationVO> tutorialList)
	{
		this.tutorialList = tutorialList;
	}

	public List<ExamTutorialVO> getExamTutList()
	{
		return examTutList;
	}

	public void setExamTutList(List<ExamTutorialVO> examTutList)
	{
		this.examTutList = examTutList;
	}

	public int getTutorialCityCentersId()
	{
		return tutorialCityCentersId;
	}

	public void setTutorialCityCentersId(int tutorialCityCentersId)
	{
		this.tutorialCityCentersId = tutorialCityCentersId;
	}

	public Map<String, List<Object>> getReportsMap()
	{
		return reportsMap;
	}

	public void setReportsMap(Map<String, List<Object>> reportsMap)
	{
		this.reportsMap = reportsMap;
	}
	
	//START Sasedeve  by Mrutyunjaya on date 10-05-2017
	public HashMap<Integer, String> getFitmentcolors()
	{
		return fitmentcolors;
	}
//END Sasedeve  by Mrutyunjaya on date 10-05-2017

}
