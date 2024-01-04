<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:form name="ExploreMoreOccupationForm" method="post" id="ExploreMoreOccupationForm" >
<s:hidden name="studentId" id="studentId" />
<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
<s:hidden name="selectedAnswer" id="selectedAnswer"/>
<c:set var="questionNo" value="1"></c:set>
<div style="word-wrap: break-word;" class="edupath-padding">
	<div class="rows">
		<div class="col-md-12">	
			<div id="contentdiv">
					
				<c:choose>
					<c:when test="${actiontype eq 'searchResult' }">
						<jsp:include page="/pages/exploremoreoccupation/ExploreMoreOccSearchResultScreen.jsp">
							<jsp:param value="" name="arg0"/>
						</jsp:include>
					</c:when>
					<c:otherwise>
					
				<div class="pagetitle">
					<s:text name="com.lodestar.edupath.navigation.tab.exploremoreoccupations"></s:text>
				</div>
						<div class="sectionheading">
							<s:text name="com.edupath.exploremoreoccupations.leble.searchoccupation"></s:text>
						</div>
						<div class="sectionheading">
							<span><s:text name="com.edupath.exploremoreoccupations.leble.note"></s:text>&nbsp;<s:text name="com.edupath.exploremoreoccupations.leble.notedescription"></s:text></span>
						</div>
						<div>
							<input type="hidden" id="hidden_answer_${questionNo}" class="searched_Answer" value="">
							<div class="questiondiv">
								<%-- <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
								<i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i> --%>
								<span class="questionspan">Search by subject (Max of 2)</span>
								<div class="pull-right">
									<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls arrow_question_${questionNo}" data-question="${questionNo}"></i>
									<input type="hidden" id="toggle_${questionNo}" value="YES"/>
								</div>
							</div>
							<div id="answer_${questionNo}">
								<div class="txt-field " style="padding-left: 40px !important;">
									<%
										Map subjectMap = new LinkedHashMap();
										subjectMap.put("Physics","Physics");
										subjectMap.put("Chemistry","Chemistry");
										subjectMap.put("Mathematics","Math");
										subjectMap.put("Biology","Biology");
										subjectMap.put("Home Science","Home");
										subjectMap.put("Economics","Economics");
										subjectMap.put("English","English");
										subjectMap.put("Geography","Geography");
										subjectMap.put("History","History");
										subjectMap.put("Environmental Science","Environmental");
										subjectMap.put("Technical drawing","drawing");
										subjectMap.put("Art","Art");
										subjectMap.put("Psychology","Psychology");
										subjectMap.put("Computer Science","Computer");
										subjectMap.put("Electronics","Electronics");
										
										request.setAttribute("subjectMap", subjectMap);
									%>
									<div class="row">
										<c:forEach var="entry" items="${subjectMap}" varStatus="count">
											<c:if test="${count.index gt 0 and count.index %4 eq 0}">
												</div>
												<div class="row">
											</c:if>
								  			<div class="col-md-3 custom-box-ft-subject">
												<div class="checkbox checkbox-primary">
													<input id="checkbox_answer_${questionNo}_${count.index}" name="favSubject"
															data-serial="${count.index}" data-question="${questionNo}" value="${entry.value}"
															class="auto_save styled selected_checkbox_subject checkbox_answer_${questionNo}" type="checkbox"> <label
															for="checkbox_answer_${questionNo}_${count.index}"
															class="Manual_Education_answer_txt_label">${fn:trim(entry.key)}</label>	
												</div>
											</div>
										</c:forEach>
									</div>
									<div>
										<span class="error-block"></span>
									</div>
								</div>
							</div>
						</div>
						<c:set var="questionNo" value="${questionNo + 1}"></c:set>
					<div>
						<c:forEach items="${categoryMap}" var="category">
							<input type="hidden" id="hidden_answer_${questionNo}" class="searched_Answer" value="">
							<div class="questiondiv" style="">
								<%--  <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
								 <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i> --%>
									<span class="questionspan">
										<c:if test="${category.key eq 'Interests' }">
											Search by interests
										</c:if>
									</span>
								<div class="pull-right">
									<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls arrow_question_${questionNo}" data-question="${questionNo}"></i>
									<input type="hidden" id="toggle_${questionNo}" value="YES"/>
								</div>
							</div>
							<div id="answer_${questionNo}" class="answerDiv" style="padding-left: 40px !important;">
								<div class="txt-field">
									<c:set var="start" value="1"></c:set>
									<c:set var="end" value="1"></c:set>
									<c:set var="length" value="${fn:length(category.value)}"></c:set>
									<c:forEach items="${category.value}" var="subcategory" varStatus="count">
										<c:if test="${start eq end }" >
											<div class="row">
											<c:set var="end" value="${end + 4}"></c:set>
										</c:if>
											<div class="col-md-3 custom-box-ft-subject" style="padding-top: 10px">
												<div class="checkbox checkbox-primary" style="padding-left: 0px">
													<input type="checkbox" id="subCategory_${subcategory.value[0].subCategoryDTO.id}" name="subCategory" data-subCategoryId="${subcategory.value[0].subCategoryDTO.id}"
														data-serial="${start}" value="${subcategory.key}"
														class="auto_save styled subCategory_check subCategory_get_${questionNo}" data-question="${questionNo}"> <label
														for="subCategory_${subcategory.value[0].subCategoryDTO.id}" class="Manual_Individual_subCategory_txt_label">${fn:trim(subcategory.key)}</label>
												</div>
												<div class="txt-field  " style="padding-left: 10px">
													<c:forEach items="${subcategory.value}" var="area">
														<div class="col-md-12" style="  padding-left: 0px !important;">
															<div class="checkbox checkbox-primary" style="padding-left: 0px;" >
																<input type="checkbox" id="area_${area.id}" name="area" data-subCategoryId="${subcategory.value[0].subCategoryDTO.id}" data-areaId="${area.id}"
																	data-serial="${area.id}" value="${area.name}"
																	class="auto_save styled area_check subCategory_${subcategory.value[0].subCategoryDTO.id}" > <label
																	for="area_${area.id}" class="Manual_Individual_area_txt_label subCategory_${start}">${fn:trim(area.name)}</label>
															</div>
														</div>
													</c:forEach>
												</div>
											</div>
										<c:if test="${start eq end - 1 || length eq start }">
											</div>
										</c:if>
										<c:set var="start" value="${start + 1}"></c:set>
									</c:forEach>
								</div>
							</div>
							<c:set var="questionNo" value="${questionNo + 1}"></c:set>
						</c:forEach>
					</div>
					<div>
						<input type="hidden" id="hidden_answer_${questionNo}" class="searched_Answer" value="">
							<div class="questiondiv">
								<%-- <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
								<i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i> --%>
								<span class="questionspan">Search by keyword</span>
								<div class="pull-right">
									<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls arrow_question_${questionNo}" data-question="${questionNo}"></i>
									<input type="hidden" id="toggle_${questionNo}" value="YES"/>
								</div>
							</div>
							<div id="answer_${questionNo}">
								<div class="col-md-10 txt-field " style="padding-left: 40px !important;">
									<s:textarea name="aspiration" id="aspiration" class="form-control" data-question="${questionNo}"/>
								</div>
							</div>
						</div>
						<c:set var="questionNo" value="${questionNo + 1}"></c:set>
					</div>
					<div class="col-md-12 action-div" style="padding-left: 0px !important; padding-top: 30px !important;">
						 <button class="btn btn_action" onclick="return fnSearch();"><span class="next-spn"><s:text name="com.edupath.common.search"></s:text></span></button>
					</div>
				</c:otherwise>
				</c:choose>
			
			</div>
			<div id="occupationdetails" style="display: none;">
				
			</div>
