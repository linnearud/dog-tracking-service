package com.dogtracking.data.access;

import com.dogtracking.data.access.UserOriginalTrackAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOriginalTrackAccessRepository extends JpaRepository<UserOriginalTrackAccess, Long> {
  Optional<UserOriginalTrackAccess> findByOriginalTrackIdAndUsername(long originalTrackId, String username);
}