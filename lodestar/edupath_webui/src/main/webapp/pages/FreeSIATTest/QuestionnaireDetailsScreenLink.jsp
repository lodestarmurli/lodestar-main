<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="c.tld" prefix="c"%>
<HTML>
<HEAD>
<TITLE><s:text name="com.edupath.webtitle" /></TITLE>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</HEAD>
<BODY style="background-color:#f0f9ff">

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-color:#fff;padding-top:5px;padding-bottom:5px">
	
	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	
		<img src="${pageContext.request.contextPath}/images/SIATTEST/lodestar_logo.gif" class="img-responsive">
		
	</div>	
		
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" align="right">
	
		<img src="${pageContext.request.contextPath}/images/SIATTEST/hdfclogo.gif" class="img-responsive">
		
	</div>
		
	</div>
	
	 <div>
       
            <jsp:include page="/pages/FreeSIATTest/InterestQuestionScreenLink.jsp"></jsp:include>
		   
	</div>
	
	 <div id="testLoading" class="display-hide cust-loader">
	<div class="app-spinner">
<!-- 		<i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i>  -->
		<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
	</div>
</div>	
		
<s:form name="AnswerForm" id="AnswerForm" method="post">
   <s:hidden name="answerList" id="answerList"></s:hidden>
  <input type="hidden" name="userid" id="userid" value="${userid}"/>
  <input type="hidden" name="token" id="token" value="${token}"/>
   <s:hidden name="remainigTime" id="remainigTime"/> 
   <s:hidden name="remainigSecondTime" id="remainigSecondTime"></s:hidden>
   <s:hidden name="remainigMinuteTime" id="remainigMinuteTime"></s:hidden>
</s:form>


</BODY>


<script>
var form = document.AnswerForm;
function fnAnswerFormSubmit(action)
{
	fnSetFormDataFinalSubmit;
	  $('#remainigTime').val((60 * 60)  * 1000);
	  form.action = '${pageContext.request.contextPath}/Test/insert' + action;
	  form.submit();
}
 

function fnAutoSave(action)
{
	fnSetFormData();
	form.action = '';
	form.action = "${pageContext.request.contextPath}/Test/insertAjax" + action;
	var options = { 
	        beforeSubmit:  showRequest,
	        success:       showResponse
	    };  
	$("#AnswerForm").ajaxSubmit(options);
}

function fnAutoSaveForInterest(action)
{
	fnSetFormData();
	form.action = '';
	form.action = "${pageContext.request.contextPath}/Test/insertAjax" + action;
	var options = { 
	        beforeSubmit:  showRequest,
	        success:       showInterestResponse
	    };  
	$("#AnswerForm").ajaxSubmit(options);
}




function showRequest(formData, jqForm, options)
{
	$('#testLoading').show();
} 

function showResponse(responseText, statusText, xhr, $form)
{
	$('#testLoading').hide(); 
}

function showInterestResponse(responseText, statusText, xhr, $form)
{
	$('#testLoading').hide(); 
	fnNextInterest();
}



$(document).ready(function()
{
	$('.auto_save').change(function()
	{
		//fnAutoSave('AptitudeAction');
	});
});



</script>
</HTML>