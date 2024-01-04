<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<div class="edupath-padding-summary">
<div class="rows">
	<div class="col-md-12">
	 
	<!-- BEGIN PORTLET-->
	
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa   fa-edit"></i> <s:text name="com.edupath.subadmin.add.title"/>
				</div>
				
				<div class="actions">
					<a href="#" class="btn btn-default btn-sm" onclick="goBack()"> <i
						class="fa fa-back"></i><s:text name="com.edupath.common.label.back"></s:text>
					</a>
	   		 	</div>
			</div>
			
			<!-- BEGIN FORM -->
			<div class="portlet-body" >
				<!-- BEGIN FORM-->
				<s:form cssClass="form-horizontal" name="SubAdminSubmitForm" id="SubAdminSubmitForm" method="post">
				<s:hidden id="userId" name="userId"></s:hidden>
				<s:hidden id="subAdminId" name="id"></s:hidden>
				<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
				<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
						<div class="form-body">
						
							<s:if test="hasActionErrors()">		
								<div class="alert alert-danger ">
									<button class="close" data-dismiss="alert"></button>
									<s:actionerror/>
								</div>
							</s:if>
							
							<div class="form-group">
								<label class="control-label col-md-3">
									<s:text name="com.edupath.subadmin.summary.name"></s:text> <span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-sm" 	  name="name" id="name" value="%{subAdminDTO.name}"/>
									<span class="error-block name-error"> </span>
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.subadmin.summary.email"></s:text><span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-sm" name="emailId" id="emailId"  value="%{subAdminDTO.emailId}" />
									<span	class="error-block email-error"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								<s:text name="com.edupath.subadmin.summary.contactnumber"></s:text><span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-medium" name="contactNumber" id="contactNumber"   value="%{subAdminDTO.contactNumber}"/>
									<span class="error-block contact-error"></span>
								</div>
							</div>
							
							
							
						
							<div class="form-actions">
								<div class="row">
									<div class="col-md-offset-3 col-md-3">
										<button type="button" class="btn blue" onclick="fnSubmit();">
											<i class="fa fa-check"></i> <s:text name="com.edupath.common.label.submit"/>
										</button>
										<button type="button" class="btn default" onclick="fnClear()"> 
											<s:text name="com.edupath.common.label.clear"/>
										</button>
									</div>
								</div>
							</div>
						</div>
					</s:form>
				<!-- END FORM-->
				</div>
			</div>
			<!-- END PORTLET -->
		</div>
	</div>
</div>
<script type="text/javascript">
  var form = document.SubAdminSubmitForm;
  function fnSubmit()
  {
	 if(isValidate())
	 {
		 var id = '${subAdminDTO.id}';
		 if(id != '0')
		 {
			 form.action = "${pageContext.request.contextPath}/myapp/updateSubAdminSubmit";
		 }
		 else
		 {
			 form.action = "${pageContext.request.contextPath}/myapp/insertSubAdminSubmit";
		 }	 
		 form.submit();
	 }	 
  }
  
  function isValidate()
  {
	  $('.error-block').html('');
	  var name = $('#name').val();
	  var email = $('#emailId').val();
	  var contactNumber = $('#contactNumber').val();
	  
	  if(!isNameValid(name))
	  {
		return false;  
	  }	
	  if(!isEmailValid(email))
	  {
		  return false;  
	  }
	  if(!isMobileValid(contactNumber))
	  {
		  return false;
	  }
	  return true;
  }
  
  function isEmailValid(email)
  {
     var reg = /(^([A-Za-z0-9_\-\.\])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$)/;
     
     if(email == '')
     {
    	$('.email-error').html("Email Required.");  
    	return false;
     }	 
     if(reg.test(email) == false)
     {
    	$('.email-error').html("Enter valid email-id."); 
        return false;
     }
     return true;
  }
  
  function isMobileValid(value)
  {
	 if(value == '')
	 {
		 $('.contact-error').html("Contact Number Required."); 
		 return false;
	 }
	 
	 if(isNaN(value))
	 {
		  $('.contact-error').html("Enter valid Contact Number.");
		  return false;  
	 }
	 
  	 var reg = /(^(\+)?\d{10,10}$)/;
  	 if(reg.test(value) == false)
  	 {
  		$('.contact-error').html("Contact Number must be 10 digit.");
		return false; 
  	 }
  	 
  	  var size = value.length;
	  
	  if(size > 10)
	  {
	  	 $('.contact-error').html("Contact Number must be 10 digit.");
	 	 return false;  
	  }	 
  	 return true;
  }
  
  function isNameValid(value)
  {
  	 var size  = value.length;
  	 if(value == '')
  	 {
  		$('.name-error').html("Name Required.");  
  	    return false;
  	 }
  	 if(size < 3 && size < 101)
  	 {
  		$('.name-error').html("Name must be between 3 to 100 char.");  
  		 return false;
  	 }	 
  	 return true;
  		 
  }
  
  function goBack()
  {
	  form.action = "${pageContext.request.contextPath}/myapp/ManageSubAdminAction";
	  form.submit();
  }
  
  function fnClear()
  {
	  $('.error-block').html('');
	  $('#name').val('');
	  $('#emailId').val('');
	  $('#contactNumber').val('');
  }
  
</script>
<style>

  .email_txt
  {
     width: 250px;
     height: 30px;
  }
  
  .name_txt
  {
     width: 200px;
     height: 30px;
  }
  .contact_txt
  {
     width: 150px;
     height: 30px;
  }
  
  .sub_txt
  {
     border: 1px solid #BCBBBE;
     border-radius: 3px;
  }
  .sub-action
  {
    padding-top: 12px;
    text-align: right;
    width: 20%;
  }
  
  .label-div
  {
  	padding-top: 8px;
  } */
  .error-block
  {
    color: rgb(190, 69, 69);
    font-size: 12px;
  }
</style>