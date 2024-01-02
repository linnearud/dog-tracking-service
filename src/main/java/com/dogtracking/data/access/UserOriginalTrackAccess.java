package com.dogtracking.data.access;

import com.dogtracking.data.track.OriginalTrack;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class UserOriginalTrackAccess {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private String username;

  @NotNull
  private UserOriginalTrackAccessRole role;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private OriginalTrack originalTrack;

  public UserOriginalTrackAccess(String username, UserOriginalTrackAccessRole role) {
    this.username = username;
    this.role = role;
  }

  public UserOriginalTrackAccess() {
  }

  public void setOriginalTrack(OriginalTrack originalTrack) {
    this.originalTrack = originalTrack;
  }

  public UserOriginalTrackAccessRole getRole() { return this.role; }

  public OriginalTrack getOriginalTrack() {
    return originalTrack;
  }
}