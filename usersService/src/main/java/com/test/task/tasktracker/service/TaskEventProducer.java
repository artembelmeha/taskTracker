package com.test.task.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskEventProducer {


    private static final String TASK_ID = "taskId";
    public ReactiveRedisTemplate<String, String> redisTemplate;

    @Autowired
    private TaskEventProducer(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void  publishEvent(long taskId){
        this.redisTemplate.opsForStream()
                .add(StreamRecords.newRecord().ofObject(taskId).withStreamKey(TASK_ID))
                .subscribe(System.out::println);
    }

}
