<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="rows">
  <div class="col-md-12">
    <div class="col-md-9">
     <h3><s:text name="com.edupath.student.tum.career"></s:text></h3> 
     <p class="auto_txt"><s:text name="com.edupath.student.tum.autosave"></s:text></p>
    </div>
    <div class="col-md-3">
    <div class="do-know">
     <%--  <i class="fa fa-bell"></i><span class="do-know-spn"><s:text name="com.edupath.student.tum.know"></s:text></span> --%>
      <p class="do-know-txt"></p>
    </div>
    </div>
  </div>
  <div class="col-md-12">
   <div class="col-md-10">
    <div class="div_lable qusetion_txt">
     <div class="question_left_div">8.&nbsp;&nbsp;</div>
      <div class="question_right_div">
        <p class="qusetion_txt">What do you want to become in life ?</p>
      </div>
    </div>
    <div class="txt-field" style="padding-top:20px">
       <div class="row">
       <div class="col-md-10">
        <s:textarea name="occupation" id="occupation" cssClass="input-textarea auto_save" data-serial="11" cols="10" placeholder="e.g. Would like to be a painter" maxlength="500"></s:textarea>
        <span class="error-block"></span>
       </div>
       <div class="col-md-1">
        <%--   <div class="leftarrowdiv career_Div">
             <s:text name="com.edupath.student.tum.tooltip"></s:text>
          </div> --%>
       </div>
      </div>
    </div>
   </div>
  </div>
  
  <div class="col-md-12">
   <div class="col-md-9">
    <div class="div_lable qusetion_txt qusetion_txt">
      <div class="question_left_div">9.&nbsp;&nbsp;</div>
      <div class="question_right_div">
          <p class="qusetion_txt" style="padding-left:8px"> Name three things that you would like to know in order to make a decision about your career / next education </p>
      </div>
    </div>
    <div class="txt-field" style="padding-left:35px">
       <span class="error-block"></span>
       <div class="things-txt">
        <s:textfield name="things" id="things_1" cssClass="input-md auto_save" data-serial="12" placeholder="1." maxlength="500"></s:textfield>
       </div>
        <div class="things-txt">
          <s:textfield name="things" id="things_2" cssClass="input-md auto_save" data-serial="12" placeholder="2." maxlength="500"></s:textfield>
        </div>
        <div class="things-txt">
          <s:textfield name="things" id="things_3" cssClass="input-md auto_save" data-serial="12" placeholder="3." maxlength="500"></s:textfield>
        </div>
    </div>
   </div>
  </div>
  
  <div class="col-md-12">
   <div class="col-md-9">
    <div class="div_lable qusetion_txt">
     <div class="question_left_div">10.&nbsp;&nbsp;</div>
      <div class="question_right_div">
        <p class="qusetion_txt" style="padding-left:12px">Where would you like to be <b>geographically located</b> once you start working?</p>
       </div>
    </div>
    <div class="txt-field" style="padding-left:35px">
     <div>
      <c:set var="located" value="${fn:split('My City,My state,Within India,Outside India,No preference', ',') }"></c:set>
       <c:forEach items="${located}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_${count.index}" value="${item}" data-serial="13" name="located" class="auto_save">
             <label for="inlineRadio_${count.index}" class="qusetion_txt_label">${item}</label>
           </div>
       </c:forEach>
      <%--  <s:radio label="Current board" cssClass="board currentBoard auto_save" name="located" id="located" list="{' My City', ' My state', ' Within India', ' Outside India', ' No preference'}"  data-serial="13"/> --%>
     </div> 
      <span class="error-block"></span>
    </div>
   </div>
  </div>
  
  <div class="col-md-12 action-div">
      <button class="btn btn_action btn-back" onclick="fnBackSubmit('CareerinterestAction', '2','HobbiesAndInterestAction');"><span class="back-spn"><s:text name="com.edupath.common.label.back"></s:text></span></button>
      <button class="btn btn_action" onclick="fnStudentTUMFinalSubmit('CareerinterestAction');"><span class="next-spn"><s:text name="com.edupath.common.label.submit"></s:text></span></button> 	
      
      <!--22/03/18 style="margin-left: 90px;" -->
   </div> 
