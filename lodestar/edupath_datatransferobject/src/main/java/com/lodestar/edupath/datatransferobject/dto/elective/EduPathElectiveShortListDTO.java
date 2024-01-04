package com.lodestar.edupath.datatransferobject.dto.elective;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class EduPathElectiveShortListDTO implements IModel
{
	private static final long	serialVersionUID	= -8733073954109521105L;
	private int					id;
	private Integer				combinationId;
	private Integer				orderNo;
	private Integer				studentId;
	private Integer				noOfCollege;

	// non table
	private String				name;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Integer getCombinationId()
	{
		return combinationId;
	}

	public void setCombinationId(Integer combinationId)
	{
		this.combinationId = combinationId;
	}

	public Integer getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(Integer orderNo)
	{
		this.orderNo = orderNo;
	}

	public Integer getStudentId()
	{
		return studentId;
	}

	public void setStudentId(Integer studentId)
	{
		this.studentId = studentId;
	}

	public Integer getNoOfCollege()
	{
		return noOfCollege;
	}

	public void setNoOfCollege(Integer noOfCollege)
	{
		this.noOfCollege = noOfCollege;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
