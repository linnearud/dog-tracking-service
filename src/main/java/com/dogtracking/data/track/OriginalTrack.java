package com.dogtracking.data.track;

import com.dogtracking.data.access.UserOriginalTrackAccess;
import com.dogtracking.data.track.Track;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OriginalTrack {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL)
  private Track track;
  @OneToMany(mappedBy = "originalTrack", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserOriginalTrackAccess> users = new HashSet<UserOriginalTrackAccess>();

  private String description;

  public OriginalTrack(Track track, UserOriginalTrackAccess uota) {
    this.track = track;
    uota.setOriginalTrack(this);
    this.users.add(uota);
  }

  public OriginalTrack() {}

  public Track getTrack() {
    return track;
  }
}