package model;

public class OrderLine {
	
	private int id;
	private int orderId;
	private int productId;
	private float soldPrice;
	private int quantity;
	
	public OrderLine(int id, int orderId, int productId, float soldPrice, int quantity) {
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.soldPrice = soldPrice;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public int getProductId() {
		return productId;
	}
}
