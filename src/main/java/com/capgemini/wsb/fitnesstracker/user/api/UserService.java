package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user object
     *
     * @param  user the user object
     * @return user object
     */

    User createUser(User user);


    /**
     * Deletes a user object
     *
     * @param user the user object
     */
    void deleteUser(User user);

    /**
     * Updates a user object
     * @param id id of the user
     * @param user the user object
     * @return user object that has been created
     */
    User updateUser(Long id, User user);



}
