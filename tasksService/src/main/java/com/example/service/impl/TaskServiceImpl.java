package com.example.service.impl;

import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
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
