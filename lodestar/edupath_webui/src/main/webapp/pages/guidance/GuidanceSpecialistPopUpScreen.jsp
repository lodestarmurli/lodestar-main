<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

 <div class="modal fade" id="GuidanceId" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content" style="height: 235px !important;">		<!-- width: 500px !important; 28/03/18  02/04/18 -->	
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h5 class="modal-title" style="color: #63A3D1"><s:text name="com.edupath.contect.guidance.label"></s:text></h5>
        </div>
        <div class="modal-body" style="border-top:2px solid #1E5177; padding-left: 25px;">
          <div class="rows">
          	<div class="col-md-4">
          		<span class="guidance_spn_txt"><s:text name="com.edupath.facilitator.details.name"></s:text></span>
          	</div>
          	<div class="col-md-8">
          		<label class="guidance_lbl_txt">&nbsp;${utils:replaceXMLEntities(guidanceDTO.name)}</label>
          	</div>
		  </div>
		  <div class="rows">
          	<div class="col-md-4">
          		<span class="guidance_spn_txt"><s:text name="com.edupath.facilitator.details.email"></s:text></span>
          	</div>
          	<div class="col-md-8">
          		<label class="guidance_lbl_txt">&nbsp;${utils:replaceXMLEntities(guidanceDTO.emailId)}</label>
          	</div>
		  </div>
		  <div class="rows">
          	<div class="col-md-4">
          		<span class="guidance_spn_txt"><s:text name="com.edupath.facilitator.details.phonenumber"></s:text></span>
          	</div>
          	<div class="col-md-8">
          		 <label class="guidance_lbl_txt">&nbsp;${utils:replaceXMLEntities(guidanceDTO.phoneNumber)}</label>
          	</div>
		  </div>
        </div>
      </div>
    </div>
  </div>
  <script>
  function fnGuidance(action)
  {
  	form = document.MenuItemForm;
  	form.action = "${pageContext.request.contextPath}/myapp/" + action;
  	var options = { 
  	        beforeSubmit:  showGuidanceRequest,
  	        success:       showGuidanceResponse
  	    }; 
  	$("#MenuItemForm").ajaxSubmit(options);
  }
  function showGuidanceRequest(formData, jqForm, options){
  } 

  function showGuidanceResponse(responseText, statusText, xhr, $form)
  {
  	 $('#showGuidanceId').html(responseText);
  	 $('#GuidanceId').modal();
  }
  
  </script>