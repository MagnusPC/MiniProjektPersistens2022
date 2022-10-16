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
        Order tempO = oCtrl.finishOrder();
        //assert
        //TODO fejler da delivery price stadig tilføjes
        assertEquals(0, tempO.getInvoice().getInvoiceAmount());
    }
    
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
