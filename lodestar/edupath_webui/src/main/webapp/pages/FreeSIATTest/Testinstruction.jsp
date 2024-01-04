<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="c.tld" prefix="c"%>
<style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
	
	.backgroungimg
	 {
	
	 height:599px;
	 }
  </style>

<div class="container backgroungimg " >

    

    <div class="col-sm-9 col-md-9 col-xs-9  col-sm-offset-2 col-md-offset-2 col-xs-offset-2">
	<div class="row">
	
     <br><br>
      <p style="font-size:18pt;text-transform: uppercase;color:#1276bb">Test <b>Instructions</b></p>
	  
	  <p style="margin-top:50px">
	  <ul style="color:#1276bb;font-size:10pt;list-style:disc;">
			<li style="color:#1276bb "><font color="#414042">The Interest Test aims to understand your key interest areas and activities which you enjoy doing the most. This Interest Test has 60 statements. You will need <b>20-25 Minutes</b> to complete this section.</font></li>
			<li style="color:#1276bb"><font color="#414042">Kindly respond to each statement by choosing one of the options from the two possible response options provided <b>(Yes or No).</b></font></li>
			<li style="color:#1276bb"><font color="#414042">Carefully read the statements and give the first response thought which comes to your mind.DO NOT spend lot of time on one statement.</font></li>
			<li style="color:#1276bb"><font color="#414042">Respond to all statements.</font></li>
			<li style="color:#1276bb"><font color="#414042"><b>Your response </b>shall be kept <b>confidential</b>.</font></li>
			<li style="color:#1276bb"><font color="#414042">Each student can give this test only once.</font></li>
			<li style="color:#1276bb"><font color="#414042">Test once given will be be locked for the user.</font></li>
			<li style="color:#1276bb"><font color="#414042">Answers cannot be changed once submitted.</font></li>
		</ul>
	  </p>
	   
	   <br> <br>
	   <button type="button" class="btn btn-primary btn-lg" onclick="fnStartInterestTest();" style="border-radius:0px;width:170px;height:50px;background-color:#1276bb;margin-left:5px">TAKE TEST NOW</button>
	  
      
      
      

     
      
     
  
  </div>
</div>
</div>

  

