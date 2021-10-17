package com.nj.todolist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginRequest {
    @JsonProperty("user_name")
    private String userName;
    private String password;

    public UserLoginRequest() {
    }

    public UserLoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
