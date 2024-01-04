package com.lodestar.edupath.dataaccessobject.dao.induocchoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.AptitudeTestFactorDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.cgt.TableReferenceNormsDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.RAISECCodeOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTForEvalDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class SystemRecommendation
{

	private static final Logger				OUT						= LoggerFactory.getLogger(SystemRecommendation.class);

	public static final String				APP_TEST_SECION			= "Aptitude Test";

	public static final String				INTREST_TEST_SECTION	= "Interest Inventory Test";

	private static final String[]			interestSubFactor		= new String[]

																	{
			"R",
			"A",
			"I",
			"S",
			"E",
			"C"
																	};

	private static final String[]			appSubFactor			= new String[]
																	{
			"RA",
			"NA",
			"VA",
			"SA",
			"MA"
																	};
	private static Map<String, String>		appAbrevation			= new HashMap<String, String>();

	private static int						matrix[][]				= new int[3][3];

	Map<Integer, TableReferenceNormsDTO>	tableRefNormsMap		= new HashMap<Integer, TableReferenceNormsDTO>();

	private int								totalObtainedScore		= 0;

	private int								totalScore				= 120;

	Map<String, Object>						resultMap				= new LinkedHashMap<String, Object>();

	static
	{
		appAbrevation.put("RA", "Reasoning");
		appAbrevation.put("NA", "Numerical");
		appAbrevation.put("VA", "Verbal");
		appAbrevation.put("SA", "Spatial");
		appAbrevation.put("MA", "Mechanical");

		matrix[0] = new int[]
		{
			22,
			10,
			4
		};
		matrix[1] = new int[]
		{
			10,
			5,
			2
		};
		matrix[2] = new int[]
		{
			4,
			2,
			1
		};
	}

	public Map<String, Object> getSystemRecommendation(int studentId, int sex, boolean useSaved) throws Exception
	{
		long startTime = System.currentTimeMillis();
		boolean resultAvailable = false;
		getRecommendationBasedOnTUM(studentId);

		OUT.debug("Time Taken for TUM {} ms", (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		if (useSaved)
		{
			StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,
					studentId);
			if (cgtResult != null && cgtResult.getScore() > 0)
			{
				resultAvailable = true;
				resultMap.put(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name(), cgtResult.getOccupationIds());
				resultMap.put(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name(), cgtResult.getPersonalityCode());
				resultMap.put(ApplicationConstants.SystemRecommendation.SCORE.name(), cgtResult.getScore());
				resultMap.put(ApplicationConstants.SystemRecommendation.APP_SCORE.name(), cgtResult.getAppScore());
				resultMap.put(ApplicationConstants.SystemRecommendation.SS_FACTOR.name(), cgtResult.getAppScore());
			}
		}

		if (!resultAvailable)
		{
			searchNSaveCGTResult(studentId, sex);
		}

		OUT.debug("Time Taken for CGT Result {} ms", (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		if (resultMap.containsKey(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name())
				&& resultMap.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name()) != null)
		{
			List<OccupationDTO> cgtOcc = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_OCCPATION_IDS,
					Arrays.asList(((String) resultMap.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name())).split(",")));
			resultMap.put(ApplicationConstants.SystemRecommendation.CGT.name(), cgtOcc);
		}
		OUT.debug("Time Taken for CGT Occupations {} ms", (System.currentTimeMillis() - startTime));
		return resultMap;
	}

	public void searchNSaveCGTResult(int studentId, int sex) throws Exception
	{
		getRecommendationBasedOnCGT(studentId, sex);
		StudentCGTResult cgtResult = new StudentCGTResult();
		cgtResult.setStudentId(studentId);
		cgtResult.setOccupationIds((String) resultMap.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name()));
		cgtResult.setPersonalityCode((String) resultMap.get(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name()));
		cgtResult.setScore((Double) resultMap.get(ApplicationConstants.SystemRecommendation.SCORE.name()));
		cgtResult.setAppScore((String)resultMap.get(ApplicationConstants.SystemRecommendation.APP_SCORE.name()));
		MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_OR_UPDATE_STUDENT_CGT_OCC_RESULT, cgtResult);
	}

	/**
	 * @param studentId
	 * @throws Exception
	 */
	public void getRecommendationBasedOnTUM(int studentId) throws Exception
	{
		Map<String, Object> parametersObject = new HashMap<String, Object>();
		List<Integer> slNo = new ArrayList<Integer>();
		slNo.add(7);
		slNo.add(8);
		slNo.add(9);
		slNo.add(10);
		slNo.add(11);
		parametersObject.put("studentId", studentId);
		parametersObject.put("questionSlNoList", slNo);
		List<StudentTUMDTO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENT_TUM_FORSTUDENT_BY_SLNO, parametersObject);
		OUT.debug("Student Tum Answers count: {} ", resultList != null ? resultList.size() : 0);

		String intrests = null;
		String strengths = null;
		String aspirations = null;
		String subjects = null;
		for (StudentTUMDTO studentTUMDTO : resultList)
		{
			switch (studentTUMDTO.getQuestionSlNo())
			{
				case 7:
					if (subjects != null)
					{
						subjects += ",";
					}
					else
					{
						subjects = new String();
					}
					subjects += getTopSubjectsFromMarksScored(studentTUMDTO.getAnswer());
					break;
				case 8:
					strengths = trimWhiteSpaceBWComma(studentTUMDTO.getAnswer());
					break;
				case 9:
					intrests = trimWhiteSpaceBWComma(studentTUMDTO.getAnswer());
					break;
				case 10:
					if (subjects != null)
					{
						subjects += ",";
					}
					else
					{
						subjects = new String();
					}
					subjects += trimWhiteSpaceBWComma(studentTUMDTO.getAnswer());
					break;
				case 11:
					aspirations = studentTUMDTO.getAnswer();
					break;
			}
		}
		OUT.debug("Student Tum Answers intrests:{}, strengths:{}, aspirations:{}, subjects:{} ", intrests, strengths, aspirations, subjects);
		if (intrests != null && !intrests.isEmpty())
		{
			List<String> asList = CommonUtil.convertSpaceNOthersSeperatedToList(CommonUtil.replaceDBQuotes(intrests));
			Iterator<String> iterator = asList.iterator();
			while (iterator.hasNext())
			{
				String next = iterator.next();
				if (next.length() < 4)
				{
					iterator.remove();
				}
			}
			List<OccupationDTO> interstsOcc = new ArrayList<OccupationDTO>();
			if (!asList.isEmpty())
			{
				interstsOcc = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_INTERESTS, asList);
			}
			resultMap.put(ApplicationConstants.SystemRecommendation.INTERESTS.name(), interstsOcc);
		}
		if (aspirations != null && !aspirations.isEmpty())
		{
			List<String> asList = CommonUtil.convertSpaceNOthersSeperatedToList(CommonUtil.replaceDBQuotes(aspirations));
			Iterator<String> iterator = asList.iterator();
			while (iterator.hasNext())
			{
				String next = iterator.next();
				if (next.length() < 4)
				{
					iterator.remove();
				}
			}

			List<OccupationDTO> aspirationOcc = new ArrayList<OccupationDTO>();
			if (!asList.isEmpty())
			{
				aspirationOcc = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_ASPIRATIONS, asList);
			}
			resultMap.put(ApplicationConstants.SystemRecommendation.ASPIRATIONS.name(), aspirationOcc);
		}

		if (strengths != null && !strengths.isEmpty())
		{
			List<OccupationDTO> strengthOcc = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_STRENGTHS,
					Arrays.asList(strengths.split(",")));
			resultMap.put(ApplicationConstants.SystemRecommendation.STRENGTHS.name(), strengthOcc);
		}

		if (subjects != null && !subjects.isEmpty())
		{
			List<OccupationDTO> subjectOcc = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_SUBJECTS,
					Arrays.asList(subjects.split(",")));
			resultMap.put(ApplicationConstants.SystemRecommendation.SUBJECTS.name(), subjectOcc);
		}
	}

	private String trimWhiteSpaceBWComma(String str)
	{
		String trimSubjects = new String();
		for (String indvData : str.split(","))
		{
			if (trimSubjects.length() > 0)
			{
				trimSubjects += ",";
			}
			trimSubjects += indvData.trim();
		}
		return trimSubjects;
	}

	public Map<String, Object> getRecommendationBasedOnCGT(int studentId, int sex) throws Exception
	{
		try
		{
			List<TableReferenceNormsDTO> resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_TABLE_REFERENCE_NORMS,
					new TableReferenceNormsDTO());
			for (TableReferenceNormsDTO tableReferenceNormsDTO : resultAsList)
			{
				tableRefNormsMap.put(tableReferenceNormsDTO.getAggreementScore(), tableReferenceNormsDTO);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		Map<Integer, OccupationRating> occpationIndexMap = new HashMap<Integer, OccupationRating>();
		getInterestOccupationScore(studentId, occpationIndexMap, sex);
		Map<String, String> ssFactorMap = new HashMap<String, String>();
		String appOccupationScore = getAppOccupationScore(studentId, occpationIndexMap, ssFactorMap);
		resultMap.put(ApplicationConstants.SystemRecommendation.SCORE.name(), ((double) totalObtainedScore / (double) totalScore) * 100);
		resultMap.put(ApplicationConstants.SystemRecommendation.SS_FACTOR.name(), ssFactorMap);
		resultMap.put(ApplicationConstants.SystemRecommendation.APP_SCORE.name(), appOccupationScore);
		getFinalScore(occpationIndexMap);
		return resultMap;
	}

	private void getFinalScore(Map<Integer, OccupationRating> occpationIndexMap)
	{
		List<OccupationRating> finalOccupationList = new ArrayList<OccupationRating>();
		List<OccupationRating> occupationList = new ArrayList<OccupationRating>();
		List<OccupationRating> newOccupationList = new ArrayList<OccupationRating>(occpationIndexMap.values());
		if (null != newOccupationList && !newOccupationList.isEmpty())
		{
			OUT.debug("Occupation List Size {}", newOccupationList.size());
			for (OccupationRating occupationRating : newOccupationList)
			{
				if (occupationRating.getFinalScore() > 0)
				{
					occupationList.add(occupationRating);
				}
			}
			OUT.debug("New Occupation List Size {}", occupationList.size());
		}

		Collections.sort(occupationList, new Comparator<OccupationRating>()
		{
			public int compare(OccupationRating o1, OccupationRating o2)
			{
				if (o2.getFinalScore() == o1.getFinalScore())
					return 0;
				else if (o2.getFinalScore() > o1.getFinalScore())
					return 1;
				else
					return -1;
			}
		});
		if (occupationList.size() > 3)
		{
			finalOccupationList.addAll(occupationList.subList(0, 2));
		}
		else
		{
			finalOccupationList.addAll(occupationList);
		}
		String occupationsId = null;
		for (OccupationRating occupationRating : finalOccupationList)
		{
			if (occupationsId != null)
			{
				occupationsId += ",";
			}
			else
			{
				occupationsId = new String();
			}
			occupationsId += occupationRating.getOccupationId();
		}
		OUT.debug("Student Aptitude Test Occuaptions {}", occupationsId);
		resultMap.put(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name(), occupationsId);
	}

	private void getInterestOccupationScore(int studentId, Map<Integer, OccupationRating> occpationIndexMap, int sex)
	{
		String raisecSummaryCode = getStudentRAISEC_Code(studentId);
		try
		{
			if (raisecSummaryCode != null && !raisecSummaryCode.trim().isEmpty())
			{

				List<RAISECCodeOccupationDTO> resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_OCCUPATION_RAISEC_MAPPING,
						new RAISECCodeOccupationDTO());
				OccupationRating occupationRating = null;
				int lachanScore = 0;
				for (RAISECCodeOccupationDTO raiseCodeMapping : resultAsList)
				{
					lachanScore = getOccupationLachanAggrementIndex(raisecSummaryCode.toLowerCase(), raiseCodeMapping.getRaiseCode().toLowerCase(), sex);
					occupationRating = new OccupationRating();
					occupationRating.setOccupationId(raiseCodeMapping.getOccupationId());
					occupationRating.setIntrestScore(lachanScore);
					occpationIndexMap.put(raiseCodeMapping.getOccupationId(), occupationRating);
				}
			}
		}

		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	public String getStudentRAISEC_Code(int studentId)
	{
		String raisecSummaryCode = null;
		
		//Start Sasedeve Edited By Mrutyunjaya on Date 18-12-2017
		
		
		/*Map<String, Integer> scoreByFactor = getScoreByFactor(studentId, interestSubFactor, INTREST_TEST_SECTION, false);
		OUT.debug("Scores by Factor:{} ", scoreByFactor);
		int counter = 0;
		for (Entry<String, Integer> entry : scoreByFactor.entrySet())
		{
			if (raisecSummaryCode != null)
			{
				raisecSummaryCode += ",";
			}
			else
			{
				raisecSummaryCode = new String();
			}
			raisecSummaryCode += entry.getKey();
			if (counter == 2)
			{
				break;
			}
			counter++;
		}
		OUT.debug("Student Aptitude RAISEC Code:{} ", raisecSummaryCode);
		resultMap.put(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name(), raisecSummaryCode);*/
		
		SystemRecommendationV2  sysv2=new SystemRecommendationV2();
		
		raisecSummaryCode=sysv2.getStudentRAISEC_Code(studentId);
		
		
		
		//END Sasedeve Edited By Mrutyunjaya on Date 18-12-2017
		
		
		
		return raisecSummaryCode;
	}
	
	public String getTrailStudentRAISEC_Code(int studentId)
	{
		String raisecSummaryCode = null;
		
		//Start Sasedeve Edited By Mrutyunjaya on Date 18-12-2017
		
		
		/*Map<String, Integer> scoreByFactor = getScoreByFactor(studentId, interestSubFactor, INTREST_TEST_SECTION, true);
		OUT.debug("Scores by Factor:{} ", scoreByFactor);
		int counter = 0;
		for (Entry<String, Integer> entry : scoreByFactor.entrySet())
		{
			if (raisecSummaryCode != null)
			{
				raisecSummaryCode += ",";
			}
			else
			{
				raisecSummaryCode = new String();
			}
			raisecSummaryCode += entry.getKey();
			if (counter == 2)
			{
				break;
			}
			counter++;
		}
		OUT.debug("Student Aptitude RAISEC Code:{} ", raisecSummaryCode);
		resultMap.put(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name(), raisecSummaryCode);*/
		
		
		
		SystemRecommendationV2  sysv2=new SystemRecommendationV2();
		
		raisecSummaryCode=sysv2.getStudentRAISEC_Code(studentId);
		
		
		
		//END Sasedeve Edited By Mrutyunjaya on Date 18-12-2017
		
		
		return raisecSummaryCode;
	}

	private int getOccupationLachanAggrementIndex(String raisecSummaryCode, String occupationCode, int sex)
	{
		int code = 0;
		int lachanScore = 0;
		for (int i = 0; i < 3; i++)
		{
			char charAt = occupationCode.charAt(i);
			for (int z = 0; z < 3; z++)
			{
				char charAt2 = raisecSummaryCode.toLowerCase().charAt(z);
				if (charAt == charAt2)
				{
					code += matrix[i][z];
				}
			}
		}

		TableReferenceNormsDTO tableReferenceNormsDTO = tableRefNormsMap.get(code);
		if (tableReferenceNormsDTO != null)
		{
			if (sex == 0)
			{
				lachanScore = tableReferenceNormsDTO.getMaleScore();
			}
			else
			{
				lachanScore = tableReferenceNormsDTO.getFemaleScore();
			}
		}
		return lachanScore;
	}

	private String getAppOccupationScore(int studentId, Map<Integer, OccupationRating> occpationIndexMap, Map<String, String> ssFactorMap) throws Exception
	{
		Map<String, Integer> scoreByFactor = getScoreByFactor(studentId, appSubFactor, APP_TEST_SECION, false);
		String appScores = null;
		for (Entry<String, Integer> entry : scoreByFactor.entrySet())
		{
			ssFactorMap.put(entry.getKey(), getSSFactor(entry.getValue()));

			double score = ((double) entry.getValue() / (double) 12) * 100;

			String string = appAbrevation.get(entry.getKey());
			if (appScores != null)
			{
				appScores += ", ";
			}
			else
			{
				appScores = new String();
			}

			appScores += string + ": " + (int)score +"%";
		}

		List<AptitudeTestFactorDTO> resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_APTITUDE_TEST_FACTOR,
				new AptitudeTestFactorDTO());
		String ssFactor;
		String factor_ID;
		String factor_AC;
		String factor_NA;
		int ar = 0, di = 0;
		double ft = 0f;
		OccupationRating occRating = null;
		for (AptitudeTestFactorDTO aptitudeTestFactorDTO : resultAsList)
		{
			int os = 0, is = 0;
			for (Entry<String, Integer> entry : scoreByFactor.entrySet())
			{
				ssFactor = getSSFactor(entry.getValue());
				if (entry.getKey().equalsIgnoreCase("RA"))
				{
					factor_ID = aptitudeTestFactorDTO.getRA_ID();
					factor_AC = aptitudeTestFactorDTO.getRA_AC();

					factor_NA = aptitudeTestFactorDTO.getRA_NA();
					ar = getARValue(ssFactor, factor_ID, factor_AC, factor_NA);
					di = aptitudeTestFactorDTO.getRA_DI();
				}

				else if (entry.getKey().equalsIgnoreCase("NA"))
				{
					factor_ID = aptitudeTestFactorDTO.getNA_ID();
					factor_AC = aptitudeTestFactorDTO.getNA_AC();
					factor_NA = aptitudeTestFactorDTO.getNA_NA();
					ar = getARValue(ssFactor, factor_ID, factor_AC, factor_NA);
					di = aptitudeTestFactorDTO.getNA_DI();
				}
				else if (entry.getKey().equalsIgnoreCase("VA"))
				{
					factor_ID = aptitudeTestFactorDTO.getVA_ID();
					factor_AC = aptitudeTestFactorDTO.getVA_AC();
					factor_NA = aptitudeTestFactorDTO.getVA_NA();
					ar = getARValue(ssFactor, factor_ID, factor_AC, factor_NA);
					di = aptitudeTestFactorDTO.getVA_DI();
				}
				else if (entry.getKey().equalsIgnoreCase("SA"))
				{
					factor_ID = aptitudeTestFactorDTO.getSA_ID();
					factor_AC = aptitudeTestFactorDTO.getSA_AC();
					factor_NA = aptitudeTestFactorDTO.getSA_NA();
					ar = getARValue(ssFactor, factor_ID, factor_AC, factor_NA);
					di = aptitudeTestFactorDTO.getSA_DI();
				}
				else if (entry.getKey().equalsIgnoreCase("MA"))
				{
					factor_ID = aptitudeTestFactorDTO.getMA_ID();
					factor_AC = aptitudeTestFactorDTO.getMA_AC();
					factor_NA = aptitudeTestFactorDTO.getMA_NA();
					ar = getARValue(ssFactor, factor_ID, factor_AC, factor_NA);
					di = aptitudeTestFactorDTO.getMA_DI();
				}

				os += ar * di;
				is += 2 * di;
				OUT.debug("SS Factor for TestFactor:{}, ssfactor:{}, ar:{}, di:{}, os:{}, is:{}", entry.getKey(), ssFactor, ar, di, os, is);
			}
			if (is > 0 && os > 0)
			{
				ft = ((double) os / (double) is) * 100;
			}
			OUT.debug("Total FT:{}, for OccupationId:{}", ft, aptitudeTestFactorDTO.getOccupationId());

			if (occpationIndexMap.containsKey(aptitudeTestFactorDTO.getOccupationId()))
			{
				occRating = occpationIndexMap.get(aptitudeTestFactorDTO.getOccupationId());
			}
			else
			{
				occRating = new OccupationRating();
				occRating.setOccupationId(aptitudeTestFactorDTO.getOccupationId());
			}
			occRating.setApptitudeScore(ft);
			occpationIndexMap.put(aptitudeTestFactorDTO.getOccupationId(), occRating);
		}
		
		return appScores;
	}

	private int getARValue(String ssFactor, String factor_ID, String factor_AC, String factor_NA)
	{
		int ar = 0;
		if (ssFactor.equalsIgnoreCase(factor_ID))
		{
			ar = 2;
		}
		else if (ssFactor.equalsIgnoreCase(factor_AC))
		{
			ar = 1;
		}
		else if (ssFactor.equalsIgnoreCase(factor_NA))
		{
			ar = 0;
		}
		return ar;
	}

	private String getSSFactor(int score)
	{
		String ssFactor = ApplicationConstants.SSFactor.L.getProperty();
		if (score <= 4)
		{
			ssFactor = ApplicationConstants.SSFactor.L.getProperty();
		}
		else if (score <= 8)
		{
			ssFactor = ApplicationConstants.SSFactor.M.getProperty();
		}
		else if (score >= 9)
		{
			ssFactor = ApplicationConstants.SSFactor.H.getProperty();
		}
		return ssFactor;
	}

	private Map<String, Integer> getScoreByFactor(int studentId, String[] subFactor, String section, boolean isTrail)
	{
		Map<String, Object> parameterObject = new HashMap<String, Object>();
		List<StudentCGTForEvalDTO> resultList;
		Map<String, Integer> factorScoreMap = new HashMap<String, Integer>();
		ValueComparator bvc = new ValueComparator(factorScoreMap);
		TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
		try
		{
			parameterObject.put("section", section);
			parameterObject.put("studentId", studentId);
			for (int i = 0; i < subFactor.length; i++)
			{
				parameterObject.put("factor", subFactor[i]);
				resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENT_CGT_BY_SESSION_FACTOR, parameterObject);
				OUT.debug("Number of questions answered by student {},  factor:{}", resultList.size(), subFactor[i]);

				int score = 0;
				for (StudentCGTForEvalDTO studentCGTForEvalDTO : resultList)
				{
					if (section.equals(INTREST_TEST_SECTION))
					{
						score += studentCGTForEvalDTO.getAnswer().equalsIgnoreCase("yes") ? 1 : 0;
					}
					else
					{
						score += studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(studentCGTForEvalDTO.getCorrectAnswer()) ? 1 : 0;
					}
				}
				if (score > 0 || isTrail)
				{
					factorScoreMap.put(subFactor[i], score);
				}
				OUT.debug("Score for factor:{}, score:{}", subFactor[i], score);

				totalObtainedScore += score;
				OUT.debug("Total Obtained Score:{}", totalObtainedScore);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		sorted_map.putAll(factorScoreMap);

		return sorted_map;
	}

	private String getTopSubjectsFromMarksScored(String answer)
	{
		String top2Subjects = null;
		String scoreType = answer.split(",")[0];
		List<String> sortedSubjects = null;
		if (scoreType.equalsIgnoreCase("marks") || scoreType.equalsIgnoreCase("percentage"))
		{
			sortedSubjects = getSortedSubjectsByMarks(answer);
		}
		else
		{
			sortedSubjects = getSortedSubjectsByGrade(answer);
		}

		OUT.debug("Descending Sorted List {}", sortedSubjects);

		if (sortedSubjects != null && !sortedSubjects.isEmpty())
		{
			for (int i = 0; i <= sortedSubjects.size() - 1; i++)
			{
				if (i > 0)
				{
					top2Subjects += ",";
				}
				else
				{
					top2Subjects = new String();
				}
				top2Subjects += sortedSubjects.get(i);
				if (i >= 1)
				{
					break;
				}
			}

			OUT.debug("Top Subjects {}", top2Subjects);
		}
		return top2Subjects;
	}

	private List<String> getSortedSubjectsByGrade(String answer)
	{
		String[] split = answer.split(",");

		List<SubjectGrade> subjectsGradeList = new ArrayList<SystemRecommendation.SubjectGrade>();
		for (int i = 1; i < split.length; i++)
		{
			String[] subjectMarks = split[i].split(":");
			subjectsGradeList.add(new SubjectGrade(subjectMarks[0], subjectMarks[1]));
		}
		Collections.sort(subjectsGradeList);
		List<String> subjectsList = new ArrayList<String>();
		for (SubjectGrade subjectGrade : subjectsGradeList)
		{
			subjectsList.add(subjectGrade.subject);
		}
		return subjectsList;
	}

	private List<String> getSortedSubjectsByMarks(String answer)
	{
		String[] split = answer.split(",");
		List<SubjectMarks> subjectsMarksList = new ArrayList<SystemRecommendation.SubjectMarks>();
		for (int i = 1; i < split.length; i++)
		{
			String[] subjectMarks = split[i].split(":");
			subjectsMarksList.add(new SubjectMarks(subjectMarks[0], Double.parseDouble(subjectMarks[1])));
		}

		Collections.sort(subjectsMarksList);
		List<String> subjectsList = new ArrayList<String>();
		for (SubjectMarks subjectGrade : subjectsMarksList)
		{
			subjectsList.add(subjectGrade.subject.trim());
		}
		return subjectsList;
	}

	class SubjectGrade implements Comparable<SubjectGrade>
	{
		private String	subject;
		private String	grade;

		public SubjectGrade(String subject, String grade)
		{
			super();
			this.subject = subject.trim();
			this.grade = grade.toLowerCase();
		}

		public Double gpa()
		{
			if (grade.equalsIgnoreCase("A++"))
				return 6.00;
			if (grade.equalsIgnoreCase("A+"))
				return 5.00;
			if (grade.equalsIgnoreCase("A"))
				return 4.00;
			if (grade.equalsIgnoreCase("A-"))
				return 3.9;
			if (grade.equalsIgnoreCase("AB"))
				return 3.80;
			if (grade.equalsIgnoreCase("B+"))
				return 3.33;
			if (grade.equalsIgnoreCase("B"))
				return 3.00;
			if (grade.equalsIgnoreCase("B-"))
				return 2.67;
			if (grade.equalsIgnoreCase("C+"))
				return 2.33;
			if (grade.equalsIgnoreCase("C"))
				return 2.00;
			if (grade.equalsIgnoreCase("C-"))
				return 1.67;
			if (grade.equalsIgnoreCase("D"))
				return 1.00;
			if (grade.equalsIgnoreCase("F"))
				return 0.00;
			return -1.0;
		}

		public int compareTo(SubjectGrade o)
		{
			if (o != null)
			{
				return o.gpa().compareTo(this.gpa());
			}
			return 0;
		}

		public String getSubject()
		{
			return subject;
		}

		public String getGrade()
		{
			return grade;
		}
	}

	class SubjectMarks implements Comparable<SubjectMarks>
	{
		private String	subject;
		private Double	marks;

		public SubjectMarks(String subject, Double marks)
		{
			super();
			this.subject = subject;
			this.marks = marks;
		}

		public int compareTo(SubjectMarks o)
		{
			if (o != null)
			{
				return o.marks.compareTo(this.marks);
			}
			return 0;
		}
	}

	public static void main(String[] args)
	{
		// String marksData = "grade,subject1:A,subject:b,subject3:ab,subject4:b-,subject5:b+";
		String marksData = "percentage,English:98,Hindi:76,Kannada:89,Mathematics:98,Physical and Health Education:96,Physical and Health Education:99,Science :89,Social Science :99";
		System.out.println(new SystemRecommendation().getTopSubjectsFromMarksScored(marksData));
		// System.out.println(new SystemRecommendation().getOccupationLachanAggrementIndex("rai", "ira", 0));
	}

	public Map<String, Object> getResultMap()
	{
		return resultMap;
	}
}

class ValueComparator implements Comparator<String>
{
	Map<String, Integer>	base;

	public ValueComparator(Map<String, Integer> base)
	{
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with equals.
	public int compare(String a, String b)
	{
		if (base.get(a) <= base.get(b))
		{
			return 1;
		}
		else
		{
			return -1;
		} // returning 0 would merge keys
	}
}

class OccupationRating
{
	private int		occupationId;
	private int		intrestScore;
	private double	apptitudeScore;

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public int getIntrestScore()
	{
		return intrestScore;
	}

	public void setIntrestScore(int intrestScore)
	{
		this.intrestScore = intrestScore;
	}

	public double getApptitudeScore()
	{
		return apptitudeScore;
	}

	public void setApptitudeScore(double apptitudeScore)
	{
		this.apptitudeScore = apptitudeScore;
	}

	public double getFinalScore()
	{
		return (intrestScore * 0.3) + (apptitudeScore * 0.7);
	}
}
