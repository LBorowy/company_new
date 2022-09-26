package pl.great.company_new.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.great.company_new.entity.Employee;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryImplTest {

    private static final String FIRST_NAME = "TEST_NAME";
    private static final String LAST_NAME = "TEST_LAST_NAME";
    private static final String PESEL = "TEST_PESEL";
    private static final BigDecimal SALARY = BigDecimal.TEN;
    private static final String FIRST_NAME_UPDATED = "TEST_NAME_UPDATED";
    private static final String LAST_NAME_UPDATED = "TEST_LAST_NAME_UPDATED";
    private static final String PESEL_UPDATED = "TEST_PESEL_UPDATED";
    private static final BigDecimal SALARY_UPDATED = BigDecimal.ONE;

    private Employee employee;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        this.employee = this.employeeRepository.create(FIRST_NAME, LAST_NAME, PESEL, SALARY);
    }

    @AfterEach
    void clear() {
        Employee employee = this.employeeRepository.get(PESEL) != null ? this.employeeRepository.get(PESEL) : this.employeeRepository.get(PESEL_UPDATED);
        if (null != employee) {
            this.employeeRepository.delete(employee.getPesel());
        }
    }

    @Test
    void get() {
        Employee employee = this.employeeRepository.get(PESEL);

        assertEquals(FIRST_NAME, employee.getFirstName());
        assertEquals(LAST_NAME, employee.getLastName());
        assertEquals(PESEL, employee.getPesel());
        assertEquals(SALARY, employee.getSalary());
    }

    @Test
    void create() {
        assertEquals(FIRST_NAME, employee.getFirstName());
        assertEquals(LAST_NAME, employee.getLastName());
        assertEquals(PESEL, employee.getPesel());
        assertEquals(SALARY, employee.getSalary());
    }

    @Test
    void cannotCreateEmployeeWithExistingPesel() {
        assertThrows(Exception.class, () -> this.employeeRepository.create("x", "x", PESEL, BigDecimal.ZERO));
    }

    @Test
    void update() {
        Employee employeeToUpdate = new Employee(FIRST_NAME_UPDATED, LAST_NAME_UPDATED, PESEL, SALARY_UPDATED);

        Employee updatedEmployee = this.employeeRepository.update(employeeToUpdate);

        assertEquals(FIRST_NAME_UPDATED, updatedEmployee.getFirstName());
        assertEquals(LAST_NAME_UPDATED, updatedEmployee.getLastName());
        assertEquals(PESEL, updatedEmployee.getPesel());
        assertEquals(SALARY_UPDATED, updatedEmployee.getSalary());
    }

    @Test
    void delete() {
        boolean isDeleted = this.employeeRepository.delete(PESEL);

        Employee employee = this.employeeRepository.get(PESEL);

        assertTrue(isDeleted);
        assertNull(employee);
    }


}