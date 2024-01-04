<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<link href="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" type="text/css"/> 
<script src="${pageContext.request.contextPath}/thirdparty/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.multiselect.js"></script>
<style>
.ms-options-wrap,
.ms-options-wrap * {
    box-sizing: border-box;
}

.ms-options-wrap > button:focus,
.ms-options-wrap > button {
    position: relative;
    width: 100%;
    text-align: left;
    border: 1px solid #e5e5e5;
    background-color: #fff;
    padding: 6px 12px 6px 12px;
    margin-top: 1px;
    font-size: 14px;
    color: #333333;
    outline: none;
    white-space: nowrap;
}

.ms-options-wrap > button:after {
    content: ' ';
    height: 0;
    position: absolute;
    top: 50%;
    right: 5px;
    width: 0;
    border: 5px solid rgba(0, 0, 0, 0);
    border-top-color: #333;
    margin-top: -3px;
}

.ms-options-wrap > .ms-options {
    position: absolute;
    left: 0;
    width: 100%;
    margin-top: 1px;
    margin-bottom: 20px;
    background: white;
    z-index: 2000;
    border: 1px solid #aaa;
}

.ms-options-wrap > .ms-options > .ms-search input {
    width: 100%;
    padding: 4px 5px;
    border: none;
    border-bottom: 1px groove;
    outline: none;
}

.ms-options-wrap > .ms-options .ms-selectall {
    display: inline-block;
    font-size: 14px;
    text-transform: lowercase;
    text-decoration: none;
}
.ms-options-wrap > .ms-options .ms-selectall:hover {
    text-decoration: underline;
}

.ms-options-wrap > .ms-options > .ms-selectall.global {
    margin: 4px 5px;
}

.ms-options-wrap > .ms-options > ul > li.optgroup {
    padding: 3px;
}
.ms-options-wrap > .ms-options > ul > li.optgroup + li.optgroup {
    border-top: 1px solid #aaa;
}

.ms-options-wrap > .ms-options > ul > li.optgroup .label {
    display: block;
    padding: 5px 0 0 0;
    font-weight: bold;
}

.ms-options-wrap > .ms-options > ul label {
    position: relative;
    display: inline-block;
    width: 100%;
    padding: 5px 4px;
    margin: 1px 0;
}

.ms-options-wrap > .ms-options > ul li.selected label,
.ms-options-wrap > .ms-options > ul label:hover {
    background-color: #efefef;
}

.ms-options-wrap > .ms-options > ul input[type="checkbox"] {
    margin-right: 5px;
    position: absolute;
    left: 0px;
    top: 7px;
}

</style>

