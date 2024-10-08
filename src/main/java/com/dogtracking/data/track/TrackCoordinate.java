package com.dogtracking.data.track;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(indexes = @Index(columnList = "timestamp"))
public class TrackCoordinate {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  @NotNull
  private LocalDateTime timestamp;

  @NotNull
  private String type;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private TrackData trackData;

  public TrackCoordinate() {}

  public void setTrack(TrackData trackData) {
    this.trackData = trackData;
  }

  public String getType() {
    return type;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }
}