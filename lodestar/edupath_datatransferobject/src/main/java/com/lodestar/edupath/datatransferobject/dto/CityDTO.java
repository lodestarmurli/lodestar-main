package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class CityDTO implements Serializable, IModel {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CityDTO [id=" + id + ", name=" + name + "]";
	}

}
