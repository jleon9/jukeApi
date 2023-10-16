package com.jukeboxes.jukeapi.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukeboxes.jukeapi.api.model.Jukebox;
import com.jukeboxes.jukeapi.api.model.Paginated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;


/**
 *
 */
@Service
public class JukeService {

  private String jukeUrl = "http://my-json-server.typicode.com/touchtunes/tech-assignment/jukes";
  private final WebClient jukeWebClient;
  private final ObjectMapper objectMapper;

  public JukeService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {

    this.jukeWebClient = webClientBuilder.baseUrl(jukeUrl).build();
    this.objectMapper = objectMapper;
  }

  public List<Jukebox> fetchJukeboxData() {
    return jukeWebClient.get()
      .retrieve()
      .bodyToMono(String.class)
      .flatMap(this::parseJukebox)
      .block();
  }

  public void setJukeUrl(String url) {
    this.jukeUrl = url;
  }

  private Mono<List<Jukebox>> parseJukebox(String jsonResponse) {
    try {
      return Mono.just(objectMapper.readValue(jsonResponse, new TypeReference<List<Jukebox>>() {}));
    } catch (IOException e) {
      return Mono.error(new RuntimeException("Failed to parse JSON response", e));
    }
  }

  public Paginated<Jukebox> paginate(List<Jukebox> list, int offset, int limit) {
    int numItems = list.size();
    int start = Math.min(offset, numItems);  // Ensure start is within bounds
    int end = Math.min(offset + limit, numItems);  // Ensure end is within bounds

    List<Jukebox> paginatedList = list.subList(start, end);
    return new Paginated<>(
      paginatedList, numItems,
      calculateNumPages(numItems, limit),
      calculateCurrentPage(start, limit)
    );
  }

  private int calculateNumPages(int numItems, int limit) {
    return (int) Math.ceil((double) numItems / limit);
  }

  private int calculateCurrentPage(int offset, int limit) {
    return (offset / limit) + 1;
  }

}
