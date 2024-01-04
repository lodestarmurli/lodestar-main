package com.lodestar.edupath.dataaccessobject.dao.globalsetting;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;

public class GlobalSttingDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(GlobalSttingDAO.class);

	public GlobalSettingDTO getPropertesValueByName(GlobalSettingDTO global) throws Exception
	{
		GlobalSettingDTO globalDTO = (GlobalSettingDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_GLOBAL_SETTINGS_BY_PROPERTY,
				global);
		return globalDTO;
	}

	public List<GlobalSettingDTO> getAllGlobalSetting() throws Exception
	{
		List<GlobalSettingDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_GLOBAL_SETTINGS, null);
		OUT.debug("Global Setting Size : {}", (list != null ? list.size() : 0));
		return list;
	}

	/**
	 * @param globalValueKey
	 * @return
	 * @throws Exception
	 */
	public String getPropertesValueByName(String globalValueKey) throws Exception
	{
		OUT.debug("Get Global Setting Value with propertyName : {}", globalValueKey);
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(globalValueKey);
		globalDTO = (GlobalSettingDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_GLOBAL_SETTINGS_BY_PROPERTY, globalDTO);
		OUT.debug("Get Global Setting Value : {}", globalDTO.getPropertyValue());
		return globalDTO.getPropertyValue();
	}
}
