package com.mariapuri.mydom.auth.service;

import com.mariapuri.mydom.auth.entity.UserModel;
import com.mariapuri.mydom.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
      implements UserService {

  private final UserRepository userRepository;
  public UserServiceImpl(
        UserRepository userRepository) {

    this.userRepository = userRepository;
  }

  @Override
  public UserModel buscarPorAuth0Id(String auth0Id) {
    return userRepository.findByAuth0Id(auth0Id)
          .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + auth0Id));
  }

}
