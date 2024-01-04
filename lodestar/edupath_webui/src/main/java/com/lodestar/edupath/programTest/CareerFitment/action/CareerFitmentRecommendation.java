package com.lodestar.edupath.programTest.CareerFitment.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHEdupathDAO;
import com.lodestar.edupath.dataaccessobject.dao.careerFitment.CareerFitmentDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.newscoringlogic.NewScoringLogicForTrafficLight;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEdupathDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEntExamDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.JustForLodestar;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.StudentCareerFitmentDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.FitMent;

public class CareerFitmentRecommendation  {
	

	private static final Logger	OUT	= LoggerFactory.getLogger(CareerFitmentRecommendation.class);

	Map<String, Object> result = new HashMap<String,Object>();
	
	private String PersonalityCode;
	private SqlSession session = null;
	
	public String getPersonalityCode() {
		return PersonalityCode;
	}

	public void setPersonalityCode(String personalityCode) {
		PersonalityCode = personalityCode;
	}
	
	
	public Map<String, Object> getCareerFitmentRecommendation(StudentDetailsDTO student,StudentCGTResult cgtResult) throws Exception 
	{
		List<OccupationDTO> cgtOcc = new ArrayList<OccupationDTO>();
		List<OccupationDTO> allOccList = new ArrayList<OccupationDTO>();
		List<Integer> clusterIds = new ArrayList<Integer>();
		List<Integer> occIds = new ArrayList<Integer>();
		JustForLodestar jfl = new JustForLodestar();
		jfl.setCCCExamDegreedisplay("NO");
		jfl.setTop3ExamDegreedisplay("NO");
		try
		{
			CareerFitmentDAO _CFDAO = new CareerFitmentDAO();
			List<ClusterDTO> clusterList = new ArrayList<ClusterDTO>();
			StudentCareerFitmentDTO _SCFdto = _CFDAO.getStudentCareerFitment(student.getId());
			setPersonalityCode(cgtResult.getPersonalityCode());
			OUT.debug("bharath getCareerFitmentRecommendation _SCFdto:{}",_SCFdto);
			if( _SCFdto.getClusterId() != null && !_SCFdto.getClusterId().equalsIgnoreCase(""))
			{
				clusterList = _CFDAO.getClusterByIds(Arrays.asList(((String) _SCFdto.getClusterId()).split(",")));
				for(ClusterDTO clDTO:clusterList)
				{
					clusterIds.add(clDTO.getId());
				}
			}
			OUT.debug("bharath getCareerFitmentRecommendation clusterList:{}",clusterList);
			if(_SCFdto.getOccupationId() != null && !_SCFdto.getOccupationId().equalsIgnoreCase("")  )
			{
				cgtOcc = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_OCCPATION_IDS,Arrays.asList(((String) _SCFdto.getOccupationId()).split(",")));
				for(OccupationDTO occDTO:cgtOcc)
				{
					occIds.add(occDTO.getId());
				}
			}
			result.put("clusterIds", clusterIds);
			result.put("occIds", occIds);
			OUT.debug("bharath getCareerFitmentRecommendation cgtOcc:{}",cgtOcc);
			for(ClusterDTO cldto: clusterList)
			{
				OccupationDTO occDTO = new OccupationDTO();
				occDTO.setCluster(true);
				occDTO.setClusterName(cldto.getName());
				occDTO.setClusterId(cldto.getId());
				occDTO.setClusterDegree(cldto.getDegree());
				occDTO.setClusterEntExam(cldto.getEntExam());;
				occDTO.setRequiredRAISEC(cldto.getRequiredRAISEC());
				occDTO.setRequiredAbility(cldto.getRequiredAbility());
				DHEdupathDTO edu = new DHEdupathDTO();
				edu.setDegree(occDTO.getClusterDegree());
				edu.setEntExam(occDTO.getClusterEntExam());
				occDTO.setDheduDTO(edu);
				occDTO.setName(occDTO.getClusterName());
				cgtOcc.add(occDTO);
			}
			
			OUT.debug("bharath getCareerFitmentRecommendation after adding cluster cgtOcc:{}",cgtOcc);
			if(cgtOcc.size()>=4)
			{
				result.clear();
				return result;
			}
			updateFitment(cgtOcc,cgtResult.getSsFactor());
			OUT.debug("bharath getCareerFitmentRecommendation after updateFitment cgtOcc:{}",cgtOcc);
			
			int countHighFitment=0;
			for(OccupationDTO occDTO : cgtOcc) 
			{
				if(occDTO.getCareerFitmentPriority()>=1 && occDTO.getCareerFitmentPriority() <=10)
				{
					countHighFitment++;
					jfl.setCCCExamDegreedisplay("Yes");
					break;
				}
			}
//			 
			OUT.debug("bharath getCareerFitmentRecommendation countHighFitment:{}",countHighFitment);
			result.put("countHighFitment", countHighFitment);
			List<DHEdupathDTO> edupathlist = new ArrayList<DHEdupathDTO>();
			if(countHighFitment == 0)
			{
				jfl.setTop3ExamDegreedisplay("Yes");
				Map<String , List<Integer>> clustOccIds = new HashMap<String , List<Integer>>();
				clustOccIds.put("clusterIds", clusterIds);
				clustOccIds.put("occIds", occIds);
				OUT.debug("bharath getCareerFitmentRecommendation clustOccIds:{}",clustOccIds);
				
				allOccList= MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_OCC_FOR_CAREER_FITMENT, clustOccIds);
				OUT.debug("bharath getCareerFitmentRecommendation allOccList:{}",allOccList);
				updateFitment(allOccList,cgtResult.getSsFactor());
				OUT.debug("bharath getCareerFitmentRecommendation after updateFitment allOccList:{}",allOccList);
				if(allOccList.size()>3) 
				{
					allOccList=allOccList.subList(0, 3);
				}
				if(!allOccList.isEmpty()) 
				{
					List<Integer> occupationIds= new ArrayList<Integer>();
					for(OccupationDTO occ:allOccList)
					{
						occupationIds.add(occ.getId());
					}
					edupathlist = new ArrayList<DHEdupathDTO>();
					edupathlist= DHEdupathDAO.getDHEdupathbyOccId(occupationIds);
					for(OccupationDTO occ:allOccList)
					{
						for(DHEdupathDTO edu:edupathlist)
						{
							if(occ.getId()==edu.getOccupationId())
							{
								occ.setDheduDTO(edu);
							}
						}
					}
					List<String> examList = new ArrayList<String>();
					for(DHEdupathDTO dhedupath : edupathlist)
					{
						String[] examNamelist= dhedupath.getEntExam().split(",");
						for(String _examName: examNamelist)
						{
							examList.add(_examName.trim());	
						}
						
					}
					List<DHEntExamDTO> entExamlist = DHEdupathDAO.getexamdescription(examList);
					result.put("entExamlist",entExamlist);
					OUT.debug("bharath getCareerFitmentRecommendation 2 occupationIds:{}, edupathlist:{},entExamlist:{}",occupationIds,edupathlist,entExamlist);
				}
					
			}
			else
			{
				List<String> examList = new ArrayList<String>();
				List<Integer> CCCoccupationIds= new ArrayList<Integer>();
				for(OccupationDTO occ:cgtOcc)
				{	
					if(!occ.isCluster()) {
						CCCoccupationIds.add(occ.getId());
					}
				}
				if(CCCoccupationIds.size() > 0)
				{
					edupathlist= DHEdupathDAO.getDHEdupathbyOccId(CCCoccupationIds);
					for(OccupationDTO occ:cgtOcc)
					{
						if(occ.isCluster()) 
						{
							String[] examNamelist= occ.getClusterEntExam().trim().split(",");
							for(String _examName: examNamelist)
							{
								examList.add(_examName.trim());	
							}
						}
						else
						{
							for(DHEdupathDTO edu : edupathlist)
							{
								if(occ.getId() == edu.getOccupationId())
								{
						 			occ.setDheduDTO(edu);
								}
							}
						}
					}
				}
				
				for(DHEdupathDTO dhedupath : edupathlist)
				{
					String[] examNamelist= dhedupath.getEntExam().split(",");
					for(String _examName: examNamelist)
					{
						examList.add(_examName.trim());	
					}
					
				}
				List<DHEntExamDTO> entExamlist = DHEdupathDAO.getexamdescription(examList);
				result.put("entExamlist",entExamlist);
				OUT.debug("bharath getCareerFitmentRecommendation 1 CCCoccupationIds:{}, edupathlist:{},entExamlist:{}",CCCoccupationIds,edupathlist,entExamlist);
			}
			
			
			String allRaisecCode="";
			for(OccupationDTO occDTO : cgtOcc)
			{
				allRaisecCode+=occDTO.getRequiredRAISEC().replace(", ","");
			}
			allRaisecCode=allRaisecCode.trim();
	//		String allRaisecCodeTemp=allRaisecCode.replace("",",");
			String[] RAISECChar=allRaisecCode.split("");
			
