package pl.great.company_new.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.great.company_new.dto.EmployeeDto;
import pl.great.company_new.entity.Employee;
import pl.great.company_new.repository.EmployeeRepository;

import java.math.BigDecimal;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto get(String pesel) {
        return toDto(employeeRepository.get(pesel));
    }

    @Override
    public EmployeeDto create(String firstName, String lastName, String pesel, BigDecimal salary) throws Exception {
        Employee employee = employeeRepository.create(firstName, lastName, pesel, salary);
        return toDto(employee);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDtoToUpdate) {
        Employee employee = employeeRepository.get(employeeDtoToUpdate.getPesel());
        return toDto(employeeRepository.update(employee));
    }

    @Override
    public boolean delete(String pesel) {
        Employee employeeToRemove = employeeRepository.get(pesel);
        return employeeRepository.delete(employeeToRemove.getPesel());
    }

    private EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPesel(employee.getPesel());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }
}
