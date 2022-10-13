package model;

public class Club extends Customer {

	private int cvr;

	public Club(String name, String adress, int zipCode, String city, String phoneno, int cvr) {
		super(name, adress, zipCode, city, phoneno);
		this.setCvr(cvr);
	}

	public int getCvr() {
		return cvr;
	}

	public void setCvr(int cvr) {
		this.cvr = cvr;
	}


}
