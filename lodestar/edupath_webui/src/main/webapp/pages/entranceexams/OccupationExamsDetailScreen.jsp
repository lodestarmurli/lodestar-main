<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="fn" uri="fn.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%> 


<div id="occExamMainDiv" style="margin-left: 30px; margin-top: 10px;">
	<div id="occExamDetailsId">
		<div class="row">
			<div class="col-md-11">
				<div class="row">
					<div class="col-md-1">
						<img alt="edupath-ind not found" src="${pageContext.request.contextPath}/images/icons/edupath-industry-icon.gif" class="img-responsive">
					</div>
					<div class="col-md-10">
						<span class="pagetitle">${industryName}</span>
					</div>
					<div class="col-md-1">
						<div id="backToEdupath" class="entr_exam_back" style="margin-left: 45px; margin-top: 5px;">
							<a href="#" onclick="fnReturnBackToEduPath();">Back</a>
						</div>
					</div> 
				</div>
				<c:if test="${not empty occWithExamsList}">
					<div class="row" style="margin-top: 20px;">
						<span class="pathWayTitle">Occupation with entrance exam : (${fn:length(occWithExamsList)})</span>
					</div>
						<div class="row">
							<div class="col-ms-12" >
				        		<c:forEach items="${occWithExamsList}" var="occWithExam">
									<div class="col-md-3 occupationnamediv" style="margin-top: 20px; margin-left: 10px;">
										<span class="occupationname" style="margin:5px 10px 5px 10px; text-align: left;">
											<c:set var="rollOverContent">
												${occWithExam.rollOverContent}
											</c:set>
										
											<label for="occ_c_${occWithExam.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occWithExam.id}">
												${utils:replaceXMLEntities(occWithExam.name)}
											</label>
										</span>	
									</div>
					            </c:forEach>
				            </div>
						</div>
	            </c:if>
	            <c:if test="${not empty occWithoutExamsList}">
		            <div class="row" style="margin-top: 20px;">
						<span class="pathWayTitle">Occupation without entrance exam : (${fn:length(occWithoutExamsList)})</span>
					</div>
					<div class="row">
						<div class="col-ms-12">
			        		<c:forEach items="${occWithoutExamsList}" var="occWithoutExam">
								<div class="col-md-3  occupationnamediv" style="margin-top: 20px; margin-left: 10px;">
									<span class="occupationname" style="margin:5px 10px 5px 10px; text-align: left;">
										<c:set var="rollOverContent">
											${occWithoutExam.rollOverContent}
										</c:set>
									
										<label for="occ_c_${occWithoutExam.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occWithoutExam.id}">
											${utils:replaceXMLEntities(occWithoutExam.name)}
										</label>
									</span>
								</div>
				            </c:forEach>
		            	</div>
	            	</div>
	            </c:if>
			</div>
			<div class="col-md-1">
				
			</div> 	
		</div>	
	</div>
	<div id="occdeatils" style="display: none;">
		
	</div>	
</div>
<script type="text/javascript">
	var occTooltipTemplate = "<html><div class='popover' style='width:900px'><div class='arrow'></div><div class='popover-content'>"
		occTooltipTemplate += "</div><div style='float:right;text-align:right;padding-bottom:10px;padding-right:10px;'><a class='readmore' href='#'>Occupation Details ></a></div></div></html>";
	var loading = '<div align="center"><img src="${pageContext.request.contextPath}/images/loader.gif"></div>';
	$(document).ready(function(){
		fnInitRollOver('occpopover','right');
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
	
	/* function fnOccReadMore(occId)
	{
		alert(occId);
		divId = occId;
		$("#occId_"+divId).show();
		$("#occExamDetailsId").hide();
		$("#occReturnId").show();
	} */
	
	function fnReturnBackToOccExam()
	{
		$("#occReturnId").hide();
		$("#occId_"+divId).hide();
		$("#occExamDetailsId").show();
	}
	
	function fnReturnBackToEduPath()
	{
		$("#occExamMainDiv").hide();
		$("#eduPathDiv").show();
	}
	
	function fnOccReadMore(occId)
	{
		$("#occExamDetailsId").hide();
		$("#occdeatils").html('');
		$("#occdeatils").show();
		$("#my_scrolling_pane").scrollTop(0);
		$("#occdeatils").html(loading);
		//get the content and show the occupation details
		$.ajax({
			  url: "${pageContext.request.contextPath}/myapp/OccupationAction",
			  data:{"occupationId":occId},
			  dataType:"html",
			  success: function(result){
				 $("#occdeatils").html(result);
				 $("#my_scrolling_pane").scrollTop(0);
			  },
			  error: function(jqXHR, textStatus, errorThrown )
			  {
				 // alert(errorThrown)
			  }
		});
	}
	
	function fnHideOccupation()
	{
		$("#occExamDetailsId").show();
		$("#occdeatils").hide();	
	}
</script>