package com.lodestar.edupath.datatransferobject.dto.APIS.client;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRaisecCodeDTO implements IModel
{
	private static final long			serialVersionUID	= 1L;


	private String						raisec;
	private String						characters;
	private String						description;
	private String						fullFrom;
	
	public String getRaisec() {
		return raisec;
	}
	public void setRaisec(String raisec) {
		this.raisec = raisec;
	}
	public String getCharacters() {
		return characters;
	}
	public void setCharacters(String characters) {
		this.characters = characters;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFullFrom() {
		return fullFrom;
	}
	public void setFullFrom(String fullFrom) {
		this.fullFrom = fullFrom;
	}
	@Override
	public String toString() {
		return "RaisecCodeDTO [raisec=" + raisec + ", characters=" + characters + ", description=" + description
				+ ", fullFrom=" + fullFrom + "]";
	}
	
	
	
	
	
}
