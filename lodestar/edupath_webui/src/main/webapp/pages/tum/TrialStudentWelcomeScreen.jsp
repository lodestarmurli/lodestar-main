<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 

<div class="edupath-padding-nonchild">

<div id="mainPage_2">
  <div class="row">
   <!--Content 2  -->
     <div class="col-md-12" style="padding-top: 20;">
      <div class="instruction_txt_div">
             <div class="change_icon_left_div">
                  <img alt="" src="../images/icons/welcome-TUM.gif" >
             </div>
             <div class="change_right_div change_p_h6">
                <p class="change_p_h3" style="line-height: 36px;padding-top: 5px;">Interest Assessment Test </p>
                <span class="change_p_h4" style="font-weight:600">Test-instructions</span>
             </div>
        </div>
         
           <div class="instruction_txt_div" style="padding-top: 0px;">
             <div class="change_icon_left_div">
               &nbsp;
             </div>
             <div class="change_right_div change_p_h6">
         	     <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                  	This Interest Assessment Test aims to understand your key interest areas and activities which you enjoy doing the most. The Interest Assessment has 60 statements. 
                  </div>
         	    </div>
         	    
         	     <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                     You will need <strong>20-25 minutes</strong> to complete this section
                  </div>
         	    </div>
         	    
         	    <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                    Respond to each statement by choosing one of the two possible response options provided <strong>(YES or NO)</strong>
                  </div>
         	    </div>
         	    
         	    <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                     Read the statements carefully and provide the first response thought that comes to your mind. DO NOT spend a lot of time on one statement
                  </div>
         	    </div>
         	    
         	    <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                    Respond to all statements
                  </div>
         	    </div>
         	    
         	    <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                     Your responses are kept confidential
                  </div>
         	    </div>
             </div>
             <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 13%">
                    &nbsp;
                    </div>
                  <div class="change_right_div change_p_h6">
                    <p>In case your computer shuts down or you lose internet connection, you can login again and continue from where you left off.</p>
                  </div>
         	    </div>
         	   
         </div>
   </div> <!--Content 2 end -->
  </div>
</div>

  <div class="row " style="padding-left: 0px;margin-top:20px">
      <button class="btn btn_action btn-back change_back_btn" onclick="fnGetStart();"><span class="back-spn"><s:text name="com.edupathstudent.tum.welcome.getstart"></s:text></span></button>
   </div> 
</div>   
<s:form name="WelcomeForm" id="WelcomeForm" method="post">
  <s:hidden name="actionNameValue" id="actionNameValue" value="PersonalAndAcademicInfoAction"></s:hidden>
</s:form>
 
 <script>
  function fnGetStart()
  {
	  form.action = '${pageContext.request.contextPath}/myapp/InterestAction';
	  form.submit();
  }
  
 </script>