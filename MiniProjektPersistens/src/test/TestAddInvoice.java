package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ctrl.OrderCtrl;
import db.DataAccessException;
import model.Invoice;
import model.Order;

class TestAddInvoice {

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
    void testInvoiceAdded() throws DataAccessException {
        oCtrl.createNewOrder();
        oCtrl.addCustomerByPhoneNo("+45 11111111");
        oCtrl.addProductByProductId(1, 1);
        Invoice iv = oCtrl.addInvoice();
        Order o = oCtrl.finishOrder();
        assertEquals(iv, o.getInvoice());
    }
    
    @Test
    void testInvoiceNotAdded() throws DataAccessException {
        oCtrl.createNewOrder();
        oCtrl.addCustomerByPhoneNo("+45 11111111");
        assertEquals(null, oCtrl.addInvoice());
    }

}
