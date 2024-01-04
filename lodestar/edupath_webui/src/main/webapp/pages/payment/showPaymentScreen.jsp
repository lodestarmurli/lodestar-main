<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<link href="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" type="text/css"/> 
<link href="${pageContext.request.contextPath}/styles/BulkUploadStyles.css" rel="stylesheet" type="text/css"/> 
<script src="${pageContext.request.contextPath}/thirdparty/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<s:form onsubmit="return false;" name="FileUploadForm" enctype="multipart/form-data" method="POST" id="FileUploadForm">
<s:hidden name="moduleName" value="Payment"/>
<input type="hidden" name="fileName" id="tempFilename"/>
<div class="rows">

	<div class="col-md-12"style="margin-left: 6px;"><!-- added 31/03/18 style="margin-left: 6px; -->
	
	<!-- BEGIN PORTLET-->
	
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa   fa-edit"></i> <s:text name="com.lodestar.edupath.navigation.tab.managePayment"/>
				</div>
				
				
			</div>
			
			<!-- BEGIN FORM -->
			<div class="portlet-body" >
				<!-- BEGIN FORM-->
				
				<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" class="configtable" style="border:solid 1px #c3c3c3;padding: 3px;">
				<TR><TD id="pmessageTD" colspan="2" height="25" align="center">
				</TD></TR>
				<TR id="loadingTR" style="display:none;"><TD><div class="loader"><img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif"></div></TD></TR>
				<TR id="uploadTR">
					<TD width="20%" align="left"><s:text name="com.edupath.facilitator.excelupload"></s:text><em>*</em></TD>
					<TD>	
					
						<s:file name="excel" id="excelFilePath" cssClass="file" label="excel" size="40" cssStyle="width:82%" readonly="true"/>&nbsp;
						<s:submit value="SUBMIT" onclick="fnUploadTemplate()" cssClass="button" theme="simple"/>
					</TD>
				</TR>
				<TR id="downloadLinkTR">
					<TD colspan="2" align="left">
						<span class="linklabels" onclick="fnDownloadTemplate();"><s:text name="com.edupath.facilitator.click.label"/></span>
						&nbsp;<s:text name="com.edupath.facilitator.template.download.label"/> 
					</TD>
				</TR>
				<TR id="viewLinkTR" style="display:none;">
					<TD colspan="2">
						<span class="linklabels" onclick="fnViewProcessedTemplate();">Click</span>
						<s:text name="com.edupath.facilitator.file.error.view"/>
					</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" cellpadding="0" cellspacing="0" style="display:none;margin-top: 10px;" class="reporttable" id="uploadSummary">
				<TR>
					<TH align="left"><s:text name="com.edupath.bulkupload.summary.label.filename"/></TH>
					<%-- <TH align="left"><s:text name="com.edupath.bulkupload.summary.label.uploadedat"/></TH> --%>
					<TH align="left"><s:text name="com.edupath.bulkupload.summary.label.uploadedby"/></TH>
					<TH align="left"><s:text name="com.edupath.bulkupload.summary.label.completedat"/></TH>
					<TH align="left"><s:text name="com.edupath.bulkupload.summary.label.status"/></TH>
				</TR>
				<TR>
					<TD id="fileName"></TD>
				<!-- 	<TD id="uploadedAt"></TD> -->
					<TD id="uploadedBy"></TD>
					<TD id="compAt"></TD>
					<TD id="status"></TD>
				</TR>
			</TABLE>  
				
					
				<!-- END FORM-->
				</div>
			</div>
			<!-- END PORTLET -->
		</div>
	</div>
	</s:form>

	<script>
	var uploadForm = document.FileUploadForm;
	
	$(document).ready(function(){
		if('${isInProgress}' == "true")
		{
			$("#uploadTR").hide();
			$("#downloadLinkTR").hide();
			$("#viewLinkTR").hide();
			$("#loadingTR").show();
			fnCheckUploadStatus();
		}
		else
		{
			if('${uploadJson}' != null && '${uploadJson}' != "")
			{
				prepareSummaryTable(JSON.parse('${uploadJson}'));
			}	
		}
	});
	function fnDownloadTemplate()
	{
		uploadForm.action = "${pageContext.request.contextPath}/myapp/downloadExcelTemplate";
		uploadForm.submit();
	}
	
	function fnValidateFile()
	{
		var file = $("#excelFilePath").val();
		if(file == "")
		{
	      alert('Please Select File');
	      return false;
		}

		var ext = file.substring(file.lastIndexOf(".") + 1 ,file.length);
		if(ext != "xls")
		{
			alert('xls invalid');
	        return false;
		}
		return true;
	}
	
	function fnUploadTemplate()
	{
		
		if(!fnValidateFile())
		{
			return false;
		}
		$("#uploadTR").hide();
		
		$("#downloadLinkTR").hide();
		$("#viewLinkTR").hide();
		$("#loadingTR").show();
		uploadForm.action = "${pageContext.request.contextPath}/myapp/uploadExcelTemplate";
		//uploadForm.submit();
		var options = { 
		        beforeSubmit:  showRequest,
		        success:       showResponse
		    }; 
	    $("#FileUploadForm").ajaxSubmit(options);
	    setTimeout ("fnCheckUploadStatus()", 1000);
	   
	    return false;
	}
	function showRequest(formData, jqForm, options){} 

	function showResponse(responseText, statusText, xhr, $form)
	{
		var data = JSON.parse(responseText);
		prepareSummaryTable(data);
		if(data.status != 'IN PROGRESS')
		{
			$("#uploadTR").show();
			$("#viewLinkTR").show();
			$("#loadingTR").hide();
			$("#downloadLinkTR").hide();
			$("#pmessageTD").html(data.message);
		}
		else
		{
			setTimeout ("fnCheckUploadStatus()", 5000);
		}
	}
	function fnViewProcessedTemplate()
	{
		uploadForm.action = "${pageContext.request.contextPath}/myapp/viewExcelTemplate?fileName="+$("#tempFilename").val();
		uploadForm.submit();
	}
	function prepareSummaryTable(data)
	{
		$("#tempFilename").val(data.fileName);
		$("#fileName").html(data.fileName);
		$("#uploadedAt").html(data.uploadedAt);
		$("#uploadedBy").html(data.uploadedBy);
		$("#compAt").html(data.completedAt);
		$("#status").html(data.status);
		$("#uploadSummary").show();
	}
	function fnCheckUploadStatus()
	{
		var url = "${pageContext.request.contextPath}/myapp/checkExcelTemplate";
		$.ajax({
			url: url,
			global: false,
			type: "POST",
			data:({ 
				"moduleName" : $("[name=moduleName]").val()
			}),
			success: function(resp)
			{
				if(resp != "" && resp != null)
				{
					var data = JSON.parse(resp);
					prepareSummaryTable(data);
					if(data.status == 'IN PROGRESS')
					{
						$("#uploadTR").hide();
						$("#loadingTR").show();
						$("#viewLinkTR").hide();
						$("#downloadLinkTR").hide();
						$("#pmessageTD").html("");
//						timeoutVar = setTimeout ("fnCheckUploadStatus()", 5000);
						setTimeout ("fnCheckUploadStatus()", 5000);
					}
					else
					{
						$("#pmessageTD").html(data.message);
						var className = "messagefailure";
						if(data.status == 'COMPLETED')
						{
							className = "messagesuccess";
						}
						$("#pmessageTD").removeClass().addClass(className);
						$("#loadingTR").hide();
						$("#downloadLinkTR").hide();
						$("#excelFilePath").val("");
						$("#uploadTR").show();
						$("#viewLinkTR").show();
						//clearTimeout(timeoutVar)
					}
				}	
			},
			error :  function(msg,arg1,arg2)
			{
				alert(msg.responseText);
				return false;
			}
		});
	}
	</script>