package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionManager {

	static Connection connection;
	
	
		
		public static Connection getConnection() {
			
			InitialContext ctx;
			try {
				

				ctx = new javax.naming.InitialContext();
				javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup("jdbcbanco");

				connection = ds.getConnection();
				
			} catch (NamingException el) {
				el.printStackTrace();
			} catch (SQLException el) {
				el.printStackTrace();
			}
			
			
//			
//		try {
//			Class.forName("org.postgresql.Driver");
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eduardo","iaw26540084","1234");
//			
//		} catch (Exception e) {
//			System.err.println(e);
//			
//		}
		return connection;
	}
	
}
