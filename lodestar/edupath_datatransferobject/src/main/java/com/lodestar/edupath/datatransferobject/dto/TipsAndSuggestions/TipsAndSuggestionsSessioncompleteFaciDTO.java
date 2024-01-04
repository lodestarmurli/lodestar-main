package com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions;

import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TipsAndSuggestionsSessioncompleteFaciDTO implements IModel{
	
	private static final long	serialVersionUID	= 1L;

	private int						id;
	private int						studenid;
	private int						facilitatorid;
	private int						issession1completefaci;
	private int						issession2completefaci;
	private String					Session1date;
	private String					Session2date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudenid() {
		return studenid;
	}
	public void setStudenid(int studenid) {
		this.studenid = studenid;
	}
	public int getIssession1completefaci() {
		return issession1completefaci;
	}
	public void setIssession1completefaci(int issession1completefaci) {
		this.issession1completefaci = issession1completefaci;
	}
	public int getIssession2completefaci() {
		return issession2completefaci;
	}
	public void setIssession2completefaci(int issession2completefaci) {
		this.issession2completefaci = issession2completefaci;
	}
	public String getSession1date() {
		return Session1date;
	}
	public void setSession1date(String session1date) {
		Session1date = session1date;
	}
	public String getSession2date() {
		return Session2date;
	}
	public void setSession2date(String session2date) {
		Session2date = session2date;
	}
	public int getFacilitatorid() {
		return facilitatorid;
	}
	public void setFacilitatorid(int facilitatorid) {
		this.facilitatorid = facilitatorid;
	}
	
	
	
	
}
