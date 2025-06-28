package com.mariapuri.mydom.auth.repository;

import com.mariapuri.mydom.auth.entity.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository
      extends JpaRepository<RoleModel, UUID> {

}
