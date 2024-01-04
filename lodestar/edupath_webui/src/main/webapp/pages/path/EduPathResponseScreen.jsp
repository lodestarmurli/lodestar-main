<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<c:if test="${null != edupathDetailMap}">
 <c:set var="elActive" value="disabled"></c:set>
 <c:set var="occSize" value="0"></c:set>
 <c:set var="elChecked" value=""></c:set>
 <c:set var="optionMatch" value="N"></c:set>
 <c:set var="anyFirst" value="N"></c:set>

	<c:choose>
		<c:when test="${null ne edupathDetailMap['OCCUPATION'] and not empty edupathDetailMap['OCCUPATION']}">
			<c:choose>
				<c:when test="${edupathDetailMap['OCCUPATION'].size() gt 1}">
					<c:set var="occSize" value="${edupathDetailMap['OCCUPATION'].size()}"></c:set>
					<c:choose>
						<c:when test="${edupathDetailMap['OCCUPATION']['OPTION_2']['choseELActive'] eq 'Y'}">
							<c:set var="elActive" value=""></c:set>
							<c:set var="elChecked" value="checked"></c:set>
							<c:set var="optionMatch" value="Y"></c:set>
							<c:set var="anyFirst" value="N"></c:set>
						</c:when>
						<c:when test="${edupathDetailMap['OCCUPATION']['OPTION_2']['choseELActive'] eq 'F_Y'}">
							<c:set var="elActive" value=""></c:set>
							<c:set var="elChecked" value="checked"></c:set>
							<c:set var="optionMatch" value="Y"></c:set>
						 	<c:set var="anyFirst" value="Y"></c:set>
						</c:when>
					</c:choose>
				</c:when>
			</c:choose>
		</c:when>
	</c:choose>
	<div class="row">
		<div class="col-md-12">
			<c:choose>
	    		<c:when test="${topTwoActive eq 'Y'}">
	    			<p class="p_Title"><s:text name="com.eupath.path.top.two"></s:text></p>
	    			<c:set var="radioActiveParam" value=""></c:set>
	    		</c:when>
				<c:otherwise>
	    			<p class="p_Title"><s:text name="com.eupath.path.top.one"></s:text></p>
	    			<c:set var="radioActiveParam" value="disabled"></c:set>
	    		</c:otherwise>
   			</c:choose>
   				<c:if test="${showOccId eq 1 and  showIndId eq 1}">
					<c:set var="occSize" value="${edupathDetailMap['OCCUPATION'].size()}"></c:set>
					<c:choose>
						<c:when test="${edupathDetailMap['OCCUPATION']['OPTION_1']['choseELActive'] eq 'Y'}">
							<c:set var="elActive" value=""></c:set>
							<c:set var="elChecked" value="checked"></c:set>
							<c:set var="optionMatch" value="Y"></c:set>
							<c:set var="radioActiveParam" value="disabled"></c:set>
							<%-- <c:set var="anyFirst" value="N"></c:set> --%>
						</c:when>
						<c:when test="${edupathDetailMap['OCCUPATION']['OPTION_1']['choseELActive'] eq 'F_Y'}">
							<c:set var="elActive" value=""></c:set>
							<c:set var="elChecked" value=""></c:set>
							<c:set var="optionMatch" value="Y"></c:set>
							<c:set var="radioActiveParam" value=""></c:set>
						 	<%-- <c:set var="anyFirst" value="Y"></c:set> --%>
						</c:when>
					</c:choose>
				</c:if>
				<c:if test="${showOccId eq 1 and  showIndId le 0}">
				
					<c:set var="occSize" value="${edupathDetailMap['OCCUPATION'].size()}"></c:set>
					<c:choose>
						<c:when test="${edupathDetailMap['OCCUPATION']['OPTION_1']['choseELActive'] eq 'F_Y'}">
							<c:set var="elActive" value=""></c:set>
							<c:set var="elChecked" value=""></c:set>
							<c:set var="optionMatch" value="Y"></c:set>
							<c:set var="radioActiveParam" value=""></c:set>
						 	<%-- <c:set var="anyFirst" value="Y"></c:set> --%>
						</c:when>
						<c:when test="${edupathDetailMap['OCCUPATION']['OPTION_1']['choseELActive'] eq 'Y'}">
							<c:set var="elActive" value=""></c:set>
							<c:set var="elChecked" value="checked"></c:set>
							<c:set var="optionMatch" value="Y"></c:set>
							<c:set var="radioActiveParam" value=""></c:set>
						 	<%-- <c:set var="anyFirst" value="Y"></c:set> --%>
						</c:when>
					</c:choose>
				</c:if>
				
				<c:choose>
					<c:when test="${occOrderId gt 1 and inOrderId eq 1}">
						<c:set var="radioActiveParam" value=""></c:set>
						<c:set var="elActive" value=""></c:set>
						<c:set var="elChecked" value=""></c:set>
					</c:when>
				</c:choose>
		</div>
	</div>
	<div class="row" style="margin-right: 5px !important;" id="main_change_div">
		<c:choose>
			<c:when test="${showOccId gt 1 }">
				<div class="col-md-6 move_div" id="occ_change">
				   <jsp:include page="/pages/path/OccupationEduPathDetailsScreen.jsp">
				  	 <jsp:param name="optionName" value="OPTION_1" />
				  	 <jsp:param name="radioActive" value="${radioActiveParam}" />
				  	 <jsp:param value="${elChecked}" name="radioChecked"/>
				  		<jsp:param name="setCityPosition" value="Y" />
				   </jsp:include>
				</div>
				<div class="col-md-6 move_div" id="occ_change">
				  <jsp:include page="/pages/path/OccupationEduPathDetailsScreen.jsp">
				  	<jsp:param name="optionName" value="OPTION_2" />
				  	<jsp:param name="radioActive" value="${radioActiveParam}" />
				  	<jsp:param value="" name="radioChecked"/>
				  	<jsp:param name="setCityPosition" value="N" />
				  </jsp:include>
				</div>
				
			</c:when>
			<c:when test="${showOccId eq 1 and  showIndId eq 1}">
				<div class="col-md-6 move_div" id="occ_change">
				   <jsp:include page="/pages/path/OccupationEduPathDetailsScreen.jsp">
				   		<jsp:param name="optionName" value="OPTION_1" />
				   		<jsp:param value="${elChecked}" name="radioChecked"/>
				   		<jsp:param name="setCityPosition" value="Y" />
				   		 
				   </jsp:include>
				</div>
				<div class="col-md-6 move_div" id="in_change">
				   <jsp:include page="/pages/path/IndustryEduPathDetailsScreen.jsp">
				   	   <jsp:param name="optionName" value="OPTION_1" />
				   	   <jsp:param name="radioActive" value="${radioActiveParam}" />
				   	   <jsp:param name="setCityPosition" value="N" />
				   </jsp:include>
				</div>
			</c:when>
			<c:when test="${showIndId gt 1 }">
				<div class="col-md-6 move_div" id="in_change">
					<jsp:include page="/pages/path/IndustryEduPathDetailsScreen.jsp">
						<jsp:param name="optionName" value="OPTION_1" />
						<jsp:param name="radioActive" value="${radioActiveParam}" />
						 <jsp:param name="setCityPosition" value="Y" />
					</jsp:include>
				</div>
				<div class="col-md-6 move_div" id="in_change">
					<jsp:include page="/pages/path/IndustryEduPathDetailsScreen.jsp">
						<jsp:param name="optionName" value="OPTION_2" />
						<jsp:param name="radioActive" value="${radioActiveParam}" />
							<jsp:param name="setCityPosition" value="N" />
					</jsp:include>
				</div>
			</c:when>
			<c:when test="${showIndId eq 1 and showOccId le 0}">
				<div class="col-md-6">
					<jsp:include page="/pages/path/IndustryEduPathDetailsScreen.jsp">
						<jsp:param name="optionName" value="OPTION_1" /> 
						<jsp:param name="radioActive" value="" />
						 <jsp:param name="setCityPosition" value="N" />
					</jsp:include>
				</div>
				<div class="col-md-6">
				</div>
			</c:when>
			<c:when test="${showOccId eq 1 and showIndId le 0}">
				<div class="col-md-6">
					<jsp:include page="/pages/path/OccupationEduPathDetailsScreen.jsp">
						<jsp:param name="optionName" value="OPTION_1" />
						 <jsp:param name="radioActive" value="" />
						 <jsp:param value="${elChecked}" name="radioChecked"/>
						  <jsp:param name="setCityPosition" value="N" />
					</jsp:include>
				</div>
				<div class="col-md-6">
				</div>
			</c:when>
		</c:choose>
	</div>
	<div class="row">
		<div class="col-md-12">
			<button class="btn btn_action"  id="electiveBTNID" onclick="fnSetElectiveFormData();" style="margin-top: 20px;" ${elActive}><s:text name="com.eupath.path.chose.elective"></s:text></button>
		</div>
	</div>
	<div class="modal fade in" id="eduPathModalId" role="dialog" >
	   <div class="modal-dialog">
	   
	     <!-- Modal content-->
	     <div class="modal-content">
	       <div class="modal-header">
	         <button type="button" class="close" data-dismiss="modal">&times;</button>
	         <h4 class="modal-title"></h4>
	       </div>
	       <div class="modal-body" style="text-align: center;">
	         <p style="font-size: 11pt" id="occ_messageId"></p>
	       </div>
	       <div class="modal-footer" style="text-align: center;">
	         <button type="button" class="btn custom-modal-btn" data-dismiss="modal"><s:text name="com.edupath.questionnarie.model.btn.label"/></button>
	       </div>
	     </div>
	   </div>
  </div>
