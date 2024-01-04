<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld" %>
<link href="${pageContext.request.contextPath}/styles/BulkUploadStyles.css" rel="stylesheet" type="text/css"/> 
<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- END PAGE LEVEL PLUGINS -->

<div class="edupath-padding-summary">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-edit"></i><s:text name="com.edupath.failedemail.label"/>
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
			<br/>
		
			<table class="table table-striped table-hover table-bordered table-facilitator" id="sample_editable_1">
				<thead>
					<tr>
						<th>ReSend</th>
						<th>Email Id</th>
						<th>Subject</th>
						<th>Status</th>
						<th>Type</th>
						<th>Time</th>
						
					</tr>
				</thead>
				
				<tbody>
				<c:forEach items="${failedemails}" var="failedemail">
					<tr>
						<td align="center">
							<a href="javascript:void(0);"   onclick = "fnshowemailmodal('${failedemail.toAddress}','${failedemail.id}')" ><i class="fa fa-share" aria-hidden="true" style="color:blue;"></i></a>
						
						<div id="${failedemail.id}" style="display:none;">
						${failedemail.messageData}
						</div>
						
						</td>
						<td>
						
								${failedemail.toAddress}
						</td>
						<td>
						 
							${failedemail.messageSubject}
						 </a> 
						</td>
						<td>
							${failedemail.status}
						</td>
						<td>
							${failedemail.notifierType}
						</td>
						<td>
							${failedemail.queuedTime}
						</td>
						
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
	
			
			
		</div>	

		
			
	</div>
	
			
				
</div>	

<div id="emailmodal" class="modal" role="dialog" >
<div class="modal-dialog">
     <!-- Modal content-->
     <div class="modal-content" style="width:700px !important;margin-top: 40px;">
       <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title">Re-Send Email</h4>
       </div>
       <div class="modal-body" style="height:500px;overflow-y: auto">
       <div >
			<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" class="configtable" style="padding: 3px;overflow:auto;">
				<TR>
				<TD id="messageTD" height="25" align="center" style="font-size:14px;">
				<br/>
				Email Address:
				<br/><br/>
				<input type="text" id="emailids" style="width:96%;height:30px;"/>
				<br/><br/>
				<div id="error" style="display:none;font-size:14px;color:red;">
				</div>
				<input type="hidden" id="ids" />
				<br/><br/>
				</TD>
				</TR>
				
				<TR>
				<TD id="messageTD" height="25" align="center" style="font-size:14px;">
				<br/>
				Email Body:
				<br/><br/>
				<div id="emailbody" style="width:96%;">
				
				</div>
				
				<br/><br/>
				</TD>
				</TR>
				
				<TR>
				<TD id="messageTD" height="25" align="center" style="font-size:14px;">
				<br/>
				<button class="btn btn-sm apply-btn college-filter-btn"  onclick="sendemail()">
					<s:text name="Send"/>
				</button>
				
				<br/><br/>
				
				
				</TD>
				</TR>
			</TABLE>
			</div>
			        
       </div>
     </div>
</div>
</div>
<div id="validateLoading" class="modal" role="dialog" >
	<div class="app-spinner">
<!-- 		<i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i> -->
		<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
	</div>
</div>	
<s:form action="/myapp/ManageFailedEmails" method="POST" name="FailedEmails">
	
</s:form>
<script type="text/javascript">
var finalizeForm = document.FailedEmails;
function fnshowemailmodal(emailaddress,emailid)
{
	$("#error").hide();
	
	$("#emailids").val(emailaddress);
	$("#ids").val(emailid);
	
	$("#emailbody").html($("#"+emailid).html());
	
	
	$("#emailmodal").modal({
		keyboard:false,
		backdrop: 'static'
	});
	

}

function sendemail()
{
	$('#validateLoading').show(); 
	var id=$("#ids").val();
	var emailaddress=$("#emailids").val();
	//console.log(emailaddress);
	if(emailaddress!=null && emailaddress.trim()!="")
	{
		var actionUrl = "${pageContext.request.contextPath}/APIS/EmailValidator?emailids="+emailaddress;
		$.ajax({
			url : actionUrl,
			type : "GET",
			dataType : "JSON",
			success : function (resp){
				
				if(resp && resp != null && resp.status == 'SUCCESS')
				{
					sendemailagain(id,emailaddress);
				}
				else
				{
					$("#error").html("Invalide Emails");
					$("#error").show();
					$('#validateLoading').hide(); 
				}
				
			},
			error : function (arg0, arg1, arg2){
				
				alert(arg1);
			}
		})
	}
	else
	{
		$("#error").html("Email Address Required");
		$("#error").show();
		$('#validateLoading').hide(); 
	}
	
	
	
}

function sendemailagain(id,emailaddress)
{
	if(id!=null && id.trim()!="" && emailaddress!=null && emailaddress.trim()!="")
	{
		var actionUrl = "${pageContext.request.contextPath}/APIS/EmailSend?id="+id+"&emailids="+emailaddress;
		$.ajax({
			url : actionUrl,
			type : "GET",
			dataType : "JSON",
			success : function (resp){
				
				if(resp && resp != null && resp.status == 'SUCCESS')
				{
					$('#validateLoading').hide(); 
					finalizeForm.submit();
				}
				else
				{
					$('#validateLoading').hide(); 
					alert("Internal error please try again later.");
					
				}
				
			},
			error : function (arg0, arg1, arg2){
				
				alert(arg1);
				$('#validateLoading').hide();
			}
		})
	}
	else
	{
		
		$('#validateLoading').hide(); 
		alert("Internal error please try again later.");
	}
}

	$(document).ready(function() {
		TableEdittableInit();
		$("#emailmodal").hide();
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
	 				'targets' : [0]
	 			}, {
	 				"searchable" : false,
	 				"targets" : [0]
	 			} ],
	 			"order" : [ [ 5, "desc" ] ],

	 		});
	 }
</script>