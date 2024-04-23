package org.example.model;

import lombok.Data;

@Data
public class Allocation {
    private int id;
    private int admissionID;
    private int employeeID;
    private String startTime;
    private String endTime;
}
