<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- Question 1, 2 -->
<s:set name="Excellent">Excellent</s:set>
<s:set name="VeryGood">Very Good</s:set>
<s:set name="Good">Good</s:set>
<s:set name="Average">Average</s:set>
<s:set name="Poor">Poor</s:set>

<!-- Question 4, 5 -->
<s:set name='Definitely'>Definitely</s:set>
<s:set name='Mostly'>Mostly</s:set>
<s:set name='Notfully'>Not fully</s:set>
<s:set name='Notatall'>Not at all</s:set>

<!-- Question 6-->
<s:set name="Answeredallquestions">Answered all questions</s:set>
<s:set name="Answeredalmostallquestions">Answered almost all questions</s:set>
<s:set name="Answeredmanyquestions">Answered many questions</s:set>
<s:set name="Answeredafewquestions">Answered a few questions</s:set>
<s:set name="Didnotanswermyquestions">Did not answer my questions</s:set>

<!-- Question 8-->
<s:set name="Yestoaverylargeextent">Yes - to a very large extent</s:set>
<s:set name="Yestoalargeextent">Yes - to a large extent </s:set>
<s:set name="Yestoasmallextent">Yes - to a small extent</s:set>
<s:set name="NoDidnotcreateawareness">No - Did not create awareness</s:set>

<!-- Question 10 -->
<s:set name="Relevant">Relevant</s:set>
<s:set name="Notquiterelevant">Not quite relevant</s:set>
<s:set name="Somewhatrelevant">Somewhat relevant</s:set>
<s:set name="Notrelevantatall">Not relevant at all</s:set>

<!-- Question 12-->
<s:set name="Fullyinterested">Fully interested</s:set> 
<s:set name="Highlyinterested">Highly interested</s:set>
<s:set name="Interested">Interested</s:set>
<s:set name="Havelowinterest">Have low interest</s:set>
<s:set name="Notinterested">Not interested</s:set>

<!-- Question 13 -->
<s:set name="VeryhighConfidence">Very high Confidence</s:set>
<s:set name="Highconfidence">High confidence</s:set>
<s:set name="Somewhatconfident">Somewhat confident</s:set>
<s:set name="Notconfident">Not confident</s:set>

<!-- Question 14 -->
<s:set name="Veryhighlymotivated">Very highly motivated</s:set> 
<s:set name="Highlymotivated">Highly motivated</s:set>
<s:set name="Motivated">Motivated</s:set>
<s:set name="Somewhatmotivated">Somewhat motivated</s:set>
<s:set name="Notmotivatedatall">Not motivated at all</s:set>

