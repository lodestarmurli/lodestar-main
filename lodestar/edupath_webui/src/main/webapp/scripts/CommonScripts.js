//Method to check if given field is empty. If empty return true else false and display alert message
function fnIsEmpty(fieldVal, fieldName, requiredMessage)
{
	fieldValue = fnJavaScriptTrim(fieldVal);
	if (fieldValue == "")
	{
		if (fieldName)
		{
			alert(fieldName + " " + requiredMessage);
		}
		return true;
	}
	return false;
}

//Method to check if entered field object length is greater than field length. If greater return true else false   
function fnIsLength(fieldVal, fieldName, fieldLength)
{
	fieldValue = fnJavaScriptTrim(fieldVal);
	if (fieldValue.length > fieldLength)
	{
		return true;
	}
	return false;
}

//Method to check if entered field value has spaces. If space is present then removing from start and end points of the field value  
function fnJavaScriptTrim(param)
{
	//Right trim
	var trimvalue = param;
	len = trimvalue.length;
	var2 = trimvalue.substr(len-1,1);
	var regWhite = /\s/;
	
	while (regWhite.test(var2))
	{
		trimvalue = trimvalue.slice(0,len-1);
		len = trimvalue.length;
		var2 = trimvalue.substr(len-1,1);
	}	
	//Left trim
	var2 = trimvalue.substr(0,1);
	while (regWhite.test(var2))
	{
		trimvalue = trimvalue.slice(1,len);
		var2 = trimvalue.substr(0,1);
	}	
	return trimvalue;
}

//Method to load children for selected parent and displaying child as selected if it has been already selected
function fnLoadChildren(form, lstParentObj, lstChildObj, varXMLData, selectedChildId, length)
{
    var regEx = /\+/g;
    varXMLData = varXMLData.replace(regEx, " ");
    varXMLData = unescape(varXMLData);
    var xmlObj = fnGetXMLDocumentObject(unescape(varXMLData));
    if (length == undefined)
        lstChildObj.length = 1;
    else      
       lstChildObj.length = length;
    if (lstParentObj.value == -1)
                return;
    var parentRecords = xmlObj.getElementsByTagName("parentId" + lstParentObj.value)        
    for (i = 0 ; i < parentRecords.length ; i++)
    {
        var childRecords = parentRecords.item(i).childNodes; 
        for (j = 0; j < childRecords.length ; j++)
        {
            var childId = childRecords.item(j).getAttribute("id");
            var childName = childRecords.item(j).getAttribute("name");
            lstChildObj.options.length = lstChildObj.options.length+1;
            lstChildObj.options[lstChildObj.options.length-1].value = childId;
            lstChildObj.options[lstChildObj.options.length-1].text = childName;
            if (selectedChildId == childId)
            {
                lstChildObj.options[lstChildObj.options.length-1].selected = true;
            }
        }
    }
}

//Method to load all children for selected parents and displaying children as selected if it has been already selected
function fnLoadAllChildren(form, lstParentObj, lstChildObj, varXMLData, selectedChildArray, length)
{
	if (lstChildObj.disabled || lstParentObj.disabled)
		return;
    var regEx = /\+/g;
    varXMLData = varXMLData.replace(regEx, " ");
    varXMLData = unescape(varXMLData);
    var xmlObj = fnGetXMLDocumentObject(unescape(varXMLData));
    if (length == undefined)
        lstChildObj.length = 1;
    else      
       lstChildObj.length = length;
    if (lstParentObj.value == -1)
                return;
    for (p = 0 ; p < lstParentObj.options.length; p++)
    {
    	if (lstParentObj.options[p].selected)
		{
		    var parentRecords = xmlObj.getElementsByTagName("parentId" + lstParentObj.options[p].value)        
		    for (i = 0 ; i < parentRecords.length ; i++)
		    {
		        var childRecords = parentRecords.item(i).childNodes; 
		        for (j = 0; j < childRecords.length ; j++)
		        {
		            var childId = childRecords.item(j).getAttribute("id");
		            var childName = childRecords.item(j).getAttribute("name");
		            lstChildObj.options.length = lstChildObj.options.length+1;
		            lstChildObj.options[lstChildObj.options.length-1].value = childId;
		            lstChildObj.options[lstChildObj.options.length-1].text = childName;
		            for (c = 0 ; c < selectedChildArray.length; c++)
		            {
			            if (selectedChildArray[c] == childId)
			            {
			                lstChildObj.options[lstChildObj.options.length-1].selected = true;
			            }
		            }
		        }
		    }
		}
    }
}

