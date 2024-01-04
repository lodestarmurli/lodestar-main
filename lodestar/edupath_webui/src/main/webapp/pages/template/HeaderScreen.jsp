<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script src="${pageContext.request.contextPath}/thirdparty/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<style type="text/css">
.dropdown-menu li:hover, .dropdown-menu .active {
  background-color: white;
}
</style>

<s:set name="oldPasswordLabel"><s:text name="com.edupath.change.password.old.label"/></s:set>
<s:set name="newPasswordLabel"><s:text name="com.edupath.change.password.new.label"/></s:set>
<s:set name="confirmNewPasswordLabel"><s:text name="com.edupath.change.password.confirm.label"/></s:set>
<s:set name="submitLabel"><s:text name="com.edupath.common.label.submit"/></s:set>
	
<s:form name="MenuItemForm" id="MenuItemForm" theme="simple">
<s:hidden name="isLogoChanged" id="isLogoChanged"/> 
<s:hidden name="customerId"></s:hidden>
<s:hidden name="studentId" id="studentId"></s:hidden>
<s:hidden name="noteText" id="noteText"></s:hidden>

<%-- <s:hidden name="id" id="id" value="%{session.UserSessionObject.id}"/>
<s:hidden name="roleId" id="roleId" value="%{session.UserSessionObject.roleId}"/> --%>
 <c:set var="width" value="64%"></c:set>
 <c:set var="changeWidth" value="95%"></c:set>
 <c:if test="${session.UserSessionObject.roleWeight eq 2}">
   <c:set var="width" value="22%"></c:set>
   <c:set var="changeWidth" value="85%"></c:set>
 </c:if>
 <div class="row header_div1 hidden-lg "  >
   <div class="col-xs-8 ">
<div class="" id="logo_div" style="padding-left:20px">
 <img alt="LodeStar" class="img-responsive" src="${pageContext.request.contextPath}/images/lodestar_logo.gif">
</div>
</div>
<div class="col-xs-4 " style="padding:22px 22px 22px 60px">
<i class="fa fa-bars fa-2x" aria-hidden="true" onclick="addhiddenclass();"></i>

</div></div>
  <div id="notesInfoDiv" style="top:50px;">
     <jsp:include page="/pages/notes/FacilitatorNotesDetailScreen.jsp"></jsp:include>
 </div>
 
 
<div class="row header_div"  >
<!-- End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 3 -->

<!--Start Changes  code -->
<c:choose>
<c:when test="${session.UserSessionObject.roleWeight eq 2 and !session.UserSessionObject.programTestStudent}">
   <div class="col-md-1 hidden-xs">
  </div>
 </c:when>
<c:otherwise>
    <div class="col-md-6 hidden-xs">
  </div>
</c:otherwise>
 </c:choose>
<!--End  Changes code -->


<!--Start original code -->
 
 <!--  <div class="col-md-3">
  </div>-->
<!--End  original code -->


