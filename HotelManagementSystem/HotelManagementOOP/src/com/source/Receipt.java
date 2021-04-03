package com.source;

public class Receipt {
	
	private int invoiceNO;
	private Date date;
	private float cost;
	private String about;
	private String by;
	
	
	public Receipt(int invoiceNO, Date date, float cost, String about, String by) {
		this.invoiceNO = invoiceNO;
		this.date = date;
		this.cost = cost;
		this.about = about;
		this.by = by;
	}
	
	public int getInvoiceNO() {
		return invoiceNO;
	}
	public void setInvoiceNO(int invoiceNO) {
		this.invoiceNO = invoiceNO;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	
	public String toString(){
		
		
		return String.valueOf(invoiceNO)+","+ date.getString()+","+ String.valueOf(cost)+","+  about+","+ by;
	}
	
	public String[] getAll() {
		String a[] = {String.valueOf(invoiceNO), date.getString(), String.valueOf(cost), about, by};
		return a;
	}
	
	

}
