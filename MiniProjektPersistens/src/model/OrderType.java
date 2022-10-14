package model;

public class OrderType {

	private String type;
	private String description;
	
	public OrderType(String type, String description) {
		this.type = type;
		this.description = description;
	}
	
	public String getType() {
		return type;
	}
	
	public String getDescription() {
		return description;
	}
	
}
