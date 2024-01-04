<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   

<s:hidden name="isSearched" id = "isSearched"/>
<s:form name="occupationGlossary" id="occupationGlossary" theme="simple">
	<s:hidden name="studentId" id="studentId" />
	<s:hidden name="pageNumber" id = "pageNumber"/>
	<s:hidden name="isSearched" id = "isSearched"/>
    <s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
    <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
</s:form>
<style>
 .left {
      
  }

  .right {
      
  }
  .lessCareermodal{
width:30% !important;;
}
.BackToOPtionAConfirm
{
width:30% !important;;
}
@media screen and (max-width:768px){

.lessCareermodal{
width:100% !important;;
}
.BackToOPtionAConfirm
{
width:100% !important;;
}

.btn
{
	margin:2%;
}

.fitboth{
display:flex;
flex-wrap:wrap;
}
 
  .left {
      order:2;
      display:flex;
  }

  .right {
  		display:flex;
      order:1;
  }
}
 

</style>
<div class="edupath-padding">
<div class="col-md-8">
<div class="row" style="display: inline;">
					<div class="col-md-12">
						<div class="pagetitle">
							<!-- Page&nbsp;<span id="pageIndex"> -->
							</span>Select your 3 Choices from 300+ Careers below. Careers are organized Industry wise.  </span>
						</div><br>
						<p class="change_p_h">Approximately 50 are displayed per page.</p> 
					</div>	
				</div>
</div>
	<div class="row fitboth">
		<div class="col-md-8 left">
			<div id="contentdiv">
					
				<div class="row">
	
						<div class="col-md-12 col-xs-12" style="float: left;">
							<label class="bold"><s:text
									name="com.edupath.OccCluster.label.Search" /></label>&nbsp;:&nbsp;
								<s:textarea name="occName" id="occName" class="form-control"  />
								<br>
							<div class="btn-group">
								<button id="sample_editable_1_new" class="btn green"
									onclick="fnSearchOccupation()">
									<s:text name="com.edupath.facilitator.student.summary.search" />
								</button>
							</div>
						</div>
				</div>		
				<br><br>
				<div class="row top-buffer" style="min-height: 350px">
					<div class="col-md-12 occlist">
						<c:forEach items="${occupGlossMap}" var="industry">
							<div class="col-md-4">
								 <div class="industryTitle">
									 ${utils:replaceXMLEntities(industry.key)}
								</div>
								<c:forEach items="${industry.value}" var="pathWay">
									 <div class="pathWayTitle">
										${utils:replaceXMLEntities(pathWay.key)}
									</div>
									<div>
										<c:forEach items="${pathWay.value}" var="occupation">
											<div class="checkbox checkbox-primary img-rounded occupationnamediv">
												<span class="occupationname">
													<c:set var="rollOverContent">
													
								 	 				<!--<c:if test="${not empty alerts[occupation.longId]}">
															<c:set value="${alerts[occupation.longId]}" var="alertList"/>
															<b><s:text name="com.edupath.occupation.wish.alert" /></b>: <br> 
															<c:forEach items="${alertList}" var="alert" varStatus="slno">
																<c:if test="${slno.index gt 1}">
																	&nbsp;|
																</c:if>
																&nbsp;${alert.name}:<b>${alert.alertValue}</b>
															</c:forEach>
															<br><br>
															<b><s:text name="com.edupath.occupation.wish.occupation"></s:text></b>:<br>
														</c:if>-->
														${occupation.rollOverContent}
													</c:set>
												
													<input type="checkbox" id="occ_c_${occupation.id}" data-indsize="1" data-indusid="${occupation.industryId}">
													<label for="occ_c_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}">
														 
														${utils:replaceXMLEntities(occupation.name)}
													</label>
												</span>	
											</div>
										</c:forEach>
									</div>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="row">
			      <div class="row action-div">
			      	<c:choose>
						<c:when test="${isSearched=='true'}">
							<button class="btn btn_action btn-refresh" onclick="fnRefresh();" ><span class="back-spn"><s:text name=" See full 300+ list "></s:text></span></button>
						</c:when>
						<c:otherwise>
							<button class="btn btn_action btn-back" onclick="fnBackSubmit();" id="back"><span class="back-spn"><s:text name="com.edupath.common.label.back"></s:text></span></button>
     						<button class="btn btn_action btn-next" onclick="fnNextSubmit();" id="next"><span class="next-spn"><s:text name="com.edupath.common.next"></s:text></span></button>
		     			</c:otherwise>
					</c:choose>
		     		<button class="btn btn_action " onclick="fnSubmit();" ><span class="back-spn"><s:text name="com.edupath.OccCluster.label.submit"></s:text></span></button>
		  		  
		     		<button class="btn btn_action btn-back" onclick="fnBackToOPtionAConfirm();" id="back"><span class="back-spn"><s:text name="com.edupath.OccCluster.label.return.to.optionA"></s:text></span></button>
		     		<br><br><button class="btn btn_action" onclick="fnEnterNameForMail();" id="next">
		     		<span class="next-spn"><s:text name="I cannot find my career here"></s:text></span><br>
		     		<span class="next-spn"><s:text name="Connect me to Lodestar Expert"></s:text></span>
		     		</button>
		  		  </div>
		   		</div>
			</div>
			<div id="occupationdetails" style="display: none;">
			
			</div>
		</div>
		<div class="col-md-4 right">
			<jsp:include page="/pages/programTest/careerFitment/LeftOccupationWishList.jsp"></jsp:include>					
		</div>
	</div>
