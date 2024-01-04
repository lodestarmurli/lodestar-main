package com.lodestar.edupath.dataaccessobject.dao.calculatefitmentcolor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.newscoringlogic.NewScoringLogicForTrafficLight;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.FitMent;



public class CalculateFitmentColor {
	private static final Logger	OUT	= LoggerFactory.getLogger(CalculateFitmentColor.class);
	
	public List<OccupationDTO> GetFitementForManualSearch(List<OccupationDTO> resultList, int StudentID)
	{
		OUT.debug("Getting GetFitementForManualSearch:{}", StudentID);
		StudentCGTResult cgtResult=null;
		try {
			cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,
					StudentID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<OccupationDTO> resultList1=new ArrayList<OccupationDTO>();
		
		if (cgtResult != null && (cgtResult.getSsFactor() != null && !cgtResult.getSsFactor().trim().isEmpty()))
		{
		
		String ssFactorStr=cgtResult.getSsFactor();
		String PersonalityCode=cgtResult.getPersonalityCode();
		
		
		if (ssFactorStr != null && !ssFactorStr.trim().isEmpty())
		{
			try
			{
				for (OccupationDTO occupationDTO : resultList)
				{
					if (occupationDTO.getRequiredAbilityCount() > 0)
					{
						String[] requiredAbilites = trimWhiteSpaceBWComma(occupationDTO.getRequiredAbility()).split(",");

						JSONObject ssFactorJSON = new JSONObject(ssFactorStr);
						Map<String, Integer> fitmentCount = new LinkedHashMap<String, Integer>();
						fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.M.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.H.getProperty(), 0);

						for (String ability : requiredAbilites)
						{
							if (ssFactorJSON.has(ability))
							{
								fitmentCount.put(ssFactorJSON.getString(ability), fitmentCount.get(ssFactorJSON.getString(ability)) + 1);
							}
							else
							{
								fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(), fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty()) + 1);
							}
						}

						OUT.debug("Fitment SSFactor : {}, requiredAbilities: {}, fitMentCount: {}", ssFactorJSON, requiredAbilites, fitmentCount);
						int fitment = FitMent.LOW.getScore();
						int highCount = fitmentCount.get(ApplicationConstants.SSFactor.H.getProperty());
						int mediumCount = fitmentCount.get(ApplicationConstants.SSFactor.M.getProperty());
						// int lowCount = fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty());
						switch (occupationDTO.getRequiredAbilityCount())
						{
							case 5:
								if (highCount >= 4)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 3 || (highCount >= 2 && mediumCount >= 3))
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if ((highCount >= 2) || (mediumCount >= 4) || (highCount >= 1 && mediumCount >= 2))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 4:
								if (highCount >= 3)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 2 && mediumCount >= 1)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (highCount >= 2 || mediumCount >= 3 || (highCount >= 1 && mediumCount >= 1))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 3:
								if (highCount >= 3)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 2)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (highCount >= 1 || mediumCount >= 2 || (highCount >= 1 && mediumCount >= 1))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 2:
								if (highCount >= 2)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 1 && mediumCount >= 1)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (mediumCount >= 2 || highCount == 1)
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 1:
								if (highCount == 1)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (mediumCount == 1)
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							default:
								break;
						}
						occupationDTO.setFitment(fitment);
						
						//BEGIN Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 22-02-2017
						NewScoringLogicForTrafficLight NewScoringLogicForTrafficLightObj=new NewScoringLogicForTrafficLight();
						occupationDTO=NewScoringLogicForTrafficLightObj.GetNewTrafficLight(occupationDTO,PersonalityCode);
						
						//END Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 22-02-2017
						OUT.debug("Fitment: {} for occupationId: {}", fitment, occupationDTO.getId());
						
