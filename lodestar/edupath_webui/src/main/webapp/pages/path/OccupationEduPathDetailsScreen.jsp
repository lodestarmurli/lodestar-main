<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<!--Content Start   -->
<c:set var="height" value="25"></c:set>
	
<c:forEach items="${edupathDetailMap}" var="eduMap">
 	<c:if test="${eduMap.key eq 'OCCUPATION' }">
			<div class="row" style="padding-bottom: 30px;">
				<div class="custom-md-edupath-6">
				<div class="edupath_global_div">
				
					<div class="edupath_div_1">
						<img src="${pageContext.request.contextPath}/images/icons/edupath-occupation-icon.gif" class="edupath_img"/>
					</div>
					<div class="edupath_div_2" style="margin-left:21px;" ><!--added on 28/03/18 margin-left:21px  -->
					                             										
					
						<c:if test="${not empty occupationTitleMap}">
						
							<c:set var="keyItem" value="OCCUPATION_${param.optionName}" ></c:set>
							<c:choose>
								<c:when test="${param.optionName eq 'OPTION_1' }">
									<span>${utils:replaceXMLEntities(occupationTitleMap[keyItem])}</span>
								</c:when>
								<c:otherwise>
									<span>${utils:replaceXMLEntities(occupationTitleMap[keyItem])}</span>
								</c:otherwise>
							</c:choose>
						</c:if>
						
						<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 19-05-2017 -->
						
										<c:set var="fitment" value="FITMENT_${param.optionName}" ></c:set>
										<c:set var="newfitment" value="NEWFITMENT_${param.optionName}" ></c:set>	
										
												<c:if test="${not empty occupationTitleMap[fitment]}">			
													<i class="fa fa-circle fa-fw ${occupationTitleMap[fitment]}"  aria-hidden="true"></i>
													</c:if>
													<c:if test="${not empty occupationTitleMap[newfitment]}">	
												 	<i class="fa fa-square fa-fw ${occupationTitleMap[newfitment]}" aria-hidden="true"></i> 
												 	</c:if>
												 	
													<!-- END Sasedeve Added by Mrutyunjaya on Date 19-05-2017 -->	
						</div>
					</div>
				</div>
				<div class="col-md-6 custom-md-edupath-6">
				</div>
			</div>
	   <c:forEach items="${eduMap.value}" var="occDetailMap" varStatus="loop">
	      <c:if test="${occDetailMap.key eq param.optionName}">
	      	<input type= "hidden" id="${param.optionName}" value="${occDetailMap.value.size()}">
	        <c:forEach items="${occDetailMap.value}" var="dataList" varStatus="counterValue">
			 <div class="row">
				<div>
			 		<div class="div_lbl_1">
			        	<div class="div_lbl_2 lbl_img_div">
			        	<c:choose>
			       	    	<c:when test="${dataList.key eq 'PU' }">
			       	    		<img  src="${pageContext.request.contextPath}/images/icons/edupath-PU.gif" class="lbl_img"/>
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'PG' or dataList.key eq 'DG'}">
			       	    		<img  src="${pageContext.request.contextPath}/images/icons/edupath-university-icon.gif" class="lbl_img"/>
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'STATEEXAM' }">
			       	    		<img  src="${pageContext.request.contextPath}/images/icons/edupath-exams.gif" class="lbl_img"/>
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'COURSES' }">
			       	    		<img  src="${pageContext.request.contextPath}/images/icons/edupath-integrated-courses.gif" class="lbl_img"/>
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'EXAM' }">
			       	    		<img  src="${pageContext.request.contextPath}/images/icons/edupath-exams.gif" class="lbl_img"/>
			       	    	</c:when>
			       	    </c:choose>
			       	     </div> 	
			       	<div class="div_lbl_2 h_bar">
			       		<div class="h_bar_div">
			       		</div>
			       	</div> 	
			       	<div class="div_lbl_2 lbl_text_div">
			       	    <c:choose>
			       	    	<c:when test="${dataList.key eq 'PU' }">
			       	    		<span><s:text name="com.edupath.path.occupation.pu.stream"></s:text></span>
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'PG' }">
			       	    		<span><s:text name="com.edupath.path.occupation.pg.stream"></s:text></span>
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'DG' }">
			       	    		<span><s:text name="com.edupath.path.occupation.degree.stream"></s:text></span>
			       	    	</c:when>
			       	    	
			       	    	
			       	    	<c:when test="${dataList.key eq 'STATEEXAM' }">
			       	    		<c:set var="required" value="Preferred"></c:set>
			       	    		<c:if test="${dataList.value.required eq 'C'}">
			       	    			<c:set var="required" value="Compulsory"></c:set>
			       	    		</c:if>
			       	    		<span><s:text name="com.edupath.path.occupation.exam"></s:text></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			       	    		
			       	    		
			       	    		<c:if test="${param.setCityPosition eq 'Y' }">
			       	    		
			       	    		<!--<div class="row"> <span><s:text name="com.edupath.path.occupation.exam"></s:text> (${required})</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="col-md-4 pull-right">
		<s:select cssStyle="width:150px" name="cityId"  id="cityId" listKey="id" listValue="name" list="cityList" value="%{studentDTO.cityId}" onchange="fnOnCityIdChangefilter(this.value)">
			</s:select>
		</div>
	</div>-->						<c:if test="${topTwoActive eq 'Y'}">
			       	    		<s:select  name="cityId"  id="cityId" listKey="id" listValue="name" list="cityList" headerKey="-1" headerValue="--Select City--"
									value="%{cityId}"  onchange="fnOnCityIdChangefilter(this.value)"/></c:if>
								<c:set var="statecheck" value="0"></c:set>
								</c:if>
									
			       	    		
			       	    	</c:when>
			       	    	
			       	    	<c:when test="${dataList.key eq 'COURSES' }">
			       	    	
			       	    	
			       	    		<span><s:text name="com.edupath.path.occupation.courses"></s:text></span>
			       	    		
			       	    		
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'EXAM' }">
			       	    		<c:set var="required" value="Preferred"></c:set>
			       	    		<c:if test="${dataList.value.required eq 'C'}">
			       	    			<c:set var="required" value="Compulsory"></c:set>
			       	    		</c:if>
			       	    		<span><s:text name="com.edupath.path.occupation.nationalexam"></s:text> (${required})</span>
			       	    	<!-- 	</br></br>
			       	    		<span><s:text name="com.edupath.path.occupation.nationalexam"></s:text></span> -->
			       	    		
			       	    		
			       	    	</c:when>
			       	    	
			       	    	
			       	    </c:choose>
			        	</div> 		 
			    	</div>
				</div>
			 </div>
			<div class="row inner_lbl_div">
				<div>
			 		<div class="div_lbl_1">
			        	<div class="div_lbl_2 lbl_v_bar_div" id="${param.optionName}_lbl_height_${counterValue.index}">
			       		&nbsp; 
			       	</div> 	
			       	<div class="div_lbl_2 lbl_content_div" id="${param.optionName}_lbl_content_height_${counterValue.index}">
			       	  <div class="lbl_4_div">
			            <c:choose>
			       	    	<c:when test="${dataList.key eq 'PU' }">
				       	    	<div class="box_lbl_4">
						       	    	<p class="p_lbl_message"><span><s:text name="com.eupath.path.mandatory.stream"></s:text></span>
						       	    	<span>(<s:text name="com.eupath.path.suited.stream"></s:text>)</span></p>
						       	    	<input type="hidden" name="pustream_${param.optionName}" value="${dataList.value.streamId}" data-preferrdstream="${dataList.value.preferrdStreamId}" data-eduid="${dataList.value.eduPathId}" data-id="${dataList.value.occupationId}">
					       	    		<c:choose>
					       	    			<c:when test="${dataList.value.isAnyStream eq 'disabled'}">
					       	    				<div class="custom-radio-lbl">
					       	    					<label for="radio_1_${param.optionName}" class="qusetion_txt_label">${utils:replaceXMLEntities(dataList.value.streamName)}</label>
					       	    				</div>
					       	    			</c:when>
					       	    			<c:otherwise>
					       	    				<div class="radio radio-success">
					              					<input id="radio_1_${param.optionName}" name="pustream" onclick="fnCheckStream('${param.optionName}', this);"  value="${dataList.value.streamId}"  ${param.radioChecked} class="auto_save check_input" type="radio"  ${param.radioActive} >
					              					<label for="radio_1_${param.optionName}" class="qusetion_txt_label">${utils:replaceXMLEntities(dataList.value.streamName)}</label>
					            				</div>
					       	    			</c:otherwise>
					       	    		</c:choose>
				            	</div>
				            	<div class="box_lbl_4 box_lbl_4_2">
						            	<p class="p_lbl_message"><span><s:text name="com.eupath.path.specialization"></s:text></span>
						            	<span>(<s:text name="com.eupath.path.suited.subject"></s:text>)</span></p>
						            	<input type="hidden" name="pusubject_${param.optionName}" value="${dataList.value.subjectId}" data-preferrdsubject="${dataList.value.preferrdSubjectId}"  data-eduid="${dataList.value.eduPathId}" data-id="${dataList.value.occupationId}">
					            		<c:choose>
					            			<c:when test="${dataList.value.isAnySubject eq 'disabled'}">
					            				<div class="custom-radio-lbl">
					            					<label for="radio_2_${param.optionName}" class="qusetion_txt_label">${utils:replaceXMLEntities(dataList.value.subjectName)}</label> 
					            				</div>
					            			</c:when>
					            			<c:otherwise>
					            				<div class="radio radio-success ">
					              					<input id="radio_2_${param.optionName}" name="pusubject"  value="${dataList.value.subjectId}" class="auto_save" data-preferrdsubject="${dataList.value.preferrdSubjectId}"  type="radio" ${param.radioActive}>
					              					<label for="radio_2_${param.optionName}" class="qusetion_txt_label">${utils:replaceXMLEntities(dataList.value.subjectName)}</label> 
					              				</div>
					            			</c:otherwise>
					            		</c:choose>
				              	</div>	
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'PG' }">
			       	    		<div class="box_lbl_4">
			       	    			<p class="p_lbl_message"><span><s:text name="com.eupath.path.mandatory.stream"></s:text></span>
			            			<span>(<s:text name="com.eupath.path.suited.stream"></s:text>)</span></p>
			            			<p>${utils:replaceXMLEntities(dataList.value.streamName)}</p>
			       	    		</div>
			       	    		<div class="box_lbl_4 box_lbl_4_2">
			       	    			<p class="p_lbl_message"><span><s:text name="com.eupath.path.specialization"></s:text></span>
			            			<span>(<s:text name="com.eupath.path.suited.subject"></s:text>)</span></p>
			            			<p>${utils:replaceXMLEntities(dataList.value.subjectName)}</p>
			       	    		</div>
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'DG' }">
			       	    		<div class="box_lbl_4">
			       	    			<p class="p_lbl_message"><span><s:text name="com.eupath.path.mandatory.stream"></s:text></span>
			            			<span>(<s:text name="com.eupath.path.suited.stream"></s:text>)</span></p>
			            			<p>${utils:replaceXMLEntities(dataList.value.streamName)}</p>
			       	    		</div>
			       	    		<div class="box_lbl_4 box_lbl_4_2">
			       	    			<p class="p_lbl_message"><span><s:text name="com.eupath.path.specialization"></s:text></span>
			            			<span>(<s:text name="com.eupath.path.suited.subject"></s:text>)</span></p>
			            			<p>${utils:replaceXMLEntities(dataList.value.subjectName)}</p>
			       	    		</div>
			       	    	</c:when>
			       	    	
			       	    	
			       	    	
			       	    	<c:when test="${dataList.key eq 'STATEEXAM' }">
			       	    		<c:choose>
				       	    		<c:when test="${topTwoActive eq 'Y'}">
				       	    		<div id="examId_${dataList.value.occupationId}">
				       	    			<p class="p_courses_exam " onclick="fnEntranceExam('${dataList.value.occupationId}', '${occIndustryIdMap[param.optionName]}', true,1);">${utils:replaceXMLEntities(dataList.value.examName)}</p>
				       	    		</div>
				       	    		</c:when>
				       	    		<c:otherwise>
				       	    		<div id="examId_${dataList.value.occupationId}">
				       	    			<p class="p_courses_exam" onclick="fnEntranceExam('${dataList.value.occupationId}', '${occIndustryIdMap[param.optionName]}', false,1);">${utils:replaceXMLEntities(dataList.value.examName)}</p>
				       	    		</div>
				       	    		</c:otherwise>
				       	    		
			       	    		</c:choose>
			       	    		
			       	    		<c:choose>
				       	    		<c:when test="${loop.index eq 0}">
				       	    			<input type="hidden" name="FristOccuIDvalue" id="FristOccuIDvalue" value="${dataList.value.occupationId}">
				       	    			<input type="hidden" name="FristOccuIDvalueoptionName" id="FristOccuIDvalueoptionName" value="${occIndustryIdMap[param.optionName]}">
				       	    		</c:when>
				       	    		<c:otherwise>
				       	    			<input type="hidden" name="SecondOccuIDvalue" id="SecondOccuIDvalue" value="${dataList.value.occupationId}">
				       	    			<input type="hidden" name="SecondOccuIDvalueoptionName" id="SecondOccuIDvalueoptionName" value="${occIndustryIdMap[param.optionName]}">
				       	    		</c:otherwise>
			       	    		</c:choose>
			       	    			
			       	    		
			       	    	</c:when>
			       	    	
			       	    	
			       	    	
			       	    	
			       	    	
			       	    	
			       	    	<c:when test="${dataList.key eq 'COURSES' }">
			       	    		<c:choose>
				       	    		<c:when test="${topTwoActive eq 'Y'}">
				       	    		
				       	    			<p class="p_courses_exam" onclick="fnIntegratedCourses('${dataList.value.occupationId}', '${occIndustryIdMap[param.optionName]}', true);">${utils:replaceXMLEntities(dataList.value.programName)}</p>
				       	    			<input type="hidden" value="${occIndustryIdMap[param.optionName]}" name="occ_ind_${param.optionName}" id="occ_ind_${param.optionName}">
				       	    		</c:when>
			       	    			<c:otherwise>
			       	    				<p class="p_courses_exam " onclick="fnIntegratedCourses('${dataList.value.occupationId}', '${occIndustryIdMap[param.optionName]}', false);">${utils:replaceXMLEntities(dataList.value.programName)}</p>
			       	    				<input type="hidden" value="${occIndustryIdMap[param.optionName]}" name="occ_ind_${param.optionName}" id="occ_ind_${param.optionName}">
			       	    			</c:otherwise>
			       	    		</c:choose>
			       	    	</c:when>
			       	    	<c:when test="${dataList.key eq 'EXAM' }">
			       	    		<c:choose>
				       	    		<c:when test="${topTwoActive eq 'Y'}">
				       	    			<p class="p_courses_exam " onclick="fnEntranceExam('${dataList.value.occupationId}', '${occIndustryIdMap[param.optionName]}', true,0);">${utils:replaceXMLEntities(dataList.value.examName)}</p>
				       	    		
				       	    		<!--<span onclick="fnEntranceExam()"><s:text name="com.edupath.path.occupation.nationalexam"></s:text></span>-->
				       	    		
				       	    		</c:when>
				       	    		<c:otherwise>
				       	    			<p class="p_courses_exam" onclick="fnEntranceExam('${dataList.value.occupationId}', '${occIndustryIdMap[param.optionName]}', false,0);">${utils:replaceXMLEntities(dataList.value.examName)}</p>
				       	    			
				       	    		</c:otherwise>
			       	    		</c:choose>
			       	    	</c:when>
			       	    	
			       	    	
			       	    </c:choose>
			        	</div>	
			        	</div>	 
			    	</div>
				</div>
			</div>
			
		 </c:forEach>
		</c:if>
		
