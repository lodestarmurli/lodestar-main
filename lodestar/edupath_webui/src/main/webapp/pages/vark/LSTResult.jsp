<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<!-- BEGIN PAGE LEVEL PLUGINS-->
<link href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css" rel='stylesheet' /> 
<link href="https://cdn.datatables.net/buttons/1.6.0/css/buttons.dataTables.min.css" rel='stylesheet' />

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
  <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.0/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.0/js/buttons.flash.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script> 
 <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script> 
<script src="https://cdn.datatables.net/buttons/1.6.0/js/buttons.html5.min.js"></script> 
<script src="https://cdn.datatables.net/buttons/1.6.0/js/buttons.print.min.js"></script> 
<!-- END PAGE LEVEL PLUGINS -->


<s:set name="loginidLabel">
	<s:text name="com.edupath.college.report.loginId" />
</s:set>
<s:set name="nameLabel">
	<s:text name="com.edupath.college.report.name" />
</s:set>
<s:set name="classLabel">
	<s:text name="com.edupath.college.report.class" />
</s:set>
<s:set name="streamLabel">
	<s:text name="com.edupath.college.report.stream" />
</s:set>
<s:set name="career1Label">
	<s:text name="com.edupath.college.report.career1" />
</s:set>
<s:set name="career2Label">
	<s:text name="com.edupath.college.report.career2" />
</s:set>
<s:set name="ugALabel">
	<s:text name="com.edupath.college.report.ugA" />
</s:set>
<s:set name="ugBLabel">
	<s:text name="com.edupath.college.report.ugB" />
</s:set>
<s:set name="electiveLabel">
	<s:text name="com.edupath.college.report.elective" />
</s:set>
<s:set name="examLabel">
	<s:text name="com.edupath.college.report.exam" />
</s:set>
<style>
.adj {
	text-align:center;
}

</style>

<div class="edupath-padding-summary">
	<div class="portlet box blue exp">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-edit"></i>
				<s:text name="com.edupath.college.report.title" />
			</div>
		</div>

		<div class="portlet-body">

			<s:if test="hasActionMessages()">
				<div class="alert alert-success ">
					<button class="close" data-close="alert"></button>
					<s:actionmessage />
				</div>
			</s:if>
			<s:if test="hasActionErrors()">
				<div class="alert alert-danger ">
					<button class="close" data-close="alert"></button>
					<s:actionerror />
				</div>
			</s:if>
			<div class="table-toolbar">
				<div class="row">
					<div class="col-md-12 col-xs-12"
						style="margin-bottom: 7px; margin-left: 20px; width: 97%;">
						<label class="bold"><s:text
								name="com.edupath.college.report.college" /></label>&nbsp;:&nbsp;<select
							name="schoolidval" id="schoolidval" class="input-sm"
							value="${schoolidval}"
							style="width: 50%; font-size: 14px; font-weight: normal; color: #333333; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; -webkit-box-shadow: none; box-shadow: none; -webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;">
							<option value="-1">
								<s:text name="com.edupath.global.search.select.selected.title"></s:text>
							</option>
							<c:forEach items="${schoolList}" var="schoolDetails">
								<option value="${schoolDetails.id}">
									${schoolDetails.name}</option>
							</c:forEach>
						</select> &nbsp;&nbsp;<label class="bold"><s:text
								name="com.edupath.college.report.date" /></label>&nbsp;:&nbsp; <select
							name="sessionDateval" id="sessionDateval" class="input-sm"
							value="${sessionDateval}"
							style="width: 10%; font-size: 14px; font-weight: normal; color: #333333; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; -webkit-box-shadow: none; box-shadow: none; -webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;">
							<option value="-1">
								<s:text name="com.edupath.global.search.select.selected.title"></s:text>
							</option>
							<c:forEach items="${sessionYears}" var="years">
								<option value="${years}">
									${years}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;
						<div class="btn-group">
							<button id="sample_editable_1_new" class="btn green"
								onclick="fnSearchReportBySchool()">
								<s:text name="com.edupath.college.report.search" />
							</button>
						</div>
						<!-- &nbsp;&nbsp;&nbsp;&nbsp;
						<div class="btn-group" style="float: right;">
							<button id="sample_editable_2_new" class="btn green"
								onclick="fnExcelDownload()">
								<s:text name="com.edupath.college.report.download" />
							</button>
						</div> -->
					</div>
				</div>
			</div>

			<s:form name="facilitatorStudentSummaryForm" method="post">
				<s:hidden name="id" id="id"></s:hidden>
				<s:hidden name="name" id="name"></s:hidden>
				<s:hidden name="loginId" id="loginId"></s:hidden>
				<s:hidden name="userId" id="userId"></s:hidden>
				<s:hidden name="cityId" id="cityId"></s:hidden>
				<s:hidden name="fatheremailId" id="fatheremailId"></s:hidden>
				<table class="table table-striped table-hover table-bordered"
					id="sample_editable_1">
					<thead>
						<tr>
							<th>${pageScope.loginidLabel}</th>
							<th>${pageScope.nameLabel}</th>
							<th>${pageScope.classLabel}</th>
							<th>V's</th>
							<th>A's</th>
							<th>R's</th>
							<th>K's</th>
							 
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${varkReport}" var="varkReportList">
							<tr>
								<td class="adj">${utils:replaceXMLEntities(varkReportList.LDID)}</td>
								<td class="adj">${utils:replaceXMLEntities(varkReportList.name)}</td>
								<td class="adj">${utils:replaceXMLEntities(varkReportList.classLable)}</td>
								<td class="adj">${utils:replaceXMLEntities(varkReportList.visual)}</td>
								<td class="adj">${utils:replaceXMLEntities(varkReportList.aural)}</td>
								<td class="adj">${utils:replaceXMLEntities(varkReportList.readWrite)}</td>
								<td class="adj">${utils:replaceXMLEntities(varkReportList.kinesthetic)}</td>
								 
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</s:form>
		</div>
	</div>
</div>
<script type="text/javascript">
	var form = document.facilitatorStudentSummaryForm;
	$(document).ready(function() {
		$('#sample_editable_1').show();
		TableEdittableInit();

	});

	function fnSearchReportBySchool() {
		var schoolId = $('#schoolidval').val();
		var date = $("#sessionDateval").val();
		if ((schoolId == -1 || schoolId == -1) && (date == -1 || date == -1)) {
			alert("Please enter login id and date")
			return false;
		} else if (schoolId == -1 || schoolId == -1) {
			alert("Please enter login id")
			return false;
		} else if (date == -1 || date == -1) {
			alert("please select the date")
			return false;
		}

		var urlVal = "${pageContext.request.contextPath}/myapp/searchDataLSTResult?schoolId="+ schoolId + "&date=" + date;
		form.action = urlVal;
		form.submit();
	}

	function TableEdittableInit() {
		var table = $('#sample_editable_1');

		var oTable = table.dataTable({

			
			dom: 'Bftip',
			"lengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			buttons: [
	             'pageLength','excel'
	        ],
			// set the initial value
			"pageLength" : 5,

			"language" : {
				"lengthMenu" : " _MENU_ records"
			},
			"columnDefs" : [ { // set default column settings
				'orderable' : false,
				'targets' : []
			}, {
				"searchable" : false,
				"targets" : []
			} ],
			"order": [
                [0, "asc"]
            ] ,
			drawCallback : function() {
				$('[data-toggle="alertpopover"]').popover();
			}
		//"order" : [ [ 0, "asc" ] ],

		});
	}

	
</script>