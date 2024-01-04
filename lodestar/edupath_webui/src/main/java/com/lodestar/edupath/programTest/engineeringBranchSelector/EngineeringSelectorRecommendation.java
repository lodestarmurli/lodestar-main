package com.lodestar.edupath.programTest.engineeringBranchSelector;

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
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.engineeringselector.EngineeringSelectorDAO;
import com.lodestar.edupath.dataaccessobject.dao.newscoringlogic.NewScoringLogicForTrafficLight;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EngineeringSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.FitMent;

public class EngineeringSelectorRecommendation {

	private static final Logger OUT = LoggerFactory.getLogger(EngineeringSelectorRecommendation.class);
	
	Map<String, Object>									resultMap				= new LinkedHashMap<String, Object>();
	List<OccupationDTO> RecommendedOccupationList=null;
	EngineeringSelectorDTO engineeringSelectorDTO = new EngineeringSelectorDTO();
	private String PersonalityCode;
	private SqlSession session = null;
	List<OccupationDTO> cgtOcc = new ArrayList<OccupationDTO>();
	
	public String getPersonalityCode() {
		return PersonalityCode;
	}

	public void setPersonalityCode(String personalityCode) {
		PersonalityCode = personalityCode;
	}

	public List<OccupationDTO> getEngineeringSelectorRecommendation(int studentId, Map<String, Integer> favSubPriority) throws Exception {

		
		StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,
				studentId);
		if (cgtResult != null && (cgtResult.getSsFactor() != null && !cgtResult.getSsFactor().trim().isEmpty()))
		{
			resultMap.put(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name(), cgtResult.getOccupationIds());
			resultMap.put(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name(), cgtResult.getPersonalityCode());
			resultMap.put(ApplicationConstants.SystemRecommendation.SS_FACTOR.name(), cgtResult.getSsFactor());
			resultMap.put(ApplicationConstants.SystemRecommendation.APP_SCORE.name(), cgtResult.getAppScore());
			setPersonalityCode(cgtResult.getPersonalityCode());
			
		}
		if ((resultMap.containsKey(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name()) 
				&& resultMap.get(ApplicationConstants.SystemRecommendation.PESONALITY_CODE.name()) != null) 
			&& (!resultMap.containsKey(ApplicationConstants.SystemRecommendation.CGT.name()) 
					|| resultMap.get(ApplicationConstants.SystemRecommendation.CGT.name()) == null))
		{
			cgtOcc = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_OCCPATION_IDS_FOR_ENGINEERING_SELECTOR,
					Arrays.asList(((String) resultMap.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name())).split(",")));
			updateFitment(cgtOcc, (String) resultMap.get(ApplicationConstants.SystemRecommendation.SS_FACTOR.name()),favSubPriority);
			OUT.debug("bharath getEngineeringSelectorRecommendation after updateFitment cgtOcc:{}",cgtOcc);
			
			cgtOcc = removeDuplicatesDegree(cgtOcc); 
			Collections.sort(cgtOcc, new OccupationPrioritySorter());
			OUT.debug("bharath getEngineeringSelectorRecommendation after removing duplicate and sorting cgtOcc:{}",cgtOcc);
			engineeringSelectorDTO.setStudentId(studentId);
			engineeringSelectorDTO.setRaisecCode(cgtResult.getPersonalityCode().trim());
			engineeringSelectorDTO.setCompleteOccList( cgtOcc.toString().trim());
			engineeringSelectorDTO.setFavSubPriorityList(favSubPriority.toString().trim()); 
			
			if (cgtOcc.size() > 4)
			{
				cgtOcc = cgtOcc.subList(0, 4);
			}
		
			List<OccupationDTO> indexes = new ArrayList<OccupationDTO>();
			boolean isSet1=false;
			for(int count=0;count<cgtOcc.size();count++)
			{
				if(cgtOcc.get(count).getEngineeringPriority()>=12 && cgtOcc.get(count).getEngineeringPriority()<15)
				{
					isSet1=true;
				}
				if(cgtOcc.get(count).getEngineeringPriority()>=15)
				{
					indexes.add(cgtOcc.get(count));
				}
			}
			OUT.debug("bharath occ to be removed indexes:{}",indexes);
			cgtOcc.removeAll(indexes);

			engineeringSelectorDTO.setSet1(" ");
			if(isSet1)
			{
				engineeringSelectorDTO.setSet1("while Personality fitment is there for the child, aptitude needs to be worked upon and improved and refer column shortlistOccWithFavSub for  system recommendations");
			}
