package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ctrl.OrderCtrl;
import ctrl.ProductCtrl;
import db.DataAccessException;
import model.GunReplica;
import model.Order;
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
        assertThrows(DataAccessException.class, () -> oCtrl.addProductByProductId(3, 800)); //fejler fordi stock altid opdateres
    }
	//TODO nedenståede tests revideres

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
        oCtrl.addProductByProductId(1, 310); //Vi sikrer at begge locations tilgås
        oCtrl.addProductByProductId(2, 310); //Quantity opdateres til max stock hver gang min stock nåes
        oCtrl.addProductByProductId(3, 310);
        oCtrl.addInvoice();
        oCtrl.finishOrder();
        //TODO assert
        
	}
	
	@Test
	void testStockDoesNotUpdateWithZeroProducts() throws DataAccessException {
	    //arrange
	    oCtrl.createNewOrder();
	    //act
	    oCtrl.addCustomerByPhoneNo("+45 11111111");
	    Product pgr = new GunReplica(".44", "silikone", 1, "Stor gøb", (float)250.51, (float)299.99, 240, "GunReplica", 1);
        OrderLine ol = new OrderLine(pgr, 0); //orderline takes any quantity but the controller layer does not
	    oCtrl.addInvoice();
	    oCtrl.finishOrder();
	    //assert
	    assertNotEquals(ol, oCtrl.addProductByProductId(1, 0));
	}
	
	@Test
	void testStockDoesNotUpdateWithNegativeProducts() throws DataAccessException {
	    //arrange
	    oCtrl.createNewOrder();
	    //act
	    oCtrl.addCustomerByPhoneNo("+45 11111111");
	    Product pgr = new GunReplica(".44", "silikone", 1, "Stor gøb", (float)250.51, (float)299.99, 240, "GunReplica", 1);
	    OrderLine ol = new OrderLine(pgr, -1);
	    //assert
	    assertNotEquals(ol, oCtrl.addProductByProductId(1, -1));
	}

}
