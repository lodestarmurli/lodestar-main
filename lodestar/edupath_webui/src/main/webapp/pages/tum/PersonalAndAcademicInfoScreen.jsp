<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="rows">
  <div class="col-md-12">
    <div class="col-md-8">
     <h3><s:text name="com.edupath.student.tum.personal"></s:text></h3> 
     <p class="auto_txt"><s:text name="com.edupath.student.tum.autosave"></s:text></p>
    </div>
    <div class="col-md-4">
    <div class="do-know">
     <%--  <i class="fa fa-bell"></i><span class="do-know-spn"><s:text name="com.edupath.student.tum.know"></s:text></span> --%>
      <p class="do-know-txt"></p>
    </div>
    </div>
  </div>
  <div class="col-md-12">
   <div class="col-md-8">
    <div class="div_lable qusetion_txt qusetion_txt">
     <div class="question_left_div">1. &nbsp;&nbsp;</div>
       <div class="question_right_div">
          <p class="qusetion_txt">Father's profession</p>
        </div>
    </div>
    <div class="txt-field">
    <s:textfield name="father" id="father" cssClass="input-md auto_save" data-serial="1" maxlength="500"></s:textfield>
    <span class="error-block"></span>
    </div>
   </div>
  </div>
  
  <div class="col-md-12">
   <div class="col-md-8">
    <div class="div_lable qusetion_txt">
    <div class="question_left_div">2. &nbsp;&nbsp;</div>
     <div class="question_right_div">
        <p class="qusetion_txt">Mother's profession </p>
     </div>   
    </div>
    <div class="txt-field">
    <s:textfield name="mother" id="mother" cssClass="input-md auto_save" data-serial="2" maxlength="500"></s:textfield>
    <span class="error-block"></span>
    </div>
   </div>
  </div>
  
  <div class="col-md-12">
   <div class="col-md-8">
    <div class="div_lable qusetion_txt">
    <div class="question_left_div">3. &nbsp;&nbsp;</div>
     <div class="question_right_div">
       <p class="qusetion_txt">Your current <b>school board:</b></p>
      </div>
    </div>
    <div class="txt-field">
    <c:set var="currentBoard" value="${fn:split('CBSE,ICSE,IB,IGCSE,State Board', ',')}"></c:set>
       <c:forEach items="${currentBoard}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="currentBoard_${count.index}" value="${item}" data-serial="3"  name="currentBoard" class="auto_save" onclick="fnSetSubject(this)">
             <label for="currentBoard_${count.index}" class="qusetion_txt_label">${item}</label>
           </div>
       </c:forEach>
      <%--  <s:radio label="Current board" cssClass="board currentBoard auto_save" name="currentBoard" id="currentBoard" list="{'CBSE', 'ICSE', 'IB', 'IGCSE', 'State Board'}" onclick="fnSetSubject(this)" data-serial="3"/><!-- 'NIOS' --> --%>
       <div>
         <span class="error-block"></span>
       </div>
    </div>
   </div>
  </div>
  
   <div class="col-md-12">
   <div class="col-md-8">
    <div class="div_lable qusetion_txt">
    <div class="question_left_div">4. &nbsp;&nbsp;</div>
     <div class="question_right_div">
       <p class="qusetion_txt  Question_no_4">Which <b>School board</b> are you considering in Class 11?</p>
     </div>
    </div>
    <div class="txt-field">
     <c:set var="schoolBoard" value="${fn:split('CBSE,ICSE,IB,IGCSE,State Board,Not decided', ',')}"></c:set>
       <c:forEach items="${schoolBoard}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="schoolBoard_${count.index}" value="${item}" data-serial="4"  name="schoolBoard" class="schoolBoard auto_save">
             <label for="schoolBoard_${count.index}" class="qusetion_txt_label">${item}</label>
           </div>
       </c:forEach>
      <%--  <s:radio label="Current board" cssClass="board current schoolBoard auto_save" name="schoolBoard" list="{'CBSE', 'ICSE', 'IB', 'IGCSE', 'State Board', 'Not decided'}" data-serial="4"/><!--'NIOS' --> --%>
       <div>
        <span class="error-block"></span>
       </div>
    </div>
   </div>
  </div>
  
   <div class="col-md-12">
    <div class="col-md-8">
     <div class="div_lable qusetion_txt">
     <div class="question_left_div">5. &nbsp;&nbsp;</div>
      <div class="question_right_div">
        <p class="qusetion_txt  Question_no_5">Which <b>Stream</b> would you like to choose in Class 11?</p>
       </div> 
    </div>
    <div class="txt-field">
      <c:set var="stream" value="${fn:split('Science,Commerce,Arts,Not decided,Any other stream', ',')}"></c:set>
       <c:forEach items="${stream}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="stream_${count.index}" value="${item}" data-serial="5"  name="stream" class="auto_save">
             <label for="stream_${count.index}" class="qusetion_txt_label">${item}</label>
           </div>
       </c:forEach>
       <%-- <s:radio label="Current board" cssClass="board stream auto_save" name="stream" id="stream" list="{'Science', 'Commerce', 'Arts', 'Not decided', 'Any other stream'}" data-serial="5"/> --%>
       <div>
       <span class="error-block"></span>
       </div>
    </div>
   </div>
  </div>
  
  <div class="col-md-12">
    <div class="col-md-9">
     <div class="div_lable qusetion_txt">
     <div class="question_left_div">6. &nbsp;&nbsp;</div>
      <div class="question_right_div">
         <p class="qusetion_txt  Question_no_6">What <b>Subject</b> combination are you considering in Class 11?</p>
       </div> 
    </div>
    <div class="txt-field">
       <s:textfield name="subject" id="subject" cssClass="input-lrg auto_save" data-serial="6" maxlength="500"></s:textfield>
       <span class="error-block"></span>
    </div>
   </div>
  </div>
  
  <div class="col-md-12">
    <div class="col-md-11">
     <div class="div_lable qusetion_txt">
     <div class="question_left_div">7. &nbsp;&nbsp;</div>
      <div class="question_right_div">
         <p class="qusetion_txt"><b>Academic Record:</b> Enter total % OR Grade obtained in the last appeared final exam</p>
      </div>
    </div>
    <div class="txt-field">
    <div class="row">
      <div class="col-md-6 marks_lable" style="padding-bottom: 15px;">
       <div class="radio radio-success radio-inline">
             <input type="radio" id="percentages_1" value="percentage" data-serial="7"  name="mark" class="marks_input" checked="checked"/>
             <label for="percentages_1">Percentage</label>
        </div>
         <div class="radio radio-success radio-inline">
             <input type="radio" id="grades_1" value="grade" data-serial="7"  name="mark" class="marks_input" />
             <label for="grades_1" class="qusetion_txt_label">Grade</label>
        </div>
       <%--  <s:radio name="marks" list="#{'percentage':'Percentage','grade':'Grade'}" value="'percentage'" data-serial="7" cssClass="auto_save marks_input"></s:radio> --%>
      </div>
     </div> 
       <div class="rows">
        <span class="error-block" id="subject_error"></span>
      							
         <div class="col-md-1 sr-number subject_div" style="overflow-y:auto;">
           <span>Sr. No</span>
         </div>
         <div class="col-md-4 subject_div">
           <span>Subjects Studied in Class 9th & 10th</span>
         </div>
         <div class="col-md-4  subject_div">
           <span class="mark_title">Percentage obtained in 9th Standard</span>
         </div>
         <div class="col-md-3">
         </div>
       </div>
    </div>
   </div>
  </div>
  
  <s:iterator begin="1" end="9" status="rowstatus" var="count">
   <div class="col-md-12" style="padding-left: 40px;">
    <div class="col-md-11">
     <div class="rows">
           <div class="col-md-1 row_div sr-number row_${rowstatus.odd} hidden-xs">
             ${count} 
           </div>
           <div class="col-md-4 row_div row_${rowstatus.odd}">
             <div class="selectdiv">
               <s:select list="{'-Select-'}"  name="subjectName" id="subjectName_%{count}" data-count="%{count}" cssClass="input-selectbox  selectboxdiv auto_save"></s:select>
               <div class="out"></div>
             </div>
           </div>
           <div class="col-md-4 row_div  row_${rowstatus.odd}" style="text-align: center;">
               <s:textfield name="grade" cssClass=" grade_input change_input " id="grade_%{count}"  data-index='%{count}' placeholder="Enter %" onblur="fnTextSave();"></s:textfield>
           </div>
       </div>
    </div>
   </div>
  </s:iterator>
  
  <div class="col-md-12 action-div">
      <button class="btn btn_action" onclick="fnStudentTUMFormSubmit('PersonalAndAcademicInfoAction', 'HobbiesAndInterestAction');"><span class="next-spn"><s:text name="com.edupath.common.next"></s:text></span></button>
   </div> 
