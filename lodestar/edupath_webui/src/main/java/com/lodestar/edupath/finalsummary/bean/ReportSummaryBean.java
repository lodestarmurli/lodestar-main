package com.lodestar.edupath.finalsummary.bean;

import java.io.Serializable;
import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.IntegratedCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCollegeShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTutorialCentreShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.SubjectDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.SubjectStreamCombinationVO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.studentparameter.StudentCollegeParametersDTO;
import com.lodestar.edupath.datatransferobject.dto.summaryreport.OccupationIndustryVO;

public class ReportSummaryBean implements Serializable
{
	private static final long						serialVersionUID	= 1L;

	private StudentDetailsDTO						section1;
	private OccupationIndustryVO					section2OccNInd;
	private List<SubjectDTO>						section2CourseRead;
	private StudentCollegeParametersDTO				selectedParam;

	private List<EduPathShortListDTO>				section3Edupath;
	private OccupationIndustryVO					section3OccNInd;
	private SubjectStreamCombinationVO				section3StreamNElective;
	private List<EntranceExamsDTO>					section3Exams;
	private List<IntegratedCourseDTO>				section3Courses;
	private List<StudentTutorialCentreShortListDTO>	section3Tutorials;
	private List<StudentCollegeShortListDTO>		section3Colleges;

	private String									comments;
	private int										studentId;
	private int										userId;
	private String									reviewComment;
	private Boolean									isReviewer;
	private Boolean									isSentForReview;
	private Boolean									isStudent;
	private Boolean									isReportGenerated	= true;
	
	
	//Start SASEDEVE Edited By Mrutyunjaya On date 06-06-2017
	
	
	private String				challenges;
	private String				approachtopathway1;
	private String				approachtopathway2;
	private String				finalnotes;
	private String				supplementaryinformation;

	
	
	
	
	private String				reviewchallenges;
	private String				reviewapproachtopathway1;
	private String				reviewapproachtopathway2;
	private String				reviewfinalnotes;
	private String				reviewsupplementaryinformation;
	
	
	
	public String getReviewchallenges() {
		return reviewchallenges;
	}

	public void setReviewchallenges(String reviewchallenges) {
		this.reviewchallenges = reviewchallenges;
	}

	public String getReviewapproachtopathway1() {
		return reviewapproachtopathway1;
	}

	public void setReviewapproachtopathway1(String reviewapproachtopathway1) {
		this.reviewapproachtopathway1 = reviewapproachtopathway1;
	}

	public String getReviewapproachtopathway2() {
		return reviewapproachtopathway2;
	}

	public void setReviewapproachtopathway2(String reviewapproachtopathway2) {
		this.reviewapproachtopathway2 = reviewapproachtopathway2;
	}

	public String getReviewfinalnotes() {
		return reviewfinalnotes;
	}

	public void setReviewfinalnotes(String reviewfinalnotes) {
		this.reviewfinalnotes = reviewfinalnotes;
	}

	public String getReviewsupplementaryinformation() {
		return reviewsupplementaryinformation;
	}

	public void setReviewsupplementaryinformation(String reviewsupplementaryinformation) {
		this.reviewsupplementaryinformation = reviewsupplementaryinformation;
	}

	
	
	
	
	

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

	
	
	//END SASEDEVE Edited By Mrutyunjaya On date 06-06-2017
	
	
	public StudentDetailsDTO getSection1()
	{
		return section1;
	}

	public void setSection1(StudentDetailsDTO section1)
	{
		this.section1 = section1;
	}

	public OccupationIndustryVO getSection2OccNInd()
	{
		return section2OccNInd;
	}

	public void setSection2OccNInd(OccupationIndustryVO section2OccNInd)
	{
		this.section2OccNInd = section2OccNInd;
	}

	public List<SubjectDTO> getSection2CourseRead()
	{
		return section2CourseRead;
	}

	public void setSection2CourseRead(List<SubjectDTO> section2CourseRead)
	{
		this.section2CourseRead = section2CourseRead;
	}

	public StudentCollegeParametersDTO getSelectedParam()
	{
		return selectedParam;
	}

	public void setSelectedParam(StudentCollegeParametersDTO selectedParam)
	{
		this.selectedParam = selectedParam;
	}

