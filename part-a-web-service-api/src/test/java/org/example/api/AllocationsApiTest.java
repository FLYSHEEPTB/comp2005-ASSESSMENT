package org.example.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.example.model.Allocation;
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
class AllocationsApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAll() {
        ParameterizedTypeReference<List<Allocation>> responseType = new ParameterizedTypeReference<List<Allocation>>() {};
        ResponseEntity<List<Allocation>> response = restTemplate.exchange( "/Allocations", HttpMethod.GET, null, responseType );

        String allocationStr = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"admissionID\": 1,\n" +
                "        \"employeeID\": 4,\n" +
                "        \"startTime\": \"2020-11-28T16:45:00\",\n" +
                "        \"endTime\": \"2020-11-28T23:56:00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"admissionID\": 3,\n" +
                "        \"employeeID\": 4,\n" +
                "        \"startTime\": \"2021-09-23T21:50:00\",\n" +
                "        \"endTime\": \"2021-09-24T09:50:00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"admissionID\": 2,\n" +
                "        \"employeeID\": 6,\n" +
                "        \"startTime\": \"2020-12-07T22:14:00\",\n" +
                "        \"endTime\": \"2020-12-08T20:00:00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"admissionID\": 2,\n" +
                "        \"employeeID\": 3,\n" +
                "        \"startTime\": \"2020-12-08T20:00:00\",\n" +
                "        \"endTime\": \"2020-12-09T20:00:00\"\n" +
                "    }\n" +
                "]";


        ObjectMapper mapper = new ObjectMapper();
        List<Allocation> expectList = Collections.emptyList();
        try {
            expectList = mapper.readValue( allocationStr, new TypeReference<List<Allocation>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( response.getBody() ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getDetail() {
        Allocation allocation = restTemplate.getForObject( "/Allocations/1", Allocation.class );

        String allocationStr = "{\n" +
                "    \"id\": 1,\n" +
                "    \"admissionID\": 1,\n" +
                "    \"employeeID\": 4,\n" +
                "    \"startTime\": \"2020-11-28T16:45:00\",\n" +
                "    \"endTime\": \"2020-11-28T23:56:00\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Allocation expect = null;
        try {
            expect = mapper.readValue( allocationStr, Allocation.class );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( allocation ).isEqualTo( expect );

        // test empty
        Allocation allocationEmpty = restTemplate.getForObject( "/Allocations/6", Allocation.class );

        Assertions.assertThat( allocationEmpty ).isEqualTo( null );
    }
}