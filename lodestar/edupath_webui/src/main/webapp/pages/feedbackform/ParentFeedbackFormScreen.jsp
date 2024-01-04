<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="${pageContext.request.contextPath}/thirdparty/starrating/star-rating.min.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/starrating/theme-krajee-svg.css" rel="stylesheet"  type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/thirdparty/starrating/fontawesome-stars-o.css">

<!-- Question 1 -->
<s:set name="Excellent">Excellent</s:set>
<s:set name="VeryGood">Very Good</s:set>
<s:set name="Good">Good</s:set>
<s:set name="Average">Average</s:set>
<s:set name="Poor">Poor</s:set>
<!-- STart Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
<!-- Question 3 -->
<s:set name="Coordination">A. Co-ordination for scheduling of guidance sessions</s:set>
<s:set name="GuidanceQuestion">B. Guidance specialist's interaction</s:set>
<s:set name="makeDecisions">C. Ease of using Lodestar's software</s:set>
<s:set name="EaseLodestar">D. Lodestar's methodical and systematic approache</s:set>
<!-- 
<s:set name="EducationPathway">E. Choice of careers and education pathway (courses, PU college/schools)</s:set>
<s:set name="GuidanceApproach">F. Guidance specialist's methodical and systematic approach</s:set> -->
<!-- END Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
<!-- Question 4, 5 -->
<s:set name='Definitely'>Definitely</s:set>
<s:set name='Mostly'>Mostly</s:set>
<s:set name='Notfully'>Not fully</s:set>
<s:set name='Notatall'>Not at all</s:set>

<!-- Question 9 -->
<s:set name='Excellent'>Excellent</s:set>
<s:set name='VeryGood'>Very Good</s:set>
<s:set name='Good'>Good</s:set>
<s:set name='Average'>Average</s:set>
<s:set name='Poor'>Poor</s:set>
<s:set name='NotApplicable'>Not Applicable</s:set>
<!-- START Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
<!-- Question 6 -->
<s:set name="Answeredallquestions">Answered all questions</s:set>
<s:set name="Answeredalmostallquestions">Answered most questions</s:set>
<s:set name="Answeredmanyquestions">Answered few questions</s:set>
<s:set name="Didnotanswermyquestions">Did not answer my question</s:set>
<!--<s:set name="Answeredafewquestions">Answered a few questions</s:set>-->
<!-- END Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
<!-- Question 8 -->
<s:set name="Yestoaverylargeextent">Yes - to a very large extent</s:set>
<s:set name="Yestoalargeextent">Yes - to a large extent </s:set>
<s:set name="Yestoasmallextent">Yes - to a small extent</s:set>
<s:set name="NoDidnotcreateawareness">No - Did not create awareness</s:set>

<!-- Question 9 -->
<s:set name="Relevanttodecisionmaking">Relevant to decision making</s:set>
<s:set name="SomewhatRelevant">Somewhat Relevant</s:set>
<s:set name="Littlebitrelevant">Little bit relevant</s:set>
<s:set name="Notrelevantatall">Not relevant at all</s:set>

<!-- Question -->
<s:set name="Scientific">Scientific (logical)</s:set> 
<s:set name="Factbased">Fact based</s:set>
<s:set name="Qualityanddetaileddata">Quality and detailed data</s:set>
<s:set name="OneonOneinteractionwithcounsellor">One on One interaction with counsellor</s:set>
<s:set name="Reportstoreadathome">Reports to read at home</s:set>
<s:set name="Allquestionsanswered">All questions answered</s:set>
<s:set name="multiplesession">Multiple sessions with a week's gap in-between</s:set>
<!-- START Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
<!-- Question 12-->

<!--START Sasedeve edited by Mrutyunjaya on date 02-07-2018	-->
<s:set name="rs4000">Rs.7500</s:set>
<s:set name="rs5000">Rs.10000</s:set>
<s:set name="rs6000">Rs.12500</s:set>
<!-- <s:set name="morethanrs6000">More than Rs.7500</s:set> -->
<s:set name="anyamount">Any other amount in your mind</s:set>

<!--END Sasedeve edited by Mrutyunjaya on date 02-07-2018	-->

<!-- END Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->


<!-- Question -->
<s:set name="Definitely">Definitely</s:set> 
<s:set name="Maybe">Maybe</s:set>
<s:set name="Willnotrecommend">Will not recommend</s:set>

<!-- Question 10 -->
<s:set name="Thisformatisfine">Three face-to-face meetings</s:set> 
<s:set name="FacetoFacemeeting">2 face-to-face meetings and 1 discussion through video conferencing/ Skype</s:set>
<s:set name="AllmeetingsonSkype">1 face-to-face meeting and 2 discussions through video conferencing/ Skype</s:set> 
<s:set name="TelephoneorVideowithcounsellor">All three meetings through video conferencing/ Skype</s:set>
<s:set name="Nomeetingisrequired">No meeting/ video conferencing is required. If all the information, with instructions, is provided, we can do this online by ourselves and arrive at a career decision</s:set> 

