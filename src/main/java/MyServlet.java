
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public MyServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		DAO register = new DAO();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("signup") != null) {
			int amount = 0;
			String firstName = request.getParameter("signup_firstname");
			String lastName = request.getParameter("signup_lastname");
			String phone = request.getParameter("phone");
			String email = request.getParameter("signup_ID");
			String line1 = request.getParameter("line1");
			String line2 = request.getParameter("line2");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String pincode = request.getParameter("pincode");
			String password = request.getParameter("signup_password");
			String password_confirm = request.getParameter("signup_confirm_password");
			String type = request.getParameter("type");
			if (!password.equals(password_confirm)) {
				String message = "Password does not match";
				request.setAttribute("message", message);
				request.getRequestDispatcher("./signup.jsp").forward(request, response);
			}
			if (type.equals("current")) {
				amount = 5000;
				out.println("<script type='text/javascript'>");
				out.println("alert('Deposit Rs.5000 to your account');</script>");
			}
			MyBank customer = new MyBank(firstName, lastName, phone, email, line1, line2, city, state, pincode,
					password, amount, type);
			int customer_id = register.signup(customer);
			if (customer_id == -1) {
				String message = "Error Occured";
				out.println("<script type='text/javascript'>");
				out.println("alert(" + "'" + message + "'" + ");window.location='./index.jsp';</script>");
			} else {

				String message = "Your Customer ID is " + customer_id;
				out.println("<script type='text/javascript'>");
				out.println("alert(" + "'" + message + "'" + ");window.location='./index.jsp';</script>");
			}
		}
		if (request.getParameter("login") != null) {
			String Customer_ID = request.getParameter("Customer_ID");
			String password_login = request.getParameter("login_password");
			MyBank customer = new MyBank(Customer_ID, password_login);
			String result = register.login(customer);
			if (result.equals("authentication successful")) {
				response.sendRedirect("transactions.jsp?id=" + Customer_ID);
			} else if (result.equals("authentication unsuccessful")) {
				String message = "Email or Password Incorrect";
				request.setAttribute("message", message);
				request.getRequestDispatcher("./index.jsp").forward(request, response);
			}
		}
		if (request.getParameter("new_address") != null) {
			int customer_id = Integer.parseInt(request.getParameter("new_address"));
			String line1 = request.getParameter("line1");
			String line2 = request.getParameter("line2");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String pincode = request.getParameter("pincode");
			MyBank customer = new MyBank(line1, line2, city, state, pincode, customer_id);
			String result = register.change_address(customer);
			if (result.equals("Address update successfull")) {
				result = "Address Changed Successfully";
			}
			request.setAttribute("message", result);
			request.getRequestDispatcher("transactions.jsp?id=" + customer_id).forward(request, response);
		}
		if (request.getParameter("deposit") != null) {
			int Amount = Integer.parseInt(request.getParameter("amount"));
			int customer_id = Integer.parseInt(request.getParameter("deposit"));
			MyBank customer = new MyBank(Amount, customer_id);
			String result = register.deposit(customer);
			String url = "transactions.jsp?id=" + customer_id;
			if (result.equals("Successfull")) {
				result = "Money Deposited Successfully";
			}
			request.setAttribute("message", result);
			request.getRequestDispatcher(url).forward(request, response);
		}
		if (request.getParameter("withdraw") != null) {
			int Amount = Integer.parseInt(request.getParameter("amount"));
			int customer_id = Integer.parseInt(request.getParameter("withdraw"));
			MyBank customer = new MyBank(Amount, customer_id);
			String result = register.withdraw(customer);
			if (result.equals("Successfull")) {
				result = "Money Withdrawn Successfully";
			}
			request.setAttribute("message", result);
			request.getRequestDispatcher("transactions.jsp?id=" + customer_id).forward(request, response);
		}
		if (request.getParameter("transfer") != null) {
			String Account = request.getParameter("account");
			int Amount = Integer.parseInt(request.getParameter("amount"));
			int customer_id = Integer.parseInt(request.getParameter("transfer"));
			MyBank customer = new MyBank(Amount, customer_id, Account);
			String result = register.transfer(customer);
			if (result.equals("Successfull"))
				result = "Money Transfered Successfully";
			request.setAttribute("message", result);
			request.getRequestDispatcher("transactions.jsp?id=" + customer_id).forward(request, response);
		}
		if (request.getParameter("admin_login") != null) {
			if (request.getParameter("Admin_ID").equals("admin")
					&& request.getParameter("adminlogin_password").equals("admin")) {
				response.sendRedirect("closedAccounts.jsp");
			}
		}
		if (request.getParameter("close") != null) {
			int Customer_id = Integer.parseInt(request.getParameter("close"));
			MyBank customer = new MyBank(Customer_id);
			String result = register.close(customer);
			if (result.equals("Record Updated")) {
				result = "Account Closed Successfully";
				out.println("<script type='text/javascript'>");
				out.println("alert(" + "'" + result + "'" + ");window.location='./index.jsp';</script>");
			}

		}
		if (request.getParameter("print") != null) {
			int Customer_id = Integer.parseInt(request.getParameter("print"));
			response.sendRedirect("accountStatement.jsp?id=" + Customer_id);

		}
		if (request.getParameter("start_date") != null) {
			System.out.print(request.getParameter("start_date"));
		}

	}

}
