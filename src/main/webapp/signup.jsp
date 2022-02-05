<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=780px">
<title>SignUp</title>
<link rel="stylesheet" href="style.css">
<script src="https://kit.fontawesome.com/1a87f1ecb3.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav>
		<p class="title">Financial Partner</p>
		<button class="btn_home" onclick="location.href = './index.jsp';">
			<i class="fas fa-home"></i>Home
		</button>
	</nav>
	<div class="signup_form">
		<p class="login_heading">SignUp</p>
		<form action="MyServlet" method="post">
			<label class="login_text">First Name:</label><br> <input
				type="text" name="signup_firstname" class="input_login" required><br>
			<label class="login_text">Last Name:</label><br> <input
				type="text" name="signup_lastname" class="input_login" required><br>
			<label class="login_text">Phone</label><br> <input type="number"
				min="1000000000" max="9999999999" name="phone" class="input_login"
				oninvalid="this.setCustomValidity('Enter valid phone number')"
				oninput="this.setCustomValidity('')" required><br> <label
				class="login_text">Email ID:</label><br> <input type="email"
				name="signup_ID" class="input_login" required><br> <label
				class="login_text">ADDRESS:</label><br>
			<p></p>
			<label class="login_text">Line 1</label><br> <input type="text"
				name="line1" class="input_login" required><br> <label
				class="login_text">Line 2</label><br> <input type="text"
				name="line2" class="input_login"><br> <label
				class="login_text">City</label><br> <input type="text"
				name="city" class="input_login" required><br> <label
				class="login_text">State</label><br> <input type="text"
				name="state" class="input_login" required><br> <label
				class="login_text">Pincode</label><br> <input type="number"
				min="100000" max="999999" name="pincode" class="input_login"
				oninvalid="this.setCustomValidity('Enter valid pincode')"
				oninput="this.setCustomValidity('')" required><br> <input
				type="radio" class="radio1" name="type" value="saving" required>
			<label class="txt">Saving Account</label> <input type="radio"
				class="radio2" name="type" value="current" required> <label
				class="txt">Current Account</label><br> <label
				class="login_text">Create Password:</label><br> <input
				type="password" name="signup_password" minlength="8"
				class="input_login"
				oninvalid="this.setCustomValidity('Password should be of atleast 8 characters')"
				oninput="this.setCustomValidity('')" required> <label
				class="login_text">Confirm Password:</label><br> <input
				type="password" name="signup_confirm_password" class="input_login"
				required> <label class=login_text>${message}</label> <input
				type="submit" name="signup" class="btn_login">
		</form>
	</div>
	<div class="footer">
		<div class="foot">
			<p class="foot_heading">Mail Us</p>
			<p class="foot_txt">FinancePartner@gmail.com</p>
		</div>
		<div class="foot">
			<p class="foot_heading">Social</p>
			<p class="foot_txt">
				<i class="fab fa-facebook-f"></i> <i class="fab fa-twitter"></i> <i
					class="fab fa-youtube"></i>
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