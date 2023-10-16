package com.jukeboxes.jukeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * The JukeApi Application
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JukeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JukeapiApplication.class, args);

	}

}
