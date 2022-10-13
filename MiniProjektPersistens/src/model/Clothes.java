package model;

public class Clothes extends Product {

	private int id;
	private String size;
	private String color;
	
	public Clothes(int id, String size, String color, int productID, String name, double purchasePrice, double salePrice, double rentPrice, String productType, int supplierID) {
		super(productID, name, purchasePrice, salePrice, rentPrice, productType, supplierID);
		this.id = id; //TODO id skal stemme overens med product
		this.size = size;
		this.color = color;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
}
