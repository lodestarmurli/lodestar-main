package com.lodestar.edupath.datatransferobject.dto.studentparameter;

import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.collegeparameter.CollegeParametersDTO;

public class StudentCollegeParametersDTO implements IModel
{
	private static final long			serialVersionUID	= 1L;

	private int							id;
	private String						address;
	private String						selectedParameters;
	private int							studentId;
	private List<CollegeParametersDTO>	collegeParams;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getSelectedParameters()
	{
		return selectedParameters;
	}

	public void setSelectedParameters(String selectedParameters)
	{
		this.selectedParameters = selectedParameters;
	}

	@Override
	public String toString()
	{
		return "CollegeParameterDTO [id=" + id + ", address=" + address + ", selectedParameters=" + selectedParameters + ", studentId=" + studentId + "]";
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public List<CollegeParametersDTO> getCollegeParams()
	{
		return collegeParams;
	}

	public void setCollegeParams(List<CollegeParametersDTO> collegeParams)
	{
		this.collegeParams = collegeParams;
	}

}
