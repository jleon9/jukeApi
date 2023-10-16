/*
package com.jukeboxes.jukeapi.api.controller;

import com.jukeboxes.jukeapi.Service.JukeService;
import com.jukeboxes.jukeapi.Service.SettingService;
import com.jukeboxes.jukeapi.api.model.Jukebox;
import com.jukeboxes.jukeapi.api.model.Paginated;
import com.jukeboxes.jukeapi.api.model.Setting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class JukeboxControllerTest {

  @Mock
  private JukeService jukeService;

  @Mock
  private SettingService settingService;
  private JukeboxController jukeboxController;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    jukeboxController = new JukeboxController();
    mockMvc = MockMvcBuilders.standaloneSetup(jukeboxController).build();
  }*/
/*
  @Test
  void testGetJukeboxes() throws Exception {
    // Create some test data
    List<Jukebox> jukeboxes = new ArrayList<>();
    // Add some jukeboxes to the list

    // Mock the behavior of your service methods
    when(jukeService.fetchJukeboxData()).thenReturn(jukeboxes);

    // Perform the GET request
    mockMvc.perform(get("/api/jukeboxes")
        .param("settingId", "someSettingId")
        .param("model", "someModel")
        .param("offset", "0")
        .param("limit", "10"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.totalElements").value(jukeboxes.size()));
  }
}*/