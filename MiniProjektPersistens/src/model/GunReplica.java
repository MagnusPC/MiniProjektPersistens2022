package model;

public class GunReplica extends Product {
	
	private int id;
	private String caliber;
	private String material;
	
	public GunReplica(int id, String caliber, String material, int productID, String name, double purchasePrice, double salePrice, double rentPrice, String productType, int supplierID) {
		super(productID, name, purchasePrice, salePrice, rentPrice, productType, supplierID);
		this.id = id; //TODO id skal stemme overens med product
		this.caliber = caliber;
		this.material = material;
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
	 * @return the caliber
	 */
	public String getCaliber() {
		return caliber;
	}

	/**
	 * @param caliber the caliber to set
	 */
	public void setCaliber(String caliber) {
		this.caliber = caliber;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

}
