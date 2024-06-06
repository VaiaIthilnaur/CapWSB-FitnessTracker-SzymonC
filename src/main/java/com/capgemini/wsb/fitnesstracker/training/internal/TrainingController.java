package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserServiceImpl userService;


    /**
     *  Get all trainings
     * @return all trainings
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDto> fetchAllTrainings() {
        return trainingService.fetchAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }


    /**
     *  Get all trainings for a specific user
     * @param userId the ID of the user
     * @return all trainings for the user
     */
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDto> fetchTrainingsViaUser(@PathVariable Long userId) {
        return trainingService.fetchTrainingsViaUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     *  Get all trainings ending after a specific date
     * @param date the date
     * @return all trainings
     */
    @GetMapping("/finished/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDto> fetchTrainingsEndingAfter(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return trainingService.fetchTrainingsEndingAfterDate(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     *  Get all trainings via activity type
     * @param activityType the activity type
     * @return all trainings
     */
    @GetMapping("/activityType")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDto> fetchTrainingsViaType(@RequestParam String activityType) {
        ActivityType aType = ActivityType.valueOf(activityType);
        return trainingService.fetchTrainingsViaActivityType(aType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     *  Create a new training
     * @param trainingDto the training to be created
     * @return the created training with ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingWithUserIdDto trainingDto) {
        long userId = trainingDto.userId();
        User user = userService.fetchUserViaId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Training training = trainingService.createTraining(trainingMapper.toEntity(trainingDto, user, null));
        return trainingMapper.toDto(training);
    }

    /**
     *  Update a training
     * @param id the ID of the training
     * @param trainingDto the updated training
     * @return the updated training
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingWithUserIdDto trainingDto) {
        Training originalTraining = trainingService.fetchById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Training training = trainingService.updateTraining(id, trainingMapper.toEntity(trainingDto, originalTraining.getUser(), id));
        return trainingMapper.toDto(training);
    }

    /**
     *  Delete a training via ID
     * @param id the ID of the training to be deleted
     * @return the deleted training
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTraining(@PathVariable Long id) {
        Training training = trainingService.fetchTrainingViaId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
        trainingService.deleteTraining(training);
    }

}
