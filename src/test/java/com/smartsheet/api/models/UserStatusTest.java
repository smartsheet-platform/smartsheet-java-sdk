package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserStatusTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(UserStatus.valueOf("ACTIVE"));
		assertNotNull(UserStatus.valueOf("PENDING"));
		assertNotNull(UserStatus.valueOf("DECLINED"));

		assertEquals(3,UserStatus.values().length);
	}

}
