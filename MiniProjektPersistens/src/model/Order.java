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
	
//	/**
//	 * 
//	 * @return invoice id
//	 */
//	public int getInvoiceNumber() {
//		return this.invoice.getInvoiceId();
//	}
	
	/**
	 * 
	 * @return customer id
	 */
	public int getCustomerId() {
		return this.customer.getCustomerId();
	}
	
	/**
	 * 
	 * 
	 */
	public Invoice getInvoice() {
		return this.invoice;
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
	 * 
	 */
	
	private void addOrderType(OrderType ot) {
		this.orderType = ot;
	}
	
	/**
	 * @param invoice the Invoice to add
	 */
	
	public Invoice addInvoiceToOrder(Invoice invoice) {
		double totalSalePrice = calculateTotal();
		invoice.setInvoiceAmount(totalSalePrice);
		this.invoice = invoice;
		
		return this.invoice;
	}
	
	private double calculateTotal() {
		double result = 0;
		
		for (OrderLine orderLine : orderLines) {
			result += orderLine.getSoldPrice();
		}
		
		if(customer.getClass().equals(Club.class) && result >= 1500) {
			result = result * 0.85;
		}
		else if(customer.getClass().equals(PrivateCustomer.class) && (result <= 2500 && result > 0)) {
			result += 45;
		}
		
		
		return result;
	}



	/**
	 * Sets deliveryStatus, date and deliveryDate
	 */
	
	public void finishOrder() {
		OrderType ot = new OrderType("Sale", "sold through order");
		addOrderType(ot);
		
		setDeliveryStatus("In Transit");
		setDate(LocalDate.now());
		setDeliveryDate(getDate().plusDays(3));
		
	}
}
