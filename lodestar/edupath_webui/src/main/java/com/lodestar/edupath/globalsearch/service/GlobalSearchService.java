package com.lodestar.edupath.globalsearch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.city.CityDAO;
import com.lodestar.edupath.dataaccessobject.dao.college.BoardDAO;
import com.lodestar.edupath.dataaccessobject.dao.college.CollegeActivitiesDAO;
import com.lodestar.edupath.dataaccessobject.dao.college.CollegeDAO;
import com.lodestar.edupath.dataaccessobject.dao.college.CollegeInfraDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.CombinationDAO;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.integratedcourse.IntegratedCourseDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.stream.StreamDAO;
import com.lodestar.edupath.dataaccessobject.dao.tutorial.TutorialCityCentersDAO;
import com.lodestar.edupath.dataaccessobject.dao.tutorial.TutorialDAO;
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
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCityCentersDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCombinationVO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;

public class GlobalSearchService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(GlobalSearchService.class);

	public List<EntranceExamsDTO> getAllExamDetails()
	{
		List<EntranceExamsDTO> list = null;
		try
		{
			list = new EntranceExamsDAO().getAllEntranceExam();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return list;
	}

	public List<OccupationDTO> getAllOccupation()
	{
		List<OccupationDTO> occList = null;
		try
		{
			occList = new OccupationDAO().getOccupationName();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return occList;
	}

	public List<String> getExamWhenList(List<EntranceExamsDTO> examList)
	{
		List<String> whenList = new ArrayList<String>();
		for (EntranceExamsDTO examDTO : examList)
		{
			if (!whenList.contains(examDTO.getExamWhen()))
			{
				whenList.add(examDTO.getExamWhen());
			}
		}
		return whenList;
	}

	public List<IntegratedCourseDTO> getAllIntegratedCourses()
	{
		List<IntegratedCourseDTO> courseList = null;
		try
		{
			courseList = new IntegratedCourseDAO().getAllIntegratedCourseDetails();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return courseList;
	}

	public List<String> getInstituteList(List<IntegratedCourseDTO> courseList)
	{
		List<String> instituteList = new ArrayList<String>();
		for (IntegratedCourseDTO courseDTO : courseList)
		{
			if (!instituteList.contains(courseDTO.getInstitute()))
			{
				instituteList.add(courseDTO.getInstitute());
			}
		}
		return instituteList;
	}

	public List<EntranceExamsDTO> getEntranceExam(EntranceExamsDTO examsDTO)
	{
		List<EntranceExamsDTO> list = null;
		try
		{
			list = new EntranceExamsDAO().getEntranceExams(examsDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return list;
	}

	public List<IntegratedCourseDTO> getIntegratedCourse(IntegratedCourseDTO courseDTO)
	{
		List<IntegratedCourseDTO> list = null;
		try
		{
			list = new IntegratedCourseDAO().getIntegratedCourses(courseDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return list;
	}

	public List<TutorialsDTO> getAllTutorialDetails() throws Exception
	{
		return new TutorialDAO().getAllTutorial();
	}

	public List<CityDTO> getAllCityDetails() throws Exception
	{

		return new CityDAO().getAllCityList();
	}

	public List<CollegeDTO> getAllCollegeDetails() throws Exception
	{

		return new CollegeDAO().getAllCollege();
	}

	public List<StreamsDTO> getAllStreamDetails() throws Exception
	{
		return new StreamDAO().getAllStreams();
	}

	public List<CombinationDTO> getAllCombinationDetails() throws Exception
	{
		return new CombinationDAO().getAllCombinations();
	}

	public List<BoardDTO> getAllBoardDetails() throws Exception
	{
		return new BoardDAO().getAllBoard();
	}

	public Map<Integer, List<String>> getCityLocalityMap(List<CityDTO> cityList) throws Exception
	{
		Map<Integer, List<String>> cityLocalityMap = new HashMap<Integer, List<String>>();
		List<TutorialCityCentersDTO> list = new TutorialCityCentersDAO().getAllTutorialCityCenters();
		if (list != null && !list.isEmpty())
		{
			List<String> localityList;
			for (CityDTO cityDTO : cityList)
			{
				localityList = new ArrayList<String>();
				for (TutorialCityCentersDTO tutorialCityCentersDTO : list)
				{
					if (cityDTO.getId() == tutorialCityCentersDTO.getCityId())
					{
						if (!localityList.contains(tutorialCityCentersDTO.getLocality()))
						{
							localityList.add(tutorialCityCentersDTO.getLocality());
						}
					}
				}
				if (localityList != null && !localityList.isEmpty())
				{
					cityLocalityMap.put(cityDTO.getId(), localityList);
				}
			}
		}
		return cityLocalityMap;
	}

	public Map<Integer, List<String>> getCityLocalityMapForCollege(List<CityDTO> cityList) throws Exception
	{
		Map<Integer, List<String>> cityLocalityMap = new HashMap<Integer, List<String>>();
		List<CollegeDTO> list = new CollegeDAO().getAllCollege();
		if (list != null && !list.isEmpty())
		{
			List<String> localityList;
			for (CityDTO cityDTO : cityList)
			{
				localityList = new ArrayList<String>();
				for (CollegeDTO collegeDTO : list)
				{
					if (cityDTO.getId() == collegeDTO.getCityId())
					{
						if (!localityList.contains(collegeDTO.getLocality()))
						{
							localityList.add(collegeDTO.getLocality());
						}
					}
				}
				if (localityList != null && !localityList.isEmpty())
				{
					cityLocalityMap.put(cityDTO.getId(), localityList);
				}
			}
		}
		return cityLocalityMap;
	}

	public List<CollegeCombinationVO> getCollegeDetailsById(int id) throws Exception
	{
		List<CollegeCombinationVO> collegeList = new CollegeDAO().getCollegeDetailsById(id);
		// for (CollegeCombinationVO collegeCombinationVO : collegeList)
		// {
		// if (collegeCombinationVO.getAddFormSubmDate() != null)
		// {
		// collegeCombinationVO.setAppLastDateStr(TimeUtil.convertTimeAsString(collegeCombinationVO.getAddFormSubmDate(), TimeUtil.EMAIL_FORMAT));
		// }
		// }
		return collegeList;
	}

	public List<TutorialCombinationVO> getTutorialsDetailsById(TutorialsDTO tutorialsDTO) throws Exception
	{
		TutorialDAO tutorialDAO = new TutorialDAO();
		List<TutorialCombinationVO> list = tutorialDAO.getTutorialsDetailsById(tutorialsDTO);
		for (TutorialCombinationVO tutorialCombinationVO : list)
		{
			int rank = tutorialDAO.getHighestRankByTutorialId(tutorialsDTO.getId(), tutorialsDTO.getExamId());
			tutorialCombinationVO.setHighestRank(rank);
		}
		return list;
	}

	public List<CollegeActivitiesDTO> getAllCollegeActivities() throws Exception
	{
		return new CollegeActivitiesDAO().getAllCollegeActivities();
	}

	public List<CollegeInfraDTO> getAllCollegeInfra() throws Exception
	{
		return new CollegeInfraDAO().getAllCollegeInfra();
	}

	public Map<String, Object> getCollegeActivityInfraMap(CollegeCombinationVO collegeCombinationVO)
	{
		Map<String, Object> finalMap = new HashMap<String, Object>();
		Map<String, CollegeActivitiesDTO> collegeActivityMap = new HashMap<String, CollegeActivitiesDTO>();
		Map<String, CollegeInfraDTO> collegeInfraMap = new HashMap<String, CollegeInfraDTO>();
		List<CollegeActivitiesDTO> collegeActivitiesList = collegeCombinationVO.getCollegeActivitiesList();
		List<CollegeInfraDTO> collegeInfraList = collegeCombinationVO.getCollegeInfraList();
		if (null != collegeActivitiesList && !collegeActivitiesList.isEmpty())
		{
			for (CollegeActivitiesDTO collegeActivitiesDTO : collegeActivitiesList)
			{
				collegeActivityMap.put(collegeActivitiesDTO.getName(), collegeActivitiesDTO);
			}
		}
		if (null != collegeInfraList && !collegeInfraList.isEmpty())
		{
			for (CollegeInfraDTO collegeInfraDTO : collegeInfraList)
			{
				collegeInfraMap.put(collegeInfraDTO.getName(), collegeInfraDTO);
			}
		}
		finalMap.put("collegeInfra", collegeInfraMap);
		finalMap.put("collegeActivities", collegeActivityMap);
		return finalMap;
	}

	public List<CollegeDTO> getCollegeDetails(CollegeDTO collegeDTO) throws Exception
	{
		return new CollegeDAO().getAllCollegeDetails(collegeDTO);
	}

	public List<TutorialsDTO> getAllTutorialDetails(TutorialsDTO tutorialsDTO) throws Exception
	{
		return new TutorialDAO().getAllTutorialDetails(tutorialsDTO);
	}
}
