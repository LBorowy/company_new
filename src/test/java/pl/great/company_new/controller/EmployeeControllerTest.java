package pl.great.company_new.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.great.company_new.dto.EmployeeDto;
import pl.great.company_new.service.EmployeeServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Bean";
    private static final String PESEL = "0";
    private static final BigDecimal SALARY = BigDecimal.ONE;
    private static final String FIRST_NAME_TWO = "John";
    private static final String LAST_NAME_TWO = "Travolta";
    private static final String PESEL_TWO = "1";
    private static final BigDecimal SALARY_TWO = BigDecimal.TEN;

    private static EmployeeDto employeeDto;

    @BeforeEach
    void setUp() throws Exception {
        employeeDto = employeeService.create(new EmployeeDto(FIRST_NAME, LAST_NAME, PESEL, SALARY));
    }

    @AfterEach
    void clear() {
        if (null != employeeService.get(employeeDto.getPesel())) {
            employeeService.delete(employeeDto.getPesel());
        }
    }

    @Test
    void get() throws Exception {
        MvcResult mvcResult = sendRequest(MockMvcRequestBuilders.get("/employee/" + employeeDto.getPesel()).contentType(MediaType.APPLICATION_JSON), HttpStatus.OK);
        EmployeeDto employeeDtoResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EmployeeDto.class);

        assertNotNull(employeeDtoResponse);
        assertEquals(employeeDto.getFirstName(), employeeDtoResponse.getFirstName());
        assertEquals(employeeDto.getLastName(), employeeDtoResponse.getLastName());
        assertEquals(employeeDto.getPesel(), employeeDtoResponse.getPesel());
        assertEquals(employeeDto.getSalary(), employeeDtoResponse.getSalary());
    }

    @Test
    void create() throws Exception {
        String employeeDtoAsJson = objectMapper.writeValueAsString(new EmployeeDto(FIRST_NAME_TWO, LAST_NAME_TWO, PESEL_TWO, SALARY_TWO));
        MvcResult result = sendRequest(MockMvcRequestBuilders.post("/employee").content(employeeDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.OK);

        EmployeeDto employeeDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), EmployeeDto.class);

        assertNotNull(employeeDtoResponse);
        assertEquals(FIRST_NAME_TWO, employeeDtoResponse.getFirstName());
        assertEquals(LAST_NAME_TWO, employeeDtoResponse.getLastName());
        assertEquals(PESEL_TWO, employeeDtoResponse.getPesel());
        assertEquals(SALARY_TWO, employeeDtoResponse.getSalary());
    }

    @Ignore
    void cannotCreateSecondEmployeeWithExistingPesel() throws Exception {
        String employeeDtoAsJson = objectMapper.writeValueAsString(new EmployeeDto(FIRST_NAME_TWO, LAST_NAME_TWO, PESEL, SALARY_TWO));
        MvcResult result = sendRequest(MockMvcRequestBuilders.post("/employee").content(employeeDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.CONFLICT);

        Exception exceptionDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Exception.class);
        assertEquals("Cannot create employee with existing pesel: " + PESEL, exceptionDtoResponse.getMessage());
    }

    @Test
    void update() throws Exception {
        String employeeDtoAsJson = objectMapper.writeValueAsString(new EmployeeDto(FIRST_NAME_TWO, LAST_NAME_TWO, PESEL, SALARY_TWO));
        MvcResult result = sendRequest(MockMvcRequestBuilders.put("/employee").content(employeeDtoAsJson).contentType(MediaType.APPLICATION_JSON), HttpStatus.OK);

        EmployeeDto employeeDtoResponse = objectMapper.readValue(result.getResponse().getContentAsString(), EmployeeDto.class);

        assertNotNull(employeeDtoResponse);
        assertEquals(FIRST_NAME_TWO, employeeDtoResponse.getFirstName());
        assertEquals(LAST_NAME_TWO, employeeDtoResponse.getLastName());
        assertEquals(PESEL, employeeDtoResponse.getPesel());
        assertEquals(SALARY_TWO, employeeDtoResponse.getSalary());
    }

    @Test
    void delete() throws Exception {
        MvcResult result = sendRequest(MockMvcRequestBuilders.delete("/employee/{pesel}", PESEL).contentType(MediaType.APPLICATION_JSON), HttpStatus.OK);

        boolean isDeleted = objectMapper.readValue(result.getResponse().getContentAsString(), Boolean.class);

        assertTrue(isDeleted);
    }

    private MvcResult sendRequest(RequestBuilder request, HttpStatus expectedStatus) throws Exception {
        return mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus.value()))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}