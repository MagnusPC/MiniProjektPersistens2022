package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import model.Invoice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class InvoiceDB implements InvoiceDBIF{

	private static final String INSERT_Q = "INSERT into Invoice values (?, ?)";
	private PreparedStatement insertPS;
	
	private static final String MAX_INVOICENO_Q = "SELECT MAX (invoiceNo) as invoiceNo from Invoice";
	private PreparedStatement max_InvoicenoPS;
	
	public InvoiceDB() throws db.DataAccessException {
		
		init();
	}
	
	
	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		
			try {
				insertPS = con.prepareStatement(INSERT_Q);
				max_InvoicenoPS = con.prepareStatement(MAX_INVOICENO_Q);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	@Override
	public Invoice addInvoice(Invoice invoice) throws DataAccessException {
		
			try {
				//insertPS.setInt(1, invoice.getInvoiceNo());
				insertPS.setDate(1, Date.valueOf(invoice.getPaymentDate()));
				insertPS.setDouble(2, invoice.getInvoiceAmount());
				
				insertPS.executeUpdate();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	
			try {
				
				ResultSet rs = max_InvoicenoPS.executeQuery();
				
				int invoiceNo = 0;
				while(rs.next()) {
					invoiceNo = rs.getInt("invoiceNo") ;
				}
				
				invoice.setInvoiceNo(invoiceNo);
				
				
			} catch (SQLException e) {
			}
			
			
		return invoice;
	}
	
	
}
