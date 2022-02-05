F<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=780px">
<title>Home</title>
<link rel="stylesheet" href="style.css">
<script src="https://kit.fontawesome.com/1a87f1ecb3.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav>
		<p class="title">Financial Partner</p>
		<button class="btn_admin" onclick="location.href = './admin.jsp';">
			<i class="fas fa-user-shield"></i>Admin
		</button>
	</nav>
	<div class="slideshow">

		<div class="mySlides fade">
			<img src="b1.jpg" class="bank_img">
		</div>

		<div class="mySlides fade">
			<img src="b2.jpg" class="bank_img">
		</div>

		<div class="mySlides fade">
			<img src="b3.jpg" class="bank_img">
		</div>
		<div class="mySlides fade">
			<img src="b4.jpg" class="bank_img">
		</div>

	</div>
	<div class="dotdiv">
		<span class="dot"></span> <span class="dot"></span> <span class="dot"></span>
		<span class="dot"></span>
	</div>
	<div class="login_form">
		<p class="login_heading">Login</p>
		<form action="MyServlet" method="post">
			<label class="login_text">Customer ID:</label><br> <input
				type="number" name="Customer_ID" class="input_login" required><br>
			<label class="login_text">Password</label><br> <input
				type="password" name="login_password" class="input_login" required>
			<label class="login_text">${message}</label> <input type="submit"
				name="login" class="btn_login">
		</form>
		<p class='text'>New to Financial Partner? Create an account</p>
		<button class="btn_login" onclick="location.href = './signup.jsp';">Sign
			up</button>
	</div>
	<div>
		<div class="heading">LOGIN NOW!</div>
		<div class="dimg">
			<img src="1.jpg" class="img">
			<p class="img_text">Deposit money in your account anytime</p>
		</div>
		<div class="dimg">
			<img src="2.jpg" class="img">
			<p class="img_text">Print your account statement anytime</p>
		</div>
		<div class="dimg">
			<img src="3.jpg" class="img">
			<p class="img_text">Withdraw money from your account anytime</p>
		</div>
		<div class="dimg">
			<img src="4.jpg" class="img">
			<p class="img_text">Transfer money from your account anytime</p>
		</div>
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
	<script type="text/javascript">
		var slideIndex = 0;
		showSlides();

		function showSlides() {
			var i;
			var slides = document.getElementsByClassName("mySlides");
			var dots = document.getElementsByClassName("dot");
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			slideIndex++;
			if (slideIndex > slides.length) {
				slideIndex = 1
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}
			slides[slideIndex - 1].style.display = "block";
			dots[slideIndex - 1].className += " active";
			setTimeout(showSlides, 5000);
		}
	</script>
</body>
</html>