<!-- 		</div>
	</div>	
</div> -->
</s:form>
<script type="text/javascript">
var form = document.ExploreMoreOccupationForm;
	var fav_question_count = 0;
	
	/*  $('.btn_qt').click(function()
	{
		var answer = $(this).data('answer');
		var slNo = $(this).data('question');
		$('#hidden_answer_' + slNo).val(answer);
		if (answer == 'YES') {
			$('#no_' + slNo).not(this).removeClass('btn_no');
			$('#yes_' + slNo).addClass('btn_yes');
		} else {
			$('#yes_' + slNo).not(this).removeClass('btn_yes');
			$('#no_' + slNo).addClass('btn_no');
		}
	}); */
	
/* $('.fa-star-o').click(function(){
	if(fav_question_count >= 3)
	{
		alert("Max 3 questions are allowed to be prefered");
		return;
	}
		
	$("#"+this.id).hide();
	$("#"+this.id+"_Selectd").show();
	++fav_question_count;
});

$('.fa-star').click(function(){
	$("#"+this.id).hide();
	$("#fav_question_"+$(this).data("question")).show();
	--fav_question_count;
}); */

$('.common_cls').click(function(){
	var id = $(this).data("question");
	var toggle = $('#toggle_'+id).val();;

	if(toggle == 'NO')
	{
		$(this).addClass('fa-arrow-circle-o-up');
		$(this).removeClass('fa-arrow-circle-o-down');
		$('#toggle_'+id).val('YES');
	}
	else if(toggle == 'YES')
	{
		$(this).removeClass('fa-arrow-circle-o-up');
		$(this).addClass('fa-arrow-circle-o-down');
		$('#toggle_'+id).val('NO');
	}
	
	$("#answer_"+$(this).data("question")).slideToggle();
});

