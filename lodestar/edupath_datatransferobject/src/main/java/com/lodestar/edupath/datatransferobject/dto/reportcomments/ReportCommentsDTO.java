package com.lodestar.edupath.datatransferobject.dto.reportcomments;

import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ReportCommentsDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					facilitatorId;
	private String				comments;
	private Date				commentTime;
	
	
//Start SASEDEVE Edited By Mrutyunjaya on Date 06-06-2017	
	
	
	private String				challenges;
	private String				approachtopathway1;
	private String				approachtopathway2;
	private String				finalnotes;
	private String				supplementaryinformation;
	
	
	
	
	
	
	
	
	
	
	public String getChallenges() {
		return challenges;
	}

	public void setChallenges(String challenges) {
		this.challenges = challenges;
	}

	public String getApproachtopathway1() {
		return approachtopathway1;
	}

	public void setApproachtopathway1(String approachtopathway1) {
		this.approachtopathway1 = approachtopathway1;
	}

	public String getApproachtopathway2() {
		return approachtopathway2;
	}

	public void setApproachtopathway2(String approachtopathway2) {
		this.approachtopathway2 = approachtopathway2;
	}

	public String getFinalnotes() {
		return finalnotes;
	}

	public void setFinalnotes(String finalnotes) {
		this.finalnotes = finalnotes;
	}

	public String getSupplementaryinformation() {
		return supplementaryinformation;
	}

	public void setSupplementaryinformation(String supplementaryinformation) {
		this.supplementaryinformation = supplementaryinformation;
	}

	//END SASEDEVE Edited By Mrutyunjaya on Date 06-06-2017	
	
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

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public Date getCommentTime()
	{
		return commentTime;
	}

	public void setCommentTime(Date commentTime)
	{
		this.commentTime = commentTime;
	}

	@Override
	public String toString() {
		return "ReportCommentsDTO [id=" + id + ", studentId=" + studentId + ", facilitatorId=" + facilitatorId
				+ ", comments=" + comments + ", commentTime=" + commentTime + ", challenges=" + challenges
				+ ", approachtopathway1=" + approachtopathway1 + ", approachtopathway2=" + approachtopathway2
				+ ", finalnotes=" + finalnotes + ", supplementaryinformation=" + supplementaryinformation + "]";
	}

}
