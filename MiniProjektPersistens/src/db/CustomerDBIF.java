package db;

import java.util.List;

import model.Customer;

public interface CustomerDBIF {
	List<Customer> findAll() throws DataAccessException;
	Customer findCustomerByPhoneNo(String phoneno) throws DataAccessException;
	void update(Customer c) throws DataAccessException;
}
