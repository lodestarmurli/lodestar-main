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
		<div class="col-md-12">
			<!--<c:choose>
			<c:when test="${!session.UserSessionObject.aptitudeCompleted}">	
				<span class="pagetitle">Welcome to the Lodestar Engineering Branch Selector Test!</span>
			</c:when>
			<c:otherwise>
				<span class="pagetitle">Thank you for completing your tests!</span>
			</c:otherwise>
		</c:choose>-->
		</div>
	</div>
	<div id="mainPage_1">


		<div class="row">
			<div class="col-md-12 change_page_div_1">
				<c:choose>
					<c:when test="${saved==false}">
						<p class="change_p_h6">Thank You for completing the Interest
							and Aptitude Test. To help us to understand you better and to
							make a more accurate recommendation, we would like to know what
							your favourite subjects are from the list below.</p>
						<br>
						<p class="change_p_h4">Please rank it from 1 to 4 - Rank 1
							being most favourite and Rank 4 being least favourite.</p>
						<br>
						<div class="rows">
							<div class="col-md-12">
								<s:form cssClass="form-horizontal" name="FavsubSubmitForm"
									id="FavsubSubmitForm" method="post">
									<div class="form-body">
										<s:if test="hasActionErrors()">
											<div class="alert alert-danger ">
												<button class="close" data-dismiss="alert"></button>
												<s:actionerror />
											</div>
										</s:if>
										<div class="form-group">
											<label class="control-label col-md-3"> <s:text
													name="com.edupath.Engineering.Branch.subject.Physics"></s:text><span class="required" aria-required="true"> * </span>
											</label>
											<div class="col-md-2">
												<select name="physics" id="physics" class="form-control input-sm" value="%{physics}"  >
													<option value="-1">
														<s:text
															name="com.edupath.global.search.select.selected.title"></s:text>
													</option>
													<option value="1"> 1</option>
													<option value="2"> 2</option>
													<option value="3"> 3</option>
													<option value="4"> 4</option>
												</select> <span class="error-block physics-error"></span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3"> <s:text
													name="com.edupath.Engineering.Branch.subject.Chemistry"></s:text><span class="required" aria-required="true"> * </span>
											</label>
											<div class="col-md-2">
												<select name="chemistry" id="chemistry" class="form-control input-sm" value="%{chemistry}" >
													<option value="-1">
														<s:text
															name="com.edupath.global.search.select.selected.title"></s:text>
													</option>
													<option value="1"> 1</option>
													<option value="2"> 2</option>
													<option value="3"> 3</option>
													<option value="4"> 4</option>
												</select> <span class="error-block chemistry-error"></span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3"> <s:text
													name="com.edupath.Engineering.Branch.subject.Mathematics"></s:text><span class="required" aria-required="true"> * </span>
											</label>
											<div class="col-md-2">
												<select name="mathematics" id="mathematics" class="form-control input-sm" value="%{mathematics}"  >
													<option value="-1">
														<s:text
															name="com.edupath.global.search.select.selected.title"></s:text>
													</option>
													<option value="1"> 1</option>
													<option value="2"> 2</option>
													<option value="3"> 3</option>
													<option value="4"> 4</option>
												</select> <span class="error-block mathematics-error"></span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-3"> <s:text
													name="com.edupath.Engineering.Branch.subject.Biology"></s:text><span class="required" aria-required="true"> * </span>
											</label>
											<div class="col-md-2">
												<select name="biology" id="biology" class="form-control input-sm" value="%{biology}"  >
													<option value="-1">
														<s:text
															name="com.edupath.global.search.select.selected.title"></s:text>
													</option>
													<option value="1"> 1</option>
													<option value="2"> 2</option>
													<option value="3"> 3</option>
													<option value="4"> 4</option>
												</select> <span class="error-block biology-error"></span>
											</div>
										</div>
									</div>
								</s:form>
							</div>
						</div>
						<br>
						<p class="change_p_h6">Some of you may not have opted for
							biology. It is okay. Based on how much you like it, you can give
							it a rank.</p>
						<br>
						<button class="btn btn_action" onclick="fnSubmitPriority();" style="margin-right: 50px;">
			    	<span class="next-spn" style="padding-left:0px; padding-right:0px"><s:text name="com.edupath.common.label.submit"></s:text></span>
			   	</button>

					</c:when>
					<c:otherwise>
						<p class="change_p_h6">Thank You for completing the Interest
							and Aptitude Test. To help us to understand you better and to
							make a more accurate recommendation, we would like to know what
							your favourite subjects are from the list below.</p>
						<br>
						<p class="change_p_h4">Please rank it from 1 to 4 - Rank 1
							being most favourite and Rank 4 being least favourite.</p>
						<br>
						<div class="rows">
							<div class="col-md-12">
								<s:form cssClass="form-horizontal" name="studentSubmitForm"
									id="studentSubmitForm" method="post">
									<div class="form-body">
										<s:if test="hasActionErrors()">
											<div class="alert alert-danger ">
												<button class="close" data-dismiss="alert"></button>
												<s:actionerror />
											</div>
										</s:if>
										<div class="form-group">
											<label class="control-label col-md-3"> <s:text
													name="com.edupath.Engineering.Branch.subject.Physics"></s:text><span class="required" aria-required="true"> * </span>
											</label>
											<div class="col-md-2">
												<s:textfield cssClass="form-control input-sm" name="physics" id="physicstxt" value="%{physics}"  autocomplete="off" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3"> <s:text
													name="com.edupath.Engineering.Branch.subject.Chemistry"></s:text><span class="required" aria-required="true"> * </span>
											</label>
											<div class="col-md-2">
												<s:textfield cssClass="form-control input-sm" name="chemistry" id="chemistrytxt" value="%{chemistry}"  autocomplete="off" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3"> <s:text
													name="com.edupath.Engineering.Branch.subject.Mathematics"></s:text><span class="required" aria-required="true"> * </span>
											</label>
											<div class="col-md-2">
												<s:textfield cssClass="form-control input-sm" name="mathematics" id="mathematicstxt" value="%{mathematics}"  autocomplete="off" />
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-3"> <s:text
													name="com.edupath.Engineering.Branch.subject.Biology"></s:text><span class="required" aria-required="true"> * </span>
											</label>
											<div class="col-md-2">
												<s:textfield cssClass="form-control input-sm" name="biology" id="biologytxt" value="%{biology}"  autocomplete="off" />
											</div>
										</div>
									</div>
								</s:form>
							</div>
						</div>
						<br>
						<p class="change_p_h6">Some of you may not have opted for
							biology. It is okay. Based on how much you like it, you can give
							it a rank.</p>
						<br>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>



