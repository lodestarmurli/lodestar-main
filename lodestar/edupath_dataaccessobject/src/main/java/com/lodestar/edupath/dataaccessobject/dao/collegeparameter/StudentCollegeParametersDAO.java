package com.lodestar.edupath.dataaccessobject.dao.collegeparameter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.dto.collegeparameter.BoardList;
import com.lodestar.edupath.datatransferobject.dto.collegeparameter.BoardListStudentID;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.studentparameter.StudentCollegeParametersDTO;

public class StudentCollegeParametersDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentCollegeParametersDAO.class);

	public List<StudentCollegeParametersDTO> getStudentCollegeParametersList() throws Exception
	{
		List<StudentCollegeParametersDTO> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_STUDENT_PARAMETERS, null);
		OUT.debug("Student Parameters size : {}", (list != null ? list.size() : 0));
		return list;
	}

	public StudentCollegeParametersDTO getStudentCollegeParametersByStudentId(int studentId) throws Exception
	{
		OUT.debug("Student Parameters id : {}", studentId);
		//===Start Sasedeve edited by Mrutyunjaya on date 06-05-2017
		StudentDetailsDTO studentDetailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_DETAIL_BY_STUDENTID, studentId);
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
		
		BoardListStudentID boardlistandstudentid=new BoardListStudentID();
		
		boardlistandstudentid.setStudentID(studentId);
		boardlistandstudentid.setBoardId(boardId);
		
		//===END Sasedeve edited by Mrutyunjaya on date 06-05-2017
		StudentCollegeParametersDTO list = (StudentCollegeParametersDTO) MyBatisManager.getInstance().getResultAsObject(
				MyBatisMappingConstants.GET_STUDENT_PARAMETERS_BY_STUDENTID, boardlistandstudentid);
		return list;
	}

	public int insertStudentCollegeParameters(StudentCollegeParametersDTO dto) throws Exception
	{
		MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_STUDENT_PARAMETERS, dto);
		OUT.debug("Student Parameters insert id : {}", dto.getId());
		return dto.getId();
	}

	public int updateStudentCollegeParameters(StudentCollegeParametersDTO dto) throws Exception
	{
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_STUDENT_PARAMETERS_BY_STUDENTID, dto);
		OUT.debug("Student Parameters update id : {}", dto.getId());
		return dto.getId();
	}

}
