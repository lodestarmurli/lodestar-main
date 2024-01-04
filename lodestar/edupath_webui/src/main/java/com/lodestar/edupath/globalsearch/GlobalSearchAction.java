package com.lodestar.edupath.globalsearch;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.IntegratedCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.college.BoardDTO;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeActivitiesDTO;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeCombinationVO;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeDTO;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeInfraDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.CombinationDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.StreamsDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.ExamTutorialVO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCombinationVO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.globalsearch.service.GlobalSearchService;

public class GlobalSearchAction extends BaseAction
{

	private static final long						serialVersionUID			= 1L;
	private static final Logger						OUT							= LoggerFactory.getLogger(GlobalSearchAction.class);
	private int										examOccId;
	private int										examId;
	private String									examWhen;
	private int										courseOccId;
	private int										courseId;
	private String									institute;
	private int										collegeId;
	private int										streamId;
	private int										combinationId;
	private int										boardId;
	private int										colCityId;
	private String									colLocality;
	private int										tutorialId;
	private int										tutExamId;
	private int										tutCityId;
	private String									tutLocality;
	private List<EntranceExamsDTO>					examList					= new ArrayList<EntranceExamsDTO>();
	private Map<String, List<EntranceExamsDTO>>		examMap						= new HashMap<String, List<EntranceExamsDTO>>();
	private List<IntegratedCourseDTO>				courseList					= new ArrayList<IntegratedCourseDTO>();
	private Map<String, List<IntegratedCourseDTO>>	courseMap					= new HashMap<String, List<IntegratedCourseDTO>>();
	private List<CollegeCombinationVO>				collegeList					= new ArrayList<CollegeCombinationVO>();
	private List<TutorialCombinationVO>				tutorialList				= new ArrayList<TutorialCombinationVO>();
	private List<CollegeActivitiesDTO>				collegeActivitiesDetails	= new ArrayList<CollegeActivitiesDTO>();
	private List<CollegeInfraDTO>					collegeInfraDetails			= new ArrayList<CollegeInfraDTO>();
	private Map<String, CollegeActivitiesDTO>		collegeActivityMap			= new HashMap<String, CollegeActivitiesDTO>();
	private Map<String, CollegeInfraDTO>			collegeInfraMap				= new HashMap<String, CollegeInfraDTO>();
	private List<ExamTutorialVO>					examTutList					= new ArrayList<ExamTutorialVO>();
	private List<CollegeDTO>						collegeDetailsList			= new ArrayList<CollegeDTO>();
	private List<TutorialsDTO>						tutorialDetailsList			= new ArrayList<TutorialsDTO>();
	// private JSONObject collegeObject = new JSONObject();
	private Map<String, String>						collegeObject				= new HashMap<String, String>();
	private Map<Integer, String>					tutorialObject				= new HashMap<Integer, String>();

