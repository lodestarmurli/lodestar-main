<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib prefix="c" uri="c.tld"%>
<% try{ %>
<c:set value="none;" var="display"></c:set>
<c:set value="" var="displayTest"></c:set>
<c:set value="show" var="redioShow"></c:set>
<c:if test="${studentDTO.studentType.name == 'FULL'}">
	<c:set value="" var="display"></c:set>
	<c:set value="none;" var="displayTest"></c:set>
</c:if>
<c:if test="${studentDTO.studentType.name == 'FULL' and actionType eq 'modify'}">
	<c:set value="hide" var="redioShow"></c:set>
</c:if>
<div class="edupath-padding-summary">
<div class="rows">
	<div class="col-md-12">

		<!-- BEGIN PORTLET-->

		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa   fa-edit"></i>
					<s:text name="com.edupath.student.detail.title" />
				</div>

				<div class="actions">
					<a href="#" class="btn btn-default btn-sm" onclick="goBack()">
						<i class="fa fa-back"></i> <s:text
							name="com.edupath.common.label.back"></s:text>
					</a>
				</div>
			</div>

			<!-- BEGIN FORM -->
			<div class="portlet-body">
				<!-- BEGIN FORM-->
				<s:form cssClass="form-horizontal" name="studentSubmitForm"
					id="studentSubmitForm" method="post">
					<s:hidden id="userId" name="userId"></s:hidden>
					<s:hidden id="studentId" name="studentId"></s:hidden>
					<s:hidden name="parentSelectedSidebarMenuId"
						id="parentSelectedSidebarMenuId" />
					<s:hidden name="childSelectedSidebarMenuId"
						id="childSelectedSidebarMenuId" />
					<s:hidden name="session1DateStr" id="session1DateStr" />
					<s:hidden name="session2DateStr" id="session2DateStr" />
					<s:hidden name="session3DateStr" id="session3DateStr" />
					<div class="form-body">
						<div id="error_alertId" style="display: none;">
						</div>
						<s:if test="hasActionErrors()">
							<div class="alert alert-danger ">
								<button class="close" data-dismiss="alert"></button>
								<s:actionerror />
							</div>
						</s:if>

						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.summary.name"></s:text> <span
								class="required" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm" name="name"
									id="name" value="%{studentDTO.name}" autocomplete="off" />
								<span class="error-block name-error"> </span>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.gender"></s:text> <span
								class="required" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
								<s:radio cssClass="radio-list" name="gender" id="gender"
									list="#{'M':'Male','F':'Female'}" value="%{studentDTO.gender}" />
								<span class="error-block gender-error"> </span>
							</div>
						</div>

						

						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.city"></s:text><span
								class="required" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
								<s:select cssClass="form-control input-sm" name="cityId"
									id="cityId" listKey="id" listValue="name" list="cityList"
									headerKey="-1" headerValue="--Select--"
									value="%{studentDTO.cityId}" onchange="fnOnCityIdChange(this.value)" />
								<span class="error-block city-error"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.school"></s:text><span
								class="required" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
								<select name="schoolId" id="schoolId" class="form-control input-sm" value="%{studentDTO.schoolId}" onchange="fnSchoolChangeother();" >
									<option value="-11">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
								</select>
								<s:textfield name="otherSchool" id="otherSchool" class="form-control input-sm otherSchool" cssStyle="display:none; margin-top:10px;" maxlength="100"></s:textfield>
								<span class="error-block school-error"></span>
								<span  id="school-error error-block" style="font-size:12px;"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.class"></s:text><span
								class="required" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
								<select name="classId" id="classId" class="form-control input-sm" onchange="fnchangeRequiredField(this.value);" >
									<option value="-1">--Select--</option>
									<c:if test="${null ne classList and not empty classList}">
										<c:forEach items="${classList}" var="classItem">
											<option value="${classItem.id}" data-gapkey="${classItem.gap}">${utils:replaceXMLEntities(classItem.name)}</option>
										</c:forEach>
									</c:if>
								</select>	
								<span class="error-block class-error"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.section"></s:text>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm" name="section"
									id="section" value="%{studentDTO.section}"
									autocomplete="off" maxlength="2"/>
								<span class="error-block section-error"> </span>
							</div>
						</div>


						<div class="form-group" >
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.studentemail"></s:text><span
								class="required showstudentstar" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm"
									name="studentemailId" id="studentemailId"
									value="%{studentDTO.studentemailId}" autocomplete="off" />
								<span class="error-block studentemail-error"></span>
							</div>
						</div>
						
						<div class="form-group" > 
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.studentcontactno"></s:text><span
								class="required showstudentstar" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm"
									name="studentcontactNumber" id="studentcontactNumber"
									value="%{studentDTO.studentcontactNumber}" autocomplete="off" />
								<span class="error-block studentcontact-error"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.fatherName"></s:text> <span
								class="required showfatherstar" aria-required="true">  </span>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm" name="fathername"
									id="fathername" value="%{studentDTO.fatherName}"
									autocomplete="off" />
								<span class="error-block fathername-error"> </span>
							</div>
						</div>

						
						
						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.fathertrialemail"></s:text><span
								class="required showfatherstar" aria-required="true"> </span>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm"
									name="fatheremailId" id="fatheremailId"
									value="%{studentDTO.fatherEmailId}" autocomplete="off" />
								<span class="error-block email-error"></span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.fathecontactno"></s:text>
									<span
								class="required showfatherstar" aria-required="true">  </span>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm"
									name="contactNumber" id="contactNumber"
									value="%{studentDTO.fathercontactno}" autocomplete="off" />
								<span class="error-block contact-error"></span>
							</div>
						</div>

						
						
						
						
						<!--  Vyankatesh Edit Add field-->
						
						
						
						<div class="form-group " >
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.motherName"></s:text><span
								class="required showparentstar" aria-required="true">  </span>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm"
									name="motherName" id="motherName"
									value="%{studentDTO.motherName}" autocomplete="off" /> 
								<span class="error-block motherName"></span>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.motheremail"></s:text><span
								class="required showparentstar" aria-required="true">  </span>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm"
									name="motheremailId" id="motheremailId"
									value="%{studentDTO.motheremailId}" autocomplete="off" />
								<span class="error-block motheremail"></span>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.mothercontactno"></s:text><span
								class="required showparentstar" aria-required="true">  </span>
							</label>
							<div class="col-md-4">
								<s:textfield cssClass="form-control input-sm"
									name="mothercontactno" id="mothercontactno"
									value="%{studentDTO.mothercontactno}" autocomplete="off" />
								<span class="error-block mothercontactno"></span>
							</div>
						</div>
						<!--  Vyankatesh Edit Add field-->
						

						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.computerfacility"></s:text><span
								class="required" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
								<s:radio cssClass="radio-list" id="computerFacility" name="computerFacility"
									list="#{'true':'Yes','false':'No'}"
									value="%{studentDTO.computerFacility}"></s:radio>
								<span class="error-block computerFacility-error"></span>
							</div>
						</div>

						<div class="form-group session-cls" style="display: ${display}">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.selectfacilitator"></s:text><span
								class="required" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
								<!-- <s:select cssClass="form-control input-sm" name="facilitatorId" id="facilitatorId" listKey="id" listValue="name" list="facilitatorList" headerKey="-1" headerValue="--Select--" value="%{studentDTO.facilitatorId}" /> -->
								<select name="facilitatorId" id="facilitatorId" class="form-control input-sm"  value="%{studentDTO.facilitatorId}"  onchange="fnFacititaorVenue();" >
									<option value="-1">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
								</select>
								
								<span class="error-block facilitator-error"></span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.type"></s:text><span
								class="required" aria-required="true"> * </span>
							</label>
							<div class="col-md-4">
									<c:choose>
										<c:when test="${redioShow == 'show'}">
											<c:forEach items="${studentTypeEnum}" var="typeEnum">
												<input type="radio" class="radio-list studentType-cls" id="studentType_${typeEnum.name}" name="studentType" value="${typeEnum.name}" ${typeEnum.name == studentType ? 'checked' : ''}>
												<label>${typeEnum.text}</label> &nbsp;&nbsp;
											</c:forEach>
										</c:when>
										<c:otherwise>
											<input type="hidden" name="studentType" value="${studentType}">
											<label class="control-label">${studentType.text}</label>
										</c:otherwise>
									</c:choose>
									
								<span class="error-block studentType-error"></span>
							</div>
						</div>
						
						<div class="row test-cls" style="display: ${displayTest}">
							<label class="control-label col-md-3"> <s:text
									name="com.edupath.student.detail.test"></s:text><span
								class="required" aria-required="true"></span>
							</label>
							<div class="col-md-4">
									<c:forEach items="${studentTestEnum}" var="testEnum">
										<input type="radio" class="radio-list testTaken-cls" id="studentType_${testEnum.name}" name="testTaken" value="${testEnum.name}" ${testEnum.name == testTaken ? 'checked' : ''}>
										<label>${testEnum.text}</label> &nbsp;&nbsp;
									</c:forEach>
							</div>
						</div>
						
						<div class="row session-cls" style="display: ${display}">
							<div class="col-md-6">
								<div class="form-group">

									<label class="control-label col-md-6"><s:text
											name="com.edupath.student.summary.session1" /> <span
										class="required" aria-required="true">*</span> </label>
									<div class="col-md-4">
										<div class="input-icon">
											<i class="fa fa-calendar"></i>
											<s:textfield cssClass="form-control datepicker input-small"
												id="session1_date" name="session1DateValue" readonly="readonly"
												autocomplete="off" />
											<%-- <span class="error-block session1Date-error"></span> --%>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-3"><s:text
											name="com.edupath.student.detail.time" /></label>
									<div class="col-md-4">
										<div class="input-icon">
											<i class="fa fa-clock-o"></i>
											<%-- <s:textfield cssClass="form-control timepicker input-small"
												id="session1_time" name="session1Time" autocomplete="off" /> --%>
												<s:select list="timeList" cssClass="form-control input-small"
												id="session1_time" name="session1Time" autocomplete="off"></s:select>
											<%-- <span class="error-block session1Time-error"></span> --%>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group session-cls" style="display: ${display}">
							<label class="control-label col-md-3"> 
							</label>
							<div class="col-md-4">
								<span class="error-block session1Date-error"></span>
							</div>
						</div>
						<div class="row session-cls" style="display: ${display}">
							<div class="col-md-6">
								<div class="form-group">

									<label class="control-label col-md-6"><s:text
											name="com.edupath.student.summary.session2" /> <span
										class="required" aria-required="true">*</span> </label>
									<div class="col-md-4">
										<div class="input-icon">
											<i class="fa fa-calendar"></i>
											<s:textfield cssClass="form-control datepicker input-small"
												id="session2_date" name="session2DateValue" autocomplete="off" />
											<%-- <span class="error-block session2Date-error"></span> --%>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4 session-cls" style="display: ${display}">
								<div class="form-group">
									<label class="control-label col-md-3"><s:text
											name="com.edupath.student.detail.time" /></label>
									<div class="col-md-4">
										<div class="input-icon">
											<i class="fa fa-clock-o"></i>
											<%-- <s:textfield cssClass="form-control timepicker input-small"
												id="session2_time" name="session2Time" autocomplete="off" /> --%>
												<s:select list="timeList" cssClass="form-control input-small"
												id="session2_time" name="session2Time" autocomplete="off"></s:select>
											<%-- <span class="error-block session2Time-error"></span> --%>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group session-cls" style="display: ${display}">
							<label class="control-label col-md-3"> 
							</label>
							<div class="col-md-4">
								<span class="error-block session2Date-error"></span>
							</div>
						</div>
						<div class="row session-cls session3" style="display: ${display}">
							<div class="col-md-6">
								<div class="form-group">

									<label class="control-label col-md-6"><s:text
											name="com.edupath.student.summary.session3" /> <span
										class="required" aria-required="true">*</span> </label>
									<div class="col-md-4">
										<div class="input-icon">
											<i class="fa fa-calendar"></i>
											<s:textfield cssClass="form-control datepicker input-small"
												id="session3_date" name="session3DateValue" autocomplete="off" />
											<%-- <span class="error-block session3Date-error"></span> --%>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-md-3"><s:text
											name="com.edupath.student.detail.time" /></label>
									<div class="col-md-4">
										<div class="input-icon">
											<i class="fa fa-clock-o"></i>
											<%-- <s:textfield cssClass="form-control timepicker input-small"
												id="session3_time" name="session3Time" autocomplete="off" /> --%>
												<s:select list="timeList" cssClass="form-control input-small"
												id="session3_time" name="session3Time" autocomplete="off"></s:select>
											<%-- <span class="error-block session3Time-error"></span> --%>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group session-cls" style="display: ${display}">
							<label class="control-label col-md-3"> 
							</label>
							<div class="col-md-4">
								<span class="error-block session3Date-error"></span>
							</div>
						</div>
						<div class="form-group session-cls" style="display: ${display}">
							<label class="control-label col-md-3"> 
							  <s:text name="com.edupath.student.venue"></s:text>
							</label>
							<div class="col-md-4">
								<select class="form-control input-sm" id="venueSelect" onchange="fnVenueSelect(this);">
								<option value="AT SCHOOL">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
								
							       <!--   <option value="AT SCHOOL"><s:text name="com.edupath.student.at.school.select"></s:text></option>
							        <option value="AT OFFICE"><s:text name="com.edupath.student.at.office.select"></s:text></option>
							        <option value="OTHER" data-addr="#643, 11th main, Opp Indiranagar park, Indiranagar">
							        	Indiranagar (HEAD HELD HIGH)
							        </option>
							        <option value="OTHER" data-addr="Domain, 1st Floor,Shyam Complex, Opp Mother Hood Hospital, CMH Road. Near Metro Station">
							        	Indiranagar (CMH)
							        </option>
							        <option value="OTHER" data-addr="Simplilearn, 3rd floor, Above DOORS Prayer hall, Opp Kammanahalli Church, Kammanahalli Main Road">
							        	Kammanahalli
							        </option>
							        <option value="OTHER" data-addr="Systems Domain Pvt. Ltd. 54/54, 2nd Floor, Above Hotel Aramane, P.R Complex, 17th Cross, Next to Canara Bank, M.C. Road, Vijaynagar, Bangalore- 560040">
							        	Vijaynagar
							        </option>
							        <option value="OTHER" data-addr="Systems Domain Pvt. Ltd. # 150; Malleswaram Arcade, S-3,2nd Floor B/W 7th & 8th Cross, Margosa Road, Near Malleshwaram club, Bangalore- 560003">
							        	Malleshwaram
							        </option>
							        <option value="OTHER" data-addr="NMIMS, 2735, BDA House, EBlock, Sahakarnagar, Behind Swathi Gardenia restaurant, Bangalore">
							        	Sahakar Nagar
							        </option>
							        <option value="OTHER" data-addr="Aptech Training Center, 80Ft Rd, Opp New Shanthi Sagar, RT Nagar">
							        	RT Nagar
							        </option>
							        <option value="OTHER"><s:text name="com.edupath.student.at.other"></s:text></option>-->
								</select>
							</div>
						</div>
						
						<div class="form-group session-cls" style="display: ${display}">
							<label class="control-label col-md-3"> 
							</label>
							<div class="col-md-4" id="venueDIV" style="display: none;">
							 <c:choose>
							 	<c:when test="${null ne studentDTO.venue and not empty  studentDTO.venue}">
							  		<s:hidden name="venue" id="venue" value="%{studentDTO.venue}"></s:hidden>
							  	    <s:textarea name="venueAddr" id="venueAddr" value="%{studentDTO.venue}" cssClass="form-control input-lg" onchange="fnSetTextData(this)"/>
								 </c:when>
							 	<c:otherwise>
							    	<s:hidden name="venue" id="venue" value="ATSCHOOL"></s:hidden>
							        <s:textarea name="venueAddr" id="venueAddr"  cssClass="form-control input-lg" onchange="fnSetTextData(this)"/>
							 	</c:otherwise>
							 </c:choose>
							  <span class="error-block venue-error"></span>
							</div>
						</div>
						
						<!-- Sasedeve  start added by vyankatesh  -->
						
						
						<div class="row session-cls" style="display: ${display}">
							<div class="col-md-6">
								<div class="form-group">

									<label class="control-label col-md-6"><s:text
											name="com.edupath.student.summary.agreedAmount" /> <span
										class="required" aria-required="true">*</span> </label>
									<div class="col-md-4">
										<s:textfield cssClass="form-control input-sm" name="agreedAmount"
									id="agreedAmount" value="%{studentDTO.agreedAmount}" autocomplete="off" />
								<span class="error-block agreedAmount-error"> </span>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">

									<label class="control-label col-md-4"><s:text
											name="com.edupath.student.summary.paidAmount" /> <span
										class="required" aria-required="true">*</span> </label>
									<div class="col-md-4">
										<s:textfield cssClass="form-control input-sm" name="paidAmount"
									id="paidAmount" value="%{studentDTO.paidAmount}" autocomplete="off" />
								<span class="error-block paidAmount-error"> </span>
									</div>
								</div>
							</div>
						</div>
						
						
						<!--  <div class="col-md-4">
					<div class="tut-loc-com-div">
						<span><s:text name="com.edupath.tutorialfilter.locality"></s:text></span>
			 		</div>
			 		<div>
				 		<span>
					 		<select id="tutLocalityListSelect" style="width:180px;">
					 		  <option value="select">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
					 		</select>
				 		</span>
			 		</div>
				</div>-->
						
						<!-- Sasedeve  End added by vyankatesh  -->
						
						
						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<button type="button" class="btn blue" onclick="fnSubmit();">
										<i class="fa fa-check"></i>
										<s:text name="com.edupath.common.label.submit" />
									</button>
									<button type="button" class="btn default" onclick="fnClear()">
										<s:text name="com.edupath.common.label.clear" />
									</button>
								</div>
							</div>
						</div>
					</div>
				</s:form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END PORTLET -->
	</div>
</div>
</div>


<script type="text/javascript">

var form = document.studentSubmitForm;

function goBack() {
	form.action = "${pageContext.request.contextPath}/myapp/ManageStudentAction";
	form.submit();
}

var isType = true;
var TrialClass="";
$(document).ready(function() {
	var isSelected = false;
	ComponentsPickers.init();
	var classIdval = $('#classId').val();
	if(classIdval==3 || classIdval==4 || classIdval==5)
	{
		$('.session3').hide();
	}
	else 
		{
		$('.session3').show();
		}
	$('input[name=studentType]').change(function ()
	{
		if(this.value == 'FULL')
		{
			isType = true;
			$('.session-cls').show();
			$('.test-cls').hide();
		}
		else
		{
			isType = false;
			$('.session-cls').hide();
			$('.test-cls').show();
			TrialClass="TRIAL";
		}
		if('${actionType}' != 'modify')
		{
			fnClearDate();
		}
	});
	if('${studentType}' == 'TRIAL')
	{
		isType = false;
	}	
	if('${actionType}' == 'modify')
	{
		if('${studentDTO.seDetailsDTO.session1FaciCompleted}' == 'true')
		{
			$("#session1_date").attr('disabled','disabled');
			$("#session1_time").attr('disabled','disabled');
		}
		if('${studentDTO.seDetailsDTO.session2FaciCompleted}' == 'true')
		{
			$("#session2_date").attr('disabled','disabled');
			$("#session2_time").attr('disabled','disabled');
		}
		if('${studentDTO.seDetailsDTO.session3FaciCompleted}' == 'true')
		{
			$("#session3_date").attr('disabled','disabled');
			$("#session3_time").attr('disabled','disabled');
		}
		$('#venueSelect option').each(function(key, item){
			
			if(item.value != 'OTHER' )
			{
				var data =  '${utils:replaceJSONEntities(utils:replaceXMLEntities(studentDTO.venue))}';
				if(item.value == data)
				{
					$(this).prop("selected", true);
					isSelected = true;
				}
			}
			else
			{
				if(!isSelected)
				{
					$(this).prop("selected", true);
				    $('#venueDIV').show();
				}	
			}	
			
			
		});
		fnSchoolChange();
		$('.otherSchool').val('${utils:replaceJSONEntities(utils:replaceXMLEntities(studentDTO.otherSchool))}');
	}
	$(".datepicker").datepicker({
		format : 'dd/mm/yyyy',
		minDate : 0,
		autoclose : true
	});
	$(".timepicker").timepicker({
		minuteStep : 60,
		autoclose : true,
		showSeconds : false,
		showMeridian : false,
	});
	
	$('#classId option').each(function()
	{
		if($(this).val() == '${studentDTO.classId}')
		{
			$(this).prop("selected", true);
			return false;
		}	
	});
	//var obj = document.getElementById("classId");
	//fnSortSelect(obj); 
});
function fnSubmit() {
	if (isValidate()) {
		var id = '${studentId}';
		$('#studentId').val(id);
		if (id != '' && id != '0') {
			form.action = "${pageContext.request.contextPath}/myapp/updateStudentSubmit";
		} else {
			form.action = "${pageContext.request.contextPath}/myapp/insertStudentSubmit";
		}
		form.submit(); 
	}
}

function isValidate() 
{
	$('.error-block').html('');
	$('#error_alertId').hide();
	$('#error_alertId').html('');
	$('#school-error').html("");
	var name = $.trim($('#name').val());
	var gender = $('input:radio[name=gender]:checked').val();
	var fathername = $.trim($('#fathername').val());
	var cityId = $('#cityId').val();
	var schoolId = $('#schoolId').val();
	var classId = $('#classId').val();
	var contactNo = $.trim($('#contactNumber').val());
	var email = $.trim($('#fatheremailId').val());
	var computerFacility = $('input:radio[name=computerFacility]:checked').val();
	var facilitatorId = $('#facilitatorId').val();
	var session1_date = $.trim($('#session1_date').val());
	var session1_time = $.trim($('#session1_time').val());
	var session2_date = $.trim($('#session2_date').val());
	var session2_time = $.trim($('#session2_time').val());
	var session3_date = $.trim($('#session3_date').val());
	var session3_time = $.trim($('#session3_time').val());
	
	
	// Sasedeve  End  added by vyankatesh  
	var paidAmount = $.trim($('#paidAmount').val());
	var agreedAmount = $.trim($('#agreedAmount').val());
	var studentType = $('input:radio[name=studentType]:checked').val();
	var studentcontactNo = $.trim($('#studentcontactNumber').val());   
	var studentemail = $.trim($('#studentemailId').val());
	
	var motherName = $.trim($('#motherName').val());
	var motheremail = $.trim($('#motheremailId').val());
	var mothercontactno = $.trim($('#mothercontactno').val());
	  
	// Sasedeve  start added by vyankatesh  
	
	var section = $('#section').val();
	
	
	
	if (!isNameValid(name)) {
		return false;
	}
	if (null == gender || gender == '' || gender == undefined) {
		$('.gender-error').html("gender is required information.");
		return false;
	}
	//if (null == fathername || fathername == '') {
		//$('.fathername-error').html("Fathername is required information.");
		//return false;
	//}
	//var size = fathername.length;
	//if (size < 3 && size < 101) {
		//$('.fathername-error').html(
		//		"Fathername must be between 3 to 100 char.");
	//	return false;
	//}
	if (null == cityId || cityId == -1 || cityId == undefined) {
		$('.city-error').html("city is required information.");
		return false;
	}
	if (null == schoolId || schoolId == -1 || schoolId == undefined || schoolId == -11) {
		$('.school-error').html("school is required information.");
		return false;
	}
	if (null == classId || classId == -1 || classId == undefined) {
		$('.class-error').html("class is required information.");
		return false;
	}
	
	if (null != section && section != '' && section != undefined) 
	{
		var sectionLength = section.length;
		if(sectionLength > 2)
		{
			$('.section-error').html("class is required information.");
			return false;
		}	
	}
	
	//section
	
	
	if ( null == computerFacility || computerFacility == ''
			|| computerFacility == undefined) {
		$('.computerFacility-error').html(
				"computerFacility is required information.");
		return false;
	}
	if (isType && (null == facilitatorId || facilitatorId == -1
			|| facilitatorId == undefined || facilitatorId==-11)) {
		$('.facilitator-error')
				.html("facilitator is required information.");
		return false;
	}
	
	if (isType && !isSessionValid(session1_date, session1_time, session2_date,
			session2_time, session3_date, session3_time)) 
	{
		return false;
	}
	
	///////////////////////////////////////////////////////////////////Vyankatesh New 5/4/2017
	
	if(classId==1 || classId==2)
	{
		if(email !== "")
			{
				if (!isEmailValid(email)) {
        			return false;
        		}
    			
				if (null == fathername || fathername == '')
				{
    				$('.fathername-error').html("Fathername is required information.");
    				return false;
	    		}
	    		var size = fathername.length;
	    		if (size < 3 && size < 101) 
	    		{
	    			$('.fathername-error').html(
	    					"Fathername must be between 3 to 100 char.");
	    				return false;
	    		}
        		if(motheremail !== "")
        			{
            			if (!isMotherEmailValid(motheremail)) {
                			return false;
            			}
        			}
        		if(motherName !== "")
        			{
            			if (!isMotherNameValid(motherName)) {
                			return false;
                		}
        			}
			}
		else if(motheremail !== '')
		{
			if (!isMotherEmailValid(motheremail)) {
    			return false;
    		}
			if (!isMotherNameValid(motherName)) {
    			return false;
    		}
			
			if(email !== "")
    			{
    				if (!isEmailValid(email)) {
            			return false;
            		}
    			}
			if(fathername !== "")
    			{
				if (null == fathername || fathername == '')
				{
    				$('.fathername-error').html("Fathername is required information.");
    				return false;
	    		}
	    		var size = fathername.length;
	    		if (size < 3 && size < 101) 
	    		{
	    			$('.fathername-error').html(
	    					"Fathername must be between 3 to 100 char.");
	    				return false;
	    		}
    			}
		}
		else
		{
			$('.email-error').html("Email is required information.");
			return false;
		}
		
		if(contactNo !== '')
			{
				if (!isMobileValid(contactNo))
            		{
            			return false;
            		}
			
			}
		else if(mothercontactno !== '')
			{
    			if (!isMotherMobileValid(mothercontactno))
        		{
        			return false;
        		}
			}
		else
			{
    			$('.contact-error').html("Contact Number Required.");
    			return false;
			}
	}
else 
	{
		if (!isStudentEmailValid(studentemail)) {
			return false;
		}
			if (!isStudentMobileValid(studentcontactNo)) {
				return false;
			}
			
			if(email !== "")
			{
				if (!isEmailValid(email)) 
    				{
            			return false;
            		}
    			if (!isfathernameNameValid(fathername)) 
            		{
            			return false;
            		}
        		if(motheremail !== "")
        			{
            			if (!isMotherEmailValid(motheremail)) {
                			return false;
            			}
        			}
        		if(motherName !== "")
        			{
            			if (!isMotherNameValid(motherName)) {
                			return false;
                		}
        			}
			}
		else if(motheremail !== '')
			{
    			if (!isMotherEmailValid(motheremail)) {
        			return false;
        			}
    			if (!isMotherNameValid(motherName)) {
        			return false;
        			}
				if(email !== "")
        			{
        				if (!isEmailValid(email)) {
	            			return false;
	            		}
        			}
			if(fathername !== "")
    			{
    				if (!isfathernameNameValid(fathername)) {
            			return false;
            		}
    			}
		}
		else
    		{
    			$('.email-error').html("Email is required information.");
    			return false;
    		}
		if(contactNo !== '')
			{
    			if (!isMobileValid(contactNo))
                		{
                			return false;
                		}
			}
		else if(mothercontactno !== '')
			{
    			if (!isMotherMobileValid(mothercontactno))
        		{
        			return false;
        		}
			}
		else
			{
    			$('.contact-error').html("Contact Number Required.");
    			return false;
			}
		
	}
	////////////////////////////////////////////////////////////////////////////////////////END//////////////////
	
	
	//payment
	//sasedeve added by vyankatesh 
	if(studentType == 'FULL')
		{
			if (null == paidAmount || paidAmount == '') {
				$('.paidAmount-error').html("paidAmount is required information.");
				return false;
			}
			if (null == agreedAmount || agreedAmount == '') {
				$('.agreedAmount-error').html("agreedAmount is required information.");
				return false;
			}
			if(parseInt(agreedAmount) < parseInt(paidAmount))
				{
				$('.paidAmount-error').html("paidAmount is less than AgreedAmount.");
				return false;
				}
			
			if($("#venue").val() == '' || $("#venue").val() == undefined)
			{
				$('.venue-error').html("Venue is required information.");
				return false;
			}
		
		}
	else if(studentType == 'TRIAL')
		{
		
				$('#facilitatorId').find('option').remove().end().
				append('<option value="-1">----Select----</option>').val('select').trigger("change");
				$('#venueSelect').find('option').remove().end().append('<option value="Trial">----Select----</option>').val('select').trigger("change");
		
		
		
		
		}
	else
		{
			if(parseInt(agreedAmount) < parseInt(paidAmount))
			{
				$('.paidAmount-error').html("paidAmount is less than AgreedAmount.");
				return false;
			}
		}
	//sasedeve end by vyankatesh 
	
	var code = $("#schoolId option:selected").data('code');
	var text = $("#schoolId option:selected").text();
	if(code == -1 && text == "Other")
	{
		if($('.otherSchool').val() == undefined || $('.otherSchool').val() == '')
		{
			$('#school-error').html("This field is required.").show().css('color','#A94442');
    		return false;
		}	
	}
	
	$('#session1DateStr').val(session1_date + " " + session1_time);
	$('#session2DateStr').val(session2_date + " " + session2_time);
	$('#session3DateStr').val(session3_date + " " + session3_time);
	return true;
}

//Vyankatesh Added for class filter



function isSessionValid(session1_date, session1_time, session2_date,
		session2_time, session3_date, session3_time) {
	if (null == session1_date || session1_date == '') {
		$('.session1Date-error').html(
				"session 1: Date is required information");
		return false;
	}
	if (null == session1_time || session1_time == '') {
		$('.session1Date-error').html(
				"session 1: Time is required information");
		return false;
	}
	if (null == session2_date || session2_date == '') {
		$('.session2Date-error').html(
				"session 2: Date is required information");
		return false;
	}
	if (null == session2_time || session2_time == '') {
		$('.session2Date-error').html(
				"session 2: Time is required information");
		return false;
	}
	//start by bharath
	if(classId==3 || classId==4 || classId==5)  
	{
		$('.session3').hide();
	}
	else 
		{
		$('.session3').show();
		}
	//end by bharath
	if ((classId==1 || classId==2 ) && (null == session3_date || session3_date == '')) {
		$('.session3Date-error').html(
				"session 3: Date is required information");
		return false;
	}
	if ((classId==1 || classId==2 ) &&(null == session3_time || session3_time == '')) {
		$('.session3Date-error').html(
				"session 3: Time is required information");
		return false;
	}
	
	if (!checkTime(session1_time, session2_time, session3_time)) 
		{
			return false;
		}
		var today = new Date();
		var session1 = getDate(session1_date,session1_time);
		var session2 = getDate(session2_date,session2_time);
		var session3 = getDate(session3_date,session3_time);
		if (!$('#session1_date').is(':disabled') && today > session1 ) {
			$('.session1Date-error')
					.html(
							"session 1 can't be lesser or equal than today DateTime");
			return false;
		}
		if (!$('#session2_date').is(':disabled') && today > session2) {
			$('.session2Date-error')
					.html(
							"session 2 can't be lesser or equal than today DateTime");
			return false;
		}
		if (!$('#session3_date').is(':disabled') && today > session3) {
			$('.session3Date-error')
					.html(
							"session 3 can't be lesser or equal than today DateTime");
			return false;
		}
	if(!checkDate(session1, session2, session3, session1_date, session2_date, session3_date))
		{
			return false;
		}
		
	return true;

}

function checkDate(session1, session2, session3, session1_date, session2_date, session3_date)
{
	var minDays= parseInt(fnGetGap());
	var session1Date;
	var session2Date;
	var session3Date;
	$('#error_alertId').hide();
	$('#error_alertId').html('');
	
	if(minDays == -1)
	{
		$('#error_alertId').show();
		$('#error_alertId').html(fnGetErrorHTML());
		return false;
	}	
	
	if(minDays > 0)
	{
		session1Date = getDate(session1_date,"00:00");
		session2Date = getDate(session2_date,"00:00");
		session3Date = getDate(session3_date,"00:00");
	}
	else
	{
		session1Date = session1;
		session2Date = session2;
		session3Date = session3;
	}

	var timeDiff = session2Date.getTime() - session1Date.getTime();
	var diffDays = timeDiff / (1000 * 3600 * 24); 
	/* if(diffDays == 0)
	{
		var diffHrs = Math.round((timeDiff % 86400000) / 3600000); // hours
		if(diffHrs < 1)
		{
			$('.session2Date-error')
			.html(
					"Session 2 date & time must be greater than Session 1");
			return false;
		}	
	} */
	if(diffDays < minDays || diffDays <= 0)
	{
		if(minDays > 0)
			{
				$('.session2Date-error')
				.html(
						"Session 1, 2 date differ must be minimum of "+minDays+" days");
			}
		else
			{
				$('.session2Date-error')
				.html(
						"Session 2 date & time must be greater than Session 1");
			}
		
		return false;
	}
		
	
	timeDiff = session3Date.getTime() - session2Date.getTime();
	diffDays = timeDiff / (1000 * 3600 * 24); 
	/* if(diffDays == 0)
	{
		var diffHrs = Math.round((timeDiff % 86400000) / 3600000); // hours
		if(diffHrs < 1)
		{
			$('.session3Date-error')
			.html(
					"Session 3 date & time must be greater than Session 2");
			return false;
		}	
	}	 */
	if(diffDays < minDays || diffDays <= 0)
	{
		if(minDays > 0)
		{
			$('.session3Date-error')
			.html(
					"Session 2, 3 date differ must be minimum of "+minDays+" days");
		}
		else
		{
			$('.session3Date-error')
			.html(
					"Session 3 date & time must be greater than Session 2");
		}
		return false;
	}
	
	return true;
}

function getDate(date,time)
{
	var dateValue = date.split("/");
	var returnDate = new Date(dateValue[1]+"/"+dateValue[0]+"/"+dateValue[2]+ " " + time);
	return returnDate;
}

function checkTime(session1_time, session2_time, session3_time) {
	var minTime = 9;
	var maxTime = 20;
	var session1Time = session1_time.split(":")[0];
	var session2Time = session2_time.split(":")[0];
	var session3Time = session3_time.split(":")[0];
	if (isNaN(session1Time)) {
		$('.session1Date-error').html("Enter valid time");
		return false;
	}

	if (parseInt(session1Time) < minTime
			|| parseInt(session1Time) > maxTime) {
		$('.session1Date-error').html(
				"Time must be in between 09:00 to 20:00 ");
		return false;
	}
	if (isNaN(session2Time)) {
		$('.session2Date-error').html("Enter valid time");
		return false;
	}
	if (parseInt(session2Time) < minTime
			|| parseInt(session2Time) > maxTime) {
		$('.session2Date-error').html(
				"Time must be in between 09:00 to 20:00 ");
		return false;
	}
	if (isNaN(session3Time)) {
		$('.session3Date-error').html("Enter valid time");
		return false;
	}
	if (parseInt(session3Time) < minTime
			|| parseInt(session3Time) > maxTime) {
		$('.session3Date-error').html(
				"Time must be in between 09:00 to 20:00 ");
		return false;
	}
	return true;
}

function isEmailValid(email) {
	var reg = /(^([A-Za-z0-9_\-\.\])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$)/;

	if (email == '') {
		$('.email-error').html("Email is required information.");
		return false;
	}
	if (reg.test(email) == false) {
		$('.email-error').html("Enter valid email-id.");
		return false;
	}
	return true;
}



function isMobileValid(value) {
	if (value == '') {
		$('.contact-error').html("Contact Number Required.");
		return false;
	}

	if (isNaN(value)) {
		$('.contact-error').html("Enter valid Contact Number.");
		return false;
	}

	var reg = /(^(\+)?\d{10,10}$)/;
	if (reg.test(value) == false) {
		$('.contact-error').html("Contact Number must be 10 digit.");
		return false;
	}

	var size = value.length;

	if (size > 10) {
		$('.contact-error').html("Contact Number must be 10 digit.");
		return false;
	}
	return true;
}

//vyankatesh add validation 

function isStudentEmailValid(email) {
	var reg = /(^([A-Za-z0-9_\-\.\])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$)/;

	if (email == '') {
		$('.studentemail-error').html("Email is required information.");
		return false;
	}
	if (reg.test(email) == false) {
		$('.studentemail-error').html("Enter valid email-id.");
		return false;
	}
	return true;
}

function isStudentMobileValid(value) {
	if (value == '') {
		$('.studentcontact-error').html("Contact Number Required.");
		return false;
	}

	if (isNaN(value)) {
		$('.studentcontact-error').html("Enter valid Contact Number.");
		return false;
	}

	var reg = /(^(\+)?\d{10,10}$)/;
	if (reg.test(value) == false) {
		$('.studentcontact-error').html("Contact Number must be 10 digit.");
		return false;
	}

	var size = value.length;

	if (size > 10) {
		$('.studentcontact-error').html("Contact Number must be 10 digit.");
		return false;
	}
	return true;
}

 

function isMotherEmailValid(email) {
	var reg = /(^([A-Za-z0-9_\-\.\])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$)/;

	if (email == '') {
		$('.motheremail').html("Email is required information.");
		return false;
	}
	if (reg.test(email) == false) {
		$('.motheremail').html("Enter valid email-id.");
		return false;
	}
	return true;
}
 
 function isMotherMobileValid(value) {
		if (value == '') {
			$('.mothercontactno').html("Contact Number Required.");
			return false;
		}

		if (isNaN(value)) {
			$('.mothercontactno').html("Enter valid Contact Number.");
			return false;
		}

		var reg = /(^(\+)?\d{10,10}$)/;
		if (reg.test(value) == false) {
			$('.mothercontactno').html("Contact Number must be 10 digit.");
			return false;
		}

		var size = value.length;

		if (size > 10) {
			$('.mothercontactno').html("Contact Number must be 10 digit.");
			return false;
		}
		return true;
	}
 
 
 function isMotherNameValid(value) {
		var size = value.length;
		if (value == '') {
			$('.motherName').html("Name is required information.");
			return false;
		}
		if (size < 3 && size < 101) {
			$('.motherName').html("Name must be between 3 to 100 char.");
			return false;
		}
		return true;

	}
 
 function isfathernameNameValid(value) {
		var size = value.length;
		if (value == '') {
			$('.fathername-error').html("Name is required information.");
			return false;
		}
		if (size < 3 && size < 101) {
			$('.fathername-error').html("Name must be between 3 to 100 char.");
			return false;
		}
		return true;

	}


//vyankatesh End add validation 



function isNameValid(value) {
	var size = value.length;
	if (value == '') {
		$('.name-error').html("Name is required information.");
		return false;
	}
	if (size < 3 && size < 101) {
		$('.name-error').html("Name must be between 3 to 100 char.");
		return false;
	}
	return true;

}



function fnClear() {
	$('.error-block').html('');
	var option = $('#venueSelect').html();
	$('#venueSelect').html('');
	$('#venueSelect').html(option);
	$('#venue').val('ATSCHOOL');
	$('#venueAddr').val('');
	$('#name').val('');
	$('input[name=gender]').val('');
	$('#fathername').val('');
	$('#cityId').val(-1);
	$('#schoolId').val(-1);
	$('#classId').val(-1);
	$('#contactNumber').val('');
	$('#fatheremailId').val('');
	$('#facilitatorId').val(-1);
	fnClearDate();
	$('#venueDIV').hide();
	$('input[name=studentType]').click();
	
}

function fnVenueSelect(obj)
{
	if($(obj).val() == "AT OFFICE"  || $(obj).val() == "AT SCHOOL"  ||  $(obj).val() == "ONLINE"  )
	{
		$('#venue').val($(obj).val());
		$('#venueDIV').hide();
	}
	else
	{
		$('#venueDIV').show();
		$('#venueAddr').val('');
		$('#venue').val('');
		$('#venueAddr').val($(":selected",obj).data("addr"))
		$('#venue').val($(":selected",obj).data("addr"));
		
		
	}	
}
function fnSetTextData(obj)
{
	$('#venue').val($(obj).val());
}

function fnGetGap()
{
	var gapKey = $('#classId option:selected').data("gapkey");
	var gap = -1;
	<c:if test="${null ne globalSettingMap and not empty globalSettingMap}">
		<c:forEach items="${globalSettingMap}" var="itemData">
		   if('${itemData.key}' == gapKey)
		   {
			   gap = '${itemData.value}';
			   return gap;
		   } 
		</c:forEach>
   </c:if>
  return gap;
}

function fnGetErrorHTML()
{
	var html = '<div class="alert alert-danger ">'
				+'<button class="close" data-dismiss="alert"></button>'
				+'<span id="date-error">Unable to get the session gap for the class ' +$('#classId option:selected').text()+'</span>'
				+'</div>';
	return html;
}

function fnClearDate()
{
	$('#session1_date').val('');
	$('#session1_time').val('');
	$('#session2_date').val('');
	$('#session2_time').val('');
	$('#session3_date').val('');
	$('#session3_time').val('');
}

function fnSchoolChangeother()
{
	var code = $("#schoolId option:selected").data('code');
	var text = $("#schoolId option:selected").text();
	if(code == -1 || text == "Other")
	{
		$('.otherSchool').val('');
		$('.otherSchool').show();
	}
	else
	{
		$('.otherSchool').val('');
		$('.otherSchool').hide();
	}	
}

var mainCityId=0;
function fnOnCityIdChange(cityId)
{
	 fnSchoolChange(cityId);
	 fnFacilitatorMap(cityId);
	 mainCityId=cityId;
	
}


var VenueAdressMap;
cityLocalJson = JSON.parse('${jsonObject}');
VenueAdressMap = cityLocalJson.VenueAdressMap;

//alert(cityLocalJson);
//alert(VenueAdressMap);
//var oncallvalue=0;

function fnFacititaorVenue()
{
	var oncallvalue=0;
	oncallvalue = $("#facilitatorId option:selected").data('code');
	//alert(oncallvalue);
	//faceToface
	var faceTofacevalue = $("#facilitatorId option:selected").data('ftf');
	
	if(faceTofacevalue == 1)
	{
	$('#venueSelect').find('option').remove().end().
	
	append('<option value="ATSCHOOL">----Select----</option><option value="AT SCHOOL">AT SCHOOL</option><option value="AT OFFICE"">AT OFFICE</option>').val('select').trigger("change");
	}
	else
		{
		$('#venueSelect').find('option').remove().end().append('<option value="-11">----Select----</option>').val('select').trigger("change");
		
		}
	$.each(VenueAdressMap, function(key, value){
		if (key == mainCityId) 
		{
			
			var venueSize = value.length;
			
			for(var i=0;i<venueSize;i++)
              {
				if(faceTofacevalue == 1)
					{
		$("#venueSelect").append('<option value="OTHER" data-addr="'+value[i].adress+'">'+value[i].venue+'</option>');
              }
              }
		
			
		}
		
		
	});
	
	if(oncallvalue == 1)
	{
	$("#venueSelect").append("<option value='ONLINE'> ONLINE</option>");
	}
	if(faceTofacevalue == 1)
		{
		
		
	$("#venueSelect").append('<option value="OTHERa"><s:text name="com.edupath.student.at.other"></s:text></option>');
		}
}


//school change
var SchoolCityMap;
city1LocalJson = JSON.parse('${schooljsonObject}');
SchoolCityMap = city1LocalJson.schoolCityMap;



function fnSchoolChange(adress)
{
	$('#schoolId').find('option').remove().end().
	append('<option value="-11">----Select----</option>').val('select').trigger("change");
	$.each(SchoolCityMap, function(key, value){
		if (key == adress) 
		{
			//alert();
				
				var statedataSize = value.length;
				
				for(var i=0;i<statedataSize;i++)
	              {
				$("#schoolId").append('<option value="'+value[i].id+'" data-code="'+value[i].code+'">'+value[i].Name+'</option>');
				
	              }
				  if(key!=326)
				  {
					$("#schoolId").append('<option value="126" data-code="-1">Other/All College Students</option>');
				  }
				
				
			
		}
	});
}


var facilitatorCityMap;
cityLocalJson = JSON.parse('${facilitatorCityjsonObject}');
facilitatorCityMap = cityLocalJson.facilitatorcityMap;

function fnFacilitatorMap(adress)
{
	
	$('#facilitatorId').find('option').remove().end().
	append('<option value="-11">----Select----</option>').val('select').trigger("change");
	
	$.each(facilitatorCityMap, function(key, value){
		if (key == adress) 
		{
			//alert();
				
				var statedataSize = value.length;
				var facetoface="";
				var oncall="";
				var sepretor="";
				
				for(var i=0;i<statedataSize;i++)
	              {
					if(value[i].faceToface==1)
						{
						facetoface="face to face";
						}
					else
						{
						facetoface="";
						}
					if(value[i].onCall==1)
					{
					oncall="onCall";
					
					}
					else
					{
						oncall="";
					}
					if(value[i].faceToface==1 && value[i].onCall==1)
							{
						sepretor=",";
							}
					else
						{
						sepretor="";
						}
					
					$("#facilitatorId").append('<option value="'+value[i].facilitatorId+'" data-code="'+value[i].onCall+'" data-ftf="'+value[i].faceToface+'">'+value[i].Name+' ( '+ facetoface +sepretor+oncall+' ) </option>');
					
				
	              }
				
				
			
		}
	});
}


//Edit Screnn Function Start
//alert();
$(function () {
	
	var Cid="${studentDTO.cityId}";
	var Schid="${studentDTO.schoolId}";
	var Faciid="${studentDTO.facilitatorId}";

	var editvenue="${studentDTO.venue}";

	var otherSchool="${studentDTO.schoolId}";
	var otherSchooldetail="${studentDTO.otherSchool}";
	var editclassId="${studentDTO.classId}";
	
	

	if(Cid !== null && Cid !== undefined && Cid!== "0")
		{
		//alert()
		//Salert();schoolId facilitatorId
		 $("select#cityId").change(fnOnCityIdChange(Cid));
		  document.getElementById("schoolId").value = Schid;
		  document.getElementById("facilitatorId").value = Faciid;
		  fnFacititaorVenue();
		  document.getElementById("venueSelect").value = "OTHERa";
		 // fnVenueSelect venueSelect
		  if("${studentDTO.venue}" == "AT OFFICE"  || "${studentDTO.venue}" == "ATSCHOOL"  || "${studentDTO.venue}" == "ONLINE" || "${studentDTO.venue}" == "AT SCHOOL"  )
			{
				$('#venueSelect').val("${studentDTO.venue}");
				$('#venueDIV').hide();
				
				 if("${studentDTO.venue}" == "ATSCHOOL")
					 {
					 document.getElementById("venueSelect").value = "AT SCHOOL";
					 document.getElementById("venue").value = "${studentDTO.venue}";
					 
					 }
				 else
					 {
					 document.getElementById("venueSelect").value = "${studentDTO.venue}";
					 document.getElementById("venue").value = "${studentDTO.venue}";
					 }
			}
			else
			{
				$('#venueDIV').show();
				document.getElementById("venueSelect").value = "OTHERa";
				document.getElementById("venueAddr").value = "${studentDTO.venue}";
				
				document.getElementById("venue").value = "${studentDTO.venue}";
				
			}	
		 
		}
	
	
	if(editclassId==1 || editclassId==2)  
	{
		$('.showstudentstar').hide();
	}
	else 
	{
	$('.showstudentstar').show();
}
	
	
	
	if(otherSchool == 126)
	{
	$('.otherSchool').show();
	 document.getElementById("otherSchool").value = otherSchooldetail;
	
	}

});


function fnchangeRequiredField(classId)
{
	
				if(classId==1 || classId==2)  
				{
					$('.showstudentstar').hide();
				}
				else 
				{
				$('.showstudentstar').show();
			}
				

				if(classId==3 || classId==4 || classId==5)  
				{
					$('.session3').hide();
				}
				else 
					{
					$('.session3').show();
					}
	
}






</script>
	
<style></style>

<style>

.sub-main,.sub-container,.container-div {
	width: 100%;
}

.sub-common {
	float: left;
	padding: 5px;
	text-align: right;
	width: 10%;
}

.cleaner-div {
	/* clear: :left; */
	clear: both;
}

.field-div {
	
}

.email_txt {
	width: 250px;
	height: 30px;
}

.name_txt {
	width: 200px;
	height: 30px;
}

.contact_txt {
	width: 150px;
	height: 30px;
}

.sub_txt {
	border: 1px solid #BCBBBE;
	border-radius: 3px;
}

.sub-action {
	padding-top: 12px;
	text-align: right;
	width: 20%;
}

.btn-action {
	
}

.label-div {
	padding-top: 8px;
}

.error-block {
	color: rgb(190, 69, 69);
	font-size: 12px;
}

.control-label {
	padding-top: 0;
}
</style>
<% }
catch(Exception e)
{
	
	e.printStackTrace();
	
}

%>