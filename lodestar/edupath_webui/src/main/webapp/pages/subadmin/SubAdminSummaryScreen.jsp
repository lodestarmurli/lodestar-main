 <%@ taglib uri="/struts-tags" prefix="s"%>   
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<s:form name="SubAdminForm" id="SubAdminForm" method="POST">
<s:hidden id="subAdminId" name="id"></s:hidden>
<s:hidden id="userId" name="userId"></s:hidden>
<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 

<div class="edupath-padding-summary">
<div class="rows">
<div class="col-md-12">
<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-edit"></i><s:text name="com.edupath.subadmin.summary.title"></s:text>
			</div>			
		</div>
		
		<div class="portlet-body">
		  <s:if test="hasActionErrors()">
					<div class="alert alert-danger ">
						<button class="close" data-close="alert"></button>
						<s:actionerror/>
					</div>
			</s:if>
		   
			<s:if test="hasActionMessages()">
					<div class="alert alert-success ">
						<button class="close" data-close="alert"></button>
						<s:actionmessage/>
					</div>
			</s:if>
			<div class="table-toolbar">
				<div class="row">
				<div class="col-md-6">
					<div class="btn-group">
					 </div>
					</div>
					<div class="col-md-6">
						<div class="btn-group pull-right">
							<button  class="btn green" onclick="fnAddSubAdmin();">
								<s:text name="com.edupath.common.add"/> <i class="fa fa-plus"></i>	
							</button>	
						</div>
					</div>
				</div>
			</div>
			
<c:choose>
<c:when test="${ null ne subAdminList && not empty subAdminList}">
<%-- <c:when test="${ null eq subAdminList && empty subAdminList}"> --%>
  <table class="table table-striped table-hover table-bordered table_action" id="sample_editable_1">
				<thead>
					<tr>
						<th width="25" class="custom-table-action"><s:text name="com.edupath.common.edit"></s:text></th>
						<th class="custom-table-action"><s:text name="com.edupath.common.delete"></s:text></th>
						<th><s:text name="com.edupath.subadmin.summary.name"></s:text></th>
						<th><s:text name="com.edupath.subadmin.summary.email"></s:text></th>
						<th><s:text name="com.edupath.subadmin.summary.contactnumber"></s:text></th>
					</tr>
				</thead>
				
				<tbody>
				 <c:forEach items="${subAdminList}" var="subadmin">
					<tr>
						<td align="center">
							<a class="delete" href="#" onclick="fnEditSubAdmin('${subadmin.id}', '${subadmin.userId}')"> <i class="glyphicon glyphicon-edit operation-column" ></i></a>
						</td>
						<td align="center">
							<a class="delete" href="#" onclick="fnDeleteSubAdmin('${subadmin.id}', '${subadmin.userId}')"><i class="glyphicon glyphicon-trash operation-column" ></i></a>
						</td>
						<td>
							${utils:replaceXMLEntities(subadmin.name)}
						</td>
						<td>
							${utils:replaceXMLEntities(subadmin.emailId)}
						</td>
						<td align="right">
							${utils:replaceXMLEntities(subadmin.contactNumber)}
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
</c:when>
<c:otherwise>
  <div class="empty_table_div">
   <span><s:text name="com.edupath.common.table"></s:text></span>
  </div>
</c:otherwise>			
</c:choose>		
		</div>		
	</div>
	</div>
	</div>
</div>
</s:form>			
<script type="text/javascript">
var form = document.SubAdminForm
jQuery(document).ready(function() {
	TableEditable.init();
});

function fnAddSubAdmin()
{
	form.action = "${pageContext.request.contextPath}/myapp/addScreenManageSubAdminAction";
	form.submit();
}

function fnDeleteSubAdmin(id, userId)
{
	if (confirm('Are you sure you want to delete?')) 
	{
		$('#subAdminId').val(id);
		$('#userId').val(userId);
		form.action = '';
		form.action = "${pageContext.request.contextPath}/myapp/deleteSubAdminSubmit";
		form.submit();
	}
}

function fnEditSubAdmin(id, userId)
{
	$('#subAdminId').val(id);
	$('#userId').val(userId);
	form.action = '';
	form.action = "${pageContext.request.contextPath}/myapp/editScreenManageSubAdminAction";
	form.submit();
}
</script>
<style>

</style>
</html>