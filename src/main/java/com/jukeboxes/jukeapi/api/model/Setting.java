package com.jukeboxes.jukeapi.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Setting {
  @JsonProperty("id")
  private String id;

  @JsonProperty("requires")
  private List<String> requirements;

  public Setting() {}

  public Setting(String id) {
    this.id = id;
    requirements  = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public List<String> getRequirements() {
    return requirements;
  }

  public void addRequirement(String r) {
    requirements.add(r);
  }

  public void removeRequirement(String r) {
    requirements.remove(r);
  }
}
