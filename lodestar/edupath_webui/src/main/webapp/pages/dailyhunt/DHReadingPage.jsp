<%@ taglib uri="/struts-tags" prefix="s"%>
<head>
<link href="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/ApplicationStyles.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/ReadingPage.css" rel="stylesheet"  type="text/css"/>
		  
<link href="${pageContext.request.contextPath}/thirdparty/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/font-awesome/css/font-awesome.min.css" rel="stylesheet" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/boxview/crocodoc.viewer.min.css" rel="stylesheet" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/nanoScroller/enscroll-0.6.1.min.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/thirdparty/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/CommonScripts.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/turn4/turn.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery_form.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/thirdparty/boxview/crocodoc.viewer.min.js" type="text/javascript" ></script>

<script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=Promise"></script>
<script src="https://cdn01.boxcdn.net/platform/preview/1.23.0/en-US/preview.js"></script>
<link rel="stylesheet" href="https://cdn01.boxcdn.net/platform/preview/1.23.0/en-US/preview.css" />

</head>

<style>

@media print {
    .no-print {
        display:none !important;
    }
}

</style>
<s:form name="MenuItemForm" id="MenuItemForm" theme="simple">
<div class="container-fluid" id="fluidContainer">
	<div id="maincontent" class="no-print disableselect" oncontextmenu="return false">
		<div class="row rpheadingbar">
			<div class="col-md-8">
				<img src="${pageContext.request.contextPath}/images/ribbon.gif" height="50px">
				<div class="rppagetile" id="occindname">
					 
				</div>	
			</div>
			 
		</div>
		
		<!-- Start Sasedeve Edited by Mrutyunjaya on Date 02-01-2018 -->	
		<!-- <div class="row">
	
			<div class="col-md-12" style="display:none">
			
		
				<div id="loader_div" style="width: 75px; margin: auto">
					<div style="position: absolute; z-index: 1000; top: 100;" ><img src="${pageContext.request.contextPath}/images/loader.gif"></div>
				</div>
				<div id="readingContent">
					<div>
					</div>
					<div></div>
				</div>
			</div> 
			
			
			
			
			
			
		</div> -->
		
		<!-- End Sasedeve Edited by Mrutyunjaya on Date 02-01-2018 -->
		
		
		<!-- Start Sasedeve Edited by Mrutyunjaya on Date 02-01-2018 -->
	<!--	<div class="col-md-2 col-centered pagenumber_div"  >
		
		
		
			<span id="keyPreviousId" class="glyphicon glyphicon-circle-arrow-left cust-left-icon"></span>
				Page <span id="currentpage">1</span> of <span id="maxpage">1</span>
			<span id="keyNextId" class="glyphicon glyphicon-circle-arrow-right cust-right-icon"></span>	
		</div> -->
		
		<!-- End Sasedeve Edited by Mrutyunjaya on Date 02-01-2018 -->
		
		
	</div>
	
   <s:hidden name="noteText" cssClass="notes_txtarea" id="noteText"></s:hidden>
	<div  id="moveDiv" style="display:none">
	    <div class="notes_content" >
		     <div  class="note_icon_close_div">
			     <i class="fa fa-close  close-notes note_icon_close" ></i>
		     </div>
	         <div class="scrollbox_note">
        		<p class="content_scroll_div add_new_content" contenteditable="true" style="outline: none; border: none;" onblur="fnSaveNotes();">
        		</p>
       		</div>
	    </div>
	</div>
	<div id="boxviewcontent" style="display:none"></div>
</div>
</s:form>
<!-- Start Sasedeve Edited by Mrutyunjaya on Date 02-01-2018 -->
<div class="col-md-12" style="height: 80%">
			
			<div id="Newboxview">
			
			</div>
			
			</div>
<!-- End Sasedeve Edited by Mrutyunjaya on Date 02-01-2018 -->

<div class="modal fade" id="timerPOPUP" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 300px !important;">

        <div class="modal-body" style="text-align: center; font-size: 11pt;">
		 <label id="message_lbl"></label>
        </div>
      </div>
    </div>
</div>

