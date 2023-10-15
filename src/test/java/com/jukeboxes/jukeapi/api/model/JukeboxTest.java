package com.jukeboxes.jukeapi.api.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JukeboxTest {

  @Test
  void getId() {
    Jukebox jukebox = new Jukebox("123", "angelina");
    assertEquals("123", jukebox.getId());
  }

  @Test
  void getModel() {
    Jukebox jukebox = new Jukebox("123", "fusion");
    assertEquals("fusion", jukebox.getModel());
  }

  @Test
  void getComponents() {
    Jukebox jukebox = new Jukebox("123", "fusion");
    jukebox.addComponent(new Component("camera"));
    assertEquals(1, jukebox.getComponents().size());
    assertEquals("camera", jukebox.getComponents().get(0).getComponentName());

  }

  @Test
  void addComponent() {
    Jukebox jukebox = new Jukebox("000", "virtuo");
    Component component = new Component("pcb");
    jukebox.addComponent(component);

    List<Component> components = jukebox.getComponents();
    assertEquals(1, components.size());
    assertEquals("pcb", components.get(0).getComponentName());
  }

  @Test
  void removeComponent() {
    Jukebox jukebox = new Jukebox("123", "virtuo");
    Component component = new Component("amplifier");
    jukebox.addComponent(component);

    jukebox.removeComponent(component);
    List<Component> components = jukebox.getComponents();
    assertNotNull(components);
    assertEquals(0, components.size());
  }

  @Test
  void getJukeComponentNames() {
  }

  @Test
  void supportsSetting() {
    Jukebox jukebox = new Jukebox("567", "virtuo");
    Component component1 = new Component("camera");
    Component component2 = new Component("speaker");
    jukebox.addComponent(component1);
    jukebox.addComponent(component2);

    Setting setting = new Setting("1");
    setting.addRequirement("camera");
    setting.addRequirement("speaker");

    assertTrue(jukebox.supportsSetting(setting));
  }
}