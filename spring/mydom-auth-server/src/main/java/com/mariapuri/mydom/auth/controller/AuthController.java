package com.mariapuri.mydom.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @GetMapping("/public/hello")
  public String publicHello() {
    return "Hello World (Public)";
  }

  @GetMapping("/private/hello")
  public String privateHello() {
    return "Hello World (Protected)";
  }
}
