package com.lodestar.edupath.dataaccessobject.dao.stuinduocchoice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.newscoringlogic.NewScoringLogicForTrafficLight;
import com.lodestar.edupath.dataaccessobject.dao.wishlist.WishListDAO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.WishListDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.FitMent;

public class MyWishListDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(WishListDAO.class);
	public List<WishListDTO> getUserMyWishList(int userId) throws Exception
	{
		
		//START Sasedeve  by Mrutyunjaya on date 26-04-2017
		StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT_USERID,
				userId);
		
		List<WishListDTO> studentWishList=MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_WISHLIST_BY_USERID, userId);
		
		List<WishListDTO> studentWishList1=new ArrayList<WishListDTO>();
		
		if (cgtResult != null && (cgtResult.getSsFactor() != null && !cgtResult.getSsFactor().trim().isEmpty()))
		{
			for (WishListDTO wishListDTO : studentWishList)
			{
				
				
				OccupationDTO temp= updateFitment(wishListDTO.getOccupation(), cgtResult.getSsFactor(),cgtResult.getPersonalityCode());
				wishListDTO.getOccupation().setFitment(temp.getFitment());
				wishListDTO.getOccupation().setNewfitment(temp.getNewfitment());
				studentWishList1.add(wishListDTO);
			}
		  
		}
		
		
		
		return studentWishList1;
		
		
		
		//return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_WISHLIST_BY_USERID, userId);
		
		//END Sasedeve  by Mrutyunjaya on date 26-04-2017
	}
	
	
	
	//START Sasedeve  by Mrutyunjaya on date 26-04-2017
	
		public OccupationDTO updateFitment(OccupationDTO occupationDTO, String ssFactorStr, String PersonalityCode)
		{
			if (ssFactorStr != null && !ssFactorStr.trim().isEmpty())
			{
				try
				{
//					for (OccupationDTO occupationDTO : occupationsWithReqAbility)
//					{
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
							
							//BEGIN Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 26-04-2017
							NewScoringLogicForTrafficLight NewScoringLogicForTrafficLightObj=new NewScoringLogicForTrafficLight();
							occupationDTO=NewScoringLogicForTrafficLightObj.GetNewTrafficLight(occupationDTO,PersonalityCode);
							
							//END Sasedeve Call New Method For NewTrafficLight by Mrutyunjaya on date 26-04-2017
							OUT.debug("Fitment: {} for occupationId: {}", fitment, occupationDTO.getId());
						}
				//	}

					// occupations
					
				}
				catch (Exception e)
				{
					OUT.error(ApplicationConstants.EXCEPTION, e);
				}
			}
			
			return occupationDTO;
			
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

		//END Sasedeve  by Mrutyunjaya on date 26-04-2017
	
	
	
	
	
}
