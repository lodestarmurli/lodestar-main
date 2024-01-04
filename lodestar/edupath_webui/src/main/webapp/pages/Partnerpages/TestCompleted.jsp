<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="c.tld" prefix="c"%>
 <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
	 .backgroungimg2
	 {
	 background-image: url("${pageContext.request.contextPath}/images/partner/bg.png");
	 }
  </style>
  <div class="jumbotron backgroungimg2" style="height:492px;align:center" align="center">
  <div class="container text-center">
        
    <p style="color:#1276bb;font-size:25pt"><b>THANK YOU</b> FOR TAKING THE TEST</p>
  </div>
  <div class="container text-center">    
  <br><br>
  <h4 style="color:#1276bb;"><b>HOW TO COLLECT YOUR TEST REPORT?</b></h4><br>
  
  <div class="row">
  <div class="col-sm-10 col-sm-offset-1 text-center">
    <div class="col-sm-4">
      <img src="${pageContext.request.contextPath}/images/partner/edit.png" height="30px">
      <p style="font-size: 17px;">Appearing for the STEPS exam on <b>12th November</b>, 2017 is mandatory to get the report.</p>
    </div>
    <div class="col-sm-4"> 
      <img src="${pageContext.request.contextPath}/images/partner/edit.png" height="30px">
      <p style="font-size: 17px;"> The report of the psychometric test will be emailed to your registered email id along with your STEPS result in the last week
of November.</p>    
    </div>
    <div class="col-sm-4">
	<img src="${pageContext.request.contextPath}/images/partner/edit.png" height="30px">
      <p style="font-size: 17px;">You could also download the report from your STEPS dashboard.</p>
    </div>
	</div>
	
  </div>
</div>
<div style="width:100%;background-color:transparent;height:35px;margin-top:10px;opacity: 0.3;"> 
<!--<div class="container col-sm-8 col-sm-offset-2 text-center">
        <br>
    <p style="color:#1276bb;font-size:17pt;"><b>First 500 students get a FREE 15 min call with our Career Expert</b> to get an in-depth analysis of their test report</p>
  </div>-->
</div>
<c:choose>
 <c:when  test="${isregister eq 0}">
 <button onclick="fnregister('Psychometrictests')" type="button" class="btn btn-primary btn-lg" style="border-radius:0px;width:350px;height:58px;margin-top:44px;background-color:#1276bb;cursor:pointer;z-index:1000">SIGN UP TO GET A CALL</button>
</c:when>
	
	<c:otherwise>
	
	<button type="button" class="btn btn-primary btn-lg" style="border-radius:0px;width:350px;height:58px;margin-top:44px;background-color:#1276bb;cursor:default;z-index:1000">Already Signed-up</button>
	
	
	</c:otherwise>
	</c:choose>

</div>
  
<div class="container-fluid bg-3 text-center">    
  <h5>You do not have to enter your details again. We will use the contact info as submitted by you during registration.</h5>
 <div class="container col-sm-6 col-sm-offset-3 text-center">
 <hr>
 </div>
  
</div><br>

<div class="container-fluid bg-3 text-center">    
  <div class="row">
    <div class="container col-sm-10 col-sm-offset-1  text-center">
        <br>
    <p style="color:#1276bb;font-size:14pt;">ABOUT LOADSTAR CAREER GUIDANCE</p>
	<br>
	<p>Lodestar is India's first scientific Career Guidance company that uses authentic data and advanced decision methodologies to enable students to make Smart Decisions with respect to career, college, exams and tutorial. Students from over 100 leading schools in Bangalore have made a Smart Decision with Lodestar. This program offers exposure to over 250+ exciting career options, expert guidance by trained guidance specialists, and individual attention to each child, and a comprehensive career plan at the end of the program.
<br><br>
It is not just enough to choose your career - you must know all details about the education path required to get there.</p>

<br><br>

<p style="color:#1276bb;font-size:14pt;">What you get from the Lodestar Program</p>

	<div class="container col-sm-5" >

<div dir="rtl">
<ul style="list-style:disc;">
<li style="color:#1276bb;text-align:right"><font color="#414042"> Aptitude and interest profiling</font></li>
<li style="color:#1276bb;text-align:right"><font color="#414042">Career fitment evaluation </font></li>
<li style="color:#1276bb;text-align:right"><font color="#414042">Two consultations with our Career Expert</font></li>
<li style="color:#1276bb;text-align:right"><font color="#414042"> Shortlisting of 20 suitable occupations</font></li>
<li style="color:#1276bb;text-align:right"><font color="#414042">Top 2 career decisions-Plan A & B</font></li>
<li style="color:#1276bb;text-align:right"><font color="#414042"> A 30 page in-depth report</font></li>
</ul>
</div>


	</div>
	<div class="container col-sm-1" >
	</div>
	<div class="container col-sm-5 " style="text-align:left">
		<ul style="list-style:disc;color:#1276bb;">
			<li style="color:#1276bb;"> <font color="#414042">Detailed education path</font></li>
		</ul>
		<span style="margin-left:15px"> - &nbsp;&nbsp; Entrance exam</span><br>
		<span style="margin-left:15px"> - &nbsp;&nbsp; Degree planning</span><br>
		<span style="margin-left:15px"> - &nbsp;&nbsp; Degree specialization planning</span><br>
		<span style="margin-left:15px"> - &nbsp;&nbsp; UG & PG specialization planning</span><br>
		<span style="margin-left:15px"> - &nbsp;&nbsp; Relevant special institutes & courses</span><br>
		
		
		

	</div>
  </div>
  <div class="container col-sm-6 col-sm-offset-3 text-center">
 <hr>
 </div>
  <div class="container col-sm-10 col-sm-offset-1  text-center">
        <br>
    <p style="color:#1276bb;font-size:14pt;">ALL THIS IS DONE OVER 3 PERSOMALIZED AS ILLUSTRATED BELOW<font color="RED">*</font></p>
	
	<br>
	<img src="${pageContext.request.contextPath}/images/partner/abc.png">
	<br><br>
	<p style="color:red;font-size:10pt;">*For students of 11th and 12th program will be done in 2 sessions</p>
	</div>
	<div class="container col-sm-6 col-sm-offset-3 text-center">
 <hr>
 </div>
  <br>
  <div class="container col-sm-10 col-sm-offset-1  text-center">
        <br>
    <p style="color:#1276bb;font-size:14pt;text-transform: uppercase;"><b>So what are you waiting for? Give yourself the Lodestar Career Guidance advantage today!</b></p>
	<br>
	<p style="color:#414042;font-size:16pt;text-transform: uppercase;"><b>
	To know more </b> about the Lodestar Career Guidance program, <b>contact@lodestar.guru </b>
or call us at <b> +91 8026714555 </b>
	</b></p>
	<br>
	<p style="color:#414042;font-size:12pt;">  Contact: Lodestar Career Guidance, 3rd Floor, Oceana Chambers, No.757/11, K.R. Road, 13th Cross, Jayanagar, 7th Block - West, Bangalore - 560082</p>
	
	
	</div>
	
  
</div>

 
</div>


</div>
<br><br>


