<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 

<s:form name="EduPathElectiveForm" id="EduPathElectiveForm" method="post">
	<input type="hidden" name="option1Id" id="option1Id" value="${option1Id}" />	 			
	<input type="hidden" name="option1OccIndusId" id="option1OccIndusId" value="${option1OccIndusId}" />
	<input type="hidden" name="option1EdupathId" id="option1EdupathId" value="${option1EdupathId}" />
	<input type="hidden" name="option1ManStreamId" id="option1ManStreamId" value="${option1ManStreamId}" />
	<input type="hidden" name="option1PreStreamIds" id="option1PreStreamIds" value="${option1PreStreamIds}" />
	<input type="hidden" name="option1ManSubjectId" id="option1ManSubjectId" value="${option1ManSubjectId}" />
	<input type="hidden" name="option1PreSubjectIds" id="option1PreSubjectIds" value="${option1PreSubjectIds}" />
	<input type="hidden" name="option1DegreeStream" id="option1DegreeStream" value="${option1DegreeStream}" />
	
	<input type="hidden" name="option2Id" id="option2Id" value="${option2Id}" />	 			
	<input type="hidden" name="option2OccIndusId" id="option2OccIndusId" value="${option2OccIndusId}" />
	<input type="hidden" name="option2EdupathId" id="option2EdupathId" value="${option2EdupathId}" />
	<input type="hidden" name="option2ManStreamId" id="option2ManStreamId" value="${option2ManStreamId}" />
	<input type="hidden" name="option2PreStreamIds" id="option2PreStreamIds" value="${option2PreStreamIds}" />
	<input type="hidden" name="option2ManSubjectId" id="option2ManSubjectId" value="${option2ManSubjectId}" />
	<input type="hidden" name="option2PreSubjectIds" id="option2PreSubjectIds" value="${option2PreSubjectIds}" />
	<input type="hidden" name="option2DegreeStream" id="option2DegreeStream" value="${option2DegreeStream}" />
	
	<input type="hidden" name="selectedStreamId" id="selectedStreamId" value="${selectedStreamId}" />
	<input type="hidden" name="curselectedStreamId" id="curselectedStreamId" value="${selectedStreamId}" />	
	<input type="hidden" name="selectedSubjectId" id="selectedSubjectId" value="${selectedSubjectId}" />
	
	
	<input type="hidden" id="selectedCombinations" name="selectedCombinations">
