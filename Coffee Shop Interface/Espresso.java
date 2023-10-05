package Homework4;

public class Espresso extends Coffee {

	private int shots;
	
	
	public Espresso() {
		
	}

	public Espresso (int shots, String base, double price) {
		super(null, base, price);
		this.shots = shots;
		
	}
	public void setShots(int shots) {
		this.shots = shots;
	}
	public int getShots() {
		return shots;
	}
	public String toString() {
		return ("Customer info: " + getName() + ", " + getEmail() + ", " + getAddress() + " || Order Info: " + getShots() + " shots of espresso, " + getBase() + ", $" + getPrice());
	}
	public String prepare() {
		return (getShots() + " shots of espresso. " + getBase() + ". " + "Price: $" + getPrice());
	}
}
