<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="commonutil.tld" prefix="utils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<s:hidden name="aptitudeComplete" id="aptitudeComplete"></s:hidden>
<c:choose>
<c:when test="${interestCompleted ne true}">
	<div class="modal fade in" id="interestNotCompleteModal" role="dialog" >
		<div class="modal-dialog">
	    	<div class="modal-content">
	        	<div class="modal-header">
	          		<button type="button" class="close" data-dismiss="modal">&times;</button>
	          		<h4 class="modal-title"></h4>
	        	</div>
	        	<div class="modal-body" style="text-align: center;">
	          		<p style="font-size: 11pt"><s:text name="com.edupath.questionnarie.interest.not.compleated.message"></s:text></p>
		        </div>
		        <div class="modal-footer" style="text-align: center;">
	    	      <button type="button" class="btn custom-modal-btn" data-dismiss="modal" onclick="redirectToInterest()"><s:text name="com.edupath.questionnarie.model.btn.label"/></button>
	        	</div>
	      	</div>
	   	</div>
	</div>
</c:when>
<c:when test="${aptitudeComplete ne 'STARTED'}">
  <div class="modal fade in" id="completeModal" role="dialog" >
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body" style="text-align: center;">
          <p style="font-size: 11pt"><s:text name="com.edupath.questionnarie.message"></s:text></p>
          
        </div>
        <div class="modal-footer" style="text-align: center;">
          <button type="button" class="btn custom-modal-btn" data-dismiss="modal"><s:text name="com.edupath.questionnarie.model.btn.label"/></button>
        </div>
      </div>
      
    </div>
  </div>
