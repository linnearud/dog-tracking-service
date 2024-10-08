package com.dogtracking.data.track;

import com.dogtracking.data.access.UserOriginalTrackAccess;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class OriginalTrack {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL)
  private TrackData trackData;
  @OneToMany(mappedBy = "originalTrack", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserOriginalTrackAccess> users = new HashSet<UserOriginalTrackAccess>();

  @OneToMany(mappedBy = "originalTrack", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DogTrack> dogTracks = new ArrayList<>();
  private String description;

  public OriginalTrack(TrackData trackData, UserOriginalTrackAccess uota) {
    this.trackData = trackData;
    uota.setOriginalTrack(this);
    this.users.add(uota);
  }

  public OriginalTrack() {}

  public TrackData getTrackData() {
    return trackData;
  }

  public void setTrackData(TrackData trackData) {
    this.trackData = trackData;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}