package com.kaya.authservice.service;

import com.kaya.authservice.entity.AuthUser;
import com.kaya.authservice.exception.AuthException;
import com.kaya.authservice.exception.CodeEnum;
import com.kaya.authservice.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserDetailsService implements UserDetailsService {

  private final AuthUserRepository authUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AuthUser user =
        authUserRepository
            .findByUsername(username)
            .orElseThrow(() -> new AuthException(CodeEnum.CONTENT_NOT_FOUND_ERROR));
    new AccountStatusUserDetailsChecker().check(user);
    log.info("User found with username: {}, and user: {}", username, user.toString());
    return user;
  }
}
