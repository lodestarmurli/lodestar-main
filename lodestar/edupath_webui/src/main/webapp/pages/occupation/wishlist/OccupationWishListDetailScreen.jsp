<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<div class="edupath-padding">
<div class="rows" >
<div id="contentdiv">
  <div class="col-md-12" style=" padding-bottom: 20px; padding-left: 65px;">
    <span class="pagetitle"><s:text name="com.edupath.occupation.wish.label"></s:text> </span>
     <!-- Start SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
									 <br/> <br/>
		                            <span class="fa fa-circle-o fa-1x" style="color:black;"></span>
								    <span class="sectionheading">&nbsp;&nbsp;Abilities fitment</span>&nbsp;&nbsp;
									<span class="fa fa-square-o fa-1x" style="color:black;"></span><span class="sectionheading">&nbsp;&nbsp;Interest fitment</span>
									<br/><br/>
									<span class="fa fa-tag fa-lg" style="color:green"></span><span class="sectionheading">&nbsp;&nbsp;High</span>
									<span class="fa fa-tag fa-lg" style="color:yellow"></span><span class="sectionheading">&nbsp;&nbsp;Above Average</span>
									<span class="fa fa-tag fa-lg" style="color:orange"></span><span class="sectionheading">&nbsp;&nbsp;Average</span>
									<span class="fa fa-tag fa-lg" style="color:red"></span><span class="sectionheading">&nbsp;&nbsp;Low</span>
									<br/><br/>
									<!-- END SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
    
    
  </div>
 <div class="row" style=" padding-left: 65px;">
   <div class="col-md-12">
   <c:set var="divCount" value="1"></c:set>
	 <c:forEach items="${occupationWishlist.OCCUPATION}" var="occupationEntry" varStatus="count">
		<div class="col-md-4">
		  <div class="industryTitle">
			 ${utils:replaceXMLEntities(occupationEntry.key)}
		  </div>
			<div class="row" >
			 <div class="col-md-12">
			   <c:forEach items="${occupationEntry.value}" var="occupation">
				 <div class=" img-rounded occupationnamediv" style="margin-top:5px;">
				   <span class="occupationname">
					 <c:set var="rollOverContent">
					 <!-- Start Sasedeve edited by Mrutyunjaya on date 14-07-2017 -->
					 <b><s:text name="com.edupath.system.recommendation.requiredcode"/></b>:  ${occupation.requiredRAISEC}
					</br>
					 <b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${occupation.requiredAbility}
					</br></br>
													
					<!-- END Sasedeve edited by Mrutyunjaya on date 14-07-2017 -->
					  <c:if test="${not empty occupationWishlist.alerts[occupation.longId]}">
						<c:set value="${occupationWishlist.alerts[occupation.longId]}" var="alertList"/>
						 <b><s:text name="com.edupath.occupation.wish.alert"></s:text></b>: <br> 
						 <c:forEach items="${alertList}" var="alert" varStatus="slno">
							<c:if test="${slno.index gt 1}">
								&nbsp;|
							</c:if>
							&nbsp;${alert.name}:<b>${alert.alertValue}</b>		
						  </c:forEach>
						   <br><br>
						   <b><s:text name="com.edupath.occupation.wish.occupation"></s:text></b>:<br>
						   </c:if>
							  ${occupation.rollOverContent}
						   </c:set>
	                           <label for="occ_c_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}" >
							    <c:if test="${not empty occupationWishlist.alerts[occupation.longId]}">
							  <span class="fa fa-exclamation" style="color:orange"></span>
							 </c:if>
							 
							 <!-- START Sasedeve  by Mrutyunjaya on date 26-04-2017 -->
							 
							 <i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
							 <i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i>
							 <!-- START Sasedeve  by Mrutyunjaya on date 26-04-2017 -->
							 
							   ${utils:replaceXMLEntities(occupation.name)}
							 </label>
						 </span>
						</div>
					  </c:forEach>
					 </div>
				   </div>
				  </div>
				   <c:set var="divCount" value="${divCount + 1}"></c:set>
				   <c:set var="lastDiv" value=""></c:set>					
				</c:forEach>
			  </div>
		     </div>
		   </div>
		  <div  class="row">
			<div id="occupationdetails" style="display: none;" class="col-md-12">
				
			</div>
		  </div>
</div>
</div>
<script>

var occTooltipTemplate = "<html><div class='popover' style='width:900px'><div class='arrow'></div><div class='popover-content'>"
	occTooltipTemplate += "</div><div style='float:right;text-align:right;padding-bottom:10px;padding-right:10px;'><a class='readmore' href='#'>Occupation Details ></a></div></div></html>";

$(document).ready(function(){
	fnInitRollOver('occpopover','bottom');
});

function fnInitRollOver(cssclass, placementstr)
{
	$('[data-toggle="'+cssclass+'"]').popover({
		template:occTooltipTemplate,
		html:true,
        animation:false,
        trigger: "manual",
        placement:placementstr
	})
	.on("mouseenter", function () {
	    var _this = this;
	    $(this).popover("show");
           
	    $(".popover").on("mouseleave", function () {
	        $(_this).popover('hide');
	    });
	})
	.on("mouseleave", function () {
	    var _this = this;
	    setTimeout(function () {
	        if (!$(".popover:hover").length) {
	            $(_this).popover("hide");
	        }
	    }, 300);
	}).on('shown.bs.popover', function () {
	    var $popup = $(this);
	    var occId = $(this).first().attr("id");
	    $(this).next('.popover').find('a.readmore').off("click");
	    $(this).next('.popover').find('a.readmore').click(function (e) {
	        $popup.popover('hide');
	        fnOccReadMore(occId);
	    });
	});
}

function fnOccReadMore(occId)
{
	$("#contentdiv").hide();
	$("#occupationdetails").html('');
	$("#occupationdetails").show();
	//get the content and show the occupation details
	$.ajax({
		  url: "${pageContext.request.contextPath}/myapp/OccupationAction",
		  data:{"occupationId":occId},
		  dataType:"html",
		  success: function(result){
			 $("#occupationdetails").html(result);
		  },
		  error: function(jqXHR, textStatus, errorThrown )
		  {
			 // alert(errorThrown)
		  }
	});
}

function fnHideOccupation()
{
	$("#contentdiv").show();
	$("#occupationdetails").hide();	
}
</script>

