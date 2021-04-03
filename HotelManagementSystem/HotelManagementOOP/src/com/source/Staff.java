package com.source;

public class Staff extends AbsStaff{
	private String name;
	private String surname;
	private int socialID;
	private int staffID;
	private Phone phoneNumber;
	private Address address;
	private boolean insurance;
	private float salary;
	private int age;
	private boolean gender;
	private Date bDay;
	private String educationStatus;
	private Date startDay;
	private boolean working;// 1=working, 0=not working
	private String department;
	

	public Staff(String name, String surname, int socialID, int staffID, Phone phoneNumber, Address address,
			boolean insurance, float salary, int age, boolean gender, Date bDay, String educationStatus, Date startDay,
			boolean working,String department) {
		this.name = name;
		this.surname = surname;
		this.socialID = socialID;
		this.staffID = staffID;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.insurance = insurance;
		this.salary = salary;
		this.age = age;
		this.gender = gender;
		this.bDay = bDay;
		this.educationStatus = educationStatus;
		this.startDay = startDay;
		this.working = working;
		this.department=department;
	}

	
	@Override
	public String getDepartment() {
		return department;
	}

	@Override
	public void setDepartment(String department) {
		this.department = department;
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

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
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
	

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getbDay() {
		return bDay;
	}

	public void setbDay(Date bDay) {
		this.bDay = bDay;
	}

	public String getEducationStatus() {
		return educationStatus;
	}

	public void setEducationStatus(String educationStatus) {
		this.educationStatus = educationStatus;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}

	@Override
	public String toString() {
		return  name+","+surname+","+socialID+","+staffID
				+","+phoneNumber.getCountryCode()+phoneNumber.getCode()+phoneNumber.getNumber()+","+
				address.getCity()+"-"+address.getTown()+"-"+address.getStreet()+","+String.valueOf(insurance)+","+
				String.valueOf(salary)+","+String.valueOf(age)+","+String.valueOf(gender)+","+bDay.getString()+","+
				educationStatus+","+startDay.getString()+","+String.valueOf(working)+","+department;
	}

	public String[] getAll() {
		String a[] = {name,surname,String.valueOf(socialID),String.valueOf(staffID),
    			getPhoneNumber().getCountryCode()+getPhoneNumber().getCode()+getPhoneNumber().getNumber(),
    			getAddress().getCity()+"-"+getAddress().getTown()+"-"+getAddress().getStreet(), String.valueOf(insurance),
    			String.valueOf(salary),String.valueOf(age),
    			String.valueOf(gender), getbDay().getString(), getEducationStatus(), getStartDay().getString(),
    			String.valueOf(isWorking()), getDepartment()};
		return a;
	}


	

	

}