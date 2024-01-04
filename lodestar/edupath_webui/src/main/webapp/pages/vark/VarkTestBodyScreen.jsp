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

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-color:#fff;padding-top:5px;padding-bottom:5px" align="center">
	
	<img src="${pageContext.request.contextPath}/images/SIATTEST/lodestar_logo.gif" class="img-responsive">
	</div>
	
	 <div>
       
            <jsp:include page="/pages/vark/VarkQuestionScreen.jsp"></jsp:include>
		   
	</div>
	
	 <div id="testLoading" class="display-hide cust-loader">
	<div class="app-spinner">
<!-- 		<i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i>  -->
		<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
	</div>
</div>	
		

</BODY>


<script>
var form = document.AnswerForm;
 

function showRequest(formData, jqForm, options)
{
	console.log("bharath showRequest")
	$('#testLoading').show();
} 

function showResponse(responseText, statusText, xhr, $form)
{
	console.log("bharath showResponse")
	$('#testLoading').hide(); 
}


$(document).ready(function()
{
});



</script>
</HTML>