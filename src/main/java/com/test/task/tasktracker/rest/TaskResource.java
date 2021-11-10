package com.test.task.tasktracker.rest;

import static com.test.task.tasktracker.uri.ResourcePaths.TASKS_PATH;
import static com.test.task.tasktracker.uri.ResourcePaths.TASK_ID_PATH;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.test.task.tasktracker.model.Task;
import com.test.task.tasktracker.service.TaskService;

@RestController
@RequestMapping(TASKS_PATH)
public class TaskResource {

    private TaskService taskService;

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam long userId, @Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.create(task, userId));
    }

    @GetMapping(TASK_ID_PATH)
    public ResponseEntity<Task> getTask(@PathVariable long taskId) {
        return ResponseEntity.ok(taskService.readById(taskId));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(@RequestParam(required = false) Optional<Long> userId) {
        if (userId.isPresent()) {
            return ResponseEntity.ok(taskService.getAllTaskByUserId(userId.get()));
        }
        return ResponseEntity.ok(taskService.getAll());
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestParam long userId,  @Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(task, userId));
    }

    @DeleteMapping(TASK_ID_PATH)
    public void deleteTask(@PathVariable long taskId) {
        taskService.delete(taskId);
    }

}
