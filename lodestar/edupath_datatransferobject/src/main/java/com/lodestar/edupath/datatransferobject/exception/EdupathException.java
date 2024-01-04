package com.lodestar.edupath.datatransferobject.exception;

public class EdupathException extends RuntimeException
{
	private static final long	serialVersionUID	= 1L;

	private String[]			errorCode;
	private String				errorMessage;

	public EdupathException(String errorMessage, String... errorCode)
	{
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String[] getErrorCode()
	{
		return errorCode;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

}
