package com.lodestar.edupath.induoccchoice.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.lodestar.edupath.dataaccessobject.dao.industry.IndustryDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.wishlist.WishListDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.IndustryDTO;
import com.lodestar.edupath.datatransferobject.dto.student.WishListDTO;

public class WishListService
{
	public Map<String, List<WishListDTO>> getStudentWishList(int studentId) throws Exception
	{
		List<WishListDTO> studentWishList = new WishListDAO().getStudentWishList(studentId);
		Map<String, List<WishListDTO>> wishlist = new LinkedHashMap<String, List<WishListDTO>>();
		for (WishListDTO wishListDTO : studentWishList)
		{
			List<WishListDTO> list;

			if (wishlist.containsKey(wishListDTO.getOccupation().getIndustryName()))
			{
				list = wishlist.get(wishListDTO.getOccupation().getIndustryName());
			}
			else
			{
				list = new ArrayList<>();
			}

			list.add(wishListDTO);

			wishlist.put(wishListDTO.getOccupation().getIndustryName(), list);
		}

		return wishlist;
	}

	public void insertStudentWishList(int studentId, Integer occupationId, Integer occIndustryId) throws Exception
	{
		new SessionScheduleDetailsDAO().updatePreNSession1Complete(studentId);

		new WishListDAO().deleteStudentWishListByOcc(studentId, occupationId, occIndustryId);

		WishListDTO wishList = new WishListDTO();
		wishList.setOccupationId(occupationId);
		wishList.setStudentId(studentId);
		wishList.setOccIndustryId(occIndustryId);

		new WishListDAO().insertStudentWishList(wishList);
		
	}

	public void deleteWishListById(int id) throws Exception
	{
		new WishListDAO().deleteStudentWishList(id);
	}

	public void deleteWishListByOcc(int studentId, int occupationId, Integer occIndustryId) throws Exception
	{
		new WishListDAO().deleteStudentWishListByOcc(studentId, occupationId, occIndustryId);
	}

	public Set<IndustryDTO> getIndustriesForOcc(int occupationId) throws Exception
	{
		List<IndustryDTO> listOfIndustryForOcc = new IndustryDAO().getListOfIndustryForOcc(occupationId);
		Set<IndustryDTO> setOfIndustryForOcc = new TreeSet<>(new Comparator<IndustryDTO>()
		{
			@Override
			public int compare(IndustryDTO o1, IndustryDTO o2)
			{
				if (o1.getId() == o2.getId())
				{
					return 0;
				}
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		setOfIndustryForOcc.addAll(listOfIndustryForOcc);

		return setOfIndustryForOcc;
	}
}
