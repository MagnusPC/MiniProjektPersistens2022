package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ctrl.OrderCtrl;
import db.DataAccessException;

class TestOrderPersisted {
    
//    OrderDBIF odb;
    OrderCtrl oCtrl;

    @BeforeEach
    void setUp() throws Exception {
//        odb = new OrderDB();
        oCtrl = new OrderCtrl();
    }

    @AfterEach
    void tearDown() throws Exception {
//        odb = null;
    }

    @Test
    void testOrderSaved() throws DataAccessException, SQLException {
        //arrange - save first hit
        int oID = oCtrl.getOrderDB().selectTopOrderId();
        
        //create new hit
        oCtrl.createNewOrder();
        //act
        oCtrl.addCustomerByPhoneNo("+45 88330000");
        oCtrl.addProductByProductId(2, 20);
        oCtrl.addInvoice();
        oCtrl.finishOrder();
        //assert - we remember to add one, since the outcome should be one higher
        assertEquals(oID+1, oCtrl.getOrderDB().selectTopOrderId());
    }

}
