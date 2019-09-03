package ca.myseneca.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 			   Author Name : Nirali Hirpara
	 Professor's full name : Prof. Tevin Apenteng
	Assignment Description : Lab 3 - Practice JDBC API using MySQL
	     Class Description : Implement a query to update a table entry in DB using Result Set in MySQL Database 
	       Submission Date : 25 Jan 2019
*/

public class UpdateTableUpdatableResultSet {
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

			// Create a prepared statement
			System.out.println("Creating a updatable result set prepared statement...");
			String sql = "select Code,Name,Continent from Country ;";

			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// Execute a query
			rset = pstmt.executeQuery();

			// Move cursor to before the first row
			// Delete row 0
			rset.absolute(1);
			rset.deleteRow();
			
			
			// Move cursor to before the first row. 
			rset.absolute(0);

			// process ResultSet
			System.out.println("\nCode\t\tName\t\tContinent");
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
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}// end main
}// end JDBCExample
