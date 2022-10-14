package model;

public class Club extends Customer {

	private int cvr;

	public Club(int customerId, String name, String adress, int zipCode, String city, String phoneno, int cvr) {
		super(customerId, name, adress, zipCode, city, phoneno);
		this.setCvr(cvr);
	}

	public int getCvr() {
		return cvr;
	}

	public void setCvr(int cvr) {
		this.cvr = cvr;
	}


}
