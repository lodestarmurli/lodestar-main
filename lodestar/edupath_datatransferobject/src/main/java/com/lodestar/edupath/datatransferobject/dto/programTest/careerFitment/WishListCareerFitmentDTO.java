package com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;

public class WishListCareerFitmentDTO implements IModel{
	
	private int 		id;
	private int			studentId;
	private int 		optionA;
	private int 		optionB;
	private int			occupationId;
	private int 		clusterId;
	
	private OccupationDTO		occupation;
	private ClusterDTO			clusterDTO;
	
	
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
	public int getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(int occupationId) {
		this.occupationId = occupationId;
	}
	public int getClusterId() {
		return clusterId;
	}
	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}
	@Override
	public String toString() {
		return "WishListCareerFitmentDTO [id=" + id + ", studentId=" + studentId + ", optionA=" + optionA + ", optionB="
				+ optionB + ", occupationId=" + occupationId + ", clusterId=" + clusterId + ", occupation=" + occupation
				+ ", clusterDTO=" + clusterDTO + "]";
	}
	public OccupationDTO getOccupation() {
		return occupation;
	}
	public void setOccupation(OccupationDTO occupation) {
		this.occupation = occupation;
	}
	public ClusterDTO getClusterDTO() {
		return clusterDTO;
	}
	public void setClusterDTO(ClusterDTO clusterDTO) {
		this.clusterDTO = clusterDTO;
	}
	
	
	
	

}
