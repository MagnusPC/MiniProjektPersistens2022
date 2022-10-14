package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.OrderLine;

public class OrderLineDB implements OrderLineDBIF {

	private static final String insertOrderLineQ = "INSERT INTO OrderLine values(?, ?, ?, ?)";
	
	private PreparedStatement insertOrderLine;
	
	public OrderLineDB() throws SQLException {
		insertOrderLine = DBConnection.getInstance().getConnection().prepareStatement(insertOrderLineQ);
	}
	
	@Override
	public ArrayList<OrderLine> insertOrderLines(ArrayList<OrderLine> orderLines) throws SQLException {
		
		for (OrderLine orderLine : orderLines) {
			insertOrderLine.setInt(1, orderLine.getOrderId());
			insertOrderLine.setInt(2, orderLine.getProductId());
			insertOrderLine.setDouble(3, orderLine.getSoldPrice());
			insertOrderLine.setInt(4, orderLine.getQuantity());
			
			insertOrderLine.executeQuery();
		}
		
		return orderLines;
	}

}
