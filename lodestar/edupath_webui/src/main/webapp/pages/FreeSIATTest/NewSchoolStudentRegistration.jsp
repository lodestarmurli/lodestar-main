<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<HTML>
<HEAD>
<TITLE><s:text name="com.edupath.webtitle" /></TITLE>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/scripts/CommonScripts.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery_form.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/thirdparty/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/thirdparty/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript" ></script>

<script src="${pageContext.request.contextPath}/scripts/components-pickers.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.textcomplete.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/nanoScroller/nanoScrollerJS.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery/js/table-editable.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/nanoScroller/enscroll-0.6.1.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/selectpicker/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/local-store-script.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <style>
  
  </style>
</HEAD>
<BODY style=" background-color:#f0f9ff">

	
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-color:#fff;padding-top:5px;padding-bottom:5px" align="center">
	
	<img src="${pageContext.request.contextPath}/images/SIATTEST/lodestar_logo.gif" class="img-responsive">
	</div>
		
	
	<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12 col-md-push-6">
	<br><br>
	<form>
	
	<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-3 col-md-offset-2">
	<span style="font-size:20pt;color:#1276bb">Register <b> Now</b></span><br>
	<input type="hidden" name="source" value="${param.source}">
   <span style="font-size: 10pt;color:#1276bb;font-weight: 600;">Student Interest Assessment Test</span>
   
	<br/>
  <p></p>
	 <div class="form-group">
      <label for="inputlg">Student Name <font color="red">*</font></label>
      <input class="form-control input-lg" id="Name" type="text">
    </div>
    <div class="form-group">
      <label for="inputlg">Gender <font color="red">*</font></label>
      <select class="form-control input-lg" id="Gender" >
      <option value="0">---Select gender---</option>
      <option value="M">Male</option>
      <option value="F">Female</option>
      </select>
    </div>
    

    
    	 <div class="form-group">
      <label for="inputlg">Class <font color="red">*</font></label>
      <select class="form-control input-lg" id="Class" >
      <option value="0">---Select class---</option>
      <c:if test="${null ne classList and not empty classList}">
	   <c:forEach items="${classList}" var="classItem">
		<option value="${classItem.id}">${utils:replaceXMLEntities(classItem.name)}</option>
		</c:forEach>
	  </c:if>
      </select>
      
      
      
      
    </div>
    
    
	 <div class="form-group">
      <label for="inputlg">Student's Email Address <font color="red">*</font></label>
      <input class="form-control input-lg" id="Email" type="text" value="${Stemail}">
    </div>
    
    <div class="form-group">
      <label for="inputlg">Student's Mobile Number <font color="red">*</font></label>
      <input class="form-control input-lg" id="pno" type="text" value="${Stnumber}">
    </div>
    

    
    
    <div class="form-group">
      <label for="inputlg">Parent's Email Address <font color="red">*</font></label>
      <input class="form-control input-lg" id="pEmail" type="text" value="${Stemail}">
    </div>
    
    <div class="form-group">
      <label for="inputlg">Parent's Mobile Number <font color="red">*</font></label>
      <input class="form-control input-lg" id="ppno" type="text" value="${Stnumber}">
    </div>
    
    
    
    
    
    
    
    
	
       <div id="sb" class="col-sm-offset-3 col-md-offset-1 col-lg-offset-2 col-sm-7 col-md-10 col-lg-8" >
        <button id="sbb" type="button" onclick="StudentRegistor();" class="btn btn-info btn-lg btn-block">Submit</button>
		</div>
      <br/><br/><br/>
      
      <div id="testLoading" align="center" class="col-sm-offset-3 col-md-offset-3 col-lg-offset-3 col-sm-5 col-md-5 col-lg-6">
	
		<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
	
</div>
      
      
      
    </div> 
	</form>
	</div>
	
		
		
		
		
	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-md-pull-6">
<br><br>
	<div class="backgroungimg1" >
	<div class="col-sm-10 col-sm-offset-1">
   <span style="font-size:20pt;color:#1276bb">THE LODESTAR</span><br>
   <span style="font-size: 19pt;color:#1276bb;font-weight: 600;">CAREER GUIDANCE TEST</span>
	<br><br>
  </div>


  <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 col-xs-offset-1 " style="color:#414042;font-size:10pt">
    Passion is often the difference between failure and success in all walks of life, including jobs and careers. To help you progress towards a successful career, there are parameters that need to be considered and aligned:<br><br>
  </div>
  <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 col-xs-offset-1 " style="color:#414042;font-size:10pt">
  <ul style="color:#1276bb;font-size:10pt;list-style:disc;">
			<li style="color:#1276bb "><font color="#414042">Your Interests & Personality</font></li>
			<li style="color:#1276bb"><font color="#414042">Cognitive abilities</font></li>
			<li style="color:#1276bb"><font color="#414042">Choice of Career</font></li>
			<li style="color:#1276bb"><font color="#414042">Education Path - College, Graduation, Post Graduation and so on</font></li>
			
		</ul>
		 </div>
  
   <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 col-xs-offset-1 " style="color:#414042;font-size:10pt">
The test that you are about to take is based on internationally proven Holland's theory of Vocational Personalities in Work Environments, commonly known as Holland codes theory. This theory propounds that people and occupations have unique characteristics and that both of these characteristics can be measured. The theory goes onto say that individuals have greater job satisfaction when they take up jobs that match the characteristics they hold. <font color="#1276bb">The 6 personality types as per Holland Codes are - Realistic, Investigative, Artistic, Social, Enterprising and Conventional (RIASEC).</font>
</div>
<div class="col-sm-10 col-lg-12 col-md-12 col-xs-10 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 col-xs-offset-1">
<br>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12" >
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/r.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/i.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/a.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/s.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/e.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/c.png" class="img-responsive">
<br><br><br><br>
</div>


