package com.TecnologyGroup.Event_Tecnology.exception;

public class PagoNotFoundException extends RuntimeException {
    public PagoNotFoundException() {
        super();
    }

    public PagoNotFoundException(String message) {
        super(message);
    }
}