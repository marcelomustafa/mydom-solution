package com.mariapuri.mydom.auth.repository;

import com.mariapuri.mydom.auth.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository
      extends JpaRepository<UserModel, UUID> {

  Optional<UserModel> findByAuth0Id(String auth0Id);
}
