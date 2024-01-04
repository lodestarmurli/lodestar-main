<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

 <div class="modal fade" id="MyCartId" role="dialog">
    <div class="modal-dialog" style="padding: 0px !important; margin: 0px !important; width: 88% !important; height: 97% !important;">
      <div class="modal-content" style="width: 99% !important; height: 97% !important; margin-top: 10px !important; margin-bottom: 10px !important; ">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h5 class="modal-title" style="color: #63A3D1"><s:text name="com.edupath.mycart.label"></s:text></h5>
        </div>
        <div class="modal-body scrollbox_for_body"  style="border-top:2px solid #1E5177; padding-left: 25px; overflow-y: hidden; overflow-x: hidden;" id="myCart">
         <%--  <jsp:include page="/pages/studentcart/StudentCartDetailScreen.jsp"></jsp:include> --%>
        </div>
      </div>
    </div>
  </div>
  <script>
  $(document).ready(function(){
	   
	  $('#myCart').enscroll({
		 verticalScrolling: true,
		 verticalTrackClass:'vertical-track_body',
		 verticalHandleClass:'vertical-handle_body'
		});
	});
  </script>