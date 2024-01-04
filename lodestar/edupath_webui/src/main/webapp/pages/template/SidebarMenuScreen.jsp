<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<form action="#" name="submitForm" method="post">
<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 

<c:set var="height" value="82.5%"></c:set>
<div class="logo_div" id="logo_div">
 <img alt="LodeStar" class=" img-responsive" src="${pageContext.request.contextPath}/images/lodestar_logo.gif">
</div>
<div class="divider_div">
</div>
<div class="sidebar_div sidebarHodden" style="height: ${height};overflow-y:auto">
 <c:forEach items="${SideBarMenus}" var="sideBar" varStatus="loop">
 
 
 
 
 
 <c:choose>
 
 
    <c:when test="${IsStudent}">
     
     <c:choose>
     
     <c:when test="${SelectedHeaderId==11}">
     
     <c:choose>
     
     <c:when test="${IsSessionOneFeedBack}">
     
     <c:if test="${sideBar.key.id==12 or sideBar.key.id==48}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
     
     
     
     </c:if>
     </c:when>
      <c:otherwise>
     
      <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:if test="${sideBar.key.id != 48}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and  sideBarChild.refName eq 'Aptitude' and !session.UserSessionObject.programTestStudent}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
      </c:if>
      
    </c:if>
     
     
     
     
     
     
     </c:otherwise>
     </c:choose>
     
     </c:when>
      <c:when test="${SelectedHeaderId==16}">
     
     <c:choose>
     <c:when test="${IsSessiontwoFeedBack}">
     
     <c:if test="${sideBar.key.id==37 or sideBar.key.id==49}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
     
     
     
     </c:if>
     
     
     
     
     
     </c:when>
      <c:otherwise>
     
      <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:if test="${sideBar.key.id != 49}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
      </c:if>
      
    </c:if>
     
     
     
     
     
     
     </c:otherwise>
     
     </c:choose>
     </c:when>
     
     
     <c:when test="${SelectedHeaderId==4}">
     
     <c:choose>
     <c:when test="${Is12plus and IsIntresttestCompleted and IsApptitudetestCompleted}">
     
     <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude' }">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
     
     
     
     </c:if>
     
     
     
     
     
     </c:when>
      <c:otherwise>
     
      <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:if test="${sideBar.key.id != 50}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
      </c:if>
      
    </c:if>
     
     
     
     
     
     
     </c:otherwise>
     
     </c:choose>
     </c:when>
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     <c:otherwise>
    
    <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
    </c:if>
    
   
    </c:otherwise>
    
     
     
     
     
     
     
     
     
     
     </c:choose>
     
    </c:when>
    
    <c:otherwise>
    
    <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:choose>
          <c:when test="${empty sideBar.value}">
          
          <!-- Start Sasedeve Edited By Mrutyunjaya on Date 12-09-2017 -->
          
          
          <c:if test="${SelectedHeaderId == 22}">
          
			    <c:choose>
                    <c:when test="${IsSession1CompleteFaci}">
			    
			    			<c:if test="${sideBar.key.id!=52}">
                			
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
                
                
                			</c:if>
			    
			    
			    
			    
			    
			    
                     </c:when>
                     <c:otherwise>
                
                			<c:if test="${sideBar.key.id==52}">
                			
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
                
                
                			</c:if>
                
                
                      </c:otherwise>
                
                
                </c:choose>
                
                
              </c:if>  
               
               
               
                <c:if  test="${SelectedHeaderId == 27}">
                <c:choose>
                    <c:when test="${IsSession2CompleteFaci}">
			    
			               <c:if test="${sideBar.key.id!=53}">
                			
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
                
                
                			</c:if>
			    
			    
			    
			    
			    
			    
			    
                    </c:when>
                    <c:otherwise>
                
                
                           <c:if test="${sideBar.key.id==53}">
                			
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
                
                
                			</c:if>
                
                
                
                
                
                    </c:otherwise>
                
                
                </c:choose>
                
               </c:if> 
               
               
               
               
               <c:if test="${SelectedHeaderId != 22 and SelectedHeaderId != 27}">
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
               </c:if> 
               
               
               <!-- END Sasedeve Edited By Mrutyunjaya on Date 12-09-2017 -->
               
               
                
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div> 
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
    </c:if>
    
   
    </c:otherwise>
    
    </c:choose>
    
    
    
    
    
    
    
    
    
    
    
    
    
 </c:forEach>  
 <%-- <div class="sidebar_common">
      <span class="sidebar_spn_txt">System recommendation</span>
 </div> --%>

   
</div>






