<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
<c:when test="${ null ne viewLogList && not empty viewLogList}">
   <table class="table table-striped table-hover table-bordered table_action" id="sample_editable_1">
		<thead>
			<tr>
				<th style="width:200px"><s:text name="com.edupath.viewlog.actiontime"></s:text></th>
				<th><s:text name="com.edupath.viewlog.module"></s:text></th>
				<th><s:text name="com.edupath.viewlog.message"></s:text></th>
				<th><s:text name="com.edupath.viewlog.performedby"></s:text></th>
			</tr>
		</thead>
		
		<tbody>
		 <c:forEach items="${viewLogList}" var="viewlog">
			<tr>
				<td style="width:200px">
				    <fmt:formatDate pattern="dd-MMM-yyyy hh:mm:ss a" value="${viewlog.actionTime}"/>
				</td>
				<td>
					${utils:replaceXMLEntities(viewlog.module)}
				</td>
				<td>
					${utils:replaceXMLEntities(viewlog.message)}
				</td>
				<td>
					${utils:replaceXMLEntities(viewlog.performedBy)}
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</c:when>
<c:otherwise>
  <div class="empty_table_div" style="text-align: center; ">
   <span><s:text name="com.edupath.common.table"></s:text></span>
  </div>
</c:otherwise>			
</c:choose>	

<script type="text/javascript">

</script>	
