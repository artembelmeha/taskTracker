package com.test.task.tasktracker.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.test.task.tasktracker.model.Task;
import com.test.task.tasktracker.model.User;
import com.test.task.tasktracker.repository.TaskRepository;
import com.test.task.tasktracker.service.TaskService;
import com.test.task.tasktracker.service.UserService;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserService userService;

    private TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task create(Task task, long userId) {
        Task newTask = task;
        newTask.setOwner(userService.readById(userId));
        return taskRepository.save(newTask);
    }

    @Override
    public Task readById(long id) {
        Optional<Task> optional = taskRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
    }

    @Override
    public Task update(Task task) {
        readById(task.getTaskId());
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task, long userId) {
        User user = userService.readById(userId);
        task.setOwner(user);
        return taskRepository.save(task);
    }

    @Override
    public void delete(long id) {
        taskRepository.delete(readById(id));
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }

    @Override
    public List<Task> getAllTaskByUserId(long userId) {
        List<Task> tasks = taskRepository.getAllTaskByUserId(userId);
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }
}