</c:forEach>
			<div class="row">
				<div>
			 		<div class="div_lbl_1">
			        	<div class="div_lbl_2 lbl_img_div">
			        		<img  src="${pageContext.request.contextPath}/images/icons/edupath-goal.gif" class="lbl_img"/>
			        		<input type="hidden" value="${occIndustryIdMap[param.optionName]}" name="occ_ind_${param.optionName}" id="occ_ind_${param.optionName}">
			       		</div> 	
			       	<div class="div_lbl_2 h_bar">
			       		<div class="h_bar_div">
			       		</div>
			       	</div> 	
			       	<div class="div_lbl_2 lbl_text_div">
			       		<c:if test="${not empty occupationTitleMap}">
							<c:set var="keyItem" value="OCCUPATION_${param.optionName}" ></c:set>
							<c:choose>
								<c:when test="${param.optionName eq 'OPTION_1' }">
									<span>${utils:replaceXMLEntities(occupationTitleMap[keyItem])}</span>
								</c:when>
								<c:otherwise>
									<span>${utils:replaceXMLEntities(occupationTitleMap[keyItem])}</span>
								</c:otherwise>
							</c:choose>
						</c:if>
			        </div> 		 
			    	</div>
				</div>
			</div>
			
			
  
 </c:if>
</c:forEach>


<script>
var paramName = '${param.optionName}';
 var size = parseInt($('#' + paramName).val());
 for(var a = 0; a<size; a++)
 {
	var divH = $('#'+paramName+'_lbl_content_height_'+a).height();
	$('#'+paramName+'_lbl_height_'+a).css("height", divH + 23 + 'px');
 }
 $(document).ready(function(){
	 
	 var checkEdupath="${checkEdupath}";
	 if(checkEdupath == 1)
		 {
		 
		 if (document.getElementById("cityId")) {
			 document.getElementById("cityId").disabled = true;
			}
			
		 }
	 
 });
 
 
 
 
</script>

<style>
.form-control
{
width:40px;
}
</style>