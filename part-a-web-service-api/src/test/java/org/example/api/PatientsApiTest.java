package org.example.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.example.model.Admission;
import org.example.model.Patient;
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
class PatientsApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAll() {
        ParameterizedTypeReference<List<Patient>> responseType = new ParameterizedTypeReference<List<Patient>>() {};
        ResponseEntity<List<Patient>> response = restTemplate.exchange( "/Patients", HttpMethod.GET, null, responseType );

        String patientStr = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"surname\": \"Robinson\",\n" +
                "        \"forename\": \"Viv\",\n" +
                "        \"nhsNumber\": \"1113335555\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"surname\": \"Carter\",\n" +
                "        \"forename\": \"Heather\",\n" +
                "        \"nhsNumber\": \"2224446666\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"surname\": \"Barnes\",\n" +
                "        \"forename\": \"Nicky\",\n" +
                "        \"nhsNumber\": \"6663338888\"\n" +
                "    }\n" +
                "]";


        ObjectMapper mapper = new ObjectMapper();
        List<Patient> expectList = Collections.emptyList();
        try {
            expectList = mapper.readValue( patientStr, new TypeReference<List<Patient>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( response.getBody() ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getDetail() {
        Patient patient = restTemplate.getForObject( "/Patients/1", Patient.class );

        String employeeStr = "{\n" +
                "    \"id\": 1,\n" +
                "    \"surname\": \"Robinson\",\n" +
                "    \"forename\": \"Viv\",\n" +
                "    \"nhsNumber\": \"1113335555\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Patient expect = null;
        try {
            expect = mapper.readValue( employeeStr, Patient.class );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( patient ).isEqualTo( expect );
    }

    @Test
    void getPatientAdmission() {
        ParameterizedTypeReference<List<Admission>> responseType = new ParameterizedTypeReference<List<Admission>>() {};
        ResponseEntity<List<Admission>> response = restTemplate.exchange( "/Patients/1/admissions", HttpMethod.GET, null, responseType );

        String patientStr = "[\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"admissionDate\": \"2020-12-07T22:14:00\",\n" +
                "        \"dischargeDate\": \"0001-01-01T00:00:00\",\n" +
                "        \"patientID\": 1\n" +
                "    }\n" +
                "]";


        ObjectMapper mapper = new ObjectMapper();
        List<Admission> expectList = Collections.emptyList();
        try {
            expectList = mapper.readValue( patientStr, new TypeReference<List<Admission>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( response.getBody() ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getPatientsInHospital() {
        ParameterizedTypeReference<List<Patient>> responseType = new ParameterizedTypeReference<List<Patient>>() {};
        ResponseEntity<List<Patient>> response = restTemplate.exchange( "/Patients/inHospital", HttpMethod.GET, null, responseType );

        String patientStr = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"surname\": \"Robinson\",\n" +
                "        \"forename\": \"Viv\",\n" +
                "        \"nhsNumber\": \"1113335555\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"surname\": \"Carter\",\n" +
                "        \"forename\": \"Heather\",\n" +
                "        \"nhsNumber\": \"2224446666\"\n" +
                "    }\n" +
                "]";


        ObjectMapper mapper = new ObjectMapper();
        List<Patient> expectList = Collections.emptyList();
        try {
            expectList = mapper.readValue( patientStr, new TypeReference<List<Patient>>(){} );
        }catch ( Exception ex ) {
            return;
        }
        Assertions.assertThat( response.getBody() ).containsExactlyElementsOf( expectList );
    }
}