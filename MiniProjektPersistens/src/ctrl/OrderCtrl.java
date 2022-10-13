package ctrl;

import java.sql.Connection;

import db.DBConnection;
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
	
	public OrderCtrl() {
		pCtrl = new ProductCtrl();
		cCtrl = new CustomerCtrl();
		orderLineDB = new OrderLineDB();
		orderDB = new OrderDB();
		invoiceDB = new InvoiceDB();
		dbCon = DBConnection.getInstance();
		
	}
	
	public void createNewOrder() {
		newOrder = new Order();
	}
	
	public Customer addCustomerByPhoneNo(String phone) {
		Customer c = null;
		c = cCtrl.findCustomerByPhoneNo(phone);
		newOrder.addCustomer(c);
		
		return c;
	}
	
	public OrderLine addProductByProductId(int productId) {
		OrderLine ol = pCtrl.findProductByProductId(productId);
		newOrder.addOrderLine(ol);
		
		return ol;
	}
	
	public Invoice addInvoice() {
		Invoice invoice = new Invoice(/* Hvad der skal bruges for at lave en invoice */);
		newOrder.addInvoiceToOrder(invoice);
		
		return invoice;
	}
	
	public Order finishOrder() {
		newOrder.finishOrder();
		dbCon.startTransaction();
		
		invoiceDB.addInvoice(newOrder.getInvoice());
		orderDB.insertOrder(newOrder);
		orderLineDb.insertOrderLines(newOrder.getOrderLines());
		pCtrl.updateStock(newOrder);
		
		dbCon.commitTransaction();
		
		return newOrder;
	}
	

}
