package pl.great.company_new.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.great.company_new.dto.EmployeeDto;
import pl.great.company_new.service.EmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{pesel}")
    public EmployeeDto get(@PathVariable String pesel) {
        return employeeService.get(pesel);
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto employeeDto) {
        return this.employeeService.create(employeeDto);
    }

    @PutMapping
    public EmployeeDto update(@RequestBody EmployeeDto employeeDto) {
        return this.employeeService.update(employeeDto);
    }

    @DeleteMapping("/{pesel}")
    public boolean delete(@PathVariable String pesel) {
        return this.employeeService.delete(pesel);
    }
}
