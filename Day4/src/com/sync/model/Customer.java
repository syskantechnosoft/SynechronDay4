package com.sync.model;

import java.sql.Date;

public class Customer {
	private int id;
	private String name;
	private String address;
	private Date dateOfBirth;

	public Customer() {
		super();
	}
	
	public Customer( String name, String address, Date dateOfBirth) {
		super();
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}

	public Customer(int id, String name, String address, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", dateOfBirth=" + dateOfBirth + "]";
	}

}
