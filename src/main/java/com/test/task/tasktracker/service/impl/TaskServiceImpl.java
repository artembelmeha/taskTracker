package com.test.task.tasktracker.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.task.tasktracker.exception.NullEntityException;
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
        try {
            return taskRepository.save(task);
        } catch (RuntimeException e) {
            throw new NullEntityException("Task cannot be 'null'");
        }
    }

    @Override
    public Task create(Task task, long userId) {
        try {
            task.setOwner(userService.readById(userId));
            return taskRepository.save(task);
        } catch (RuntimeException e) {
            throw new NullEntityException("Task cannot be 'null'");
        }
    }

    @Override
    public Task readById(long id) {
        Optional<Task> optional = taskRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Task with id " + id + " not found");
    }

    @Override
    public Task update(long taskId, Task task) {
        if (task != null) {
            Task oldTask = readById(taskId);
            if (oldTask != null) {
                task.setTaskId(taskId);
                return taskRepository.save(task);
            }
        }
        throw new NullEntityException("Task cannot be 'null'");
    }

    @Override
    public Task update(long taskId, Task task, long userId) {
        if (task != null) {
            User user = userService.readById(userId);
            Task oldTask = readById(userId);
            if (oldTask != null) {
                task.setTaskId(taskId);
                task.setOwner(user);
                return taskRepository.save(task);
            }
        }
        throw new NullEntityException("Task cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Task Task = readById(id);
        if (Task != null) {
            taskRepository.delete(Task);
        } else {
            throw new NullEntityException("Task cannot be 'null'");
        }
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
