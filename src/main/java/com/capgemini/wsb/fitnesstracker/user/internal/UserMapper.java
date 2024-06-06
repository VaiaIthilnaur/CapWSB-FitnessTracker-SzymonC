package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    UserOnlyNameDto toShortDto(User user) {
        return new UserOnlyNameDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    UserOnlyEmailDto toEmailDto(User user) {
        return new UserOnlyEmailDto(user.getId(),
                user.getEmail());
    }

    public User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    public User toEntityWithId(UserDto userDto, Long id) {
        return new User(
                id,
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }
}
