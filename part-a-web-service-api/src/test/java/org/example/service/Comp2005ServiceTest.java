package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.example.model.Admission;
import org.example.model.Allocation;
import org.example.model.Employee;
import org.example.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class Comp2005ServiceTest {
    @Resource
    Comp2005Service comp2005Service;

    @Test
    void getAdmissionList() {
        List<Admission> admissionList = comp2005Service.getAdmissionList();

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
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"admissionDate\": \"2024-02-23T21:50:00\",\n" +
                "        \"dischargeDate\": \"2021-09-27T09:56:00\",\n" +
                "        \"patientID\": 5\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"admissionDate\": \"2024-04-12T22:55:00\",\n" +
                "        \"dischargeDate\": \"2024-04-14T11:36:00\",\n" +
                "        \"patientID\": 5\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"admissionDate\": \"2024-04-19T21:50:00\",\n" +
                "        \"dischargeDate\": \"0001-01-01T00:00:00\",\n" +
                "        \"patientID\": 5\n" +
                "    }\n" +
                "]";


        ObjectMapper mapper = new ObjectMapper();
        List<Admission> expectList = Collections.emptyList();
        try {
            expectList = mapper.readValue( admissionStr, new TypeReference<List<Admission>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( admissionList ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getAdmissionDetail() {
        Admission admission = comp2005Service.getAdmissionDetail( 1 );

        String admissionStr = "{\n" +
                "    \"id\": 1,\n" +
                "    \"admissionDate\": \"2020-11-28T16:45:00\",\n" +
                "    \"dischargeDate\": \"2020-11-28T23:56:00\",\n" +
                "    \"patientID\": 2\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Admission expect = null;
        try {
            expect = mapper.readValue( admissionStr, Admission.class );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( admission ).isEqualTo( expect );
    }

    @Test
    void getAllocationList() {
        List<Allocation> allocationList = comp2005Service.getAllocationList();

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
        Assertions.assertThat( allocationList ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getAllocationDetail() {
        Allocation allocation = comp2005Service.getAllocationDetail( 1 );

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
    }

    @Test
    void getEmployeeList() {
        List<Employee> employeeList = comp2005Service.getEmployeeList();

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
        Assertions.assertThat( employeeList ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getEmployeeDetail() {
        Employee employee = comp2005Service.getEmployeeDetail( 1 );

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
    void getPatientList() {
        List<Patient> patientList = comp2005Service.getPatientList();

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
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"surname\": \"King\",\n" +
                "        \"forename\": \"Jacky\",\n" +
                "        \"nhsNumber\": \"7773338888\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"surname\": \"Sharpe\",\n" +
                "        \"forename\": \"Rhi\",\n" +
                "        \"nhsNumber\": \"6663339999\"\n" +
                "    }\n" +
                "]";


        ObjectMapper mapper = new ObjectMapper();
        List<Patient> expectList = Collections.emptyList();
        try {
            expectList = mapper.readValue( patientStr, new TypeReference<List<Patient>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( patientList ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getPatientDetail() {
        Patient patient = comp2005Service.getPatientDetail( 1 );

        String patientStr = "{\n" +
                "    \"id\": 1,\n" +
                "    \"surname\": \"Robinson\",\n" +
                "    \"forename\": \"Viv\",\n" +
                "    \"nhsNumber\": \"1113335555\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Patient expect;
        try {
            expect = mapper.readValue( patientStr, Patient.class );
        }catch ( Exception ex ) {
            return;
        }
        Assertions.assertThat( patient ).isEqualTo( expect );
    }

    @Test
    void getPatientAdmission() {
        List<Admission> admissionList = comp2005Service.getPatientAdmission( 1 );

        String admissionStr = "[\n" +
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
            expectList = mapper.readValue( admissionStr, new TypeReference<List<Admission>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( admissionList ).containsExactlyElementsOf( expectList );

        // corner case
        admissionList = comp2005Service.getPatientAdmission( 6 );

        expectList = Collections.emptyList();

        Assertions.assertThat( admissionList ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getPatientInHospital() {
        List<Patient> patientList = comp2005Service.getPatientInHospital( );

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
                "        \"id\": 5,\n" +
                "        \"surname\": \"Sharpe\",\n" +
                "        \"forename\": \"Rhi\",\n" +
                "        \"nhsNumber\": \"6663339999\"\n" +
                "    }\n" +
                "]";

        ObjectMapper mapper = new ObjectMapper();
        List<Patient> expectList;
        try {
            expectList = mapper.readValue( patientStr, new TypeReference<List<Patient>>(){} );
        }catch ( Exception ex ) {
            return;
        }
        Assertions.assertThat( patientList ).containsExactlyElementsOf( expectList );
    }

    @Test
    void getMostAdmissionEmployee() {
        Employee employee = comp2005Service.getMostAdmissionEmployee( );

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
    void getEmployeeWithoutAdmission() {
        List<Employee> employeeList = comp2005Service.getEmployeeWithoutAdmission( );

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
        List<Employee> expectList = Collections.emptyList();
        try {
            expectList = mapper.readValue( employeeStr, new TypeReference<List<Employee>>(){} );
        }catch ( Exception ex ) {
            System.out.println("error");
        }
        Assertions.assertThat( employeeList ).containsExactlyElementsOf( expectList );
    }
}