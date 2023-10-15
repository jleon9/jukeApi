package com.jukeboxes.jukeapi.api.model;

import java.util.ArrayList;
import java.util.List;

public class Paginated<T> {
  private List<T> items;
  private long numItems;
  private long numPages;
  private int currentPage;

  public Paginated(List<T> items, long numItems, long numPages, int currentPage) {
    this.items = items;
    this.numItems = numItems;
    this.numPages = numPages;
    this.currentPage = currentPage;
  }

  public List<T> getItems() { return items; }

  public long getNumItems() { return numItems; }

  public long getNumPages() {
    return numPages;
  }

  public int getCurrentPage() {
    return currentPage;
  }

}
