package d1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class day01 {
	private static final String URL = "jdbc:mysql://localhost:3306/employee";
	private static final String UN = "root";
	private static final String PWD = "Sneha@123";
	private static final String QUERY = "SELECT * FROM emp";
	private static final String QUE = "INSERT INTO emp (id,name,email,dept,salary) VALUES (3,'ram','ram@email.com','sales',25000)";
	

	public static void main(String[] args)  {
		
	
	Connection	con=null;
	Statement state = null;
	ResultSet res = null;
	
	
		// 1.load the driver
		try {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		System.out.println("driver is loaded");
		
		con = DriverManager.getConnection(URL, UN, PWD);
		//System.out.println("connection established");
		//3.CREATE A SQL STATEMNET
	 state=con.createStatement();
		
		
		//EXECUTE THE SQL QUERIES
		res= state.executeQuery(QUERY);
	//	System.out.println("query executed");
		
		
		
		//PROCESS RESULT
		printResultSet(res);
		
		
		
		}
//		catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(res != null) {
					res.close();
				}
				if(state != null) {
					state.close();
				}
			if(con != null) {
				con.close();
			}
			}
			catch(SQLException e) {
				
			}
		
		}
		}
			

	private static void printResultSet(ResultSet res) throws SQLException {
		while(res.next()){
			int id = res.getInt(1);
			String name =res.getString(2);
			String email=res.getString(3);
			String dept=res.getString(4);
					int salary=res.getInt(5);
			
			System.out.printf("|%d|  %-17s| %-20s| %-10s| %-7d|\n",id,name,email,dept,salary);
		}
	}

	//private static void While(boolean b) {
		// TODO Auto-generated method stub
		
	}
