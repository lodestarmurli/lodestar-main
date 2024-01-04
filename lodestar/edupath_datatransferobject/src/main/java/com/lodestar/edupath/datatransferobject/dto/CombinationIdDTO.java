package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class CombinationIdDTO implements IModel{
	private int					combinationId ;

	public int getCombinationId() {
		return combinationId;
	}

	public void setCombinationId(int combinationId) {
		this.combinationId = combinationId;
	}
	
	@Override
	public String toString() {
		return "CombinationIdDTO [combinationId=" + combinationId + "]";
	}

}
