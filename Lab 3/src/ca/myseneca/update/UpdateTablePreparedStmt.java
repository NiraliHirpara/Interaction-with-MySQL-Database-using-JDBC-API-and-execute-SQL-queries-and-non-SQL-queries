package ca.myseneca.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
			   Author Name : Nirali Hirpara
	 Professor's full name : Prof. Tevin Apenteng
	Assignment Description : Lab 3 - Practice JDBC API using MySQL
	     Class Description : Implement a Prepared Statement query to update a table entry in DB in MySQL Database 
	       Submission Date : 25 Jan 2019
*/

public class UpdateTablePreparedStmt {
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
			System.out.println("Creating a update prepared statement...");
			String updatesql = "update Country set LifeExpectancy = ? where Code2 = ?";
			pstmt = conn.prepareStatement(updatesql);

			// Get user input
			Scanner inputReader = new Scanner(System.in);
			System.out.println("Please enter new Life Expectency : ");
			float age = inputReader.nextFloat();
			System.out.println("Please counrty code whose Life Expectency is to be updated: ");
			String code = inputReader.next();
			inputReader.close();
			

			// Set up query parameters
			pstmt.setFloat(1, age);
			pstmt.setString(2, code);

			int count = pstmt.executeUpdate();

			if (count > 0) {
				System.out.println("Statement Updated success");
			} else {
				System.out.println("Updated unsuccessfull");
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
