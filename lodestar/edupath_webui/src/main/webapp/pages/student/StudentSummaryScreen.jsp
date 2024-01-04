
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
	<s:hidden id="openSessionType" name="openSessionType" />
	<s:hidden name="parentSelectedSidebarMenuId"
		id="parentSelectedSidebarMenuId" />
	<s:hidden name="childSelectedSidebarMenuId"
		id="childSelectedSidebarMenuId" />
	<div class="edupath-padding-summary">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>
					<s:text name="com.edupath.student.summary.title"></s:text>
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
				<div class="table-toolbar">
					<div class="row">

						<div class="col-md-5 col-xs-12" style="float: left;">
							<!-- add col-xs-12  23/03/18 -->
							<label class="bold"><s:text
									name="com.edupath.facilitator.student.summary.global.search" /></label>&nbsp;:&nbsp;<input
								type="text" name="studentloginId" id="studentloginId"
								class="globle_txt">
							<div class="btn-group">
								<button id="sample_editable_1_new" class="btn green"
									onclick="fnSearchStudentDetailsById()">
									<s:text name="com.edupath.facilitator.student.summary.search" />
								</button>
							</div>
						</div>



						<!-- Start SASEDEVE edited by Mrutyunjaya on Date 07-06-2017 -->

						<div class="col-md-2">
							<!--<div class="btn-group">
									<span id="sample_editable_1_new" class="btn green"
										onclick="fnShowUploadDialog('Student')"> <s:text
											name="com.edupath.common.upload" /> <i class="fa fa-plus"></i>
									</span>
								</div>-->
						</div>
						<div class="col-md-2">
							<!--	<div class="btn-group">
									<span id="sample_editable_1_new" class="btn green"
										onclick="fnShowUploadDialog('StudentInterest Test')"> <s:text
											name="StudentInterest Test" /> <i class="fa fa-plus"></i>
									</span>
								</div> -->
						</div>


						<!-- End SASEDEVE edited by Mrutyunjaya on Date 07-06-2017 -->

						<div class="col-md-6" style="float: right;">
							<div class="btn-group pull-right">
								<button class="btn green" onclick="fnAddstudent();">
									<s:text name="com.edupath.common.add" />
									<i class="fa fa-plus"></i>
								</button>
							</div>
							<div class="btn-group pull-right export_btn">
								<button class="btn dropdown-toggle" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu pull-right action-div1">
									<!-- <li><a href="#"> Print </a></li>
										<li><a href="#"> Save as PDF </a></li> -->
									<li onclick="fnExport(this)" data-report="Excel" id="Excel"><a
										href="#"> Export to Excel </a></li>
                                    <li onclick="fnShowUploadDialog('CDD Student');"><a
										href="javascript:void(0);"> Import CDD Students </a></li>
									<!-- Start SASEDEVE edited by Mrutyunjaya on Date 07-06-2017 -->
									<li onclick="fnShowUploadDialog('Student');"><a
										href="javascript:void(0);"> Import New Students </a></li>
									<li onclick="fnShowUploadDialog('Interest Test');"><a
										href="javascript:void(0);"> Import Interest Tests </a></li>
									<li onclick="fnShowUploadDialog('StudentInterest Test');"><a
										href="javascript:void(0);"> Import New Students with
											Interest Tests </a></li>
									<li onclick="fnShowUploadDialog('Chanakya Student Test');"><a
										href="javascript:void(0);">  Import Chanakya Student With Tests </a></li>

									<!-- End SASEDEVE edited by Mrutyunjaya on Date 07-06-2017 -->
								</ul>
							</div>
						</div>
					</div>
				</div>

				<%-- <c:choose> --%>
				<%-- <c:when test="${ null ne studentList && not empty studentList}"> --%>
				<%-- <c:when test="${ null eq studentList && empty studentList}"> --%>
				<div class="table-responsive">
					<table
						class="table table-striped table-hover table-bordered table_action"
						id="sample_editable_1" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th width="40px"></th>
								<th class="custom-table-action text-center"><s:text
										name="com.edupath.common.edit" /></th>
								<th class="custom-table-action text-center"><s:text
										name="com.edupath.common.delete" /></th>
								<th><s:text name="com.edupath.student.summary.type" /></th>
								<th><s:text name="com.edupath.student.summary.name" /></th>
								<th><s:text name="com.edupath.student.summary.email" /></th>
								<th><s:text name="com.edupath.student.summary.school" /></th>
								<th><s:text name="com.edupath.student.summary.loginid" /></th>
								<th><s:text name="com.edupath.student.summary.password" /></th>

								<!-- <th><s:text name="com.edupath.student.summary.session1" /></th> 
											<th><s:text name="com.edupath.student.summary.session2" /></th> 
											<th><s:text name="com.edupath.student.summary.session3" /></th>-->

								<!-- End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 1 -->
								<!--<th><s:text name="com.edupath.student.summary.venue" /></th>-->



								<!-- End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 1 -->
								<!-- <th><s:text name="com.edupath.student.summary.dueamount" /></th> -->
								<!-- End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 1 -->

								 <th style="display: none;" class="hide">id</th> 
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${finalList}" var="studentDetails">
								<tr>
									<td><a href=javascript:void(0);
										onclick="fnOpenSessionModel(${utils:replaceXMLEntities(studentDetails.id)}, ${utils:replaceXMLEntities(studentDetails.userId)}, ${utils:replaceXMLEntities(studentDetails.preSessionCompleted)}, ${utils:replaceXMLEntities(studentDetails.session1FaciCompleted)}, ${utils:replaceXMLEntities(studentDetails.session2FaciCompleted)}, ${utils:replaceXMLEntities(studentDetails.session3FaciCompleted)}, ${utils:replaceXMLEntities(studentDetails.active)}, ${utils:replaceXMLEntities(studentDetails.reportGenerated)})">
											<i class='glyphicon glyphicon-collapse-down'></i>
									</a></td>
									<td class="text-center"><a class=delete
										href=javascript:void(0);
										onclick="fnEditstudent(${utils:replaceXMLEntities(studentDetails.id)}, ${utils:replaceXMLEntities(studentDetails.userId)})">
											<i class=glyphicon glyphicon-edit operation-column></i> <i
											class='glyphicon glyphicon-edit operation-column'></i>
									</a> &nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td class="text-center">
										<c:if test="${utils:replaceXMLEntities(studentDetails.isDelete)}">
											<a class=delete href=javascript:void(0); onclick="fnDeletestudent(${utils:replaceXMLEntities(studentDetails.id)}, ${utils:replaceXMLEntities(studentDetails.userId)})"> <i class=glyphicon glyphicon-edit operation-column></i>
												<i class='glyphicon glyphicon-trash operation-column'></i></a>
										</c:if>
									</td>
									<td class="text-center">
									${utils:replaceXMLEntities(studentDetails.studentTypeStr)}
									</td>
									<td class="text-center">
									<a href=javascript:void(0); onclick="fnOpenCompleteDetails( ${utils:replaceXMLEntities(studentDetails.userId)})">
										<p>${utils:replaceXMLEntities(studentDetails.name)}</p></a>
									</td>
									
									<td class="text-center">
									${utils:replaceXMLEntities(studentDetails.fatheremailId)}
									</td>
									<td class="text-center">
									${utils:replaceXMLEntities(studentDetails.schoolName)}
									</td>
									<td class="text-center">
									${utils:replaceXMLEntities(studentDetails.loginId)}
									</td>
									<td class="text-center">
									${utils:replaceXMLEntities(studentDetails.passwordStr)}
									</td>
									<td class="text-center hide">
									${utils:replaceXMLEntities(studentDetails.userId)}
									</td>
									
									
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
	<div id="studentDetailsUploadDiv" class="modal" role="dialog"></div>
	<div class="modal fade cust-stud-sesion-dialog"
		id="ShowCompleteStudentdetails" tabindex="-1" role="dialog"
		aria-hidden="true" style="margin-top: -10%;"></div>

	<div class="modal fade cust-stud-sesion-dialog"
		id="openSessionDialogId" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<s:text name="com.edupath.student.summary.title" />
					</h4>
				</div>

				<div class="modal-body">
					<div>
						<div class="radio radio-success cust-session-radio-div"
							style="margin-left: 60px !important;">
							<input type="radio" name="sessionType" id="preSessionId"
								value="pre"> <label for="preSessionId"><s:text
									name="com.edupath.student.summary.presession" /></label>
						</div>
						<div class="radio radio-success cust-session-radio-div"
							style="margin-left: 60px !important;">
							<input type="radio" name="sessionType" id="session1Id"
								value="one"> <label for="session1Id"><s:text
									name="com.edupath.student.summary.session1" /></label>
						</div>
						<div class="radio radio-success cust-session-radio-div"
							style="margin-left: 60px !important;">
							<input type="radio" name="sessionType" id="session2Id"
								value="two"> <label for="session2Id"><s:text
									name="com.edupath.student.summary.session2" /></label>
						</div>
						<div class="radio radio-success cust-session-radio-div"
							style="margin-left: 60px !important;">
							<input type="radio" name="sessionType" id="session3Id"
								value="three"> <label for="session3Id"><s:text
									name="com.edupath.student.summary.session3" /></label>
						</div>
						<div class="radio radio-success cust-session-radio-div"
							style="margin-left: 60px !important;">
							<input type="radio" name="sessionType" id="activeSessionId">
							<label for="activeSessionId" id="activeSesId"></label>
						</div>
					</div>
					<div id="report-gen-mesg">
						<p>
							<s:text name="com.edupath.student.summary.reportgen.message" />
						</p>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnOpenSession();">
						<s:text name="com.edupath.common.confirm" />
					</button>
					<button type="button" class="btn btn-sm btn-danger"
						onclick="fnCloseSessionDialog();">
						<s:text name="com.edupath.common.cancel" />
					</button>
				</div>
			</div>
		</div>
	</div>

