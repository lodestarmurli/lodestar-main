<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<link href="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" type="text/css"/> 
<script src="${pageContext.request.contextPath}/thirdparty/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/styles/BulkUploadStyles.css" rel="stylesheet" type="text/css"/> 

<div class="rows">

	<div class="col-md-12" style="margin-left:6px;">  <!-- added 31/03/18  "margin-left:6px; -->
	
	<!-- BEGIN PORTLET-->
	
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa   fa-edit"></i> <s:text name="com.edupath.school.details.portlet.header.label"/>
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
				<s:form cssClass="form-horizontal" name="schoolDetailsSubmitForm" id="schoolDetailsSubmitForm" method="post" enctype="multipart/form-data">
						<s:hidden name="code" value="1"></s:hidden>
						<s:hidden id="id" name="id"></s:hidden>
						<div class="form-body">
						${actionerror}
							<s:if test="hasActionErrors()">		
								<div class="alert alert-danger ">
									<button class="close" data-dismiss="alert"></button>
									<s:actionerror/>
								</div>
							</s:if>
							
							<div class="form-group">
								<label class="control-label col-md-3">
								  <s:text name="com.edupath.facilitator.details.city"></s:text><span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-3">
									<s:select cssClass="able-group-action-input form-control input-medium" name="cityId"  id="cityId" list="cityMap" value='%{schoolDTO.cityId}'   />
									<span	class="error-block"></span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">
									<s:text name="com.edupath.school.details.name"></s:text> <span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-sm" 	maxlength="50"  name="name" id="name" value="%{schoolDTO.name}"/>
									<span class="error-block"> </span>
								</div>
							</div>
							<!-- start bharath on 16-05-2019 -->
							<div class="form-group">
								<label class="control-label col-md-3">
									<s:text name="com.edupath.school.upload.logo"></s:text> <span class="required image-required" aria-required="true">*</span><br><br style=" line-height: 2px;">
									<span style="font-size: smaller;">(Min-50*50. Max-300*70)</span>
								</label> 
					
								<div class="col-md-8">
									<s:file name="image" id="imageFilePath" cssClass="file" label="image" size="40" cssStyle="width:82%" readonly="true" value="%{schoolDTO.logopath}"/>&nbsp;  
									<span style="color:red" class="image-error-block"> </span>	
									
										<img id="dispimageFilePath" alt="<s:text name="%{schoolDTO.logopath}"></s:text>" class="img-responsive" src="${pageContext.request.contextPath}/images/school_logo/<s:text name="%{schoolDTO.logopath}"></s:text>">
								
								</div>
							</div>
							
							<div class="form-group">
							  <label class="control-label col-md-3">
							  <s:text name="com.edupath.school.details.displaylogo"></s:text> <span class="required" aria-required="true"> * </span>
							</label> 
							<div class="col-md-4">
								<s:radio cssClass="radio-list" name="displaylogo" id="displaylogo" 
									list="#{1:' Yes',0:' No'}" value= "%{schoolDTO.displaylogo}" />
							<span style="color:red" class="displaylogo-error-block"> </span>
							</div>
						</div>
						<!-- end bharath on 16-05-2019 -->
							
							<div class="form-actions" id="form-actions">
								<div class="row">
									<div class="col-md-offset-3 col-md-9">
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
<script type="text/javascript">

  var form = document.schoolDetailsSubmitForm;

  $(document).ready(function () {
	  
		requiredLabel = '<s:text name="com.edupath.common.information.required"/>';
	    $('#schoolDetailsSubmitForm').validate({
	    	errorElement: 'span', 
	        errorClass: 'error-block',
	        rules: {
	        	name:{
	        		required: true,
	        	}
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
	        	fnAddSchoolDetails();
	        }
	    }); 
	    
	  //start bharath on 16-05-2019	    
	    $('input[name=displaylogo]').change(function(e) {
			  if(this.value == '1')
				{
				  $('.image-required').show();
				}else
					{
					$('.image-required').hide();
					}
		  });
	    
  });
  

  var _URL = window.URL || window.webkitURL;

  $("#imageFilePath").change(function(e) {
      var file, img;


      if ((file = this.files[0])) {
          img = new Image();
       
		  if(file.type == "image/png" || file.type == "image/jpeg" || file.type == "PNG" || file.type == "JPEG")
			  {
			 	
			  }else{
				  alert('File format invalid. format allowed are .png .jpg .jpeg');
			 	$("#imageFilePath").val("");
			 	return false;
			  }
          img.onload = function() {
        	  if(this.width>300 || this. height>70 || this.width<50 || this.height<50)
        		  {
              		alert("Image dimension not valid \nCurrent image size "+this.width + " x " + this.height);
              		$("#imageFilePath").val("");
              		return false;
        		  }
        	  
          };
          img.onerror = function() {
              alert( "File format invalid. format allowed are .png .jpg .jpeg' ");
              $("#imageFilePath").val("");
          };
          img.src = _URL.createObjectURL(file);
          

      }

  });
 
  
 	var radios = this.form.elements['displaylogo'];
	  for (var i=0, len=radios.length; i<len; i++) 
	  {
		  var r = radios[i];
		  	  if ( r.value == '0' && r.checked == true ) 
		  	  {
		  		  $('.image-required').hide();
			  }
  	  }

	 
	  //end bharath on 16-05-2019
  
  function fnAddSchoolDetails()
  {
	  //start bharath on 16-05-2019
	 	 var displaylogo = $('input:radio[name=displaylogo]:checked').val();
	   
	  	if (null == displaylogo || displaylogo == '' || displaylogo == undefined) {
			$('.displaylogo-error-block').html("required");
			return false;
		}
	  	var image = $("#imageFilePath").val();
	  	var dispimageFilePath = "${schoolDTO.logopath}";
		var imageFileName =  image.substring(image.lastIndexOf("\\") + 1 ,image.length);
	  	if(displaylogo == '1' && (image == null || image == '' || image == undefined ) && ( dispimageFilePath == null  || dispimageFilePath == "") )
	  	{
	  		$('.image-error-block').html(alert("Please upload the Logo"));
	  		return false;
	  	}
	  	
	    id="${schoolDTO.id}";
		$("#id").val(id);
		//end bharath on 16-05-2019
		
	
		var urlVal;
		if(id == "0"){
			urlVal= "${pageContext.request.contextPath}/myapp/insertManageSchoolSubmitAction";
		}
		else{
			urlVal="${pageContext.request.contextPath}/myapp/updateManageSchoolSubmitAction";
		} 
		form.action = urlVal;
		form.submit(); 
  }
  
  function goBack()
  {
		path = '${pageContext.request.contextPath}/myapp/ManageSchoolSummaryAction';
		form.action = path;
		form.submit();
  }
  
  function fnClear()
  {
	  $("#name").val("");
	  $("#imageFilePath").val("");
	  //start bharath on 16-05-2019
	  $('.image-required').hide();
	  var radios = this.form.elements['displaylogo'];
	  for (var i=0, len=radios.length; i<len; i++) 
	  {
		  var r = radios[i];
		  if ( r.value == '0' ) 
		  {
				r.checked = true;
		   }else
			   {
			   r.checked = false;
			   }
		  
	  }
	//end bharath on 16-05-2019
  }
  
</script>
