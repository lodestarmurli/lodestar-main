<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<!-- v3 calendar 
<link href="${pageContext.request.contextPath}/thirdparty/calendar/fullcalendar.min.css" rel='stylesheet' />
<link href="${pageContext.request.contextPath}/thirdparty/calendar/fullcalendar.print.min.css" rel='stylesheet' media='print' />
<script src="${pageContext.request.contextPath}/thirdparty/calendar/lib/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/calendar/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/calendar/fullcalendar.min.js"></script> -->

 <!-- v4 calendar -->
<link href="${pageContext.request.contextPath}/thirdparty/calendar/packages/core/main.css" rel='stylesheet' />
<link href="${pageContext.request.contextPath}/thirdparty/calendar/packages/daygrid/main.css" rel='stylesheet' />
<link href="${pageContext.request.contextPath}/thirdparty/calendar/packages/timegrid/main.css" rel='stylesheet' />
<link href="${pageContext.request.contextPath}/thirdparty/calendar/packages/list/main.css" rel='stylesheet' />
<script src="${pageContext.request.contextPath}/thirdparty/calendar/packages/core/main.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/calendar/packages/interaction/main.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/calendar/packages/daygrid/main.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/calendar/packages/timegrid/main.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/calendar/packages/list/main.js"></script>

<style>

  body {
    margin: 0;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #script-warning {
    display: none;
    background: #eee;
    border-bottom: 1px solid #ddd;
    padding: 0 10px;
    line-height: 40px;
    text-align: center;
    font-weight: bold;
    font-size: 12px;
    color: red;
  }

  #loading {
    display: none;
    position: absolute;
    top: 10px;
    right: 10px;
  }

  #calendar {
    max-width: 900px;
    margin: 40px auto;
    padding: 0 10px;
  }

</style>


<div class="edupath-padding-summary">
<div class="rows">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<s:text name="Facilitator Calendar"></s:text>
				</div>			
			</div>
			
			<div class="portlet-body">
			  
				<div class="row" style="padding: 0 10px;">
				 	<div class="col-md-12"> 
				       <div class="col-md-4" style="display: inline-block;">
								<select name="facilitatorId" id="facilitatorId" class="form-control input-sm"  value="${facilitatorId}">
									<option value="-1">
					 		  	<s:text name="com.edupath.global.search.select.selected.title"></s:text> 
					 		  </option>
					 		  <c:forEach items="${facilitatorDetailsList}" var="facilitatorDetails">
					 		  	<option value="${facilitatorDetails.id}"> ${facilitatorDetails.name} </option>
					 		  </c:forEach>
								</select>
								
							 </div>	
							
				       
				       <div style="display: inline-block; padding-left: 10px;margin-top:5px;"><!--margin-top: 5px;  31/03/18--->
		           	   		<button class="btn blue" onclick="getFacilitatorSchedule()"><s:text name="com.edupath.common.go"></s:text></button>
				       </div>
					 </div>
					 
				</div>
				<br>
				<br>
				<div id='loading'>loading...</div>
				<div id='calendar'></div>
					
			   
			   <div id="viewFeedbackLoading" class="display-hide cust-loader">
					<div class="app-spinner">
				<!-- 		<i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i> -->
						<img src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
					</div>
				</div>
						
			</div>		
		</div>
	</div>
</div>
</div>


<script>
				
function getFacilitatorSchedule()
{
	//$('#calendar').html("");
	var faciId =  $('#facilitatorId').val();
	var url = "${pageContext.request.contextPath}/myapp/getFacilitatorScheduleFacilitatorCalendarAction";
	$.ajax({
		url : url,
		global : false,
		type : "POST",
		data : (
		{
			"facilitatorId" : faciId
		}),
		success : function(resp) 
		{			
			data=JSON.stringify(resp);
			//var dueamount
			if (data != "" && data != null) 
			{
				$('#calendar').html("");
				    var calendarEl = document.getElementById('calendar');

				    var calendar = new FullCalendar.Calendar(calendarEl, {
				      plugins: ['rerenderEvents','interaction', 'dayGrid', 'timeGrid', 'list' ],
				      header: {
				        left: 'prev,next today',
				        center: 'title',
				        right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
				      },
				      //defaultDate: '2019-09-12',
				      editable: false,
				      navLinks: true, // can click day/week names to navigate views
				      eventLimit: true, // allow "more" link when too many events
				      events: resp,
				    	  
				      loading: function(bool) {
				        document.getElementById('loading').style.display =
				          bool ? 'block' : 'none';
				      }
				    });
					
				   	calendar.render();
			}	
		},
		error : function(msg, arg1, arg2)
		{
			//return false;
		}
	});
	
}



function fnOpenCompleteDetails(userid)
{
	console.log("userid= "+userid)
	$("#ShowCompleteStudentdetails").modal(
	{
		keyboard : false,
		backdrop : 'static'
	});
	var url = "${pageContext.request.contextPath}/myapp/getCompleteSummaryStudentSummary";
	$.ajax({
		url : url,
		global : false,
		type : "POST",
		data : (
		{
			"userId" : userid
		}),
		success : function(resp) 
		{
			//console.log("resp=>"+JSON.stringify(resp));
			
			data=JSON.stringify(resp);
			var dueamount
			if (data != "" && data != null) 
			{
				console.log("1data=>"+data);
				resp[0].dueAmount
				if(resp[0].dueAmount==null)
				{
					dueamount="<s:text name="com.edupath.common.no.record.found" />";
				}else
				{
					dueamount=resp[0].dueAmount
				}
				$("#ShowCompleteStudentdetails").html('<div class="modal-dialog modal-full"><div class="modal-content"><div class="modal-header"><h4 class="modal-title"><s:text name="com.edupath.student.summary.title"/><button type="button" class="btn btn-sm btn-danger" onclick="fnCloseSudentDetailsDialog();"style="float:right"><s:text name="X"/></button></h4></div><div class="modal-body"><table class="table table-striped table-hover table-bordered"><tr><td><s:text name="com.edupath.student.summary.name" /></td><td>'+resp[0].name+'</td></tr><tr><td><s:text name="com.edupath.student.summary.type" /></td><td>'+resp[0].studentTypeStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.email" /></td><td>'+resp[0].fatheremailId+'</td></tr><tr><td><s:text name="com.edupath.student.summary.school" /></td><td>'+resp[0].schoolName+'</td></tr><tr><td><s:text name="com.edupath.viewfeedback.summary.facilitator.name" /></td><td>'+resp[0].facilitatorName+'</td></tr><tr><td><s:text name="com.edupath.student.summary.session1" /></td><td>'+resp[0].session1DateStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.session2" /></td><td>'+resp[0].session2DateStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.session3" /></td><td>'+resp[0].session3DateStr+'</td></tr><tr><td><s:text name="com.edupath.student.summary.venue" /></td><td>'+resp[0].venue+'</td></tr><tr><td><s:text name="com.edupath.student.summary.dueamount" /></td><td>'+dueamount+'</td></tr></table></div> </div></div>');
				console.log("2data=>"+resp[0].name);
			}	
		},
		error : function(msg, arg1, arg2)
		{
			//return false;
		}
	});
}

	
	
</script>