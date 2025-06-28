package com.mariapuri.mydom.auth.entity;

import com.mariapuri.mydom.auth.entity.defaultEntity.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "TBL_USER")
@Getter @Setter
public class UserModel
        extends Model {

  @Column(name = "AUTH0_ID", unique = true, nullable = false)
  private String auth0Id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "EMAIL")
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "USER_ROLE",
        joinColumns = @JoinColumn(name = "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
  private Set<RoleModel> roles;

}
