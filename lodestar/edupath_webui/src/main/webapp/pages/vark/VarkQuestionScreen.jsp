<%@ taglib uri="/struts-tags" prefix="s"%>   

<%@ taglib uri="commonutil.tld" prefix="utils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/ApplicationStyles.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/StudentInfo.css" rel="stylesheet"  type="text/css"/>
		  
<link href="${pageContext.request.contextPath}/styles/StudentTUM.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/Questionnaire.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/select2/select2.css" type="text/css" />
<link href="${pageContext.request.contextPath}/thirdparty/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/thirdparty/font-awesome/css/font-awesome.min.css" rel="stylesheet" rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/thirdparty/datatables/css/components.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/datatables/css/plugins.css" rel="stylesheet" type="text/css"/> 
<link href="${pageContext.request.contextPath}/thirdparty/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/thirdparty/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.textcomplete.css" rel="stylesheet" type="text/css"/> 
<link href="${pageContext.request.contextPath}/thirdparty/nanoScroller/nano.css" rel="stylesheet" type="text/css"/> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/thirdparty/checkbox/build.css"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/thirdparty/selectpicker/css/bootstrap-select.min.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/thirdparty/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/CommonScripts.js" type="text/javascript"></script>
<%-- <script src="${pageContext.request.contextPath}/thirdparty/select2/select2.min.js" type="text/javascript"></script> --%>
<script src="${pageContext.request.contextPath}/thirdparty/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery_form.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/thirdparty/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/thirdparty/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript" ></script>

<script src="${pageContext.request.contextPath}/scripts/components-pickers.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.textcomplete.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/nanoScroller/nanoScrollerJS.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery/js/table-editable.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/nanoScroller/enscroll-0.6.1.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/selectpicker/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/local-store-script.js"></script>

