package com.kaya.authservice.service;

import com.kaya.authservice.entity.AuthUser;
import com.kaya.authservice.exception.AuthException;
import com.kaya.authservice.repository.AuthUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthUserDetailsServiceTest {

  @InjectMocks private AuthUserDetailsService authUserDetailsService;

  @Mock private AuthUserRepository authUserRepository;

  @Test
  public void shouldFindUserDetails() {
    // given
    var username = "username";
    var user = AuthUser.builder().build();

    // when
    when(authUserRepository.findByUsername(username)).thenReturn(Optional.of(user));

    authUserDetailsService.loadUserByUsername(username);
    // verify
    verify(authUserRepository).findByUsername(username);
  }

  @Test(expected = AuthException.class)
  public void shouldNotFindUserDetails() {
    // given
    var username = "username";

    authUserDetailsService.loadUserByUsername(username);
    // when
    when(authUserRepository.findByUsername(username)).thenReturn(Optional.empty());
  }
}
