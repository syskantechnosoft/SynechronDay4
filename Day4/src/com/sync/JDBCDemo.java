package com.sync;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) throws Exception {
		String user = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/synechron";
		// Step 1 : Loading & Registering the Driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Step 2 : Establishing the connection
		Connection connection = DriverManager.getConnection(url, user, password);

		// Step 3 : creating the query
		Statement stmt = connection.createStatement();

		String selectQuery = "select * from employee";
		String insertQuery = "INSERT INTO employee (name, email, phone) VALUES ('new', 'new@gmail.com', '9878678756')";
		String deleteQuery = "DELETE FROM employee WHERE id = 6";
		String updateQuery = "UPDATE employee  SET name = 'ABC123' WHERE id=1";
		// Step 4 : Execute query & process the result
		// insert Operation
		int insertStatus = 0;
		insertStatus = stmt.executeUpdate(insertQuery);

		if (insertStatus != 0)
			System.out.println("1 record successfully inserted!!!");

		// update operation
		int updateStatus = 0;
		updateStatus = stmt.executeUpdate(updateQuery);

		if (updateStatus != 0)
			System.out.println("1 record successfully updated!!!");

		ResultSet rs = stmt.executeQuery(selectQuery);

		while (rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getString("name") + "\t" + rs.getString("email") + "\t"
					+ rs.getLong("phone"));
		}

		// delete operation
		int deleteStatus = 0;
		deleteStatus = stmt.executeUpdate(deleteQuery);

		if (deleteStatus != 0)
			System.out.println("1 record successfully deleted!!!");

		// step 5: closing the resources
		rs.close();
		stmt.close();
		connection.close();

	}

}
