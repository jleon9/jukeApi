package com.jukeboxes.jukeapi.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukeboxes.jukeapi.api.model.Jukebox;
import com.jukeboxes.jukeapi.api.model.Setting;
import com.jukeboxes.jukeapi.api.model.SettingJSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SettingService {
  /*
  @Value("${api.url}")
  private String apiUrl;
   */
  private String settingsUrl = "http://my-json-server.typicode.com/touchtunes/tech-assignment/settings";

  private final WebClient webClient;
  private final ObjectMapper objectMapper;

  public SettingService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
    this.webClient = webClientBuilder.baseUrl(settingsUrl).build();
    this.objectMapper = objectMapper;
  }

  public SettingJSON fetchSettingData() {
    return webClient.get()
      .retrieve()
      .bodyToMono(String.class)
      .flatMap(this::parseSettings)
      .block();
  }

  public void setJukeUrl(String url) {
    this.settingsUrl = url;
  }


  private Mono<SettingJSON> parseSettings(String jsonResponse) {
    try {
      return Mono.just(objectMapper.readValue(jsonResponse, new TypeReference<SettingJSON>() {}));
    } catch (Exception e) {
      return Mono.error(new RuntimeException("Failed to parse JSON response", e));
    }
  }
}
