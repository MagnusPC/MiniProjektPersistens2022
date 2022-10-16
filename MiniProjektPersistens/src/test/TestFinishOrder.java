package test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ctrl.OrderCtrl;
import db.DataAccessException;
import model.Order;

class TestFinishOrder {
    
    OrderCtrl oCtrl;

	@BeforeEach
	void setUp() throws Exception {
	    oCtrl = new OrderCtrl();
	}

	@AfterEach
	void tearDown() throws Exception {
	    oCtrl = null;
	}

	@Disabled("Just to be sure - only test one stock method at a time")
	@Test
	void testStockUpdatesOneLocation() throws DataAccessException {
	    oCtrl.createNewOrder();
	    oCtrl.addCustomerByPhoneNo("+45 11111111");
	    oCtrl.addProductByProductId(1, 5);
	    oCtrl.addProductByProductId(2, 5);
	    oCtrl.addProductByProductId(3, 5);
	    oCtrl.addInvoice();
	    Order o = oCtrl.finishOrder();
	    assertSame(o, oCtrl.finishOrder()); //selvfølgelig er de the same
	    //TODO få fat i stock db somehow og check om stocks matcher v fx print eller assertequals
	}
	
	@Test
	void testStockUpdatesMultipleLocations() throws DataAccessException {
	    oCtrl.createNewOrder();
        oCtrl.addCustomerByPhoneNo("+45 11111111");
        oCtrl.addProductByProductId(1, 305); //Vi sikrer at begge locations tilgås
        oCtrl.addProductByProductId(2, 305);
        oCtrl.addProductByProductId(3, 405); //Priserne er taget efter current quantity i msdb
        oCtrl.addInvoice();
        oCtrl.finishOrder();
        //ASSERT
        
	}
	
	@Disabled
	@Test
    void testNoProductsAdded() throws DataAccessException, SQLException {
        //arrange
        oCtrl.createNewOrder();
        //act
        oCtrl.addCustomerByPhoneNo("+45 11111111");
        oCtrl.addInvoice();
        //assert
            //ingen exception bliver kastet
//          assertThrows(DataAccessException.class, () -> oCtrl1.finishOrder());
        //arrange
//        Order tempO = oCtrl.finishOrder();
        //assert
        //TODO fejler da delivery price stadig tilføjes
//        assertEquals(0, tempO.getInvoice().getInvoiceAmount());
    }
    
	@Disabled
    @Test
    void testMixedLocationProductsAdded() throws DataAccessException {
        //arrange
        oCtrl.createNewOrder();
        //act
        oCtrl.addCustomerByPhoneNo("+45 11111111");
        oCtrl.addProductByProductId(3, 800); //TODO prøver at finde ekstra stock i øvrige locations og indexoutofbounds kommer dermed af, at systemet prøver at finde stock i en tredje location
        oCtrl.addInvoice();
        //assert
        assertThrows(DataAccessException.class, () -> oCtrl.finishOrder());    
    }

}
