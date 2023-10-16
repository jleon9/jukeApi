package com.jukeboxes.jukeapi.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukeboxes.jukeapi.api.model.SettingJSON;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * The SettingsService class provide a SettingJSON object from a requested URL.
 * This SettingJSON object contains a Java Array of Setting objects that can be accessed
 * from any other class.
 */
@Service
public class SettingService {
  private String settingsUrl = "http://my-json-server.typicode.com/touchtunes/tech-assignment/settings";

  private final WebClient webClient;
  private final ObjectMapper objectMapper;

  /**
   * @param webClientBuilder From the HTTP web client library, sets the service context
   * @param objectMapper Jackson library object used to convert JSON into java object
   */
  public SettingService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
    this.webClient = webClientBuilder.baseUrl(settingsUrl).build();
    this.objectMapper = objectMapper;
  }

  /**
   * @return List of Jukeboxes parsed from the JSON object given by the URL.
   */
  public SettingJSON fetchSettingData() {
    return webClient.get()
      .retrieve()
      .bodyToMono(String.class)
      .flatMap(this::parseSettings)
      .block();
  }

  /**
   * @param url URL we want the jukeService to point to.
   */
  public void setSettingsUrl(String url) {
    this.settingsUrl = url;
  }


  private Mono<SettingJSON> parseSettings(String jsonResponse) {
    try {
      return Mono.just(objectMapper.readValue(jsonResponse, new TypeReference<>() {
      }));
    } catch (Exception e) {
      return Mono.error(new RuntimeException("Failed to parse JSON response", e));
    }
  }
}
