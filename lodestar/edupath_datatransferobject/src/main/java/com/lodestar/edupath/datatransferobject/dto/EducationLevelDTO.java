package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class EducationLevelDTO implements Serializable, IModel
{

	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private Integer				orderNo;

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

	@Override
	public String toString()
	{
		return "EducationLevelDTO [id=" + id + ", name=" + name + ", orderNo=" + orderNo + "]";
	}

	public Integer getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(Integer orderNo)
	{
		this.orderNo = orderNo;
	}

}
