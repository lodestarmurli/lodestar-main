<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="c.tld" prefix="c"%>
<HTML>
<HEAD>
<TITLE><s:text name="com.edupath.webtitle" /></TITLE>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
</HEAD>
<BODY style="background-color:#f0f9ff">

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-color:#fff;padding-top:5px;padding-bottom:5px" align="center">
	
	<img src="${pageContext.request.contextPath}/images/SIATTEST/lodestar_logo.gif" class="img-responsive">
	</div>
	
	 <div>
       
            <div class="container backgroungimg " >

    

    <div class="col-sm-9 col-md-9 col-xs-9  col-sm-offset-2 col-md-offset-2 col-xs-offset-2">
	<div class="row">
	
     <br><br>
      <p style="font-size:18pt;text-transform: uppercase;color:#1276bb; text-align:center"> <b>Learning Style Test</b></p>
	  
	  <br>
	   <p class="change_p_h4" style=" text-align:center">Thank You for registering . You will receive the test link to you registered email id.
	   </p>
				
	  
	   
	   <br> <br>
	  
      
      
      

     
      
     
  
  </div>
</div>
</div>
		   
	</div>
	
	 
		

</BODY>


<script>
var form = document.AnswerForm;
 

function showRequest(formData, jqForm, options)
{
	console.log("bharath showRequest")
	$('#testLoading').show();
} 

function showResponse(responseText, statusText, xhr, $form)
{
	console.log("bharath showResponse")
	$('#testLoading').hide(); 
}


$(document).ready(function()
{
});



</script>
</HTML>