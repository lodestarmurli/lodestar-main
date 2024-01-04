<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
  
<s:hidden name="whistListSize" id = "whistListSize"/>
<div class="panel" style="border-left:1px dotted #DEE0E2;border-left-width:2px ;height: 96%">
	<div class="wishListheading">
		<s:text name="Your 3 Choices"></s:text>
	</div>
	<div style="height: 2px;background-color: #DEE0E2; margin: 2px 10px 15px 32px">
	
	</div>
  	<div id="wishlistcontent">
  	
  	</div>
</div> 

<!-- <div id="industriesdiv" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
     <!--<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><s:text name="com.edupath.occupation.wish.industry.select"></s:text> </h4>
      </div>
      <div class="modal-body">
      	<div id="industryitems">
      		
      	</div>
      	<button type="button" class="btn btn-default btn-success btn-block" onclick="fnSelectIndustry()"></span>Submit</button>  
      </div>
    </div>

  </div>
</div>  -->

<div class="modal fade" id="occadd_msg_timer" style="width: 400px;position: fixed;left: auto;top: -120;right: 10px">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 300px !important;">
        <div class="modal-body" style="text-align: center; font-size: 11pt;">
		 	<label class="label" id="occadd_msg_label">Added to wish list</label>
        </div>
      </div>
    </div>
</div>

