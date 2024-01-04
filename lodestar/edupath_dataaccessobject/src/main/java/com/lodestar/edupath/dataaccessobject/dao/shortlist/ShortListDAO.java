package com.lodestar.edupath.dataaccessobject.dao.shortlist;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;

public class ShortListDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ShortListDAO.class);

	public List<ShortListDTO> getShortListDetailsByStudentId(ShortListDTO dto) throws Exception
	{
		List<ShortListDTO> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_TOP_TWO_SHORTLIST_BY_STUDENTID, dto);
		OUT.debug("Short List result: {}", (list != null ? list.size() : 0));
		return list;
	}
}
