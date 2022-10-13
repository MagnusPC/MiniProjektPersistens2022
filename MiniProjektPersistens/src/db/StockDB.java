package db;

import model.*;

public class StockDB implements StockDBIF{

	
	
	public void updateStock(Order order) {
		for (int i = 0; i < order.getOrderLines().length(); i++) {
			ol = order.getOrderLines().get(i);
			q = ol.getQuantity();
			pId = ol.getProductId();
			
		}
	}
}
