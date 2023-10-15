package com.jukeboxes.jukeapi.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Component {

  @JsonProperty("name")
  private String componentName;

  public Component() {}

  public Component(String componentName) {
    this.componentName = componentName;
  }

  public String getComponentName() {
    return componentName;
  }

  public void setComponentName(String componentName) {
    this.componentName = componentName;
  }
}
