package com.imooc.entity;

import java.util.HashSet;
import java.util.Set;

public class UserSet {
    Set<User> users;

    public UserSet() {
        users = new HashSet<>();
        users.add(new User());
        users.add(new User());
    }

    @Override
    public String toString() {
        return "UserSet{" +
                "users=" + users +
                '}';
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
