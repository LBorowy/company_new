package pl.great.company_new.exception;

public class EmployeeRequestException extends RuntimeException {
    public EmployeeRequestException(String message) {
        super(message);
    }
}
