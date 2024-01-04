<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld" %>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- END PAGE LEVEL PLUGINS -->


<s:form name="PaymentFormUI" id="PaymentFormUI" target="paymentui">

<s:hidden name="studenid" id="studenid" ></s:hidden>
<s:hidden name="loginid" id="loginid" ></s:hidden>
<s:hidden name="agreeamount" id="agreeamount" ></s:hidden>
<s:hidden name="dueamount" id="dueamount" ></s:hidden>
												
</s:form>
											

<s:set name="alertLabel"><s:text name="com.edupath.facilitator.student.summary.alert"/></s:set>
<s:set name="nameLabel"><s:text name="com.edupath.facilitator.student.summary.name"/></s:set>
<s:set name="emailLabel"><s:text name="com.edupath.facilitator.student.summary.emailid"/></s:set>
<s:set name="contactLabel"><s:text name="com.edupath.facilitator.student.summary.contactnumber"/></s:set>
<s:set name="schoolLabel"><s:text name="com.edupath.facilitator.student.summary.schoolname"/></s:set>
<s:set name="session1Label"><s:text name="com.edupath.facilitator.student.summary.session1completed"/></s:set>
<s:set name="session2Label"><s:text name="com.edupath.facilitator.student.summary.session2completed"/></s:set>
<s:set name="session3Label"><s:text name="com.edupath.facilitator.student.summary.session3completed"/></s:set>
<s:set name="loginIdLabel"><s:text name="com.edupath.student.summary.loginid"/></s:set>

<s:set name="facilitatorNameLabel"><s:text name="com.edupath.viewfeedback.summary.facilitator.name"/></s:set>
<s:set name="reportGeneratedLabel"><s:text name="com.edupath.facilitator.student.summary.Report.Generated.date"/></s:set>




<div class="edupath-padding-summary">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-edit"></i><s:text name="com.edupath.facilitator.student.report.completed"/>
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
			
			
			<s:form name="facilitatorStudentSummaryForm" method="post">
			<s:hidden name="id" id="id"></s:hidden>
			<s:hidden name="name" id="name"></s:hidden>
			<s:hidden name="loginId" id="loginId"></s:hidden>
			<s:hidden name="userId" id="userId"></s:hidden>
			<s:hidden name="cityId" id="cityId"></s:hidden>
			<s:hidden name="fatheremailId" id="fatheremailId"></s:hidden>
			<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
				<thead>
					<tr>
						<th>${pageScope.loginIdLabel}</th>
						<th style="width: 100px;">${pageScope.nameLabel}</th>
						<th>${pageScope.facilitatorNameLabel}</th>
						<th>${pageScope.session1Label}</th>
						<th>${pageScope.session2Label}</th>
						<th>${pageScope.session3Label}</th>
						<th>${pageScope.reportGeneratedLabel}</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${studentDetailsList}" var="studentDetails">
					<tr>
						<td>
							${utils:replaceXMLEntities(studentDetails.loginId)} 
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.name)} 
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.facilitatorName)}
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.session1FaciCompletedDateStr)}
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.session2FaciCompletedDateStr)}
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.session3FaciCompletedDateStr)}
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.reportGeneratedDateStr)}
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
var form = document.facilitatorStudentSummaryForm;
	$(document).ready(function() {
		TableEdittableInit();
		$("#studentloginId").val('${studLoginId}');
		if ('${sessionDate}' == "") 
		{
			//fnSetDefaultDate();
		}
		else 
		{
			$("#sessionDate").val('${sessionDate}');
		}		
		
		$("#sessionDate").datepicker({
	        "autoclose": true
		});
		
		$("#sessionDate").datepicker({
		});
		
		$('[data-toggle="alertpopover"]').popover();
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
	 				'orderable' : true,
	 				'targets' : [ 0,1,2,3,4,5,6]
	 			}, {
	 				"searchable" : true,
	 				"targets" : [ 0,1,2,3,4,5,6]
	 			} ],
	 			"order": [
                    [6, "dsc"]
                ] ,
	 			drawCallback: function() {
	 				$('[data-toggle="alertpopover"]').popover();
	 			  } 
	 			//"order" : [ [ 0, "asc" ] ],

	 		});
	 }
	 
	
	 
	
	 

	 
	 
</script>