</div>
<div class="modal fade cust-stud-sesion-dialog lessCareermodal" id="lessCareermodal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
					 
				</div>

				<div class="modal-body">
					 
					 <s:text name="com.edupath.OccCluster.label.lesschoice" />
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnlessCareermodalOK();">
						<s:text name="OK" />
					</button>
					 
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade cust-stud-sesion-dialog" id="OverFlowWishList" tabindex="-1" role="dialog" aria-hidden="true" style="width: 30% !important;">
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
					 
				</div>

				<div class="modal-body">
					 
					 <s:text name="com.edupath.OccCluster.label.OverFlowWishList" />
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnOverFlowWishListOK();">
						<s:text name="com.edupath.OccCluster.label.OK" />
					</button>
					 
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade cust-stud-sesion-dialog" id="ConfirmSubmit" tabindex="-1" role="dialog" aria-hidden="true" style="width: 30% !important;">
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
				</div>
				<div class="modal-body">
				<p id="ConfirmWishsList">
				</p>
					 <s:text name="com.edupath.OccCluster.label.Confirm.Submit" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnConfirmSubmit();">
						<s:text name="com.edupath.common.label.submit" />
					</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnConfirmSubmitcancel();">
						<s:text name="com.edupath.common.cancel" />
					</button>
					 
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade cust-stud-sesion-dialog" id="thankyoumail" tabindex="-1" role="dialog" aria-hidden="true" style="width: 30% !important;">
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
				</div>
				<div class="modal-body">
					 <s:text name="Thank You - our representatives will get back to you soon" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnthankyoumailOk();">
						<s:text name="OK" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade cust-stud-sesion-dialog" id="BackToOPtionAConfirm" tabindex="-1" role="dialog" aria-hidden="true" >
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
				</div>
				<div class="modal-body">
					 <s:text name="If you have made any choices from the second set they will be lost. " />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnBackToOPtionA();">
						<s:text name="OK" />
					</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnBackToOPtionAClose();">
						<s:text name="Cancel" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade cust-stud-sesion-dialog" id="OccNameForMail" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
				</div>
				<div class="modal-body">
					 <p class="change_p_h6">Try searching for your career with a different spelling or alternate name </p>
					 <br><p class="change_p_h6">If you still cannot find your career names, type your career name choices below and we will get back to you. </p>
					 <br><s:textarea name="occNameMail" id="occNameMailtxt" class="form-control"  />
					 <br><p class="change_p_h6">Lodestar has a Content & Career Research Team which is constantly adding new careers. Our Career experts will schedule a meeting with you to recommend the fitment for your chosen careers</p>
					  <br><p class="change_p_h6" style="color:red">If you choose this option, your account will get locked and Lodestar representative  will get in touch with you to proceed further.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnConfirmMailSubmit();">
						<s:text name="com.edupath.common.label.submit" />
					</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnConfirmMailcancel();">
						<s:text name="com.edupath.common.cancel" />
					</button>
					 
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
	
	$(document).ready(function(){
		var isSearched = $("#isSearched").val();
		console.log("bharath isSearched=>"+isSearched);
		var occupation = '${OCCUPATION}';
		console.log("bharath occupation=>",occupation)
		   if(occupation.length > 0)
		   {
			   var newData = $.parseJSON(occupation);
			   $('#occName').textcomplete([{
				    match: /(^|\s)(\w{1,})$/,
				    search: function (term, callback) {
				        callback($.map(newData, function (arg)
				        {
				        	var re = new RegExp(term, 'gi');
				            return arg.match(re) != null ? arg : null;
				        }));
				    },
				    replace: function (newData) {
				        return newData + ' ';
				    }
				}]);
		   }
		fnReloadWishList();
		if($(window).width() < 720)
	     {
			 fnInitRollOver('occpopover','bottom');
	     }
	     else
	     {
	    	
	    	 fnInitRollOver('occpopover','right');
	    	 
	     }
	});
	
	$('input[type="checkbox"]').change(function() {
		if(this.checked)
		{
			console.log("bharath in input check whistListSizeFromJS=>>"+whistListSizeFromJS)
			if(whistListSizeFromJS == 3)
			{
				$("#OverFlowWishList").modal('show');
				this.checked=false;
				return false;
			}
			fnAddWishList($(this).attr("id"));
		}	
	});
	
	function fnOverFlowWishListOK()
	{
		$("#OverFlowWishList").modal('hide');
	}
	
	var pageNumber = parseInt($("#pageNumber").val());
	$("#pageIndex").html(pageNumber+1);
	
	
	if (pageNumber == 0) {
		$("#back").hide();
	}else {
		$("#back").show();
	}
	var pageCount = parseInt($("#showNum").html());
	var count = ${occupCount} - (pageNumber*pageCount+pageCount);
	if (count < 1) {
		$("#showNum").html(count + pageCount);
		$("#next").hide();
	}
	
	var form = document.occupationGlossary;
	
	function fnNextSubmit()
	{
		$("#pageNumber").val(pageNumber+1);
		form.action = "${pageContext.request.contextPath}/myapp/CareerFitmentOccGlossary";
		form.submit();	
	}
	
	function fnBackSubmit()
	{
		$("#pageNumber").val(pageNumber-1);
		form.action = "${pageContext.request.contextPath}/myapp/CareerFitmentOccGlossary";
		form.submit();	
	}
	
	function fnlessCareermodalOK()
	{
		$("#lessCareermodal").modal('hide');
	}
	function fnSearchOccupation()
	{
		var occName = $("#occName").val()
		if(occName == "" || occName == null){
			alert("Please enter career name")
			return false;
		} 
		var urlVal = "${pageContext.request.contextPath}/myapp/searchOccCareerFitmentOccGlossary?occName="+occName;
		form.action = urlVal;
		form.submit();
	}
	function fnBackToOPtionAConfirm()
	{
		$("#BackToOPtionAConfirm").modal('show');
		 
	}
	function fnBackToOPtionAClose()
	{
		$("#BackToOPtionAConfirm").modal('hide');
		 
	}
	function fnBackToOPtionA()
	{
		$("#BackToOPtionAConfirm").modal('hide');
		var urlVal = "${pageContext.request.contextPath}/myapp/CareerFitmentClusterOccGlossary";
		form.action = urlVal;
		form.submit();
	}
	function fnEnterNameForMail()
	{
		$("#OccNameForMail").modal('show');
		$('#OccNameForMail').on('shown.bs.modal', function () {
		    $('#occNameMailtxt').focus();
		})  
	}
	function fnConfirmMailSubmit()
	{
		
		var occNameMail = $("#occNameMailtxt").val()
		if(occNameMail == "" || occNameMail == null){
			alert("Please enter career name")
			return false;
		} 
		//var urlVal = "${pageContext.request.contextPath}/myapp/mailOccupationNameToLodestarCareerFitmentOccGlossary?occNameMail="+occNameMail;
		//form.action = urlVal;
		//form.submit();
		$.ajax({
			  url: "${pageContext.request.contextPath}/myapp/mailOccupationNameToLodestarOccupationClusterWishList",
			  data:{
				  	"occNameMail":occNameMail,
			  		},
			  dataType:"json",
			  success: function(result){
				  $("#OccNameForMail").modal('hide');
				  $("#thankyoumail").modal('show');
			  },
			  error: function(jqXHR, textStatus, errorThrown )
			  {
				//  alert(errorThrown)
			  }
		});
		
		$("#OccNameForMail").modal('hide');
	}
	function fnConfirmMailcancel()
	{
		$("#OccNameForMail").modal('hide');
	}
	function fnthankyoumailOk()
	{
		$("#thankyoumail").modal('hide');
		form.action = "${pageContext.request.contextPath}/myapp/WelcomeStudentAction";
		form.submit();
	}
	
	function fnRefresh()
	{
		$("#pageNumber").val(pageNumber-pageNumber);
		form.action = "${pageContext.request.contextPath}/myapp/CareerFitmentOccGlossary";
		form.submit();
	}
	
	
</script>