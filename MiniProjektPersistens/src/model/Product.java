package model;



public abstract class Product {
	
	private int productID;
	private String name;
	private float purchasePrice;
	private float salePrice;
	private float rentPrice;
	private String productType;
	private int supplierID;

	public Product(int productID, String name, float purchasePrice, float salePrice, float rentPrice, String productType, int supplierID) {
		this.productID = productID;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.rentPrice = rentPrice;
		this.productType = productType;
		this.supplierID = supplierID;
	}
	
	public int getProductID() {
		return productID;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the purchasePrice
	 */
	public double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * @return the salePrice
	 */
	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the rentPrice
	 */
	public double getRentPrice() {
		return rentPrice;
	}

	/**
	 * @param rentPrice the rentPrice to set
	 */
	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the supplierID
	 */
	public int getSupplierID() {
		return supplierID;
	}

	/**
	 * @param supplierID the supplierID to set
	 */
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	
}
