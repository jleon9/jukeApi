package com.jukeboxes.jukeapi.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingJSON {

  @JsonProperty("settings")
  private Setting[] settings;

  public SettingJSON() {}

  public SettingJSON(Setting[] settings){
    this.settings = settings;
  }

  /**
   * @return The array of Setting objects contained in the JSON response
   */
  public Setting[] getSettings() {
    return settings;
  }
}
