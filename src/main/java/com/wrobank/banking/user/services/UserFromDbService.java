package com.wrobank.banking.user.services;

import com.wrobank.banking.common.domain.UserWasNotFoundException;
import com.wrobank.banking.user.domain.User;
import com.wrobank.banking.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFromDbService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new UserWasNotFoundException("User is not found: " + username));
    }
}
