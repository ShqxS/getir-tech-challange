package com.kaya.authservice.service;

import com.kaya.authservice.dto.UserCreateDTO;
import com.kaya.authservice.entity.AuthUser;
import com.kaya.authservice.exception.AuthException;
import com.kaya.authservice.exception.CodeEnum;
import com.kaya.authservice.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final AuthUserRepository authUserRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthUser create(UserCreateDTO userCreateDTO) {

    var userDB = authUserRepository.findByUsername(userCreateDTO.getUsername());
    if (userDB.isPresent()) {
      throw new AuthException(CodeEnum.CONFLICT_ERROR);
    }

    var user =
        AuthUser.builder()
            .id(UUID.randomUUID().toString())
            .username(userCreateDTO.getUsername())
            .password(passwordEncoder.encode(userCreateDTO.getPassword()))
            .permissions(Arrays.asList("READ_BOOK", "WRITE_BOOK", "READ_ORDER", "WRITE_ORDER"))
            .build();

    log.info("User created, user: {}", user.toString());

    return authUserRepository.save(user);
  }
}
