package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ctrl.OrderCtrl;
import db.DataAccessException;
import model.Order;

class TestCalculateTotal {
    
    private OrderCtrl oCtrl;

	@BeforeEach
	void setUp() throws Exception {
	    oCtrl = new OrderCtrl();
	}

	@AfterEach
	void tearDown() throws Exception {
	    oCtrl = null;
	}

//	@Disabled
	@Test
	void testSingleProductWithDelivery() throws DataAccessException {
		//ARRANGE
        //We make the orders
	    oCtrl.createNewOrder();
	    
	    //ACT
        //We add objects to the orders
	    oCtrl.addCustomerByPhoneNo("+45 97971010");
	    oCtrl.addProductByProductId(3, 1);
	    
	    //We add the invoice, and thus the total, to the orders
	    oCtrl.addInvoice();
	  
	    //We make the total retrieveable
	    Order tempO = oCtrl.finishOrder();
	    
	    //ASSERT
	    //We check to see that the delivery price has been added
	    assertEquals((float)165.29+45, tempO.getInvoice().getInvoiceAmount()); 
	}
	
//	@Disabled
	@Test
	void testMultipleProductsWithoutDelivery() throws DataAccessException {
	    //arrange
	    oCtrl.createNewOrder();
	    //act
        oCtrl.addCustomerByPhoneNo("+45 97971010");
        oCtrl.addProductByProductId(1, 9);
        oCtrl.addInvoice();
        Order tempO = oCtrl.finishOrder();
        //assert
        assertEquals((float)299.99*9, tempO.getInvoice().getInvoiceAmount());
	}
	
//	@Disabled
	@Test
	void testSingleProductWithoutDiscount() throws DataAccessException {
	    //ARRANGE
        oCtrl.createNewOrder();
        
        //ACT
        //We add objects to the orders
        oCtrl.addCustomerByPhoneNo("+45 88330000");
        oCtrl.addProductByProductId(1, 1);
        
        //We add the invoice, and thus the total, to the orders
        oCtrl.addInvoice();
        
        //We make the total retrieveable
        Order tempO = oCtrl.finishOrder();
        
        //ASSERT
        //We check to see that the discount has not been added
        assertEquals((float)299.99, tempO.getInvoice().getInvoiceAmount());
	}
	
	@Test
	void testMultipleProductsWithDiscount() throws DataAccessException {
	    //arrange
	    oCtrl.createNewOrder();
	    //act 
        oCtrl.addCustomerByPhoneNo("+45 88330000");
        oCtrl.addProductByProductId(1, 6);
        oCtrl.addInvoice();
        Order tempO = oCtrl.finishOrder();
        //assert
        assertEquals((float)(299.99*6)*0.85, tempO.getInvoice().getInvoiceAmount());
	}

}
