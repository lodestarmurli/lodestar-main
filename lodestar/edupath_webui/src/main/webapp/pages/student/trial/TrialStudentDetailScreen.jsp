<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib prefix="c" uri="c.tld"%>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<div class="rows">
	<!--  <div class="col-md-4 trial-content">-->
	<div class="col-md-4 trial-content">
		<p>
			Passion is often the difference between failure and success in all walks of life, including jobs and careers. To help the student progress towards a successful career, there are parameters that need to be considered and aligned
		</p>
		<p>
			- Student Interests & Personality   
		</p>
		<p>
			- Cognitive abilities 
		</p>
		<p>
			- Choice of Career 
		</p>
		<p>
			- Education Path - Stream, Subject and College
		</p>
		
		<p>
			At Lodestar, we understand these complexities and have developed tools and techniques to help the student become aware, explore, evaluate, and identify the ideal path towards a successful career.
		</p>
		<p>As a first step to discover his / her  &#x22True Calling&#x22, take a FREE Student Interest Assessment Test (SIAT).</p>
		<p>
			<font color="red">To register, please fill all details in the form on the right including at least one parent's valid e-mail Id and contact details.</font>
			
		</p>
		<p>
			On registration, you will receive a User Id and password to access the test. A detailed report will be sent to your e-mail once the assessment is completed.
		</p>
	</div>
	<div class="col-md-8">
		<div class="" style="margin-top:3%; margin-bottom:20px;">
		
		
		
			<s:form cssClass="form-horizontal trial-form" name="studentTrailSubmitForm" id="studentTrailSubmitForm" method="post">
				<s:hidden name="authKey"></s:hidden>
			    <div class="form-body">
			    
			    <div class="change_right_div change_p_h6" align="center">
               
                <span class="change_p_h4" style="font-weight:800">Student Interest Assessment Test - Registration (SIAT)</span>
             </div>
			    
					<div id="error_alertId" style="display: none;">
					</div>
					<div class="form-group">
						<label class="control-label col-md-3"> 
						</label>
						<div class="col-md-6">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger ">
									<button class="close" data-dismiss="alert"></button>
									<s:actionerror />
								</div>
							</s:if>
							<s:if test="hasActionMessages()">
								<div class="alert alert-success ">
									<span class="close" data-dismiss="alert"></span>
									<s:actionmessage/>
								</div>
							</s:if>
		
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4"> <s:text
								name="com.edupath.student.summary.name"></s:text> <span class="required" aria-required="true"> * </span>
						</label>
						<div class="col-md-5">
							<s:textfield cssClass="form-control input-sm" name="name"
								id="name" value="%{bean.name}" autocomplete="off" maxlength="100" />
							<span class="error-block name-error"> </span>
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-md-4"> <s:text
								name="com.edupath.student.detail.gender"></s:text> <span
							class="required" aria-required="true"> * </span>
						</label>
						<div class="col-md-5">
							<s:radio cssClass="radio-list" name="gender" id="gender"
								list="#{'M':'Male','F':'Female'}" value="%{bean.gender}" />
							<span class="error-block gender-error"> </span>
						</div>
					</div>
		
					
		
					<div class="form-group">
						<label class="control-label col-md-4"> <s:text
								name="com.edupath.student.detail.city"></s:text><span
							class="required" aria-required="true"> * </span>
						</label>
						<div class="col-md-5">
							<s:select cssClass="form-control input-sm" name="cityId"
								id="cityId" listKey="id" listValue="name" list="cityList"
								headerKey="" headerValue="--Select--"
								value="%{bean.cityId}" />
							<span class="error-block city-error"></span>
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-md-4"> <s:text
								name="com.edupath.student.detail.school"></s:text><span
							class="required" aria-required="true"> * </span>
						</label>
						<div class="col-md-5">
							<select name="schoolId" id="schoolId" class="form-control input-sm" onchange="fnSchoolChange();">
								<option value="">--Select--</option>
								<c:if test="${null ne schoolList and not empty schoolList}">
									<c:forEach items="${schoolList}" var="schoolItem">
										<option value="${schoolItem.id}" ${schoolItem.id == bean.schoolId ? 'selected' : ''} data-code="${schoolItem.code}">${utils:replaceXMLEntities(schoolItem.name)}</option>
									</c:forEach>
								</c:if>
							</select>
							<s:textfield name="otherSchool" id="otherSchool" class="form-control input-sm otherSchool" cssStyle="display:none; margin-top:10px;font-size:12px;" maxlength="100"></s:textfield>
							<span class="error-block school-error"></span>
							<span  id="school-error" style="font-size:12px;"></span>
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-md-4"> <s:text
								name="com.edupath.student.detail.class"></s:text><span
							class="required" aria-required="true"> * </span>
						</label>
						<div class="col-md-5">
							<select name="classId" id="classId" class="form-control input-sm"  onchange="fnchangeRequiredField(this.value);">
								<option value="">--Select--</option>
								<c:if test="${null ne classList and not empty classList}">
									<c:forEach items="${classList}" var="classItem">
										<option value="${classItem.id}" ${classItem.id == bean.classId ? 'selected' : ''} data-gapkey="${classItem.gap}">${utils:replaceXMLEntities(classItem.name)}</option>
									</c:forEach>
								</c:if>
							</select>	
							<span class="error-block class-error"></span>
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-md-4"> <s:text
								name="com.edupath.student.detail.section"></s:text>
						</label>
						<div class="col-md-5">
							<s:textfield cssClass="form-control input-sm" name="section"
								id="section" value="%{bean.section}"
								autocomplete="off" maxlength="2"/>
							<span class="error-block section-error"> </span>
						</div>
					</div>
					<!--  Vyankatesh Edit Add field-->
					
					<div class="form-group test-cls" >
							<label class="control-label col-md-4"> <s:text
									name="com.edupath.student.detail.studentemail"></s:text><span
								class="required showstudentstar" aria-required="true">  * </span>
							</label>
							<div class="col-md-5">
								<s:textfield cssClass="form-control input-sm"
									name="studentemailId" id="studentemailId"
									value="%{bean.studentemailId}" autocomplete="off" />
								<span class="error-block studentemail-error"></span>
							</div>
						</div>
						
						<div class="form-group test-cls" > 
							<label class="control-label col-md-4"> <s:text
									name="com.edupath.student.detail.studentcontactno"></s:text><span
								class="required showstudentstar" aria-required="true"> * </span>
							</label>
							<div class="col-md-5">
								<s:textfield cssClass="form-control input-sm"
									name="studentcontactNumber" id="studentcontactNumber"
									value="%{bean.studentcontactNumber}" autocomplete="off" />
								<span class="error-block studentcontact-error"></span>
							</div>
						</div>
						
						<!--  Vyankatesh Edit Add field-->
						
						<div class="form-group">
						<label class="control-label col-md-4"> <s:text
								name="com.edupath.student.detail.fatherName"></s:text> <span
							class="required " aria-required="true">  </span>
						</label>
						<div class="col-md-5">
							<s:textfield cssClass="form-control input-sm" name="fathername"
								id="fathername" value="%{bean.fathername}"
								autocomplete="off" maxlength="100"/>
							<span class="error-block fathername-error"> </span>
						</div>
					</div>
						
						
						<div class="form-group">
						<label class="control-label col-md-4"> <s:text
								name="com.edupath.student.detail.fathertrialemail"></s:text><span
							class="required " aria-required="true">  </span>
						</label>
						<div class="col-md-5">
							<s:textfield cssClass="form-control input-sm"
								name="fatheremailId" id="fatheremailId"
								value="%{bean.fatheremailId}" autocomplete="off" maxlength="200"/>
							<span class="error-block email-error"></span>
						</div>
					</div>
					
					
		
					<div class="form-group">
						<label class="control-label col-md-4"> <s:text
								name="com.edupath.student.detail.fathecontactno"></s:text><span
							class="required showparentstar" aria-required="true">  </span>
						</label>
						<div class="col-md-5">
							<s:textfield cssClass="form-control input-sm"
								name="contactNumber" id="contactNumber"
								value="%{bean.contactNumber}" autocomplete="off" maxlength="15"/>
							<span class="error-block contact-error"></span>
						</div>
					</div>
		
					
					
					<!--  Vyankatesh Edit Add field-->
						
						
						
						<div class="form-group test-cls">
							<label class="control-label col-md-4"> <s:text
									name="com.edupath.student.detail.motherName"></s:text><span
								class="required showparentstar" aria-required="true"> </span>
							</label>
							<div class="col-md-5">
								<s:textfield cssClass="form-control input-sm"
									name="motherName" id="motherName"
									value="%{bean.motherName}" autocomplete="off" /> 
								<span class="error-block mothername-error"></span>
							</div>
						</div>
						<div class="form-group test-cls" >
							<label class="control-label col-md-4"> <s:text
									name="com.edupath.student.detail.motheremail"></s:text><span
								class="required showparentstar" aria-required="true">  </span>
							</label>
							<div class="col-md-5">
								<s:textfield cssClass="form-control input-sm"
									name="motheremailId" id="motheremailId"
									value="%{bean.motheremailId}" autocomplete="off" />
								<span class="error-block motheremail"></span>
							</div>
						</div>
						<div class="form-group test-cls">
							<label class="control-label col-md-4"> <s:text
									name="com.edupath.student.detail.mothercontactno"></s:text><span
								class="required showparentstar" aria-required="true">  </span>
							</label>
							<div class="col-md-5">
								<s:textfield cssClass="form-control input-sm"
									name="mothercontactno" id="mothercontactno"
									value="%{bean.mothercontactno}" autocomplete="off" />
								<span class="error-block mothercontactno"></span>
							</div>
						</div>
						<!--  Vyankatesh Edit Add field-->
					
					<div class="form-group">
						<label class="control-label col-md-4"></label>
						<div class="col-md-5">
							 <div class="g-recaptcha" data-sitekey="${recaptchaSiteKey}"></div>
							 <span class="error-block" id="error_msg"></span>
						</div>
					</div>
		
					<div class="form-actions">
						<div class="row text-right">
							<div class="col-md-offset-4 col-md-5">
								<button type="submit" class="btn blue" id="submitButtonId">
									<i class="fa fa-check"></i>
									<s:text name="com.edupath.common.label.submit" />
								</button>
								<button type="reset" class="btn default"  style="margin-left: 20px;">
									<s:text name="com.edupath.common.label.clear" />
								</button>
							</div>
						</div>
					</div>
				</div>
			</s:form>
		</div>
	</div>
