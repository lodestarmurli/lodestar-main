package com.lodestar.edupath.CDDBulkDownload.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.CareerDegreeDiscovery.CareerDegreeDiscoveryDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.engineeringselector.EngineeringSelectorDAO;
import com.lodestar.edupath.dataaccessobject.dao.newscoringlogic.NewScoringLogicForTrafficLight;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery.CDDStreamDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery.CareerDegreeDiscoveryResultDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EngineeringSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.FitMent;

public class CDDRecommendationBulk {
	
	private static final Logger OUT = LoggerFactory.getLogger(CDDRecommendationBulk.class);
	Map<String, Object>									resultMap				= new LinkedHashMap<String, Object>();
	List<OccupationDTO> RecommendedOccupationList=null;
	CareerDegreeDiscoveryResultDTO _CDDDTO = new CareerDegreeDiscoveryResultDTO();
	private String PersonalityCode;
	private SqlSession session = null;
	List<OccupationDTO> cgtOcc = new ArrayList<OccupationDTO>();
	
	public String getPersonalityCode() {
		return PersonalityCode;
	}

	public void setPersonalityCode(String personalityCode) {
		PersonalityCode = personalityCode;
	}
	
	public List<OccupationDTO> getOccupationlist(StudentDetailsDTO studentDTO) throws Exception
	{
		List<OccupationDTO> allocclist = new ArrayList<OccupationDTO>();
		OccupationDAO occDAO = new OccupationDAO();
		
		try {	
			
				if (studentDTO.getClassId() == 1 || studentDTO.getClassId() == 2 ) 
				{
					allocclist = occDAO.getOccupationListForEngineeringS2WithException();
				} 
				else if ( studentDTO.getClassId() == 3 || studentDTO.getClassId() == 4 || studentDTO.getClassId() == 5) 
				{
					CDDStreamDTO _CDDdto = new CDDStreamDTO();
					CareerDegreeDiscoveryDAO _CDDdao = new CareerDegreeDiscoveryDAO();
					_CDDdto=_CDDdao.getCDDStream(studentDTO.getId());
					 
					if(_CDDdto != null)
					{
						DHStudentDetailsDTO dhstudentDTO = new DHStudentDetailsDTO();
						dhstudentDTO.setStream(_CDDdto.getStream());
						allocclist = occDAO.getOccupationListBasedStream(dhstudentDTO);
					}
					
				}
				else if ( studentDTO.getClassId() == 5 ) 
				{
					//allocclist=null;
					List<OccupationDTO> occlist = new ArrayList<OccupationDTO>();
					occlist= occDAO.getOccupationForTwelvePlusSCIENCE_MATH(getPersonalityCode());
					for(OccupationDTO occdto:occlist)
					{
						occdto.setDhStream(1);
						allocclist.add(occdto);
					}
					occlist= occDAO.getOccupationForTwelvePlusSCIENCE_WO_MATH(getPersonalityCode());
					for(OccupationDTO occdto:occlist)
					{
						occdto.setDhStream(6);
						allocclist.add(occdto);
					}
					occlist= occDAO.getOccupationForTwelvePlusCOMMERCE(getPersonalityCode());
					for(OccupationDTO occdto:occlist)
					{
						occdto.setDhStream(7);
						allocclist.add(occdto);
					}
					occlist= occDAO.getOccupationForTwelvePlusARTS(getPersonalityCode());
					for(OccupationDTO occdto:occlist)
					{
						occdto.setDhStream(5);
						allocclist.add(occdto);
					}
				}
				
		}catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return allocclist;
	}
	

