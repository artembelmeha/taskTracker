package com.test.task.tasktracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.task.tasktracker.model.Task;
import com.test.task.tasktracker.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    private TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<Task> createTask(@PathVariable long userId, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.create(task, userId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable long taskId) {
        return ResponseEntity.ok(taskService.readById(taskId));
    }

    @PutMapping("/{taskId}/user/{userId}")
    public ResponseEntity<Task> updateTask(@PathVariable long taskId, @PathVariable long userId, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(taskId, task, userId));
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable long taskId) {
        taskService.delete(taskId);
    }

    @GetMapping("/users/{userId}/all")
    public ResponseEntity<List<Task>> getTaskByOwnerId(@PathVariable long userId) {
        return ResponseEntity.ok(taskService.getAllTaskByUserId(userId));
    }



}