<div class="edupath-padding-summary">
<div class="rows">

	<div class="col-md-12">
	
	<!-- BEGIN PORTLET-->
	
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa   fa-edit"></i> <s:text name="com.edupath.facilitator.details.portlet.header.label"/>
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
				<s:form cssClass="form-horizontal" name="facilitatorDetailsSubmitForm" id="facilitatorDetailsSubmitForm" method="post">
						
						<s:hidden id="id" name="id"></s:hidden>
						<s:hidden id="userId" name="userId"></s:hidden>
						<div class="form-body">
						
							<s:if test="hasActionErrors()">		
								<div class="alert alert-danger ">
									<button class="close" data-dismiss="alert"></button>
									<s:actionerror/>
								</div>
							</s:if>
							
							<div class="form-group">
								<label class="control-label col-md-3">
									<s:text name="com.edupath.facilitator.details.name"></s:text> <span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-sm" 	maxlength="50"  name="name" id="name" value="%{facilitatorDetailsDTO.name}" readonly="%{readOnly}"/>
									<span class="error-block"> </span>
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.email"></s:text><span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-sm" maxlength="100" name="emailId" id="emailId"  value="%{facilitatorDetailsDTO.emailId}" />
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.altemail"></s:text>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-sm" maxlength="100" name="altEmailId" id="altEmailId"  value="%{facilitatorDetailsDTO.altEmailId}" />
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								<s:text name="com.edupath.facilitator.details.phonenumber"></s:text><span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-medium" maxlength="15" name="phoneNumber" id="phoneNumber"   value="%{facilitatorDetailsDTO.phoneNumber}"/>
									<span class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								<s:text name="com.edupath.facilitator.details.altphonenumber"></s:text>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-medium" maxlength="15" name="altPhoneNumber" id="altPhoneNumber"   value="%{facilitatorDetailsDTO.altPhoneNumber}"/>
									<span class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.street"></s:text>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-sm" maxlength="100" name="streetAddr" id="streetAddr"  value="%{facilitatorDetailsDTO.streetAddr}" />
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.area"></s:text>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-sm" maxlength="100" name="areaAddr" id="areaAddr"  value="%{facilitatorDetailsDTO.areaAddr}" />
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.city"></s:text><span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-3">
									<s:select cssClass="able-group-action-input form-control input-medium" name="cityId"  id="cityId" list="cityMap" value='%{facilitatorDetailsDTO.cityId}'   />
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="On location"></s:text><span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-3">
									<s:select cssClass="able-group-action-input form-control input-medium" name="faceToFaceCityId" multiple="true" id="faceToFaceCityId" list="cityMap" value='%{facilitatorDetailsDTO.faceToFaceCityId}'/>
									<span	class="error-block"></span>
								</div>
								
								<label class="control-label col-md-3">
								  <s:text name="On Call(Skype/Teliphone)"></s:text><span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-3">
									<s:select cssClass="able-group-action-input form-control input-medium"  name="onCallCityId" multiple="true"  id="onCallCityId" list="cityMap" value='%{facilitatorDetailsDTO.onCallCityId}' />
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.screen.dob"></s:text>
								</label>
								<div class="col-md-2">
									<s:textfield cssClass="form-control input-medium" maxlength="100" name="dobStr" id="dobStr"  value="%{facilitatorDetailsDTO.dobStr}" readonly="true"/>
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.pannumber"></s:text>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-sm" maxlength="50" name="panNumber" id="panNumber"  value="%{facilitatorDetailsDTO.panNumber}" />
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.qualification"></s:text><span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-2">
									<s:select cssClass="able-group-action-input form-control input-medium" name="highestQualificationId"  id="highestQualificationId" list="highestQualificationMap" value='%{facilitatorDetailsDTO.highestQualificationId}'/>
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.screen.reviewer"></s:text>
								</label>
								<div class="col-md-1" style="padding-top: 8px;">
									<c:choose>
										<c:when test="${readOnly == 'readonly'}">
											<s:checkbox type="checkbox"  value="false" name="isReviewer" id="isReviewer" onclick="return false"/>
										</c:when>
										<c:otherwise>
											<s:checkbox type="checkbox"  value="false" name="isReviewer" id="isReviewer" />
										</c:otherwise>
									</c:choose>
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.active"></s:text>
								</label>
								<div class="col-md-1" style="padding-top: 8px;">
									<c:choose>
										<c:when test="${readOnly == 'readonly'}">
											<s:checkbox type="checkbox"  value="true" name="isActive" id="isActive" onclick="return false"/>
										</c:when>
										<c:otherwise>
											<s:checkbox type="checkbox"  value="true" name="isActive" id="isActive" />
										</c:otherwise>
									</c:choose>
									
									<span	class="error-block"></span>
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.type"></s:text>
								</label>
								<div class="col-md-3" style="padding-top: 8px;">
									<c:choose>
										<c:when test="${readOnly == 'readonly'}">
											<s:radio name="type" id="type" list="typeList" listKey="key" listValue="value" onclick="return false" cssClass="radio-list"/>
										</c:when>
										<c:otherwise>
											<s:radio name="type" id="type" list="typeList" listKey="key" listValue="value" cssClass="radio-list"/>
										</c:otherwise>
									</c:choose>								
									<span class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.languages"></s:text>
								</label>
								<div class="col-md-1" style="width: auto; padding-top: 8px;">
									<c:choose>
										<c:when test="${readOnly == 'readonly'}">
											<s:checkboxlist theme="simple" id="additionalLanguages" list="languageList" name="additionalLanguages" value="" onclick="return false"/>
										</c:when>
										<c:otherwise>
											<s:checkboxlist theme="simple" id="additionalLanguages" list="languageList" name="additionalLanguages" value="" />
										</c:otherwise>
									</c:choose>
									
									<span	class="error-block"></span>
								</div>
							</div>
							
							
							<div class="form-actions" id="form-actions">
								<div class="row">
									<div class="col-md-offset-2 col-md-4">
										<button type="submit" class="btn blue">
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



