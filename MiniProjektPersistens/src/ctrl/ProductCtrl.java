package ctrl;

import java.sql.SQLException;

import db.*;
import model.*;

public class ProductCtrl {

	private ProductDBIF productDB;
	private StockDBIF stockDB;
	
	
	public ProductCtrl() throws DataAccessException {
		productDB = new ProductDB();
		try {
            stockDB = new StockDB();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public Product findProductByProductId(int productId) {
		Product result = null;
		try {
			result = productDB.findProductByID(productId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public void updateStock(Order order) {
		try {
			stockDB.updateStock(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
