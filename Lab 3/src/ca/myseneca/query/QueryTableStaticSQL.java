package ca.myseneca.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 			   Author Name : Nirali Hirpara
	 Professor's full name : Prof. Tevin Apenteng
	Assignment Description : Lab 3 - Practice JDBC API using MySQL
	     Class Description : Implement a Static SQL query in MySQL Database 
	       Submission Date : 25 Jan 2019
*/

public class QueryTableStaticSQL {
	
	static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver"; // JDBC driver
	static final String SYS_NAME = "mymysql.senecacollege.ca"; // Database server
	static final String DB_NAME = "****"; // Database name
	
    // Database credentials
	static final String USERID = "****";
	static final String PASSWORD = "****";


	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			// Load / Register JDBC driver (class)
			Class.forName(DRIVER_NAME);

			// Database URL
			String url = "jdbc:mysql://" + SYS_NAME + "/" + DB_NAME;
			
			// Establish the connection.
			conn = DriverManager.getConnection(url, USERID, PASSWORD);
			System.out.println("Connected database successfully...");

			//Create a static statement and Execute a query
			System.out.println("Creating a static select statement...");
			Statement stmt = conn.createStatement();
			rset = stmt.executeQuery("select Code2 , Name , LifeExpectancy from Country "
					+ "where LifeExpectancy between 50 and 75 order by 3 desc;");
			
			
			// Process ResultSet
			System.out.println("\nCode\t\tCountry\t\t\t\t\tLifeExpectancy");
			while (rset.next()) {
				System.out.print(rset.getString(1) + "\t\t");
				System.out.print(rset.getString(2) + "\t\t\t\t\t");
				System.out.print(rset.getString(3) + "\t\t");
				System.out.println();
			}
			

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} 
		
		// Close the connection in finally block.
		finally {
			try {
				if (rset != null) rset.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {}
		}
	}// end main
}// end JDBCExample

