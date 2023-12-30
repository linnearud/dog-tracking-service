package com.dogtracking;

import com.dogtracking.services.DogService;
import com.dogtracking.data.dog.Dog;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class DogTrackingServiceController {
  @Autowired
  private DogService dogService;

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @ResponseStatus(HttpStatus.CREATED) // 201
  @PostMapping("/dog")
  public Dog createDog(@RequestBody @Valid Dog dog) {
    return dogService.create(dog);
  }

  @GetMapping("/dogs")
  public List<Dog> listDogs() {
    return dogService.list();
  }
}