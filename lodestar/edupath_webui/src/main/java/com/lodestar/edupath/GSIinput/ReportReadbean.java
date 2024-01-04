package com.lodestar.edupath.GSIinput;

import java.io.Serializable;
import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;

public class ReportReadbean implements Serializable{
	private static final long						serialVersionUID	= 1L;
	private List<OccupationDTO> OcupationsList;
	
	private int TotalNoOfOccupation;
	private int TotalNoOfIndustry;
	
	public int getTotalNoOfOccupation() {
		return TotalNoOfOccupation;
	}
	public void setTotalNoOfOccupation(int totalNoOfOccupation) {
		TotalNoOfOccupation = totalNoOfOccupation;
	}
	public int getTotalNoOfIndustry() {
		return TotalNoOfIndustry;
	}
	public void setTotalNoOfIndustry(int totalNoOfIndustry) {
		TotalNoOfIndustry = totalNoOfIndustry;
	}
	
	
	public List<OccupationDTO> getOcupationsList() {
		return OcupationsList;
	}
	public void setOcupationsList(List<OccupationDTO> ocupationsList) {
		OcupationsList = ocupationsList;
	}
	
}
