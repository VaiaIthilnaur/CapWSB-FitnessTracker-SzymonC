package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;


    @Override
    public Optional<Training> fetchTrainingViaId(final Long trainingId) {
        log.info("Fetching Training with id: {}", trainingId);
        return trainingRepository.findById(trainingId);
    }
    @Override
    public Optional<User> fetchUserViaTrainingId(final Long trainingId) {
        log.info("Fetching Training with id: {}", trainingId);
        return Optional.ofNullable(trainingRepository.getReferenceById(trainingId).getUser());
    }


    @Override
    public Optional<Training> fetchById(Long id) {
        log.info("Fetching Training with id: {}", id);
        return trainingRepository.findById(id);
    }


    @Override
    public List<Training> fetchAllTrainings() {
        log.info("Fetching all Trainings");
        return trainingRepository.findAll();
    }


    @Override
    public Training createTraining(Training training) {
        log.info("Creating Training {}", training);
        return trainingRepository.save(training);
    }


    @Override
    public List<Training> fetchTrainingsViaUserId(Long userId) {
        log.info("Fetching all Trainings for user with id: {}", userId);
        return trainingRepository.fetchViaUserId(userId);
    }

    @Override
    public List<Training> fetchTrainingsEndingAfterDate(Date date) {
        log.info("Fetching all Trainings that end after: {}", date);
        return trainingRepository.fetchViaEndTimeAfter(date);
    }

    @Override
    public List<Training> fetchTrainingsStartAfterDate(Date date) {
        log.info("Fetching all Trainings that start after: {}", date);
        return trainingRepository.fetchViaStartTimeAfter(date);
    }

    @Override
    public List<Training> fetchTrainingsViaActivityType(ActivityType type) {
        log.info("Fetching all Trainings that start after: {}", type);
        return trainingRepository.fetchViaActivityType(type);
    }

    @Override
    public Training updateTraining(Long trainingId, Training newData) {
        log.info("Updating Training with id: {}", trainingId);
        Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new IllegalArgumentException("User with id " + trainingId + " not found"));

        if (newData.getUser() != null) {
            log.info("User has been found: {}", newData.getUser());
            training.setUser(newData.getUser());
        }
        if (newData.getStartTime() != null) {
            log.info("User has been found: {}", newData.getStartTime());
            training.setStartTime(newData.getStartTime());
        }
        if (newData.getEndTime() != null) {
            log.info("User has been found: {}", newData.getEndTime());
            training.setEndTime(newData.getEndTime());
        }
        if (newData.getActivityType() != null) {
            log.info("User has been found: {}", newData.getActivityType());
            training.setActivityType(newData.getActivityType());
        }
        if (newData.getDistance() > 0.0) {
            log.info("User has been found: {}", newData.getDistance());
            training.setDistance(newData.getDistance());
        }
        if (newData.getAverageSpeed() > 0.0) {
            log.info("User has been found: {}", newData.getAverageSpeed());
            training.setAverageSpeed(newData.getAverageSpeed());
        }

        return trainingRepository.save(training);
    }

    @Override
    public void deleteTraining(Training training) {
        log.info("Deleting Training {}", training);
        trainingRepository.delete(training);
    }
}