<!-- End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 3 -->

 
  <div class="col-md-4 header-info-div header-info col-xs-6" style="text-align: right;">
    <span><s:text name="com.edupath.welcome.label"></s:text></span>&nbsp;:&nbsp;<span style="cursor: pointer;" onclick="fnShowDropDown();">${session.UserSessionObject.fullName} &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/images/icons/profile-drop-down-arrow.gif" style="cursor: pointer;" /></span>
	    	<div  id="dropDown" style="width:${changeWidth};">
	    		<label class="password_change_lbl"  onclick="fnShowChangePassDialog()">Change Password</label>
	    	</div>
  </div>
  <c:if test="${session.UserSessionObject.roleWeight eq 2 and !session.UserSessionObject.programTestStudent}">
	  <div class="col-md-1  header-info-div header-info col-xs-3" style="background: #0970bb;">
	  	<span onclick="fnShowStudentCart(this , '${session.UserSessionObject.id}', 'student');" style="cursor: pointer;"><i class="fa fa-shopping-cart"></i>&nbsp;&nbsp;<s:text name="com.edupath.mycart.label"></s:text></span>
	  </div>
	  <div class="col-md-1  header-info-div header-info col-xs-3" style="background: #0970bb;">
    	  <span onclick="fnShowNotes();" style="cursor:pointer;"><img src="${pageContext.request.contextPath}/images/icons/students-notes-reading-interface.gif" height="21"/>&nbsp;&nbsp;<span ><s:text name="com.edupath.notes.label"></s:text></span></span>
   		</div>
  
 	 	<div class="col-md-3  header-info-div header-info col-xs-6" style="background: #0970bb;">
     	   <span onclick="fnShowGuidance();" style="cursor:pointer;">&nbsp;<span ><s:text name="com.edupath.contect.guidance.label"></s:text></span></span>
  		</div>
  </c:if> 

   	<div class="col-md-1  header-info-div header-info col-xs-3" style="background: #0970bb;">
       <span onclick="fnLogOut();" style="cursor:pointer"> <img src="${pageContext.request.contextPath}/images/icons/logout.gif"/>&nbsp;&nbsp;<s:text name="com.edupath.logout.label"></s:text></span> 
  	</div>
  	
  	<!-- Start Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 3 -->
  	
  	
  	
  	<c:if test="${session.UserSessionObject.roleWeight eq 2 and !session.UserSessionObject.programTestStudent }">
  	<div class="col-md-1 col-xs-3 header-info-div header-info" style="background: #0970bb;">
       <span onclick="fnShowhelpdetail();" style="cursor:pointer"> <i class="fa fa-question-circle" aria-hidden="true"></i>&nbsp;&nbsp;<s:text name="com.edupath.help.label"></s:text></span> 
  	</div>
  	</c:if>
  	<c:if test="${session.UserSessionObject.roleWeight eq 3 }">
  	<div class="col-md-1 col-xs-3 header-info-div header-info" style="background: #0970bb;">
       <span onclick="fnShowFacilitatorhelpdetail();" style="cursor:pointer"> <i class="fa fa-question-circle" aria-hidden="true"></i>&nbsp;&nbsp;<s:text name="com.edupath.help.label"></s:text></span> 
  	</div>
  	</c:if>
  	<c:if test="${session.UserSessionObject.roleWeight eq 2 and session.UserSessionObject.programTestStudent  }">
  	<div class="col-md-1 col-xs-3 header-info-div header-info" style="background: #0970bb;">
       <span onclick="fnShowProgramTesthelpdetail();" style="cursor:pointer"> <i class="fa fa-question-circle" aria-hidden="true"></i>&nbsp;&nbsp;<s:text name="com.edupath.help.label"></s:text></span> 
  	</div>
  	</c:if>
  	
  	
  	
  	
  	
  	
  	<!-- End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 for issue no 3 -->
  	
  	
