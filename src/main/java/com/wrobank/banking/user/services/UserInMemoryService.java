package com.wrobank.banking.user.services;

import com.wrobank.banking.user.domain.User;

import java.util.*;

public class UserInMemoryService implements UserService {

    Map<String, User> users = Map.of(
            "user1", User.builder().userName("user1").id(1L).build(),
            "user2", User.builder().userName("user2").id(2L).build(),
            "user3", User.builder().userName("user3").id(3L).build());

    @Override
    public User findUserByName(String name) {
        return users.get(name);
    }

    @Override
    public Collection<User> findAllUsers() {
        return users.values();
    }
}
