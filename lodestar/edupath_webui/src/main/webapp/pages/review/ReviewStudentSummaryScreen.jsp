<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld" %>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- END PAGE LEVEL PLUGINS -->

<s:set name="nameLabel"><s:text name="com.edupath.facilitator.student.summary.name"/></s:set>
<s:set name="emailLabel"><s:text name="com.edupath.facilitator.student.summary.emailid"/></s:set>
<s:set name="contactLabel"><s:text name="com.edupath.facilitator.student.summary.contactnumber"/></s:set>
<s:set name="schoolLabel"><s:text name="com.edupath.facilitator.student.summary.schoolname"/></s:set>
<s:set name="session1Label"><s:text name="com.edupath.facilitator.student.summary.session1"/></s:set>
<s:set name="session2Label"><s:text name="com.edupath.facilitator.student.summary.session2"/></s:set>
<s:set name="session3Label"><s:text name="com.edupath.facilitator.student.summary.session3"/></s:set>

<div class="edupath-padding-summary">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-edit"></i><s:text name="com.edupath.facilitator.student.summary.portlet.header.label"/>
			</div>			
		</div>
		
		<div class="portlet-body">
			<s:if test="hasActionMessages()">
					<div class="alert alert-success ">
						<button class="close" data-close="alert"></button>
						<s:actionmessage/>
					</div>
			</s:if>
			 <s:if test="hasActionErrors()">
					<div class="alert alert-danger ">
						<button class="close" data-close="alert"></button>
						<s:actionerror/>
					</div>
			</s:if>
			
			<table class="table table-striped table-hover table-bordered" id="review_summary_editable">
				<thead>
					<tr>
						<th style="width: 100px;">${pageScope.nameLabel}</th>
						<th>${pageScope.emailLabel}</th>
						<th>${pageScope.contactLabel}</th>
						<th>${pageScope.schoolLabel}</th>
						<th>${pageScope.session1Label}</th>
						<th>${pageScope.session2Label}</th>
						<th>${pageScope.session3Label}</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${studentDetailsList}" var="studentDetails">
					<tr>
						<td>
							 <a href="#" onclick="fnGetStudentDetails('${studentDetails.id}', '${studentDetails.name}', '${studentDetails.loginId}', '${studentDetails.userId}', '${studentDetails.cityId}', true, '${studentDetails.fatheremailId}')"> 
								${utils:replaceXMLEntities(studentDetails.name)}
							 </a> 
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.fatheremailId)}
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.contactNumber)}
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.schoolName)}
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.session1DateStr)}
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.session2DateStr)}
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.session3DateStr)}
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<s:form name="facilitatorStudentSummaryForm" method="post">
				<s:hidden name="id" id="id"></s:hidden>
				<s:hidden name="name" id="name"></s:hidden>
				<s:hidden name="loginId" id="loginId"></s:hidden>
				<s:hidden name="userId" id="userId"></s:hidden>
				<s:hidden name="cityId" id="cityId"></s:hidden>
				<s:hidden name="isReview" id="isReview"></s:hidden>
				<s:hidden name="fatheremailId" id="fatheremailId"></s:hidden>
			</s:form>	
		</div>		
	</div>
</div>
	
<script type="text/javascript">
var form = document.facilitatorStudentSummaryForm;
	$(document).ready(function() {
		TableEdittableInit();
	});
	
	 function fnGetStudentDetails(id , name , loginId, userId, cityId, isReviewer, fatheremailId)
	 {
		 $("#id").val(id);
		 $("#name").val(name);
		 $("#loginId").val(loginId);
		 $("#userId").val(userId);
		 $("#cityId").val(cityId);
		 $("#isReview").val(isReviewer);
		 $("#fatheremailId").val(fatheremailId);
		 var urlVal = "${pageContext.request.contextPath}/myapp/getDetailsFacilitatorStudentSummaryAction";
		 form.action = urlVal;
		 form.submit();
	 }
	 
	 function TableEdittableInit(){
		 	
	 		var table = $('#review_summary_editable');

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
	 				'targets' : [ ]
	 			}, {
	 				"searchable" : false,
	 				"targets" : [ ]
	 			} ],
	 			//"order" : [ [ 0, "asc" ] ],

	 		});
	 }
</script>