<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="fn" uri="fn.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%> 

<link rel="stylesheet" href="${pageContext.request.contextPath}/thirdparty/checkbox/build.css"/>
<link href="${pageContext.request.contextPath}/thirdparty/select2_new/dist/css/select2.min.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/ApplicationStyles.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/ReadingPage.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/StudentTUM.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/datatables/css/components.css" rel="stylesheet" type="text/css"/>		
  
<link href="${pageContext.request.contextPath}/thirdparty/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/font-awesome/css/font-awesome.min.css" rel="stylesheet" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/nanoScroller/enscroll-0.6.1.min.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/thirdparty/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/CommonScripts.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery_form.js" type="text/javascript" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/select2_new/dist/js/select2.min.js"></script>

<div>
	<div class="row rpheadingbar" style="height: 50px;">
		<div class="col-md-12">
			<div class="rppagetile" style="padding-top: 15px;">
				 Global Search
			</div>	
		</div>
	</div>
	<div id="globalSearchMainDiv" style="padding-left: 30px; padding-top: 20px;">
		<div class="row">
			<div class="col-md-12">
				 <div class="radio radio-success radio-inline" style="display: inline;">
		             <input type="radio" id="radio_1" value="Exam" name="useroption" checked="checked" onclick="fnShowExam();">
		             <label for="radio_1" class="qusetion_txt_label" style="padding-left: 5px;">
		             	<s:text name="com.edupath.global.search.radio.exams"></s:text>
		             </label>
		          </div>
	          	  <div class="radio radio-success radio-inline" style="display: inline;">
		             <input type="radio" id="radio_2" value="Course" name="useroption" onclick="fnGetIntegratedCourse();">
		             <label for="radio_2" class="qusetion_txt_label" style="padding-left: 5px;">
		             	<s:text name="com.edupath.global.search.radio.course"></s:text>
		             </label>
		          </div>
		          <div class="radio radio-success radio-inline" style="display: inline;">
		             <input type="radio" id="radio_3" value="College" name="useroption"  onclick="fnGetCollege();">
		             <label for="radio_3" class="qusetion_txt_label" style="padding-left: 5px;">
		             	<s:text name="com.edupath.global.search.radio.pucollege"></s:text>
		             </label>
		          </div>
	          	  <div class="radio radio-success radio-inline" style="display: inline;">
		             <input type="radio" id="radio_4" value="Tutorial" name="useroption" onclick="fnGetTutorials();">
		             <label for="radio_4" class="qusetion_txt_label" style="padding-left: 5px;">
		             	<s:text name="com.edupath.global.search.radio.tutorial"></s:text>
		             </label>
		          </div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;" id="examSearchDiv">
			<div class="col-md-3">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.occupation.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="occExamListSelect" style="width:180px;" onchange="fnOccExamSel();">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-3">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.exam.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="examNameListSelect" style="width:180px;" onchange="fnExamNameSel();">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-3">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.when.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="examWhenListSelect" style="width:180px;" onchange="fnExamWhenSel();">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-3">
				<div class="row">
					<button class="btn btn_action" style="margin-left:16px; margin-top: 7px;" onclick="fnExamSearch();"><span><s:text name="com.edupath.common.search"></s:text></span></button>
				</div>
			</div>
		</div>
		<div class="row" id="courseSearchDiv" style="margin-top: 20px; display: none;">
			<div class="col-md-3">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.occupation.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="occCourseListSelect" style="width:180px;" onchange="fnOccCourseSel();">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-3">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.course.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="courseNameListSelect" style="width:180px;" onchange="fnCourseNameSel();">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-3">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.institute.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="instituteListSelect" style="width:180px;" onchange="fnInstituteSel();">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-3">
				<div class="row">
					<button class="btn btn_action" onclick="fnCourseSearch();"><span><s:text name="com.edupath.common.search"></s:text></span></button>
				</div>
			</div>
		</div>
		
		
		
		<!-- -------------- college serach------- -->
		<div id="collegeSearchDiv" style="margin-top: 20px; display: none;">
			<div class="row">
				<div class="col-md-3">
					<div>
						<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.name.title"></s:text>&nbsp;:&nbsp;</span>
			 		</div>
			 		<div>
				 		<span>
					 		<select id="colNameListSelect" style="width:180px;" onchange="fnColNameSel();">
					 		  <option value="select">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
					 		</select>
				 		</span>
			 		</div>
				</div>
				<div class="col-md-3">
					<div>
						<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.stream.title"></s:text>&nbsp;:&nbsp;</span>
			 		</div>
			 		<div>
				 		<span>
					 		<select id="colStreamListSelect" style="width:180px;" onchange="fnColStreamSel();">
					 		  <option value="select">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
					 		</select>
				 		</span>
			 		</div>
				</div>
				<div class="col-md-3">
					<div>
						<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.electivecombination.title"></s:text>&nbsp;:&nbsp;</span>
			 		</div>
			 		<div>
				 		<span>
					 		<select id="colElectiveCombListSelect" style="width:180px;" onchange="fnColElectiveCombSel();">
					 		  <option value="select">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
					 		</select>
				 		</span>
			 		</div>
				</div>
				<div class="col-md-3">
					
				</div>
			</div>
			
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-3">
					<div>
						<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.board.title"></s:text>&nbsp;:&nbsp;</span>
			 		</div>
			 		<div>
				 		<span>
					 		<select id="colBoardListSelect" style="width:180px;" onchange="fnColBoardSel();">
					 		  <option value="select">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
					 		</select>
				 		</span>
			 		</div>
				</div>
				<div class="col-md-3">
					<div>
						<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.city.title"></s:text>&nbsp;:&nbsp;</span>
			 		</div>
			 		<div>
				 		<span>
					 		<select id="colCityListSelect" style="width:180px;" onchange="fnColCitySel(this.value);">
					 		  <option value="select">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
					 		</select>
				 		</span>
			 		</div>
				</div>
				<div class="col-md-3">
					<div>
						<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.locality.title"></s:text>&nbsp;:&nbsp;</span>
			 		</div>
			 		<div>
				 		<span>
					 		<select id="colLocalityListSelect" style="width:180px;">
					 		  <option value="select">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
					 		</select>
				 		</span>
			 		</div>
				</div>
				<div class="col-md-3">
					<div class="row" style="margin-left: 20px;">
						<button class="btn btn_action" onclick="fnCollegeSearch();"><span><s:text name="com.edupath.common.search"></s:text></span></button>
					</div>
				</div>
			</div>
		</div>
		
		
		
		
		<!-- -------------- tutorial serach------- -->
		<div class="row" id="tutorialSearchDiv" style="margin-top: 20px; display: none;">
			<div class="col-md-2">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.institute.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="tutInstitListSelect" style="width:160px;" onchange="fnTutInstitSel();">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-2">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.exam.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="tutExamsListSelect" style="width:160px;" onchange="fnTutExamsSel();">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-2">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.city.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="tutCityListSelect" style="width:160px;" onchange="fnTutCitySel(this.value);">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-2">
				<div>
					<span class="qusetion_txt_label"><s:text name="com.edupath.global.search.select.locality.title"></s:text>&nbsp;:&nbsp;</span>
		 		</div>
		 		<div>
			 		<span>
				 		<select id="tutLocalityListSelect" style="width:160px;">
				 		  <option value="select">
				 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
				 		  </option>
				 		</select>
			 		</span>
		 		</div>
			</div>
			<div class="col-md-4">
				<div class="row" style="margin-left: 20px;">
					<button class="btn btn_action" onclick="fnTutorialSearch();"><span><s:text name="com.edupath.common.search"></s:text></span></button>
				</div>
			</div>
		</div>
		
		
		
		
	</div>	
	<div class="row" id="contentDiv" style="display: none; padding-left: 30px;">
		<div class="row" id="entrExamDiv" >
			
		</div>
		<div class="row" id="integCourseDiv" >
			
		</div>
		<div class="row" id="collegeDiv">
			
		</div>
		<div class="row" id="tutorialDiv">
			
		</div>
	</div>
