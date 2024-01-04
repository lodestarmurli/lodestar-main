<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<!-- v3 calendar 
<link href="${pageContext.request.contextPath}/thirdparty/calendar/fullcalendar.min.css" rel='stylesheet' />
<link href="${pageContext.request.contextPath}/thirdparty/calendar/fullcalendar.print.min.css" rel='stylesheet' media='print' />
<script src="${pageContext.request.contextPath}/thirdparty/calendar/lib/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/calendar/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/thirdparty/calendar/fullcalendar.min.js"></script> -->

<!-- v4 calendar -->
<link
	href="${pageContext.request.contextPath}/thirdparty/calendar/packages/core/main.css"
	rel='stylesheet' />
<link
	href="${pageContext.request.contextPath}/thirdparty/calendar/packages/daygrid/main.css"
	rel='stylesheet' />
<link
	href="${pageContext.request.contextPath}/thirdparty/calendar/packages/timegrid/main.css"
	rel='stylesheet' />
<link
	href="${pageContext.request.contextPath}/thirdparty/calendar/packages/list/main.css"
	rel='stylesheet' />
<script
	src="${pageContext.request.contextPath}/thirdparty/calendar/packages/core/main.js"></script>
<script
	src="${pageContext.request.contextPath}/thirdparty/calendar/packages/interaction/main.js"></script>
<script
	src="${pageContext.request.contextPath}/thirdparty/calendar/packages/daygrid/main.js"></script>
<script
	src="${pageContext.request.contextPath}/thirdparty/calendar/packages/timegrid/main.js"></script>
<script
	src="${pageContext.request.contextPath}/thirdparty/calendar/packages/list/main.js"></script>


<s:form name="ReportForm" id="ReportForm" theme="simple">
	<s:hidden name="ReportstudentId" id="ReportstudentId"></s:hidden>
	<s:hidden name="cddReportGenerated" id="cddReportGenerated"></s:hidden>
</s:form>


<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#script-warning {
	display: none;
	background: #eee;
	border-bottom: 1px solid #ddd;
	padding: 0 10px;
	line-height: 40px;
	text-align: center;
	font-weight: bold;
	font-size: 12px;
	color: red;
}

#loading {
	display: none;
	position: absolute;
	top: 10px;
	right: 10px;
}

#calendar {
	max-width: 900px;
	margin: 40px auto;
	padding: 0 10px;
}
</style>


<div class="edupath-padding-summary">
	<div class="rows">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<s:text name="CDD Bulk Download"></s:text>
					</div>
				</div>

				<div class="portlet-body">

					<div class="row" style="padding: 10px;">
						<div class="col-md-12"
							style="display: flex; flex-direction: column">
							<div class="col-md-8"
								style="display: flex; justify-content: center; align-items: center; gap: 15px; margin-bottom: 20px">
								<!-- Remove the drop-down code -->
								<label for="ldidFrom" style="font-weight: 800; width: 40%">LDID
									From:</label> <input type="text" name="ldidFrom" id="ldidFrom"
									class="form-control input-sm" /> <label for="ldidTo"
									style="font-weight: 800; width: 40%">LDID To:</label> <input
									type="text" name="ldidTo" id="ldidTo"
									class="form-control input-sm" />

							</div>
							<div
								style="display: inline-block; padding-left: 10px; margin-top: 5px;">
								<!-- Add the "Download Report" button -->
								<button id="downloadButton" class="btn blue"
									onclick="fnDownloadCareerDegreeDiscoveryReport();">
									<s:text name="Download Report"></s:text>
								</button>
							</div>
						</div>

					</div>
					<br> <br>
					<div id='loading'>loading...</div>
					<div id='calendar'></div>

					<div id="viewFeedbackLoading" class="display-hide cust-loader">
						<div class="app-spinner">
							<!-- <i class="fa fa-spinner fa-pulse fa-spin fa-4x"></i> -->
							<img
								src="${pageContext.request.contextPath}/thirdparty/images/loader.gif">
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>


<script>
	function fnDownloadCareerDegreeDiscoveryReport() {
		var form = document.ReportForm;

		// Get LDID values
		var ldidFromValue = document.getElementById("ldidFrom").value;
		var ldidToValue = document.getElementById("ldidTo").value;

		// Log LDID values
		console.log("LDID From:", ldidFromValue);
		console.log("LDID To:", ldidToValue);

		// Check if LDID range is valid
		if (!isValidLDIDRange(ldidFromValue, ldidToValue)) {
			// Show error message
			alert("Invalid LDID range. Please enter a valid range.");

			// Disable the download button

			return;
		}


		// Set form action with LDID values
		form.action = "${pageContext.request.contextPath}/myapp/CDDBulkPDFReportDownload?ldidFrom="
				+ ldidFromValue + "&ldidTo=" + ldidToValue;

		// Submit the form
		form.submit();
	}

	// Function to check if LDID range is valid
	function isValidLDIDRange(ldidFrom, ldidTo) {
		// Extract numeric part of LDID
		var fromNumber = extractNumericPart(ldidFrom);
		var toNumber = extractNumericPart(ldidTo);

		// Check if LDID From is greater than LDID To
		return fromNumber <= toNumber;
	}

	// Function to extract numeric part from LDID
	function extractNumericPart(ldid) {
		// Match the numeric part using a regular expression
		var match = ldid.match(/\d+/);

		// Check if a match is found
		if (match) {
			// Convert the matched string to a number
			return parseInt(match[0], 10);
		}

		// If no match found, return NaN (invalid)
		return NaN;
	}

	
</script>