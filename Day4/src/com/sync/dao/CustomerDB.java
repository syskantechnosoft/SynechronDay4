package com.sync.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDB {

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;
	private static PreparedStatement pStatement = null;

	// insert operation
	public static int addCustomer(String name, String address, Date dob) {
		int status = 0;
		String query = "INSERT INTO customer (name, address, dob) VALUES (?,?,?)";
		try {
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, name);
			pStatement.setString(2, address);
			pStatement.setDate(3, dob);

			status = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	// update operation
	public static int updateData(int id,String name, String address, Date dob) {
		int status = 0;
		String query = "UPDATE customer SET name=?,email=?,phone=? WHERE id=?";

		try {
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, name);
			pStatement.setString(2, address);
			pStatement.setDate(3, dob);
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
		String query = "DELETE FROM customer WHERE id=" + id;
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
		String query = "select * from customer where id=" + id;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			if (result.next()) {
				System.out.println(result.getInt(1) + "\t" + result.getString("name") + "\t" + result.getString("address")
						+ "\t" + result.getDate("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Read all data
	public static void getAll() {
		String query = "select * from customer";
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				System.out.println(result.getInt(1) + "\t" + result.getString("name") + "\t" + result.getString("address")
						+ "\t" + result.getDate("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