						resultList1.add(occupationDTO);
					}
				}

				// occupations
				
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		
		
		}
		return resultList1;
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

	
	public OccupationDTO getOccupationFitmentForGlossary(OccupationDTO occupationDTO,int StudentID)
	{
		OUT.debug("Getting getOccupationFitmentForGlossary:{}", StudentID);
		StudentCGTResult cgtResult=null;
		try {
			cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,
					StudentID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (cgtResult != null && (cgtResult.getSsFactor() != null && !cgtResult.getSsFactor().trim().isEmpty()))
		{
		
		String ssFactorStr=cgtResult.getSsFactor();
		String PersonalityCode=cgtResult.getPersonalityCode();
		
		
		if (ssFactorStr != null && !ssFactorStr.trim().isEmpty())
		{
			try
			{
//				for (OccupationDTO occupationDTO : resultList)
//				{
				
				
				//System.out.println("========================>"+occupationDTO.getRequiredAbilityCount());
				
					if (occupationDTO.getRequiredAbilityCount() > 0)
					{
						String[] requiredAbilites = trimWhiteSpaceBWComma(occupationDTO.getRequiredAbility()).split(",");

						JSONObject ssFactorJSON = new JSONObject(ssFactorStr);
						Map<String, Integer> fitmentCount = new LinkedHashMap<String, Integer>();
						fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.M.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.H.getProperty(), 0);

						for (String ability : requiredAbilites)
						{
							if (ssFactorJSON.has(ability))
							{
								fitmentCount.put(ssFactorJSON.getString(ability), fitmentCount.get(ssFactorJSON.getString(ability)) + 1);
							}
							else
							{
								fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(), fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty()) + 1);
							}
						}

						OUT.debug("Fitment SSFactor : {}, requiredAbilities: {}, fitMentCount: {}", ssFactorJSON, requiredAbilites, fitmentCount);
						int fitment = FitMent.LOW.getScore();
						int highCount = fitmentCount.get(ApplicationConstants.SSFactor.H.getProperty());
						int mediumCount = fitmentCount.get(ApplicationConstants.SSFactor.M.getProperty());
						// int lowCount = fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty());
						switch (occupationDTO.getRequiredAbilityCount())
						{
							case 5:
								if (highCount >= 4)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 3 || (highCount >= 2 && mediumCount >= 3))
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if ((highCount >= 2) || (mediumCount >= 4) || (highCount >= 1 && mediumCount >= 2))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 4:
								if (highCount >= 3)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 2 && mediumCount >= 1)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (highCount >= 2 || mediumCount >= 3 || (highCount >= 1 && mediumCount >= 1))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 3:
								if (highCount >= 3)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 2)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (highCount >= 1 || mediumCount >= 2 || (highCount >= 1 && mediumCount >= 1))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 2:
								if (highCount >= 2)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 1 && mediumCount >= 1)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (mediumCount >= 2 || highCount == 1)
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 1:
								if (highCount == 1)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (mediumCount == 1)
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							default:
								break;
						}
						occupationDTO.setFitment(fitment);
						
						//BEGIN Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 22-02-2017
						NewScoringLogicForTrafficLight NewScoringLogicForTrafficLightObj=new NewScoringLogicForTrafficLight();
						occupationDTO=NewScoringLogicForTrafficLightObj.GetNewTrafficLight(occupationDTO,PersonalityCode);
						
						//END Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 22-02-2017
						OUT.debug("Fitment: {} for occupationId: {}", fitment, occupationDTO.getId());
						
						
					}
				//}

				// occupations
				
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		
		
		}
		return occupationDTO;
		
		
		
	}
	
	public List<ShortListDTO> getOccupationFitmentForShortList(List<ShortListDTO> shortListDTO,int StudentID)
	{
		OUT.debug("Getting getOccupationFitmentForShortList:{}", StudentID);
		StudentCGTResult cgtResult=null;
		try {
			cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,
					StudentID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<ShortListDTO> shortListDTO1= new ArrayList<ShortListDTO>();
		if (cgtResult != null && (cgtResult.getSsFactor() != null && !cgtResult.getSsFactor().trim().isEmpty()))
		{
		
		String ssFactorStr=cgtResult.getSsFactor();
		String PersonalityCode=cgtResult.getPersonalityCode();
		
		
		if (ssFactorStr != null && !ssFactorStr.trim().isEmpty())
		{
			try
			{
				
				
				for (ShortListDTO occupationDTO : shortListDTO)
				{
				
				
			//	System.out.println("========================>"+occupationDTO.getRequiredAbilityCount());
				
					if (occupationDTO.getRequiredAbilityCount() > 0)
					{
						String[] requiredAbilites = trimWhiteSpaceBWComma(occupationDTO.getRequiredAbility()).split(",");

						JSONObject ssFactorJSON = new JSONObject(ssFactorStr);
						Map<String, Integer> fitmentCount = new LinkedHashMap<String, Integer>();
						fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.M.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.H.getProperty(), 0);

						for (String ability : requiredAbilites)
						{
							if (ssFactorJSON.has(ability))
							{
								fitmentCount.put(ssFactorJSON.getString(ability), fitmentCount.get(ssFactorJSON.getString(ability)) + 1);
							}
							else
							{
								fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(), fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty()) + 1);
							}
						}

						OUT.debug("Fitment SSFactor : {}, requiredAbilities: {}, fitMentCount: {}", ssFactorJSON, requiredAbilites, fitmentCount);
						int fitment = FitMent.LOW.getScore();
						int highCount = fitmentCount.get(ApplicationConstants.SSFactor.H.getProperty());
						int mediumCount = fitmentCount.get(ApplicationConstants.SSFactor.M.getProperty());
						// int lowCount = fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty());
						switch (occupationDTO.getRequiredAbilityCount())
						{
							case 5:
								if (highCount >= 4)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 3 || (highCount >= 2 && mediumCount >= 3))
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if ((highCount >= 2) || (mediumCount >= 4) || (highCount >= 1 && mediumCount >= 2))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 4:
								if (highCount >= 3)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 2 && mediumCount >= 1)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (highCount >= 2 || mediumCount >= 3 || (highCount >= 1 && mediumCount >= 1))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 3:
								if (highCount >= 3)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 2)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (highCount >= 1 || mediumCount >= 2 || (highCount >= 1 && mediumCount >= 1))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 2:
								if (highCount >= 2)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 1 && mediumCount >= 1)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (mediumCount >= 2 || highCount == 1)
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 1:
								if (highCount == 1)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (mediumCount == 1)
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							default:
								break;
						}
						occupationDTO.setFitment(fitment);
						
						//BEGIN Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 22-02-2017
						NewScoringLogicForTrafficLight NewScoringLogicForTrafficLightObj=new NewScoringLogicForTrafficLight();
						occupationDTO=NewScoringLogicForTrafficLightObj.GetNewTrafficLightForShortList(occupationDTO,PersonalityCode);
						
						//END Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 22-02-2017
						OUT.debug("Fitment: {} for occupationId: {}", fitment, occupationDTO.getId());
						shortListDTO1.add(occupationDTO);
						
					}
					
					
					
				}

				// occupations
				
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		
		
		}
		return shortListDTO1;
		
		
		
	}
	
	
	public List<ShortListDTO> CalculatefitmentforstudentShortList(List<ShortListDTO> shortListDTO,int StudentID)
	{
		OUT.debug("Getting CalculatefitmentforstudentShortList:{}", StudentID);
		StudentCGTResult cgtResult=null;
		try {
			cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,
					StudentID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<ShortListDTO> shortListDTO1= new ArrayList<ShortListDTO>();
		int colorFitment=1;
		String improv=null;
		if (cgtResult != null && (cgtResult.getSsFactor() != null && !cgtResult.getSsFactor().trim().isEmpty()))
		{
		
		String ssFactorStr=cgtResult.getSsFactor();
		String PersonalityCode=cgtResult.getPersonalityCode();
		
		
		if (ssFactorStr != null && !ssFactorStr.trim().isEmpty())
		{
			try
			{
				
				
				for (ShortListDTO occupationDTO : shortListDTO)
				{
				
				
			//	System.out.println("========================>"+occupationDTO.getRequiredAbilityCount());
				
					if (occupationDTO.getRequiredAbilityCount() > 0)
					{
						String[] requiredAbilites = trimWhiteSpaceBWComma(occupationDTO.getRequiredAbility()).split(",");
						
						JSONObject ssFactorJSON = new JSONObject(ssFactorStr);
						
						int HighFitmentCount=0;
						
						
						
						for (String ability : requiredAbilites)
						{
							String ObtainedScore= ssFactorJSON.getString(ability).toString().trim();
							
							
							
							//System.out.println("==========ObtainedScore==============>"+ObtainedScore);
							
							
							//System.out.println("==========HighFitmentCount==============>"+HighFitmentCount);
							if(ObtainedScore.equals("H"))
							{
								HighFitmentCount++;
							}
							else
							{
								if(improv==null)
								{
									improv=ability;
								}
								else
								{
									improv=improv+", "+ability;
								}
							}

							
							//System.out.println("==========HighFitmentCount==============>"+HighFitmentCount);
						}
						
						//System.out.println("==========requiredAbilites==============>"+Arrays.toString(requiredAbilites));
						//System.out.println("==========ssFactorStr==============>"+ssFactorStr);
						//System.out.println("=========HighFitmentCount===============>"+HighFitmentCount);
						//System.out.println("==========occupationDTO.getRequiredAbilityCount()==============>"+occupationDTO.getRequiredAbilityCount());
						
						if(HighFitmentCount==occupationDTO.getRequiredAbilityCount())
						{
							colorFitment=4;
						}

						OUT.debug("colorFitment==============>"+colorFitment);
						
						
					}
					
					occupationDTO.setFitmentforstudent(colorFitment);
					occupationDTO.setImproveability(improv);
					improv=null;
					colorFitment=1;
					
					shortListDTO1.add(occupationDTO);
					
					
				}

				// occupations
				
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		
		
		}
		return shortListDTO1;
		
		
		
	}
	
	
	
	public List<EduPathShortListDTO> CalculatefitmentforEdupathShortList(List<EduPathShortListDTO> EduPathshortListDTO,int StudentID)
	{
		OUT.debug("Getting CalculatefitmentforEdupathShortList:{}", StudentID);
		StudentCGTResult cgtResult=null;
		try {
			cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,
					StudentID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<EduPathShortListDTO> EduPathshortListDTO1= new ArrayList<EduPathShortListDTO>();
		if (cgtResult != null && (cgtResult.getSsFactor() != null && !cgtResult.getSsFactor().trim().isEmpty()))
		{
		
		String ssFactorStr=cgtResult.getSsFactor();
		String PersonalityCode=cgtResult.getPersonalityCode();
		
		
		if (ssFactorStr != null && !ssFactorStr.trim().isEmpty())
		{
			try
			{
				
				
				for (EduPathShortListDTO occupationDTO : EduPathshortListDTO)
				{
				
				
			//	System.out.println("========================>"+occupationDTO.getRequiredAbilityCount());
				
					if (occupationDTO.getRequiredAbilityCount() > 0)
					{
						String[] requiredAbilites = trimWhiteSpaceBWComma(occupationDTO.getRequiredAbility()).split(",");

						JSONObject ssFactorJSON = new JSONObject(ssFactorStr);
						Map<String, Integer> fitmentCount = new LinkedHashMap<String, Integer>();
						fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.M.getProperty(), 0);
						fitmentCount.put(ApplicationConstants.SSFactor.H.getProperty(), 0);

						for (String ability : requiredAbilites)
						{
							if (ssFactorJSON.has(ability))
							{
								fitmentCount.put(ssFactorJSON.getString(ability), fitmentCount.get(ssFactorJSON.getString(ability)) + 1);
							}
							else
							{
								fitmentCount.put(ApplicationConstants.SSFactor.L.getProperty(), fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty()) + 1);
							}
						}

						OUT.debug("Fitment SSFactor : {}, requiredAbilities: {}, fitMentCount: {}", ssFactorJSON, requiredAbilites, fitmentCount);
						int fitment = FitMent.LOW.getScore();
						int highCount = fitmentCount.get(ApplicationConstants.SSFactor.H.getProperty());
						int mediumCount = fitmentCount.get(ApplicationConstants.SSFactor.M.getProperty());
						// int lowCount = fitmentCount.get(ApplicationConstants.SSFactor.L.getProperty());
						switch (occupationDTO.getRequiredAbilityCount())
						{
							case 5:
								if (highCount >= 4)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 3 || (highCount >= 2 && mediumCount >= 3))
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if ((highCount >= 2) || (mediumCount >= 4) || (highCount >= 1 && mediumCount >= 2))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 4:
								if (highCount >= 3)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 2 && mediumCount >= 1)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (highCount >= 2 || mediumCount >= 3 || (highCount >= 1 && mediumCount >= 1))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 3:
								if (highCount >= 3)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 2)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (highCount >= 1 || mediumCount >= 2 || (highCount >= 1 && mediumCount >= 1))
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 2:
								if (highCount >= 2)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (highCount >= 1 && mediumCount >= 1)
								{
									fitment = FitMent.A_AVG.getScore();
								}
								else if (mediumCount >= 2 || highCount == 1)
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							case 1:
								if (highCount == 1)
								{
									fitment = FitMent.HIG.getScore();
								}
								else if (mediumCount == 1)
								{
									fitment = FitMent.AVG.getScore();
								}
								break;
							default:
								break;
						}
						occupationDTO.setFitment(fitment);
						
						//BEGIN Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 22-02-2017
						NewScoringLogicForTrafficLight NewScoringLogicForTrafficLightObj=new NewScoringLogicForTrafficLight();
						occupationDTO=NewScoringLogicForTrafficLightObj.GetNewTrafficLightForEdupathShortList(occupationDTO,PersonalityCode);
						
						//END Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 22-02-2017
						OUT.debug("Fitment: {} for occupationId: {}", fitment, occupationDTO.getId());
						EduPathshortListDTO1.add(occupationDTO);
						
					}
					
					
					
				}

				// occupations
				
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		
		
		}
		return EduPathshortListDTO1;
		
		
		
		
	}
	
	
	
	
}
