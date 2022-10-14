package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Club;
import model.Customer;
import model.PrivateCustomer;


public class CustomerDB implements CustomerDBIF{
	
	private static final String findAllQ = "SELECT id, fname, lname, address, zipCode, phoneNo, cpr, cvr FROM Customer";
	private static final String findByPhoneNoQ = findAllQ + " where phoneNo = '?'";
	//private static final String updateQ = "update persons set name = ?, adress = ?, zipCode = ? , city = ?, phoneNo = ?";
	
	private PreparedStatement findAll, findByPhoneNo, update;
	
	public CustomerDB() throws DataAccessException {
		try {
			System.out.println("Fejl 1");
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findByPhoneNo = DBConnection.getInstance().getConnection().prepareStatement(findByPhoneNoQ); 
			System.out.println("Fejl 2");
			//update = DBConnection.getInstance().getConnection().prepareStatement(updateQ);	
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	
	@Override
	public List<Customer> findAll() throws DataAccessException {
			ResultSet rs;
			try {
				rs = findAll.executeQuery();
				List<Customer> res = buildObjects(rs);
				return res;
			} catch (SQLException e) {
				throw new DataAccessException(e, "Could not retrieve all persons");
			}
	}

	@Override
	public Customer findCustomerByPhoneNo(String phoneNo) /*throws DataAccessException*/ {
		Customer c = null;
		try {
			findByPhoneNo.setString(1, phoneNo);
			System.out.println("Fejl 3");
			ResultSet rs = findByPhoneNo.executeQuery();
			System.out.println("Fejl 4");
		
			
			while(rs.next()) {
				System.out.println(rs.getString("fname"));
				/*
				if(rs.getInt("cvr") != 0) {
					System.out.println("Fejl 3");
					c = buildPrivateCustomerObject(rs); 
					
					}
				else {
					System.out.println("Fejl 4");
					c = buildClubObject(rs);
					
				}
				*/
			}
			
			
		} catch (SQLException e) {
			System.out.println(e);
			//throw new DataAccessException(e, "Could not find by Phonenumber = " + phoneNo);
		}
		return c;
	}
	

	@Override
	public void update(Customer c) throws DataAccessException {
		final String name = c.getName();
		final String adress = c.getAdress();
		final int zipCode = c.getZipCode();
		final String city = c.getCity();
		final String phoneNo = c.getPhoneno();
		try {
			//update person set 
			//name = ?, email = ?, phone = ? , 
			//birth_date = ?, groups_id = ? where id = ?"
			update.setString(1, name);
			update.setString(2, adress);
			update.setInt(3, zipCode);
			update.setString(4, city);
			update.setString(5, phoneNo);
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not update person where Phonenumber = " + phoneNo);
		}

	}

	private PrivateCustomer buildPrivateCustomerObject(ResultSet rs) throws SQLException {
		PrivateCustomer pc = new PrivateCustomer(
				rs.getInt("customerId"),
				rs.getString("name"),
				rs.getString("adress"),
				rs.getInt("zipCode"),
				rs.getString("city"),
				rs.getString("phoneno"), 
				rs.getInt("cpr"));
				
					
				;
		return pc;
	}
	
	private Club buildClubObject(ResultSet rs) throws SQLException {
		Club clb = new Club(
				rs.getInt("customerId"),
				rs.getString("name"),
				rs.getString("adress"),
				rs.getInt("zipCode"),
				rs.getString("city"),
				rs.getString("phoneno"), 
				rs.getInt("cvr"));
				
					
				;
		return clb;
	}

	private List<Customer> buildObjects(ResultSet rs) throws SQLException {
		List<Customer> res = new ArrayList<>();
		while(rs.next()) {
			if (rs.getString("cvr") == "null") 
				res.add(buildPrivateCustomerObject(rs));
			else 
				res.add(buildClubObject(rs));
		}
		return res;
	}
		
}



