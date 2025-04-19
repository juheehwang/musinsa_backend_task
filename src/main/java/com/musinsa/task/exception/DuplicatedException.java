package com.musinsa.task.exception;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message) {
      super(message);
    }

    public DuplicatedException(String message, Throwable cause) {
      super(message, cause);
    }

}
