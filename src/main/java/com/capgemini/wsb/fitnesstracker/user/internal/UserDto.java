package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {

}

record UserOnlyNameDto(Long id, String firstName, String lastName) {
}

record UserOnlyEmailDto(Long id, String email) {}

record UserWithoutBirthdateDto(Long id, String firstName, String lastName, String email) {}

