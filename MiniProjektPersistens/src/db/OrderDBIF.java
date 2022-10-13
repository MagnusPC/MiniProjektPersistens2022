package db;

import java.sql.SQLException;

import model.Order;

public interface OrderDBIF {

	public Order insertOrder(Order newOrder) throws SQLException;
	public int selectTopOrderId() throws SQLException;
	
}
