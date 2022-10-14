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
	private static final String joinGunReplicaTable_Q = "SELECT Product.productId, [name], purchasePrice, salePrice, rentPrice, productType, supplierId, caliber, material  FROM Product, Gunreplica WHERE Product.productID = GunReplica.productID AND GunReplica.productID = ? ;";
	private static final String joinEquipmentTable_Q = "SELECT Product.productId, [name], purchasePrice, salePrice, rentPrice, productType, supplierId, [type], [description] FROM Product, Equipment WHERE Product.productID = Equipment.productID AND Equipment.productID = ? ;";
	private static final String joinClothesTable_Q = "SELECT Product.productId, [name], purchasePrice, salePrice, rentPrice, productType, supplierId, [size], [color] FROM Product, Clothes WHERE Product.productID = Clothes.productID AND Clothes.productID = ?;";
	
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
				p = buildObject(rs, productID);
			}
			return p;
		}
		catch (SQLException e) {
			throw new DataAccessException(e, "Could not find product by ID");
		}
	}
	
	private Product buildObject(ResultSet rs, int productID) throws SQLException {
		Product p = null;
			if(rs.getString("productType").equals("GunReplica")) {
				//We overwrite the resultset to create a new table
				joinGun.setInt(1, productID);
				rs = joinGun.executeQuery();
				if(rs.next()) {
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
				//We add in the data
				
			}
			else if(rs.getString("productType").equals("Equipment")) {
				joinEquip.setInt(1, productID);
				rs = joinEquip.executeQuery();
				if(rs.next()) {
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
				
			}
			else if(rs.getString("productType").equals("Clothes")) {
				joinCloth.setInt(1, productID);
				rs = joinCloth.executeQuery();
				if(rs.next()) {
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
				
			}
		return p;
	}
}
