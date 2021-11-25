package com.test.task.tasktracker.service;

import java.util.List;

import com.test.task.tasktracker.model.User;

public interface UserService {
    User create(User user);
    User readById(long id);
    User update(User user);
    void delete(long id);
    List<User> getAll();
    List<Long> getAllTasksIdByUserId(long userId);
    void deleteTaskFromUserByTaskId(long taskId);
}