<c:choose>	
	<c:when test="${interestCompleted eq true}">
		<div id="interestCompletedMsgModal">
		
		
		<jsp:include page="/pages/FreeSIATTest/TestCompleted.jsp"></jsp:include>
		
		</div>
		
		
		
	</c:when>
	<c:otherwise>
		<div id="instruction1">
		     
		     <jsp:include page="/pages/vark/Testinstruction.jsp"></jsp:include>
		     
		   </div>
		 
		 <!--Question Start-->
		 <section class=" col-md-12 col-xs-12 col-sm-12">
		 <div class="container" style="margin-top:26px;">
		 
		 <div class="question_main" id="questions_div" style="display: block;">
		  
		 <div class="qusetion_container">
		 
		 <div>
		     <div class="qusetion_txt question_div_1_1">
		      <div class="serial_left_div ">
		         1.&nbsp;
		      </div>
		       <div class="qusetion_right_div">
		           <p class="question_p">Remember when you learned how to play a new computer or board game. You learned best by: </p>
		       </div>
		     </div>
		     <div class="option_div">
		          <div class="checkbox checkbox-primary radio-inline">
		             <input type="checkbox" id="inlineCheck_01_1" value="K" data-count="" data-serial="1" name="option_1" class="auto_save">
		             <label for="inlineCheck_01_1" class="qusetion_txt_label "> watching others do it first.  </label>
		           </div>
		           <br>
		           <div class="checkbox checkbox-primary radio-inline">
		             <input type="checkbox" id="inlineCheck_01_2" value="A" data-count="" data-serial="1" name="option_1" class="auto_save">
		             <label for="inlineCheck_01_2" class="qusetion_txt_label ">listening to somebody explaining it and asking questions.  </label>
		           </div>
		           <br>
		           <div class="checkbox checkbox-primary radio-inline">
		             <input type="checkbox" id="inlineCheck_01_3" value="R" data-count="" data-serial="1" name="option_1" class="auto_save">
		             <label for="inlineCheck_01_3" class="qusetion_txt_label ">reading the instructions.</label>
		           </div>
		           <br>
		           <div class="checkbox checkbox-primary radio-inline">
		             <input type="checkbox" id="inlineCheck_01_4" value="V" data-count="" data-serial="1" name="option_1" class="auto_save">
		             <label for="inlineCheck_01_4" class="qusetion_txt_label ">clues from the diagrams in the instructions.  </label>
		           </div>
		           <br>
		     </div>
       </div>
    
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           2.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">After reading a play you need to do a project. Would you prefer to: </p>
        </div>
      </div>
      <div class="option_div">
          <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_02_1" value="V" data-count="" data-serial="2" name="option_2" class="auto_save">
             <label for="inlineCheck_02_1" class="qusetion_txt_label ">draw or sketch something that happened in the play?  </label>
           </div><br>
           <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_02-2" value="A" data-count="" data-serial="2" name="option_2" class="auto_save">
             <label for="inlineCheck_02-2" class="qusetion_txt_label ">read a speech from the play? </label>
           </div><br>
           <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_02-3" value="R" data-count="" data-serial="2" name="option_2" class="auto_save">
             <label for="inlineCheck_02-3" class="qusetion_txt_label ">write about the play? </label>
           </div><br>
           <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_02-4" value="K" data-count="" data-serial="2" name="option_2" class="auto_save">
             <label for="inlineCheck_02-4" class="qusetion_txt_label ">act out a scene from the play? </label>
           </div><br>
     </div>
     </div>
     
     
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           3.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You have to present your ideas to your class. You would:  </p>
        </div>
      </div>
      <div class="option_div">
          <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_03-1" value="K" data-count="" data-serial="3" name="option_3" class="auto_save">
             <label for="inlineCheck_03-1" class="qusetion_txt_label ">gather examples and stories to make it real and practical. </label>
           </div><br>
            <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_03-2" value="V" data-count="" data-serial="3" name="option_3" class="auto_save">
             <label for="inlineCheck_03-2" class="qusetion_txt_label ">make diagrams or get graphs to help explain my ideas. 	</label>
           </div><br>
            <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_03-3" value="R" data-count="" data-serial="3" name="option_3" class="auto_save">
             <label for="inlineCheck_03-3" class="qusetion_txt_label ">write out my speech and learn it by reading it again and again.  </label>
           </div><br>
            <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_03-4" value="A" data-count="" data-serial="3" name="option_3" class="auto_save">
             <label for="inlineCheck_03-4" class="qusetion_txt_label ">write a few key words and say them again and again. </label>
           </div><br>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           4.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You are about to hook up your parent's new computer. You would:  </p>
        </div>
      </div>
      <div class="option_div">
          <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_04-1" value="K"  data-count="" data-serial="4" name="option_4" class="auto_save">
             <label for="inlineCheck_04-1" class="qusetion_txt_label ">unpack the box and start putting the pieces together.  </label>
           </div><br>
           <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_04-2" value="A"  data-count="" data-serial="4" name="option_4" class="auto_save">
             <label for="inlineCheck_04-2" class="qusetion_txt_label ">phone, text or email a friend and ask how to do it.  </label>
           </div><br>
           <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_04-3" value="V"  data-count="" data-serial="4" name="option_4" class="auto_save">
             <label for="inlineCheck_04-3" class="qusetion_txt_label ">follow the diagrams that show how it is done.  </label>
           </div><br>
           <div class="checkbox checkbox-primary radio-inline">
             <input type="checkbox" id="inlineCheck_04-4" value="R"  data-count="" data-serial="4" name="option_4" class="auto_save">
             <label for="inlineCheck_04-4" class="qusetion_txt_label ">read the instructions that came with it.  </label>
           </div><br>
     </div>
     </div>
     
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           5.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You are about to buy a new digital camera or mobile phone. Other than price, what would most influence your decision?  </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_05-1" value="A" data-count=""  data-serial="5" name="option_5" class="auto_save">
	             <label for="inlineCheck_05-1" class="qusetion_txt_label ">the salesperson telling me about it. </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_05-2" value="V" data-count=""  data-serial="5" name="option_5" class="auto_save">
	             <label for="inlineCheck_05-2" class="qusetion_txt_label ">it is the latest design and looks good.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_05-3" value="K" data-count=""  data-serial="5" name="option_5" class="auto_save">
	             <label for="inlineCheck_05-3" class="qusetion_txt_label ">trying it.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_05-4" value="R" data-count=""  data-serial="5" name="option_5" class="auto_save">
	             <label for="inlineCheck_05-4" class="qusetion_txt_label ">reading the details about its features.  </label>
	           </div><br>
	     </div>
	     </div>
     
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           6.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You want some feedback about an event, competition or test. You would like to have feedback:   </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_06-1" value="K"  data-count="" data-serial="6" name="option_6" class="auto_save">
	             <label for="inlineCheck_06-1" class="qusetion_txt_label ">that used examples of what I have done.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_06-2" value="R"  data-count="" data-serial="6" name="option_6" class="auto_save">
	             <label for="inlineCheck_06-2" class="qusetion_txt_label ">that used a written description or table of my results.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_06-3" value="V"  data-count="" data-serial="6" name="option_6" class="auto_save">
	             <label for="inlineCheck_06-3" class="qusetion_txt_label ">that used graphs showing what I achieved.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_06-4" value="A"  data-count="" data-serial="6" name="option_6" class="auto_save">
	             <label for="inlineCheck_06-4" class="qusetion_txt_label ">from somebody who discussed it with me.  </label>
	           </div><br>
	     </div>
	     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           7.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">A new movie has arrived in town. What would most influence your decision to go (or not go)?   </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_07-1" value="K"  data-count="" data-serial="7" name="option_7" class="auto_save">
	             <label for="inlineCheck_07-1" class="qusetion_txt_label ">it is similar to others you have liked.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_07-2" value="A"  data-count="" data-serial="7" name="option_7" class="auto_save">
	             <label for="inlineCheck_07-2" class="qusetion_txt_label ">hear friends talking about it.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_07-3" value="V"  data-count="" data-serial="7" name="option_7" class="auto_save">
	             <label for="inlineCheck_07-3" class="qusetion_txt_label ">you see a preview of it.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_07-4" value="R"  data-count="" data-serial="7" name="option_7" class="auto_save">
	             <label for="inlineCheck_07-4" class="qusetion_txt_label ">you read what others say about it online or in a magazine. </label>
	           </div><br>
	     </div>
	     </div>
     
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           8.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You want to plan a surprise party for a friend. You would:    </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_08-1" value="R"  data-count="" data-serial="8" name="option_8" class="auto_save">
	             <label for="inlineCheck_08-1" class="qusetion_txt_label ">make lists of what to do and what to buy for the party.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_08-2" value="K"  data-count="" data-serial="8" name="option_8" class="auto_save">
	             <label for="inlineCheck_08-2" class="qusetion_txt_label ">invite friends and just let it happen. </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_08-3" value="A"  data-count="" data-serial="8" name="option_8" class="auto_save">
	             <label for="inlineCheck_08-3" class="qusetion_txt_label ">talk about it on the phone or text others.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_08-4" value="V"  data-count="" data-serial="8" name="option_8" class="auto_save">
	             <label for="inlineCheck_08-4" class="qusetion_txt_label ">draw a map and make a special design for the invitation.  </label>
	           </div><br>
	     </div>
	     </div>
	     
	     
	  <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           9.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You need to give directions to go to a house nearby. You would:    </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_09-1" value="R"  data-count="" data-serial="9" name="option_9" class="auto_save">
	             <label for="inlineCheck_09-1" class="qusetion_txt_label ">write down the directions as a list.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_09-2" value="A"  data-count="" data-serial="9" name="option_9" class="auto_save">
	             <label for="inlineCheck_09-2" class="qusetion_txt_label ">tell them the directions. </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_09-3" value="K"  data-count="" data-serial="9" name="option_9" class="auto_save">
	             <label for="inlineCheck_09-3" class="qusetion_txt_label ">walk with them.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_09-4" value="V"  data-count="" data-serial="9" name="option_9" class="auto_save">
	             <label for="inlineCheck_09-4" class="qusetion_txt_label ">draw a map on a piece of paper or get a map online.  </label>
	           </div><br>
	           
	     </div>
	     </div>
     
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           10.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You are learning to take photos with your new digital camera or mobile phone. You would like to have:    </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_10-1" value="K"  data-count="" data-serial="10" name="option_10" class="auto_save">
	             <label for="inlineCheck_10-1" class="qusetion_txt_label ">examples of good and poor photos and how to improve them.  </label>
	           </div><br>
	              <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_10-2" value="V"  data-count="" data-serial="10" name="option_10" class="auto_save">
	             <label for="inlineCheck_10-2" class="qusetion_txt_label ">diagrams showing the camera and how to use it.  </label>
	           </div><br>
	              <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_10-3" value="R"  data-count="" data-serial="10" name="option_10" class="auto_save">
	             <label for="inlineCheck_10-3" class="qusetion_txt_label ">clear written instructions with lists and bullet points.  </label>
	           </div><br>
	              <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_10-4" value="A"  data-count="" data-serial="10" name="option_10" class="auto_save">
	             <label for="inlineCheck_10-4" class="qusetion_txt_label ">a chance to ask questions and talk about the camera's features.  </label>
	           </div><br>
	           
	     </div>
	     </div>
     
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           11.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You are going to make something special for your family. You would:    </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_11-1" value="V"  data-count="" data-serial="11" name="option_11" class="auto_save">
	             <label for="inlineCheck_11-1" class="qusetion_txt_label ">decide from pictures in magazines.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_11-2" value="R"  data-count="" data-serial="11" name="option_11" class="auto_save">
	             <label for="inlineCheck_11-2" class="qusetion_txt_label ">find written instructions to make it.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_11-3" value="A"  data-count="" data-serial="11" name="option_11" class="auto_save">
	             <label for="inlineCheck_11-3" class="qusetion_txt_label ">talk it over with my friends. </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_11-4" value="K"  data-count="" data-serial="11" name="option_11" class="auto_save">
	             <label for="inlineCheck_11-4" class="qusetion_txt_label ">make something I have made before.</label>
	           </div><br>
	     </div>
	     </div>
     
     
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           12.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Do you prefer a teacher who likes to use:    </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_12-1" value="A"  data-count="" data-serial="12" name="option_12" class="auto_save">
	             <label for="inlineCheck_12-1" class="qusetion_txt_label ">class discussions, online discussion, online chat and guest speakers</label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_12-2" value="R"  data-count="" data-serial="12" name="option_12" class="auto_save">
	             <label for="inlineCheck_12-2" class="qusetion_txt_label ">a textbook and plenty of handouts.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_12-3" value="V"  data-count="" data-serial="12" name="option_12" class="auto_save">
	             <label for="inlineCheck_12-3" class="qusetion_txt_label ">an overview diagram, charts, labelled diagrams and maps.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_12-4" value="K"  data-count="" data-serial="12" name="option_12" class="auto_save">
	             <label for="inlineCheck_12-4" class="qusetion_txt_label ">field trips, case studies, videos, labs and hands-on practical sessions.  </label>
	           </div><br>
	     </div>
	     </div>
     
     
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           13.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">A website has a video showing how to make a special graph. There is a person speaking, some lists and words describing what to do and some diagrams. You would learn most from:    </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_13-1" value="K"  data-count="" data-serial="13" name="option_13" class="auto_save">
	             <label for="inlineCheck_13-1" class="qusetion_txt_label ">watching the actions.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_13-2" value="A"  data-count="" data-serial="13" name="option_13" class="auto_save">
	             <label for="inlineCheck_13-2" class="qusetion_txt_label ">listening. </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_13-3" value="R"  data-count="" data-serial="13" name="option_13" class="auto_save">
	             <label for="inlineCheck_13-3" class="qusetion_txt_label ">reading the words.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_13-4" value="V"  data-count="" data-serial="13" name="option_13" class="auto_save">
	             <label for="inlineCheck_13-4" class="qusetion_txt_label ">seeing the diagrams.  </label>
	           </div><br>
	     </div>
	     </div>
     
     
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           14.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">I like websites that have:    </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_14-1" value="K"  data-count="" data-serial="14" name="option_14" class="auto_save">
	             <label for="inlineCheck_14-1" class="qusetion_txt_label ">things I can click on and do.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_14_2" value="R"  data-count="" data-serial="14" name="option_14" class="auto_save">
	             <label for="inlineCheck_14_2" class="qusetion_txt_label ">interesting information and articles in print.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_14_3" value="A"  data-count="" data-serial="14" name="option_14" class="auto_save">
	             <label for="inlineCheck_14_3" class="qusetion_txt_label ">audio channels for music, chat and discussion.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_14_4" value="V"  data-count="" data-serial="14" name="option_14" class="auto_save">
	             <label for="inlineCheck_14_4" class="qusetion_txt_label ">interesting design and visual effects.  </label>
	           </div><br>
	     </div>
	     </div>
     
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           15.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You have been selected as a tutor or a leader for a holiday program. This is interesting for your friends. You would:   </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_15_1" value="K"  data-count="" data-serial="15" name="option_15" class="auto_save">
	             <label for="inlineCheck_15_1" class="qusetion_txt_label ">start practising the activities I will be doing in the program. </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_15_2" value="A"  data-count="" data-serial="15" name="option_15" class="auto_save">
	             <label for="inlineCheck_15_2" class="qusetion_txt_label ">describe the activities I will be doing in the program.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_15_3" value="R"  data-count="" data-serial="15" name="option_15" class="auto_save">
	             <label for="inlineCheck_15_3" class="qusetion_txt_label ">show them the list of activities in the program.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_15_4" value="V"  data-count="" data-serial="15" name="option_15" class="auto_save">
	             <label for="inlineCheck_15_4" class="qusetion_txt_label ">show them the map of where it will be held and diagrams about it.  </label>
	           </div><br>
	     </div>
	     </div>
     
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           16.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">You have a problem with your knee. Would you prefer that the doctor: </p>
        </div>
      </div>
      <div class="option_div">
	          <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_16_1" value="V"  data-count="" data-serial="16" name="option_16" class="auto_save">
	             <label for="inlineCheck_16_1" class="qusetion_txt_label ">showed you a diagram of what was wrong.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_16-2" value="A"  data-count="" data-serial="16" name="option_16" class="auto_save">
	             <label for="inlineCheck_16-2" class="qusetion_txt_label ">described to you what was wrong.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_16-3" value="R"  data-count="" data-serial="16" name="option_16" class="auto_save">
	             <label for="inlineCheck_16-3" class="qusetion_txt_label ">gave you an article or brochure that explained knee injuries.  </label>
	           </div><br>
	           <div class="checkbox checkbox-primary radio-inline">
	             <input type="checkbox" id="inlineCheck_16-4" value="K"  data-count="" data-serial="16" name="option_16" class="auto_save">
	             <label for="inlineCheck_16-4" class="qusetion_txt_label ">demonstrated what was wrong using a model of a knee.  </label>
	           </div><br>
	     </div>
	     </div>
     
		  
		 </div>
		
		 <div class="col-md-12 question_action" style="display: block;" id="interest_submit_btn">
		     <button class="btn btn_action" onclick="fnSubmitLSTResult();"><span class="next-spn"><s:text name="com.edupath.common.label.submit"></s:text></span></button>
		 </div>
		  <div class="col-md-12 question_action" id="interest_next_btn">
		     <%-- <button class="btn btn_action" onclick="fnNextInterest();"><span class="next-spn"><s:text name="com.edupath.common.next"></s:text></span></button> --%>
		 </div>
		 
		 
		 </div>
		 
		</div>
