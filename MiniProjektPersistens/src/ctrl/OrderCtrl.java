package ctrl;

import java.sql.Connection;
import java.sql.SQLException;

import db.DBConnection;
import db.DataAccessException;
import db.InvoiceDB;
import db.InvoiceDBIF;
import db.OrderDB;
import db.OrderDBIF;
import db.OrderLineDB;
import db.OrderLineDBIF;
import model.Customer;
import model.Invoice;
import model.Order;
import model.OrderLine;
import model.Product;

public class OrderCtrl {
	
	private ProductCtrl pCtrl;
	private CustomerCtrl cCtrl;
	private OrderLineDBIF orderLineDB;
	private OrderDBIF orderDB;
	private InvoiceDBIF invoiceDB;
	private DBConnection dbCon;
	private Order newOrder;
    private OrderLine ol;
	
	public OrderCtrl() throws SQLException, DataAccessException {
		pCtrl = new ProductCtrl();
		cCtrl = new CustomerCtrl();
		orderLineDB = new OrderLineDB();
		orderDB = new OrderDB();
		invoiceDB = new InvoiceDB();
		dbCon = DBConnection.getInstance();
		ol = null;
		
	}
	
	public void createNewOrder() {
		newOrder = new Order();
	}
	
	public Customer addCustomerByPhoneNo(String phone) throws DataAccessException {
		Customer c = null;
		c = cCtrl.findCustomerByPhoneNo(phone);
		newOrder.addCustomer(c);
		
		return c;
	}
	
	public OrderLine addProductByProductId(int productId, int quantity) {
		Product p = pCtrl.findProductByProductId(productId);
		ol = null;
		if(quantity > 0) {
		    ol = new OrderLine(p, quantity);
		}
		newOrder.addOrderLine(ol);
		
		return ol;
	}
	
	public Invoice addInvoice() {
	    Invoice invoice = null;
	    if(ol!=null) {
	        invoice = new Invoice();
	        newOrder.addInvoiceToOrder(invoice);
	    }
	    return invoice;
	}
	
	public Order finishOrder() throws DataAccessException {
		newOrder.finishOrder();
		
		try {
			dbCon.startTransaction();
			
			invoiceDB.addInvoice(newOrder.getInvoice());
			orderDB.insertOrder(newOrder);
			orderLineDB.insertOrderLines(newOrder.getOrderLines(), newOrder.getOrderId());
			pCtrl.updateStock(newOrder);
			
			dbCon.commitTransaction();
		} catch (SQLException e) {
		    try {
                dbCon.rollbackTransaction();
                //dbCon.
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		    throw new DataAccessException(e, "Error could not finish order");
		}
		
		return newOrder;
	}
	
	//Only for test class
	public OrderDBIF getOrderDB() {
	    return orderDB;
	}
	

}
