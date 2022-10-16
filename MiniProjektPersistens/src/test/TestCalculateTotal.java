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

	@Test
	void testDeliveryPriceAddedValidOrder() throws DataAccessException, SQLException {
		//ARRANGE
        //We make the orders
	    oCtrl.createNewOrder();
	    
	    //ACT
        //We add objects to the orders
	    oCtrl.addCustomerByPhoneNo("+45 97971010");
	    oCtrl.addProductByProductId(1, 2);
	    oCtrl.addProductByProductId(2, 1);
	    oCtrl.addProductByProductId(3, 1);
	    
	    //We save the expected total of Order 1 for later
	    double tempTotal = (299.99*2)+119.19+165.29;
	    
	    //We add the invoice, and thus the total, to the orders
	    oCtrl.addInvoice();
	  
	    //We make the total retrieveable
	    Order tempO = oCtrl.finishOrder();
	    
	    //ASSERT
	    //We check to see that the delivery price has been added
	    assertEquals(tempTotal+45, tempO.getInvoice().getInvoiceAmount(), 0.0001); //delta values are added to stop failure when comparing decimals
	}
	
	@Test
	void testDeliveryPriceNotAddedValidOrder() throws DataAccessException, SQLException {
	    //arrange
	    oCtrl.createNewOrder();
	    //act
        oCtrl.addCustomerByPhoneNo("+45 97971010");
        oCtrl.addProductByProductId(1, 9);
        oCtrl.addInvoice();
        Order tempO = oCtrl.finishOrder();
        //assert
        assertEquals(299.99*9, tempO.getInvoice().getInvoiceAmount(), 0.0001);
	}
	
	@Disabled("Better to make finishOrder fail")
	@Test
	void testDeliveryPriceNotAddedInvalidOrder() throws DataAccessException {
	    oCtrl.createNewOrder();
	    oCtrl.addCustomerByPhoneNo("+45 11111111");
	    oCtrl.addInvoice();
	    Order tempO = oCtrl.finishOrder();
	    assertEquals(0, tempO.getInvoice().getInvoiceAmount());
	}
	
	@Test
	void testDiscountAddedValidOrder() throws DataAccessException, SQLException {
	    //ARRANGE
        oCtrl.createNewOrder();
        
        //ACT
        //We add objects to the orders
        oCtrl.addCustomerByPhoneNo("+45 88330000");
        oCtrl.addProductByProductId(1, 4);
        
        //We add the invoice, and thus the total, to the orders
        oCtrl.addInvoice();
        
        //We make the total retrieveable
        Order tempO = oCtrl.finishOrder();
        
        //ASSERT
        //We check to see that the discount has not been added
        assertEquals(299.99*4, tempO.getInvoice().getInvoiceAmount(), 0.0001);
	}
	
	@Test
	void testDiscountNotAddedValidOrder() throws DataAccessException, SQLException {
	    //arrange
	    oCtrl.createNewOrder();
	    //act 
        oCtrl.addCustomerByPhoneNo("+45 88330000");
        oCtrl.addProductByProductId(1, 6);
        oCtrl.addInvoice();
        Order tempO = oCtrl.finishOrder();
        //assert
        assertEquals((299.99*6)*0.85, tempO.getInvoice().getInvoiceAmount(), 0.0001);
	}

}
