package com.lodestar.edupath.datatransferobject.enumtype;

public enum RoleTypeEnum
{
	ADMIN(1), USER(2), FACILITATOR(3), SUBADMIN(4), REPORTADMIN(5), STUDENTOR(6);

	private int	weight;

	RoleTypeEnum(int weight)
	{
		this.weight = weight;
	}

	public int getWeight()
	{
		return weight;
	}
}
