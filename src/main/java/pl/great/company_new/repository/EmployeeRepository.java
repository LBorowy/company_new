package pl.great.company_new.repository;

import pl.great.company_new.entity.Employee;

import java.math.BigDecimal;

public interface EmployeeRepository {
    Employee get(String pesel);
    Employee create(String firstName, String lastName, String pesel, BigDecimal salary);
    Employee update(Employee employee);
    boolean delete(String pesel);
    // todo add getAll method and use it in IT
}
