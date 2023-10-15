package com.jukeboxes.jukeapi.api.model;

import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class SettingTest {

  @Test
  void getId() {
    Setting setting = new Setting("12");
    setting.addRequirement("camera");
    assertEquals("12", setting.getId());
  }

  @Test
  void getRequirements() {
    Setting setting = new Setting("1");
    setting.addRequirement("camera");
    assertEquals(1, setting.getRequirements().size());
    assertEquals("camera", setting.getRequirements().get(0));

  }

  @Test
  void addRequirement() {
    Setting setting = new Setting("1");
    setting.addRequirement("camera");

    List<String> requirements = setting.getRequirements();
    assertEquals(1, requirements.size());
    assertEquals("camera", requirements.get(0));
  }

  @Test
  void removeRequirement() {
    Setting setting = new Setting("1");
    setting.addRequirement("camera");

    List<String> requirements = setting.getRequirements();
    assertEquals(1, requirements.size());
    assertEquals("camera", requirements.get(0));
  }
}