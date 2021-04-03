package com.source;

import edu.utexas.se.swing.sample.ReceptionSW;

public class Administrator extends Staff {
	ReceptionSW rAdmin;
	Accounting aAdmin;
	private float totalMoney;

	public Administrator(String name, String surname, int socialID, int staffID, Phone phoneNumber, Address address,
			boolean insurance, float salary, int age, boolean gender, Date bDay, String educationStatus, Date startDay,
			boolean working,String department) {
		super(name, surname, socialID, staffID, phoneNumber, address, insurance, salary, age, gender, bDay,
				educationStatus, startDay, working,department);
		/*rAdmin = new Reception(name, surname, socialID, staffID, phoneNumber, address, insurance, salary, age, gender,
				bDay, educationStatus, startDay, working,department);*/
		aAdmin = new Accounting(name, surname, socialID, staffID, phoneNumber, address, insurance, salary, age, gender,
				bDay, educationStatus, startDay, working,department);
	}

	public void addStaff(Object staff) {/////////////////////////////////////////////////////////
		System.out.println("kn");
	}

	public void deleteStaff(int staffID) {
	}
	
	public ReceptionSW getrAdmin() {
		return rAdmin;
	}

	public void setrAdmin(ReceptionSW rAdmin) {
		this.rAdmin = rAdmin;
	}

	public Accounting getaAdmin() {
		return aAdmin;
	}

	public void setaAdmin(Accounting aAdmin) {
		this.aAdmin = aAdmin;
	}

	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

}