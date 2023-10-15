package com.jukeboxes.jukeapi.api.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaginatedTest {

  @Test
  void getItems() {
    List<Integer> items = Arrays.asList(1, 2, 3);
    Paginated<Integer> paginated = new Paginated<>(items, 3, 1, 1);
    assertEquals(items, paginated.getItems(), "Items should match");
  }

  @Test
  void getNumItems() {
    Paginated<Integer> paginated = new Paginated<>(new ArrayList<>(), 10, 2, 1);
    assertEquals(10, paginated.getNumItems(), "NumItems should match");
  }

  @Test
  void getNumPages() {
    Paginated<Integer> paginated = new Paginated<>(new ArrayList<>(), 20, 4, 3);
    assertEquals(4, paginated.getNumPages(), "NumPages should match");
  }

  @Test
  void getCurrentPage() {
    Paginated<Integer> paginated = new Paginated<>(new ArrayList<>(), 30, 5, 2);
    assertEquals(2, paginated.getCurrentPage(), "CurrentPage should match");
  }
}