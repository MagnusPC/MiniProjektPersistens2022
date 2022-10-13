package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
	
	int orderId;
	LocalDate date, deliveryDate;
	String deliveryStatus;
	OrderType orderType; //Lav OrderType klassen
	Invoice invoice;
	Customer customer;
	ArrayList<OrderLine> orderLines;
	
	
	
	public Order() {
		deliveryStatus = "Not Ready";
		orderLines = new ArrayList<>();
		
	}



	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	private void setDate(LocalDate date) {
		this.date = date;
	}



	/**
	 * @return the deliveryDate
	 */
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}



	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	private void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}



	/**
	 * @return the deliveryStatus
	 */
	public String getDeliveryStatus() {
		return deliveryStatus;
	}



	/**
	 * @param deliveryStatus the deliveryStatus to set
	 */
	private void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	/**
	 * @return order type string
	 */
	public String getOrderTypeString() {
		return this.orderType.getType();
	}
	
	/**
	 * 
	 * @return invoice id
	 */
	public int getInvoiceNumber() {
		return this.invoice.getInvoiceId();
	}
	
	/**
	 * 
	 * @return customer id
	 */
	public int getCustomerId() {
		return this.customer.getCustomerId();
	}
	
	
	
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}



	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public ArrayList<OrderLine> getOrderLines(){
		return orderLines;
	}


	/**
	 * @param c the Customer to add
	 */
	public Customer addCustomer(Customer c) {
		this.customer = c;
		
		return this.customer;
	}
	
	/**
	 * @param ol the OrderLine to add
	 */
	
	public OrderLine addOrderLine(OrderLine ol) {
		if(!orderLines.contains(ol)) {
			orderLines.add(ol);
		}
		
		return ol;
	}
	
	/**
	 * @param invoice the Invoice to add
	 */
	
	public Invoice addInvoiceToOrder(Invoice invoice) {
		this.invoice = invoice;
		
		return this.invoice;
	}
	
	/**
	 * Sets deliveryStatus, date and deliveryDate
	 */
	
	public Order finishOrder() {
		setDeliveryStatus("In Transit");
		setDate(LocalDate.now());
		setDeliveryDate(getDate().plusDays(3));
	}
}
