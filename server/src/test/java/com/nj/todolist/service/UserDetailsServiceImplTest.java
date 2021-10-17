package com.nj.todolist.service;

import com.nj.todolist.entity.User;
import com.nj.todolist.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplTest {
    private UserRepository userRepository;
    private UserDetailsServiceImpl appUserDetailsService;

    @BeforeEach
    public void setUp(){
        userRepository = mock(UserRepository.class);
        appUserDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void shouldCheckIfUserReturnedOnSuccess() {
        String userName = "neha";
        String password = "neha123";
        Example<User> requestUser = Example.of(new User(userName, null));
        User resultUser = new User(userName, password);

        when(userRepository.findOne(requestUser)).thenReturn(Optional.of(resultUser));
        UserDetails userDetails = appUserDetailsService.loadUserByUsername(userName);
        org.springframework.security.core.userdetails.User expectedUser = new org.springframework.security.core.userdetails.User(userName, password, Collections.emptyList());

        assertThat(userDetails, is(expectedUser));
    }

    @Test
    public void shouldCheckIfExceptionThrownOnFailure() {
        String userName = "neha";
        Example<User> requestUser = Example.of(new User(userName, null));

        when(userRepository.findOne(requestUser)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> appUserDetailsService.loadUserByUsername(userName));
    }

}