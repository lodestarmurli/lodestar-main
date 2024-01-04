package com.lodestar.edupath.datatransferobject.dto;

import java.io.File;

public class SchoolDTO implements IModel
{

	private static final long	serialVersionUID	= 8968108249276393477L;
	private int					id;
	private String				name;
	private String				code;
	private int					cityId;
	private int	               schoolId;
	
	//start bharath on date 16-5-2019
	
	private int 				displaylogo;
	private String 					logopath;

	private File				image;

	private String				imageContentType;

	private String				imageFileName;
	
	//end bharath on date 16-5-2019

	


	private int					studentCount;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public int getStudentCount()
	{
		return studentCount;
	}

	public void setStudentCount(int studentCount)
	{
		this.studentCount = studentCount;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	@Override
	public String toString() {
		return "SchoolDTO [id=" + id + ", name=" + name + ", code=" + code + ", cityId=" + cityId + ", studentCount="
				+ studentCount + ", logopath="+logopath+", displaylogo= "+ displaylogo + "]";
	}

	public String getLogopath() {
		return logopath;
	}

	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getDisplaylogo() {
		return displaylogo;
	}

	public void setDisplaylogo(int displaylogo) {
		this.displaylogo = displaylogo;
	}

	

}
