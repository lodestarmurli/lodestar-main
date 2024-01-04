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
      <p style="font-size:18pt;text-transform: uppercase;color:#1276bb"> <b>Learning Style Test Instructions</b></p>
	  
	  <p style="margin-top:50px">
	  <ul style="color:#1276bb;font-size:10pt;list-style:disc;">
			<li style="color:#1276bb "><font color="#414042">Choose the answer which best explains your preference and click the box next to it.  </font></li>
			<li style="color:#1276bb"><font color="#414042">Please click<b> more than one </b>if a single answer does not match your perception.  Leave blank any question that does not apply.  </font></li>
			<li style="color:#1276bb"><font color="#414042">All questions are<b> compulsory.</font></li>
		</ul>
	  </p>
	   
	   <br> <br>
	   <button type="button" class="btn btn-primary btn-lg" onclick="fnStartLSTTest();" style="border-radius:0px;width:170px;height:50px;background-color:#1276bb;margin-left:5px">TAKE TEST NOW</button>
	  
      
      
      

     
      
     
  
  </div>
</div>
</div>

  

