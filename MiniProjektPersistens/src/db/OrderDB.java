package db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Order;

public class OrderDB implements OrderDBIF{

	private static final String insertOrderQ = "INSERT INTO [Order] values(?, ?, ?, ?, ?, ?)";
	private static final String selectTopOrderIdQ = "SELECT MAX(orderId) from Order ";
	
	private PreparedStatement insertOrder;
	private PreparedStatement selectTopOrderId;
	
	public OrderDB() throws SQLException {
		insertOrder = DBConnection.getInstance().getConnection().prepareStatement(insertOrderQ);
		selectTopOrderId = DBConnection.getInstance().getConnection().prepareStatement(selectTopOrderIdQ);
	}

	@Override
	public Order insertOrder(Order newOrder) throws SQLException {
		
		
		LocalDate date = newOrder.getDate();
		String deliveryStatus = newOrder.getDeliveryStatus();
		LocalDate deliveryDate = newOrder.getDeliveryDate();
		String orderType = newOrder.getOrderTypeString();
		int invoiceNumber = newOrder.getInvoiceNumber();
		int customerId = newOrder.getCustomerId();
		
		insertOrder.setDate(1, Date.valueOf(date));
		insertOrder.setString(2, deliveryStatus);
		insertOrder.setDate(3, Date.valueOf(deliveryDate));
		insertOrder.setString(4, orderType);
		insertOrder.setInt(5, invoiceNumber);
		insertOrder.setInt(6, customerId);
		
		insertOrder.execute();
		
		
		newOrder.setOrderId(selectTopOrderId());
		
		return newOrder;
	}

	@Override
	public int selectTopOrderId() throws SQLException {
		
		ResultSet rs = selectTopOrderId.executeQuery(); //Ved ikke om man får den nyeste når transaction ikke er commited
		
		int orderId = rs.getInt("orderId");
		
		return orderId;
	}
	
}
