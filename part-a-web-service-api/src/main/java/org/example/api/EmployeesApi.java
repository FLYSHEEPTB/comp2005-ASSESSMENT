package org.example.api;

import org.example.model.Employee;
import org.example.service.Comp2005Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("Employees")
public class EmployeesApi {
    @Resource
    Comp2005Service comp2005Service;

    @GetMapping("")
    public List<Employee> getAll() {
        return comp2005Service.getEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getDetail(@PathVariable int id) {
        return comp2005Service.getEmployeeDetail( id );
    }

    /**
     * F4 -A list of staff who have no (zero) admissions
     * @return List<Admission>
     */
    @GetMapping("/maxAdmissions")
    public Employee getMaxAdmissionEmployee() {
        return comp2005Service.getMostAdmissionEmployee( );
    }

    /**
     * F4 -A list of staff who have no (zero) admissions
     * @return List<Admission>
     */
    @GetMapping("/noAdmissions")
    public List<Employee> getNoAdmissionEmployee() {
        return comp2005Service.getEmployeeWithoutAdmission( );
    }
}
