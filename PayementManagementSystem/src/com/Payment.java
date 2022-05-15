package com;
import java.sql.*;

public class Payment {
	
	public Connection connect()
	{
			Connection con = null;
			try
			{
			
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/payment", "root", "");
			
				//For testing
				System.out.print("Successfully connected");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return con;
	}
	
//Insert	
public String insertPayment(String paymentName	, String paymentAddress, String paymentMeterNo, String paymentAccountNo, String paymentAmount)
		{
			String output = "";
			try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into payments(`paymentCode`,`paymentName`,`paymentAddress`,`paymentMeterNo`,`paymentAccountNo`, `paymentAmount`)"
					 + " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, paymentName); 
			 preparedStmt.setString(3, paymentAddress); 
			 preparedStmt.setString(4, paymentMeterNo); 
			 preparedStmt.setString(5, paymentAccountNo); 
			 preparedStmt.setDouble(6, Double.parseDouble(paymentAmount)); 


			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayment + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Payment deatils.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}

//Read
public String readPayment()
{
		String output = "";
		try
		{
				Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading.";
					}

					//Prepare the HTML table to be displayed
					output = "<table border='3'>"
							+ "<tr><th>Payment Name</th>"
							+"<th>Payment Address</th>"
							+ "<th>Payment Meter Number</th>"
							+ "<th>Payment Account Number</th>"
							+ "<th>Payment Amount</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from payments";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next())
					{
						 String paymentCode = Integer.toString(rs.getInt("paymentCode")); 
						 String paymentName = rs.getString("paymentName"); 
						 String paymentAddress = rs.getString("paymentAddress"); 
						 String paymentMeterNo = rs.getString("paymentMeterNo"); 
						 String paymentAccountNo = rs.getString("paymentAccountNo"); 
						 String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));



						// Add a row into the html table
						output += "<tr><td><input id='hidPaymentIDUpdate'name='hidPaymentIDUpdate'type='hidden' value='" + paymentCode  + "'>"+ paymentName  + "</td>";
						output += "<td>" + paymentAddress + "</td>";
						output += "<td>" + paymentMeterNo + "</td>";
						output += "<td>" + paymentAccountNo + "</td>";
						output += "<td>" + paymentAmount + "</td>";


						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-paymentid='" + paymentCode + "'></td>"
								+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-paymentid='" + paymentCode + "'></td></tr>"; 
					}
					con.close();


					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the payment details.";
				System.err.println(e.getMessage());
			}
			return output;
		}


//Delete
public String deletePayment(String paymentCode)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}


			// create a prepared statement
			String query = "delete from payments where paymentCode =?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentCode));

			// execute the statement
			preparedStmt.execute();
			con.close();

			//output = "Deleted successfully";
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayment + "\"}";
			}
		catch (Exception e)
		{
			//output = "Error while deleting the Payment.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the payments.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

//method to update payment details in DB
	public String updatePayments(String paymentCode, String paymentName, String paymentAddress, String paymentMeterNo, String paymentAccountNo, String paymentAmount)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
				
			// create a prepared statement
			String query = "UPDATE payments SET paymentName=?,paymentAddress=?,paymentMeterNo=?,paymentAccountNo=?,paymentAmount=?  WHERE paymentCode=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			 preparedStmt.setString(1, paymentName); 
			 preparedStmt.setString(2, paymentAddress); 
			 preparedStmt.setString(3, paymentMeterNo); 
			 preparedStmt.setString(4, paymentAccountNo); 
			 preparedStmt.setDouble(5,Double.parseDouble(paymentAmount));
			 preparedStmt.setInt(6, Integer.parseInt(paymentCode));

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated Payment details successfully";
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayment + "\"}"; }
		catch (Exception e)
		{
			//output = "Error while updating the Customer Details.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the payments.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}

