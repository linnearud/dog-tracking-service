package com.dogtracking.data.access;

import com.dogtracking.data.access.UserDogAccessRole;
import com.dogtracking.data.dog.Dog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class UserDogAccess {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private String username;

  @NotNull
  private UserDogAccessRole role;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private Dog dog;

  public UserDogAccess(String username, UserDogAccessRole role) {
    this.username = username;
    this.role = role;
  }

  public UserDogAccess() {
  }

  public void setDog(Dog dog) {
    this.dog = dog;
  }
}