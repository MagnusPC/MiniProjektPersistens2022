package db;

import java.sql.SQLException;

import model.Order;

public interface StockDBIF {
	void updateStock(Order order) throws DataAccessException, SQLException;

}