</div>

<script>
var validator;
$(document).ready(function () 
{
	var emailValue = $('#fatheremailId').val();

	
	var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	jQuery.validator.addMethod("emailValid", function(emailValue, element){
	    if (filter.test(emailValue)) {
	        return true;  // FAIL validation when REGEX matches
	    } else {
	        return false;   // PASS validation otherwise
	    };
	}, "Please enter a valid email.");
	
	
	
	 validator = $('#studentTrailSubmitForm').validate({
		
    	//errorElement: 'div', 
        errorClass: 'error-block',
        errorPlacement: function(error, element) 
        {
        	$(element).parent().find('.error-block').remove();
        	$(element).parent().append('<div class=error-block >' + error.text() +'</div>');
        },
        rules: {
        	name:{
        		required: true,
        	},
        	gender: 
        	{
                required: true
            },
            /* fathername: 
            {
            	required: true
            },*/
           
            cityId : {
            	required: true
            },
            classId : {
            	required: true
            },
            schoolId : {
            	required: true
            },
            /*  fatheremailId: 
        	{
                required: true,
                emailValid: true
            },*/
            /* contactNumber: {
                required: true,
                maxlength: 15
            },*/
        },
        invalidHandler: function (event, validator) 
        { //display error alert on form submit   

        },
        highlight: function (element) 
        { // hightlight error inputs
            $(element).closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group
        },
        success: function (label,element) 
        {
        	$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            label.remove();
        },
        submitHandler: function (form)
        {
        	
        	$('#error_msg, #school-error').html("");
        	$('.email-error, .contact-error, .studentemail-error, .studentcontact-error, .motheremail, .mothercontactno, .mothername').html("");
        	
        	
        	var code = $("#schoolId option:selected").data('code');
        	var text = $("#schoolId option:selected").text();
        	var classId = $('#classId').val();
        	var studentcontactNo = $.trim($('#studentcontactNumber').val());   
    		var studentemail = $.trim($('#studentemailId').val());
    		
    		var motherName = $.trim($('#motherName').val());
    		var motheremail = $.trim($('#motheremailId').val());
    		var mothercontactno = $.trim($('#mothercontactno').val());
    		var contactNo = $.trim($('#contactNumber').val());
    		var email = $.trim($('#fatheremailId').val());
    		var fathername = $.trim($('#fathername').val());
        	
        	if(code == -1)
        	{
        		if($('.otherSchool').val() == undefined || $('.otherSchool').val() == '')
        		{
        			$('#school-error').html("This field is required.").show().css('color','#A94442');
            		return false;
        		}	
        	}
        	
        	if(classId==1 || classId==2)
        	{
        		if(email !== "")
        			{
        			
	        			if (!isEmailValid(email)) {
	            			return false;
	            		
	            		}
	        			
	            		if (!isfathernameNameValid(fathername)) {
	            			return false;
	            		
	            		}
	            		
	            		
	            		if(motheremail !== "")
	            			{
		            			if (!isMotherEmailValid(motheremail)) {
		                			return false;
		            			}
	            			}
	            		if(motherName !== "")
	            			{
		            			if (!isMotherNameValid(motherName)) {
		                			return false;
		                		
		                		}
	            			
	            			}
        			
        			}
        		else if(motheremail !== '')
        		{
        			if (!isMotherEmailValid(motheremail)) {
            			return false;
            		
            		}
        			if (!isMotherNameValid(motherName)) {
            			return false;
            		
            		}
        			
        			
        			
        			if(email !== "")
	        			{
	        				if (!isEmailValid(email)) {
		            			return false;
		            		
		            		}
	        			}
        			if(fathername !== "")
	        			{
	        				if (!isfathernameNameValid(fathername)) {
		            			return false;
		            		
		            		}
	        			
	        			}
        			
        			
        		}
        		else
        		{
        			$('.email-error').html("Email is required information.");
        			return false;
        		}
        		
        		if(contactNo !== '')
        			{
        			
	        			if (!isMobileValid(contactNo))
	                		{
	                			return false;
	                		}
        			
        			}
        		else if(mothercontactno !== '')
        			{
	        			if (!isMotherMobileValid(mothercontactno))
	            		{
	            			return false;
	            		}
        			}
        		else
        			{
	        			$('.contact-error').html("Contact Number Required.");
	        			return false;
        			}
        			
        	
        		
        	}
        else 
        	{
        		if (!isStudentEmailValid(studentemail)) {
        			return false;
        		}
        			if (!isStudentMobileValid(studentcontactNo)) {
        				return false;
        			}
        			
        			if(email !== "")
        			{
        				if (!isEmailValid(email)) 
	        				{
		            			return false;
		            		
		            		}
	        			
	            		if (!isfathernameNameValid(fathername)) 
		            		{
		            			return false;
		            		
		            		}
	            		if(motheremail !== "")
	            			{
		            			if (!isMotherEmailValid(motheremail)) {
		                			return false;
		            			}
	            			}
	            		if(motherName !== "")
	            			{
		            			if (!isMotherNameValid(motherName)) {
		                			return false;
		                		
		                		}
	            			
	            			}
        			
        			}
        		else if(motheremail !== '')
        			{
	        			if (!isMotherEmailValid(motheremail)) {
	            			return false;
	            			}
	        			if (!isMotherNameValid(motherName)) {
	            			return false;
	            			}
        				if(email !== "")
		        			{
		        				if (!isEmailValid(email)) {
			            			return false;
			            		}
		        			}
        			if(fathername !== "")
	        			{
	        				if (!isfathernameNameValid(fathername)) {
		            			return false;
		            		}
	        			}
        			
        			
        		}
        		else
	        		{
	        			$('.email-error').html("Email is required information.");
	        			return false;
	        		}
        		
        		if(contactNo !== '')
        			{
	        			if (!isMobileValid(contactNo))
		                		{
		                			return false;
		                		}
        			}
        		else if(mothercontactno !== '')
        			{
	        			if (!isMotherMobileValid(mothercontactno))
	            		{
	            			return false;
	            		}
        			}
        		else
        			{
	        			$('.contact-error').html("Contact Number Required.");
	        			return false;
        			}
        		
        	}
        	


        	var captcharesp = grecaptcha.getResponse();
        	//if(captcharesp == '')
        	//{
        	//	$('#error_msg').html("Please take captcha challenge").show().css('color','#A94442');
        	//	return false;
			//}

        	


           fnSave('#trialContentDiv','${pageContext.request.contextPath}/doAddtrial', $(form).serialize());
        }
    });
});

