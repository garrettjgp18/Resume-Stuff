package Homework4;

public class Mocha extends Espresso{
	
	private String syrup;
	
	public Mocha() {
		
	}
	
	public Mocha(int shots, String syrup, String base, double price) {
		super(shots, base, price);
		this.syrup = syrup;
	}
	public void setSyrup(String syrup) {
		this.syrup = syrup;
	}
	public String getSyrup() {
		return syrup;
	}
	public String toString() {
		return ("Customer info: " + getName() + ", " + getEmail() + ", " + getAddress() + " || Order Info: " + getShots() + " mocha shots, " + getSyrup() + " syrup, " + getBase() + ", $" + getPrice());
	}
	public String prepare() {
		return ("Mocha with " + getShots() +" shots. " + getSyrup() + " syrup. " + getBase() + ". Price: $" + getPrice());
	}
}
