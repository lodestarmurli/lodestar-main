<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<div class="edupath-padding-summary">
<div class="rows">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i><s:text name="View GSInputs Details"></s:text>
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
				<br/>
				<div class="row">
				  <div class="col-md-12" style="padding-left: 160px;">
				     <span class="error-block" id="dateError"></span>
				  </div>
				</div>
				<div class="row" style="padding: 0 10px;">
				 	<div class="col-md-12">
				 	  <div style="display: inline-block;">
				 	  	 <label class="bold"><s:text name="com.edupath.common.from"></s:text></label>
				      	 <s:textfield name="from" cssClass="date_input fromDate" id="fromId" readonly="true"></s:textfield>
				       </div>
				       
				       <div class="viewfeedback" style="display: inline-block;">	<!--changes 21/03/18 padding-left: 10px;-->
				         <label class="bold"><s:text name="com.edupath.common.to"></s:text> </label>
				         <s:textfield name="to" cssClass="date_input toDate" id="toId" readonly="true"></s:textfield>
				       </div>
				       
				       <div style="display: inline-block; padding-left: 10px;margin-top:5px;"><!--margin-top: 5px;  31/03/18--->
		           	   		<button class="btn blue" onclick="fnShowViewLog();"><s:text name="com.edupath.common.go"></s:text></button>
				       </div>
				       
				       <div class="export" style="display: inline-block;margin-top:5px;"><!--margin-top: 5px;  31/03/18--->
		           	   		<button class="btn blue" onclick="fnExportToExcel();"><s:text name="com.edupath.common.export.excel"></s:text></button>
				       </div>
					 </div>
					 
				</div>
				
				<div class="row" style="padding-bottom: 40px;">
				   <span class="error-block" id="dateError"></span>
				 	<div class="col-md-12">
				 	  <div style="display: inline-block;">
				      	 <div style="height: 20px; padding-left: 45px; width: 240px;">
				      	     <span class="error-block" id="fromError"></span>
				      	 </div>
				       </div>
				       
				       <div style="display: inline-block; padding-left: 10px;">
				          <div style="height: 20px; padding-left: 45px; width: 240px;">
				           <span class="error-block" id="toError"></span>
				         </div>
				       </div>
				       
				       <div style="display: inline-block; padding-left: 10px;">
				       </div>
				       
					 </div>
				</div>
			   
			   <div id="studentFeedbackId"></div>
			   <div id="parentFeedbackId"></div>	
			   
			   <div id="viewFeedbackLoading" class="display-hide cust-loader">
					<div class="app-spinner">
				<!-- 		<i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i> -->
						<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
					</div>
				</div>
						
			</div>		
		</div>
	</div>
</div>
</div>
<s:form name="ViewFeedbackForm" id="ViewFeedbackForm" method="POST">
	<s:hidden id="fromDate" name="fromDate"></s:hidden>
	<s:hidden id="toDate" name="toDate"></s:hidden>
	<s:hidden id="feedbackType" name="feedbackType"></s:hidden>
	<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
	<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
</s:form>	

