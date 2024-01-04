<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld" %>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- END PAGE LEVEL PLUGINS -->
<!-- Start SASEDEVE edited By Mrutyunjaya on Date 12-06-2017 -->

<s:form name="PaymentFormUI" id="PaymentFormUI" target="paymentui">

<s:hidden name="studenid" id="studenid" ></s:hidden>
<s:hidden name="loginid" id="loginid" ></s:hidden>
<s:hidden name="agreeamount" id="agreeamount" ></s:hidden>
<s:hidden name="dueamount" id="dueamount" ></s:hidden>
												
</s:form>
											
<!-- END SASEDEVE edited By Mrutyunjaya on Date 12-06-2017 -->											

<s:set name="alertLabel"><s:text name="com.edupath.facilitator.student.summary.alert"/></s:set>
<s:set name="nameLabel"><s:text name="com.edupath.facilitator.student.summary.name"/></s:set>
<s:set name="emailLabel"><s:text name="com.edupath.facilitator.student.summary.emailid"/></s:set>
<s:set name="contactLabel"><s:text name="com.edupath.facilitator.student.summary.contactnumber"/></s:set>
<s:set name="schoolLabel"><s:text name="com.edupath.facilitator.student.summary.schoolname"/></s:set>
<s:set name="session1Label"><s:text name="com.edupath.facilitator.student.summary.session1"/></s:set>
<s:set name="session2Label"><s:text name="com.edupath.facilitator.student.summary.session2"/></s:set>
<s:set name="session3Label"><s:text name="com.edupath.facilitator.student.summary.session3"/></s:set>
<!-- Start Sasedeve edited by vyankatesh  on date 27-01-2017 -->
<s:set name="venue"><s:text name="com.edupath.facilitator.student.summary.venue"/></s:set>

<!-- End Sasedeve edited by vyankatesh  on date 27-01-2017 -->

<!-- Start Sasedeve edited by vyankatesh  on date 30-01-2017 -->
<s:set name="dueamount"><s:text name="com.edupath.facilitator.student.summary.dueamount"/></s:set>

<s:set name="city"><s:text name="com.edupath.facilitator.student.summary.city"/></s:set>

