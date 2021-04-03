package com.source;

public class Reception extends Staff implements InterReception{

	public Reception(String name, String surname, int socialID, int staffID, Phone phoneNumber, Address address,
			boolean insurance, float salary, int age, boolean gender, Date bDay, String educationStatus, Date startDay,
			boolean working,String department) {
		super(name, surname, socialID, staffID, phoneNumber, address, insurance, salary, age, gender, bDay,
				educationStatus, startDay, working, department);
	}

	public void addCustomer(int roomID) {
	}

	///
	public void deleteCustomer(int roomID) {
	}

	///
	public void displayCustomers() {
	}
	public void addCustomerToRoom(Customer customer) {
	}

	public Room searchRoom(int roomID) {/////////////////////////////////////////////////
		
		return null;
	}// Room room

	public Customer searchCustomer(String name, String surname) {//////////////////////////////////////////////
		Date dd = new Date(27,4,2020);
		Date ld = new Date(28,4,2020);
		Phone p = new Phone ("+90",507,444);
		Address ad = new Address("hamidiye","cekmekoy","istanbul");
		float f = 504.3f;
		Customer customer = new Customer("Nergis", "bela",151,30,dd,ld,f,5,p,ad,true);
		return customer;
	}
	public void displayAvailableRooms() {
	}

	public void displaySpecificRoom(String type, float price, int numberOfBed, int numberOfShower, String view) {
	}

	public void Queue(Customer customer) {
	}

}