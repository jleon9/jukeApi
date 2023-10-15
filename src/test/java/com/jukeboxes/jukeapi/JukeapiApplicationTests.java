package com.jukeboxes.jukeapi;

import com.jukeboxes.jukeapi.Service.WebClientConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebClientConfig.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
class JukeapiApplicationTests {

	@Test
	void contextLoads() {
	}

}
