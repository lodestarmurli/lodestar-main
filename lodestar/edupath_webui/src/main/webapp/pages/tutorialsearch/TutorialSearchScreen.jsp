<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib prefix="c" uri="c.tld"%>

<s:set var="filterErrorMsg">Please select an exam</s:set>
<link href="${pageContext.request.contextPath}/thirdparty/select2_new/dist/css/select2.min.css" rel="stylesheet"  type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/select2_new/dist/js/select2.min.js"></script>
<div>
	<div class="row filter-margin-top">
		<div class="col-md-1 common-filter-header" onclick="fnShowFilter()">
			<span><s:text name="com.edupath.common.filter"></s:text></span> 
			<i class="fa fa-filter fa-lg common-filter-icon"></i>
		</div>
		<div class="col-md-11 college-search-header">
			<s:text name="com.edupath.tutorialfilter.name"></s:text>
		</div>
	</div>
	<div id="filterDiv" class="row tut-filter-div">
		<div class="col-md-2 tut-exam-div">
			<div class="tut-exam-name-div">
				<s:text name="com.edupath.tutorialfilter.exams"></s:text>
			</div>
			<c:forEach items="${examList}" var="examDetails">
				<div class="radio radio-success tut-exam-chk-div" id="cust_radio">
		            <input type="radio" id="radio_${examDetails.id}" value="${examDetails.id}" name="examId">
		            <label for="radio_${examDetails.id}" class="cust-chk-txt-label">${examDetails.examName}</label>
				</div>
			</c:forEach>
		</div>
		<div class="col-md-8 tut-location-div">
			<div class="tut-loc-name-div">
				<span><s:text name="com.edupath.tutorialfilter.locations"></s:text></span>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="tut-loc-com-div">
						<span><s:text name="com.edupath.tutorialfilter.city"></s:text></span>
					</div>
					<div>
						<s:select list="cityList" cssClass="select2me" listKey="id" listValue="name"  name="cityId" id="cityId" cssStyle="width:180px;" onchange="fnCityChange(this.value)"  disabled="true"></s:select>
					</div>
				</div>
				<div class="col-md-2">
					<div class="tut-loc-com-div">
						<span><s:text name="com.edupath.tutorialfilter.locality"></s:text></span>
			 		</div>
			 		<div>
				 		<span>
					 		<select id="tutLocalityListSelect" style="width:180px;">
					 		  <option value="select">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
					 		</select>
				 		</span>
			 		</div>
				</div>
			</div>
		</div>
		
		<div class="tutorial-filters-header">
			<div class="reset-btn-div">
				<button id="reset_btn" class="btn btn-sm reset-btn college-filter-btn" onclick="fnResetAll()">
					<s:text name="com.edupath.common.reset"/>
				</button>		
			</div>
			<div class="tutorial-filters-btn-value">
				<button id="cancel_btn" class="btn btn-sm cancel-btn college-filter-btn" onclick="fnCancel()">
					<s:text name="com.edupath.common.cancel"/>
				</button>
			</div>
			<div class="tutorial-filters-btn-value ">
				<button id="apply_btn" class="btn btn-sm apply-btn college-filter-btn" onclick="fnGetTutorialsDetails(0)">
					<s:text name="com.edupath.common.apply"/>
				</button>
			</div>
		</div>
	</div>
	

	<div id="tutorialFilterContentDiv" class="college-details-div">
		<div class="tut-total-records-div" id="filterMessage" style="display: none">
		</div>
	</div>

	<div id="tutorialCentersDiv" class="college-details-div">
	
	</div>
	
	<div class="modal fade" role="dialog" id="messageDIVId">
		<div class="modal-dialog">
		    <!-- Modal content-->
		   	<div class="modal-content" style="width: 555px !important;">
		       	<div class="modal-header">
		         		<button type="button" class="close" data-dismiss="modal">&times;</button>
		         		<h4 class="modal-title">Tutorial Centers</h4>
		       	</div>
		       	<div class="modal-body tutorial-center-model-body">
		        	<div id="seleMsgText" class="tut-model-sel-msg-txt">
			        	You have selected <b></b> Tutorial Centers
			        </div>
		            <div id="messageTxt" class="tut-model-sel-msg-txt"></div>
		            <div id="selTutNameId"></div>
	       		</div>
	       		<div class="modal-footer tutorial-filters-footer">
	         		<button class="btn btn-sm cancel-btn college-filter-btn" data-dismiss="modal">
						<s:text name="com.edupath.common.cancel"/>
					</button>
					<button class="btn btn-sm apply-btn college-filter-btn" id="tutModelConfirmBtnId" style="margin-left: 12px;" onclick="fnShortListTutorialsCenters()">
						<s:text name="com.edupath.common.confirm"/>
					</button>
	       		</div>
		     </div>
		</div>
	</div>

	<div class="modal fade" role="dialog" id="errorMsgDIVId">
		<div class="modal-dialog">
		    <!-- Modal content-->
		   	<div class="modal-content" style="width: 555px !important;">
		       	<div class="modal-header">
		         	<button type="button" class="close" data-dismiss="modal">&times;</button>
		         	<h4 class="modal-title">Error Message</h4>
		       	</div>
		       	<div class="modal-body">
		        	<div id="errorPopId"></div>
	       		</div>
	       		<div class="modal-footer tutorial-filters-footer">
	         		<button class="btn btn-sm apply-btn college-filter-btn"  onclick="fnCloseErrorPopUp()">
						<s:text name="com.edupath.questionnarie.model.btn.label"/>
					</button>
	       		</div>
		     </div>
		</div>
	</div>
	
	<div class="modal fade" id="tutorial_msg_timer" style="width: 400px; position: fixed; left: auto; top: -120px; right: 10px; display: none;" aria-hidden="true">
	    <div class="modal-dialog">
	      <div class="modal-content" style="width: 300px !important;">
	        <div class="modal-body" style="text-align: center; font-size: 11pt;">
			 	<label class="label label-success" id="tutorial_msg_label"></label>
	        </div>
	      </div>
	    </div>
	</div>
	
	<div class="nomore-records-center" style="display: none">
		<div>
			<s:text name='com.edupath.common.nomorerecords.text'/>
		</div>
	</div>
	
	<div id="tutorialFilterLoading" class="display-hide cust-loader">
		<div class="app-spinner">
	<!-- 		<i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i> -->
			<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
		</div>
	</div>
	
	<%-- <center id="nomore-records-center"><div class='noRecords' id='nomore-records-center' style="display: none"><s:text name='com.edupath.common.nomorerecords.text'/></div></center> --%>