</div>
<c:choose>
<c:when test="${session.UserSessionObject.roleWeight eq 3 && session.StudentSessionObject.id gt 0}"> 
	<div class="row usr_info_div">
	
	<!-- BEGIN Sasedeve Edited By Mrutyunjaya on date 14-02-2017 --> 
	 
	 <!--BEGIN Change Code -->
	 
	 <div class="col-md-4 selected_student_col_8 ">
		  <span class="selected_student"><s:text name="com.edupath.selectstudent.label"></s:text></span>
		  <span class="student_name">${session.StudentSessionObject.fullName}&nbsp;(&nbsp;${session.StudentSessionObject.loginId}&nbsp;)</span>&nbsp;
		  <span class="student_status" onclick="fnGoBackStudentSummary()" style="cursor:pointer">Change</span>  
		  <span class="hidden-lg hidden-md hidden-sm" onclick="toggleSummury();" style="cursor:ponter;margin-left:10px"> More <i class="fa fa-angle-down" aria-hidden="true"></i></span>
		  
	 </div>
	 
	   <div class="col-md-8 " style="text-align: right;" id="summryRemain">
     <div class="custom_student_col custom_student_col_5 " style="display: inline-block; padding-right: 30px;">
	  	<span onclick="fnShowSearch();" style="cursor: pointer;"><img src="${pageContext.request.contextPath}/images/icons/student-search.gif"/>
	  		<span style="vertical-align:bottom;">&nbsp;&nbsp;<s:text name="com.edupath.search.label"></s:text></span>
	  	</span>
	  </div> 
	  
	  <div class="custom_student_col custom_student_col_5 " style="display: inline-block; padding-right: 30px;">
	 	<span onclick="fnShowNotes('FacilitatorNotesAction');"><img src="${pageContext.request.contextPath}/images/icons/student-notes.gif"/>&nbsp;&nbsp;<span class="header_txt_icon"><s:text name="com.edupath.notes.label"></s:text></span></span>
	 </div>
	 
	<div class="custom_student_col custom_student_col_5 " style="display: inline-block; padding-right: 30px;">
	 	<span onclick="fnShowStudentCart(this , '${session.StudentSessionObject.id}', 'facilitator');"><img src="${pageContext.request.contextPath}/images/icons/cart.gif"/>&nbsp;&nbsp;<span class="header_txt_icon"><s:text name="com.edupath.studentcart.label"></s:text></span></span>
	 </div>
	 
	 <div class="custom_student_col custom_student_col_5 " style="display: inline-block; padding-right: 30px;">
	  	<span onclick="fnGenerateStudentReport('${session.StudentSessionObject.id}');" style="cursor: pointer;"><img height="22" width="19" src="${pageContext.request.contextPath}/images/icons/download.gif"/>
	  		<span style="vertical-align:bottom;">&nbsp;&nbsp;<s:text name="com.edupath.report.label"></s:text></span>
	  	</span>
	  </div>
	 
	 
	 <div class=" custom_student_col " style="display: inline-block; padding-right: 10px;">
	    <span onclick="fnShowStudent(this , '${session.StudentSessionObject.id}');"><span class="header_txt_icon"><s:text name="com.edupath.showstudent.label"></s:text></span>&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/images/icons/down.gif"/></span>
	 </div>
	 
	</div>
	
	</div>
	 
	 
	 
	 
	 
	 <!-- END Change Code -->
	 
	 
	 <!--BEGIN Original Code -->
	
	<!-- <div class="col-md-5 selected_student_col_8">
		  <span class="selected_student"><s:text name="com.edupath.selectstudent.label"></s:text></span>
		  <span class="student_name">${session.StudentSessionObject.fullName}&nbsp;(&nbsp;${session.StudentSessionObject.loginId}&nbsp;)</span>&nbsp;
		  <span class="student_status" onclick="fnGoBackStudentSummary()" style="cursor:pointer">Change</span>
	 </div>
	 
	   <div class="col-md-7" style="text-align: right;">
     <div class="custom_student_col custom_student_col_5" style="display: inline-block; padding-right: 30px;">
	  	<span onclick="fnShowSearch();" style="cursor: pointer;"><img src="${pageContext.request.contextPath}/images/icons/student-search.gif"/>
	  		<span style="vertical-align:bottom;">&nbsp;&nbsp;<s:text name="com.edupath.search.label"></s:text></span>
	  	</span>
	  </div> 
	  
	  <div class="custom_student_col custom_student_col_5" style="display: inline-block; padding-right: 30px;">
	 	<span onclick="fnShowNotes('FacilitatorNotesAction');"><img src="${pageContext.request.contextPath}/images/icons/student-notes.gif"/>&nbsp;&nbsp;<span class="header_txt_icon"><s:text name="com.edupath.notes.label"></s:text></span></span>
	 </div>
	 
	<div class="custom_student_col custom_student_col_5" style="display: inline-block; padding-right: 30px;">
	 	<span onclick="fnShowStudentCart(this , '${session.StudentSessionObject.id}', 'facilitator');"><img src="${pageContext.request.contextPath}/images/icons/cart.gif"/>&nbsp;&nbsp;<span class="header_txt_icon"><s:text name="com.edupath.studentcart.label"></s:text></span></span>
	 </div>
	 
	 <div class=" custom_student_col" style="display: inline-block; padding-right: 10px;">
	    <span onclick="fnShowStudent(this , '${session.StudentSessionObject.id}');"><span class="header_txt_icon"><s:text name="com.edupath.showstudent.label"></s:text></span>&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/images/icons/down.gif"/></span>
	 </div>
	</div>
	
	</div>-->
	
	 <!--END Original Code -->
	<!-- END Sasedeve Edited By Mrutyunjaya on date 14-02-2017 --> 
	
	
	
		<div class="rows student_info_div scrollbox_for_body" id="student_info_div" style="padding-left: 10px; padding-right: 10px; margin-bottom:20px;  width:100%; height: 50%; overflow-y: hidden; overflow-x:hidden">
		 
		</div>
		
	
		
 </c:when>

