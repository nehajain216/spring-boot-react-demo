package com.nj.todolist.model;

public class UserLoginResponse {
    private String token;

    public UserLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
