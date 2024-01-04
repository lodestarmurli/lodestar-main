package com.lodestar.edupath.datatransferobject.enumtype;

public enum StudentTypeEnum
{
	TRIAL("Trial"), FULL("Full");
//	ORGROUP("ORGROUP");

	private String	text;

	private StudentTypeEnum(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return this.text;
	}

	public String getName()
	{
		return this.name();
	}

	public enum StudentTestTakenEnum
	{
		YES("Yes"), NO("No");
		private String	text;

		private StudentTestTakenEnum(String text)
		{
			this.text = text;
		}

		public String getText()
		{
			return text;
		}

		public String getName()
		{
			return this.name();
		}
	}

}
