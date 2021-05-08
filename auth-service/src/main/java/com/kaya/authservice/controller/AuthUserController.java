package com.kaya.authservice.controller;

import com.kaya.authservice.dto.UserCreateDTO;
import com.kaya.authservice.dto.UserResponseDTO;
import com.kaya.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthUserController.ENDPOINT)
@RequiredArgsConstructor
public class AuthUserController {

  public static final String ENDPOINT = "user";

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(
      @Validated @RequestBody UserCreateDTO userCreateDTO) {
    return ResponseEntity.ok(UserResponseDTO.mapToDTO(userService.create(userCreateDTO)));
  }
}