</s:form>
<div class="col-md-12">	
	<div class="row">
		<div class="col-md-10">
			<div class="pagetitle">
				<s:text name="com.edupath.viewelective.page.title"></s:text>
			</div>
		</div>
		<div class="col-md-2">
			<div class="occhoverreturnlabel">
				<a href="#" onclick="fnHideElective()">Return</a>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 pull-right" style="display:none">
			City:&nbsp;<s:select cssStyle="width:200px" id="elective_cities" list="electiveData.cities" listKey="id" listValue="name" onchange="fnGetCombination()">
			</s:select>
		</div>
	</div>
	<div class="row">
		<div id="electivedetails" style="margin-top: 20px;">
			<div class="col-md-6">
				<div id="elective_option1">
					<div class="elective_name">
						<div class="row">
							<div class="col-md-2">
								<c:choose>
									<c:when test="${fn:startsWith(option1Id, 'occ')}">
										<img src="${pageContext.request.contextPath}/images/icons/edupath-occupation-icon.gif" class="edupath_img"/>
									</c:when>
									<c:otherwise>
										<img  src="${pageContext.request.contextPath}/images/icons/edupath-industry-icon.gif" class="edupath_img"/>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-10">
								1)&nbsp;${electiveData.option1Name}	
							</div>					
						</div>
					</div>
					<c:if test="${empty option1ManSubjectId or option1ManSubjectId eq '-1'}">
						<c:set var="option1ManSubDisplay" value="none"></c:set>
					</c:if>
					<div class="elective_mand_subject" style="display: ${option1ManSubDisplay}">
						<div class="row">
							<div class="col-md-10 col-md-offset2">
								<s:text name="com.edupath.viewelective.mandatory.subject"></s:text>:&nbsp;
								<c:forEach items="${electiveData.subjectList}" var="subjectObj">
									<c:if test="${subjectObj.id eq  option1ManSubjectId}">
										${subjectObj.name}
									</c:if>
								</c:forEach>
							</div>
						</div>	
					</div>
					<c:if test="${empty option1PreSubjectIds or option1PreSubjectIds eq '-1'}">
						<c:set var="option1PrefSubDisplay" value="none"></c:set>
					</c:if>
					<div class="elective_pref_subject" style="display: ${option1PrefSubDisplay}">
						<div class="row">
							<div class="col-md-10 col-md-offset2">
								<s:text name="com.edupath.viewelective.preferred.subjects"></s:text>:&nbsp;
								<c:forEach var="subjectId" items="${fn:split(option1PreSubjectIds, ',')}">
									<c:forEach items="${electiveData.subjectList}" var="subjectObj">
										<c:if test="${subjectObj.id eq subjectId}">
											${subjectObj.name}, 
										</c:if>
									</c:forEach>
				                </c:forEach>
			             	</div>
		               </div> 
					</div>
				</div>	
			</div>
			<div class="col-md-6">
				<c:set var="option2display" value=""></c:set>
				<c:if test="${empty option2Id}">
					<c:set var="option2display" value="none"></c:set>
				</c:if>
				<div id="elective_option2" style="display: ${option2display};">
					<div class="elective_name">
						<div class="row">
							<div class="col-md-2">
								<c:choose>
									<c:when test="${fn:startsWith(option2Id, 'occ')}">
										<img src="${pageContext.request.contextPath}/images/icons/edupath-occupation-icon.gif" class="edupath_img"/>
									</c:when>
									<c:otherwise>
										<img  src="${pageContext.request.contextPath}/images/icons/edupath-industry-icon.gif" class="edupath_img"/>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-10">
								2)&nbsp;${electiveData.option2Name}	
							</div>					
						</div>
					</div>
					<c:if test="${empty option2ManStreamId or option2ManStreamId eq '-1'}">
						<c:set var="option2mandatory" value="none"></c:set>
					</c:if>
					<div class="elective_mand_stream" style="display: ${option2mandatory};">
						<div class="row">
							<div class="col-md-10 col-md-offset2">
								<s:text name="com.edupath.viewelective.madatory.stream"></s:text>:&nbsp;
								<c:forEach items="${electiveData.streams}" var="streamObj">
									<c:if test="${streamObj.id eq  option2ManStreamId}">
										${streamObj.name}
									</c:if>
								</c:forEach>
							</div>
						</div>	
					</div>
					<c:if test="${empty option2PreStreamIds or option2PreStreamIds eq '-1'}">
						<c:set var="option2PreferredStream" value="none"></c:set>
					</c:if>
					<div class="elective_pref_stream" style="display: ${option2PreferredStream};">
						<div class="row">
							<div class="col-md-10 col-md-offset2">				
								<s:text name="com.edupath.viewelective.preferred.stream"></s:text>:
								<c:forEach var="streamId" items="${fn:split(option2PreStreamIds, ',')}">
									<c:forEach items="${electiveData.streams}" var="streamObj">
										<c:if test="${streamObj.id eq streamId}">
											${streamObj.name}, 
										</c:if>
									</c:forEach>
				                </c:forEach>
		                	</div>
		                </div>	
					</div>
					<c:if test="${empty option2ManSubjectId or option2ManSubjectId eq '-1'}">
						<c:set var="option2ManSubjectDisplay" value="none"></c:set>
					</c:if>
					<div class="elective_mand_subject" style="display: ${option2ManSubjectDisplay}">
						<div class="row">
							<div class="col-md-10 col-md-offset2">
								<s:text name="com.edupath.viewelective.mandatory.subject"></s:text>:&nbsp;
								<c:forEach items="${electiveData.subjectList}" var="subjectObj">
									<c:if test="${subjectObj.id eq  option2ManSubjectId}">
										${subjectObj.name}
									</c:if>
								</c:forEach>
							</div>
						</div>	
					</div>
					<c:if test="${empty option2PreSubjectIds or option2PreSubjectIds eq '-1'}">
						<c:set var="option2PrefSubDisplay" value="none"></c:set>
					</c:if>
					<div class="elective_pref_subject" style="display: ${option2PrefSubDisplay}">
						<div class="row">
							<div class="col-md-10 col-md-offset2">					
								<s:text name="com.edupath.viewelective.preferred.subjects"></s:text>:&nbsp;
								<c:forEach var="subjectId" items="${fn:split(option2PreSubjectIds, ',')}">
									<c:forEach items="${electiveData.subjectList}" var="subjectObj">
										<c:if test="${subjectObj.id eq subjectId}">
											${subjectObj.name}, 
										</c:if>
									</c:forEach>
				                </c:forEach>
		                	</div>
		                </div>	
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="elective_selectStream" class="elective_selectStream">
				<span class="selectstream">
					<s:text name="com.edupath.viewelective.selectstream"></s:text>:&nbsp;
				</span>	 
				<c:choose>
					<c:when test="${not empty selectedStreamId and selectedStreamId ne '-1'}">
						<c:forEach items="${electiveData.streams}" var="streamObj">
							<c:if test="${streamObj.id eq  selectedStreamId}">
								${streamObj.name}
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<select id="streamList" style="width:200px">
							<option value="-1">Select</option>
							<c:forEach items="${electiveData.noneAnyStream}" var="stream">
								<option value="${stream.id}">
									${stream.name}
									<c:forEach var="streamId" items="${fn:split(option1PreStreamIds, ',')}">
										<c:if test="${stream.id eq streamId}">
											(preferred)
										</c:if>
				                	</c:forEach>
								</option>
							</c:forEach>
						</select>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<s:text name="com.edupath.viewelective.selectelectivecombination"></s:text>
			</div>
			<div id="elective_combination">
				
			</div>			
		</div>	
	</div>	
	<div class="row">
		<div class="col-md-12" style="margin-bottom:40px">		
			<div id="addToEduPath">
				<c:if test="${allowSelect eq true}">
					<button class="btn btn_action veweleFon" onclick="fnAddToEdupath();" style="margin-top: 20px;">
						<s:text name="com.edupath.viewelective.addedupath"></s:text>
					</button>
				</c:if>
			</div>
		</div>
	</div>		
