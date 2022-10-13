package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;

public class ProductDB implements ProductDBIF {
	//Default visibility, package protected
	Connection con;
	
	//TODO udvælg hvilke kolonner der skal selectes
	private static final String findByID_Q = "SELECT * FROM product p, gunreplica gr, equipment e, clothes c WHERE id = ? AND (p.productID=gr.id OR p.productID=e.id OR p.productID=c.id); ";
	
	private PreparedStatement findByID;
	
	public ProductDB() throws DataAccessException {
		con = DBConnection.getInstance().getConnection();
		try {
			findByID = con.prepareStatement(findByID_Q);
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
		Product p = new Product(rs.getInt("productID"), 
				rs.getString("name"),
				rs.getDouble("purchasePrice"),
				rs.getDouble("salePrice"),
				rs.getDouble("rentPrice"),
				rs.getString("productType"),
				rs.getString("supplierID"),
				/*GunReplica*/
				rs.getString("caliber"),
				rs.getString("material"),
				/*Equipment*/
				rs.getString("type"),
				rs.getString("description"),
				/*Clothes*/
				rs.getString("size"),
				rs.getString("color")
				);
		//TODO skal subklasser i et if/else
		//Skal måske skrives som i update metoden i PersonDB
		return p;
	}
}
