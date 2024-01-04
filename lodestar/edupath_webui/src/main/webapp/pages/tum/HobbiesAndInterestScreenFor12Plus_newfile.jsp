<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="rows">
  <div class="col-md-12">
    <div class="col-md-9">
     <h3><s:text name="com.edupath.student.tum.hobbies"></s:text></h3> 
     <p class="auto_txt"><s:text name="com.edupath.student.tum.autosave"></s:text></p>
    </div>
    <div class="col-md-3 hidden-xs">
    <div class="do-know">
     <%--  <i class="fa fa-bell"></i><span class="do-know-spn"><s:text name="com.edupath.student.tum.know"></s:text></span> --%>
      <p class="do-know-txt"></p>
    </div>
    </div>
  </div>
  <div class="col-md-10  col-xs-12">
   <div class="col-md-12  col-xs-12">
    <div class="div_lable qusetion_txt col-xs-12">
     <div class="question_left_div">5.&nbsp;&nbsp;</div>
       <div class="question_right_div">
          <p class="qusetion_txt">What are your strengths as a person?</p>
       </div>
    </div>
    <div class="txt-field strength_div  col-xs-12">
    <c:set var="strengths" value="${fn:split('Logic and Reasoning,Imaginative and Innovative,Quick Learner,Focus and Attention,Creativity,Numerical Ability,Repairing/Assembling,Decision Making,Organised/Systematic,Problem Solving,Independence (Self Drive),Money Management,Punctuality,Patience,Helping Nature,Persuasion,Confidence,People Orientation,Teamwork,Leadership', ',') }"></c:set>
    <fmt:parseNumber var="i" integerOnly="true" type="number" value="${fn:length(strengths) / 3}" />
       <c:set var="v" value="${i}"></c:set>
       <c:set var="start" value="0"></c:set>
       <c:set var="end" value="2"></c:set>
    <c:forEach begin="0" end="${v}" var="count">
     <div class="rows">
     
       <c:forEach begin="${start}" end="${end}" var="item" varStatus="count">
         <c:if test="${item lt fn:length(strengths) }">
          <div class="col-md-4 col-xs-12">
            <div class="checkbox checkbox-primary">
            <!-- Start Sasedeve edited by Mrutyunjaya on Date 08-09-2017 -->
               <input id="checkbox_8_${count.index}" name="strength" data-serial="8" data-itemid="${count.index}" value="${strengths[item]}" class="auto_save styled abcclick" type="checkbox">
                <!-- END Sasedeve edited by Mrutyunjaya on Date 08-09-2017 -->
               <label for="checkbox_8_${count.index}" class="qusetion_txt_label">${fn:trim(strengths[item])}</label>
             </div>  
          </div>
         </c:if>
          <c:set var="start" value="${item + 1}"></c:set>
       </c:forEach>
        <c:set var="end" value="${end + 3}"></c:set>
       </div> 
    </c:forEach>
      <div>
        <span class="error-block"></span>
      </div>
    </div>
   </div>
  </div>
  
  <div class="col-md-12">
   <div class="col-md-12">
    <div class="div_lable qusetion_txt">
     <div class="question_left_div">6.&nbsp;&nbsp;</div>
      <div class="question_right_div">
         <p class="qusetion_txt">What kind of activities are you good at and like doing? What do you do in your free time - what are you hobbies? </p>
       </div>
    </div>
    <div class="txt-field" style="padding-top:20px">
      <div class="row">
       <div class="col-md-10">
        <s:textarea name="activities" id="activities" cssClass="input-textarea auto_save" data-serial="9" cols="10" placeholder="e.g. Would like to be a painter"  title=" e.g. Would like to be a painter" data-toggle="tooltip" data-placement="right" maxlength="500"></s:textarea>
         <span class="error-block"></span>
       </div>
       <div class="col-md-2">
        <%--   <div class="leftarrowdiv">
             <s:text name="com.edupath.student.tum.tooltip"></s:text>
          </div> --%>
       </div>
      </div>
    </div>
   </div>
  </div>
  
  <div class="col-md-12 col-xs-12">
   <div class="col-md-12 col-xs-12">
    <div class="div_lable qusetion_txt col-xs-12">
     <div class="question_left_div">7.&nbsp;&nbsp;</div>
      <div class="question_right_div " style="padding-left:16px">
          <p class="qusetion_txt">What subjects or area of education <b>interests</b> you most?</p>
       </div>
    </div>
    <div class="txt-field strength_div col-xs-12" style="padding-left:35px">
       <c:set var="subjectOrArea" value="${fn:split('Mathematics,Physics,Chemistry,Biology,History,Civics,Geography,English,Other Languages,Art,Physical and Health Edu,Economics,Business/Commercial Studies,Technical Drawing,Computer Science/Applications,Environmental Science,Agricultural Science', ',')}"></c:set>
        <fmt:parseNumber var="i" integerOnly="true" type="number" value="${fn:length(subjectOrArea) / 3}" />
       <c:set var="v" value="${i}"></c:set>
       <c:set var="start" value="0"></c:set>
       <c:set var="end" value="2"></c:set>
    <c:forEach begin="0" end="${v}" var="count">
     <div class="rows">
     
       <c:forEach begin="${start}" end="${end}" var="item" varStatus="count">
         <c:if test="${item lt fn:length(subjectOrArea) }">
          <div class="col-md-4 col-xs-12 ">
            <div class="checkbox checkbox-primary col-xs-12">
            	<c:choose>
            		<c:when test="${subjectOrArea[item] eq 'Mathematics'}">
            			<input id="checkbox_10_${count.index}" name="subjectOrArea" data-serial="10" value="Math" class="auto_save styled" type="checkbox">
               			<label for="checkbox_10_${count.index}" class="qusetion_txt_label">${fn:trim(subjectOrArea[item])}</label>
            		</c:when>
            		<c:otherwise>
            			<input id="checkbox_10_${count.index}" name="subjectOrArea" data-serial="10" value="${subjectOrArea[item]}" class="auto_save styled" type="checkbox">
               			<label for="checkbox_10_${count.index}" class="qusetion_txt_label">${fn:trim(subjectOrArea[item])}</label>
            		</c:otherwise>
            	</c:choose>
             </div>  
          </div>
         </c:if>
          <c:set var="start" value="${item + 1}"></c:set>
       </c:forEach>
        <c:set var="end" value="${end + 3}"></c:set>
       </div> 
    </c:forEach>
       <%--  <s:checkboxlist name="subjectOrArea" cssClass="auto_save" data-serial="10" list="{'Mathematics', 'Physics', 'Chemistry', 'Biology', 'History', 'Civics', 'Geography',  'English', 'Other Languages', 'Art (Drama/Visual/Music)', 'Physical and Health Edu', 'Economics', 'Business/Commercial Studies', 'Technical Drawing', 'Computer Science/Applications', 'Environmental Science', 'Agricultural Science'}"></s:checkboxlist> --%>
       <div>
         <span class="error-block"></span>
      </div>
    </div>
   </div>
  </div>
  
  <div class="col-md-1 col-xs-3 col-md-offset-1">
  	  <button class="btn btn_action btn-back" onclick="fnBackSubmit('HobbiesAndInterestAction', '1', 'PersonalAndAcademicInfoAction');"><span class="back-spn"><s:text name="com.edupath.common.label.back"></s:text></span></button>
   </div> 
   <div class="col-md-6 col-xs-6 ">
  	  <button class="btn btn_action" onclick="fnStudentTUMFormSubmit('HobbiesAndInterestAction','CareerinterestAction');" style="margin-left: 90px;"><span class="next-spn"><s:text name="com.edupath.common.next"></s:text></span></button>
   </div> 
