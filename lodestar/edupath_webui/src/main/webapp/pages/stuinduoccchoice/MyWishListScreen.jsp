<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<div class="edupath-padding-nonchild">

<div class="row">
	<div class="col-md-12 pagetitle">
		 My Occupation Wishlist (${count})
	</div>				
	<div class="col-md-8 sectionheading">
		Read, review and shortlist your top 3-5 preferred occupations/Industry by browsing reports.
		
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
	<div class="col-md-4">
		<c:if test="${hasVisitedDoc}">
			<div class="mywishresume mywishtooltip" onclick="fnShowPreviousReport()" data-toggle="tooltip" data-placement="bottom" title="You'll be taken to the last accessed report where you left off.">
				Resume Report Reading >
			</div>
		</c:if>
	</div>
</div>

	<c:forEach items="${myWishList}" var="occupationEntry" varStatus="counter">
	<c:if test="${counter.index % 2 eq 0}">
	<div class="row">
	<c:set value="${counter.index}" var="rowNum"></c:set>
	</c:if>
		<div class="col-md-6">
			<div class="mywishindustrybox">
				<div class="row">
					<div class="col-md-12">
						<div class="mywishindustrynamediv">
							<div class="row">
								<div class="col-md-7">
									<c:set value="${fn:substringAfter(occupationEntry.key, '_')}" var="industryName"></c:set>
									<span class="mywishindustrynamelbl">${utils:replaceXMLEntities(industryName)}</span>
								</div>
								<div class="col-md-4">
									<c:set value="${fn:substringBefore(occupationEntry.key, '_')}" var="industryId"></c:set>
									<div class="mywishreadreportlbl" onclick="fnShowIndustryReport(${industryId})">Read report</div>
								</div>
								<div class="col-md-1" >
									<span style="cursor:pointer;color: #b7c1ca;" class="fa fa-arrow-circle-o-up up_${rowNum}" onclick="fnHideOccDetails('${rowNum}')"></span>
									<span style="cursor:pointer;color: #b7c1ca; display:none" class="fa fa-arrow-circle-o-down down_${rowNum}" onclick="fnHideOccDetails('${rowNum}')"></span>
								</div>
							</div>
						</div>	
					</div>	
				</div>	
				<div class="row occ_${rowNum}">
					<div class="col-md-12" >
						<c:forEach items="${occupationEntry.value}" var="occupation">
							<div class="my-wish-occ-row">
								<div class="col-md-6" style="width: 100%">
									<div class="mywishoccpationdiv mywishtooltip" data-toggle="occtooltip" title="Click to read report" onclick="fnShowOccReport(${occupation.id}, ${occupation.industryId})">
										<c:set var="visited">
											${visitedOccItems[occupation.longId]}
										</c:set>
										<img src="data:image/png;base64,${occupation.base64img}" class="mywishoccimage ${visited} img-responsive">
										<div class="mywishoccname ${visited}" >
										
										<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 15-05-2017 -->
																<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
													
													
												 	<i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 15-05-2017 -->
										
										
											 ${utils:replaceXMLEntities(occupation.name)}
										</div>
									</div>
								</div>
							</div>		
						</c:forEach>
					</div>
				</div>	
			</div>
		</div>					
	<c:if test="${counter.index % 2 ne 0}">
		</div>
	</c:if>
	</c:forEach>
	
	<c:if test="${myWishList.size() % 2 ne 0}">
		</div>
	</c:if>
	
</div>

<script>
	var newWindow; 
	function fnHideOccDetails(indusId)
	{
		$(".occ_"+indusId).toggle();
		$(".up_"+indusId).toggle();
		$(".down_"+indusId).toggle();
	}

	function fnGetContent(dataStr)
	{
		fnShowReadingPage();
		fnLoadContent(dataStr)
	}
	
	function fnShowIndustryReport(indusId)
	{
		fnGetContent("industryId="+indusId);
	}
	
	function fnShowOccReport(occId, occIndusId)
	{
		fnGetContent("occupationId="+occId+"&industryId="+occIndusId);
	}
	
	function fnShowPreviousReport()
	{
		fnGetContent("lastWishListDoc=true");
	}
	
	function fnShowReadingPage()
	{
		if(newWindow!=null)
        {
	         if(!newWindow.closed)
	         {
	        	 newWindow.close();
	         }
        }
		
		newWindow = window.open("${pageContext.request.contextPath}/pages/stuinduoccchoice/ReadingPage.jsp","ReadingPane"
				,"height=700px, width=1100px, left=0, top=0, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no",true);
		newWindow.focus();
	}
	
	var datastr;
	var docType;
	function fnLoadContent(localdataStr)
	{
		datastr = localdataStr;
		docType = "OCCUPATION";
		newWindow.onload = function()
	    {
			//newWindow.fnGetDocumentURLNLoad(true, dataStr);
	    }
	}
	
	$(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip();
	    $('[data-toggle="occtooltip"]').tooltip(
	    		{
	    			delay: {show: 1000, hide: 100},
	    		}
	    );
	});
</script>