<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="fn" uri="fn.tld"%>

<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%> 

<div class="edupath-padding">
<div class="row" style="margin-left: 0px;">
	<div class="col-md-12" style="margin-bottom:30px; margin-top: 10px;">
		<div class="pagetitle">
			<span><s:text name="com.edupath.college.parameter.title"></s:text></span>
		</div>
	</div>
</div>

<div id="editDetails">
	<div style="margin-bottom:10px;">
		<div class="row" style="margin-bottom:10px;">
			<div class="col-md-4 col-xs-7">
				<div class="col-location-param">
					<span><s:text name="com.edupath.college.parameter.location"></s:text> : </span>
				</div>
			</div>
			<div class="col-md-4 ">
				<div class="btn-group">
		         <input type="hidden" id="toggleId" value="NO">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="1" id="yes_1">YES</button>
		         <button type="button" class="btn btn_qt btn_no" data-answer="NO" data-serial="1" id="no_1">NO</button>
		       </div>
			</div>
		</div>
		<div class="row" id="collegeTxtId" style="display: none;">
			<div class="col-md-4 col-xs-7">
				<div class="col-location-param">
					<span><s:text name="com.edupath.college.parameter.locationnear"></s:text> : </span>
				</div>	
			</div>
			<div class="col-md-6 col-mod-offset-2 col-xs-12">
				<textarea class="college-param-textarea" name="address" id="addressAreaId" >${studentParametersDTO.address}</textarea>
				<div class="map-locate-div">
					<a href="#" onclick="fnOpenMap();"><s:text name="com.edupath.college.parameter.location.map"></s:text></a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-11 col-xs-11 collegeparamwidth" >
			<table class="table table-bordered course_table tablepaddingcollege">
			    <thead>
			        <tr>
			            <th class="col-md-2 col-xs-3 tablepaddingcollege"><s:text name="com.edupath.college.parameter.name"/></th>
			            <th class="col-md-4 col-xs-4 tablepaddingcollege"><s:text name="com.edupath.college.parameter.important"/></th>
			            <th class="col-md-4 col-xs-4 tablepaddingcollege">Available information for short-listing</th>
			            <th class="col-md-1 col-xs-1 tablepaddingcollege" style="text-align:center;"><s:text name="com.edupath.college.parameter.select"/></th>
			        </tr>
			    </thead>
	   		 	<tbody>
	   		 		<c:forEach items="${collegeParamDetails}" var="collegeParam" varStatus="paramCounter">
	   		 		<tr class="tablepaddingcollege">
	             		<td class="tablepaddingcollege">${collegeParam.parameter}</td>
	             		<td class="tablepaddingcollege">${collegeParam.description}</td>
	             		<td class="tablepaddingcollege">
	             			<c:forEach items="${collegeParam.values}" var="value" varStatus="counter">
	             				${value.displayValue}<c:if test="${counter.index < collegeParam.values.size() - 1}">, </c:if>
	             			</c:forEach>
	             		</td>
	             		<td align="center" class="tablepaddingcollege">
	             			<div class="checkbox checkbox-primary">
								<input id="checkbox_${paramCounter.index + 1}" name="collegeParam" value="${collegeParam.id}" class="styled" type="checkbox"> 
								<label for="checkbox_${paramCounter.index + 1}"></label>
							</div> 
	             		</td>
	         		</tr>
	   		 		</c:forEach>
	  			</tbody>
			</table>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12 question_action" style="padding-right: 68px !important;margin-top: 0px;margin-bottom: 60px;">
		    <button class="btn btn_action" onclick="fnSaveCollegeParam();"><span class="next-spn"><s:text name="com.edupath.common.label.submit"></s:text></span></button>
		</div>
	</div>
</div>

<div id="readDetails">
	<p>
		<c:if test="${studentParametersDTO.address ne null and fn:length(studentParametersDTO.address) gt 0}">
			Your venue preference is ${utils:replaceXMLEntities(studentParametersDTO.address)}.<br><br>
		</c:if>
		Your college parameter selection is as below. Click <a href="#" onclick="fnEditCollegeParameters()">here</a> to modify
		<br><br>
		  Parameters:
<%-- 		  <c:set var="option" value="${fn:split(utils:replaceXMLEntities(studentParametersDTO.selectedParameters), ',')}"></c:set> --%>
		  <ul>	
	          <c:forEach items="${studentParametersDTO.collegeParams}" var="collegeParam" varStatus="count">
		        <li>
	            	<label class="qusetion_txt_label">${collegeParam.parameter}</label>
	           </li>
	          </c:forEach>
          </ul>
	</p>
</div>
</div>

