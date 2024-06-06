package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserMapper userMapper;
    private final UserServiceImpl userService;

    /**
     *  Get all users
     * @return all users
     */
    @GetMapping
    public List<UserDto> fetchAllUsers() {
        return userService.fetchAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     *  Get all users in shorter format
     * @return all users in shorter format
     */
    @GetMapping("/simple")
    @ResponseStatus(HttpStatus.OK)
    public List<UserOnlyNameDto> fetchAllUsersInShorter() {
        return userService.fetchAllUsers()
                .stream()
                .map(userMapper::toShortDto)
                .toList();
    }

    /**
     *  Get user in shorter format
     * @param id the ID of the user
     * @return the user in shorter format
     */
    @GetMapping("/simple/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserOnlyNameDto fetchUserInShorter(@PathVariable Long id) {
        return userService.fetchUserViaId(id)
                .map(userMapper::toShortDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
    }

    /**
     *  Get user in full format
     * @param id the ID of the user
     * @return the user in full format
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto fetchUser(@PathVariable Long id) {
        return userService.fetchUserViaId(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
    }

    /**
     *  Get all users with same email
     * @param email the email
     * @return all users with same email
     */
    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public List<UserOnlyEmailDto> fetchUsersByEmail(@RequestParam String email) {
        return userService.fetchAllUsersWithSameEmail(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     *  Get all users with same email part
     * @param emailPart the email part (letters)
     * @return all users with same email
     */
    @GetMapping("/emailPart")
    @ResponseStatus(HttpStatus.OK)
    public List<UserOnlyEmailDto> fetchAllUsersViaEmailAndIgnoreCase(@RequestParam String emailPart) {
        return userService.fetchAllUsersViaEmailAndIgnoreCase(emailPart)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     *  Get all users older than a specific date
     * @param date the date
     * @return all users
     */
    @GetMapping("/older/{time}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> fetchUserThatAreOlderThanDate(@PathVariable("time") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return userService.fetchAllUsersOlderThanDate(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     *  Get all users younger than a specific date
     * @param date the date
     * @return all users
     */
    @GetMapping("/younger/{time}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> fetchUsersThatAreYoungerThanDate(@PathVariable("time") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return userService.fetchAllUsersYoungerThanDate(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     *  Add new user
     * @param userDto the user
     * @return the created user
     * @throws InterruptedException if user already exists
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        if (userService.fetchUserViaEmail(userDto.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email " + userDto.email() + " already exists");
        }
        return userService.createUser(userMapper.toEntity(userDto));
    }


    /**
     *  Delete user
     * @param id the ID of the user
     * @return the deleted user
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        User user = userService.fetchUserViaId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
        userService.deleteUser(user);
    }

    /**
     *  Update user
     * @param id the ID of the user
     * @param userDto the updated user
     * @return the updated user
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.fetchUserViaId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));

        return userService.updateUser(id, userMapper.toEntityWithId(userDto, id));
    }
}