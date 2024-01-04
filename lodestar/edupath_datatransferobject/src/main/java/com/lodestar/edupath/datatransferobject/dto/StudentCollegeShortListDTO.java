package com.lodestar.edupath.datatransferobject.dto;

public class StudentCollegeShortListDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					collegeId;

	// non-table
	private String				collegeName;
	private String 				collegeAddress;
	
	//Start SASEDEVE Edited By Mrutyunjaya on Date 03-06-2017
	
	private String          Location;
	private String          Website;
	private String          AgeofInstitute;
	private String          Collegetype;
	private String          ApplicationCost;
	private String          Applicationlastdate;
	private String          Admissionapplicationform;
	private String          StreamAvailable;
	private String          Courseavailable;
	private String          Cateogory;
	private String          Seats;
	private String          Cut;
	private String          Pass;
	private String          Fee;
	private String          TeachingStaff;
	
	
	

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String website) {
		Website = website;
	}

	public String getAgeofInstitute() {
		return AgeofInstitute;
	}

	public void setAgeofInstitute(String ageofInstitute) {
		AgeofInstitute = ageofInstitute;
	}

	public String getCollegetype() {
		return Collegetype;
	}

	public void setCollegetype(String collegetype) {
		Collegetype = collegetype;
	}

	public String getApplicationCost() {
		return ApplicationCost;
	}

	public void setApplicationCost(String applicationCost) {
		ApplicationCost = applicationCost;
	}

	public String getApplicationlastdate() {
		return Applicationlastdate;
	}

	public void setApplicationlastdate(String applicationlastdate) {
		Applicationlastdate = applicationlastdate;
	}

	public String getAdmissionapplicationform() {
		return Admissionapplicationform;
	}

	public void setAdmissionapplicationform(String admissionapplicationform) {
		Admissionapplicationform = admissionapplicationform;
	}

	public String getStreamAvailable() {
		return StreamAvailable;
	}

	public void setStreamAvailable(String streamAvailable) {
		StreamAvailable = streamAvailable;
	}

	public String getCourseavailable() {
		return Courseavailable;
	}

	public void setCourseavailable(String courseavailable) {
		Courseavailable = courseavailable;
	}


	public String getCateogory() {
		return Cateogory;
	}

	public void setCateogory(String cateogory) {
		Cateogory = cateogory;
	}

	public String getSeats() {
		return Seats;
	}

	public void setSeats(String seats) {
		Seats = seats;
	}

	public String getCut() {
		return Cut;
	}

	public void setCut(String cut) {
		Cut = cut;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getTeachingStaff() {
		return TeachingStaff;
	}

	public void setTeachingStaff(String teachingStaff) {
		TeachingStaff = teachingStaff;
	}

	
	//END SASEDEVE Edited By Mrutyunjaya on Date 03-06-2017
	
	
	
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

	public int getCollegeId()
	{
		return collegeId;
	}

	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}

	public String getCollegeName()
	{
		return collegeName;
	}

	public void setCollegeName(String collegeName)
	{
		this.collegeName = collegeName;
	}

	@Override
	public String toString()
	{
		return "StudentCollegeShortListDTO [id=" + id + ", studentId=" + studentId + ", collegeId=" + collegeId + ", collegeName=" + collegeName + "]";
	}

	public String getCollegeAddress()
	{
		return collegeAddress;
	}

	public void setCollegeAddress(String collegeAddress)
	{
		this.collegeAddress = collegeAddress;
	}

}