<!-- End Sasedeve edited by vyankatesh  on date 30-01-2017 -->

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
			<div class="table-toolbar">
				<div class="row">
					<div class="col-md-7 col-xs-12" style="margin-bottom:7px;">   <!-- add col-xs-12  23/03/18 add  margin-bottom:7px; 03/04/18 -->
						<label class="bold"><s:text name="com.edupath.facilitator.student.summary.select.date"/></label>&nbsp;:&nbsp;<input type="text" name="sessionDate" id="sessionDate" readonly="readonly" class="globle_txt">
						<div class="btn-group">
							<button id="sample_editable_1_new" class="btn green" onclick="fnGetFacilitatorStudentDetails()">
								<s:text name="com.edupath.common.go"/>	
							</button>	
						</div>
					</div>
					<div class="col-md-5 col-xs-12">  <!-- add col-xs-12  23/03/18 -->
						<label class="bold"><s:text name="com.edupath.facilitator.student.summary.global.search"/></label>&nbsp;:&nbsp;<input type="text" name="studentloginId" id="studentloginId" class="globle_txt">
						<div class="btn-group">
							<button id="sample_editable_1_new" class="btn green" onclick="fnSearchFacilitatorStudentDetails()">
								<s:text name="com.edupath.facilitator.student.summary.search"/>	
							</button>	
						</div>
					</div>
				</div>
			</div>
			
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
						<th style="width: 100px;">${pageScope.nameLabel}</th>
						<th>${pageScope.emailLabel}</th>
						<th>${pageScope.contactLabel}</th>
						<th>${pageScope.schoolLabel}</th>
						<th>${pageScope.session1Label}</th>
						<th>${pageScope.session2Label}</th>
						<th>${pageScope.session3Label}</th>
						<!-- Start Sasedeve edited by vyankatesh  on date 27-01-2017 -->
							<th>${pageScope.venue}</th>
						
						<!-- End Sasedeve edited by vyankatesh  on date 27-01-2017 -->
						
						<!-- Start Sasedeve edited by vyankatesh  on date 30-01-2017 -->
							<th>${pageScope.dueamount}</th>
						<!-- End Sasedeve edited by vyankatesh  on date 30-01-2017 -->
						<th>${pageScope.city}</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${studentDetailsList}" var="studentDetails">
					<tr>
						<td>
							<c:if test="${null !=  studentDetails.studentAlertMessage and studentDetails.studentAlertMessage != ''}">
								<span class="fa fa-exclamation" style="color:orange" data-toggle="alertpopover"  data-content="${studentDetails.studentAlertMessage}" data-trigger="hover" data-placement="right"></span>
							</c:if>
						 <a href="#" onclick='fnGetStudentDetails("${studentDetails.id}", fnReplaceXMLEntities("${utils:replaceXMLEntities(studentDetails.name)}"), "${studentDetails.loginId}", "${studentDetails.userId}", "${studentDetails.cityId}", "${show}", "${studentDetails.fatheremailId}")'> 
							${utils:replaceXMLEntities(studentDetails.name)}
						 </a> 
						</td>
						<td>
							${utils:replaceXMLEntities(studentDetails.fatheremailId)}
						</td>
						<td>
							<p>F: ${utils:replaceXMLEntities(studentDetails.fatherContact)}</p>
							<p>S: ${utils:replaceXMLEntities(studentDetails.studentContact)}</p>
							<p>M: ${utils:replaceXMLEntities(studentDetails.motherContact)}</p>
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
						<!-- Start Sasedeve Edited by Vyankatesh on date 27-01-2017 -->
						<td>
						
							${utils:replaceXMLEntities(studentDetails.venue)}
						</td>
						<!-- end Sasedeve Edited by Vyankatesh on date 27-01-2017 -->
						<!-- Start Sasedeve Edited by Vyankatesh on date 30-01-2017 -->
						<td>
						
						<c:choose>
							    <c:when test="${studentDetails.dueAmount != null}">
							       
							       ${studentDetails.dueAmount}
						<c:if test="${zohoVlaue eq 1 }">
							       
							       <a href="https://forms.zohopublic.com/zohotarun116/form/LodestarFeeCollectionForm/
												formperma/5294kHkKad49EgkG1J433_F20?
											referrername=${session.UserSessionObject.fullName}&&
											SingleLine=${utils:replaceXMLEntities(studentDetails.name)}&&
											Email=${utils:replaceXMLEntities(studentDetails.fatheremailId)}&&
											Email1=${session.UserSessionObject.loginId}&&
											Currency=${utils:replaceXMLEntities(studentDetails.agreedAmount)}&&
											SingleLine5=${utils:replaceXMLEntities(studentDetails.loginId)}&&
											SingleLine7=${utils:replaceXMLEntities(studentDetails.cityName)}&&
											SingleLine1=${utils:replaceXMLEntities(studentDetails.fathername)}&&
											PhoneNumber=${utils:replaceXMLEntities(studentDetails.contactNumber)}" target="ZOHO">Link</a>
											
											
											</c:if>
							    </c:when>
							    <c:otherwise>
							      <s:text name="com.edupath.common.no.record.found"/>
							      <c:if test="${zohoVlaue eq 1 }">
							      
							       <a href="https://forms.zohopublic.com/zohotarun116/form/LodestarFeeCollectionForm/
												formperma/5294kHkKad49EgkG1J433_F20?
											referrername=${session.UserSessionObject.fullName}&&
											SingleLine=${utils:replaceXMLEntities(studentDetails.name)}&&
											Email=${utils:replaceXMLEntities(studentDetails.fatheremailId)}&&
											Email1=${session.UserSessionObject.loginId}&&
											Currency=0&&
											SingleLine5=${utils:replaceXMLEntities(studentDetails.loginId)}&&
											SingleLine7=${utils:replaceXMLEntities(studentDetails.cityName)}&&
											SingleLine1=${utils:replaceXMLEntities(studentDetails.fathername)}&&
											PhoneNumber=${utils:replaceXMLEntities(studentDetails.contactNumber)}" target="ZOHO">Link</a>
											
										
											</c:if>
							    </c:otherwise>
							</c:choose>
							
						</td>
						<!-- End Sasedeve Edited by Vyankatesh on date 30-01-2017 -->
						<td>
							${utils:replaceXMLEntities(studentDetails.cityName)}
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
	
	 function fnGetFacilitatorStudentDetails()
	 {
		date = $("#sessionDate").val()
		if(date == "" || date == null){
			alert("please select the date")
			return false;
		} 
		var urlVal = "${pageContext.request.contextPath}/myapp/FacilitatorStudentSummaryAction?date="+date;
		form.action = urlVal;
		form.submit();
	 }
	 
	 function fnSearchFacilitatorStudentDetails()
	 {
		loginId = $("#studentloginId").val()
		if(loginId == "" || loginId == null){
			alert("Please enter login id")
			return false;
		} 
		var urlVal = "${pageContext.request.contextPath}/myapp/searchDataFacilitatorStudentSummaryAction?studLoginId="+loginId;
		form.action = urlVal;
		form.submit();
	 }
	 
	 function fnGetStudentDetails(id , name , loginId, userId, cityId, show, fatheremailId)
	 {
		 if (show != null && show != "") 
		 {
			 if (confirm('Are you sure you want to view this student details?')) {
				 $("#id").val(id);
				 $("#name").val(name);
				 $("#loginId").val(loginId);
				 $("#userId").val(userId);
				 $("#cityId").val(cityId);
				 $("#fatheremailId").val(fatheremailId);
					var urlVal = "${pageContext.request.contextPath}/myapp/getDetailsFacilitatorStudentSummaryAction";
					form.action = urlVal;
					form.submit();
				}
		 }
		 else
		 {
			 $("#id").val(id);
			 $("#name").val(name);
			 $("#loginId").val(loginId);
			 $("#userId").val(userId);
			 $("#cityId").val(cityId);
			 $("#fatheremailId").val(fatheremailId);
				var urlVal = "${pageContext.request.contextPath}/myapp/getDetailsFacilitatorStudentSummaryAction";
				form.action = urlVal;
				form.submit();
		 }
	 }
	
	 function fnSetDefaultDate(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1;
		var yyyy = today.getFullYear();
		
		if (dd < 10) {
			dd = '0' + dd
		}
		if (mm < 10) {
			mm = '0' + mm
		}
		
		today = mm+'/'+dd+'/'+yyyy;
		$("#sessionDate").val(today.trim());
	 }
	 
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
	 				'targets' : [ ]
	 			}, {
	 				"searchable" : false,
	 				"targets" : [ ]
	 			} ],
	 			drawCallback: function() {
	 				$('[data-toggle="alertpopover"]').popover();
	 			  } 
	 			//"order" : [ [ 0, "asc" ] ],

	 		});
	 }
	 
	
	 
	 //Start SASEDEVE Edited By Mrutyunjaya on Date 12-06-2017
	 
	 function Paymentformsubmit()
	 {
		 var paymentform1 = document.PaymentFormUI;
		 
		 
		 paymentform1.action="${pageContext.request.contextPath}/";
		 paymentform1.submit();
		 
		 
	 }
	 
	 //End SASEDEVE Edited By Mrutyunjaya on Date 12-06-2017
	 
	 
</script>