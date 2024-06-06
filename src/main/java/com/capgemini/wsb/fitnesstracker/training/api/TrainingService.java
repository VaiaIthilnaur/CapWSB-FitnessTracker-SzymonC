package com.capgemini.wsb.fitnesstracker.training.api;


import com.capgemini.wsb.fitnesstracker.user.api.User;

/**
 * Interface (API) for modifying operations on {@link Training} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingService {

    /**
     * Creates a new training
     *
     * @param training the training to be created
     * @return the created training
     */
    Training createTraining(Training training);

    /**
     *  Updates a training via ID and the provided training
     *
     * @param id the ID of the training
     * @param training the training
     * @return the training
     */
    Training updateTraining(Long id, Training training);

    /**
     *  Deletes a training
     * @param training the training to be deleted
     * @return the deleted training
     */
    void deleteTraining(Training training);
}
