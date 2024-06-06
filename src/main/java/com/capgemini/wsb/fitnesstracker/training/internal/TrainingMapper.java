package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import org.springframework.stereotype.Component;

@Component
class TrainingMapper {

    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                new UserMapper().toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());

    }

    Training toEntity(TrainingWithUserIdDto trainingDto, User user, Long id) {
        return new Training(id,
                user,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed());
    }
}