	public OccupationIndustryVO getSection3OccNInd()
	{
		return section3OccNInd;
	}

	public void setSection3OccNInd(OccupationIndustryVO section3OccNInd)
	{
		this.section3OccNInd = section3OccNInd;
	}

	public SubjectStreamCombinationVO getSection3StreamNElective()
	{
		return section3StreamNElective;
	}

	public void setSection3StreamNElective(SubjectStreamCombinationVO section3StreamNElective)
	{
		this.section3StreamNElective = section3StreamNElective;
	}

	public List<EntranceExamsDTO> getSection3Exams()
	{
		return section3Exams;
	}

	public void setSection3Exams(List<EntranceExamsDTO> section3Exams)
	{
		this.section3Exams = section3Exams;
	}

	public List<StudentTutorialCentreShortListDTO> getSection3Tutorials()
	{
		return section3Tutorials;
	}

	public void setSection3Tutorials(List<StudentTutorialCentreShortListDTO> section3Tutorials)
	{
		this.section3Tutorials = section3Tutorials;
	}

	public List<StudentCollegeShortListDTO> getSection3Colleges()
	{
		return section3Colleges;
	}

	public void setSection3Colleges(List<StudentCollegeShortListDTO> section3Colleges)
	{
		this.section3Colleges = section3Colleges;
	}

	public List<EduPathShortListDTO> getSection3Edupath()
	{
		return section3Edupath;
	}

	public void setSection3Edupath(List<EduPathShortListDTO> section3Edupath)
	{
		this.section3Edupath = section3Edupath;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public String getReviewComment()
	{
		return reviewComment;
	}

	public void setReviewComment(String reviewComment)
	{
		this.reviewComment = reviewComment;
	}

	public Boolean getIsReviewer()
	{
		return isReviewer;
	}

	public void setIsReviewer(Boolean isReviewer)
	{
		this.isReviewer = isReviewer;
	}

	public Boolean getIsSentForReview()
	{
		return isSentForReview;
	}

	public void setIsSentForReview(Boolean isSentForReview)
	{
		this.isSentForReview = isSentForReview;
	}

	public Boolean getIsStudent()
	{
		return isStudent;
	}

	public void setIsStudent(Boolean isStudent)
	{
		this.isStudent = isStudent;
	}

	public Boolean getIsReportGenerated()
	{
		return isReportGenerated;
	}

	public void setIsReportGenerated(Boolean isReportGenerated)
	{
		this.isReportGenerated = isReportGenerated;
	}

	public List<IntegratedCourseDTO> getSection3Courses()
	{
		return section3Courses;
	}

	public void setSection3Courses(List<IntegratedCourseDTO> section3Courses)
	{
		this.section3Courses = section3Courses;
	}

	@Override
	public String toString() {
		return "ReportSummaryBean [section1=" + section1 + ", section2OccNInd=" + section2OccNInd
				+ ", section2CourseRead=" + section2CourseRead + ", selectedParam=" + selectedParam
				+ ", section3Edupath=" + section3Edupath + ", section3OccNInd=" + section3OccNInd
				+ ", section3StreamNElective=" + section3StreamNElective + ", section3Exams=" + section3Exams
				+ ", section3Courses=" + section3Courses + ", section3Tutorials=" + section3Tutorials
				+ ", section3Colleges=" + section3Colleges + ", comments=" + comments + ", studentId=" + studentId
				+ ", userId=" + userId + ", reviewComment=" + reviewComment + ", isReviewer=" + isReviewer
				+ ", isSentForReview=" + isSentForReview + ", isStudent=" + isStudent + ", isReportGenerated="
				+ isReportGenerated + ", challenges=" + challenges + ", approachtopathway1=" + approachtopathway1
				+ ", approachtopathway2=" + approachtopathway2 + ", finalnotes=" + finalnotes
				+ ", supplementaryinformation=" + supplementaryinformation + ", reviewchallenges=" + reviewchallenges
				+ ", reviewapproachtopathway1=" + reviewapproachtopathway1 + ", reviewapproachtopathway2="
				+ reviewapproachtopathway2 + ", reviewfinalnotes=" + reviewfinalnotes
				+ ", reviewsupplementaryinformation=" + reviewsupplementaryinformation + "]";
	}
	
}
