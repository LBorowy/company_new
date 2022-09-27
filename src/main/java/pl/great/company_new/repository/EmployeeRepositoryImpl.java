package pl.great.company_new.repository;

import org.springframework.stereotype.Repository;
import pl.great.company_new.entity.Employee;
import pl.great.company_new.exception.EmployeeRequestException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private List<Employee> employeeList = new ArrayList<>();

    @Override
    public Employee get(String pesel) {
        for (Employee employee : employeeList) {
            if (Objects.equals(employee.getPesel(), pesel)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return employeeList;
    }

    @Override
    public Employee create(String firstName, String lastName, String pesel, BigDecimal salary) {
        Employee employee = new Employee(firstName, lastName, pesel, salary);
        Employee existingEmployee = this.get(employee.getPesel());
        if (null != existingEmployee) {
            throw new EmployeeRequestException("Cannot create employee with existing pesel: " + employee.getPesel());
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employeeToUpdate) {
        Employee oldEmployee = this.get(employeeToUpdate.getPesel());
        int index = employeeList.indexOf(oldEmployee);
        employeeList.set(index, employeeToUpdate);
        return employeeToUpdate;
    }

    @Override
    public boolean delete(String pesel) {
        Employee employeeToDelete = this.get(pesel);
        return employeeToDelete != null ? employeeList.remove(employeeToDelete) : false;
    }
}
