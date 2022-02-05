
import java.sql.*;

public class DBconnection {

	public static Connection initializeDatabase() {
		Connection con = null;
		// Initialize all the information regarding
		// Database Connection
		String dbDriver = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql:// localhost:3306/";
		// Database name to access
		String dbName = "financepartner";
		String dbUsername = "root";
		String dbPassword = "root";
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