<script>
var oTable;
	$(document).ready(function(){
		fnSetDefaultDate();
		//TableEdittableViewLogInit();
		$("#fromId").datepicker({
			format: 'dd-mm-yyyy',
			endDate: '0d',
			autoclose: true
		}).on('changeDate', function(selected){
			var startDate = new Date(selected.date.valueOf());
			$('#toId').datepicker('setStartDate', startDate);
		});
		$("#toId").datepicker({
			format: 'dd-mm-yyyy',
			autoclose: true
		}).on('changeDate', function(selected){
			var endDate = new Date(selected.date.valueOf());
			$('#fromId').datepicker('setEndDate', endDate);
		});
		$("#feedbackType").val('student');
		loadFeedback('student', 'studentFeedbackId');
	});
	
	function fnSetDefaultDate()
	{
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		
		if (dd < 10) {
			dd = '0' + dd;
		}
		if (mm < 10) {
			mm = '0' + mm;
		}
		
		today = dd+'-'+mm+'-'+yyyy;
		$(".date_input").val(today.trim());
	}
	//Start Sasedeve edited by Mrutyunjaya on date 20-07-2017
	$("#studentTabId, #parentTabId").click(function(){
		var feedbackType =  $(this).data("feedback-type");
		
		if(feedbackType=="student")
		{
			$("#newparentid").hide();
		}
		
		
		var divId = $(this).data("div-id");
		$("#feedbackType").val(feedbackType);
		loadFeedback(feedbackType, divId);
		
		
		
		
	});
	
	
	$("#parentTabId2, #parentTabId3").click(function(){
		var feedbackType =  $(this).data("feedback-type");
		var divId = $(this).data("div-id");
		$("#feedbackType").val(feedbackType);
		loadFeedback(feedbackType, divId);
		
		
		
		
	});
	
	$("#parentTabId1").click(function(){
		
		
		$("#feedbackType").val('parent2');
		loadFeedback('parent2', 'parentFeedbackId');
		$("#parentTabId2").removeClass("active");
		$("#parentTabId3").removeClass("active");
		$("#parentTabId").removeClass("active");
		$("#parentTabId2").addClass("active");
		$("#newparentid").show();
	});
	
	
	//End Sasedeve edited by Mrutyunjaya on date 20-07-2017
	
	function loadFeedback(feedbackType, divId)
	{
		if(fnIsValid())
		{
			$('#fromDate').val($('#fromId').val()+" 00:00 am");
			$('#toDate').val($('#toId').val()+" 11:59 pm");
			$('#feedbackType').val(feedbackType);
			var url = "${pageContext.request.contextPath}/myapp/getGSInputsByFilterGSinputsView";
			$("#viewFeedbackLoading").show();
			$.ajax({
				url : url,
				type : 'POST',
				data : ({
					'feedbackType' : feedbackType,
					'fromDate' : $('#fromDate').val(),
					'toDate' : $('#toDate').val()
				}),
				success : function(resp)
				{
					$("#viewFeedbackLoading").hide();
					$("#studentFeedbackId").hide();
					$("#studentFeedbackId").html("");
					$("#parentFeedbackId").hide();
					if (resp != null && resp != '') 
					{
						$('#'+divId).html(resp);
						$('#'+divId).show();
					}
				},
				error : function(msg, arg1, arg2)
				{
					$("#viewFeedbackLoading").hide();
					alert(msg.responseText);
					return false;
				}
			});
		}
	}
	
	function fnShowViewLog()
	{
		var feedbackType = $("#feedbackType").val();
		var divId;
		if (feedbackType == 'student') 
		{
			divId = 'studentFeedbackId';
		} 
		else 
		{
			divId = 'parentFeedbackId';
		}
		loadFeedback(feedbackType, divId);
	}
	
	function fnIsValid()
	{
		$('#fromError').html('');
		$('#toError').html('');
		if($('#fromId').val() == '' || $('#fromId').val() == undefined)
		{
			$('#fromError').html('This field required.');
			return false;
		}
		
		if($('#toId').val() == '' || $('#toId').val() == undefined)
		{
			$('#toError').html('This field required.');
			return false;
		}
		var fromDateArr = $('#fromId').val().split("-");
		var fromDate = new Date(fromDateArr[2], fromDateArr[1]-1, fromDateArr[0]);
		var toDateArr = $('#toId').val().split("-");
		var toDate = new Date(toDateArr[2], toDateArr[1]-1, toDateArr[0]);
		
		if(toDate < fromDate)
		{
			alert("To date cannot be less than From date");
			return false;
		}
		var oneDay = 1000*60*60*24;
		var dateDiff = (toDate.getTime() - fromDate.getTime())/oneDay;
		console.log(dateDiff);
		if(dateDiff > 30)
		{
			alert('From and To date must be between one month.');
			return false;
		}
		return true;
	}
	
	function TableEdittableViewLogInit(){
		var table = $('#sample_editable_1');
		oTable = table.dataTable({
			
			destroy : true,
			
			"lengthMenu" : [ [ 5, 10, 20, 50, 100, -1 ], [ 5, 10, 20, 50, 100, "All" ] // change
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
			"order" : [ [ 0, "asc" ] ],

		});
				
	}
	
	function fnExportToExcel()
	{
		var form = document.ViewFeedbackForm;
		form.action = "${pageContext.request.contextPath}/myapp/getReportGSinputsView";
		form.submit();
	}
</script>