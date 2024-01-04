package com.lodestar.edupath.datatransferobject.dto;

import java.util.Date;

public class ReportReviewCommentsDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					facilitatorId;
	private String				review;
	private Date				lastUpdatedDate;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public int getFacilitatorId()
	{
		return facilitatorId;
	}

	public void setFacilitatorId(int facilitatorId)
	{
		this.facilitatorId = facilitatorId;
	}

	public String getReview()
	{
		return review;
	}

	public void setReview(String review)
	{
		this.review = review;
	}

	public Date getLastUpdatedDate()
	{
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate)
	{
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Override
	public String toString()
	{
		return "ReportReviewCommentsDTO [id=" + id + ", studentId=" + studentId + ", facilitatorId=" + facilitatorId + ", review=" + review + ", lastUpdatedDate="
				+ lastUpdatedDate + "]";
	}

}
