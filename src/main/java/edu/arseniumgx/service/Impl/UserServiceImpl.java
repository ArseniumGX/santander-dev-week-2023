package edu.arseniumgx.service.Impl;

import edu.arseniumgx.domain.model.User;
import edu.arseniumgx.domain.repository.UserRepository;
import edu.arseniumgx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @Override
  public User create(User user) {
    if (userRepository.existsByAccountNumber(user.getAccount().getNumber()))
      throw new IllegalArgumentException("This account number already exists");
    return userRepository.save(user);
  }
}
