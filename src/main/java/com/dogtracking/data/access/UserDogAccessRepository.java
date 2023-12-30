package com.dogtracking.data.access;

import com.dogtracking.data.access.UserDogAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDogAccessRepository extends JpaRepository<UserDogAccess, Long> {

}