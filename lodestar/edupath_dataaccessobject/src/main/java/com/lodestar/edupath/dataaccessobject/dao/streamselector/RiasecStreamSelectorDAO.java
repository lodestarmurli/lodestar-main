package com.lodestar.edupath.dataaccessobject.dao.streamselector;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.RiasecStreamSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorDescriptionDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorResultDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorStudentDTO;
 


public class RiasecStreamSelectorDAO {
	private static final Logger	OUT	= LoggerFactory.getLogger(RiasecStreamSelectorDAO.class);


	public RiasecStreamSelectorDTO getStreamByRiasecCode(String riasecCode) throws Exception
	{ 
		RiasecStreamSelectorDTO dto = (RiasecStreamSelectorDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STREAM_BY_RIASECCODE, riasecCode);
		return dto;
	}
	
	
	public StreamSelectorOccupationDTO getStreamSelectorOcc(String riasecCode) throws Exception
	{
		StreamSelectorOccupationDTO dto = (StreamSelectorOccupationDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STREAM_OCC_BY_RIASECCODE, riasecCode);
		return dto;
	}
	
	
	public int insertStreamSelectorStudent(SqlSession session,StreamSelectorStudentDTO streamSelectorStudentDTO)
	{
		Integer id = -1;
		id = session.insert(MyBatisMappingConstants.INSERT_STREAM_SELECTOR_STUDENT, streamSelectorStudentDTO);
		return id;
	}
	
	public StreamSelectorResultDTO getStreamSelectorResult(int studentId) throws Exception
	{
		StreamSelectorResultDTO dto = (StreamSelectorResultDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STREAM_SELECTOR_RESULT, studentId);
		return dto;
	}
	
	
	public int insertOrUpdateStreamSelectorResult(SqlSession session,StreamSelectorResultDTO streamSelectorResultDTO)
	{
		Integer id = -1;
		id = session.insert(MyBatisMappingConstants.INSERT_STREAM_SELECTOR_RESULT, streamSelectorResultDTO);
		return id;
	}
	
	public StreamSelectorDescriptionDTO getStreamSelectorDescription(String stream) throws Exception
	{
		StreamSelectorDescriptionDTO dto = (StreamSelectorDescriptionDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STREAM_SELECTOR_DESCRIPTION, stream);
		return dto;
	}
	
}
