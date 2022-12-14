package model;

public abstract class  Customer {

		private int customerId;
		private String name;
		private String adress;
		private int zipCode;
		private String city;
		private String phoneno;
		
		
		public Customer(int customerId, String name, String adress, int zipCode, String city, String phoneno) {
			this.customerId  = customerId;
			this.name = name;
			this.adress = adress;
			this.zipCode = zipCode;
			this.city = city;
			this.phoneno = phoneno;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getAdress() {
			return adress;
		}


		public void setAdress(String adress) {
			this.adress = adress;
		}


		public int getZipCode() {
			return zipCode;
		}


		public void setZipCode(int zipCode) {
			this.zipCode = zipCode;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public String getPhoneno() {
			return phoneno;
		}


		public void setPhoneno(String phoneno) {
			this.phoneno = phoneno;
		}


		public int getCustomerId() {
			return customerId;
		}


		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		
		

}
