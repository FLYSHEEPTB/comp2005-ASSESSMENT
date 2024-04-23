package org.example.api;

import org.example.model.Admission;
import org.example.service.Comp2005Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("Admissions")
public class AdmissionsApi {
    @Resource
    Comp2005Service comp2005Service;
    @GetMapping("")
    public List<Admission> getAll() {
        return comp2005Service.getAdmissionList();
    }

    @GetMapping("/{id}")
    public Admission getDetail(@PathVariable int id) {
        return comp2005Service.getAdmissionDetail( id );
    }
}
