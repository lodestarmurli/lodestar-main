package com.lodestar.edupath.facilitator.action;

import java.io.Serializable;

import com.lodestar.edupath.util.datatable.DataTableAnnotation;

public class CalendarVO implements Serializable {

//	@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private static final long	serialVersionUID	= 1L;
	
	//@DataTableAnnotation(exclude = false, displayColumnName = "", tableColumnName = "")
	private String			start;
	private String			title;

	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "CalendarVO [start=" + start + ", title=" + title + "]";
	}

	
	
	
	
	

	
}
