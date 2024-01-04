package com.lodestar.edupath.datatransferobject.dto;

public class StudentCityLockDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					studentLockId;
	private int	               cityLockId;
	
	
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	public int getStudentLockId() {
		return studentLockId;
	}
	public void setStudentLockId(int studentLockId) {
		this.studentLockId = studentLockId;
	}
	public int getCityLockId() {
		return cityLockId;
	}
	public void setCityLockId(int cityLockId) {
		this.cityLockId = cityLockId;
	}
	@Override
	public String toString() {
		return "StudentCityLock [id=" + id + ", studentLockId=" + studentLockId + ", cityLockId=" + cityLockId + "]";
	}
	
	
}
