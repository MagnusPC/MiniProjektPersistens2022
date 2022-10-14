package model;

public class PrivateCustomer extends Customer{
	
	private String cpr;
	
	public PrivateCustomer(int customerId,String name, String adress, int zipCode, String city, String phoneno, String cpr) {
		super(customerId, name, adress, zipCode, city, phoneno);
		this.setCpr(cpr);
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	
	

}
