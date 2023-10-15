/*
package com.jukeboxes.jukeapi.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukeboxes.jukeapi.api.model.Component;
import com.jukeboxes.jukeapi.api.model.Jukebox;
import com.jukeboxes.jukeapi.api.model.Paginated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class JukeServiceTest {

  @Mock
  private JukeService jukeService;


  @BeforeEach
  void setUp() {
    String jukeUrl = "https://my-json-server.typicode.com/touchtunes/tech-assignment/jukes";
  }

  @Test
  void fetchJukeboxData() {

    List<Jukebox> expectedJukeboxes = new ArrayList<>();
    Jukebox j1 = new Jukebox("5ca94a8ac470d3e47cd4713c", "fusion");
    j1.addComponent(new Component("led_panel"));
    j1.addComponent(new Component("amplifier"));
    j1.addComponent(new Component("led_panel"));
    j1.addComponent(new Component("led_panel"));
    j1.addComponent(new Component("pcb"));
    expectedJukeboxes.add(j1);

    Jukebox j2 = new Jukebox("5ca94a8a77e20d15a7d16d0a", "angelina");
    j1.addComponent(new Component("pcb"));
    j1.addComponent(new Component("money_pcb"));
    j1.addComponent(new Component("touchscreen"));
    j1.addComponent(new Component("speaker"));
    j1.addComponent(new Component("speaker"));
    expectedJukeboxes.add(j2);


    // Mock the data access behavior
    when(jukeService.fetchJukeboxData()).thenReturn(expectedJukeboxes);

    List<Jukebox> fetchedJukeboxes = jukeService.fetchJukeboxData();
    List<Jukebox> actualJukeboxes = new ArrayList<>();
    fetchedJukeboxes.add(fetchedJukeboxes.get(0));
    fetchedJukeboxes.add(fetchedJukeboxes.get(1));


  }

  @Test
  void paginate() {

  }
}


/*
[
  {
  "id": "5ca94a8ac470d3e47cd4713c",
  "model": "fusion",
  "components": [
  {
  "name": "led_panel"
  },
  {
  "name": "amplifier"
  },
  {
  "name": "led_panel"
  },
  {
  "name": "led_panel"
  },
  {
  "name": "pcb"
  }
  ]
  },
  {
  "id": "5ca94a8a77e20d15a7d16d0a",
  "model": "angelina",
  "components": [
  {
  "name": "pcb"
  },
  {
  "name": "money_pcb"
  },
  {
  "name": "touchscreen"
  },
  {
  "name": "speaker"
  },
  {
  "name": "speaker"
  }
  ]
  }
  */

