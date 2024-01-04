<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="fn" uri="fn.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<style>
.row_custum{
	margin-top: 10px;
	padding-left: 20px;
}
</style>
<!-- END SASEDEVE Edited by Mrutyunjaya On date 20_05_2017   -->
<style>
.GreenColor{
color: green;
}

.RedColor{
color: red;
}
.popover {
  width:100%;
  
}

</style>
<!-- END SASEDEVE Edited by Mrutyunjaya On date 20_05_2017   -->
<div class="edupath-padding">
	<div class="row row_custum">
		<span class="pagetitle">My Shortlist&nbsp;(${shortListCount})</span>
	</div>
	<div class="row row_custum">
		<p class="sectionheading">Review and confirm your shortlisted occupations.</p>
	</div>
	<div class="row row_custum">
		<c:forEach items="${shortListDTOList}" var="shortList">
			<div class="col-md-2">
					<div class="text-center row_custum img-responsive thumbnail">
						<div style="width: 100%;height: 15px">
							<i class="fa fa-times pull-right" style="cursor: pointer;" onclick="fnDeleteFromShortList(${shortList.id})"></i>
						</div>
						<c:choose>
							<c:when test="${not empty shortList.occupationName}">
								<img src="data:image/png;base64,${shortList.base64img}" class="" style="height: 100px; margin:auto">
								<p>${shortList.occupationName}</p>
							</c:when>
							<c:otherwise>
								<img src="${pageContext.request.contextPath}/images/indsutry_collage.jpg" class="" style="height: 100px; margin:auto">
								<p>${shortList.industryName}</p>
							</c:otherwise>
						</c:choose>
					</div>
			</div>
		</c:forEach>		
	</div>
	
	<div class="row row_custum">
		<p class="sectionheading">Confirm the priority for the shortlisted Occupations / Industries.</p>
		                            
		                          <!-- Start SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
		                            <span class="fa fa-circle-o fa-1x" style="color:black;"></span>
								    <span class="sectionheading">&nbsp;&nbsp;Abilities fitment</span>&nbsp;&nbsp;
									<span class="fa fa-square-o fa-1x" style="color:black;"></span><span class="sectionheading">&nbsp;&nbsp;Interest fitment</span>
									<br/><br/>
									<span class="fa fa-tag fa-lg" style="color:green"></span><span class="sectionheading">&nbsp;&nbsp;High</span>
									<span class="fa fa-tag fa-lg" style="color:yellow"></span><span class="sectionheading">&nbsp;&nbsp;Above Average</span>
									<span class="fa fa-tag fa-lg" style="color:orange"></span><span class="sectionheading">&nbsp;&nbsp;Average</span>
									<span class="fa fa-tag fa-lg" style="color:red"></span><span class="sectionheading">&nbsp;&nbsp;Low</span>
									<br/><br/>
									<a href="${pageContext.request.contextPath}/thirdparty/Notetothestudent.pdf" target="_OpenPDF">Click here to know how to shortlist and prioritize using the color code</a>
									<br/><br/>
									
									
									<!-- END SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
									
	</div>
	<div class="row row_custum">
		<div class="col-md-5" style="border-style: solid; border-width: 1px;">
			<div class="row row_custum" style="padding-left: 20px;">
				<p class="pathWayTitle">List of Occupations / Industries</p>
			</div>
			<div class="row" style="padding-left: 20px; margin-top: 0px;" id="items">
				<c:forEach items="${shortListDTOList}" var="shortList" varStatus="count">
					<c:choose>
						<c:when test="${not empty shortList.occupationName}">
						
						
							<c:set var="labelName" value="${shortList.occupationName}"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="labelName" value="${shortList.industryName}"></c:set>
						</c:otherwise>
					</c:choose>
					
						    
					 <div class="radio radio-success">
					 
					 
					 
		               <input id="radio_8_${count.index}" name="strength" data-serial="${count.index + 1}" value="${labelName}" data-index="${count.index}" type="radio">
		         <!-- Start SASEDEVE Edited by Mrutyunjaya On date 20-05-2017 -->      
		          
		               <c:choose>
						<c:when test="${shortList.fitmentforstudent==4}">
							<!-- Start Sasedeve edited by Mrutyunjaya on date 145-07-2017 -->
						
						<c:set var="rollOverContent">
													
					<b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${shortList.requiredAbility}
					
													</c:set>
						
						
						
									<!-- END Sasedeve edited by Mrutyunjaya on date 15-07-2017 -->
							
							 <label for="radio_8_${count.index}" class="qusetion_txt_label" data-toggle="popover" data-trigger="hover" data-content="${utils:replaceXMLEntities(rollOverContent)}">
		               		 <i class="fa fa-circle fa-fw ${fitmentcolors[shortList.fitment]}" aria-hidden="true"></i><i class="fa fa-square fa-fw ${fitmentcolors[shortList.newfitment]}" aria-hidden="true"></i> ${labelName}
		               </label>
							 
							 
						</c:when>
						<c:otherwise>
						
						<!-- Start Sasedeve edited by Mrutyunjaya on date 145-07-2017 -->
						
						<c:set var="rollOverContent">
													
					<b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${shortList.requiredAbility}
					<br/>
					<b><s:text name="Need To Improve Ability"/></b>: ${shortList.improveability}								
		
												
													</c:set>
						
						
						
									<!-- END Sasedeve edited by Mrutyunjaya on date 15-07-2017 -->
						
							
							<label for="radio_8_${count.index}" class="qusetion_txt_label" data-toggle="popover" data-trigger="hover" data-content="${utils:replaceXMLEntities(rollOverContent)}">
		               		<i class="fa fa-circle fa-fw ${fitmentcolors[shortList.fitment]}" aria-hidden="true"></i><i class="fa fa-square fa-fw ${fitmentcolors[shortList.newfitment]}" aria-hidden="true"></i> ${labelName}
		               </label>
							
							
							
						</c:otherwise>
					</c:choose>
		               
		               
		               
		               
		               
		               
		               <!-- END SASEDEVE Edited by Mrutyunjaya On date 20-05-2017 -->   
		               
		               <input type="hidden" name="id" id="id" value="${shortList.id}">
		             </div>
				</c:forEach>
			</div>
		</div>
		<div class="col-md-7" style="min-height: 100px;">
			<div class="row row_custum" style="margin-top: 10px; padding-left: 20px;">
				<button class="btn btn_action"  id="up" style="width: 129px;"><span><s:text name="com.edupath.my.shortlist.move.up"></s:text></span></button>
			</div>
			<div class="row row_custum" style="padding-left: 20px;">
				<button class="btn btn_action" id="down"><span><s:text name="com.edupath.my.shortlist.move.down"></s:text></span></button>
			</div>
		</div>
	</div>
	<div class="row">
		   <div class="row action-div action-div1">
	     		<button class="btn btn_action" onclick="fnDiscard();"><span><s:text name="com.edupath.my.shortlist.discard"></s:text></span></button>
	     		<button class="btn btn_action" onclick="fnSave();" style="margin-left: 10px;"><span><s:text name="com.edupath.my.shortlist.save"></s:text></span></button>
  		   </div>
	</div>
