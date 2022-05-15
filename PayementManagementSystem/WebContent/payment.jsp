<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Payment Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/Payment.js"></script>
	</head>

	<body> 
		<div class="container"><div class="row"><div class="col-6"> 
		<h1>Payment Management </h1>
		
			<form id="formPayment" name="formPayment">
			
 				Payment Name : 
 				<input id="paymentName" name="paymentName" type="text" 
 				class="form-control form-control-sm"> <br>
			    
			    Payment Address : 
 				<input id="paymentAddress" name="paymentAddress" type="text" 
 				class="form-control form-control-sm"><br> 
 				
 				Payment Meter No : 
 				<input id="paymentMeterNo" name="paymentMeterNo" type="text" 
				class="form-control form-control-sm"><br>
 				
 				Payment Account No : 
 				<input id="paymentAccountNo" name="paymentAccountNo" type="text" 
 				class="form-control form-control-sm"><br> 
 				
 				Payment Amount : 
 				<input id="paymentAmount" name="paymentAmount" type="text" 
 				class="form-control form-control-sm"><br>
 				
 				<input id="btnSave" name="btnSave" type="button" value="Save" 
 				class="btn btn-primary">
 				<input type="hidden" id="hidpaymentCodeSave" 
				name="hidpaymentCodeSave" value="">
				
			</form>
			
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
		<div id="divPaymentGrid">
 		<%
 		Payment paymentObj = new Payment(); 
 		 		out.print(paymentObj.readPayment());
 		%>
	</div>
	
	</div> </div> </div> 
</body>
</html>
