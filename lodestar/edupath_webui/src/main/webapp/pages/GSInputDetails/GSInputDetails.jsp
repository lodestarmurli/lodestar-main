<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="${pageContext.request.contextPath}/thirdparty/starrating/star-rating.min.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/starrating/theme-krajee-svg.css" rel="stylesheet"  type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/thirdparty/starrating/fontawesome-stars-o.css">
<s:set name="Yes">Yes</s:set>
<s:set name="No">No</s:set>

<div class="feedback-form-main">
	<div class="row filter-margin-top">
		<div class="col-md-12 col-xs-12 college-search-header"> <!--added 26/03/18 -->
			<s:text name="GS Inputs"/> 
		</div>
	</div>
	<div class="feedback-form-questions-card">
		<div class="feedback-form-questions">
		
		
		<div class="feedback-question-div form-group">
		
							<div>
								<p>
									
								<label class="control-label"><b>1. Could you please mention the child's interests and hobbies? </b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success" id="radio_other_textarea_div">
						<textarea cols="100" rows="3" data-novalidate="false" class="textarea-md form-control" id="radio_other_textarea" name="1"></textarea>
						
						</div>
							
							</div>
		
		
		</div>
		
		
		<div class="feedback-question-div form-group">
		
							<div>
								<p>
									
								<label class="control-label"><b>2. Have you discussed his/her academic performance? </b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_2" name="2" value="${pageScope.Yes}"/>
				            	<label for="radio_excellent_2">${pageScope.Yes}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_2" name="2" value="${pageScope.No}"/>
				            	<label for="radio_verygood_2">${pageScope.No}</label>
				        	</div>
							
							</div>
		
		
		</div>
		
		
		<div class="feedback-question-div form-group">
		
							<div>
								<p>
									
								<label class="control-label"><b>3. Can you describe the nature of the student briefly from whatever you have understood in your limited interaction?</b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success" id="radio_other_textarea_div">
						<textarea cols="100" rows="3" data-novalidate="false" class="textarea-md form-control" id="radio_other_textarea_3" name="3"></textarea>
						
						</div>
							
							</div>
		
		
		</div>
		
		<div class="feedback-question-div form-group">
		
							<div>
								<p>
									
								<label class="control-label" ><b>4. Have the interest and abilities test results been discussed?</b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_4" name="4" value="${pageScope.Yes}"/>
				            	<label for="radio_excellent_2">${pageScope.Yes}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_4" name="4" value="${pageScope.No}"/>
				            	<label for="radio_verygood_2">${pageScope.No}</label>
				        	</div>
				        	<br>
				        	<br>
							<div>
							
								<p>
								
								<label class="control-label" style=padding-left:15px;"><b>What was the response of the student / parent about the same.</b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success" id="radio_other_textarea_div">
						<textarea cols="100" rows="3" data-novalidate="false" class="textarea-md form-control" id="radio_other_textarea_41" name="41"></textarea>
						
						</div>
							</div>
							
							
		</div>
		
		</div>
		
		
		
							
							</div>
		
		<div class="feedback-question-div form-group">
		
							<div>
								<p>
									
								<label class="control-label"><b>5. What according to you is the child's theme? </b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success" id="radio_other_textarea_div">
						<textarea cols="100" rows="3" data-novalidate="false" class="textarea-md form-control" id="radio_other_textarea_5" name="5"></textarea>
						
						</div>
							
							</div>
		
		
		</div>
		
		
		<div class="feedback-question-div form-group">
							<div>
								<p>
									
								<label class="control-label"><b>6. Have the expectations been set with the student and the parent?</b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_6" name="6" value="${pageScope.Yes}"/>
				            	<label for="radio_excellent_6">${pageScope.Yes}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_6" name="6" value="${pageScope.No}"/>
				            	<label for="radio_verygood_6">${pageScope.No}</label>
				        	</div>
							
							</div>
		
		
		</div>
		
		
		
		<div class="feedback-question-div form-group">
							<div>
								<p>
									
								<label class="control-label"><b>7. Have you briefed about the IRs and ORs? </b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_excellent_7" name="7" value="${pageScope.Yes}"/>
				            	<label for="radio_excellent_2">${pageScope.Yes}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control' type="radio" id="radio_verygood_7" name="7" value="${pageScope.No}"/>
				            	<label for="radio_verygood_2">${pageScope.No}</label>
				        	</div>
							
							</div>
		
		
		</div>
		<div class="feedback-question-div form-group">
							<div>
								<p>
									
								<label class="control-label"><b>8. What were the parameters considered to create the occupation wish list for the child? </b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success" id="radio_other_textarea_div">
						<textarea cols="100" rows="3" data-novalidate="false" class="textarea-md form-control" id="radio_other_textarea_8" name="8"></textarea>
						
						</div>
							
							
							
							
							</div>
		
		
		</div>
		
		
		<div class="feedback-question-div form-group">
		
							<div>
								<p>
									
								<label class="control-label"><b>9. Did the parent / student has any suggestion on the WOJs? </b></label>
									
								</p>
							</div>
							<div>
							<div class="radio radio-success radio-inline">
				            	<input class='form-control abcclick' type="radio" id="radio_excellent_9" name="9" value="${pageScope.Yes}"/>
				            	<label for="radio_excellent_9">${pageScope.Yes}</label>
				        	</div>
				        	<div class="radio radio-success radio-inline">
				            	<input class='form-control abcclick' type="radio" id="radio_verygood_9" name="9" value="${pageScope.No}"/>
				            	<label for="radio_verygood_9">${pageScope.No}</label>
				        	</div>
							
							
							</div>
							<br/>
							<div id="91question" style="display:none;">
							    <p>
									
								<label class="control-label" style="padding-left:15px;"><b>What was the suggestion</b></label>
									
								</p>
							<div class="radio radio-success" id="radio_other_textarea_div">
						      <textarea data-novalidate="true" cols="100" rows="3" class="textarea-md form-control" id="radio_other_textarea_91" name="9"></textarea>
						
						</div>
							</div>
							
		
		
		</div>
		<div class="feedback-question-div form-group">
		
							<div>
								<p>
									
								<label class="control-label"><b>10.	Which are the industries the student / parent showed interest in?</b></label>
									
								</p>
							</div>
							<div>
							
							<div class="radio radio-success" id="radio_other_textarea_div">
						<textarea cols="100" rows="3" data-novalidate="false" class="textarea-md form-control" id="radio_other_textarea_10" name="10"></textarea>
						
						</div>
							
							</div>
		
		
		</div>
	
		
		
		<div class="feedback_submit">
			<button id="feedback_submit_button" class="btn feedback_submit-btn"><s:text name="com.edupath.common.label.submit"/></button>
		</div>
		
		</div>
		</div>
		<s:form action="/myapp/GSinputSubmitAction" method="POST" name="GSinputSubmitformAction">
	<s:hidden name="TipsAnswered" id="TipsAnswered"/>
      </s:form>
