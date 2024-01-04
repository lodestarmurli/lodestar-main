package com.lodestar.edupath.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServiceProperties
{

	private static final Logger			OUT				= LoggerFactory.getLogger(ServiceProperties.class);

	private static ServiceProperties	appProperties	= null;

	private Properties					prop;

	/**
	 * loading the properties.
	 */
	private ServiceProperties() throws Exception
	{
		InputStream stream = null;
		try
		{
			URL url = this.getClass().getClassLoader().getResource("ServiceProperties.properties");
			if (url == null)
			{
				OUT.warn("Could not load application properties \"ServiceProperties.properties\" does not exist");
				return;
			}
			stream = url.openStream();
			this.prop = new Properties();
			this.prop.load(stream);
			OUT.info("Loaded Service properties from " + url);
		}
		catch (IOException e)
		{
			this.prop = null;
			OUT.error("Exception", e);
		}
		finally
		{
			if (stream != null)
			{
				stream.close();
				stream = null;
			}
		}
	}

	/**
	 * @return
	 */
	public static ServiceProperties getInstance()
	{
		try
		{
			if (null == appProperties)
			{
				appProperties = new ServiceProperties();
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return appProperties;
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

		return !(null == this.prop.getProperty(key));
	}

	/**
	 * Retrieves the value of the key.
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key)
	{
		return this.prop.getProperty(key);
	}

	public String getProperty(String key, String defaultValue)
	{
		return this.prop.getProperty(key, defaultValue);
	}
}
