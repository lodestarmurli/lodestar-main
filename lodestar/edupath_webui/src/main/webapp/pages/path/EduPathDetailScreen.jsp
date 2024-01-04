<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<s:set name="nameLabel"><s:text name="com.edupath.template.BoardComparator"/></s:set>
<link href="${pageContext.request.contextPath}/thirdparty/select2_new/dist/css/select2.min.css" rel="stylesheet"  type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/select2_new/dist/js/select2.min.js"></script>
<!-- topTwoSelected -->
<div class="edupath-margin-top edupath-padding">
	<div class="row" id="eduPathDiv">
		<div class="col-md-6">
			<span class="edupath_select_lbl"><s:text name="com.edupath.path.select.title"></s:text></span>
	 		<select id="shortListSelect" style="width:300px;">
	 		    <option value="top" data-ids="${topTwoSelected}" selected="selected"><s:text name="com.edupath.path.option"></s:text></option>
	 		    <c:if test="${null ne shortList and not empty shortList}">
	 		       <c:forEach items="${shortList}" var="shortId">
	 		          <c:choose>
	 		            <c:when test="${shortId.industryId eq null}">
	 		              <option value="0" data-occid="${shortId.occupationId}">${utils:replaceXMLEntities(shortId.occupationName)}</option>
	 		            </c:when>
	 		            <c:otherwise>
	 		              <option value="${shortId.industryId}" data-occid="0">${utils:replaceXMLEntities(shortId.industryName)}</option>
	 		            </c:otherwise>
	 		          </c:choose>
	 		       </c:forEach>
	 		    </c:if>
	 		</select>
	 		<div style="display: inline-block; padding-left: 10px;">
	 			<%-- <button class="btn blue" onclick="fnShowResult();"><s:text name="com.edupath.path.btn.show"></s:text></button> --%>
	 		</div>
		</div>
		<div class="col-md-6">
		<!--<span onclick="fnDownloadBoardComparator()">Download Excel Document</span>-->
		<a href="${pageContext.request.contextPath}/thirdparty/BoardComparison.xls">Download Board Comparator</a>
		</div>
	<div class="edupath-padding">
		<div class="edupath_content">
			<div class="row">
				<div class="col-md-12">
				</div>
			</div>
			
			<div  id="eduPathResopnesId">
				<jsp:include page="/pages/path/EduPathResponseScreen.jsp"></jsp:include>
			</div>
		</div>
	</div>
	</div>
	<div class="edupath-padding">
		<div class="row" id="entrExamDiv" >
			
		</div>
	</div>
	<div class="edupath-padding">
		<div class="row" id="integCourseDiv" >
			
		</div>
	</div>
	<div class="edupath-padding">
		<div class="row" id="occExamDiv" >
			
		</div>
	</div>
	<div class="edupath-padding">
		<div class="row" id="occCourseDiv" >
		
		</div>
	</div>
	<div class="edupath-padding">
		<div class="row" id="viewelectives" >
			
		</div>
	</div>
</div>


<s:form name="EduPathResponseForm" id="EduPathResponseForm" method="post">
	<s:hidden name="occupationIds" id="occupationIds"></s:hidden>
	<s:hidden name="industryIds" id="industryIds"></s:hidden>
	<s:hidden name="occupationNameStr" id="occupationNameStr"></s:hidden>
	<s:hidden name="industryNameStr" id="industryNameStr"></s:hidden>
	<s:hidden name="topTwoNameStr" id="topTwoNameStr"></s:hidden>
	<s:hidden name="topTwoActive" id="topTwoActive"></s:hidden>
</s:form>
<script type="text/javascript">
//var form = document.eduPath;
var loading = '<div align="center"><img src="${pageContext.request.contextPath}/images/loader.gif"></div>';
var occupationName = [];
var isAction = true;
var oldData;
// vyankatesh
var stateId="";
stateId="${cityId}";

var SecondOccuID="";
var FristOccuID ="";

