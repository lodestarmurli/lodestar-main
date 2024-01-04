<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="row">
	<div class="col-md-12">
		<div class="occhoverreturnlabel">
			<a href="#" onclick="fnHideOccupation()">Return</a>
		</div>
	</div>
</div>

<div class="rows">
	<div class="col-md-7">
		<div>
			<span class="occhoverindusname">
				${occupationDetails.industryName}
			</span>
			<span class="occhoveroccname">
				${occupationDetails.name}:
			</span>
		</div>
		<div class="occhoverdesc">
			${occupationDetails.description}
		</div>
	</div>
	<div class="col-md-5">
		<img src="data:image/png;base64,${occupationDetails.base64img}" class="img-responsive">
	</div>

</div>    
    
    
    