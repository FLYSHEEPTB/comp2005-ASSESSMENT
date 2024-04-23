package org.example.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.example.model.Admission;
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
class AdmissionsApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAll() {
        ParameterizedTypeReference<List<Admission>> responseType = new ParameterizedTypeReference<List<Admission>>() {};
        ResponseEntity<List<Admission>> response = restTemplate.exchange( "/Admissions", HttpMethod.GET, null, responseType );

        String admissionStr = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"admissionDate\": \"2020-11-28T16:45:00\",\n" +
                "        \"dischargeDate\": \"2020-11-28T23:56:00\",\n" +
                "        \"patientID\": 2\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"admissionDate\": \"2020-12-07T22:14:00\",\n" +
                "        \"dischargeDate\": \"0001-01-01T00:00:00\",\n" +
                "        \"patientID\": 1\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"admissionDate\": \"2021-09-23T21:50:00\",\n" +
                "        \"dischargeDate\": \"2021-09-27T09:56:00\",\n" +
                "        \"patientID\": 2\n" +
                "    }\n" +
                "]";


        ObjectMapper mapper = new ObjectMapper();
        List<Admission> expectList = Collections.emptyList();
        try {
            expectList = mapper.readValue( admissionStr, new TypeReference<List<Admission>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( response.getBody() ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getDetail() {
        Admission admission = restTemplate.getForObject( "/Admissions/1", Admission.class );

        String admissionStr = "{\n" +
                "    \"id\": 1,\n" +
                "    \"admissionDate\": \"2020-11-28T16:45:00\",\n" +
                "    \"dischargeDate\": \"2020-11-28T23:56:00\",\n" +
                "    \"patientID\": 2\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        try {
            Admission expect = mapper.readValue( admissionStr, Admission.class );

            Assertions.assertThat( admission ).isEqualTo( expect );
        }catch ( Exception ex ) {
            Assertions.assertThat( true ).isEqualTo( false );
        }

        // test empty
        Admission admissionEmpty = restTemplate.getForObject( "/Admissions/6", Admission.class );
        Assertions.assertThat( admissionEmpty ).isEqualTo( null );
    }
}