</div>
<s:form name="tutorialDetailForm" id="tutorialDetailForm" method="POST" action="">
	<s:hidden name="loadFurther" id="loadFurther"/>
	<s:hidden name="studentId" id="studentId"></s:hidden>
	<s:hidden name="pageNumber" id="pageNumber"></s:hidden>
	<s:hidden name="pageSizeCount" id="pageSizeCount"></s:hidden>
	<s:hidden name="totalCount" id="totalCount"></s:hidden>
	<s:hidden name="parentSelectedSidebarMenuId"/>
	<s:hidden name="childSelectedSidebarMenuId"/>
	<s:hidden name="isBackAction" value="true"/>
</s:form>
<script type="text/javascript">
	var tutorialJson = {};
	var tutorialCityLocalityMap;
	var showNoRecord = true;
	var maxPos = 0;
	var Cid="${cityId}";
	$(document).ready(function(){
		
		
		//alert(Cid);
		//fnResetAll();
		//$('#cityId option:eq('+Cid+')').prop('selected', true);
		fnCityChange(Cid);
		
		
		
		$("#cityId, #tutLocalityListSelect").select2();
		cityLocalJson = JSON.parse('${jsonObject}');
		tutorialCityLocalityMap = cityLocalJson.tutorialCityLocalityMap;
		$("#studentId").val('${studentId}');
		var localStoreName = EdupathLocalStore.getTutorialName();
		var localStorageData = JSON.parse(EdupathLocalStore.doGet(localStoreName, '${studentId}'));
		var firstEmaiId = 0;
		<c:forEach items="${examList}" var="examDetails">
			if(firstEmaiId == 0)
			{
				firstEmaiId = '${examDetails.id}'
			}
		</c:forEach>
		if(localStorageData == null)
		{
			var city = Cid;
			$("#cityId").val(city).trigger("change");
			$("input[name=examId][value=" + firstEmaiId + "]").prop('checked', true);
			fnCityChange($("#cityId").val());
		}
		else
		{
			var emxamId = localStorageData['examId'];
			var cityId = Cid;//localStorageData['cityId'];
			var locality = localStorageData['locality'];
			if (emxamId != undefined && emxamId != '') 
			{
				$("input[name=examId][value=" + localStorageData['examId'] + "]").prop('checked', true);
			}
			else if(firstEmaiId && firstEmaiId > 0)
			{
				$("input[name=examId][value=" + firstEmaiId + "]").prop('checked', true);
			}
				
			if (cityId != undefined && cityId != '') 
			{
				$("#cityId").val(cityId).trigger("change");
			}
			fnCityChange($("#cityId").val());
			if (locality != undefined && locality != '') 
			{
				$("#tutLocalityListSelect").val(locality).trigger("change");
			}
		}
		fnGetTutorialsDetails(Cid);
		
	   	$("#my_scrolling_pane").scroll(function()
		{
	        var windowpos = $("#my_scrolling_pane").scrollTop();
	        if(windowpos >= maxPos)
   			{
	        	var loader = $("#loadFurther").val();
	        	maxPos = ($('.nomore-records-center').position().top - 600);
	        	if(loader == "true" && showNoRecord)
	        	{
	     		  	    callScroll();
	        	}
	        	else if(loader == 'false')
	    		{
	        		$(".nomore-records-center").show();
	    			showNoRecord = false;
	    		}
   			}
	  	});
	});
	
	function fnShowFilter()
	{
		$("#filterDiv").slideToggle();
		$(".common-filter-header").toggleClass( "bgcolor" );
	}
	
	function fnGetTutorialsDetails(Cid)
	{
		maxPos = 0;
		showNoRecord = true;
		$("#tutorialCentersDiv").hide();
		$("#tutorialFilterContentDiv").show();
		$(".nomore-records-center").hide();
		$(".common-filter-header").toggleClass( "bgcolor" );
		var cityId=$("#cityId").val();
		if(Cid==0)
			{
			 cityId =$("#cityId").val();
			}
		else
			{
			 cityId =Cid;//$("#cityId").val();
			}
		$('#cityId option:eq('+cityId+')').prop('selected', true);
		var examId = $('input[name=examId]:checked').val();
		var studentId = $("#studentId").val();
		var locality = '';
		if($('#tutLocalityListSelect').val() != "select")
		{
			locality = $("#tutLocalityListSelect").val();
		}
		url = '${pageContext.request.contextPath}/myapp/getTutorialByFilterSelectTutorialsAction';
		
		if(examId && examId > 0)
		{
			$.ajax({
				url : url,
				type: 'POST',
				data : ({
					'cityId' : cityId,
					'examId' : examId,
					'studentId' : studentId,
					'locality' : locality,
					'countExist' : false,
				}),
				success : function(resp)
				{
					if(resp != "" && resp != null)
		 			{
						$("#tutorialFilterContentDiv").html('');
		 				$("#filterDiv").slideUp();
		 				$("#tutorialFilterContentDiv").append(resp);
		 			}
				},
				error : function(msg,arg1,arg2)
				{
					alert("page load error");
				}
			});
		}
		else
		{
			$('#filterMessage').show();
			$('#filterMessage').html('${pageScope.filterErrorMsg}');
		}
		
		var localStoreName = EdupathLocalStore.getTutorialName();
		tutorialJson["examId"]=examId;
		tutorialJson["cityId"]=cityId;
		tutorialJson["locality"]=locality;
		EdupathLocalStore.doInsert(localStoreName, studentId, tutorialJson);
	}
	
	function fnCityChange(cityId)
	{
		$('#tutLocalityListSelect').find('option').remove().end().
		append('<option value="select">----Select----</option>').val('select').trigger("change");
		if(tutorialCityLocalityMap!=null)
			{
		$.each(tutorialCityLocalityMap, function(key, value){
			if (key == cityId) 
			{
				$.each(value, function(key, value){
 					$("#tutLocalityListSelect").append("<option value='"+value+"'>"+value+"</option>");
 				}); 
			}
		});
			}
	}
	
	function callScroll()
	{
		url = '${pageContext.request.contextPath}/myapp/getTutorialByFilterSelectTutorialsAction';
		var cityId = $("#cityId").val();
		var examId = $('input[name=examId]:checked').val();
		var studentId = $("#studentId").val();
		var locality = '';
		if($('#tutLocalityListSelect').val() != "select")
		{
			locality = $("#tutLocalityListSelect").val();
		}
		var pageNumber = parseInt($("#pageNumber").val());
		pageNumber = pageNumber + 1;
		$("#pageNumber").val(pageNumber);
		doAjaxAndAppend(url, cityId, examId, studentId, locality, pageNumber);
	}
	
	function doAjaxAndAppend(url, cityId, examId, studentId, locality, pageNumber)
	{
		$("#tutorialFilterLoading").show();
		$.ajax({
			url : url,
			type: 'POST',
			data : ({
				'cityId' : cityId,
				'examId' : examId,
				'studentId' : studentId,
				'locality' : locality,
				'pageNumber' : pageNumber,
				'pageSizeCount' : $("#pageSizeCount").val(),
				'countExist' : true
			}),
			success : function(resp)
			{
				if(resp != "" && resp != null)
	 			{
	 				$("#tutorialFilterContentDiv").append(resp);
	 			}
				$("#tutorialFilterLoading").hide();
			},
			error : function(msg,arg1,arg2)
			{
				$("#tutorialFilterLoading").hide();
				alert("page load error");
			}
		});
	}
	
	function fnCancel()
	{
		var localStoreName = EdupathLocalStore.getTutorialName();
		var localStorageData = JSON.parse(EdupathLocalStore.doGet(localStoreName, '${studentId}'));
		var emxamId = localStorageData['examId'];
		var cityId = localStorageData['cityId'];
		var locality = localStorageData['locality'];
		if (emxamId != undefined && emxamId != '') 
		{
			$("input[name=examId][value=" + localStorageData['examId'] + "]").prop('checked', true);
		}
		if (cityId != undefined && cityId != '') 
		{
			$("#cityId").val(cityId).trigger('change');
		}
		if(locality != undefined && locality != '')
		{
			$("#tutLocalityListSelect").val(locality).trigger('change');
		}
	}
	
	function fnGetTutorialsCenters()
	{
		$(".nomore-records-center").hide();
		var tutIds = $('input[name=tutorialId]:checked').map(function(_, el) {
	        return $(el).val();
	    }).get();
		var tutoriaIds = tutIds.join();
		if(tutoriaIds == '' || tutoriaIds == undefined)
		{
			var message = "Please select atleast one Tutorial to proceed";
			$("#errorPopId").html(message);
			$("#errorMsgDIVId").modal('show');
			return false;
		}
		var examId = $('input[name=examId]:checked').val();
		var studentId = $("#studentId").val();
		var cityId = $("#cityId").val();
		var locality = '';
		if($('#tutLocalityListSelect').val() != "select")
		{
			locality = $("#tutLocalityListSelect").val();
		}
		url = '${pageContext.request.contextPath}/myapp/getTutorialCentersSelectTutorialsAction';
		$.ajax({
			url : url,
			type: 'POST',
			data : ({
				'examId' : examId,
				'studentId' : studentId,
				'tutorialIdStr' : tutoriaIds,
				'locality' : locality,
				'cityId' : cityId
			}),
			success : function(resp)
			{
				if(resp != "" && resp != null)
	 			{
					$("#totalTutorialRecordDiv").hide();
					$("#tutorialFilterContentDiv").hide();
					$("#tutorialCentersDiv").show();
					$("#tutorialCentersDiv").html(resp);
					$("#totalCentersRecordDiv").show();
	 			}
			},
			error : function(msg,arg1,arg2)
			{
				alert("page load error");
			}
		});
	}
	
	
	function fnShortListCenterConfirm()
	{
		var message = '';
		var tutCentreIds = $('input[name=tutorialCenterId]:enabled:checked').map(function(_, el) {
	        return $(el).val();
	    }).get();
		if(tutCentreIds.length == 0)
		{
			message += "Please select some Tutorial Centers to shortlist";
			$("#errorPopId").html(message);
			$("#errorMsgDIVId").modal('show');
			return false;
		}
		$('#tutModelConfirmBtnId').show();
		message += "Do you want to add these items to the cart?";
		$('#seleMsgText').find('b').html(tutCentreIds.length);
		$('#messageTxt').html(message);
		$("#selTutNameId").html("");
		$.each(tutCentreIds, function(ind, tutId){
			
			var tutName = $("#tutorialCenterId_"+tutId).data("cetername");
			var tutNameDataStr = '';
			tutNameDataStr += "<div class='tut-model-main-deta-div' id='tutShortListId_"+tutId+"'>";
			tutNameDataStr += "<div class='finalize-deta-div'>"+ tutName +"</div>";	
			tutNameDataStr += "<div class='finalize-deta-close-div' onclick='fnRemoveTutShortList("+tutId+")'><i class='glyphicon glyphicon-remove'></i></div>";
			tutNameDataStr += "</div>";
			
			$("#selTutNameId").append(tutNameDataStr);
		});
		$('#messageDIVId').modal("show");
	}
	
	function fnShortListTutorialsCenters()
	{
		$('#messageDIVId').modal('toggle');
		var tutorialCenterObject = {};
		var tutCentreIds = $('input[name=tutorialCenterId]:enabled:checked').map(function(_, el) {
	        return $(el).val();
	    }).get();
		
		$.each(tutCentreIds, function(index, val){
			//console.log("tutCentreId:"+ val);
			//console.log("tutId"+ $("#center_tutorialId_"+val).val());
			tutorialCenterObject[val] = $("#center_tutorialId_"+val).val();
		});
		
		url = '${pageContext.request.contextPath}/myapp/insertCentresToCartSelectTutorialsAction';
		$.ajax({
			url : url,
			type: 'POST',
			data : ({
				'tutorialCenterObject' : tutorialCenterObject,
				'tutorialCentreSelectCount' : centerCount
			}),
			success : function(resp)
			{
				if(resp != "" && resp != null)
	 			{
					if(resp.status == 'success')
					{
						fnDisableChkbox(resp.tutorialCenterMap);
						 $('#tutorial_msg_label').text("Tutorial added successfully");
						 $('#tutorial_msg_timer').modal('show');
						 setTimeout(function(){
							  $("#tutorial_msg_timer").modal("hide");
						  }, 2000);	
					}
					else if(resp.TutShortListMaxCount == 'exceed')
					{
						var message = "You can shortlist "+centerCount+" Tutorial Centers. Please choose the appropriate number of Tutorial Centers to continue";
						$("#errorPopId").html(message);
						$("#errorMsgDIVId").modal('show');
						return false;
					}
					else if(resp.tutorialExist == 'tutorialExist')
					{
						var message = "Tutorial "+resp.tutorialName+" already exist";
						$("#errorPopId").html(message);
						$("#errorMsgDIVId").modal('show');
						return false;
					}
	 			}
			},
			error : function(msg,arg1,arg2)
			{
				alert("page load error");
			}
		});
	}
	
	function fnDisableChkbox(tutorialCenterObject)
	{
		$.each(tutorialCenterObject, function(key, val){
			$("input[name=tutorialCenterId][value=" + key + "]").prop('disabled', true);
		});
	}
	
	function fnResetAll()
	{
		var localStoreName = EdupathLocalStore.getTutorialName();
		EdupathLocalStore.doDelete(localStoreName, '${studentId}');
		var localStorageData = JSON.parse(EdupathLocalStore.doGet(localStoreName, '${studentId}'));
		var form = document.tutorialDetailForm;
		form.action = '${pageContext.request.contextPath}/myapp/SelectTutorialsAction';
		form.submit();
	}
	
	function fnSkip()
	{
		var form = document.tutorialDetailForm;
		form.action = '${pageContext.request.contextPath}/myapp/FinalizeAction';
		form.submit();
	}
	
	function fnRedirectToCollegeSelect()
	{
		var form = document.tutorialDetailForm;
		form.action = '${pageContext.request.contextPath}/myapp/getFilterCollegeSelectionAction';
		form.submit();
	}
	
	function fnRemoveTutShortList(tutId)
	{
		var count = $('#seleMsgText').find('b').html();
		$("#tutShortListId_"+tutId).remove();
		$.each($('input[name=tutorialCenterId]:enabled:checked'), function() {
	        if(this.id == "tutorialCenterId_"+tutId)
	        {
	        	$(this).prop("checked", false);	
	        }
	    });
		if (count == 1) 
		{
			$('#messageTxt').html("Please select some Tutorial Centers to shortlist");
			$('#tutModelConfirmBtnId').hide();
		}
		$('#seleMsgText').find('b').html(count - 1);
	}
	
	function fnCloseErrorPopUp()
	{
		$("#errorMsgDIVId").modal('toggle');
	}
</script>