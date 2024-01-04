<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="commonutil.tld" prefix="utils"%>
<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="fn" uri="fn.tld" %>

<link href="${pageContext.request.contextPath}/thirdparty/select2_new/dist/css/select2.min.css" rel="stylesheet"  type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/select2_new/dist/js/select2.min.js"></script>

<div class="row filter-margin-top">
	<div class="col-md-12 col-xs-12 college-search-header">  <!-- ADDED 27/03/17 -->
		<s:text name="com.edupath.college.search.header"/> 
	</div>
</div>

<c:if test="${not empty finalMap}">
	<div class="electives">
	<!-- vyankatesh  -->
	<div class="zone-row">
			<div class="col-md-6 college-search-label">
				<span><s:text name="com.edupath.bulkupload.column.city"></s:text></span>
			</div>
			<div class="col-md-6">
					<s:select list="cityList" class="zone-select select2" listKey="id" listValue="name"  name="cityId" id="cityId" disabled="true" >
					</s:select>
					
			</div>
			<br><br>
			
		</div>
	
	
	
	
	
		<div class="zone-row">
		<br><br>
			<div class="col-md-6 college-search-label">
				<span><s:text name="com.edupath.college.search.locationpreference"/></span>
			</div>
			<div class="col-md-6">
				<select id="location_preference" class="zone-select select2">
					<option value="">-- Select --</option>
					<c:forEach items="${finalMap['collegeZones']}" var="zone">
						<option value="${zone}">${zone}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<p class="entr_exam_details">${utils:replaceXMLEntities(finalMap['venueAddress'])}</p>
				</div>
			</div>
		</div>
		<div class="elective-row">
			<div class="col-md-6 college-search-label">
				<s:text name="com.edupath.college.search.electives"/>
			</div>
			<div class="col-md-6">
				<c:if test="${null ne finalMap['electiveMap'] and not empty finalMap['electiveMap']}">
			   		<c:forEach items="${finalMap['electiveMap']}" var="dataList" varStatus="count">
			   			<div class="col-md-12 el_txt college-border">
			   				${utils:replaceXMLEntities(dataList.value)}
			   			</div>	
			   		</c:forEach>
			   </c:if>
			</div>
		</div>
	</div>
	<div class="college-params-row">
		<div class="avail-params">
			<div class="col-param-header">
				<s:text name="com.edupath.college.search.availableparam"/>
			</div>
			<ul class="connectedSortable sortable1">
				<c:forEach items="${finalMap['collegeParameters']}" var="availParams">
					<li class="param-item"  value="${availParams.id}" data-param-name="${availParams.paramName}" data-range-param="${availParams.isRangeParam}" data-ismulti-select="${availParams.isMultiSelect}">
						<label class="hidden">${availParams.paramValues.toString()}</label>
				 		${availParams.parameter}
				 		<i class="glyphicon glyphicon-remove param-remove" style="display: none;"></i>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="param-dragtext-row">
			<div>
				<s:text name="com.edupath.college.search.dragdrop.label"/>
			</div>
			<div>
				<i class="glyphicon glyphicon-arrow-left"></i>
			</div>
			<div>
				<i class="glyphicon glyphicon-arrow-right"></i>
			</div>
		</div>
		<div class="selected-params">
			<div class="col-param-header">
				<s:text name="com.edupath.college.search.selectedparam"/>
			</div>
			<ul class="connectedSortable sortable2">
				<c:forEach items="${finalMap['selectedCollegeParams']}" var="selectedParams">
					<li class="param-item"  value="${selectedParams.id}" data-param-name="${selectedParams.paramName}" data-range-param="${selectedParams.isRangeParam}" data-ismulti-select="${availParams.isMultiSelect}">
						<label class="hidden">${selectedParams.paramValues.toString()}</label>
				 		${selectedParams.parameter}
				 		<i class="glyphicon glyphicon-remove param-remove"></i>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="params-values">
			<div class="col-param-header">
			</div>
			<ul class="param_values_ul" id="param_values_ul">
			</ul>
		</div>
	</div>
	<div class="get-college-div colSlectBottom" >
		<button id="get_college_button" class="btn btn-sm get-college-button"><s:text name="com.edupath.college.search.getcollegelist.button"/></button>
	</div>
	
	<form action="${pageContext.request.contextPath}/myapp/getFilterCollegeSelectionAction" name="GetCollegeList" id="GetCollegeList" method="POST">
		<s:hidden name="parentSelectedSidebarMenuId"/>
		<s:hidden name="childSelectedSidebarMenuId"/>
	</form>
