<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=780px">
<title>Admin</title>
<link rel="stylesheet" href="style.css">
  <script src="https://kit.fontawesome.com/1a87f1ecb3.js" crossorigin="anonymous"></script>
</head>
<body>
  <nav>
    <p class="title">Financial Partner</p>
    <button class="btn_home" onclick="location.href = './index.jsp';"><i class="fas fa-home"></i>Home</button>
    </nav>
<div class="admin_form">
    <p class="login_heading">Login</p>
  <form action="MyServlet" method="post">
  <label class="login_text">Admin ID:</label><br>
  <input type="text" name="Admin_ID" class="input_login" required><br>
  <label class="login_text">Password:</label><br>
  <input type="password" name="adminlogin_password" class="input_login" required>
  <input type="submit" name="admin_login" class="btn_login" >
</form>
</div>
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
</body>
</html>