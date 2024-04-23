package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Request;
import org.example.http.HttpService;
import org.example.model.Admission;
import org.example.model.Allocation;
import org.example.model.Employee;
import org.example.model.Patient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Comp2005Service {

    @Resource
    HttpService httpService;

    private final String apiHost = "https://web.socem.plymouth.ac.uk/COMP2005/api/";

    public List<Admission> getAdmissionList() {

        String response = getApiResponse( "Admissions" );
        if ( StringUtils.isEmpty( response ) ) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Admission> admissionList = mapper.readValue( response, new TypeReference<List<Admission>>(){} );

            return admissionList;
        }catch ( Exception ex ) {
            return null;
        }
    }

    public Admission getAdmissionDetail(int id) {
        String response = getApiResponse( "Admissions/" + id );
        if ( StringUtils.isEmpty( response ) ) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            Admission admission = mapper.readValue( response, Admission.class );

            return admission;
        }catch ( Exception ex ) {
            return null;
        }
    }

    public List<Allocation> getAllocationList() {

        String response = getApiResponse( "Allocations" );
        if ( StringUtils.isEmpty( response ) ) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Allocation> allocationList = mapper.readValue( response, new TypeReference<List<Allocation>>(){} );

            return allocationList;
        }catch ( Exception ex ) {
            return null;
        }
    }

    public Allocation getAllocationDetail(int id) {
        String response = getApiResponse( "Allocations/" + id );
        if ( StringUtils.isEmpty( response ) ) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            Allocation allocation = mapper.readValue( response, Allocation.class );

            return allocation;
        }catch ( Exception ex ) {
            return null;
        }
    }

    public List<Employee> getEmployeeList() {

        String response = getApiResponse( "Employees" );
        if ( StringUtils.isEmpty( response ) ) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Employee> employeeList = mapper.readValue( response, new TypeReference<List<Employee>>(){} );

            return employeeList;
        }catch ( Exception ex ) {
            return null;
        }
    }

    public Employee getEmployeeDetail(int id) {
        String response = getApiResponse( "Employees/" + id );
        if ( StringUtils.isEmpty( response ) ) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            Employee employee = mapper.readValue( response, Employee.class );

            return employee;
        }catch ( Exception ex ) {
            return null;
        }
    }

    public List<Patient> getPatientList() {

        String response = getApiResponse( "Patients" );
        if ( StringUtils.isEmpty( response ) ) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Patient> patientList = mapper.readValue( response, new TypeReference<List<Patient>>(){} );

            return patientList;
        }catch ( Exception ex ) {
            return null;
        }
    }

    public Patient getPatientDetail(int id) {
        String response = getApiResponse( "Patients/" + id );
        if ( StringUtils.isEmpty( response ) ) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            Patient patient = mapper.readValue( response, Patient.class );

            return patient;
        }catch ( Exception ex ) {
            return null;
        }
    }

    private String getApiResponse(String url) {
        Request apiRequest = new Request.Builder()
                .url( apiHost + url )
                .build();

        String response = httpService.getRequest( apiRequest );

        return response;
    }

    /**
     * F1 A list of all admissions for a specific patient
     * @param id int
     * @return List<Admission>
     */
    public List<Admission> getPatientAdmission(int id) {
        List<Admission>  admissionList = getAdmissionList();

        List<Admission> patientAdmissionList = new ArrayList<>();

        admissionList.forEach( admission -> {
            if ( admission.getPatientID() == id ) {
                patientAdmissionList.add( admission );
            }
        } );
        return patientAdmissionList;
    }

    /**
     * F2 -A list of patients currently admitted (who have not been discharged yet)
     * @return List<Patient>
     */
    public List<Patient> getPatientInHospital() {
        List<Admission>  admissionList = getAdmissionList();

        List<Patient> patientList = getPatientList();

        List<Integer> patientIds = new ArrayList<>();
        admissionList.forEach( admission -> {
            patientIds.add( admission.getPatientID() );
        } );

        List<Patient> inHospitalPatientList = new ArrayList<>();
        patientList.forEach( patient -> {
            if ( patientIds.contains( patient.getId() ) ) {
                inHospitalPatientList.add( patient );
            }
        } );
        return inHospitalPatientList;
    }

    /**
     * F3 -Identify which member of staff has the most admissions
     * @return
     */
    public Employee getMostAdmissionEmployee() {
        List<Allocation> allocationList = getAllocationList();

        Map<Integer, Integer> allocationMap = new HashMap<>();

        allocationList.forEach( allocation -> {
            Integer count = allocationMap.getOrDefault( allocation.getEmployeeID(), 0 );
            allocationMap.put( allocation.getEmployeeID(), count + 1 );
        } );

        int maxCount = 0;
        int maxCountEmployeeId = 0;

        for ( Map.Entry<Integer, Integer> entry : allocationMap.entrySet() ) {
            if ( entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxCountEmployeeId = entry.getKey();
            }
        }
        Employee employee = getEmployeeDetail( maxCountEmployeeId );

        return employee;
    }

    /**
     * F4 -A list of staff who have no (zero) admissions
     * @return List<Employee>
     */
    public List<Employee> getEmployeeWithoutAdmission() {
        List<Employee>  employeeList = getEmployeeList();


        List<Allocation> allocationList = getAllocationList();

        List<Integer> employeeIds = new ArrayList<>();
        allocationList.forEach( allocation -> {
            employeeIds.add( allocation.getEmployeeID() );
        } );
        List<Employee> withoutAdmission = new ArrayList<>();
        employeeList.forEach( employee -> {
            if ( !employeeIds.contains( employee.getId() ) ) {
                withoutAdmission.add( employee );
            }
        } );
        return withoutAdmission;
    }
}
