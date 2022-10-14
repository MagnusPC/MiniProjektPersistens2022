package ctrl;

import db.*;
import model.*;

public class ProductCtrl {

	private ProductDB productDB;
	private StockDB stockDB;
	
	
	
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
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
