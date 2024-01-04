package com.lodestar.edupath.dataaccessobject.dao.StudentDetails;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ClassDAO
{
	private static Logger	OUT	= LoggerFactory.getLogger(ClassDAO.class);

	public List<ClassDTO> getAllClassList() throws Exception
	{
		List<ClassDTO> classList = new ArrayList<ClassDTO>();
		classList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_CLASS_NAME_WITH_ID, new ClassDTO());
		return classList;
	}
	public static ClassDTO getClassByName(String name) throws Exception
	{
		ClassDTO classDTO = new ClassDTO();
		classDTO.setName(name);
		ClassDTO classResult = null;
		try {

			classResult = (ClassDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CLASS_ID_BY_NAME, classDTO);
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return classResult;
	}

}