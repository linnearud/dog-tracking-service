package com.dogtracking.data.track;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackDataRepository extends JpaRepository<TrackData, Long> {
}