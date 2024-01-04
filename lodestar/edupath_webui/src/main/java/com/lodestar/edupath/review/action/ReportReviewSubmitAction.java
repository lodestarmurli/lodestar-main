package com.lodestar.edupath.review.action;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.PDFReport.service.StudentFinalReportEmailService;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.ReportReviewCommentsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.review.service.ReviewService;

public class ReportReviewSubmitAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(ReportReviewSubmitAction.class);

	private String				review;

	public String insert()
	{
		OUT.debug("ReportReviewSubmitAction : inside insert method");
		try
		{
			ReviewService reviewService = new ReviewService();
			ReportReviewCommentsDTO reportReviewCommentsDTO = new ReportReviewCommentsDTO();
			FacilitatorDetailsDTO facilitatorDetailsDTO = reviewService.getFacilitatorDetailsByUserId(getUserSessionObject().getId());
			if (null != facilitatorDetailsDTO)
			{
				reportReviewCommentsDTO.setFacilitatorId(facilitatorDetailsDTO.getId());
			}
			reportReviewCommentsDTO.setStudentId(getStudentSessionObject().getId());
			reportReviewCommentsDTO.setReview(review);
			reportReviewCommentsDTO.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
			reviewService.insertReportReviewComments(reportReviewCommentsDTO, getUserSessionObject().getLoginId(), getStudentSessionObject());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}
	
	public String finalizeReport()
	{
		OUT.debug("ReportReviewSubmitAction : inside finalize method");
		try
		{
			ReviewService reviewService = new ReviewService();
			ReportReviewCommentsDTO reportReviewCommentsDTO = new ReportReviewCommentsDTO();
			reportReviewCommentsDTO.setStudentId(getStudentSessionObject().getId());
			reviewService.finalizeReportReviewComments(reportReviewCommentsDTO, getUserSessionObject().getLoginId(), getStudentSessionObject());
			new StudentFinalReportEmailService().CreatePDFFromReviewer(getStudentSessionObject().getUserId());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public String getReview()
	{
		return review;
	}

	public void setReview(String review)
	{
		this.review = review;
	}

}
