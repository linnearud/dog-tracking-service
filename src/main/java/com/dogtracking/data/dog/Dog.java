package com.dogtracking.data.dog;

import com.dogtracking.data.access.UserDogAccess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dogtracking.data.track.DogTrack;
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
  private Set<UserDogAccess> users = new HashSet<>();

  @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DogTrack> tracks = new ArrayList<>();

  public void addUser(UserDogAccess uda) {
    uda.setDog(this);
    users.add(uda);
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
}