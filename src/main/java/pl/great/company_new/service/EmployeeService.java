package pl.great.company_new.service;

import pl.great.company_new.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto get(String pesel);
    EmployeeDto create(EmployeeDto employeeDto) throws Exception;
    EmployeeDto update(EmployeeDto employeeDto);
    boolean delete(String pesel);
}
