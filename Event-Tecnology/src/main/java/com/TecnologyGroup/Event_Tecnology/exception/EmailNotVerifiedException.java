package com.TecnologyGroup.Event_Tecnology.exception;

public class EmailNotVerifiedException extends RuntimeException{
    public EmailNotVerifiedException() {
        super();
    }
    public EmailNotVerifiedException(String message) {
        super(message);
    }
}
