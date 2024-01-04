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
					<th width="10%"><s:text name="com.edupath.viewfeedback.summary.student.facilitator.loginid"></s:text></th>
				</c:if>
				<th><s:text name="com.edupath.viewfeedback.summary.question"></s:text></th>
				<th width="20%;"><s:text name="com.edupath.viewfeedback.summary.response"></s:text></th>
			</tr>
		</thead>
		
		<tbody>
		 <c:forEach items="${studentList}" var="studentDetails">
			<tr>
			<td style="width:10%;">
				    ${studentDetails.feedbackdate}
				</td>
			
				<td style="width:10%;">
				    ${utils:replaceXMLEntities(studentDetails.name)}
				</td>
				<td style="width:10%;">
					${utils:replaceXMLEntities(studentDetails.loginId)}
				</td>
				<c:if test="${admin}">
					<td style="width:10%;">
					    ${utils:replaceXMLEntities(studentDetails.facilitatorName)}
					</td>
					<td style="width:10%;">
						${utils:replaceXMLEntities(studentDetails.facilitatorLoginId)}
					</td>
				</c:if>
				<td>
					${utils:replaceXMLEntities(studentDetails.question)}
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