<!-- STart Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
<!-- New Question 2 -->

<s:set name="StructuredApproach">Structured Approach</s:set> 
<s:set name="Exposuretowiderchoiceofcareers">Exposure to wider choice of careers</s:set>
<s:set name="Systemrecommendationonmatchingcareers">System recommendation on matching careers</s:set> 
<s:set name="InteractionandguidanceprovidedbyGS">Interaction and guidance provided by GS</s:set>
<s:set name="Informationoncollegesandelectives">Information on colleges and electives</s:set> 
<s:set name="Others">Others</s:set> 
<!-- End Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->



<div class="feedback-form-main">
	<div class="row filter-margin-top">
		<div class="col-md-12 col-xs-12 college-search-header">
			<s:text name="com.edupath.feedbackform.parentfeedbackform.title"/> 
		</div>
	</div>
	<div class="feedback-form-questions-card">
		<div class="feedback-form-questions">
			<s:iterator value="feedbackQuestions" var="fbquestion" status="counter">
			<s:if test="%{#fbquestion.questionNo eq 1 or #fbquestion.questionNo eq 2 or #fbquestion.questionNo eq 3 or #fbquestion.questionNo eq 4 or #fbquestion.questionNo eq 6 or #fbquestion.questionNo eq 8 or #fbquestion.questionNo eq 9 or #fbquestion.questionNo eq 11 or #fbquestion.questionNo eq 12 or #fbquestion.questionNo eq 13}">
			
				<div class="feedback-question-div form-group">
					<div>
						<p>
							<s:if test="%{#fbquestion.questionNo eq 13}">
								<label class="control-label"><s:property value="%{#fbquestion.question}"/></label>
							</s:if>
							<s:else>
								<label class="control-label"><b><s:property value="%{#counter.index + 1}"/><em>.</em> <s:property value="%{#fbquestion.question}"/></b></label>
							</s:else>
						</p>
					</div>
					<div>
						<s:if test="%{#fbquestion.questionNo eq 1}">
						
						<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Excellent}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Excellent}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.VeryGood}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.VeryGood}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Good}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Good}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Average}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.Average}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_poor_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Poor}"/>
				            	<label for="radio_poor_${fbquestion.questionNo}">${pageScope.Poor}</label>
				        	</div>
						<!--   <s:if test="#fbquestion.questionNo eq 9">
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_NotApplicable_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.NotApplicable}"/>
					            	<label for="radio_NotApplicable_${fbquestion.questionNo}">${pageScope.NotApplicable}</label>
					        	</div>
				        	</s:if> -->
						</s:if>
						<!-- STart Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
						<s:elseif test="%{#fbquestion.questionNo eq 2}">
						
						<div class="radio radio-success">
				            	<input class='form-control abcclick' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.StructuredApproach}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.StructuredApproach}</label>
				        	</div>
				        	<div class="radio radio-success">
				            	<input class='form-control abcclick' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Exposuretowiderchoiceofcareers}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.Exposuretowiderchoiceofcareers}</label>
				        	</div>
				        	<div class="radio radio-success">
				            	<input class='form-control abcclick' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Systemrecommendationonmatchingcareers}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Systemrecommendationonmatchingcareers}</label>
				        	</div>
				        	<div class="radio radio-success">
				            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.InteractionandguidanceprovidedbyGS}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.InteractionandguidanceprovidedbyGS}</label>
				        	</div>
				        	<div class="radio radio-success">
				            	<input class='form-control abcclick' type="radio" id="radio_poor_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Informationoncollegesandelectives}"/>
				            	<label for="radio_poor_${fbquestion.questionNo}">${pageScope.Informationoncollegesandelectives}</label>
				        	</div>
				        	<div class="radio radio-success" >
				            	<input class='form-control abcclick' type="radio" id="radio_other_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Others}"/>
				            	<label for="radio_poor_${fbquestion.questionNo}">${pageScope.Others}</label>
				        	</div>
						
						<div class="radio radio-success" id="radio_other_textarea_div" style="display:none">
						<textarea data-novalidate="true" cols="100" rows="3" class="textarea-md form-control" id="radio_other_textarea" name="${fbquestion.questionNo}"></textarea>
						
						</div>
						</s:elseif>
						
						<s:elseif test="%{#fbquestion.questionNo eq 4}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control xyzclick' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Definitely}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Definitely}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control xyzclick' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Mostly}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.Mostly}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control xyzclick' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Notfully}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Notfully}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control xyzclick' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Notatall}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.Notatall}</label>
				        	</div>
				        	
				        	<div  id="radio_extradetails_textarea_div" style="display:none">
				        	
				        	<br/>
				        	<label><b>Please elaborate what was missing in the session </b></label>
				        	
				        	
					 	  <textarea data-novalidate="true" cols="100" rows="3" class="textarea-md form-control" id="radio_extradetails_textarea" name="${fbquestion.questionNo}"></textarea>
						
						
						</div>
				        	
				        	
				        	
						</s:elseif>
						
						
						
						
					
					<!-- <s:elseif test="%{#fbquestion.questionNo eq 5}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Definitely}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Definitely}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Mostly}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.Mostly}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Notfully}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Notfully}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Notatall}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.Notatall}</label>
				        	</div>
						</s:elseif>-->
						
						
							
						
						
						
						<s:elseif test="%{#fbquestion.questionNo eq 6}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control efgclick' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Answeredallquestions}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Answeredallquestions}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control efgclick' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Answeredalmostallquestions}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.Answeredalmostallquestions}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control efgclick' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Answeredmanyquestions}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Answeredmanyquestions}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control efgclick' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Didnotanswermyquestions}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.Didnotanswermyquestions}</label>
				        	</div>
				        	
				        	
				        	<div class="radio-success" id="radio_extradetails_textarea1_div" style="display:none">
				        	<br/>
				        	<label><b>Please elaborate which questions were not answered in the session </b></label>
				        	
				        	
						<textarea data-novalidate="true" cols="100" rows="3" class="textarea-md form-control" id="radio_extradetails_textarea1" name="${fbquestion.questionNo}"></textarea>
						
						</div>
				        	
						</s:elseif>
						
						
						
						
						<s:elseif test="%{#fbquestion.questionNo eq 8}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control qrsclick' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Yestoaverylargeextent}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Yestoaverylargeextent}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control qrsclick' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Yestoalargeextent}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.Yestoalargeextent}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control qrsclick' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Yestoasmallextent}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Yestoasmallextent}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control qrsclick' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.NoDidnotcreateawareness}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.NoDidnotcreateawareness}</label>
				        	</div>
				        	<div class="radio-success" id="radio_extradetails_textarea2_div" style="display:none">
				        	
				        		<br/>
				        	<label><b>Please elaborate why Lodestar was not able to create the awareness </b></label>
				        	
						<textarea data-novalidate="true" cols="100" rows="3" class="textarea-md form-control" id="radio_extradetails_textarea2" name="${fbquestion.questionNo}"></textarea>
						
						</div>
				        	
						</s:elseif>
						
						
						
						<!-- END Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
						
						<s:elseif test="%{#fbquestion.questionNo eq 9}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Relevanttodecisionmaking}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Relevanttodecisionmaking}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.SomewhatRelevant}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.SomewhatRelevant}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Littlebitrelevant}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Littlebitrelevant}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Notrelevantatall}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.Notrelevantatall}</label>
				            </div>
						</s:elseif>
						<s:elseif test="%{#fbquestion.questionNo eq 12}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control jklclick' type="radio" id="radio_rs4000_${fbquestion.questionNo}" data-questionno="${fbquestion.questionNo}" 
				            	    name="${fbquestion.questionNo}" value="${pageScope.rs4000}"/>
				            	<label for="radio_rs4000_${fbquestion.questionNo}">${pageScope.rs4000}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control jklclick' type="radio" id="radio_rs5000_${fbquestion.questionNo}" data-questionno="${fbquestion.questionNo}" 
				            		name="${fbquestion.questionNo}" value="${pageScope.rs5000}"/>
				            	<label for="radio_rs5000_${fbquestion.questionNo}">${pageScope.rs5000}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control jklclick' type="radio" id="radio_rs6000_${fbquestion.questionNo}" data-questionno="${fbquestion.questionNo}"  
				            		name="${fbquestion.questionNo}" value="${pageScope.rs6000}"/>
				            	<label for="radio_rs6000_${fbquestion.questionNo}">${pageScope.rs6000}</label>
				        	</div>
				         <!--START Sasedeve edited by Mrutyunjaya on date 02-07-2018	-->
				       <!-- 	<div class="radio radio-success radio-inline">
				            	<input class='form-control jklclick' type="radio" id="radio_morethanrs6000_${fbquestion.questionNo}" data-questionno="${fbquestion.questionNo}"  
				            		name="${fbquestion.questionNo}" value="${pageScope.morethanrs6000}"/>
				            	<label for="radio_morethanrs6000_${fbquestion.questionNo}">${pageScope.morethanrs6000}</label>
				        	</div> -->
				        	
				        	<!--END Sasedeve edited by Mrutyunjaya on date 02-07-2018	-->
							<div class="radio radio-success radio-inline">
				            	<input class='form-control  jklclick' type="radio" id="radio_anyamount_${fbquestion.questionNo}" data-questionno="${fbquestion.questionNo}"  
				            		data-value-id="QNO_${fbquestion.questionNo}_5" name="${fbquestion.questionNo}" value="${pageScope.anyamount}"/>
				            	<label for="radio_anyamount_${fbquestion.questionNo}">${pageScope.anyamount}
				            	</label>
				        	</div>
				        	<div class="radio-inline" style="clear: both; width: 150px; height: 20px;">
			        			<input  id="QNO_${fbquestion.questionNo}_5" class="form-control" onblur="fnAutoSave()" style="height: 25px;" data-seperator-start=" ( Rs. " data-seperator-end=" )"
			        			data-novalidate="true">
			        		</div>
						</s:elseif>
						
				<!--		<s:elseif test="%{#fbquestion.questionNo eq 10}">
							<div>
								<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_fine_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Thisformatisfine}"/>
					            	<label for="radio_fine_${fbquestion.questionNo}">${pageScope.Thisformatisfine}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_facetoface_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.FacetoFacemeeting}"/>
					            	<label for="radio_facetoface_${fbquestion.questionNo}">${pageScope.FacetoFacemeeting}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_skype_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.AllmeetingsonSkype}"/>
					            	<label for="radio_skype_${fbquestion.questionNo}">${pageScope.AllmeetingsonSkype}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_media_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.TelephoneorVideowithcounsellor}"/>
					            	<label for="radio_media_${fbquestion.questionNo}">${pageScope.TelephoneorVideowithcounsellor}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_Nomeetingisrequired_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Nomeetingisrequired}"/>
					            	<label for="radio_Nomeetingisrequired_${fbquestion.questionNo}">${pageScope.Nomeetingisrequired}</label>
					        	</div>
							</div>
						</s:elseif> -->
						<!-- START Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
						<s:elseif test="%{#fbquestion.questionNo eq 3}">
							<div class="feedback-star-rating">
								<s:iterator value="{'a', 'b', 'c', 'd'}" var="subQuestion">
									<div class="feedback-star-rating-row">
										<s:if test="%{#subQuestion eq 'a'}">
											<label for="input_${fbquestion.questionNo}_a" class="control-label">${pageScope.Coordination}</label>
										</s:if>
										<s:elseif test="%{#subQuestion eq 'b'}">
											<label for="input_${fbquestion.questionNo}_b" class="control-label">${pageScope.GuidanceQuestion}</label>
										</s:elseif>
										<s:elseif test="%{#subQuestion eq 'c'}">
											<label for="input_${fbquestion.questionNo}_c" class="control-label">${pageScope.makeDecisions}</label>
										</s:elseif>
										<s:elseif test="%{#subQuestion eq 'd'}">
											<label for="input_${fbquestion.questionNo}_d" class="control-label">${pageScope.EaseLodestar}</label>
										</s:elseif>
									  <!--	<s:elseif test="%{#subQuestion eq 'e'}">
											<label for="input_${fbquestion.questionNo}_e" class="control-label">${pageScope.EducationPathway}</label>
										</s:elseif>
										<s:elseif test="%{#subQuestion eq 'f'}">
											<label for="input_${fbquestion.questionNo}_f" class="control-label">${pageScope.GuidanceApproach}</label>
										</s:elseif>-->
		    							<select class="example-fontawesome-o" name="${fbquestion.questionNo}_${subQuestion}">
							                 <option value="1" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">1</option>
							                 <option value="2" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">2</option>
							                 <option value="3" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">3</option>
							                 <option value="4" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">4</option>
							                 <option value="5" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">5</option>
							               <!--    <option value="6" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">6</option>
							                 <option value="7" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">7</option>
							                 <option value="8" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">8</option>
							                 <option value="9" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">9</option>
							                 <option value="10" data-subtype="${subQuestion}" data-questionno="${fbquestion.questionNo}">10</option>-->
						                </select>
					                </div>
								</s:iterator>
							</div>
						</s:elseif>
						<!-- END Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
						
				<!--		<s:elseif test="%{#fbquestion.questionNo eq 7}">
							<textarea cols="100" rows="5" class="textarea-md form-control" name="${fbquestion.questionNo}"></textarea>
						</s:elseif>  -->
						
						<!-- START Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
						<s:elseif test="%{#fbquestion.questionNo eq 11}">
						<textarea cols="100" rows="5" class="textarea-md form-control" data-novalidate="false" name="${fbquestion.questionNo}"></textarea>
						</s:elseif>
						<s:elseif test="%{#fbquestion.questionNo eq 13}">
							<textarea cols="100" rows="5" class="textarea-md form-control" data-novalidate="true" name="${fbquestion.questionNo}"></textarea>
						</s:elseif>
						
						<!-- END Sasedeve edited by Mrutyunjaya on date 05-09-2017 -->
					</div>
				</div>
				
				
				</s:if>
				
				
			</s:iterator>
		</div>
		<div class="feedback_submit">
			<button id="feedback_submit_button" class="btn feedback_submit-btn"><s:text name="com.edupath.common.label.submit"/></button>
		</div>
	</div>
