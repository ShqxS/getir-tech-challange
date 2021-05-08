package com.kaya.authservice.dto;

import com.kaya.authservice.entity.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

  private String username;

  public static UserResponseDTO mapToDTO(AuthUser authUser) {
    return UserResponseDTO.builder().username(authUser.getUsername()).build();
  }
}
