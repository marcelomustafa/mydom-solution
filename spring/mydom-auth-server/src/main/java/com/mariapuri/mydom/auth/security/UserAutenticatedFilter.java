package com.mariapuri.mydom.auth.security;

import com.mariapuri.mydom.auth.entity.UserModel;
import com.mariapuri.mydom.auth.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class UserAutenticatedFilter
      extends OncePerRequestFilter {

  private final UserService userService;
  public UserAutenticatedFilter(
        UserService userService) {

    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
        throws ServletException, IOException {

    var authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
      String auth0Id = jwt.getSubject();
      UserModel user = userService.buscarPorAuth0Id(auth0Id);

      List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> (GrantedAuthority) () -> "ROLE_" + role.getName())
            .toList();

      UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(user, jwt.getTokenValue(), authorities);

      SecurityContextHolder.getContext().setAuthentication(token);
    }

    filterChain.doFilter(request, response);
  }

//  @Override
//  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
//    return null;
//  }
//
//  @Override
//  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
//    return null;
//  }


//  @Override
//  protected void doFilterInternal(HttpServletRequest request,
//                                  HttpServletResponse response,
//                                  FilterChain filterChain)
//        throws ServletException, IOException {
//
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//    if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
//      String auth0Id = jwt.getSubject();
//      UserModel user = userService.buscarPorAuth0Id(auth0Id);
//
//      // Crie um novo Authentication com roles reais do banco
//      List<GrantedAuthority> authorities = user.getRoles().stream()
//            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//            .toList();
//
//      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//            user, jwt.getTokenValue(), authorities);
//
//      SecurityContextHolder.getContext().setAuthentication(auth);
//    }
//
//    filterChain.doFilter(request, response);
//  }


}