</c:when>
<c:otherwise>
<div class="rows">
<c:if test=""></c:if>
<!--Instruction start -->
 <div class="clo-md-12" style="  padding-top: 10px;padding-left: 25px;">
   <div id="instruction" >
     <div class="instruction_txt pagetitle">
       <span><s:text name="com.edupath.questionnarie.aptitude.instruction.label"></s:text></span>
     </div>
     <div class="instruction">
       <ul>
         <li>
           <div class="instruction_txt_div">
             <div class="icon_left_div">
              <i class="fa fa-hand-o-right"></i>
             </div>
             <div class="instruction_right_div">
                The Aptitude Test has 5 sections namely, Numerical, Verbal, Mechanical, Reasoning & Spatial Each section is aimed at assessing your ability & proficiency in some key fundamental areas.
             </div>
           </div>
         </li>
         <li>
          <div class="instruction_txt_div">
           <div class="icon_left_div">
              <i class="fa fa-hand-o-right"></i>
           </div>
           <div class="instruction_right_div">
              <span>
               Each section has 12 questions, which have multiple choices as answers. 
             </span>
           </div>
          </div>
         </li>
         <li>
          <div class="instruction_txt_div">
            <div class="icon_left_div">
             <i class="fa fa-hand-o-right"></i>
            </div>
            <div class="instruction_right_div">
               <span>
                 For each question there is only one correct answer. Your task is to choose the best answer and mark the answer directly on your answer sheet. 
              </span>
            </div>
          </div>
         </li>
         <li>
         <div class="instruction_txt_div">
            <div class="icon_left_div">
              <i class="fa fa-hand-o-right"></i>
            </div>
            <div class="instruction_right_div">
               <span>
                   Ensure that you read the question carefully before selecting your answer.
               </span>
            </div>
          </div>
         </li>
         <li>
         <div class="instruction_txt_div">
            <div class="icon_left_div">
              <i class="fa fa-hand-o-right"></i>
            </div>
            <div class="instruction_right_div">
              <span>
               There is no negative marking in the test. 
             </span>
            </div>
          </div>
         </li>
         <li>
         <div class="instruction_txt_div">
            <div class="icon_left_div">
              <i class="fa fa-hand-o-right"></i>
            </div>
            <div class="instruction_right_div">
              <span>
               Answer all questions.
             </span>
            </div>
          </div>
         </li>
          <li>
         <div class="instruction_txt_div">
            <div class="icon_left_div">
              <i class="fa fa-hand-o-right"></i>
            </div>
            <div class="instruction_right_div">
              <span>
               The Aptitude test is timed strictly and allows only 60 minutes to complete all the sections. Once 60 minutes are over, you will not be able to answer any further questions.
             </span>
            </div>
          </div>
         </li>
       </ul>
     </div>
   </div>
 </div><!--Instruction end -->

 <div class="row">
 <div class="col-md-12  remove_div" >
 <!-- <div class="startdiv"> -->
   <button class="btn btn_action" onclick="fnStartTest();" style="margin-right: 50px;">
      <span class="next-spn" style="padding-left:0px; padding-right:0px"><s:text name="com.edupath.questionnarie.start"></s:text></span>
   </button>
    <button class="btn btn_action" onclick="fnGoBack();" >
      <span class="next-spn" style="padding-left:0px; padding-right:0px"><s:text name="com.edupath.questionnarie.latter"></s:text></span>
   </button>
 <!-- </div> -->
 </div>
 <div class="col-md-3">
 </div>
 </div>
 <div id="questionDiv">
    <div class="" style="margin-top: 0px; text-align: right; padding-right: 35px; width: 80%; position: fixed;">
      <span class="remain-time"><s:text name="com.edupath.questionnarie.remaintime"></s:text> &nbsp; <label id="timerlabel">00 : 00</label>&nbsp; &nbsp;<span>(<s:text name="com.edupath.questionnarie.time"></s:text>)</span></span> 
   </div>
   <!--Question start  -->
   <div class="apt_main">
    
    <div class="question_div_1">
      <div>
     <div class="qusetion_txt question_div_1_1">
      <div class="serial_left_div ">
         1.&nbsp;
      </div>
       <div class="qusetion_right_div">
           <p class="question_p">Which expression is equivalent to 48?</p>
       </div>
     </div>
     <div class="option_div">
         <c:set var="option" value="${fn:split('52-10X2-3X12, (52-10)2-3X12, 52-10(2-3X12), 52-10X2-(3X12)', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio1_${count.index}" value="${item}" data-serial="61" name="option_61" class="auto_save">
             <label for="inlineRadio1_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
    
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           2.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">If the product 4864 X 9 P 2 is divisible by 12, the value of P is :</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('2, 0, 6, 1', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_2_${count.index}" value="${item}" data-serial="62" name="option_62" class="auto_save">
             <label for="inlineRadio_2_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           3.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">If x=y=2z and xyz=256 then what is the value of x?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('4, 16, 12, 8', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_3_${count.index}" value="${item}" data-serial="63" name="option_63" class="auto_save">
             <label for="inlineRadio_3_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           4.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">A can do a work in 6 days and B in 9 days. How many days will both take together to complete the work?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('0.27, 3, 3.6, 7.5', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_4_${count.index}" value="${item}" data-serial="64" name="option_64" class="auto_save">
             <label for="inlineRadio_4_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           5.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">A dice is rolled twice. What is the probability of getting a sum equal to 10?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('1/6, 1/9, 1/12,	1/18', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_5_${count.index}" value="${item}" data-serial="65" name="option_65" class="auto_save">
             <label for="inlineRadio_5_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           6.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">By selling a note book for Rs.19.50, a dealer makes a profit of 30%. By how much should he increase his selling price so as to make a profit of 40%.</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('1.75, 1.5, 3, 2', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_6_${count.index}" value="${item}" data-serial="66" name="option_66" class="auto_save">
             <label for="inlineRadio_6_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           7.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Two trains running in opposite directions cross a man standing on the platform in 30 seconds and 20 seconds respectively . If they cross each other in 26 seconds, what is the ratio of their speeds?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('3:4, 1:3, 3:1, 3:2', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_7_${count.index}" value="${item}" data-serial="67" name="option_67" class="auto_save">
             <label for="inlineRadio_7_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
       <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           8.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">How many two-digit numbers satisfy this property : The last digit (units digit) of the square of the two-digit number is 8?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('3, 5, 4, None of the above', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_8_${count.index}" value="${item}" data-serial="68" name="option_68" class="auto_save">
             <label for="inlineRadio_8_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           9.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Which of the following integers has the most number of divisors?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('1000, 176, 182, 99', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_9_${count.index}" value="${item}" data-serial="69" name="option_69" class="auto_save">
             <label for="inlineRadio_9_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           10.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">When 0.36 is written in simplest fractional form, the sum of the numerator and denominiator is </p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('15, 45, 34, 136', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_10_${count.index}" value="${item}" data-serial="70" name="option_70" class="auto_save">
             <label for="inlineRadio_10_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           11.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">The difference of two numbers is 20% of the larger number. If the smaller number is 12, the larger is :</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('16, 15, 18, 20', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_11_${count.index}" value="${item}" data-serial="71" name="option_71" class="auto_save">
             <label for="inlineRadio_11_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           12.&nbsp;
        </div>
        <div class="qusetion_right_div" >
           <p class="question_p">One litre of water is added to a syrup of 3 litres having 40% sugar. The percentage of sugar in the new solution is</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('13.33%, 15%, 30%, 33%', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_12_${count.index}" value="${item}" data-serial="72" name="option_72" class="auto_save">
             <label for="inlineRadio_12_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           13.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">A catastrophe is a </p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('scheme,	bulletin, blemish, tragedy', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_13_${count.index}" value="${item}" data-serial="73" name="option_73" class="auto_save">
             <label for="inlineRadio_13_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           14.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Given below are two words which have a certain relationship with each other. Pick the word from the four options to form the pair of the same relationship.</p>
			<p class="question_p">King : Throne :: Rider : ?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('Seat, Horse, Saddle, Chair', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_14_${count.index}" value="${item}" data-serial="74" name="option_74" class="auto_save">
             <label for="inlineRadio_14_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           15.&nbsp;
        </div>
        <div class="qusetion_right_div" >
           <p class="question_p">Read the sentence highlighted below. Then choose the answer from the four sentences in which the underlined word is used in the same way.</p>
           <p class="question_p"><strong>He needs a <span style="text-decoration: underline;">tape</span> that is sticky on both sides</strong> </p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('The TV crew will tape the sports show, 	Wrap the wire with electrical tape,	Tape the box before you courier it to the shop,	I recorded this music tape at the show yesterday', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_15_${count.index}" value="${item}" data-serial="75" name="option_75" class="auto_save">
             <label for="inlineRadio_15_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           16.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Read the sentence carefully. If one of the words is misspelled, choose that option from the options given below or else mark no mistake.</p>
		   <p class="question_p">(A) My cat is hesitant (B) while my dog has a friskyer (C) temperament. (D) No mistake </p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, C, D, B', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_16_${count.index}" value="${item}" data-serial="76" name="option_76" class="auto_save">
             <label for="inlineRadio_16_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           17.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Complete the sentence with the most appropriate word:</p>
           <p class="question_p">There are ______________ views on the issue of giving bonus to the employees.</p>
			<p class="question_p">A. Independent</p>
			<p class="question_p">B. Divergent</p>
			<p class="question_p">C. Modest</p>
			<p class="question_p">D. Valuable</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('D, A, B, C', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_17_${count.index}" value="${item}" data-serial="77" name="option_77" class="auto_save">
             <label for="inlineRadio_17_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           18.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Choose the word from the options below which expresses the opposite meaning of the word given below
           <p class="question_p"> CRYPTIC</p>
			<div class="row">
             	<div class="col-md-3" style="width: 20%">
                	<p class="question_p" > A. Futile
             	</div>
             	<div class="col-md-4">
                	<p class="question_p"> B. Mysterious
             	</div>
            	 <div class="col-md-4">
            	 </div>
           	</div>
           	<div class="row">
             	<div class="col-md-3" style="width: 20%">
                	<p class="question_p" > C. Candid
             	</div>
             	<div class="col-md-4">
                	<p class="question_p"> D. Worst
             	</div>
            	 <div class="col-md-4">
            	 </div>
           	</div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, C, D, B', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_18_${count.index}" value="${item}" data-serial="78" name="option_78" class="auto_save">
             <label for="inlineRadio_18_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           19.&nbsp;
        </div>
         <div class="qusetion_right_div">
           <p class="question_p">Complete the sentence with the appropriate option given below</p>
		   <p class="question_p">It is not easy to remain tranquil when those around you ......</p>
		   <p class="question_p">A. behave in a socially acceptable manner
		   <p class="question_p">B. exhibit pleasant mannerism
		   <p class="question_p">C. are losing their heads
           <p class="question_p">D. agree to whatever you say</p>
          </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('C, A, B, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_19_${count.index}" value="${item}" data-serial="79" name="option_79" class="auto_save">
             <label for="inlineRadio_19_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           20.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">The sentence below has a mistake in the sentence structure. If you find a mistake, choose the option from the options given below or else choose No Mistake
           <p class="question_p">To win a gold medal to athele is one of the greatest honours that could come at Olympic Games
		   <p class="question_p">A. At the Olympic Games to win a gold medal is one of the greatest honours that could come to an athelete
		   <p class="question_p">B. One of the greatest honours that could come to an athelete is to win a gold medal at the Oylmpic Games
		   <p class="question_p">C. One of the honours, to win a gold medal at the Olympic Games, is one of the greatest honours that could come to an athelete
           <p class="question_p">D. No mistake </p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, C, D, B', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_20_${count.index}" value="${item}" data-serial="80" name="option_80" class="auto_save">
             <label for="inlineRadio_20_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           21.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Rearrange the following jumbling parts that are labeled as P, Q, R, S and T to complete the sentence below

		   <p class="question_p">Venice is a strange and beautiful city in the north of Italy.

			<p class="question_p">P : There are about four hundred old stone bridges joining the island of Venice.
			<p class="question_p">Q : In this city there are no motor cars, no horses, no buses.
			<p class="question_p">R : These small islands are near one another.
			<p class="question_p">S : It is not an island but a hundred and seventeen islands.
			<p class="question_p">T. This is because Venice has no streets</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('PQTRS, PQSRT, PRQTS, SRPQT', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_21_${count.index}" value="${item}" data-serial="81" name="option_81" class="auto_save">
             <label for="inlineRadio_21_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           22.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Prose is related to writing in the same way as Lisp is related to....</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('Reading, Speech, Drawing, Music ', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_22_${count.index}" value="${item}" data-serial="82" name="option_82" class="auto_save">
             <label for="inlineRadio_22_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           23.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Complete the sentence with the most appropriate word:</p>
		   <p class="question_p">Although the ________ implications of genetic research are hotly debated, most biologists ________ its undeniable scientific importance.

    	   <p class="question_p">A. moral---reject
		   <p class="question_p">B. psychological --- complicate
	       <p class="question_p">C. ethical ---- acknowledge
		   <p class="question_p">D. academic ---- compartmentalize </p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_23_${count.index}" value="${item}" data-serial="83" name="option_83" class="auto_save">
             <label for="inlineRadio_23_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
       <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           24.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Choose the correct meaning of the idiom from the options given below

			<p class="question_p">To catch a tartar

			<p class="question_p">A. To trap wanted criminal with great difficulty
			<p class="question_p">B. To catch a dangerous person
			<p class="question_p">C. To meet with disaster
			<p class="question_p">D. To deal with a person who is more than one's match</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_24_${count.index}" value="${item}" data-serial="84" name="option_84" class="auto_save">
             <label for="inlineRadio_24_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
         <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           25.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Which of the following mechanical devices is used to aid in the cooling of an internal combustion engine?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A lever, A gauge, A hammer, A pump', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_25_${count.index}" value="${item}" data-serial="85" name="option_85" class="auto_save">
             <label for="inlineRadio_25_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           26.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">The tool shown below is used ______________</p>
            <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_86.png">
           </div> 
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('for dirt removal, with bit to drill holes into wood	, with forging a hammer to manipulate hot metal, with solder to fuse metal pieces together', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_26_${count.index}" value="${item}" data-serial="86" name="option_86" class="auto_save">
             <label for="inlineRadio_26_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           27.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Which of the following will create the smaller area of light?</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_87.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, Neither A or B', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_27_${count.index}" value="${item}" data-serial="87" name="option_87" class="auto_save">
             <label for="inlineRadio_27_${count.index}" class="qusetion_txt_label apt_radio_lbl apt_md_lbl">${item}</label>
           </div>
          </c:forEach>
           <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_27_100" value="Can't say" data-serial="87" name="option_87" class="auto_save">
             <label for="inlineRadio_27_100" class="qusetion_txt_label apt_radio_lbl">Can't say</label>
           </div>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           28.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Which type of external energy could be used to operate a pump?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A battery, An internal combustion engine, An electric motor, All of the above', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_28_${count.index}" value="${item}" data-serial="88" name="option_88" class="auto_save">
             <label for="inlineRadio_28_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           29.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Which tool, among the given options, is most closely related to the tool shown in the figure on the left?</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_89.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, C, D, B', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_29_${count.index}" value="${item}" data-serial="89" name="option_89" class="auto_save">
             <label for="inlineRadio_29_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
       <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           30.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Which doorstop illustrated below, is more likely to slip?</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_90.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, Neither A or B', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_30_${count.index}" value="${item}" data-serial="90" name="option_90" class="auto_save">
             <label for="inlineRadio_90_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
           <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_30_100" value="Can't say" data-serial="90" name="option_90" class="auto_save">
             <label for="inlineRadio_30_100" class="qusetion_txt_label apt_radio_lbl">Can't say</label>
           </div>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           31.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">V-belts are usually used for ____________________</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('Long and short drives, Long drive, Short drive,	None of the above', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_31_${count.index}" value="${item}" data-serial="91" name="option_91" class="auto_save">
             <label for="inlineRadio_31_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           32.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p"> Rear view mirror is a ___________________</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('convex mirror, concave mirror, plain mirror, None of the above', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_32_${count.index}" value="${item}" data-serial="92" name="option_92" class="auto_save">
             <label for="inlineRadio_32_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           33.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">The main function of a pulley is _________________</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('to decrease the strength of a construction crane, to override the power of an electric motor, to add energy to a system, to change the direction of a pulling force', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success">
             <input type="radio" id="inlineRadio_33_${count.index}" value="${item}" data-serial="93" name="option_93" class="auto_save">
             <label for="inlineRadio_33_${count.index}" class="qusetion_txt_label apt_radio_lbl big_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           34.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">There are two balls which are of same density, one large and another small, are rolled toward each other at the equal speed. If they collide, what will happen to the smaller ball?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('It will continue forward in the same direction, It will stop and stay at the point of impact, It will be propelled backwards in the opposite direction, It will jump over the heavier ball', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_34_${count.index}" value="${item}" data-serial="94" name="option_94" class="auto_save">
             <label for="inlineRadio_34_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           35.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Kunal has a lever whose pivot point is 3 feet from the 50 pound box he wants to lift. Kunal is standing at the other end of the lever, 6 feet from the pivot point. How much force does he apply to lift the box?</p>
            <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_95.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('50 pounds, 25 pounds, 100 pounds, 6 pounds', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_35_${count.index}" value="${item}" data-serial="95" name="option_95" class="auto_save">
             <label for="inlineRadio_35_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           36.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">The suspension system on a bicycle is likely to use which of the following mechanical devices?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A chain, A pulley, A gear, A spring', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_36_${count.index}" value="${item}" data-serial="96" name="option_96" class="auto_save">
             <label for="inlineRadio_36_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           37.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Complete the series:
 		   <p class="question_p">AB, BA, ABC, CBA, ABCD, _________</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('ECDF, EDCF, DCBA, DCBE', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_37_${count.index}" value="${item}" data-serial="97" name="option_97" class="auto_save">
             <label for="inlineRadio_37_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
       <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           38.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Appeciation is related to Reward in the same way as Disgrace is related to</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('Crime, Punishment, Guilt, Allegation', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_38_${count.index}" value="${item}" data-serial="98" name="option_98" class="auto_save">
             <label for="inlineRadio_38_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           39.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">If GO is coded as 32, SHE as 49, then SOME will be coded as ______________</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('58, 64, 62, 56', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_39_${count.index}" value="${item}" data-serial="99" name="option_99" class="auto_save">
             <label for="inlineRadio_39_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           40.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Reena is twice as old as Sunita. Three years ago, she was three times as old as Sunita. How old is Reena now? </p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('12 years, 8 years, 6 years, 9 years', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_40_${count.index}" value="${item}" data-serial="100" name="option_100" class="auto_save">
             <label for="inlineRadio_40_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_40_100" value="Can't say" data-serial="100" name="option_100" class="auto_save">
             <label for="inlineRadio_40_100" class="qusetion_txt_label apt_radio_lbl">Can't say</label>
           </div>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           41.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Find a number which when added to itself 13 times, give 112</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('7, 8, 9, 11', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_41_${count.index}" value="${item}" data-serial="101" name="option_101" class="auto_save">
             <label for="inlineRadio_41_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           42.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Complete the series:
		   <p class="question_p">AOP, CQR, EST, GUV, ________________</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('IYZ, HWX, JWX, IWX', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_42_${count.index}" value="${item}" data-serial="102" name="option_102" class="auto_save">
             <label for="inlineRadio_42_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
       <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           43.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">If South-East becomes North, North-East becomes West and so on. What will West become?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('North-East, North-West, South-East, South-West', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_43_${count.index}" value="${item}" data-serial="103" name="option_103" class="auto_save">
             <label for="inlineRadio_43_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           44.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">In a certain code TWENTY is written as 863985 and ELEVEN is written as 323039. Then TWELVE would be written as</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('863903, 863584, 863203, 863063', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_44_${count.index}" value="${item}" data-serial="104" name="option_104" class="auto_save">
             <label for="inlineRadio_44_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           45.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Arjun has put his time-piece on the table in such a way that at 6 p.m, the hour hand points to the North. In which direction would the minute hand point at 9.15 p.m?</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('South-East, South, North, West', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_45_${count.index}" value="${item}" data-serial="105" name="option_105" class="auto_save">
             <label for="inlineRadio_45_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           46.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">A shoe always has</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('Laces, Sole, Leather, Design', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_46_${count.index}" value="${item}" data-serial="106" name="option_106" class="auto_save">
             <label for="inlineRadio_46_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           47.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">In the following question a statement is given, which is followed by two conclusions numbered I and II. You have to assume everything in the statement to be true, then consider the two conclusions together and decide which of them logically follows beyond a reasonable doubt from the information given in the statement.
			<p class="question_p">Statement: Reading makes a full man, conference a ready man and writing an exact man.

			<p class="question_p">Conclusions:
			<p class="question_p">I. Pointed and precise expression comes only through extensive writing.
			<p class="question_p">II. Extensive reading makes a complete man.</p>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('Only conclusion I follows, Only conclusion II follows, Either I or II follows, Both I and II follow', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_47_${count.index}" value="${item}" data-serial="107" name="option_107" class="auto_save">
             <label for="inlineRadio_47_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           48.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Read the statement given below. 

			<p class="question_p">"People speculate when they consider a situation and assume something to be true, based on inconclusive evidence". 

			<p class="question_p">Which one of the following situations given below is the best example of Speculation?</p>
        </div>
      </div>
      <div class="option_div">
       <div class="radio radio-success">
             <input type="radio" id="inlineRadio_48_1_100" value='Alina decides that it would be appropriate to wear jeans to her new office on Friday after reading about "Casual Fridays" in her employee handbook' data-serial="108" name="option_108" class="auto_save">
             <label for="inlineRadio_48_1_100" class="qusetion_txt_label big_radio_lbl">Alina decides that it would be appropriate to wear jeans to her new office on Friday after reading about "Casual Fridays" in her employee handbook</label>
        </div>
        <div class="radio radio-success">
             <input type="radio" id="inlineRadio_48_2_100" value="When Emily opens the door in tears, Robert guesses that she's had a death in her family." data-serial="108" name="option_108" class="auto_save">
             <label for="inlineRadio_48_2_100" class="qusetion_txt_label big_radio_lbl">When Emily opens the door in tears, Robert guesses that she's had a death in her family.</label>
        </div>
        <div class="radio radio-success">
             <input type="radio" id="inlineRadio_48_3_100" value="Mary spends thirty minutes sitting in traffic and wishes that she took the train instead of driving" data-serial="108" name="option_108" class="auto_save">
             <label for="inlineRadio_48_3_100" class="qusetion_txt_label big_radio_lbl">Mary spends thirty minutes sitting in traffic and wishes that she took the train instead of driving</label>
        </div>
        <div class="radio radio-success">
             <input type="radio" id="inlineRadio_48_4_100" value="After consulting several guidebooks and her travel agent, Jennifer feels confident that the hotel she has chosen is first-rate" data-serial="108" name="option_108" class="auto_save">
             <label for="inlineRadio_48_4_100" class="qusetion_txt_label big_radio_lbl">After consulting several guidebooks and her travel agent, Jennifer feels confident that the hotel she has chosen is first-rate</label>
        </div>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           49.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">In the following question there are a number of cubes with patterned faces; no pattern appears on more than one side of the cube. Find out which of the answer cubes is the original cube on the left, if rotated in any direction:</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_109.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_49_${count.index}" value="${item}" data-serial="109" name="option_109" class="auto_save">
             <label for="inlineRadio_49_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           50.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">In the following question there are a number of cubes with patterned faces; no pattern appears on more than one side of the cube. Find out which of the answer cubes is the original cube on the left, if rotated in any direction:</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_110.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_50_${count.index}" value="${item}" data-serial="110" name="option_110" class="auto_save">
             <label for="inlineRadio_50_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           51.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">In the following question there are a number of cubes with patterned faces; no pattern appears on more than one side of the cube. Find out which of the answer cubes is the original cube on the left, if rotated in any direction:</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_111.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_51_${count.index}" value="${item}" data-serial="111" name="option_111" class="auto_save">
             <label for="inlineRadio_51_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           52.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">In the questions below, select the SINGLE answer choice that represents the two parts that join together to make the given figure on the left. </p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_112.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_52_${count.index}" value="${item}" data-serial="112" name="option_112" class="auto_save">
             <label for="inlineRadio_52_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
      <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           53.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">From the options given, choose the figure that comprises of ALL the objects shown on the left.</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_113.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_53_${count.index}" value="${item}" data-serial="113" name="option_113" class="auto_save">
             <label for="inlineRadio_53_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           54.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Which pattern, from the given options, can be folded to make the cube shown on the left</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_114.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_54_${count.index}" value="${item}" data-serial="114" name="option_114" class="auto_save">
             <label for="inlineRadio_54_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           55.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">In the figure below on the left, there is an unfolded cardboard pattern. The solid lines represent the shape of the different parts and the dashed lines represent fold lines. Choose any of the below options which will result if the pattern is folded up.</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_115.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_55_${count.index}" value="${item}" data-serial="115" name="option_115" class="auto_save">
             <label for="inlineRadio_55_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           56.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">In the figure below on the left, there is an unfolded cardboard pattern. The solid lines represent the shape of the different parts and the dashed lines represent fold lines. Choose any of the below options which will result if the pattern is folded up.</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_116.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_56_${count.index}" value="${item}" data-serial="116" name="option_116" class="auto_save">
             <label for="inlineRadio_56_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           57.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">The figure shown on the extreme left is a 2-D image. Chose the correct 3-D image from the options given below which would be formed from the original 2-D image.</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_117.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_57_${count.index}" value="${item}" data-serial="117" name="option_117" class="auto_save">
             <label for="inlineRadio_57_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           58.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Identify the figure from the given options, which would complete the pattern given on the extreme left.</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_118.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_58_${count.index}" value="${item}" data-serial="118" name="option_118" class="auto_save">
             <label for="inlineRadio_58_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           59.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Find out which of the four figures can be formed by joining the pieces given in the figure on the left?</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_119.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_59_${count.index}" value="${item}" data-serial="119" name="option_119" class="auto_save">
             <label for="inlineRadio_59_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
     <div>
       <div class="qusetion_txt question_div_1_1">
        <div class="serial_left_div ">
           60.&nbsp;
        </div>
        <div class="qusetion_right_div">
           <p class="question_p">Determine what the final figure will look like in this analogy

			<p class="question_p">A. 1 circle on the left side, 1 square on the right side
			<p class="question_p">B. 2 squares on the left side, 1 circle on the right side
			<p class="question_p">C. 2 circles on the left side, 1 square on the right side
			<p class="question_p">D. 1 square on the left side, 1 circle on the right side</p>
           <div class="question_img_div">
             <img alt="question" class="question_img" src="${pageContext.request.contextPath}/images/question/aptitude/slNo_120.png">
           </div>
        </div>
      </div>
      <div class="option_div">
         <c:set var="option" value="${fn:split('A, B, C, D', ',') }"></c:set>
          <c:forEach items="${option}" var="item" varStatus="count">
          <div class="radio radio-success radio-inline">
             <input type="radio" id="inlineRadio_60_${count.index}" value="${item}" data-serial="120" name="option_120" class="auto_save">
             <label for="inlineRadio_60_${count.index}" class="qusetion_txt_label apt_radio_lbl">${item}</label>
           </div>
          </c:forEach>
     </div>
     </div>
     
   <div class="col-md-12 question_action">
     <button class="btn btn_action" onclick="fnAnswerFormSubmit('AptitudeAction');"><span class="next-spn"><s:text name="com.edupath.common.label.submit"></s:text></span></button>
   </div>
     
     
     
     
     
    </div><!--Qusetion_1  -->
    
   </div>
 </div>
</c:otherwise>
</c:choose>
<!--Message div  -->
<div class="modal fade" id="testCompleteModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body" style="text-align: center;">
          <p><s:text name="com.edupath.questionnarie.test.message"></s:text></p>
          
        </div>
        <div class="modal-footer" style="text-align: center;">
          <button type="button" class="btn custom-modal-btn" data-dismiss="modal"><s:text name="com.edupath.questionnarie.model.btn.label"/></button>
        </div>
      </div>
      
    </div>
  </div> 
<script>

if('${interestCompleted}' == 'false')
{
	$('#interestNotCompleteModal').modal("show");	
}
var slNumberSize = 120;
var startTest;
var second = 1;
var minute = 0;
var maxTestTime = 60; /* In minute  */
var remainSecond = 60;
var isStop = false, isRequest = false;
var aptitudeComplete = $('#aptitudeComplete').val();
var remainTime = 0;
var remainTimeMenute = '${remainigMinuteTime}';
var remainTimeSecond = '${remainigSecondTime}';
var studentAnswer = '${utils:replaceJSONEntities(studentAnswerList)}';
if(remainTimeMenute > 0)
{
	remainSecond = parseInt(remainTimeSecond);
	if(remainSecond ==0)
	{
		remainSecond = 60;
	}
	else
	{
		second = (60 - parseInt(remainTimeSecond)) + 1;
	}	
	maxTestTime  = parseInt(remainTimeMenute) + 1;
	minute = 59 - parseInt(remainTimeMenute);
}	
if(aptitudeComplete != "STARTED")
{
	/* $('#instruction').remove();
	$('#questionDiv').remove();
	$('.remove_div').remove();*/
	$('#apt_messageId').show(); 
}
function fnINITForm()
{
	if(studentAnswer.length > 0)
	{
		var jsonArray = jQuery.parseJSON(studentAnswer);
		$.each(jsonArray, function(key, value)
	    {
			 $("input[name*='option_"+value.slNo+"']").each(function(key, item){
				 if($.trim(value.answer) == $.trim(item.value))
				 {
					 $(this).prop("checked", true);
				 }	 
			 });
		});
	}
}
  function fnStartTest()
  {
	  $('.remove_div').remove();
	  $('#instruction').remove();
	  $('#questionDiv').show();
	  fnINITForm();
	  //fnUpdateStudentResult('AptitudeAction');
	  if(maxTestTime != 0)
	  {
		  maxTestTime = maxTestTime - 1;
	  }	  
	  startTest = setInterval(fnStudentTimer,1000);
	 
  }
  
  function fnStudentTimer()
  {
	  var secondMSG, minuteMSG;
	  
	  remainSecond = remainSecond - 1;
	  if(remainSecond == 0 && minute <=60)
	  {
		  minute = minute + 1;
		  maxTestTime = maxTestTime - 1;
		  second = 1;
		  if(maxTestTime != -1)
		  {
		    fnUpdateStudentResult('AptitudeAction', (maxTestTime * 60) * 1000);
		  }	  
	  }
	  if(minute == 59)
	  {
		  if(!isRequest)
		  {
		    isRequest = true;
		    fnUpdateStudentResult('AptitudeAction', 59  * 1000); 
		  }	  
	  }	  
	  if(minute == 60 && remainSecond == 0)
	  {
		  isStop = true;
		  remainTime = 60;
		  maxTestTime = 0;
		  clearInterval(startTest);
		  fnAutoSave('AptitudeAction');
		  $("#testCompleteModal").modal("show");
		  return false;
		  
	  }
	  if(remainSecond == 0)
	  {
		  remainSecond = 59;
	  }	
	  if(remainSecond <10)
	  {
		  secondMSG = "0" +  remainSecond;
	  }
	  else
	  {
		  if(isStop)
		  {
			  secondMSG = "00";
		  }
		  else
		  {
			  secondMSG = remainSecond;
		  }	  
	  }
	  if(maxTestTime <10)
	  {
		  if(maxTestTime == -1)
		  {
			  minuteMSG = "00";
		  }
		  else
		  {
		    minuteMSG = "0" +  maxTestTime;
		  }	  
	  }
	  else
	  {
		  minuteMSG = maxTestTime;
	  } 
	  remainTime = (maxTestTime * 60  + remainSecond) * 1000;
	  var timeTxt = minuteMSG + " : " + secondMSG;
	  $('#timerlabel').html(timeTxt);
  }
  
  function fnSetFormData(e)
  {
	 var finalJsonArray = []; 
	// for(var count = 61; count <= parseInt(slNumberSize); count++)
	// {
		 var answer =  $(e).val();
		 if(answer != '' && answer != undefined)
		 {
			 var json = new Object();
			     json.slNo = $(e).data('serial');
			     json.correctAnswer = $.trim(answer);
			 finalJsonArray.push(json);
		 }	 
	 //}
	 if(minute == 60)
	 {
		 remainTime = (remainTime * 60)  * 1000;
	 }	
	 
	 console.log("===>"+JSON.stringify(finalJsonArray))
	 
	 $('#remainigTime').val(remainTime);
	 $('#answerList').val(JSON.stringify(finalJsonArray));

  }
  
  function fnSetFormDataApptiFinalSubmit()
  {
	  var finalJsonArray = []; 
		 for(var count = 119; count <= parseInt(slNumberSize); count++)
		 {
			 var answer = $("input[name*='option_"+count+"']:checked").val();
			 if(answer != '' && answer != undefined)
			 {
				 var json = new Object();
				     json.slNo = count;
				     json.correctAnswer = $.trim(answer);
				 finalJsonArray.push(json);
			 }	 
		 }
		 if(minute == 60)
		 {
			 remainTime = (remainTime * 60)  * 1000;
		 }	
		 
		 console.log("===>"+JSON.stringify(finalJsonArray))
		 
		 $('#remainigTime').val(remainTime);
		 $('#answerList').val(JSON.stringify(finalJsonArray));

  }
  
  
  
  $(document).ready(function()
  {
	  if("${aptitudeComplete ne 'STARTED'}" == 'true')
	  {
		  $("#completeModal").modal('show');
	  }	  
	  $("#testCompleteModal").on('hidden.bs.modal', function () 
	  {
		   fnGoBack();
      }); 
	  $("#completeModal").on('hidden.bs.modal', function () 
	  {
		   fnGoBack();
      });
	  
  });
  
  function redirectToInterest()
  {
	  form.action = '${pageContext.request.contextPath}/myapp/InterestAction';
	  form.submit();
  }
</script>