<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="c.tld" prefix="c" %> 
<%@ taglib uri="commonutil.tld" prefix="utils"%>

<div class=" col-xs-12 col-md-12 summary-report-comments"> <!--added 26/03/18  -->
	<div class="summary-comments-header">
		<div class="summary-comments-tabs">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#"><s:text name="com.edupath.summary.report.commentsandsuggestions.counsellor.tab.label"/></a></li>
				<li><a href="#"><s:text name="com.edupath.summary.report.commentsandsuggestions.reviewer.tab.label"/></a></li>
			</ul>
		</div>
	</div>
	
	
	<!-- Start SASEDEVE Edited By Mrutyunjaya on date 06-06-2017 -->
	
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Observations.label"/>
		</div>
	</div>
	 <div class="summary-section-row">
		<div class="summary-comments-area-div summary-comments-label">
			<s:textarea maxlength="400" name="comments" id="comments"  cssClass="form-control summary-comments-textarea" ></s:textarea>
			<br/>
		<span id="Observations">400</span> characters remaining	
		</div>
	</div>
	
	
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Challenges.label"/>
		</div>
	</div>
	 <div class="summary-section-row">
		<div class="summary-comments-area-div summary-comments-label">
			<s:textarea maxlength="400" name="Challenges" id="Challenges"  cssClass="form-control summary-comments-textarea"  ></s:textarea>
			
			<br/>
		<span id="Challenges1">400</span> characters remaining	
		</div>
	</div>
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Approachtopathway1.label"/>
		</div>
	</div>
	 <div class="summary-section-row">
		<div class="summary-comments-area-div summary-comments-label">
			<s:textarea maxlength="400" name="Approachtopathway1" id="Approachtopathway1"  cssClass="form-control summary-comments-textarea"  ></s:textarea>
			
			<br/>
		<span id="Approachtopathway11">400</span> characters remaining	
		</div>
	</div>
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Approachtopathway2.label"/>
		</div>
	</div>
	 <div class="summary-section-row">
		<div class="summary-comments-area-div summary-comments-label">
			<s:textarea maxlength="400" name="Approachtopathway2" id="Approachtopathway2"  cssClass="form-control summary-comments-textarea"  ></s:textarea>
			
			<br/>
		<span id="Approachtopathway21">400</span> characters remaining	
		</div>
	</div>
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.Finalnotes.label"/>
		</div>
	</div>
	 <div class="summary-section-row">
		<div class="summary-comments-area-div summary-comments-label">
			<s:textarea maxlength="400" name="Finalnotes" id="Finalnotes"  cssClass="form-control summary-comments-textarea"  ></s:textarea>
			
			<br/>
		<span id="Finalnotes1">400</span> characters remaining	
		</div>
	</div>
	
	
	
	<div class="summary-report-clear-both">
		<div class="summary-comments-title">
			<s:text name="com.edupath.summary.report.SUPPLEMENTARYINFORMATION.label"/>
		</div>
	</div>
	 <div class="summary-section-row">
		<div class="summary-comments-area-div summary-comments-label">
			<s:textarea maxlength="800" name="SUPPLEMENTARYINFORMATION" id="SUPPLEMENTARYINFORMATION"  cssClass="form-control summary-comments-textarea"  ></s:textarea>
			
			<br/>
		<span id="SUPPLEMENTARYINFORMATION1">800</span> characters remaining	
		</div>
	</div>
	
	
	
	
	
	
	
	<!-- END SASEDEVE Edited By Mrutyunjaya on date 06-06-2017 -->
	
	
	
	
	
	<c:if test="${not empty bean.reviewComment}">
		<div class="summary-report-clear-both">
			<div class="summary-comments-title">
				<s:text name="com.edupath.summary.report.reviewercomment.label"/>
			</div>
		</div>
		<div class="summary-report-clear-both">
			<div class="summary-review-comments">
				${utils:replaceXMLEntities(bean.reviewComment)}
			</div>
		</div>
	</c:if>
	<div class="summary-section-row">
		<c:if test="${!bean.isSentForReview}">
			<div class="summary-comments-btn-div">
				<s:set name="btnVal"><s:text name='com.edupath.summary.report.submit.button'/></s:set>
				<s:submit cssClass="btn btn-sm comment-submit-btn" value="%{btnVal}" onclick="fnCounsellorSubmit()"/>
			</div>
		</c:if>
	</div>
	<s:form name="ReportReviewCommentForm" method="POST">
		<s:hidden name="review" id="review"></s:hidden>
	</s:form>
