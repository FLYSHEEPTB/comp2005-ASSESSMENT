package org.example.model;

import lombok.Data;

@Data
public class Admission {
    private int id;
    private String admissionDate;
    private String dischargeDate;
    private int patientID;
}
