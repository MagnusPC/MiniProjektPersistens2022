package db;

import model.*;

public class StockDB implements StockDBIF{

	
	
	public void updateStock(Order order) {
		String sqlQuery = "";
		for (int i = 0; i < order.getOrderLines().length(); i++) {
			OrderLine ol = order.getOrderLines().get(i);
			int q = ol.getQuantity();
			int pId = ol.getProductId();
			
			String sqlQuery2 = ("select stockId from Product where serialNumber = " + pId);
		}
	}
}
