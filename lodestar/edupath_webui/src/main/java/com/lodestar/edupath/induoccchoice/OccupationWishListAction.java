package com.lodestar.edupath.induoccchoice;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.occupation.IndustryDTO;
import com.lodestar.edupath.datatransferobject.dto.student.WishListDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.induoccchoice.service.WishListService;

public class OccupationWishListAction extends BaseAction
{
	private static final Logger		OUT					= LoggerFactory.getLogger(OccupationWishListAction.class);
	private static final long		serialVersionUID	= 1L;

	private int						studentId;

	private int						wishListId;

	private String					wishId;

	private Integer					occIndustryId;

	Map<String, List<WishListDTO>>	studentWishList;

	private int						excludeForOcc		= 6;

	Set<IndustryDTO>				industriesForOcc	= new TreeSet<>();

	public String execute()
	{
		try
		{
			studentWishList = new WishListService().getStudentWishList(studentId);

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String delete()
	{
		try
		{
			WishListService wishListService = new WishListService();

			wishListService.deleteWishListById(wishListId);
			StringBuilder auditMessage = new StringBuilder();
			auditMessage.append("Student's ").append(getStudentSessionObject().getFullName()).append(" wishlist deleted wishlistId: ").append(wishListId);
			insertAudit(auditMessage.toString(), getUserSessionObject());

			studentWishList = wishListService.getStudentWishList(studentId);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String add()
	{
		try
		{
			WishListService wishListService = new WishListService();

			if (wishId.startsWith("occ"))
			{
				String substring = wishId.substring(excludeForOcc);
				wishListService.insertStudentWishList(studentId, Integer.parseInt(substring), occIndustryId);
				StringBuilder auditMessage = new StringBuilder();
				auditMessage.append("Student's ").append(getStudentSessionObject().getFullName()).append(" wishlist added Occ/IndustryId ").append(wishId);
				insertAudit(auditMessage.toString(), getUserSessionObject());
			}
			studentWishList = wishListService.getStudentWishList(studentId);

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String industries() throws Exception
	{
		try
		{
			WishListService wishListService = new WishListService();

			if (wishId.startsWith("occ"))
			{
				String substring = wishId.substring(excludeForOcc);
				industriesForOcc = wishListService.getIndustriesForOcc(Integer.parseInt(substring));
			}

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			throw e;
		}
		return SUCCESS;
	}

	private void insertAudit(String auditMessage, UserSessionObject userSessionObject)
	{
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.OCCUPATION_WISH_LIST, auditMessage, userSessionObject.getLoginId());
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public Map<String, List<WishListDTO>> getStudentWishList()
	{
		return studentWishList;
	}

	public void setOccIndustryId(Integer occIndustryId)
	{
		this.occIndustryId = occIndustryId;
	}

	public int getWishListId()
	{
		return wishListId;
	}

	public void setWishListId(int wishListId)
	{
		this.wishListId = wishListId;
	}

	public String getWishId()
	{
		return wishId;
	}

	public void setWishId(String wishId)
	{
		this.wishId = wishId;
	}

	public Integer getOccIndustryId()
	{
		return occIndustryId;
	}

	public Set<IndustryDTO> getIndustriesForOcc()
	{
		return industriesForOcc;
	}

}