$(document).ready(function(){
	   var val = '${readOnly}';
			if(val=="readonly"){
				$("#name").prop("readonly", true);
				$("#emailId").prop("readonly", true);
				$("#altEmailId").prop("readonly", true);
				$("#phoneNumber").prop("readonly", true);
				$("#altPhoneNumber").prop("readonly", true);
				$("#streetAddr").prop("readonly", true);
				$("#areaAddr").prop("readonly", true);
				$("#panNumber").prop("readonly", true);
				$("#cityId").prop("disabled", true);
				$("#highestQualificationId").prop("disabled", true);
				$("#form-actions").hide();
			}
	       
			isActive="${facilitatorDetailsDTO.isActive}";
			isReviewer="${facilitatorDetailsDTO.isReviewer}";
			type="${facilitatorDetailsDTO.type}";
			//alert(isActive);
			//tonteliphoId="${facilitatorDetailsDTO.faceToFaceCityId}";
			//
			additionLanguages="${facilitatorDetailsDTO.additionalLanguages}";
			//alert(additionLanguages);
			var lang = additionLanguages.split(",");
			$("[name='additionalLanguages']").each(function(index){
				chckbox = $(this);
				for (var i = 0; i < lang.length; i++) {
				    if(lang[i].trim().toUpperCase() == $(chckbox).val().trim().toUpperCase())
					{
				    	$(chckbox).prop("checked",true);
						 return;
					}	
				}
			});
			
			if(isActive == "false"){
				$("#isActive").prop("checked",false);
			}
			if (isReviewer=="true") {
				$("#isReviewer").prop("checked",true);
			}
			if (type=="P") {
				$("#typeP").prop("checked",true);
			}
			else{
				$("#typeF").prop("checked",true);
			}
			
			
			//$('#cityId').multiselect({
			//    columns: 1,
			//    placeholder: 'Select Languages',
			 //   search: true,
			 //   selectAll: true
			//});
			$('#onCallCityId').multiselect({
			    columns: 1,
			    placeholder: 'Select City',
			    search: true,
			    selectAll: true
			});
			$('#faceToFaceCityId').multiselect({
			    columns: 1,
			    placeholder: 'Select City',
			    search: true,
			    selectAll: true
			});
			
			//$("#onCallCityId").multiselect().multiselectfilter();
			//$("#faceToFaceCityId").multiselect().multiselectfilter();
		});


  var form = document.facilitatorDetailsSubmitForm;
  function fnAddFacilitatorDetails()
  {
	   id="${facilitatorDetailsDTO.id}";
	   userId = "${facilitatorDetailsDTO.userId}";
		$("#id").val(id);
		$("#userId").val(userId);
		
		var urlVal;
		if(id == "0"){
			urlVal= "${pageContext.request.contextPath}/myapp/insertManageFacilitatorAction";
		}
		else{
			urlVal="${pageContext.request.contextPath}/myapp/updateManageFacilitatorAction";
		} 
		form.action = urlVal;
		form.submit(); 
  }
  
  function goBack(){
		path = '${pageContext.request.contextPath}/myapp/ManageFacilitatorAction';
		form.action = path;
		form.submit();
  }
  
  function fnClear(){

	    $("#name").val("");
		$("#emailId").val("");
		$("#altEmailId").val("");
		$("#phoneNumber").val("");
		$("#altPhoneNumber").val("");
		$("#streetAddr").val("");
		$("#areaAddr").val("");
		$("#dobStr").val("");
		$("#panNumber").val("");
		$("#cityId").val("1");
		$("#highestQualificationId").val("1");
		$("#isActive").prop("checked", true);
		$("#isReviewer").prop("checked", false);
		$("#typeF").prop("checked", true);
		$("#additionalLanguages-1").prop("checked",false);
		$("#additionalLanguages-2").prop("checked",false);
		$("#additionalLanguages-3").prop("checked",false);
		$("#additionalLanguages-4").prop("checked",false);
  }
  
  $(document).ready(function () {
	  if ('${readOnly}' != 'readonly') {
		  $("#dobStr").datepicker({
		 		dateFormat:"dd-M-yy",
		 		 endDate:'-1d',
	 		 });
	}
	 	
	  
	  	var emailValue = $('#emailId').val();
		var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		jQuery.validator.addMethod("emailValid", function(emailValue, element){
		    if (filter.test(emailValue)) {
		        return true;  // FAIL validation when REGEX matches
		    } else {
		        return false;   // PASS validation otherwise
		    };
		}, "Please enter a valid email.");
		
		var altEmailValue = $('#altEmailId').val();
		jQuery.validator.addMethod("altEmailValid", function(altEmailValue, element){
		    if(altEmailValue == ""){
		    	return true;
		    }
			if (filter.test(altEmailValue)) {
		        return true;  // FAIL validation when REGEX matches
		    } else {
		        return false;   // PASS validation otherwise
		    };
		}, "Please enter a valid email.");
	  
		requiredLabel = '<s:text name="com.edupath.common.information.required"/>';
	    $('#facilitatorDetailsSubmitForm').validate({
	    	
	    	errorElement: 'span', 
	        errorClass: 'error-block',
	        rules: {
	        	name:{
	        		required: true,
	        	},
	        	emailId: {
	                required: true,
	                emailValid: true
	            },
	            altEmailId: {
	                altEmailValid: true
	            },
	            phoneNumber: {
	                required: true,
	                maxlength: 15
	            },
	           
	        },
	       
	        invalidHandler: function (event, validator) { //display error alert on form submit   

	        },
	        highlight: function (element) { // hightlight error inputs
	            $(element).closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group
	        },
	        success: function (label,element) {
	        	$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
	            label.remove();
	        },
	        submitHandler: function (form) {
	        	fnAddFacilitatorDetails();// for demo
	            // for demo
	        }
	    });
	});
</script>
