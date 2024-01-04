<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<div>
<!-- Start Sasedeve edited By Mrutyunjaya on Date 31-07-2017 -->
<c:choose>
<c:when test="${Is12plus}">



<c:choose>
  <c:when test="${pageAction eq 1 }">
    <div id="page_1">
	  <jsp:include page="/pages/tum/PersonalAndAcademicInfoScreenFor12Plus.jsp"></jsp:include>
	</div>
  </c:when>
  
  <c:when test="${pageAction eq 2 }">
    <div id="page_2">
	   <jsp:include page="/pages/tum/HobbiesAndInterestScreenFor12Plus.jsp"></jsp:include>
	</div>
  </c:when>
  
  <c:when test="${pageAction eq 3 }">
    <div id="page_3">
	    <jsp:include page="/pages/tum/CareerInterestScreenFor12Plus.jsp"></jsp:include>
	</div>
  </c:when>
</c:choose>









</c:when>
<c:otherwise>



<c:choose>
  <c:when test="${pageAction eq 1 }">
    <div id="page_1">
	  <jsp:include page="/pages/tum/PersonalAndAcademicInfoScreen.jsp"></jsp:include>
	</div>
  </c:when>
  
  <c:when test="${pageAction eq 2 }">
    <div id="page_2">
	   <jsp:include page="/pages/tum/HobbiesAndInterestScreen.jsp"></jsp:include>
	</div>
  </c:when>
  
  <c:when test="${pageAction eq 3 }">
    <div id="page_3">
	    <jsp:include page="/pages/tum/CareerInterestScreen.jsp"></jsp:include>
	</div>
  </c:when>
</c:choose>



</c:otherwise>
</c:choose>
<!-- End Sasedeve edited By Mrutyunjaya on Date 31-07-2017 -->

	<div class="modal fade in" id="TUMCompletedMsgModal" role="dialog" >
			<div class="modal-dialog">
			     <!-- Modal content-->
			   	<div class="modal-content">
			       	<div class="modal-header">
			         		<button type="button" class="close" data-dismiss="modal">&times;</button>
			         		<h4 class="modal-title"></h4>
			       		</div>
			       	<div class="modal-body" style="text-align: center;">
			         		<p style="font-size: 11pt"><s:text name="com.edupath.questionnarie.TUM.compleated.message"></s:text></p>
			       	</div>
			       	<div class="modal-footer" style="text-align: center;">
		         		<button type="button" class="btn custom-modal-btn" data-dismiss="modal"><s:text name="com.edupath.questionnarie.model.btn.label"/></button>
			        </div>
		      	</div>
		    </div>
		</div>

</div>

<script type="text/javascript">
var form = document.StudentTUMForm;
var interval;
var count = 0;
var length = 0;
var staticLength = 0;
var isFirst = true;
var tampArray = [];
$(document).ready(function()
{
	//$('[data-toggle="tooltip"]').tooltip();   
	$('.auto_save').change(function()
	{
		fnSetFormData();
		fnAutoSave();
	});
	

	  var doYouKnow = '${DoYouKnow}';
	  if(doYouKnow.length > 0)
	  {
		  count = ${pageAction} - 1;
		  var data = '${DoYouKnow}'; 
		  var newData = $.parseJSON(data);	
		  staticLength = newData.length;
		  $('.do-know-txt').html('');
		  $('.do-know-txt').html(fnReplaceBackXMLEntities(newData[count]));
		  interval =   setInterval(fnDoYouKnow, 1 * 60 * 1000); 
	  }
});

function isNumber(evt, element) {

	var returnCode = true;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if ((charCode != 46 || $(element).val().indexOf('.') != -1) &&     
       (charCode < 48 || charCode > 57))
    {
    	returnCode =  false;
    }
    return returnCode;
}
 
 function onlyAlphabets(e, t) {
     try {
    	 var charCode ;
         if (window.event) {
              charCode = window.event.keyCode;
         }
         else if (e) {
           charCode = e.which;
         }
         else { return true; }
         if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode == 65 ))
         {
        	 return true;
         }
         else
         {
        	 return false;
         }
     }
     catch (err) {
         alert(err.Description);
     }
 }

function fnAutoSave()
{
	form.action = '';
	form.action = "${pageContext.request.contextPath}/myapp/autoSavePersonalAndAcademicInfoAction";
	var options = { 
	        beforeSubmit:  showRequest,
	        success:       showResponse
	    };  
	$("#StudentTUMForm").ajaxSubmit(options);
}


function showRequest(formData, jqForm, options){
} 

function showResponse(responseText, statusText, xhr, $form)
{
	 
}

function fnStudentTUMFormSubmit(action, newAction)
{
	// if(isValid())
	// {
		 fnSetFormData();
		 form.action = "${pageContext.request.contextPath}/myapp/insert" + action;
		 $('#actionNameValue').val(newAction);
		 form.submit(); 
	// }	 
}

function fnBackSubmit(action, pageAction, newAction)
{
	$('#actionNameValue').val(newAction);
	 if(pageAction == "1")
	 {
		 $('#serialNum').val(7);
	 }	 
	 if(pageAction == "2")
	 {
		 $('#pageAction').val(pageAction);
		 fnSetFormData();
		 form.action = "${pageContext.request.contextPath}/myapp/insert" + action; 
		 form.submit(); 
		/*  if(isValid())
		 {
			 $('#pageAction').val(pageAction);
			 fnSetFormData();
			 form.action = "${pageContext.request.contextPath}/myapp/insert" + action; 
			 form.submit(); 
		 } */	 
	 }
	 else
	 {
		$('#pageAction').val(pageAction);
	    form.action = "${pageContext.request.contextPath}/myapp/back" + action;
	    form.submit(); 
	 }
}

function fnDoYouKnow()
{ 
   $(".do-know-txt").slideUp();
   var data = '${DoYouKnow}'; 
   var newData = $.parseJSON(data);	
   if(count == staticLength)
   {
	   count = 0;  
   }
   var str = newData[count];
   $('.do-know-txt').html('');
   $('.do-know-txt').html(fnReplaceBackXMLEntities(str));
   $( ".do-know-txt" ).slideDown();
   count++;
}

function fnStudentTUMFinalSubmit(action)
{
	fnSetFormData();
	$('#TUMCompletedMsgModal').modal("show");
	$('#pageAction').val(4);
	//$('#actionNameValue').val(action);
	form.action = "${pageContext.request.contextPath}/myapp/insert" + action;
	form.submit(); 
}

function fnTextSave()
{
	fnSetFormData();
	fnAutoSave();
}
</script>
<style>
.author_name
{
  width: 100%;
  text-align: right;
  font-weight: bold;
}
</style>
