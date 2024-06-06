package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    //*
    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    default Optional<User> getTraining(Long trainingId) {
        return Optional.empty();
    }

    /**
     * Fetches a training via ID
     *
     * @param trainingId the ID of the training
     * @return the training
     */
    Optional<Training> fetchTrainingViaId(Long trainingId);

    /**
     * Fetches a training via ID
     *
     * @param trainingId the ID of the training
     * @return the training
     */
    Optional<User> fetchUserViaTrainingId(Long trainingId);


    /**
     * Fetches all trainings
     *
     * @return all trainings
     */
    List<Training> fetchAllTrainings();

    /**
     *  Fetches all trainings for a specific user
     * @param userId the ID of the user
     * @return all trainings
     */
    List<Training> fetchTrainingsViaUserId(Long userId);

    /**
     *  Fetches all trainings ending after a specific date
     * @param date the date
     * @return all trainings tgat end after the specified date
     */
    List<Training> fetchTrainingsEndingAfterDate(Date date);

    /**
     *   Fetches all trainings starting after a specific date
     * @param date the date
     * @return all trainings that start after the specified date
     */
    List<Training> fetchTrainingsStartAfterDate(Date date);

    /**
     * Fetches trainings for a user specified activity type
     *
     * @param type the activity type
     * @return all trainings with the specified activity type
     */

    List<Training> fetchTrainingsViaActivityType(ActivityType type);


    /**
     * Creates a new training
     *
     * @param training the training to be created
     * @return the created training
     */
    Training createTraining(Training training);


    /**
     *  Fetches a training by ID
     * @param id    the ID of the training
     * @return the training
     */
    Optional<Training> fetchById(Long id);
}
