package com.sync.starter;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.sync.dao.CustomerDAO;
import com.sync.model.Customer;
import com.sync.util.DBUtil;

public class DBOperation {
	public static void main(String[] args) {
		int choice = 0;
		CustomerDAO.getConnection();
		Scanner sc = new Scanner(System.in);
		while (choice != 6) {
			// TODO Auto-generated method stub
			System.out.println("\t\t 1. Add Customer");
			System.out.println("\t\t 2. View Customers");
			System.out.println("\t\t 3. Search Customer");
			System.out.println("\t\t 4. Edit Customer");
			System.out.println("\t\t 5. Delete Customer");
			System.out.println("\t\t 6. Exit");

			System.out.print("Please Enter Your Choice(1-6):");

			choice = sc.nextInt();
			String name = null;
			String address = null;
			String dob = null;
			Date dateOfBirth = null;
			int id = 0;
			
			int d[] = new int[3];
			int i = 0;
			Customer customer = null;

			switch (choice) {
			case 1:
				// ProperJDBC.addData();
				System.out.println("Insert Operation Started!!!!");
				System.out.println("-----------------------------------------------------");
				System.out.print("Please Enter the Name :");
				name = sc.next();
				System.out.print("Please Enter the Address :");
				address = sc.next();
				System.out.print("Please Enter the DOB (YYYY-MM-DD) :");
				dob = sc.next();
				StringTokenizer st = new StringTokenizer(dob, "-");
				
				while (st.hasMoreTokens()) {
					d[i] = Integer.parseInt(st.nextToken());
				}
				dateOfBirth = new Date(d[0], d[1], d[2]);
				customer = new Customer(name, address, dateOfBirth);
				CustomerDAO.addCustomer(customer);
				System.out.println("Insert Operation Done!!!!");
				System.out.println("-----------------------------------------------------");
				break;
			case 2:
				List<Customer> customersList = CustomerDAO.getCustomers();
				for (Customer cust : customersList) {
					System.out.println(cust);
				}
				break;
			case 3:
				System.out.println("-----------------------------------------------------");
				System.out.println("Please Enter the ID for Search:");
				id = sc.nextInt();
				// ProperJDBC.getById(111);
				customer = CustomerDAO.getCustomerById(id);
				System.out.println(customer);
				System.out.println("-----------------------------------------------------");
				break;
			case 4:
				// ProperJDBC.updateData(222);
				System.out.println("Update Operation Started!!!!");
				System.out.println("-----------------------------------------------------");
				System.out.println("Please Enter the ID for Update:");
				id = sc.nextInt();
				System.out.print("Please Enter the Name :");
				name = sc.next();
				System.out.print("Please Enter the Address :");
				address = sc.next();
				System.out.print("Please Enter Date of Birth :");
				dob = sc.next();
				StringTokenizer st1 = new StringTokenizer(dob, "-");
				
				while (st1.hasMoreTokens()) {
					d[i] = Integer.parseInt(st1.nextToken());
				}
				dateOfBirth = new Date(d[0], d[1], d[2]);
				customer = new Customer(name, address, dateOfBirth);
				CustomerDAO.updateCustomer(id, customer);
				System.out.println("Update Operation Done!!!!");
				System.out.println("-----------------------------------------------------");
				break;
			case 5:
				System.out.println("Delete Operation Started!!!!");
				System.out.println("-----------------------------------------------------");
				System.out.println("Please Enter the ID for Delete:");
				id = sc.nextInt();
				CustomerDAO.deleteCustomer(id);
				System.out.println("Delete Operation Done!!!!");
				System.out.println("-----------------------------------------------------");
				break;
			case 6:
				CustomerDAO.closeResources();
				sc.close();
				System.out.println("Thanks For Using this Application. Bye......");
				System.exit(0);
			}
		}
	}
}
