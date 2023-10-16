package com.jukeboxes.jukeapi.api.controller;

import com.jukeboxes.jukeapi.Service.JukeService;
import com.jukeboxes.jukeapi.Service.SettingService;
import com.jukeboxes.jukeapi.api.model.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class JukeboxControllerTest {
  @Autowired
  private JukeboxController jukeboxController;

  private MockMvc mockMvc;
  private MockWebServer mockWebServerSetting;
  private MockWebServer mockWebServerJuke;



  @Autowired
  private JukeService jukeService;



  @Autowired
  private SettingService settingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(jukeboxController).build();
  }

  @Test
  void testGetJukeboxes() throws Exception {
    mockWebServerJuke = new MockWebServer();
    mockWebServerJuke.start();
    String baseUrlJuke = mockWebServerJuke.url("/").toString();

    // Configure WebClient to use the mock server URL
    jukeService.setJukeUrl(baseUrlJuke);

    // Mock the server response
    MockResponse mockResponseJuke = new MockResponse()
      .setResponseCode(200)
      .setBody("[]");
    jukeService.setJukeUrl(baseUrlJuke);
    mockWebServerJuke.enqueue(mockResponseJuke);


    mockWebServerSetting = new MockWebServer();
    mockWebServerSetting.start();
    String baseUrlSetting = mockWebServerSetting.url("/").toString();

    // Configure WebClient to use the mock server URL
    settingService.setSettingsUrl(baseUrlSetting);

    // Mock the server response
    MockResponse mockResponse = new MockResponse()
      .setResponseCode(200)
      .setBody("[]");
    mockWebServerSetting.enqueue(mockResponse);

    Assertions.assertEquals(4,
      Objects.requireNonNull(jukeboxController.getJukeboxes(
      "\"86506865-f971-496e-9b90-75994f251459\"",
      "\"fusion\"",
      1,
      3).getBody()).getNumPages());

    Assertions.assertEquals(10,
      Objects.requireNonNull(jukeboxController.getJukeboxes(
        "\"86506865-f971-496e-9b90-75994f251459\"",
        "\"fusion\"",
        1,
        3).getBody()).getNumItems());

    Assertions.assertEquals(2,
      Objects.requireNonNull(jukeboxController.getJukeboxes(
        "\"86506865-f971-496e-9b90-75994f251459\"",
        "\"fusion\"",
        3,
        3).getBody()).getCurrentPage());

    Assertions.assertEquals(2,
      Objects.requireNonNull(jukeboxController.getJukeboxes(
        "\"86506865-f971-496e-9b90-75994f251459\"",
        null,
        3,
        3).getBody()).getCurrentPage());

    mockWebServerSetting.shutdown();
    mockWebServerJuke.shutdown();
    // Add more assertions based on the response you expect
  }

}