
<style type="text/css">
#errorHeader{
border-bottom: 2px solid #1E91CE;
}
#errorBody{
	padding: 10px;
	font-family: Verdana;
	font-size: 9px;
}
</style>
<body onload="fnSetTimeOut();" style="padding: 0; margin: 0;" id="invaliderrorPage">
 <form id="invaliderrorForm">
 </form>
	<div id="errorHeader">
		<table width="100%">
			<tr>
				<td width="30%">
					<img id="ftlogo" />
				</td>
				<td width="70%">
					&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<div id="errorBody">
		<form action="InvalidSessionRedirect" name="invalidSession" method="post">
			<h5 style="color: red; text-align: center;">Your session is invalid, please login again.</h5>
			<h5 style="color: #000; text-align: center;">Please wait while you are being redirected to login page.</h5>
			<div style="text-align: center;"><img src="${pageContext.request.contextPath}/images/loader.gif"></div>
		</form>
	</div>
	<div id="errorFotter">
	</div>
</body>
<script type="text/javascript">
var base ="";
function fnSetImage()
{
	base = document.URL;
	base = base.substr(0, base.indexOf("/", base.indexOf("/", base.indexOf("//") + 2) + 1));
	if(!parent.window.document.MenuItemForm)
	{
		document.getElementById("ftlogo").src = base + "/images/FT_logo.png";
		document.getElementById("errorHeader").style.display = "";
	}
	else
	{
		document.getElementById("errorHeader").style.display = "none";
	}
	
	document.getElementById("progressImg").src= base + "/images/loader.gif";
}
function fnSetTimeOut()
{
	
	setTimeout(function()
	{
		if(parent.window.document.MenuItemForm)
		{
			form = parent.window.document.MenuItemForm;
		}
		else
		{
			form = document.invalidSession;
		}
		form.action = "${pageContext.request.contextPath}/InvalidSessionRedirect";
		form.submit();
	},3000);
}

</script>