</c:choose>
<div id="showGuidanceId">
	<jsp:include page="/pages/guidance/GuidanceSpecialistPopUpScreen.jsp"></jsp:include>
</div>
<div id="showMyCart">
	<jsp:include page="/pages/studentcart/StudentMyCartModal.jsp"></jsp:include>
</div>
 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content" >
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h5 class="modal-title" style="color: #63A3D1">Change Password</h5>
        </div>
        <div class="modal-body" style="border-top:2px solid #1E5177;">
          
          <div >
			   
			      <div class="row" style="margin-top: 10px;">
			    	<div class="col-md-4">
			    		
			    	</div>
			    	<div class="col-md-8">
			       		<span class="password_status" style="color: red;"></span>
			    	</div>
			    </div>
			    
			    <div class="row" style="margin-top: 10px;">
			    	<div class="col-md-4" style="padding-top: 10px;">
			    		${oldPasswordLabel}
			    	</div>
			    	<div class="col-md-8">
			        <s:password name="oldPassword" id="oldPassword" cssClass="form-control input-sm" placeholder="${oldPasswordLabel}"/>
		    		<span class="error-block"></span>
			    	</div>
			    </div>
			    <div class="row" style="margin-top: 10px;">
			    	<div class="col-md-4" style="padding-top: 16px;">
			    		${newPasswordLabel}
			    	</div>
			    	<div class="col-md-8" style="margin-top: 10px;">
			    		<s:password name="newPassword" id="newPassword" cssClass="form-control input-sm" placeholder="${newPasswordLabel}"/>
		    		<span class="error-block"></span>
			    	</div>
			    </div>
			    <div class="row" style="margin-top: 10px;">
			    	<div class="col-md-4" style="padding-top: 10px;">
			    		${confirmNewPasswordLabel}
			    	</div>
			    	<div class="col-md-8">
			    		<s:password name="confirmNewPassword" id="confirmNewPassword" cssClass="form-control input-sm" placeholder="${confirmNewPasswordLabel}"/>
					<span class="error-block"></span>
			    	</div>
			    </div>
			    <div class="row" style="margin-top: 10px;">
			    	<div class="col-md-12" style="text-align: center;">
			    		<s:submit cssClass="btn btn-sm button "  value="%{submitLabel}"/>
			    	</div>
			    </div>
		  </div>
        </div>
      </div>
    </div>
  </div> 
</s:form>

<script>
var actionToggle = true, noteToggle = true;
var toggle = true, changePasswordToggle = true;
var newWindow;

function toggleSummury()
{
	$("#summryRemain").toggle('slow');
	}

