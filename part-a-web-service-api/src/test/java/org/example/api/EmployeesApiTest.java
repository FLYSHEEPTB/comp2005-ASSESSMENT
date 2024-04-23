package org.example.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.example.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class EmployeesApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAll() {
        ParameterizedTypeReference<List<Employee>> responseType = new ParameterizedTypeReference<List<Employee>>() {};
        ResponseEntity<List<Employee>> response = restTemplate.exchange( "/Employees", HttpMethod.GET, null, responseType );

        String employeeStr = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"surname\": \"Finley\",\n" +
                "        \"forename\": \"Sarah\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"surname\": \"Jackson\",\n" +
                "        \"forename\": \"Robert\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"surname\": \"Allen\",\n" +
                "        \"forename\": \"Alice\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"surname\": \"Jones\",\n" +
                "        \"forename\": \"Sarah\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"surname\": \"Wicks\",\n" +
                "        \"forename\": \"Patrick\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"surname\": \"Smith\",\n" +
                "        \"forename\": \"Alice\"\n" +
                "    }\n" +
                "]";


        ObjectMapper mapper = new ObjectMapper();
        List<Employee> expectList = Collections.emptyList();
        try {
            expectList = mapper.readValue( employeeStr, new TypeReference<List<Employee>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( response.getBody() ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getDetail() {
        Employee employee = restTemplate.getForObject( "/Employees/1", Employee.class );

        String employeeStr = "{\n" +
                "    \"id\": 1,\n" +
                "    \"surname\": \"Finley\",\n" +
                "    \"forename\": \"Sarah\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Employee expect = null;
        try {
            expect = mapper.readValue( employeeStr, Employee.class );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( employee ).isEqualTo( expect );
    }

    @Test
    void getMaxAdmissionEmployee() {
        Employee employee = restTemplate.getForObject( "/Employees/maxAdmissions", Employee.class );

        String employeeStr = "{\n" +
                "    \"id\": 4,\n" +
                "    \"surname\": \"Jones\",\n" +
                "    \"forename\": \"Sarah\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Employee expect = null;
        try {
            expect = mapper.readValue( employeeStr, Employee.class );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( employee ).isEqualTo( expect );
    }

    @Test
    void getNoAdmissionEmployee() {
        ParameterizedTypeReference<List<Employee>> responseType = new ParameterizedTypeReference<List<Employee>>() {};
        ResponseEntity<List<Employee>> response = restTemplate.exchange( "/Employees/noAdmissions", HttpMethod.GET, null, responseType );

        String employeeStr = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"surname\": \"Finley\",\n" +
                "        \"forename\": \"Sarah\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"surname\": \"Jackson\",\n" +
                "        \"forename\": \"Robert\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"surname\": \"Wicks\",\n" +
                "        \"forename\": \"Patrick\"\n" +
                "    }\n" +
                "]";

        ObjectMapper mapper = new ObjectMapper();
        List<Employee> expect = Collections.emptyList();
        try {
            expect = mapper.readValue( employeeStr, new TypeReference<List<Employee>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( response.getBody() ).isEqualTo( expect );
    }
}