</c:if>

<script type="text/javascript">
	$(document).ready(function (){
		$(".select2").select2();
		dragDropInit();
		// On ready building param values if any itam in selected param
		popParamValues($('.sortable2'));
		$('#param_values_ul').css({"min-height" : $('.sortable2').outerHeight()});
		if("${empty finalMap['electiveMap']}" == 'true')
		{
			$('#get_college_button').css('background-color', "#AAAAAA");
			$('#get_college_button').prop('disabled', true);
		}
	});
	
	function dragDropInit()
	{
		$(".sortable1, .sortable2").sortable({
			connectWith : ".connectedSortable",
			cursor: "move",
			scroll: false
		}).disableSelection();
		
		$( ".sortable1" ).on( "sortupdate", function( event, ui ){
			$(this).find(".glyphicon.glyphicon-remove.param-remove").show();
		});
		
		$( ".sortable2" ).on( "sortupdate", function( event, ui ){
			$(this).find(".glyphicon.glyphicon-remove.param-remove").show();
			// On selectable update rebuilding paramvalues
			popParamValues(this);
		});
	}
	
	function popParamValues(ulObj)
	{
		// Removing all param values
		$("#param_values_ul").find("li").remove();
		if($(ulObj).children("li") && $(ulObj).children("li").length > 0)
		{
			// Recreating all the param values
			$.each($(ulObj).children("li"), function(index, liObj){
				if($(this).find("label").html())
				{
					var options = [];
					$.each(JSON.parse($(this).find("label").html()), function(key, value){
						options.push({key : value.actualValue, value : value.displayValue});
					});
					// Createing select list and appending li
					var selectTag = getSelectTag("", options, $(this).val(), $(this).data('paramName'), $(this).data('rangeParam'), $(this).data('ismultiSelect'), "select2 param-value-select");
					appendLiToUl("param_values_ul", selectTag, $(this).width(), $(this).outerHeight());
				}
			});
			$(".select2").select2();
		}
	}
	
	function getSelectTag(id, options, collegeParamId, paramName, rangeParam, isMultiSelect, clazz)
	{
		var selectHtml = "<select id='" + id + "' class='" + clazz + "' data-param-name='" + paramName + "' data-range-param='" + rangeParam + "' data-college-param-id='";
		selectHtml += collegeParamId+"'";
		if(isMultiSelect)
		{
// 			selectHtml += " multiple='true'";
		}
		selectHtml += ">";
		for(var i = 0; i < options.length; i++)
		{
			selectHtml += "<option value='"+options[i].key+"'>" + options[i].value + "</option>";
		}
		return selectHtml;
	}
	
	function appendLiToUl(ulId, liHtml, width, height)
	{
		var finalLiHtml =  "<li class='param-item param-value-li'";
		finalLiHtml += "style='height: " + height + "px;'";
		finalLiHtml += ">"+liHtml+"</li>";
		$('#'+ulId).append(finalLiHtml);
	}
	
	$('#get_college_button').click(function(){
		var paramJSON = {};
		if($('#param_values_ul').find("li").length > 0)
		{
			$.each($('#param_values_ul').find("li"), function(index, liObj){
				CollegeSelectFilter.genGetCollegeValue($(liObj).children("select"), paramJSON);
			});
			saveToLocalStore(paramJSON);
		}
		else
		{
			alert("Please <s:text name='com.edupath.college.search.dragdrop.label'/> atleast one parameter");
		}
	});
	
	function saveToLocalStore(paramJSON)
	{
		EdupathLocalStore.doInsert(EdupathLocalStore.getCollegeSelectName(), '${studentId}', paramJSON);
		$('#GetCollegeList').submit();
	}
	
	$(".glyphicon.glyphicon-remove.param-remove").click(function(){
		var parentObj = $(this).parent();
		$(this).hide();
		$(".connectedSortable.sortable1").append(parentObj);
		$( ".sortable2" ).trigger("sortupdate");
	});
	
	//added 27/03/18
	$(".glyphicon.glyphicon-remove.param-remove").click(function(){
		var parentObj1 = $(this).parent();
		$(this).hide();
		$(".connectedSortable.sortable2").append(parentObj1);
		$( ".sortable1" ).trigger("sortupdate");
});
</script>