	public String getExamDetails()
	{
		OUT.debug("GlobalSearchAction : inside getExamDetails");
		try
		{
			GlobalSearchService globalSearchService = new GlobalSearchService();
			List<String> examWhenList = new ArrayList<String>();
			List<OccupationDTO> occupationlist = globalSearchService.getAllOccupation();
			List<EntranceExamsDTO> examList = globalSearchService.getAllExamDetails();
			if (examList != null && !examList.isEmpty())
			{
				examWhenList = globalSearchService.getExamWhenList(examList);
			}
			JSONObject jsonObject = new JSONObject();
			JSONArray occArray = new JSONArray(occupationlist);
			JSONArray examNameArray = new JSONArray(examList);
			JSONArray examWhenArray = new JSONArray(examWhenList);
			jsonObject.put("occupationName", occArray);
			jsonObject.put("examName", examNameArray);
			jsonObject.put("examWhen", examWhenArray);

			// JSONArray jsonArray = new JSONArray(examList);

			PrintWriter writer = response.getWriter();
			response.setContentType("application/json");
			writer.write(jsonObject.toString());
			writer.close();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String getCourseDetails()
	{
		OUT.debug("GlobalSearchAction : inside getCourseDetails");
		try
		{
			GlobalSearchService globalSearchService = new GlobalSearchService();
			List<String> instituteList = new ArrayList<String>();
			List<OccupationDTO> occupationlist = globalSearchService.getAllOccupation();
			List<IntegratedCourseDTO> courseList = globalSearchService.getAllIntegratedCourses();
			if (courseList != null && !courseList.isEmpty())
			{
				instituteList = globalSearchService.getInstituteList(courseList);
			}
			JSONObject jsonObject = new JSONObject();
			JSONArray occArray = new JSONArray(occupationlist);
			JSONArray courseNameArray = new JSONArray(courseList);
			JSONArray instituteArray = new JSONArray(instituteList);
			jsonObject.put("occupationName", occArray);
			jsonObject.put("courseName", courseNameArray);
			jsonObject.put("institute", instituteArray);

			// JSONArray jsonArray = new JSONArray(examList);

			PrintWriter writer = response.getWriter();
			response.setContentType("application/json");
			writer.write(jsonObject.toString());
			writer.close();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String getTutorialDetails()
	{
		OUT.debug("GlobalSearchAction : inside getTutorialDetails");
		try
		{
			GlobalSearchService globalSearchService = new GlobalSearchService();
			List<TutorialsDTO> tutorialList = globalSearchService.getAllTutorialDetails();
			List<EntranceExamsDTO> examList = globalSearchService.getAllExamDetails();
			List<CityDTO> cityList = globalSearchService.getAllCityDetails();
			Map<Integer, List<String>> tutorialCityLocalityMap = null;
			if (cityList != null && !cityList.isEmpty())
			{
				tutorialCityLocalityMap = globalSearchService.getCityLocalityMap(cityList);
			}

			JSONObject jsonObject = new JSONObject();
			JSONArray tutorialNameArray = new JSONArray(tutorialList);
			JSONArray examNameArray = new JSONArray(examList);
			JSONArray cityNameArray = new JSONArray(cityList);

			jsonObject.put("examName", examNameArray);
			jsonObject.put("tutorialName", tutorialNameArray);
			jsonObject.put("cityName", cityNameArray);
			jsonObject.put("tutorialCityLocalityMap", tutorialCityLocalityMap);

			PrintWriter writer = response.getWriter();
			response.setContentType("application/json");
			writer.write(jsonObject.toString());
			writer.close();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String getCollegeDetails()
	{
		OUT.debug("GlobalSearchAction : inside getCollegeDetails");
		try
		{
			GlobalSearchService globalSearchService = new GlobalSearchService();
			List<CollegeDTO> collegeList = globalSearchService.getAllCollegeDetails();
			List<StreamsDTO> streamList = globalSearchService.getAllStreamDetails();
			List<CombinationDTO> combinationList = globalSearchService.getAllCombinationDetails();
			List<BoardDTO> boardList = globalSearchService.getAllBoardDetails();
			List<CityDTO> cityList = globalSearchService.getAllCityDetails();
			Map<Integer, List<String>> collegeCityLocalityMap = null;
			if (cityList != null && !cityList.isEmpty())
			{
				collegeCityLocalityMap = globalSearchService.getCityLocalityMapForCollege(cityList);
			}

			JSONObject jsonObject = new JSONObject();
			JSONArray collegeNameArray = new JSONArray(collegeList);
			
			JSONArray streamNameArray = new JSONArray(streamList);
			JSONArray combinationNameArray = new JSONArray(combinationList);
			JSONArray boardNameArray = new JSONArray(boardList);
			JSONArray cityNameArray = new JSONArray(cityList);
			jsonObject.put("collegeName", collegeNameArray);
			jsonObject.put("streamName", streamNameArray);
			jsonObject.put("combinationName", combinationNameArray);
			jsonObject.put("boardName", boardNameArray);
			jsonObject.put("cityName", cityNameArray);
			jsonObject.put("collegeCityLocalityMap", collegeCityLocalityMap);
			
			
			PrintWriter writer = response.getWriter();
			response.setContentType("application/json");
			writer.write(jsonObject.toString());
			writer.close();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String getExamByData()
	{
		try
		{
			EntranceExamsDTO examsDTO = new EntranceExamsDTO();
			if (examOccId != 0)
			{
				examsDTO.setOccupationId(examOccId);
			}
			if (examId != 0)
			{
				examsDTO.setId(examId);
			}
			if (null != examWhen && "" != examWhen)
			{
				examsDTO.setExamWhen(examWhen);
			}
			examList = new GlobalSearchService().getEntranceExam(examsDTO);
			List<EntranceExamsDTO> list = null;
			if (null != examList && !examList.isEmpty())
			{
				for (EntranceExamsDTO entranceExamsDTO : examList)
				{
					if (null != examMap && examMap.containsKey(entranceExamsDTO.getExamLevel()))
					{
						list = examMap.get(entranceExamsDTO.getExamLevel());
					}
					else
					{
						list = new ArrayList<EntranceExamsDTO>();
						examMap.put(entranceExamsDTO.getExamLevel(), list);
					}
					list.add(entranceExamsDTO);
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showexam";
	}

	public String getCourseByData()
	{
		try
		{
			IntegratedCourseDTO courseDTO = new IntegratedCourseDTO();
			if (courseOccId != 0)
			{
				courseDTO.setOccupationId(courseOccId);
			}
			if (courseId != 0)
			{
				courseDTO.setId(courseId);
			}
			if (null != institute && "" != institute)
			{
				courseDTO.setInstitute(institute);
			}
			courseList = new GlobalSearchService().getIntegratedCourse(courseDTO);
			List<IntegratedCourseDTO> list = null;
			if (null != courseList && !courseList.isEmpty())
			{
				for (IntegratedCourseDTO integratedCourseDTO : courseList)
				{
					if (null != courseMap && courseMap.containsKey(integratedCourseDTO.getProgramType()))
					{
						list = courseMap.get(integratedCourseDTO.getProgramType());
					}
					else
					{
						list = new ArrayList<IntegratedCourseDTO>();
						courseMap.put(integratedCourseDTO.getProgramType(), list);
					}
					list.add(integratedCourseDTO);
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showcourse";
	}

	@SuppressWarnings("unchecked")
	public String getCollegeByData()
	{
		OUT.debug("GlobalSearchAction : inside getCollegeByData method");
		try
		{
			GlobalSearchService globalSearchService = new GlobalSearchService();
			CollegeDTO collegeDTO = new CollegeDTO();
			if (streamId != 0)
			{
				collegeDTO.setStreamId(streamId);
			}
			if (combinationId != 0)
			{
				collegeDTO.setCombinationId(combinationId);
			}
			if (boardId != 0)
			{
				collegeDTO.setAffiliatedToBoardId(boardId);
			}
			if (colCityId != 0)
			{
				collegeDTO.setCityId(colCityId);
			}
			if (null != colLocality && !colLocality.equals(""))
			{
				collegeDTO.setLocality(colLocality);
			}
			if (collegeId != 0)
			{
				collegeList = globalSearchService.getCollegeDetailsById(collegeId);
				collegeActivitiesDetails = globalSearchService.getAllCollegeActivities();
				collegeInfraDetails = globalSearchService.getAllCollegeInfra();
				if (null != collegeList && !collegeList.isEmpty())
				{
					Map<String, Object> finalMap = globalSearchService.getCollegeActivityInfraMap(collegeList.get(0));
					collegeInfraMap = (Map<String, CollegeInfraDTO>) finalMap.get("collegeInfra");
					collegeActivityMap = (Map<String, CollegeActivitiesDTO>) finalMap.get("collegeActivities");
				}
				if (collegeObject != null && !collegeObject.isEmpty())
				{
					CollegeDTO colDto;
					for (Entry<String, String> collegeData : collegeObject.entrySet())
					{
						colDto = new CollegeDTO();
						colDto.setId(Integer.parseInt(collegeData.getKey()));
						colDto.setName(collegeData.getValue());
						collegeDetailsList.add(colDto);
					}
				}
			}
			else
			{
				collegeDetailsList = globalSearchService.getCollegeDetails(collegeDTO);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showcollege";
	}

	public String getTutorialByData()
	{
		OUT.debug("GlobalSearchAction : inside getTutorialByData method");
		try
		{
			GlobalSearchService globalSearchService = new GlobalSearchService();
			TutorialsDTO tutorialsDTO = new TutorialsDTO();
			if (tutExamId != 0)
			{
				tutorialsDTO.setExamId(tutExamId);
			}
			if (tutCityId != 0)
			{
				tutorialsDTO.setCityId(tutCityId);
			}
			if (null != tutLocality && !tutLocality.equals(""))
			{
				tutorialsDTO.setLocality(tutLocality);
			}
			if (tutorialId != 0)
			{
				tutorialsDTO.setStudentId(getStudentSessionObject().getId());
				tutorialsDTO.setId(tutorialId);
				tutorialList = globalSearchService.getTutorialsDetailsById(tutorialsDTO);
				examTutList = tutorialList.get(0).getExamTutorialList();
				if (tutorialObject != null && !tutorialObject.isEmpty())
				{
					TutorialsDTO tutDto;
					for (Entry<Integer, String> tutorialData : tutorialObject.entrySet())
					{
						tutDto = new TutorialsDTO();
						tutDto.setId(tutorialData.getKey());
						tutDto.setName(tutorialData.getValue());
						tutorialDetailsList.add(tutDto);
					}
				}
			}
			else
			{
				tutorialDetailsList = globalSearchService.getAllTutorialDetails(tutorialsDTO);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showtutorial";
	}

	public int getExamOccId()
	{
		return examOccId;
	}

	public void setExamOccId(int examOccId)
	{
		this.examOccId = examOccId;
	}

	public int getExamId()
	{
		return examId;
	}

	public void setExamId(int examId)
	{
		this.examId = examId;
	}

	public String getExamWhen()
	{
		return examWhen;
	}

	public void setExamWhen(String examWhen)
	{
		this.examWhen = examWhen;
	}

	public int getCourseOccId()
	{
		return courseOccId;
	}

	public void setCourseOccId(int courseOccId)
	{
		this.courseOccId = courseOccId;
	}

	public int getCourseId()
	{
		return courseId;
	}

	public void setCourseId(int courseId)
	{
		this.courseId = courseId;
	}

	public String getInstitute()
	{
		return institute;
	}

	public void setInstitute(String institute)
	{
		this.institute = institute;
	}

	public List<EntranceExamsDTO> getExamList()
	{
		return examList;
	}

	public void setExamList(List<EntranceExamsDTO> examList)
	{
		this.examList = examList;
	}

	public Map<String, List<EntranceExamsDTO>> getExamMap()
	{
		return examMap;
	}

	public void setExamMap(Map<String, List<EntranceExamsDTO>> examMap)
	{
		this.examMap = examMap;
	}

	public List<IntegratedCourseDTO> getCourseList()
	{
		return courseList;
	}

	public void setCourseList(List<IntegratedCourseDTO> courseList)
	{
		this.courseList = courseList;
	}

	public Map<String, List<IntegratedCourseDTO>> getCourseMap()
	{
		return courseMap;
	}

	public void setCourseMap(Map<String, List<IntegratedCourseDTO>> courseMap)
	{
		this.courseMap = courseMap;
	}

	public int getCollegeId()
	{
		return collegeId;
	}

	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public int getCombinationId()
	{
		return combinationId;
	}

	public void setCombinationId(int combinationId)
	{
		this.combinationId = combinationId;
	}

	public int getBoardId()
	{
		return boardId;
	}

	public void setBoardId(int boardId)
	{
		this.boardId = boardId;
	}

	public int getColCityId()
	{
		return colCityId;
	}

	public void setColCityId(int colCityId)
	{
		this.colCityId = colCityId;
	}

	public String getColLocality()
	{
		return colLocality;
	}

	public void setColLocality(String colLocality)
	{
		this.colLocality = colLocality;
	}

	public int getTutorialId()
	{
		return tutorialId;
	}

	public void setTutorialId(int tutorialId)
	{
		this.tutorialId = tutorialId;
	}

	public int getTutExamId()
	{
		return tutExamId;
	}

	public void setTutExamId(int tutExamId)
	{
		this.tutExamId = tutExamId;
	}

	public int getTutCityId()
	{
		return tutCityId;
	}

	public void setTutCityId(int tutCityId)
	{
		this.tutCityId = tutCityId;
	}

	public String getTutLocality()
	{
		return tutLocality;
	}

	public void setTutLocality(String tutLocality)
	{
		this.tutLocality = tutLocality;
	}

	public List<TutorialCombinationVO> getTutorialList()
	{
		return tutorialList;
	}

	public void setTutorialList(List<TutorialCombinationVO> tutorialList)
	{
		this.tutorialList = tutorialList;
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

	public List<ExamTutorialVO> getExamTutList()
	{
		return examTutList;
	}

	public void setExamTutList(List<ExamTutorialVO> examTutList)
	{
		this.examTutList = examTutList;
	}

	public List<CollegeDTO> getCollegeDetailsList()
	{
		return collegeDetailsList;
	}

	public void setCollegeDetailsList(List<CollegeDTO> collegeDetailsList)
	{
		this.collegeDetailsList = collegeDetailsList;
	}

	public List<TutorialsDTO> getTutorialDetailsList()
	{
		return tutorialDetailsList;
	}

	public void setTutorialDetailsList(List<TutorialsDTO> tutorialDetailsList)
	{
		this.tutorialDetailsList = tutorialDetailsList;
	}

	public Map<String, String> getCollegeObject()
	{
		return collegeObject;
	}

	public void setCollegeObject(Map<String, String> collegeObject)
	{
		this.collegeObject = collegeObject;
	}

	public Map<Integer, String> getTutorialObject()
	{
		return tutorialObject;
	}

	public void setTutorialObject(Map<Integer, String> tutorialObject)
	{
		this.tutorialObject = tutorialObject;
	}

}
