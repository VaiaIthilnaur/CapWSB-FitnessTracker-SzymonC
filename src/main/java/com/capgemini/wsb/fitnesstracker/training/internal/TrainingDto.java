package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import jakarta.annotation.Nullable;

import java.util.Date;

record TrainingDto(@Nullable Long id, UserDto user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
}

record TrainingWithUserIdDto(@Nullable Long id, Long userId, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {}