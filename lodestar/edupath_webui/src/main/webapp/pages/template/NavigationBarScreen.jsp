<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="changeClass" value="navigatin_div_OLD"></c:set>
<c:if test="${null ne HeaderMenus && not empty HeaderMenus}">
<c:set var="changeClass" value="navigatin_div"></c:set>
</c:if>

<c:if test="${session.StudentSessionObject.id > 0 || session.IsStudent}">
<div class="${changeClass}">
  <c:set value="false" var="header_menu"></c:set>
			<c:forEach items="${HeaderMenus}" var="menu">
				<c:if test="${null !=  menu.key.displayName and menu.key.displayName != ''}">
				<c:choose>
					<c:when test="${ActiveHeaderId eq menu.key.id }">
					<!--Active -->
						<c:set value="active_div" var="activeClass"></c:set>
						<c:set value="active_div" var="normalClass"></c:set>
						<c:set value="true" var="header_menu"></c:set>
						<c:set var="actionName">${menu.key.actionPath }</c:set>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${header_menu == true }">
							<!--InActive -->
								<c:set value="normal_arrow change_cursor" var="activeClass"></c:set>
						        <c:set value="normal_arrow change_cursor" var="normalClass"></c:set>
							</c:when>
							<c:otherwise>
							<!--Complited -->
							   <c:set value="" var="activeClass"></c:set>
						       <c:set value="complete_div" var="normalClass"></c:set>
						       <c:set var="actionName">${menu.key.actionPath }</c:set>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				<s:set var= "cdisplayName">${menu.key.displayName}</s:set>
				<c:set var="display"><s:text name="%{cdisplayName}"></s:text></c:set>
					<c:choose>
					  <c:when test="${fn:toUpperCase(display) eq 'SUMMARY' }">
					     <div class="col-sm-4 col-md-4 col-xs-4 col-lg-4 normal_arrow custom_navigatin_last ${normalClass}"  onclick="fnGoSession('${actionName}');">
                           <div class="navigatin_bar_last ${normalClass}">
      						 <span class="ngv_spn">${fn:toUpperCase(display)}</span>
                             <p class="sub_title_txt">${HeaderSubTitle[display]}</p>
                           </div>
                          </div>
					  </c:when>
					  <c:when test="${!menu.key.enableClick}">
					    <div class="custom_navigatin ${activeClass} arrow_box" >
                           <div class="navigatin_bar change_cursor ${normalClass}" >
                             <span class="ngv_spn">${fn:toUpperCase(display)}</span>
                              <p class="sub_title_txt">${HeaderSubTitle[display]}</p>
                             </div>
                           </div>
					  </c:when>
					  <c:otherwise>
					     <div class="custom_navigatin ${activeClass} arrow_box" onclick="fnGoSession('${actionName}');">
                           <div class="navigatin_bar ${normalClass}">
                             <span class="ngv_spn">${fn:toUpperCase(display)}</span>
                              <p class="sub_title_txt">${HeaderSubTitle[display]}</p>
                             </div>
                           </div>
					  </c:otherwise>
					</c:choose>
					<c:set value="" var="style"></c:set>
					<c:set var="actionName" value=""></c:set>
				</c:if>
			</c:forEach>

</div>
 </c:if>
 <s:form name="SessionForm" id="SessionForm" method="post" class="no-margin">
 </s:form>
 <script>
 $(document).ready(function (){
	 // Updating width of session headers based on number of headers
	var noOfHeaders = $('.navigatin_div').find('div').size() / 2;
	var divWidth = ($(".navigatin_div").outerWidth() / $('.navigatin_div').parent().outerWidth() * 100) - 3;
	$('.custom_navigatin, .custom_navigatin_last').animate({width : (divWidth / noOfHeaders+"%")});
 });
 var form = document.SessionForm;
 function fnGoSession(action)
 {
	 if(action != '' && action != undefined)
	 {
		form.action = "${pageContext.request.contextPath}/myapp/" + action;
		form.submit();
	 }	 
 }
 </script>
 <style>
 .change_cursor
 {
   cursor: default !important; 
 }
</style>

