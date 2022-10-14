package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.GunReplica;
import model.Product;

public class ProductDB implements ProductDBIF {
	Connection con; //Default visibility, package protected
	
	//We find the product type
	private static final String findProductTypeByID_Q = "SELECT productType FROM product WHERE productID = ? ; ";
	//We join each subtable to their super
	private static final String joinGunReplicaTable_Q = "SELECT * FROM Product, Gunreplica WHERE productID = ? ;";
	private static final String joinEquipmentTable_Q = "SELECT * FROM Product, Equipment WHERE productID = ? ;";
	private static final String joinClothesTable_Q = "SELECT * FROM Product, Clothes WHERE productID = ? ;";
	
	private PreparedStatement findByID, joinGun, joinEquip, joinCloth;
	
	public ProductDB() throws DataAccessException {
		con = DBConnection.getInstance().getConnection();
		try {
			findByID = con.prepareStatement(findProductTypeByID_Q);
			joinGun = con.prepareStatement(joinGunReplicaTable_Q);
			joinEquip = con.prepareStatement(joinEquipmentTable_Q);
			joinCloth = con.prepareStatement(joinClothesTable_Q);
		}
		catch(SQLException e) {
			throw new DataAccessException(e, "Could not prepare statements");
		}
	}
	
	@Override
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
			throw new DataAccessException(e, "Could not find product by ID");
		}
	}
	
	private Product buildObject(ResultSet rs) throws SQLException {
		Product p = null;
			if(rs.getString("productType")=="GunReplica") {
				//We overwrite the resultset to create a new table
				rs = joinGun.executeQuery();
				//We add in the data
				p = new GunReplica(rs.getString("caliber"),
						rs.getString("material"),
						rs.getInt("productID"),
						rs.getString("name"),
						rs.getFloat("purchasePrice"),
						rs.getFloat("salePrice"),
						rs.getFloat("rentPrice"),
						rs.getString("productType"),
						rs.getInt("supplierID"));
			}
			else if(rs.getString("productType")=="Equipment") {
				rs = joinEquip.executeQuery();
				p = new GunReplica(rs.getString("type"),
						rs.getString("description"),
						rs.getInt("productID"),
						rs.getString("name"),
						rs.getFloat("purchasePrice"),
						rs.getFloat("salePrice"),
						rs.getFloat("rentPrice"),
						rs.getString("productType"),
						rs.getInt("supplierID"));
			}
			else if(rs.getString("productType")=="Clothes") {
				rs = joinCloth.executeQuery();
				p = new GunReplica(rs.getString("size"),
						rs.getString("color"),
						rs.getInt("productID"),
						rs.getString("name"),
						rs.getFloat("purchasePrice"),
						rs.getFloat("salePrice"),
						rs.getFloat("rentPrice"),
						rs.getString("productType"),
						rs.getInt("supplierID"));
			}
		return p;
	}
}
