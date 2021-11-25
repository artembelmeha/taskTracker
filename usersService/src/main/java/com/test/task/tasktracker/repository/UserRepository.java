package com.test.task.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.task.tasktracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "delete from user_my_tasks where my_tasks = ?1", nativeQuery = true)
    void deleteTaskFromUserByTaskId(long myTaskId);
}
