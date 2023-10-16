package com.jukeboxes.jukeapi.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClient provided by the Spring WebFlux module
 * used to make HTTP requests to web services and APIs
 */
@Configuration
public class WebClientConfig {

  @Bean
  public WebClient webClient() {
    return WebClient.create();
  }
}