<div class="modal fade" id="collegeModalId" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 500px !important; height: 200px !important;">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h5>Message</h5>
        </div>
        <div class="modal-body" style="border-top:2px solid #1E5177; padding-left: 25px;">
        	<p id="collegeMSGId" style="background-color: #E6E3E3;padding: 5px;"></p>
        </div>
        <div class="modal-footer" style="text-align: right;">
          <button type="button" class="btn custom-modal-btn" data-dismiss="modal"><s:text name="com.edupath.questionnarie.model.btn.label"/></button>
        </div>
      </div>
    </div>
  </div>
<s:form name="CollegeForm" id="CollegeForm" method="post">
	<s:hidden name="selectedParameters" id="selectedParametersId" value="%{studentParametersDTO.selectedParameters}"></s:hidden>
	<s:hidden name="address" id="addressId" value="%{studentParametersDTO.address}"></s:hidden>
	<s:hidden name="id" id="paramId" value="%{studentParametersDTO.id}"></s:hidden>
	<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
    <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
</s:form>

<style>
.college-param-textarea
{
    width: 562px;
    height: 110px;
    border: 1px solid #DDDDDD;
    resize: none;
}
.col-location-param
{
    width: 100%;
    padding-top: 10px;
    text-align: right;
    font-weight: 600;
}
.map-locate-div
{
    text-align: right;
    padding-right: 25px;
    text-decoration: underline;
    font-size: 10pt;
}

</style>

<script type="text/javascript">
var form = document.CollegeForm;


$(document).ready(function()
{
	if( $('#paramId').val() > 0)
	{
		var select = $('#selectedParametersId').val();
		if(select != '' && select != undefined)
		{
			var selectArr = select.split(",");
			$.each(selectArr, function(key, item)
			{
				$('input[name=collegeParam]').each(function(key, value)
				{
					if(item == value.value)
					{
						$(this).prop("checked", true);
					}	
				});
			});
		}
		var addrStr = $('#addressId').val();
		
		if($.trim(addrStr).length > 0)
		{
			$('#collegeTxtId').slideDown();
			$('#toggleId').val('YES');
			$('#no_1').not(this).removeClass('btn_no');
			$('#yes_1').addClass('btn_yes');
		}	
		
		$("#editDetails").hide();
		$("#readDetails").show();
	  }
	 else
		{
		 	$("#editDetails").show();
			$("#readDetails").hide();
		}
		
	
	  $('.btn_qt').click(function()
	  {
		  var answer = $(this).data('answer');
		  var slNo = $(this).data('serial');
		  $('#toggleId').val(answer);
		  if(answer == 'YES')
		  {
			  $('#no_'+slNo).not(this).removeClass('btn_no');
			  $('#yes_'+slNo).addClass('btn_yes');
			  $('#collegeTxtId').slideDown();
			  if( $('#paramId').val() > 0)
			  {
				 $('#addressAreaId').val(fnReplaceBackXMLEntities('${utils:replaceJSONEntities(utils:replaceXMLEntities(studentParametersDTO.address))}'));
			  }
		  }
		  else
		  {
			  $('#yes_'+slNo).not(this).removeClass('btn_yes');
			  $('#no_'+slNo).addClass('btn_no');
			  $('#addressAreaId').val('');
			  $('#collegeTxtId').slideUp();
		  }	
		
	  });	
});

function fnEditCollegeParameters()
{
	$("#editDetails").show();
	$("#readDetails").hide();
}

function fnSaveCollegeParam()
{
	if(fnSetFormData())
	{
		fnSubmit();
	}	
}

function fnSetFormData()
{
	var selectedValue = [];
	var toggle = $('#toggleId').val();
	var address = $('#addressAreaId').val();
	$('input[name=collegeParam]:checked ').each(function(key, value)
	{
		selectedValue.push($(this).val());
	});
	
	if(selectedValue.length <= 0)
	{
		$('#collegeMSGId').html('<s:text name="com.edupath.college.parameter.select.msg"/>');
		$('#collegeModalId').modal();
		
		return false;
	}
	
	if(toggle == 'YES')
	{
		if( $.trim(address ) == '' || address == undefined)
		{
			$('#collegeMSGId').html('<s:text name="com.edupath.college.parameter.location.msg"/>');
			$('#collegeModalId').modal();
			return false;
		}	
	}
	else
	{
		address = "";
	}
	
	$('#addressId').val(address);
	
	$('#selectedParametersId').val(selectedValue.join(","));
	return true;
}

function fnSubmit()
{
	form.action = '${pageContext.request.contextPath}/myapp/insertOrUpdateCollegeParameter';
	form.submit();
}

function fnOpenMap()
{
	window.open("${pageContext.request.contextPath}/pages/collegeparameter/CollegeLocaterMapScreen.jsp", "myWindow", "width=900, height=600");
}

function fnGetAddress()
{
  return fnReplaceXMLEntities($('#addressAreaId').val());
}

function fnSetAddress(address)
{
	$('#addressAreaId').val(address);
}

</script>
