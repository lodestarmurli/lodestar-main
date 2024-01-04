<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<link href="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" type="text/css"/> 
<link href="${pageContext.request.contextPath}/styles/BulkUploadStyles.css" rel="stylesheet" type="text/css"/> 
<script src="${pageContext.request.contextPath}/thirdparty/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<div class="rows">

	<div class="col-md-12">
	
	
	<div align="center" style="margin-top:30px;">
	<a href="#" onclick="fnShowReadingPage();"><h3><b>Click here to explore the WORLD OF JOBS</b></h3></a>
	<img alt="LodeStar" class="img-responsive worldofjobs" src="${pageContext.request.contextPath}/images/world_of_jobs.jpg"> <!--22/03/18class added-->
	
	</div>
		</div>
	</div>


	<script>
	function fnShowReadingPage()
	{
		if(newWindow!=null)
        {
	         if(!newWindow.closed)
	         {
	        	 newWindow.close();
	         }
        }
		
		newWindow = window.open("${pageContext.request.contextPath}/pages/worldofjobs/ReadingPageWorldofjobs.jsp","ReadingPane"
				,"height=500px, width=1100px, left=0, top=0, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no",true);
		newWindow.focus();
	}
	</script>