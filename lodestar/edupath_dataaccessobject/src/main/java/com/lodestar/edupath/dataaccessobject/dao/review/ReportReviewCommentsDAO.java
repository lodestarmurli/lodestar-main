package com.lodestar.edupath.dataaccessobject.dao.review;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.ReportReviewCommentsDTO;

public class ReportReviewCommentsDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ReportReviewCommentsDAO.class);

	public void insert(SqlSession session, ReportReviewCommentsDTO reportReviewCommentsDTO)
	{
		OUT.debug("Inserting report review comments for studentId {} by facilitatorId {}", reportReviewCommentsDTO.getStudentId(),
				reportReviewCommentsDTO.getFacilitatorId());
		session.insert(MyBatisMappingConstants.REPORT_REVIEW_COMMENTS_INSERT, reportReviewCommentsDTO);
	}

	public ReportReviewCommentsDTO getCommentByStudentId(int studentId) throws Exception
	{
		return (ReportReviewCommentsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.REPORT_REVIEW_COMMENTS_GET, studentId);
	}

	public void update(SqlSession session, ReportReviewCommentsDTO reportReviewCommentsDTO)
	{
		OUT.debug("Updating report review comments for studentId {} by facilitatorId {}", reportReviewCommentsDTO.getStudentId(),
				reportReviewCommentsDTO.getFacilitatorId());
		session.update(MyBatisMappingConstants.REPORT_REVIEW_COMMENTS_UPDATE, reportReviewCommentsDTO);
	}

}
