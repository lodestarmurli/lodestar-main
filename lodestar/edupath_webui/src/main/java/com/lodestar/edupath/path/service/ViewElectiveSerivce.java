package com.lodestar.edupath.path.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.city.CityDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.CombinationDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.SubjectDAO;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.industry.IndustryDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.stream.StreamDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.CombinationDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathElectiveShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.StreamsDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.SubjectDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.EduPathEnum;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class ViewElectiveSerivce
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ViewElectiveSerivce.class);

	public Map<String, Object> getElectiveData(String option1Id, String option1ManStreamId, String option1PreStreamIds, String option1ManSubjectId,
			String option1PreSubjectIds, String option2Id, String option2ManStreamId, String option2PreStreamIds, String option2ManSubjectId,
			String option2PreSubjectIds, String selectedStreamId, String selectedSubjectId, int cityId, int studentId) throws NumberFormatException, Exception
	{
		if (cityId <= 0)
		{
			StudentDetailsDTO studentDetailsById = new StudentDetailsDAO().getStudentDetailsByStudentId(studentId);
			StudentCityLockDTO studentcityById = new EntranceExamsDAO().getCityIdByStudentId(studentId);
			
			if(null != studentcityById  && !studentcityById.toString().isEmpty())
			{
				cityId = studentcityById.getCityLockId();
			}
			else
			{
				cityId = studentDetailsById.getCityId();
			}
			
			
			
		}

		String option1Name = null, option2Name = null;
		if (option1Id.startsWith("occ"))
		{
			option1Name = new OccupationDAO().getOccupationBasicDetails(getOptionId(option1Id)).getName();
		}
		if (option2Id != null && option2Id.startsWith("occ"))
		{
			option2Name = new OccupationDAO().getOccupationBasicDetails(getOptionId(option2Id)).getName();
		}
		if (option1Id.startsWith("ind"))
		{
			option1Name = new IndustryDAO().getIndustryById(getOptionId(option1Id)).getName();
		}
		if (option2Id != null && option2Id.startsWith("ind"))
		{
			option2Name = new IndustryDAO().getIndustryById(getOptionId(option2Id)).getName();
		}

		// List<CombinationDTO> combinationDetails = getElectiveCombinationWithCollegeCount(
		// (selectedStreamId != null && !selectedStreamId.trim().isEmpty()) ? Integer.valueOf(selectedStreamId) : -1,
		// (selectedSubjectId != null && !selectedSubjectId.trim().isEmpty()) ? Integer.valueOf(selectedSubjectId) : -1, cityId);

		List<SubjectDTO> subjectsList = new SubjectDAO().getSubjectsByIds(getSubjectIds(option2ManSubjectId, option2PreSubjectIds, option1ManSubjectId,
				option1PreSubjectIds));
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(-1);
		subjectDTO.setName(EduPathEnum.ANY.getValue());
		subjectsList.add(subjectDTO);

		Map<String, Object> finalMap = new HashMap<String, Object>();
		List<StreamsDTO> allStreams = new StreamDAO().getAllStreams();
		finalMap.put("noneAnyStream", allStreams);

		List<StreamsDTO> allInNoneStreams = new ArrayList<StreamsDTO>(allStreams);
		StreamsDTO stream = new StreamsDTO();
		stream.setId(-1);
		stream.setName(EduPathEnum.ANY.getValue());
		allInNoneStreams.add(stream);

		finalMap.put("streams", allInNoneStreams);
		// finalMap.put("combinations", combinationDetails);
		finalMap.put("subjectList", subjectsList);
		finalMap.put("option1Name", option1Name);
		finalMap.put("option2Name", option2Name);
		finalMap.put("currentCity", cityId);
		finalMap.put("cities", getCities());

		return finalMap;
	}

	private List<Integer> getSubjectIds(String option2ManSubjectId, String option2PreSubjectIds, String option1ManSubjectId, String option1PreSubjectIds)
	{
		List<Integer> subjectIds = new ArrayList<>();
		if (option1ManSubjectId != null && !option1ManSubjectId.trim().isEmpty() && !option1ManSubjectId.trim().equals("-1"))
		{
			subjectIds.add(Integer.parseInt(option1ManSubjectId));
		}
		if (option2ManSubjectId != null && !option2ManSubjectId.trim().isEmpty() && !option2ManSubjectId.trim().equals("-1"))
		{
			subjectIds.add(Integer.parseInt(option2ManSubjectId));
		}
		if (option1PreSubjectIds != null && !option1PreSubjectIds.trim().isEmpty() && !option1PreSubjectIds.trim().equals("-1"))
		{
			subjectIds.addAll(CommonUtil.convertCommaSeperatedToIntegerList(option1PreSubjectIds));
		}
		if (option2PreSubjectIds != null && !option2PreSubjectIds.trim().isEmpty() && !option2PreSubjectIds.trim().equals("-1"))
		{
			subjectIds.addAll(CommonUtil.convertCommaSeperatedToIntegerList(option2PreSubjectIds));
		}

		return subjectIds;
	}

	private int getOptionId(String optionId)
	{
		return Integer.parseInt(optionId.substring(4));
	}

	public List<CityDTO> getCities() throws Exception
	{
		return new CityDAO().getAllCityList();
	}

	public List<CombinationDTO> getElectiveCombinationWithCollegeCount(int streamId, int subjectId, int cityId) throws Exception
	{
		return new CombinationDAO().getCombinationListForElectiveScreen(streamId, cityId, subjectId);
	}

	public void addEduNElectiveShortList(int studentId, String puStreamId, String option1Id, String option1ManStreamId, int option1OccIndusId,
			String option1DegreeName, int option1EdupathId, String option2Id, String option2ManStreamId, int option2OccIndusId, String option2DegreeName,
			int option2EdupathId, String selectedCombinations) throws Exception
	{
		EduPathShortListDTO edupath1 = new EduPathShortListDTO();
		edupath1.setStudentId(studentId);
		edupath1.setPuStreamId(Integer.parseInt(puStreamId));

		if (option1Id.startsWith("occ"))
		{
			edupath1.setOccupationId(getOptionId(option1Id));
			edupath1.setOccIndustryId(option1OccIndusId);
			edupath1.setEdupathId(option1EdupathId);
		}
		else
		{
			edupath1.setIndustryId(getOptionId(option1Id));
		}

		edupath1.setDegreeStream(option1DegreeName);
		edupath1.setPriority(1);

		EduPathShortListDTO edupath2 = null;
		if (option2Id != null && !option2Id.trim().isEmpty() && (option2ManStreamId.equalsIgnoreCase("-1") || puStreamId.equalsIgnoreCase(option2ManStreamId)))
		{
			edupath2 = new EduPathShortListDTO();
			edupath2.setStudentId(studentId);
			edupath2.setPuStreamId(Integer.parseInt(puStreamId));

			if (option2Id.startsWith("occ"))
			{
				edupath2.setOccupationId(getOptionId(option2Id));
				edupath2.setOccIndustryId(option2OccIndusId);
				edupath2.setEdupathId(option2EdupathId);
			}
			else
			{
				edupath2.setIndustryId(getOptionId(option2Id));
			}

			edupath2.setDegreeStream(option2DegreeName);
			edupath2.setPriority(3);
		}
		// combinationId, noOfCollege

		List<EduPathElectiveShortListDTO> shortListItems = new ArrayList<EduPathElectiveShortListDTO>();
		EduPathElectiveShortListDTO shortListItem = null;
		String[] split = selectedCombinations.split(",");
		for (String combinationNum : split)
		{
			String[] split2 = combinationNum.split(":");
			shortListItem = new EduPathElectiveShortListDTO();
			shortListItem.setCombinationId(Integer.parseInt(split2[0]));
			shortListItem.setNoOfCollege(Integer.parseInt(split2[1]));
			shortListItem.setStudentId(studentId);

			shortListItems.add(shortListItem);
		}

		new EduPathShortListDAO().insertEdupathNElective(edupath1, edupath2, shortListItems);
	}
	
	public boolean checkEdupathExists(Integer studentId) throws Exception
	{
		boolean isExists = false;
		List<EduPathShortListDTO> eduPathShortListByStudentId = new EduPathShortListDAO().getEduPathShortListByStudentId(studentId);
		if(eduPathShortListByStudentId.size() > 0)
		{
			isExists = true;
		}
		
		return isExists;
	}
}
