package pl.great.company_new.service;

import pl.great.company_new.dto.EmployeeDto;

import java.math.BigDecimal;

public interface EmployeeService {
    EmployeeDto get(String pesel);
    EmployeeDto create(String firstName, String lastName, String pesel, BigDecimal salary) throws Exception;
    EmployeeDto update(EmployeeDto employeeDto);
    boolean delete(String pesel);
}
