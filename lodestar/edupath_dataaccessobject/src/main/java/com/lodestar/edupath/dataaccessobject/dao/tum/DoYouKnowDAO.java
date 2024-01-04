package com.lodestar.edupath.dataaccessobject.dao.tum;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.DoYouKnowDTO;

public class DoYouKnowDAO
{
	private static final Logger	OUT = LoggerFactory.getLogger(DoYouKnowDAO.class);
	
	public List<DoYouKnowDTO> getDoYouKnowDetails() throws Exception
	{
		List<DoYouKnowDTO> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_DOYOUKNOW_DETAIL, null);
		OUT.debug("DoYouKnow founded :{}" , (list != null ? list.size():0));
		return list;
	}
}
