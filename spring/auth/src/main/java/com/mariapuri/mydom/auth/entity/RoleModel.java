package com.mariapuri.mydom.auth.entity;

import com.mariapuri.mydom.auth.entity.defaultEntity.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TBL_ROLE")
@Getter @Setter
public class RoleModel
        extends Model {

  @Column(name = "NAME", nullable = false, unique = true, length = 80)
  private String name;

}