package com.lodestar.edupath.dataaccessobject.dao.college;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.college.BoardDTO;


public class BoardDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(BoardDAO.class);

	public List<BoardDTO> getAllBoard() throws Exception
	{
		List<BoardDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.BOARD_GET_ALL, null);
		OUT.debug("Board  founded :{}", (list != null ? list.size() : 0));
		return list;
	}
}
