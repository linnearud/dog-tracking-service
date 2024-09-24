package com.dogtracking.data.track;

import com.dogtracking.data.access.UserOriginalTrackAccess;

import com.dogtracking.data.dog.Dog;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import javax.lang.model.util.Elements;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DogTrack {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL)
  private TrackData trackData;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private Dog dog;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private OriginalTrack originalTrack;

  private String description;

//  public DogTrack(TrackData trackData, UserOriginalTrackAccess uota) {
//    this.trackData = trackData;
//    uota.setOriginalTrack(this);
//    this.users.add(uota);
//  }

  public DogTrack() {}

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