function fnSave(idOrClass, url, data)
{
	$('#submitButtonId').prop("disabled", true);
	$.ajax({
		url : url,
		type : 'POST',
		data : data,
		success : function (response)
		{
			$('#submitButtonId').prop("disabled", false);
			$(idOrClass).html('');
			$(idOrClass).html(response);
		},
		error : function (errRess)
		{
			$('#submitButtonId').prop("disabled", false);
			$(idOrClass).html('');
			$(idOrClass).html(errRess);
		}
	});
}

function fnClear()
{
	var validators = $("#studentTrailSubmitForm" ).validate();
	validators.resetForm();
	/* $('.error-block').html('');
	$('#name').val('');
	$('.radio-list').prop("checked", false);
	$('#fathername').val('');
	$('#cityId').val(-1);
	$('#schoolId').val(-1);
	$('#classId').val(-1);
	$('#contactNumber').val('');
	$('#fatheremailId').val(''); */
}

function fnSchoolChange()
{
	var code = $("#schoolId option:selected").data('code');
	var text = $("#schoolId option:selected").text();
	if(code == -1)
	{
		$('.otherSchool').val('');
		$('.otherSchool').show();
	}
	else
	{
		$('.otherSchool').val('');
		$('.otherSchool').hide();
	}	
}

//vyankatesh add validation 

