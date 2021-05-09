package com.kaya.authservice.service;

import com.kaya.authservice.dto.UserCreateDTO;
import com.kaya.authservice.entity.AuthUser;
import com.kaya.authservice.repository.AuthUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  @InjectMocks private UserService userService;

  @Mock private AuthUserRepository authUserRepository;
  @Mock private PasswordEncoder passwordEncoder;

  private AuthUser user;

  @Before
  public void init() {
    user = AuthUser.builder().username("username").password("encodedPassword").build();
  }

  @Test
  public void shouldCreate() {
    // given
    var password = "password";
    var username = "username";
    var userCreateDTO = UserCreateDTO.builder().username(username).password(password).build();
    var argumentCaptor = ArgumentCaptor.forClass(AuthUser.class);

    // when
    when(authUserRepository.findByUsername(username)).thenReturn(Optional.empty());
    when(passwordEncoder.encode(password)).thenReturn(user.getPassword());

    userService.create(userCreateDTO);
    // verify
    verify(authUserRepository).findByUsername(username);
    verify(passwordEncoder).encode(password);
    verify(authUserRepository).save(argumentCaptor.capture());
    assertEquals(username, argumentCaptor.getValue().getUsername());
    assertEquals(user.getPassword(), argumentCaptor.getValue().getPassword());
  }

  @Test(expected = RuntimeException.class)
  public void shouldNotCreateWhenUserAlreadyExists() {
    // given
    var password = "password";
    var username = "username";
    var userCreateDTO = UserCreateDTO.builder().username(username).password(password).build();
    var argumentCaptor = ArgumentCaptor.forClass(AuthUser.class);

    // when
    when(authUserRepository.findByUsername(username)).thenReturn(Optional.of(user));

    userService.create(userCreateDTO);
  }
}
