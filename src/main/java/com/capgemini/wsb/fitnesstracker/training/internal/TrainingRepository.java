package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

interface TrainingRepository extends JpaRepository<Training, Long> {


    /**
     *  Fetches all trainings for a specific user
     * @param userId the ID of the user
     * @return all trainings
     */
    default List<Training> fetchViaUserId(Long userId) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .toList();
    }

    /**
     *  Fetches all trainings ending after a specific date
     * @param date the date to compare
     * @return all trainings that end after the specified date
     */
    default List<Training> fetchViaEndTimeAfter(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime().compareTo(date) > 0)
                .toList();
    }

    /**
     *  Fetches all trainings starting after a specific date
     * @param date the date to compare
     * @return all trainings that start after the specified date
     */
    default List<Training> fetchViaEndTimeBefore(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime().compareTo(date) < 0)
                .toList();
    }

    /**
     *  Fetches all trainings starting after a specific date
     * @param date the date to compare
     * @return all trainings that start after the specified date
     */
    default List<Training> fetchViaStartTimeAfter(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime().compareTo(date) > 0)
                .toList();
    }

    /**
     *  Fetches all trainings ending after a specific date
     * @param date the date to compare
     * @return all trainings that end after the specified date
     */
    default List<Training> fetchViaStartTimeBefore(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime().compareTo(date) < 0)
                .toList();
    }

    /**
     *  Fetches all trainings via activity type
     * @param type the type to compare
     * @return all trainings that end after the specified date
     */
    default List<Training> fetchViaActivityType(ActivityType type) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getActivityType(), type))
                .toList();
    }
}
