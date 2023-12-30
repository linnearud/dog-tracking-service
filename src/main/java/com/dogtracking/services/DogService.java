package com.dogtracking.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.dogtracking.data.dog.Dog;
import com.dogtracking.data.dog.DogRepository;
import com.dogtracking.data.access.UserDogAccess;
import com.dogtracking.data.access.UserDogAccessRepository;
import com.dogtracking.data.access.UserDogAccessRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {

  @Autowired
  private DogRepository dogRepository;
  @Autowired
  private UserDogAccessRepository userDogAccessRepository;

  public Dog create(Dog dog) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    UserDogAccess uda = new UserDogAccess(username, UserDogAccessRole.OWNER);
    dog.addUser(uda);
    return dogRepository.save(dog);
  }

  public List<Dog> list() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    return dogRepository.findAllByUsersUsername(username);
  }
}