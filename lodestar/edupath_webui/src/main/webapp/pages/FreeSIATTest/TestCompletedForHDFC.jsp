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
      
      padding: 25px;
    }
	 .backgroungimg2
	 {
	 
	 }
  </style>
  
  <link href="${pageContext.request.contextPath}/styles/SIATTest/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
  <script type="text/javascript" src="${pageContext.request.contextPath}/styles/SIATTest/bootstrap-datetimepicker.js" charset="UTF-8"></script>
  
  <div style="height:492px;align:center;" align="center">

  
  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-image: url('${pageContext.request.contextPath}/images/SIATTEST/bg1.png');padding-top:40px;padding-bottom:20px">
  
   <p style="color:#1276bb;font-size:25pt"><b>THANK YOU</b> FOR TAKING THE TEST</p>
  	
  </div>
  
  
  
  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-image: url('${pageContext.request.contextPath}/images/SIATTEST/2bg.png');padding-top:15px;padding-bottom:20px;">
  
   <p style="color:#1276bb;font-size:12pt">
   <img src="${pageContext.request.contextPath}/images/SIATTEST/edit.png" height="20px" ><br><br>
  <b> You have completed the vocational personality test. To complete the other steps please schedule your session.</b></p>
  	 <br> 
  	 <form action="${pageContext.request.contextPath}/Test/AppointmentbookLink" method="get" name="appointmentbook" id="appointmentbook">
  	 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="float:left;align:center" align="center">
  	 <div class="form-group" >
                <label for="dtp_input1" class="col-md-1 col-lg-1 col-sm-1 col-xs-12  col-lg-offset-3 col-sm-offset-3 col-md-offset-3 control-label"><b>Slot</b></label>
                <div class="input-group date form_datetime col-md-3 col-sm-4 col-xs-12" data-date="" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1" style="float:left;margin-bottom:20px">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
					
                </div>
                <div class="col-lg-2 col-md-3 col-sm-4 col-xs-10 col-lg-offset-0 col-sm-offset-0 col-md-offset-0 col-xs-offset-1" >
                <button type="button" class="btn btn-info btn-md btn-block" onclick="scheduleappointment();">Schedule Appointment</button>
                </div>
				<input type="hidden" id="dtp_input1" name="dtp_input1" value="" />
				<input type="hidden" id="token" name="token" value="${token}" /><br/><br/>
            </div>
            
            
            </div>
            
           </form> 
  	 
  	 
  	 
  	 
  	 
  	 
  </div>
  
  
  
  
   <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12  col-sm-offset-1 col-lg-offset-1 col-md-offset-1" style="padding-top:40px;padding-bottom:20px;">
  
   <p style="color:#1276bb;font-size:12pt"><b>ABOUT LODESTAR CAREER GUIDANCE</b></p>
   
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-top:20px;padding-bottom:20px">
   <p style="font-size:10pt">Lodestar is India's first scientific Career Guidance company that uses authentic data and advanced decision methodologies to enable students to make Smart Decisions with respect to career, college, exams and tutorial. Students from over 100 leading schools in Bangalore have made a Smart Decision with Lodestar. This program offers exposure to over 250+ exciting career options, expert guidance by trained guidance specialists, and individual attention to each child, and a comprehensive career plan at the end of the program.
</p></div>
<p style="font-size:10pt">It is not just enough to choose your career - you must know all details about the education path required to get there.</p>
  	
  </div>
  
  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-top:20px;">
  
   <p style="color:#1276bb;font-size:12pt"><b>WHAT YOU GET FROM THE LODESTAR PROGRAM</b></p>
   
   </div>
   
    <div class="col-lg-10 col-md-10  col-sm-12  col-xs-12 col-lg-offset-1  col-md-offset-1  ">
    <hr style="border: 0.5px solid #b3cfe2;">
    
     
     
     <div class="col-sm-3 col-lg-3 col-md-3 col-xs-12  ">
	<img src="${pageContext.request.contextPath}/images/SIATTEST/1offer.png" class="img-responsive">
	</div>
	
	<div class="col-sm-3 col-lg-3 col-md-3 col-xs-12">
	<img src="${pageContext.request.contextPath}/images/SIATTEST/2offer.png" class="img-responsive">
	</div>
	
	<div class="col-sm-3 col-lg-3 col-md-3 col-xs-12">
	<img src="${pageContext.request.contextPath}/images/SIATTEST/3offer.png" class="img-responsive">
	</div>
	
	<div class="col-sm-3 col-lg-3 col-md-3 col-xs-12">
	<img src="${pageContext.request.contextPath}/images/SIATTEST/4offer.png" class="img-responsive">
	</div>
     
    </div>
    
   
    
   <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12 col-lg-offset-1  col-md-offset-1" style="padding-bottom:20px">
    
       
         <div class="col-lg-12 col-md-12">
    
	<br>
	<p style="color:#414042;font-size:12pt;">  Contact: Lodestar Career Guidance, 3rd Floor, Oceana Chambers, No.757/11, K.R. Road, 13th Cross, Jayanagar, 7th Block - West, Bangalore - 560082</p>
	</div>
	 <br> <br> <br> 
	</div>
    
  
  
 



</div>
  <script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	function scheduleappointment()
	{
		if($("#dtp_input1").val()!=null && $("#dtp_input1").val().trim()!="")
		{
			var form = document.appointmentbook;
			form.submit();
			
			
			
			
		}
		else
		{
			alert("Please select date and time for schedule");
		}
	}
	
</script>
  



