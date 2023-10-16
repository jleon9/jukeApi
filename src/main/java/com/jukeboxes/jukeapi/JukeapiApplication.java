package com.jukeboxes.jukeapi;

import com.jukeboxes.jukeapi.api.model.Jukebox;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.SpringVersion;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JukeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JukeapiApplication.class, args);

	}

}
