package com.dogtracking.data.dog;

import com.dogtracking.data.access.UserDogAccess;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Dog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull
  private String name;
  private LocalDate birthDate;
  @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserDogAccess> users = new HashSet<UserDogAccess>();

  public String getName() {
    return name;
  }

  public void addUser(UserDogAccess uda) {
    uda.setDog(this);
    users.add(uda);
  }
}