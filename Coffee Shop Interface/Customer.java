package Homework4;

public abstract class Customer {
	private String name;
	private String email;
	private String address;
	
	public Customer() {
		
	}
	public Customer(String name, String email, String address) {
	this.name = name;
	this.email = email;
	this.address = address;
	
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
}