<div class="modal fade cust-stud-sesion-dialog"
		id="ShowCompleteStudentdetails" tabindex="-1" role="dialog"
		aria-hidden="true" style="margin-top: -10%;">
		
		<p></p>
		</div>
</div>

<style>
.change_back_btn {
	display: none;
}
</style>

<script>


$(document).ready(function(){
	   $('select').on('change', function(event ) {
		   console.log("steeeee")
	       //restore previously selected value
	       var prevValue = $(this).data('previous');
	       $('select').not(this).find('option[value="'+prevValue+'"]').show();
	       //hide option selected                
	       var value = $(this).val();
	       //update previously selected data
	       $(this).data('previous',value);
	       $('select').not(this).find('option[value="'+value+'"]').hide();
	       print($(this).attr('class'));
	   });
	   $("#physicstxt").prop("disabled", true);
	   $("#chemistrytxt").prop("disabled", true);
	   $("#mathematicstxt").prop("disabled", true);
	   $("#biologytxt").prop("disabled", true);
	   $("#ShowCompleteStudentdetails").removeClass('fade').modal('hide');
	   
	});

	function print(value)
	{
		console.log("value==>"+value)
	}
	function fnSubmitPriority() {
		var form = document.FavsubSubmitForm;
		if (isValidate()) {
			if(confirm('Are you sure you want to submit the priorities'))
			{
				form.action = "${pageContext.request.contextPath}/myapp/saveEBFavouriteSubjectAction";
				form.submit();
			}
		}
	}
	
	function isValidate() 
	{
		$('.error-block').html('');
		var physics= $("#physics").val();
		var chemistry= $("#chemistry").val();
		var mathematics= $("#mathematics").val();
		var biology= $("#biology").val();
		if(physics==-1)
		if (null == physics || physics == '' || physics == undefined || physics == -1)
		{
			$('.physics-error').html("Rank physics");
			return false;
		}
		if (null == chemistry || chemistry == '' || chemistry == undefined || chemistry == -1)
		{
			$('.chemistry-error').html("Rank chemistry");
			return false;
		}
		if (null == mathematics || mathematics == '' || mathematics == undefined || mathematics == -1)
		{
			$('.mathematics-error').html("Rank mathematics");
			return false;
		}
		if (null == biology || biology == '' || biology == undefined || biology == -1)
		{
			$('.biology-error').html("Rank biology");
			return false;
		}
		return true;
		
	}
	
</script>