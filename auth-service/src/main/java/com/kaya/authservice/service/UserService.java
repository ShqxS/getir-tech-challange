package com.kaya.authservice.service;

import com.kaya.authservice.dto.UserCreateDTO;
import com.kaya.authservice.entity.AuthUser;
import com.kaya.authservice.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

  private final AuthUserRepository authUserRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthUser create(UserCreateDTO userCreateDTO) {

    var userDB = authUserRepository.findByUsername(userCreateDTO.getUsername());
    if (userDB.isPresent()) {
      throw new RuntimeException("User already exist");
    }

    var user =
        AuthUser.builder()
            .id(UUID.randomUUID().toString())
            .username(userCreateDTO.getUsername())
            .password(passwordEncoder.encode(userCreateDTO.getPassword()))
            .permissions(Arrays.asList("READ_BOOK", "WRITE_BOOK", "READ_ORDER", "WRITE_ORDER"))
            .build();
    return authUserRepository.save(user);
  }
}
