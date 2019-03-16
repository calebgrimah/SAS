package com.fproject.sas.controller.model;

import com.fproject.sas.domain.User;

import java.util.List;


public class UsersResponseBody {
    List<User> users;

    public UsersResponseBody(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
