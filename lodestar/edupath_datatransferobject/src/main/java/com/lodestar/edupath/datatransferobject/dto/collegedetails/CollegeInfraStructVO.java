package com.lodestar.edupath.datatransferobject.dto.collegedetails;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeInfraStructVO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				infra;
	private String				infraExist;
	private int					count;
	private String				capacity;

	public CollegeInfraStructVO()
	{

	}

	public CollegeInfraStructVO(String infraExist)
	{
		this.infraExist = infraExist;
	}

	public String getInfra()
	{
		return infra;
	}

	public void setInfra(String infra)
	{
		this.infra = infra;
	}

	public String getInfraExist()
	{
		return infraExist;
	}

	public void setInfraExist(String infraExist)
	{
		this.infraExist = infraExist;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "CollegeInfraStructVO [id=" + id + ", infra=" + infra + ", infraExist=" + infraExist + ", count=" + count + ", capacity=" + capacity + "]";
	}

	public String getCapacity()
	{
		return capacity;
	}

	public void setCapacity(String capacity)
	{
		this.capacity = capacity;
	}

}
