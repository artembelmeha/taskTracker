package com.test.task.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.task.tasktracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