</section>







		 
</c:otherwise>
</c:choose>

<s:form name="AnswerForm" id="AnswerForm" method="post">
   <s:hidden name="answerList" id="answerList"></s:hidden>
   <s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
   <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
   <input type="hidden" name="studentId" id="studentId" value="${session.UserSessionObject.id}"/>
   <s:hidden name="remainigTime" id="remainigTime"/> 
   <s:hidden name="userid" id="userid"/> 
   <s:hidden name="remainigSecondTime" id="remainigSecondTime"></s:hidden>
   <s:hidden name="remainigMinuteTime" id="remainigMinuteTime"></s:hidden>
</s:form>



<script>
var slNumberSize = 60;
var studentAnswer = '${utils:replaceJSONEntities(studentAnswerList)}';
var currentQSlNo = 0;
$(document).ready(function(){
	console.log("bharath studentAnswer=>"+studentAnswer)
	 $('#instruction1').show();
	 // $('#instruction').hide();
	 $('#questions_div').hide();
	 	
 
    
    //$('.auto_save').change(function()
	//{
   // 	console.log("$(this)=>"+$(this))
	//	console.log("(this)=>"+this)
	//	console.log("JSON.stringify(this)=>"+JSON.stringify($(this)))
	//	fnAutoSave(this);
	//});
    
    $('input[type="checkbox"]').change(function() {
	 	 
		if (this.checked) 
		{
			fnAutoSave(this);
		}
		else
		{
			fnAutoDelete(this);
		}
	}); 
    
    
    
  });
  
