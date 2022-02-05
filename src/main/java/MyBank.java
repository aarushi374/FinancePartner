
public class MyBank {
 private String customer_id_login,password_login,account;
 private String firstName, lastName, Email, Phone, Line1,Line2,city,state,pincode, Password,type;
 private int Amount,transfer_amount,customer_id;
 public MyBank() {
		super();
	}
 public MyBank(int customer_id)
 {
	 this.customer_id=customer_id;
 }
 public MyBank(String customer_id_login,String password_login)
 {
	 this.customer_id_login=customer_id_login;
	 this.password_login=password_login;
 }
 public MyBank(String firstName, String lastName, String Phone, String Email, String Line1,String Line2, 
		 String city,String state,String pincode,String Password,int Amount,String type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.Password = Password;
		this.Email = Email;
		this.Line1 = Line1;
		this.city=city;
		this.state=state;
		this.Line2=Line2;
		this.pincode=pincode;
		this.Phone = Phone;
		this.Amount=Amount;
		this.type=type;
	}
 public MyBank(String Line1,String Line2,String city,String state,String pincode,int customer_id)
 {
	 this.Line1=Line1;
	 this.Line2=Line2;
	 this.city=city;
	 this.state=state;
	 this.pincode=pincode;
	 this.customer_id=customer_id;

 }
 public MyBank(int transfer_amount,int customer_id)
 {
	 this.transfer_amount=transfer_amount;
	 this.customer_id=customer_id;
 }
 public MyBank(int transfer_amount,int customer_id,String account)
 {
	 this.transfer_amount=transfer_amount;
	 this.customer_id=customer_id;
	 this.account=account;
 }
 public String getType()
 {
	 return type;
 }
 public String getAccount()
 {
	 return account;
 }
 public int getCustomer_ID()
 {
	 return customer_id;
 }
 public int getTransferAmount()
 {
	 return transfer_amount;
 }
 public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLine1() {
		return Line1;
	}
	public String getLine2()
	{
		return Line2;
	}
	public String getPincode()
	{
		return pincode;
	}
	public String getState()
	{
		return state;
	}
	public String getCity()
	{
		return city;
	}
	public int getAmount()
	{
		return Amount;
	}

	public String getPhone() {
		return Phone;
	}

	public String getEmail() {
		return Email;
	}

	public String getPassword() {
		return Password;
	}


 public String getCustomerLogin()
 {
	 return customer_id_login;
 }
 public void setCustomerLogin(String customer_id_login)
 {
	 this.customer_id_login=customer_id_login;
 }
 public String getPasswordLogin()
 {
	 return password_login;
 }
 public void setPasswordLogin(String password_login)
 {
	 this.password_login=password_login;
 }
 
}