// vyankatesh
var indIdList = [];
	$(document).ready(function()
	{ 
		$eventSelect = $("#shortListSelect");
		oldData = $("#shortListSelect").html();
		
		$("#shortListSelect option").each(function(key, value)
		{
			var json = new Object();
			json.name = $(this).text();
			occupationName.push(json);
		});
		
		$("#shortListSelect").select2({theme: "classic"});
		
		 $eventSelect.on("select2:open", function (e) 
	     { 
			fnINITEvent();
	     });
		
		$eventSelect.on("select2:close", function (e) 
		{ 
			//$("#shortListSelect").html('');
			//$("#shortListSelect").html(oldData);
			isAction = true;
	   	}); 
		$("#shortListSelect").change(function()
		{
			isAction = true;
			fnGetSelectedValue();
		});
		var indIdStr = $('#industryIds').val();
		if(indIdStr != '' && indIdStr != undefined)
		{
			var idArr = indIdStr.split(",");
			for(var a = 0; a < idArr.length; a++)
			{
				indIdList.push(idArr[a]);
			}
		}	
	});
	
	function fnEntranceExam(occId, occIndId, editFlag,filterID)
	{
		//alert(stateId);
		var id;
		
		if(filterID == 0)
			{
			id="0";
			}
		else
			{
			//alert(stateId)
			id=stateId;
			}
		
		if(occIndId == 'null')
		{
			occIndId = null
		}
		
		
		url = '${pageContext.request.contextPath}/myapp/EntranceExams';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		data:({ 
	 			"occId" : occId,
	 			"occIndusryId" : occIndId,
	 			"flag" : editFlag ,
	 			"statenational" : id, 
	 		}),
	 		success: function(resp)
	 		{
	 			if(resp != "" && resp != null)
	 			{
	 				
	 				//console.log("=============================>"+resp);
	 				$("#entrExamDiv").show();
	 				$("#eduPathDiv").hide();
	 				$("#integCourseDiv").hide();
	 				$("#entrExamDiv").html(resp);
	 				$("#my_scrolling_pane").scrollTop(0);
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			return false;
	 		}
	 	});
	}
	
	function fnIntegratedCourses(occId, occIndId, editFlag)
	{
		if(occIndId == 'null')
		{
			occIndId = null
		}
		url = '${pageContext.request.contextPath}/myapp/IntegratedCourse';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		data:({ 
	 			"occId" : occId,
	 			"occIndusryId" : occIndId,
	 			"flag" : editFlag 
	 		}),
	 		success: function(resp)
	 		{
	 			if(resp != "" && resp != null)
	 			{
	 				$("#integCourseDiv").show();
	 				$("#eduPathDiv").hide();
	 				$("#entrExamDiv").hide();
	 				$("#integCourseDiv").html(resp);
	 				$("#my_scrolling_pane").scrollTop(0);
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			return false;
	 		}
	 	});
	}
	
	function fnGetOccupationWithExam(indusId)
	{
		url = '${pageContext.request.contextPath}/myapp/getOccWithExamsEntranceExams';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		data:({ 
	 			
	 			"industryId" : indusId
	 			
	 		}),
	 		success: function(resp)
	 		{
	 			if(resp != "" && resp != null)
	 			{
	 				$("#occExamDiv").show();
	 				$("#entrExamDiv").hide();
	 				$("#eduPathDiv").hide();
	 				$("#integCourseDiv").hide();
	 				$("#occExamDiv").html(resp);
	 				$("#my_scrolling_pane").scrollTop(0);
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			return false;
	 		}
	 	});
	}
	
	function fnGetOccupationWithCourse(indusId)
	{
		url = '${pageContext.request.contextPath}/myapp/getOccWithCourseIntegratedCourse';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		data:({ 
	 			
	 			"industryId" : indusId
	 			
	 		}),
	 		success: function(resp)
	 		{
	 			if(resp != "" && resp != null)
	 			{
	 				$("#occCourseDiv").show();
	 				$("#entrExamDiv").hide();
	 				$("#eduPathDiv").hide();
	 				$("#integCourseDiv").hide();
	 				$("#occCourseDiv").html(resp);
	 				$("#my_scrolling_pane").scrollTop(0);
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			return false;
	 		}
	 	});
	}
	
	function fnGetOccupationName(searchTxt)
	{
		url = '${pageContext.request.contextPath}/myapp/getOccupationEduPath';
		$('.custom-input-loader').addClass("loadinggif");
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		data:{"oldData":JSON.stringify(occupationName)},
	 		success: function(resp)
	 		{
	 			if(resp != "" && resp != null)
	 			{
	 				$("#shortListSelect").html('');
	 				$("#shortListSelect").html(oldData);
	 				var isMatch = true;
	 				$.each(resp, function(key, value)
	 				{
	 				    if(isMatch) 
 						{
	 				        var re = new RegExp(searchTxt, 'gi');
	 					 	if(value.name.match(re) != null)
	 					 	{
	 							 var newOpt = "<option value='0' data-occid='"+value.id+"'>" +value.name+ "</option>";
		 						 $("#shortListSelect:eq(0)").append(newOpt);
		 						 isMatch = false;
	 						}
	 					 	else
	 					 	{
	 					 		 var newOpt = "<option value='0' data-occid='"+value.id+"'>" +value.name+ "</option>";
		 						 $("#shortListSelect").append(newOpt);
	 					 	}	
	 					 }
	 				     else
	 				     {
	 				    	 var newOpt = "<option value='0' data-occid='"+value.id+"'>" +value.name+ "</option>";
	 						 $("#shortListSelect").append(newOpt);
	 				     }
 						
	 				 });
	 				$('.custom-input-loader').removeClass("loadinggif");
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			$('.custom-input-loader').removeClass("loadinggif");
	 			return false;
	 		}
	 	});
	}
	
	function fnINITEvent()
	{
		$('.select2-container').find('input').addClass("custom-input-loader");
		$('.select2-container').find('input').keypress(function(){
			 var opt = $('.select2-results__option').text();
			 var searchTxt =  $(this).val();
			 if(opt == "No results found")
			 {
				 if(isAction)
				 {
					fnGetOccupationName(searchTxt);
					isAction = false;
				 }	 
			 }
		});
	}
	
	function fnGetSelectedValue()
	{
		var value = $("#shortListSelect option:selected").val();
		var indIds = [] , occIds = [];
		if(value == "top")
		{
			$('#topTwoActive').val('Y');
			var ids = $("#shortListSelect option:selected").data("ids");
			var newIds = ids.split(',');
			for(var a = 0; a< newIds.length; a++)
			{
				if(newIds[a].startsWith('oc_'))
				{
				   var item = newIds[a].split('_');
				   occIds.push(item[1]);
				}
				else
				{
					var item = newIds[a].split('_');
					indIds.push(item[1]);
				}	
			}	
			
		}
		else
		{
			if(value != '0')
			{
				indIds.push(value);
				$('#industryNameStr').val($("#shortListSelect option:selected").text());
			}
			else
			{
				occIds.push($("#shortListSelect option:selected").data("occid"));
				$('#occupationNameStr').val($("#shortListSelect option:selected").text());
				
			}
			$('#topTwoActive').val('N');
		}	
		fnSetRequestData(indIds, occIds);
	}
	function fnSetRequestData(inIds, occIds)
	{
		if(inIds.length> 0)
		{
			$('#industryIds').val(inIds.join(","));
		}
		else
		{
			$('#industryIds').val('');
		}	
		if(occIds.length> 0)
		{
			$('#occupationIds').val(occIds.join(","));
		}
		else
		{
			$('#occupationIds').val('');
		}
		fnGetEduPathDetails();
	}
	
	function fnGetEduPathDetails()
	{
		
		var forms = document.EduPathResponseForm;
		forms.action = '${pageContext.request.contextPath}/myapp/getDetailsEduPath';
		var options = { 
		        beforeSubmit:  showEduPathRequest,
		        success:       showEduPathResponse
		    }; 
	    $("#EduPathResponseForm").ajaxSubmit(options);
	}
	
	function showEduPathRequest(formData, jqForm, options)
	{
		$('#eduPathResopnesId').html("<div style='width:100%; text-align:center; top:200px;'><img src='${pageContext.request.contextPath}//images/loader.gif'/></div>");
	} 
	
	function showEduPathResponse(responseText, statusText, xhr, $form)
	{
		$('#eduPathResopnesId').html('');
		$('#eduPathResopnesId').html(responseText);
	}
	
	//option1Id values occ_1 or ind_1
	function fnShowElective(option1Id, option1EdupathId, option1OccIndusId, option1ManStreamId, option1PreStreamIds, option1ManSubjectId, option1PreSubjectIds,
			option2Id, option2EdupathId, option2OccIndusId, option2ManStreamId, option2PreStreamIds, option2ManSubjectId, option2PreSubjectIds, allowSelect, 
			selectedStreamId, selectedSubjectId, option1DegreeStream, option2DegreeStream)
	{
		$("#occCourseDiv").hide();
		$("#entrExamDiv").hide();
		$("#eduPathDiv").hide();
		$("#integCourseDiv").hide();
		$("#viewelectives").show();
		$("#viewelectives").html(loading)
		url = '${pageContext.request.contextPath}/myapp/viewElective';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		data:({ 
	 			"option1Id":option1Id, "option1EdupathId":option1EdupathId, "option1OccIndusId":option1OccIndusId, 
	 			"option1ManStreamId":option1ManStreamId, "option1PreStreamIds":option1PreStreamIds, 
	 			"option1ManSubjectId":option1ManSubjectId, "option1PreSubjectIds":option1PreSubjectIds,
	 			"option2Id":option2Id, "option2EdupathId":option2EdupathId, "option2OccIndusId":option2OccIndusId, 
	 			"option2ManStreamId":option2ManStreamId, "option2PreStreamIds":option2PreStreamIds, 
	 			"option2ManSubjectId":option2ManSubjectId, "option2PreSubjectIds":option2PreSubjectIds, "allowSelect":allowSelect, 
	 			"selectedStreamId":selectedStreamId, "selectedSubjectId":selectedSubjectId, 
	 			"option1DegreeStream":option1DegreeStream,"option2DegreeStream":option2DegreeStream
	 		}),
	 		success: function(resp)
	 		{
	 			if(resp != "" && resp != null)
	 			{
	 				$("#viewelectives").html(resp);
	 				$("#my_scrolling_pane").scrollTop(0);
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			return false;
	 		}
	 	});	
	}
	
	function fnOnCityIdChangefilter(id)
	 {
		 stateId=id;
		 
		 
		 var checkFristOccuIDvalue = document.getElementById('FristOccuIDvalue');
		 if (checkFristOccuIDvalue === null) 
			{
			 var FristOccuIDvalue="";
			 var FristOccuIDvalueoptionName="";
			}
		 else
			 {
			 var FristOccuIDvalue=document.getElementById("FristOccuIDvalue").value;
			 var FristOccuIDvalueoptionName=document.getElementById("FristOccuIDvalueoptionName").value;
			 }
		 
		 var checkSecondOccuIDvalue = document.getElementById('SecondOccuIDvalue');
		 if (checkSecondOccuIDvalue === null) 
			 {
			 var SecondOccuIDvalue="";
			 var SecondOccuIDvalueoptionName="";
			 }
		 else
			 {
			 var SecondOccuIDvalue=document.getElementById("SecondOccuIDvalue").value;
			 var SecondOccuIDvalueoptionName=document.getElementById("SecondOccuIDvalueoptionName").value;
			 }
		 
		 
		 
		 
		
		 var showdata="";
		 var showdata1="";
		 //alert(SecondOccuIDvalue);
		// alert(SecondOccuIDvalueoptionName);
		if(SecondOccuIDvalue != "")
	 {
	document.getElementById("examId_"+SecondOccuIDvalue).innerHTML="No Exam Found";
			url = '${pageContext.request.contextPath}/myapp/getStateExamsEntranceExams';
			
			$.ajax({
		 		url: url,
		 		type: "POST",
		 		data:({ 
		 			
		 			"occId" : SecondOccuIDvalue,
		 			"cityId" : id,
		 			
		 		}),
		 		success: function(resp)
		 		{
		 			//alert(resp);
		 			//alert("examId_"+SecondOccuIDvalue);
		 			document.getElementById("examId_"+SecondOccuIDvalue).innerHTML="No Exam Found";
		 			if(resp != "" && resp != null)
		 			{
		 				
		 				document.getElementById("examId_"+SecondOccuIDvalue).innerHTML="";
		 				showdata=showdata+'<p class="p_courses_exam " onclick="fnEntranceExam('+SecondOccuIDvalue+', '+SecondOccuIDvalueoptionName+', true,1);">'+resp+'</p>';
		 				document.getElementById("examId_"+SecondOccuIDvalue).innerHTML=showdata;
		 				
		 				
		 			}	
		 		},
		 		error :  function(msg,arg1,arg2)
		 		{
		 			return false;
		 		}
		 	});
			
	 }
		
			//Frist Occupation 
			if(FristOccuIDvalue !="")
				{
				
				
			document.getElementById("examId_"+FristOccuIDvalue).innerHTML="No Exam Found";
			url = '${pageContext.request.contextPath}/myapp/getStateExamsSecondEntranceExams';
			$.ajax({
		 		url: url,
		 		type: "POST",
		 		data:({ 
		 			
		 			"occId" : FristOccuIDvalue,
		 			"cityId" : id,
		 			
		 		}),
		 		success: function(resp1)
		 		{
		 			//alert(resp);
		 			//alert("examId_"+SecondOccuIDvalue);
		 			document.getElementById("examId_"+FristOccuIDvalue).innerHTML="No Exam Found";
		 			if(resp1 != "" && resp1 != null)
		 			{
		 				
		 				
		 				document.getElementById("examId_"+FristOccuIDvalue).innerHTML=" ";
		 				showdata1=showdata1+'<p class="p_courses_exam " onclick="fnEntranceExam('+FristOccuIDvalue+', '+FristOccuIDvalueoptionName+', true,1);">'+resp1+'</p>';
		 				
		 				document.getElementById("examId_"+FristOccuIDvalue).innerHTML=showdata1;
		 			}	
		 		},
		 		error :  function(msg,arg1,arg2)
		 		{
		 			return false;
		 		}
		 	});
			
				}
			
			if(FristOccuIDvalue =="" && SecondOccuIDvalue=="")
				{
				
				url = '${pageContext.request.contextPath}/myapp/lockOnlyCityEntranceExams';
				$.ajax({
			 		url: url,
			 		type: "POST",
			 		data:({ 
			 			
			 			
			 			"cityId" : id,
			 			
			 		}),
			 		success: function(resp1)
			 		{
			 			
			 		},
			 		error :  function(msg,arg1,arg2)
			 		{
			 			return false;
			 		}
			 	});
				
				
				}
			
			
			
	 }
	
	
	
	
