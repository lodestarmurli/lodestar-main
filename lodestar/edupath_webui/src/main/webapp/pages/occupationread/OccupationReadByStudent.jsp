<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<div style="word-wrap: break-word;" class="edupath-padding">
		<div class="rows">
			<div class="col-md-12">
				<div id="contentdiv">

					<div class="pagetitle">
						<s:text name="Occupation Reports Read"></s:text>&nbsp;&nbsp;<span style="font-size:18px;color:black;">(Total&nbsp;${reportreadbystudentbean.getTotalNoOfOccupation()}&nbsp;Reports)</span>
					</div>
					
					<c:set value="1" var="count"></c:set>
					<div class="shortList_div" style="margin-top: 30px; margin-bottom: 10px; padding-left: 15px">
					
					<ul class="col-lg-12">
					
						<c:forEach items="${reportreadbystudentbean.getOcupationsList()}" var="occupationName">
							<c:if test="${occupationName.name ne null and not empty occupationName.name}">
							
					       <li style="line-height: 30px;">${count}.&nbsp;&nbsp;${occupationName.name}.</li>
							
							<c:set var="count" value="${count + 1}"></c:set>
							
							</c:if>
						</c:forEach>
						
						</ul>
						
					</div>
					<br/><br/>
					<div class="pagetitle">
						<s:text name="Industry Reports Read"></s:text>&nbsp;&nbsp;<span style="font-size:18px;color:black;">(Total&nbsp;${reportreadbystudentbean.getTotalNoOfIndustry()}&nbsp;Reports)</span>
					</div>
					
						<c:set value="1" var="count"></c:set>
					<div class="shortList_div" style="margin-top: 30px; margin-bottom: 10px; padding-left: 15px">
					
					<ul class="col-lg-12">
					
						<c:forEach items="${reportreadbystudentbean.getOcupationsList()}" var="occupationName">
							
							<c:if test="${occupationName.industryName ne null and not empty occupationName.industryName}">
					       <li style="line-height: 30px;">${count}.&nbsp;&nbsp;${occupationName.industryName}.</li>
							
							<c:set var="count" value="${count + 1}"></c:set>
							
							</c:if>
						</c:forEach>
						
						</ul>
						
					</div>		
							
						
				</div>
			</div>
		</div>
	</div>

<script>
$(document).ready(function(){
   
   
    
    
});


</script>