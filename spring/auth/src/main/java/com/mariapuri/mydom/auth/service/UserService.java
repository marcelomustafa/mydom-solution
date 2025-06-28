package com.mariapuri.mydom.auth.service;

import com.mariapuri.mydom.auth.entity.UserModel;

public interface UserService {
  UserModel buscarPorAuth0Id(String auth0Id);
}
