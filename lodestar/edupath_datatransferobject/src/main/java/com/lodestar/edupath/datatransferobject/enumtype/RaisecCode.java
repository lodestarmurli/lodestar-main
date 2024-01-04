package com.lodestar.edupath.datatransferobject.enumtype;

public enum RaisecCode
{
	R("Realistic"), I("Investigative"), A("Artistic"), S("Social"), E("Enterprising"), C("Conventional") ;

	private String	value;

	RaisecCode(String value)
	{
		this.value = value;
	}

	public String Value()
	{
		return value;
	}
}
