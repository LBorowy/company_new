package pl.great.company_new.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.great.company_new.dto.EmployeeDto;
import pl.great.company_new.entity.Employee;
import pl.great.company_new.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto get(String pesel) {
        Employee employee = employeeRepository.get(pesel);
        return toDto(employee);
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) throws Exception {
        Employee employee = employeeRepository.create(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getPesel(), employeeDto.getSalary());
        return toDto(employee);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.update(toDao(employeeDto));
        return toDto(employee);
    }

    @Override
    public boolean delete(String pesel) {
        return employeeRepository.delete(pesel);
    }

    private EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPesel(employee.getPesel());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }

    private Employee toDao(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPesel(employeeDto.getPesel());
        employee.setSalary(employeeDto.getSalary());
        return employee;
    }
}
