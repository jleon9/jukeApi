package com.jukeboxes.jukeapi.api.controller;

import com.jukeboxes.jukeapi.Service.JukeService;
import com.jukeboxes.jukeapi.Service.SettingService;
import com.jukeboxes.jukeapi.api.model.Jukebox;
import com.jukeboxes.jukeapi.api.model.Paginated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JukeboxControllerTest {

  @Autowired
  private JukeboxController jukeboxController;

  @Mock
  private JukeService jukeService;

  @Mock
  private SettingService settingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetJukeboxes() {

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
  }

  @Test
  void testGetJukeboxesNoModelOffsetLimit() {
    ResponseEntity<Paginated<Jukebox>> response =
      jukeboxController.getJukeboxes(
        "\"86506865-f971-496e-9b90-75994f251459\"" +
          "",
        null,
        null,
        null);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void testGetJukeboxesNoModel() {
    ResponseEntity<Paginated<Jukebox>> response =
      jukeboxController.getJukeboxes(
        "\"86506865-f971-496e-9b90-75994f251459\"",
        null,
        1,
        3);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void testGetJukeboxesNoOffset() {
    ResponseEntity<Paginated<Jukebox>> response =
      jukeboxController.getJukeboxes(
        "\"86506865-f971-496e-9b90-75994f251459\"",
        "\"fusion\"",
        null,
        3);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void testGetJukeboxesNoLimit() {
    ResponseEntity<Paginated<Jukebox>> response =
      jukeboxController.getJukeboxes(
        "\"86506865-f971-496e-9b90-75994f251459\"",
        "\"fusion\"",
        1,
        null);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void testGetJukeboxesInvalidSettingId() {
    IllegalArgumentException exception =
      assertThrows(IllegalArgumentException.class, () -> jukeboxController.getJukeboxes(
        null,
        null,
        null,
        null));
    Assertions.assertEquals(
      "Please enter a valid settinId",
      exception.getMessage());
  }

  @Test
  void testGetJukeboxesInvalidSettingIdInexistent() {
    NoSuchElementException exception = assertThrows(
      NoSuchElementException.class, () -> jukeboxController.getJukeboxes(
        "invalidSettingId",
        null,
        null,
        null));
    Assertions.assertEquals(
      "No matching setting found for settingId: invalidSettingId",
      exception.getMessage());
  }
}