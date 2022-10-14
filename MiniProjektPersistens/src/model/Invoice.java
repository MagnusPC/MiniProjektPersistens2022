package model;

import java.time.LocalDate;

import db.InvoiceDB;
import db.InvoiceDBIF;

public class Invoice {
	private int invoiceNo;
	private LocalDate paymentDate;
	private double invoiceAmount;
	
	
	
	
	
	
	public Invoice() {
		//this.invoiceNo = invoiceNo;
		this.paymentDate = LocalDate.now();
		//this.invoiceAmount = invoiceAmount;
	}
	
	public int getInvoiceNo() {
		return invoiceNo;
	}
	
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	
	public double getInvoiceAmount() {
		return invoiceAmount;
	}	
		
	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
		
	}
	
	public void setInvoiceAmount(double amount) {
		invoiceAmount = amount;
	}
	
}