</c:if>	

<script type="text/javascript">

var pustreamArr = [];
var occupationSize = '${occSize}';

var occOrder = ${occOrderId};
var inOrder = ${inOrderId};

if('${topTwoActive}' == 'Y')
{
	if(occOrder > inOrder)
	{
		$('#main_change_div').find('#in_change').insertBefore($("#main_change_div").find("#in_change").prev());
	}
	if(inOrder > occOrder)
	{
		$('#main_change_div').find('#occ_change').insertBefore($("#main_change_div").find("#occ_change").prev());
	}	
}

if(occOrder > 1)
{
	  $('.check_input').change(function ()
	  {
		    $('.check_input').not(this).prop("checked", false);
	  });
	  
}	

function fnSetElectiveFormData()
{
	var optionOneId = null, optionTwoId = null, optionOneEdupathId = null, 
		optionTwoEdupathId = null, optionOneOccIndusId = null, optionTwoOccIndusId = null, 
		optionOneManStreamId = -1, optionTwoManStreamId =-1, optionOnePreStreamIds = -1, 
		optionTwoPreStreamIds = -1, optionOneManSubjectId = -1, optionTwoManSubjectId = -1, 
		optionOnePreSubjectIds = -1, optionTwoPreSubjectIds = -1, selectedStreamId = -1, 
		selectedSubjectId = -1, optionOneDegreeStream = null, optionTwoDegreeStream = null;
	
	var allowSelect = false;
	if('${showOccId gt 1}' == 'true')
	{
		
			optionOneId = "occ_" + $('input[name=pustream_OPTION_1]').data('id');
			optionOneManStreamId = $('input[name=pustream_OPTION_1]').val();
			optionOnePreStreamIds = $('input[name=pustream_OPTION_1]').data('preferrdstream');
			optionOneEdupathId = $('input[name=pustream_OPTION_1]').data('eduid');
			
			optionTwoId = "occ_" + $('input[name=pustream_OPTION_2]').data('id');
			optionTwoManStreamId = $('input[name=pustream_OPTION_2]').val();
			optionTwoPreStreamIds = $('input[name=pustream_OPTION_2]').data('preferrdstream');
			optionTwoEdupathId = $('input[name=pustream_OPTION_2]').data('eduid');
			
			
			optionOneManSubjectId = $('input[name=pusubject_OPTION_1]').val();
			optionOnePreSubjectIds = $('input[name=pusubject_OPTION_1]').data('preferrdsubject');
			
			optionTwoManSubjectId = $('input[name=pusubject_OPTION_2]').val();
			optionTwoPreSubjectIds = $('input[name=pusubject_OPTION_2]').data('preferrdsubject');
			
			optionOneOccIndusId = $('#occ_ind_OPTION_1').val();
			optionTwoOccIndusId = $('#occ_ind_OPTION_2').val();
			
			selectedSubjectId = optionOneManSubjectId;
			if(optionOneManSubjectId == "-1")
			{
				var selected = $("input[name=pusubject]:checked").val();
				if(selected != "")
				{
					selectedSubjectId = selected
				}	
			}
			
			selectedStreamId = optionOneManStreamId;
			if(optionOneManStreamId == "-1")
			{
				var selected = $("input[name=pustream]:checked").val();
				if(selected != "")
				{
					selectedStreamId = selected
				}
			}	
			
		    allowSelect = true;
		
	}
	else if('${showOccId eq 1 and  showIndId eq 1}' == 'true' )
	{
		optionOneId = "ind_" + $('#IND_OPTION_1').val();
		
		if(occOrder > inOrder)
		{
			optionOneId = "ind_" + $('#IND_OPTION_1').val();
			optionTwoId = "occ_" + $('input[name=pustream_OPTION_1]').data('id');
		}
		if(inOrder > occOrder)
		{
			optionTwoId = "ind_" + $('#IND_OPTION_1').val();
			optionOneId = "occ_" + $('input[name=pustream_OPTION_1]').data('id');
		}
		
		//selectedSubjectId = $('input[name=indPUSubject]:checked').val();
		var puStObj = $('input[name=pustream]').val();
		var puSubObj = $('input[name=pusubject]').val();
		
		if(($('input[name=indPUStream]:checked').val() == '' || $('input[name=indPUStream]:checked').val() == undefined) && puStObj == undefined)
		{
			//pustream_OPTION_1
			selectedStreamId = $('input[name=pustream_OPTION_1]').val();
		}
		else if(($('input[name=indPUStream]:checked').val() == '' || $('input[name=indPUStream]:checked').val() == undefined) && puStObj != undefined)
		{
			//pustream_OPTION_1
			selectedStreamId = $('input[name=pustream]:checked').val();
		}
		else if($('input[name=indPUStream]:checked').val() != '' && $('input[name=indPUStream]:checked').val() != undefined)
		{
			selectedStreamId =  $('input[name=indPUStream]:checked').val();
		}
		
		
		if(puSubObj == undefined)
		{
			optionOneManSubjectId = $('input[name=pusubject_OPTION_1]').val();
		}
		else if($('input[name=pusubject]:checked').val() != '' && $('input[name=pusubject]:checked').val() != undefined)
		{
			optionOneManSubjectId = $('input[name=pusubject]:checked').val();
			selectedSubjectId = optionOneManSubjectId;
		}
		
		
		
		//selectedStreamId =   $('input[name=indPUStream]:checked').val();
		if(inOrder == 1)
		{
			optionOneDegreeStream = $('input[name=indDGStream]:checked').val();//indDGStream
			optionTwoDegreeStream = null;
			
			optionTwoEdupathId = $('input[name=pustream_OPTION_1]').data('eduid');
		}	
		else
		{
			optionTwoDegreeStream = $('input[name=indDGStream]:checked').val();//indDGStream
			optionOneDegreeStream = null;
			optionOneEdupathId = $('input[name=pustream_OPTION_1]').data('eduid');
		}	
		
		//if((optionOneDegreeStream == null && optionTwoDegreeStream == undefined) || (optionOneDegreeStream == undefined && optionTwoDegreeStream == null))
		if(!optionOneDegreeStream && !optionTwoDegreeStream)
		{
			$('#occ_messageId').html("Please select degree stream.");
			fnINITEduPathDialog();
			return;
		}	
		
		optionOneManStreamId = $('input[name=pustream_OPTION_1]').val();
		optionOnePreStreamIds = $('input[name=pustream_OPTION_1]').data('preferrdstream');
		
		//optionOneManSubjectId = $('input[name=pusubject_OPTION_1]').val();
		optionOnePreSubjectIds = $('input[name=pusubject_OPTION_1]').data('preferrdsubject');
		
		
		 //selectedSubjectId = $('input[name=pusubject]:checked').val();
		//selectedStreamId =  $('input[name=pustream]:checked').val();
		
		allowSelect =true;
	}
	else if('${showIndId gt 1}' == 'true' )
	{
		optionOneId = "ind_" + $('#IND_OPTION_1').val();
		optionTwoId = "ind_" + $('#IND_OPTION_2').val();
		
		//selectedSubjectId = $('input[name=indPUSubject]:checked').val();
		selectedStreamId =   $('input[name=indPUStream]:checked').val();
		optionOneDegreeStream = $('input[name=indDGStream]:checked').val();//indDGStream
		optionTwoDegreeStream = $('input[name=indDGStream]:checked').val();
		
		allowSelect =true;
		
		if(optionOneDegreeStream == null)
		{
			$('#occ_messageId').html("Please select degree stream.");
			fnINITEduPathDialog();
			return;
		}
		
	}
	else if('${showIndId eq 1 and showOccId le 0}' == 'true' )
	{
		optionOneId = "ind_" + $('#IND_OPTION_1').val();
		//selectedSubjectId = $('input[name=indPUStream]:checked').val();
		selectedStreamId =   $('input[name=indPUStream]:checked').val();
		optionOneDegreeStream = $('input[name=indDGStream]:checked').val();//indDGStream
		
		allowSelect =false;
		if(optionOneDegreeStream == null)
		{
			$('#occ_messageId').html("Please Select degree stream.");
			fnINITEduPathDialog();
			return;
		}
	}
	else if('${showOccId eq 1 and showIndId le 0}' == 'true' )
	{
		optionOneId = "occ_" + $('input[name=pustream_OPTION_1]').data('id');
		
		optionOneManStreamId = $('input[name=pustream_OPTION_1]').val();
		optionOnePreStreamIds = $('input[name=pustream_OPTION_1]').data('preferrdstream');
		
		optionOneManSubjectId = $('input[name=pusubject_OPTION_1]').val();
		optionOnePreSubjectIds = $('input[name=pusubject_OPTION_1]').data('preferrdsubject');
		
		optionOneEdupathId = $('input[name=pustream_OPTION_1]').data('eduid');
		
		selectedSubjectId = $('input[name=pusubject]:checked').val();
		selectedStreamId =  $('input[name=pustream]:checked').val();
		
		allowSelect  = false;
	}
	
	/* console.log("optionOneId : " + optionOneId + " optionOneManStreamId : " + optionOneManStreamId + " optionOnePreStreamIds : " + optionOnePreStreamIds);
	console.log(" optionOneEdupathId : " + optionOneEdupathId + " optionTwoId : " + optionTwoId + " optionTwoManStreamId : " + optionTwoManStreamId);
	console.log(" optionTwoEdupathId : " + optionTwoEdupathId + " optionOneManSubjectId : " + optionOneManSubjectId + " optionTwoPreStreamIds : " + optionTwoPreStreamIds);
	console.log("optionTwoPreSubjectIds : " + optionTwoPreSubjectIds + " optionOneOccIndusId : " + optionOneOccIndusId + " optionTwoOccIndusId : " + optionTwoOccIndusId);
	console.log("selectedSubjectId : " + selectedSubjectId + " selectedStreamId : " + selectedStreamId + " optionOneDegreeStream" + optionOneDegreeStream + " optionTwoDegreeStream" + optionTwoDegreeStream);  */
	
	fnShowElective(optionOneId, optionOneEdupathId, optionOneOccIndusId, optionOneManStreamId, optionOnePreStreamIds, optionOneManSubjectId, optionOnePreSubjectIds, 
			optionTwoId, optionTwoEdupathId, optionTwoOccIndusId, optionTwoManStreamId, optionTwoPreStreamIds, optionTwoManSubjectId, optionTwoPreSubjectIds,
			allowSelect, selectedStreamId, selectedSubjectId, optionOneDegreeStream, optionTwoDegreeStream);
	
}

