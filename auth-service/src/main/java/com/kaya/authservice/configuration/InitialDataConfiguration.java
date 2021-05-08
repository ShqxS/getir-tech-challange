package com.kaya.authservice.configuration;

import com.kaya.authservice.entity.AuthUser;
import com.kaya.authservice.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class InitialDataConfiguration {

  private final AuthUserRepository authUserRepository;
  private final PasswordEncoder passwordEncoder;

  @PostConstruct
  public void init() {
    var user =
        AuthUser.builder()
            .id("22e0aed5-3686-43a8-ab3f-3d0c186fca34")
            .username("admin")
            .password(passwordEncoder.encode("admin"))
            .permissions(Arrays.asList("READ_BOOK", "WRITE_BOOK", "READ_ORDER", "WRITE_ORDER"))
            .build();
    authUserRepository.save(user);
  }
}
