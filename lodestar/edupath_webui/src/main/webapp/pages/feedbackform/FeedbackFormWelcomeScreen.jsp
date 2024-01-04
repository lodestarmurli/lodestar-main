<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="c.tld"%>

<s:set name="parentFromAction" value="#"/>
<s:set name="studentFromAction" value="#"/>

<div class="feedback-form-main">
	<div class="row filter-margin-top">
		<div class="col-md-12 col-xs-12 college-search-header">  <!-- 23/03/18 added col-xs-12 -->
			<s:text name="com.edupath.feedbackform.title"/> 
		</div>
	</div>
	<div class="feedback-form-card">
		<div class="feedback-form-welcome">
			<p><b>Thank you for completing the Lodestar career guidance program!</b></p>
			<p>You can view all the industry, occupation, education pathway, college and tutorial, discussed with you in the three sessions 
				<a href="#" onclick="fnShowStudentCart(this , '${session.UserSessionObject.id}', 'student');">here</a>.
			</p>
			<p>We hope our unique and scientific career guidance program has helped you find your true career path. 
				We also hope that the career guidance specialist assigned to you has facilitated you career and education path discovery successfully.
			</p>
			<p>We are continuously working with parents and students to improve the quality of our program. Your feedback about the program will help us immensely in this endeavour.</p>
			<p>We are working on your summary report. Your report will be available to you within <b>7 working days</b> of completing the feedback.</p>
		</div>
		<div class="feedback-form-list">
			<div class="feedback-form-list-item">
				<div class="feedback-form-icon">
					<img alt="Parents" class="feedback-patent-image" src="${pageContext.request.contextPath}/images/icons/parents.png">
				</div>
				<div class="feedback-form-link">
				   	<s:if test="%{model.parentFeedbackStatus}">
						<a href="#" class="feedback-patent-label">
							<s:text name="com.edupath.feedbackform.parent.label"/>
						</a>
					</s:if>
					<s:else>
						<a href="#" class="feedback-patent-label" data-href-url="${pageContext.request.contextPath}/myapp/showParentFeedbackFormAction">
							<s:text name="com.edupath.feedbackform.parent.label"/>
						</a>
					</s:else>
				</div>
				<div class="feedback-form-status">
					<s:if test="%{model.parentFeedbackStatus}">
						<img alt="Complete" src="${pageContext.request.contextPath}/images/icons/complete.png">
						<label><s:text name="com.edupath.feedbackform.completed.label"/></label>
					</s:if>
					<s:else>
						<img alt="Pending" src="${pageContext.request.contextPath}/images/icons/pending.png">
						<label><s:text name="com.edupath.feedbackform.pending.label"/></label>
					</s:else>
				</div>
			</div>
			<div class="feedback-form-list-item">
				<div class="feedback-form-icon">
					<img alt="Student" class="feedback-patent-image" src="${pageContext.request.contextPath}/images/icons/student.png">
				</div>
				<div class="feedback-form-link">
					<s:if test="%{model.studentFeedbackStatus}">
						<a href="#">
							<s:text name="com.edupath.feedbackform.student.label"/>
						</a>
					</s:if>
					<s:else>
						<a href="#" data-href-url="${pageContext.request.contextPath}/myapp/showStudentFeedbackFormAction">
							<s:text name="com.edupath.feedbackform.student.label"/>
						</a>
					</s:else>
				</div>
				<div class="feedback-form-status">
					<s:if test="%{model.studentFeedbackStatus}">
						<img alt="Complete" src="${pageContext.request.contextPath}/images/icons/complete.png">
						<label><s:text name="com.edupath.feedbackform.completed.label"/></label>
					</s:if>
					<s:else>
						<img alt="Pending" src="${pageContext.request.contextPath}/images/icons/pending.png">
						<label><s:text name="com.edupath.feedbackform.pending.label"/></label>
					</s:else>
				</div>
			</div>
		</div>
		<div class="feedback-proceed-button">
			<button id="proceed_summary" class="btn btn-lg"><s:text name="com.edupath.feedbackform.proceedtosummary.button.label"/></button>
		</div>
	</div>
</div>
<s:form method="POST" name="FeedbackFormRedirectFrom">
	<s:hidden name="parentSelectedSidebarMenuId"/>
	<s:hidden name="childSelectedSidebarMenuId"/>
</s:form>

<s:form method="POST" name="ProceedToSummaryFrom" id="ProceedToSummaryFrom" action="/myapp/showSummaryForStudentFinalSummaryAction">
</s:form>

<script type="text/javascript">
	var form = document.FeedbackFormRedirectFrom;
	
	$(document).ready(function(){
		if('${model.studentFeedbackStatus and model.parentFeedbackStatus}' == 'true')
		{
			initProceesSummaryBtn();
		}
	});
	
	function initProceesSummaryBtn()
	{
		$("#proceed_summary").addClass("proceed_summary_btn");
		$("#proceed_summary").click(function(){
			
			//Start SASEDEVE Edited By Mrutyunjaya on Date 19-06-2017
			
			$.ajax({
				  url: "${pageContext.request.contextPath}/jsonapp/sendingfinalreportemail",
				  data:{
					  		
					  	},
				  dataType:"json",
				  success: function(result){
					  $('#ProceedToSummaryFrom').submit();
				  },
				  error: function(jqXHR, textStatus, errorThrown)
				  {
					  $('#ProceedToSummaryFrom').submit();
				  }
			});
			
		//End SASEDEVE Edited By Mrutyunjaya on Date 19-06-2017
			
		});
	}
	
	$('.feedback-form-link > a').click(function(){
		if($(this).data("hrefUrl")){
			form.action = $(this).data("hrefUrl");
			form.submit();
		}
	});
</script>

