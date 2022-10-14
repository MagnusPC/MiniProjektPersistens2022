package model;

public class PrivateCustomer extends Customer{
	
	private int cpr;
	
	public PrivateCustomer(int customerId,String name, String adress, int zipCode, String city, String phoneno, int cpr) {
		super(customerId, name, adress, zipCode, city, phoneno);
		this.setCpr(cpr);
	}

	public int getCpr() {
		return cpr;
	}

	public void setCpr(int cpr) {
		this.cpr = cpr;
	}

	
	

}
