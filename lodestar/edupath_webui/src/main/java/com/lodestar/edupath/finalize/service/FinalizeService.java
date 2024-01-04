package com.lodestar.edupath.finalize.service;

import com.lodestar.edupath.dataaccessobject.dao.collegeparameter.StudentCollegeParametersDAO;
import com.lodestar.edupath.datatransferobject.dto.studentparameter.StudentCollegeParametersDTO;

public class FinalizeService
{
	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public String getStudentAddress(int studentId) throws Exception
	{
		String studAddr = null;
		StudentCollegeParametersDTO collegeParametersDTO = new StudentCollegeParametersDAO().getStudentCollegeParametersByStudentId(studentId);
		if (collegeParametersDTO != null)
		{
			studAddr = collegeParametersDTO.getAddress();
		}
		return studAddr;
	}
}
