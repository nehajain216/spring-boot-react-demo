package com.nj.todolist.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JwtHelper.class)
class JwtHelperTest {

    @Autowired
    private JwtHelper jwtHelper;

    @Test
    public void testIfProperTokenIsGenerated(){
        String userName = "neha";

        String token = jwtHelper.createToken(userName);

        assertEquals(jwtHelper.verifyToken(token), userName);
    }
}