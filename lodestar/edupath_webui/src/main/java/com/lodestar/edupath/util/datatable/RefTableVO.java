package com.lodestar.edupath.util.datatable;

import java.io.Serializable;

/**
 * @author yogesh.s
 */
public class RefTableVO implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	private String				refNameColumn;
	private String				refTableName;

	public String getRefNameColumn()
	{
		return refNameColumn;
	}

	public void setRefNameColumn(String refNameColumn)
	{
		this.refNameColumn = refNameColumn;
	}

	public String getRefTableName()
	{
		return refTableName;
	}

	public void setRefTableName(String refTableName)
	{
		this.refTableName = refTableName;
	}
}