function isEmailValid(email) {
		var reg = /(^([A-Za-z0-9_\-\.\])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$)/;

		if (email == '') {
			$('.email-error').html("Email is required information.");
			return false;
		}
		if (reg.test(email) == false) {
			$('.email-error').html("Enter valid email-id.");
			return false;
		}
		
		return true;
	}
	
	

	function isMobileValid(value) {
		if (value == '') {
			$('.contact-error').html("Contact Number Required.");
			return false;
		}

		if (isNaN(value)) {
			$('.contact-error').html("Enter valid Contact Number.");
			return false;
		}

		var reg = /(^(\+)?\d{10,10}$)/;
		if (reg.test(value) == false) {
			$('.contact-error').html("Contact Number must be 10 digit.");
			return false;
		}

		var size = value.length;

		if (size > 10) {
			$('.contact-error').html("Contact Number must be 10 digit.");
			return false;
		}
		return true;
	}

function isStudentEmailValid(email) {
	var reg = /(^([A-Za-z0-9_\-\.\])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$)/;   

	if (email == '') {
		$('.studentemail-error').html("Email is required information.");
		return false;
	}
	if (reg.test(email) == false) {
		$('.studentemail-error').html("Enter valid email-id.");
		return false;
	}
	return true;
}

function isStudentMobileValid(value) {
	if (value == '') {
		$('.studentcontact-error').html("Contact Number Required.");   
		return false;
	}

	if (isNaN(value)) {
		$('.studentcontact-error').html("Enter valid Contact Number.");
		return false;
	}

	var reg = /(^(\+)?\d{10,10}$)/;
	if (reg.test(value) == false) {
		$('.studentcontact-error').html("Contact Number must be 10 digit.");
		return false;
	}

	var size = value.length;

	if (size > 10) {
		$('.studentcontact-error').html("Contact Number must be 10 digit.");
		return false;
	}
	if (size < 10) {
		$('.studentcontact-error').html("Contact Number must be 10 digit.");
		return false;
	}
	return true;
}

  