</s:form>
<script type="text/javascript">
	var form = document.StudentSummaryForm;
	var oTable;
	var oTable2;
	$(document).ready(function() {
		handleTable();
	});
	

	function handleTable() {

        var table = $('#sample_editable_1');

        oTable = table.dataTable({

            // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
            // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
            // So when dropdowns used the scrollable div should be removed. 
            //"dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
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
        'orderable': false,
        'targets': [0, 1, 2, 8] //  Sasedeve Edited by Vyankatesh on date:- 30-01-2017   original[0, 1, 2, 11]
    }, {
        "searchable": false,
        "targets": [0, 1, 2, 8] //  Sasedeve Edited by Vyankatesh on date:- 30-01-2017   original[0, 1, 2, 11]
    }],
    "order": [
              [9, "dsc"]
          ] ,
          "processing": true,
	       // "serverSide": true,
          drawCallback: function() {
				$('[data-toggle="alertpopover"]').popover();
			  } 
          
        });


}
	
	function fnSearchStudentDetailsById()
	{
		loginId = $("#studentloginId").val()
		if(loginId == "" || loginId == null){
			alert("Please enter login id")
			return false;
		} 
		var urlVal = "${pageContext.request.contextPath}/myapp/searchDataStudentSummary?studLoginId="+loginId;
		form.action = urlVal;
		form.submit();
		
		
	}
	
	//start bharath backup old logic 14/10/2019
	function getJSONSummaryStudentSummary()
	{
		//StudentTableEditable.init();
		//handleTable();
		var columnStr = '${dataTableColumns}';
		var columnArr = [];
		if(columnStr != '')
		{
			columnArr = JSON.parse(columnStr);
		}
		var table2 = $('#sample_editable_2');
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
	                'orderable': false,
	                'targets': [0, 1, 2, 8] //  Sasedeve Edited by Vyankatesh on date:- 30-01-2017   original[0, 1, 2, 11]
	            }, {
	                "searchable": false,
	                "targets": [0, 1, 2, 8] //  Sasedeve Edited by Vyankatesh on date:- 30-01-2017   original[0, 1, 2, 11]
	            }],
	            "order": [
	                      [5, "dsc"]
	                  ] ,
	        "processing": true,
	        "serverSide": true,
	        "ajax": {
	        	url : "${pageContext.request.contextPath}/myapp/getJSONSummaryStudentSummary",
	        	 dataFilter : function (data)
	        	{
	        		//session expaire
	        		if(typeof data == 'string')
	        		{
	        			if(data.indexOf('<form id="invaliderrorForm">') > -1)
	        			{
	        			  fnGoLogOut();
	        			  return false;
	        			}	
	        		}
	        		return data;
	        	} 
	        },
	        "columns": columnArr,
	        
	        "fnCreatedRow": function( nRow, aData, iDataIndex ) 
	         {       	 
	             $('td:eq(0)', nRow).html(fnGetOpenSessionHtml(aData));
	             $('td:eq(1)', nRow).html(fnGetEditActionsHtml(aData)).addClass('text-center');
	             $('td:eq(2)', nRow).html(fnGetDeleteActionsHtml(aData)).addClass('text-center');
	             $('td:eq(4)', nRow).html(fnGetCompleteData(aData)).addClass('text-center');
	             $('td:eq(8)', nRow).html(fnGetPassword(aData)).addClass('text-center');
	             //$('td:eq(8)', nRow).html(fnDuepayment(aData)).addClass('text-center');//Sasedeve new code by vyankatesh on date 30-1-2017
	             $('td:eq(9)', nRow).html(aData.id).addClass("student hide").attr("id", "student_"+aData.id).attr('data-val', aData.id); // Sasedeve edited by vyankatesh on Date 30-1-2017 ,Original code $('td:eq(12)', nRow).html(aData.id).addClass("student hide").attr("id", "student_"+aData.id).attr('data-val', aData.id);
	             
	         }
	               
	    });
		
	}
	//End bharath backup old logic 14/10/2019
	
	
	function fnGetOpenSessionHtml(data)
	{
		var html = '<a href=javascript:void(0); onclick="fnOpenSessionModel('+data.id+', '+data.userId+', '+data.preSessionCompleted+', '+data.session1FaciCompleted+', '+data.session2FaciCompleted+', '+data.session3FaciCompleted+', '+data.active+', '+data.reportGenerated+')">'
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
	
	function fnAddstudent()
	{
		$('#stdId').val(0);
		form.action = "${pageContext.request.contextPath}/myapp/addStudentDetailAction";
		form.submit();
	}

	function fnDeletestudent(id, userId) {
		if (confirm('Are you sure you want to delete?')) {
			$('#stdId').val(id);
			$('#userId').val(userId);
			form.action = '';
			form.action = "${pageContext.request.contextPath}/myapp/deleteStudentDetailAction";
			form.submit();
		}
	}
	

	function fnGetCompleteData(data)
	{
		var html = '<a href=javascript:void(0); onclick="fnOpenCompleteDetails('+data.userId+')">'
		+ "<p>"+data.name+"</p>"
	    +"</a>";
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
		console.log("userid= "+userid)
		$("#ShowCompleteStudentdetails").modal(
		{
			keyboard : false,
			backdrop : 'static'
		});
		var url = "${pageContext.request.contextPath}/myapp/getCompleteSummaryStudentSummary";
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
					$("#ShowCompleteStudentdetails").html('<div class="modal-dialog modal-full"><div class="modal-content"><div class="modal-header"><h4 class="modal-title"><s:text name="com.edupath.student.summary.title"/><button type="button" class="btn btn-sm btn-danger" onclick="fnCloseSudentDetailsDialog();"style="float:right"><s:text name="X"/></button></h4></div><div class="modal-body"><table class="table table-striped table-hover table-bordered"><tr><td><s:text name="com.edupath.student.summary.name" /></td><td>'+resp[0].name+'</td></tr><tr><td><s:text name="com.edupath.student.summary.type" /></td><td>'+resp[0].studentTypeStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.email" /></td><td>'+resp[0].fatheremailId+'</td></tr><tr><td><s:text name="com.edupath.student.summary.school" /></td><td>'+resp[0].schoolName+'</td></tr><tr><td><s:text name="com.edupath.viewfeedback.summary.facilitator.name" /></td><td>'+resp[0].facilitatorName+'</td></tr><tr><td><s:text name="com.edupath.student.summary.session1" /></td><td>'+resp[0].session1DateStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.session2" /></td><td>'+resp[0].session2DateStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.session3" /></td><td>'+resp[0].session3DateStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.venue" /></td><td>'+resp[0].venue+'</td></tr><tr><td><s:text name="com.edupath.student.summary.dueamount" /></td><td>'+dueamount+'</td></tr></table></div> </div></div>');
					console.log("2data=>"+resp[0].name);
				}				
			},
			error : function(msg, arg1, arg2)
			{
				//return false;
			}
		});
	}
	
	
	function fnExport(obj)
	{
			 var table = $('#sample_editable_1').DataTable();
			
			/*var arrayIndex = [];
			
			arrayIndex=oTable.fnSettings().aiDisplay;
			var searchValue= '';
			var lastHiddenIdColoumn=oTable.fnSettings().aoColumns.length - 1;
			$.each(arrayIndex, function( index, value ) 
			{
				var aData = table.row( value ).data();
				if(searchValue != '')
				{
					searchValue = searchValue + ",";
				}
				// searchValue = searchValue + aData[lastHiddenIdColoumn]; 
				searchValue = searchValue + aData.id;
			 }); */
			var info = table.page.info();
			searchValue = $('.dataTables_filter input').val();
			form.action = "${pageContext.request.contextPath}/myapp/getReportExcelPdfReport?reportFileType="+$("#"+obj.id).data("report")+"&searchValue="+searchValue+"&moduleName=Student&filterRecord="+info.recordsDisplay;
			form.submit();
	}

	function fnEditstudent(id, userId) {
		$('#stdId').val(id);
		$('#userId').val(userId);
		form.action = '';
		form.action = "${pageContext.request.contextPath}/myapp/editStudentDetailAction";
		form.submit();
	}

	function fnShowUploadDialog(moduleName) 
	{
		moduleName = $.trim(moduleName);
		$("#studentDetailsUploadDiv").modal(
		{
			keyboard : false,
			backdrop : 'static'
		});
		$("#studentDetailsUploadDiv").html('<div class="loader"><img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif"></div>');
		var url = "${pageContext.request.contextPath}/myapp/showExcelTemplate";
		$.ajax({
			url : url,
			global : false,
			type : "POST",
			data : (
			{
				"moduleName" : moduleName
			}),
			success : function(resp) 
			{
				if (resp != "" && resp != null) 
				{
					$("#studentDetailsUploadDiv").html(resp);
				}
			},
			error : function(msg, arg1, arg2)
			{
				//return false;
			}
		});
		//$("#studentDetailsUploadDiv").dialog("open");
	}
	
	


	
