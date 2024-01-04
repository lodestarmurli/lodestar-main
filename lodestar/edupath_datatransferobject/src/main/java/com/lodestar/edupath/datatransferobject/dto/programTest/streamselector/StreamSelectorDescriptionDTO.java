package com.lodestar.edupath.datatransferobject.dto.programTest.streamselector;

public class StreamSelectorDescriptionDTO {
	
	private int 	id;
	private String 	stream;
	private String 	description;
	private String 	courses;
	private String 	careerPotential;
	public int getId() {
		return id;
	}
	public String getStream() {
		return stream;
	}
	public String getDescription() {
		return description;
	}
	public String getCourses() {
		return courses;
	}
	public String getCareerPotential() {
		return careerPotential;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	public void setCareerPotential(String careerPotential) {
		this.careerPotential = careerPotential;
	}
	@Override
	public String toString() {
		return "StreamSelectorDescription [id=" + id + ", stream=" + stream + ", description=" + description
				+ ", courses=" + courses + ", careerPotential=" + careerPotential + "]";
	}
	
	

}
