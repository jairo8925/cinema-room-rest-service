package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** This class is the exception that occurs if the ticket
 * for the chair has already been purchased or the row and
 * column number passed does not belong to any seat
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CinemaException extends RuntimeException {

    CinemaException() {
        super();
    }

    CinemaException(String message) {
        super(message);
    }

}
