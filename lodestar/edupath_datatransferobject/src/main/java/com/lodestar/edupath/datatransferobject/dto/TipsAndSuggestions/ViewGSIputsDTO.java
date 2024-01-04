package com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions;



import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ViewGSIputsDTO implements IModel{
	private static final long	serialVersionUID	= 1L;
	
	private String  			gsinputdate;
	private String  			studentname;
	private String  			ldid;
	private String  			gsname;
	private int     			id;
	private String  			studentclass;
	private String  			answer;
	private int      			questionno;
	private int  			    studentid;
	private int  			    gsid;
	private Date				gsinputFromDate;
	private Date				gsinputToDate;
	
	
	
	public Date getGsinputFromDate() {
		return gsinputFromDate;
	}
	public void setGsinputFromDate(Date gsinputFromDate) {
		this.gsinputFromDate = gsinputFromDate;
	}
	public Date getGsinputToDate() {
		return gsinputToDate;
	}
	public void setGsinputToDate(Date gsinputToDate) {
		this.gsinputToDate = gsinputToDate;
	}
	public String getGsinputdate() {
		return gsinputdate;
	}
	public void setGsinputdate(String gsinputdate) {
		this.gsinputdate = gsinputdate;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getLdid() {
		return ldid;
	}
	public void setLdid(String ldid) {
		this.ldid = ldid;
	}
	public String getGsname() {
		return gsname;
	}
	public void setGsname(String gsname) {
		this.gsname = gsname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getQuestionno() {
		return questionno;
	}
	public void setQuestionno(int questionno) {
		this.questionno = questionno;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getGsid() {
		return gsid;
	}
	public void setGsid(int gsid) {
		this.gsid = gsid;
	}
	
	public String getStudentclass() {
		return studentclass;
	}
	public void setStudentclass(String studentclass) {
		this.studentclass = studentclass;
	}
	@Override
	public String toString() {
		return "ViewGSIputsDTO [id=" + id + ", gsinputdate=" + gsinputdate + ", studentname=" + studentname + ", ldid=" + ldid
				+ ", gsname=" + gsname + ", answer=" + answer + ", questionno=" + questionno
				+ ", studentid=" + studentid + ", gsid=" + gsid + ", studentclass=" + studentclass +"]";
	}
	

}
