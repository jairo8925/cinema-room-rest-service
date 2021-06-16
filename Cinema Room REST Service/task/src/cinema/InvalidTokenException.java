package cinema;

/** This exception occurs if the token passed does not belong to any ticket */
public class InvalidTokenException extends RuntimeException {

    InvalidTokenException() {
        super();
    }

    InvalidTokenException(String message) {
        super(message);
    }

}

