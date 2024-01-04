<%-- 
	@(#) Index.jsp    
	
    Licensed Materials - Property of JaMocha Tech
    (c) Copyright JaMochaTech Private Limited 2009, 2015. ALL RIGHTS RESERVED
 
    #731, 2nd Floor, 3rd Block, Koramangala, Bengaluru-560034, India 
 
    This software is the confidential and proprietary information of
    JaMocha Tech Private Limited ("Confidential Information").
    You shall not disclose such 'Confidential Information' and shall
    use it only in accordance with the terms of the license agreement
    you entered into with JaMocha Tech Private Limited.
	
 	Code Change Control:
    Date                     Developer                           Remarks
    ----------               ------------------                  -------------------
    dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 	
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<HTML>
 <HEAD>
  <TITLE><s:text name="com.edupath.webtitle" /></TITLE>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/ApplicationStyles.css" type="text/css">
</HEAD>
<BODY>
  <TABLE width="100%" height="100%" cellpadding="0" cellspacing="0" border="0">
	<TR>
		<TD width="100%" height="100%">
			<iframe id="indexFrame" name="indexFrame" style="overflow:hidden;overflow-x:hidden;overflow-y:hidden;height:100%;width:100%;position:absolute;top:0px;left:0px;right:0px;bottom:0px;" height="100%" width="100%" height="100%" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" src="<%=request.getContextPath()%>/pages/LoginScreen.jsp"></iframe>
		</TD>
	</TR>
  </TABLE>
 </BODY>
</HTML>