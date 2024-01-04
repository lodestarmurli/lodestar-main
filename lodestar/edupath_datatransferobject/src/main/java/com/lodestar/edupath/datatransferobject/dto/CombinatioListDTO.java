package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;
import java.util.List;

public class CombinatioListDTO implements Serializable, IModel{

	private static final long	serialVersionUID	= 1L;

	
	private List<Integer> idsList;
	private int					collegeId ;
	private int					streamId;
	
	
	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public List<Integer> getIdsList() {
		return idsList;
	}

	public void setIdsList(List<Integer> idsList) {
		this.idsList = idsList;
	}


	

}
