package com.lodestar.edupath.datatransferobject.dto.elective;

import java.util.List;

public final class StreamsDTO
{
	private int		id;
	private String	name;
	private Integer	edulavelId;
	
	// none table 
	private List<Integer> idsList;

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

	public Integer getEdulavelId()
	{
		return edulavelId;
	}

	public void setEdulavelId(Integer edulavelId)
	{
		this.edulavelId = edulavelId;
	}

	public List<Integer> getIdsList()
	{
		return idsList;
	}

	public void setIdsList(List<Integer> idsList)
	{
		this.idsList = idsList;
	}

}
