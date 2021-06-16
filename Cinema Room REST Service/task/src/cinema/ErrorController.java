package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(CinemaException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private Error handleErrorMessage(CinemaException e) {
        return new Error(e.getMessage());
    }

}
