package com.gimaletdinov.exampleProject.controller.handler.exceptions;

public class NoSuchObjectException extends RuntimeException{

    public NoSuchObjectException(String message) {
        super(message);
    }
}
