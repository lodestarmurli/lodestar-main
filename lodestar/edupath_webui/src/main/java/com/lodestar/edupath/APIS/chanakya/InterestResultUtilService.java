package com.lodestar.edupath.APIS.chanakya;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.chanakya.ChanakyaStudentOccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.newscoringlogic.NewScoringLogicForTrafficLight;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTForEvalDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.FitMent;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class InterestResultUtilService {

	private static final String YES = "yes";

	private static final Logger OUT = LoggerFactory.getLogger(InterestResultUtilService.class);

	public static final String APP_TEST_SECION = "Aptitude Test";

	public static final String INTREST_TEST_SECTION = "Interest Inventory Test";

	private static final String[] interestSubFactor = new String[]

	{ ApplicationConstants.PERSONALITY.A.getName(), ApplicationConstants.PERSONALITY.C.getName(),
			ApplicationConstants.PERSONALITY.E.getName(), ApplicationConstants.PERSONALITY.I.getName(),
			ApplicationConstants.PERSONALITY.R.getName(), ApplicationConstants.PERSONALITY.S.getName() };

	private static final String[] appSubFactor = new String[] { ApplicationConstants.ABILITY.MA.getName(),
			ApplicationConstants.ABILITY.NA.getName(), ApplicationConstants.ABILITY.RA.getName(),
			ApplicationConstants.ABILITY.SA.getName(), ApplicationConstants.ABILITY.VA.getName(), };
	private static Map<String, String> appAbrevation = new HashMap<String, String>();

	private static Map<String, Map<String, String[]>> keyMap;

	Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

	static {
		appAbrevation.put(ApplicationConstants.ABILITY.MA.getName(), "Mechanical");
		appAbrevation.put(ApplicationConstants.ABILITY.NA.getName(), "Numerical");
		appAbrevation.put(ApplicationConstants.ABILITY.RA.getName(), "Reasoning");
		appAbrevation.put(ApplicationConstants.ABILITY.SA.getName(), "Spatial");
		appAbrevation.put(ApplicationConstants.ABILITY.VA.getName(), "Verbal");

		// tie breaking questions
		Map<String, String[]> RTieQuestions = new HashMap<String, String[]>();
		RTieQuestions.put(ApplicationConstants.PERSONALITY.I.getName(), new String[] { "13", "32", "55" });
		RTieQuestions.put(ApplicationConstants.PERSONALITY.A.getName(), new String[] { "31", });
		RTieQuestions.put(ApplicationConstants.PERSONALITY.S.getName(), new String[] { "37", "49", "52" });
		RTieQuestions.put(ApplicationConstants.PERSONALITY.E.getName(), new String[] { "17", "25", "47" });
		RTieQuestions.put(ApplicationConstants.PERSONALITY.C.getName(), new String[] { "7", "48", "60" });

		Map<String, String[]> ITieQuestions = new HashMap<String, String[]>();
		ITieQuestions.put(ApplicationConstants.PERSONALITY.A.getName(), new String[] { "14", });
		ITieQuestions.put(ApplicationConstants.PERSONALITY.S.getName(), new String[] { "22", "38", "50" });
		ITieQuestions.put(ApplicationConstants.PERSONALITY.E.getName(), new String[] { "20", "26", "59" });
		ITieQuestions.put(ApplicationConstants.PERSONALITY.C.getName(), new String[] { "24", "44", "42" });

		Map<String, String[]> STieQuestions = new HashMap<String, String[]>();
		STieQuestions.put(ApplicationConstants.PERSONALITY.E.getName(), new String[] { "4", "16", "29" });
		STieQuestions.put(ApplicationConstants.PERSONALITY.A.getName(), new String[] { "10", });
		STieQuestions.put(ApplicationConstants.PERSONALITY.C.getName(), new String[] { "28", "40", "54" });

		Map<String, String[]> ETieQuestions = new HashMap<String, String[]>();
		ETieQuestions.put(ApplicationConstants.PERSONALITY.C.getName(), new String[] { "11", "36", "53" });
		ETieQuestions.put(ApplicationConstants.PERSONALITY.A.getName(), new String[] { "41", });

		Map<String, String[]> CTieQuestions = new HashMap<String, String[]>();
		CTieQuestions.put(ApplicationConstants.PERSONALITY.A.getName(), new String[] { "30", });

		// RTieQuestions,ITieQuestions,STieQuestions, ETieQuestions, CTieQuestions
		keyMap = new HashMap<String, Map<String, String[]>>();
		keyMap.put(ApplicationConstants.PERSONALITY.R.getName(), RTieQuestions);
		keyMap.put(ApplicationConstants.PERSONALITY.I.getName(), ITieQuestions);
		keyMap.put(ApplicationConstants.PERSONALITY.S.getName(), STieQuestions);
		keyMap.put(ApplicationConstants.PERSONALITY.E.getName(), ETieQuestions);
		keyMap.put(ApplicationConstants.PERSONALITY.C.getName(), CTieQuestions);
	}
	List<OccupationDTO> RecommendedOccupationList = null;

	private String PersonalityCode;

	public String getPersonalityCode() {
		return PersonalityCode;
	}

	public void setPersonalityCode(String personalityCode) {
		PersonalityCode = personalityCode;
	}

	public Map<String, Object> getSystemRecommendation(int studentId, boolean useSaved) throws Exception {
		long startTime = System.currentTimeMillis();
		boolean resultAvailable = false;
		if (useSaved) {
			StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance()
					.getResultAsObjectById(MyBatisMappingConstants.GET_DH_STUDENT_CGT_RESULT, studentId);
			if (cgtResult != null && (cgtResult.getSsFactor() != null && !cgtResult.getSsFactor().trim().isEmpty())) {
				resultAvailable = true;
				resultMap.put(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name(),
						cgtResult.getOccupationIds());
				resultMap.put(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name(),
						cgtResult.getPersonalityCode());
				resultMap.put(ApplicationConstants.SystemRecommendation.SS_FACTOR.name(), cgtResult.getSsFactor());
				resultMap.put(ApplicationConstants.SystemRecommendation.APP_SCORE.name(), cgtResult.getAppScore());

			}
		}

		if (!resultAvailable) {
			searchNSaveCGTResult(studentId);
		}
		OUT.debug("Time Taken for CGT Result {} ms", (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();

		if ((resultMap.containsKey(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name())
				&& resultMap.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name()) != null)
				&& (!resultMap.containsKey(ApplicationConstants.SystemRecommendation.CGT.name())
						|| resultMap.get(ApplicationConstants.SystemRecommendation.CGT.name()) == null)) {
			List<OccupationDTO> cgtOcc = MyBatisManager.getInstance()
					.getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_OCCPATION_IDS, Arrays.asList(
							((String) resultMap.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name()))
									.split(",")));

			updateFitment(cgtOcc, (String) resultMap.get(ApplicationConstants.SystemRecommendation.SS_FACTOR.name()));

			RecommendedOccupationList = cgtOcc;
			if (cgtOcc.size() > 5) {
				cgtOcc = cgtOcc.subList(0, 5);
			}

			resultMap.put(ApplicationConstants.SystemRecommendation.CGT.name(), cgtOcc);
		}
		OUT.debug("Time Taken for CGT Occupations {} ms", (System.currentTimeMillis() - startTime));
		// getRecommendationBasedOnTUM(studentId);
		OUT.debug("Time Taken for TUM {} ms", (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		return resultMap;
	}

	public void searchNSaveCGTResult(int studentId) throws Exception {
		getRecommendationBasedOnCGT(studentId);
		StudentCGTResult cgtResult = new StudentCGTResult();
		cgtResult.setStudentId(studentId);
		cgtResult.setOccupationIds(
				(String) resultMap.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name()));
		cgtResult.setPersonalityCode(
				(String) resultMap.get(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name()));
		cgtResult.setAppScore((String) resultMap.get(ApplicationConstants.SystemRecommendation.APP_SCORE.name()));
		cgtResult.setSsFactor((String) resultMap.get(ApplicationConstants.SystemRecommendation.SS_FACTOR.name()));
		MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_OR_UPDATE_DHSTUDENT_CGT_OCC_RESULT,
				cgtResult);
	}

	/**
	 * @param studentId
	 * @throws Exception
	 */
	public void getRecommendationBasedOnTUM(int studentId) throws Exception {
		Map<String, Object> parametersObject = new HashMap<String, Object>();
		List<Integer> slNo = new ArrayList<Integer>();
		slNo.add(7);
		slNo.add(8);
		slNo.add(9);
		slNo.add(10);
		slNo.add(11);
		parametersObject.put("studentId", studentId);
		parametersObject.put("questionSlNoList", slNo);
		List<StudentTUMDTO> resultList = MyBatisManager.getInstance()
				.getResultList(MyBatisMappingConstants.GET_STUDENT_TUM_FORSTUDENT_BY_SLNO, parametersObject);
		OUT.debug("Student Tum Answers count: {} ", resultList != null ? resultList.size() : 0);
		String intrests = null;
		String strengths = null;
		String aspirations = null;
		String subjects = null;
		for (StudentTUMDTO studentTUMDTO : resultList) {
			switch (studentTUMDTO.getQuestionSlNo()) {
			case 7:
				if (subjects != null) {
					subjects += ",";
				} else {
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
				if (subjects != null) {
					subjects += ",";
				} else {
					subjects = new String();
				}
				subjects += trimWhiteSpaceBWComma(studentTUMDTO.getAnswer());
				break;
			case 11:
				aspirations = studentTUMDTO.getAnswer();
				break;
			}
		}
		OUT.debug("Student Tum Answers intrests:{}, strengths:{}, aspirations:{}, subjects:{} ", intrests, strengths,
				aspirations, subjects);
		if (intrests != null && !intrests.isEmpty()) {
			List<String> asList = CommonUtil.convertSpaceNOthersSeperatedToList(CommonUtil.replaceDBQuotes(intrests));
			Iterator<String> iterator = asList.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				if (next.length() < 4) {
					iterator.remove();
				}
			}
			List<OccupationDTO> interstsOcc = new ArrayList<OccupationDTO>();
			if (!asList.isEmpty()) {
				interstsOcc = MyBatisManager.getInstance()
						.getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_INTERESTS, asList);
			}

			List<OccupationDTO> interstsOccNew = new ArrayList<OccupationDTO>(interstsOcc);
			if (RecommendedOccupationList != null && interstsOcc != null) {
				for (OccupationDTO cgtOccTEMP : RecommendedOccupationList) {

					for (OccupationDTO OccTEMP : interstsOcc) {
						if (cgtOccTEMP.getId() == OccTEMP.getId()) {

							interstsOccNew.remove(interstsOccNew.indexOf(OccTEMP));
						}
					}
				}

			}

			updateFitment(interstsOccNew,
					(String) resultMap.get(ApplicationConstants.SystemRecommendation.SS_FACTOR.name()));

			resultMap.put(ApplicationConstants.SystemRecommendation.INTERESTS.name(), interstsOccNew);
		}
		if (aspirations != null && !aspirations.isEmpty()) {
			List<String> asList = CommonUtil
					.convertSpaceNOthersSeperatedToList(CommonUtil.replaceDBQuotes(aspirations));
			Iterator<String> iterator = asList.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				if (next.length() < 4) {
					iterator.remove();
				}
			}

			List<OccupationDTO> aspirationOcc = new ArrayList<OccupationDTO>();
			if (!asList.isEmpty()) {
				aspirationOcc = MyBatisManager.getInstance()
						.getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_ASPIRATIONS, asList);
			}

			List<OccupationDTO> aspirationOccNew = new ArrayList<OccupationDTO>(aspirationOcc);
			if (RecommendedOccupationList != null && aspirationOcc != null) {
				for (OccupationDTO cgtOccTEMP : RecommendedOccupationList) {

					for (OccupationDTO OccTEMP : aspirationOcc) {
						if (cgtOccTEMP.getId() == OccTEMP.getId()) {

							aspirationOccNew.remove(aspirationOccNew.indexOf(OccTEMP));
						}
					}
				}

			}

			updateFitment(aspirationOccNew,
					(String) resultMap.get(ApplicationConstants.SystemRecommendation.SS_FACTOR.name()));

			resultMap.put(ApplicationConstants.SystemRecommendation.ASPIRATIONS.name(), aspirationOccNew);

		}

		if (strengths != null && !strengths.isEmpty()) {
			List<OccupationDTO> strengthOcc = MyBatisManager.getInstance().getResultList(
					MyBatisMappingConstants.GET_OCCUPATION_BY_STRENGTHS, Arrays.asList(strengths.split(",")));
			List<OccupationDTO> strengthOccNew = new ArrayList<OccupationDTO>(strengthOcc);

			if (RecommendedOccupationList != null && strengthOcc != null) {
				for (OccupationDTO cgtOccTEMP : RecommendedOccupationList) {

					for (OccupationDTO OccTEMP : strengthOcc) {
						if (cgtOccTEMP.getId() == OccTEMP.getId()) {

							strengthOccNew.remove(strengthOccNew.indexOf(OccTEMP));
						}
					}
				}

			}

			updateFitment(strengthOccNew,
					(String) resultMap.get(ApplicationConstants.SystemRecommendation.SS_FACTOR.name()));
			resultMap.put(ApplicationConstants.SystemRecommendation.STRENGTHS.name(), strengthOccNew);

		}

		if (subjects != null && !subjects.isEmpty()) {
			List<OccupationDTO> subjectOcc = MyBatisManager.getInstance().getResultList(
					MyBatisMappingConstants.GET_OCCUPATION_BY_SUBJECTS, Arrays.asList(subjects.split(",")));

			List<OccupationDTO> subjectOccNew = new ArrayList<OccupationDTO>(subjectOcc);

			if (RecommendedOccupationList != null && subjectOcc != null) {
				for (OccupationDTO cgtOccTEMP : RecommendedOccupationList) {

					for (OccupationDTO OccTEMP : subjectOcc) {
						if (cgtOccTEMP.getId() == OccTEMP.getId()) {

							subjectOccNew.remove(subjectOccNew.indexOf(OccTEMP));
						}
					}
				}

			}

			updateFitment(subjectOccNew,
					(String) resultMap.get(ApplicationConstants.SystemRecommendation.SS_FACTOR.name()));
			resultMap.put(ApplicationConstants.SystemRecommendation.SUBJECTS.name(), subjectOccNew);

		}
	}

	private String trimWhiteSpaceBWComma(String str) {
		String trimSubjects = new String();
		for (String indvData : str.split(",")) {
			if (trimSubjects.length() > 0) {
				trimSubjects += ",";
			}
			trimSubjects += indvData.trim();
		}
		return trimSubjects;
	}

	public Map<String, Object> getRecommendationBasedOnCGT(int studentId) throws Exception {
		listOccupationsByPersonality(studentId);
		Map<String, String> ssFactorMap = new HashMap<String, String>();
		String appOccupationScore = getAbilityScores(studentId, ssFactorMap);
		resultMap.put(ApplicationConstants.SystemRecommendation.SS_FACTOR.name(),
				new JSONObject(ssFactorMap).toString());
		resultMap.put(ApplicationConstants.SystemRecommendation.APP_SCORE.name(), appOccupationScore);
		// getFinalScore(occpationIndexMap);

		if ((resultMap.containsKey(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name())
				&& resultMap.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name()) != null)) {
			List<OccupationDTO> cgtOcc = MyBatisManager.getInstance()
					.getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_OCCPATION_IDS_FOR_DAILYHUNT, Arrays.asList(
							((String) resultMap.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name()))
									.split(",")));

			updateFitment(cgtOcc, (String) resultMap.get(ApplicationConstants.SystemRecommendation.SS_FACTOR.name()));
			RecommendedOccupationList = cgtOcc;
			if (cgtOcc.size() > 5) {
				OUT.debug("Number of occupations greater than 5");
				OUT.debug("Full List: {}", cgtOcc);
				cgtOcc = cgtOcc.subList(0, 5);
				OUT.debug("Sub List: {}", cgtOcc);
			}

			String occupationsId = null;
			for (OccupationDTO occupationRating : cgtOcc) {
				if (occupationsId != null) {
					occupationsId += ",";
				} else {
					occupationsId = new String();
				}
				occupationsId += occupationRating.getId();
			}
			OUT.debug("Occupation Ids after fitment: {}", occupationsId);
			resultMap.put(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name(), occupationsId);
			resultMap.put(ApplicationConstants.SystemRecommendation.CGT.name(), cgtOcc);
		}
		return resultMap;
	}

	private void listOccupationsByPersonality(int studentId) {
		ChanakyaStudentOccupationDAO dhstudentOccupationDAO = new ChanakyaStudentOccupationDAO();
		try {
			ChanakyaStudentOccupationDTO raisecSummaryCode = dhstudentOccupationDAO
					.getPersonalityCodeOccupationByStudentId(studentId);
			setPersonalityCode(raisecSummaryCode.getPersonalityCode());
			resultMap.put(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name(),
					raisecSummaryCode.getPersonalityCode());
//			resultMap.put(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name(),
//					raisecSummaryCode.getOccList());
		} catch (Exception e) {
			OUT.error("Exception", e);
		}
	}

	public String getTrailStudentRAISEC_Code(int studentId) {
		return getStudentRAISEC_Code(studentId);

	}

	private String getPersonalityCodePostTieBreakV2(Map<String, Integer> inputScoreByFactor,
			Map<String, StudentCGTForEvalDTO> questionList) {
		Map<String, Integer> orgScoreFactor = new LinkedHashMap<String, Integer>();
		orgScoreFactor.putAll(inputScoreByFactor);
		Map<String, Integer> scoreByFactor = new LinkedHashMap<String, Integer>();
		scoreByFactor.putAll(inputScoreByFactor);
		TreeMap<String, Integer> scoreByFactor1 = null;

		String raisecSummaryCode = null;
		ValueComparator1 bvc;
		Map<String, Integer> standsOutScoreByFactor = new LinkedHashMap<String, Integer>();
		if (!scoreByFactor.isEmpty() && questionList.size() == 15) {
			int tieCouter = 0;
			boolean sameScore = false;
			do {
				sameScore = false;
				HashSet<Integer> hashSet = new HashSet<Integer>(scoreByFactor.values());
				if (hashSet.size() == scoreByFactor.size()) // tie breaking is does not exists or it is broken
				{
					for (Entry<String, Integer> entry : scoreByFactor.entrySet()) {
						standsOutScoreByFactor.put(entry.getKey(), orgScoreFactor.get(entry.getKey()));
						if (standsOutScoreByFactor.size() >= 3) {
							break; // break the loop as
						}
					}
				} else if (hashSet.size() != scoreByFactor.size() && standsOutScoreByFactor.size() < 3) // multiple
																										// factors have
																										// same score
				{
					sameScore = true;
					OUT.debug("Multiple Factors have same score counter: {}", tieCouter++);
					if (tieCouter >= 100) // unable to break the tie breaker
					{
						OUT.error("Unable to the break the tie breaker {}", standsOutScoreByFactor);
						break;
					}
					Integer scoreOfAll = hashSet.iterator().next();
					if (hashSet.size() == 1 && (scoreOfAll == 10 || scoreOfAll == 0)
							&& standsOutScoreByFactor.isEmpty()) // when the size is one it means all
																	// have same score if 10 or 0 it
																	// migh be that the student has not
																	// answered correctly
					{
						OUT.error("All the factors have same value 10");
						for (int i = 0; i < interestSubFactor.length; i++) {
							standsOutScoreByFactor.put(interestSubFactor[i], 10);
						}
						resultMap.put(ApplicationConstants.SystemRecommendation.DISCLAIMER.name(), true);
						break;
					}

					List<String> keysList = new ArrayList<String>(scoreByFactor.keySet());
					// questionList
					for (int i = 0; i < keysList.size() && standsOutScoreByFactor.size() < 3; i++) {
						String currentKey = keysList.get(i);
						OUT.debug("Current key: {}, does it already stand out: {}", currentKey,
								standsOutScoreByFactor.containsKey(currentKey));
						if (!standsOutScoreByFactor.containsKey(currentKey)) {
							Integer currentValue = scoreByFactor.get(currentKey);
							OUT.debug("Current key: {}, currentValue: {}", currentKey, currentValue);
							boolean sameValues = false;
							List<String> sameValueKeys = new ArrayList<String>();
							for (int j = i + 1; j < keysList.size() && standsOutScoreByFactor.size() < 3; j++) {
								if (currentValue.intValue() == scoreByFactor.get(keysList.get(j)).intValue()) {
									sameValues = true;
									sameValueKeys.add(keysList.get(j));
								}
							}
							if (!sameValues) {
								standsOutScoreByFactor.put(currentKey, currentValue);
								sameScore = false;
								break;
							} else {
								sameValueKeys.add(0, keysList.get(i));
							}

							OUT.debug("Current key: {}, currentValue: {}, hasTie: {}, tieBetween: {}", currentKey,
									currentValue, sameValues, sameValueKeys);
							if (sameValues && sameValueKeys.size() == 2) {
								Map<String, Integer> localScoreByFactor = new LinkedHashMap<String, Integer>();
								String currentFactor = sameValueKeys.get(0);
								String nextFactor = sameValueKeys.get(1);
								localScoreByFactor.put(currentFactor, scoreByFactor.get(currentFactor));
								localScoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor));
								
								if (questionList.containsKey(currentFactor+","+nextFactor)) {
									StudentCGTForEvalDTO studentCGTForEvalDTO = questionList.get(currentFactor+","+nextFactor);
										if (studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(YES)) {
											scoreByFactor.put(currentFactor, scoreByFactor.get(currentFactor) + 1);
										} else {
											scoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor) + 1);
											currentFactor=nextFactor;
										}
									}
								
									else if (questionList.containsKey(nextFactor+","+currentFactor)) {
										StudentCGTForEvalDTO studentCGTForEvalDTO = questionList.get(nextFactor+","+currentFactor);
										if (studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(YES)) {
											scoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor) + 1);
											currentFactor=nextFactor;
										} else {
											scoreByFactor.put(currentFactor, scoreByFactor.get(currentFactor) + 1);
										}
									}
								
								
								/*
								 * if (keyMap.containsKey(currentFactor) &&
								 * keyMap.get(currentFactor).containsKey(nextFactor)) { for (String
								 * questionNumber : keyMap.get(currentFactor).get(nextFactor)) {
								 * updateScoreFactor(localScoreByFactor, questionList, nextFactor,
								 * currentFactor, questionNumber); } } else if (keyMap.containsKey(nextFactor)
								 * && keyMap.get(nextFactor).containsKey(currentFactor)) { for (String
								 * questionNumber : keyMap.get(nextFactor).get(currentFactor)) {
								 * updateScoreFactor(localScoreByFactor, questionList, currentFactor,
								 * nextFactor, questionNumber); } }
								 */

								OUT.debug("Inside Score By Factor after tie breaking {}", localScoreByFactor);

								bvc = new ValueComparator1(localScoreByFactor);
								scoreByFactor1 = new TreeMap<String, Integer>(bvc);
								scoreByFactor1.putAll(localScoreByFactor);

								localScoreByFactor.clear();
								localScoreByFactor.putAll(scoreByFactor1);

								OUT.debug("Inside Score By Factor after tie breaking and re-ordering {}",
										localScoreByFactor);
//								for (Entry<String, Integer> entry : localScoreByFactor.entrySet()) {
//									standsOutScoreByFactor.put(entry.getKey(), scoreByFactor.get(entry.getKey()));
//								}
								standsOutScoreByFactor.put(currentFactor, scoreByFactor.get(currentFactor));
								sameScore=false;
								break;
							}
							if (sameValues && sameValueKeys.size() > 2) {
								Map<String, Integer> localScoreByFactor = new LinkedHashMap<String, Integer>();
								String firstFactor = sameValueKeys.get(0);
								Map<String, String> nextFactorNGreater = new HashMap<String, String>();
								localScoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor));
								for (int z = 1; z < sameValueKeys.size(); z++) {
									String nextFactor = sameValueKeys.get(z);
									OUT.debug("Tie between {} & {} ", firstFactor, nextFactor);
									localScoreByFactor.clear();
									localScoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor));
									localScoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor));
									//updateScoreFactorV2(localScoreByFactor, questionList, nextFactor, firstFactor);
									if (questionList.containsKey(firstFactor+","+nextFactor)) {
									StudentCGTForEvalDTO studentCGTForEvalDTO = questionList.get(firstFactor+","+nextFactor);
										if (studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(YES)) {
											scoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor) + 1);
										} else {
											scoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor) + 1);
											firstFactor=nextFactor;
										}
									}
								
									else if (questionList.containsKey(nextFactor+","+firstFactor)) {
										StudentCGTForEvalDTO studentCGTForEvalDTO = questionList.get(nextFactor+","+firstFactor);
										if (studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(YES)) {
											scoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor) + 1);
											firstFactor=nextFactor;
										} else {
											scoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor) + 1);
										}
									}
									
									

									OUT.debug("Inside Score By Factor after tie breaking {}", localScoreByFactor);

									bvc = new ValueComparator1(localScoreByFactor);
									scoreByFactor1 = new TreeMap<String, Integer>(bvc);
									scoreByFactor1.putAll(localScoreByFactor);

									localScoreByFactor.clear();
									localScoreByFactor.putAll(scoreByFactor1);

									OUT.debug("Inside Score By Factor after tie breaking and re-ordering {}",
											localScoreByFactor);

									/*
									 * nextFactorNGreater.put(nextFactor,
									 * localScoreByFactor.entrySet().iterator().next().getKey());
									 */
								}
								standsOutScoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor));
								OUT.debug("Tie breaker for factors: {}, nextFactorNGreater: {}", firstFactor,
										nextFactorNGreater);
								/*
								 * if (!nextFactorNGreater.containsValue(firstFactor)) {
								 * OUT.debug("Factor: {}, is not greater than any factor so reducing the value",
								 * firstFactor); for (Entry<String, String> greaterFactorEntry :
								 * nextFactorNGreater.entrySet()) {
								 * scoreByFactor.put(greaterFactorEntry.getKey(),
								 * (scoreByFactor.get(greaterFactorEntry.getKey()) + 1)); } } else { int
								 * currentFactorGreaterThan = 0; for (String greaterFactor :
								 * nextFactorNGreater.values()) { if
								 * (greaterFactor.equalsIgnoreCase(firstFactor)) { currentFactorGreaterThan++; }
								 * } if (currentFactorGreaterThan == nextFactorNGreater.size()) {
								 * standsOutScoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor));
								 * OUT.debug("Tie breaker currentFactor: {} stands out completely",
								 * firstFactor); } else {
								 * OUT.debug("Tie breaker for factor: {}, nextFactorNGreater: {}", firstFactor,
								 * nextFactorNGreater); scoreByFactor.put(firstFactor,
								 * scoreByFactor.get(firstFactor) + 1); for (Entry<String, String>
								 * greaterFactorEntry : nextFactorNGreater.entrySet()) { if
								 * (!greaterFactorEntry.getValue().equalsIgnoreCase(firstFactor)) {
								 * scoreByFactor.put(greaterFactorEntry.getKey(),
								 * (scoreByFactor.get(greaterFactorEntry.getKey()) + 2)); OUT.debug(
								 * "Increasing the score for the factor: {} by: 2 as it is greater than currentFactor : {}"
								 * , greaterFactorEntry.getKey(), firstFactor); } } } }
								 */

								bvc = new ValueComparator1(scoreByFactor);
								scoreByFactor1 = new TreeMap<String, Integer>(bvc);
								scoreByFactor1.putAll(scoreByFactor);

								scoreByFactor.clear();
								scoreByFactor.putAll(scoreByFactor1);

								OUT.debug("Inside Score By Factor after tie breaking and re-ordering {}",
										scoreByFactor);
								sameScore=false;
								break;
							}
						}
					}
				}
			} while (sameScore);

			OUT.debug("Score By Factor after tie breaking {}", standsOutScoreByFactor);
			int counter = 0;
			for (Entry<String, Integer> entry : standsOutScoreByFactor.entrySet()) {
				if (raisecSummaryCode != null) {
					raisecSummaryCode += ",";
				} else {
					raisecSummaryCode = new String();
				}
				raisecSummaryCode += entry.getKey();
				if (counter == 2) {
					break;
				}
				counter++;
			}
		}OUT.debug("Student Aptitude RAISEC Code:{} ",raisecSummaryCode);resultMap.put(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name(),raisecSummaryCode);

	setPersonalityCode(raisecSummaryCode);
		return raisecSummaryCode;
	}

	private String getPersonalityCodePostTieBreak(Map<String, Integer> inputScoreByFactor,
			Map<String, StudentCGTForEvalDTO> questionList) {
		Map<String, Integer> orgScoreFactor = new LinkedHashMap<String, Integer>();
		orgScoreFactor.putAll(inputScoreByFactor);
		Map<String, Integer> scoreByFactor = new LinkedHashMap<String, Integer>();
		scoreByFactor.putAll(inputScoreByFactor);
		TreeMap<String, Integer> scoreByFactor1 = null;

		String raisecSummaryCode = null;
		ValueComparator1 bvc;
		Map<String, Integer> standsOutScoreByFactor = new LinkedHashMap<String, Integer>();
		if (!scoreByFactor.isEmpty() && questionList.size() == 18) {
			int tieCouter = 0;
			boolean sameScore = false;
			do {
				sameScore = false;
				HashSet<Integer> hashSet = new HashSet<Integer>(scoreByFactor.values());
				if (hashSet.size() == scoreByFactor.size()) // tie breaking is does not exists or it is broken
				{
					for (Entry<String, Integer> entry : scoreByFactor.entrySet()) {
						standsOutScoreByFactor.put(entry.getKey(), orgScoreFactor.get(entry.getKey()));
						if (standsOutScoreByFactor.size() >= 3) {
							break; // break the loop as
						}
					}
				} else if (hashSet.size() != scoreByFactor.size() && standsOutScoreByFactor.size() < 3) // multiple
																										// factors have
																										// same score
				{
					sameScore = true;
					OUT.debug("Multiple Factors have same score counter: {}", tieCouter++);
					if (tieCouter >= 100) // unable to break the tie breaker
					{
						OUT.error("Unable to the break the tie breaker {}", standsOutScoreByFactor);
						break;
					}
					Integer scoreOfAll = hashSet.iterator().next();
					if (hashSet.size() == 1 && (scoreOfAll == 10 || scoreOfAll == 0)
							&& standsOutScoreByFactor.isEmpty()) // when the size is one it means all
																	// have same score if 10 or 0 it
																	// migh be that the student has not
																	// answered correctly
					{
						OUT.error("All the factors have same value 10");
						for (int i = 0; i < interestSubFactor.length; i++) {
							standsOutScoreByFactor.put(interestSubFactor[i], 10);
						}
						resultMap.put(ApplicationConstants.SystemRecommendation.DISCLAIMER.name(), true);
						break;
					}

					List<String> keysList = new ArrayList<String>(scoreByFactor.keySet());
					// questionList
					for (int i = 0; i < keysList.size() && standsOutScoreByFactor.size() < 3; i++) {
						String currentKey = keysList.get(i);
						OUT.debug("Current key: {}, does it already stand out: {}", currentKey,
								standsOutScoreByFactor.containsKey(currentKey));
						if (!standsOutScoreByFactor.containsKey(currentKey)) {
							Integer currentValue = scoreByFactor.get(currentKey);
							OUT.debug("Current key: {}, currentValue: {}", currentKey, currentValue);
							boolean sameValues = false;
							List<String> sameValueKeys = new ArrayList<String>();
							for (int j = i + 1; j < keysList.size() && standsOutScoreByFactor.size() < 3; j++) {
								if (currentValue.intValue() == scoreByFactor.get(keysList.get(j)).intValue()) {
									sameValues = true;
									sameValueKeys.add(keysList.get(j));
								}
							}
							if (!sameValues) {
								standsOutScoreByFactor.put(currentKey, currentValue);
							} else {
								sameValueKeys.add(0, keysList.get(i));
							}

							OUT.debug("Current key: {}, currentValue: {}, hasTie: {}, tieBetween: {}", currentKey,
									currentValue, sameValues, sameValueKeys);
							if (sameValues && sameValueKeys.size() == 2) {
								Map<String, Integer> localScoreByFactor = new LinkedHashMap<String, Integer>();
								String currentFactor = sameValueKeys.get(0);
								String nextFactor = sameValueKeys.get(1);
								localScoreByFactor.put(currentFactor, scoreByFactor.get(currentFactor));
								localScoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor));
								if (keyMap.containsKey(currentFactor)
										&& keyMap.get(currentFactor).containsKey(nextFactor)) {
									for (String questionNumber : keyMap.get(currentFactor).get(nextFactor)) {
										updateScoreFactor(localScoreByFactor, questionList, nextFactor, currentFactor,
												questionNumber);
									}
								} else if (keyMap.containsKey(nextFactor)
										&& keyMap.get(nextFactor).containsKey(currentFactor)) {
									for (String questionNumber : keyMap.get(nextFactor).get(currentFactor)) {
										updateScoreFactor(localScoreByFactor, questionList, currentFactor, nextFactor,
												questionNumber);
									}
								}

								OUT.debug("Inside Score By Factor after tie breaking {}", localScoreByFactor);

								bvc = new ValueComparator1(localScoreByFactor);
								scoreByFactor1 = new TreeMap<String, Integer>(bvc);
								scoreByFactor1.putAll(localScoreByFactor);

								localScoreByFactor.clear();
								localScoreByFactor.putAll(scoreByFactor1);

								OUT.debug("Inside Score By Factor after tie breaking and re-ordering {}",
										localScoreByFactor);
								for (Entry<String, Integer> entry : localScoreByFactor.entrySet()) {
									standsOutScoreByFactor.put(entry.getKey(), scoreByFactor.get(entry.getKey()));
								}
								break;
							}
							if (sameValues && sameValueKeys.size() > 2) {
								Map<String, Integer> localScoreByFactor = new LinkedHashMap<String, Integer>();
								String firstFactor = sameValueKeys.get(0);
								Map<String, String> nextFactorNGreater = new HashMap<String, String>();
								localScoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor));
								for (int z = 1; z < sameValueKeys.size(); z++) {
									String nextFactor = sameValueKeys.get(z);
									OUT.debug("Tie between {} & {} ", firstFactor, nextFactor);
									localScoreByFactor.clear();
									localScoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor));
									localScoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor));
									if (keyMap.containsKey(firstFactor)
											&& keyMap.get(firstFactor).containsKey(nextFactor)) {
										for (String questionNumber : keyMap.get(firstFactor).get(nextFactor)) {
											updateScoreFactor(localScoreByFactor, questionList, nextFactor, firstFactor,
													questionNumber);
										}
									} else if (keyMap.containsKey(nextFactor)
											&& keyMap.get(nextFactor).containsKey(firstFactor)) {
										for (String questionNumber : keyMap.get(nextFactor).get(firstFactor)) {
											updateScoreFactor(localScoreByFactor, questionList, firstFactor, nextFactor,
													questionNumber);
										}
									}
									OUT.debug("Inside Score By Factor after tie breaking {}", localScoreByFactor);

									bvc = new ValueComparator1(localScoreByFactor);
									scoreByFactor1 = new TreeMap<String, Integer>(bvc);
									scoreByFactor1.putAll(localScoreByFactor);

									localScoreByFactor.clear();
									localScoreByFactor.putAll(scoreByFactor1);

									OUT.debug("Inside Score By Factor after tie breaking and re-ordering {}",
											localScoreByFactor);

									nextFactorNGreater.put(nextFactor,
											localScoreByFactor.entrySet().iterator().next().getKey());
								}

								OUT.debug("Tie breaker for factors: {}, nextFactorNGreater: {}", firstFactor,
										nextFactorNGreater);
								if (!nextFactorNGreater.containsValue(firstFactor)) {
									OUT.debug("Factor: {}, is not greater than any factor so reducing the value",
											firstFactor);
									for (Entry<String, String> greaterFactorEntry : nextFactorNGreater.entrySet()) {
										scoreByFactor.put(greaterFactorEntry.getKey(),
												(scoreByFactor.get(greaterFactorEntry.getKey()) + 1));
									}
								} else {
									int currentFactorGreaterThan = 0;
									for (String greaterFactor : nextFactorNGreater.values()) {
										if (greaterFactor.equalsIgnoreCase(firstFactor)) {
											currentFactorGreaterThan++;
										}
									}
									if (currentFactorGreaterThan == nextFactorNGreater.size()) {
										standsOutScoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor));
										OUT.debug("Tie breaker currentFactor: {} stands out completely", firstFactor);
									} else {
										OUT.debug("Tie breaker for factor: {}, nextFactorNGreater: {}", firstFactor,
												nextFactorNGreater);
										scoreByFactor.put(firstFactor, scoreByFactor.get(firstFactor) + 1);
										for (Entry<String, String> greaterFactorEntry : nextFactorNGreater.entrySet()) {
											if (!greaterFactorEntry.getValue().equalsIgnoreCase(firstFactor)) {
												scoreByFactor.put(greaterFactorEntry.getKey(),
														(scoreByFactor.get(greaterFactorEntry.getKey()) + 2));
												OUT.debug(
														"Increasing the score for the factor: {} by: 2 as it is greater than currentFactor : {}",
														greaterFactorEntry.getKey(), firstFactor);
											}
										}
									}
								}

								bvc = new ValueComparator1(scoreByFactor);
								scoreByFactor1 = new TreeMap<String, Integer>(bvc);
								scoreByFactor1.putAll(scoreByFactor);

								scoreByFactor.clear();
								scoreByFactor.putAll(scoreByFactor1);

								OUT.debug("Inside Score By Factor after tie breaking and re-ordering {}",
										scoreByFactor);
								break;
							}
						}
					}
				}
			} while (sameScore);

			OUT.debug("Score By Factor after tie breaking {}", standsOutScoreByFactor);
			int counter = 0;
			for (Entry<String, Integer> entry : standsOutScoreByFactor.entrySet()) {
				if (raisecSummaryCode != null) {
					raisecSummaryCode += ",";
				} else {
					raisecSummaryCode = new String();
				}
				raisecSummaryCode += entry.getKey();
				if (counter == 2) {
					break;
				}
				counter++;
			}
		}
		OUT.debug("Student Aptitude RAISEC Code:{} ", raisecSummaryCode);
		resultMap.put(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name(), raisecSummaryCode);

		setPersonalityCode(raisecSummaryCode);
		return raisecSummaryCode;
	}

	private void updateScoreFactor(Map<String, Integer> scoreByFactor, Map<String, StudentCGTForEvalDTO> questionList,
			String nextFactor, String factor, String questionNumber) {
		if (questionList.containsKey(questionNumber)) {
			StudentCGTForEvalDTO studentCGTForEvalDTO = questionList.get(questionNumber);
			if (studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(YES)) {
				scoreByFactor.put(factor, scoreByFactor.get(factor) + 1);
			} else {
				scoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor) + 1);
			}
		}
	}

	private void updateScoreFactorV2(Map<String, Integer> scoreByFactor, Map<String, StudentCGTForEvalDTO> questionList,
			String nextFactor, String factor) {
		if (questionList.containsKey(factor + "," + nextFactor)) {
			StudentCGTForEvalDTO studentCGTForEvalDTO = questionList.get(factor + "," + nextFactor);
			if (studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(YES)) {
				scoreByFactor.put(factor, scoreByFactor.get(factor) + 1);
			} else {
				scoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor) + 1);
				factor = nextFactor;
			}
		}

		else if (questionList.containsKey(nextFactor + "," + factor)) {
			StudentCGTForEvalDTO studentCGTForEvalDTO = questionList.get(nextFactor + "," + factor);
			if (studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(YES)) {
				scoreByFactor.put(nextFactor, scoreByFactor.get(nextFactor) + 1);
				factor = nextFactor;
			} else {
				scoreByFactor.put(factor, scoreByFactor.get(factor) + 1);
			}
		}
	}

	public String getStudentRAISEC_Code(int studentId) {
		Map<String, Object> parameterObject = new HashMap<String, Object>();
		List<StudentCGTForEvalDTO> resultList;
		Map<String, Integer> factorScoreMap = new HashMap<String, Integer>();
		ValueComparator1 bvc = new ValueComparator1(factorScoreMap);
		TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
		Map<String, StudentCGTForEvalDTO> questionList = new HashMap<String, StudentCGTForEvalDTO>();
		try {
			parameterObject.put("section", INTREST_TEST_SECTION);
			parameterObject.put("studentId", studentId);
			int totalAnswered = 0;
			for (int i = 0; i < interestSubFactor.length; i++) {
				parameterObject.put("factor", interestSubFactor[i]);
				resultList = MyBatisManager.getInstance().getResultList(
						MyBatisMappingConstants.GET_CHANAKYASTUDENT_CGT_BY_SESSION_FACTOR, parameterObject);
				OUT.debug("Number of questions answered by student {},  factor:{}", resultList.size(),
						interestSubFactor[i]);
				totalAnswered += resultList.size();
				int score = 0;
				for (StudentCGTForEvalDTO studentCGTForEvalDTO : resultList) {
					score += studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(YES) ? 1 : 0;
					questionList.put(studentCGTForEvalDTO.getSlNo(), studentCGTForEvalDTO);
				}
				factorScoreMap.put(interestSubFactor[i], score);
				OUT.debug("Score for factor:{}, score:{}", interestSubFactor[i], score);
			}

			resultMap.put(ApplicationConstants.SystemRecommendation.PESONALITY_ANSWERED.name(), totalAnswered);
		} catch (Exception e) {
			OUT.error("Exception", e);
		}

		sorted_map.putAll(factorScoreMap);

		OUT.debug("Scores by Factor:{} ", sorted_map);

		// get list of tie break
		Map<String, StudentCGTForEvalDTO> questionListTieBreak = new HashMap<String, StudentCGTForEvalDTO>();
		try {
			Map<String, Object> paramObject = new HashMap<String, Object>();
			paramObject.put("section", "tie");
			paramObject.put("studentId", studentId);
			List<StudentCGTForEvalDTO> resultListTieBreak = MyBatisManager.getInstance()
					.getResultList(MyBatisMappingConstants.GET_CHANAKYASTUDENT_CGT_BY_STUDENTID, paramObject);
			for (StudentCGTForEvalDTO studentCGTForEvalDTO : resultListTieBreak) {
				questionListTieBreak.put(studentCGTForEvalDTO.getFactor(), studentCGTForEvalDTO);
			}

		} catch (Exception e) {
			OUT.error("Exception", e);
		}

		return getPersonalityCodePostTieBreakV2(sorted_map, questionListTieBreak);
	}

	private String getAbilityScores(int studentId, Map<String, String> ssFactorMap) throws Exception {
		Map<String, Object> parameterObject = new HashMap<String, Object>();
		List<StudentCGTForEvalDTO> resultList;
		Map<String, Integer> factorScoreMap = new HashMap<String, Integer>();
		ValueComparator1 bvc = new ValueComparator1(factorScoreMap);
		TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
		String appScores = null;
		try {
			String[] subFactor = appSubFactor;
			String section = APP_TEST_SECION;
			parameterObject.put("section", section);
			parameterObject.put("studentId", studentId);
			for (int i = 0; i < subFactor.length; i++) {
				parameterObject.put("factor", subFactor[i]);
				resultList = MyBatisManager.getInstance()
						.getResultList(MyBatisMappingConstants.GET_DHSTUDENT_CGT_BY_SESSION_FACTOR, parameterObject);
				OUT.debug("Number of questions answered by student {},  factor:{}", resultList.size(), subFactor[i]);

				int score = 0;
				for (StudentCGTForEvalDTO studentCGTForEvalDTO : resultList) {
					score += studentCGTForEvalDTO.getAnswer().equalsIgnoreCase(studentCGTForEvalDTO.getCorrectAnswer())
							? 1
							: 0;
				}
				factorScoreMap.put(subFactor[i], score);
				OUT.debug("Score for factor: {}, score: {}", subFactor[i], score);

				String ssFactor = getSSFactor(score, subFactor[i]);
				OUT.debug("SSFactor Identified factor: {}, score: {}, ssFactor: {}", subFactor[i], score, ssFactor);

				ssFactorMap.put(subFactor[i], ssFactor);
				String string = appAbrevation.get(subFactor[i]);
				if (appScores != null) {
					appScores += ", ";
				} else {
					appScores = new String();
				}

				appScores += string + ": " + ssFactor;
			}
		} catch (Exception e) {
			OUT.error("Exception", e);
		}

		sorted_map.putAll(factorScoreMap);
		return appScores;
	}

	private String getSSFactor(int score, String abilityCode) {
		String ssFactor = ApplicationConstants.SSFactor.L.getProperty();

		if (abilityCode.equalsIgnoreCase(ApplicationConstants.ABILITY.MA.getName())) {
			if (score <= 4) {
				ssFactor = ApplicationConstants.SSFactor.L.getProperty();
			} else if (score <= 7) {
				ssFactor = ApplicationConstants.SSFactor.M.getProperty();
			} else if (score >= 8) {
				ssFactor = ApplicationConstants.SSFactor.H.getProperty();
			}
		} else if (abilityCode.equalsIgnoreCase(ApplicationConstants.ABILITY.NA.getName())) {
			if (score <= 2) {
				ssFactor = ApplicationConstants.SSFactor.L.getProperty();
			} else if (score <= 6) {
				ssFactor = ApplicationConstants.SSFactor.M.getProperty();
			} else if (score >= 7) {
				ssFactor = ApplicationConstants.SSFactor.H.getProperty();
			}
		} else if (abilityCode.equalsIgnoreCase(ApplicationConstants.ABILITY.RA.getName())
				|| abilityCode.equalsIgnoreCase(ApplicationConstants.ABILITY.SA.getName())) {
			if (score <= 3) {
				ssFactor = ApplicationConstants.SSFactor.L.getProperty();
			} else if (score <= 6) {
				ssFactor = ApplicationConstants.SSFactor.M.getProperty();
			} else if (score >= 7) {
				ssFactor = ApplicationConstants.SSFactor.H.getProperty();
			}
		} else if (abilityCode.equalsIgnoreCase("VA")) {
			if (score <= 4) {
				ssFactor = ApplicationConstants.SSFactor.L.getProperty();
			} else if (score <= 8) {
				ssFactor = ApplicationConstants.SSFactor.M.getProperty();
			} else if (score >= 9) {
				ssFactor = ApplicationConstants.SSFactor.H.getProperty();
			}
		}
		return ssFactor;
	}

	private String getTopSubjectsFromMarksScored(String answer) {
		String top2Subjects = null;
		String scoreType = answer.split(",")[0];
		List<String> sortedSubjects = null;
		if (scoreType.equalsIgnoreCase("marks") || scoreType.equalsIgnoreCase("percentage")) {
			sortedSubjects = getSortedSubjectsByMarks(answer);
		} else {
			sortedSubjects = getSortedSubjectsByGrade(answer);
		}

		OUT.debug("Descending Sorted List {}", sortedSubjects);

		if (sortedSubjects != null && !sortedSubjects.isEmpty()) {
			for (int i = 0; i <= sortedSubjects.size() - 1; i++) {
				if (i > 0) {
					top2Subjects += ",";
				} else {
					top2Subjects = new String();
				}
				top2Subjects += sortedSubjects.get(i);
				if (i >= 1) {
					break;
				}
			}

			OUT.debug("Top Subjects {}", top2Subjects);
		}
		return top2Subjects;
	}

	private List<String> getSortedSubjectsByGrade(String answer) {
		String[] split = answer.split(",");

		List<SubjectGrade> subjectsGradeList = new ArrayList<InterestResultUtilService.SubjectGrade>();
		for (int i = 1; i < split.length; i++) {
			String[] subjectMarks = split[i].split(":");
			subjectsGradeList.add(new SubjectGrade(subjectMarks[0], subjectMarks[1]));
		}
		Collections.sort(subjectsGradeList);
		List<String> subjectsList = new ArrayList<String>();
		for (SubjectGrade subjectGrade : subjectsGradeList) {
			subjectsList.add(subjectGrade.subject);
		}
		return subjectsList;
	}

	private List<String> getSortedSubjectsByMarks(String answer) {
		String[] split = answer.split(",");
		List<SubjectMarks> subjectsMarksList = new ArrayList<InterestResultUtilService.SubjectMarks>();
		for (int i = 1; i < split.length; i++) {
			String[] subjectMarks = split[i].split(":");
			subjectsMarksList.add(new SubjectMarks(subjectMarks[0], Double.parseDouble(subjectMarks[1])));
		}

		Collections.sort(subjectsMarksList);
		List<String> subjectsList = new ArrayList<String>();
		for (SubjectMarks subjectGrade : subjectsMarksList) {
			subjectsList.add(subjectGrade.subject.trim());
		}
		return subjectsList;
	}

	public void updateFitment(List<OccupationDTO> occupationsWithReqAbility, String ssFactorStr) {
		if (ssFactorStr != null && !ssFactorStr.trim().isEmpty() && occupationsWithReqAbility != null
				&& !occupationsWithReqAbility.isEmpty()) {
			try {
				for (OccupationDTO occupationDTO : occupationsWithReqAbility) {
					if (occupationDTO.getRequiredAbilityCount() > 0) {
						String[] requiredAbilites = trimWhiteSpaceBWComma(occupationDTO.getRequiredAbility())
								.split(",");

						JSONObject ssFactorJSON = new JSONObject(ssFactorStr);
						Map<String, Integer> fitmentCount = new LinkedHashMap<String, Integer>();
						fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.M.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.H.getProperty(), 0);

						for (String ability : requiredAbilites) {
							if (ssFactorJSON.has(ability)) {
								fitmentCount.put(ssFactorJSON.getString(ability),
										fitmentCount.get(ssFactorJSON.getString(ability)) + 1);
							} else {
								fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(),
										fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty()) + 1);
							}
						}

						OUT.debug("Fitment SSFactor : {}, requiredAbilities: {}, fitMentCount: {}", ssFactorJSON,
								requiredAbilites, fitmentCount);
						int fitment = FitMent.LOW.getScore();
						int highCount = fitmentCount.get(ApplicationConstants.SSFactor.H.getProperty());
						int mediumCount = fitmentCount.get(ApplicationConstants.SSFactor.M.getProperty());
						// int lowCount =
						// fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty());
						switch (occupationDTO.getRequiredAbilityCount()) {
						case 5:
							if (highCount >= 4) {
								fitment = FitMent.HIG.getScore();
							} else if (highCount >= 3 || (highCount >= 2 && mediumCount >= 3)) {
								fitment = FitMent.A_AVG.getScore();
							} else if ((highCount >= 2) || (mediumCount >= 4) || (highCount >= 1 && mediumCount >= 2)) {
								fitment = FitMent.AVG.getScore();
							}
							break;
						case 4:
							if (highCount >= 3) {
								fitment = FitMent.HIG.getScore();
							} else if (highCount >= 2 && mediumCount >= 1) {
								fitment = FitMent.A_AVG.getScore();
							} else if (highCount >= 2 || mediumCount >= 3 || (highCount >= 1 && mediumCount >= 1)) {
								fitment = FitMent.AVG.getScore();
							}
							break;
						case 3:
							if (highCount >= 3) {
								fitment = FitMent.HIG.getScore();
							} else if (highCount >= 2) {
								fitment = FitMent.A_AVG.getScore();
							} else if (highCount >= 1 || mediumCount >= 2 || (highCount >= 1 && mediumCount >= 1)) {
								fitment = FitMent.AVG.getScore();
							}
							break;
						case 2:
							if (highCount >= 2) {
								fitment = FitMent.HIG.getScore();
							} else if (highCount >= 1 && mediumCount >= 1) {
								fitment = FitMent.A_AVG.getScore();
							} else if (mediumCount >= 2 || highCount == 1) {
								fitment = FitMent.AVG.getScore();
							}
							break;
						case 1:
							if (highCount == 1) {
								fitment = FitMent.HIG.getScore();
							} else if (mediumCount == 1) {
								fitment = FitMent.AVG.getScore();
							}
							break;
						default:
							break;
						}
						occupationDTO.setFitment(fitment);
						NewScoringLogicForTrafficLight NewScoringLogicForTrafficLightObj = new NewScoringLogicForTrafficLight();
						occupationDTO = NewScoringLogicForTrafficLightObj.GetNewTrafficLight(occupationDTO,
								getPersonalityCode());
						OUT.debug("Fitment: {} for occupationId: {}", fitment, occupationDTO.getId());
						occupationDTO = updatePriority(occupationDTO);
					}
				}
				// occupations
				Collections.sort(occupationsWithReqAbility, new OccupationPrioritySorter());
				OUT.debug("updated fitement occupationsWithReqAbility:{}", occupationsWithReqAbility);
			} catch (Exception e) {
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
	}

	public OccupationDTO updatePriority(OccupationDTO occupationDTO) {
//		
//		a  1	Green 4		Green 4
		if (occupationDTO.getNewfitment() == 4 && occupationDTO.getFitment() == 4) {
			occupationDTO.setDhPriority(1);
		}

//		b  2	Green 4 	Yellow 3
		if (occupationDTO.getNewfitment() == 4 && occupationDTO.getFitment() == 3) {
			occupationDTO.setDhPriority(2);
		}

//		C  3	Yellow 3	Green 4
		if (occupationDTO.getNewfitment() == 3 && occupationDTO.getFitment() == 4) {
			occupationDTO.setDhPriority(3);
		}

//		D  4	Green 4		Orange 2
		if (occupationDTO.getNewfitment() == 4 && occupationDTO.getFitment() == 2) {
			occupationDTO.setDhPriority(4);
		}
//		E  5	Yellow 3	Yellow 3
		if (occupationDTO.getNewfitment() == 3 && occupationDTO.getFitment() == 3) {
			occupationDTO.setDhPriority(5);
		}

//		F  6	Yellow 3	Orange 2
		if (occupationDTO.getNewfitment() == 3 && occupationDTO.getFitment() == 2) {
			occupationDTO.setDhPriority(6);
		}

//		G  7	Orange 2	Green 4
		if (occupationDTO.getNewfitment() == 2 && occupationDTO.getFitment() == 4) {
			occupationDTO.setDhPriority(7);
		}

//		H  8	Orange 2	Yellow 3
		if (occupationDTO.getNewfitment() == 2 && occupationDTO.getFitment() == 3) {
			occupationDTO.setDhPriority(8);
		}

//		I   9	Orange 2	Orange 2
		if (occupationDTO.getNewfitment() == 2 && occupationDTO.getFitment() == 2) {
			occupationDTO.setDhPriority(9);
		}

//		J  10	Green 4		Red 1
		if (occupationDTO.getNewfitment() == 4 && occupationDTO.getFitment() == 1) {
			occupationDTO.setDhPriority(10);
		}

//		K  11	Yellow 3	Red 1
		if (occupationDTO.getNewfitment() == 3 && occupationDTO.getFitment() == 1) {
			occupationDTO.setDhPriority(11);
		}

//		L  12	Orange 2	Red 1
		if (occupationDTO.getNewfitment() == 4 && occupationDTO.getFitment() == 3) {
			occupationDTO.setDhPriority(12);
		}

		return occupationDTO;
	}

	public class SubjectGrade implements Comparable<SubjectGrade> {
		private String subject;
		private String grade;

		public SubjectGrade(String subject, String grade) {
			super();
			this.subject = subject.trim();
			this.grade = grade.toLowerCase();
		}

		public Double gpa() {
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

		public int compareTo(SubjectGrade o) {
			if (o != null) {
				return o.gpa().compareTo(this.gpa());
			}
			return 0;
		}

		public String getSubject() {
			return subject;
		}

		public String getGrade() {
			return grade;
		}
	}

	class SubjectMarks implements Comparable<SubjectMarks> {
		private String subject;
		private Double marks;

		public SubjectMarks(String subject, Double marks) {
			super();
			this.subject = subject;
			this.marks = marks;
		}

		public int compareTo(SubjectMarks o) {
			if (o != null) {
				return o.marks.compareTo(this.marks);
			}
			return 0;
		}
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public static void main(String[] args) {
		String marksData = "percentage,English:98,Hindi:76,Kannada:89,Mathematics:98,Physical and Health Education:96,Physical and Health Education:99,Science :89,Social Science :99";
		System.out.println(new InterestResultUtilService().getTopSubjectsFromMarksScored(marksData));
	}
}

class ValueComparator1 implements Comparator<String> {
	Map<String, Integer> base;

	public ValueComparator1(Map<String, Integer> base) {
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with equals.
	public int compare(String a, String b) {
		if (base.get(a) <= base.get(b)) {
			return 1;
		} else {
			return -1;
		} // returning 0 would merge keys
	}
}

class OccupationFitmentSorter implements Comparator<OccupationDTO> {
	private static final Logger OUT = LoggerFactory.getLogger(OccupationFitmentSorter.class);

	public int compare(OccupationDTO o1, OccupationDTO o2) {
		if (o1.getNewfitment() == o2.getNewfitment()) {
			return Integer.valueOf(o2.getRequiredAbilityCount()).compareTo(o1.getRequiredAbilityCount());
		}
		return Integer.valueOf(o2.getNewfitment()).compareTo(o1.getNewfitment());

	}
}

class OccupationPrioritySorter implements Comparator<OccupationDTO> {
	private static final Logger OUT = LoggerFactory.getLogger(OccupationPrioritySorter.class);

	public int compare(OccupationDTO o1, OccupationDTO o2) {
		if (o1.getDhPriority() == o2.getDhPriority()) {
			return Integer.valueOf(o2.getRequiredAbilityCount()).compareTo(o1.getRequiredAbilityCount());
		}
		return Integer.valueOf(o1.getDhPriority()).compareTo(o2.getDhPriority());

	}
}
