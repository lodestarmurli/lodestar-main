package com.lodestar.edupath.dataaccessobject.dao.partner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.partner.PartnerDeatilsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;


public class PartnerDeatilsDAO {
	private static final Logger	OUT	= LoggerFactory.getLogger(PartnerDeatilsDAO.class);
	public static PartnerDeatilsDTO getParnterDetails(String APIkey)
	{
		OUT.debug("APIkey:{}",APIkey);
		PartnerDeatilsDTO dto = null;
				try
				{
					dto = (PartnerDeatilsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_PARTNER_DETAILS, APIkey);
				}
				catch (Exception e)
				{
					OUT.error(ApplicationConstants.EXCEPTION, e);
				}
				return dto;	
	}
	public static PartnerDeatilsDTO getParnterDetailsByID(int id)
	{
//		OUT.debug("APIkey:{}",APIkey);
		PartnerDeatilsDTO dto = null;
				try
				{
					dto = (PartnerDeatilsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_PARTNER_DETAILS_BY_ID, id);
				}
				catch (Exception e)
				{
					OUT.error(ApplicationConstants.EXCEPTION, e);
				}
				return dto;	
	}
}
