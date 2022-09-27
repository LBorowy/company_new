package pl.great.company_new.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.great.company_new.dto.EmployeeDto;
import pl.great.company_new.entity.Employee;
import pl.great.company_new.repository.EmployeeRepositoryImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    private static final String PESEL = "PESEL";
    private static final BigDecimal SALARY = BigDecimal.TEN;
    private static final List<Employee> employeeList = new ArrayList<>();

    private Employee employee;
    private EmployeeDto employeeDto;

    @Mock
    EmployeeRepositoryImpl employeeRepository;
    @InjectMocks
    EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employee = new Employee(FIRST_NAME, LAST_NAME, PESEL, SALARY);
        employeeDto = new EmployeeDto(FIRST_NAME, LAST_NAME, PESEL, SALARY);
        employeeList.add(employee);
    }

    @AfterEach
    void clear() {
        employeeService.getAll().forEach(e -> {
            employeeService.delete(e.getPesel());
        });
        employeeList.clear();
    }

    @Test
    void get() {
        when(employeeRepository.get(PESEL)).thenReturn(employee);

        EmployeeDto employeeDto = employeeService.get(PESEL);

        assertNotNull(employeeDto);
        assertEquals(FIRST_NAME, employeeDto.getFirstName());
        assertEquals(LAST_NAME, employeeDto.getLastName());
        assertEquals(PESEL, employeeDto.getPesel());
        assertEquals(SALARY, employeeDto.getSalary());
    }

    @Test
    void getAll() {
        when(employeeRepository.getAll()).thenReturn(employeeList);

        List<EmployeeDto> employeeDtoList = employeeService.getAll();

        assertEquals(1, employeeDtoList.size());
    }

    @Test
    void create() {
        when(employeeRepository.create(anyString(), anyString(), anyString(), any())).thenReturn(employee);

        EmployeeDto createdEmployeeDto = employeeService.create(employeeDto);

        assertNotNull(createdEmployeeDto);
        assertEquals(FIRST_NAME, createdEmployeeDto.getFirstName());
        assertEquals(LAST_NAME, createdEmployeeDto.getLastName());
        assertEquals(PESEL, createdEmployeeDto.getPesel());
        assertEquals(SALARY, createdEmployeeDto.getSalary());
    }

    @Test
    void update() {
        when(employeeRepository.update(any(Employee.class))).thenReturn(employee);

        EmployeeDto updatedEmployeeDto = employeeService.update(employeeDto);

        assertNotNull(updatedEmployeeDto);
        assertEquals(FIRST_NAME, updatedEmployeeDto.getFirstName());
        assertEquals(LAST_NAME, updatedEmployeeDto.getLastName());
        assertEquals(PESEL, updatedEmployeeDto.getPesel());
        assertEquals(SALARY, updatedEmployeeDto.getSalary());
    }

    @Test
    void delete() {
        when(employeeRepository.delete(PESEL)).thenReturn(Boolean.TRUE);

        boolean isEmployeeDeleted = employeeService.delete(PESEL);

        assertTrue(isEmployeeDeleted);
    }
}