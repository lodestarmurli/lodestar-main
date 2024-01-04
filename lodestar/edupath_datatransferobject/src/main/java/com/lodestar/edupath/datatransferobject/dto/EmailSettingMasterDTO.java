package com.lodestar.edupath.datatransferobject.dto;

public class EmailSettingMasterDTO
{
	private int		id;
	private String	username;
	private String	password;
	private String	server;
	private int		port;
	private String	fromAddress;
	private String	encryptiontype;
	private Boolean	isSMTPAuthenticationRequired;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getServer()
	{
		return server;
	}

	public void setServer(String server)
	{
		this.server = server;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public String getFromAddress()
	{
		return fromAddress;
	}

	public void setFromAddress(String fromAddress)
	{
		this.fromAddress = fromAddress;
	}

	public String getEncryptiontype()
	{
		return encryptiontype;
	}

	public void setEncryptiontype(String encryptiontype)
	{
		this.encryptiontype = encryptiontype;
	}

	public Boolean getIsSMTPAuthenticationRequired()
	{
		return isSMTPAuthenticationRequired;
	}

	public void setIsSMTPAuthenticationRequired(Boolean isSMTPAuthenticationRequired)
	{
		this.isSMTPAuthenticationRequired = isSMTPAuthenticationRequired;
	}

}
