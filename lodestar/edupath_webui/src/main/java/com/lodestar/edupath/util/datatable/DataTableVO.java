/**
 * 
 */
package com.lodestar.edupath.util.datatable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yogesh singh
 *         Jul 9, 2016
 *         DataTableVO.java
 */
public class DataTableVO implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private int					start;

	private int					pageLength			= 10;

	private String				searchValue;

	private int					draw;

	private String				orderBy;

	private String				orderColumnName;

	private List<String>		columnNameList		= new ArrayList<String>(10);

	private List<RefTableVO>	refTableList		= new ArrayList<RefTableVO>(10);

	private RefTableVO			refTable;

	private long				recordsTotalCount;
	private long				recordsFilteredCount;

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public String getSearchValue()
	{
		return searchValue;
	}

	public void setSearchValue(String searchValue)
	{
		this.searchValue = searchValue;
	}

	public int getDraw()
	{
		return draw;
	}

	public void setDraw(int draw)
	{
		this.draw = draw;
	}

	public String getOrderBy()
	{
		return orderBy;
	}

	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}

	public String getOrderColumnName()
	{
		return orderColumnName;
	}

	public void setOrderColumnName(String orderColumnName)
	{
		this.orderColumnName = orderColumnName;
	}

	/**
	 * @return the columnNameList
	 */
	public List<String> getColumnNameList()
	{
		return columnNameList;
	}

	/**
	 * @param columnNameList
	 *            the columnNameList to set
	 */
	public void setColumnNameList(List<String> columnNameList)
	{
		this.columnNameList = columnNameList;
	}

	@Override
	public String toString()
	{
		return "DataTableVO [start=" + start + ", searchValue=" + searchValue + ", draw=" + draw + ", orderBy=" + orderBy + ", columnNameList=" + columnNameList
				+ ", orderColumnName=" + orderColumnName + "]";
	}

	/**
	 * @return the pageLength
	 */
	public int getPageLength()
	{
		return pageLength;
	}

	/**
	 * @param pageLength
	 *            the pageLength to set
	 */
	public void setPageLength(int pageLength)
	{
		this.pageLength = pageLength;
	}

	public List<RefTableVO> getRefTableList()
	{
		return refTableList;
	}

	public void setRefTableList(List<RefTableVO> refTableList)
	{
		this.refTableList = refTableList;
	}

	public RefTableVO getRefTable()
	{
		return refTable;
	}

	public void setRefTable(RefTableVO refTable)
	{
		this.refTable = refTable;
	}

	public long getRecordsTotalCount()
	{
		return recordsTotalCount;
	}

	public void setRecordsTotalCount(long recordsTotalCount)
	{
		this.recordsTotalCount = recordsTotalCount;
	}

	public long getRecordsFilteredCount()
	{
		return recordsFilteredCount;
	}

	public void setRecordsFilteredCount(long recordsFilteredCount)
	{
		this.recordsFilteredCount = recordsFilteredCount;
	}

}
