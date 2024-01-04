package com.lodestar.edupath.dataaccessobject.dao.collegeparameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.dto.collegeparameter.BoardList;
import com.lodestar.edupath.datatransferobject.dto.collegeparameter.CollegeParametersDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;

public class CollegeParametersDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(CollegeParametersDAO.class);


	/**
	 * @return
	 * @throws Exception
	 */
	public List<CollegeParametersDTO> getAllParameters(int studentId, boolean isStudentID) throws Exception
	{
		OUT.debug("Getting all college parameters");
		
	//======Start Sasedeve edited By Mrutyunjaya On Date 17-04-2017
		
		StudentDetailsDTO studentDetailsDTO;
		if(isStudentID)
		{
		 studentDetailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_DETAIL_BY_STUDENTID, studentId);
		}
		else
		{
		 studentDetailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_DETAIL_BY_USERID, studentId);
		}
		
		StudentCityLockDTO studentcityById = new EntranceExamsDAO().getCityIdByStudentId(studentDetailsDTO.getId());
		int cityid=0;
		
		if(null != studentcityById  && !studentcityById.toString().isEmpty())
		{
			cityid=studentcityById.getCityLockId();
		}
		else
		{
			cityid=studentDetailsDTO.getCityId();
		}
		List<BoardList> boardIdList=MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.BoardNotListed, cityid);
		List<BoardList> boardIdListwith=MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.BoardListed, cityid);
//		GET_STUDENT_DETAIL_BY_STUDENTID	
//		
		OUT.debug("Not Listed Board: ", boardIdList);
		ArrayList boardId=new ArrayList();
	//	for (int i=0;i < boardIdList.size();i++)
		for (BoardList BoardListtemp : boardIdList)
		{
			boardId.add(BoardListtemp.getId());
			
			//System.out.println(BoardListtemp.getId());
			
		}
		
		ArrayList boardIdwith=new ArrayList();
		for (BoardList BoardListtemp : boardIdListwith)
		{
			boardIdwith.add(BoardListtemp.getId());
			
			//System.out.println(BoardListtemp.getId());
			
		}
		boardId.removeAll(boardIdwith);
		
		List<CollegeParametersDTO> collegeParametersList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_PARAMETERS_GET, boardId);
		OUT.debug("Number of college parameters found : {}", collegeParametersList != null ? collegeParametersList.size() : 0);
		return collegeParametersList;
		
   //======END Sasedeve edited By Mrutyunjaya On Date 17-04-2017
		
		
		
		
	}

}