<div class="feedback-form-main">
	<div class="row filter-margin-top">
		<div class="col-md-12 col-xs-12 college-search-header">
			<s:text name="com.edupath.feedbackform.studentfeedbackform.title"/> 
		</div>
	</div>
	<div class="feedback-form-questions-card">
		<div class="feedback-form-title">
			<p><b><s:text name="com.edupath.feedbackform.kindlyanswer.label"/></b></p>
		</div>
		<div>
			<s:iterator value="feedbackQuestions" var="fbquestion" status="counter">
				<div class="feedback-question-div">
					<div class="feedback-form-questions">
						<p>
							<b><s:property value="%{#counter.index + 1}"/><em>.</em> <s:property value="%{#fbquestion.question}"/></b>
						</p>
					</div>
					<div class="feedback-ans-row">
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
						</s:if>
						<s:elseif test="%{#fbquestion.questionNo eq 2}">
							<div class="feedback-subquestion">
								<p>a. Guidance Specialist was systematic and methodical during the process</p> 
							</div>
							<div>
								<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}_a" data-subtype="a"  data-questionno="${fbquestion.questionNo}" 
					            		name="${fbquestion.questionNo}_a" value="${pageScope.Excellent}"/>
					            	<label for="radio_excellent_${fbquestion.questionNo}_a">${pageScope.Excellent}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}_a" data-subtype="a"  data-questionno="${fbquestion.questionNo}" 
					            		name="${fbquestion.questionNo}_a" value="${pageScope.VeryGood}"/>
					            	<label for="radio_verygood_${fbquestion.questionNo}_a">${pageScope.VeryGood}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}_a" data-subtype="a"  data-questionno="${fbquestion.questionNo}" 
					            		name="${fbquestion.questionNo}_a" value="${pageScope.Good}"/>
					            	<label for="radio_good_${fbquestion.questionNo}_a">${pageScope.Good}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}_a" data-subtype="a"  data-questionno="${fbquestion.questionNo}" 
					            		name="${fbquestion.questionNo}_a" value="${pageScope.Average}"/>
					            	<label for="radio_average_${fbquestion.questionNo}_a">${pageScope.Average}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_poor_${fbquestion.questionNo}_a" data-subtype="b"  data-questionno="${fbquestion.questionNo}" 
					            		name="${fbquestion.questionNo}_a" value="${pageScope.Poor}"/>
					            	<label for="radio_poor_${fbquestion.questionNo}_a">${pageScope.Poor}</label>
					        	</div>
							</div>
							<div class="feedback-subquestion">
								<p>b. Guidance Specialist's approach to clarifying your doubts</p>
							</div>
							<div>
								<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}_b" data-subtype="b" data-questionno="${fbquestion.questionNo}" 
					            		name="${fbquestion.questionNo}_b" value="${pageScope.Excellent}"/>
					            	<label for="radio_excellent_${fbquestion.questionNo}_b">${pageScope.Excellent}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}_b" data-subtype="b" data-questionno="${fbquestion.questionNo}" 
					            		name="${fbquestion.questionNo}_b" value="${pageScope.VeryGood}"/>
					            	<label for="radio_verygood_${fbquestion.questionNo}_b">${pageScope.VeryGood}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}_b" data-subtype="b" data-questionno="${fbquestion.questionNo}"
					            	 name="${fbquestion.questionNo}_b" value="${pageScope.Good}"/>
					            	<label for="radio_good_${fbquestion.questionNo}_b">${pageScope.Good}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}_b" data-subtype="b" data-questionno="${fbquestion.questionNo}" 
					            	name="${fbquestion.questionNo}_b" value="${pageScope.Average}"/>
					            	<label for="radio_average_${fbquestion.questionNo}_b">${pageScope.Average}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_poor_${fbquestion.questionNo}_b" data-subtype="b" data-questionno="${fbquestion.questionNo}" 
					            	name="${fbquestion.questionNo}_b" value="${pageScope.Poor}"/>
					            	<label for="radio_poor_${fbquestion.questionNo}_b">${pageScope.Poor}</label>
					        	</div>
							</div>
						</s:elseif>
						<s:elseif test="%{#fbquestion.questionNo eq 3 or #fbquestion.questionNo eq 7}">
							<textarea cols="100" rows="5" class="textarea form-control" name="${fbquestion.questionNo}"></textarea>
						</s:elseif>
						<s:elseif test="%{#fbquestion.questionNo eq 4 or #fbquestion.questionNo eq 5}">
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
						</s:elseif>
						<s:elseif test="%{#fbquestion.questionNo eq 6}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Answeredallquestions}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Answeredallquestions}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Answeredalmostallquestions}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.Answeredalmostallquestions}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Answeredmanyquestions}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Answeredmanyquestions}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Answeredafewquestions}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.Answeredafewquestions}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_poor_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Didnotanswermyquestions}"/>
				            	<label for="radio_poor_${fbquestion.questionNo}">${pageScope.Didnotanswermyquestions}</label>
				        	</div>
						</s:elseif>
						<s:elseif test="%{#fbquestion.questionNo eq 8 or #fbquestion.questionNo eq 9}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Yestoaverylargeextent}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Yestoaverylargeextent}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Yestoalargeextent}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.Yestoalargeextent}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Yestoasmallextent}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Yestoasmallextent}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.NoDidnotcreateawareness}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.NoDidnotcreateawareness}</label>
				        	</div>
						</s:elseif>
						<s:elseif test="%{#fbquestion.questionNo eq 10 or #fbquestion.questionNo eq 11}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Relevant}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Relevant}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Notquiterelevant}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.Notquiterelevant}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Somewhatrelevant}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Somewhatrelevant}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Notrelevantatall}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.Notrelevantatall}</label>
				        	</div>
						</s:elseif>
						<s:elseif test="%{#fbquestion.questionNo eq 12}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}" data-questionno="${fbquestion.questionNo}" 
				            	    name="${fbquestion.questionNo}" value="${pageScope.Fullyinterested}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Fullyinterested}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}"data-questionno="${fbquestion.questionNo}" 
				            		name="${fbquestion.questionNo}" value="${pageScope.Highlyinterested}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.Highlyinterested}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}" data-questionno="${fbquestion.questionNo}"  
				            		name="${fbquestion.questionNo}" value="${pageScope.Interested}"/>
				            	<label for="radio_good_${fbquestion.questionNo}">${pageScope.Interested}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}" data-questionno="${fbquestion.questionNo}"  
				            		name="${fbquestion.questionNo}" value="${pageScope.Havelowinterest}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.Havelowinterest}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}" data-questionno="${fbquestion.questionNo}"  
				            		name="${fbquestion.questionNo}" value="${pageScope.Notinterested}"/>
				            	<label for="radio_average_${fbquestion.questionNo}">${pageScope.Notinterested}</label>
				        	</div>
						</s:elseif>
						<s:elseif test="%{#fbquestion.questionNo eq 13}">
							<div class='feedback-leftside-question'>
								<div class="feedback-subquestion">
									<p>A. Before the Program</p>
								</div>
								<div class="radio radio-success">
					            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}_a" data-subtype="a" data-questionno="${fbquestion.questionNo}" 
					            	    name="${fbquestion.questionNo}_a" value="${pageScope.VeryhighConfidence}"/>
					            	<label for="radio_excellent_${fbquestion.questionNo}_a">${pageScope.VeryhighConfidence}</label>
					        	</div>
					        	<div class="radio radio-success">
					            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}_a" data-subtype="a" data-questionno="${fbquestion.questionNo}" 
					            		name="${fbquestion.questionNo}_a" value="${pageScope.Highconfidence}"/>
					            	<label for="radio_verygood_${fbquestion.questionNo}_a">${pageScope.Highconfidence}</label>
					        	</div>
					        	<div class="radio radio-success">
					            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}_a" data-subtype="a" data-questionno="${fbquestion.questionNo}"  
					            		name="${fbquestion.questionNo}_a" value="${pageScope.Somewhatconfident}"/>
					            	<label for="radio_good_${fbquestion.questionNo}_a">${pageScope.Somewhatconfident}</label>
					        	</div>
					        	<div class="radio radio-success">
					            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}_a" data-subtype="a" data-questionno="${fbquestion.questionNo}"  
					            		name="${fbquestion.questionNo}_a" value="${pageScope.Notconfident}"/>
					            	<label for="radio_average_${fbquestion.questionNo}_a">${pageScope.Notconfident}</label>
					        	</div>
					        </div>
					        <div class="feedback-rightside-question">
					        	<div class="feedback-subquestion">
						        	<p>B. After the Program</p>
					        	</div>
								<div class="radio radio-success">
					            	<input class='form-control' type="radio" id="radio_excellent_${fbquestion.questionNo}_b" data-subtype="b" data-questionno="${fbquestion.questionNo}"  
					            		name="${fbquestion.questionNo}_b" value="${pageScope.VeryhighConfidence}"/>
					            	<label for="radio_excellent_${fbquestion.questionNo}_b">${pageScope.VeryhighConfidence}</label>
					        	</div>
					        	<div class="radio radio-success">
					            	<input class='form-control' type="radio" id="radio_verygood_${fbquestion.questionNo}_b" data-subtype="b" data-questionno="${fbquestion.questionNo}"  
					            		name="${fbquestion.questionNo}_b" value="${pageScope.Highconfidence}"/>
					            	<label for="radio_verygood_${fbquestion.questionNo}_b">${pageScope.Highconfidence}</label>
					        	</div>
					        	<div class="radio radio-success">
					            	<input class='form-control' type="radio" id="radio_good_${fbquestion.questionNo}_b" data-subtype="b" data-questionno="${fbquestion.questionNo}"  
					            		name="${fbquestion.questionNo}_b" value="${pageScope.Somewhatconfident}"/>
					            	<label for="radio_good_${fbquestion.questionNo}_b">${pageScope.Somewhatconfident}</label>
					        	</div>
					        	<div class="radio radio-success">
					            	<input class='form-control' type="radio" id="radio_average_${fbquestion.questionNo}_b" data-subtype="b" data-questionno="${fbquestion.questionNo}"  
					            		name="${fbquestion.questionNo}_b" value="${pageScope.Notconfident}"/>
					            	<label for="radio_average_${fbquestion.questionNo}_b">${pageScope.Notconfident}</label>
					        	</div>
					        </div>
						</s:elseif> 
						<s:elseif test="%{#fbquestion.questionNo eq 14}">
							<div>
								<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_Veryhighlymotivated_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Veryhighlymotivated}"/>
					            	<label for="radio_Veryhighlymotivated_${fbquestion.questionNo}">${pageScope.Veryhighlymotivated}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_Highlymotivated_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Highlymotivated}"/>
					            	<label for="radio_Highlymotivated_${fbquestion.questionNo}">${pageScope.Highlymotivated}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_Motivated_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Motivated}"/>
					            	<label for="radio_Motivated_${fbquestion.questionNo}">${pageScope.Motivated}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_Somewhatmotivated_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Somewhatmotivated}"/>
					            	<label for="radio_Somewhatmotivated_${fbquestion.questionNo}">${pageScope.Somewhatmotivated}</label>
					        	</div>
					        	<div class="radio radio-success radio-inline">
					            	<input class='form-control' type="radio" id="radio_Notmotivatedatall_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Notmotivatedatall}"/>
					            	<label for="radio_Notmotivatedatall_${fbquestion.questionNo}">${pageScope.Notmotivatedatall}</label>
					        	</div>
							</div>
						</s:elseif>
					</div>
				</div>
			</s:iterator>
		</div>
		<div class="feedback_submit">
			<button id="feedback_submit_button" class="btn feedback_submit-btn" style="margin-right:65px;"><s:text name="com.edupath.common.label.submit"/></button>
		</div>
	</div>
