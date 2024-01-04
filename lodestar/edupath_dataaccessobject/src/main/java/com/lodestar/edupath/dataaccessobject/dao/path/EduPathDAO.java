package com.lodestar.edupath.dataaccessobject.dao.path;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.elective.StreamsDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EdupathCountDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathPUStreamsDTO;

public class EduPathDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(EduPathDAO.class);

	public EduPathDTO getEduPath(EduPathDTO edudto) throws Exception
	{
		EduPathDTO list = (EduPathDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_EDUPATH_BY_IDS, edudto);
		OUT.debug("EduPath Details founded : ", list);
		return list;
	}

	public List<StreamsDTO> getEdupathStreamByIds(StreamsDTO dto) throws Exception
	{
		List<StreamsDTO> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_EDU_PATH_STREAM, dto);
		OUT.debug("Streams Details founded : ", (list != null ? list.size() : 0));
		return list;
	}

	public List<EdupathCountDTO> getEdupathCountForPUStreamByIndus(int industryId) throws Exception
	{
		OUT.debug("Getting the PU Stream Count for industry:{}", industryId);
		List<EdupathCountDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_EDU_PATH_COUNT_PU_STREAM_BY_INDUS, industryId);
		return list;
	}

	public List<EdupathCountDTO> getEdupathCountForDegreeStreamByIndus(int industryId) throws Exception
	{
		OUT.debug("Getting the Degree Stream Count for industry:{}", industryId);
		List<EdupathCountDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_EDU_PATH_DEGREE_STREAM_BY_INDUS, industryId);
		return list;
	}

	public List<EdupathCountDTO> getEdupathCountForDegreeSpecialByIndus(int industryId) throws Exception
	{
		OUT.debug("Getting the Degree Specialization Count for industry:{}", industryId);
		List<EdupathCountDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_EDU_PATH_DEGREE_SPECIAL_BY_INDUS, industryId);
		return list;
	}
	
	public List<EduPathPUStreamsDTO> getPUStreamsByOccId(int eduPathId) throws Exception
	{
		OUT.debug("Getting the PU Stream for edupath:{}", eduPathId);
		List<EduPathPUStreamsDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_PUSTREAMS_BY_OCCID, eduPathId);
		OUT.debug("PUStreams Details founded : ", (list != null ? list.size() : 0));
		return list;
	}
}
