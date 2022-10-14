package db;

import model.Invoice;

public interface InvoiceDBIF {

	
	Invoice addInvoice(Invoice invoice) throws DataAccessException;
}
