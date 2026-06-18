package adjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class d1 {
	private static void displayTable(Statement stmt) throws SQLException {

	    String query = "select * from empg";

	    ResultSet rs = stmt.executeQuery(query);

	    System.out.println("-------------------------------------------------------------");
	    System.out.printf("%-5s %-15s %-30s %-10s%n",
	            "ID", "NAME", "DESIGNATION", "SALARY");
	    System.out.println("-------------------------------------------------------------");

	    while(rs.next()) {

	        System.out.printf("%-5d %-15s %-30s %-10d%n",
	                rs.getInt(1),
	                rs.getString(2),
	                rs.getString(3),
	                rs.getInt(4));
	    }

	    System.out.println("-------------------------------------------------------------");
	}

public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
	String url = "jdbc:mysql://localhost:3306/employ";
	String un = "root";
	String pw = "Sneha@123";
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	System.out.println("driver is loaded");
	
	Connection con =DriverManager.getConnection(url, un, pw);
	
	System.out.println("connection established");
	
	
//	Statement stmt = con.createStatement(
//	        ResultSet.TYPE_SCROLL_SENSITIVE,
//	        ResultSet.CONCUR_READ_ONLY);
	
	Statement stmt = con.createStatement();
	System.out.println("statement created");
	

	while(true) {

	    System.out.println("1. Insert");
	    System.out.println("2. Update");
	    System.out.println("3. Delete");
	    System.out.println("4. Display");
	    System.out.println("5. Add Column");
	    System.out.println("6. Delete Column");
	    System.out.println("7. Exit");

	    System.out.print("Enter Choice: ");
	    int choice = sc.nextInt();

	    switch(choice) {

	        case 1:
	        {	
	        	System.out.print("Enter Id: ");
	        	int id = sc.nextInt();
	        	sc.nextLine();
	        	System.out.print("Enter Name: ");
	        	String name = sc.nextLine();

	        	System.out.print("Enter Designation: ");
	        	String des = sc.nextLine();

	        	System.out.print("Enter Salary: ");
	        	int sal = sc.nextInt();

	        	String insertQuery =
	        			"insert into empg values("+id+",'"+name+"','"+des+"',"+sal+")";

	        			int rowsInserted = stmt.executeUpdate(insertQuery);

	        			if(rowsInserted > 0) {
	        			    System.out.println("Record Inserted Successfully");
	        			    displayTable(stmt);
	        			}
	           
	        	
	            break;
	        }

	        case 2:
	        {
	            
	        	System.out.print("Enter Id: ");
	        	int id = sc.nextInt();

	        	System.out.print("Enter New Salary: ");
	        	int sal = sc.nextInt();

	        	String updateQuery =
	        			"update empg set salary="+sal+" where id="+id;

	        			int rowsUpdated = stmt.executeUpdate(updateQuery);

	        	if(rowsUpdated>0)
	        	{
	        	    System.out.println("Record Updated Successfully ");
	        	    displayTable(stmt);
	        	}
	        	
	            break;
	        }
	        case 3:
	        {
	           
	        	System.out.print("Enter Id to Delete: ");
	        	int id = sc.nextInt();

	        	String deleteQuery =
	        			"delete from empg where id="+id;

	        			int rowsDeleted = stmt.executeUpdate(deleteQuery);
	        	
	        			if(rowsDeleted > 0) {
	        			    System.out.println("Record Deleted Successfully");
	        			    displayTable(stmt);
	        			}
	            break;
	        }
	        case 4:
	        {
	            String selectQuery = "select * from empg";

	            ResultSet result = stmt.executeQuery(selectQuery);

	            ResultSetMetaData meta = result.getMetaData();

	            int cols = meta.getColumnCount();

	            System.out.println("----------------------------------------------------------------------------------------");

	            for(int i=1; i<=cols; i++)
	            {
	                System.out.printf("%-20s", meta.getColumnName(i));
	            }
	            System.out.println();

	            System.out.println("------------------------------------------------------------------------------------------");

	            while(result.next())
	            {
	                for(int i=1; i<=cols; i++)
	                {
	                    System.out.printf("%-20s", result.getString(i));
	                }
	                System.out.println();
	            }

	            System.out.println("------------------------------------------------------------------------------------------");

	            break;
	        }
	        case 5:
	        {
	            sc.nextLine();

	            System.out.print("Enter Column Name: ");
	            String colName = sc.nextLine();

	            System.out.print("Enter Data Type: ");
	            String dataType = sc.nextLine();

	            String addColumnQuery =
	                    "ALTER TABLE empg ADD COLUMN "
	                    + colName + " " + dataType;

	            stmt.executeUpdate(addColumnQuery);

	            System.out.println("Column Added Successfully");

	            break;
	        }
	        case 6:
	        {
	            sc.nextLine();

	            System.out.print("Enter Column Name to Delete: ");
	            String colName = sc.nextLine();

	            String dropColumnQuery =
	                    "ALTER TABLE empg DROP COLUMN "
	                    + colName;

	            stmt.executeUpdate(dropColumnQuery);

	            System.out.println("Column Deleted Successfully");

	            break;
	        }
	        
	        case 7:
	        {
	            System.exit(0);
	        }

	        default:
	            System.out.println("Invalid Choice");
	            
	            
	    }
	    
	    
	}
	
	
	
	
	//when u dont know anything baout table or db then use metadata
//   ResultSetMetaData metaData = res.getMetaData();
//    System.out.println( metaData.getColumnCount());	
//    
//   for(int i=1;i<=metaData.getColumnCount();i++) {
//	   System.out.println(metaData.getColumnName(i)+" "+metaData.getColumnTypeName(i));
//	   
//	    }
//   
//   while(res.next()== true) {
//	   System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getInt(4));
//
//   }
//   
//   
//   res.absolute(3);
//   System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getInt(4));

	
	
	
	//this is used when u know the db or table data
//	while(res.next()==true) {
//	
//	System.out.println(res.getInt(1)+"  " +res.getString(2)+"  "+res.getString(3)+"  " +res.getInt(4));
//	
//	
//	}
//	
	
//	res.first();
//	System.out.println(res.getInt(1)+"  " +res.getString(2)+"  "+res.getString(3)+"  " +res.getInt(4));
//	
//	
//	res.absolute(3);
//	System.out.println(res.getInt(1)+"  " +res.getString(2)+"  "+res.getString(3)+"  " +res.getInt(4));
//
	
	
	}
	catch (ClassNotFoundException e){
		e.printStackTrace();
	}
	catch(SQLException  e) {
		e.printStackTrace();
	}
}

}