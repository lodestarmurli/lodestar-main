package com.jamochatech.tools.fileupload.dto;

import java.io.Serializable;

public class TrialInterestCodeMappingDTO implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				raisecCode;
	private byte[]				content;
	private String				fileName;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getRaisecCode()
	{
		return raisecCode;
	}

	public void setRaisecCode(String raisecCode)
	{
		this.raisecCode = raisecCode;
	}

	public byte[] getContent()
	{
		return content;
	}

	public void setContent(byte[] content)
	{
		this.content = content;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

}
