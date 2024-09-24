package com.dogtracking.data.track;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class TrackData {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private LocalDateTime startedAt;

  private LocalDateTime endedAt;

  private String description;

  @OneToMany(mappedBy = "trackData", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TrackCoordinate> coordinates = new ArrayList<TrackCoordinate>();

  public TrackData(String description) {
    this.description = description;
    this.startedAt = LocalDateTime.now();
  }
  public TrackData() {
    this.startedAt = LocalDateTime.now();
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  public LocalDateTime getEndedAt() {
    return endedAt;
  }

  public void setEndedAt(LocalDateTime endedAt) {
    this.endedAt = endedAt;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void addCoordinate(TrackCoordinate coordinate) {
    coordinate.setTrack(this);
    this.coordinates.add(coordinate);
  }
}