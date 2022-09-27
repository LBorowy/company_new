package pl.great.company_new.service;

import pl.great.company_new.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto get(String pesel);
    List<EmployeeDto> getAll();
    EmployeeDto create(EmployeeDto employeeDto);
    EmployeeDto update(EmployeeDto employeeDto);
    boolean delete(String pesel);
}