<div id="myModalsidebar" class="modal fade" role="dialog">
  <div class="modal-dialog">

        
      </div>
    <!-- Modal content-->
    <div class="modal-content" style="margin-top:50px">
    <div class="modal-header">
    <div class="row" style="">
        <button type="button" class="btn btn-default" onclick="hideModal()" style="font-size:10px;float:right;margin-right:15px">X</button>
        </div>
      <div class="sidebar_div1" style="background-color:#d2d6df;min-height:70%">
 <c:forEach items="${SideBarMenus}" var="sideBar" varStatus="loop">
 
 
 
 
 
 <c:choose>
 
 
    <c:when test="${IsStudent}">
     
     <c:choose>
     
     <c:when test="${SelectedHeaderId==11}">
     
     <c:choose>
     
     <c:when test="${IsSessionOneFeedBack}">
     
     <c:if test="${sideBar.key.id==12 or sideBar.key.id==48}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
     
     
     
     </c:if>
     </c:when>
      <c:otherwise>
     
      <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:if test="${sideBar.key.id != 48}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
      </c:if>
      
    </c:if>
     
     
     
     
     
     
     </c:otherwise>
     </c:choose>
     
     </c:when>
      <c:when test="${SelectedHeaderId==16}">
     
     <c:choose>
     <c:when test="${IsSessiontwoFeedBack}">
     
     <c:if test="${sideBar.key.id==37 or sideBar.key.id==49}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
     
     
     
     </c:if>
     
     
     
     
     
     </c:when>
      <c:otherwise>
     
      <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:if test="${sideBar.key.id != 49}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
      </c:if>
      
    </c:if>
     
     
     
     
     
     
     </c:otherwise>
     
     </c:choose>
     </c:when>
     
     
     <c:when test="${SelectedHeaderId==4}">
     
     <c:choose>
     <c:when test="${Is12plus and IsIntresttestCompleted and IsApptitudetestCompleted}">
     
     <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
     
     
     
     </c:if>
     
     
     
     
     
     </c:when>
      <c:otherwise>
     
      <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:if test="${sideBar.key.id != 50}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
      </c:if>
      
    </c:if>
     
     
     
     
     
     
     </c:otherwise>
     
     </c:choose>
     </c:when>
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     <c:otherwise>
    
    <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:choose>
         <c:when test="${empty sideBar.value}">
			    <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div>
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div>
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
    </c:if>
    
   
    </c:otherwise>
    
     
     
     
     
     
     
     
     
     
     </c:choose>
     
    </c:when>
    
    <c:otherwise>
    
    <c:if test="${sideBar.key.parentId == SelectedHeaderId or empty sideBar.key.parentId}">
      <c:choose>
          <c:when test="${empty sideBar.value}">
          
          <!-- Start Sasedeve Edited By Mrutyunjaya on Date 12-09-2017 -->
          
          
          <c:if test="${SelectedHeaderId == 22}">
          
			    <c:choose>
                    <c:when test="${IsSession1CompleteFaci}">
			    
			    			<c:if test="${sideBar.key.id!=52}">
                			
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
                
                
                			</c:if>
			    
			    
			    
			    
			    
			    
                     </c:when>
                     <c:otherwise>
                
                			<c:if test="${sideBar.key.id==52}">
                			
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
                
                
                			</c:if>
                
                
                      </c:otherwise>
                
                
                </c:choose>
                
                
              </c:if>  
               
               
               
                <c:if  test="${SelectedHeaderId == 27}">
                <c:choose>
                    <c:when test="${IsSession2CompleteFaci}">
			    
			               <c:if test="${sideBar.key.id!=53}">
                			
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
                
                
                			</c:if>
			    
			    
			    
			    
			    
			    
			    
                    </c:when>
                    <c:otherwise>
                
                
                           <c:if test="${sideBar.key.id==53}">
                			
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
                
                
                			</c:if>
                
                
                
                
                
                    </c:otherwise>
                
                
                </c:choose>
                
               </c:if> 
               
               
               
               
               <c:if test="${SelectedHeaderId != 22 and SelectedHeaderId != 27}">
                
                <s:set var= "displayName">${sideBar.key.displayName}</s:set>
				<div class="sidebar_common sidebar_normal" id="parent_${sideBar.key.id}" onclick="fnMenuClick('${sideBar.key.id}', 0, '${pageContext.request.contextPath}/myapp/${sideBar.key.actionPath}' );">
                <div id="left_arrow_${sideBar.key.id}"></div>
                <c:choose>
                	<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
                	  <img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
                	</c:when>
                	<c:otherwise>
                	  <i class="icon_spn"></i>   
                	</c:otherwise>
                </c:choose>
                <span class="sidebar_spn_txt"><s:text name="%{displayName}"></s:text></span>
                </div> 
                
               </c:if> 
               
               
               <!-- END Sasedeve Edited By Mrutyunjaya on Date 12-09-2017 -->
               
               
                
		  </c:when>
		 <c:otherwise>
			 <s:set var= "displayName">${sideBar.key.displayName}</s:set>
			  <div class="sidebar_common sidebar_normal" >
               <c:choose>
               		<c:when test="${null ne sideBar.key.iconPath and not empty sideBar.key.iconPath}">
               	  		<img  src="${pageContext.request.contextPath}/images/icons/${sideBar.key.iconPath}.gif" id="icon_img_${sideBar.key.id}" class="icon_spn" data-name="${sideBar.key.iconPath}">
               		</c:when>
               		<c:otherwise>
               		  <i class="icon_spn"></i>   
               		</c:otherwise>
               </c:choose>
                  <span class="sidebar_spn_txt  hasChild"><s:text name="%{displayName}"></s:text></span>
                  <div  class="child_menu_main">
                   <div id="left_arrow_${sideBar.key.id}"></div>
                  <c:forEach items="${sideBar.value}" var="sideBarChild" varStatus="cloop" >
	                  <c:choose>
	                  	<c:when test="${session.UserSessionObject.trial and !session.UserSessionObject.programTestStudent and sideBarChild.refName eq 'Aptitude'}">
	                  		<!-- do nothing  -->
	                  	</c:when>
	                  	<c:otherwise>
	                  		<div class="child_menu"  onclick="fnMenuClick('${sideBar.key.id}', '${sideBarChild.id}', '${pageContext.request.contextPath}/myapp/${sideBarChild.actionPath}' );">
		                       <span class="sidebar_child_spn_txt child_active" id="child_${sideBarChild.id}">
		                           <s:set var= "cdisplayName">${sideBarChild.displayName}</s:set>
							       <s:text name="%{cdisplayName}"></s:text>
							   </span>
		                    </div> 
	                  	</c:otherwise>
	                  </c:choose>
                  </c:forEach>
                 </div>
              </div>
		 </c:otherwise>
      </c:choose>
    </c:if>
    
   
    </c:otherwise>
    
    </c:choose>
    
    
    
    
    
    
    
    
    
    
    
    
    
 </c:forEach>  
 <%-- <div class="sidebar_common">
      <span class="sidebar_spn_txt">System recommendation</span>
 </div> --%>

   
