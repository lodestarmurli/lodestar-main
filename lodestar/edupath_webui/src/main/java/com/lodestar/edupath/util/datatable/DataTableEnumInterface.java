/**
 * 
 */
package com.lodestar.edupath.util.datatable;

/**
 * @author yogesh singh
 *         Jul 10, 2016
 *         DataTableEnum.java
 */

public interface DataTableEnumInterface
{
	public enum DataTableEnum implements DataTableEnumInterface
	{
		ORDER_ASC("asc"), ORDER_DESC("desc");

		private String	order;

		DataTableEnum(String order)
		{
			this.order = order;
		}

		public String getOrder()
		{
			return this.order;
		}
	}

	public enum ActionPermissionEnum
	{
		AUT_CREATE("create"), AUT_UPDATE("update"), AUT_DELETE("remove"), AUTH_VIEW("view");

		private String	jsonKey;

		private ActionPermissionEnum(String jsonKey)
		{
			this.jsonKey = jsonKey;
		}

		public String getJSONKey()
		{
			return this.jsonKey;
		}
	}
}
