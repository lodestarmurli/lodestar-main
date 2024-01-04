package com.lodestar.edupath.datatransferobject.dto;

public class DoYouKnowDTO implements IModel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				message;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "DoYouKnowDTO [id=" + id + ", message=" + message + "]";
	}

}
