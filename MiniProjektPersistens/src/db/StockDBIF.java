package db;

import model.Order;

public interface StockDBIF {
	void updateStock(Order order) throws DataAccessException;

}
