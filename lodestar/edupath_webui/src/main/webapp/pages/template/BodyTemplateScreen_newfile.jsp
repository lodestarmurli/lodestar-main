<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="c.tld"%>
<html>
	<head>
		<title><s:text name="com.edupath.webtitle" /></title>
<link href="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/ApplicationStyles.css" rel="stylesheet"  type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/StudentInfo.css" rel="stylesheet"  type="text/css"/>
		  
<link href="${pageContext.request.contextPath}/styles/StudentTUM.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/styles/Resposive.css" rel="stylesheet" type="text/css"/>

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

</head>
<body style="margin:0;position:absolute; width:100%; height:100%;">
<div class="main_div">
  <div class="row">
    <div class="col-sm-2 col-md-2 col-xs-2 col-lg-2  hidden-xs">
        <tiles:insertAttribute name="sidebarContent"/>
    </div>
    <div class="col-sm-10 col-md-10 col-lg-10  col-xs-12 ">
     <div>
         <tiles:insertAttribute name="headerContent" />
     </div>
     
     <div class="row">
          <tiles:insertAttribute name="navigationBar" />
     </div>
     <div class="row scrollbox_for_body" id="my_scrolling_pane">
        <div class="" id="bodyDiv">
            <tiles:insertAttribute name="bodyContent"/>
         </div>
     </div>
    </div>
  </div>
  <div class="footer">
      <tiles:insertAttribute name="footer"/>
  </div>
</div>
	<!-- <div id="dvLoading"></div> -->
</body>

<script>
$(document).ready(function(){
  $("[aria-controls='sample_editable_1']").selectpicker();
  $('#my_scrolling_pane').enscroll({
	 verticalScrolling: true,
	 verticalTrackClass:'vertical-track_body',
	 verticalHandleClass:'vertical-handle_body'
	});
  $('#student_info_div').enscroll({
		 verticalScrolling: true,
		 verticalTrackClass:'vertical-track_body',
		 verticalHandleClass:'vertical-handle_body'
		});
});

 if(null != '${requestScope.chiledActionPath}' && '${requestScope.chiledActionPath}' != 0)
 {
	fnMenuClick('${requestScope.parentMenuItemId}', '${requestScope.menuItemId}', '${pageContext.request.contextPath}/myapp/${requestScope.chiledActionPath}?menuItemId=${requestScope.menuItemId}&clearSession=true');
 }
 else if(null != '${requestScope.parentActionPath}' && '${requestScope.parentActionPath}' != 0)
 {
	fnMenuClick('${requestScope.parentMenuItemId}', 0, '${pageContext.request.contextPath}/myapp/${requestScope.parentActionPath}?menuItemId=${requestScope.menuItemId}&clearSession=true');
 } 

</script>
</html>