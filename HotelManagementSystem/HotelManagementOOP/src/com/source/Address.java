package com.source;
public class Address {
	private String street;
	private String town;
	private String city;

	public Address(String street, String town, String city) {
		this.street = street;
		this.town = town;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}