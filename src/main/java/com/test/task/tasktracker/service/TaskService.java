package com.test.task.tasktracker.service;

import java.util.List;

import com.test.task.tasktracker.model.Task;

public interface TaskService extends CrudService<Task>{
    Task create(Task task, long userId);
    Task update(long id, Task task, long userId);
    List<Task> getAllTaskByUserId(long userId);
}