<script type="text/javascript">
	
	var occTooltipTemplate = "<html><div class='popover' style='width:900px;color:#3e6293;'><div class='arrow'></div><div class='popover-content'>"
	occTooltipTemplate += "</div><div style='float:right;text-align:right;padding-bottom:10px;padding-right:10px;'><a class='readmore' href='#'>Occupation Details ></a></div></div></html>";
	var loading = '<div align="center"><img src="${pageContext.request.contextPath}/images/loader.gif"></div>';
	
	var clusterTooltipTemplate = "<html><div class='popover' style='width:900px;color:#3e6293;'><div class='arrow'></div><div class='popover-content'>"
		clusterTooltipTemplate += "</div><div style='float:right;text-align:right;padding-bottom:10px;padding-right:10px;'></div></div></html>";
		
	$(document).ready(function(){
		var wishlistsize=parseInt($("#whistListSize").val());
		console.log("bharath whistListSize=>"+wishlistsize)
		$("#industriesdiv").modal(
		{
			  keyboard: false,
			  show:false
		});
		
	      
		 
	});

	 
	function fnReloadWishList()
	{
		console.log("bharath fnReloadWishList")
		$.ajax({
			  url: "${pageContext.request.contextPath}/jsonapp/OccupationClusterWishList",
			  data:{"studentId":$("#studentId").val()},
			  dataType:"json",
			  success: function(result){
				  
				//  console.log(result);
				  fnUpdateWishList(result)
			  },
			  error: function(jqXHR, textStatus, errorThrown )
			  {
				  //alert(errorThrown)
			  }
		});
	}
	
	var whistListSizeFromJS = 0;
	var ListOfWishList='';
	function fnUpdateWishList(result)
	{
		whistListSizeFromJS = 0;
		ListOfWishList="<b>Your WishList: </b><br>";
		console.log("bharath fnUpdateWishList result=>"+JSON.stringify(result))
		
    	$("#wishlistcontent").html('');
    	var wishlistHTML = ""
    		wishlistHTML += '<ul class="list-group wishlist">'
        $(result.studentWishList).each(function(i, occupationlist){
        	whistListSizeFromJS++;
        	console.log("bharath writing whistListSizeFromJS=>>"+whistListSizeFromJS)
            
        	console.log("bharath fnUpdateWishList studentWishList occupationlist=>"+JSON.stringify(occupationlist))
        	console.log("bharath fnUpdateWishList studentWishList i=>"+i)
        	 
        		
                //for(var index in occupationlist)
                //{
                	console.log("bharath fnUpdateWishList occupationlist[index].occupation=>"+JSON.stringify(occupationlist))	
                	var occobj = occupationlist;
                	var rollOver = "";
                	if(occobj.rollOverContent)
                	{
                		rollOver = '<b>Occupation</b>:<br/>'+occobj.rollOverContent;	
                	}
                	
                	if(occobj.iscluster==true)
               		{
                		wishlistHTML +='<li class="list-group-item" data-toggle="leftclspopover" data-content="'+rollOver+'" id='+occobj.id+'><div class="wishoccupationname"><div>';
                 		wishlistHTML += ' '+occobj.name+'</div><span style="cursor:pointer" class="fa fa-times" onclick="fnDeleteFromWishListById('+occupationlist.id+')"></span></div></li>';
               		}
                	else
               		{
               			wishlistHTML +='<li class="list-group-item" data-toggle="leftoccpopover" data-content="'+rollOver+'" id='+occobj.id+'><div class="wishoccupationname"><div>';
                 		wishlistHTML += ' '+occobj.name+'</div><span style="cursor:pointer" class="fa fa-times" onclick="fnDeleteFromWishListById('+occupationlist.id+')"></span></div></li>';
               		}
                	//wishlistHTML += occobj.name+'</div><span style="cursor:pointer" class="fa fa-times" onclick="fnDeleteFromWishListById('+occupationlist[index].id+')"></span></div></li>';
                
                //}
                	ListOfWishList+=occobj.name+"<br>";
              
        });
    		  wishlistHTML += '</ul>';
        $("#wishlistcontent").html(wishlistHTML);
        
        fnInitClsusterRollOver('leftclspopover', 'left');  
        fnInitRollOver('leftoccpopover', 'left');  
	}
	
	function fnDeleteFromWishListById(wishListId)
	{
		console.log("bharath fnDeleteFromWishListById wishListId=>"+wishListId)
		$.ajax({
			  url: "${pageContext.request.contextPath}/jsonapp/deleteOccupationClusterWishList?option=optionA",
			  data:{
				  	"studentId":$("#studentId").val(),
			  		"wishListId": wishListId
			  		},
			  dataType:"json",
			  success: function(result){
				  fnUpdateWishList(result)
			  },
			  error: function(jqXHR, textStatus, errorThrown )
			  {
				//  alert(errorThrown)
			  }
		});
	}
	
	var occupationId;
	function fnAddWishList(occId,  showDialog)
	{
		
		console.log("bharath fnAddWishList occId=>"+occId)
		//console.log("bharath fnAddWishList occIndustryId=>"+occIndustryId)
		//console.log("bharath fnAddWishList noOfIndustries=>"+noOfIndustries)
		console.log("bharath fnAddWishList showDialog=>"+showDialog)
		
			fnSubmitWishList(occId, showDialog);
		
	}
	
	function fnSelectIndustry()
	{
		console.log("bharath fnSelectIndustry")
		var selected = $("input[type='radio'][name='rad_industry']:checked");
		if (selected.length > 0) {
		    selectedVal = selected.val();
		    $("#industriesdiv").modal("hide");
			fnSubmitWishList(occupationId, true);
		}
		else
		{
			alert("Select the Industry")
		}	
	}
	
	function fnSubmitWishList(occId, showDialog)
	{
		console.log("bharath fnSubmitWishList occId=>"+occId)
		//console.log("bharath fnSubmitWishList occIndustryId=>"+occIndustryId)
		//console.log("bharath fnSubmitWishList showDialog=>"+showDialog)
		$.ajax({
			  url: "${pageContext.request.contextPath}/jsonapp/addOptionAOccupationClusterWishList",
			  data:{
				  		"wishId": occId,
				  		//"occIndustryId": occIndustryId,
				  		"studentId":$("#studentId").val()
				  	},
			  dataType:"json",
			  success: function(result){
				  console.log("bharath fnSubmitWishList result:{}",JSON.stringify(result))
				  fnUpdateWishList(result);
				  showDialogWin("success", "Occupation added to wish list", occId, showDialog);
			  },
			  error: function(jqXHR, textStatus, errorThrown)
			  {
					showDialogWin("error", "Error while adding Occupation to wish list", occId, showDialog)
			  }
		});
	}

	function showDialogWin(status, message, occId, showDialog)
	{
		console.log("bharath showDialogWin status=>"+status)
		console.log("bharath showDialogWin message=>"+message)
		console.log("bharath showDialogWin occId=>"+occId)
		console.log("bharath showDialogWin showDialog=>"+showDialog)
		
		  if(showDialog == false)
		  {
			 //do nothing
		  } 
		  else
		  {
			$("#occadd_msg_timer").modal();
			if(status == "success")
			{
				$("#occadd_msg_label").addClass("label-success");
			}
			$("#occadd_msg_label").text(message)
		  }
		  
	  	  setTimeout(function(){
			  $("#occadd_msg_timer").modal("hide");
			  $("#"+occId).prop('checked', false);
		  }, 2000);	
	}
	
	function fnInitRollOver(cssclass, placementstr)
	{
		console.log("bharath fnInitRollOver cssclass=>"+cssclass)
		console.log("bharath fnInitRollOver placementstr=>"+placementstr)
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
	
	function fnInitClsusterRollOver(cssclass, placementstr)
	{
		console.log("bharath fnInitRollOver cssclass=>"+cssclass)
		console.log("bharath fnInitRollOver placementstr=>"+placementstr)
		$('[data-toggle="'+cssclass+'"]').popover({
			template:clusterTooltipTemplate,
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
		console.log("bharath fnOccReadMore occId=>"+occId)
		$("#contentdiv").hide();
		$("#occupationdetails").html('');
		$("#occupationdetails").show();
		$("#my_scrolling_pane").scrollTop(0);
		$("#occupationdetails").html(loading)
		//get the content and show the occupation details
		$.ajax({
			  url: "${pageContext.request.contextPath}/myapp/OccupationAction",
			  data:{"occupationId":occId},
			  dataType:"html",
			  success: function(result){
				 $("#occupationdetails").html(result);
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
		console.log("bharath fnHideOccupation ")
		$("#contentdiv").show();
		$("#occupationdetails").hide();	
	}
	
	function fnSubmit()
	{
		var wishlistsize=parseInt($("#whistListSize").val());
		console.log("bharath whistListSize=>"+wishlistsize)
		if(whistListSizeFromJS<3)
		{
			$("#lessCareermodal").modal('show');
		}
		if(whistListSizeFromJS == 3)
		{
			$("#ConfirmWishsList").html(ListOfWishList);
			$("#ConfirmSubmit").modal('show');
		}
			
	}
	function fnConfirmSubmit()
	{
		$("#ConfirmSubmit").modal('hide');
		form.action = "${pageContext.request.contextPath}/jsonapp/submitOccupationClusterWishList?option=optionA";
		form.submit();
	}
	function fnConfirmSubmitcancel()
	{
		$("#ConfirmSubmit").modal('hide');
	}
	
	 
</script>   