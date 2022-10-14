package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DBConnection;
import model.*;

class testDBAccess {

	static DBConnection con = null;
	static Order testO;
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
