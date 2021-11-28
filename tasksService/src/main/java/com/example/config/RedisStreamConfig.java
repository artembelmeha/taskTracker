package com.example.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

@Configuration
public class RedisStreamConfig {

    private static final String TASK_ID = "taskId";
    @Autowired
    private StreamListener<String, ObjectRecord<String, Long>> streamListener;

    @Autowired
    public ReactiveRedisTemplate<String, String> redisTemplate;

    @Bean
    public Subscription subscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {

        this.redisTemplate.opsForStream()
                .add(StreamRecords.newRecord().ofObject(0L).withStreamKey(TASK_ID))
                .subscribe(System.out::println);
        this.redisTemplate.opsForStream().createGroup(TASK_ID, TASK_ID).subscribe(System.out::println);

        StreamMessageListenerContainer listenerContainer = StreamMessageListenerContainer
                .create(redisConnectionFactory, StreamMessageListenerContainer
                        .StreamMessageListenerContainerOptions
                        .builder()
                        .pollTimeout(Duration.ofSeconds(1))
                        .targetType(Long.class)
                        .build());
        Subscription subscription = listenerContainer.receiveAutoAck(
                Consumer.from(TASK_ID, InetAddress.getLocalHost().getHostName()),
                StreamOffset.create(TASK_ID, ReadOffset.lastConsumed()), streamListener);
        listenerContainer.start();
        return subscription;
    }



}