function fnShowChangePassDialog()
{
	$(".password_status").html('');
	$('#dropDown').hide();
	$(".error-block").html('');
	$("#myModal").modal({
		keyboard:false,
		backdrop: 'static'
	});
}

var myCartToggle = true;
function fnLogOut()
{
	form = document.MenuItemForm;
	form.action = "${pageContext.request.contextPath}/Logout";
  	form.submit();
}

function fnGoBackStudentSummary()
{
	form = document.MenuItemForm;
	form.action = "${pageContext.request.contextPath}/myapp/FacilitatorStudentSummaryAction";
  	form.submit();
}
function fnShowStudent(obj , id)
{
	var form = document.MenuItemForm;
	$("#studentId").val(id);
	if(toggle)
	{
		 $("#student_info_div").html('<div align="center"><img src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"></div>');
		form.action = "${pageContext.request.contextPath}/myapp/getStudentTUMStudentInfoAction";
		var options = { 
		        beforeSubmit:  showRequest,
		        success:       showResponse
		    };  
		
		$("#MenuItemForm").ajaxSubmit(options);
		$(obj).find('img').attr("src", '${pageContext.request.contextPath}/images/icons/up.gif');
		$('.student_info_div').slideDown();
		myCartToggle = true;
		toggle = false;
	}
	else
	{
		myCartToggle = true;
		toggle = true;
		$(obj).find('img').attr("src", '${pageContext.request.contextPath}/images/icons/down.gif');
		$('.student_info_div').slideUp();
	}
}
//BEGIN Sasedeve Added function by Mrutyunjaya on date 15-02-2017
function fnGenerateStudentReport(id)
{
	
	var form = document.MenuItemForm;
	$("#studentId").val(id);
	form.action = "${pageContext.request.contextPath}/myapp/getStudentTUMReport";
	form.submit();
		 
}

//END Sasedeve Added function by Mrutyunjaya on date 15-02-2017

function showRequest(formData, jqForm, options){
} 

function showResponse(responseText, statusText, xhr, $form)
{
	$("#student_info_div").show();
	$("#student_info_div").html(responseText);
	
}

var roleW = '${session.UserSessionObject.roleWeight}';
var selectStudentId = '${session.StudentSessionObject.id}';
var height = screen.height;

$('#logo_div').addClass("logo_div");
if(roleW == 3 && selectStudentId != 0)
{
   $('#logo_div').removeClass("logo_div");
   $('#logo_div').addClass("logo_div_selected_stud");
  // $('.sidebar_div').css("min-height", height - 80 + "px");
}
else
{
	// $('.sidebar_div').css("min-height", height - 200 + "px");
}	

$(document).ready(function() { 
	
	 if($(window).width() < 720)
     {
		 $('#summryRemain').hide();
     }
     else
     {
    	 $('#summryRemain').show();
     }
	
$('#MenuItemForm').validate({
	errorElement: 'span', 
    errorClass: 'error-block',
    rules : {
    	oldPassword:{
			required : true,
			minlength: 5
		},
    	newPassword:{
			required : true,
			minlength: 6
		},
		confirmNewPassword:{
			required : true,
			equalTo: "#newPassword"
		}
	},
    invalidHandler: function (event, validator) {
    },
    highlight: function (element) { // hightlight error inputs
        $(element).closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group
    },
    success: function (label,element) {
    	$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
        label.remove();
    	$(".centered-form .panel").css("height","320px")
    },
    submitHandler: function (form) {
    	 fnSaveNewPassword();
    }
});
   $.validator.messages.required="This is a required field";
});

function fnSaveNewPassword()
{
		form = document.MenuItemForm;
		form.action = "ChangePasswordAction";
		//form.submit();
		var options = { 
		        beforeSubmit:  showRequest,
		        success:       showResponse1
		    }; 
	    $("#MenuItemForm").ajaxSubmit(options);
}

