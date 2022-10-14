package model;

public class Club extends Customer {

	private String cvr;

	public Club(int customerId, String name, String adress, int zipCode, String city, String phoneno, String cvr) {
		super(customerId, name, adress, zipCode, city, phoneno);
		this.setCvr(cvr);
	}

	public String getCvr() {
		return cvr;
	}

	public void setCvr(String cvr) {
		this.cvr = cvr;
	}


}
