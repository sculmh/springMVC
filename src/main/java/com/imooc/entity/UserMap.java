package com.imooc.entity;

import java.util.Map;

public class UserMap {
    Map<String,User> users;

    @Override
    public String toString() {
        return "UserMap{" +
                "users=" + users +
                '}';
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
