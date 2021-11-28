package com.example.service;

import lombok.SneakyThrows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class TaskEventConsumer  implements StreamListener<String, ObjectRecord<String, Long>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskEventConsumer.class);

    public TaskService taskService;

    @Autowired
    private TaskEventConsumer(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    @SneakyThrows
    public void onMessage(ObjectRecord<String, Long> record) {
        LOGGER.info("Task even Consumer received message to delete task with id {}", record.getValue());
        taskService.delete(record.getValue());
    }

}
