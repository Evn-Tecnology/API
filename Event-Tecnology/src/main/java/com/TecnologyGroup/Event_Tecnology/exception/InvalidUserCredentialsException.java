package com.TecnologyGroup.Event_Tecnology.exception;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super();
    }
    public InvalidUserCredentialsException(String message) {
        super(message);
    }
}
