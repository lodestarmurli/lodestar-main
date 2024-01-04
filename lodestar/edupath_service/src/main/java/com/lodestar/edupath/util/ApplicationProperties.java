package com.lodestar.edupath.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationProperties
{
	private static final Logger				OUT	= LoggerFactory.getLogger(ApplicationProperties.class);
	private static ApplicationProperties	INSTANCE;
	private Properties						properties;

	private ApplicationProperties()
	{
		InputStream openStream = null;
		try
		{
			URL url = this.getClass().getClassLoader().getResource("applicationproperties.properties");
			openStream = url.openStream();
			this.properties = new Properties();
			this.properties.load(openStream);
		}
		catch (Exception e)
		{
			this.properties = null;
			OUT.error("Exception", e);
		}
		finally
		{
			if (openStream != null)
			{
				try
				{
					openStream.close();
				}
				catch (IOException e)
				{
					OUT.error("Exception", e);
				}
			}
		}
	}

	public static ApplicationProperties getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new ApplicationProperties();
		}
		return INSTANCE;
	}

	/**
	 * Checks whether the property is available or not.
	 * 
	 * @param key
	 * @return
	 */
	public boolean isPropertyExist(String key)
	{
		if (null == key)
			return false;

		return !(null == this.properties.getProperty(key));
	}

	/**
	 * Retrieves the value of the key.
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key)
	{
		return this.properties.getProperty(key);
	}

	/**
	 * Retrieves the value of the key.
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key, String defaultValue)
	{
		if (this.properties.getProperty(key) != null)
			return this.properties.getProperty(key, defaultValue);
		return defaultValue;
	}
}
