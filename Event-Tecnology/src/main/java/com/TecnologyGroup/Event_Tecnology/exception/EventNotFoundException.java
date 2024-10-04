package com.TecnologyGroup.Event_Tecnology.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException() {
        super();
    }

    public EventNotFoundException(String mensaje) {
        super(mensaje);
    }
}
