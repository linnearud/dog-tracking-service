package com.dogtracking.services;

import com.dogtracking.data.access.UserOriginalTrackAccess;
import com.dogtracking.data.access.UserOriginalTrackAccessRepository;
import com.dogtracking.data.access.UserOriginalTrackAccessRole;
import com.dogtracking.data.track.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackService {

  @Autowired
  private TrackDataRepository trackDataRepository;

  public TrackData createOriginalTrack(TrackData trackData) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    UserOriginalTrackAccess uota = new UserOriginalTrackAccess(username, UserOriginalTrackAccessRole.OWNER);
    OriginalTrack originalTrack = new OriginalTrack(trackData, uota);
    originalTrackRepository.save(originalTrack);

    return trackDataRepository.save(trackData);
  }

  @Autowired
  private OriginalTrackRepository originalTrackRepository;

  @Autowired
  private UserOriginalTrackAccessRepository userOriginalTrackAccessRepository;

  private TrackData getOriginalTrack(long trackId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    Optional<UserOriginalTrackAccess> uota = userOriginalTrackAccessRepository.findByOriginalTrackIdAndUsername(trackId, username);

    if (uota.isEmpty() || uota.get().getRole() != UserOriginalTrackAccessRole.OWNER) {
      throw new AccessDeniedException("Access Denied");
    };

    return uota.get().getTrackData();
  }

  public TrackData updateOriginalTrack(long trackId, TrackData trackData) {
    TrackData savedTrackData = getOriginalTrack(trackId);

    if (trackData.getDescription() != null) {
      savedTrackData.setDescription(trackData.getDescription());
    }

    if (trackData.getEndedAt() != null) {
      savedTrackData.setEndedAt(trackData.getEndedAt());
    }

    return trackDataRepository.save(savedTrackData);
  }

  public TrackCoordinate createTrackCoordinate(long trackId, TrackCoordinate coordinate) {
    TrackData trackData = getOriginalTrack(trackId);
    trackData.addCoordinate(coordinate);
    trackDataRepository.save(trackData);
    
    return coordinate;
  }
}