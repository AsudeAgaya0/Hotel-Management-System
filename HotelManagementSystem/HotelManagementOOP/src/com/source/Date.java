package com.source;

public class Date {
	private int day;
	private int month;
	private int year;
	private String string = null;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public Date(String date) {//"27.04.2020"
		this.string = date;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	public String getString() {
		if (string == null)
			return day+"/"+month+"/"+year;
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	@Override
	public String toString() {
		return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
	}
	
	

}