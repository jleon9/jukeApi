package com.jukeboxes.jukeapi.Service;

import com.jukeboxes.jukeapi.api.model.Jukebox;
import com.jukeboxes.jukeapi.api.model.Paginated;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JukeServiceTest {

  @Autowired
  private JukeService jukeService;

  private MockWebServer mockWebServer;

  @BeforeEach
  public void setup() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
    String baseUrl = mockWebServer.url("/").toString();

    // Configure WebClient to use the mock server URL
    jukeService.setJukeUrl(baseUrl);

    // Mock the server response
    MockResponse mockResponse = new MockResponse()
      .setResponseCode(200)
      .setBody("[]");
    mockWebServer.enqueue(mockResponse);
  }

  @AfterEach
  public void tearDown() throws IOException {
    mockWebServer.shutdown();
  }

  @Test
  public void fetchJukeboxDataTest() {

    List<Jukebox> jukeboxes = jukeService.fetchJukeboxData();

    // Add assertions to verify jukeboxes
    assertEquals(30, jukeboxes.size());
    assertEquals("5ca94a8ac470d3e47cd4713c", jukeboxes.get(0).getId());
  }

  @Test
  public void paginateTest() {

    List<Jukebox> jukeboxes = jukeService.fetchJukeboxData();
    Paginated<Jukebox> paginatedJukes = jukeService.paginate(jukeboxes,2,3);

    assertEquals(
      "5ca94a8a75c231bb18715112",
      paginatedJukes.getItems().get(0).getId());
    assertEquals(3, paginatedJukes.getItems().size());
    assertEquals(30, paginatedJukes.getNumItems());
    assertEquals(10, paginatedJukes.getNumPages());

  }

  // Add more test methods to cover other functionalities
}