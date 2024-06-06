package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) { throw new IllegalArgumentException("User has already DB ID, update is not permitted!");}
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(final User user) {
        log.info("Deleting User {}", user);
        userRepository.delete(user);
    }

    @Override
    public Optional<User> fetchUserViaId(final Long userId) {
        log.info("Fetching User via ID{}", userId);
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> fetchUserViaEmail(final String email) {
        log.info("Fetching User via email {}", email);
        return userRepository.fetchUserViaEmail(email);
    }
    @Override
    public Optional<User> fetchUserViaEmailAndIgnoreCase(String emailPart) {
        log.info("Fetching User via email {}", emailPart);
        return userRepository.fetchUserViaEmailAndIgnoreCase(emailPart);
    }
    public List<User> fetchAllUsersViaEmailAndIgnoreCase(String emailPart) {
        log.info("Fetching User via email {}", emailPart);
        return userRepository.fetchAllUsersViaEmailAndIgnoreCase(emailPart);
    }

    @Override
    public List<User> fetchAllUsers() {
        log.info("Fetching all Existing Users");
        return userRepository.findAll();
    }
    @Override
    public List<User> fetchAllUsersWithSameEmail(String email) {
        log.info("Fetching all Existing Users with same email {}", email);
        return userRepository.fetchAllUsersViaEmail(email);
    }
    @Override
    public List<User> fetchAllUsersOlderThanDate(LocalDate date) {
        log.info("Fetching all Existing Users older than date {}", date);
        return userRepository.fetchAllUsersOlderThanDate(date);
    }
    @Override
    public List<User> fetchAllUsersYoungerThanDate(LocalDate date) {
        log.info("Fetching all Existing Users older than date {}", date);
        return userRepository.fetchAllUsersYoungerThanDate(date);
    }
    @Override
    public User updateUser(Long updatedUserId,User newData) {
        log.info("Updating User with id: {}", updatedUserId);
        User user = userRepository.findById(updatedUserId).orElseThrow(() -> new IllegalArgumentException("User with id " + updatedUserId + " not found"));

        if (newData.getFirstName() != null) {
            log.info("First name found: {}", newData.getFirstName());
            user.setFirstName(newData.getFirstName());
        }
        if (newData.getLastName() != null) {
            log.info("Last name found: {}", newData.getLastName());
            user.setLastName(newData.getLastName());
        }
        if (newData.getBirthdate() != null) {
            log.info("Birthdate found: {}", newData.getBirthdate());
            user.setBirthdate(newData.getBirthdate());
        }
        if (newData.getEmail() != null) {
            log.info("Email found: {}", newData.getEmail());
            user.setEmail(newData.getEmail());
        }
        return userRepository.save(user);
    }
}