</div>

<div class="modal fade" role="dialog" id="msgModal">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content">  <!-- 23/03/18 style="width: 555px !important;" -->
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">Message</h4>
	       	</div>
	       	<div class="modal-body">
	        	<div id="parentNotFilledMsg" style="display: none">
		       		<p>
		       			Thank you for your valuable feedback. We would like to hear from your parents, too! Kindly ask them to fill the feedback form.<br/><br/>
						We are working on your summary report. It will be available to you within 7 working days of completing the feedback.
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

<script type="text/javascript">
	var actionURL = '${pageContext.request.contextPath}/myapp/saveStudentFormFeedbackFormAction';
	var finalizeForm = document.FeedbackFormAction;
	
	$(document).ready(function(){
		fnPopPreAns();
		setInterval('${autoSaveInterval * 1000}', fnAutoSave());
	});
	
	$('#msgModal').on('hide.bs.modal', function(){
		fnClosePopUp();
	});
	
	$('#feedback_submit_button').click(function()
	{
		if(fnIsValidationSuccess())
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
	
	function fnFinalizeStudentFeedBack(dataParam)
	{
		var actionUrl = "${pageContext.request.contextPath}/myapp/finalizeStudentFormFeedbackFormAction";
		$.ajax({
			url : actionUrl,
			type : "POST",
			data : {feedback : JSON.stringify(dataParam)},
			dataType : "JSON",
			success : function (resp){
				if(resp && resp != null && resp.STATUS == 'success')
				{
					if(resp.PARENT_ALREADY_FILLED)
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
				if($("textarea[name="+value.questionNo+"]").size() > 0)
				{
					$("textarea[name="+value.questionNo+"]").val(value.answer);
				}
				else if($("input[name='"+value.questionNo+"']").size() > 0)
				{
					$("input[name='"+value.questionNo+"']").each(function(){
						if($(this).val() == value.answer)
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
								$("input[name='"+value.questionNo+"_"+subType+"']").each(function(){
									if($(this).val() == answerValue)
									{
										$(this).prop("checked", true);
									}
								});
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
		$("input[type='radio']:checked, textarea").each(function(){
			subQuestion = null;
			questionNo = $(this).prop("name");
			doSave = true;
			if($(this).data("subtype"))
			{
				subQuestion = $(this).data("subtype");
				questionNo = $(this).data("questionno");
			}
			fnGetFeedbackFormJSON(dataParam, questionNo, $(this).prop("disabled") ? "" : $(this).val()+"", subQuestion);
		});
		if(doSave)
		{
			saveAnswer(actionURL, dataParam);
		}
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
				alert(arg1);
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
		$('.feedback-form-main').find("input, textarea").each(function(){
			if(!$(this).prop("disabled"))
			{
				var name = $(this).prop("name");
				if(!name)
				{
					name = $(this).data("questionno")+"_"+$(this).data("subtype");
				}
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
			}
		});
		return status;
	}
</script>