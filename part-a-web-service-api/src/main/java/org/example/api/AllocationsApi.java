package org.example.api;

import org.example.model.Allocation;
import org.example.service.Comp2005Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("Allocations")
public class AllocationsApi {
    @Resource
    Comp2005Service comp2005Service;

    @GetMapping("")
    public List<Allocation> getAll() {
        return comp2005Service.getAllocationList();
    }

    @GetMapping("/{id}")
    public Allocation getDetail(@PathVariable int id) {
        return comp2005Service.getAllocationDetail( id );
    }
}
