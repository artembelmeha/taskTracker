package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service")
public interface UserClient {
    @DeleteMapping( "users/task/{taskId}")
    public void removeTaskFromUser(@PathVariable("taskId") Long taskId);

}
