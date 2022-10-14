package db;

import model.Product;

public interface ProductDBIF {
	Product findProductByID(int id) throws DataAccessException;
	//list all, update products
}
