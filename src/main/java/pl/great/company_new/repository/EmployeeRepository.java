package pl.great.company_new.repository;

import pl.great.company_new.entity.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository {
    Employee get(String pesel);
    List<Employee> getAll();
    Employee create(String firstName, String lastName, String pesel, BigDecimal salary);
    Employee update(Employee employee);
    boolean delete(String pesel);
}
