package com.dogtracking.data.track;

import com.dogtracking.data.dog.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OriginalTrackRepository extends JpaRepository<OriginalTrack, Long> {
}