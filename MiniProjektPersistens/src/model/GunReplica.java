package model;

public class GunReplica extends Product {
	
	private int id;
	private String caliber;
	private String material;
	
	public GunReplica(int id, String caliber, String material, String name, double purchasePrice, double salePrice, double rentPrice, String productType, int supplierID) {
		super(name, purchasePrice, salePrice, rentPrice, productType, supplierID);
		this.id = id;
		this.caliber = caliber;
		this.material = material;
	}

}
