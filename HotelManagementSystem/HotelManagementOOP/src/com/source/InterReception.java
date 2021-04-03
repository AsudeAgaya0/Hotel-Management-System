package com.source;

public interface InterReception {

public void addCustomer(int roomID);
public void deleteCustomer(int roomID);
public void addCustomerToRoom(Customer customer);
public Room searchRoom(int roomID);
public Customer searchCustomer(String name, String surname);
public void displayAvailableRooms();
public void displaySpecificRoom(String type, float price, int numberOfBed, int numberOfShower, String view);
public void displayCustomers();
public void Queue(Customer customer);
}