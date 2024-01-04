<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<c:forEach items="${edupathDetailMap}" var="eduMap">
	<c:if test="${eduMap.key eq 'INDUSTRY' }">
<div class="row" style="padding-bottom: 30px;">
	<div class="custom-md-edupath-6">
		<div class="edupath_global_div">
			<div class="edupath_div_1">
				<img  src="${pageContext.request.contextPath}/images/icons/edupath-industry-icon.gif" class="edupath_img"/>
	 		</div>
	 		<div class="edupath_div_2" style=" margin-left:21px;">   <!--added on 28/03/18 margin-left:21px  -->
		 		 <c:if test="${not empty industryTitleMap}">
					<c:set var="keyItem" value="INDUSTRY_${param.optionName}" ></c:set>
					<c:choose>
						<c:when test="${param.optionName eq 'OPTION_1' }">
							<span>${utils:replaceXMLEntities(industryTitleMap[keyItem])}</span>
						</c:when>
						<c:otherwise>
							<span>${utils:replaceXMLEntities(industryTitleMap[keyItem])}</span>
						</c:otherwise>
					</c:choose>
				</c:if>
	 		</div>
	 	</div>
	</div>
	<div class="col-md-6 custom-md-edupath-6">
	</div>
</div>

<c:forEach items="${eduMap.value}" var="indDetailMap">
	<c:if test="${indDetailMap.key eq param.optionName}">
	<input type= "hidden" id="${param.optionName}" value="${indDetailMap.value.size()}">
	<c:forEach items="${indDetailMap.value}" var="dataList" varStatus="counterValue">
			<div class="row">
				<div>
			 		<div class="div_lbl_1">
			        	<div class="div_lbl_2 lbl_img_div">
			        	<c:choose>
			        		<c:when test="${dataList.key eq 'PU'}">
			        			<img  src="${pageContext.request.contextPath}/images/icons/edupath-PU.gif" class="lbl_img"/>
			        		</c:when>
			        		<c:when test="${dataList.key eq 'DG'}">
			        			<img  src="${pageContext.request.contextPath}/images/icons/edupath-university-icon.gif" class="lbl_img"/>
			        		</c:when>
			        		<c:when test="${dataList.key eq 'TOP'}">
			        			<img  src="${pageContext.request.contextPath}/images/icons/edupath-integrated-courses.gif" class="lbl_img"/>
			        		</c:when>
			        	</c:choose>
			       	</div> 	
			       	<div class="div_lbl_2 h_bar">
			       		<div class="h_bar_div">
			       		</div>
			       	</div> 	
			       	<div class="div_lbl_2 lbl_text_div">
			       		<c:choose>
			        		<c:when test="${dataList.key eq 'PU'}">
			        			<span><s:text name="com.edupath.path.industry.pu.stream"></s:text></span>
			        		</c:when>
			        		<c:when test="${dataList.key eq 'DG'}">
			        			<span><s:text name="com.edupath.path.industry.degree.stream"></s:text></span>
			        		</c:when>
			        		<c:when test="${dataList.key eq 'TOP'}">
			        			<span><s:text name="com.eupath.path.degree.specialization"></s:text></span>
			        		</c:when>
			        	</c:choose>
			        </div> 		 
			    	</div>
				</div>
			</div>
			
			<div class="row inner_lbl_div" >
				<div>
			 		<div class="div_lbl_1">
			        	<c:choose>
			        		<c:when test="${dataList.key eq 'TOP'}">
			        			<div class="div_lbl_2 lbl_v_bar_div ${param.optionName}_ind_div_hr_height" >
			        				&nbsp; 
			        			</div> 
			        		</c:when>
			        		<c:otherwise>
				        		<div class="div_lbl_2 lbl_v_bar_div" id="${param.optionName}_ind_lbl_height_${counterValue.index}">
				        			&nbsp; 
				        		</div> 
			        		</c:otherwise>
			        	</c:choose>	
			        	<div class="div_lbl_2 lbl_content_div" >
			        		<div class="lbl_4_div" >
			        			<%-- ${dataList.value} --%>
			        			<c:choose>
			        				<c:when test="${dataList.key eq 'PU'}">
			        					<div class="box_lbl_4 ${param.optionName}_ind_lbl_content_1_height_${counterValue.index}" >
			        						<input type="hidden" value="${dataList.value['IND_ID']}" id="IND_${param.optionName}">
			        						<p class="p_lbl_message"><s:text name="com.eupath.path.mandatory.stream"></s:text> (${dataList.value['pu_mandat_percent']}%)</p>
			        						<c:forEach items="${dataList.value['pu_mandat_stream']}" var="itemList" varStatus="count">
				        						<div class="radio radio-success" style="clear: both;">
						              				<input id="radio_${count.index}_1_${param.optionName}" onclick="fnCheckIndStream();" name="indPUStream"  value="${itemList.streamId}" ${elChecked} class="cl check_input" type="radio" ${param.radioActive}>
						              				<label for="radio_${count.index}_1_${param.optionName}" class="qusetion_txt_label">${utils:replaceXMLEntities(itemList.name)}</label>
						            			</div>
						            			<div class="custom-pg-bar">
						            				<div class="pg-bar-message_1">
								            			<div class="progress">
			    											<div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: ${itemList.percentageStr}%">
			    											</div>
			 		 									</div>
		 		 									</div>
		 		 									<div class="pg-bar-message_2">
		 		 										<span>&nbsp;&nbsp;${itemList.percentageStr}%</span>
		 		 									</div>
	 		 									</div>
 		 									</c:forEach>
			        					</div>
			        					<div class="box_lbl_4 ${param.optionName}_ind_lbl_content_2_height_${counterValue.index}" >
			        					   <p class="p_lbl_message"><s:text name="com.eupath.path.any"></s:text>&nbsp;(${dataList.value['pu_any_percent']}%)&nbsp;with&nbsp;<s:text name="com.eupath.path.suited.stream"></s:text></p>
			        					   	<!--radioActiveParam  -->
			        						<c:forEach items="${dataList.value['pu_any_stream']}" var="itemListTwo" varStatus="counttwo">
				        						<div class="radio radio-success" style="clear: both;">
						              				<input id="radio_${counttwo.index}_1_${param.optionName}_PU" name="indPUStream" onclick="fnCheckIndStream();"  value="${itemListTwo.streamId}" class="cl check_input" type="radio" ${param.radioActive}>
						              				<label for="radio_${counttwo.index}_1_${param.optionName}_PU" class="qusetion_txt_label">${utils:replaceXMLEntities(itemListTwo.name)}</label>
						            			</div>
						            			<div class="custom-pg-bar">
						            				<div class="pg-bar-message_1">
								            			<div class="progress">
			    											<div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: ${itemListTwo.percentageStr}%">
			    											</div>
			 		 									</div>
		 		 									</div>
		 		 									<div class="pg-bar-message_2">
		 		 										<span>&nbsp;&nbsp;${itemListTwo.percentageStr}%</span>
		 		 									</div>
	 		 									</div>
 		 									</c:forEach>
			        						
			        					</div>
			        				</c:when>
			        				<c:when test="${dataList.key eq 'DG'}">
			        					<div class="box_lbl_4 ${param.optionName}_ind_lbl_content_1_height_${counterValue.index}" >
			        						<p class="p_lbl_message"><s:text name="com.eupath.path.mandatory.stream"></s:text> (${dataList.value['degree_mandat_percent']}%)</p>
			        						<c:forEach items="${dataList.value['degree_mandat_stream']}" var="itemList" varStatus="count">
						            			<div class="radio radio-success" style="clear: both;">
						              				<input id="radio_${count.index}_1_${param.optionName}_DG_1" name="indDGStream"  value="${utils:replaceXMLEntities(itemList.name)}" class="cl" type="radio">
						              				<label for="radio_${count.index}_1_${param.optionName}_DG_1" class="qusetion_txt_label">${utils:replaceXMLEntities(itemList.name)}</label>
						            			</div>
						            			<div class="custom-pg-bar">
						            				<div class="pg-bar-message_1">
								            			<div class="progress">
			    											<div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: ${itemList.percentageStr}%">
			    											</div>
			 		 									</div>
		 		 									</div>
		 		 									<div class="pg-bar-message_2">
		 		 										<span>&nbsp;&nbsp;${itemList.percentageStr}%</span>
		 		 									</div>
	 		 									</div>
 		 									</c:forEach>
			        					</div>
			        					<div class="box_lbl_4 ${param.optionName}_ind_lbl_content_2_height_${counterValue.index}" >
			        						<p class="p_lbl_message"><s:text name="com.eupath.path.any"> </s:text> (${dataList.value['degree_any_percent']}%)&nbsp;with&nbsp;<s:text name="com.eupath.path.suited.stream"></s:text></p>
			        						<c:forEach items="${dataList.value['degree_any_stream']}" var="itemListTwo" varStatus="countTwo">
						            			<div class="radio radio-success" style="clear: both;">
						              				<input id="radio_${countTwo.index}_1_${param.optionName}_DG_2" name="indDGStream"  value="${utils:replaceXMLEntities(itemListTwo.name)}" class="cl" type="radio">
						              				<label for="radio_${countTwo.index}_1_${param.optionName}_DG_2" class="qusetion_txt_label">${utils:replaceXMLEntities(itemListTwo.name)}</label>
						            			</div>
						            			<div class="custom-pg-bar">
						            				<div class="pg-bar-message_1">
								            			<div class="progress">
			    											<div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: ${itemListTwo.percentageStr}%">
			    											</div>
			 		 									</div>
		 		 									</div>
		 		 									<div class="pg-bar-message_2">
		 		 										<span>&nbsp;&nbsp;${itemListTwo.percentageStr}%</span>
		 		 									</div>
	 		 									</div>
 		 									</c:forEach>
			        					</div>
			        				</c:when>
			        				<c:when test="${dataList.key eq 'TOP'}">
			        					<div class="${param.optionName}_ind_lbl_content_top">
			        					<c:forEach items="${dataList.value['top5_degree_sepcial']}" var="topItem">
			        						<div class="box_lbl_4 job-count-div">
			        							<span>${utils:replaceXMLEntities(topItem.name)}&nbsp;(${topItem.jobCount})</span>
			        						</div>
			        					</c:forEach>
			        					</div>
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
			        		<img  src="${pageContext.request.contextPath}/images/icons/edupath-integrated-courses.gif" class="lbl_img"/>
			       		</div> 	
			       	<div class="div_lbl_2 h_bar">
			       		<div class="h_bar_div">
			       		</div>
			       	</div> 	
			       	<div class="div_lbl_2 lbl_text_div">
			       		<c:choose>
		       	    		<c:when test="${topTwoActive eq 'Y'}">
		       	    			<span><s:text name="com.edupath.path.occupation.courses"></s:text></span>
		       	    		</c:when>
	       	    			<c:otherwise>
	       	    				<span><s:text name="com.edupath.path.occupation.courses"></s:text></span>
	       	    			</c:otherwise>
	       	    		</c:choose>
			        </div> 		 
			    	</div>
				</div>
			</div>
			<div class="row inner_lbl_div" >
				<div>
	 				<div class="div_lbl_1">
	 					<div class="div_lbl_2 lbl_v_bar_div" style="height: 50px;">
			        		&nbsp; 
			        	</div> 	
			        	<div class="div_lbl_2 lbl_content_div">
			        		<c:choose>
			       	    		<c:when test="${topTwoActive eq 'Y'}">
			       	    			<p class="p_courses_exam" onclick="fnGetOccupationWithCourse('${industryIdMap[param.optionName]}');"><s:text name="com.eupath.path.review"></s:text></p>
			       	    		</c:when>
		       	    			<c:otherwise>
		       	    				<p class="p_courses_exam" onclick="fnGetOccupationWithCourse('${industryIdMap[param.optionName]}');"><s:text name="com.eupath.path.review"></s:text></p>
		       	    			</c:otherwise>
	       	    			</c:choose>
			        	</div>
	 				</div>
	 			</div>
	 		</div>
			<div class="row">
				<div>
			 		<div class="div_lbl_1">
			        	<div class="div_lbl_2 lbl_img_div">
			        		<img  src="${pageContext.request.contextPath}/images/icons/edupath-exams.gif" class="lbl_img"/>
			       		</div> 	
			       	<div class="div_lbl_2 h_bar">
			       		<div class="h_bar_div">
			       		</div>
			       	</div> 	
			       	<div class="div_lbl_2 lbl_text_div">
			       		<c:choose>
		       	    		<c:when test="${topTwoActive eq 'Y'}">
		       	    			<span><s:text name="com.edupath.path.occupation.exam"></s:text></span>
		       	    			
		       	    			<c:if test="${param.setCityPosition eq 'Y' }">
			       	    		<s:select  name="cityId"  id="cityId" listKey="id" listValue="name" list="cityList" headerKey="-1" headerValue="--Select City--"
									value="%{cityId}"  onchange="fnOnCityIdChangefilter(this.value)"/>
								<s:set var="statecheck1" value="N"></s:set>
								</c:if>
		       	    			
		       	    		</c:when>
	       	    			<c:otherwise>
	       	    				<span><s:text name="com.edupath.path.occupation.exam"></s:text></span>
	       	    				<s:select  name="cityId"  id="cityId" listKey="id" listValue="name" list="cityList" headerKey="-1" headerValue="--Select City--"
									value="%{cityId}"  onchange="fnOnCityIdChangefilter(this.value)"/>
								<c:set var="statecheck" value="1" scope="session"></c:set>
								
								
	       	    			</c:otherwise>
	       	    		</c:choose>
			        </div> 		 
			    	</div>
				</div>
			</div>
			<div class="row inner_lbl_div" >
				<div>
	 				<div class="div_lbl_1">
	 					<div class="div_lbl_2 lbl_v_bar_div" style="height: 50px;">
			        		&nbsp; 
			        	</div> 	
			        	<div class="div_lbl_2 lbl_content_div">
			        		<c:choose>
			       	    		<c:when test="${topTwoActive eq 'Y'}">
			       	    			<p class="p_courses_exam" onclick="fnGetOccupationWithExam('${industryIdMap[param.optionName]}');"><s:text name="com.eupath.path.review"></s:text></p>
			       	    		</c:when>
		       	    			<c:otherwise>
		       	    				<p class="p_courses_exam" onclick="fnGetOccupationWithExam('${industryIdMap[param.optionName]}');"><s:text name="com.eupath.path.review"></s:text></p>
		       	    			</c:otherwise>
	       	    			</c:choose>
			        	</div>
	 				</div>
	 			</div>
	 		</div>
			<div class="row">
				<div>
			 		<div class="div_lbl_1">
			        	<div class="div_lbl_2 lbl_img_div">
			        		<img  src="${pageContext.request.contextPath}/images/icons/edupath-goal.gif" class="lbl_img"/>
			       		</div> 	
			       	<div class="div_lbl_2 h_bar">
			       		<div class="h_bar_div">
			       		</div>
			       	</div> 	
			       	<div class="div_lbl_2 lbl_text_div">
			       		<c:if test="${not empty industryTitleMap}">
							<c:set var="keyItem" value="INDUSTRY_${param.optionName}" ></c:set>
							<c:choose>
								<c:when test="${param.optionName eq 'OPTION_1' }">
									<span>${utils:replaceXMLEntities(industryTitleMap[keyItem])}</span>
								</c:when>
								<c:otherwise>
									<span>${utils:replaceXMLEntities(industryTitleMap[keyItem])}</span>
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
	var divH1 = $('.'+paramName+'_ind_lbl_content_1_height_'+a).height();
	var divH2 = $('.'+paramName+'_ind_lbl_content_2_height_'+a).height();
	var divH = divH2;
	if(divH1 > divH2)
	{
		divH = divH1;
	}
	if(divH > 125)
    {
		divH = divH - 50;
    } 		
	$('#'+paramName+'_ind_lbl_height_'+a).css("height", divH + 'px');
 }
 var h = $('.'+paramName+ '_ind_lbl_content_top').height();
 if(h <= 0)
 {
	 h = 100;
 } 
 $('.'+paramName+'_ind_div_hr_height').css("height", h + 'px');
 
 $(document).ready(function(){
 });
 
</script>