function fnOpenSessionModel(studentId, userId, preSessionCompleted, session1Completed, session2Completed, session3Completed, isActive, reportGenerated)
{
	$("input[type=radio]:checked").prop("checked", false);
	$("input[type=radio]").prop("disabled", false);
	$("#activeSesId").html("");
	$('#stdId').val(studentId);
	$('#userId').val(userId);
	console.log("$('#stdId').val===>"+$('#stdId').val())
	console.log("$('#userId').val()===>"+$('#userId').val())
	if(preSessionCompleted == 'false') 
	{
		$("#preSessionId").prop('disabled', true);
	}
	if(session1Completed == 'false') 
	{
		$("#session1Id").prop('disabled', true);
	}
	if(session2Completed == 'false') 
	{
		$("#session2Id").prop('disabled', true);
	}
	if(session3Completed == 'false') 
	{
		$("#session3Id").prop('disabled', true);
	}
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
	if(!($("input[type = radio]:checked").length > 0))
	{
		alert("Please select any one");
		return false;
	}
	var openSessionType = $("input[type = radio]:checked").val();
	$("#openSessionDialogId").removeClass('fade').modal('hide');
	$('#openSessionType').val(openSessionType);
	console.log("openSessionType==>"+openSessionType)
	console.log("#openSessionType==>"+JSON.stringify($('#openSessionType').val(openSessionType)))
	form.action = '';
	form.action = "${pageContext.request.contextPath}/myapp/openSessionStudentDetailAction";
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
//Start Sasedeve Edited by Vyankatesh on date:- 30-01-2017
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
//End Sasedeve Edited by Vyankatesh on date:- 30-01-2017

</script>
</html>