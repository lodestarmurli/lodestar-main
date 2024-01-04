package com.lodestar.edupath.datatransferobject.dto.APIS;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CheckingDuplicationDTO implements IModel{
	private static final long	serialVersionUID	= 1L;
	private int			countstudent;
	public int getCountstudent() {
		return countstudent;
	}
	public void setCountstudent(int countstudent) {
		this.countstudent = countstudent;
	}
	
	
}
