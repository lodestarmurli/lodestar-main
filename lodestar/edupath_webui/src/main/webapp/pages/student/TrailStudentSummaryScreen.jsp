
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<script
	src="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.js"
	type="text/javascript"></script>
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/thirdparty/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/thirdparty/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/thirdparty/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<s:form name="StudentSummaryForm" id="StudentSummaryForm" method="POST">
	<s:hidden id="stdId" name="studentId"></s:hidden>
	<s:hidden id="userId" name="userId"></s:hidden>
	<s:hidden id="openSessionType" name="openSessionType"/>
	<s:hidden name="parentSelectedSidebarMenuId"
		id="parentSelectedSidebarMenuId" />
	<s:hidden name="childSelectedSidebarMenuId"
		id="childSelectedSidebarMenuId" />
	<div class="edupath-padding-summary">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>
						<s:text name="com.edupath.trail.student.summary.title"></s:text>
					</div>
				</div>

				<div class="portlet-body">
					<s:if test="hasActionErrors()">
						<div class="alert alert-danger ">
							<span class="close" data-dismiss="alert"></span>
							<s:actionerror />
						</div>
					</s:if>

					<s:if test="hasActionMessages()">
						<div class="alert alert-success ">
							<span class="close" data-dismiss="alert"></span>
							<s:actionmessage />
						</div>
					</s:if>
					

					<%-- <c:choose> --%>
						<%-- <c:when test="${ null ne studentList && not empty studentList}"> --%>
							<%-- <c:when test="${ null eq studentList && empty studentList}"> --%>
							<div class="table-responsive">
								<table class="table table-striped table-hover table-bordered table_action" id="sample_editable_1" cellspacing="0" width="100%">
									<thead>
										<tr>
										   <th width="30px"></th>
										   <th class="custom-table-action text-center"><s:text name="com.edupath.common.edit" /></th>
											<th class="custom-table-action text-center"><s:text name="com.edupath.common.delete" /></th> 
											<th><s:text name="com.edupath.student.summary.type" /></th>		
											<th><s:text name="com.edupath.student.summary.name" /></th>
											<th><s:text name="com.edupath.student.summary.email" /></th>
											<th><s:text name="com.edupath.student.summary.school" /></th>
											<th><s:text name="com.edupath.student.summary.loginid" /></th>
											<th><s:text name="com.edupath.student.summary.password" /></th>
											<th><s:text name="com.edupath.student.summary.TrailCGTCompleted" /></th>
											<th style="display: none;" class="hide">id</th>
											 <th style="display: none;" class="hide">id</th> 
											
										</tr>
									</thead>
								</table>
							</div>
				</div>
			</div>
	</div>
	<div id="studentDetailsUploadDiv" class="modal" role="dialog"></div>
	<div class="modal fade cust-stud-sesion-dialog"  id="ShowCompleteStudentdetails" tabindex="-1" role="dialog" aria-hidden="true" style="margin-top: -10%;"></div>
	
	<div class="modal fade cust-stud-sesion-dialog" id="openSessionDialogId" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<s:text name="com.edupath.student.summary.title"/>
					</h4>
				</div>
				
				<div class="modal-body">
					<div>
						
					    <div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
				       		<input type="radio" name="sessionType" id="activeSessionId">
							<label for="activeSessionId" id="activeSesId"></label>
					    </div>
					</div>
					<div id="report-gen-mesg">
						<p><s:text name="com.edupath.student.summary.reportgen.message"/></p>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-success" onclick="fnOpenSession();">
						<s:text name="com.edupath.common.confirm"/>
					</button>
					<button type="button" class="btn btn-sm btn-danger" onclick="fnCloseSessionDialog();">
						<s:text name="com.edupath.common.cancel"/>
					</button>
				</div>
			</div>
		</div>
	</div>

