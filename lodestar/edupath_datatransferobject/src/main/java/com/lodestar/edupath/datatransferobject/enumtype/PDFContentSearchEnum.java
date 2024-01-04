package com.lodestar.edupath.datatransferobject.enumtype;

public enum PDFContentSearchEnum
{
	STUDENT_NAME("Name"), SCHOOL("School"), CLASS_AND_SECTION("Class & Section"), STUDENT_DETAILS("Student details");

	private String	text;

	private PDFContentSearchEnum(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public enum PDFContentReplaceEnum
	{
		REPLACE_STUDENT_NAME("Name "), REPLACE_SCHOOL("School "), REPLACE_CLASS_AND_SECTION("Class & Section ");

		private String	text;

		private PDFContentReplaceEnum(String text)
		{
			this.text = text;
		}

		public String getText()
		{
			return text;
		}
	}
}