//Method to get XML Document Object for specific browser
function fnGetXMLDocumentObject(varXMLData)
{
    var xmlObj;
    if (jQuery.browser.msie)
    {
        xmlObj = new ActiveXObject("Microsoft.XMLDOM");
        xmlObj.loadXML(varXMLData);
    }
    else if (document.implementation && document.implementation.createDocument) //For NS
    {
        xmlObj = document.implementation.createDocument("", "", null);
        Document.prototype.loadXML = new function() 
        {
            if (varXMLData != "")
            {
                var DOMDocument = new DOMParser().parseFromString(varXMLData, "text/xml"); 
                xmlObj = DOMDocument;
            }
            else
            {
                xmlObj = document.implementation.createDocument("", "", null);
            }
        }          
    }
    return xmlObj;
}

//Method to get XML as String for for specific browser
function fnGetXMLString(domObject)
{
    var xmlData;
    if (jQuery.browser.msie)
    {
        xmlData = domObject.xml;
    }
    else if (document.implementation && document.implementation.createDocument) //For NS
    {
        var serializer = new XMLSerializer();
        xmlData = serializer.serializeToString(domObject);
    }
    return xmlData;
}

//Method to validate multiple check box whether any thing is selected or not
function fnValidateMultipleSelect(obj) 
{
	var numOptions = obj.options.length;
    lastSelected = -1;
	for (loop = numOptions - 1; loop >= 0; loop--) 
	{
		if (obj.options[loop].selected) 
	    {
			lastSelected = loop;
	        break;
	    }
	}   
    if (lastSelected > -1)
    {
      return true;
    }
    else
    {
      return false;
    }                
}   

//Method to get XMLHttpObject for Ajax    
function fnGetXmlHttpObject()
{ 
	try 
    { 
         return new ActiveXObject("Msxml2.XMLHTTP"); 
    } 
    catch (e)
    {}
    try 
    { 
         return new ActiveXObject("Microsoft.XMLHTTP"); 
    } 
    catch (e) 
    {}
    try 
    { 
         return new XMLHttpRequest(); 
    }
    catch(e) 
    {}
    alert("XMLHttpRequest not supported");
    return null;
}    

//Method to replace '+' symbol with empty when url is encoded
function fnEscapeValues(varData)
{
   var regEx = /\+/g;
   varData = varData.replace(regEx, " ");
   varData = unescape(varData)
   return varData;
}

//Method to replace special characters with empty except (a-z, A-Z and 0-9) characters
function fnReplaceSpecialEntities(value)
{
	var temp = new String(value);
	temp.replace(/[^a-zA-Z 0-9]+/g,'');
	return temp;
}

//Method to replace xml characters : (&(ampersand) = "&amp;" , <(less than) = "&lt;" , >(greater than) = "&gt;" , "(double quote) = "&quot;" , '(Single quote) = "&#039;"
function fnReplaceXMLEntities(value)
{
	 regEx = /\&/g;
	 value = value.replace(regEx, "&amp;");
	 regEx = /\</g;
	 value = value.replace(regEx, "&lt;");
	 regEx = /\>/g;
	 value = value.replace(regEx, "&gt;");
	 regEx = /\"/g;
	 value = value.replace(regEx, "&quot;");
	 regEx = /\'/g;
	 value = value.replace(regEx, "&#039;");
	 return value;
}

/*Method to replace xml characters : (&(ampersand) = "&amp;" , <(less than) = "&lt;" , >(greater than) = "&gt;" , "(double quote) = "&quot;" , '(Single quote) = "&#039;" 
 * and a new line
 */
function fnReplaceXMLEntitiesAndNewLine(value)
{
	 regEx = /\&/g;
	 value = value.replace(regEx, "&amp;");
	 regEx = /\</g;
	 value = value.replace(regEx, "&lt;");
	 regEx = /\>/g;
	 value = value.replace(regEx, "&gt;");
	 regEx = /\"/g;
	 value = value.replace(regEx, "&quot;");
	 regEx = /\'/g;
	 value = value.replace(regEx, "&#039;");
	 regEx = /\r\n/g;
	 value = value.replace(regEx, "");
	 return value;
}

