/**
 * 
 */
package com.lodestar.edupath.util.datatable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yogesh singh
 *         Jul 10, 2016
 *         DataTableAnnotation.java
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(
{
	ElementType.FIELD
})
public @interface DataTableAnnotation
{
	boolean exclude() default true;

	// NOTE : for multiple column sort - provide comma separated columns without any space 
	String tableColumnName();

	String displayColumnName();
}