</div>
<s:form name="myShortList" id="myShortList" method="post">
	<s:hidden name="shortListIds" id="shortListIds"/> 
	<s:hidden name="studentId" id="studentId"/>
</s:form>

<div class="modal fade" id="timerPOPUP" role="dialog" style="width: 400px;position: fixed;left: auto;top: -120;right: 10px">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 300px !important;">

        <div class="modal-body" style="text-align: center; font-size: 11pt;">
		 <label id="message_lbl"></label>
        </div>
      </div>
    </div>
</div>

<script type="text/javascript">

var existingIdArray = [];
$(document).ready(function(){
    var selected=0;
   
    var itemlist = $('#items');
    var len=$(itemlist).children().length; 
    
	$('#items div input:hidden').each(function() {
		existingIdArray.push($(this).val());
	});
	
    $("#items div").click(function(){
    	isSelected= $(this).find('input').prop('checked');
    	if (isSelected) 
    	{
	        selected= $(this).index();
		}
    });
      

     $("#up").click(function(e){
       e.preventDefault();
       if(selected>0)
       	{
       		jQuery($(itemlist).children().eq(selected-1)).before(jQuery($(itemlist).children().eq(selected)));
         	selected=selected-1;
     	}
    });

     $("#down").click(function(e){
         e.preventDefault();
        if(selected < len-1)
        {
        	jQuery($(itemlist).children().eq(selected+1)).after(jQuery($(itemlist).children().eq(selected)));
         	selected=selected+1;
     	}
    });
     
   //START SASEDEVE Edited by Mrutyunjaya on Date 20-05-2017
  // Start Sasedeve edited by Mrutyunjaya on date 15-07-2017
						
     fnInitRollOver('popover','right');
   
 	// End Sasedeve edited by Mrutyunjaya on date 15-07-2017
	
    // $('[data-toggle="popover"]').popover();   
   //END SASEDEVE Edited by Mrutyunjaya on Date 20-05-2017
     
});

var form =document.myShortList; 
	
function fnSave(){
	var idArray = [];
	$('#items div input:hidden').each(function() {
		idArray.push($(this).val());
	});

	var priority1_2Changed = false, allowSubmit = true;
	if('${edupathExists}' == 'true')
	{
		$(existingIdArray).each(function(i, value){
			if(i < 2 && idArray[i] != value){
				priority1_2Changed = true;
				allowSubmit = false;
			}
		});
	}	
	
	if(priority1_2Changed)
	{
		var status = confirm("By changing Priority 1 or Priority 2, Edupath will be removed, Are you sure you want to continue");
		allowSubmit = status;
	}	
	
	if(allowSubmit){
		$("#shortListIds").val(idArray.toString());
		form.action="${pageContext.request.contextPath}/myapp/saveMyShortListAction";
		form.submit();		
	}
}

function fnDiscard(){
	
	form.action="${pageContext.request.contextPath}/myapp/MyShortListAction";
	form.submit();
}

function fnDeleteFromShortList(shortListId) {
	var status = confirm("Are you sure you want to delete from Shortlist");
	if (status && shortListId) {
		$.ajax({
					url : "${pageContext.request.contextPath}/myapp/deleteMyShortListSubAction",
					method : "POST",
					data : {
						"shortListId" : shortListId
					},
					dataType : "json",
					success : function(result) {
						if (result.status == "SUCCESS") {
							fnStartTimer('Removed successfully.');
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						//alert(errorThrown)
					}
				});
	}
}

function fnCloseModal()
{
	clearInterval(startTimer);
	$('#timerPOPUP').modal('hide');
	fnDiscard();
}

function fnStartTimer(msg)
{
	$('#message_lbl').html(msg);
	$('#timerPOPUP').modal();
	startTimer = setInterval(fnCloseModal,2000);
}
// Start Sasedeve edited by Mrutyunjaya on date 15-07-2017

var occTooltipTemplate = "<html><div class='popover' style='width:900px'><div class='arrow'></div><div class='popover-content'>"
	occTooltipTemplate += "</div><div style='float:right;text-align:right;padding-bottom:10px;padding-right:10px;'</div></div></html>";
	var loading = '<div align="center"><img src="/edupath_webui/images/loader.gif"></div>';

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

//END Sasedeve edited by Mrutyunjaya on date 15-07-2017




</script>