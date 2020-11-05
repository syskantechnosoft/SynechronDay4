package com.sync.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static String username = "root";
	private static String password = "root";
	private static String url = "jdbc:mysql://localhost:3306/synechron";
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;
	private static PreparedStatement pStatement = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeResources() {

		try {
			if (result != null)
				result.close();
			if (pStatement != null)
				pStatement.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
