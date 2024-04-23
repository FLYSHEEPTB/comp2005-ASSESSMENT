package org.example.model;

import lombok.Data;

@Data
public class Patient {
    private int id;
    private String surname;
    private String forename;
    private String nhsNumber;
}
