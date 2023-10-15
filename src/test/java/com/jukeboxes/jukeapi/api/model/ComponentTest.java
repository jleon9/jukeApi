package com.jukeboxes.jukeapi.api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComponentTest {

  @Test
  void getComponentName() {
    Component c = new Component("touchscreen");
    assertEquals(c.getComponentName(), "touchscreen", "Test getComponentName");
  }

  @Test
  void setComponentName() {
    Component c = new Component("touchscreen");
    c.setComponentName("pcb");
    assertEquals(c.getComponentName(), "pcb", "Test setComponentName");
  }
}