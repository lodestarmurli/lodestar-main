<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   

<s:form name="occupationGlossary" id="occupationGlossary" method="post">
	<s:hidden name="studentId" id="studentId" />
	<s:hidden name="pageNumber" id = "pageNumber"/>
    <s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
    <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
</s:form>
<style>
 .left {
      
  }

  .right {
      
  }
@media screen and (max-width:768px){

#lessCareermodal{
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
						Choose Your 3 Careers from the list below					
						</div>
						<div>
						<p class="change_p_h4">Click on Career name to select</p> 
						</div>
					</div>	
				</div>
	</div>
	<div class="row fitboth">
		<div class="col-md-8 left">
			<div id="contentdiv">
			<!--  	<div class="row" style="display: inline;">
					<div class="col-md-12">
						<div class="pagetitle">
						Choose Your 3 Careers from the list below					
						</div>
						<div>
						<p class="change_p_h4">Click on Career name to select</p> 
						</div>
					</div>	
				</div>			
				-->
				<div class="row top-buffer" style="min-height: 350px">
					<div class="col-md-12">
						 
							<div class="col-md-6">
							<c:forEach items="${clusterGloss}" var="cluster">
											<div class="checkbox checkbox-primary img-rounded clusternamediv">
												<span class="clustername">
												 	<c:set var="rollOverContent">
														${cluster.rollOverContent}
													</c:set>
													<input type="checkbox" id="cls_c_${cluster.id}" data-indsize="1" >
													<label for="cls_c_${cluster.id}" data-toggle="clspopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${cluster.id}">
														${utils:replaceXMLEntities(cluster.name)}
													</label>
												</span>	
											</div>
								</c:forEach>
							</div>
						 
				<!-- 	</div>
				</div>
				
				
				<div class="row top-buffer" style="min-height: 350px">
					<div class="col-md-12"> -->
						<c:forEach items="${occupGlossMap}" var="industry">
							<div class="col-md-6">
								 
								<c:forEach items="${industry.value}" var="pathWay">
									 
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
																	&nbsp;
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
														<!--<c:if test="${not empty alerts[occupation.longId]}">
															<span class="fa fa-exclamation" style="color:orange"></span>
														</c:if>
														
														 BEGIN Sasedeve Added by Mrutyunjaya on Date 04-05-2017 
																<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
													
													
												 	<i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i> 
													-->
													<!-- END Sasedeve Added by Mrutyunjaya on Date 04-05-2017 -->
														
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
		     		<button class="btn btn_action " onclick="fnSubmit();" ><span class="back-spn"><s:text name="com.edupath.OccCluster.label.submit"></s:text></span></button>
		     		<button class="btn btn_action" onclick="fnNextSubmit();" ><span class="next-spn"><s:text name="com.edupath.OccCluster.label.optionB"></s:text></span></button>
		  		  </div>
		   		</div>
			</div>
			<div id="occupationdetails" style="display: none;">
			
			</div>
		</div>
		<div class="col-md-4 right">
			<jsp:include page="/pages/programTest/careerFitment/LeftOccupationClusterWishList.jsp"></jsp:include>					
		</div>
	</div>
</div>
	<div class="modal fade cust-stud-sesion-dialog" id="lessCareermodal" tabindex="-1" role="dialog" aria-hidden="true" style="width: 30% !important;">
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
	<div class="modal fade cust-stud-sesion-dialog"
		id="OverFlowWishList" tabindex="-1" role="dialog"
		aria-hidden="true">
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
	
	<div class="modal fade cust-stud-sesion-dialog"
		id="moveToOptionB" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-full">
			<div class="modal-content">
				<div class="modal-header">
				</div>
				<div class="modal-body">
					 <s:text name="com.edupath.OccCluster.label.movetoOptionB" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnmoveToOptionB();">
						<s:text name="com.edupath.OccCluster.label.goto" />
					</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="fnmoveToOptionBcancel();">
						<s:text name="com.edupath.OccCluster.label.goback" />
					</button>
					 
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade cust-stud-sesion-dialog"
		id="ConfirmSubmit" tabindex="-1" role="dialog"
		aria-hidden="true">
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

<script type="text/javascript">
	
	$(document).ready(function(){
		fnReloadWishList();
		if($(window).width() < 720)
	     {
			 fnInitRollOver('occpopover','bottom');
	     	 fnInitClsusterRollOver('clspopover','bottom');
	     }
	     else
	     {
	    	 fnInitRollOver('occpopover','right');
	    	 fnInitClsusterRollOver('clspopover','right');
	     }
		 if($(window).width() < 720)
	     {
			fnInitClsusterRollOver('leftclspopover', 'right');  
	        fnInitRollOver('leftoccpopover', 'right');  
	     }
		else
			{
			fnInitClsusterRollOver('leftclspopover', 'left');  
	        fnInitRollOver('leftoccpopover', 'left'); 
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
	
	function fnlessCareermodalOK()
	{
		$("#lessCareermodal").modal('hide');
	}
	
	function fnOverFlowWishListOK()
	{
		$("#OverFlowWishList").modal('hide');
	}
	
	 
	
	var form = document.occupationGlossary;
	
	function fnNextSubmit()
	{
		$("#moveToOptionB").modal('show');
		//form.action = "${pageContext.request.contextPath}/myapp/CareerFitmentOccGlossary";
		//form.submit();	
	}
	function fnmoveToOptionBcancel()
	{
		$("#moveToOptionB").modal('hide');
	}
	function fnmoveToOptionB()
	{
		$("#moveToOptionB").modal('hide');
		form.action = "${pageContext.request.contextPath}/myapp/CareerFitmentOccGlossary";
		form.submit();	
	}
	
	
	
</script>