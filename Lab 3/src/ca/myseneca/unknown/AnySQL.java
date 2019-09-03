package ca.myseneca.unknown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
  			   Author Name : Nirali Hirpara
	 Professor's full name : Prof. Tevin Apenteng
	Assignment Description : Lab 3 - Practice JDBC API using MySQL
	     Class Description : Implement a query using a Java class in MySQL Database 
	       Submission Date : 25 Jan 2019
*/

public class AnySQL {

	static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver"; // JDBC driver
	static final String SYS_NAME = "mymysql.senecacollege.ca"; // Database server
	static final String DB_NAME = "****"; // Database name

	// Database credentials
	static final String USERID = "****";
	static final String PASSWORD = "****";

	public static void main(String[] args) {
		Scanner inputReader = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		int size = 0;

		try {
			// Load / Register JDBC driver (class)
			Class.forName(DRIVER_NAME);

			// Database URL
			String url = "jdbc:mysql://" + SYS_NAME + "/" + DB_NAME;

			// Establish the connection.
			conn = DriverManager.getConnection(url, USERID, PASSWORD);
			System.out.println("Connected database successfully...");
			
			// Create a statement
			System.out.println("Creating any statement...");
			Statement stmt = conn.createStatement();

			// Enter a query
			System.out.println("Please enter any query :");
			
			query = inputReader.nextLine();
			
			// Status is true if query returns result set
			boolean status = stmt.execute(query);


			// if status returns true result set is fetched and result set is processed
			if (status) {
				// Execute a query
				rset = stmt.getResultSet();
				ResultSetMetaData rsmd = rset.getMetaData();

				size = rsmd.getColumnCount();

				for (int i = 1; i <= size; i++) {
					System.out.print(rsmd.getColumnName(i) + "\t\t\t\t\t");
				}
				System.out.println();

				// process ResultSet
				while (rset.next()) {
					for (int i = 1; i <= size; i++) {
						System.out.print(rset.getString(i) + "\t\t\t\t\t");
					}
					System.out.println();
				}
				rset.close();
			} else {
				// Query can be update or any query apart from select query
				int count = stmt.getUpdateCount();
				System.out.println("Total records updated: " + count);
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