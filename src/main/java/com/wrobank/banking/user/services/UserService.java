package com.wrobank.banking.user.services;

import com.wrobank.banking.user.domain.User;

import java.util.Collection;

public interface UserService {
    User findUserByName(String name);

    Collection<User> findAllUsers();
}
