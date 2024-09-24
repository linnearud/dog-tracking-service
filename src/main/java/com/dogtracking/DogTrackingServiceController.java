package com.dogtracking;

import com.dogtracking.data.track.TrackData;
import com.dogtracking.data.track.TrackCoordinate;
import com.dogtracking.services.DogService;
import com.dogtracking.data.dog.Dog;

import com.dogtracking.services.TrackService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.midi.Track;
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
  public TrackData createOriginalTrack(@RequestBody @Valid TrackData trackData) {
    return trackService.createOriginalTrack(trackData);
  }

  @PutMapping("/original-track/{trackId}")
  public TrackData updateOriginalTrack(@PathVariable("trackId") long trackId, @RequestBody TrackData trackData) {
    return trackService.updateOriginalTrack(trackId, trackData);
  }

  @ResponseStatus(HttpStatus.CREATED) // 201
  @PostMapping("/original-track/{trackId}/coordinate")
  public TrackCoordinate createTrackCoordinate(
      @PathVariable("trackId") long trackId,
      @RequestBody @Valid TrackCoordinate trackCoordinate
  ) {
    return trackService.createTrackCoordinate(trackId, trackCoordinate);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/original-track/{trackId}/dog-tracks/{dogId}")
  public String createDogTrack(
      @PathVariable("trackId") long originalTrackId,
      @PathVariable("dogId") long dogId,
      @RequestBody TrackData trackData
  ) {
    System.out.println(originalTrackId);
    return "";
  }
}