package com.source;

public class Phone {
	private String countryCode;
	private int code;
	private int number;

	public Phone(String countryCode, int code, int number) {
		this.countryCode = countryCode;
		this.code = code;
		this.number = number;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Phone [countryCode=" + countryCode + ", code=" + code + ", number=" + number + "]";
	}
	

}