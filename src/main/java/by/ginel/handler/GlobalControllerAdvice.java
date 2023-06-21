package by.ginel.handler;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex) {
        String message = ex.getMessage();
        log.error(message, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> catchRuntimeException(Exception ex) {
        String message = ex.getMessage();
        log.error(message, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<Object> catchAccessDeniedException(Exception ex) {
        String message = ex.getMessage();
        log.error(message, ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }
}
