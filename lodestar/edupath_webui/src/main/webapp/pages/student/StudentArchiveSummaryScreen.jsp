
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
						<s:text name="com.edupath.archive.student.summary.title"></s:text>
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
										   <th width="40px" style="display:none"></th>
										    <th class="custom-table-action text-center"><s:text name="com.edupath.common.edit" /></th>
										    <th class="custom-table-action text-center"><s:text name="com.edupath.common.delete" /></th> 
											<th><s:text name="com.edupath.student.summary.type" /></th>		
											<th><s:text name="com.edupath.student.summary.name" /></th>
											<th><s:text name="com.edupath.student.summary.email" /></th>
											<th><s:text name="com.edupath.student.summary.school" /></th>
											<th><s:text name="com.edupath.student.summary.session1" /></th>
											<th><s:text name="com.edupath.student.summary.session2" /></th>
											<th><s:text name="com.edupath.student.summary.session3" /></th>
											
											<!-- End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 1 -->
											<th><s:text name="com.edupath.student.summary.venue" /></th>
											
											<th><s:text name="com.edupath.student.summary.loginid" /></th>
											<th><s:text name="com.edupath.student.summary.password" /></th>
											
											<!-- End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 1 -->
											<th><s:text name="com.edupath.student.summary.dueamount" /></th> 
											<!-- End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 1 -->
											 
											<th style="display: none;" class="hide">id</th>
										</tr>
									</thead>
								</table>
							</div>
				</div>
			</div>
	</div>
	<div id="studentDetailsUploadDiv" class="modal" role="dialog"></div>
	
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
				       		<input type="radio" name="sessionType" id="preSessionId" value="pre">
							<label for="preSessionId"><s:text name="com.edupath.student.summary.presession"/></label>
					    </div>
					    <div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
				       		<input type="radio" name="sessionType" id="session1Id" value="one">
							<label for="session1Id"><s:text name="com.edupath.student.summary.session1"/></label>
					    </div>
					    <div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
				       		<input type="radio" name="sessionType" id="session2Id" value="two">
							<label for="session2Id"><s:text name="com.edupath.student.summary.session2"/></label>
					    </div>
					    <div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
				       		<input type="radio" name="sessionType" id="session3Id" value="three">
							<label for="session3Id"><s:text name="com.edupath.student.summary.session3"/></label>
					    </div>
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
	                'orderable': false,
	                'targets': [0, 1, 2, 12] //  Sasedeve Edited by Vyankatesh on date:- 30-01-2017   original[0, 1, 2, 11]
	            }, {
	                "searchable": false,
	                "targets": [0, 1, 2, 12] //  Sasedeve Edited by Vyankatesh on date:- 30-01-2017   original[0, 1, 2, 11]
	            }],
	            "order": [
	                      [4, "dsc"]
	                  ] ,
	        "processing": true,
	        "serverSide": true,
	        "ajax": {
	        	url : "${pageContext.request.contextPath}/myapp/getArchiveJSONSummaryStudentSummaryArchive",
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
	          //  console.log(fnGetOpenSessionHtml(aData))
	             //$('td:eq(0)', nRow).html(fnGetOpenSessionHtml(aData));
	             $('td:eq(0)', nRow).html(fnGetEditActionsHtml(aData)).addClass('text-center');
	             $('td:eq(1)', nRow).html(fnGetDeleteActionsHtml(aData)).addClass('text-center');
	             $('td:eq(2)', nRow).html(aData.id).addClass("student hide").attr("id", "student_"+aData.id).attr('data-val', aData.id);
	             $('td:eq(13)', nRow).html(fnDuepayment(aData)).addClass('text-center');//Sasedeve new code by vyankatesh on date 30-1-2017
	             $('td:eq(14)', nRow).html(aData.id).addClass("student hide").attr("id", "student_"+aData.id).attr('data-val', aData.id); // Sasedeve edited by vyankatesh on Date 30-1-2017 ,Original code $('td:eq(12)', nRow).html(aData.id).addClass("student hide").attr("id", "student_"+aData.id).attr('data-val', aData.id);
	         }
	               
	    });
	});
	
	
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
	

	function fnDeletestudent(id, userId) {
		if (confirm('Are you sure you want to delete?')) {
			$('#stdId').val(id);
			$('#userId').val(userId);
			form.action = '';
			form.action = "${pageContext.request.contextPath}/myapp/deleteStudentDetailAction";
			form.submit();
		}
	}


	function fnEditstudent(id, userId) {
		$('#stdId').val(id);
		$('#userId').val(userId);
		form.action = '';
		form.action = "${pageContext.request.contextPath}/myapp/editArchiveDetailAction";
		form.submit();
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
	                'targets': [0, 1, 2, 11]  //  Sasedeve Edited by Vyankatesh on date:- 30-01-2017   original[0, 1, 2, 10]
	            }, {
	                "searchable": false,
	                "targets": [0, 1, 2, 11] //  Sasedeve Edited by Vyankatesh on date:- 30-01-2017   original[0, 1, 2, 10]
	            }],
	            "order": [
	                      [4, "dsc"]
	                  ] 
	        });

	
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