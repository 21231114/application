package com.example.myapplication.entity;

public class UserInfo {
    private int user_id;
    private String username;
    private String password;
    private int register_type;

    public UserInfo(int user_id, String username, String password, int register_type) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.register_type = register_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRegister_type() {
        return register_type;
    }

    public void setRegister_type(int register_type) {
        this.register_type = register_type;
    }
}
