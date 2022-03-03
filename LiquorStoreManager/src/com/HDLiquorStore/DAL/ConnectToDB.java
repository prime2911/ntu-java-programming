package com.HDLiquorStore.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {

	Connection conn;

	public static Connection establishConnection(String dbName, String username, String password) {
		// Step 1. Install driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		// Step 2. Connect to a database
		String urlDB = "jdbc:mysql://localhost:3306/" + dbName;

		try {
			return DriverManager.getConnection(urlDB, username, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
}