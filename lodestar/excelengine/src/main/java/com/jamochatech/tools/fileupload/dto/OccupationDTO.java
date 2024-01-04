package com.jamochatech.tools.fileupload.dto;

import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;

import com.jamochatech.tools.excelengine.dto.IModel;


public class OccupationDTO implements IModel, Serializable
{
	private static final long serialVersionUID = 1L;
	private int					id;
	private String				name;

	private String				rollOverContent;
	private String				description;
	private byte[]				image;

	private int					industryId;
	private int					pathWayId;
	private boolean				isActive;

	private String				industryName;
	private String				pathWayName;

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

	public String getRollOverContent()
	{
		return rollOverContent;
	}

	public void setRollOverContent(String rollOverContent)
	{
		this.rollOverContent = rollOverContent;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(int industryId)
	{
		this.industryId = industryId;
	}

	public int getPathWayId()
	{
		return pathWayId;
	}

	public void setPathWayId(int pathWayId)
	{
		this.pathWayId = pathWayId;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public String getIndustryName()
	{
		return industryName;
	}

	public void setIndustryName(String industryName)
	{
		this.industryName = industryName;
	}

	public long getLongId()
	{
		return id;
	}

	@Override
	public String toString()
	{
		return "OccupationDTO [id=" + id + ", name=" + name + ", industryId=" + industryId + ", pathWayId=" + pathWayId + ", isActive=" + isActive
				+ ", industryName=" + industryName + ", pathWayName=" + pathWayName + "]";
	}

	public byte[] getImage()
	{
		return image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}

	public String getBase64img()
	{
		String base64String = null;
		if (image != null)
		{
			try
			{
				base64String = Base64.encodeBase64String(image);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return base64String;
	}

	public String getPathWayName()
	{
		return pathWayName;
	}

	public void setPathWayName(String pathWayName)
	{
		this.pathWayName = pathWayName;
	}
}
