package com.source;

public class Customer {
	private String name;
	private String surname;
	private int socialID;
	private int age;
	private Date departureDay;
	private Date ArrivalDay;
	private float totalPrice = 0;
	private int roomID;
	private Phone phoneNumber;
	private Address address;
	private boolean gender;

	public Customer(String name, String surname, int socialID, int age, Date departureDay, Date arrivalDay,
			float totalPrice, int roomID, Phone phoneNumber, Address address, boolean gender) {
		this.name = name;
		this.surname = surname;
		this.socialID = socialID;
		this.age = age;
		this.departureDay = departureDay;
		this.ArrivalDay = arrivalDay;
		this.totalPrice = totalPrice;
		this.roomID = roomID;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getSocialID() {
		return socialID;
	}

	public void setSocialID(int socialID) {
		this.socialID = socialID;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDepartureDay() {
		return departureDay;
	}

	public void setDepartureDay(Date departureDay) {
		this.departureDay = departureDay;
	}

	public Date getArrivalDay() {
		return ArrivalDay;
	}

	public void setArrivalDay(Date arrivalDay) {
		this.ArrivalDay = arrivalDay;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public Phone getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Phone phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return  name+","+surname+","+socialID+","+age
				+","+departureDay.getString()+","+ArrivalDay.getString()+","+totalPrice
				+","+roomID +","+phoneNumber.getCountryCode()+phoneNumber.getCode()+phoneNumber.getNumber()+","+
			    address.getStreet()+"-"+address.getTown()+"-"+address.getCity()+","+ gender;
	}
	
	public String[] getAll() {
		String a[] = {name,surname,String.valueOf(socialID),String.valueOf(age),
				departureDay.getString(),ArrivalDay.getString(),String.valueOf(totalPrice),
				String.valueOf(roomID), phoneNumber.getCountryCode()+phoneNumber.getCode()+phoneNumber.getNumber(),
				address.getCity()+"-"+address.getTown()+"-"+address.getStreet(), String.valueOf(gender)};
		return a;
	}
	
	public void Money(float money) {
		this.totalPrice += money;
	}

}