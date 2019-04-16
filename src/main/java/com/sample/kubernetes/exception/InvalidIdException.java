package com.sample.kubernetes.exception;

public class InvalidIdException extends RuntimeException {

  private static final long serialVersionUID = 248378603729719700L;

  public InvalidIdException(String message) {
    super(message);
  }

  public InvalidIdException(String message, Throwable cause) {
    super(message, cause);
  }

}
