package model;

public class OrderLine {
	
	private int orderId;
	private int productId;
	private float soldPrice;
	private int quantity;
	
	public OrderLine(Product p, int quantity) {
		productId = p.getProductID();
		this.quantity = quantity;
		soldPrice = (quantity * calculateSubtotal(p));
	
	}

	public float calculateSubtotal(Product p) {
		return p.getSalePrice();
	}
	
	
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public float getSoldPrice() {
		return soldPrice;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
