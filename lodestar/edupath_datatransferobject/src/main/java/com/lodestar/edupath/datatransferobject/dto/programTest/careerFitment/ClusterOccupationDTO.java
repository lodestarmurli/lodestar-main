package com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment;

import java.util.Arrays;
import java.util.List;


public class ClusterOccupationDTO {
	
	private int 				id;
	private boolean				isOccupation;
	private boolean 			iscluster;
	private int					optionA;
	private int 				optionB;
	private int					occupationId;
	private int 				clusterId;
	private String				name;
	private String				rollOverContent;
	private String				description;
	private byte[]				image;

	private boolean				isActive;

	// non table coulumn
	private int					industryId;
	private String				industryName;

	private int					pathWayId;
	private String				pathWayName;


	private int					entranceExamId;
	private int					integratedCourseId;

	private String				requiredRAISEC;
	private String				requiredAbility;
	private int					fitment;
	
	private int					newfitment;
	private String				clusterName;

	private String				clusterDegree;
	private String				clusterEntExam;
	private int 				careerFitmentPriority;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isOccupation() {
		return isOccupation;
	}
	public void setOccupation(boolean isOccupation) {
		this.isOccupation = isOccupation;
	}
	public boolean isIscluster() {
		return iscluster;
	}
	public void setIscluster(boolean iscluster) {
		this.iscluster = iscluster;
	}
	public int getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(int occupationId) {
		this.occupationId = occupationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollOverContent() {
		return rollOverContent;
	}
	public void setRollOverContent(String rollOverContent) {
		this.rollOverContent = rollOverContent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getIndustryId() {
		return industryId;
	}
	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public int getPathWayId() {
		return pathWayId;
	}
	public void setPathWayId(int pathWayId) {
		this.pathWayId = pathWayId;
	}
	public String getPathWayName() {
		return pathWayName;
	}
	public void setPathWayName(String pathWayName) {
		this.pathWayName = pathWayName;
	}
	 
	public int getEntranceExamId() {
		return entranceExamId;
	}
	public void setEntranceExamId(int entranceExamId) {
		this.entranceExamId = entranceExamId;
	}
	public int getIntegratedCourseId() {
		return integratedCourseId;
	}
	public void setIntegratedCourseId(int integratedCourseId) {
		this.integratedCourseId = integratedCourseId;
	}
	public String getRequiredRAISEC() {
		return requiredRAISEC;
	}
	public void setRequiredRAISEC(String requiredRAISEC) {
		this.requiredRAISEC = requiredRAISEC;
	}
	public String getRequiredAbility() {
		return requiredAbility;
	}
	public void setRequiredAbility(String requiredAbility) {
		this.requiredAbility = requiredAbility;
	}
	public int getFitment() {
		return fitment;
	}
	public void setFitment(int fitment) {
		this.fitment = fitment;
	}
	public int getNewfitment() {
		return newfitment;
	}
	public void setNewfitment(int newfitment) {
		this.newfitment = newfitment;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public int getClusterId() {
		return clusterId;
	}
	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}
	public String getClusterDegree() {
		return clusterDegree;
	}
	public void setClusterDegree(String clusterDegree) {
		this.clusterDegree = clusterDegree;
	}
	public String getClusterEntExam() {
		return clusterEntExam;
	}
	public void setClusterEntExam(String clusterEntExam) {
		this.clusterEntExam = clusterEntExam;
	}
	public int getCareerFitmentPriority() {
		return careerFitmentPriority;
	}
	public void setCareerFitmentPriority(int careerFitmentPriority) {
		this.careerFitmentPriority = careerFitmentPriority;
	}
	public int getOptionA() {
		return optionA;
	}
	public void setOptionA(int optionA) {
		this.optionA = optionA;
	}
	public int getOptionB() {
		return optionB;
	}
	public void setOptionB(int optionB) {
		this.optionB = optionB;
	}
	@Override
	public String toString() {
		return "ClusterOccupationDTO [id=" + id + ", isOccupation=" + isOccupation + ", iscluster=" + iscluster
				+ ", optionA=" + optionA + ", optionB=" + optionB + ", occupationId=" + occupationId + ", clusterId="
				+ clusterId + ", name=" + name + ", rollOverContent=" + rollOverContent + ", description=" + description
				+ ", image=" + Arrays.toString(image) + ", isActive=" + isActive + ", industryId=" + industryId
				+ ", industryName=" + industryName + ", pathWayId=" + pathWayId + ", pathWayName=" + pathWayName
				+ ", entranceExamId=" + entranceExamId + ", integratedCourseId=" + integratedCourseId
				+ ", requiredRAISEC=" + requiredRAISEC + ", requiredAbility=" + requiredAbility + ", fitment=" + fitment
				+ ", newfitment=" + newfitment + ", clusterName=" + clusterName + ", clusterDegree=" + clusterDegree
				+ ", clusterEntExam=" + clusterEntExam + ", careerFitmentPriority=" + careerFitmentPriority + "]";
	}
 
	
	
	
		

}
