<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<div class="row">
<div class="col-md-8">
</div>
<div class="col-md-4">
  <div  id="moveDiv" style="top: 75px;">
    <div class="notes_content" >
     <div  class="note_icon_close_div">
      <i class="fa fa-close  close-notes note_icon_close" ></i>
     </div>
     <div class="scrollbox_note" id="myNote">
        <p class="content_scroll_div add_new_content" contenteditable="true" style="outline: none; border: none;" onblur="fnSaveNotes();" >
        </p>
       </div>
    </div>
  </div>
</div>
</div>
<script>
var isStop = true;
$(document).ready(function()
{
	$('#moveDiv').draggable();
    
	$( "#moveDiv" ).resizable(); 
	$('.close-notes').click(function()
	{
	   $('#notesInfoDiv').hide();
	   noteToggle = true;
	});
	
	//$('.scrollbox').enscroll();
	$('#myNote').enscroll();

	$('.add_new_content').mouseenter(function() {
		if(isStop)
		{
			 $(this).attr('contenteditable',true);
	         $( "#moveDiv" ).draggable( "destroy" );
	         isStop = false;
		 }
	  })
	  .mouseleave(function() {
		  if(!isStop)
		  {
	         $( "#moveDiv" ).draggable( );
	         isStop = true;
		  }
	  });
});

function fnShowNotes()
{
	
	actionToggle = true;
	$('#notesInfoDiv').show();
	if(noteToggle)
	{
		fnChangeAction('get');
		noteToggle = false;
	}
	else
	{
		noteToggle = true;
	}	
}

function fnSaveNotes(action)
{
	actionToggle = false;
	$("#noteText").val($('.add_new_content').html());
	fnINITEvent();
	fnChangeAction('save');
}

function fnNotes(action)
{
	form = document.MenuItemForm;
	form.action = "${pageContext.request.contextPath}/myapp/" + action;
	var options = { 
	        beforeSubmit:  showNoteRequest,
	        success:       showNoteResponse
	    }; 
	$("#MenuItemForm").ajaxSubmit(options);
}

function showNoteRequest(formData, jqForm, options){
} 

function showNoteResponse(responseText, statusText, xhr, $form)
{
	if(actionToggle)
	{
		$("#noteText").val(responseText);
		$('.add_new_content').html(responseText);
	}	
}

function fnChangeAction(actionType)
{
   if('${session.UserSessionObject.roleWeight}' == 3)
   {
	   if(actionType == 'save')
	   {
		   fnNotes('insertFacilitatorNotesAction');
	   }
	   else
	   {
		   fnNotes('FacilitatorNotesAction');
	   } 
   }
   else
   {
	   if(actionType == 'save')
	   {
		   fnNotes('insertStudentNotesAction');
	   }
	   else
	   {
		   fnNotes('StudentNotesAction');
	   } 
   }	   
}
function fnINITEvent()
{
	if(!isStop)
	{
		$("#moveDiv" ).draggable();
        isStop = true;
	}
}

</script>
<style>

</style>