</div>

<div class="modal fade" role="dialog" id="msgModal">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content">			<!--23/03/18 style="width: 555px !important;" -->
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">Message</h4>
	       	</div>
	       	<div class="modal-body">
	        	<div id="parentNotFilledMsg" style="display: none">
	        		<p>
	        			Thank you for valuable feedback. We would like to hear from your child too! Kindly ask them to fill the feedback form.<br/><br/>
						We are working on your child's summary report. It will be available to you within 7 working days of completing the feedback.
	        		</p>
	        	</div>
	        	<div id="parentFilledMsg" style="display: none">
	        		<p>
	        			We appreciate you taking the time out to help us improve the program. Your summary report would be available within 7 working days.
	        		</p>
	        	</div>
	       	</div>
       		<div class="modal-footer">
         		<button class="btn btn-sm apply-btn college-filter-btn"  onclick="fnClosePopUp()">
					<s:text name="com.edupath.questionnarie.model.btn.label"/>
				</button>
       		</div>
	     </div>
	</div>
</div>

<s:form action="/myapp/FeedbackFormAction" method="POST" name="FeedbackFormAction">
	<s:hidden name="isFinalSubmit" id="isFinalSubmit"/>
</s:form>

<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/starrating/jquery.barrating.min.js"></script>
<script type="text/javascript">
	var actionURL = '${pageContext.request.contextPath}/myapp/saveParentFormFeedbackFormAction';
	var finalizeForm = document.FeedbackFormAction;
	
	$(document).ready(function(){
		fnPopPreAns();
		setInterval(fnAutoSave, '${autoSaveInterval * 1000}');
		$('.example-fontawesome-o').barrating('show', {
			  theme: 'fontawesome-stars-o',
			  onSelect: function(value, text, event) {
			    if (typeof(event) !== 'undefined') 
			    {
			    	fnAutoSave();
			    } 
			  }
			});
	});
	
	$('#msgModal').on('hide.bs.modal', function(){
		fnClosePopUp();
	});
	//Start Sasedeve By Mrutyunjaya on Date 05-09-2017
	$('#feedback_submit_button').click(function()
	{
		
		
		if(fnIsValidationSuccess() && ValidateTextField())
		{
			var dataJSON = fnAutoSave();
			fnFinalizeStudentFeedBack(dataJSON);
		}
		else
		{
			alert("Please answer all unanswered questions");
		}
	});
	
	function fnClosePopUp()
	{
		finalizeForm.submit();
	}
	
	
	function ValidateTextField()
	{
		var status = true;
		var isFocused = false;
		
		
		   
		    if($("#radio_other_2").is(':checked'))
			{
				if($('#radio_other_textarea').val()==null || $('#radio_other_textarea').val().trim()=="")
				{
					$('#radio_other_textarea').focus();
					isFocused = true;
					$('#radio_other_textarea').parent().parent().addClass('has-error has-danger');	
					status = false;
					
					return status;
				}
			}
		    if($("#radio_good_4").is(':checked') || $("#radio_average_4").is(':checked'))
			{
				if($('#radio_extradetails_textarea').val()==null || $('#radio_extradetails_textarea').val().trim()=="")
				{
					$('#radio_extradetails_textarea').focus();
					isFocused = true;
					$('#radio_extradetails_textarea').parent().parent().addClass('has-error has-danger');	
					status = false;
					
					return status;
				}
			}
		    if($("#radio_good_6").is(':checked') || $("#radio_average_6").is(':checked'))
			{
				if($('#radio_extradetails_textarea1').val()==null || $('#radio_extradetails_textarea1').val().trim()=="")
				{
					$('#radio_extradetails_textarea1').focus();
					isFocused = true;
					$('#radio_extradetails_textarea1').parent().parent().addClass('has-error has-danger');	
					status = false;
					
					return status;
				}
			}
		    
		    if($("#radio_good_8").is(':checked') || $("#radio_average_8").is(':checked'))
			{
				if($('#radio_extradetails_textarea2').val()==null || $('#radio_extradetails_textarea2').val().trim()=="")
				{
					$('#radio_extradetails_textarea2').focus();
					isFocused = true;
					$('#radio_extradetails_textarea2').parent().parent().addClass('has-error has-danger');	
					status = false;
					
					return status;
				}
			}
			
		    if($("#radio_anyamount_").is(':checked'))
			{
				if($('#QNO_12_5').val()==null || $('#QNO_12_5').val().trim()=="")
				{
					$('#QNO_12_5').focus();
					isFocused = true;
					$('#QNO_12_5').parent().parent().addClass('has-error has-danger');	
					status = false;
					
					return status;
				}
				
			}
			
		   
		   
		
		return status;
	}
	
	
	$('.abcclick').click(function(){
		
		if($(this).val()=="Others")
		{   
			
			$('#radio_other_textarea_div').show();
			
			
		}
		else
		{
			
			$('#radio_other_textarea_div').hide();
		}
		
	});
	