</script>

<style>
.edupath_select_lbl
{
  color: #9EC4E1;
  font-size: 17pt;
  vertical-align: top;
  padding-right: 10px;
}
.edupath_div_1
{
  width: 23%;
  float:left;
  height: 100%;
  padding-top: 20px;
  padding-bottom: 20px;
  padding-left: 35px;
}
.edupath_div_2
{
  float:left;
  font-size: 15pt;
  width: 69%;
  padding-left: 15px;
  padding-top: 20px;
  color: #B6C1D3;
  /* position: absolute; */
}
.eupath_img
{
}
.edupath_global_div
{
   height: 120px;
   background-color: #F2F5FA;
}
.custom-md-edupath-6
{
 	width: 95% !important;
}

.div_lbl_1
{
  width: 100%;
  clear: both;
}
.div_lbl_2
{
  float: left;
}
.h_bar_div
{
  
}
.h_bar
{
    width: 2%;
    border: 1px solid #E4EBF1;
    margin-top: 20px;
}
.lbl_img_div
{
  width: 9%;
}
.lbl_text_div
{
    width: 80%;
    padding-left: 10px;
    padding-top: 12px;
    font-size: 11pt;
    font-weight: 600;
}
.lbl_v_bar_div
{
    width: 9%;
    border-left: 2px solid #E4EBF1;
    margin-left: 21px;
}
.lbl_content_div
{
   width: 80%;
   font-size: 11pt;
}
.inner_lbl_div
{
    margin-top: 10px;
    margin-bottom: 10px;
}
.lbl_4_div
{
 clear: both;
 width: 100%;
}
.box_lbl_4
{
  width: 50%;
  word-warp:break-word;
  float: left;
}
.box_lbl_4_2
{
  padding-left: 20px;
}
.progress-bar
{
  background: #0F89F6 !important;
}
.progress
{
  height: 7px !important;
  background-color: #D5DEE3;
}
.custom-pg-bar
{
   padding-left: 35px;
   clear: both;
   width: 100%;
}
.custom-radio-lbl
{
    padding-left: 30px;
    padding-bottom: 5px;
    padding-top: 5px;
    clear: both;
}
.pg-bar-message_1
{
	float: left;
	width: 70%;
}
.pg-bar-message_2
{
	float: left;
	margin-top: -5px;
    font-size: 9pt;
    font-weight: 600;
    color: #B5BDC8;
}
.p_lbl_message
{
  height: 50px;
}
.loadinggif 
{
    background:url('../images/text-loader.gif') no-repeat right center;
}
.p_courses_exam
{
    cursor: pointer;
    text-decoration: underline;
    color: #6DABF8;
    font-size: 11pt;
    word-spacing: 5px;
    line-height: 20px;
}
.job-count-div
{
  padding-bottom: 5px;
}
.exam-course-incative
{
   text-decoration: none !important;
   cursor: default !important;
}
.p_Title
{
    line-height: 60px;
    font-size: 11pt;
}
</style>
