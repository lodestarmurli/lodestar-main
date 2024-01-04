<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
  <c:when test="${pageAction eq 1}">
     <div>
        <jsp:include page="/pages/tum/questionnaire/InterestQuestionScreen.jsp"></jsp:include>
      </div>
  </c:when>
  <c:otherwise>
		<div>
		   <jsp:include page="/pages/tum/questionnaire/AptitudeQuestionScreen.jsp"></jsp:include>
		</div>
  </c:otherwise>
</c:choose>
<div id="testLoading" class="display-hide cust-loader">
	<div class="app-spinner">
<!-- 		<i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i> -->
		<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
	</div>
</div>
<s:form name="AnswerForm" id="AnswerForm" method="post">
   <s:hidden name="answerList" id="answerList"></s:hidden>
   <s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
   <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
   <input type="hidden" name="studentId" id="studentId" value="${session.UserSessionObject.id}"/>
   <s:hidden name="remainigTime" id="remainigTime"/> 
   <s:hidden name="remainigSecondTime" id="remainigSecondTime"></s:hidden>
   <s:hidden name="remainigMinuteTime" id="remainigMinuteTime"></s:hidden>
</s:form>
<script>
var form = document.AnswerForm;
function fnAnswerFormSubmit(action)
{
	fnSetFormDataApptiFinalSubmit();
	  $('#remainigTime').val((60 * 60)  * 1000);
	  form.action = '${pageContext.request.contextPath}/myapp/insert' + action;
	  form.submit();
}

function fnAnswerFormSubmitInteresttest(action)
{
	fnSetFormDataFinalSubmit();
	  $('#remainigTime').val((60 * 60)  * 1000);
	  form.action = '${pageContext.request.contextPath}/myapp/insert' + action;
	  form.submit();
}


function fnGoBack(param)
{
	var sessionstr='${session}';
	console.log("bharath ${session.UserSessionObject.programTestStudent}=>>"+'${session.UserSessionObject}')
	 if(param)
	 {
		form.action = '${pageContext.request.contextPath}/myapp/WelcomeStudentAction';
	 }	
	 else if('${session.UserSessionObject.trial}' == 'true' && '${session.UserSessionObject.programTestStudent}' == 'false')
	 {
		 form.action = '${pageContext.request.contextPath}/myapp/doGetTrialSummaryInterestAction';
	 } 
	 else
	 {
	 	form.action = '${pageContext.request.contextPath}/myapp/WelcomeStudentAction';
	 } 
	 form.submit();	  
} 

function fnAutoSave(action,e)
{
	fnSetFormData(e);
	form.action = '';
	form.action = "${pageContext.request.contextPath}/myapp/insertAjax" + action;
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
	form.action = "${pageContext.request.contextPath}/myapp/insertAjax" + action;
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

function fnUpdateStudentResult(action, time)
{
	  $('#remainigTime').val(time);
	  form.action = "${pageContext.request.contextPath}/myapp/insertStudentResult" + action;
	  var options = { 
		        beforeSubmit:  showRequest,
		        success:       showResponse
		    };  
		$("#AnswerForm").ajaxSubmit(options);
}

$(document).ready(function()
{
	$('.auto_save').change(function()
	{
		fnAutoSave('AptitudeAction',this);
	});
});



</script>