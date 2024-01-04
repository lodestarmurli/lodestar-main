<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<div class="edupath-padding-summary">
<div class="rows">
<div class="col-md-12">
<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-edit"></i><s:text name="com.edupath.viewlog.title"></s:text>
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
			<div class="table-toolbar">
				<div class="row">
				<div class="col-md-6">
					<div class="btn-group">
					 </div>
					</div>
					<div class="col-md-6">
						<div class="btn-group pull-right">
							<%-- <button  class="btn green" onclick="fnAddSubAdmin();">
								<s:text name="com.edupath.common.add"/> <i class="fa fa-plus"></i>	
							</button>	 --%>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			  <div class="col-md-12" style="padding-left: 160px;">
			     <span class="error-block" id="dateError"></span>
			  </div>
			</div>
			<div class="row" style="padding-bottom: 0px;">
			 	<div class="col-md-12">
			 	  <div style="display: inline-block; padding: 0px 15px;">
			 	  	 <label class="bold"><s:text name="com.edupath.common.from"></s:text></label>
			      	 <s:textfield name="from" cssClass="date_input fromDate" id="fromId"></s:textfield>
			       </div>
			       
			       <div class="viewlog" style="display: inline-block;"> <!-- 21/3/18 Changes -->
			         <label class="bold"><s:text name="com.edupath.common.to"></s:text> </label>
			         <s:textfield name="to" cssClass="date_input toDate" id="toId"></s:textfield>
			       </div>
			       
			        <div style="display: inline-block;margin-left: 7px;margin-top: 5px;"  class="viewlog" ><!-- added 31/03/18 margin-left: 7px;margin-top: 5px;  -->
			         <select id="moduleNameSelect" class="selectpicker"  multiple="multiple"  title='Select module name'>
			 		   	 <c:forEach items="${moduleMap}" var="module">
			 		       <option value="${utils:replaceXMLEntities(module.key)}">${utils:replaceXMLEntities(module.value)}</option>
			 		     </c:forEach>
			 		</select>
			       </div>
			       
			       <div style="display: inline-block; padding-left: 10px;">
			         <button class="btn blue" onclick="fnShowViewLog();"><s:text name="com.edupath.common.go"></s:text></button>
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
			 		 <div style="height: 20px; padding-left: 45px; width: 240px;">
			 		   <span class="error-block" id="moduleError"></span>
			 		  </div> 
			       </div>
			       
			       <div style="display: inline-block; padding-left: 10px;">
			       </div>
			       
				 </div>
				 
			</div>
		   <div id="viewLogTable">
		     <jsp:include page="/pages/viewlog/ViewLogTableScreen.jsp"></jsp:include>
		   </div>			
		</div>		
	</div>
	</div>
	</div>
</div>
<s:form name="ViewLogForm" id="ViewLogForm" method="POST">
  <s:hidden id="fromDate" name="fromDate"></s:hidden>
  <s:hidden id="toDate" name="toDate"></s:hidden>
  <s:hidden id="moduleName" name="moduleName"></s:hidden>
  <s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
  <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
</s:form>	

<script type="text/javascript">
jQuery(document).ready(function() {
	fnINITTable();
	fnSetDefaultDate();
	$('.selectpicker').selectpicker({
		 size: 5
	});
	$(".fromDate").datepicker({
		format: 'dd-mm-yyyy',
		endDate: '0d'
	});
	$(".toDate").datepicker({
		format: 'dd-mm-yyyy',
		satrtDate: '0d'
	});
	
});

function fnINITTable()
{
	TableEdittableViewLogInit();
	$("[aria-controls='sample_editable_1']").selectpicker();
}

function fnShowViewLog()
{
	var options = [];
	$('.error-block').html('');
	if(fnIsValid())
	{
		$('#moduleNameSelect option:selected').each(function(key, item)
		{
			options.push(item.value);
		});
		
		var obj = $('#moduleNameSelect').html();
		
		$('#moduleNameSelect').html('');
		$('#moduleNameSelect').html(obj);
		$('.selectpicker').selectpicker('refresh');
		
		$('#moduleName').val(options.join(","));
		$('#fromDate').val($('#fromId').val());
		$('#toDate').val($('#toId').val());
		
		fnViewLog('getViewLogByAjaxViewLogAction');
	}
}

function fnViewLog(action)
{
	form = document.ViewLogForm;
	form.action = "${pageContext.request.contextPath}/myapp/" + action;
	var options = { 
	        beforeSubmit:  showViewLogRequest,
	        success:       showViewLogResponse
	    }; 
	$("#ViewLogForm").ajaxSubmit(options);
}

function showViewLogRequest(formData, jqForm, options){
	$("#viewLogTable").css("text-align", 'center');
	$("#viewLogTable").html("<img src='${pageContext.request.contextPath}/images/ajax-loader-bar.gif'/>");	
} 

function showViewLogResponse(responseText, statusText, xhr, $form)
{
	    $("#viewLogTable").css("text-align", '');
		$("#viewLogTable").html(responseText);
		fnINITTable();
}

function fnIsValid()
{
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
	
	/* var opt = $('#moduleNameSelect option:selected').val();
	if(opt == '' || opt == undefined)
	{
		$('#moduleError').html('This field required.');
		return false;
	} */
	
	var strDataOne = $('#fromId').val().split('-');
	var strDataTwo = $('#toId').val().split('-');
	var fromDate = new Date(strDataOne[2], (parseInt(strDataOne[1]) - 1), strDataOne[0], 00, 00, 00);
	var toDate = new Date(strDataTwo[2], (parseInt(strDataTwo[1]) - 1), strDataTwo[0], 00, 00, 00);
	var dateDiff = toDate.getDate() - fromDate.getDate();
	if(dateDiff > 7)
	{
		$('#dateError').html('From and To date must be between one week.');
		return false;
	}
	return true;
}

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
 
function TableEdittableViewLogInit(){
 	
		var table = $('#sample_editable_1');

		var oTable = table.dataTable({

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

</script>	
