<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
<c:when test="${ null ne studentList && not empty studentList}">
   <table class="table table-striped table-hover table-bordered table_action" id="sample_editable_1">
		<thead>
			<tr style="width: 100%;">
			   <th width="10%;"><s:text name="Date"></s:text></th>
				<th width="10%;"><s:text name="com.edupath.viewfeedback.summary.student.name"></s:text></th>
				<th width="10%;"><s:text name="com.edupath.viewfeedback.summary.student.loginid"></s:text></th>
				<c:if test="${admin}">
					<th width="10%"><s:text name="com.edupath.viewfeedback.summary.facilitator.name"></s:text></th>
					<th width="10%"><s:text name="Student Class"></s:text></th>
				</c:if>
				<th><s:text name="com.edupath.viewfeedback.summary.question"></s:text></th>
				<th width="20%;"><s:text name="com.edupath.viewfeedback.summary.response"></s:text></th>
			</tr>
		</thead>
		
		<tbody>
		 <c:forEach items="${studentList}" var="studentDetails">
			<tr>
			<td style="width:10%;">
				    ${studentDetails.gsinputdate}
				</td>
			
				<td style="width:10%;">
				    ${utils:replaceXMLEntities(studentDetails.studentname)}
				</td>
				<td style="width:10%;">
					${utils:replaceXMLEntities(studentDetails.ldid)}
				</td>
				<c:if test="${admin}">
					<td style="width:10%;">
					    ${utils:replaceXMLEntities(studentDetails.gsname)}
					</td>
					<td style="width:10%;">
						${utils:replaceXMLEntities(studentDetails.studentclass)}
					</td>
				</c:if>
				<td>
				   <c:if test="${studentDetails.questionno == 1}">
				Could you please mention the child's interests and hobbies
				    </c:if>
				    
				    
				    <c:if test="${studentDetails.questionno == 2}">
				Have you discussed his/her academic performance
				    </c:if>
				    
				    
				    <c:if test="${studentDetails.questionno == 3}">
				Can you describe the nature of the student briefly from whatever you have understood in your limited interaction
				    </c:if>
				    
				    
				    <c:if test="${studentDetails.questionno == 4}">
				Have the interest and abilities test results been discussed
				    </c:if>
				    
				    
				    <c:if test="${studentDetails.questionno == 41}">
			What was the response of the student / parent about the same
				    </c:if>
				    
				    
				    <c:if test="${studentDetails.questionno == 5}">
				What according to you is the child's theme
				    </c:if>
				    
				    
				    <c:if test="${studentDetails.questionno == 6}">
				Have the expectations been set with the student and the parent
				    </c:if>
				    
				    
				    <c:if test="${studentDetails.questionno == 7}">
				Have you briefed about the IRs and ORs
				    </c:if>
				    
				    
				    
				    <c:if test="${studentDetails.questionno == 8}">
				What were the parameters considered to create the occupation wish list for the child
				    </c:if>
				    
				    
				    
				    <c:if test="${studentDetails.questionno == 9}">
			Did the parent / student has any suggestion on the WOJs
				    </c:if>
				    
				    
				    
				    <c:if test="${studentDetails.questionno == 10}">
				Which are the industries the student / parent showed interest in
				    </c:if>
				    
				   
				    
					
				</td>
				<td style="width:20%;">
					${utils:replaceXMLEntities(studentDetails.answer)}
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</c:when>
<c:otherwise>
  <div class="empty_table_div" style="text-align: center; ">
   <span><s:text name="com.edupath.common.table"></s:text></span>
  </div>
</c:otherwise>			
</c:choose>	

<script>
var feedbackToDate = '${toDate}';
var feedbackFromDate = '${fromDate}';
$(document).ready(function(){
	TableEdittableViewLogInit();
});
</script>