package com.jukeboxes.jukeapi.api.controller;
import com.jukeboxes.jukeapi.Service.JukeService;
import com.jukeboxes.jukeapi.Service.SettingService;
import com.jukeboxes.jukeapi.api.model.Jukebox;
import com.jukeboxes.jukeapi.api.model.Paginated;
import com.jukeboxes.jukeapi.api.model.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@RestController
public class JukeboxController {

  @Autowired
  private JukeService jukeService;

  @Autowired
  private SettingService settingService;

  public JukeboxController() {}

  public JukeboxController(JukeService jukeService, SettingService settingService) {
    this.jukeService = jukeService;
    this.settingService = settingService;
  }


  /**
   * @param settingId The Id of the setting containing the requirements
   *                  that the jukeboxes must meet.
   * @param model     (optional)
   *                  Additional filter that requires the jukeboxes
   *                  to be of a certain model.
   * @param offset    (optional)
   *                  Starting index of the page (0 by default).
   * @param limit     (optional)
   *                  Maximum number of jukeboxes in a page
   *                  (total number of jukeboxes ny default)
   * @return HTTP Response that holds a Paginated object containing
   * the paginated list, the total number of elements in the list,
   * the number of pages, and the number of the current page.
   */
  @GetMapping("/api/jukeboxes")
  //HashMap<String, List<Jukebox>>
  public ResponseEntity<Paginated<Jukebox>> getJukeboxes(
    @RequestParam(name = "settingId") String settingId,
    @RequestParam(name = "model", required = false) String model,
    @RequestParam(name = "offset", required = false) Integer offset,
    @RequestParam(name = "limit", required = false) Integer limit
  ) {
    if (settingId == null) {
      throw new IllegalArgumentException("Please enter a valid settinId");
    }

    // List of jukeboxes parsed from the JSON
    List<Jukebox> jukeList = jukeService.fetchJukeboxData();

    // List of settings parsed from the JSON
    Setting[] settingsArray = settingService.fetchSettingData().getSettings();

    // Setting object possibly returned that has the queried settingId
      Setting queriedSetting = Arrays.stream(settingsArray)
        .filter(setting -> ("\"" + setting.getId() + "\"").equals(settingId))
        .findFirst().orElseThrow(() -> new NoSuchElementException("No matching setting found for settingId: " + settingId));

    // List of Jukeboxes that satisfy the setting's requirements
    List<Jukebox> filteredJukes = jukeList.stream()
      .filter(jukebox -> jukebox.supportsSetting(queriedSetting))
      .toList();

    if (model != null) {
      filteredJukes = filteredJukes.stream()
        .filter(jukebox -> ("\"" + jukebox.getModel() + "\"").equals(model))
        .toList();
    }

    // Handle optional offset and limit parameters to avoid NullPointerExceptions.
    if (offset == null) {
      offset = 0;
    }
    if (limit == null) {
      limit = filteredJukes.size();
    }

    Paginated<Jukebox> paginatedJukes = jukeService.paginate(filteredJukes, offset, limit);

    // Returns a HashMap with settingId as a single key and its corresponding Jukeboxes that meet the requirements.
    HashMap<String, List<Jukebox>> result = new HashMap<>();
    result.put(settingId.substring(1, settingId.length() - 1), filteredJukes);


    try {
      return ResponseEntity.status(HttpStatus.OK)
        .body(paginatedJukes);

    } catch (HttpClientErrorException.BadRequest e) {
      throw e;
    }
  }
}