</div>


      
    </div>

  </div>
</div>





</form>

<script>
var childMenuId = 0;
var parentMenuId = 0;
var hasChild = true;
$(document).ready(function() {  
	if('${session.UserSessionObject.roleWeight eq 3 && session.StudentSessionObject.id gt 0}' == 'true')
	{
		$('#my_scrolling_pane').height('75.5%');
		$('.sidebar_div').height('75.5%');
	}
	else if('${session.UserSessionObject.roleWeight}' == 1 || '${session.UserSessionObject.roleWeight}' == 4)
	{
		$('#my_scrolling_pane').height('87%');
		$('.sidebar_div').height('82.5%');
		$('.icon_spn').css('padding-left', '15px');
	}
	
	
      $('.hasChild').click(function()
	  {
    	  if(hasChild)
    	  {
    		 if(childMenuId > 0)
    	      {
    		 	 $('.sidebar_common').removeClass('sidebar_active');
        	 	 $(this).css('color','#fff');
        	  	 $(this).parent().addClass('sidebar_active_with_child');
        	     $(this).parent().removeClass('sidebar_normal');
        	     hasChild = false;
    	      }
    	  }
    	  else
    	  {
    		 // $(this).parent().addClass('sidebar_active_with_child'); 
    	  }	  
	      $(this).parent().find('.child_menu_main').slideToggle('2000',"swing", function () {
	  });
   });
	
	//childMenuId="${childSelectedSidebarMenuId}";
	//parentMenuId="${parentSelectedSidebarMenuId}";
    var imgName = $('#icon_img_'+parentMenuId).data('name');
    $('#icon_img_'+parentMenuId).attr("src", '${pageContext.request.contextPath}/images/icons/'+imgName+"-selected.gif");
	$("#parent_"+parentMenuId).addClass("sidebar_active"); 
	$("#left_arrow_"+parentMenuId).addClass("arrow-left_menu"); 
	
	$("#child_"+childMenuId).removeClass('child_active sidebar_txt_active');
	
	$('.child_active').removeClass('sidebar_child_spn_txt');
	$('.child_active').addClass('sidebar_txt_active');
	
	if(childMenuId > 0)
	{
		hasChild = false;
	}	
	$("#child_"+childMenuId).addClass('sidebar_child_active');
	$("#child_"+childMenuId).prepend('<i class="fa fa-circle" style="color:#0A9569"></i>');
	$("#child_"+childMenuId).parent().parent().parent().addClass('sidebar_active_with_child ');
	$("#child_"+childMenuId).parent().parent().parent().find('.hasChild').css('color','#fff');
	$("#child_"+childMenuId).parent().parent().parent().removeClass('sidebar_normal');
	$("#child_"+childMenuId).parent().parent().slideToggle('2000',"swing", function () {
});
	
});

function fnMenuClick(parentObjectId, childObjId, actionName)
{
	/* $("#"+childMenuId).attr("class", ""); 
	$("#"+parentMenuId).attr("class", "");  */
	
	$("#parentSelectedSidebarMenuId").val(parentObjectId);
	$("#childSelectedSidebarMenuId").val(childObjId);
	var form=document.submitForm;
	form.action=actionName;
	form.submit();  
}		
</script>