</div>

<script>
	var combination = "";
	
	$(document).ready(function(){
		$("#elective_cities").val(${electiveData.currentCity})
		$("#elective_cities").select2({theme: "classic"});
		fnGetCombination();
	});
	
	//"combi_'+$("#curselectedStreamId").val()+"_"+val.id+'_'+val.noOfCollege+'"
	function fnCombinationChanged(checkboxObj)
	{
		var combinationId = $(checkboxObj).attr("id");
		var combId = $(checkboxObj).val();
		var splitData = combinationId.split("_");
		if($(checkboxObj).is(':checked'))
		{
			if($("input[name=combination]:checked").length <= 3)
			{
				if(combination.length == 0)
				{
					combination = $.trim(splitData[2])+":"+$.trim(splitData[3]);
				}
				else
				{
					combination += ","+$.trim(splitData[2])+":"+$.trim(splitData[3]);
				}					
			}
			else
			{
				$(checkboxObj).prop('checked', false);
				alert("Max of 3 Elective combination can be selected");
			}	
		}
		else
		{
			if(combination.length > 0)
			{
				var combinationdata = combination.split(",");
				var remcombdata = [];
				for(var i=0;i<combinationdata.length;i++){
					combdata = combinationdata[i].split(":");
					if($.trim(combId) != $.trim(combdata[0]))
					{
						remcombdata.push(combinationdata[i])
					}
				}
				combination = "";
				for(var i=0;i<remcombdata.length;i++){
					if(i > 0)
					{
						combination += ",";
					}
					combination += remcombdata[i];
				}
			}
		}
	}
	
	$("#streamList").change(function()
		{
			if($.trim($("#curselectedStreamId").val()) == "" || $.trim($("#curselectedStreamId").val()) == "-1")
			{
				$("#curselectedStreamId").val($("#streamList").val());
				fnGetCombination();				
			}
			else
			{
				var status = confirm("By changing Stream previously selected Elective combination will be removed");
				if(status)
				{
					combination = "";
					$("#curselectedStreamId").val($("#streamList").val());
					fnGetCombination();
				}
				else
				{
					$("#streamList").val($("#curselectedStreamId").val());
				}
			}	
		}
	)
	
	function fnGetCombination()
	{
		
		streamId = $("#curselectedStreamId").val()
		cityId = $("#elective_cities").val();
		if(streamId == '-1' || streamId == "")
		{
			
			//fnOnCityIdChangefilter(cityId);
			$("#elective_combination").html('');
			return;
		}
		//fnOnCityIdChangefilter(cityId);
		
		
		$("#elective_combination").html('');
		url = '${pageContext.request.contextPath}/myapp/subjectCombinationDetailsElective';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		data:({ 
	 			"streamId":streamId, "cityId":cityId,"selectedSubjectId":$("#selectedSubjectId").val() 
	 		}),
	 		dataType:"json",
	 		success: function(resp)
	 		{
	 			fnDisplayCombinationData(resp.combinationData)
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			return false;
	 		}
	 	});	
	}
	
	function fnDisplayCombinationData(combinationData)
	{
		$("#elective_combination").html('');
		htmlcontent = '';
		//option1ManSubjectId, option1PreSubjectIds,option2ManSubjectId, option2PreSubjectIds, allowSelect,selectedSubjectId
		$("#elective_combination").removeClass("checkbox");
		
		$(combinationData).each(function(i, val){
			htmlcontent += '<div class="checkbox-inline checkbox-primary custom_ch_box">';	
			if('${allowSelect}' == 'true'){
				$("#elective_combination").addClass("checkbox");
				var checked = "";
				var combinationdata = combination.split(",");
				for(i=0;i<combinationdata.length;i++){
					combdata = combinationdata[i].split(":");
					if(val.id == combdata[0])
					{
						checked = "checked";
					}	
				};
				htmlcontent += '<input type="checkbox" '+checked+' name="combination" value="'+val.id+'" id="combi_'+$("#curselectedStreamId").val()+"_"+val.id+'_'+val.noOfCollege+'">'
			}
				
			var bold, color;
			val.subjectIds.split(",").forEach(function(subjectId) {
					$("#option1PreSubjectIds").val().split(",").forEach(function(preferredSubject){
						if($.trim(subjectId) == $.trim(preferredSubject)){
							bold = true;
							color = '#000000';
							return;
						};
					});
					if(!bold){
						$("#option2ManSubjectId").val().split(",").forEach(function(preferredSubject){
								if($.trim(subjectId) == $.trim(preferredSubject)){
									bold = true;
									color = '#3e6293';
									return;
								};
							});						
					}
					if(!bold){
						$("#option2PreSubjectIds").val().split(",").forEach(function(preferredSubject){
							if($.trim(subjectId) == $.trim(preferredSubject)){
								bold = true;
								color = '#82da97';
								return;
							};
						});
					}
			});
			
			htmlcontent += '<label class="qusetion_txt_label" for="combi_'+$("#curselectedStreamId").val()+"_"+val.id+'_'+val.noOfCollege+'">';
			if(bold)
			{
				htmlcontent += '<span style="color:'+color+'">';
				htmlcontent += '<b>';
			}
			
			htmlcontent += val.name+' ('+val.noOfCollege+')';
			
			if(bold)
			{
				htmlcontent += '</b></span>';
			}
			htmlcontent += '</label></div>'; 
		});
		
		$("#elective_combination").html(htmlcontent);
		$("input[name=combination]").change(function(){
			fnCombinationChanged($(this))
		});
	}
	
	function fnAddToEdupath()
	{
		var status = true;
		if(combination.length == 0)
		{
			alert("Please select the Combination electives")
			return;
		}	
		else if(combination.split(",").length < 3)
		{
			status = confirm("Are you sure you want to add the EduPath to with only "+combination.split(",").length+" number of electives selected");
		}
		
		if(${option1ManStreamId} == "-1" && ${option2ManStreamId} != "-1")
		{
			var id = $("#curselectedStreamId").val();
			if(${option2ManStreamId} != id)
			{
				status = confirm("This will not work for option 2, Option 2 will not be added to Edupath, Do you want to continue?");
			}	
		}

		if(status)
		{
			$("#selectedStreamId").val($("#curselectedStreamId").val())
			$("#selectedCombinations").val(combination)
			var forms = document.EduPathElectiveForm;
			forms.action = '${pageContext.request.contextPath}/myapp/addTOCartElective';
			var options = { 
			        success:       fnShowStatus
			}; 
			$("#EduPathElectiveForm").ajaxSubmit(options);			
		}
	}
	
	function fnShowStatus(responseText, statusText, xhr, $form)
	{
		if(responseText.status == "success")
		{
			document.getElementById("cityId").disabled = true;
			alert("Added successfully");
			fnHideElective();
		}
		else if(responseText.status == "error")
		{
			alert("EudPath already exists please delete before adding new one")
		}
		else
		{
			alert("Error while storing the EduPath please try again")
		}
	}
	
	function fnHideElective()
	{
		$("#eduPathDiv").show();
		$("#occCourseDiv").hide();
		$("#entrExamDiv").hide();
		$("#integCourseDiv").hide();
		$("#viewelectives").hide();
	}
	
</script>

<style>
.custom_ch_box
{
 height: 30px;
}
</style>