</div>




</div>
	
	</div>
	
	
	
		
		
		
	
	
	

	
	
	
	
	
	
	
	
<s:form name="Studentreg" id="Studentreg" method="post">
   <input type="hidden" name="srname" id="srname" />
   <input type="hidden" name="sremailid" id="sremailid" />
   <input type="hidden" name="srclassid" id="srclassid" />
   <input type="hidden" name="srgender" id="srgender" />
   <input type="hidden" name="srpno" id="srpno" />
    <input type="hidden" name="prpno" id="prpno" />
     <input type="hidden" name="premail" id="premail" />
</s:form>

<s:form name="SIATTest" id="SIATTest" method="get">
  <input type="hidden" name="id" id="id" value="" />
</s:form>

</BODY>

<script>
var form = document.Studentreg;
function StudentRegistor()
{
	form.action = '';
	
	if(Validatinginput())
	{
		$('#srname').val($('#Name').val());
		$('#sremailid').val($('#Email').val());
		$('#srclassid').val($('#Class').val());
		$('#srgender').val($('#Gender').val());
		$('#srpno').val($('#pno').val());
		
		$('#prpno').val($('#ppno').val());
		$('#premail').val($('#pEmail').val());
		
		
		
		form.action = "${pageContext.request.contextPath}/APIS/NewSchoolStudentRegistor";
		var options = { 
		        beforeSubmit:  showRequest,
		        success:       showResponse
		    };  
		$("#Studentreg").ajaxSubmit(options);
	}
	
	
}
function Validatinginput()
{
	var status=true;
	
	if($('#Name').val()!=null && $('#Name').val().trim()!="")
	{}
	else
	{
		$('#Name').addClass('has-error has-danger');
		$('#Name').focus();
		return false;
	}
	
	if($('#Email').val()!=null && $('#Email').val().trim()!="")
	{
		 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		
		 if (reg.test($('#Email').val()) == false) 
	       {
			    alert('Invalid Email Address');
			    $('#Email').addClass('has-error has-danger');
				$('#Email').focus();
				return false;
	       }
		
	}
	else
	{
		$('#Email').addClass('has-error has-danger');
		$('#Email').focus();
		return false;
	}
	
	
	if($('#pEmail').val()!=null && $('#pEmail').val().trim()!="")
	{
		 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		
		 if (reg.test($('#pEmail').val()) == false) 
	       {
			    alert('Invalid Email Address');
			    $('#pEmail').addClass('has-error has-danger');
				$('#pEmail').focus();
				return false;
	       }
		
	}
	else
	{
		$('#pEmail').addClass('has-error has-danger');
		$('#pEmail').focus();
		return false;
	}
	
	
	
	
	
	
	
	if($('#pno').val()!=null && $('#pno').val().trim()!="")
	{
		
		var phoneno = /^\d{10}$/; 
		
		if($('#pno').val().trim().match(phoneno))
		{}
		else
		{
			alert('Invalid Phone Number');
		    $('#pno').addClass('has-error has-danger');
			$('#pno').focus();
			return false;
		}
		
		
	}
	else
	{
		$('#pno').addClass('has-error has-danger');
		$('#pno').focus();
		return false;
	}
	
	
	if($('#ppno').val()!=null && $('#ppno').val().trim()!="")
	{
		
		var phoneno = /^\d{10}$/; 
		
		if($('#ppno').val().trim().match(phoneno))
		{}
		else
		{
			alert('Invalid Phone Number');
		    $('#ppno').addClass('has-error has-danger');
			$('#ppno').focus();
			return false;
		}
		
		
	}
	else
	{
		$('#ppno').addClass('has-error has-danger');
		$('#ppno').focus();
		return false;
	}
	
	
	
	
	
	
	if($('#Class').val()!=null && $('#Class').val().trim()!="")
	{
		if($('#Class').val()==0)
		{
			alert('Please select class')
			$('#Class').addClass('has-error has-danger');
			$('#Class').focus();
			return false;
		}
		
	}
	else
	{
		$('#Class').addClass('has-error has-danger');
		$('#Class').focus();
		return false;
	}
	
	if($('#Gender').val()!=null && $('#Gender').val().trim()!="")
	{
		
		if($('#Gender').val()==0)
		{
			alert('Please select gender')
			$('#Gender').addClass('has-error has-danger');
			$('#Gender').focus();
			return false;
		}
		
	}
	else
	{
		$('#Gender').addClass('has-error has-danger');
		$('#Gender').focus();
		return false;
	}
	
	
	return status;
}

function showRequest(formData, jqForm, options)
{
	$('#testLoading').show();
	$('#sb').hide(); 
	
} 

function showResponse(responseText, statusText, xhr, $form)
{
	
	//alert(JSON.stringify(responseText));
	if(responseText.status=="success")
	{
		
		$("#id").val(responseText.id);
		var form1 = document.SIATTest;
		form1.action = "${pageContext.request.contextPath}/Test/StartSIATTestStudentRegistration";
		 form1.submit();
	}
	else if(responseText.status=="duplicate")
	{
		alert("Already Register");
		$('#testLoading').hide(); 
		$('#sb').show();
	}
	else
	{
		alert("Error Please try again...");
		$('#testLoading').hide();
		$('#sb').show();
	}
	
	//alert(responseText.status);
	//$('#testLoading').hide(); 
}
$(document).ready(function()
		{
	$('#testLoading').hide(); 
		});
</script>
</HTML>
