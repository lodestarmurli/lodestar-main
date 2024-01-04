package com.lodestar.edupath.occupation.wishlist;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.occupation.wishlist.service.OccupationWishListService;

public class OccupationWishListAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static final Logger	OUT					= LoggerFactory.getLogger(OccupationWishListAction.class);
	private Map<String, Object>	occupationWishlist;
	private OccupationWishListService service;
	
	//START Sasedeve  by Mrutyunjaya on date 26-04-2017
	private static HashMap<Integer, String>	fitmentcolors			= new HashMap<Integer, String>();

	static
	{
		fitmentcolors.put(ApplicationConstants.FitMent.HIG.getScore(), ApplicationConstants.FitMent.HIG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.A_AVG.getScore(), ApplicationConstants.FitMent.A_AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.AVG.getScore(), ApplicationConstants.FitMent.AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.LOW.getScore(), ApplicationConstants.FitMent.LOW.getColor());
	}
	
	//END Sasedeve  by Mrutyunjaya on date 26-04-2017
	public String execute()
	{
		OUT.debug("Inside OccupationWishListAction");
		try
		{
			service = new OccupationWishListService();
			occupationWishlist = service.getMyWishList(getStudentSessionObject().getUserId());
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public Map<String, Object> getOccupationWishlist()
	{
		return occupationWishlist;
	}

	public void setOccupationWishlist(Map<String, Object> occupationWishlist)
	{
		this.occupationWishlist = occupationWishlist;
	}
	//START Sasedeve  by Mrutyunjaya on date 26-04-2017
	public HashMap<Integer, String> getFitmentcolors()
	{
		return fitmentcolors;
	}
	//END Sasedeve  by Mrutyunjaya on date 26-04-2017
}
