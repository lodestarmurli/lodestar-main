<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
    
<div class="panel" style="border-left:1px dotted #DEE0E2;border-left-width:2px ;height: 96%">
	<div class="wishListheading">
		<s:text name="com.edupath.wishlist.occupation"></s:text>
	</div>
	<div style="height: 2px;background-color: #DEE0E2; margin: 2px 10px 15px 32px">
	
	</div>
  	<div id="wishlistcontent">
  	
  	</div>
</div> 

<div id="industriesdiv" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
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
</div>

<div class="modal fade" id="occadd_msg_timer" style="width: 400px;position: fixed;left: auto;top: -120;right: 10px">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 300px !important;">
        <div class="modal-body" style="text-align: center; font-size: 11pt;">
		 	<label class="label" id="occadd_msg_label">Occupation added to wish list</label>
        </div>
      </div>
    </div>
</div>

<script type="text/javascript">
	
	var occTooltipTemplate = "<html><div class='popover' style='width:900px;color:#3e6293;'><div class='arrow'></div><div class='popover-content'>"
	occTooltipTemplate += "</div><div style='float:right;text-align:right;padding-bottom:10px;padding-right:10px;'><a class='readmore' href='#'>Occupation Details ></a></div></div></html>";
	var loading = '<div align="center"><img src="${pageContext.request.contextPath}/images/loader.gif"></div>';
	
	$(document).ready(function(){
		$("#industriesdiv").modal(
		{
			  keyboard: false,
			  show:false
		});
	});
	
	function fnReloadWishList()
	{
		$.ajax({
			  url: "${pageContext.request.contextPath}/jsonapp/OccupationWishList",
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

	function fnUpdateWishList(result)
	{
		
		console.log(result);
		
    	$("#wishlistcontent").html('');
    	var wishlistHTML = ""
        $(result.studentWishList).each(function(i, val){
        	for (var prop in val) {
        		wishlistHTML += '<ul class="list-group wishlist">'
        		wishlistHTML +='<li class="list-group-item-heading wishindustryTitle">'+prop+'</li>';
                var occupationlist = val[prop];
                for(var index in occupationlist)
                {
                	var occobj = occupationlist[index].occupation;
                	var rollOver = "";
                	if(occobj.rollOverContent)
                	{
                		rollOver = '<b>Required Code</b>: '+occobj.requiredRAISEC+'<br/><b>Required Ability</b>: '+occobj.requiredAbility+'<br/><br/><b>Occupation</b>:<br/>'+occobj.rollOverContent;	
                	}
                	
                	//START Sasedeve  by Mrutyunjaya on date 07-04-2017
                	
                	var newfitmentcolor="fitment_low";
                	if(occobj.newfitment==4)
                	{
                		newfitmentcolor="fitment_high";
                	}
                	else if(occobj.newfitment==3)
                	{
                		newfitmentcolor="fitment_above_average";
                	}
                	else if(occobj.newfitment==2)
                	{
                		newfitmentcolor="fitment_average";
                	}
                	var fitmentcolor="fitment_low";
                	if(occobj.fitment==4)
                	{
                		fitmentcolor="fitment_high";
                	}
                	else if(occobj.fitment==3)
                	{
                		fitmentcolor="fitment_above_average";
                	}
                	else if(occobj.fitment==2)
                	{
                		fitmentcolor="fitment_average";
                	}
                	
                	
                	wishlistHTML +='<li class="list-group-item" data-toggle="leftoccpopover" data-content="'+rollOver+'" id='+occobj.id+'><div class="wishoccupationname"><div>';
                     wishlistHTML += '<i class="fa fa-circle fa-fw '+fitmentcolor+'" aria-hidden="true"></i><i class="fa fa-square fa-fw '+newfitmentcolor+'" aria-hidden="true"></i> '+occobj.name+'</div><span style="cursor:pointer" class="fa fa-times" onclick="fnDeleteFromWishListById('+occupationlist[index].id+')"></span></div></li>';
                
                	//wishlistHTML += occobj.name+'</div><span style="cursor:pointer" class="fa fa-times" onclick="fnDeleteFromWishListById('+occupationlist[index].id+')"></span></div></li>';
                
                	//END Sasedeve  by Mrutyunjaya on date 07-04-2017
                }
                wishlistHTML += '</ul>';
            }
        });
        $("#wishlistcontent").html(wishlistHTML);
        
        fnInitRollOver('leftoccpopover', 'left');     
	}
	
	function fnDeleteFromWishListById(wishListId)
	{
		$.ajax({
			  url: "${pageContext.request.contextPath}/jsonapp/deleteOccupationWishList",
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
	function fnAddWishList(occId, occIndustryId, noOfIndustries, showDialog)
	{
		if(noOfIndustries < 2)
		{
			fnSubmitWishList(occId, occIndustryId, showDialog);
		}
		else
		{
			$("#industryitems").html(loading);
		 	$("#industriesdiv").modal("show");
			occupationId = occId;
			$.ajax({
				  url: "${pageContext.request.contextPath}/jsonapp/industriesOccupationWishList",
				  data:{
				  		"wishId": occId
				  		},
				  dataType:"json",
				  success: function(result){
					  if(result.industriesForOcc.length > 1)
					  {
						  $("#industryitems").html('');
			        	  wishlistHTML = ''
						  $(result.industriesForOcc).each(function(i, val)
						  {
		                    	wishlistHTML +='<div class="radio radio-primary"><input name="rad_industry" type="radio" value="'+val.id+'" id="ind_'+val.id+'">';
		                    	wishlistHTML += '<label for="ind_'+val.id+'">'+val.name+'</label></div>';
						  });
		                  $("#industryitems").html(wishlistHTML);
					  }
					  else
					  {
						  $("#industriesdiv").modal("hide");
						  fnSubmitWishList(occId, occIndustryId, showDialog);
					  }		  
				  },
				  error: function(jqXHR, textStatus, errorThrown )
				  {
					  alert("Error While getting Industries")
				  }
			});
		}	
	}
	
	function fnSelectIndustry()
	{
		var selected = $("input[type='radio'][name='rad_industry']:checked");
		if (selected.length > 0) {
		    selectedVal = selected.val();
		    $("#industriesdiv").modal("hide");
			fnSubmitWishList(occupationId, selectedVal, true);
		}
		else
		{
			alert("Select the Industry")
		}	
	}
	
	function fnSubmitWishList(occId, occIndustryId, showDialog)
	{
		$.ajax({
			  url: "${pageContext.request.contextPath}/jsonapp/addOccupationWishList",
			  data:{
				  		"wishId": occId,
				  		"occIndustryId": occIndustryId,
				  		"studentId":$("#studentId").val()
				  	},
			  dataType:"json",
			  success: function(result){
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
		$("#contentdiv").show();
		$("#occupationdetails").hide();	
	}
</script>   