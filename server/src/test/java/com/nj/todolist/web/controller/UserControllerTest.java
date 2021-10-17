package com.nj.todolist.web.controller;

import com.nj.todolist.config.JwtHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class UserControllerTest {
    private MockMvc mvc;

    private AuthenticationManager authenticationManager;
    private JwtHelper jwtHelper;

    @BeforeEach
    public void setup() {
        authenticationManager = mock(AuthenticationManager.class);
        jwtHelper = mock(JwtHelper.class);
        UserController userController = new UserController(authenticationManager, jwtHelper);
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testShouldCheckForSuccessLogin() throws Exception {
        String userName = "neha";
        Authentication authentication = new UsernamePasswordAuthenticationToken(userName, "neha123");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_name", userName);
        jsonObject.put("password", "neha123");

        when(authenticationManager.authenticate(authentication)).thenReturn(authentication);
        when(jwtHelper.createToken(userName)).thenReturn("12345");

        mvc.perform(post("/user/login")
                .content(jsonObject.toString())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"12345\"}"));
    }

    @Test
    public void testShouldCheckForFailedLogin() throws Exception {
        Authentication authentication = new UsernamePasswordAuthenticationToken("neha", "neha12");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_name", "neha");
        jsonObject.put("password", "neha12");

        when(authenticationManager.authenticate(authentication)).thenThrow(BadCredentialsException.class);

        mvc.perform(post("/user/login")
                .content(jsonObject.toString())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

}