</div>
<s:form name="StudentTUMForm" id="StudentTUMForm" method="post">
   <s:hidden name="studentTUMList" id="studentTUMList"></s:hidden>
   <s:hidden name="serialNum" id="serialNum" value="10"></s:hidden>
   <s:hidden name="pageAction" id="pageAction" value="1"></s:hidden>
   <s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
   <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
   <s:hidden name="actionNameValue" id="actionNameValue"></s:hidden>
   <s:hidden name="deleteQusestionList" id="deleteQusestionList"></s:hidden>
</s:form>

<script type="text/javascript">
$(document).ready(function(){
  fnINITFormData();
	var occupation = '${OCCUPATION}';
	   if(occupation.length > 0)
	   {
		   var newData = $.parseJSON(occupation);
		   $('#occupation').textcomplete([{
			    match: /(^|\s)(\w{1,})$/,
			    search: function (term, callback) {
			        callback($.map(newData, function (arg)
			        {
			        	var re = new RegExp(term, 'gi');
			            return arg.match(re) != null ? arg : null;
			        }));
			    },
			    replace: function (newData) {
			        return newData + ' ';
			    }
			}]);
	   }
	   
	   $('.input-textarea').focusout(function() {
		    $('.leftarrowdiv').hide('slow');
		  });
	   
	   $( ".input-textarea" ).focus(function() {
		   $('.leftarrowdiv').show('slow');
		 });
});
function fnSetFormData()
{
	 var jsonArr = [];
	 var jsonOC = new Object();
	 jsonOC.questionSlNo = $('#occupation').data('serial');
	 jsonOC.answer = $('#occupation').val();
	 jsonArr.push(jsonOC);
	 
	 var jsonTH= new Object();
	 jsonTH.questionSlNo = $("input[name*='things']").data('serial');
	 
	 var things = [];
	 for(var count = 1; count <= 3; count++)
	 {
		 if($("#things_"+count).val() != '')
		 {
			 things.push($("#things_"+count).val());
		 }	 
	 }	 
	 jsonTH.answer = things.join(',');
	 jsonArr.push(jsonTH);
	 
	 var jsonLOC= new Object();
	 jsonLOC.questionSlNo = $("input[name*='located']").data('serial');
	 jsonLOC.answer =  $("input[name*='located']:checked").val();
	 jsonArr.push(jsonLOC);
	 
	 var finalArray = []; 
		
	 $.each(jsonArr, function(key, value)
		{
			if(value.answer != '' && value.answer != undefined)
			{
				finalArray.push(value);
			}
		}); 
		 
		$('#studentTUMList').val(JSON.stringify(finalArray));
}

function isValid()
{
	$('.error-block').html('');
	if($('#occupation').val() == '' || $('#occupation').val() == undefined)
	{
		$('#occupation').parent().find('.error-block').html('This field required.');
		return false;
	}
	
	if(($('#things_1').val() == '' && $('#things_2').val() == '') && $('#things_3').val() == '')
	{
		$('#things_1').parent().parent().find('.error-block').html('This field required.');
		return false;
	}
	
	if($("input[name*='located']:checked").val() == '' || $("input[name*='located']:checked").val() == undefined)
	{
		$("input[name*='located']").parent().parent().find('.error-block').html('This field required.');
		return false;
	}
	return true;
}
function fnINITFormData()
{
	 var jsonData = '${utils:replaceJSONEntities(studentTUMArraylist)}';
	 if(jsonData.length > 0)
	 {
	   var jsonArray = $.parseJSON(jsonData);
	   
	   $.each(jsonArray, function(key, item)
	   {
		   if(item.questionSlNo == 13)
		   {
			   $("input[name*='located']").each(function(key, value)
			   {
					if(item.answer == value.value)	
					{
						$(this).prop("checked", true);
					}	
			   });
		   }
		   
		   if(item.questionSlNo == 12)
		   {
			   var data = item.answer.split(',');
			   for(var a= 0; a< data.length; a++ )
			   {
				   if(data[a] !='')
				   {
					  $('#things_'+(a+1)).val(fnReplaceBackXMLEntities(data[a]));
				   }	   
			   }	   
		   }
		   if(item.questionSlNo == 11)
		   {
			  $('#occupation').val(fnReplaceBackXMLEntities(item.answer));
		   }
	   });
	 }	 
}

</script>

