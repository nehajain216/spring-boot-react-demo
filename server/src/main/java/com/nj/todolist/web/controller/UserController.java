package com.nj.todolist.web.controller;

import com.nj.todolist.model.UserLoginRequest;
import com.nj.todolist.model.UserLoginResponse;
import com.nj.todolist.config.JwtHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private AuthenticationManager authenticationManager;
    private JwtHelper jwtHelper;
    private final Logger LOGGER = LogManager.getLogger(this.getClass());


    public UserController(AuthenticationManager authenticationManager, JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        String userName = userLoginRequest.getUserName();
        String password = userLoginRequest.getPassword();
        UsernamePasswordAuthenticationToken authParameters =
                new UsernamePasswordAuthenticationToken(userName, password);

        try {
            authenticationManager.authenticate(authParameters);
            String token = jwtHelper.createToken(userName);
            UserLoginResponse userLoginResponse = new UserLoginResponse(token);
            LOGGER.info("Login response created");
            return ResponseEntity.ok().body(userLoginResponse);
        } catch (BadCredentialsException exception) {
            LOGGER.error(exception.getMessage());
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }
}
