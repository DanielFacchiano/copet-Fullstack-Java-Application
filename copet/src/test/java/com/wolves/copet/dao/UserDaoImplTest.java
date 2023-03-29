package com.wolves.copet.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wolves.copet.CopetApplication;
import com.wolves.copet.dto.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CopetApplication.class)
class UserDaoImplTest {

	@Autowired
	UserDao userDao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddAndGetUser() {
		User newUser = new User();
		newUser.setUserName("coolguy");
		newUser.setHashedPw("hasChillDay");
		newUser.setAdmin(false);
		newUser.setEmail("ayy@gmail.com");
		newUser = userDao.createUser(newUser);

		User fromDaoUser = userDao.getUser(newUser.getId());
		assertEquals(fromDaoUser, newUser);
	}
}
