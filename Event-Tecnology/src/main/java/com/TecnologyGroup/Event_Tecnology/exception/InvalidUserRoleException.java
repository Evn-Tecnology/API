package com.TecnologyGroup.Event_Tecnology.exception;

public class InvalidUserRoleException extends RuntimeException {
    public InvalidUserRoleException() {
        super();
    }

    public InvalidUserRoleException(String message) {
        super(message);
    }
}