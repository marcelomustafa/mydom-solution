package com.mariapuri.mydom.auth.config;

import com.mariapuri.mydom.auth.security.UserAutenticatedFilter;
import com.mariapuri.mydom.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

  private final UserService userService;
  public SecurityConfig(
        UserService userService){

    this.userService = userService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {

    http
          .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
          )
          .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                      .jwtAuthenticationConverter(jwtAuthenticationConverter())
                )
          )
          .addFilterAfter(new UserAutenticatedFilter(userService),
                SecurityContextHolderAwareRequestFilter.class);

    return http.build();
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(jwt -> List.of()); // Sem claims diretas do token
    return converter;
  }
}
