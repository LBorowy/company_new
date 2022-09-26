package pl.great.company_new.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class EmployeeException {
    private final String msg;
    private final HttpStatus httpStatus;
    private final LocalDateTime timeStamp;

    public EmployeeException(String msg, HttpStatus httpStatus, LocalDateTime timeStamp) {
        this.msg = msg;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public String getMsg() {
        return msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
