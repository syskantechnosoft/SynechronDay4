package com.sync.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sync.model.Customer;

public class CustomerDAO {
	private static String username = "root";
	private static String password = "root";
	private static String url = "jdbc:mysql://localhost:3306/synechron";

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;
	private static PreparedStatement pStatement = null;

	public static void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		return connection;
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

	// insert operation
	public static int addCustomer(Customer customer) {
		int status = 0;
		String query = "INSERT INTO customer (name, address, dob) VALUES (?,?,?)";
		try {
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, customer.getName());
			pStatement.setString(2, customer.getAddress());
			pStatement.setDate(3, customer.getDateOfBirth());

			status = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	// update operation
	public static int updateCustomer(int id, Customer customer) {
		int status = 0;
		String query = "UPDATE customer SET name=?,address=?,dob=? WHERE id=?";

		try {
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, customer.getName());
			pStatement.setString(2, customer.getAddress());
			pStatement.setDate(3, customer.getDateOfBirth());
			pStatement.setInt(4, id);
			status = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	// delete operation
	public static int deleteCustomer(int id) {
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
	public static Customer getCustomerById(int id) {
		String query = "select * from customer where id=" + id;
		Customer customer = new Customer();
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			if (result.next()) {
				customer.setId(result.getInt(1));
				customer.setName(result.getString(2));
				customer.setAddress(result.getString(3));
				customer.setDateOfBirth(result.getDate(4));
//				System.out.println(result.getInt(1) + "\t" + result.getString("name") + "\t"
//						+ result.getString("address") + "\t" + result.getDate("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	// Read all data
	public static List<Customer> getCustomers() {
		String query = "select * from customer";
		List<Customer> customersList = new ArrayList<Customer>();
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				Customer customer = new Customer();
				customer.setId(result.getInt(1));
				customer.setName(result.getString(2));
				customer.setAddress(result.getString(3));
				customer.setDateOfBirth(result.getDate(4));
				customersList.add(customer);
//				System.out.println(result.getInt(1) + "\t" + result.getString("name") + "\t"
//						+ result.getString("address") + "\t" + result.getDate("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customersList;
	}
}
