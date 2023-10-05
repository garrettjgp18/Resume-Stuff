package Homework4;

public class Coffee extends Customer {
	
	private String size;
	private String base;
	private double price;
	
	public Coffee() {
		
	}
	public Coffee (String size, String base, double price) {
		this.size = size;
		this.base = base;
		this.price = price;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	public String getSize() {
		return size;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getBase() {
		return base;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	public String toString() { //for text file
		return ("Customer info: " + getName() + ", " + getEmail() + ", " + getAddress() + " || Order Info: " + getSize() + " coffee, "+ getBase() + ", " + "$" + getPrice());
	}
	public String prepare() {
		return ("Type: " + getSize() + " coffee. Base: " + getBase() + ". Price: $" + getPrice());
	}
	
}