$('.xyzclick').click(function(){
		

		if($(this).val()=="Not fully" || $(this).val()=="Not at all")
		{
			
			
			$('#radio_extradetails_textarea_div').show();
		}
		else
		{
			
			$('#radio_extradetails_textarea_div').hide();
		}
		//fnAutoSave();
	});
	
	
$('.efgclick').click(function(){
	

	if($(this).val()=="Answered few questions" || $(this).val()=="Did not answer my question")
	{
		
		$('#radio_extradetails_textarea1_div').show();
	}
	else
	{
		
		$('#radio_extradetails_textarea1_div').hide();
	}
	//fnAutoSave();
});
	
$('.qrsclick').click(function(){
	

	if($(this).val()=="Yes - to a small extent" || $(this).val()=="No - Did not create awareness")
	{
		
	
		$('#radio_extradetails_textarea2_div').show();
	}
	else
	{
		
		$('#radio_extradetails_textarea2_div').hide();
	}
	//fnAutoSave();
});


	//END Sasedeve By Mrutyunjaya on Date 05-09-2017
	
	function fnFinalizeStudentFeedBack(dataParam)
	{
		var actionUrl = "${pageContext.request.contextPath}/myapp/finalizeParentFormFeedbackFormAction";
		$.ajax({
			url : actionUrl,
			type : "POST",
			data : {feedback : JSON.stringify(dataParam)},
			dataType : "JSON",
			success : function (resp){
				if(resp && resp != null && resp.STATUS == 'success')
				{
					if(resp.STUDENT_ALREADY_FILLED)
					{
						$('#parentFilledMsg').show();
					}
					else
					{
						$('#parentNotFilledMsg').show();
					}
					$('#msgModal').modal("show");
				}
				else
				{
					alert("<s:text name='com.edupath.action.internal.error'/>");
				}
			},
			error : function (arg0, arg1, arg2){
				alert(arg1);
			}
		});
	}
	
	function fnPopPreAns()
	{
		if('${not empty feedbackAnswered}' == 'true')
		{
			var ansArray = JSON.parse('${feedbackAnswered}');
			$.each(ansArray, function(index, value){
				
				if(value.questionNo==8)
				{
					var res=value.answer.split(":");
					
					if(res[0].trim()=="Yes - to a small extent" || res[0].trim()=="No - Did not create awareness")
					{
							$("input[name='"+value.questionNo+"']").each(function(){
							
							if($(this).val() == getFinalAns(this, res[0].trim()))
							{
								$(this).prop("checked", true);
								fnDisableEnableQuestion(this, res[0].trim());
							}
						});
						
						$("#radio_extradetails_textarea2").val(res[1].trim());
						$('#radio_extradetails_textarea2_div').show();
						
					}
					else
					{
						$("input[name='"+value.questionNo+"']").each(function(){
							
							if($(this).val() == getFinalAns(this, res[0].trim()))
							{
								$(this).prop("checked", true);
								fnDisableEnableQuestion(this, res[0].trim());
							}
						});
						$('#radio_extradetails_textarea2_div').hide();
						
					}
					
				}
				else if(value.questionNo==6)
				{
					var res=value.answer.split("-");
					
					if(res[0].trim()=="Answered few questions" || res[0].trim()=="Did not answer my question")
					{
							$("input[name='"+value.questionNo+"']").each(function(){
							
							if($(this).val() == getFinalAns(this, res[0].trim()))
							{
								$(this).prop("checked", true);
								fnDisableEnableQuestion(this, res[0].trim());
							}
						});
						$("#radio_extradetails_textarea1").val(res[1].trim());
						$('#radio_extradetails_textarea1_div').show();
						
					}
					else
					{
						$("input[name='"+value.questionNo+"']").each(function(){
							
							if($(this).val() == getFinalAns(this, res[0].trim()))
							{
								$(this).prop("checked", true);
								fnDisableEnableQuestion(this, res[0].trim());
							}
						});
						$('#radio_extradetails_textarea1_div').hide();
						
					}
					
				}
				else if(value.questionNo==4)
				{
					var res=value.answer.split("-");
					
					if(res[0].trim()=="Not fully" || res[0].trim()=="Not at all")
					{
							$("input[name='"+value.questionNo+"']").each(function(){
							
							if($(this).val() == getFinalAns(this, res[0].trim()))
							{
								$(this).prop("checked", true);
								fnDisableEnableQuestion(this, res[0].trim());
							}
						});
						
						$("#radio_extradetails_textarea").val(res[1].trim());
						$('#radio_extradetails_textarea_div').show();
						
					}
					else
					{
						$("input[name='"+value.questionNo+"']").each(function(){
							
							if($(this).val() == getFinalAns(this, res[0].trim()))
							{
								$(this).prop("checked", true);
								fnDisableEnableQuestion(this, res[0].trim());
							}
						});
						
						
						$('#radio_extradetails_textarea_div').hide();
						
					}
					
				}
				else if(value.questionNo==2)
				{
					var res=value.answer.split("-");
					
					//alert(res[0]);
					if(res[0].trim()=="Others")
					{
						
						$("input[name='"+value.questionNo+"']").each(function(){
							
							if($(this).val() == getFinalAns(this, res[0].trim()))
							{
								$(this).prop("checked", true);
								fnDisableEnableQuestion(this, res[0].trim());
							}
						});
						
						$("#radio_other_textarea").val(res[1].trim());
						$('#radio_other_textarea_div').show();
					}
					else
					{
						$("input[name='"+value.questionNo+"']").each(function(){
							
							if($(this).val() == getFinalAns(this, res[0].trim()))
							{
								$(this).prop("checked", true);
								fnDisableEnableQuestion(this, res[0].trim());
							}
						});
						$('#radio_other_textarea_div').hide();
					}
					
					
				}
				else if($("textarea[name="+value.questionNo+"]").size() > 0)
				{
					$("textarea[name="+value.questionNo+"]").val(value.answer);
				}
				else if($("input[name='"+value.questionNo+"']").size() > 0)
				{
					$("input[name='"+value.questionNo+"']").each(function(){
						
						if($(this).val() == getFinalAns(this, value.answer))
						{
							$(this).prop("checked", true);
							fnDisableEnableQuestion(this, value.answer);
						}
					});
				}
				else 
				{
					try
					{
						var ansJson = JSON.parse(value.answer);
						$.each(ansJson, function(index, answerObj){
							$.each(answerObj, function(subType, answerValue){
								if($("input[name='"+value.questionNo+"_"+subType+"']").size() > 0)
								{
									$("input[name='"+value.questionNo+"_"+subType+"']").each(function(){
										if($(this).val() == answerValue || $(this).data('subtype') == subType)
										{
											$(this).prop("checked", true);
											if($(this).hasClass('rating'))
											{
												$(this).rating('update', answerValue);
											}
										}
									});
								}
								else if($("select[name='"+value.questionNo+"_"+subType+"']").size() > 0)
								{
									$("select[name='"+value.questionNo+"_"+subType+"']>option").each(function(){
										if($(this).val() == answerValue)
										{
											$(this).prop("selected", "selected");
										}
									});
								}
							});
						});
					}
					catch(error)
					{
						console.log(error);
					}
				}
			});
		}
	}

	function fnAutoSave()
	{
		var dataParam = {};
		var doSave = false;
		var subQuestion = null;
		var questionNo = null;
		$("input[type='radio']:checked, textarea, select>option:selected").each(function(){
			subQuestion = null;
			questionNo = $(this).prop("name");
			doSave = true;
			var answer = $(this).val();
			
			if(questionNo=="8")
			{
				
				if($(this).val()=="Yes - to a small extent" || $(this).val()=="No - Did not create awareness")
				{
					answer=$(this).val()+" : "+ $('#radio_extradetails_textarea2').val();
				}
				else if($(this).val()=="Yes - to a very large extent" || $(this).val()=="Yes - to a large extent")
				{
					answer=$(this).val();
				}
				else
				{
					answer="404";
					
				}
				
			}
			else if(questionNo=="6")
			{
				
				if($(this).val()=="Answered few questions" || $(this).val()=="Did not answer my question")
				{
					answer=$(this).val()+" - "+ $('#radio_extradetails_textarea1').val();
				}
				else if($(this).val()=="Answered all questions" || $(this).val()=="Answered most questions")
				{
					answer=$(this).val();
				}
				else
				{
					answer="404";
					
				}
				
			}
			else if(questionNo=="4")
			{
				
				if($(this).val()=="Not fully" || $(this).val()=="Not at all")
				{
					answer=$(this).val()+" - "+ $('#radio_extradetails_textarea').val();
				}
				else if($(this).val()=="Definitely" || $(this).val()=="Mostly")
				{
					answer=$(this).val();
				}
				else
				{
					answer="404";
					
				}
				
			}
			else if(questionNo=="2")
			{
				if($(this).val()=="Others")
				{
					answer="Others - "+ $('#radio_other_textarea').val();
				}
				else if($(this).val()=="Structured Approach" || $(this).val()=="Exposure to wider choice of careers" || $(this).val()=="System recommendation on matching careers" || $(this).val()=="Interaction and guidance provided by GS" || $(this).val()=="Information on colleges and electives" )
				{
					answer=$(this).val();
				}
				else
				{
					answer="404";
					
				}
			}
			else
			{
				if($(this).data("subtype"))
				{
					subQuestion = $(this).data("subtype");
					questionNo = $(this).data("questionno")+"";
				}
				 answer = $(this).val();
				if($(this).data("valueId"))
				{
					var $valueField = $("#"+$(this).data("valueId"));
					answer += ($valueField.data("seperatorStart") + $('#'+$(this).data("valueId")).val() + $valueField.data("seperatorEnd"));
				}
			}
			
			if(answer!="404")
			{
				fnGetFeedbackFormJSON(dataParam, questionNo, $(this).prop("disabled") ? "" : answer + "", subQuestion);
			}
			
			
		});
		if(doSave)
		{
			saveAnswer(actionURL, dataParam);
		}
		
		//console.log("===>"+ JSON.stringify(dataParam));
		
		
		return dataParam;
	}
	
	function saveAnswer(action, dataParam)
	{
		$.ajax({
			url : action,
			type:"POST",
			data : {feedback : JSON.stringify(dataParam)},
			success : function(resp)
			{
				// Donothing
			},
			error : function(arg0, arg1, arg2)
			{
				console.log(arg1);
			}
			
		});
	}
	
	$('input[type=radio]').click(function(){
		$(this).parent().parent().removeClass('has-error has-danger');
		fnAutoSave();
		fnDisableEnableQuestion(this);
	});
	
	function fnDisableEnableQuestion($this, ans)
	{
		if($($this).prop("name") == 6)
		{
			if( ($($this).val() == '${Answeredallquestions}' || ans == '${Answeredallquestions}'))
			{
				$('textarea[name="7"]').prop("disabled", true);
			}
			else
			{
				$('textarea[name="7"]').prop("disabled", false);
			}
		}
	}
	
	$('textarea').focusout(function(){
		$(this).parent().removeClass('has-error has-danger');
		fnAutoSave();
	});
	
	function fnGetFeedbackFormJSON(dataParam, questionNo, answer, subQuestion)
	{
		if(!dataParam.FEED_BACK || dataParam.FEED_BACK.length <= 0)
		{
			dataParam.FEED_BACK = [];
		}
		
		if(subQuestion)
		{
			var questionFound = false;
			var finalAns = [];
			$.each(dataParam.FEED_BACK, function(index, value){
				if(value.QUESTION_NO == questionNo)
				{
					questionFound = true;
					var ans = {};
					ans[subQuestion] = answer;
					finalAns = JSON.parse(value.QUESTION_ANS);
					finalAns.push(ans);
					$(this).remove();
				}
			});
			if(!questionFound)
			{
				var ans = {};
				ans[subQuestion] = answer;
				finalAns.push(ans);
			}
			finalAns = JSON.stringify(finalAns);
			dataParam.FEED_BACK.push({QUESTION_NO : questionNo, QUESTION_ANS : finalAns});
		}
		else
		{
			dataParam.FEED_BACK.push({QUESTION_NO : questionNo, QUESTION_ANS : answer});
		}
	}
	
	function fnIsValidationSuccess()
	{
		var status = true;
		var isFocused = false;
		$('.feedback-form-main').find("input, textarea").each(function(index){
			if(!$(this).prop("disabled"))
			{
				var name = $(this).prop("name");
				if(!name)
				{
					name = $(this).data("questionno")+"_"+$(this).data("subtype");
				}
				
				console.log($(this).data("novalidate"));
				
				if(!($(this).data("novalidate")) && $(this).prop("type") == 'radio' && !$('input[name="'+name+'"]').is(":checked"))
				{
					if(!isFocused)
					{
						$(this).focus();
						isFocused = true;
					}
					$(this).parent().parent().addClass('has-error has-danger');	
					status = false;
				}
				else if(!($(this).data("novalidate")) && (!$(this).val() || $(this).val() == null || $(this).val() == "" || $(this).val().length == 0 || $(this).val() == 0))
				{
					if(!isFocused)
					{
						$(this).focus();
						isFocused = true;
					}
					$(this).parent().addClass('has-error has-danger');	
					status = false;
				}
				var $field = ('input[name="'+name+'"]');
				if($(this).prop("type") == 'radio' && $($field+":checked").data("valueId"))
				{
					var fieldVal = $("#"+$($field+":checked").data("valueId")).val();
					if(fieldVal == null || fieldVal == "")
					{
						if(!isFocused)
						{
							$("#"+$($field+":checked").data("valueId")).focus();
							isFocused = true;
						}
						$($field+":checked").parent().addClass('has-error has-danger');	
						status = false;
					}
				}
			}
		});
		return status;
	}
	
	function getFinalAns($this, answer)
	{
		var actualValue = answer;
		if($($this) && $($this).data("valueId") && answer.indexOf($("#"+$($this).data("valueId")).data("seperatorStart")) > 0)
		{
			var $valueField = $("#"+$($this).data("valueId"));
			var splitValue = actualValue.split($valueField.data("seperatorStart"));
			var fieldValue = splitValue[1].replace($valueField.data("seperatorEnd"), "");
			$valueField.val(fieldValue);
			actualValue = splitValue[0];
		}
		return actualValue;
	}
</script>