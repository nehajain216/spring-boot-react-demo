package com.nj.todolist.service;

import com.nj.todolist.entity.User;
import com.nj.todolist.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Example<User> requestUser = Example.of(new User(username, null));
        Optional<User> resultUser = userRepository.findOne(requestUser);

        if (resultUser.isEmpty()) {
            LOGGER.error("Username is not available");
            throw new UsernameNotFoundException("Username is not available");
        }

        User user = resultUser.get();
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Collections.emptyList());
    }
}
