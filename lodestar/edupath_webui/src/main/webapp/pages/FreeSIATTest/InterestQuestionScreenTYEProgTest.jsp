<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="c.tld" prefix="c"%>
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
		     
		     <jsp:include page="/pages/FreeSIATTest/Testinstruction.jsp"></jsp:include>
		     
		   </div>
		 
		 <!--Question Start-->
		 <section class=" col-md-12 col-xs-12 col-sm-12">
		 <div class="container" style="margin-top:26px;">
		 
		 <div class="question_main" id="questions_div" style="display: none;">
		  
		 <div class="qusetion_container">
		 
		 <div id="questnodisplay" align="right"><b><p><br/>Question no <span id="qno">1</span> out of 60</p></b></div>
		 
		   <div class="qusetion_content" style="display: none" id="qusetion_content_1">
		     <div class="col-md-9 col-xs-9 col-sm-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               1.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like to understand how different machines and equipment work</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3 col-xs-3 col-sm-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_1">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="1" id="yes_1">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="1" id="no_1">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_2">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               2.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like reading about scientific inventions and discoveries</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_2">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="2" id="yes_2">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="2" id="no_2">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_3">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               3.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given an opportunity, I would like to draw and/or paint as part of my job</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_3">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="3" id="yes_3">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="3" id="no_3">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_4">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               4.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">To participate in co-curricular activities, I choose projects that involve working with others than participate in debates, individual competitions, etc.</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_4">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="4" id="yes_4">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="4" id="no_4">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_5">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               5.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I am comfortable speaking in front of large audiences</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_5">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="5" id="yes_5">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="5" id="no_5">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_6">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               6.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I prefer to keep track of my pocket money</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_6">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="6" id="yes_6">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="6" id="no_6">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_7">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               7.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I prefer to understand how the parts of a gadget work (like a phone, an airplane, a computer etc) than follow a process to create a software</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_7">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="7" id="yes_7">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="7" id="no_7">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_8">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               8.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like reading about monuments and historical sites, for example, Hampi, Jaisalmer, Bahai Temple, etc.</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_8">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="8" id="yes_8">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="8" id="no_8">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_9">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               9.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a chance, I would like to be part of a musical/theatrical troupe and tour the world to perform</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_9">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="9" id="yes_9">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="9" id="no_9">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_10">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               10.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a choice, I like to participate in activities that require group interaction than art work</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_10">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="10" id="yes_10">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="10" id="no_10">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_11">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               11.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy more when I lead projects at school than when I have to document (write the steps) of the project</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_11">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="11" id="yes_11">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="11" id="no_11">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_12">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               12.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">When working on a problem, I like to strictly follow the rules to solve it</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_12">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="12" id="yes_12">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="12" id="no_12">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_13">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               13.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a choice, I would like to play outdoor games like cricket, football etc., than solve puzzles</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_13">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="13" id="yes_13">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="13" id="no_13">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_14">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               14.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I find researching and learning about new things more interesting than creating new portraits and paintings</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_14">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="14" id="yes_14">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="14" id="no_14">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_15">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               15.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a chance, I would enjoy pursuing photography as a profession</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_15">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="15" id="yes_15">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="15" id="no_15">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_16">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               16.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">Helping/teaching people interests and motivates me more than leading people in projects</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_16">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="16" id="yes_16">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="16" id="no_16">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_17">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               17.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">For group projects, I like to decide and assign tasks to people than be in charge of arranging or managing the material (gadgets, stationery etc.) required</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_17">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="17" id="yes_17">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="17" id="no_17">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_18">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               18.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I usually have a fixed timetable for completing my daily tasks</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_18">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="18" id="yes_18">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="18" id="no_18">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_19">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               19.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy working with physical things like plants, trees, gadgets, machines etc</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_19">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="19" id="yes_19">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="19" id="no_19">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_20">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               20.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy tasks that require researching new topics than leading people to work on tasks</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_20">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="20" id="yes_20">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="20" id="no_20">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_21">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               21.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I would like to take up projects that require creative designing</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_21">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="21" id="yes_21">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="21" id="no_21">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_22">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               22.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I would enjoy teaching science than conducting science experiments</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_22">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="22" id="yes_22">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="22" id="no_22">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_23">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               23.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">It is important for me to express my opinion in a group or  during group discussions </p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_23">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="23" id="yes_23">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="23" id="no_23">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_24">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               24.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I prefer to work on assignments that have fixed processes/steps to do than those which have to be researched </p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_24">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="24" id="yes_24">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="24" id="no_24">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_25">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               25.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a choice, I would enjoy participating in sports than debate on a topic</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_25">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="25" id="yes_25">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="25" id="no_25">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_26">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               26.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I find working on science experiments more fascinating than participating in debates, competitions, etc</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_26">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="26" id="yes_26">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="26" id="no_26">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_27">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               27.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like performing dance, music etc., in front of an audience</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_27">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="27" id="yes_27">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="27" id="no_27">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_28">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               28.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like to help others with activities than to keep track of expenses, follow steps for a process etc.</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_28">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="28" id="yes_28">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="28" id="no_28">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_29">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               29.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I would like to work with the team to complete the project, than drive/lead a project</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_29">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="29" id="yes_29">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="29" id="no_29">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_30">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               30.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I would prefer to have my desk neatly organized than decorate my desk and shelves</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_30">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="30" id="yes_30">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="30" id="no_30">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_31">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               31.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a choice, I would like to play outdoors than be indoors drawing or painting</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_31">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="31" id="yes_31">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="31" id="no_31">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_32">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               32.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a choice, I would like to solve brain-teaser puzzles, than playing outdoor sports</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_32">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="32" id="yes_32">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="32" id="no_32">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_33">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               33.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I would like to create a cartoon character, illustrate and animate it</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_33">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="33" id="yes_33">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="33" id="no_33">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_34">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               34.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like to work with others to help them solve their problems</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_34">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="34" id="yes_34">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="34" id="no_34">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_35">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               35.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like being in charge of group projects in the classroom</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_35">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="35" id="yes_35">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="35" id="no_35">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_36">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               36.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a choice, as part of a project, I would like to follow the steps to complete the project than startup or initiate ideas</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_36">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="36" id="yes_36">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="36" id="no_36">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_37">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               37.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a choice, I would prefer to create working models for science projects than teach/explain the science used behind it</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_37">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="37" id="yes_37">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="37" id="no_37">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_38">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               38.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy reading about the mysteries of space and solar system than teaching/explaining the same to others</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_38">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="38" id="yes_38">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="38" id="no_38">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_39">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               39.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I dream of being a novelist/author one day</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_39">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="39" id="yes_39">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="39" id="no_39">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_40">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               40.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I can easily strike conversations with new people than follow process/steps/rules</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_40">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="40" id="yes_40">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="40" id="no_40">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_41">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               41.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I would prefer to debate on an issue than sketch portraits or paint landscapes</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_41">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="41" id="yes_41">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="41" id="no_41">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_42">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               42.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I am more interested in organising information than in researching for information</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_42">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="42" id="yes_42">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="42" id="no_42">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_43">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               43.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy repairing household appliances like phones, clocks, etc</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_43">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="43" id="yes_43">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="43" id="no_43">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_44">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               44.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like working with number games and brain-teasers such as Sudoku than solve puzzles such as 'Spot the difference', Find the odd-one-out', etc.</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_44">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="44" id="yes_44">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="44" id="no_44">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_45">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               45.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given an opportunity, I would like to work in an art museum</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_45">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="45" id="yes_45">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="45" id="no_45">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_46">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               46.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like to volunteer for social causes such as helping the elderly at old age homes, volunteering at orphanages, schools for the differently abled people etc</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_46">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="46" id="yes_46">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="46" id="no_46">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_47">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               47.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy the thrill of playing games that involve money exchange like business, etc, than playing outdoor sports</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_47">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="47" id="yes_47">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="47" id="no_47">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_48">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               48.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I prefer work that involves processes and rules than work with machines and tools</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_48">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="48" id="yes_48">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="48" id="no_48">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_49">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               49.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy work that involves caring for animals, plants and handling tools, machines etc. than teaching or helping others</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_49">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="49" id="yes_49">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="49" id="no_49">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_50">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               50.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy understanding and studying maps and charts than explaining the same to others</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_50">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="50" id="yes_50">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="50" id="no_50">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_51">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               51.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy theatre and dramatics and if given an opportunity, I want to be an actor</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_51">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="51" id="yes_51">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="51" id="no_51">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_52">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               52.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a choice, I would like to discuss, teach and explain concepts than create models to put across my point</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_52">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="52" id="yes_52">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="52" id="no_52">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_53">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               53.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I would be interested to learn how a business works than to learn processes in developing software</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_53">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="53" id="yes_53">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="53" id="no_53">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_54">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               54.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">While working on my assignments, I give importance to following the rules and instructions than discussing/interacting with others</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_54">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="54" id="yes_54">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="54" id="no_54">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_55">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               55.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy participating in adventurous activities/sports than in science exhibitions</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_55">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="55" id="yes_55">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="55" id="no_55">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_56">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               56.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I like to understand and explore different scientific theories</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_56">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="56" id="yes_56">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="56" id="no_56">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_57">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               57.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given a chance, I would want to play a musical instrument and pursue it as a career</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_57">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="57" id="yes_57">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="57" id="no_57">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_58">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               58.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">If given an opportunity, I would like to serve/help people as part of my job</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_58">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="58" id="yes_58">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="58" id="no_58">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_59">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               59.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I enjoy group discussions and debating on subjects/topics more than writing about new ideas in different subjects of study</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_59">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="59" id="yes_59">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="59" id="no_59">NO</button>
		       </div>
		    </div>
		  </div>
		  
		   <div class="qusetion_content" style="display: none" id="qusetion_content_60">
		     <div class="col-md-9">
		         <div class="qusetion_txt">
		            <div class="serial_left_div ">
		               60.&nbsp;
		            </div>
		            <div class="qusetion_right_div">
		             <p class="question_p">I prefer to explain using diagrams and flowcharts than build working models for science projects</p>
		            </div>
		          </div>
		     </div>
		    <div class="col-md-3">
		       <div class="btn-group">
		         <input type="hidden" id="answer_60">
		         <button type="button" class="btn btn_qt " data-answer="YES" data-serial="60" id="yes_60">YES</button>
		         <button type="button" class="btn btn_qt " data-answer="NO" data-serial="60" id="no_60">NO</button>
		       </div>
		    </div>
		  </div>
		  
		 </div>
		
		 <div class="col-md-12 question_action" style="display: none;" id="interest_submit_btn">
		     <button class="btn btn_action" onclick="fnSubmitInterestForm();"><span class="next-spn"><s:text name="com.edupath.common.label.submit"></s:text></span></button>
		 </div>
		  <div class="col-md-12 question_action" id="interest_next_btn">
		     <%-- <button class="btn btn_action" onclick="fnNextInterest();"><span class="next-spn"><s:text name="com.edupath.common.next"></s:text></span></button> --%>
		 </div>
		 
		 
		 </div>
		 
		</div>
