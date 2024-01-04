<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld" %>
<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- END PAGE LEVEL PLUGINS -->
<s:set name="editActionLabel"><s:text name="com.edupath.common.edit" /></s:set>
<s:set name="deleteActionLabel"><s:text name="com.edupath.common.delete" /></s:set>
<s:set name="facilitatorNameLabel"><s:text name="com.edupath.facilitator.summary.name"/></s:set>
<s:set name="emailLabel"><s:text name="com.edupath.facilitator.summary.email"/></s:set>
<s:set name="contactNumberLabel"><s:text name="com.edupath.facilitator.summary.contactnumber"/></s:set>
<s:set name="residenceAreaLabel"><s:text name="com.edupath.facilitator.summary.area"/></s:set>
<s:set name="typeLabel"><s:text name="com.edupath.facilitator.details.type"/></s:set>
<s:set name="isActiveLabel"><s:text name="com.edupath.facilitator.summary.active"/></s:set>
<div class="edupath-padding-summary">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-edit"></i><s:text name="com.edupath.facilitator.summary.portlet.header.label"/>
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
					<div class="col-md-1">
						<div class="btn-group">    <!-- added 31/03/18 style="margin-bottom: -33px;  -->
							<button id="sample_editable_1_new" class="btn green" style="margin-bottom: -33px;" onclick="fnShowUploadDialog()">
								<s:text name="com.edupath.common.upload"/> <i class="fa fa-plus"></i>	
							</button>
						</div>
					</div>
					
					<div class="col-md-11">
						<div class="btn-group pull-right">
							<button id="sample_editable_1_new" class="btn green" onclick="fnAddFacilitatorDetails()">
								<s:text name="com.edupath.common.add"/><i class="fa fa-plus" style="padding-left: 3px;"></i>	
							</button>	
						</div>
					</div>
				</div>
			</div>
			
			<s:form name="facilitatorDetailsSummaryForm" method="post">
			<s:hidden id="id" name="id"></s:hidden>
			<s:hidden id="userId" name="userId"></s:hidden>
			<s:hidden id="emailId" name="emailId"></s:hidden>
			<table class="table table-striped table-hover table-bordered table-facilitator" id="sample_editable_1">
				<thead>
					<tr>
						<th>${pageScope.editActionLabel}</th>
						<th>${pageScope.deleteActionLabel}</th>
						<th>${pageScope.facilitatorNameLabel}</th>
						<th>${pageScope.emailLabel}</th>
						<th>${pageScope.contactNumberLabel}</th>
						<th>${pageScope.residenceAreaLabel}</th>
						<th>${pageScope.typeLabel}</th>
						<th>${pageScope.isActiveLabel}</th>
					</tr>
				</thead>
				
				<tbody>
				<c:forEach items="${facilitatorDetailsList}" var="facilitatorDetails">
					<tr>
						<td align="center">
							<a class="delete" href="#" onclick = "fnEditFacilitatorDetails('${facilitatorDetails.id}')"> <i class="glyphicon glyphicon-edit operation-column" ></i></a>
						</td>
						<td align="center">
							<a class="delete" href="#" onclick = "fnDeleteFacilitatorDetails('${facilitatorDetails.id}', '${facilitatorDetails.userId}', '${facilitatorDetails.emailId}')"> <i class="glyphicon glyphicon-trash operation-column" ></i></a>
						</td>
						<td>
						 <a href="#" onclick="fnShowFacilitatorDetails('${facilitatorDetails.id}')"> 
							${facilitatorDetails.name}
						 </a> 
						</td>
						<td>
							${facilitatorDetails.emailId}
						</td>
						<td>
							${facilitatorDetails.phoneNumber}
						</td>
						<td>
							${facilitatorDetails.areaAddr}
						</td>
						<td>
							${facilitatorDetails.type}
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${facilitatorDetails.isActive}"><i class="fa fa-check"></i></c:when>
								<c:otherwise><i class="fa fa-times"></i></c:otherwise>
							</c:choose>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div id="facilitatorDetailsUploadDiv" class="modal" role="dialog">
			
			</div>
			
			</s:form>	
		</div>		
	</div>
</div>	
<script type="text/javascript">

 var form = document.facilitatorDetailsSummaryForm;
 var faci = "${facilitatorDetailsList}";
 function fnShowUploadDialog()
 {
	$("#facilitatorDetailsUploadDiv").modal({
		keyboard:false,
		backdrop: 'static'
	});
 	$("#facilitatorDetailsUploadDiv").html('<div class="loader"><img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif"></div>')
 	var url = "${pageContext.request.contextPath}/myapp/showExcelTemplate";
 	$.ajax({
 		url: url,
 		global: false,
 		type: "POST",
 		data:({ 
 			"moduleName" : 'Facilitator'
 		}),
 		success: function(resp)
 		{
 			if(resp != "" && resp != null)
 			{
 				$("#facilitatorDetailsUploadDiv").html(resp);
 			}	
 		},
 		error :  function(msg,arg1,arg2)
 		{
 			return false;
 		}
 	});
 	//$("#facilitatorDetailsUploadDiv").dialog("open");
 }
 
 
 
 function fnDeleteFacilitatorDetails(id , userId , emailId)
 {
	if (confirm('Are you sure you want to delete?')) 
	{
		$('#id').val(id);
		$('#userId').val(userId);
		$('#emailId').val(emailId);
		var urlVal = "${pageContext.request.contextPath}/myapp/deleteManageFacilitatorAction";
		form.action = urlVal;
		form.submit();
	}
 } 
 
 function fnEditFacilitatorDetails(id)
 {
	 $('#id').val(id);
	var urlVal = "${pageContext.request.contextPath}/myapp/getDataManageFacilitatorAction";
	form.action = urlVal;
	form.submit();
 }
 
function fnShowFacilitatorDetails(id)
 {
	 $('#id').val(id);
	 var urlVal = "${pageContext.request.contextPath}/myapp/showDataManageFacilitatorAction";
	form.action = urlVal;
	form.submit(); 
 } 

 function fnAddFacilitatorDetails()
 {
	 $('#id').val("0");
	 $('#userId').val("0");
	 $('#emailId').val("");
	var urlVal = "${pageContext.request.contextPath}/myapp/getDataManageFacilitatorAction";
	form.action = urlVal;
	form.submit();
 } 

	$(document).ready(function() {
		TableEdittableInit();
	});
	
	 function TableEdittableInit(){
		 	
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
	 				'targets' : [ 0,1,7 ]
	 			}, {
	 				"searchable" : false,
	 				"targets" : [ 0,1,7 ]
	 			} ],
	 			"order" : [ [ 2, "asc" ] ],

	 		});
	 }
</script>