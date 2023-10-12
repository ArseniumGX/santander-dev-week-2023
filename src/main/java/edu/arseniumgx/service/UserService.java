package edu.arseniumgx.service;

import edu.arseniumgx.domain.model.User;

public interface UserService {
  User findById(Long id);
  User create(User user);
}