	public List<OccupationDTO> getCareerDegreeDiscoveryRecommendation(StudentDetailsDTO student) throws Exception 
	{

		try {		
				StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,
						student.getId());
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
					cgtOcc = getOccupationlist(student);
					OUT.debug("bharath getCareerDegreeDiscoveryRecommendation cgtOcc:{}",cgtOcc);
					updateFitment(cgtOcc, (String) resultMap.get(ApplicationConstants.SystemRecommendation.SS_FACTOR.name()));
					OUT.debug("bharath getCareerDegreeDiscoveryRecommendation after updateFitment cgtOcc:{}",cgtOcc);
					
					Collections.sort(cgtOcc, new OccupationPrioritySorter());
					OUT.debug("bharath getCareerDegreeDiscoveryRecommendation after removing duplicate and sorting cgtOcc:{}",cgtOcc);
					
					List<OccupationDTO> indexes = new ArrayList<OccupationDTO>();
					for(int count=0;count<cgtOcc.size();count++)
					{
						if(cgtOcc.get(count).getDhPriority()==0 )
						{
							indexes.add(cgtOcc.get(count));
						}
						if(cgtOcc.get(count).getDhPriority()>=10)
						{
							indexes.add(cgtOcc.get(count));
						}
					}
					OUT.debug("bharath occ to be removed indexes:{}",indexes);
					cgtOcc.removeAll(indexes);
					Collections.sort(cgtOcc, new OccupationPrioritySorter());
					if (cgtOcc.size() > 5)
					{
						cgtOcc = cgtOcc.subList(0, 5);
					}
					resultMap.put(ApplicationConstants.SystemRecommendation.CGT.name(), cgtOcc);
					_CDDDTO.setStudentId(student.getId());
					_CDDDTO.setPersonalityCode(cgtResult.getPersonalityCode());
					if(!cgtOcc.isEmpty())
					{
						String occids=null;
						for(OccupationDTO occupationDTO:cgtOcc)
						{
							if (occids != null)
							{
								occids += ", ";
									
							}
							else
							{
								occids = new String();
							}
							occids+=occupationDTO.getName();	
						}
						_CDDDTO.setFinalOcc(occids);
						_CDDDTO.setOccOne(cgtOcc.get(0).getId());
						_CDDDTO.setOccTwo(cgtOcc.get(1).getId());
					}
					OUT.debug("bharath getCareerDegreeDiscoveryRecommendation engineeringSelectorDTO:{}",_CDDDTO);
				 	
				}
				session = MyBatisManager.getInstance().getSession();
				CareerDegreeDiscoveryDAO dao = new CareerDegreeDiscoveryDAO();
				int result = dao.insertORUpdateCDDResult(session, _CDDDTO);
				session.commit();
		} catch (Exception e) {
			if (session != null) {
				session.close();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		
		} finally {
			if (session != null) {
				session.close();
			}
		}
				
				
		return cgtOcc;
		
	}
	
	 
	public void updateFitment(List<OccupationDTO> occupationsWithReqAbility, String ssFactorStr) {
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
			occupationDTO.setDhPriority(1);
		}
		
//		b  2	Green 4 	Yellow 3
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setDhPriority(2);
		}
		
//		C  3	Yellow 3	Green 4
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setDhPriority(3);
		}
		
//		D  4	Green 4		Orange 2
		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setDhPriority(4);
		}
//		E  5	Yellow 3	Yellow 3
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setDhPriority(5);
		}
		
//		F  6	Yellow 3	Orange 2
		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setDhPriority(6);
		}
		
//		G  7	Orange 2	Green 4
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==4)
		{
			occupationDTO.setDhPriority(7);
		}
		
//		H  8	Orange 2	Yellow 3
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==3)
		{
			occupationDTO.setDhPriority(8);
		}
		
//		I   9	Orange 2	Orange 2
		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==2)
		{
			occupationDTO.setDhPriority(9);
		}
		
////		J  10	Red 1		Green 4		
//		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==4)
//		{
//			occupationDTO.setDhPriority(10);
//		}
//		
////		K  11	Red 1 		Yellow 3	
//		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==3)
//		{
//			occupationDTO.setDhPriority(11);
//		}
//		
////		L  12	Green 4		Red 1
//		if(occupationDTO.getNewfitment()==4 && occupationDTO.getFitment()==1)
//		{
//			occupationDTO.setDhPriority(12);
//		}
//		
//		
////		M  13	Yellow 3	Red 1
//		if(occupationDTO.getNewfitment()==3 && occupationDTO.getFitment()==1)
//		{
//			occupationDTO.setDhPriority(13);
//		}
//		
//		
////		N  14	Orange 2	Red 1
//		if(occupationDTO.getNewfitment()==2 && occupationDTO.getFitment()==1)
//		{
//			occupationDTO.setDhPriority(14);
//		}
//		
//		
////		O  15 	Red 1		Orange 2
//		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==2)
//		{
//			occupationDTO.setDhPriority(15);
//		}
//		
//		
////		P  16	Red 1		Red 1
//		if(occupationDTO.getNewfitment()==1 && occupationDTO.getFitment()==1)
//		{
//			occupationDTO.setDhPriority(16);
//		}

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
		if(o1.getDhPriority()==o2.getDhPriority())
		{
			return Integer.valueOf(o2.getRequiredAbilityCount()).compareTo(o1.getRequiredAbilityCount());
		}
		return Integer.valueOf(o1.getDhPriority()).compareTo(o2.getDhPriority());
		
		
	}
}