</div>

<!-- Start SASEDEVE Edited By Mrutyunjaya on date 11-05-2018 -->

<s:form name="autoGScomments" id="autoGScomments" method="post">
   <s:hidden name="acomments" id="acomments"></s:hidden>
   <s:hidden name="achallenges" id="achallenges"></s:hidden>
   <s:hidden name="aapproachtopathway1" id="aapproachtopathway1"></s:hidden>
   <s:hidden name="aapproachtopathway2" id="aapproachtopathway2"></s:hidden>
   <s:hidden name="afinalnotes" id="afinalnotes"></s:hidden>
   <s:hidden name="asupplementaryinformation" id="asupplementaryinformation"></s:hidden>
   </s:form>



<!-- END SASEDEVE Edited By Mrutyunjaya on date 11-05-2018 -->

<!-- Start SASEDEVE Edited By Mrutyunjaya on date 06-06-2017 -->

<script type="text/javascript">
var total_auto_save_runing=0;
	$(document).ready(function(){
		
		//Start SASEDEVE Edited By yogamaya on date 07-06-2018
		
		
		
		$("#comments").val('${utils:replaceJSONEntities(bean.comments)}');
		
		
		$("#Challenges").val('${utils:replaceJSONEntities(bean.challenges)}');
		$("#Approachtopathway1").val('${utils:replaceJSONEntities(bean.approachtopathway1)}');
		$("#Approachtopathway2").val('${utils:replaceJSONEntities(bean.approachtopathway2)}');
		$("#Finalnotes").val('${utils:replaceJSONEntities(bean.finalnotes)}');
		$("#SUPPLEMENTARYINFORMATION").val('${utils:replaceJSONEntities(bean.supplementaryinformation)}');
		
		//End SASEDEVE Edited By yogamaya on date 07-06-2018
		
		if('${bean.isSentForReview}' == 'true')
		{
			$("#comments").prop("disabled", true);
			$("#Challenges").prop("disabled", true);
			$("#Approachtopathway1").prop("disabled", true);
			$("#Approachtopathway2").prop("disabled", true);
			$("#Finalnotes").prop("disabled", true);
			$("#SUPPLEMENTARYINFORMATION").prop("disabled", true);
		}
		
		

		var maxLengthcomment = 400;
		var maxLengthcomment1 = 800;
		var a=/^[0-9A-Za-z.,']+$/;
		  var length = $('#comments').val().length;
		  var length = maxLengthcomment-length;
		  $('#Observations').text(length);
		  
		  
		  var length = $('#Challenges').val().length;
		  var length = maxLengthcomment-length;
		  $('#Challenges1').text(length);
		  
		  
		  var length = $('#Approachtopathway1').val().length;
		  var length = maxLengthcomment-length;
		  $('#Approachtopathway11').text(length);
		  
		  
		  var length = $('#Approachtopathway2').val().length;
		  var length = maxLengthcomment-length;
		  $('#Approachtopathway21').text(length);
		  
		  
		  var length = $('#Finalnotes').val().length;
		  var length = maxLengthcomment-length;
		  $('#Finalnotes1').text(length);
		  
		  
		  var length = $('#SUPPLEMENTARYINFORMATION').val().length;
		  var length = maxLengthcomment1-length;
		  $('#SUPPLEMENTARYINFORMATION1').text(length);
		  
			
		  //Start SASEDEVE Edited By yogamaya on date 06-06-2018
  
		
		$('#comments').keyup(function() {
			
		  var length = $(this).val().length;
		  var length = maxLengthcomment-length;
		  $('#Observations').text(length);
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
			
		$('#Challenges').keyup(function() {
			  var length = $(this).val().length;
			  var length = maxLengthcomment-length;
			  $('#Challenges1').text(length);
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
		
		$('#Approachtopathway1').keyup(function() {
			  var length = $(this).val().length;
			  var length = maxLengthcomment-length;
			  $('#Approachtopathway11').text(length);
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
		
		$('#Approachtopathway2').keyup(function() {
			  var length = $(this).val().length;
			  var length = maxLengthcomment-length;
			  $('#Approachtopathway21').text(length);
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
		$('#Finalnotes').keyup(function() {
			  var length = $(this).val().length;
			  var length = maxLengthcomment-length;
			  $('#Finalnotes1').text(length);
			  var yourInput = $(this).val();
				re = /[`~!@#$%^*()_|+\-=?;:<>\{\}\[\]\\\/]/gi;
				var isSplChar = re.test(yourInput);
				if(isSplChar)
				{
					alert("special characters are not allowed");
					var no_spl_char = yourInput.replace(/[`~!@#$%^*()_|+\-=?;:<>\{\}\[\]\\\/]/gi,'');	
					$(this).val(no_spl_char);
					
				}
				
			});	
		$('#SUPPLEMENTARYINFORMATION').keyup(function() {
			  var length = $(this).val().length;
			  var length = maxLengthcomment1-length;
			  $('#SUPPLEMENTARYINFORMATION1').text(length);
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
		// start by bharath 15/7/2019
		$(function(){ 
			if('${bean.isSentForReview}' != 'true')
			{
				setInterval("AutoSaveGScomments();",10000);	
			}
			
		});
		// end by bharath 15/7/2019
			});
	//END SASEDEVE Edited By yogamaya on date 06-06-2018

	function fnCounsellorSubmit()
	{
		if(total_auto_save_runing==0)
		{
		   var dataJSON = {comments : $('textarea[name=comments]').val(),challenges: $('textarea[name=Challenges]').val(),approachtopathway1: $('textarea[name=Approachtopathway1]').val(),approachtopathway2: $('textarea[name=Approachtopathway2]').val(),finalnotes: $('textarea[name=Finalnotes]').val(),supplementaryinformation: $('textarea[name=SUPPLEMENTARYINFORMATION]').val()};
		   fnDoAjax('${pageContext.request.contextPath}/myapp/saveCommentFinalSummaryAction', dataJSON); 
		}
	
	}
	function fnDoAjax(actionUrl, dataParam)
	{
		$.ajax({
			url : actionUrl,
			data : dataParam,
			type : "POST",
			dataType : "JSON",
			success: function(resp){
				if(resp != null && resp)
				{
					if(resp.STATUS == "SUCCESS")
					{
						alert(resp.MESSAGE);
						$('#comments').prop("disabled", true);
						$("#Challenges").prop("disabled", true);
						$("#Approachtopathway1").prop("disabled", true);
						$("#Approachtopathway2").prop("disabled", true);
						$("#Finalnotes").prop("disabled", true);
						$("#SUPPLEMENTARYINFORMATION").prop("disabled", true);
						$('.summary-comments-btn-div').hide();
					}
					else
					{
						alert(resp.MESSAGE);
					}
				}
			},
			error : function (arg0, arg1, arg2){
				alert(arg1);
			}
		});
	}
	
	//Start SASEDEVE Edited By Mrutyunjaya on date 11-05-2018	
	function AutoSaveGScomments()
	{	
		//start bharath on date 13-09-2019
		if($('#comments').val()!="" || $('#Challenges').val() != "" || $('#Approachtopathway1').val() != ""|| $('#Approachtopathway2').val() != ""|| $('#Finalnotes').val() != ""|| $('#SUPPLEMENTARYINFORMATION').val() != "" )
		{
			$('#acomments').val($('#comments').val());
			$('#achallenges').val($('#Challenges').val());
			$('#aapproachtopathway1').val($('#Approachtopathway1').val());
			$('#aapproachtopathway2').val($('#Approachtopathway2').val());
			$('#afinalnotes').val($('#Finalnotes').val());
			$('#asupplementaryinformation').val($('#SUPPLEMENTARYINFORMATION').val());
					
			var aform = document.autoGScomments;
			
			aform.action = "${pageContext.request.contextPath}/myapp/autoSaveCommentsofGS";
			var aoptions = { 
			        beforeSubmit:  ashowRequest,
			        success:       ashowResponse
			    };  
			$("#autoGScomments").ajaxSubmit(aoptions);
		}
		//end bharath on date 13-09-2019

		
	}
	function ashowRequest(formData, jqForm, options){
		
		total_auto_save_runing++;
		
	} 

	function ashowResponse(responseText, statusText, xhr, $form)
	{
		total_auto_save_runing--;
	}
	
	
	
	
	
	//END SASEDEVE Edited By Mrutyunjaya on date 11-05-2018
	
</script>

	<!-- END SASEDEVE Edited By Mrutyunjaya on date 06-06-2017 -->