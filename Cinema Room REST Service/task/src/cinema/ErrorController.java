package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(SeatNotAvailableException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private Error handleErrorMessage(SeatNotAvailableException e) {
        Error error = new Error();
        error.setError(e.getMessage());
        return error;
    }

}
