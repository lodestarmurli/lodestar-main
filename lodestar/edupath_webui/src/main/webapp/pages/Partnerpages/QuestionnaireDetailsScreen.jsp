<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="c.tld" prefix="c"%>
<HTML>
<HEAD>
<TITLE><s:text name="com.edupath.webtitle" /></TITLE>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/ApplicationStyles.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js"></script>

<link href="${pageContext.request.contextPath}/styles/help/reset.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/help/style.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/scripts/help/modernizr.js"></script>
<!-- Modernizr -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/ApplicationStyles.css"
	type="text/css">
	
  <style>
.backgroungimg3
	 {
	 background-image: url("${pageContext.request.contextPath}/images/partner/background.png");
	 height:726px
	 }
	 .backgroungimg31
	 {
	
	 height:726px
	 }
	   </style>	
	
</HEAD>
   <BODY>
       
       <div style="top: 0px;width: 100%;z-index:1;background-color:white; ">
		<div class="logo_div"
			style="border-bottom: 4px solid white; padding-left: 70px; padding-top: 11px; height: 90px;width:260px;background-color:white">
			<img alt="LodeStar"
				src="${pageContext.request.contextPath}/images/partner/lodestar_logo.gif">
		</div>
		
		<div class="logo_div"
			style="border-bottom: 4px solid white;height: 90px;width:260px;background-color:white;float: right;margin-top: -90px;">
			
				<img alt="LodeStar"
				src="${pageContext.request.contextPath}/images/partner/${logo}">
		</div>
		
		
		
	</div> 
	<c:choose>
       <c:when  test="${interestCompleted eq true}">
       <div  class="backgroungimg31" >
       
            <jsp:include page="/pages/Partnerpages/InterestQuestionScreen.jsp"></jsp:include>
		   
	</div>
	</c:when>
	
	<c:otherwise>
       <div  class="backgroungimg3" >
       
            <jsp:include page="/pages/Partnerpages/InterestQuestionScreen.jsp"></jsp:include>
		   
	</div>
	</c:otherwise>
	</c:choose>
	
		
 <div id="testLoading" class="display-hide cust-loader">
	<div class="app-spinner">
<!-- 		<i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i>  -->
		<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
	</div>
</div>	
		
<s:form name="AnswerForm" id="AnswerForm" method="post">
   <s:hidden name="answerList" id="answerList"></s:hidden>
  <input type="hidden" name="userid" id="userid" value="${userid}"/>
   <s:hidden name="remainigTime" id="remainigTime"/> 
   <s:hidden name="remainigSecondTime" id="remainigSecondTime"></s:hidden>
   <s:hidden name="remainigMinuteTime" id="remainigMinuteTime"></s:hidden>
</s:form>
<input type="hidden" id="puin" value="${puin}" />
<input type="hidden" id="suin" value="${suin}" />



<div class="footer_div" style="width:100%;position:fixed;bottom: 0px;margin-bottom: 0px;">
<div class="row" style="margin-right:0px;margin-left:0px;">
		
		
	
		
	<div align="center"  class="col-md-12" style="height:30px;">
	
	Your test responses are saved automatically, you may return to continue from where you leave. <a style="color:white" href="http://www.allindiasteps.com/">Click here to go to Allindiasteps.com</a>

	</div>
</div>
</div>



</BODY>  

<script>
var form = document.AnswerForm;
function fnAnswerFormSubmit(action)
{
	fnSetFormDataFinalSubmit;
	  $('#remainigTime').val((60 * 60)  * 1000);
	  form.action = '${pageContext.request.contextPath}/Partner/insert' + action+"?puin="+$('#puin').val()+"&suin="+$('#suin').val();
	  form.submit();
}
 

function fnAutoSave(action)
{
	fnSetFormData();
	form.action = '';
	form.action = "${pageContext.request.contextPath}/Partner/insertAjax" + action;
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
	form.action = "${pageContext.request.contextPath}/Partner/insertAjax" + action;
	var options = { 
	        beforeSubmit:  showRequest,
	        success:       showInterestResponse
	    };  
	$("#AnswerForm").ajaxSubmit(options);
}

function fnregister(action)
{
	 fnSetFormData();
	  $('#remainigTime').val((60 * 60)  * 1000);
	  form.action = '${pageContext.request.contextPath}/Partner/register' + action+"?puin="+$('#puin').val()+"&suin="+$('#suin').val();
	  form.submit();
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