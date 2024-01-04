package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class VenueDetailDTO implements Serializable, IModel
{
	
	private static final long	serialVersionUID	= 1L;
	
	private int					id;
	private int					cityId;
	private String				venue;
	private String				address;
	
	
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
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@Override
	public String toString() {
		return "VenueDetailDTO [id=" + id + ", cityId=" + cityId + ", venue=" + venue + ", address=" + address + "]";
	}
	

}