function showResponse1(responseText, statusText, xhr, $form)
{
	var data = JSON.parse(responseText);
	if (data.messageSucc != null) 
	{
		$(".password_status").html(data.messageSucc);
		$(".password_status").removeAttr('style').css({'color' : 'green' , 'font-size' : '10pt'});
		
	}
	else
	{
		$(".password_status").html(data.messageErr);
		$(".password_status").removeAttr('style').css({'color' : 'red' , 'font-size' : '10pt'});
	}
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#confirmNewPassword").val("");
}

function fnShowStudentCart(obj, id, roleType){
	var form = document.MenuItemForm;
	if(myCartToggle)
	{
		var action = "${pageContext.request.contextPath}/myapp/StudentCartDetailAction";
		if(roleType == 'facilitator'){
			$('.student_info_div').slideDown();
			toggle = true;
			myCartToggle = false;
			action = action+"?studentId="+id;
		 $("#student_info_div").html('<div align="center"><img src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"></div>');
		}
		else if(roleType == 'student'){
			action = action+"?studentUserId="+id;
			 $("#myCart").html('<div align="center"><img src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"></div>');
			 $('#MyCartId').modal();
		}
			 $.ajax({
				  url: action,
				  data:{"subActionType":roleType},
				  success: function(result){
					  if(roleType == 'facilitator'){
						  $("#student_info_div").html(result);
						  }
					  else if(roleType == 'student'){
						   $("#myCart").html(result);
						  }
					  
				  },
				  error: function(jqXHR, textStatus, errorThrown )
				  {
					  //alert(errorThrown)
				  }
			}); 
		
	}
	else
	{
		toggle = true;
		myCartToggle = true;
		$('.student_info_div').slideUp();
	}
		$('.arrow_down_icon').removeClass('fa-arrow-circle-o-up');
		$('.arrow_down_icon').addClass('fa-arrow-circle-o-down');
}
function fnShowGuidance()
{
	fnGuidance('GuidanceAction');
}

function fnShowDropDown()
{
  if(changePasswordToggle)
  {
	  changePasswordToggle  = false;
	  $('#dropDown').show();
  }
  else
  {
	changePasswordToggle  = true;
    $('#dropDown').hide();
  }	  
}

function fnShowSearch()
{
	if(newWindow!=null)
    {
         if(!newWindow.closed)
         {
        	 newWindow.close();
         }
    }
	
	newWindow = window.open("${pageContext.request.contextPath}/pages/globalsearch/GlobalSearch.jsp","ReadingPane"
			,"height=700px, width=1050px, left=0, top=0, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no",true);
	newWindow.focus();
}


//Start Sasedeve Edited by vyankatesh on date 27-01-2017
function fnShowhelpdetail()
{
	var url="${pageContext.request.contextPath}/pages/help/ShowHelpDetail.jsp";
	 window.open(url, 'StutdentHelp');             
}
//Start Edited by bharath on date 16-07-2020
function fnShowProgramTesthelpdetail()
{
	var url="${pageContext.request.contextPath}/pages/help/ShowPackageTestHelpDetail.jsp";
	 window.open(url, 'StutdentHelp');  
}
//End Edited by bharath on date 16-07-2020
function fnShowFacilitatorhelpdetail()
{
	var url="${pageContext.request.contextPath}/pages/help/ShowFacilitatorHelpDetail.jsp";
	 window.open(url, 'FacilitatorHelp');             
}

function addhiddenclass()
{
	 $(".addhiddenClass").removeClass("hidden-xs hidden-sm hidden-md");
	 $(".sidebarHodden").hide();
	 $('#myModalsidebar').modal({
		    backdrop: 'static',
		    keyboard: false
		})
	
}

function hideModal()
{
	$( ".addhiddenClass" ).addClass( "hidden-xs hidden-sm hidden-md" );
	$('#myModalsidebar').modal('hide');
	
	}

//End Sasedeve Edited by vyankatesh on date 27-01-2017
</script>


