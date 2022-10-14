package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;
import db.*;
import ctrl.OrderCtrl;

class finishOrder {
	
	static OrderCtrl oCtrl;

	@BeforeEach
	void setUp() throws Exception {
		oCtrl = new OrderCtrl();
	}

	@AfterEach
	void tearDown() throws Exception {
		oCtrl = null;
	}

	@Test
	void testFinishOrderWithNoProducts() throws DataAccessException {
		//Arrange
		oCtrl.createNewOrder();
		oCtrl.addCustomerByPhoneNo("+45 11111111");
		
		assertThrows(DataAccessException.class, () -> oCtrl.finishOrder())
	}

}