</div>

  <!-- Start Sasedeve edited by Mrutyunjaya on Date 08-09-2017 -->


<!-- Strength 0 -->

<div class="modal fade" role="dialog" id="0">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You enjoy solving or understanding the logic / reason behind things. For example: you like solving puzzles; you solve sudoko everyday; participate in debating about solving issues. 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>



<!-- Strength 1 -->

<div class="modal fade" role="dialog" id="1">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You enjoy doing activities that involve imagination and creativity. For example: writing poems / stories; creating / drawing new innovative designs. You have won a few prizes at school or inter-school competitions in this category. You are usually known to be that person who comes up with new ideas for a birthday / party theme - you have seen that most often your ideas are appreciated for their creativity and also for being innovative.
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>



<!-- Strength 2 -->

<div class="modal fade" role="dialog" id="2">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You are good at learning new things. For example: You enjoy playing games that require you to learn rules that are different from the games you have played before; you quickly learn new concepts when you have to. You are among the first ones in the class who can grasp what the teacher is trying to explain. 
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>



<!-- Strength 3 -->

<div class="modal fade" role="dialog" id="3">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You can be focused and hold your attention for long. For example: You are able to read for long hours. You also like to read to know more about things - science, technology, history, inventions, discoveries, famous personalities etc.
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 4 -->

