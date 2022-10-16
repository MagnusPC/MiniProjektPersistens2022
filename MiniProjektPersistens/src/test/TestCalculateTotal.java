package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ctrl.OrderCtrl;
import db.DataAccessException;
import model.Order;

class TestCalculateTotal {
    
    private OrderCtrl oCtrl1;

	@BeforeEach
	void setUp() throws Exception {
	    oCtrl1 = new OrderCtrl();
	}

	@AfterEach
	void tearDown() throws Exception {
	    oCtrl1 = null;
	}

	@Test
	void testDeliveryPriceAdded() throws DataAccessException, SQLException {
		//ARRANGE
        //We make the orders
	    oCtrl1.createNewOrder();
	    
	    //ACT
        //We add objects to the orders
	    oCtrl1.addCustomerByPhoneNo("+45 97971010");
	    oCtrl1.addProductByProductId(1, 2);
	    oCtrl1.addProductByProductId(2, 1);
	    oCtrl1.addProductByProductId(3, 1);
	    
	    //We save the expected total of Order 1 for later
	    double tempTotal = (299.99*2)+119.19+165.29;
	    
	    //We add the invoice, and thus the total, to the orders
	    oCtrl1.addInvoice();
	  
	    //We make the total retrieveable
	    Order tempO = oCtrl1.finishOrder();
	    
	    //ASSERT
	    //We check to see that the delivery price has been added
	    assertEquals(tempTotal+45, tempO.getInvoice().getInvoiceAmount(), 0.0001); //delta values are added to stop failure when comparing decimals
	}
	
	@Test
	void testDeliveryPriceNotAdded() throws DataAccessException, SQLException {
	    //arrange
	    oCtrl1.createNewOrder();
	    //act
        oCtrl1.addCustomerByPhoneNo("+45 97971010");
        oCtrl1.addProductByProductId(1, 9);
        oCtrl1.addInvoice();
        Order tempO = oCtrl1.finishOrder();
        //assert
        assertEquals(299.99*9, tempO.getInvoice().getInvoiceAmount(), 0.0001);
	}
	
	@Test
	void testDiscountAdded() throws DataAccessException, SQLException {
	    //ARRANGE
        oCtrl1.createNewOrder();
        
        //ACT
        //We add objects to the orders
        oCtrl1.addCustomerByPhoneNo("+45 88330000");
        oCtrl1.addProductByProductId(1, 4);
        
        //We add the invoice, and thus the total, to the orders
        oCtrl1.addInvoice();
        
        //We make the total retrieveable
        Order tempO = oCtrl1.finishOrder();
        
        //ASSERT
        //We check to see that the discount has not been added
        assertEquals(299.99*4, tempO.getInvoice().getInvoiceAmount(), 0.0001);
	}
	
	@Test
	void testDiscountNotAdded() throws DataAccessException, SQLException {
	    //arrange
	    oCtrl1.createNewOrder();
	    //act 
        oCtrl1.addCustomerByPhoneNo("+45 88330000");
        oCtrl1.addProductByProductId(1, 6);
        oCtrl1.addInvoice();
        Order tempO = oCtrl1.finishOrder();
        //assert
        assertEquals((299.99*6)*0.85, tempO.getInvoice().getInvoiceAmount(), 0.0001);
	}

}
