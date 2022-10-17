package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ctrl.OrderCtrl;
import db.DataAccessException;
import model.GunReplica;
import model.OrderLine;
import model.Product;

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
    void testOrderHigherThanStock() throws DataAccessException {
        oCtrl.createNewOrder();
        oCtrl.addCustomerByPhoneNo("+45 11111111");
        assertThrows(DataAccessException.class, () -> oCtrl.addProductByProductId(3, 800));
    }
	
	@Disabled("Until next iteration")
	@Test
	void testOrderFromBothLocations() throws DataAccessException {
	    oCtrl.createNewOrder();
	    oCtrl.addCustomerByPhoneNo("+45 11111111");
	    oCtrl.addProductByProductId(1, 310);
	    oCtrl.addInvoice();
//	    Order tempO = oCtrl.finishOrder();
//	    assertEquals(x, tempO.getStock()); //noget med at lægge stocks sammen måske, inde i order
	    
	}
	
	@Test
	void testOrderAddZeroQuantity() throws DataAccessException {
	    //arrange
	    oCtrl.createNewOrder();
	    //act
	    oCtrl.addCustomerByPhoneNo("+45 11111111");
	    //Make ol to compare with - order controller handles zero-negative values
	    Product pgr = new GunReplica(".44", "silikone", 1, "Stor gøb", (float)250.51, (float)299.99, 240, "GunReplica", 1);
        OrderLine ol = new OrderLine(pgr, 0);
        //assert
        //first that it is not equal to the object made by Orderline, second that it is in fact null
        assertNotEquals(ol, oCtrl.addProductByProductId(1, 0));
        assertEquals(null, oCtrl.addProductByProductId(1, 0));
	    
	}
	
	@Test
	void testOrderAddNegativeQuantity() throws DataAccessException {
	    oCtrl.createNewOrder();
	    oCtrl.addCustomerByPhoneNo("+45 11111111");
	    
	    Product pgr = new GunReplica(".44", "silikone", 1, "Stor gøb", (float)250.51, (float)299.99, 240, "GunReplica", 1);
        OrderLine ol = new OrderLine(pgr, -1);
        assertNotEquals(ol, oCtrl.addProductByProductId(1, -1));
        assertEquals(null, oCtrl.addProductByProductId(1, -1));
        
	}

	@Disabled("Until next iteration")
	@Test
	void testStockUpdatesOneLocation() throws DataAccessException {
	    oCtrl.createNewOrder();
	    oCtrl.addCustomerByPhoneNo("+45 11111111");
	    oCtrl.addProductByProductId(3, 5);
	    oCtrl.addInvoice();
//	    Order tempO = oCtrl.finishOrder();
//      assertEquals(x, tempO.getStock()); //noget med at lægge stocks sammen måske, inde i order
	    //TODO få fat i stock db somehow og check om stocks matcher v fx print eller assertequals        
	}

}
