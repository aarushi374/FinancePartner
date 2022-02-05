import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; 
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

public class DAO {
    static class GetType{
    	String type;
    	int amount;
    	GetType(String type,int amount)
    	{
    		this.type=type;
    		this.amount=amount;
    	}
    }
	public String login(MyBank bank) {
		Connection con = DBconnection.initializeDatabase();
		String sql = "select customer_id,password from signup";
		String result = "authentication unsuccessful";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				if (resultSet.getString("customer_id").equals(bank.getCustomerLogin())
						&& resultSet.getString("password").equals(bank.getPasswordLogin())) {
					result = "authentication successful";
				}
			}
		} catch (Exception e) {
			result = "sql exception";
		}
		return result;
	}
	public int signup(MyBank bank) {
		Connection con = DBconnection.initializeDatabase();
		String sql = "insert into signup(firstname,lastname,phone,email,line1,line2,city,state,pincode,password,amount,type) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
		String sql2="select max(customer_id) max_id from signup";
		//String result = "Account created";
		PreparedStatement ps;
		int id = -1;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, bank.getFirstName());
			ps.setString(2, bank.getLastName());
			ps.setString(3, bank.getPhone());
			ps.setString(4, bank.getEmail());
			ps.setString(5, bank.getLine1());
			ps.setString(6, bank.getLine2());
			ps.setString(7, bank.getCity());
			ps.setString(8, bank.getState());
			ps.setString(9, bank.getPincode());
			ps.setString(10, bank.getPassword());
			ps.setInt(11, bank.getAmount());
			ps.setString(12, bank.getType());
			ps.executeUpdate();
			Statement statement=con.createStatement();
			ResultSet resultset=statement.executeQuery(sql2);
		
			if (resultset.next()) {
			   id = resultset.getInt("max_id");  
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return id;
	}
	public String change_address(MyBank bank)
	{
		Connection con = DBconnection.initializeDatabase();
		String sql="update signup set line1=?,line2=?,city=?,state=?,pincode=? where customer_id=?";
		String result="Address update successfull";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, bank.getLine1());
			ps.setString(2, bank.getLine2());
			ps.setString(3, bank.getCity());
			ps.setString(4, bank.getState());
			ps.setString(5, bank.getPincode());
			ps.setInt(6, bank.getCustomer_ID());
			ps.executeUpdate();
		} catch (Exception e) {
			result = "sql error";
		}
		return result;
	}
	public GetType getAmount(MyBank bank)
	{
		Connection con = DBconnection.initializeDatabase();
		String sql2="select amount,type from signup where customer_id=?";
		PreparedStatement ps;
		ResultSet result;
		String type="";
		int amount=0;
		try {
			ps=con.prepareStatement(sql2);
			ps.setInt(1,bank.getCustomer_ID());
			result=ps.executeQuery();
			while(result.next())
			{
				amount=result.getInt("amount");
				type=result.getString("type");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		GetType t=new GetType(type,amount);
		return t;
	}
	public String deposit(MyBank bank)
	{
		Connection con = DBconnection.initializeDatabase();
		String sql="update signup set amount=? where customer_id=?";
		String result="Money deposited successfully";
		GetType t=getAmount(bank);
		PreparedStatement ps1;
		try {
		
			ps1 = con.prepareStatement(sql);
			ps1.setInt(1, t.amount+bank.getTransferAmount());
			ps1.setInt(2, bank.getCustomer_ID());
			ps1.executeUpdate();
		} catch (Exception e) {
			result = "sql error";
		}
		if(result.equals("Money deposited successfully"))
			result=print_statement(bank,"Deposited");
		return result;
	}
	public String deposit_transfer(MyBank bank)
	{
		Connection con = DBconnection.initializeDatabase();
		String sql="update signup set amount=? where customer_id=?";
		String result="Money deposited successfully";
		String sql2="select amount from signup where customer_id=?";
		PreparedStatement ps;
		ResultSet re;
		int amount=0;
		//int acc=Integer.parseInt(bank.getAccount());
		PreparedStatement ps1;
		try {
			ps=con.prepareStatement(sql2);
			ps.setString(1,bank.getAccount());
			re=ps.executeQuery();
			while(re.next())
			{
				amount=re.getInt("amount");
			}
			ps1 = con.prepareStatement(sql);
			ps1.setInt(1, amount+bank.getTransferAmount());
			ps1.setString(2, bank.getAccount());
			ps1.executeUpdate();
		} catch (Exception e) {
			result = "sql error";
		}
		return result;
	}
	public String getDate(MyBank bank)
	{
		Connection con = DBconnection.initializeDatabase();
		String sql1="select COUNT(type) count,type,customer_id from print where type='Withdrawn' AND customer_id=?";
		String sql2="select max(date) d from print where customer_id=? AND type='Withdrawn'";
		PreparedStatement ps1,ps2;
		ResultSet r1,r2;
		int count=0;
		String date="",result="success";
		try {
			ps1=con.prepareStatement(sql1);
			ps1.setInt(1, bank.getCustomer_ID());
			r1=ps1.executeQuery();
			while(r1.next())
			{
				count=r1.getInt("count");
			}
			ps2=con.prepareStatement(sql2);
			ps2.setInt(1, bank.getCustomer_ID());
			r2=ps2.executeQuery();
			while(r2.next()) {
				date=r2.getString("d");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		  //System.out.println(count+" "+date);
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		  Date date1 = new Date(); 
		  formatter.format(date1);
		try {
		if(count==10)
		{
		    Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(date);
		    Calendar calendar1 = Calendar.getInstance();
		    calendar1.setTime(date1);
		    Calendar calendar2 = Calendar.getInstance();
		    calendar2.setTime(date2);
		    if(calendar1.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH))
		    {
		    	result="Maximum 10 withdrawals per month are allowed";
		    }
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public String withdraw(MyBank bank)
	{
		Connection con = DBconnection.initializeDatabase();
		String sql="update signup set amount=? where customer_id=?";
		String result="Money Withdrawn successfully";
		GetType t=getAmount(bank);
		if(t.amount<bank.getTransferAmount())
			return "Not enough balance";
		if(t.type.equals("saving"))
		{
			result= getDate(bank);
			if(result.equals("Maximum 10 withdrawals per month are allowed"))
			{
				return result;
			}
			
		}
		int balance=t.amount-bank.getTransferAmount();
		if(t.type.equals("current") && balance<5000)
		{
			return "Minimum balance to maintain this account should be Rs.5000";
		}
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, balance);
			ps.setInt(2, bank.getCustomer_ID());
			ps.executeUpdate();
		} catch (Exception e) {
			result = "sql error";
		}
			result=print_statement(bank,"Withdrawn");
		return result;
	}
	public String withdraw_transfer(MyBank bank)
	{
		Connection con = DBconnection.initializeDatabase();
		String sql="update signup set amount=? where customer_id=?";
		String result="Money Withdrawn successfully";
		GetType t=getAmount(bank);
		if(t.amount<bank.getTransferAmount())
			return "Not enough balance";
		int balance=t.amount-bank.getTransferAmount();
		if(t.type.equals("current") && balance<5000)
		{
			return "Minimum balance to maintain this account should be Rs.5000";
		}
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, balance);
			ps.setInt(2, bank.getCustomer_ID());
			ps.executeUpdate();
		} catch (Exception e) {
			result = "sql error";
		}
		return result;
	}
	public String transfer(MyBank bank)
	{
		Connection con = DBconnection.initializeDatabase();
		String sql="select customer_id from signup";
		String result="Account number not found";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				if (resultSet.getString("customer_id").equals(bank.getAccount()))
				{
					result="Transfer successfull";
					 if(withdraw_transfer(bank).equals("Money Withdrawn successfully"))
					 {
						 deposit_transfer(bank);
					 }
						
					 else
						 return withdraw_transfer(bank);
				}
				
			}
		} catch (Exception e) {
			result = "sql exception";
		}
		
		if(result.equals("Transfer successfull"))
			result=print_statement(bank,"Transfered");
	    return result;
	}
	public String print_statement(MyBank bank,String type)
	{
		Connection con = DBconnection.initializeDatabase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		   LocalDateTime now = LocalDateTime.now();
		String sql="insert into print values(?,?,?,?,?)";
		PreparedStatement ps;
		String result="Successfull";
	
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1,bank.getCustomer_ID());
			ps.setString(2, type);
			ps.setInt(3,bank.getTransferAmount());
			
		if(type.equals("Transfered"))
		{	
		ps.setString(4, bank.getAccount());
		}
		else
		{
			ps.setInt(4, 0);
				
		}
		ps.setString(5, dtf.format(now));
		ps.executeUpdate();
		}

			catch(Exception e)
			{
				result="sql error 2";
			}
	
		return result;
	}
	public String close(MyBank bank)
	{
		Connection con = DBconnection.initializeDatabase();
		String sql="delete from signup where customer_id=?";
		String result="Account Successfully Deleted";
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, bank.getCustomer_ID());
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			result="sql error";
		}
		if(result.equals("Account Successfully Deleted"))
			result=admin_record(bank);
		return result;
	}
	public String admin_record(MyBank bank)
	{		Connection con = DBconnection.initializeDatabase();
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		   LocalDateTime now = LocalDateTime.now();  

		String result="Record Updated";
		String sql="insert into close values(?,?)";
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, bank.getCustomer_ID());
			ps.setString(2,dtf.format(now));
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			result="sql error 2";
		}
		return result;
	}
	
}