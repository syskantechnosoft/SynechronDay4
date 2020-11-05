package com.sync;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProperJDBC {
	private static String username = "root";
	private static String password = "root";
	private static String url = "jdbc:mysql://localhost:3306/synechron";
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;
	private static PreparedStatement pStatement = null;

	// insert operation
	public static int addData() {
		int status = 0;
		String query = "INSERT INTO employee (name, email, phone) VALUES (?,?,?)";
		String name = "example";
		String email = "example@gmail.com";
		long phone = 8767785634l;
		try {
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, name);
			pStatement.setString(2, email);
			pStatement.setLong(3, phone);

			status = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	// update operation
	public static int updateData(int id) {
		int status = 0;
		String query = "UPDATE employee SET name=?,email=?,phone=? WHERE id=?";
		String name = "updated1";
		String email = "example1@gmail.com";
		long phone = 8767785634l;
		try {
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, name);
			pStatement.setString(2, email);
			pStatement.setLong(3, phone);
			pStatement.setInt(4, id);
			status = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	// delete operation
	public static int deleteData(int id) {
		int status = 0;
		String query = "DELETE FROM employee WHERE id=" + id;
		try {
			statement = connection.createStatement();
			status = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	// Read One data
	public static void getById(int id) {
		String query = "select * from employee where id=" + id;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			if (result.next()) {
				System.out.println(result.getInt(1) + "\t" + result.getString("name") + "\t" + result.getString("email")
						+ "\t" + result.getLong("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Read all data
	public static void getAll() {
		String query = "select * from employee";
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				System.out.println(result.getInt(1) + "\t" + result.getString("name") + "\t" + result.getString("email")
						+ "\t" + result.getLong("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProperJDBC.getConnection();
		int status= 0;
		//Insert operation
		status = ProperJDBC.addData();
		if(status!=0)
			System.out.println("Successfully Inserted!!!!");
		
		//update operation
		status = ProperJDBC.updateData(5);
		if(status!=0)
			System.out.println("Successfully Updated!!!!");
		
		//getById
		ProperJDBC.getById(5);
		
		//delete
		status = ProperJDBC.deleteData(7);
		if(status!=0)
			System.out.println("Successfully Deleted!!!!");
		//getAll
		 ProperJDBC.getAll();
		
		ProperJDBC.closeResources();
	}

}