</section>







		<div class="modal fade in" id="questionMadatoryModal" role="dialog" >
			<div class="modal-dialog">
		      <!-- Modal content-->
		    	<div class="modal-content">
		        	<div class="modal-header">
		          		<button type="button" class="close" data-dismiss="modal">&times;</button>
		          		<h4 class="modal-title"></h4>
		        		</div>
		        	<div class="modal-body" style="text-align: center;">
		          		<p style="font-size: 11pt"><s:text name="com.edupath.questionnarie.interest.not.answered.message"></s:text></p>
		        	</div>
		        	<div class="modal-footer" style="text-align: center;">
		          		<button type="button" class="btn custom-modal-btn" data-dismiss="modal"><s:text name="com.edupath.questionnarie.model.btn.label"/></button>
			        </div>
		      	</div>
		    </div>
		</div>
		<div class="modal fade in" id="interestSubmitMsgModal" role="dialog" >
			<div class="modal-dialog">
		      <!-- Modal content-->
		    	<div class="modal-content">
		        	<div class="modal-header">
		          		<button type="button" class="close" data-dismiss="modal">&times;</button>
		          		<h4 class="modal-title"></h4>
		        		</div>
		        	<div class="modal-body" style="text-align: center;">
		          		<p style="font-size: 11pt">
		          			<c:choose>
		          				<c:when test="${session.UserSessionObject.trial eq true}">
		          					<s:text name="com.edupath.questionnarie.interest.compleated.message.trail"></s:text>
		          				</c:when>
		          				<c:otherwise>
		          					<s:text name="com.edupath.questionnarie.interest.compleated.message"></s:text>
		          				</c:otherwise>
		          			</c:choose>
		          		</p>
		        	</div>
		        	<div class="modal-footer" style="text-align: center;">
		          		<button type="button" class="btn custom-modal-btn" data-dismiss="modal"><s:text name="com.edupath.questionnarie.model.btn.label"/></button>
			        </div>
		      	</div>
		    </div>
		</div>