			String[] finalCode = new HashSet<String>(Arrays.asList(allRaisecCode.split(""))).toArray(new String[0]);
			OUT.debug("bharath getCareerFitmentRecommendation  allRaisecCode:{}, RAISECChar:{}, finalCode:{}",allRaisecCode,RAISECChar,finalCode);
			List<String> Codelist= new ArrayList<String>(Arrays.asList(finalCode));
			String[] studentcode=getPersonalityCode().split(",");
			OUT.debug("bharath getCareerFitmentRecommendation before removing getPersonalityCode():{}, Codelist:{}, studentcode:{}",getPersonalityCode(),Codelist,studentcode);
			for(String indiviCode:studentcode)
			{
				if(Codelist.get(0)==null || Codelist.get(0).equals(""))
				{
					Codelist.remove(0);
				}
				if(Codelist.contains(indiviCode))
				{
					Codelist.remove(indiviCode);
				}
			}
			result.put("raisecCodelist", Codelist);
			result.put("studentRaiseccode", studentcode);
			OUT.debug("bharath getCareerFitmentRecommendation after removing Codelist:{}, studentcode:{}",Codelist,studentcode);
			
			
			result.put("cgtOcc", cgtOcc);
			result.put("allOccList", allOccList);
			OUT.debug("bharath getCareerFitmentRecommendation cgtOcc.size(){}",cgtOcc.size());
			OUT.debug("bharath getCareerFitmentRecommendation result{}",result);
			jfl.setStudentId(student.getId());
			jfl.setPersonalityCode(getPersonalityCode());
			jfl.setAbility(cgtResult.getSsFactor());
			jfl.setLDID("LD"+student.getUserId());
//			if(!cgtOcc.isEmpty())
//			{
//				String CCCexam="";
//				String CCCdegress="";
//				if(cgtOcc.get(0).isCluster())
//				{
//					jfl.setCCC1(cgtOcc.get(0).getClusterName());
////					CCCexam += cgtOcc.get(0).getClusterEntExam()+", ";
////					CCCdegress+=cgtOcc.get(0).getClusterDegree()+", ";
//				}
//				else
//				{
//					jfl.setCCC1(cgtOcc.get(0).getName());
////					CCCexam += cgtOcc.get(0).getDheduDTO().getEntExam()+", ";
////					CCCdegress+=cgtOcc.get(0).getDheduDTO().getDegree()+", ";
//				}
//				jfl.setCCC1PF(cgtOcc.get(0).getInterestfitment());
//				jfl.setCCC1AF(cgtOcc.get(0).getAbilityfitment());
//				jfl.setCCC1AFreqd(cgtOcc.get(0).getRequiredAbility());
//				jfl.setCCC1priority(cgtOcc.get(0).getCareerFitmentPriority());
//				
//				
//				if(cgtOcc.size()>=2)
//				{
//					if(cgtOcc.get(1).isCluster())
//					{
//						jfl.setCCC2(cgtOcc.get(1).getClusterName());
////						CCCexam += cgtOcc.get(1).getClusterEntExam()+", ";
////						CCCdegress+=cgtOcc.get(1).getClusterDegree()+", ";
//					}
//					else
//					{
//						jfl.setCCC2(cgtOcc.get(1).getName());
////						CCCexam += cgtOcc.get(1).getDheduDTO().getEntExam()+", ";
////						CCCdegress+=cgtOcc.get(1).getDheduDTO().getDegree()+", ";
//					}
//					jfl.setCCC2PF(cgtOcc.get(1).getInterestfitment());
//					jfl.setCCC2AF(cgtOcc.get(1).getAbilityfitment());
//					jfl.setCCC2AFreqd(cgtOcc.get(1).getRequiredAbility());
//					jfl.setCCC2priority(cgtOcc.get(1).getCareerFitmentPriority());
//				}
//				if(cgtOcc.size()==3)
//				{
//					if(cgtOcc.get(2).isCluster())
//					{
//						jfl.setCCC3(cgtOcc.get(2).getClusterName());
////						CCCexam += cgtOcc.get(2).getClusterEntExam()+", ";
////						CCCdegress+=cgtOcc.get(2).getClusterDegree()+", ";
//					}
//					else
//					{
//						jfl.setCCC3(cgtOcc.get(2).getName());
////						CCCexam += cgtOcc.get(2).getDheduDTO().getEntExam();
////						CCCdegress+=cgtOcc.get(2).getDheduDTO().getDegree();
//					}
//					jfl.setCCC3PF(cgtOcc.get(2).getInterestfitment());
//					jfl.setCCC3AF(cgtOcc.get(2).getAbilityfitment());
//					jfl.setCCC3AFreqd(cgtOcc.get(2).getRequiredAbility());
//					jfl.setCCC3priority(cgtOcc.get(2).getCareerFitmentPriority());
//					
//					
//				}
//				jfl.setCCCexam(CCCexam);
//				jfl.setCCCdegree(CCCdegress);
//				
//			}
//			if(!allOccList.isEmpty()) 
//			{	
//				jfl.setTop3(allOccList.toString());
////				jfl.setAllexam(allOccList.get(0).getDheduDTO().getEntExam()+", "+allOccList.get(1).getDheduDTO().getEntExam()+", "+allOccList.get(2).getDheduDTO().getEntExam());
////				jfl.setAllDegree(allOccList.get(0).getDheduDTO().getDegree()+", "+allOccList.get(1).getDheduDTO().getDegree()+", "+allOccList.get(2).getDheduDTO().getDegree());
//			}
//			jfl.setPMF(Codelist.toString());
//				
//			
//			session = MyBatisManager.getInstance().getSession();
//			int result = _CFDAO.insertORUpdateJustForLodestar(session, jfl);
//			session.commit();	
		} catch (Exception e) {
			if (session != null) {
				session.close();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
			result.clear();
			return result;
		
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}
	
	
	
	 
	public void updateFitment(List<OccupationDTO> occupationsWithReqAbility, String ssFactorStr) {
		OUT.debug("bharath getCareerFitmentRecommendation updateFitment occupationsWithReqAbility:{},ssFactorStr:{}",occupationsWithReqAbility,ssFactorStr);
		if (ssFactorStr != null && !ssFactorStr.trim().isEmpty() && occupationsWithReqAbility != null
				&& !occupationsWithReqAbility.isEmpty()) {
			try {
				for (OccupationDTO occupationDTO : occupationsWithReqAbility) {
					if (occupationDTO.getRequiredAbilityCount() > 0) {
						String[] requiredAbilites = trimWhiteSpaceBWComma(occupationDTO.getRequiredAbility()).split(",");
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
						occupationDTO = NewScoringLogicForTrafficLightObj.GetNewTrafficLight(occupationDTO,getPersonalityCode());
						OUT.debug("Fitment: {} for occupationId: {}", fitment, occupationDTO.getId());
						occupationDTO=updatePriority(occupationDTO);
						OUT.debug("bharath after updatePriority occupationDTO: {} ", occupationDTO);
					}
				}

				// occupations
				OUT.debug("bharath before OccupationPrioritySorter occupationsWithReqAbility:{} ",occupationsWithReqAbility);
				Collections.sort(occupationsWithReqAbility, new OccupationPrioritySorter());
				
				OUT.debug("bharath after OccupationPrioritySorter occupationsWithReqAbility:{} ",occupationsWithReqAbility);
			} catch (Exception e) {
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
	}
	
	public OccupationDTO updatePriority(OccupationDTO occupationDTO)
	{
//		
//		a  1	Green 4		Green 4
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setCareerFitmentPriority(1);
		}
		
//		b  2	Green 4 	Yellow 3
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setCareerFitmentPriority(2);
		}
		
//		C  3	Yellow 3	Green 4
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setCareerFitmentPriority(3);
		}
		
//		D  4	Yellow 3	Yellow 3
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setCareerFitmentPriority(4);
		}
