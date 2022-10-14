package ctrl;

import java.util.List;

import db.CustomerDB;
import db.DataAccessException;
import model.Customer;




public class CustomerCtrl {
	private CustomerDB customerDB;
	
	public CustomerCtrl() throws DataAccessException {
		customerDB = new CustomerDB();
	}
	
	public List<Customer> findAll() throws DataAccessException {
		return customerDB.findAll();
	}
		
	public Customer findCustomerByPhoneNo(String phoneno) throws DataAccessException {
		return customerDB.findCustomerByPhoneNo(phoneno);
		
	
	

}
}