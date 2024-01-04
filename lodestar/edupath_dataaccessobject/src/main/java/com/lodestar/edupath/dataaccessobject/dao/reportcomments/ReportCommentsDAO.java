package com.lodestar.edupath.dataaccessobject.dao.reportcomments;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.reportcomments.ReportCommentsDTO;

public class ReportCommentsDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ReportCommentsDAO.class);

	/**
	 * @param commentsDTO
	 * @throws Exception
	 */
	public void insertReportComment(SqlSession session, ReportCommentsDTO commentsDTO) throws Exception
	{
		OUT.debug("Inserting report comment for studentId: {}, facilitatorId: {}", commentsDTO.getStudentId(), commentsDTO.getFacilitatorId());
		session.insert(MyBatisMappingConstants.REPORT_COMMENTS_INSERT, commentsDTO);
		OUT.debug("Inserted report comment id: {} for studentId: {}, facilitatorId: {}", commentsDTO.getId(), commentsDTO.getStudentId(),
				commentsDTO.getFacilitatorId());
	}

	/**
	 * @param commentsDTO
	 * @throws Exception
	 */
	public void updateReportComment(SqlSession session, ReportCommentsDTO commentsDTO) throws Exception
	{
		OUT.debug("Updating report comment for studentId: {}, facilitatorId: {}", commentsDTO.getStudentId(), commentsDTO.getFacilitatorId());
		session.insert(MyBatisMappingConstants.REPORT_COMMENTS_UPDATE, commentsDTO);
		OUT.debug("updated report comment id: {} for studentId: {}, facilitatorId: {}", commentsDTO.getId(), commentsDTO.getStudentId(),
				commentsDTO.getFacilitatorId());
	}

	/**
	 * @param commentsDTO
	 * @return
	 * @throws Exception
	 */
	public ReportCommentsDTO getReportComment(ReportCommentsDTO commentsDTO) throws Exception
	{
		OUT.debug("Getting report comment for studentId: {}, facilitatorId: {}", commentsDTO.getStudentId(), commentsDTO.getFacilitatorId());
		ReportCommentsDTO resultAsObject = (ReportCommentsDTO) MyBatisManager.getInstance().getResultAsObject(
				MyBatisMappingConstants.REPORT_COMMENTS_GET_BY_STUD_FACI_ID, commentsDTO);
		OUT.debug("Report comment {} for studentId: {}, facilitatorId: {}", resultAsObject != null ? " FOUND " : " NOT FOUND ", commentsDTO.getStudentId(),
				commentsDTO.getFacilitatorId());
		return resultAsObject;
	}
}
