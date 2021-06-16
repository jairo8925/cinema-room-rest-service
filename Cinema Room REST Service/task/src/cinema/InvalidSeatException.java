package cinema;

/** This exception occurs if the ticket for the seat
 * has already been purchased or the row and column
 * number passed does not belong to any seat
 */
class InvalidSeatException extends RuntimeException {

    InvalidSeatException() {
        super();
    }

    InvalidSeatException(String message) {
        super(message);
    }

}