<div class="modal fade" role="dialog" id="4">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        		You enjoy doing activities that involve imagination and creativity. For example: writing poems / stories; creating / drawing new innovative designs. You have won a few prizes at school or inter-school competitions in this category. You are usually known to be that person who comes up with new ideas for a birthday / party theme - you have seen that most often your ideas are appreciated for their creativity and also for being innovative.
	        		 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 5 -->

<div class="modal fade" role="dialog" id="5">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You enjoy playing with numbers and like doing  activities that involve them. For example: keep track of your favourite sportsperson's  scores; participate in math olympiad; achieved at least 5th level in abacus.
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 6 -->

<div class="modal fade" role="dialog" id="6">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        		You enjoy working on gadgets / objects for the purpose of repairing or re-organising / re-modeling it. For example: You usually like to open up gadgets such as clocks, phones, watches or any household appliance to understand the mechanics or the workings of it.
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 7 -->

<div class="modal fade" role="dialog" id="7">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			 You are comfortable making decisions. For example: You decide which subject to study on a given day; you decide to go a function / party or not. You decide whom to stay friends with; You make your own decision about the clothes you wear on a daily basis; food to be eaten, participation in sports or events etc., and you are often right about your choices.
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 8 -->

<div class="modal fade" role="dialog" id="8">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You are very orderly, systematic and organised. For example: You always keep your things - books, clothes, gadgets, money etc., in an orderly fashion. You prepare for exams throughout the year and not leave much till the end. 
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 9 -->

<div class="modal fade" role="dialog" id="9">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You enjoy solving or understanding the logic / reason behind things. For example: you like solving puzzles; you solve sudoko everyday; participate in debating about solving issues. 
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 10 -->

<div class="modal fade" role="dialog" id="10">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You are very independent by nature. For example: You do not rely much on others to make decisions or for handling issues you may face at school or with your friends or siblings. You can take care of your basic needs such as cooking quick bites / snacks for yourself; run errands for your parents; shop for inexpensive things.
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 11 -->

<div class="modal fade" role="dialog" id="11">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You understand money management well. For example: You save some from the money your parents give you as allowance and have bought many things. You are interested in understanding investments, saving etc.
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 12 -->

<div class="modal fade" role="dialog" id="12">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You keep up with time well. For example: You are always on time - whether it is for a party, to school or a game.
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 13 -->

<div class="modal fade" role="dialog" id="13">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        		You are very patient by nature. For example: You do not do anything impulsively. You can wait patiently for the results on a competition you've participated in, you can listen to people who are complaining; you can wait and think before you act on anything.
	        		 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 14 -->

<div class="modal fade" role="dialog" id="14">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        		You are helpful by nature. For example: You like to help people and you do it even before they express their need. You like helping people at home with chores; you like helping classmates with project work etc.
	        		 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 15 -->

<div class="modal fade" role="dialog" id="15">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You are good at persuading others. For example: You can convince your friends to take up a challenge or to agree with your choice of gift for a friend on her / his birthday; You can convince people to agree with your viewpoint. 
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 16 -->

<div class="modal fade" role="dialog" id="16">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You are a confident person. For example: You are comfortable being yourself. You can talk about any topic with out any hesitation. A crowd of people do not make you uncomfortable. 
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>


<!-- Strength 17 -->

<div class="modal fade" role="dialog" id="17">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You are a people person. For example: You enjoy interacting with people, discussing with people. You would love to meet and spend time with others more than you would want to be alone.
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>



<!-- Strength 18 -->

<div class="modal fade" role="dialog" id="18">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			You are a good team player. For example: You can share work and responsibilities. You enjoy doing group activities.You always participate in group competitions like collage making,group quizzes etc.,  rather than individual competitions like essay writing.
	        			
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>

<!-- Strength 19 -->

<div class="modal fade" role="dialog" id="19">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">About Strength</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        		You believe that you have leadership skills. For example: For group projects at school, you usually take on the role of a leader. Because of this, you are usually selected as a lead by your teacher or your classmates.
	        		
	        			
	        			 
	        		</p>
	        	
	        		
	       	</div>
       		<div class="modal-footer">
         		
       		</div>
	     </div>
	</div>
