package com.lodestar.edupath.datatransferobject.dto.APIS.chanakya;

import java.util.List;

public class ChanakyaEdupathDTO {
	private static final long	serialVersionUID	= 1L;

	private int			id;
	private String 		raisecOccId;
	private int			occupationId;
	private int 		dhPriority;
	private String 		stream;
	private String 		degree;
	private String 		entExam;
	private String 		OccName;
	private List<ChanakyaEntExamDTO>		exams;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRaisecOccId() {
		return raisecOccId;
	}
	public void setRaisecOccId(String raisecOccId) {
		this.raisecOccId = raisecOccId;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public List<ChanakyaEntExamDTO> getExams() {
		return exams;
	}
	public void setExams(List<ChanakyaEntExamDTO> exams) {
		this.exams = exams;
	}
	public String getEntExam() {
		return entExam;
	}
	public void setEntExam(String entExam) {
		this.entExam = entExam;
	}
	public int getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(int occupationId) {
		this.occupationId = occupationId;
	}
	public String getOccName() {
		return OccName;
	}
	public void setOccName(String occName) {
		OccName = occName;
	}
	public int getDhPriority() {
		return dhPriority;
	}
	public void setDhPriority(int dhPriority) {
		this.dhPriority = dhPriority;
	}
	@Override
	public String toString() {
		return "ChanakyaDHEdupathDTO [id=" + id + ", raisecOccId=" + raisecOccId + ", occupationId=" + occupationId
				+ ", dhPriority=" + dhPriority + ", stream=" + stream + ", degree=" + degree + ", entExam=" + entExam
				+ ", OccName=" + OccName + ", exams=" + exams + "]";
	}
	
	
}
