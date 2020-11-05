package com.sync;

import java.util.Scanner;

public class DBOperation {

	public static void main(String[] args) {
		int choice = 0;
		ProperJDBC1.getConnection();
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
			String email = null;
			long phone = 0;
			int id = 0;

			switch (choice) {
			case 1:
				// ProperJDBC.addData();
				System.out.println("Insert Operation Started!!!!");
				System.out.println("-----------------------------------------------------");
				System.out.print("Please Enter the Name :");
				name = sc.next();
				System.out.print("Please Enter the Email :");
				email = sc.next();
				System.out.print("Please Enter the Phone Number :");
				phone = sc.nextLong();
				ProperJDBC1.addData(name, email, phone);
				System.out.println("Insert Operation Done!!!!");
				System.out.println("-----------------------------------------------------");
				break;
			case 2:
				// ProperJDBC.getAll();
				ProperJDBC1.getAll();
				break;
			case 3:
				System.out.println("-----------------------------------------------------");
				System.out.println("Please Enter the ID for Search:");
				id = sc.nextInt();
				// ProperJDBC.getById(111);
				ProperJDBC1.getById(id);
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
				System.out.print("Please Enter the Email :");
				email = sc.next();
				System.out.print("Please Enter the Phone Number :");
				phone = sc.nextLong();
				ProperJDBC1.updateData(id, name, email, phone);
				System.out.println("Update Operation Done!!!!");
				System.out.println("-----------------------------------------------------");
				break;
			case 5:
				// ProperJDBC.deleteData(111);

				System.out.println("Delete Operation Started!!!!");
				System.out.println("-----------------------------------------------------");
				System.out.println("Please Enter the ID for Delete:");
				id = sc.nextInt();
				ProperJDBC1.deleteData(id);
				System.out.println("Delete Operation Done!!!!");
				System.out.println("-----------------------------------------------------");
				break;
			case 6:
				ProperJDBC1.closeResources();
				sc.close();
				System.out.println("Thanks For Using this Application. Bye......");
				System.exit(0);
			}
		}
	}

}
