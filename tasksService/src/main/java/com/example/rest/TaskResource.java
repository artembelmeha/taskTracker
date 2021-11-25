package com.example.rest;

import static com.example.uri.ResourcePaths.TASKS_PATH;
import static com.example.uri.ResourcePaths.TASK_ID_PATH;

import com.example.model.Task;
import com.example.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(TASKS_PATH)
public class TaskResource {

    private TaskService taskService;

    @Autowired
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.create(task));
    }

    @GetMapping(TASK_ID_PATH)
    public ResponseEntity<Task> getTask(@PathVariable long taskId) {
        return ResponseEntity.ok(taskService.readById(taskId));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(@RequestParam(required = false) Optional<Long> userId) {
        return userId.map(aLong -> ResponseEntity.ok(taskService.getAllTaskByUserId(aLong)))
                .orElseGet(() -> ResponseEntity.ok(taskService.getAll()));
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(task));
    }

    @DeleteMapping(TASK_ID_PATH)
    public void deleteTask(@PathVariable long taskId) {
        taskService.delete(taskId);
    }

}
