<%@ taglib prefix="s" uri="/struts-tags"%>
<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" style="padding-top: 15px;">
	<TR id="messageContent">
		<td height="25px">
			<s:actionmessage cssClass="messagesuccess"/>
        </td>
		<td align="left" height="25px" style="padding-left: 0px;">
			<s:actionerror cssClass="messagefailure" cssStyle="margin: 0px;padding: 0px;"/>
        </td>
        <td height="25px">
			<s:actionmessage cssClass="messagesuccess"/>
        </td>
	</TR>
</TABLE>
<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" >
	<TR>
        <TD align="center" valign="top" style="padding-top: 0px;border : 1px solid #C3C3C3;-moz-border-radius: 5px;-webkit-border-radius: 5px;z-index: -1; color:red;" colspan="2" >
			<h3>! Error From Action</h3>        
        </TD>
	</TR>
</TABLE>