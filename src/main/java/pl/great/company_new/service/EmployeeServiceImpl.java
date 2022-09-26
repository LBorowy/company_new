package pl.great.company_new.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.great.company_new.dto.EmployeeDto;
import pl.great.company_new.entity.Employee;
import pl.great.company_new.repository.EmployeeRepository;

import java.util.Objects;

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
        // todo add validation
        return toDto(employee);
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
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
        if (Objects.nonNull(employee)) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setFirstName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeeDto.setPesel(employee.getPesel());
            employeeDto.setSalary(employee.getSalary());
            employeeDto.setCreated(employee.getCreated());
            return employeeDto;
        }
        return null;
    }

    private Employee toDao(EmployeeDto employeeDto) {
        if (Objects.nonNull(employeeDto)) {
            return new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getPesel(), employeeDto.getSalary());
        }
        return null;
    }
}
