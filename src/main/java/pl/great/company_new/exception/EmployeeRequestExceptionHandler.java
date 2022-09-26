package pl.great.company_new.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class EmployeeRequestExceptionHandler {

    @ExceptionHandler(value = EmployeeRequestException.class)
    public ResponseEntity<Object> handleEmployeeRequestException(EmployeeRequestException e) {
        EmployeeException exception = new EmployeeException(e.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now());
        return new ResponseEntity<>(exception, exception.getHttpStatus());
    }
}
