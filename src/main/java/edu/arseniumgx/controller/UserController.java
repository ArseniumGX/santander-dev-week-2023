package edu.arseniumgx.controller;

import edu.arseniumgx.domain.model.User;
import edu.arseniumgx.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserController {
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @PostMapping("/")
  public ResponseEntity<User> create(@RequestBody User user) {
    var userCreated = service.create(user);
    URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}")
            .buildAndExpand(userCreated.getId()).toUri();
    return ResponseEntity.created(location).body(userCreated);
  }
}