//Method to replace back xml characters : (&(ampersand) = "&amp;" , <(less than) = "&lt;" , >(greater than) = "&gt;" , "(double quote) = "&quot;" , '(Single quote) = "&#039;" 
function fnReplaceBackXMLEntities(value)
{
	 regEx = /\&amp;/g;
	 value = value.replace(regEx, "&");
	 regEx = /\&lt;/g;
	 value = value.replace(regEx, "<");
	 regEx = /\&gt;/g;
	 value = value.replace(regEx, ">");
	 regEx = /\&quot;/g;
	 value = value.replace(regEx, "\"");
	 regEx = /\&#039;/g;
	 value = value.replace(regEx, "'");
	 return value;
}

//Method to check if options is available or not
function fnHasOptions(obj) 
{
	if (obj != null && obj.options != null) 
	{ 
		return true;
	}
	return false;
}

//Method to sort selected values in alphabetical order
function fnSortSelect(obj) 
{
	var o = new Array();
	if (!fnHasOptions(obj)) 
	{ 
		return;
	}
	for (var i = 0; i < obj.options.length; i++) 
	{
		o[o.length] = new Option( obj.options[i].text, obj.options[i].value, obj.options[i].defaultSelected, obj.options[i].selected) ;
	}
	if (o.length == 0) 
	{ 
		return; 
	}
	o = o.sort( 
		function(a, b) 
		{ 
			if ((a.text.toLowerCase() + "") < (b.text.toLowerCase() + "")) 
			{ 
				return -1; 
			}
			if ((a.text.toLowerCase() + "") > (b.text.toLowerCase() + "")) 
			{ 
				return 1; 
			}
			return 0;
		} 
	);

	for (var i = 0; i < o.length; i++)
	{
		obj.options[i] = new Option(o[i].text, o[i].value, o[i].defaultSelected, o[i].selected);
	}
}

