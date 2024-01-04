package com.lodestar.edupath.dataaccessobject.dao.stream;

import java.util.List;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.elective.StreamsDTO;

public class StreamDAO
{
	public List<StreamsDTO> getAllStreams() throws Exception
	{
		List<StreamsDTO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_STREAMS, null);
		return resultList;
	}
}
