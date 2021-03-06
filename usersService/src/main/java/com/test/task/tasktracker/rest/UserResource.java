package com.test.task.tasktracker.rest;

import static com.test.task.tasktracker.uri.ResourcePaths.USERS_PATH;
import static com.test.task.tasktracker.uri.ResourcePaths.USERS_TASK_ID_PATH;
import static com.test.task.tasktracker.uri.ResourcePaths.USER_ID_PATH;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
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
import com.test.task.tasktracker.service.TaskEventProducer;
import com.test.task.tasktracker.service.UserService;

@RestController
@RequestMapping(USERS_PATH)
public class UserResource {

    private TaskEventProducer taskEventProducer;

    private UserService userService;

    @Autowired
    public UserResource(TaskEventProducer taskEventProducer, UserService userService) {
        this.taskEventProducer = taskEventProducer;
        this.userService = userService;
    }

    @GetMapping(USER_ID_PATH)
    public ResponseEntity<User> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.readById(userId)
                .add(linkTo(methodOn(UserResource.class).getUsers()).withSelfRel()));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping(USER_ID_PATH)
    public void deleteUser(@PathVariable long userId) {
        userService.getAllTasksIdByUserId(userId)
                .forEach(taskId->taskEventProducer.publishEvent(taskId));
        userService.delete(userId);
    }

    @DeleteMapping(USERS_TASK_ID_PATH)
    public void deleteTaskFromUserByTaskId(@PathVariable long taskId) {
        userService.deleteTaskFromUserByTaskId(taskId);
    }


    @GetMapping
    public ResponseEntity<CollectionModel<User>> getUsers() {
        List<User> users = userService.getAll();
        users.forEach(user -> {
            user.add(linkTo(methodOn(UserResource.class).getUser(user.getUserId())).withSelfRel());
            user.add(linkTo(methodOn(UserResource.class).updateUser(user)).withSelfRel());
        });
        Link allUsersLink = linkTo(methodOn(UserResource.class).getUsers()).withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(users, allUsersLink));
    }
}
