package model;

public class Equipment extends Product {

	private int id;
	private String type;
	private String description;
	
	public Equipment(String type, String description, int productID, String name, float purchasePrice, float salePrice, float rentPrice, String productType, int supplierID) {
		super(productID, name, purchasePrice, salePrice, rentPrice, productType, supplierID);
//		this.id = id; //TODO id skal stemme overens med product
		this.type = type;
		this.description = description;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
