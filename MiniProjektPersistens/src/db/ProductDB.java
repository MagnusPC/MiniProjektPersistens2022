package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.GunReplica;
import model.Product;

public class ProductDB implements ProductDBIF {
	//Default visibility, package protected
	Connection con;
	
	//TODO lav join table - evt direkte i db
//	private static final String tableEach = "CREATE ; ";
	//TODO udvælg hvilke kolonner der skal selectes
	private static final String findProductTypeByID_Q = "SELECT productType FROM product WHERE productID = ?; ";
//	join tabeller
	
	private PreparedStatement findByID;
	
	public ProductDB() throws DataAccessException {
		con = DBConnection.getInstance().getConnection();
		try {
			findByID = con.prepareStatement(findProductIDByID_Q);
		}
		catch(SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	
	public Product findProductByID(int productID) throws DataAccessException {
		try {
			findByID.setInt(1, productID);
			ResultSet rs = findByID.executeQuery();
			Product p = null;
			if(rs.next()) {
				p = buildObject(rs);
			}
			return p;
		}
		catch (SQLException e) {
			throw new DataAccessException(e, "Could not find prod by ID");
		}
	}
	
	private Product buildObject(ResultSet rs) throws SQLException {
		Product p = null;
//		Product p = new Product(rs.getInt("productID"), 
//				rs.getString("name"),
//				rs.getDouble("purchasePrice"),
//				rs.getDouble("salePrice"),
//				rs.getDouble("rentPrice"),
//				rs.getString("productType"),
//				rs.getString("supplierID"));
			if(rs.getString("productType")=="GunReplica") {
				p = new GunReplica(rs.getString("caliber"),
						rs.getString("material"),
						rs.getString("name"),
						rs.getDouble("purchasePrice"),
						rs.getDouble("salePrice"),
						rs.getDouble("rentPrice"),
						rs.getString("productType"),
						rs.getInt("supplierID"));
			}
			else if(rs.getString("productType")=="Equipment") {
				p = new GunReplica(rs.getString("type"),
						rs.getString("description"),
						rs.getString("name"),
						rs.getDouble("purchasePrice"),
						rs.getDouble("salePrice"),
						rs.getDouble("rentPrice"),
						rs.getString("productType"),
						rs.getInt("supplierID"));
			}
			else if
		return p;
	}
}