</div>
<s:form name="StudentTUMForm" id="StudentTUMForm" method="post">
   <s:hidden name="studentTUMList" id="studentTUMList"></s:hidden>
   <s:hidden name="serialNum" id="serialNum" value="10"></s:hidden>
   <s:hidden name="pageAction" id="pageAction" value="2"></s:hidden>
   <s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
   <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
   <s:hidden name="actionNameValue" id="actionNameValue"></s:hidden>
   <s:hidden name="deleteQusestionList" id="deleteQusestionList"></s:hidden>
</s:form>



<script>
$(document).ready(function(){
	fnSetMarks("percentage");
	fnINITFormData();
	$('.marks_input').click(function()
	{
		fnSetMarks($(this).val());
	});
	
	/*  $('.input-selectbox').select2({
        /*  placeholder: "Select an option",
         allowClear: true, */
        // theme: "classic",
        // allowClear: true
    // });
	 
	 $(".input-selectbox").change(function () {
	        var str = "";
	        str = $(this).find(":selected").text();
	        $(this).next(".out").text(str);
	    }).trigger('change');
	
	 $('.input-selectbox').change(function ()
     {
		 var index = $(this).data("count");
		 if(index != '' && index != undefined)
		 {
			 $('#grade_'+ index).val('');
		 }	
	 });
	 
	 
	 if('${session.UserSessionObject.classId}' == 3 || '${session.UserSessionObject.classId}' == 4)
		{
			$('.Question_no_4').html('');
			$('.Question_no_4').html('What was your Class 10 school board?');
			$('.Question_no_5').html('');
			$('.Question_no_5').html('What <b>stream</b> have you chosen in Class 11?');
			$('.Question_no_6').html('');
			$('.Question_no_6').html('What <b>subject</b> combination have you chosen in Class 11?');
		}
	
});

