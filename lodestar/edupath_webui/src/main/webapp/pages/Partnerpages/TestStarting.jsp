<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="c.tld" prefix="c"%>
<style>
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }

    .carousel-inner img {
      width: 100%; /* Set width to 100% */
      min-height: 200px;
    }

    /* Hide the carousel text when the screen is less than 600 pixels wide */
    @media (max-width: 600px) {
      .carousel-caption {
        display: none; 
      }
    }
	
	.backgroungimg1
	 {
	 background-image: url("${pageContext.request.contextPath}/images/partner/background.png");
	 height:726px
	 }
	 .riasac
	 {
	 background-image: url("${pageContext.request.contextPath}/images/partner/riasac.png");
	 height:140px
	 }
  </style>
  <div class="backgroungimg1" >
	<div class="col-sm-10 col-sm-offset-1 text-center" style="margin-top:50px">
   <span style="font-size:20pt;color:#1276bb">THE LODESTAR-STEPS</span><br>
   <span style="font-size: 19pt;color:#1276bb;font-weight: 600;">CAREER GUIDANCE TEST</span>
	<br><br>
  </div>

<div class="container">
<div class="row">
  <div class="col-sm-10 col-sm-offset-1 text-center" style="color:#414042;font-size:10pt">
    Passion is often the difference between failure and success in walks of the life, including job and careers.To help you progress towards a successful career, there are parameters that need to be considered and aligned :<br><br>
  </div>
  
   <div class="col-sm-10 col-sm-offset-1 text-left" style="color:#414042;font-size:10pt">
   
   
  
        <div class="col-sm-3" style="width:22%">
           <div class="col-sm-9">
          <p>Your Interests <br>and Personality</p> 
		  </div>
		   <div class="col-sm-3">
		   <img src="${pageContext.request.contextPath}/images/partner/m.png">
         </div>
        </div>
        
        <div class="col-sm-3" style="width:23%">
         <div class="col-sm-9">
          <p style="padding-top:10px">Cognitive abilities</p> 
		  </div>
		   <div class="col-sm-3">
		   <img src="${pageContext.request.contextPath}/images/partner/m.png">
         </div>
         </div>
       
		  <div class="col-sm-3" style="width:22%">
         <div class="col-sm-9">
          <p style="padding-top:10px">Choice of Career</p> 
		  </div>
		   <div class="col-sm-3">
		   <img src="${pageContext.request.contextPath}/images/partner/m.png">
         </div>
               
         
        </div>
        
		<div class="col-sm-3" style="width:26%">
          
           <p>Education path - College, Graduation, Post Graduation and so on</p> 
          
        </div>
      </div>
	  
	  
	  
	  <div class="col-sm-10 col-sm-offset-1 text-center" style="color:#414042;font-size:10pt">
	  <br><br>
		The test that you are about to take is based on internationally proven Holland's theory of Vocational Personalities in Work Environments, commonly known as Holland codes theory. This theory propounds that people and occupations have unique characteristics and that both of these characteristics can be measured. The theory goes onto say that individuals have greater job satisfaction when they take up jobs that match the characteristics they hold. <br><br>
  </div>
  
  <div class="col-sm-10 col-sm-offset-1 text-center" style="color:#1276bb;font-size:10pt">
    The 6 personality types as per Holland Codes are - Realistic, Investigative, Artistic, Social, Enterprising and Conventional (RIASEC)
  </div>
	  
	  
  </div>
  
</div>




<div class="container text-center">    
  
  <br>
  <div class="row">
   <div class="col-sm-2"></div>
    <div class="col-sm-8 riasac" >
    
	</div>
  </div>
</div><br>

			<div class="col-sm-10 col-sm-offset-1 text-center">
    <button type="button" class="btn btn-primary btn-lg" onclick="fninstruction2();" style="border-radius:0px;width:300px">TEST INSTRUCTION</button>
  </div>

</div>