//			if(cgtOcc.isEmpty())
//			{
//				engineeringSelectorDTO.setSet2("Engineering is not a good fit for the child in terms of Personality or Aptitude and they would be better off considering some different types of careers.");
//			}
//			Collections.sort(cgtOcc, new OccupationFavSubjectPrioritySorter());
			engineeringSelectorDTO.setFinalList(cgtOcc.toString().trim());
			OUT.debug("bharath getEngineeringSelectorRecommendation engineeringSelectorDTO:{}",engineeringSelectorDTO);
		 	
		}
		engineeringSelectorDTO.setShortlistOcc(cgtOcc.toString().trim()); 
		engineeringSelectorDTO.setShortlistOccWithFavSub(" ");
		engineeringSelectorDTO.setSet2(" ");			
		session = MyBatisManager.getInstance().getSession();
		EngineeringSelectorDAO dao = new EngineeringSelectorDAO();
		int result = dao.insertORUpdateStudentCGTResult(session, engineeringSelectorDTO);
		session.commit();
		return cgtOcc;
		
	}
	
	public  List<OccupationDTO> removeDuplicatesDegree(List<OccupationDTO> cgtOcc2) 
    { 
        List<OccupationDTO> newList = new ArrayList<OccupationDTO>(); 
        List<String> Temphold= new ArrayList<String>();
        for (OccupationDTO occ : cgtOcc2) 
        { 
            if (!Temphold.contains(occ.getEngineeringDegree().trim())) { 
                newList.add(occ); 
                Temphold.add(occ.getEngineeringDegree().trim());
            } 
        } 
        return newList; 
    } 

	public void updateFitment(List<OccupationDTO> occupationsWithReqAbility, String ssFactorStr,Map<String, Integer> favSubPriority) {
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
						occupationDTO=updatePriority(occupationDTO, favSubPriority);
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
	
	public OccupationDTO updatePriority(OccupationDTO occupationDTO,Map<String, Integer> favSubPriority)
	{
//		
//		a  1	Green 4		Green 4
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setEngineeringPriority(1);
		}
		
//		b  2	Green 4 	Yellow 3
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setEngineeringPriority(2);
		}
		
//		C  3	Yellow 3	Green 4
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setEngineeringPriority(3);
		}
		
//		D  4	Green 4		Orange 2
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setEngineeringPriority(4);
		}
//		E  5	Yellow 3	Yellow 3
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setEngineeringPriority(5);
		}
		
//		F  6	Yellow 3	Orange 2
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setEngineeringPriority(6);
		}
		
//		G  7	Orange 2	Green 4
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setEngineeringPriority(7);
		}
		
//		H  8	Orange 2	Yellow 3
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setEngineeringPriority(8);
		}
		
//		I   9	Orange 2	Orange 2
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setEngineeringPriority(9);
		}
		
//		J  10	Red 1		Green 4		
		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setEngineeringPriority(10);
		}
		
//		K  11	Red 1 		Yellow 3	
		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setEngineeringPriority(11);
		}
		
//		L  12	Green 4		Red 1
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==1)
		{
			occupationDTO.setEngineeringPriority(12);
		}
		
		
//		M  13	Yellow 3	Red 1
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==1)
		{
			occupationDTO.setEngineeringPriority(13);
		}
		
		
//		N  14	Orange 2	Red 1
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==1)
		{
			occupationDTO.setEngineeringPriority(14);
		}
		
		
//		O  15 	Red 1		Orange 2
		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setEngineeringPriority(15);
		}
		
		
//		P  16	Red 1		Red 1
		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==1)
		{
			occupationDTO.setEngineeringPriority(16);
		}

		for (Map.Entry<String,Integer> subPriority : favSubPriority.entrySet())  
		{
			OUT.debug("bharath updatePriority occupationDTO.getFavSubject():{} favSubPriority subPriority.getKey():{}, subPriority.getValue():{}",occupationDTO.getFavSubject(),subPriority.getKey(),subPriority.getValue());
			if(occupationDTO.getFavSubject().equalsIgnoreCase(subPriority.getKey()))
			{
				occupationDTO.setFavSubPriority(subPriority.getValue());
			}
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



//class OccupationFavSubjectPrioritySorter implements Comparator<OccupationDTO>
//{
//	private static final Logger							OUT						= LoggerFactory.getLogger(OccupationFavSubjectPrioritySorter.class);
//	public int compare(OccupationDTO o1, OccupationDTO o2)
//	{
//		if (o1.getFavSubPriority() == o2.getFavSubPriority())
//		{
//			return Integer.valueOf(o2.getRequiredAbilityCount()).compareTo(o1.getRequiredAbilityCount());
//		}
//		return Integer.valueOf(o1.getFavSubPriority()).compareTo(o2.getFavSubPriority());
//	}
//}


class OccupationPrioritySorter implements Comparator<OccupationDTO>
	{
		private static final Logger	OUT	= LoggerFactory.getLogger(OccupationPrioritySorter.class);
		public int compare(OccupationDTO o1, OccupationDTO o2)
		{		
			if(o1.getEngineeringPriority()==o2.getEngineeringPriority())
			{
				if(o1.getFavSubPriority()==o2.getFavSubPriority())
				{
					return Integer.valueOf(o2.getRequiredAbilityCount()).compareTo(o1.getRequiredAbilityCount());
				}
				return Integer.valueOf(o1.getFavSubPriority()).compareTo(o2.getFavSubPriority());
			}
			return Integer.valueOf(o1.getEngineeringPriority()).compareTo(o2.getEngineeringPriority());
		}
}


