package model;

public class PrivateCustomer extends Customer{
	
	private int cpr;
	
	public PrivateCustomer(String name, String adress, int zipCode, String city, String phoneno, int cpr) {
		super(name, adress, zipCode, city, phoneno);
		this.setCpr(cpr);
	}

	public int getCpr() {
		return cpr;
	}

	public void setCpr(int cpr) {
		this.cpr = cpr;
	}

	
	

}