function fnSetMarks(value)
{
	if(value == 'percentage')
	{
		$('.mark_title').html('');
		$('.mark_title').html('Percentage obtained in 9th Standard');
		if('${session.UserSessionObject.classId}' == 3 || '${session.UserSessionObject.classId}' == 4)
		{
			$('.mark_title').html('');
			$('.mark_title').html('Percentage obtained in 10th Standard');
		}
		$('.change_input').attr('placeholder', 'Enter %');
		$('.change_input').val('');
		$('.change_input').removeClass('mark_txt');
		$('.change_input').addClass('grade_input');
		fnINITSelectBox($( "input[name*='currentBoard']:checked" ).val());
		
		 $( ".change_input" ).unbind();
		 $(".grade_input").keypress(function (event) {
			 var charCode = (event.which) ? event.which : event.keyCode;
			 if(charCode == 10 || charCode == 8)
			 {
				  return true;
			 } 
		      return isNumber(event, this);
		 });
		 
	     $(".grade_input").keyup(function(){
		   if ($(this).val().length > 5) {
		        $(this).val($(this).val().substring(0, 5));
		    }
         });
	   
	     $(".grade_input").keydown(function(){
		   if ($(this).val().length > 5) {
		        $(this).val($(this).val().substring(0, 5));
		   }
          });
	}
	else
	{
		$('.change_input').addClass('mark_txt');
		$('.change_input').removeClass('grade_input');
		$('.mark_title').html('');
		$('.mark_title').html('Grade obtained in 9th Standard');
		if('${session.UserSessionObject.classId}' == 3 || '${session.UserSessionObject.classId}' == 4)
		{
			$('.mark_title').html('');
			$('.mark_title').html('Grade obtained in 10th Standard');
		}
		$('.change_input').attr('placeholder', 'Grade');
		$('.change_input').val('');
		fnINITSelectBox($( "input[name*='currentBoard']:checked" ).val());
		$( ".change_input" ).unbind();
	 	$(".mark_txt").keypress(function (event) {
		      
		     // return onlyAlphabets(event, this);
		   });
	      $(".mark_txt").keyup(function(){
	    	  var str =$(this).val().charAt(0);
	    	  if($.isNumeric(str))
	    	  {
	    		   $(this).val('');
	    	  }	
		      if ($(this).val().length > 3) 
		      {
		         $(this).val($(this).val().substring(0, 3));
		      }
          });
	   
	      $(".mark_txt").keydown(function(){
		      if ($(this).val().length > 3) 
		      {
		           $(this).val($(this).val().substring(0, 3));
		      }
           }); 
	}	
}
 var form = document.PersonalForm;
 var subjectList = new Object();
 var CBSE = ['Mathematics', 'Science ', 'Social Science ', 'Hindi', 'English', 'Kannada', 'Art', 'Physical and Health Education','Telugu'];
 
 var ICSE = ['Mathematics', 'Science ', 'Social Science ', 'English', 'Second Language', 'Economics', 'Commercial Studies', 'Technical Drawing', 'Computer Science ', 'Environmental Science ', 'Agricultural Science ', 'Computer Applications', 'Economic Applications', 'Commercial Applications'];

 var STATEBOARD = ['Mathematics', 'Science ', 'Social Science ', 'English', 'Kannada', 'Hindi', 'Sanskrit','Telugu'];

 var IB	= ['Mathematics', 'Science ', 'Social Science ', 'English', 'Spanish', 'French', 'Physical Education', 'Design', 'Arts-Visual', 'Arts-Media', 'Arts-Drama', 'Arts-Music'];

 var IGCSE = ['Mathematics', 'Physics', 'Chemistry', 'Biology', 'English', 'Hindi', 'French', 'Mandarian', 'Spanish', 'Economics', 'History', 'Business Studies', 'Computers', 'Arts', 'Drama', 'Music']; 
 
 subjectList.CBSE = CBSE;
 subjectList.ICSE = ICSE;
 subjectList.STATEBOARD = STATEBOARD;
 subjectList.IB = IB;
 subjectList.IGCSE = IGCSE;
 
 function fnSetSubject(obj)
 {
	 var value = $(obj).val();
	 $('.change_input').val('');
	 fnINITSelectBox(value);
 }
 function fnINITSelectBox(value)
 {
	 if(value){
		 var updateStr = value.replace(/\s/g, '');
		 var data = subjectList[updateStr.toUpperCase()];
		 $('.input-selectbox').html('');
		 var option = "<option value='0'>-Select-</option>";
		 $('.input-selectbox').append(option);
		 $.each(data.sort(), function(key, item)
		 {

			 if(item == 'Mathematics')
			 {
				$('.input-selectbox').append("<option value='Maths'>"+item+"</option>");
			 }
			 else
			 {
				 $('.input-selectbox').append("<option value='"+item+"'>"+item+"</option>");
			 }	 

		 });
		 $(".out").html('');
		 $('.out').html('-Select-');
	 }
 }
 
 function fnSetFormData()
 {
	 var jsonArr = [];
	 var jsonF = new Object();
	 jsonF.questionSlNo = $('#father').data('serial');
	 jsonF.answer = $('#father').val();
	 jsonArr.push(jsonF);
	 
	 var jsonM = new Object();
	 jsonM.questionSlNo = $('#mother').data('serial');
	 jsonM.answer = $('#mother').val();
	 jsonArr.push(jsonM);
	 
	 var jsonSub = new Object();
	 jsonSub.questionSlNo = $('#subject').data('serial');
	 jsonSub.answer = $('#subject').val();
	 jsonArr.push(jsonSub);
	 
	 var jsonCB = new Object();
	 jsonCB.questionSlNo = $("input[name*='currentBoard']").data('serial');
	 jsonCB.answer = $( "input[name*='currentBoard']:checked" ).val();
	 jsonArr.push(jsonCB);
	 
	 var jsonSB = new Object();
	 jsonSB.questionSlNo = $("input[name*='schoolBoard']").data('serial');
	 jsonSB.answer = $( "input[name*='schoolBoard']:checked" ).val();
	 jsonArr.push(jsonSB);
	 
	 var jsonSTREAM = new Object();
	 jsonSTREAM.questionSlNo = $("input[name*='stream']").data('serial');
	 jsonSTREAM.answer = $( "input[name*='stream']:checked" ).val();
	 jsonArr.push(jsonSTREAM);
	 
	 var jsonMark = new Object();
	 jsonMark.questionSlNo = $("input[name*='mark']").data('serial');
	 var mark = $( "input[name*='mark']:checked" ).val();
	 var storeValue = [];
	 var gradeOrPer = '';
	 for(var count = 1; count < 10; count++)
	 {
		 var subName = $('#subjectName_'+count+ ' option:selected').val();
		 if(subName != '0' && subName != '-Select-')
		 {
		   gradeOrPer = $('#grade_'+count).val();
		   if(gradeOrPer != '' &&  gradeOrPer != undefined )
	       {
			   storeValue.push(subName + ":" + gradeOrPer);
	       }	
		 }	 
	 }
	 if(storeValue.length > 0)
	 {
		 var answerStr = mark + "," + storeValue.join(",");
		 jsonMark.answer = answerStr; 
	 }	 
	 jsonArr.push(jsonMark);
	 
	var finalArray = []; 
	
    
    $.each(jsonArr, function(key, value)
    {
		if((value.answer != '' && value.answer != undefined))
		{
		    finalArray.push(value);
		}	
    });
	
	$('#studentTUMList').val(JSON.stringify(finalArray));
 }
 
 function isValid()
 {
	 $('.error-block').html('');
	 if($('#father').val() == '' || $('#father').val() == undefined)
	 {
		 $("#father").parent().find('.error-block').html('This field required.');
		 return false;
	 }	
	 
	 if($('#mother').val() == '' || $('#mother').val() == undefined)
	 {
		 $("#mother").parent().find('.error-block').html('This field required.');
		 return false;
	 }	
	 
	 if( $( "input[name*='currentBoard']:checked" ).val() == '' ||  $( "input[name*='currentBoard']:checked" ).val() == undefined)
	 {
		 $("input[name*='currentBoard']").parent().find('.error-block').html('This field required.');
		 return false;
	 }	
	 
	 if($( "input[name*='schoolBoard']:checked" ).val() == '' ||  $( "input[name*='schoolBoard']:checked" ).val() == undefined)
	 {
		 $("input[name*='schoolBoard']").parent().find('.error-block').html('This field required.');
		 return false;
	 }	
	 
	 if($( "input[name*='stream']:checked" ).val() == '' ||  $( "input[name*='stream']:checked" ).val() == undefined)
	 {
		 $("input[name*='stream']").parent().find('.error-block').html('This field required.');
		 return false;
	 }	
	 
	 if($('#subject').val() == '' || $('#subject').val() == undefined)
	 {
		 $("#subject").parent().find('.error-block').html('This field required.');
		 return false;
	 }	
	   var isSelect = false;
		 for(var count = 1; count < 10; count++)
		 {
			 var subName = $('#subjectName_'+count+ ' option:selected').val();
			 if(subName == '0' || subName == '-Select-')
			 {
				 isSelect = false; 
			 }	 
		 }
		 
		 for(var count = 1; count < 10; count++)
		 {
			 var subName = $('#subjectName_'+count+ ' option:selected').val();
			 var gradeOrPer = $('#grade_'+count).val();
			 if(subName != '0' && subName != '-Select-')
			 {
				 if(gradeOrPer == '' || gradeOrPer == undefined)
				 {
					 isSelect = false; 
					 break;
				 }
				 isSelect = true; 
				 $('#subject_error').html('');
			 }
		
		 }
	 if(!isSelect)
	 {
		$('#subject_error').html('This field required.');
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
	   	
	   		switch (item.questionSlNo) {
	   	    case 1:
	   	    		 $('#father').val(fnReplaceBackXMLEntities(item.answer));
	   	       		 break;
	   	    case 2:
	   	    		 $('#mother').val(fnReplaceBackXMLEntities(item.answer));
	   	       		 break;
	   	    case 3:
	   	    		 $("input[name*='currentBoard']").each(function(key, value){
	   	    			 if(item.answer == value.value)
	   	    			 {
	   	    				$(this).prop("checked", true);
	   	    				fnINITSelectBox(value.value);
	   	    			 }	 
	   	    		 });
	   	        	 break;
	   	    case 4:
	   	    		 $("input[name*='schoolBoard']").each(function(key, value){
  	    			    	if(item.answer == value.value)
  	    			    	{
  	    			    		$(this).prop("checked", true);
  	    			   		}	 
  	    		     });
	   	        	 break;
	   	    case 5:
	   	    		 $("input[name*='stream']").each(function(key, value){
  	    			 	if(item.answer == value.value)
  	    			 	{
  	    			 		$(this).prop("checked", true);
  	    				}	 
  	    		 	 });
	   	        	 break;
	   	    case 6:
	   	    		 $('#subject').val(fnReplaceBackXMLEntities(item.answer));
	   	        	 break;
	   		 case 7:
	   			        var data = item.answer.split(',');
	   					$("input[name*='mark']").each(function(key, value){
	    			 		if(data[0] == value.value)
	    			 		{
	    			 			$(this).prop("checked", true);
	    			 			fnSetMarks(value.value);
	    					}	 
	    		 		 });
	   					for(var a= 1; a<data.length; a++)
	   					{
	   						var subject = data[a].split(":");
	   						$('#subjectName_'+a + ' option').each(function(key, value)
	   						{
	   							if(subject[0] == value.value)
	   							{
	   							  $(this).prop("selected", true);
	   							  $('#grade_'+a).val(fnReplaceBackXMLEntities(subject[1]));
	   							}	 
	   						});
	   					} 	
	   	                break;
	   	}
	   	   	   
	  	});
	 }
 }
</script>
<style>
.select2-search__field
{
  display: none;
}

.selectdiv {
    width:250px;
    border:1px solid #ccc;
    height:30px;
    background:url(../images/select.png) no-repeat right center;
    border-radius:3px;
    cursor:pointer;
}
.selectdiv:hover, .selectdiv:focus {
    width:250px;
    border:1px solid #ccc;
    height:30px;
    background: url(../images/select.png) no-repeat right;
    border-radius:3px;
    cursor:pointer;
}
.selectdiv div {
    z-index:-1;
    border:0px solid #000;
    height:28px;
    overflow:hidden;
    padding:4px 0px 0px 5px;
    cursor:pointer;
}
.selectboxdiv {
    height: 30px;
    margin-top: 0;
    position: absolute;
    width: 250px;
    z-index: 2;
    -ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
    -khtml-opacity: 0;
    -moz-opacity: 0;
    opacity: 0;
    cursor:pointer;
}
</style>