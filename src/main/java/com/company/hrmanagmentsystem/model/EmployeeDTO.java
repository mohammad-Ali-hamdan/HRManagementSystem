package com.company.hrmanagmentsystem.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class EmployeeDTO {

    private int id;
    private String name;
    private String email;
    private String address;
    private int departmentId;
}
