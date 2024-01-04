<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="c.tld" prefix="c" %>
<%@ taglib uri="commonutil.tld" prefix="utils"%>

<div class="summary-report-comments">
	<div class="summary-comments-header">
		<div class="summary-comments-tabs">
			<ul class="nav nav-tabs">
				<li ><a href="#"><s:text name="com.edupath.summary.report.commentsandsuggestions.counsellor.tab.label"/></a></li>
				<li class="active"><a href="#"><s:text name="com.edupath.summary.report.commentsandsuggestions.reviewer.tab.label"/></a></li>
			</ul>
		</div>
	</div>
	
	
	
	<!-- Start SASEDEVE Edited By Mrutyunjaya on date 06-06-2017 -->
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Observations.label"/>
		</div>
	</div> 
	<c:if test="${not empty bean.reviewComment}">
		<div class="summary-report-clear-both">
			<div class="summary-review-comments">
				${utils:replaceXMLEntities(bean.reviewComment)}
			</div>
		</div>
	</c:if>
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Challenges.label"/>
		</div>
	</div> 
	<c:if test="${not empty bean.reviewComment}">
		<div class="summary-report-clear-both">
			<div class="summary-review-comments">
				${utils:replaceXMLEntities(bean.reviewchallenges)}
			</div>
		</div>
	</c:if>
	
	
	
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Approachtopathway1.label"/>
		</div>
	</div> 
	<c:if test="${not empty bean.reviewComment}">
		<div class="summary-report-clear-both">
			<div class="summary-review-comments">
				${utils:replaceXMLEntities(bean.reviewapproachtopathway1)}
			</div>
		</div>
	</c:if>
	
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Approachtopathway2.label"/>
		</div>
	</div> 
	<c:if test="${not empty bean.reviewComment}">
		<div class="summary-report-clear-both">
			<div class="summary-review-comments">
				${utils:replaceXMLEntities(bean.reviewapproachtopathway2)}
			</div>
		</div>
	</c:if>
	
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Finalnotes.label"/>
		</div>
	</div> 
	<c:if test="${not empty bean.reviewComment}">
		<div class="summary-report-clear-both">
			<div class="summary-review-comments">
				${utils:replaceXMLEntities(bean.reviewfinalnotes)}
			</div>
		</div>
	</c:if>
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.SUPPLEMENTARYINFORMATION.label"/>
		</div>
	</div> 
	<c:if test="${not empty bean.reviewComment}">
		<div class="summary-report-clear-both">
			<div class="summary-review-comments">
				${utils:replaceXMLEntities(bean.reviewsupplementaryinformation)}
			</div>
		</div>
	</c:if>
	
	
	
	
	
	
	
	
	
	
	<!-- END SASEDEVE Edited By Mrutyunjaya on date 06-06-2017 -->
	
	
	
	<div class="summary-section-row">
		<div class="summary-comments-area-div">
			<div class="summary-comments-title">
				<s:text name="com.edupath.summary.report.reviewercomment.label"/>
			</div>
			<s:textarea name="comments" id="comments" cssClass="form-control summary-comments-textarea"></s:textarea>
		</div>
	</div>
	<div class="summary-section-row">
		<div class="summary-final-btn-div">
			<s:set name="finalizebtnVal"><s:text name='com.edupath.summary.report.finalize.button'/></s:set>
			<s:submit cssClass="btn btn-sm comment-review-btn" value="%{finalizebtnVal}" onclick="fnFinalize()"/>
		</div>
		<div class="summary-back-btn-div">
			<s:set name="sendBackbtnVal"><s:text name='com.edupath.summary.report.sendback.button'/></s:set>
			<s:submit cssClass="btn btn-sm comment-review-btn" value="%{sendBackbtnVal}" onclick="fnSendBack()"/>
		</div>
	</div>
	<s:form name="ReportReviewCommentForm" method="POST">
		<s:hidden name="review" id="review"></s:hidden>
	</s:form>
</div>


<!-- Start SASEDEVE Edited By Mrutyunjaya on date 11-05-2018 -->

<s:form name="autoReviewcomments" id="autoReviewcomments" method="post">
   <s:hidden name="avcomments" id="avcomments"></s:hidden>
   </s:form>



<!-- END SASEDEVE Edited By Mrutyunjaya on date 11-05-2018 -->




<script type="text/javascript">
	$(document).ready(function(){
		$("#comments").val('${utils:replaceJSONEntities(bean.comments)}');
	});
	
	$(function(){
		 
		$('#comments').keyup(function()
		{
			var yourInput = $(this).val();
			re = /[`~!@#$%^*()_|+\-=?;:<>\{\}\[\]\\\/]/gi;
			var isSplChar = re.test(yourInput);
			if(isSplChar)
			{
				alert("special characters are not allowed");
				var no_spl_char = yourInput.replace(/[`~!@#$%^*()_|+\-=?;:<>\{\}\[\]\\\/]/gi, '');
				$(this).val(no_spl_char);
			}
		});
	 
	});
	// start by bharath 15/7/2019
$(function()
{ 
	setInterval("AutoSaveReviewcomments();",60000);
});
//end by bharath 15/7/2019
	
	function fnSendBack()
	{
		var reportReviewForm = document.ReportReviewCommentForm;
		var comments = $('textarea[name=comments]').val();
		$("#review").val(comments);
		
		if(comments == '')
		{
			alert("Please write review comments");
			return false;
		}
		if (confirm('Are you sure you want to send back?')) 
		{
			reportReviewForm.action = "${pageContext.request.contextPath}/myapp/insertReportReviewSubmitAction";
			reportReviewForm.submit();
		}
	}

	function fnFinalize()
	{
		var reportReviewForm = document.ReportReviewCommentForm;
		if (confirm('Are you sure you want to Finalize?')) 
		{
			reportReviewForm.action = "${pageContext.request.contextPath}/myapp/finalizeReportReportReviewSubmitAction";
			reportReviewForm.submit();
		}
	}
	//Start SASEDEVE Edited By Mrutyunjaya on date 11-05-2018	
	function AutoSaveReviewcomments()
	{
		$('#avcomments').val($('#comments').val());
		
		var avform = document.autoReviewcomments;
		
		avform.action = "${pageContext.request.contextPath}/myapp/autoSaveCommentsofReview";
		var avoptions = { 
		        beforeSubmit:  avshowRequest,
		        success:       avshowResponse
		    };  
		$("#autoReviewcomments").ajaxSubmit(avoptions);
		
	}
	function avshowRequest(formData, jqForm, options){
	} 

	function avshowResponse(responseText, statusText, xhr, $form)
	{
		 
	}
	
	
	
	
	
	//END SASEDEVE Edited By Mrutyunjaya on date 11-05-2018	
	
	
	
	
	
</script>