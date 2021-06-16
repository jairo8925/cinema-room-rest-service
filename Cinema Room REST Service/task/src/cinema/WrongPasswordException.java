package cinema;

/** This exception occurs if the password passed is incorrect */
class WrongPasswordException extends RuntimeException {

    WrongPasswordException() {
        super();
    }

    WrongPasswordException(String message) {
        super(message);
    }
}
