package com.jukeboxes.jukeapi.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukeboxes.jukeapi.api.model.Jukebox;
import com.jukeboxes.jukeapi.api.model.Paginated;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;


/**
 * The JukeService class provides Jukebox objects from a requested URL.
 */
@Service
public class JukeService {

  private String jukeUrl = "http://my-json-server.typicode.com/touchtunes/tech-assignment/jukes";
  private final WebClient jukeWebClient;
  private final ObjectMapper objectMapper;

  /**
   * @param webClientBuilder From the HTTP web client library, sets the service context
   * @param objectMapper Jackson library object used to convert JSON into java object
   */
  public JukeService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {

    this.jukeWebClient = webClientBuilder.baseUrl(jukeUrl).build();
    this.objectMapper = objectMapper;
  }


  /**
   * Fetches the JSON jukeboxes into a Java list of Jukebox objects
   * @return List of Jukeboxes parsed from the JSON object given by the URL.
   */
  public List<Jukebox> fetchJukeboxData() {
    return jukeWebClient.get()
      .retrieve()
      .bodyToMono(String.class)
      .flatMap(this::parseJukebox)
      .block();
  }


  /**
   * @param url URL we want the jukeService to point to.
   */
  public void setJukeUrl(String url) {
    this.jukeUrl = url;
  }


  /**
   * @param jsonResponse JSON string retrieved from calling the first touchtunes API
   *                     touchtunes/tech-assignment/jukes
   * @return returns a WebFlux Mono object containing a List of Jukebox objects
   */
  private Mono<List<Jukebox>> parseJukebox(String jsonResponse) {
    try {
      return Mono.just(objectMapper.readValue(jsonResponse, new TypeReference<List<Jukebox>>() {}));
    } catch (IOException e) {
      return Mono.error(new RuntimeException("Failed to parse JSON response", e));
    }
  }


  /**
   * @param list list to paginate
   * @param offset offset of the pagination (going from 0)
   * @param limit maximum number of items a single page can contain
   * @return The corresponding paginated list of jukeboxes
   */
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

  /**
   * @param numItems Number of items in the list to paginate
   * @param limit Maximum number of items the page can hold
   * @return The corresponding number of pages
   */
  private int calculateNumPages(int numItems, int limit) {
    if (limit == 0) {
      return 1;
    }
    return (int) Math.ceil((double) numItems / limit);
  }


  /**
   * @param offset offset of the pagination (going from 0)
   * @param limit maximum number of items a single page can contain
   * @return The current page referred by these parameters
   */
  private int calculateCurrentPage(int offset, int limit) {
    if (limit == 0) {
      return 1;
    }
    return (offset / limit) + 1;
  }

}
