package com.lodestar.edupath.dataaccessobject.dao.tags;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.tags.AreaDTO;

public class AreaDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(AreaDAO.class);

	public List<AreaDTO> getAreaByInterests() throws Exception
	{
		List<AreaDTO> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_AREA_DETAIL_BY_INTERESTS, null);
		OUT.debug("Area founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<AreaDTO> getListByCategoryName(String categoryName) throws Exception
	{
		List<AreaDTO> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryName", categoryName);
		list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_RESULT_BY_CATEGORY_NAME, param);
		return list;
	}
}
