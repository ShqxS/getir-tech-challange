package com.kaya.authservice.configuration;

import com.kaya.authservice.entity.AuthUser;
import com.kaya.authservice.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitialDataConfiguration {

  private final AuthUserRepository authUserRepository;
  private final PasswordEncoder passwordEncoder;

  @PostConstruct
  public void init() {
    var user =
        AuthUser.builder()
            .id(UUID.randomUUID().toString())
            .username("admin")
            .password(passwordEncoder.encode("admin"))
            .permissions(Arrays.asList("READ_BOOK", "WRITE_BOOK"))
            .build();
    authUserRepository.save(user);
  }
}
