package com.lodestar.edupath.datatransferobject.dto.programTest.streamselector;

public class StreamSelectorResultVO {

	private int 		id;
	private int 		studentId;

	private String		mainStream;
	private String 		stream;
	private String		commonName;
	private String 		abililtyRequired1;
	private String 		abililtyRequired2;
	private String 		abililtyValue1;
	private String 		abililtyValue2;
	private String 		abililtyOutcome;
	private boolean 	code;
	private int			streamFitment;
	private int			StreamPriority;
	private String		riasecCode;	
	private String		occupation;
	private String		description;
	private String		courses;
	private	String		careerPotential;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getMainStream() {
		return mainStream;
	}
	public void setMainStream(String mainStream) {
		this.mainStream = mainStream;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getAbililtyRequired1() {
		return abililtyRequired1;
	}
	public void setAbililtyRequired1(String abililtyRequired1) {
		this.abililtyRequired1 = abililtyRequired1;
	}
	public String getAbililtyRequired2() {
		return abililtyRequired2;
	}
	public void setAbililtyRequired2(String abililtyRequired2) {
		this.abililtyRequired2 = abililtyRequired2;
	}
	public String getAbililtyValue1() {
		return abililtyValue1;
	}
	public void setAbililtyValue1(String abililtyValue1) {
		this.abililtyValue1 = abililtyValue1;
	}
	public String getAbililtyValue2() {
		return abililtyValue2;
	}
	public void setAbililtyValue2(String abililtyValue2) {
		this.abililtyValue2 = abililtyValue2;
	}
	public String getAbililtyOutcome() {
		return abililtyOutcome;
	}
	public void setAbililtyOutcome(String abililtyOutcome) {
		this.abililtyOutcome = abililtyOutcome;
	}
	public boolean isCode() {
		return code;
	}
	public void setCode(boolean code) {
		this.code = code;
	}
	public int getStreamFitment() {
		return streamFitment;
	}
	public void setStreamFitment(int streamFitment) {
		this.streamFitment = streamFitment;
	}
	public int getStreamPriority() {
		return StreamPriority;
	}
	public void setStreamPriority(int streamPriority) {
		StreamPriority = streamPriority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	public String getCareerPotential() {
		return careerPotential;
	}
	public void setCareerPotential(String careerPotential) {
		this.careerPotential = careerPotential;
	}
	public String getRiasecCode() {
		return riasecCode;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setRiasecCode(String riasecCode) {
		this.riasecCode = riasecCode;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	@Override
	public String toString() {
		return "StreamSelectorResultVO [id=" + id + ", studentId=" + studentId + ", mainStream=" + mainStream
				+ ", stream=" + stream + ", commonName=" + commonName + ", abililtyRequired1=" + abililtyRequired1
				+ ", abililtyRequired2=" + abililtyRequired2 + ", abililtyValue1=" + abililtyValue1
				+ ", abililtyValue2=" + abililtyValue2 + ", abililtyOutcome=" + abililtyOutcome + ", code=" + code
				+ ", streamFitment=" + streamFitment + ", StreamPriority=" + StreamPriority + ", riasecCode="
				+ riasecCode + ", occupation=" + occupation + ", description=" + description + ", courses=" + courses
				+ ", careerPotential=" + careerPotential + "]";
	}
	  
	
}