</c:otherwise>
</c:choose>





<script>
var slNumberSize = 60;
var studentAnswer = '${studentAnswerList}';

var currentQSlNo = 0;
$(document).ready(function(){
	if(studentAnswer.length > 0)
	{
		var jsonArray = jQuery.parseJSON(studentAnswer);
		$.each(jsonArray, function(index, value)
	    {
			 $('#answer_' + value.slNo).val(value.answer);
			if(value.answer == 'YES')
			{
				$('#yes_' + value.slNo).addClass('btn_yes');
			}
			else
			{
				$('#no_' + value.slNo).addClass('btn_no');
			}	
			$('#qusetion_content_' + value.slNo).hide();
			currentQSlNo = value.slNo;
			
			
		});
		var unAnsQuestion = findFirstUnAnsweredQuestion();
		if(unAnsQuestion < currentQSlNo)
		{
			currentQSlNo = unAnsQuestion - 1;
		}
		//fnStartInterestTest();
		
	}	
    fnNextInterest(true);
    if('${interestCompleted}' == "true")
    {
    	// $('#interestCompletedMsgModal').modal("show");
    }
  });
  
  function fnNextInterest(skipAnsCheck)
  {
	   if(!skipAnsCheck && !$('#answer_' + currentQSlNo).val() || $('#answer_' + currentQSlNo).val() == "")
	   {
		   $('#questionMadatoryModal').modal("show");
		   return;
	   }
	   if(currentQSlNo <= 60)
	   {
	   		$('#qusetion_content_' + currentQSlNo++).hide();
	   		$('#qusetion_content_'+ currentQSlNo).show();
	   		if(currentQSlNo<61)
	   		{
	   			$('#qno').html(currentQSlNo);
	   		}
	   		else
	   		{
	   			$("#questnodisplay").hide();
	   		}
	   		
	   } 
	   if(currentQSlNo >= 60)
	   {
		   $('#interest_submit_btn').hide();
		   $('#interest_next_btn').hide();
	   }
	   if(currentQSlNo > 60)
	   {
		   fnSubmitInterestForm();
	   }
  }

  $('.btn_qt').click(function()
  {
	  var answer = $(this).data('answer');
	  var slNo = $(this).data('serial');
	  $('#answer_' + slNo).val(answer);
	  if(answer == 'YES')
	  {
		  $('#no_' + slNo).not(this).removeClass('btn_no');
		  $('#yes_' + slNo).addClass('btn_yes');
	  }
	  else
	  {
		  $('#yes_' + slNo).not(this).removeClass('btn_yes');
		  $('#no_' + slNo).addClass('btn_no');
	  }	
	  fnAutoSaveForInterest('TYEProgTest');
   });
  
  function fnSetFormData()
  {
	 var finalJsonArray = []; 
	 for(var count = currentQSlNo; count <= parseInt(slNumberSize); count++)
	 {
		 var answer = $('#answer_' + count).val();
		 if(answer != '' && answer != undefined)
		 {
			 var json = new Object();
			     json.slNo = count;
			     json.correctAnswer = answer;
			 finalJsonArray.push(json);
		 }	 
	 }
	 console.log("========>"+JSON.stringify(finalJsonArray));
	 $('#answerList').val(JSON.stringify(finalJsonArray));

  }
  function fnSetFormDataFinalSubmit()
  {
	 var finalJsonArray = []; 
	 for(var count = 59; count <= parseInt(slNumberSize); count++)
	 {
		 var answer = $('#answer_' + count).val();
		 if(answer != '' && answer != undefined)
		 {
			 var json = new Object();
			     json.slNo = count;
			     json.correctAnswer = answer;
			 finalJsonArray.push(json);
		 }	 
	 }
	 console.log("========>"+JSON.stringify(finalJsonArray));
	 $('#answerList').val(JSON.stringify(finalJsonArray));

  }
  
  function fnSubmitInterestForm()
  {
	  fnAnswerFormSubmit('TYEProgTest');
  }
  
 /* $('#interestSubmitMsgModal').on('hidden.bs.modal', function () 
  {
	  fnAnswerFormSubmit('Psychometrictests');
  });*
  
 /* $("#interestCompletedMsgModal").on('hidden.bs.modal', function () 
  {
	   fnGoBack();
  });*/
  
  function isAllAnswered()
  {
	  var isAllAnswered = true;
	  for(var i = 1; i <= 60; i++)
	  {
		  if(!$('#answer_' + i).val() || $('#answer_' + i).val() == "")
		  {
			  isAllAnswered = false;
			  return;
		  }
	  }
	  return isAllAnswered;
  }
  
  function findFirstUnAnsweredQuestion()
  {
	  var unAnsQuestionNo = 0;
	  for(var i = 1; i <= 60; i++)
	  {
		  if(!$('#answer_' + i).val() || $('#answer_' + i).val() == "")
		  {
			  unAnsQuestionNo = i;
			  return unAnsQuestionNo;
		  }
	  }
	  return unAnsQuestionNo;
  }
  
  function fnStartInterestTest()
  {
	  $('#instruction1').hide();
	 // $('#instruction').hide();
	  $('#questions_div').show();
  }
  
  function fninstruction2()
  {
	 
	  $('#instruction').hide();
	  $('#instruction1').show();
  }
  
  
  
</script>