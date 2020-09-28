package com.ly.model;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @Description
 * @Created by Administrator
 * @Date 2020/9/23 15:52
 */
public class User {

    public interface WithoutPasswordView {};

    public interface WithPasswordView extends WithoutPasswordView {};

    private String name;
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @JsonView(WithoutPasswordView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonView(WithPasswordView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
