package com.lodestar.edupath.APIS;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.ReportReviewCommentsDTO;
import com.lodestar.edupath.datatransferobject.dto.reportcomments.ReportCommentsDTO;
import com.lodestar.edupath.finalsummary.FinalSummaryAction;
import com.lodestar.edupath.finalsummary.service.FinalSummaryService;
import com.lodestar.edupath.review.service.ReviewService;

public class AutoSaveComments extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(AutoSaveComments.class);
	private String acomments;
	private String achallenges;
	private String aapproachtopathway1;
	private String aapproachtopathway2;
	private String afinalnotes;
	private String asupplementaryinformation;
	private String avcomments;
	
	public String getAvcomments() {
		return avcomments;
	}

	public void setAvcomments(String avcomments) {
		this.avcomments = avcomments;
	}

	public String getAcomments() {
		return acomments;
	}

	public void setAcomments(String acomments) {
		this.acomments = acomments;
	}

	public String getAchallenges() {
		return achallenges;
	}

	public void setAchallenges(String achallenges) {
		this.achallenges = achallenges;
	}

	public String getAapproachtopathway1() {
		return aapproachtopathway1;
	}

	public void setAapproachtopathway1(String aapproachtopathway1) {
		this.aapproachtopathway1 = aapproachtopathway1;
	}

	public String getAapproachtopathway2() {
		return aapproachtopathway2;
	}
	public void setAapproachtopathway2(String aapproachtopathway2) {
		this.aapproachtopathway2 = aapproachtopathway2;
	}
	public String getAfinalnotes() {
		return afinalnotes;
	}
	public void setAfinalnotes(String afinalnotes) {
		this.afinalnotes = afinalnotes;
	}

	public String getAsupplementaryinformation() {
		return asupplementaryinformation;
	}

	public void setAsupplementaryinformation(String asupplementaryinformation) {
		this.asupplementaryinformation = asupplementaryinformation;
	}

	public String AutoSaveGSComments()
	{
		OUT.debug("Inside AutoSaveComments class : AutoSaveGSComments method");
		
		try
		{
			
				StudentSessionObject studentObject = getStudentSessionObject();
				UserSessionObject userObject = getUserSessionObject();
				int studentId = 0;
				int userId = 0;
				studentId = studentObject.getId();
				userId = userObject.getId();
				ReportCommentsDTO commentsDTO=new ReportCommentsDTO();
				
				commentsDTO.setStudentId(studentId);
				
				if(acomments!=null && !acomments.trim().equals(""))
				{
					commentsDTO.setComments(acomments);
				}
				
				if(achallenges!=null && !achallenges.trim().equals(""))
				{
					commentsDTO.setChallenges(achallenges);
				}
				
				if(aapproachtopathway1!=null && !aapproachtopathway1.trim().equals(""))
				{
					commentsDTO.setApproachtopathway1(aapproachtopathway1);
				}
				
				if(aapproachtopathway2!=null && !aapproachtopathway2.trim().equals(""))
				{
					commentsDTO.setApproachtopathway2(aapproachtopathway2);
				}
				
				if(afinalnotes!=null && !afinalnotes.trim().equals(""))
				{
					commentsDTO.setFinalnotes(afinalnotes);
				}
				
				if(asupplementaryinformation!=null && !asupplementaryinformation.trim().equals(""))
				{
					commentsDTO.setSupplementaryinformation(asupplementaryinformation);
				}
				
				new FinalSummaryService().AutosaveComment_GS(commentsDTO, userId);
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		return null;
	}
	
	
	public String AutoSaveReviewerComments()
	{
		OUT.debug("Inside AutoSaveComments class : AutoSaveReviewerComments method");
		
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
			
			if(avcomments!=null && !avcomments.trim().equals(""))
			{
				reportReviewCommentsDTO.setReview(avcomments);
			}
			
			reportReviewCommentsDTO.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
			reviewService.AutoSaveReportReviewComments(reportReviewCommentsDTO);
			
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		return null;
	}
	
	
	
}
