package com.lodestar.edupath.stuinduocchoice.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.stuinduocchoice.service.MyWishListService;

public class MyWishListAction extends BaseAction
{
	private static final Logger					OUT					= LoggerFactory.getLogger(MyWishListAction.class);
	private static final long					serialVersionUID	= 1L;

	private int									occupationId;

	private int									industryId;

	private Map<String, List<OccupationDTO>>	myWishList;

	private int									count;

	private boolean								hasVisitedDoc;

	Map<Long, String>							visitedOccItems		= new HashMap<Long, String>();
	
	//START Sasedeve  by Mrutyunjaya on date 15-05-2017
			private static HashMap<Integer, String>	fitmentcolors			= new HashMap<Integer, String>();

			static
			{
				fitmentcolors.put(ApplicationConstants.FitMent.HIG.getScore(), ApplicationConstants.FitMent.HIG.getColor());
				fitmentcolors.put(ApplicationConstants.FitMent.A_AVG.getScore(), ApplicationConstants.FitMent.A_AVG.getColor());
				fitmentcolors.put(ApplicationConstants.FitMent.AVG.getScore(), ApplicationConstants.FitMent.AVG.getColor());
				fitmentcolors.put(ApplicationConstants.FitMent.LOW.getScore(), ApplicationConstants.FitMent.LOW.getColor());
			}
			
			//END Sasedeve  by Mrutyunjaya on date 15-05-2017
	
	

	public String execute()
	{
		try
		{
			UserSessionObject userSessionObject = getUserSessionObject();
			int studentUserId = userSessionObject.getId();

			MyWishListService myWishListService = new MyWishListService();
			myWishList = myWishListService.getMyWishList(studentUserId);
			hasVisitedDoc = myWishListService.hasVisitedDocument(studentUserId);
			visitedOccItems = myWishListService.getVisitedItems(studentUserId);
			// count = myWishList.entrySet().size();
			if (null != myWishList && !myWishList.isEmpty())
			{
				for (Entry<String, List<OccupationDTO>> entry : myWishList.entrySet())
				{
					if (null != entry.getValue())
					{
						count = count + entry.getValue().size();
					}
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public int getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(int industryId)
	{
		this.industryId = industryId;
	}

	public Map<String, List<OccupationDTO>> getMyWishList()
	{
		return myWishList;
	}

	public int getCount()
	{
		return count;
	}

	public boolean isHasVisitedDoc()
	{
		return hasVisitedDoc;
	}

	public Map<Long, String> getVisitedOccItems()
	{
		return visitedOccItems;
	}
	//START Sasedeve  by Mrutyunjaya on date 15-05-2017
			public HashMap<Integer, String> getFitmentcolors()
			{
				return fitmentcolors;
			}
		//END Sasedeve  by Mrutyunjaya on date 15-05-2017

}
