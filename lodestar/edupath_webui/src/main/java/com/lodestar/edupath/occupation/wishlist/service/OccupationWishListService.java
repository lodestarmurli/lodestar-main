package com.lodestar.edupath.occupation.wishlist.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.stuinduocchoice.MyWishListDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.WishListDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AlertsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class OccupationWishListService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(OccupationWishListService.class);
	
	
	
	
	
	public  Map<String, Object> getMyWishList(int userId) 
	{

		Map<String, List<OccupationDTO>> indusOccupMap = new HashMap<String, List<OccupationDTO>>();
        Map<String, Object> finalResultMap = new HashMap<String, Object>();
		try
		{
			List<OccupationDTO> list;
			String key;
			List<Integer> occupationId = new ArrayList<Integer>();
			List<WishListDTO> userMyWishList = new MyWishListDAO().getUserMyWishList(userId);
			 Map<Long, List<AlertsDTO>> alertMap = new HashMap<Long, List<AlertsDTO>>();
			for (WishListDTO wishListDTO : userMyWishList)
			{
				key =  wishListDTO.getOccupation().getIndustryName();
				list = indusOccupMap.get(key);
				if (list == null)
				{
					list = new ArrayList<OccupationDTO>();
				}

				
				
				
				
				list.add(wishListDTO.getOccupation());
				occupationId.add(wishListDTO.getOccupation().getId());
				indusOccupMap.put(key, list);
			}
			
			if(!occupationId.isEmpty())
			{
				alertMap = getAlertsByOccupation(occupationId);
			}
			
			finalResultMap.put("OCCUPATION", indusOccupMap);
			finalResultMap.put("alerts", alertMap);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		return finalResultMap;
	}
	
	public Map<Long, List<AlertsDTO>> getAlertsByOccupation(List<Integer> occupationIds) throws Exception
	{
		List<AlertsDTO> alerts = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_ALERTS, occupationIds);
		Map<Long, List<AlertsDTO>> occupationAlerts = new HashMap<Long, List<AlertsDTO>>();
		List<AlertsDTO> list;
		for (AlertsDTO alertsDTO : alerts)
		{
			if (occupationAlerts.containsKey(alertsDTO.getOccupationId()))
			{
				list = occupationAlerts.get(alertsDTO.getOccupationId());
			}
			else
			{
				list = new ArrayList<>();
			}

			list.add(alertsDTO);
			occupationAlerts.put((long)alertsDTO.getOccupationId(), list);
		}
		return occupationAlerts;
	}
	
	public static void main(String[] args)
	{
		new OccupationWishListService().getMyWishList(1) ;
	}
}
