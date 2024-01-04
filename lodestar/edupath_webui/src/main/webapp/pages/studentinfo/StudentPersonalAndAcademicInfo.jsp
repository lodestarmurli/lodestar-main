<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="fn" uri="fn.tld"%>
	
	<div class="col-md-4">				<!-- 23/03/18 style="width: 500px;" -->
		<div class="rows">
			<span class="heading">Personal & Academic info</span>
			<div class="rows">
			  <div class="col-md-6">		
				<div class="row">
					<span class="label_text">Name:&nbsp;</span>
					<span class="normal_text">${studentDetailsDTO.name}&nbsp;(&nbsp;class&nbsp;${studentDetailsDTO.className}&nbsp;)</span>
				</div>
				<div class="row">
					<span class="label_text">Father:&nbsp;</span>
					<span class="normal_text">${studentDetailsDTO.studentQuesAnsMap[1]}</span>
				</div>
				<div class="row">
					<span class="label_text">Mother:&nbsp;</span>
					<span class="normal_text">${studentDetailsDTO.studentQuesAnsMap[2]}</span>
				</div>
				</div>
				<div class="col-md-6">
				<div class="row">
					<span class="label_text">School:&nbsp;</span>
					<span class="normal_text">${studentDetailsDTO.schoolName}&nbsp;(&nbsp;${studentDetailsDTO.studentQuesAnsMap[3].trim()}&nbsp;)</span>
				</div>
				<div class="row">
					<span class="label_text">Class 11 Consideration:&nbsp;</span>
					<span class="normal_text">${studentDetailsDTO.studentQuesAnsMap[5]}&nbsp;(&nbsp;${studentDetailsDTO.studentQuesAnsMap[6]}&nbsp;)&nbsp;with&nbsp;${studentDetailsDTO.studentQuesAnsMap[4]}&nbsp;syllabus</span>
				</div>
			  </div>	
			</div>
			
			<div class="rows"  style="display: none;">
				<span class="label_text">Interests:</span><br>
				<p class="normal_text" style="word-wrap: break-word;">
					
				</p>
			</div>
		</div>
		
		<div class="rows" style="margin-top: 12px;">    <!-- 23/03/18 80px  -->
			<span class="heading">Hobbies and Interest</span>
			<div class="rows">
			<span class="label_text">Strengths:</span><br>
				<p class="normal_text" style="word-wrap: break-word;">${studentDetailsDTO.studentQuesAnsMap[8]}</p>
			</div>
			
			<div class="rows">
				<span class="label_text">Hobbies:</span><br>
				<p class="normal_text">${studentDetailsDTO.studentQuesAnsMap[9]}</p>
			</div>
		</div>
		
		<div class="rows" style="margin-top: 15px;">
			<span class="heading">Questionnarie</span><br>
			<div class="rows">
				<div class="rows" style="margin-top: 0px; padding-bottom: 20px;">
					<span class="label_text">Score:&nbsp;</span>
					<span class="normal_text">
						 <c:choose>
						 	<c:when test="${not empty studentCGTResult.appScore}">
								${studentCGTResult.appScore}
						 	</c:when>
						 	<c:otherwise>
						 		-
						 	</c:otherwise>
						 </c:choose>	
						
						,&nbsp;
					</span>
					<span class="label_text">Personality code:&nbsp;</span>
					<span class="normal_text">${studentCGTResult.personalityCode},&nbsp;</span>
					<span class="label_text">Recommended Occupations:&nbsp;</span>
					<span class="normal_text">
						<c:forEach var="occup" items="${occupIndusMap}">
    						${occup.key}&nbsp;(&nbsp;${occup.value}&nbsp;), 
						</c:forEach>
					</span>
				</div>
				<%-- <div class="rows" style="margin-top: 0px; padding-bottom: 20px;">
					<span class="label_text">Recommended Occupations: </span>
					<span class="normal_text">
						<c:forEach var="occup" items="${occupIndusMap}">
    						${occup.key} (${occup.value}), 
						</c:forEach>
					</span>
				</div> --%>
				
			</div>
		</div>
	</div>
	
	<div class="col-md-4" style="padding-left: 20px; width: 200px;">			<!-- 23/03/18  style="padding-left: 70px; -->
		<div class="rows">
			<span class="label_text">Academic record:</span><br>
			<div class="rows" style="margin-top: 0px;">
				<c:forTokens var="token" items="${studentDetailsDTO.studentQuesAnsMap[7]}" delims=",">
  			 		<div class="rows normal_text" style="margin-top: 0px;">
  			 			<c:if test="${token ne 'percentage'}">
						<c:out value="${token}"/>  
  			 			</c:if>
					</div>
       			</c:forTokens>
			</div>
		</div>
		
		<div class="rows">
			<span class="label_text">Most Interested Subjects:</span>
			<div class="rows normal_text" style="margin-top: 0px;">
				<c:forTokens var="token" items="${studentDetailsDTO.studentQuesAnsMap[10]}" delims=",">
  			 		<div class="rows normal_text" style="margin-top: 0px;">
						<c:out value="${token}"/>  
					</div>
       			</c:forTokens>	
			</div>
			
		</div>
	</div>
	
	<div class="col-md-4">
		<div class="rows">
			<span class="label_text">Work aspiration</span>
			<div class="rows normal_text" style="margin-top: 0px;">
					${studentDetailsDTO.studentQuesAnsMap[11]}
			</div>
		</div>
		
		<div class="rows">
			<span class="label_text">Things i would like to know:</span>
			<div class="rows normal_text" style="margin-top: 0px;">
			<c:forTokens var="token" items="${studentDetailsDTO.studentQuesAnsMap[12]}" delims="," varStatus="numCount">
		 		<div class="rows normal_text" style="margin-top: 0px;">
					${numCount.count}&nbsp;.&nbsp;<c:out value="${token}"/>  
				</div>
       		</c:forTokens>
			</div>
		</div>
		
		<div class="rows">
			<span class="label_text">Preferred work location</span>
			<div class="rows normal_text" style="margin-top: 0px;">
				${studentDetailsDTO.studentQuesAnsMap[13]}	
			</div>
			
		</div>
	</div>
