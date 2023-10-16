package com.jukeboxes.jukeapi.Service;

import com.jukeboxes.jukeapi.api.model.SettingJSON;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SettingServiceTest {

  @Autowired
  private SettingService settingService;

  private MockWebServer mockWebServer;

  @BeforeEach
  public void setup() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
    String baseUrl = mockWebServer.url("/").toString();

    // Configure WebClient to use the mock server URL
    settingService.setSettingsUrl(baseUrl);

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

    SettingJSON settings = settingService.fetchSettingData();

    // Add assertions to verify jukeboxes
    assertEquals(30, settings.getSettings().length);
    assertEquals("e9869bbe-887f-4d0a-bb9d-b81eb55fbf0a", settings.getSettings()[0].getId());
  }

  // Add more test methods to cover other functionalities
}