package com.lodestar.edupath.datatransferobject.dto.streamselector;

public class StreamSelectorResultDTO {

	private int 		studentId;
	private String		streamFitment;
	private String 		streamOccupation;
	public int getStudentId() {
		return studentId;
	}
	public String getStreamFitment() {
		return streamFitment;
	}
	public String getStreamOccupation() {
		return streamOccupation;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public void setStreamFitment(String streamFitment) {
		this.streamFitment = streamFitment;
	}
	public void setStreamOccupation(String streamOccupation) {
		this.streamOccupation = streamOccupation;
	}
	@Override
	public String toString() {
		return "StreamSelectorResultDTO [studentId=" + studentId + ", streamFitment=" + streamFitment
				+ ", streamOccupation=" + streamOccupation + "]";
	}
	 
	
	
}
