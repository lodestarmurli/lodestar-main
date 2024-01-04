package com.lodestar.edupath.dataaccessobject.dao.city;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;

public class CityDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(CityDAO.class);

	public List<CityDTO> getAllCityList() throws Exception
	{
		List<CityDTO> cityList = new ArrayList<CityDTO>();
		cityList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_CITY_NAME_WITH_ID, new CityDTO());
		OUT.debug("City details : {}", cityList != null ? cityList.size() : 0);
		return cityList;
	}
}
