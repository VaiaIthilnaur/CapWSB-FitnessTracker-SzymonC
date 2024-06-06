package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for fetching operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserProvider {

    /**
     * Fetches a user via ID
     *
     * @param userId the ID of the user
     * @return the user
     */
    Optional<User> fetchUserViaId(Long userId);

    /**
     * Fetches a user via email
     *
     * @param email the email of the user
     * @return the user
     */
    Optional<User> fetchUserViaEmail(String email);

    /**
     * Fetches a user via part of the email (letters)
     *
     * @param emailPart part of the email
     * @return the user
     */
    Optional<User> fetchUserViaEmailAndIgnoreCase(String emailPart);

    /**
     * Fetches all users via part of the email (letters)
     * @param emailPart part of the email
     * @return all users that matches the email address
     */
    List<User> fetchAllUsersViaEmailAndIgnoreCase(String emailPart);

    /**
     * Fetches all users
     *
     * @return all users
     */
    List<User> fetchAllUsers();

    /**
     * Fetches all users via email
     *
     * @param email the email
     * @return all users that matches described email
     */
    List<User> fetchAllUsersWithSameEmail(String email);

    /**
     * Fetches all users older than the given date
     *
     * @param date the date
     * @return all users older than the given date
     */
    List<User> fetchAllUsersOlderThanDate(LocalDate date);

    /**
     * Fetches all users younger than the given date
     *
     * @param date the date
     * @return all users younger than the given date
     */
    List<User> fetchAllUsersYoungerThanDate(LocalDate date);

}
