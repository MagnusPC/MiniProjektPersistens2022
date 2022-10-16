package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ctrl.OrderCtrl;
import db.DataAccessException;
import model.Order;

class TestCalculateTotal {
    
    private OrderCtrl oCtrl1;
    private OrderCtrl oCtrl2;
    private Order tempOrder;

	@BeforeEach
	void setUp() throws Exception {
	    oCtrl1 = new OrderCtrl();
	    oCtrl2 = new OrderCtrl();
	}

	@AfterEach
	void tearDown() throws Exception {
	    oCtrl1 = null;
	    oCtrl2 = null;
	}

	@Test
	void testDeliveryPriceAdded() throws DataAccessException {
		//ARRANGE
        //We make the orders
	    oCtrl1.createNewOrder();
	    oCtrl2.createNewOrder();
	    
	    //ACT
        //We add objects to the orders
	    oCtrl1.addCustomerByPhoneNo("+45 97971010");
	    oCtrl1.addProductByProductId(1, 2);
	    oCtrl1.addProductByProductId(2, 1);
	    oCtrl1.addProductByProductId(3, 1);
	    //We save the expected total of Order 1 for later
	    double tempTotal = (299.99*2)+119.19+165.29;
	    
	    oCtrl2.addCustomerByPhoneNo("+45 97971010");
	    oCtrl2.addProductByProductId(1, 9);
	    
	    //We add the invoice, and thus the total, to the orders
	    oCtrl1.addInvoice();
	    oCtrl2.addInvoice();
	    //We make the total retrieveable
	    Order tempO1 = oCtrl1.finishOrder();
	    Order temp02 = oCtrl2.finishOrder();
	    
	    //ASSERT
	    //We check to see that the delivery price has been added
	    assertEquals(tempTotal+45, tempO1.getInvoice().getInvoiceAmount());
	    //We check to see that the delivery price has not been added
	    assertEquals(299.99*9, temp02.getInvoice().getInvoiceAmount());
	    
	}
	
	@Test
	void testDiscountAdded() throws DataAccessException {
	    //ARRANGE
        oCtrl1.createNewOrder();
        oCtrl2.createNewOrder();
        
        //ACT
        //We add objects to the orders
        oCtrl1.addCustomerByPhoneNo("+45 88330000");
        oCtrl1.addProductByProductId(1, 4);
        //We save the expected total of Order 1 for later
        
        oCtrl2.addCustomerByPhoneNo("+45 88330000");
        oCtrl2.addProductByProductId(1, 6);
        
        //We add the invoice, and thus the total, to the orders
        oCtrl1.addInvoice();
        oCtrl2.addInvoice();
        //We make the total retrieveable
        Order tempO1 = oCtrl1.finishOrder();
        Order temp02 = oCtrl2.finishOrder();
        
        //ASSERT
        //We check to see that the discount has not been added
        assertEquals(299.99*4, tempO1.getInvoice().getInvoiceAmount());
        //We check to see that the discount has been added
        assertEquals((299.99*6)*0.85, temp02.getInvoice().getInvoiceAmount());
	}

}
