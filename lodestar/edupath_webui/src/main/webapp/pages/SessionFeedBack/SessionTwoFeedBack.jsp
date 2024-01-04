<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="${pageContext.request.contextPath}/thirdparty/starrating/star-rating.min.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/starrating/theme-krajee-svg.css" rel="stylesheet"  type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/thirdparty/starrating/fontawesome-stars-o.css">

<!-- Question 1 -->
<s:set name="Yes">Yes</s:set>
<s:set name="No">No</s:set>

<div class="feedback-form-main">
	<div class="row filter-margin-top">
		<div class="col-md-12 col-xs-12 college-search-header">				<!-- 23/03/18 added col-xs-12 -->
			<s:text name="com.edupath.feedbackform.parentfeedbackformsessiontwo.title"/> 
		</div>
	</div>
	<div class="feedback-form-questions-card">
		<div class="feedback-form-questions">
			<s:iterator value="feedbackQuestions" var="fbquestion" status="counter">
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
				            	<input class='form-control' type="radio" id="radio_yes_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Yes}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Yes}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_no_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.No}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.No}</label>
				        	</div>
				        	
						</s:if>
						<s:if test="%{#fbquestion.questionNo eq 2}">
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_yes_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.Yes}"/>
				            	<label for="radio_excellent_${fbquestion.questionNo}">${pageScope.Yes}</label>
				            	
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_no_${fbquestion.questionNo}" name="${fbquestion.questionNo}" value="${pageScope.No}"/>
				            	<label for="radio_verygood_${fbquestion.questionNo}">${pageScope.No}</label>
				        	</div>
				        	<br/><br/>
				        	<textarea style="display:none;" cols="100" rows="5" class="textarea-md form-control" data-novalidate="true" id="txt_${fbquestion.questionNo}" name="${fbquestion.questionNo}"></textarea>
				        	
				        	
						</s:if>
						
						<s:if test="%{#fbquestion.questionNo eq 3}">
							<textarea cols="100" rows="5" class="textarea-md form-control" name="${fbquestion.questionNo}"></textarea>
						</s:if>
						
						
						
						
					</div>
				</div>
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
	   	<div class="modal-content">		<!-- 23/03/18 style="width: 555px !important;" -->
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">Message</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        	<div id="parentFilledMsg" style="display: none">
	        		<p>
	        			We appreciate your time in providing the feedback.
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

<s:form action="/myapp/Session2" method="POST" name="sessionFeedbackFormAction">
	
</s:form>

<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/starrating/jquery.barrating.min.js"></script>
<script type="text/javascript">
	
	var finalizeForm = document.sessionFeedbackFormAction;
	
	$(document).ready(function(){
	
		
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
	
	$('#feedback_submit_button').click(function()
	{
		var dataJSON = fnAutoSave();
		if(dataJSON!=null && dataJSON.FEED_BACK.length==3)
		{
			
			fnFinalizeStudentFeedBack(dataJSON);
			
		}
		else
		{
			alert("Please answer all unanswered questions");
		}
	});
	
	
	function fnFinalizeStudentFeedBack(dataParam)
	{
		
		//console.log(JSON.stringify(dataParam));
		
		
		var actionUrl = "${pageContext.request.contextPath}/myapp/SaveSessionTwoFeedBack";
		$.ajax({
			url : actionUrl,
			type : "POST",
			data : {feedbackAnswered : JSON.stringify(dataParam)},
			dataType : "JSON",
			success : function (resp){
				if(resp && resp != null && resp.STATUS == 'success')
				{
					$('#parentFilledMsg').show();
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
	
	
	function fnClosePopUp()
	{
		finalizeForm.submit();
	}
	
	$('input[type=radio]').click(function(){
		$(this).parent().parent().removeClass('has-error has-danger');
		fnDisableEnableQuestion(this);
		
	});
	
	function fnDisableEnableQuestion($this, ans)
	{
		if($($this).prop("name") == 2)
		{
			if( ($($this).val() == '${Yes}' || ans == '${Yes}'))
			{
				$('textarea[name="2"]').hide();
			}
			else
			{
				$('textarea[name="2"]').show();
			}
		}
	}
	
	$('textarea').focusout(function(){
		$(this).parent().removeClass('has-error has-danger');
		
	});
	function fnAutoSave()
	{
		var dataParam = {};
		var doSave = false;
		var subQuestion = null;
		var questionNo = null;
		$("input[type='radio']:checked, textarea, select>option:selected").each(function(){
			subQuestion = null;
			
			questionNo = $(this).prop("name");
			if(questionNo==2)
			{
				if($(this).val()=="No")
				{
					doSave = true;
					if($(this).data("subtype"))
					{
						subQuestion = $(this).data("subtype");
						questionNo = $(this).data("questionno")+"";
					}
					var answer=$("#txt_2").val();
					if($(this).data("valueId"))
					{
						var $valueField = $("#"+$(this).data("valueId"));
						answer += ($valueField.data("seperatorStart") + $('#'+$(this).data("valueId")).val() + $valueField.data("seperatorEnd"));
					}
					
					
					fnGetFeedbackFormJSON(dataParam, questionNo, $(this).prop("disabled") ? "" : answer + "", subQuestion);
				}
				else if($(this).val()=="Yes")
				{
					doSave = true;
					if($(this).data("subtype"))
					{
						subQuestion = $(this).data("subtype");
						questionNo = $(this).data("questionno")+"";
					}
					var answer=$(this).val();
					if($(this).data("valueId"))
					{
						var $valueField = $("#"+$(this).data("valueId"));
						answer += ($valueField.data("seperatorStart") + $('#'+$(this).data("valueId")).val() + $valueField.data("seperatorEnd"));
					}
					
					
					fnGetFeedbackFormJSON(dataParam, questionNo, $(this).prop("disabled") ? "" : answer + "", subQuestion);
				}
				
				
			}
			else
			{
				doSave = true;
				if($(this).data("subtype"))
				{
					subQuestion = $(this).data("subtype");
					questionNo = $(this).data("questionno")+"";
				}
				var answer = $(this).val();
				if($(this).data("valueId"))
				{
					var $valueField = $("#"+$(this).data("valueId"));
					answer += ($valueField.data("seperatorStart") + $('#'+$(this).data("valueId")).val() + $valueField.data("seperatorEnd"));
				}
				fnGetFeedbackFormJSON(dataParam, questionNo, $(this).prop("disabled") ? "" : answer + "", subQuestion);
			}
			
		});
		if(doSave)
		{
			//saveAnswer(actionURL, dataParam);
		}
		
		///console.log(JSON.stringify(dataParam));
		
		return dataParam;
	}
	function fnGetFeedbackFormJSON(dataParam, questionNo, answer, subQuestion)
	{
		if(!dataParam.FEED_BACK || dataParam.FEED_BACK.length <= 0)
		{
			dataParam.FEED_BACK = [];
		}
		
		if(subQuestion)
		{
			dataParam.FEED_BACK.push({QUESTION_NO : questionNo, QUESTION_ANS : answer});
		}
		else
		{
			dataParam.FEED_BACK.push({QUESTION_NO : questionNo, QUESTION_ANS : answer});
		}
	}
</script>