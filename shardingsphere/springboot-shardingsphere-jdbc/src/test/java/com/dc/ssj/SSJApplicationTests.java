package com.dc.ssj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
class SSJApplicationTests {

	@Resource
	private DataSource dataSource;

	@Test
	void contextLoads() {
	}

}
