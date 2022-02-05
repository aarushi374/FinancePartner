<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=780px">
<title>Transactions</title>
<link rel="stylesheet" href="style.css">
  <script src="https://kit.fontawesome.com/1a87f1ecb3.js" crossorigin="anonymous"></script>
</head>
<body>
<%
		try {
			Connection con = null;
			String dbDriver = "com.mysql.jdbc.Driver";
			String dbURL = "jdbc:mysql:// localhost:3306/";
			String dbName = "financepartner";
			String dbUsername = "root";
			String dbPassword = "root";
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
			Statement statement = con.createStatement();
			String sql = "select amount from signup where customer_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, request.getParameter("id"));
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
		%>
<nav>
    <p class="title">Financial Partner</p>
    <button class="btn_home" onclick="logout()"><i class="fas fa-home"></i>Home</button>
    </nav>
    <p class="login_heading">Current Balance: <%=resultSet.getInt("amount") %></p>
    <p class="text">${message}</p>
    <div id="address_change" class="transaction-form" style="display:none">
    <form action="MyServlet" method="post">
     <label class="transaction_text">ENTER NEW ADDRESS:</label><br>
     <p></p>
     <label class="transaction_text">Line 1</label><br>
     <input type="text" name="line1" class="input_login" required><br>
     <label class="transaction_text">Line 2</label><br>
     <input type="text" name="line2" class="input_login"><br>
     <label class="transaction_text">City</label><br>
     <input type="text" name="city" class="input_login" required><br>
     <label class="transaction_text">State</label><br>
     <input type="text" name="state" class="input_login" required><br>
     <label class="transaction_text">Pincode</label><br>
     <input type="number" min="100000" max="999999" name="pincode" class="input_login"
      oninvalid="this.setCustomValidity('Enter valid pincode')"
  oninput="this.setCustomValidity('')" required><br>
     <button type="button" class="btn_form" onclick="closeAddressForm()">Close</button>
     <button type="submit" name="new_address" value='<%=request.getParameter("id")%>' class="btn_form">Submit</button>

    </form>
    </div>

    <div id="money_deposit" class="transaction-form" style="display:none">
    <form action="MyServlet" method="post">
     <label class="transaction_text">Enter Deposit Amount:</label><br>
     <input type="number" name="amount" class="input_login" required><br>
     <button type="button" class="btn_form" onclick="closeDepositForm()">Close</button>
     <button type="submit" name="deposit" value='<%=request.getParameter("id")%>' class="btn_form">Submit</button>

    </form>
    </div>

    <div id="money_withdrawal" class="transaction-form" style="display:none">
    <form action="MyServlet" method="post">
     <label class="transaction_text">Enter Withdrawal Amount:</label><br>
     <input type="number" name="amount" class="input_login" required><br>
     <button type="button" class="btn_form" onclick="closeWithdrawalForm()">Close</button>
     <button type="submit" name="withdraw" value='<%=request.getParameter("id")%>' class="btn_form">Submit</button>

    </form>
    </div>

    <div id="money_transfer" class="transaction-form" style="display:none">
    <form action="MyServlet" method="post">
    <label class="transaction_text">Enter Account Number:</label><br>
    <input type="number" name="account" class="input_login" required><br>
     <label class="transaction_text">Enter Transfer Amount:</label><br>
     <input type="number" name="amount" class="input_login" required><br>
     <button type="button" class="btn_form" onclick="closeTransferForm()">Close</button>
     <button type="submit" value='<%=request.getParameter("id")%>' name="transfer" class="btn_form">Submit</button>

    </form>
    </div>
     <button class="btn_transaction" onclick="openAddressForm()">Address Change</button>
     <button class="btn_transaction" onclick="openDepositForm()">Money Deposit</button>
     <button  class="btn_transaction" onclick="openWithdrawalForm()">Money Withdrawal</button>
     <button  class="btn_transaction" onclick="openTransferForm()">Transfer Money</button>
     <form action="MyServlet" method="post" style=" margin:0px; padding:0px; display:inline;">
     <button  class="btn_transaction" name="print" value='<%=request.getParameter("id")%>'>Print Statement</button>
     </form>
     <form method="post" action="MyServlet" style=" margin:0px; padding:0px; display:inline;">
     <button  class="btn_transaction" name="close" value='<%=request.getParameter("id")%>'>Account Closure</button>
     </form>
     <div class="footer">
       <div class="foot">
         <p class="foot_heading">Mail Us</p>
         <p class="foot_txt">FinancePartner@gmail.com</p>
       </div>
       <div class="foot">
         <p class="foot_heading">Social</p>
         <p class="foot_txt"><i class="fab fa-facebook-f"></i>
           <i class="fab fa-twitter"></i>
           <i class="fab fa-youtube"></i>
         </p>
       </div>
       <div class="foot">
         <p class="foot_heading">Contact</p>
         <p class="foot_txt">1800 254 73XX</p>
       </div>
       <div class="foot">
         <p class="foot_heading">Office Address</p>
         <p class="foot_txt">Financial Partner Jaipur,Rajasthan</p>
       </div>
     </div>
     <%}
		con.close();
		} catch (Exception e) {
		e.printStackTrace();
		} %>
     <script type="text/javascript">
      
     function openAddressForm() {
     document.getElementById("address_change").style.display = "block";
   }

   function closeAddressForm() {
     document.getElementById("address_change").style.display = "none";
   }
   function openDepositForm() {
 document.getElementById("money_deposit").style.display = "block";
}

function closeDepositForm() {
 document.getElementById("money_deposit").style.display = "none";
}
function openWithdrawalForm() {
document.getElementById("money_withdrawal").style.display = "block";
}

function closeWithdrawalForm() {
document.getElementById("money_withdrawal").style.display = "none";
}
function openTransferForm() {
document.getElementById("money_transfer").style.display = "block";
}

function closeTransferForm() {
document.getElementById("money_transfer").style.display = "none";
}
function logout()
{
	var con=confirm('Do you want to logout from this account? Press OK to confirm');
	if(con==true)
		window.location='./index.jsp';
}
/*function close()	
	{
		var con=confirm('Are you sure you want to close this account. Press Ok to confirm');
		if(con==true)
			{
			var f=document.closeForm;
			f.action="MyServlet";
			}
	}*/
 </script>
</body>
</html>