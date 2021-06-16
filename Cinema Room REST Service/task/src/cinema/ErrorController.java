package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(InvalidSeatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    private Error handleInvalidSeatException(InvalidSeatException e) {
        return new Error(e.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    private Error handleInvalidTokenException(InvalidTokenException e) {
        return new Error(e.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    private Error handleWrongPasswordException(WrongPasswordException e) {
        return new Error(e.getMessage());
    }

}
