package com.lodestar.edupath.stuinduocchoice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.document.DocumentsDAO;
import com.lodestar.edupath.dataaccessobject.dao.stuinduocchoice.MyWishListDAO;
import com.lodestar.edupath.datatransferobject.dto.DocumentDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.WishListDTO;

public class MyWishListService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(MyWishListService.class);

	public Map<String, List<OccupationDTO>> getMyWishList(int userId) throws Exception
	{
		List<WishListDTO> userMyWishList = new MyWishListDAO().getUserMyWishList(userId);

		Map<String, List<OccupationDTO>> indusOccupMap = new HashMap<String, List<OccupationDTO>>();

		List<OccupationDTO> list;
		String key;
		for (WishListDTO wishListDTO : userMyWishList)
		{
			key = wishListDTO.getOccupation().getIndustryId() + "_" + wishListDTO.getOccupation().getIndustryName();
			list = indusOccupMap.get(key);
			if (list == null)
			{
				list = new ArrayList<OccupationDTO>();
			}

			list.add(wishListDTO.getOccupation());

			indusOccupMap.put(key, list);
		}

		return indusOccupMap;
	}

	public boolean hasVisitedDocument(int userId)
	{
		boolean hasVisited = false;
		try
		{
			DocumentDTO lastVisitedWishListDocument = new DocumentsDAO().getLastVisitedWishListDocument(userId);
			if (lastVisitedWishListDocument != null)
			{
				hasVisited = true;
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		return hasVisited;
	}

	public Map<Long, String> getVisitedItems(int userId)
	{
		Map<Long, String> visitedOcc = new HashMap<>();
		try
		{
			List<DocumentDTO> visitedOccListDocument = new DocumentsDAO().getVisitedOccupationDocs(userId);
			if (!visitedOccListDocument.isEmpty())
			{
				for (DocumentDTO documentDTO : visitedOccListDocument)
				{
					visitedOcc.put((long) documentDTO.getOccupationId(), "visited");
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		return visitedOcc;
	}
}
