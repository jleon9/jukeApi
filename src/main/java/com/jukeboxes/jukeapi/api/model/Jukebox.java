package com.jukeboxes.jukeapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Jukebox {
  @JsonProperty("id")
  private String id;

  @JsonProperty("model")
  private String model;

  @JsonProperty("components")
  private List<Component> components;

  public Jukebox() {}

  public Jukebox (
    String id,
    String model
  ) {
    this.id = id;
    this.model = model;
    this.components = new ArrayList<Component>();
  }

  public String getId() {
    return id;
  }


  public String getModel() {
    return model;
  }


  public List<Component> getComponents() {
    return components;
  }

  public void addComponent(Component c) {
    components.add(c);
  }

  public void removeComponent(Component c) {
    components.remove(c);
  }


  /**
   * @return The names of the Jukebox components
   */
  @JsonIgnore
  public List<String> getJukeComponentNames() {
    List<String> jukeComponents = new ArrayList<>();
    for (Component c : this.getComponents()) {
      jukeComponents.add(c.getComponentName());
    }
    return jukeComponents;
  }

  public boolean supportsSetting(Setting s) {
    return this.getJukeComponentNames().containsAll(s.getRequirements());
  }

}

