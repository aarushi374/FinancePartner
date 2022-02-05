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
<title>Closed Accounts</title>
<link rel="stylesheet" href="style.css">
  <script src="https://kit.fontawesome.com/1a87f1ecb3.js" crossorigin="anonymous"></script>
</head>
<body>
			
<nav>
    <p class="title">Financial Partner</p>
    <button class="btn_home" onclick="logout_admin()"><i class="fas fa-home"></i>Home</button>
    </nav>
<div class="">
  <p class="heading2">Closed Accounts</p>
   <div class="date">
    <form method="post" action="MyServlet">
  <label class="text2">Start date</label><input type="date" id="datePicker1" name="start_print">
  <label class="text2">End date</label><input type="date" id="datePicker2" name="end_print" >
  <button type="button" onclick="date()" name="button" id="btn" class="btn_date">Submit</button>
  <p class="text" id="text"></p>
  </form>
  </div>
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
			String sql = "select * from close where date between ? and ? order by date desc";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,request.getParameter("start"));
			ps.setString(2,request.getParameter("end"));
			ResultSet resultSet=ps.executeQuery();%>
  <table>
    <tr>
   <th>Customer_ID</th>
   <th>Date</th>
 </tr>
 <%while (resultSet.next()) {%>
 <tr>
   <td><%=resultSet.getInt("customer_id") %></td>
   <td><%=resultSet.getString("date") %></td>
 </tr>
 <%}%>
  </table>
</div>
<%
con.close();
			}catch(Exception e)
{ 
				e.printStackTrace();}
%>
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
<script>
function logout_admin()
{var con=confirm('Do you want to logout as admin? Press OK to confirm');
if(con==true)
	window.location='./index.jsp';
	}
function date()
{
  if(document.getElementById('datePicker1').value=="" || document.getElementById('datePicker2').value=="")
  {
    var x="Please Enter both start date and end date";
    document.getElementById('text').innerHTML=x;
  }
  else if(document.getElementById('datePicker1').value!="" && document.getElementById('datePicker2').value!="")
  {
	  
    window.location="closedAccounts.jsp?start="+document.getElementById('datePicker1').value+
    "&end="+document.getElementById('datePicker2').value;
  }
}
</script>
</body>
</html>