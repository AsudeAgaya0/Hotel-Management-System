package com.source;

public class Room {
	private String type;
	private int roomID;
	private float price;
	private int numberOfBed;
	private int numberOfShower;
	private String view;
	private boolean available = true;
	private Customer customer;

	public Room(String type, int roomID, float price, int numberOfBed, int numberOfShower, String view,
			boolean available, Customer c) {
		this.type = type;
		this.roomID = roomID;
		this.price = price;
		this.numberOfBed = numberOfBed;
		this.numberOfShower = numberOfShower;
		this.view = view;
		this.available = available;
		this.customer = c;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNumberOfBed() {
		return numberOfBed;
	}

	public void setNumberOfBed(int numberOfBed) {
		this.numberOfBed = numberOfBed;
	}

	public int getNumberOfShower() {
		return numberOfShower;
	}

	public void setNumberOfShower(int numberOfShower) {
		this.numberOfShower = numberOfShower;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		String customerID = null;
		if (customer != null)
			customerID = String.valueOf(customer.getSocialID());
		return type +","+roomID+","+String.valueOf(price)+","+String.valueOf(numberOfBed)
				+","+String.valueOf(numberOfShower)+","+ view +","+String.valueOf(available)+","+customerID;
	}
	
	public String[] getAll() {
		String socialID = "null";
		if (customer != null)
			socialID = String.valueOf(customer.getSocialID());

		String[] a = {type,String.valueOf(roomID),String.valueOf(price),String.valueOf(numberOfBed),String.valueOf(numberOfShower),
		view,String.valueOf(available),socialID};
		return a;
	}

}