<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="c.tld"%>
<s:hidden name="parentSelectedSidebarMenuId"
	id="parentSelectedSidebarMenuId" />
<s:hidden name="childSelectedSidebarMenuId"
	id="childSelectedSidebarMenuId" />
<s:form name="ReportForm" id="ReportForm" theme="simple">
	<s:hidden name="ReportstudentId" id="ReportstudentId"></s:hidden>
</s:form>
<div class="edupath-padding-nonchild">
	<div class="row">
		<div class="col-md-12"><span class="pagetitle">Thank you for completing the Interest & Aptitude test. </span></div>
	</div>
	<div id="mainPage_1">


		<div class="row">
			<div class="col-md-12 change_page_div_1">

						<p class="change_p_h4"><b>Please confirm your class once again.</b></p>
						<br>
						
								<form name="Streamclass" id="Streamclass" method="post">
								<s:hidden id="studentId" name="studentId"></s:hidden>
								<s:hidden id="classId" name="classId"></s:hidden>
								<s:hidden id="stream" name="stream"></s:hidden>
									<div>
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="className" id="className" value="1">
											<label>9<sup>th</sup> - studying in 9<sup>th</sup> or in-between 9<sup>th</sup> and 10<sup>th</sup></label>
										</div>
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="className" id="className" value="2">
											<label>10<sup>th</sup> - Studying in 10<sup>th</sup> or in-between 10<sup>th</sup> and 11<sup>th</sup> </label>
										</div>
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="className" id="className" value="3">
											<label>11<sup>th</sup></label>
										</div>
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="className" id="className" value="4">
											<label >12<sup>th</sup></label>
										</div>
										<!--
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="className" id="className" value="5">
											<label>12<sup>th</sup>  Plus - Passed twelfth and joined degree. </label>
										</div>
										-->
									</div>
									<br>
									<p class="change_p_h4"><b>Confirm the stream of your study
										(only for 11th/12th & 12th plus students)</b></p>
									<br>

									<div>
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="StreamName" id="StreamName" class="Stream" value="1">
											<label for="Science with Maths"><s:text
													name="Science with Maths" /></label>
										</div>
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="StreamName" id="StreamName" class="Stream" value="2">
											<label for="Science with Biology"><s:text
													name="Science with Biology" /></label>
										</div>
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="StreamName" id="StreamName" class="Stream" value="3">
											<label for="Commerce with Maths"><s:text
													name="Commerce with Maths" /></label>
										</div>
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="StreamName" id="StreamName" class="Stream"  value="4">
											<label for="Commerce without Maths"><s:text
													name="Commerce without Maths" /></label>
										</div>
										<div class="radio radio-success cust-session-radio-div" style="margin-left: 60px !important;">
											<input type="radio" name="StreamName" id="StreamName" class="Stream"  value="5">
											<label for="Arts"><s:text name="Arts" /></label>
										</div>

									</div>
									<br>
									<button type="button" class="btn blue" onclick="fnSubmit();">
										<i class="fa fa-check"></i>
										<s:text name="com.edupath.common.label.submit" />
									</button>
								</form>
					</div>
		</div>
	</div>




</div>
 
<style>
.change_back_btn {
	display: none;
}
</style>

<script>

$(document).ready(function() {

		
		
		console.log("StreamName=>=>" +$('#StreamName').val())
		console.log("className=>=>" + $('#StreamName').val())
		$('input[name=className]').change(function ()
		{
			var StreamName = $('input:radio[name=StreamName]:checked').val();
			var className = $('input:radio[name=className]:checked').val();
			console.log("StreamName=>" +StreamName)
			console.log("className=>" + className)
			if(className == 1 || className == 2)
			{
			        var x = document.getElementsByClassName("Stream");
                    var i;
                    for (i = 0; i < x.length; i++) {
                        x[i].disabled = true;
                    }
 			}
			else if(className == 3 || className == 4 || className == 5)
			{
                    var x = document.getElementsByClassName("Stream");
                    var i;
                    for (i = 0; i < x.length; i++) {
                        x[i].disabled = false;
                    }
			}
		});
		 

	});

	function fnSubmit() {
		var form = document.Streamclass;
		var StreamName = $('input:radio[name=StreamName]:checked').val();
		var className = $('input:radio[name=className]:checked').val();
		console.log("StreamName=>" +StreamName )
		console.log("className=>" + className)
	 	if(validate())
	 	{
			$('#stream').val(StreamName);
			$('#classId').val(className);
			if(className == 1 || className == 2 )
			{
				$('#stream').val(null);
			}
			
			form.action = "${pageContext.request.contextPath}/myapp/saveCCDStreamSelectAction";
			form.submit();
	 	}
	}
	function validate()
	{
		console.log("step 1")
		var StreamName = $('input:radio[name=StreamName]:checked').val();
		var className = $('input:radio[name=className]:checked').val();
		console.log("StreamName=>" +StreamName )
		console.log("className=>" + className)
		console.log("step 2")
		if(className == undefined || className <= 0 || className == -1)
		{
			console.log("step 3")
			alert("Select your class");
			return false;
		}
		if(className == 3 || className == 4 || className == 5)
		{
			console.log("step 4")
				if(StreamName == undefined || StreamName <= 0 || StreamName == -1)
				{
					console.log("step 5")
					alert("Select your Stream");
					return false;
				}
		}
		return true;
	}
</script>