<script lang=JavaScript> 
	var highligths = new Object();
	var high_index = 1;
	var currentpage = 1;
	var firstTimeNotes = true, isChange=true;
	var isStop = true;
	var related = false;
	var shortListId;
	var occIndId;
	var occIndustryId;
	var occupationSummary;
	var pagenumber = 1;
	var maxpages = 1;
	var startTimer;
	var docType = '${docType}';
	var datastr ='${datastr}';
	var fromCart = null;
	$(document).ready(function(){
		if(null != docType && "" != docType && docType !== undefined)
		{
			if(docType == "OCCUPATION")
			{
				$("#occindname").text("INDUSTRY:");
			}
			else if(docType == "SUBJECT")
			{
				$("#occindname").text(docType+":");
			}
		}
		
		$("#maincontent").show();
		//$(".nano").nanoScroller();
		//$(".nano").nanoScroller({ alwaysVisible: true });
		
		$("#moveDiv").draggable();
		$("#moveDiv").resizable();
		$("#moveDiv").css({top: 120, right: 40, position:'absolute'});
	
		$("#readerclose").on("click", function(){
			window.close();
		});
		
		$("#showNotes").click(function(){
			fnGetNotes();
		});
		
		$(".note_icon_close").click(function(){
			$("#moveDiv").hide();
		});
		
		$('.scrollbox_note').enscroll();
		   
		$('.add_new_content').click(function(){
		   if(isStop)
		   {
	         $( "#moveDiv" ).draggable( "destroy" );
	         isStop = false;
		   }
		   $(this).attr('contenteditable',true);
	    });
	
		$("#hightlight").click(function()
		{
			fnAddHighlight();
			fnResizeDrag();
		});

		fnGetDocumentURLNLoad(true, datastr);
	});
	
	function fnGetNotes()
	{
		if(firstTimeNotes)
		{
			form = document.MenuItemForm;
			form.action = "${pageContext.request.contextPath}/myapp/StudentNotesAction";
			var options = { 
			        success:       showNoteResponse
			    }; 
			$("#MenuItemForm").ajaxSubmit(options);
		}
		else
		{
			showNoteResponse();
		}
	}
	
	var appendOccSummary;
	function fnCopySummaryToNotes()
	{
		if(occupationSummary)
		{
			appendOccSummary = "<br>"+occupationSummary;
			fnGetNotes();
		}	
	}

	function fnSaveNotes()
	{
		$("#noteText").val($('.add_new_content').html());
		fnINITEvent();
		form = document.MenuItemForm;
		form.action = "${pageContext.request.contextPath}/myapp/insertStudentNotesAction";
		var options = { 
		        success:       showNoteResponse
		    }; 
		$("#MenuItemForm").ajaxSubmit(options);
	}
	
	function fnINITEvent()
	{
		if(!isStop)
		{
			$("#moveDiv" ).draggable();
	        isStop = true;
		}
	}
	
	function showNoteResponse(responseText, statusText, xhr, $form)
	{
		if(firstTimeNotes)
		{
			$("#noteText").val(responseText);
			$('.add_new_content').html(responseText);
			firstTimeNotes = false;
		}
		
		if(appendOccSummary)
		{
			$('.add_new_content').append(appendOccSummary);
			appendOccSummary = null;
			fnSaveNotes();
		}	
		
		$("#moveDiv").show();
	}	
	
	function fnAddHighlight(topvalue, leftvalue, heightvalue, widthvalue)
	{
		topposition = 100+(high_index* 5);
		leftposition = 100+(high_index* 5);
		heightnumber = "20px";
		widthnumber = "200px";

		if(topvalue)
		{
			topposition = topvalue;
		}
		if(leftvalue)
		{
			leftposition = leftvalue; 
		}
		if(heightvalue)
		{
			heightnumber = heightvalue+"px";
		}
		if(widthvalue)
		{
			widthnumber = widthvalue+"px";
		}
		
		div_id = "high_"+high_index;
		var div = '<div class="highlighter" id='+div_id+'></div>';
		$("#readingContent").append(div);
		$("#"+div_id).css({top: topposition, left: leftposition, position:'absolute', height:heightnumber, width:widthnumber});
		
		$("#"+div_id).draggable({stop:function(event){fnResizeDrag();}});
		
		$("#"+div_id).resizable({stop:function(event){fnResizeDrag();}});
		high_index++;

	}
	
	var readingContent = "#readingContent";
	var viewer;
	function fnInitPage(relatedOcc, assetURL)
	{
		//fnGetAdditionDetails();
		//fnGetLastPageVisited();
		//fnGetHighLights();
		
		//alert(assetURL);
		
		
		//Start Sasedeve edited by Mrutyunjaya on date 02-01-2018
		
		
		
		var preview = new Box.Preview();
      	preview.show(assetURL, 'm2277MKKnjdlsOUf6UXwCV1v86ePBOQl', {
            container: '#Newboxview',
            showDownload: false,
			showAnnotations: false,
			useHotkeys: false,
			header:'none'
    
        });
		
		
		
		
		
		
		
		
		
		//assetURL = 'https://view-api.box.com/1/sessions/5a34f299b35947b5ac1e0b4d83553392/assets'; 
/*		viewer = Crocodoc.createViewer($("#boxviewcontent"), {
			url: assetURL,
			layout: Crocodoc.LAYOUT_PRESENTATION,
			zoom:1,
			enableTextSelection:false,
			enableDragging:false,
			enableLinks:false
		});
		viewer.load();
		//viewer.zoom(Crocodoc.ZOOM_FIT_WIDTH);
	
		
		viewer.on('ready', function (event) {
			maxpages = event.data.numPages;	
			$("#maxpage").html(maxpages);
			
			$(readingContent).turn({
				autoCenter: true,
				display: 'single',
				acceleration: true,
				gradients: !$.isTouch,
				height: 525,
				autoCenter: true,
				elevation:50,
				pages: maxpages
			});  
			
			$(readingContent).turn("peel","br");
		});
		
		viewer.on('pageload', function (event) {

			$('[data-toggle="tooltip"]').tooltip();
			if(event.data.page == pagenumber)
			{
				scrollTO(pagenumber);
				$(readingContent).turn('page', pagenumber);
			}	

			if(event.data.page == 1){
				fnAddConent(1);
			}
			if(event.data.page == 2){
				fnAddConent(2);
			}
		});
	
		$(readingContent).bind('turned', function(e, page) {
			fnAddConent(page);
			scrollTO(page);
			if(page == maxpages)
			{
				$(readingContent).turn("peel","bl");
			}
			else
			{
				$(readingContent).turn("peel","br");
			}
		});
		
		$(readingContent).bind('turning', function(e, page) {
			var range = $(this).turn('range', page);
			  for (page = range[0]; page<=range[1]; page++)
				addPage(page, $(this));			
		});*/
		
		
		//End Sasedeve edited by Mrutyunjaya on date 02-01-2018	
		
		
		
	}

	$(window).bind('keydown', function(e){
		if (e.keyCode==37){
			$(readingContent).turn('previous');
			//viewer.scrollTo(Crocodoc.SCROLL_PREVIOUS);
		}
		else if (e.keyCode==39){
			$(readingContent).turn('next');
			//viewer.scrollTo(Crocodoc.SCROLL_NEXT);
		}
	});

	//binding next previous
	$("#keyNextId").bind('click', function(e){
		$(readingContent).turn('next');
	});
	
	$("#keyPreviousId").bind('click', function(e){
		$(readingContent).turn('previous');
	});
	
	
	function fnAddConent(page)
	{
		var htmlcontent = $(".crocodoc-doc > .crocodoc-page:eq("+(page-1)+")").contents();
		$(readingContent+" .p"+page).html(htmlcontent.html());
		$(readingContent+" .p"+page).find("iframe").contents().find('html').html(htmlcontent.find("iframe").contents().find("html").html());
	}
	
	function scrollTO(topage)
	{
		$("#currentpage").html(topage);
		currentpage = topage;
		fnShowHighLights();
		fnUpdateCurrentPage();
	}
	
	
	function addPage(page, book) {
		// Check if the page is not in the book
		if (!book.turn('hasPage', page)) {
			// Create an element for this page
			var element = $('<div />');
			// Get the data for this page   
			/*
			element.html($(
					".crocodoc-doc > .crocodoc-page:eq(" + (page - 1) + ")")
					.html());
			*/
			// Add the page
			book.turn('addPage', element, page);

		}
	}

	function fnGetAdditionDetails() {
		if(null != docType && "" != docType && docType !== undefined)
			{
				if(docType == "OCCUPATION")
					{
						fnGetOccAdditionalDetail();
					}
				else if(docType == "SUBJECT")
					{
						fnGetSubjectAdditionalDetail();
					}
			}
			
	}
			
	function fnGetSubjectAdditionalDetail()
	{
		$.ajax({
			url : "${pageContext.request.contextPath}/myapp/subjectDetailsDocumentAction",
			method : "POST",
			dataType : "json",
			data : {
				"related" : false
			},
			success : function(result) {
				$("#loader_div").hide();
				if (result.status == "SUCCESS") {
					$("#occindname").text(result.details.name);
					//subjectId = result.details.id;
					//if (subjectId > 0) {
					//	$("#addSummartToNotes").show();
						//occupationSummary = result.details.occupationSummary;
						//fnAddRelatedOcc(result.details.relatedOcc);
					//}
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				//alert(errorThrown)
			}
		});
	}
	
	function fnGetOccAdditionalDetail()
	{
		$.ajax({
			url : "${pageContext.request.contextPath}/myapp/occIndDetailsDocumentAction",
			method : "POST",
			dataType : "json",
			data : {
				"related" : related
			},
			success : function(result) {
				$("#loader_div").hide();
				if (result.status == "SUCCESS") {
					if (result.details.shortListId > 0) {
						$("#addShort").hide();
						$('#addSummartToNotes').css('width', '70%');
						$("#removeShort").show();
						shortListId = result.details.shortListId;
					} else {
						$("#addShort").show();
						$('#addSummartToNotes').css('width', '77%');
						$("#removeShort").hide();
					}
			
					$("#occindname").text(result.details.name);
					occIndId = result.details.id;
					if (occIndId.indexOf("occ") >= 0) {
						$("#addSummartToNotes").show();
						if(result.details.industryId > 0){
							occIndustryId = result.details.industryId;
						}
						occupationSummary = result.details.occupationSummary;
						fnAddRelatedOcc(result.details.relatedOcc);
					}
				}
				if(fromCart){ //if reading from cart no option to add/remove from short
					$("#addShort").hide();
					$("#removeShort").hide();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				//alert(errorThrown)
			}
		});
	}
	
	function fnAddRelatedOcc(relatedOccList) {
		$("#relatedOccDiv").show();
		$("#relatedOcc").children().remove();
		$(relatedOccList).each(function() 
		{
			$("#relatedOcc").append(
							'<div class="rpoccupationnamediv"><span class="rpoccupationname" onclick="fnLoadContent('
									+ this.id
									+ ')">'
									+ this.name
									+ '</span></div>');
		});
	}

	function fnLoadContent(occId) {
		$(readingContent).turn('destroy');
		$(readingContent).children().remove();
		$(readingContent).append("<div></div><div></div>")
		fnGetDocumentURLNLoad(false, "occupationId=" + occId);
	}

	function fnGetDocumentURLNLoad(relatedOcc, local_dataStr) {
		//alert("Getting")
		highligths = new Object();
		high_index = 1;
		currentpage = 1;
		firstTimeNotes = true;
		isStop = true;
		related = relatedOcc;
		shortListId = null;
		occIndId = null;
		occIndustryId = null;
		occupationSummary = null;
		pagenumber = 1;
		maxpages = 1;

		//"occupationId="+occId+"&industryId="+occIndusId
		if(local_dataStr.indexOf("&") > 0){
			occIndustryId = local_dataStr.substring(local_dataStr.indexOf("&")+12);
		}
		console.log(" fnGetDocumentURLNLoad local_dataStr==>"+local_dataStr)
		$("#relatedOccDiv").hide();
		$("#relatedOcc").children().remove();
		$("#addShort").hide();
		$("#removeShort").hide();
		$('#addSummartToNotes').hide();
		$("#loader_div").show();
		$.ajax({
					url : "${pageContext.request.contextPath}/DailyHunt/getdocumentURLOccupationViewer",
					method : "POST",
					data : local_dataStr,
					dataType : "json",
					success : function(result) {
						if (result.status == "SUCCESS") {
							fnInitPage(relatedOcc, result.docURL);
						}
						if (result.status == "ERROR") {
							alert("Error while getting the document");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert("Report not available");
					}
				});
		console.log(" fnGetDocumentURLNLoad Done docURL==>"+docURL)
	}

	function fnAddtoShortList() {
		if (occIndId) {
			$.ajax({
						url : "${pageContext.request.contextPath}/myapp/insertMyShortListSubAction",
						method : "POST",
						data : {
							'occIndId' : occIndId,
							'occIndustryId': occIndustryId,
						},
						dataType : "json",
						success : function(result) {
							if (result.status == "SUCCESS") {
								if (result.shortListId > 0) {
									$("#addShort").hide();
									$('#addSummartToNotes').css('width', '70%');
									$("#removeShort").show();
									shortListId = result.shortListId;
									fnStartTimer('Added successfully.');
									
								}
							}
							if (result.status == "ERROR") {
								alert("Cannot add to shortlist as max already reached");
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							//alert(errorThrown)
						}
					});
		}
	}

	function fnDeleteFromShortList() {
		if (shortListId) {
			$.ajax({
						url : "${pageContext.request.contextPath}/myapp/deleteMyShortListSubAction",
						method : "POST",
						data : {
							"shortListId" : shortListId
						},
						dataType : "json",
						success : function(result) {
							if (result.status == "SUCCESS") {
								$("#addShort").show();
								$('#addSummartToNotes').css('width', '77%');
								$("#removeShort").hide();
								shortListId = null;
								fnStartTimer('Removed successfully.');
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							//alert(errorThrown)
						}
					});
		}
	}

	function fnShowHighLights() {
		$(".highlighter").remove();
		if (highligths) {
			$(highligths[currentpage]).each(
					function(index, currentObj) {
						fnAddHighlight(currentObj.top, currentObj.left,
								currentObj.height, currentObj.width);
					});
		}
	}

	function fnGetLastPageVisited() {
		$.ajax({
					url : "${pageContext.request.contextPath}/myapp/lastPageVisitedDocumentAction",
					method : "POST",
					dataType : "json",
					success : function(result) {
						if (result.status == "SUCCESS") {
							if (result.pageNumber > 0) {
								pagenumber = result.pageNumber;
							} else {
								fnUpdateCurrentPage();
							}
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						//alert(errorThrown)
					}
				});
	}

	function fnUpdateCurrentPage() {
		$.ajax({
					url : "${pageContext.request.contextPath}/myapp/updateCurrentPageDocumentAction",
					method : "POST",
					dataType : "json",
					data : {
						"pageNumber" : currentpage
					},
					success : function(result) {

					},
					error : function(jqXHR, textStatus, errorThrown) {
						//alert(errorThrown)
					}
				});
	}

	function fnGetHighLights() {
		$.ajax({
					url : "${pageContext.request.contextPath}/myapp/docHighlightsDocumentAction",
					method : "POST",
					dataType : "json",
					success : function(result) {
						if (result.status == "SUCCESS") {
							if (result.highlight) {
								highligths = JSON.parse(result.highlight);
							}
						} else {
							alert("Error while getting markers");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						//alert(errorThrown)
					}
				});

	}

	function fnResizeDrag() {
		if (!highligths) {
			highligths = new Object();
		}

		var curHighlights = new Array();
		$(".highlighter").each(function() {
			var highlight = new Object();
			highlight.top = $(this).position().top;
			highlight.left = $(this).position().left;
			highlight.height = $(this).height();
			highlight.width = $(this).width();

			curHighlights.push(highlight);
		});

		//curHighlights = {currentpage:curHighlights}
		delete highligths[currentpage];
		highligths[currentpage] = curHighlights;

		$.ajax({
					url : "${pageContext.request.contextPath}/myapp/updateHighlightDocumentAction",
					method : "POST",
					data : {
						"highlight" : JSON.stringify(highligths)
					},
					dataType : "json",
					success : function(result) {
					},
					error : function(jqXHR, textStatus, errorThrown) {
						//alert(errorThrown)
					}
				});
	}
	
	function fnCloseModal()
	{
		clearInterval(startTimer);
		$('#timerPOPUP').modal('hide');
	}
	
	function fnStartTimer(msg)
	{
		$('#message_lbl').html(msg);
		$('#timerPOPUP').modal();
		startTimer = setInterval(fnCloseModal,2000);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	var message = "Function Disabled!";
	function clickIE4() {
		if (event.button == 2) {
			alert(message);
			return false;
		}
	}
	function clickNS4(e) {
		if (document.layers || document.getElementById && !document.all) {
			if (e.which == 2 || e.which == 3) {
				alert(message);
				return false;
			}
		}
	}
	if (document.layers) {
		document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown = clickNS4;
	} else if (document.all && !document.getElementById) {
		document.onmousedown = clickIE4;
	}
	document.oncontextmenu = new Function("alert(message);return false")

	//////////F12 disable code////////////////////////
	document.onkeypress = function(event) {
		event = (event || window.event);
		if (event.keyCode == 123) {
			//alert('No F-12');
			return false;
		}
	}
	document.onmousedown = function(event) {
		event = (event || window.event);
		if (event.keyCode == 123) {
			//alert('No F-keys');
			return false;
		}
	}
	document.onkeydown = function(event) {
		event = (event || window.event);
		if (event.keyCode == 123) {
			//alert('No F-keys');
			return false;
		}
	}
	/////////////////////end///////////////////////
	
</script>