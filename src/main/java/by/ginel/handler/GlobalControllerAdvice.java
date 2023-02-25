package by.ginel.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public String notFoundEntityMessage() {
        return "No Such Mapping or Entity not found";
    }
    @ExceptionHandler(RuntimeException.class)
    public String catchRuntimeException() {
        return "Service expirienced some problems";
    }
}
