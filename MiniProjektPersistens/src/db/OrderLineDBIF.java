package db;

import java.sql.SQLException;
import java.util.ArrayList;

import model.OrderLine;

public interface OrderLineDBIF {
	
	public ArrayList<OrderLine> insertOrderLines(ArrayList<OrderLine> orderLines, int orderId) throws SQLException;

}
