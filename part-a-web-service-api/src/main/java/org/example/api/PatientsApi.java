package org.example.api;

import org.example.model.Admission;
import org.example.model.Patient;
import org.example.service.Comp2005Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("Patients")
public class PatientsApi {
    @Resource
    Comp2005Service comp2005Service;

    @GetMapping("")
    public List<Patient> getAll() {

        return comp2005Service.getPatientList();
    }

    @GetMapping("/{id}")
    public Patient getDetail(@PathVariable int id) {
        return comp2005Service.getPatientDetail( id );
    }

    /**
     * F1 -A list of all admissions for a specific patient
     * @return List<Admission>
     */
    @GetMapping("/{id}/admissions")
    public List<Admission> getPatientAdmission(@PathVariable int id) {
        return comp2005Service.getPatientAdmission( id );
    }

    /**
     * F2 -A list of patients currently admitted (who have not been discharged yet)
     * @return List<Patient>
     */
    @GetMapping("/inHospital")
    public List<Patient> getPatientsInHospital() {
        return comp2005Service.getPatientInHospital( );
    }

}
