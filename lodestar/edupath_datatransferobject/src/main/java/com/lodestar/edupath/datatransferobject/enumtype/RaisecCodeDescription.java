package com.lodestar.edupath.datatransferobject.enumtype;

public enum RaisecCodeDescription {
	R("Realistic People are Do-ers! They are active & don’t like slowing down. The kind of careers suitable for them are roles that have a hands on or practical element to it."),
	I("Investigative people are Thinkers! They are curious & love learning. The kind of roles suitable for them are roles that have a analytical or theoretical element to it."),
	A("Artistic people are Creators! They are creative & love to try new things. The kind of careers suitable for them are roles that need out of the box thinking and are idea based."),
	S("Social people are Helpers! They love helping others & solving other peoples problems. The kind of careers suitable for them are roles that involve facing people and require coordination."),
	E("Enterprising people are Leaders! They love to take charge of any situation & can convince people. The kind of careers suitable for them include teaching others & helping others."),
	C("Conventional people are Organisers! They are responsible & organised with their things. The kind of careers suitable for them include roles that have fixed steps or processes and have set rules.");

	private String value;

	RaisecCodeDescription(String value) {
		this.value = value;
	}

	public String Value() {
		return value;
	}
}
