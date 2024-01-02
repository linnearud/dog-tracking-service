package com.dogtracking.services;

import com.dogtracking.data.access.UserOriginalTrackAccess;
import com.dogtracking.data.access.UserOriginalTrackAccessRepository;
import com.dogtracking.data.access.UserOriginalTrackAccessRole;
import com.dogtracking.data.dog.DogRepository;
import com.dogtracking.data.track.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {

  @Autowired
  private TrackRepository trackRepository;

  @Autowired
  private OriginalTrackRepository originalTrackRepository;

  @Autowired
  private UserOriginalTrackAccessRepository userOriginalTrackAccessRepository;

  public Track createOriginalTrack(Track track) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    UserOriginalTrackAccess uota = new UserOriginalTrackAccess(username, UserOriginalTrackAccessRole.OWNER);
    OriginalTrack originalTrack = new OriginalTrack(track, uota);
    originalTrackRepository.save(originalTrack);

    return trackRepository.save(track);
  }

  public TrackCoordinate createTrackCoordinate(TrackCoordinate coordinate, long trackId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    Optional<UserOriginalTrackAccess> uota = userOriginalTrackAccessRepository.findByOriginalTrackIdAndUsername(trackId, username);

    if (uota.isEmpty() || uota.get().getRole() != UserOriginalTrackAccessRole.OWNER) {
      throw new AccessDeniedException("Access Denied");
    };

    Track track = uota.get().getOriginalTrack().getTrack();
    track.registerCoordinate(coordinate);
    trackRepository.save(track);
    
    return coordinate;
  }
}