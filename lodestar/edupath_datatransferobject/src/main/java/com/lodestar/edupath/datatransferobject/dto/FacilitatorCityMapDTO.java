package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class FacilitatorCityMapDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;
	
	private int					id;
	private int					cityId;
	private int					facilitatorId;
	private int					faceToFaceCity;
	private int					oncallCity;
	private String				name;
	private int					oldid;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getFacilitatorId() {
		return facilitatorId;
	}
	public void setFacilitatorId(int facilitatorId) {
		this.facilitatorId = facilitatorId;
	}
	public int getFaceToFaceCity() {
		return faceToFaceCity;
	}
	public void setFaceToFaceCity(int faceToFaceCity) {
		this.faceToFaceCity = faceToFaceCity;
	}
	public int getOncallCity() {
		return oncallCity;
	}
	public void setOncallCity(int oncallCity) {
		this.oncallCity = oncallCity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOldid() {
		return oldid;
	}
	public void setOldid(int oldid) {
		this.oldid = oldid;
	}
	@Override
	public String toString() {
		return "FacilitatorCityMapDTO [id=" + id + ", cityId=" + cityId + ", facilitatorId=" + facilitatorId
				+ ", faceToFaceCity=" + faceToFaceCity + ", oncallCity=" + oncallCity + ", name=" + name + ", oldid="
				+ oldid + "]";
	}
	
}
