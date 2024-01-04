<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:form name="ReviewElectiveForm" method="post" id="ReviewElectiveForm" >
<s:hidden name="stdId" id="stdId" />
<s:hidden name="shortListIds" id="shortListIds"/> 
<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
	<div style="word-wrap: break-word;" class="edupath-padding">
		<div class="rows">
			<div class="col-md-12">
				<div id="contentdiv">

					<div class="pagetitle">
						<s:text name="com.lodestar.edupath.navigation.tab.reviewelectives"></s:text>
					</div>
					<div class="sectionheading">
						<s:text name="com.edupath.reviewelective.header.lable"></s:text>
					</div>
					<c:set value="1" var="count"></c:set>
					<div class="shortList_div" style="margin-top: 30px; margin-bottom: 10px; padding-left: 15px">
						<c:forEach items="${electiveMap.EDUPATHSHORTLIST}" var="edupathShortList">
							<div style="margin-bottom: 10px;">
								
								<c:if test="${null != edupathShortList.industryName}">
									<img alt="" style="height: 45px; width: 50px;" src="${pageContext.request.contextPath}/images/icons/edupath-industry-icon.gif">&nbsp;&nbsp;
									
									<span style=" font-size: 12pt;  /* font-weight: 600; */ font-style: normal; line-height: 10pt; letter-spacing: 0.03em;  color: black;">
										${pageScope.count})&nbsp;${edupathShortList.industryName}
									</span>&nbsp;
								</c:if>
								<c:if test="${null != edupathShortList.occupationName}">
									<img alt="" style="height: 45px; width: 50px;" src="${pageContext.request.contextPath}/images/icons/edupath-occupation-icon.gif">&nbsp;&nbsp;
									<span style=" font-size: 12pt;  /* font-weight: 600; */ font-style: normal; line-height: 10pt; letter-spacing: 0.03em;  color: black;">
										${pageScope.count})&nbsp;${edupathShortList.occupationName}
									</span>&nbsp;
								</c:if>
							</div>
							<c:set var="count" value="${count + 1}"></c:set>
						</c:forEach>
					</div>
					<c:if test="${null != electiveMap.SUBJECT}">
						<div class="sbjct_div" style="width: 100%; height: auto;margin-top: 30px;padding-left: 15px;">
							<div style="width: 100%;font-size: 11pt;font-style: normal;font-weight: 600;">
								Available Course reports
							</div>
							<div class="txt-field" style="padding-top:10px;padding-left: 35px;width: 100%;margin-top: 10px">
							<c:set var="start" value="1"></c:set>
							<c:set var="end" value="1"></c:set>
							<c:set var="length" value="${fn:length(electiveMap.SUBJECT)}"></c:set>
							<c:forEach items="${electiveMap.SUBJECT}" var="subject" varStatus="count">
								<c:if test="${start eq end }" >
									<div class="row" style="margin-bottom: 20px">
									<c:set var="end" value="${end + 6}"></c:set>
								</c:if>
									<div class="col-md-2" style="text-align: center;margin: auto;">
										<div style="cursor: pointer;" onclick="fnGetSubjectDoc(${subject.id});"><img alt="" style="height: 75px;" src="${pageContext.request.contextPath}/images/icons/Note-icon.png"></div>
										<div style="font-family: inherit; font-size: 10pt;color:black;">${subject.name}</div>
									</div>
								<c:if test="${start eq end - 1 || length eq start }">
									</div>
								</c:if>
								<c:set var="start" value="${start + 1}"></c:set>
							</c:forEach>
							
							<%-- <c:forEach items="${electiveMap.SUBJECT}" var="subject">
								<div style="width: 10%; float: left;">
									<div><img alt="" style="height: 75px;" src="${pageContext.request.contextPath}/images/icons/Note-icon.png"></div>
									<div>${subject.name}</div>
								</div>
							</c:forEach> --%>
							</div>
						</div>
					</c:if>
					<div style="margin-top: 10px; margin-bottom: 10px; padding-left: 15px">
						<span style="font-size: 11pt; font-weight: 600; font-style: normal; line-height: 10pt; letter-spacing: 0.03em;  color: black;">
							Selected ${electiveMap.STREAM.edulevelName} Streams: ${electiveMap.STREAM.streamName}
						</span>
					</div>
					<div class="combination_shortlist" style="margin-top: 30px; margin-bottom: 10px; margin-left: 30px;">
						<div class="row row_custum" >
							<div class="col-md-5" style="border-style: solid; border-width: 1px;height: 45%;">
								<div class="row row_custum" style="padding-left: 20px;width: 100%; height: 20%">
									<p class="pathWayTitle">Electives</p>
								</div>
								<div class="row" style="padding-left: 20px; margin-top: 0px;height: 80%; overflow:auto; " id="items">
								<c:if test="${null eq electiveMap.COMBINATION or empty electiveMap.COMBINATION}">
									<div style="text-align: center;padding-top: 10px;font-size: 9pt;">
										No electives to show
									</div>
								</c:if>
									<c:forEach items="${electiveMap.COMBINATION}" var="combination" varStatus="count">
										<c:set var="labelName" value="${combination.name}"></c:set>
										 <div class="radio radio-success">
							               <input id="radio_8_${count.index}" name="strength" data-serial="${count.index + 1}" value="${labelName}" data-index="${count.index}" type="radio">
							               <label for="radio_8_${count.index}" class="qusetion_txt_label">
							               		${labelName}&nbsp;(&nbsp;${null != combination.noOfCollege ? combination.noOfCollege : "-"}&nbsp;)
							               </label>
							               <input type="hidden" name="id" id="id" value="${combination.id}">
							             </div>
									</c:forEach>
								</div>
							</div>
							<div class="col-md-7" style="min-height: 100px;">
								<div class="row row_custum div col-xs-12" style="margin-top: 75px; padding-left: 20px;">
									<button class="btn btn_action "  id="up" style="width: 129px;"><span><s:text name="com.edupath.my.shortlist.move.up"></s:text></span></button>
								</div>
								<div class="row row_custum col-xs-12" style="padding-left: 20px;margin-top: 10px;">
									<button class="btn btn_action" id="down"><span><s:text name="com.edupath.my.shortlist.move.down"></s:text></span></button>
								</div>
							</div>
						</div>
						<div class="row">
							   <div class="row action-div action-div1">
						     		<button class="btn btn_action" onclick="fnDiscard();"><span><s:text name="com.edupath.my.shortlist.discard"></s:text></span></button>
						     		<button class="btn btn_action save"  onclick="fnSave();" style="margin-left: 10px;"><span><s:text name="com.edupath.my.shortlist.save"></s:text></span></button>
					  		   </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script>