<script>		
$('#feedback_submit_button').click(function()
		{
			
			
			//console.log("=====>"+JSON.stringify(dataJSON));
			
			
			if(fnIsValidationSuccess() && ValidateTextField())
			{
				var dataJSON = fnAutoSave();
				$("#TipsAnswered").val(JSON.stringify(dataJSON));
				var finalizeForm = document.GSinputSubmitformAction;
				
				finalizeForm.submit();
				
			}
			else
			{
				alert("Please answer all unanswered questions");
			}
		});	
$('.abcclick').click(function(){
	
	if($(this).val()=="Yes")
	{   
		
		$('#91question').show();
		
		
	}
	else
	{
		
		$('#91question').hide();
	}
	
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
			doSave = true;
			var answer = $(this).val();

			if(questionNo=="9")
			{
				
				if($(this).val()=="Yes")
				{
					answer=$(this).val()+" : "+ $('#radio_other_textarea_91').val();
				}
				else if($(this).val()=="No")
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
			//saveAnswer(actionURL, dataParam);
		}
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
			if(answer.trim()!="")
			{
				dataParam.FEED_BACK.push({QUESTION_NO : questionNo, QUESTION_ANS : answer});
			}
		}
		else
		{
			if(answer.trim()!="")
			{
				dataParam.FEED_BACK.push({QUESTION_NO : questionNo, QUESTION_ANS : answer});
			}
		}
	}
	function ValidateTextField()
	{
		var status = true;
		var isFocused = false;
		
		
		   
		    if($("#radio_excellent_9").is(':checked'))
			{
				if($('#radio_other_textarea_91').val()==null || $('#radio_other_textarea_91').val().trim()=="")
				{
					$('#radio_other_textarea_91').focus();
					isFocused = true;
					$('#radio_other_textarea_91').parent().parent().addClass('has-error has-danger');	
					status = false;
					
					return status;
				}
			}
		    
			
		return status;
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
	
	
	
	
	
	
	
</script>		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
						