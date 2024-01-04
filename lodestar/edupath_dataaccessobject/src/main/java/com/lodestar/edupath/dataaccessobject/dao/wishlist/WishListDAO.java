package com.lodestar.edupath.dataaccessobject.dao.wishlist;

import java.util.ArrayList;
import java.util.Collections;
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
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.WishListDTO;
import com.lodestar.edupath.datatransferobject.dto.summaryreport.OccupationIndustryVO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.FitMent;

public class WishListDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(WishListDAO.class);

	public List<WishListDTO> getStudentWishList(int studentId) throws Exception
	{
		//START Sasedeve  by Mrutyunjaya on date 07-04-2017
		
		StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,
				studentId);
		
		List<WishListDTO> studentWishList=MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_WISHLIST_BY_STUDENTID, studentId);
		
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
		
		//return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_WISHLIST_BY_STUDENTID, studentId);
		return studentWishList1;
		
		//END Sasedeve  by Mrutyunjaya on date 07-04-2017
	}

	public void deleteStudentWishList(int id) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.DELETE_WISHLIST_BY_ID, id);
	}

	public void deleteStudentWishListByOcc(int studentId, int occupationId, Integer occIndustryId) throws Exception
	{
		WishListDTO dto = new WishListDTO();
		dto.setOccupationId(occupationId);
		dto.setStudentId(studentId);
		dto.setOccIndustryId(occIndustryId);
		MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_WISHLIST_BY_OCCID, dto);
	}

	public void insertStudentWishList(WishListDTO wishList) throws Exception
	{
		MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_WISHLIST, wishList);
	}

	
	
	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public OccupationIndustryVO getOccNIndDetailsByStudentId(int studentId) throws Exception
	{
		OUT.debug("Getting occupationIndustry details for studentId : {}", studentId);
		OccupationIndustryVO occupationIndustryVO = (OccupationIndustryVO) MyBatisManager.getInstance().getResultAsObject(
				MyBatisMappingConstants.STUDENT_OCCS_N_INDS_GET_BY_STUDENT_ID, studentId);
		OUT.debug("occupationIndustry details {} for studentId : {}", occupationIndustryVO != null ? " FOUND " : " NOT FOUND ", studentId);
		return occupationIndustryVO;
	}

	//START Sasedeve  by Mrutyunjaya on date 07-04-2017
	
	public OccupationDTO updateFitment(OccupationDTO occupationDTO, String ssFactorStr, String PersonalityCode)
	{
		if (ssFactorStr != null && !ssFactorStr.trim().isEmpty())
		{
			try
			{
//				for (OccupationDTO occupationDTO : occupationsWithReqAbility)
//				{
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

	//END Sasedeve  by Mrutyunjaya on date 07-04-2017

}
