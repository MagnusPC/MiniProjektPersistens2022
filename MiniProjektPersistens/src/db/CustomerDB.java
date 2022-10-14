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
	
	private static final String findAllQ =
			"select, id name, adress, zipCode, city, phoneno";
	private static final String findByPhonoNoQ =
			findAllQ + " where phoneno = ?";
	private static final String updateQ = 
			"update persons set name = ?, adress = ?, zipCode = ? , city = ?, phoneno = ?";
	
	private PreparedStatement findAll, findByPhoneNo, update;
	
	public CustomerDB() throws DataAccessException {
		try {
			findAll = DBConnection.getInstance().getConnection()
					.prepareStatement(findAllQ);
			findByPhoneNo = DBConnection.getInstance().getConnection()
					.prepareStatement(findByPhonoNoQ); 
			update = DBConnection.getInstance().getConnection() 
					.prepareStatement(updateQ);	
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
	public Customer findCustomerByPhoneNo(String phoneno) throws DataAccessException {
		try {
			findByPhoneNo.setString(1, phoneno);
			ResultSet rs = findByPhoneNo.executeQuery();
		
			Customer c = null;
			if(rs.next() && (rs.getString("cvr") == "null")) {
				c = buildPrivateCustomerObject(rs); }
			else {
				c = buildClubObject(rs);
			}
			return c;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by Phonenumber = " + phoneno);
		}
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



