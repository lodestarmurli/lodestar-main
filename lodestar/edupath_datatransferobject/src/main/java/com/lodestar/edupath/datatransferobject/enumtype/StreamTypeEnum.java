package com.lodestar.edupath.datatransferobject.enumtype;

public enum StreamTypeEnum
{
	SCIENCE_MATH(1,"Science with Maths"), SCIENCE_BIO(2,"Science with Biology"), COMMERCE_MATH(3,"Commerce with Maths"), COMMERCE_WOMATH(4,"Commerce without Maths"), ARTS(5,"Arts"),  SCIENCE_WO_MATH(6,""), COMMERCE(7,"");

	private int	weight;
	private String displayName;

	StreamTypeEnum(int weight,String displayName)
	{
		this.weight = weight;
		this.displayName=displayName;
	}

	public int getWeight()
	{
		return weight;
	}

	public String getDisplayName() {
		return displayName;
	}

}
