package com.test.task.tasktracker.rest;

import static com.test.task.tasktracker.uri.ResourcePaths.USERS_PATH;
import static com.test.task.tasktracker.uri.ResourcePaths.USER_ID_PATH;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.task.tasktracker.model.User;
import com.test.task.tasktracker.service.UserService;

@RestController
@RequestMapping(USERS_PATH)
public class UserResource {

    private UserService userService;

    private UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(USER_ID_PATH)
    public ResponseEntity<User> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.readById(userId));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping(USER_ID_PATH)
    public void deleteUser(@PathVariable long userId) {
        userService.delete(userId);
    }


    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(users);
    }
}
