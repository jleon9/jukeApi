package com.jukeboxes.jukeapi.api.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SettingJSONTest {

  @Test
  void getSettings() {
    Setting s1 = new Setting("1");
    s1.addRequirement("camera"); s1.addRequirement("speaker");

    Setting s2 = new Setting("2");
    s2.addRequirement("touchscreen"); s2.addRequirement("led_panel");

    Setting[] settings = {s1, s2};
    SettingJSON settingJSON = new SettingJSON(settings);

    assertArrayEquals(settings, settingJSON.getSettings(), "Settings should match");
  }
}