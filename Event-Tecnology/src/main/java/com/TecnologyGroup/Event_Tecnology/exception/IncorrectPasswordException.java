package com.TecnologyGroup.Event_Tecnology.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super();
    }
    public IncorrectPasswordException(String message) {
        super(message);
    }
}