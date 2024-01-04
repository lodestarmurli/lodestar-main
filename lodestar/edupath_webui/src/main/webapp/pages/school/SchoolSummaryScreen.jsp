<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld" %>

<s:set name="editActionLabel"><s:text name="com.edupath.common.edit" /></s:set>
<s:set name="deleteActionLabel"><s:text name="com.edupath.common.delete" /></s:set>
<s:set name="schoolNameLabel"><s:text name="com.edupath.school.summary.name"/></s:set>
<s:set name="displayLogoLabel"><s:text name="com.edupath.school.summary.displaylogo"/></s:set>
<div class="edupath-padding-summary">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-edit"></i><s:text name="com.edupath.school.summary.portlet.header.label"/>
			</div>			
		</div>
		
		<div class="portlet-body">
		   
			<s:if test="hasActionMessages()">
					<div class="alert alert-success ">
						<span class="close" data-dismiss="alert"></span>
						<s:actionmessage/>
					</div>
			</s:if>
			 <s:if test="hasActionErrors()">
					<div class="alert alert-danger ">
						<span class="close" data-dismiss="alert"></span>
						<s:actionerror/>
					</div>
			</s:if>
			<div class="table-toolbar">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-11">
						<div class="btn-group pull-right">
							<button id="sample_editable_1_new" class="btn green" onclick="fnAddSchool()">
								<s:text name="com.edupath.common.add"/><i class="fa fa-plus" style="padding-left: 3px;"></i>	
							</button>	
						</div>
					</div>
				</div>
			</div>
			
			<s:form name="schoolDetailsSummaryForm" method="post">
				<s:hidden id="id" name="id"></s:hidden>
				<table class="table table-striped table-hover table-bordered table-facilitator" id="sample_editable_1">
					<thead>
						<tr>
							<th>${pageScope.editActionLabel}</th>
							<th>${pageScope.deleteActionLabel}</th>
							<th>${pageScope.schoolNameLabel}</th>
							<th>${pageScope.displayLogoLabel} </th>
						</tr>
					</thead>
					
					<tbody>
					<c:forEach items="${schoolList}" var="schoolDetail">
						<tr>
							<td align="center">
								<a class="delete" href="#" onclick = "fnEditSchool('${schoolDetail.id}')"> <i class="glyphicon glyphicon-edit operation-column" ></i></a>
							</td>
							<td align="center">
								<c:if test="${schoolDetail.studentCount == 0}">
									<a class="delete" href="#" onclick = "fnDeleteSchool('${schoolDetail.id}')"> <i class="glyphicon glyphicon-trash operation-column" ></i></a>
								</c:if>
							</td>
							<td>
								${utils:replaceXMLEntities(schoolDetail.name)}
							</td>
							<td>
								<c:if test="${schoolDetail.displaylogo == 1}">
									YES
								</c:if>
								<c:if test="${schoolDetail.displaylogo == 0}">
									NO
								</c:if>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</s:form>	
		</div>		
	</div>
</div>
	
<script type="text/javascript">

 var form = document.schoolDetailsSummaryForm;
 
	$(document).ready(function() {
		TableEdittableInit();
	});
	
	 function TableEdittableInit()
	 {
		 	
 		var table = $('#sample_editable_1');
 		var oTable = table.dataTable({

 			"lengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
 																		// per
 																		// page
 																		// values
 																		// here
 			],
 			// set the initial value
 			"pageLength" : 5,

 			"language" : {
 				"lengthMenu" : " _MENU_ records"
 			},
 			"columnDefs" : [ { // set default column settings
 				'orderable' : false,
 				'targets' : [ 0,1 ]
 			}, {
 				"searchable" : false,
 				"targets" : [ 0,1 ]
 			} ],
 			"order" : [ [ 2, "asc" ] ],

 		});
	 }
	 
	 function fnDeleteSchool(id)
	 {
		if (confirm('Are you sure you want to delete?')) 
		{
			$('#id').val(id);
			var urlVal = "${pageContext.request.contextPath}/myapp/deleteManageSchoolSummaryAction";
			form.action = urlVal;
			form.submit();
		}
	 } 
	 
	 function fnEditSchool(id)
	 {
		 $('#id').val(id);
		var urlVal = "${pageContext.request.contextPath}/myapp/ManageSchoolSubmitAction";
		form.action = urlVal;
		form.submit();
	 }
	 

	 function fnAddSchool()
	 {
		$('#id').val(0);
		var urlVal = "${pageContext.request.contextPath}/myapp/ManageSchoolSubmitAction";
		form.action = urlVal;
		form.submit();
	 } 
</script>