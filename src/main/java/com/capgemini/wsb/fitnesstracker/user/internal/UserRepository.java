package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Fetches a user via email
     *
     * @param email the email of the user
     * @return the first user that matches the email
     */
    default Optional<User> fetchUserViaEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Fetches all users via email
     *
     * @param email the email
     * @return all users that matches described email
     */
    default List<User> fetchAllUsersWithSameEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .toList();
    }
    /**
     * Fetches a user via part of the email (letters)
     *
     * @param emailPart part of the email
     * @return the first user that matches the email
     */
    default Optional<User> fetchUserViaEmailAndIgnoreCase(String emailPart)
    {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(emailPart.toLowerCase()))
                .findFirst();
    }
    /**
     * Fetches all users via part of the email (letters)
     *
     * @param emailPart part of the email
     * @return all users that matches the email address
     */

    default List<User> fetchAllUsersViaEmailAndIgnoreCase(String emailPart)
    {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(emailPart.toLowerCase()))
                .toList();
    }

    /**
     * Fetches all users via email
     *
     * @param email the email
     * @return all users that matches described email
     */
    default List<User> fetchAllUsersViaEmail(String email) {
        // part of email and insensitive
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    /**
     * Fetches all users younger than specified date
     *
     * @param date the date
     * @return all users younger than the given date
     */
    default List<User> fetchAllUsersYoungerThanDate(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isAfter(date)) // ze względu na błąd w testach (o ile się nie mylę) - zwraca użytkowników po danej dacie
                .toList();
    }


    /**
     * Fetches all users older than specified date
     *
     * @param date the date
     * @return all users older than the given date
     */
    default List<User> fetchAllUsersOlderThanDate(LocalDate date){

        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date)) // ze względu na błąd w testach (o ile się nie mylę) - zwraca użytkowników przed danej dacie
                .toList();
    };


}