</s:form>
<script type="text/javascript">
	var form = document.StudentSummaryForm;
	var oTable;
	$(document).ready(function() {
		//StudentTableEditable.init();
		//handleTable();
		var columnStr = '${dataTableColumns}';
		var columnArr = [];
		if(columnStr != '')
		{
			columnArr = JSON.parse(columnStr);
			console.log("columnArr==>"+JSON.stringify(columnArr))
		}
		var table = $('#sample_editable_1');
		oTable = table.dataTable(
	    {
	    	 "lengthMenu": [
	    	                [10, 20, 50, 100, 200, 500, 1000],
	    	                [10, 20, 50, 100, 200, 500, 1000] // change per page values here
	    	            ],
	    	            // set the initial value
	    	            "pageLength": 10,

	    	            "language": {
	    	                "lengthMenu": " _MENU_ Records"
	    	            },
	    	 "columnDefs": [{ // set default column settings
	                'orderable': true,
	                'targets': [0, 1, 2, 4, 5, 6, 7, 8, 9, 10] 
	            }, {
	                "searchable": true,
	                "targets": [0, 1, 2, 4, 5, 6, 7, 8, 9] 
	            }],
	            "order": [
	                      [7, "desc"]
	                  ] ,
	        "processing": true,
	        "serverSide": true,
	        "ajax": {
	        	url : "${pageContext.request.contextPath}/myapp/getJSONSummaryTrialStudentAction",
	        	 dataFilter : function (data)
	        	{
	        		 console.log("data"+data)
	        		//session expaire
	        		if(typeof data == 'string')
	        		{
	        			if(data.indexOf('<form id="invaliderrorForm">') > -1)
	        			{
	        			  fnGoLogOut();
	        			  return false;
	        			}	
	        		}
	        		//console.log("data"+data)
	        		return data;
	        	} 
	        },
	        "columns": columnArr,
	        
	        "fnCreatedRow": function( nRow, aData, iDataIndex ) 
	         {       	 
	             $('td:eq(0)', nRow).html(fnGetOpenSessionHtml(aData));
	             $('td:eq(1)', nRow).html(fnGetEditActionsHtml(aData)).addClass('text-center');
	             $('td:eq(2)', nRow).html(fnGetDeleteActionsHtml(aData)).addClass('text-center');
	             $('td:eq(3)', nRow).html(fnGetType(aData)).addClass('text-center');
	             $('td:eq(4)', nRow).html(fnGetName(aData)).addClass('text-center');
	             $('td:eq(6)', nRow).html(fnGetSchool(aData)).addClass('text-center');
	             $('td:eq(5)', nRow).html(fnGetEmail(aData)).addClass('text-center');
	             $('td:eq(7)', nRow).html(fnGetLDID(aData)).addClass('text-center');
	             $('td:eq(8)', nRow).html(fnGetPassword(aData)).addClass('text-center');
	             $('td:eq(9)', nRow).html(fnpersonalityCode(aData)).addClass('text-center');
	             $('td:eq(10)', nRow).html(aData.id).addClass("student hide").attr("id", "student_"+aData.id).attr('data-val', aData.id);
	             $('td:eq(11)', nRow).html(aData.id).addClass("student hide").attr("id", "student_"+aData.id).attr('data-val', aData.id); 
	             
	         }
	               
	    });
	});
	
	function fnGetOpenSessionHtml(data)
	{
		var html = '<a href=javascript:void(0); onclick="fnOpenSessionModel('+data.id+', '+data.userId+',  '+data.active+', '+data.reportGenerated+')">'
					+ "<i class='glyphicon glyphicon-collapse-down'></i>"
				    +"</a>";
	  	return html;		    
	}
	
	
	function fnGetEditActionsHtml(data)
	{
		var html = '<a class=delete href=javascript:void(0); onclick="fnEditstudent('+data.id+', '+data.userId+')"> <i class=glyphicon glyphicon-edit operation-column></i>'
					+ "<i class='glyphicon glyphicon-edit operation-column'></i>"
				    +"</a> &nbsp;&nbsp;&nbsp;&nbsp;";
	    return html;		    
	}
	
	function fnGetDeleteActionsHtml(data)
	{
		var html = '';
		if(data.isDelete)
		{
			 html = '<a class=delete href=javascript:void(0); onclick="fnDeletestudent('+data.id+', '+data.userId+')"> <i class=glyphicon glyphicon-edit operation-column></i>'
					+ "<i class='glyphicon glyphicon-trash operation-column'></i>"
			    	+"</a>";		    
		}	
		return html;		    
	}
	
	function fnEditstudent(id, userId) {
		$('#stdId').val(id);
		$('#userId').val(userId);
		form.action = '';
		form.action = "${pageContext.request.contextPath}/myapp/editTrialStudentAction";
		form.submit();
	}
	

	function fnDeletestudent(id, userId) {
		if (confirm('Are you sure you want to delete?')) {
			$('#stdId').val(id);
			$('#userId').val(userId);
			form.action = '';
			form.action = "${pageContext.request.contextPath}/myapp/deleteTrialStudentAction";
			form.submit();
		}
	}
	
	function fnGetType(data)
	{
		var html = 
		'<p style="font-family: verdana;">'+data.studentTypeStr+"</p>";
		return html;	
	}
	function fnGetEmail(data)
	{
		var html = 
		'<p style="font-family: verdana;">'+data.fatheremailId+"</p>";
		return html;	
	}
	function fnpersonalityCode(data)
	{
		var html = 
		'<p style="font-family: verdana;">'+data.personalityCode+"</p>";
		return html;	
	}
	function fnGetSchool(data)
	{
		var html = 
		'<p style="font-family: verdana;">'+data.schoolName+"</p>";
		return html;	
	}
	function fnGetLDID(data)
	{
		var html = 
		'<p style="font-family: verdana;">'+data.loginId+"</p>";
		return html;	
	}
	
	function fnGetName(data)
	{
		var html = 
			'<p style="font-family: verdana;">'+data.name+"</p>";
			return html;	
	}
	
	function fnGetPassword(data)
	{
		var html = 
		'<p style="font-family: verdana;">'+data.passwordStr+"</p>";
		return html;	
	}
		
	
	function fnOpenCompleteDetails(userid)
	{
		$("#ShowCompleteStudentdetails").modal(
		{
			keyboard : false,
			backdrop : 'static'
		});
		var url = "${pageContext.request.contextPath}/myapp/getCompleteSummaryTrialStudentAction";
		$.ajax({
			url : url,
			global : false,
			type : "POST",
			data : (
			{
				"userId" : userid
			}),
			success : function(resp) 
			{
				//console.log("resp=>"+JSON.stringify(resp));
				
				data=JSON.stringify(resp);
				var dueamount
				if (data != "" && data != null) 
				{
					console.log("1data=>"+data);
					resp[0].dueAmount
					if(resp[0].dueAmount==null)
					{
						dueamount="<s:text name="com.edupath.common.no.record.found" />";
					}else
					{
						dueamount=resp[0].dueAmount
					}
					$("#ShowCompleteStudentdetails").html('<div class="modal-dialog modal-full"><div class="modal-content"><div class="modal-header"><h4 class="modal-title"><s:text name="com.edupath.student.summary.title"/><button type="button" class="btn btn-sm btn-danger" onclick="fnCloseSudentDetailsDialog();"style="float:right"><s:text name="X"/></button></h4></div><div class="modal-body"><table class="table table-striped table-hover table-bordered"><tr><td><s:text name="com.edupath.student.summary.name" /></td><td>'+resp[0].name+'</td></tr><tr><td><s:text name="com.edupath.student.summary.type" /></td><td>'+resp[0].studentTypeStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.email" /></td><td>'+resp[0].fatheremailId+'</td></tr><tr><td><s:text name="com.edupath.student.summary.school" /></td><td>'+resp[0].schoolName+'</td></tr><tr><td><s:text name="com.edupath.student.summary.session1" /></td><td>'+resp[0].session1DateStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.session2" /></td><td>'+resp[0].session2DateStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.session3" /></td><td>'+resp[0].session3DateStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.venue" /></td><td>'+resp[0].venue+'</td></tr><tr><td><s:text name="com.edupath.student.summary.dueamount" /></td><td>'+dueamount+'</td></tr></table></div> </div></div>');
					console.log("2data=>"+resp[0].name);
				}
				
				
				
			},
			error : function(msg, arg1, arg2)
			{
				//return false;
			}
		});
	}
	
	
	
	function handleTable() {

	        var table = $('#sample_editable_1');

	        oTable = table.dataTable({

	            // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
	            // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
	            // So when dropdowns used the scrollable div should be removed. 
	            //"dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
	            "lengthMenu": [
	                [5, 15, 20, -1],
	                [5, 15, 20, "All"] // change per page values here
	            ],
	            // set the initial value
	            "pageLength": 5,

	            "language": {
	                "lengthMenu": " _MENU_ Records"
	            },
	            "columnDefs": [{ // set default column settings
	                'orderable': false,
	                'targets': [0, 1, 2, 11]  
	            }, {
	                "searchable": false,
	                "targets": [0, 1, 2, 11] 
	            }],
	            "order": [
	                      [3, "dsc"]
	                  ] 
	        });

	
}

	
function fnOpenSessionModel(studentId, userId, isActive, reportGenerated)
{
	console.log("Inside fnOpenSessionModel")
	$("input[type=radio]:checked").prop("checked", false);
	$("input[type=radio]").prop("disabled", false);
	$("#activeSesId").html("");
	$('#stdId').val(studentId);
	$('#userId').val(userId);
	console.log("$('#stdId').val===>"+$('#stdId').val())
	console.log("$('#userId').val()===>"+$('#userId').val())
	
	if(isActive)
	{
		$("#activeSessionId").val("inActive");
		$("#activeSesId").html("De-Activate");
	}
	else
	{
		$("#activeSessionId").val("active");
		$("#activeSesId").html("Activate");
	}
	if(reportGenerated == 'true')
	{
		$('#report-gen-mesg').show();
	}
	else
	{
		$('#report-gen-mesg').hide();
	}
	$("#openSessionDialogId").modal('show');
}
	
function fnOpenSession()
{
	console.log("Inside fnOpenSession")
	if(!($("input[type = radio]:checked").length > 0))
	{
		alert("Please select any one");
		return false;
	}
	var openSessionType = $("input[type = radio]:checked").val();
	$("#openSessionDialogId").removeClass('fade').modal('hide');
	$('#openSessionType').val(openSessionType);
	console.log("openSessionType==>"+openSessionType)
	//console.log("#openSessionType==>"+JSON.stringify($('#openSessionType').val(openSessionType)))
	form.action = '';
	form.action = "${pageContext.request.contextPath}/myapp/openSessionTrialStudentAction";
	form.submit();
}

function fnCloseSessionDialog()
{
	$("#openSessionDialogId").removeClass('fade').modal('hide');
}
function fnCloseSudentDetailsDialog()
{
	$("#ShowCompleteStudentdetails").removeClass('fade').modal('hide');
}

function fnGoLogOut()
{
	form.action = '';
	form.action = "${pageContext.request.contextPath}/Logout";
	form.submit();
}

function fnDuepayment(data)
{
	//console.log(data.dueAmount);
	var html = data.dueAmount;
	if(data.dueAmount==null)
	{
		html="<s:text name="com.edupath.common.no.record.found" />";
	}
	
	
  	return html;		    
}


</script>
</html>