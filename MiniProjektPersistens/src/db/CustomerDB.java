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
	
	private static final String findAllQ = "SELECT id, fname, lname, [address], zipCode, phoneNo, cpr, cvr FROM Customer";
	private static final String findByPhonoNoQ = findAllQ + " WHERE phoneNo = ?";
	//private static final String updateQ = "update persons set name = ?, adress = ?, zipCode = ? , city = ?, phoneno = ?";
	private static final String getCityQ = "SELECT city FROM zipCodeCity where zipCode = ?";
	
	private PreparedStatement findAll, findByPhoneNo, update, getCity;
	
	public CustomerDB() throws DataAccessException {
		try {
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findByPhoneNo = DBConnection.getInstance().getConnection().prepareStatement(findByPhonoNoQ); 
			getCity = DBConnection.getInstance().getConnection().prepareStatement(getCityQ);
			
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
	public Customer findCustomerByPhoneNo(String phoneNo) throws DataAccessException {
		Customer c = null;
		try {
			findByPhoneNo.setString(1, phoneNo);
			ResultSet rs = findByPhoneNo.executeQuery();
			
		
			while(rs.next()) {
				if((rs.getString("cvr") == null)) {
					c = buildPrivateCustomerObject(rs); }
				else {
					c = buildClubObject(rs);
				}
			}
			
			
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by Phonenumber = " + phoneNo);
		}
		return c;
	}
	

	@Override
	public void update(Customer c) throws DataAccessException {
		final String name = c.getName();
		final String adress = c.getAdress();
		final int zipCode = c.getZipCode();
		final String city = c.getCity();
		final String phoneno = c.getPhoneno();
		try {
			//update person set 
			//name = ?, email = ?, phone = ? , 
			//birth_date = ?, groups_id = ? where id = ?"
			update.setString(1, name);
			update.setString(2, adress);
			update.setInt(3, zipCode);
			update.setString(4, city);
			update.setString(5, phoneno);
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not update person where Phonenumber = " + phoneno);
		}

	}

	private PrivateCustomer buildPrivateCustomerObject(ResultSet rs) throws SQLException {
		
		
		
		int id = rs.getInt("id");
		String fName = rs.getString("fName");
		String lName = rs.getString("lName");
		String name = fName + " " + lName;
		String address = rs.getString("address");
		int zipCode = rs.getInt("zipCode");
		String phoneNo = rs.getString("phoneNo");
		String cpr = rs.getString("cpr");
		
			
		
		
		
		
		getCity.setInt(1, zipCode);
		rs = getCity.executeQuery();
		
		String city = null;
		
		if(rs.next()) {
			city = rs.getString("city");
		}
		
		
		PrivateCustomer pc = new PrivateCustomer(id, name, address, zipCode, city, phoneNo, cpr);
				
		return pc;
	}
	
	private Club buildClubObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String fName = rs.getString("fName");
		String lName = rs.getString("lName");
		String name = fName + " " + lName;
		String address = rs.getString("address");
		int zipCode = rs.getInt("zipCode");
		String phoneNo = rs.getString("phoneNo");
		String cvr = rs.getString("cvr");
		
			
		
		
		
		
		getCity.setInt(1, zipCode);
		rs = getCity.executeQuery();
		
		String city = null;
		
		if(rs.next()) {
			city = rs.getString("city");
		}
		
		
		Club clb = new Club(id, name, address, zipCode, city, phoneNo, cvr);
				
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



