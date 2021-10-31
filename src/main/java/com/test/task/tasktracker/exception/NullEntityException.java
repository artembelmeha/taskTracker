package com.test.task.tasktracker.exception;

public class NullEntityException extends RuntimeException {
    public NullEntityException() {    }

    public NullEntityException(String message) {
        super(message);
    }
}
