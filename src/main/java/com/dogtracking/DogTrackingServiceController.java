package com.dogtracking;

import com.dogtracking.data.track.Track;
import com.dogtracking.data.track.TrackCoordinate;
import com.dogtracking.services.DogService;
import com.dogtracking.data.dog.Dog;

import com.dogtracking.services.TrackService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class DogTrackingServiceController {
  @Autowired
  private DogService dogService;

  @Autowired
  private TrackService trackService;

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @ResponseStatus(HttpStatus.CREATED) // 201
  @PostMapping("/dog")
  public Dog createDog(@RequestBody @Valid Dog dog) {
    return dogService.create(dog);
  }

  @GetMapping("/dogs")
  public List<Dog> listDogs() {
    return dogService.list();
  }

  @ResponseStatus(HttpStatus.CREATED) // 201
  @PostMapping("/original-track")
  public Track createOriginalTrack(@RequestBody @Valid Track track) {
    return trackService.createOriginalTrack(track);
  }

  @ResponseStatus(HttpStatus.CREATED) // 201
  @PostMapping("/original-track/{trackId}/coordinate")
  public TrackCoordinate createTrackCoordinate(
      @PathVariable("trackId") long trackId,
      @RequestBody @Valid TrackCoordinate trackCoordinate
  ) {
    return trackService.createTrackCoordinate(trackCoordinate, trackId);
  }
}