//Method to check if the file attachment is valid or not. If it is not valid display alert message
function fnIsValidAttachment(fileObj, message)
{
	var regAttachments = /^[A-Za-z\\//]+([a-zA-Z0-9 -_=+~`!@#$%^&*\(\)\{\}\[\]\"\':;<>?//.,|\\])*$/;
	if(!regAttachments.test(fnJavaScriptTrim(fileObj.value)))
	{
		alert(message);
		return false;
	}
	return true;
}

//Method to convert xml to string for specific browser
function fnXmlToString(xmlObj) 
{
	if (document.implementation && document.implementation.createDocument)
    {
       return (new XMLSerializer()).serializeToString(xmlObj);
    }
	else if(jQuery.browser.msie)
    {
        return xmlObj.xml;
    }
}

//Method to set IframeSize based on width and height offset
function fnSetIframeSize(elementId, heightOffset, widthOffset) {
    var viewportwidth;
    var viewportheight;
    // For mozilla/netscape/opera/IE7 browsers
    if (typeof window.innerWidth != 'undefined')
    {
         viewportwidth = window.innerWidth,
         viewportheight = window.innerHeight
    }
    // For IE6
    else if (typeof document.documentElement != 'undefined'
        && typeof document.documentElement.clientWidth !=
        'undefined' && document.documentElement.clientWidth != 0)
    {
          viewportwidth = document.documentElement.clientWidth,
          viewportheight = document.documentElement.clientHeight
    }
    // For older versions of IE
    else
    {
          viewportwidth = document.getElementsByTagName('body')[0].clientWidth,
          viewportheight = document.getElementsByTagName('body')[0].clientHeight
    }
    var el = document.getElementById(elementId);
    el.style.height = (viewportheight - heightOffset) + "px";
    el.style.width = (viewportwidth - widthOffset) + "px"; 
}

function fnVerifyCheckAll(lstBoxObj, chkObj)
{
	isCheckAll = false;
	totalItemSize = lstBoxObj.length;
	selectedItemSize = 0;
	for (c = 0 ; c < lstBoxObj.length; c++)
	{
		if(lstBoxObj[c].selected)
		{
			selectedItemSize++;
		}
	}
	if (selectedItemSize == totalItemSize && totalItemSize > 0)
	{
		chkObj.checked = true;
	}
	else
	{
		chkObj.checked = false;
	}
}

function fnSetCookie(c_name, value, expiresInMin)
{
	var exdate = new Date();
	expiresInMin = expiresInMin * 1000 * 60;
	var expires_date = new Date( exdate.getTime() + expiresInMin);
	document.cookie = c_name+ "=" + escape(value) + ((expiresInMin==null) ? "" : ";expires=" + expires_date.toGMTString());
}

//Method to move selected values from left t0 right or right to left bucket
function fnMoveSelectedOptions(from, to) 
{  
	// Move them over
	if (!fnHasOptions(from))
	{ 
		return;
	}
	var grpExists = 0;
	var errMsg = 0;
	for (var i = 0; i < from.options.length; i++) 
	{
		var o = from.options[i];
		if (o.selected) 
		{
			if (!fnHasOptions(to)) 
			{ 
				var index = 0; 
			} 
			else 
			{ 
				var index = to.options.length; 
			  
			}
			
			to.options[index] = new Option( o.text, o.value, false, false);
		}
	 }
	// Delete them from original
	for (var i = (from.options.length-1); i >= 0; i--) 
	{
		var o = from.options[i];
		if (o.selected) 
		{
			from.options[i] = null;
		}
	}
	if ((arguments.length < 3) || (arguments[2] == true)) 
	{
		//fnSortSelect(from);
		//fnSortSelect(to);
	}
	from.selectedIndex = -1;
	to.selectedIndex = -1;
}

//Method to reorder the select list
function fnReorderList(list,index,to)
{
	var total = list.options.length-1;
	if(index == -1) 
	   return false;
	if (to == +1 && index == total) 
	   return false;
	if (to == -1 && index == 0) 
	   return false;
	   
	var items = new Array;
	var values = new Array;
	for (i = total; i >= 0; i--)
	{
	   items[i] = list.options[i].text;
	   values[i] = list.options[i].value;
	}

	for (i = total; i >= 0; i--)
	{
	   if (index == i)
	   {
	     list.options[i + to] = new Option(items[i],values[i], 0, 1);
	     list.options[i] = new Option(items[i + to], values[i + to]);
	     i--;
	   }
	   else
	   {
	     list.options[i] = new Option(items[i], values[i]);
	   }
	}
   list.focus();
}

function isInteger(str)
{
    var objRegExp = /(^-?\d\d*$)/;
    return objRegExp.test(str);
}

function isDouble(str)
{
    var objRegExp = /(^\d\d*(\.\d\d*)?$)/;
    return objRegExp.test(str);
}

function isInt(str)
{
	var objRegExp = /(^ *[0-9]+ *$)/;
    return objRegExp.test(str);
}

function isFloat(value)
{
	var objRegExp = /(^\d+\.\d{0,2}$)/;
    return objRegExp.test(value);
}

function isEmailIdValid(email)
{
   var reg = /(^([A-Za-z0-9_\-\.\])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$)/;
   if(reg.test(email) == false)
   {
      return false;
   }
   return true;
}

function isMobileNoValid(value)
{
	var reg = /(^(\+)?\d{10,14}$)/;
	return reg.test(value);
}

function isZipCodeValid(value)
{
	var reg = /(^\d{6}$)/;
	return reg.test(value);
}

function isTelephoneValid(value)
{
	var reg = /(^\d{11}$)/;
	return reg.test(value);
}

function isFaxNumberValid(value)
{
	var reg = /([\+? *[1-9]+]?[0-9 ]$)/;
	return reg.test(value);
}

function setCookie(c_name, value, expiresInMin)
{
	var exdate = new Date();
	expiresInMin = expiresInMin * 1000 * 60;
	var expires_date = new Date( exdate.getTime() + expiresInMin);
	document.cookie = c_name+ "=" + escape(value) + ((expiresInMin==null) ? "" : ",expires=" + expires_date.toGMTString());
}

function getCookie(c_name)
{
	if (document.cookie.length > 0)
	{
		c_start = document.cookie.indexOf(c_name + "=");
		if (c_start != -1)
		{
			c_start = c_start + c_name.length+1;
			c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1) c_end = document.cookie.length;
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
	return "";
}

function fnApplyAltColor(tableId)
{
	rowcount = 0;
	$("#"+tableId+" > tbody").children(":visible").each(
		function()
		{
			rowcount++;
			newClass = (rowcount % 2 == 0) ? 'tabletdeven' : 'tabletdodd';
			oldClass = "tabletdeven";
			if(newClass == "tabletdeven")
				oldClass = 'tabletdodd';
			$(this).children().removeClass(oldClass).addClass(newClass);
		});
}