//		E  5	Green 4		Orange 2
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setCareerFitmentPriority(5);
		}
		
//		F  6	Yellow 3	Orange 2
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setCareerFitmentPriority(6);
		}
		
//		G  7	Orange 2	Green 4
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setCareerFitmentPriority(7);
		}
		
//		H  8	Orange 2	Yellow 3
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setCareerFitmentPriority(8);
		}
		
//		I   9	Orange 2	Orange 2
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setCareerFitmentPriority(9);
		}
		
//		J  10	Green 4		Red 1			
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==1)
		{
			occupationDTO.setCareerFitmentPriority(10);
		}
		
//		K  11	Yellow 3	Red 1 			
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==1)
		{
			occupationDTO.setCareerFitmentPriority(11);
		}
		
//		L  12	Orange 2	Red 1
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==1)
		{
			occupationDTO.setCareerFitmentPriority(12);
		}
		
		
//		M  13	Red 1		Green 4	
		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setCareerFitmentPriority(13);
		}
		
		
//		N  14	Red 1		Yellow 3
		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setCareerFitmentPriority(14);
		}
		
		
//		O  15 	Red 1		Orange 2
		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setCareerFitmentPriority(15);
		}
		
		
//		P  16	Red 1		Red 1
		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==1)
		{
			occupationDTO.setCareerFitmentPriority(16);
		}

		return occupationDTO;
	}

	
	
	public String OccToString(List<OccupationDTO> cgtOcc)
	{
		String completeOccList="";
		for(OccupationDTO occ:cgtOcc)
		{
			completeOccList="occdto[";
			completeOccList+="Name = "+occ.getName();
			
			completeOccList+=", InterestFItment=";
			if(occ.getNewfitment()==4)
			{
				completeOccList+="Green";
			}
			else if(occ.getNewfitment()==3)
			{
				completeOccList+="Yellow";
			}
			else if(occ.getNewfitment()==2)
			{
				completeOccList+="Orange";
			}
			else if(occ.getNewfitment()==1)
			{
				completeOccList+="Red";
			}
			
			
			completeOccList+=", ability=";
			if(occ.getFitment()==4)
			{
				completeOccList+="Green";
			}
			else if(occ.getFitment()==3)
			{
				completeOccList+="Yellow";
			}
			else if(occ.getFitment()==2)
			{
				completeOccList+="Orange";
			}
			else if(occ.getFitment()==1)
			{
				completeOccList+="Red";
			}
			
			
			
			completeOccList+=", requiredRAISEC="+occ.getRequiredRAISEC();
			completeOccList+=", requiredAbility="+occ.getRequiredAbility();
			completeOccList+=", engineeringPriority="+occ.getEngineeringPriority();
			completeOccList+=", favSubject="+occ.getFavSubject();
			completeOccList+=", favSubPriority="+occ.getFavSubPriority();
			completeOccList+=", engineeringDegree="+occ.getEngineeringDegree();
			completeOccList+="], ";
		}
		OUT.debug("bharath OccToString completeOccList:{}",completeOccList);
		return completeOccList;
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

	
	
	
	
}

class OccupationPrioritySorter implements Comparator<OccupationDTO>
{
	private static final Logger	OUT	= LoggerFactory.getLogger(OccupationPrioritySorter.class);
	public int compare(OccupationDTO o1, OccupationDTO o2)
	{	
		if(o1.getCareerFitmentPriority()==o2.getCareerFitmentPriority())
		{
			return Integer.valueOf(o2.getRequiredAbilityCount()).compareTo(o1.getRequiredAbilityCount());
		}
		return Integer.valueOf(o1.getCareerFitmentPriority()).compareTo(o2.getCareerFitmentPriority());
		
		
	}
}

