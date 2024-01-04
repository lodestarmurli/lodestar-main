package com.lodestar.edupath.path.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.calculatefitmentcolor.CalculateFitmentColor;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.integratedcourse.IntegratedCourseDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.path.EduPathDAO;
import com.lodestar.edupath.dataaccessobject.dao.path.EduPathDegreesDAO;
import com.lodestar.edupath.dataaccessobject.dao.path.EduPathPGDAO;
import com.lodestar.edupath.dataaccessobject.dao.path.EduPathPUElectivesDAO;
import com.lodestar.edupath.dataaccessobject.dao.stuinduocchoice.MyShortListDAO;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.IntegratedCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathDegreeSpecializationDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathDegreesDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathPGDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathPGSpecializationDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathPUElectivesDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EdupathCountDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathPUStreamsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;
import com.lodestar.edupath.datatransferobject.enumtype.EduPathEnum;

public class EduPathService
{
	private static final Logger	OUT				= LoggerFactory.getLogger(EduPathService.class);
	private DecimalFormat		decimalFarmat	= new DecimalFormat("#");

	public List<ShortListDTO> getTopShortList(int studentId)
	{
		List<ShortListDTO> shortList = null;
		//BEGIN Sasedeve Added by Mrutyunjaya on Date 19-05-2017
				List<ShortListDTO> shortList1 = null;
		try
		{
			MyShortListDAO shortListDAO = new MyShortListDAO();
			shortList = shortListDAO.getTopShortListDetailsByStudentId(studentId);
			
			
			CalculateFitmentColor calfit=new CalculateFitmentColor();
			
			shortList1=calfit.getOccupationFitmentForShortList(shortList,studentId);
			
			//END Sasedeve Added by Mrutyunjaya on Date 19-05-2017
			
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return shortList1;
	}

	public Map<String, Object> getIndustryDetails(int industryId)
	{
		Map<String, Object> finalMapSend = new LinkedHashMap<String, Object>();
		try
		{
			Map<String, Object> finalMap = new LinkedHashMap<String, Object>();
			List<EdupathCountDTO> puStream = new EduPathDAO().getEdupathCountForPUStreamByIndus(industryId);
			getPUIndustryStreamData(finalMap, puStream);
			finalMap.put("IND_ID", industryId);
			finalMapSend.put("PU", finalMap);

			finalMap = new LinkedHashMap<String, Object>();
			List<EdupathCountDTO> degreeStream = new EduPathDAO().getEdupathCountForDegreeStreamByIndus(industryId);
			getDegreeIndustryStreamData(finalMap, degreeStream);
			finalMapSend.put("DG", finalMap);

			finalMap = new LinkedHashMap<String, Object>();
			List<EdupathCountDTO> top5DegreeSecial = null;
			List<EdupathCountDTO> degreeSpecialization = new EduPathDAO().getEdupathCountForDegreeSpecialByIndus(industryId);
			getTop5Specilization(finalMap, top5DegreeSecial, degreeSpecialization);
			finalMapSend.put("TOP", finalMap);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		return finalMapSend;
	}

	private void getTop5Specilization(Map<String, Object> finalMap, List<EdupathCountDTO> top5DegreeSecial, List<EdupathCountDTO> degreeSpecialization)
	{
		Map<String, Integer> degreeSepcilizationMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		int count = 0;
		for (EdupathCountDTO edupathCountDTO : degreeSpecialization)
		{
			String[] split = edupathCountDTO.getName().split(",");
			for (String specializationName : split)
			{
				count = 1;
				if (degreeSepcilizationMap.containsKey(specializationName.trim()))
				{
					count = degreeSepcilizationMap.get(specializationName.trim());
					count++;
				}
				degreeSepcilizationMap.put(specializationName.trim(), count);
			}
		}

		List<EdupathCountDTO> degreeSpecialization1 = new ArrayList<EdupathCountDTO>();
		EdupathCountDTO dto = null;
		for (Entry<String, Integer> specializationEntry : degreeSepcilizationMap.entrySet())
		{
			dto = new EdupathCountDTO();
			dto.setName(specializationEntry.getKey());
			dto.setJobCount(specializationEntry.getValue());
			degreeSpecialization1.add(dto);
		}

		Collections.sort(degreeSpecialization1, new JobCountComparator());
		if (degreeSpecialization1.size() > 5)
		{
			top5DegreeSecial = degreeSpecialization1.subList(0, 4);
		}
		else if (degreeSpecialization1.size() > 0)
		{
			top5DegreeSecial = degreeSpecialization1;
		}

		finalMap.put("top5_degree_sepcial", top5DegreeSecial);
	}

	private void getDegreeIndustryStreamData(Map<String, Object> finalMap, List<EdupathCountDTO> degreeStream)
	{
		int totalJobCount = 0;
		int mandatoryStreamCount = 0;
		int anyStreamCount = 0;
		Map<Integer, EdupathCountDTO> degreeEdupathMandMap = new HashMap<Integer, EdupathCountDTO>();
		Map<Integer, EdupathCountDTO> degreeEdupathPrefMap = new HashMap<Integer, EdupathCountDTO>();
		for (EdupathCountDTO edupathCountDTO : degreeStream)
		{
			if (EduPathEnum.COMPULSORY.getValue().equalsIgnoreCase(edupathCountDTO.getType())
					&& !EduPathEnum.ANY.getValue().equalsIgnoreCase(edupathCountDTO.getName()))
			{
				degreeEdupathMandMap.put(edupathCountDTO.getEdupathId(), edupathCountDTO);
			}
			else if (!degreeEdupathMandMap.containsKey(edupathCountDTO.getEdupathId())
					|| EduPathEnum.ANY.getValue().equalsIgnoreCase(degreeEdupathMandMap.get(edupathCountDTO.getEdupathId()).getName()))
			{
				degreeEdupathPrefMap.put(edupathCountDTO.getEdupathId(), edupathCountDTO);
			}
		}
		mandatoryStreamCount = degreeEdupathMandMap.size();
		anyStreamCount = degreeEdupathPrefMap.size();
		totalJobCount = mandatoryStreamCount + anyStreamCount;

		Map<String, Integer> degreeEdupathDegreeMandMap = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
		Map<String, Integer> degreeEdupathDegreePrefMap = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
		Integer jobCount = 1;
		String[] split;
		for (Entry<Integer, EdupathCountDTO> edupathEntry : degreeEdupathMandMap.entrySet())
		{
			split = edupathEntry.getValue().getName().split(",");
			for (String degreeStreamName : split)
			{
				jobCount = 1;
				if (degreeEdupathDegreeMandMap.containsKey(degreeStreamName.trim()))
				{
					jobCount = degreeEdupathDegreeMandMap.get(degreeStreamName.trim());
					jobCount++;
				}
				degreeEdupathDegreeMandMap.put(degreeStreamName.trim(), jobCount);
			}
		}

		List<EdupathCountDTO> mandatoryStreamCountList = new ArrayList<EdupathCountDTO>();
		EdupathCountDTO edupathCountDTO;
		for (Entry<String, Integer> streamCountEntry : degreeEdupathDegreeMandMap.entrySet())
		{
			edupathCountDTO = new EdupathCountDTO();
			edupathCountDTO.setJobCount(streamCountEntry.getValue());
			edupathCountDTO.setName(streamCountEntry.getKey());
			edupathCountDTO.setPercentage(((double) edupathCountDTO.getJobCount() / (double) mandatoryStreamCount) * 100);
			edupathCountDTO.setPercentageStr(decimalFarmat.format(((double) edupathCountDTO.getJobCount() / (double) mandatoryStreamCount) * 100));

			mandatoryStreamCountList.add(edupathCountDTO);
		}

		for (Entry<Integer, EdupathCountDTO> edupathEntry : degreeEdupathPrefMap.entrySet())
		{
			split = edupathEntry.getValue().getName().split(",");
			for (String degreeStreamName : split)
			{
				jobCount = 1;
				if (degreeEdupathDegreePrefMap.containsKey(degreeStreamName.trim()))
				{
					jobCount = degreeEdupathDegreePrefMap.get(degreeStreamName.trim());
					jobCount++;
				}
				degreeEdupathDegreePrefMap.put(degreeStreamName.trim(), jobCount);
			}
		}

		List<EdupathCountDTO> anyStreamCountList = new ArrayList<EdupathCountDTO>();
		for (Entry<String, Integer> streamCountEntry : degreeEdupathDegreePrefMap.entrySet())
		{
			edupathCountDTO = new EdupathCountDTO();
			edupathCountDTO.setJobCount(streamCountEntry.getValue());
			edupathCountDTO.setName(streamCountEntry.getKey());
			edupathCountDTO.setPercentage(((double) edupathCountDTO.getJobCount() / (double) anyStreamCount) * 100);
			edupathCountDTO.setPercentageStr(decimalFarmat.format(((double) edupathCountDTO.getJobCount() / (double) anyStreamCount) * 100));

			anyStreamCountList.add(edupathCountDTO);
		}
		Collections.sort(mandatoryStreamCountList, new JobCountComparator());
		Collections.sort(anyStreamCountList, new JobCountComparator());

		finalMap.put("degree_mandat_percent", decimalFarmat.format(((double) mandatoryStreamCount / (double) totalJobCount) * 100));
		finalMap.put("degree_any_percent", decimalFarmat.format(((double) anyStreamCount / (double) totalJobCount) * 100));
		finalMap.put("degree_mandat_stream", mandatoryStreamCountList);
		finalMap.put("degree_any_stream", anyStreamCountList);
	}

	private void getPUIndustryStreamData(Map<String, Object> finalMap, List<EdupathCountDTO> puStream)
	{
		List<EdupathCountDTO> mandatoryStreamCountList = new ArrayList<EdupathCountDTO>();
		List<EdupathCountDTO> anyStreamCountList = new ArrayList<EdupathCountDTO>();

		int totalJobCount = 0;
		int mandatoryStreamCount = 0;
		int anyStreamCount = 0;

		for (EdupathCountDTO edupathCountDTO : puStream)
		{
			totalJobCount += edupathCountDTO.getJobCount();
			if (edupathCountDTO.getType().equalsIgnoreCase(EduPathEnum.COMPULSORY.getValue()) && edupathCountDTO.getStreamId() > 0)
			{
				mandatoryStreamCount += edupathCountDTO.getJobCount();
				mandatoryStreamCountList.add(edupathCountDTO);
			}
			else
			{
				anyStreamCount += edupathCountDTO.getJobCount();
				anyStreamCountList.add(edupathCountDTO);
			}
		}

		for (EdupathCountDTO edupathCountDTO : mandatoryStreamCountList)
		{
			edupathCountDTO.setPercentage(((double) edupathCountDTO.getJobCount() / (double) mandatoryStreamCount) * 100);
			edupathCountDTO.setPercentageStr(decimalFarmat.format(((double) edupathCountDTO.getJobCount() / (double) mandatoryStreamCount) * 100));
		}

		for (EdupathCountDTO edupathCountDTO : anyStreamCountList)
		{
			edupathCountDTO.setPercentage(((double) edupathCountDTO.getJobCount() / (double) anyStreamCount) * 100);
			edupathCountDTO.setPercentageStr(decimalFarmat.format(((double) edupathCountDTO.getJobCount() / (double) anyStreamCount) * 100));
		}

		Collections.sort(mandatoryStreamCountList, new JobCountComparator());
		Collections.sort(anyStreamCountList, new JobCountComparator());

		finalMap.put("pu_mandat_percent", decimalFarmat.format(((double) mandatoryStreamCount / (double) totalJobCount) * 100));
		finalMap.put("pu_any_percent", decimalFarmat.format(((double) anyStreamCount / (double) totalJobCount) * 100));
		finalMap.put("pu_mandat_stream", mandatoryStreamCountList);
		finalMap.put("pu_any_stream", anyStreamCountList);

	}

	public JSONArray getOccupationName(String dataStr)
	{
		OUT.debug("Inside EduPathService other Occupation");
		JSONArray jsonArr = new JSONArray();
		Map<String, String> matchMap = null;
		try
		{
			if (null != dataStr && !dataStr.isEmpty())
			{
				JSONArray arr = new JSONArray(dataStr);
				matchMap = new HashMap<String, String>();

				for (int a = 0; a < arr.length(); a++)
				{
					JSONObject js = arr.getJSONObject(a);
					matchMap.put(js.getString("name"), js.getString("name"));
				}
			}
			List<OccupationDTO> list = new OccupationDAO().getOccupationName();

			if (null != list && !list.isEmpty())
			{
				if (null != matchMap && !matchMap.isEmpty())
				{
					for (OccupationDTO dto : list)
					{

						if (!matchMap.containsKey(dto.getName()))
						{
							JSONObject json = new JSONObject();
							json.put("id", dto.getId());
							json.put("name", dto.getName());
							jsonArr.put(json);
						}
					}

				}
				else
				{
					for (OccupationDTO dto : list)
					{
						JSONObject json = new JSONObject();
						json.put("id", dto.getId());
						json.put("name", dto.getName());
						jsonArr.put(json);
					}
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return jsonArr;
	}

	public Map<String, Map<String, Object>> createEduPathDetails(String[] idArray, String option,Integer i)
	{
		OUT.debug("Inside EduPathService");

		Map<String, Map<String, Object>> finalMap = new LinkedHashMap<String, Map<String, Object>>();
		boolean isMatch = false;
		try
		{
			int opt = 1;
			for (String id : idArray)
			{
				if (option.equals("OCCUPATION"))
				{
					finalMap.put("OPTION_" + opt, getOccupationDetails(Integer.valueOf(id.trim()),i));
				}
				else
				{
					if (!id.isEmpty())
					{
						finalMap.put("OPTION_" + opt, getIndustryDetails(Integer.valueOf(id.trim())));
					}

				}
				opt++;
			}
			if (option.equals("OCCUPATION"))
			{
				if (finalMap.size() > 1)
				{
					isMatch = isSameStreamAndSubject(finalMap);
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return finalMap;
	}

	public Map<String, Object> getOccupationDetails(int occId, Integer cityId)
	{
		OUT.debug("Inside EduPathService");
		Map<String, Object> finalMap = new LinkedHashMap<String, Object>();
		try
		{
			EduPathDTO edudto = new EduPathDTO();
			edudto.setOccupationId(occId);
			edudto = new EduPathDAO().getEduPath(edudto);
			if (null != edudto)
			{
				List<EduPathPUStreamsDTO> puStreamslist = new EduPathDAO().getPUStreamsByOccId(edudto.getId());

				EduPathPUElectivesDTO eduELDTO = new EduPathPUElectivesDTO();

				List<Integer> IdList = new ArrayList<Integer>();

				List<String> commonList = new ArrayList<String>();

				if (null != puStreamslist && !puStreamslist.isEmpty())
				{

					for (EduPathPUStreamsDTO puStreamsDTO : puStreamslist)
					{
						if (EduPathEnum.COMPULSORY.getValue().equalsIgnoreCase(puStreamsDTO.getType())
								&& EduPathEnum.ANY.getValue().equalsIgnoreCase(puStreamsDTO.getStreamName()))
						{
							for (EduPathPUStreamsDTO puDTO : puStreamslist)
							{
								if (puDTO.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()) && !EduPathEnum.ANY.getValue().equalsIgnoreCase(puDTO.getStreamName()))
								{
									commonList.add(puDTO.getStreamName());
									IdList.add(puDTO.getStreamId());
								}
							}
							if (!commonList.isEmpty())
							{
								eduELDTO.setStreamName(puStreamsDTO.getStreamName() + " (" + StringUtils.join(commonList, ", ") + ")");
								eduELDTO.setPreferrdStreamId(StringUtils.join(IdList, ","));
								IdList.clear();
								commonList.clear();

							}
							else
							{
								eduELDTO.setStreamName(puStreamsDTO.getStreamName());
							}
							eduELDTO.setStreamId(puStreamsDTO.getStreamId());
							eduELDTO.setType(EduPathEnum.COMPULSORY.getValue());
							eduELDTO.setIsAnyStream("");
							eduELDTO.setType(null);
							break;
						}
						else if (EduPathEnum.COMPULSORY.getValue().equalsIgnoreCase(puStreamsDTO.getType())
								&& !EduPathEnum.ANY.getValue().equalsIgnoreCase(puStreamsDTO.getStreamName()))
						{

							eduELDTO.setStreamName(puStreamsDTO.getStreamName());
							eduELDTO.setStreamId(puStreamsDTO.getStreamId());
							eduELDTO.setType(EduPathEnum.COMPULSORY.getValue());
							eduELDTO.setIsAnyStream("");
							eduELDTO.setType(null);
							break;
						}
						else if (EduPathEnum.ANY.getValue().equalsIgnoreCase(puStreamsDTO.getStreamName()))
						{
							for (EduPathPUStreamsDTO puDTO : puStreamslist)
							{
								if (puDTO.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()))
								{
									commonList.add(puDTO.getStreamName());
									IdList.add(puDTO.getStreamId());
								}
							}
							if (!commonList.isEmpty())
							{
								eduELDTO.setStreamName(EduPathEnum.ANY.getValue() + " (" + StringUtils.join(commonList, " ,") + ")");
								eduELDTO.setPreferrdStreamId(StringUtils.join(IdList, ","));
								eduELDTO.setStreamId(puStreamsDTO.getStreamId());
								eduELDTO.setIsAnyStream(EduPathEnum.ISANY.getValue());
								eduELDTO.setType(EduPathEnum.ANY.getValue());
								IdList.clear();
								commonList.clear();
							}
							else
							{
								if (null != puStreamsDTO.getStreamName())
								{
									eduELDTO.setStreamName(puStreamsDTO.getStreamName());
								}
								else
								{
									eduELDTO.setStreamName("");
								}
								eduELDTO.setStreamName(puStreamsDTO.getStreamName());
								eduELDTO.setStreamId(puStreamsDTO.getStreamId());
								eduELDTO.setIsAnyStream(EduPathEnum.ISANY.getValue());
								eduELDTO.setType(EduPathEnum.ANY.getValue());
							}
							break;
						}
						else if (EduPathEnum.PREFERRED.getValue().equalsIgnoreCase(puStreamsDTO.getType()))
						{
							eduELDTO.setStreamId(-1);
							eduELDTO.setPreferrdStreamId(String.valueOf(puStreamsDTO.getStreamId()));
							eduELDTO.setStreamName(EduPathEnum.ANY.getValue() + " (" + puStreamsDTO.getStreamName() + ")");
							eduELDTO.setIsAnyStream(EduPathEnum.ISANY.getValue());
							eduELDTO.setType(EduPathEnum.ANY.getValue());
							// break;
						}
					}
				}
				else
				{
					eduELDTO.setStreamId(-1);
					eduELDTO.setStreamName(EduPathEnum.ANY.getValue());
					eduELDTO.setIsAnyStream(EduPathEnum.ISANY.getValue());
					eduELDTO.setType(EduPathEnum.ANY.getValue());
				}

				eduELDTO.setEduPathId(edudto.getId());
				List<EduPathPUElectivesDTO> elList = new EduPathPUElectivesDAO().getPuElective(eduELDTO);

				if (null != elList && !elList.isEmpty())
				{

					for (EduPathPUElectivesDTO elDTO : elList)
					{
						if (EduPathEnum.COMPULSORY.getValue().equalsIgnoreCase(elDTO.getType())
								&& EduPathEnum.ANY.getValue().equalsIgnoreCase(elDTO.getSubjectName()))
						{

							for (EduPathPUElectivesDTO eduPathPUElectivesDTO : elList)
							{
								if (eduPathPUElectivesDTO.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()) && !EduPathEnum.ANY.getValue().equalsIgnoreCase(eduPathPUElectivesDTO.getSubjectName()))
								{
									commonList.add(eduPathPUElectivesDTO.getSubjectName());
									IdList.add(eduPathPUElectivesDTO.getSubjectId());
								}
							}
							if (!commonList.isEmpty())
							{
								eduELDTO.setSubjectName(elDTO.getSubjectName() + " (" + StringUtils.join(commonList, ", ") + " )");
								eduELDTO.setPreferrdSubjectId(StringUtils.join(IdList, ","));
								IdList.clear();
								commonList.clear();
							}
							else
							{

								eduELDTO.setSubjectName(elDTO.getSubjectName());
							}
							eduELDTO.setSubjectId(elDTO.getSubjectId());
							eduELDTO.setType(EduPathEnum.COMPULSORY.getValue());
							eduELDTO.setIsAnySubject("");
							eduELDTO.setType(null);
							break;
						}
						else if (EduPathEnum.COMPULSORY.getValue().equalsIgnoreCase(elDTO.getType())
								&& !EduPathEnum.ANY.getValue().equalsIgnoreCase(elDTO.getSubjectName()))
						{
							eduELDTO.setSubjectName(elDTO.getSubjectName());
							eduELDTO.setSubjectId(elDTO.getSubjectId());
							eduELDTO.setType(EduPathEnum.COMPULSORY.getValue());
							eduELDTO.setIsAnySubject("");
							eduELDTO.setType(null);
							break;
						}
						else if (EduPathEnum.ANY.getValue().equalsIgnoreCase(elDTO.getSubjectName()))
						{

							for (EduPathPUElectivesDTO eduPathPUElectivesDTO : elList)
							{
								if (eduPathPUElectivesDTO.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()))
								{
									commonList.add(eduPathPUElectivesDTO.getSubjectName());
									IdList.add(eduPathPUElectivesDTO.getSubjectId());
								}
							}
							if (!commonList.isEmpty())
							{
								eduELDTO.setSubjectName(EduPathEnum.ANY.getValue() + " (" + StringUtils.join(commonList, " ,") + ")");
								eduELDTO.setPreferrdSubjectId(StringUtils.join(IdList, ","));
								IdList.clear();
								commonList.clear();
								eduELDTO.setSubjectId(elDTO.getSubjectId());
								eduELDTO.setIsAnySubject(EduPathEnum.ISANY.getValue());
								eduELDTO.setType(EduPathEnum.ANY.getValue());
							}
							else
							{
								if (null != elDTO.getSubjectName())
								{
									eduELDTO.setSubjectName(elDTO.getSubjectName());
								}
								else
								{
									eduELDTO.setSubjectName("");
								}

								eduELDTO.setSubjectId(elDTO.getSubjectId());
								eduELDTO.setIsAnySubject(EduPathEnum.ISANY.getValue());
								eduELDTO.setType(EduPathEnum.ANY.getValue());
							}
							break;
						}
						else if (EduPathEnum.PREFERRED.getValue().equalsIgnoreCase(elDTO.getType()))
						{
							eduELDTO.setSubjectId(-1);
							eduELDTO.setPreferrdSubjectId(String.valueOf(elDTO.getSubjectId()));
							eduELDTO.setSubjectName(EduPathEnum.ANY.getValue() + " (" + elDTO.getSubjectName() + ")");
							eduELDTO.setIsAnySubject(EduPathEnum.ISANY.getValue());
							eduELDTO.setType(EduPathEnum.ANY.getValue());
							// break;
						}
					}
					eduELDTO.setOccupationId(occId);
					finalMap.put("PU", eduELDTO);
				}
				else
				{
					eduELDTO.setSubjectId(-1);
					eduELDTO.setSubjectName(EduPathEnum.ANY.getValue());
					eduELDTO.setIsAnySubject(EduPathEnum.ISANY.getValue());
					eduELDTO.setType(EduPathEnum.ANY.getValue());
					eduELDTO.setOccupationId(occId);
					finalMap.put("PU", eduELDTO);
				}

				String streamName = "", subjectName = "";

				// Degree
				EduPathDegreesDTO dgDTO = new EduPathDegreesDTO();
				dgDTO.setEduPathId(edudto.getId());
				List<EduPathDegreesDTO> eduDegreeDTO = new EduPathDegreesDAO().getEduPathDegreesById(dgDTO);

				// Degree Specialization
				EduPathDegreeSpecializationDTO dgSPDTO = new EduPathDegreeSpecializationDTO();
				dgSPDTO.setEduPathId(edudto.getId());
				List<EduPathDegreeSpecializationDTO> degreeSpList = new EduPathDegreesDAO().getEduPathDegreesSpecializationById(dgSPDTO);

				EduPathPGDTO eduPGDTO = new EduPathPGDTO();
				eduPGDTO.setEduPathId(edudto.getId());
				eduPGDTO = new EduPathPGDAO().getEduPathPGById(eduPGDTO);

				EduPathPGSpecializationDTO eduPGSPDTO = new EduPathPGSpecializationDTO();
				eduPGSPDTO.setEduPathId(edudto.getId());
				eduPGSPDTO = new EduPathPGDAO().getEduPathPGSpecializationById(eduPGSPDTO);

				List<IntegratedCourseDTO> integratedCourseList = new IntegratedCourseDAO().getIntegratedCourseDetailsByOccupatioId(occId);
				//change Vyankltesh 11-4-2017
				//   old//  List<EntranceExamsDTO> examList = new EntranceExamsDAO().getEntranceExamsDetailsByOccupatioId(occId);
				List<EntranceExamsDTO> examList = new EntranceExamsDAO().getEntranceExamsDetailsByOccupatioIdNational(occId);
				List<EntranceExamsDTO> stateexamList = new EntranceExamsDAO().getEntranceExamsDetailsByOccupatioIdstate(occId,cityId);

				eduELDTO = new EduPathPUElectivesDTO();

				if (null != eduDegreeDTO && !eduDegreeDTO.isEmpty())
				{
					for (EduPathDegreesDTO eduPathDegreesDTO : eduDegreeDTO)
					{
						if (EduPathEnum.COMPULSORY.getValue().equalsIgnoreCase(eduPathDegreesDTO.getType())
								&& EduPathEnum.ANY.getValue().equalsIgnoreCase(eduPathDegreesDTO.getName()))
						{
							boolean isAdd = false;
							for (EduPathDegreesDTO dTO : eduDegreeDTO)
							{
								if (dTO.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()) && !EduPathEnum.ANY.getValue().equalsIgnoreCase(dTO.getName()))
								{
									streamName = eduPathDegreesDTO.getName() + " (" + dTO.getName() + ")";
									isAdd = true;
								}
							}
							if (!isAdd)
							{
								streamName = eduPathDegreesDTO.getName();
							}
							break;
						}
						else if (EduPathEnum.COMPULSORY.getValue().equalsIgnoreCase(eduPathDegreesDTO.getType())
								&& !EduPathEnum.ANY.getValue().equalsIgnoreCase(eduPathDegreesDTO.getName()))
						{
							streamName = eduPathDegreesDTO.getName();
							break;
						}
						else if (EduPathEnum.ANY.getValue().equalsIgnoreCase(eduPathDegreesDTO.getName()))
						{
							boolean isAdd = false;
							for (EduPathDegreesDTO dTO : eduDegreeDTO)
							{
								if (dTO.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()) && !EduPathEnum.ANY.getValue().equalsIgnoreCase(dTO.getName()))
								{
									streamName = EduPathEnum.ANY.getValue() + " (" + dTO.getName() + ")";
									isAdd = true;
								}
							}
							if (!isAdd)
							{
								streamName = eduPathDegreesDTO.getName();
							}
							break;
						}
					}
					eduELDTO.setStreamName(streamName);
					streamName = "";
				}

				if (null != degreeSpList && !degreeSpList.isEmpty())
				{
					for (EduPathDegreeSpecializationDTO eduPathDegreeSpecializationDTO : degreeSpList)
					{
						if (EduPathEnum.COMPULSORY.getValue().equalsIgnoreCase(eduPathDegreeSpecializationDTO.getType())
								&& EduPathEnum.ANY.getValue().equalsIgnoreCase(eduPathDegreeSpecializationDTO.getName()))
						{
							boolean isAdd = false;
							for (EduPathDegreeSpecializationDTO dto : degreeSpList)
							{
								if (dto.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()) && !EduPathEnum.ANY.getValue().equalsIgnoreCase(dto.getName()))
								{
									subjectName = eduPathDegreeSpecializationDTO.getName() + " (" + dto.getName() + ")";
									isAdd = true;
								}
							}

							if (!isAdd)
							{
								subjectName = eduPathDegreeSpecializationDTO.getName();
							}
							break;
						}
						else if (EduPathEnum.COMPULSORY.getValue().equalsIgnoreCase(eduPathDegreeSpecializationDTO.getType())
								&& !EduPathEnum.ANY.getValue().equalsIgnoreCase(eduPathDegreeSpecializationDTO.getName()))
						{
							subjectName = eduPathDegreeSpecializationDTO.getName();
							break;
						}
						else if (EduPathEnum.ANY.getValue().equalsIgnoreCase(eduPathDegreeSpecializationDTO.getName()))
						{
							boolean isAdd = false;
							for (EduPathDegreeSpecializationDTO dto : degreeSpList)
							{
								if (dto.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()) && !EduPathEnum.ANY.getValue().equalsIgnoreCase(dto.getName()))
								{
									subjectName = EduPathEnum.ANY.getValue() + " (" + dto.getName() + ")";
									isAdd = true;
								}
							}

							if (!isAdd)
							{
								subjectName = eduPathDegreeSpecializationDTO.getName();
							}
							break;
						}
					}
					eduELDTO.setSubjectName(subjectName);
					subjectName = "";
				}
				finalMap.put("DG", eduELDTO);

				eduELDTO = new EduPathPUElectivesDTO();
				if (null != eduPGDTO)
				{
					eduELDTO.setStreamName(eduPGDTO.getName());
				}
				else
				{
					eduELDTO.setStreamName("");
				}
				if (null != eduPGSPDTO)
				{
					eduELDTO.setSubjectName(eduPGSPDTO.getName());
				}
				else
				{
					eduELDTO.setSubjectName("");
				}
				finalMap.put("PG", eduELDTO);
				
				
				boolean isRequirede = false;
				for (EntranceExamsDTO entranceExamsDTO : stateexamList)
				{
					if (entranceExamsDTO.getRequired().equalsIgnoreCase(EduPathEnum.REQUIRED_C.getValue()))
					{
						isRequirede = true;
					}
					commonList.add("\t" + entranceExamsDTO.getExamName());
				}

				EntranceExamsDTO dtoe = new EntranceExamsDTO();
				if (isRequirede)
				{
					dtoe.setRequired(EduPathEnum.REQUIRED_C.getValue());
				}
				else
				{
					dtoe.setRequired(EduPathEnum.REQUIRED_P.getValue());
				}
				dtoe.setExamName(StringUtils.join(commonList, ","));
				commonList.clear();
				dtoe.setOccupationId(occId);
				
				finalMap.put("STATEEXAM", dtoe);
				
				
				
				

				if (null != integratedCourseList && !integratedCourseList.isEmpty())
				{
					for (IntegratedCourseDTO integratedCourseDTO : integratedCourseList)
					{
						commonList.add("\t" + integratedCourseDTO.getProgramName());
					}
					IntegratedCourseDTO dto = new IntegratedCourseDTO();
					dto.setProgramName(StringUtils.join(commonList, ","));
					dto.setOccupationId(occId);
					finalMap.put("COURSES", dto);
					commonList.clear();
				}

				if (null != examList && !examList.isEmpty())
				{
					boolean isRequired = false;
					for (EntranceExamsDTO entranceExamsDTO : examList)
					{
						if (entranceExamsDTO.getRequired().equalsIgnoreCase(EduPathEnum.REQUIRED_C.getValue()))
						{
							isRequired = true;
						}
						commonList.add("\t" + entranceExamsDTO.getExamName());
					}

					EntranceExamsDTO dto = new EntranceExamsDTO();
					if (isRequired)
					{
						dto.setRequired(EduPathEnum.REQUIRED_C.getValue());
					}
					else
					{
						dto.setRequired(EduPathEnum.REQUIRED_P.getValue());
					}
					dto.setExamName(StringUtils.join(commonList, ","));
					commonList.clear();
					dto.setOccupationId(occId);
					finalMap.put("EXAM", dto);
				}
				//vyankatesh Added
//				if (null != stateexamList && !stateexamList.isEmpty())
//				{
					
					//OUT.debug("Inside EduPathActionwrewwwrwrwr"+getStudentSessionObject().getCityId());
					
				//}
				
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return finalMap;
	}

	class JobCountComparator implements Comparator<EdupathCountDTO>
	{
		public int compare(EdupathCountDTO o1, EdupathCountDTO o2)
		{
			if (o2.getJobCount() == o1.getJobCount()) return 0;
			else if (o2.getJobCount() > o1.getJobCount()) return 1;
			else return -1;
		}
	}

	private boolean isSameStreamAndSubject(Map<String, Map<String, Object>> finalMap)
	{
		EduPathPUElectivesDTO firstDTO = (EduPathPUElectivesDTO) finalMap.get("OPTION_1").get("PU");
		EduPathPUElectivesDTO secondDTO = (EduPathPUElectivesDTO) finalMap.get("OPTION_2").get("PU");
		if (null != firstDTO && null != secondDTO)
		{
			/*
			 * if
			 * ((firstDTO.getStreamName().startsWith((EduPathEnum.ANY.getValue
			 * ()))) &&
			 * secondDTO.getType().equalsIgnoreCase(EduPathEnum.COMPULSORY
			 * .getValue())) { finalMap.get("OPTION_2").put("choseELAlert",
			 * "Y"); } else { finalMap.get("OPTION_2").put("choseELAlert", "N");
			 * }
			 */

			if (firstDTO.getStreamName().equalsIgnoreCase(secondDTO.getStreamName()) && firstDTO.getSubjectName().equalsIgnoreCase(secondDTO.getSubjectName()))
			{
				finalMap.get("OPTION_2").put("choseELActive", "Y");
				return true;
			}
			else if (firstDTO.getStreamName().equalsIgnoreCase(secondDTO.getStreamName()))
			{
				finalMap.get("OPTION_2").put("choseELActive", "Y");
				return true;
			}
			else if (!firstDTO.getStreamName().startsWith(EduPathEnum.ANY.getValue()) && !firstDTO.getSubjectName().startsWith(EduPathEnum.ANY.getValue()))
			{
				finalMap.get("OPTION_2").put("choseELActive", "Y");
				return true;
			}
			else if (firstDTO.getStreamName().startsWith((EduPathEnum.ANY.getValue())))
			{
				finalMap.get("OPTION_2").put("choseELActive", "F_Y");
				return true;
			}
			else if (secondDTO.getStreamName().startsWith(EduPathEnum.ANY.getValue()))
			{
				finalMap.get("OPTION_2").put("choseELActive", "F_Y");
				return true;
			}
			finalMap.get("OPTION_2").put("choseELActive", "N");
		}
		return false;
	}

	public Map<String, Object> isSameStreamAndSubject(Map<String, Object> finalMap, String name)
	{
		Map<String, Map<String, Object>> newMap = (Map<String, Map<String, Object>>) finalMap.get("OCCUPATION");
		EduPathPUElectivesDTO firstDTO = (EduPathPUElectivesDTO) newMap.get("OPTION_1").get("PU");
		if (null != firstDTO)
		{
			if (!firstDTO.getStreamName().startsWith(EduPathEnum.ANY.getValue()))
			{
				newMap.get("OPTION_1").put("choseELActive", "Y");
			}
			else if (firstDTO.getStreamName().startsWith(EduPathEnum.ANY.getValue()))
			{
				newMap.get("OPTION_1").put("choseELActive", "F_Y");
			}
			else
			{
				newMap.get("OPTION_1").put("choseELActive", "N");
			}

		}
		finalMap.put("OCCUPATION", newMap);
		return finalMap;
	}

	public static void main(String[] args)
	{
		Map<String, Map<String, Object>> finalMap = new EduPathService().createEduPathDetails(new String[]
		{
				"1",
				"2"
		}, "OCCUPATION",null);
		/*
		 * Map<String, Object> ob = finalMap.get("OPTION_1");
		 * System.out.println("\n::::: " + finalMap.get("OPTION_1").size());
		 * vyankatesh Null added
		 */
		System.out.println(finalMap);
	}

}
