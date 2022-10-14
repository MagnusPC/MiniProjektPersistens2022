package ctrl;

import db.*;
import model.*;

public class ProductCtrl {

	private ProductDB productDB;
	private StockDB stockDB;
	
	
	
	public Product findProductByID(int productId) {
		Product result = null;
		try {
			result = productDB.findProductByID(productId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public void updateStock(Order order) {
		stockDB.updateStock(order);
	}
	
}