$(document).ready(function(){
    var selected=0;
   
     var itemlist = $('#items');
    var len=$(itemlist).children().length; 
    
    $("#items div").click(function(){
    	isSelected= $(this).find('input').prop('checked');
    	if (isSelected) 
    	{
	        selected= $(this).index();
		}
    });
      

     $("#up").click(function(e){
       e.preventDefault();
       if(selected>0)
       	{
       		jQuery($(itemlist).children().eq(selected-1)).before(jQuery($(itemlist).children().eq(selected)));
         	selected=selected-1;
     	}
    });

     $("#down").click(function(e){
         e.preventDefault();
        if(selected < len-1)
        {
        	jQuery($(itemlist).children().eq(selected+1)).after(jQuery($(itemlist).children().eq(selected)));
         	selected=selected+1;
     	}
    });
   
   
    
    
});

var form =document.ReviewElectiveForm; 
var newWindow;	
function fnSave(){
	var idArray = [];
	$('#items div input:hidden').each(function() {
		idArray.push($(this).val());
	});
	$("#shortListIds").val(idArray.toString());
	form.action="${pageContext.request.contextPath}/myapp/saveCombinationOrderReviewElectivesAction";
	form.submit();
}

function fnDiscard(){
	
	form.action="${pageContext.request.contextPath}/myapp/ReviewElectivesAction";
	form.submit();
}

function fnGetSubjectDoc(subjectId)
{
	fnGetContent("subjectId="+subjectId);
}
function fnGetContent(dataStr)
{
	fnShowReadingPage();
	fnLoadContent(dataStr);
}
function fnShowReadingPage()
{
	if(newWindow!=null)
    {
         if(!newWindow.closed)
         {
        	 newWindow.close();
         }
    }
	
	newWindow = window.open("${pageContext.request.contextPath}/pages/stuinduoccchoice/ReadingPage.jsp","ReadingPane"
			,"height=700px, width=1100px, left=0, top=0, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no",true);
	newWindow.focus();
}
var datastr;
var docType;
function fnLoadContent(dataStr)
{
	datastr = dataStr;
	docType = "SUBJECT";
	newWindow.onload = function()
    {
		//newWindow.fnGetDocumentURLNLoad(true, dataStr);
    }
}
</script>