</div>

  <!-- END Sasedeve edited by Mrutyunjaya on Date 08-09-2017 -->






<s:form name="StudentTUMForm" id="StudentTUMForm" method="post">
   <s:hidden name="studentTUMList" id="studentTUMList"></s:hidden>
   <s:hidden name="serialNum" id="serialNum" value="13"></s:hidden>
   <s:hidden name="pageAction" id="pageAction" value="3"></s:hidden>
   <s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
   <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
   <s:hidden name="actionNameValue" id="actionNameValue"></s:hidden>
   <s:hidden name="deleteQusestionList" id="deleteQusestionList"></s:hidden>
</s:form>

<script>
//Start Sasedeve Edited By Mrutyunjaya on Date 08-09-2017
$('.abcclick').click(function(){

	 if($(this).is(':checked'))
	 {
		 $('#'+$(this).data("itemid")).modal("show");
	 }



});


//END Sasedeve Edited By Mrutyunjaya on Date 08-09-2017

$(document).ready(function(){
   fnINITFormData();
   var hobbies = '${HOBBIES}';
   if(hobbies.length > 0)
   {
	   var newData = $.parseJSON(hobbies);
	   var finalArray = [];
	   $.each(newData.sort(), function(key, value){
		   var data = value.split('/');
		   $.each(data, function(key, value){
			   finalArray.push(value.trim());
		   });
	   });
	   $('#activities').textcomplete([{
		    match: /(^|\s)(\w{1,})$/,
		    search: function (term, callback) {
		        callback($.map(finalArray, function (arg) 
		        {
		        	var re = new RegExp(term, 'gi');
		            return arg.match(re) != null ? arg : null;
		        }));
		    },
		    replace: function (finalArray) {
		        return finalArray + ' ';
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
</script>
<script type="text/javascript">
function fnSetFormData()
{
	 var jsonArr = [];
	 var jsonAct = new Object();
	 jsonAct.questionSlNo = $('#activities').data('serial');
	 jsonAct.answer = $('#activities').val();
	 jsonArr.push(jsonAct);
	 
	 var jsonSTRN= new Object();
	 jsonSTRN.questionSlNo = $("input[name*='strength']").data('serial');
	 
	 var strength = [];
	 $( "input[name*='strength']:checked" ).each(function(key, item){
		  
		 strength.push(item.value);
	 });
	 jsonSTRN.answer = strength.join(',');
	 jsonArr.push(jsonSTRN);
	 
	 var jsonSA= new Object();
	 jsonSA.questionSlNo = $("input[name*='subjectOrArea']").data('serial');
	 
	 var subjectOrArea = [];
	 $( "input[name*='subjectOrArea']:checked" ).each(function(key, item){
		  
		 subjectOrArea.push(item.value);
	 });
	 jsonSA.answer = subjectOrArea.join(',');
	 jsonArr.push(jsonSA);
	 
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
	if($( "input[name*='strength']:checked" ).val() == '' || $( "input[name*='strength']:checked" ).val() == undefined)
	{
		$("input[name*='strength']").parent().find('.error-block').html('This field required.');
	    return false;
	}
	
	if($( "#activities" ).val() == '' || $( "#activities" ).val() == undefined)
	{
	   $("#activities").parent().find('.error-block').html('This field required.');
	   return false;
	}
	
	if($("input[name*='subjectOrArea']:checked").val() == '' || $("input[name*='subjectOrArea']:checked").val() == undefined)
	{
		$("input[name*='subjectOrArea']").parent().find('.error-block').html('This field required.');
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
		   if(item.questionSlNo == 8)
		   {
			   var data = item.answer.split(',');
			   for(var a = 0; a< data.length; a++)
			   {
				   $("input[name*='strength']").each(function(key, value)
				   {
					  if(data[a] == value.value)
					  {
						  $(this).prop("checked", true);
					  }	  
				   });
			   }	   
		   }
		   
		   if(item.questionSlNo == 9)
		   {
			   $( "#activities" ).val(fnReplaceBackXMLEntities(item.answer));
		   }
		   
		   if(item.questionSlNo == 10)
		   {
			   var data = item.answer.split(',');
			   for(var a = 0; a< data.length; a++)
			   {
				   $("input[name*='subjectOrArea']").each(function(key, value)
				   {
					  if(data[a] == value.value)
					  {
						  $(this).prop("checked", true);
					  }	  
				   });
			   }	
		   }
	   });
	 }	 
}
</script>

