<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTMl>
	<HEAD>
  	
<link href="${pageContext.request.contextPath}/thirdparty/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/thirdparty/select2/select2.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/thirdparty/datatables/plugins/bootstrap/dataTables.bootstrap.css" type="text/css" />
<link href="${pageContext.request.contextPath}/thirdparty/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/thirdparty/datatables/css/components.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/datatables/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css"/> 
<link href="${pageContext.request.contextPath}/thirdparty/chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/thirdparty/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/thirdparty/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>

<script src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/select2/select2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/thirdparty/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<link href="${pageContext.request.contextPath}/styles/Resposive.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/scripts/components-pickers.js"></script>
    
    <title><s:text name="com.edupath.webtitle" /></title>
    <script type="text/javascript">
    	var contextPath = "${pageContext.request.contextPath}";
    </script>
</HEAD>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<BODY id="main_body" style="overflow: auto;padding: 0px;">
       <tiles:insertAttribute name="bodyContent"/> 
</BODY>

<script>
$(window).load(function(){
	$('#dvLoading').hide();
	window.parent.$("#dvLoading").hide();
	
});
</script>
</HTML>