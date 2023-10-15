package com.jukeboxes.jukeapi.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingJSON {

  @JsonProperty("settings")
  private Setting[] settings;

  public SettingJSON() {}

  public SettingJSON(Setting[] settings){
    this.settings = settings;
  }

  public Setting[] getSettings() {
    return settings;
  }
}