function isMotherEmailValid(email) {
	var reg = /(^([A-Za-z0-9_\-\.\])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$)/;     

	if (email == '') {
		$('.motheremail').html("Mother Email is required information.");
		return false;
	}
	if (reg.test(email) == false) {
		$('.motheremail').html("Enter valid email-id.");
		return false;
	}
	return true;
}
 
 function isMotherMobileValid(value) {
		if (value == '') {
			$('.mothercontactno').html("Contact Number Required.");
			return false;
		}

		if (isNaN(value)) {
			$('.mothercontactno').html("Enter valid Contact Number.");
			return false;
		}

		var reg = /(^(\+)?\d{10,10}$)/;
		if (reg.test(value) == false) {
			$('.mothercontactno').html("Contact Number must be 10 digit.");
			return false;
		}

		var size = value.length;

		if (size > 10) {
			$('.mothercontactno').html("Contact Number must be 10 digit.");
			return false;
		}
		return true;
	}
 
 
 function isMotherNameValid(value) {fathername
		var size = value.length; 
		if (value == '') {
			$('.mothername-error').html("Name is required information.");
			return false;
		}
		if (size < 3 && size < 101) {
			$('.mothername-error').html("Name must be between 3 to 100 char.");
			return false;
		}
		return true;

	}
 
 function isfathernameNameValid(value) {
		var size = value.length; 
		if (value == '') {
			$('.fathername-error').html("Name is required information.");
			return false;
		}
		if (size < 3 && size < 101) {
			$('.fathername-error').html("Name must be between 3 to 100 char.");
			return false;
		}
		return true;

	}
 
 function fnchangeRequiredField(classId)
	{
		
					if(classId==1 || classId==2)  
					{
						$('.showparentstar').show();
						$('.showstudentstar').hide();
						$('.showfatherstar').show();
						
					}
					else if(classId==3 || classId==4)
					{
					
						$('.showparentstar').hide();
						$('.showstudentstar').show();
						$('.showfatherstar').hide();
						
					}
			
		else
			{
			$('.showparentstar').hide();
			$('.showstudentstar').hide();
			$('.showfatherstar').show();
			}
	}
		
	


//vyankatesh End add validation 

</script>