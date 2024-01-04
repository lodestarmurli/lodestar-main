<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Modal -->
<div class="modal fade" role="dialog" id="colShortListModal">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content  colShortListModalheight">	<!--27/03/18 removed style="width: 555px !important;" -->
	       	<div class="modal-header">
	         		<button type="button" class="close" data-dismiss="modal">&times;</button>
	         		<h4 class="modal-title">College <s:text name="com.edupath.common.shortlist.button"/></h4>
	       	</div>
	       	<div class="modal-body tutorial-center-model-body">
	        	<div id="seleCountMsgText" class="tut-model-sel-msg-txt">
		       		You have selected <b></b> Colleges
		       	</div>	
		       	<div id="messageTxt" class="tut-model-sel-msg-txt"></div>
		       	<div id="selCollegeNames"></div>
       		</div>
       		<div class="modal-footer tutorial-filters-footer">
         		<button class="btn btn-sm cancel-btn college-filter-btn" data-dismiss="modal">
					<s:text name="com.edupath.common.cancel"/>
				</button>
				<button class="btn btn-sm apply-btn college-filter-btn" style="margin-left: 12px;" onclick="fnSubmitCollegeShortList()">
					<s:text name="com.edupath.common.confirm"/>
				</button>
       		</div>
	     </div>
	</div>
</div>

<div class="modal fade" role="dialog" id="errorMsgModal">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content">	<!--27/03/18 removed style="width: 555px !important;" -->
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">Error Message</h4>
	       	</div>
	       	<div class="modal-body">
	        	<div id="errorModalMsg"></div>
       		</div>
       		<div class="modal-footer">
         		<button class="btn btn-sm apply-btn college-filter-btn"  onclick="fnCloseErrorPopUp()">
					<s:text name="com.edupath.questionnarie.model.btn.label"/>
				</button>
       		</div>
	     </div>
	</div>
</div>

<div class="modal fade" id="college_msg_timer" style="width: 400px; position: fixed; left: auto; top: -120px; right: 10px; display: none;" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">	<!--27/03/18 removed style="width: 555px !important;" -->
        <div class="modal-body" style="text-align: center; font-size: 11pt;">
		 	<label class="label label-success" id="college_msg_label"></label>
        </div>
      </div>
    </div>
</div>

<div id="collegeFilterLoading" class="display-hide cust-loader">
	<div class="app-spinner">
<!-- 		<i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i> -->
		<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
	</div>
</div>

<s:form name="CollegeCompareNShortList" id="CollegeCompareNShortList" action="CollegeCompareNShortList" method="POST">
	<s:hidden name="parentSelectedSidebarMenuId"/>
	<s:hidden name="childSelectedSidebarMenuId"/>
	<s:hidden name="collegeIdsStr" id="collegeIdsStr"/>
	<s:hidden name="isCompareAction" id="isCompareAction"/>
</s:form>

<script type="text/javascript">

 function shortListColleges()
 { 		
	var collegeCounter = 0;
	var collegeId = 0;
	$("#selCollegeNames").html("");
	$(".modal-footer.tutorial-filters-footer>.btn.btn-sm.apply-btn.college-filter-btn").prop("disabled", false);
	$.each($('.college-check.styled'), function(){
		if($(this).is(":checked"))
		{
			collegeId = $(this).val();
			collegeCounter++;
			var collegeName = $(this).data("collegeName");
			var collegeNameHtml = '';
			collegeNameHtml += "<div class='tut-model-main-deta-div' id='collegeShortListId_"+ collegeId +"'>";
			collegeNameHtml += "<div class='finalize-deta-div'>" + collegeName + "</div>";	
			collegeNameHtml += "<div class='finalize-deta-close-div' onclick='fnRemoveCollegeShortList(" + collegeId + ", this)'><i class='glyphicon glyphicon-remove'></i></div>";
			collegeNameHtml += "</div>";

			$("#selCollegeNames").append(collegeNameHtml);
		}
	});
	
	var message = "Do you want to add these items to the cart?";
	$('#seleCountMsgText').find('b').html(collegeCounter);
	$('#messageTxt').html(message);
	if (collegeCounter == 0) 
	{
		$(".modal-footer.tutorial-filters-footer>.btn.btn-sm.apply-btn.college-filter-btn").prop("disabled", true);
		$('#messageTxt').html("Please select some colleges to shortlist");
	}
	else
	{
		$(".modal-footer.tutorial-filters-footer>.btn.btn-sm.apply-btn.college-filter-btn").prop("disabled", false);
	}
	$('#colShortListModal').modal("show");
 }
 
 function fnRemoveCollegeShortList(collegeId, thisObj)
 {
	 $(thisObj).parent().remove();
	 $("#collegeId_" + collegeId).click();
	 if($('.tut-model-main-deta-div').size() == 0)
	 {
		 $(".modal-footer.tutorial-filters-footer>.btn.btn-sm.apply-btn.college-filter-btn").prop("disabled", true);
	 }	 
 }
 
 function fnGetSelectedColleges()
 {
	 var collegeIds = null;
	 $.each($('.college-check.styled'), function(){
		if($(this).is(":checked"))
		{
			var collegeId = $(this).val();
			if(collegeIds == null)
			{
				collegeIds = collegeId;
			}
			else
			{
				collegeIds += "," + collegeId;
			}
		}
	});
	 return collegeIds; 
 }
 
 function fnSubmitCollegeShortList()
 {
	 var dataJSON = {"collegeIdsStr" : fnGetSelectedColleges()};
	 $.ajax({
		 url : "${pageContext.request.contextPath}/myapp/doShortListCollegeCompareNShortList",
		 type : "POST",
		 cache : false,
		 data : dataJSON,
		 dataType : "JSON",
		 success : function (resp){
			 if(resp != null && resp)
			 {
				 if(resp.STATUS == 'SUCCESS')
				 {
					 $("#colShortListModal").modal("hide");
					 $('#college_msg_label').text(resp.MESSAGE);
					 $('#college_msg_timer').modal('show');
					 setTimeout(function(){
						  $("#college_msg_timer").modal("hide");
					  }, 2000);	
				 }
				 else
				 {
					 $("#colShortListModal").modal("hide");
					 $('#errorModalMsg').text(resp.MESSAGE);
					 $('#errorMsgModal').modal("show");
				 }
				 
			 }
		 },
		 error : function(arg0, arg1, arg2){
			 alert(arg1);
		 }
	 });
 }
 
 function fnCloseErrorPopUp()
 { 
	 $('#errorMsgModal').modal('hide');
 }
</script>