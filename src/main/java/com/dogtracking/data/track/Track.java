package com.dogtracking.data.track;

import com.dogtracking.data.access.UserDogAccess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Track {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private LocalDateTime startedAt;

  private LocalDateTime endedAt;

  private String description;

  @OneToMany(mappedBy = "track", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TrackCoordinate> coordinates = new ArrayList<TrackCoordinate>();

  public Track(String description) {
    this.description = description;
    this.startedAt = LocalDateTime.now();
  }
  public Track() {
    this.startedAt = LocalDateTime.now();
  }

  public void endTrack() {
    this.endedAt = LocalDateTime.now();
  }

  public void registerCoordinate(TrackCoordinate coordinate) {
    coordinate.setTrack(this);
    this.coordinates.add(coordinate);
  }

}