package com.lodestar.edupath.datatransferobject.dto;

public class ClassDTO implements IModel
{

	private static final long	serialVersionUID	= 1284435058138375896L;
	private int					id;
	private String				name;
	private String				gap;

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

	@Override public String toString()
	{
		return "ClassDTO [id=" + id + ", name=" + name + ", gap=" + gap + "]";
	}

	public String getGap()
	{
		return gap;
	}

	public void setGap(String gap)
	{
		this.gap = gap;
	}

}
