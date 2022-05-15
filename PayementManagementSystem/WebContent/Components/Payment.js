$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();

// Form validation-------------------
var status = validatePaymentForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
     }
 
// If valid------------------------
var type = ($("#hidpaymentCodeSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "PaymentAPI",
 type : type,
 data : $("#formPayment").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onPaymentSaveComplete(response.responseText, status);
 }
 });
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidpaymentCodeSave").val($(this).data("paymentid"));
 $("#paymentName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#paymentAddress").val($(this).closest("tr").find('td:eq(1)').text());
 $("#paymentMeterNo").val($(this).closest("tr").find('td:eq(2)').text());
 $("#paymentAccountNo").val($(this).closest("tr").find('td:eq(3)').text());
 $("#paymentAmount").val($(this).closest("tr").find('td:eq(4)').text());
});

//DELETE==========================================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "PaymentAPI",
 type : "DELETE",
 data : "paymentCode=" + $(this).data("paymentid"),
 dataType : "text",
 complete : function(response, status)
 {
 onPaymentDeleteComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validatePaymentForm()
{
	
//Payment_name
if ($("#paymentName").val().trim() == "")
 {
 return "Insert Payment Name.";
 }

// Payment_address
if ($("#paymentAddress").val().trim() == "")
 {
 return "Insert Payment Address.";
 } 

// Payment_Meter_No-------------------------------
if ($("#paymentMeterNo").val().trim() == "")
 {
 return "Insert Payment Meter No.";
 }

// Payment_Account_No-------------------------------
if ($("#paymentAccountNo").val().trim() == "")
 {
 return "Insert Payment Account No.";
 }
  
 // Payment_Amount-------------------------------
if ($("#paymentAmount").val().trim() == "")
 {
 return "Insert Payment Amount.";
 }
 
return true;
}

function onPaymentSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
$("#hidpaymentCodeSave").val("");
 $("#formPayment")[0].reset();
}

function onPaymentDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}
