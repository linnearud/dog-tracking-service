package com.dogtracking.data.dog;

import com.dogtracking.data.dog.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
  List<Dog> findAllByUsersUsername(String username);
}