function fnINITForm()
{
	if(studentAnswer.length > 0)
	{
		var jsonArray = jQuery.parseJSON(studentAnswer);
		$.each(jsonArray, function(key, value)
	    {
			 $("input[name='option_"+value.questionNo+"']").each(function(key, item){
				 if($.trim(value.answer) == $.trim(item.value))
				 {
					 $(this).prop("checked", true);
				 }	 
			 });
		});
	}
}
  
function fnAutoSave(e)
{
	console.log("bharath inside fnAutoSave")
	fnSetFormData(e);
	form.action = '';
	form.action = "${pageContext.request.contextPath}/vark/insertAjaxVarkStartTest" ;
	var options = { 
	        beforeSubmit:  showRequest,
	        success:       showResponse
	    };  
	$("#AnswerForm").ajaxSubmit(options);
	console.log("bharath exiting fnAutoSave")
}
function fnAutoDelete(e)
{
	console.log("bharath inside fnAutoDelete")
	fnSetFormData(e);
	form.action = '';
	form.action = "${pageContext.request.contextPath}/vark/deleteAjaxVarkStartTest" ;
	var options = { 
	        beforeSubmit:  showRequest,
	        success:       showResponse
	    };  
	$("#AnswerForm").ajaxSubmit(options);
	console.log("bharath exiting fnAutoDelete")
}
  
function fnSetFormData(e)
{
	 var json = new Object();
		 var answer =  $(e).val();
		console.log("fnSetFormData answer=>"+answer)
		 if(answer != '' && answer != undefined )
		 {
			     json.questionNo = $(e).data('serial');
			     json.answer = $.trim(answer);
			 console.log("finalJsonArray===>"+JSON.stringify(json))
		 }	 
	  
	 $('#answerList').val(JSON.stringify(json));

}
  
  function fnStartLSTTest()
  {
	  $('#instruction1').hide();
	 // $('#instruction').hide();
	  $('#questions_div').show();
	  fnINITForm();
  }
  
  function fninstruction2()
  {
	 
	  $('#instruction').hide();
	  $('#instruction1').show();
  }
  
  function fnSubmitLSTResult()
  {
	  console.log("bharath inside fnSubmitLSTResult")
		 
		form.action = '';
		form.action = "${pageContext.request.contextPath}/vark/submitVarkStartTest";
		form.submit();  
		console.log("bharath exiting fnSubmitLSTResult") 
  }
  
  
  
</script>