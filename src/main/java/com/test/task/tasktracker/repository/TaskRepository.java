package com.test.task.tasktracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.task.tasktracker.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select * from tasks where owner_id = ?1", nativeQuery = true)
    List<Task> getAllTaskByUserId(long userId);
}