$('.selected_checkbox_subject').change(function(){
	$('.searched_Answer').val("");
	var checkbox_count = 0;
	var isExceedMax = false;
	$('.selected_checkbox_subject').each(function(){
		if($("#"+this.id).is(":checked"))
			{
				++checkbox_count;
				if(checkbox_count > 2)
					{
						isExceedMax = true;
						return;
					}
			}
	});
	if(isExceedMax)
		{
		alert("Max 2 answer are allowed for subject");
		$("#"+this.id).attr("checked", false);
		}
	var value=$('.selected_checkbox_subject:checked').map(function () {
		  return this.value;
	}).get();
	$('#hidden_answer_' + $(this).data("question")).val(value);
	
	$('.subCategory_check').prop("checked", false);
	$('.area_check').prop("checked", false);
	$('.area_check').prop("disabled", false);
	$('#aspiration').val("");
});

$('.area_check').change(function(){
	$('.searched_Answer').val("");
	$('.selected_checkbox_subject').prop("checked", false);
	$('#aspiration').val("");
});

$('.subCategory_check').change(function(){
	
	if($("#"+this.id).is(":checked"))
		{
			$('.'+this.id).prop("checked", true);
			$('.'+this.id).prop("disabled", true);
		}
	else
		{
			$('.'+this.id).prop("checked", false);
			$('.'+this.id).prop("disabled", false);
		}
	$('.searched_Answer').val("");
	$('.selected_checkbox_subject').prop("checked", false);
	$('#aspiration').val("");
});

$('#aspiration').keyup(function(){
	$('.subCategory_check').prop("checked", false);
	$('.area_check').prop("checked", false);
	$('.area_check').prop("disabled", false);
	$('.selected_checkbox_subject').prop("checked", false);
	$('.searched_Answer').val("");
	$('#hidden_answer_' + $(this).data("question")).val($('#aspiration').val());
});

function fnSearch(){
		
	 var finalJsonArray = []; 
	 var JsonObject = new Object();
	 for(var count = 1; count <= '${questionNo}'; count++)
	 {
		 var answer = $('#hidden_answer_'+count).val();
		 if($( "input[type=checkbox][name=subCategory]" ).hasClass( 'subCategory_get_'+count))
			 {
				$('.subCategory_get_'+count).each(function(){
					$('.'+this.id).each(function(){
						if(this.id != "")
							{
							if($("#"+this.id).is(":checked"))
								{
									if(answer != "")
									{
										answer = answer  + ",";
									}
									answer = answer + $(this).attr("data-areaId");
								}
							}
					});
				});
			 }
		 if(null != answer && answer != '' && answer != undefined && answer != -1)
		 {
			 var json = new Object();
			     json.slNo = count;
			     json.correctAnswer = answer;
			 finalJsonArray.push(json);
		 }	 
	 }
	if(null == JSON.stringify(finalJsonArray) || finalJsonArray.length == 0)
		{
			alert("please select at least one value for search result");
			return false;
		}
	JsonObject.selectedAnswer=finalJsonArray;
	$("#selectedAnswer").val(JSON.stringify(JsonObject));
	 form.action = '${pageContext.request.contextPath}/myapp/getSearchResultExploremoreoccupationsAction';
	 form.submit();
}
 function fnResetSearch(){
	 var form = document.ExploreMoreOccupationForm;
	 form.action = '${pageContext.request.contextPath}/myapp/ExploremoreoccupationsAction';
	 form.submit();
 }
</script>
<style type="text/css">
.common_cls{
	cursor: pointer;
}
</style>