</div>
<div id="globalSearchLoading" class="display-hide cust-loader globalSearchLoading">
	<div class="app-spinner">
		<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
	</div>
</div>

<script type="text/javascript">
var isCollegeReturn = false;
var isTutorialReturn = false;
var tutorialCityLocalityMap;
var collegeCityLocalityMap;
	$(document).ready(function()
	{ 
		$("#occExamListSelect").select2({theme: "classic"});
		$("#examNameListSelect").select2({theme: "classic"});
		$("#examWhenListSelect").select2({theme: "classic"});
		
		$("#occCourseListSelect").select2({theme: "classic"});
		$("#courseNameListSelect").select2({theme: "classic"});
		$("#instituteListSelect").select2({theme: "classic"});
		
		$("#tutInstitListSelect").select2({theme: "classic"});
		$("#tutExamsListSelect").select2({theme: "classic"});
		$("#tutCityListSelect").select2({theme: "classic"});
		$("#tutLocalityListSelect").select2({theme: "classic"});
		
		$("#colNameListSelect").select2({theme: "classic"});
		$("#colStreamListSelect").select2({theme: "classic"});
		$("#colElectiveCombListSelect").select2({theme: "classic"});
		$("#colBoardListSelect").select2({theme: "classic"});
		$("#colCityListSelect").select2({theme: "classic"});
		$("#colLocalityListSelect").select2({theme: "classic"});
		
		url = '${pageContext.request.contextPath}/myapp/getExamDetailsGlobalSearch';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		beforeSend: function( xhr ) 
	 		{
	 			$('.globalSearchLoading').show();
 		    },
	 		success: function(resp)
	 		{
	 			$('.globalSearchLoading').hide();
	 			if(resp != "" && resp != null)
	 			{
	 				$.each(resp.occupationName, function(key, value){
	 					$("#occExamListSelect").append("<option value='"+value.id+"'>"+value.name+"</option>");
	 				}); 
	 				$.each(resp.examName, function(key, value){
	 					$("#examNameListSelect").append("<option value='"+value.id+"'>"+value.examName+"</option>");
	 				});
	 				$.each(resp.examWhen, function(key, value){
	 					$("#examWhenListSelect").append("<option value='"+value+"'>"+value+"</option>");
	 				});
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			$('.globalSearchLoading').hide();
	 			return false;
	 		}
	 	});
	});
	
	var data = "";
	function fnGetIntegratedCourse()
	{
		
		$("#examSearchDiv").hide();
		$("#courseSearchDiv").show();
		$("#contentDiv").hide();
		$("#tutorialSearchDiv").hide();
		$("#collegeSearchDiv").hide();
		if (data == null || data == "") 
		{
			data = "course";
			url = '${pageContext.request.contextPath}/myapp/getCourseDetailsGlobalSearch';
			$.ajax({
		 		url: url,
		 		type: "POST",
		 		beforeSend: function( xhr ) 
		 		{
		 			$('.globalSearchLoading').show();
	 		    },
		 		success: function(resp)
		 		{
		 			$('.globalSearchLoading').hide();
		 			if(resp != "" && resp != null)
		 			{
		 				$.each(resp.occupationName, function(key, value){
		 					$("#occCourseListSelect").append("<option value='"+value.id+"'>"+value.name+"</option>");
		 				}); 
		 				$.each(resp.courseName, function(key, value){
		 					$("#courseNameListSelect").append("<option value='"+value.id+"'>"+value.programName+"</option>");
		 				});
		 				$.each(resp.institute, function(key, value){
		 					$("#instituteListSelect").append("<option value='"+value+"'>"+value+"</option>");
		 				});
		 			}	
		 		},
		 		error :  function(msg,arg1,arg2)
		 		{
		 			$('.globalSearchLoading').hide();
		 			return false;
		 		}
		 	});
		}
	}
	
	function fnShowExam()
	{
		$("#examSearchDiv").show();
		$("#courseSearchDiv").hide();
		$("#tutorialSearchDiv").hide();
		$("#collegeSearchDiv").hide();
		$("#contentDiv").hide();
	}
	
	function fnOccExamSel()
	{
		if($('#occExamListSelect').val() != "select")
		{
	 		$("#examNameListSelect").val("select").trigger("change");
			$("#examWhenListSelect").val("select").trigger("change"); 
		}
	}
	
	function fnExamNameSel()
	{
		if($('#examNameListSelect').val() != "select")
		{
			$("#occExamListSelect").val("select").trigger("change");
			$("#examWhenListSelect").val("select").trigger("change"); 
		}
	}
	
	function fnExamWhenSel()
	{
		if($('#examWhenListSelect').val() != "select")
		{
			$("#examNameListSelect").val("select").trigger("change");
			$("#occExamListSelect").val("select").trigger("change"); 
		}
	}
	
	function fnOccCourseSel()
	{
		if($('#occCourseListSelect').val() != "select")
		{
	 		$("#courseNameListSelect").val("select").trigger("change");
			$("#instituteListSelect").val("select").trigger("change");
		}
	}
	
	function fnCourseNameSel()
	{
		if($('#courseNameListSelect').val() != "select")
		{
			$("#occCourseListSelect").val("select").trigger("change");
			$("#instituteListSelect").val("select").trigger("change");
		}
	}
	
	function fnInstituteSel()
	{
		if($('#instituteListSelect').val() != "select")
		{
			$("#occCourseListSelect").val("select").trigger("change");
			$("#courseNameListSelect").val("select").trigger("change");
		}
	}
	
	function fnExamSearch()
	{
		var occName = "";
		var examName = "";
		var examWhen = "";
		if($("#occExamListSelect").val() != "select")
		{
			occName = $("#occExamListSelect").val();
		}
		else if($("#examNameListSelect").val() != "select")
		{
			examName = $("#examNameListSelect").val();
		}
		else if($("#examWhenListSelect").val() != "select")
		{
			examWhen = $("#examWhenListSelect").val();
		}
		else
		{
			alert("please select data");
			return false;
		}
		
		url = '${pageContext.request.contextPath}/myapp/getExamByDataGlobalSearch';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		beforeSend: function( xhr ) 
	 		{
	 			$('.globalSearchLoading').show();
 		    },
	 		data:({ 
	 			"examOccId" : occName,
	 			"examId" : examName,
	 			"examWhen" : examWhen 
	 		}),
	 		success: function(resp)
	 		{
	 			$('.globalSearchLoading').hide();
	 			if(resp != "" && resp != null)
	 			{
	 				$("#entrExamDiv").html(resp);
	 				$("#contentDiv").show();
	 				$("#entrExamDiv").show();
	 				$("#globalSearchMainDiv").hide();
	 				$("#integCourseDiv").hide();
	 				$("#collegeDiv").hide();
	 				$("#tutorialDiv").hide();
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			$('.globalSearchLoading').hide();
	 			return false;
	 		}
	 	});
	}
	
	function fnCourseSearch()
	{
		var occName = "";
		var courseName = "";
		var institute = "";
		if($("#occCourseListSelect").val() != "select")
		{
			occName = $("#occCourseListSelect").val();
		}
		else if($("#courseNameListSelect").val() != "select")
		{
			courseName = $("#courseNameListSelect").val();
		}
		else if($("#instituteListSelect").val() != "select")
		{
			institute = $("#instituteListSelect").val();
		}
		else
		{
			alert("please select data");
			return false;
		}
		
		url = '${pageContext.request.contextPath}/myapp/getCourseByDataGlobalSearch';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		beforeSend: function( xhr ) 
	 		{
	 			$('.globalSearchLoading').show();
 		    },
	 		data:({ 
	 			"courseOccId" : occName,
	 			"courseId" : courseName,
	 			"institute" : institute 
	 		}),
	 		success: function(resp)
	 		{
	 			$('.globalSearchLoading').hide();
	 			if(resp != "" && resp != null)
	 			{
	 				$("#integCourseDiv").html(resp);
	 				$("#contentDiv").show();
	 				$("#integCourseDiv").show();
	 				$("#globalSearchMainDiv").hide();
	 				$("#entrExamDiv").hide();
	 				$("#collegeDiv").hide();
	 				$("#tutorialDiv").hide();
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			$('.globalSearchLoading').hide();
	 			return false;
	 		}
	 	});
	}
	
	
	//------tutorial search-----
	
	var tutData = "";
	function fnGetTutorials()
	{
		$("#examSearchDiv").hide();
		$("#courseSearchDiv").hide();
		$("#collegeSearchDiv").hide();
		$("#tutorialSearchDiv").show();
		$("#contentDiv").hide();
		if (tutData == null || tutData == "") 
		{
			tutData = "tutorial";
			url = '${pageContext.request.contextPath}/myapp/getTutorialDetailsGlobalSearch';
			$.ajax({
		 		url: url,
		 		type: "POST",
		 		beforeSend: function( xhr ) 
		 		{
		 			$('.globalSearchLoading').show();
	 		    },
		 		success: function(resp)
		 		{
		 			$('.globalSearchLoading').hide();
		 			if(resp != "" && resp != null)
		 			{
		 				$.each(resp.tutorialName, function(key, value){
		 					$("#tutInstitListSelect").append("<option value='"+value.id+"'>"+value.name+"</option>");
		 				}); 
		 				$.each(resp.examName, function(key, value){
		 					$("#tutExamsListSelect").append("<option value='"+value.id+"'>"+value.examName+"</option>");
		 				});
		 				$.each(resp.cityName, function(key, value){
		 					$("#tutCityListSelect").append("<option value='"+value.id+"'>"+value.name+"</option>");
		 				});
		 				tutorialCityLocalityMap = resp.tutorialCityLocalityMap;
		 				
		 			}	
		 		},
		 		error :  function(msg,arg1,arg2)
		 		{
		 			$('.globalSearchLoading').hide();
		 			return false;
		 		}
		 	});
		}
	}
	
	function fnTutInstitSel()
	{
		if($('#tutInstitListSelect').val() != "select")
		{
	 		$("#tutExamsListSelect").val("select").trigger("change");
			$("#tutCityListSelect").val("select").trigger("change");
			$("#tutLocalityListSelect").val("select").trigger("change"); 
		}
	}
	
	function fnTutExamsSel()
	{
		if($('#tutExamsListSelect').val() != "select")
		{
	 		$("#tutInstitListSelect").val("select").trigger("change");
			$("#tutCityListSelect").val("select").trigger("change");
			$("#tutLocalityListSelect").val("select").trigger("change"); 
		}
	}
	
	function fnTutCitySel(cityId)
	{
		$('#tutLocalityListSelect').find('option').remove().end().
			append('<option value="select">----Select----</option>').val('select').trigger("change");
		
		if($('#tutCityListSelect').val() != "select")
		{
	 		$("#tutInstitListSelect").val("select").trigger("change");
			$("#tutExamsListSelect").val("select").trigger("change");
			//$("#tutLocalityListSelect").val("select").trigger("change"); 
		}
		$.each(tutorialCityLocalityMap, function(key, value){
			if (key == cityId) 
			{
				$.each(value, function(key, value){
 					$("#tutLocalityListSelect").append("<option value='"+value+"'>"+value+"</option>");
 				}); 
			}
		});
	}
	
	function fnTutorialSearch()
	{
		var tutId = "";
		var tutExamId = "";
		var tutCityId = "";
		var tutLocality = "";
		if($("#tutInstitListSelect").val() != "select")
		{
			tutId = $("#tutInstitListSelect").val();
		}
		else if($("#tutExamsListSelect").val() != "select")
		{
			tutExamId = $("#tutExamsListSelect").val();
		}
		else if($("#tutLocalityListSelect").val() != "select")
		{
			tutCityId = $("#tutCityListSelect").val();
			tutLocality = $("#tutLocalityListSelect").val();
		}
		else if($("#tutCityListSelect").val() != "select")
		{
			tutCityId = $("#tutCityListSelect").val();
		}
		else
		{
			alert("please select data");
			return false;
		}
		
		url = '${pageContext.request.contextPath}/myapp/getTutorialByDataGlobalSearch';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		beforeSend: function( xhr ) 
	 		{
	 			$('.globalSearchLoading').show();
 		    },
	 		data:({ 
	 			"tutorialId" : tutId,
	 			"tutExamId" : tutExamId,
	 			"tutCityId" : tutCityId,
	 			"tutLocality" : tutLocality
	 		}),
	 		success: function(resp)
	 		{
	 			$('.globalSearchLoading').hide();
	 			if(resp != "" && resp != null)
	 			{
	 				$("#tutorialDiv").html(resp);
	 				$("#contentDiv").show();
	 				$("#tutorialDiv").show();
	 				$("#globalSearchMainDiv").hide();
	 				$("#entrExamDiv").hide();
	 				$("#collegeDiv").hide();
	 				$("#integCourseDiv").hide();
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			$('.globalSearchLoading').hide();
	 			return false;
	 		}
	 	});
	}
	
	//------college search-----
	
	var colData = "";
	function fnGetCollege()
	{
		$("#examSearchDiv").hide();
		$("#courseSearchDiv").hide();
		$("#collegeSearchDiv").hide();
		$("#tutorialSearchDiv").hide();
		$("#collegeSearchDiv").show();
		$("#contentDiv").hide();
		if (colData == null || colData == "") 
		{
			colData = "college";
			url = '${pageContext.request.contextPath}/myapp/getCollegeDetailsGlobalSearch';
			$.ajax({
		 		url: url,
		 		type: "POST",
		 		beforeSend: function( xhr ) 
		 		{
		 			$('.globalSearchLoading').show();
	 		    },
		 		success: function(resp)
		 		{
		 			$('.globalSearchLoading').hide();
		 			if(resp != "" && resp != null)
		 			{
		 				$.each(resp.collegeName, function(key, value){
		 					$("#colNameListSelect").append("<option value='"+value.id+"'>"+value.name+"</option>");
		 				}); 
		 				$.each(resp.streamName, function(key, value){
		 					$("#colStreamListSelect").append("<option value='"+value.id+"'>"+value.name+"</option>");
		 				});
		 				$.each(resp.combinationName, function(key, value){
		 					$("#colElectiveCombListSelect").append("<option value='"+value.id+"'>"+value.name+"</option>");
		 				});
		 				$.each(resp.boardName, function(key, value){
		 					$("#colBoardListSelect").append("<option value='"+value.id+"'>"+value.name+"</option>");
		 				});
		 				$.each(resp.cityName, function(key, value){
		 					$("#colCityListSelect").append("<option value='"+value.id+"'>"+value.name+"</option>");
		 				});
		 				
		 				collegeCityLocalityMap = resp.collegeCityLocalityMap;
		 			}	
		 		},
		 		error :  function(msg,arg1,arg2)
		 		{
		 			$('.globalSearchLoading').hide();
		 			return false;
		 		}
		 	});
		}
	}
	
	function fnColNameSel()
	{
		if($('#colNameListSelect').val() != "select")
		{
	 		$("#colStreamListSelect").val("select").trigger("change");
			$("#colElectiveCombListSelect").val("select").trigger("change");
			$("#colBoardListSelect").val("select").trigger("change"); 
			$("#colCityListSelect").val("select").trigger("change"); 
			$("#colLocalityListSelect").val("select").trigger("change"); 
		}
	}
	
	function fnColStreamSel()
	{
		if($('#colStreamListSelect').val() != "select")
		{
			$("#colNameListSelect").val("select").trigger("change");
			$("#colElectiveCombListSelect").val("select").trigger("change");
			$("#colBoardListSelect").val("select").trigger("change"); 
			$("#colCityListSelect").val("select").trigger("change"); 
			$("#colLocalityListSelect").val("select").trigger("change"); 
		}
	}
	
	function fnColElectiveCombSel()
	{
		if($('#colElectiveCombListSelect').val() != "select")
		{
			$("#colNameListSelect").val("select").trigger("change");
			$("#colStreamListSelect").val("select").trigger("change");
			$("#colBoardListSelect").val("select").trigger("change"); 
			$("#colCityListSelect").val("select").trigger("change"); 
			$("#colLocalityListSelect").val("select").trigger("change"); 
		}
	}
	
	function fnColBoardSel()
	{
		if($('#colBoardListSelect').val() != "select")
		{
			$("#colNameListSelect").val("select").trigger("change");
			$("#colStreamListSelect").val("select").trigger("change");
			$("#colElectiveCombListSelect").val("select").trigger("change"); 
			$("#colCityListSelect").val("select").trigger("change"); 
			$("#colLocalityListSelect").val("select").trigger("change"); 
		}
	}
	
	function fnColCitySel(cityId)
	{
		$('#colLocalityListSelect').find('option').remove().end().
			append('<option value="select">----Select----</option>').val('select').trigger("change");
		
		if($('#colCityListSelect').val() != "select")
		{
			$("#colNameListSelect").val("select").trigger("change");
			$("#colStreamListSelect").val("select").trigger("change");
			$("#colElectiveCombListSelect").val("select").trigger("change"); 
			$("#colBoardListSelect").val("select").trigger("change"); 
		}
		
		$.each(collegeCityLocalityMap, function(key, value){
			if (key == cityId) 
			{
				$.each(value, function(key, value){
 					$("#colLocalityListSelect").append("<option value='"+value+"'>"+value+"</option>");
 				}); 
			}
		});
	}
	
	function fnCollegeSearch()
	{
		var colName = "";
		var colStream = "";
		var colCombination = "";
		var colBoard = "";
		var colCity = "";
		var colLocality = "";
		if($("#colNameListSelect").val() != "select")
		{
			colName = $("#colNameListSelect").val();
		}
		else if($("#colStreamListSelect").val() != "select")
		{
			colStream = $("#colStreamListSelect").val();
		}
		else if($("#colElectiveCombListSelect").val() != "select")
		{
			colCombination = $("#colElectiveCombListSelect").val();
		}
		else if($("#colBoardListSelect").val() != "select")
		{
			colBoard = $("#colBoardListSelect").val();
		}
		else if($("#colLocalityListSelect").val() != "select")
		{
			colCity = $("#colCityListSelect").val();
			colLocality = $("#colLocalityListSelect").val();
		}
		else if($("#colCityListSelect").val() != "select")
		{
			colCity = $("#colCityListSelect").val();
		}
		else
		{
			alert("please select data");
			return false;
		}
		url = '${pageContext.request.contextPath}/myapp/getCollegeByDataGlobalSearch';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		beforeSend: function( xhr ) 
	 		{
	 			$('.globalSearchLoading').show();
 		    },
	 		data:({ 
	 			"collegeId" : colName,
	 			"streamId" : colStream,
	 			"combinationId" : colCombination,
	 			"boardId" : colBoard,
	 			"colCityId" : colCity,
	 			"colLocality" : colLocality,
	 		}),
	 		success: function(resp)
	 		{
	 			//console.log(resp);
	 			$('.globalSearchLoading').hide();
	 			if(resp != "" && resp != null)
	 			{
	 				$("#collegeDiv").html(resp);
	 				$("#contentDiv").show();
	 				$("#collegeDiv").show();
	 				$("#globalSearchMainDiv").hide();
	 				$("#entrExamDiv").hide();
	 				$("#tutorialDiv").hide();
	 				$("#integCourseDiv").hide();
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			$('.globalSearchLoading').hide();
	 			return false;
	 		}
	 	});
	}
	
</script>