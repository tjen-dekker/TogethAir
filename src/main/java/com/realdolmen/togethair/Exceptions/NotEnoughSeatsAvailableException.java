package com.realdolmen.togethair.Exceptions;

public class NotEnoughSeatsAvailableException extends Exception {

    public NotEnoughSeatsAvailableException() {
    }

    public NotEnoughSeatsAvailableException(String message) {
        super(message);
    }

    public NotEnoughSeatsAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughSeatsAvailableException(Throwable cause) {
        super(cause);
    }
}
