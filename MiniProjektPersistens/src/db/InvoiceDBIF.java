package db;

import model.Invoice;

public interface InvoiceDBIF {

	
	Invoice insert(Invoice invoice) throws DataAccessException;
}