function fnCheckStream(opt, thisObj)
{
    var currentValue = $(thisObj).val();
    var count = 0;
    
    if('${anyFirst}' == 'Y')
    {
    	return;
    }	
    if(parseInt(occupationSize) > 1)
	{
	   $("input[name=pustream]").each(function (key, item)
	   {
		   pustreamArr.push($(this).val());
		      
	   });
	   
	   for(var a = 0; a< pustreamArr.length; a++)
	   {
		  if(currentValue == pustreamArr[a])
	      {
			  count = count + 1;
	      }		  
	   }	   
	   
	   if(count > 1)
	   {
		   pustreamArr = [];
		   fnActiveChoseElective();
	   }
	   else
	   {
		   pustreamArr = [];
		   fnCheckOption(opt);
	   }	   
	}
    else
    {
    	pustreamArr = [];
    	fnActiveChoseElective();
    }	
}

function fnCheckOption(opt)
{
	if(opt == "OPTION_1")
	{
		   pustreamArr = [];
		   $('#occ_messageId').html('<s:text name="com.eupath.path.option.one.message"/>');
		   fnINITEduPathDialog();
		   fnActiveChoseElective();
	 }
	 else
	 {
		   pustreamArr = [];
		   $('#electiveBTNID').prop("disabled", true);
		   $('#occ_messageId').html('<s:text name="com.eupath.path.option.two.message"/>');
		   fnINITEduPathDialog();
	  }	
}

function fnActiveChoseElective()
{
	$('#electiveBTNID').prop("disabled", false);
}

function fnINITEduPathDialog()
{
	$('#eduPathModalId').modal();
}

function fnCheckIndStream()
{
	fnActiveChoseElective();
}

</script>

