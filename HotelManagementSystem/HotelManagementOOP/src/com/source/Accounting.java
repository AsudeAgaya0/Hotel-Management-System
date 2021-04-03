package com.source;

public class Accounting extends Staff {
	private float totalMoney;

	public Accounting(String name, String surname, int socialID, int staffID, Phone phoneNumber, Address address,
			boolean insurance, float salary, int age, boolean gender, Date bDay, String educationStatus, Date startDay,
			boolean working,String department) {
		super(name, surname, socialID, staffID, phoneNumber, address, insurance, salary, age, gender, bDay,
				educationStatus, startDay, working, department);
	}

	public void displayStaffs() {
	}

	public void displayDepartmentMembers(String department) {
	}

	public void calculateIncome() {
	}

	public void calculateExpence() {
	}

	public Staff searchStaff(String name, String surname,Staff staff) {/////////////////////////////////////////////
		return staff;
	}

	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

}