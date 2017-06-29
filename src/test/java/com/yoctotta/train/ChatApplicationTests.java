package com.yoctotta.train;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = "debug=true")
@SpringBootTest
public class ChatApplicationTests {

	@Test
	public void contextLoads() {
	}

}
